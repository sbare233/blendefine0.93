package triango;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MCTSNode {
    private TriangoBoard state;
    private MCTSNode parent;
    private List<MCTSNode> children;
    private int visitCount;
    private double winScore;
    private int[] move; // 导致此状态的移动
    private double priorProbability; // 神经网络的先验概率
    private int captureCount; // 吃子数量
    private int libertyCount; // 移动后的气数
    
    public MCTSNode(TriangoBoard state) {
        this.state = state;
        this.children = new ArrayList<>();
        this.visitCount = 0;
        this.winScore = 0;
        this.move = null;
        this.parent = null;
        this.priorProbability = 0.0;
        this.captureCount = 0;
        this.libertyCount = 0;
    }
    
    // 修改构造函数以包含气数
    public MCTSNode(TriangoBoard state, MCTSNode parent, int[] move, int captureCount, int libertyCount) {
        this(state);
        this.parent = parent;
        this.move = move;
        this.captureCount = captureCount;
        this.libertyCount = libertyCount;
    }

    // 添加 getter 和 setter
    public int getCaptureCount() {
        return captureCount;
    }

    public void setCaptureCount(int captureCount) {
        this.captureCount = captureCount;
    }
   
    // 设置先验概率
    public void setPriorProbability(double priorProbability) {
        this.priorProbability = priorProbability;
    }
    
    // 获取先验概率
    public double getPriorProbability() {
        return priorProbability;
    }
    
    public List<MCTSNode> getChildren() {
        return children;
    }
    
    public MCTSNode getParent() {
        return parent;
    }
    
    public TriangoBoard getState() {
        return state;
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public double getWinScore() {
        return winScore;
    }
    
    public int[] getMove() {
        return move;
    }

    public int getLibertyCount() {
        return libertyCount;
    }
    
    public void setLibertyCount(int libertyCount) {
        this.libertyCount = libertyCount;
    }
    public void incrementVisit() {
        visitCount++;
    }
    
    public void addScore(double score) {
        winScore += score;
    }
    
    public MCTSNode getRandomChild() {
        if (children.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return children.get(rand.nextInt(children.size()));
    }
    
    public MCTSNode getChildWithMaxScore() {
        MCTSNode bestChild = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        
        for (MCTSNode child : children) {
            double score = child.winScore / child.visitCount;
            if (score > bestScore) {
                bestScore = score;
                bestChild = child;
            }
        }
        
        return bestChild;
    }
    
    public boolean isFullyExpanded() {
        return children.size() == TriangoBoard.cachedLegalMoves.size(); // 使用缓存的合法移动
    }

    // 使用神经网络指导的扩展方法
    public MCTSNode expandWithNeuralNetwork() {
       
      
        // 获取所有合法移动
        List<int[]> legalMoves = TriangoBoard.cachedLegalMoves;
        
        // 找到尚未扩展的移动
        for (int[] move : legalMoves) {
            // 检查移动是否有效
            if (move[0] != -1) {
                int[] index = state.coordinateToIndex(move[0], move[1]);
                if (index == null || state.getPosition(move[0], move[1]) != TriangoBoard.EMPTY) {
                    continue; // 跳过无效移动
                }
            }
            
            boolean alreadyExpanded = false;
            for (MCTSNode child : children) {
                if ((child.move[0] == move[0] && child.move[1] == move[1]) ||
                    (move[0] == -1 && child.move[0] == -1)) {
                    alreadyExpanded = true;
                    break;
                }
            }
            
            if (!alreadyExpanded) {
                TriangoBoard newState = new TriangoBoard(state);
                int captureCount = 0;
                MCTSNode newNode;
                if (move[0] == -1) {
                    newState.pass();
                    newNode = new MCTSNode(newState, this, move, 0, Integer.MAX_VALUE); // 放弃移动，气数设为最大值
                } else {
                    captureCount = newState.makeMove(move[0], move[1]);
                    if (captureCount == -1) {
                        continue;
                    }
                    
                    // 计算移动后的气数
                    int libertyCount = newState.calculateLibertyCountAfterMove(move[0], move[1]);
                    newNode = new MCTSNode(newState, this, move, captureCount, libertyCount);
                }

                children.add(newNode);
                return newNode;
            }
        }
        return null;
    }
    
    // 原始扩展方法（保留兼容性）
    public MCTSNode expand() {
        List<int[]> legalMoves = TriangoBoard.cachedLegalMoves; // 使用缓存的合法移动
        
       

        // 找到尚未扩展的移动
        for (int[] move : legalMoves) {
            // 检查移动是否有效
            if (move[0] != -1) {
                int[] index = state.coordinateToIndex(move[0], move[1]);
                if (index == null || state.getPosition(move[0], move[1]) != TriangoBoard.EMPTY) {
                    continue; // 跳过无效移动
                }
            }
            
            boolean alreadyExpanded = false;
            for (MCTSNode child : children) {
                if ((child.move[0] == move[0] && child.move[1] == move[1]) ||
                    (move[0] == -1 && child.move[0] == -1)) {
                    alreadyExpanded = true;
                    break;
                }
            }
            
            if (!alreadyExpanded) {
                TriangoBoard newState = new TriangoBoard(state);
                int captureCount = 0;
                MCTSNode newNode;
             // 在expand和expandWithNeuralNetwork方法中修改
                if (move[0] == -1) {
                    newState.pass();
                    newNode = new MCTSNode(newState, this, move, 0, Integer.MAX_VALUE); // 放弃移动，气数设为最大值
                } else {
                    captureCount = newState.makeMove(move[0], move[1]);
                    if (captureCount == -1) {
                        continue;
                    }
                    
                    // 计算移动后的气数
                    int libertyCount = newState.calculateLibertyCountAfterMove(move[0], move[1]);
                    newNode = new MCTSNode(newState, this, move, captureCount, libertyCount);
                }
                newNode.setPriorProbability(0.001f);
                
                children.add(newNode);
                return newNode;
            }
        }
        
        return null; // 所有可能的移动都已扩展
    }
    
    public boolean isTerminalNode() {
        return state.isGameOver();
    }
    
   
}