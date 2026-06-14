package triango;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TriangoBoard {
    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int BOARD_SIZE = 9; // 9x9 压缩数组
    
    public int[][] board; // 9x9 压缩数组表示棋盘状态
    private int currentPlayer;
    private int lastMoveX = -1;
    private int lastMoveY = -1;
    
    //打劫禁入点
    private int koX = -1;
    private int koY = -1;

 // 添加终局相关变量
    private boolean blackPassed = false;
    private boolean whitePassed = false;
    private int consecutivePasses = 0;
    // 坐标映射: 将实际坐标(x,y)映射到9x9数组索引(row,col)
    private int[][][] coordinateMapping;
    // 反向映射: 将9x9数组索引(row,col)映射回实际坐标(x,y)
    private int[][][] reverseMapping;
    public int[] scores= {0,0};
    public int moves=1;
    private String cachedStateHash; // 缓存状态哈希值
    private Map<String, List<int[]>> neighborsCache; // 缓存每个坐标的邻居列表
    public static List<int[]> cachedLegalMoves; // 缓存合法移动
    public TriangoBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        coordinateMapping = new int[BOARD_SIZE][BOARD_SIZE][2];
        reverseMapping = new int[18][9][2];
        // 初始化映射为无效值
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                coordinateMapping[i][j][0] = -1;
                coordinateMapping[i][j][1] = -1;
            }
        }
        
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 9; j++) {
                reverseMapping[i][j][0] = -1;
                reverseMapping[i][j][1] = -1;
            }
        }
        
        initializeCoordinateMapping();
