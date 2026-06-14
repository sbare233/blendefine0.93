package triango;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TriangoMCTS {
    // 其他现有代码保持不变...
    public static final int MCTS_ITERATIONS = 1200;
    static final int MAXMOVE = 120;
    private static final String DATA_FILE = "triango_training_data.json";
    private static final String RESULT_FILE = "triango_game_results.json";
    private TriangoBoard board;
    public MCTS mcts;
    private Random random; // 添加随机数生成器
    
    public TriangoMCTS() {
        board = new TriangoBoard();
        mcts = new MCTS();
//        onnx = new ONNXInference();
//        mcts.setNeuralNetwork(onnx);

        random = new Random(); // 初始化随机数生成器
    }
    
   
    public void selfPlay(int numGames) {
        for (int game = 1; game <= numGames; game++) {
            System.out.println("开始第 " + game + " 局自对弈...共"+numGames+"局");
            board.reset();
            
            int moveCount = 0;
            while (!board.isGameOver()) {
                System.out.println("第 " + moveCount + " 步，当前玩家: " + 
                                  (board.getCurrentPlayer() == TriangoBoard.BLACK ? "黑方" : "白方"));
                
              
                // AI思考
                System.out.println("开始MCTS搜索...");
                MCTSNode bestMoveNode = mcts.findNextMove(board, MCTS_ITERATIONS);
                System.out.println("MCTS搜索完成,迭代次数"+MCTS_ITERATIONS);
                
                if (bestMoveNode == null) {
                    System.out.println("没有找到有效移动，放弃落子");
                    board.pass();
                    moveCount++;
                    continue;
                }
                TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动

                // 执行最佳移动
                int[] move = bestMoveNode.getMove();
                
                if (move[0] == -1) {
                    // 放弃落子
                    System.out.println("AI选择放弃落子");
                    board.pass();
                } else {
                    System.out.println("AI选择移动: (" + move[0] + ", " + move[1] + ")");
                    
                 // 检查移动是否有效且非自杀
                    if (board.getPosition(move[0], move[1]) != TriangoBoard.EMPTY 
//                    		||board.isSuicideMove(move[0], move[1])
                        ) {
                        System.err.println("无效或自杀移动: (" + move[0] + ", " + move[1] + ")");
                        
                        // 尝试找到下一个最佳移动
                        MCTSNode nextBestNode = findNextBestMove(bestMoveNode, board);
                        if (nextBestNode != null) {
                            System.out.println("尝试下一个最佳移动: (" + nextBestNode.getMove()[0] + ", " + nextBestNode.getMove()[1] + ")");
                            move = nextBestNode.getMove();
                            
                            if (move[0] == -1) {
                                System.out.println("改为放弃落子");
                                board.pass();
                            } else {
                                int moveSuccess = board.makeMove(move[0], move[1]);
                                if (moveSuccess==-1) {
                                    System.err.println("移动失败: (" + move[0] + ", " + move[1] + ")");
                                    System.out.println("改为放弃落子");
                                    board.pass();
                                }
                            }
                        } else {
                            System.out.println("没有找到其他有效移动，改为放弃落子");
                            board.pass();
                        }
                    } else {
                        int moveSuccess = board.makeMove(move[0], move[1]);
                        if (moveSuccess==-1) {
                            System.err.println("移动失败: (" + move[0] + ", " + move[1] + ")");
                            System.out.println("改为放弃落子");
                            board.pass();
                        }
                    }
                }
                
                
                moveCount++;
                
                // 显示棋盘状态
                System.out.println(board.toString());
                // 防止无限循环
                if (moveCount >= MAXMOVE) {
                    System.out.println("移动步数超过，强制结束游戏");
                    break;
                }
                
            }
            
            // 显示最终棋盘和结果
            System.out.println("最终棋盘:");
            System.out.println(board.toString());

            int winner = board.getWinner();
            System.out.println("第 " + game + " 局结束，胜利者: " + 
                              (winner == 0 ? "平局" : (winner == 1 ? "黑方" : "白方")));
            int[] scores = board.calculateScores();

        }
        
    }
    private MCTSNode findNextBestMove(MCTSNode bestNode, TriangoBoard board) {
        MCTSNode parent = bestNode.getParent();
        if (parent == null) {
            return null;
        }
        
        // 获取所有子节点并按访问次数排序
        List<MCTSNode> children = parent.getChildren();
        children.sort((a, b) -> Integer.compare(b.getVisitCount(), a.getVisitCount()));
        
        // 找到下一个最佳非自杀移动
        for (MCTSNode child : children) {
            if (child == bestNode) {
                continue; // 跳过当前最佳节点
            }
            
            int[] move = child.getMove();
            if (move[0] == -1) {
                return child; // 放弃落子总是可用的
            }
            
            // 检查移动是否有效且非自杀
            if (board.getPosition(move[0], move[1]) == TriangoBoard.EMPTY 
            		&& !board.isSuicideMove(move[0], move[1])
                ) {
                return child;
            }
        }
        
        return null;
    }
    public void playAgainstAI() {
        Scanner scanner = new Scanner(System.in);
        board.reset();
        
        System.out.println("开始与AI对弈！您执黑先行。");
        System.out.println("输入格式: x y (例如: 4 5)");
        
        while (!board.isGameOver()) {
            if (board.getCurrentPlayer() == TriangoBoard.BLACK) {
                // 玩家回合
                System.out.println(board.toString());
                System.out.print("请输入您的移动: ");
                
                try {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    
                    if (board.makeMove(x, y)==-1) {
                        System.out.println("无效移动，请重试！");
                        continue;
                    }
                    TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动
                } catch (Exception e) {
                    System.out.println("输入格式错误，请重试！");
                    scanner.nextLine(); // 清除缓冲区
                    continue;
                }
            } else {
                // AI回合
                System.out.println("AI思考中...");
                MCTSNode bestMoveNode = mcts.findNextMove(board, MCTS_ITERATIONS);
                int[] move = bestMoveNode.getMove();
                board.makeMove(move[0], move[1]);
                TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动
                System.out.println("AI移动: (" + move[0] + ", " + move[1] + ")");

            }
        }
        
        System.out.println("游戏结束！");
        System.out.println(board.toString());
        
        int winner = board.getWinner();
        if (winner == 0) {
            System.out.println("平局！");
        } else if (winner == TriangoBoard.BLACK) {
            System.out.println("您赢了！");
        } else {
            System.out.println("AI赢了！");
        }
        
        scanner.close();
    }
    public void simpleTest() {
        System.out.println("开始简化测试...");
        
        // 测试基本功能
        board.reset();
        System.out.println("初始棋盘:");
        System.out.println(board.toString());
        
        // 测试几个基本移动
        int[][] testMoves = {
            {9, 0}, {8, 1}, {10, 1}, {9, 1}
        };
        
        for (int[] move : testMoves) {
            System.out.println("尝试移动: (" + move[0] + ", " + move[1] + ")");
            int success = board.makeMove(move[0], move[1]);
            System.out.println("移动结果: " + (success!=-1 ? "成功" : "失败"));
            
            if (success!=-1) {
                System.out.println("移动后棋盘:");
                System.out.println(board.toString());
            }
        }
        
        System.out.println("简化测试完成");
    }
    public static void main(String[] args) {
        TriangoMCTS triango = new TriangoMCTS();
        
//                triango.randomPlay(1000000);
         
                triango.selfPlay(100000);
           
//                triango.playAgainstAI();
           
//                triango.simpleTest();
             
    }
}