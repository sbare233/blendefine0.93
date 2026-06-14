package triango;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MCTS {
    private static final double EXPLORATION_PARAMETER = 1.41; // √2
    private static final int SIMULATION_LIMIT = 1000;
    private static final double CAPTURE_BONUS = 6;
    private Random random;
    private ExecutorService executorService;
    private boolean useNeuralNetwork; // 是否使用神经网络
    private static final double LIBERTY_PENALTY = 3; // 可根据需要调整
    
    public MCTS() {
        random = new Random();
        this.useNeuralNetwork = false;
    }
    
    // 使用神经网络的节点选择方法
    public MCTSNode selectPromisingNodeWithNN(MCTSNode rootNode) {
        MCTSNode node = rootNode;
        while (!node.isTerminalNode()) {
            if (!node.isFullyExpanded()) {
                return node;
            } else {
                node = findBestNodeWithPUCT(node);
            }
        }
        
        return node;
    }
    
    // 使用PUCT公式（包含先验概率）选择最佳节点
    private MCTSNode findBestNodeWithPUCT(MCTSNode node) {
        double bestPUCTValue = Double.NEGATIVE_INFINITY;
        MCTSNode bestNode = null;
        
        for (MCTSNode child : node.getChildren()) {
            double puctValue = calculatePUCT(child);
            if (puctValue > bestPUCTValue) {
                bestPUCTValue = puctValue;
                bestNode = child;
            }
        }
        
        return bestNode;
    }
    
 // 同样修改calculatePUCT方法
    private double calculatePUCT(MCTSNode node) {
        if (node.getVisitCount() == 0) {
            return Integer.MAX_VALUE;
        }
        
        MCTSNode parent = node.getParent();
        if (parent == null) {
            return 0;
        }
        
        double winRatio = node.getWinScore() / node.getVisitCount();
        double priorProbability = node.getPriorProbability();
        
        double exploration = EXPLORATION_PARAMETER * priorProbability * 
                            Math.sqrt(parent.getVisitCount()) / (1 + node.getVisitCount());
        
        // 添加吃子奖励
        double captureReward = CAPTURE_BONUS * node.getCaptureCount();
        
        // 添加气数惩罚
        double libertyPenalty = 0;
        if (node.getLibertyCount() == 1) 
        {
            libertyPenalty = LIBERTY_PENALTY;
        } else if (node.getLibertyCount() < 3) {
            libertyPenalty = LIBERTY_PENALTY/3;
        }
        
        return winRatio + exploration + captureReward - libertyPenalty;
    }
  
    // 原始模拟方法（保留兼容性）
    public int simulateRandomPlayout(MCTSNode node) {
        TriangoBoard tempState = new TriangoBoard(node.getState());
        int playoutResult = -1;
        int moves = 0;
        int maxMoves = 200;

        while (!tempState.isGameOver() && moves < maxMoves) {
            // 使用缓存的合法移动
            List<int[]> legalMoves = TriangoBoard.cachedLegalMoves;
           
            // 有一定概率放弃落子（模拟人类玩家的放弃决策）
            if (legalMoves.isEmpty() 
                    || (legalMoves.size() > 10 && Math.random() < 0.1)
                    ) {
                tempState.pass();
            } else {
                int[] randomMove = legalMoves.get(random.nextInt(legalMoves.size()));
                tempState.makeMove(randomMove[0], randomMove[1]);
            }
            moves++;
        }
        playoutResult = tempState.getWinner();
        return playoutResult;
    }
    
 // 修改calculateUCT方法
    private double calculateUCT(MCTSNode node) {
        if (node.getVisitCount() == 0) {
            return Integer.MAX_VALUE;
        }
        
        MCTSNode parent = node.getParent();
        if (parent == null) {
            return 0;
        }
        
        double winRatio = node.getWinScore() / node.getVisitCount();
        double exploration = EXPLORATION_PARAMETER * 
                            Math.sqrt(Math.log(parent.getVisitCount() + 1) / (node.getVisitCount() + 1));
        
        // 添加吃子奖励
        double captureReward = CAPTURE_BONUS * node.getCaptureCount();
        
        // 添加气数惩罚 - 气数越少，惩罚越大
        double libertyPenalty = 0;
        if (node.getLibertyCount() == 1) 
        {
            libertyPenalty = LIBERTY_PENALTY; // 只剩一口气时给予最大惩罚
        } else if (node.getLibertyCount() < 3) {
            libertyPenalty = LIBERTY_PENALTY/ 3; // 气数少时给予部分惩罚
        }
        
        return winRatio + exploration + captureReward - libertyPenalty;
    }

    
    // 原始节点选择方法（保留兼容性）
    public MCTSNode selectPromisingNode(MCTSNode rootNode) {
        MCTSNode node = rootNode;
        while (!node.isTerminalNode()) {
            if (!node.isFullyExpanded()) {
                return node;
            } else {
                node = findBestNodeWithUCT(node);
            }
        }
        
        return node;
    }
    
    // 原始最佳节点查找（保留兼容性）
    private MCTSNode findBestNodeWithUCT(MCTSNode node) {
        double bestUCTValue = Double.NEGATIVE_INFINITY;
        MCTSNode bestNode = null;
        
        for (MCTSNode child : node.getChildren()) {
            double uctValue = calculateUCT(child);
            if (uctValue > bestUCTValue) {
                bestUCTValue = uctValue;
                bestNode = child;
            }
        }
        
        return bestNode;
    }
    
    public void backPropagation(MCTSNode nodeToExplore, int playoutResult) {
        MCTSNode tempNode = nodeToExplore;
        
        while (tempNode != null) {
            tempNode.incrementVisit();
            double scoreIncrement = 0.0;
           
            TriangoBoard state = tempNode.getState();
            int[] scores = state.calculateScores();
            // 领地奖励（根据围空大小给予奖励）
            double territoryBonus = (state.getCurrentPlayer() == TriangoBoard.BLACK) ? 
                                   scores[0] * 1 : scores[1] * 1;
            scoreIncrement += territoryBonus-10;
            
            // 基础得分：如果当前节点对应的玩家赢了，增加得分
            if (playoutResult == state.getCurrentPlayer()) {
                scoreIncrement = 10.0+territoryBonus/3;
            } else if (playoutResult != 0) {
                scoreIncrement = -10.0+territoryBonus/3;
            }
          
            // 气数惩罚 - 在回溯阶段也考虑气数
            if (tempNode.getLibertyCount() == 1) {
                scoreIncrement -= LIBERTY_PENALTY * 0.5; // 回溯阶段的惩罚可以小一些
	        } else if (tempNode.getLibertyCount() < 3) {
	            scoreIncrement -= LIBERTY_PENALTY * 0.5 / 3;
	        }
            // 根据吃子数量增加额外奖励（每吃一子奖励1）
            double captureBonus = 1 * tempNode.getCaptureCount();
            scoreIncrement += captureBonus;
            if(captureBonus!=0) {
            	 // 关键改进：如果当前节点有吃子，惩罚对方上一手棋
                if (tempNode.getCaptureCount() > 0 && tempNode.getParent() != null) {
                    MCTSNode opponentMoveNode = tempNode.getParent();
                    opponentMoveNode.addScore(-territoryBonus);
                    // 应用惩罚到对方上一手棋节点
                    opponentMoveNode.addScore(-captureBonus);
                }
            }
            
            tempNode.addScore(scoreIncrement);
            tempNode = tempNode.getParent();
        }
    }

    // 使用神经网络的搜索方法
    public MCTSNode findNextMoveWithNN(TriangoBoard board, int iterations) {
        MCTSNode rootNode = new MCTSNode(new TriangoBoard(board));
        
        for (int i = 0; i < iterations; i++) {
            // 1. 选择
            MCTSNode promisingNode = useNeuralNetwork ? 
                selectPromisingNodeWithNN(rootNode) : selectPromisingNode(rootNode);
            
            // 2. 扩展
            if (!promisingNode.isTerminalNode()) {
                MCTSNode expandedNode = useNeuralNetwork ? 
                    promisingNode.expandWithNeuralNetwork() : promisingNode.expand();
                
                if (expandedNode != null) {
                    promisingNode = expandedNode;
                }
            }
            
            // 3. 模拟
            int playoutResult =simulateRandomPlayout(promisingNode);
            
            // 4. 回溯
            backPropagation(promisingNode, playoutResult);
//            System.exit(0);
        }
        
        // 返回访问次数最多的有效子节点
        MCTSNode bestNode = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        
        for (MCTSNode child : rootNode.getChildren()) {
            // 检查移动是否有效
            if (child.getMove()[0] != -1) {
                int[] move = child.getMove();
                if (board.getPosition(move[0], move[1]) != TriangoBoard.EMPTY) {
                    continue; // 跳过无效移动
                }
            }
            
            double score = child.getVisitCount();
            if (score > bestScore) {
                bestScore = score;
                bestNode = child;
            }
        }
        
        // 如果没有有效移动，选择放弃
        if (bestNode == null) {
            for (MCTSNode child : rootNode.getChildren()) {
                if (child.getMove()[0] == -1) {
                    bestNode = child;
                    break;
                }
            }
        }
        
        return bestNode;
    }
    
    // 原始搜索方法（保留兼容性）
    public MCTSNode findNextMove(TriangoBoard board, int iterations) {
        return findNextMoveWithNN(board, iterations);
    }
    
    // 关闭执行器服务
    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
    
    // 最终化方法，确保资源释放
    @Override
    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }
}