//        verifyCoordinateMapping(); // 添加验证
        reset();
        neighborsCache = new HashMap<>();
        initializeNeighborsCache(); // 初始化邻居缓存
        cachedLegalMoves = getLegalMoves(); // 预计算合法移动
    }
    public TriangoBoard(TriangoBoard other) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board[i] = Arrays.copyOf(other.board[i], BOARD_SIZE);
        }
        this.currentPlayer = other.currentPlayer;
        this.lastMoveX = other.lastMoveX;
        this.lastMoveY = other.lastMoveY;
        
        // 复制映射
        this.coordinateMapping = new int[BOARD_SIZE][BOARD_SIZE][2];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.coordinateMapping[i][j][0] = other.coordinateMapping[i][j][0];
                this.coordinateMapping[i][j][1] = other.coordinateMapping[i][j][1];
            }
        }
        
        this.reverseMapping = new int[18][9][2];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 9; j++) {
                this.reverseMapping[i][j][0] = other.reverseMapping[i][j][0];
                this.reverseMapping[i][j][1] = other.reverseMapping[i][j][1];
            }
        }
        this.cachedStateHash = other.cachedStateHash;
        this.neighborsCache = new HashMap<>(other.neighborsCache);
    }
    private void initializeNeighborsCache() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int[] coord = indexToCoordinate(row, col);
                if (coord != null) {
                    String key = coord[0] + "," + coord[1];
                    neighborsCache.put(key, calculateNeighbors(coord[0], coord[1]));
                }
            }
        }
    }
    private List<int[]> calculateNeighbors(int x, int y) {

    	List<int[]> neighbors = new ArrayList<>();
        
        // 根据实际的三角形棋盘布局定义相邻关系
        // 这里需要根据棋盘的具体形状来确定
        
        // 基本思路：每个三角形有三个相邻的三角形
        // 具体关系取决于位置在棋盘上的位置
        
        // 尝试根据坐标确定相邻关系
        if ((x+y) % 2 == 0) {
            // 偶数行
            if (x > 1) neighbors.add(new int[]{x-1, y}); // 左下
            if (x < 17) neighbors.add(new int[]{x+1, y});   // 右下
            if (y > 0) neighbors.add(new int[]{x, y-1});   // 上方
        } else {
            // 奇数行
            if (y > 0) neighbors.add(new int[]{x-1, y});   // 左上
            if (x < 17 && y > 0) neighbors.add(new int[]{x+1, y}); // 右上
            if (y < 8) neighbors.add(new int[]{x, y+1});   // 下方
        }
        
        // 过滤掉无效的坐标
        List<int[]> validNeighbors = new ArrayList<>();
        for (int[] neighbor : neighbors) {
            if (isValidCoordinate(neighbor[0], neighbor[1])) {
                validNeighbors.add(neighbor);
            }
        }
        return neighbors;
    }
    
     // 初始化坐标映射 - 修正版
        private void initializeCoordinateMapping() {
            // 初始化所有映射为无效值
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    coordinateMapping[i][j][0] = -1;
                    coordinateMapping[i][j][1] = -1;
                }
            }
            
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 9; j++) {
                    reverseMapping[i][j][0] = -1;
                    reverseMapping[i][j][1] = -1;
                }
            }
            
            // 根据您提供的格式初始化映射
            // 每行9个坐标对，共9行
            int row = 0;
            
            // 第0行: 9,0 10,8 11,8 12,8 13,8 14,8 15,8 16,8 17,8
            setCoordinateMapping(row, 0, 9, 0);
            setCoordinateMapping(row, 1, 10, 8);
            setCoordinateMapping(row, 2, 11, 8);
            setCoordinateMapping(row, 3, 12, 8);
            setCoordinateMapping(row, 4, 13, 8);
            setCoordinateMapping(row, 5, 14, 8);
            setCoordinateMapping(row, 6, 15, 8);
            setCoordinateMapping(row, 7, 16, 8);
            setCoordinateMapping(row, 8, 17, 8);
            row++;
            
            // 第1行: 8,1 9,1 10,1 11,7 12,7 13,7 14,7 15,7 16,7
            setCoordinateMapping(row, 0, 8, 1);
            setCoordinateMapping(row, 1, 9, 1);
            setCoordinateMapping(row, 2, 10, 1);
            setCoordinateMapping(row, 3, 11, 7);
            setCoordinateMapping(row, 4, 12, 7);
            setCoordinateMapping(row, 5, 13, 7);
            setCoordinateMapping(row, 6, 14, 7);
            setCoordinateMapping(row, 7, 15, 7);
            setCoordinateMapping(row, 8, 16, 7);
            row++;
            
            // 第2行: 7,2 8,2 9,2 10,2 11,2 12,6 13,6 14,6 15,6
            setCoordinateMapping(row, 0, 7, 2);
            setCoordinateMapping(row, 1, 8, 2);
            setCoordinateMapping(row, 2, 9, 2);
            setCoordinateMapping(row, 3, 10, 2);
            setCoordinateMapping(row, 4, 11, 2);
            setCoordinateMapping(row, 5, 12, 6);
            setCoordinateMapping(row, 6, 13, 6);
            setCoordinateMapping(row, 7, 14, 6);
            setCoordinateMapping(row, 8, 15, 6);
            row++;
            
            // 第3行: 6,3 7,3 8,3 9,3 10,3 11,3 12,3 13,5 14,5
            setCoordinateMapping(row, 0, 6, 3);
            setCoordinateMapping(row, 1, 7, 3);
            setCoordinateMapping(row, 2, 8, 3);
            setCoordinateMapping(row, 3, 9, 3);
            setCoordinateMapping(row, 4, 10, 3);
            setCoordinateMapping(row, 5, 11, 3);
            setCoordinateMapping(row, 6, 12, 3);
            setCoordinateMapping(row, 7, 13, 5);
            setCoordinateMapping(row, 8, 14, 5);
            row++;
            
            // 第4行: 5,4 6,4 7,4 8,4 9,4 10,4 11,4 12,4 13,4
            setCoordinateMapping(row, 0, 5, 4);
            setCoordinateMapping(row, 1, 6, 4);
            setCoordinateMapping(row, 2, 7, 4);
            setCoordinateMapping(row, 3, 8, 4);
            setCoordinateMapping(row, 4, 9, 4);
            setCoordinateMapping(row, 5, 10, 4);
            setCoordinateMapping(row, 6, 11, 4);
            setCoordinateMapping(row, 7, 12, 4);
            setCoordinateMapping(row, 8, 13, 4);
            row++;
            
            // 第5行: 4,5 5,5 6,5 7,5 8,5 9,5 10,5 11,5 12,5
            setCoordinateMapping(row, 0, 4, 5);
            setCoordinateMapping(row, 1, 5, 5);
            setCoordinateMapping(row, 2, 6, 5);
            setCoordinateMapping(row, 3, 7, 5);
            setCoordinateMapping(row, 4, 8, 5);
            setCoordinateMapping(row, 5, 9, 5);
            setCoordinateMapping(row, 6, 10, 5);
            setCoordinateMapping(row, 7, 11, 5);
            setCoordinateMapping(row, 8, 12, 5);
            row++;
            
            // 第6行: 3,6 4,6 5,6 6,6 7,6 8,6 9,6 10,6 11,6
            setCoordinateMapping(row, 0, 3, 6);
            setCoordinateMapping(row, 1, 4, 6);
            setCoordinateMapping(row, 2, 5, 6);
            setCoordinateMapping(row, 3, 6, 6);
            setCoordinateMapping(row, 4, 7, 6);
            setCoordinateMapping(row, 5, 8, 6);
            setCoordinateMapping(row, 6, 9, 6);
            setCoordinateMapping(row, 7, 10, 6);
            setCoordinateMapping(row, 8, 11, 6);
            row++;
            
            // 第7行: 2,7 3,7 4,7 5,7 6,7 7,7 8,7 9,7 10,7
            setCoordinateMapping(row, 0, 2, 7);
            setCoordinateMapping(row, 1, 3, 7);
            setCoordinateMapping(row, 2, 4, 7);
            setCoordinateMapping(row, 3, 5, 7);
            setCoordinateMapping(row, 4, 6, 7);
            setCoordinateMapping(row, 5, 7, 7);
            setCoordinateMapping(row, 6, 8, 7);
            setCoordinateMapping(row, 7, 9, 7);
            setCoordinateMapping(row, 8, 10, 7);
            row++;
            
            // 第8行: 1,8 2,8 3,8 4,8 5,8 6,8 7,8 8,8 9,8
            setCoordinateMapping(row, 0, 1, 8);
            setCoordinateMapping(row, 1, 2, 8);
            setCoordinateMapping(row, 2, 3, 8);
            setCoordinateMapping(row, 3, 4, 8);
            setCoordinateMapping(row, 4, 5, 8);
            setCoordinateMapping(row, 5, 6, 8);
            setCoordinateMapping(row, 6, 7, 8);
            setCoordinateMapping(row, 7, 8, 8);
            setCoordinateMapping(row, 8, 9, 8);
        }
    
    private void setCoordinateMapping(int row, int col, int x, int y) {
        coordinateMapping[row][col][0] = x;
        coordinateMapping[row][col][1] = y;
        reverseMapping[x][y][0] = row;
        reverseMapping[x][y][1] = col;
    }
    
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public int getPosition(int x, int y) {
        int[] index = coordinateToIndex(x, y);
        if (index == null) {
            return -1; // 无效位置
        }
        return board[index[0]][index[1]];
    }
    
    // 将实际坐标(x,y)转换为9x9数组索引(row,col)
    public int[] coordinateToIndex(int x, int y) {
        if (x < 1 || x > 17 || y < 0 || y > 8) {
            return null;
        }
        
        int row = reverseMapping[x][y][0];
        int col = reverseMapping[x][y][1];
        
        if (row == -1 || col == -1) {
            return null;
        }
        
        return new int[]{row, col};
    }
    
    // 将9x9数组索引(row,col)转换为实际坐标(x,y)
    public int[] indexToCoordinate(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return null;
        }
        
        int x = coordinateMapping[row][col][0];
        int y = coordinateMapping[row][col][1];
        
        if (x == -1 || y == -1) {
            return null;
        }
        
        return new int[]{x, y};
    }
    public int makeMove(int x, int y) {
        int[] index = coordinateToIndex(x, y);
        if (index == null || board[index[0]][index[1]] != EMPTY) {
            return -1;
        }
        if (x == koX && y == koY) {
            return -1; // 打劫禁手
        }

        // 记录落子前的分数（黑白地）
        int[] beforeScores = calculateScores();
        int beforeMyScore = (currentPlayer == BLACK) ? beforeScores[0] : beforeScores[1];

        // 临时放置棋子
        board[index[0]][index[1]] = currentPlayer;

        // 检查是否能提掉对方棋子
        // 检查并能提掉对方棋子，返回吃子数量
        int captureCount = checkAndCapture();

        // 检查是否自杀
        int[] coord = indexToCoordinate(index[0], index[1]);
        List<int[]> currentGroup = findConnectedGroup(coord[0], coord[1]);
        Set<String> currentLiberties = calculateLiberties(currentGroup);

        if (currentLiberties.isEmpty()) {
            // 自杀，回退
            board[index[0]][index[1]] = EMPTY;
            return -1;
        }

        // 计算落子后的分数
        int[] afterScores = calculateScores();
        int afterMyScore = (currentPlayer == BLACK) ? afterScores[0] : afterScores[1];

        if (afterMyScore < beforeMyScore) {
            // 如果落子导致自己围地减少，判为无效，回退
            board[index[0]][index[1]] = EMPTY;
            return -1;
        }

        // 走子有效，正式更新状态
        lastMoveX = x;
        lastMoveY = y;
        currentPlayer = (currentPlayer == BLACK) ? WHITE : BLACK;
        scores = afterScores;
        moves++;

        cachedStateHash = null; // 状态改变，清除缓存
        return captureCount;
        
    }
    public List<int[]> getLegalMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    int[] coord = indexToCoordinate(row, col);
                    if (coord != null&&(koX!=coord[0]||koY!=coord[1])&&!isSuicideMove(coord[0],coord[1])) {
                    	if(this.moves>30||!(coord[0]==9&&coord[1]==0||coord[0]==1&&coord[1]==8||coord[0]==17&&coord[1]==8)) {
                    		   moves.add(coord);
                       	}
                    }
                }
            }
        }
        return moves;
    }
    

    public void verifyCoordinateMapping() {
        System.out.println("验证坐标映射...");
        
        int validMappings = 0;
        int invalidMappings = 0;
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int[] coord = indexToCoordinate(row, col);
                if (coord != null) {
                    int[] reverseIndex = coordinateToIndex(coord[0], coord[1]);
                    if (reverseIndex != null && reverseIndex[0] == row && reverseIndex[1] == col) {
                        validMappings++;
                    } else {
                        System.err.println("映射不一致: (" + row + "," + col + ") -> (" + 
                                         coord[0] + "," + coord[1] + ") -> (" + 
                                         (reverseIndex != null ? reverseIndex[0] : "null") + "," + 
                                         (reverseIndex != null ? reverseIndex[1] : "null") + ")");
                        invalidMappings++;
                    }
                } else {
                    System.err.println("无效映射: (" + row + "," + col + ")");
                    invalidMappings++;
                }
            }
        }
        
        System.out.println("坐标映射验证完成: " + validMappings + " 个有效, " + invalidMappings + " 个无效");
    }
   
    
    public String getStateHash() {
        // 使用更复杂的哈希算法减少冲突
    	if (cachedStateHash == null) {
	        StringBuilder sb = new StringBuilder();
	        for (int row = 0; row < BOARD_SIZE; row++) {
	            for (int col = 0; col < BOARD_SIZE; col++) {
	                sb.append(board[row][col]);
	            }
	        }
	        sb.append(currentPlayer);
	        sb.append(consecutivePasses);
	        
	        // 添加更多状态信息
	        if (lastMoveX != -1 && lastMoveY != -1) {
	            sb.append(lastMoveX).append(lastMoveY);
	        }
	        cachedStateHash = Integer.toHexString(sb.toString().hashCode());
    	}
        return cachedStateHash;
    }
    public List<int[]> getNeighbors(int x, int y) {
        String key = x + "," + y;
        return neighborsCache.getOrDefault(key, new ArrayList<>());
    }

 // 调试方法：打印连通区域信息
    public void debugConnectedGroup(int x, int y) {
        int[] index = coordinateToIndex(x, y);
        if (index == null || board[index[0]][index[1]] == EMPTY) {
            System.out.println("位置(" + x + "," + y + ")没有棋子");
            return;
        }
        
        List<int[]> group = findConnectedGroup(x, y);
        Set<String> liberties = calculateLiberties(group);
        
        System.out.println("位置(" + x + "," + y + ")的连通区域:");
        for (int[] stone : group) {
            int[] coord = indexToCoordinate(stone[0], stone[1]);
            System.out.print("(" + coord[0] + "," + coord[1] + ") ");
        }
        System.out.println("\n气: " + liberties.size());
        for (String liberty : liberties) {
            String[] parts = liberty.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            int[] coord = indexToCoordinate(row, col);
            System.out.print("(" + coord[0] + "," + coord[1] + ") ");
        }
        System.out.println();
    }
    public boolean isKoMove(int x, int y) {
    	return x==koX&&y==koY;
    }
    public boolean isSuicideMove(int x, int y) {
        int[] index = coordinateToIndex(x, y);
        if (index == null || board[index[0]][index[1]] != EMPTY) {
            return true; // 无效位置或已有棋子，视为自杀
        }

        // 临时放置棋子
        board[index[0]][index[1]] = currentPlayer;
        
        // 检查是否能提掉对方棋子
        boolean canCapture = checkCanCapture(x, y);
        
        // 检查当前落子是否导致自己的棋子无气
        int[] coord = indexToCoordinate(index[0], index[1]);
        List<int[]> currentGroup = findConnectedGroup(coord[0], coord[1]);
        Set<String> currentLiberties = calculateLiberties(currentGroup);
        
        // 回退临时落子
        board[index[0]][index[1]] = EMPTY;
        
        // 如果不能提掉对方棋子且自己的棋子无气，则是自杀
        return !canCapture && currentLiberties.isEmpty();
    }
    // 检查落子后是否能提掉对方棋子
    private boolean checkCanCapture(int x, int y) {
        // 临时保存棋盘状态
        int[][] tempBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, BOARD_SIZE);
        }
        
        // 临时放置棋子
        int[] index = coordinateToIndex(x, y);
        board[index[0]][index[1]] = currentPlayer;
        
        // 检查是否能提掉对方棋子
        boolean canCapture = false;
        boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!visited[row][col] && board[row][col] != EMPTY && board[row][col] != currentPlayer) {
                    int[] coord = indexToCoordinate(row, col);
                    if (coord == null) continue;
                    
                    List<int[]> group = findConnectedGroup(coord[0], coord[1]);
                    Set<String> liberties = calculateLiberties(group);
                    
                    if (liberties.isEmpty()) {
                        canCapture = true;
                        break;
                    }
                }
            }
            if (canCapture) break;
        }

        // 恢复棋盘状态
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(tempBoard[i], 0, board[i], 0, BOARD_SIZE);
        }
        
        return canCapture;
    }
 // 检查并执行提子
    private int checkAndCapture() {
        boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];
        int capturedCount = 0;
        int lastCapturedX = -1, lastCapturedY = -1;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!visited[row][col] && board[row][col] != EMPTY) {
                    int[] coord = indexToCoordinate(row, col);
                    if (coord == null) continue;

                    List<int[]> group = findConnectedGroup(coord[0], coord[1]);
                    Set<String> liberties = calculateLiberties(group);

                    if (liberties.isEmpty()) {
                        int groupColor = board[group.get(0)[0]][group.get(0)[1]];
                        if (groupColor != currentPlayer) {
                            for (int[] stone : group) {
                                board[stone[0]][stone[1]] = EMPTY;
                                visited[stone[0]][stone[1]] = true;
                                capturedCount++;
                                lastCapturedX = stone[0];
                                lastCapturedY = stone[1];
                            }
                        }
                    } else {
                        for (int[] stone : group) {
                            visited[stone[0]][stone[1]] = true;
                        }
                    }
                }
            }
        }

        // 打劫条件：只提掉一个棋子
        if (capturedCount == 1) {
            int[] coord = indexToCoordinate(lastCapturedX, lastCapturedY);
            koX = coord[0];
            koY = coord[1];
        } else {
            koX = -1;
            koY = -1;
        }
        return capturedCount;
    }
    public int calculateLibertyCountAfterMove(int x, int y) {
        int[] index = coordinateToIndex(x, y);
        if (index == null) {
            return 0;
        }
        // 找到与这个位置相连的所有同色棋子
        List<int[]> group = findConnectedGroup(x, y);
        
        // 计算这个组的气
        Set<String> liberties = calculateLiberties(group);
        
        return liberties.size();
    }
    // 找到与指定位置相连的所有同色棋子
    private List<int[]> findConnectedGroup(int x, int y) {
        int[] startIndex = coordinateToIndex(x, y);
        if (startIndex == null || board[startIndex[0]][startIndex[1]] == EMPTY) {
            return new ArrayList<>();
        }
        
        int color = board[startIndex[0]][startIndex[1]];
        List<int[]> group = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];
        
        queue.add(startIndex);
        visited[startIndex[0]][startIndex[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            group.add(current);
            
            int[] coord = indexToCoordinate(current[0], current[1]);
            if (coord == null) continue;
            
            List<int[]> neighbors = getNeighbors(coord[0], coord[1]);
            for (int[] neighbor : neighbors) {
                int[] neighborIndex = coordinateToIndex(neighbor[0], neighbor[1]);
                if (neighborIndex == null || visited[neighborIndex[0]][neighborIndex[1]]) continue;
                
                if (board[neighborIndex[0]][neighborIndex[1]] == color) {
                    queue.add(neighborIndex);
                    visited[neighborIndex[0]][neighborIndex[1]] = true;
                }
            }
        }
        
        return group;
    }

    // 计算一个连通区域的气
    private Set<String> calculateLiberties(List<int[]> group) {
        Set<String> liberties = new HashSet<>();
        
        for (int[] stone : group) {
            int[] coord = indexToCoordinate(stone[0], stone[1]);
            if (coord == null) continue;
            
            List<int[]> neighbors = getNeighbors(coord[0], coord[1]);
            for (int[] neighbor : neighbors) {
                int[] neighborIndex = coordinateToIndex(neighbor[0], neighbor[1]);
                if (neighborIndex == null) continue;
                
                if (board[neighborIndex[0]][neighborIndex[1]] == EMPTY) {
                    liberties.add(neighborIndex[0] + "," + neighborIndex[1]);
                }
            }
        }
        
        return liberties;
    }
    // 判断坐标是否有效
    private boolean isValidCoordinate(int x, int y) {
        return coordinateToIndex(x, y) != null;
    }
    

    // 添加重置方法中的终局变量重置
    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], EMPTY);
        }
        currentPlayer = BLACK;
        lastMoveX = -1;
        lastMoveY = -1;
        moves=1;
        // 重置终局相关变量
        blackPassed = false;
        whitePassed = false;
        consecutivePasses = 0;
        cachedStateHash = null;
    }
    
    // 添加放弃落子方法
    public boolean pass() {
        if (currentPlayer == BLACK) {
            blackPassed = true;
        } else {
            whitePassed = true;
        }
        moves++;
        consecutivePasses++;
        currentPlayer = (currentPlayer == BLACK) ? WHITE : BLACK;
        cachedStateHash = null;
        return true;
    }
    
    // 修改游戏结束判断
    public boolean isGameOver() {
        // 双方都放弃落子时游戏结束

        if (consecutivePasses >= 2||(moves>10&&Math.abs(scores[0]-scores[1])>39)) {
            return true;
        }
        // 或者棋盘已满
//        System.out.println(cachedLegalMoves.size());
        return cachedLegalMoves.isEmpty();
    }
    // 修改胜负判断方法
    public int getWinner() {
        // 计算双方领地和棋子数
        int[] scores = this.scores;
        int blackScore = scores[0];
        int whiteScore = scores[1];
        // 比较得分
        if (blackScore > whiteScore) {
            return BLACK;
        } else if (whiteScore > blackScore) {
            return WHITE;
        } else {
            return 0; // 平局
        }
    }
   
 // 添加这个方法到TriangoBoard类中
    public int[][] getBoardState() {
        int[][] copy = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, BOARD_SIZE);
        }
        return copy;
    }


    // 计算得分（领地）
    public int[] calculateScores() {
        int blackScore = 0;
        int whiteScore = 0;
        
        // 标记已访问的位置
        boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];
        
        // 遍历所有位置
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!visited[row][col] && board[row][col] == EMPTY) {
                    // 找到空白区域，判断属于哪方领地
                    int[] territoryResult = evaluateTerritory(row, col, visited);
                    if (territoryResult[0] == BLACK) {
                        blackScore += territoryResult[1];
                    } else if (territoryResult[0] == WHITE) {
                        whiteScore += territoryResult[1];
                    }
                    // 如果territoryResult[0] == EMPTY，表示中立区域，不计分
                } 
//                else if (board[row][col] == BLACK) {
//                    blackScore++; // 黑棋子
//                } 
//                else if (board[row][col] == WHITE) {
//                    whiteScore++; // 白棋子
//                }
            }
        }
//        whiteScore++; // 黑贴一目
        return new int[]{blackScore, whiteScore};
    }
    
    // 评估领地归属
    private int[] evaluateTerritory(int startRow, int startCol, boolean[][] visited) {
        // 使用BFS遍历相连的空白区域
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        
        List<int[]> territory = new ArrayList<>();
        territory.add(new int[]{startRow, startCol});
        
        int borderColor = EMPTY; // 边界颜色
        boolean isNeutral = false; // 是否中立（接触双方棋子）
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            
            // 获取实际坐标以确定相邻位置
            int[] coord = indexToCoordinate(row, col);
            if (coord == null) continue;
            
            List<int[]> neighbors = getNeighbors(coord[0], coord[1]);
            
            for (int[] neighbor : neighbors) {
                int[] neighborIndex = coordinateToIndex(neighbor[0], neighbor[1]);
                if (neighborIndex == null) continue;
                
                int nRow = neighborIndex[0];
                int nCol = neighborIndex[1];
                
                if (board[nRow][nCol] == EMPTY) {
                    // 空白区域，继续遍历
                    if (!visited[nRow][nCol]) {
                        visited[nRow][nCol] = true;
                        queue.add(new int[]{nRow, nCol});
                        territory.add(new int[]{nRow, nCol});
                    }
                } else {
                    // 遇到棋子，记录边界颜色
                    if (borderColor == EMPTY) {
                        borderColor = board[nRow][nCol];
                    } else if (borderColor != board[nRow][nCol]) {
                        // 接触了双方棋子，领地中立
                        isNeutral = true;
                    }
                }
            }
        }
        
        if (isNeutral || borderColor == EMPTY) {
            return new int[]{EMPTY, 0}; // 中立领地或无边界领地
        } else {
            return new int[]{borderColor, territory.size()}; // 返回领地颜色和大小
        }
    }
    
    // 修改toString方法，添加游戏状态信息
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // 打印棋盘
        for (int y = 0; y < 9; y++) {
            // 添加缩进，使棋盘呈三角形
            for (int i = 0; i < y; i++) {
                sb.append(" ");
            }
            
            for (int x = 1; x <= 17; x++) {
                if (isValidCoordinate(x, y)) {
                    int[] index = coordinateToIndex(x, y);
                    
                    char c = '.';
                    if((x+y)%2==1)c='△';
                    else c='▽';
                    if (board[index[0]][index[1]] == BLACK) c = 'B';
                    else if (board[index[0]][index[1]] == WHITE) c = 'W';
                    sb.append(c);
                } else {
                    sb.append("  ");
                }
            }
            sb.append("\n");
        }
        
        // 添加游戏状态信息
        sb.append("当前玩家: ").append(currentPlayer == BLACK ? "黑方" : "白方").append("\n");
        sb.append("连续放弃次数: ").append(consecutivePasses).append("\n");
        
        if (isGameOver()) {
        	 int[] scores = this.scores;
            int winner = getWinner();
           
            sb.append("游戏结束! ")
              .append(winner == BLACK ? "黑方胜" : winner == WHITE ? "白方胜" : "平局")
              .append(" (黑方: ").append(scores[0])
              .append(", 白方: ").append(scores[1]).append(")\n");
        }
        
        return sb.toString();
    }
   
}