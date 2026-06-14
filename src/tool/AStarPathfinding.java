package tool;
import java.util.*;

public class AStarPathfinding {
    // 定义网格节点类
    private static class Node implements Comparable<Node> {
        int x, y;           // 节点坐标
        int g;              // 起点到当前节点的实际代价
        int h;              // 当前节点到终点的启发式估计值
        Node parent;        // 路径回溯指针

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 计算f值（总代价）
        int f() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }
    }

    public static ArrayList<int[]> findPath(Integer[][] grid, int[] start, int[] end) {
        // 参数校验
        if (grid == null || start == null || end == null) {
            return new ArrayList<>();
        }

        int rows = grid.length;
        int cols = grid[0].length;
        // 检查起点终点有效性
        if (start[0] < 0 || start[0] >= rows || start[1] < 0 || start[1] >= cols ||
            end[0] < 0 || end[0] >= rows || end[1] < 0 || end[1] >= cols ||
            grid[start[0]][start[1]] == null || grid[end[0]][end[1]] == null) {
            return new ArrayList<>();
        }
        // 方向数组（上，右，下，左）
        final int[][] DIRECTIONS = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        Map<String, Integer> gScores = new HashMap<>();
        
        // 初始化起点
        Node startNode = new Node(start[0], start[1]);
        startNode.g = 0;
        startNode.h = manhattanDistance(start, end);
        openList.add(startNode);
        gScores.put(startNode.x + "," + startNode.y, 0);
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            
            // 到达终点，回溯路径
            if (current.x == end[0] && current.y == end[1]) {
                return reconstructPath(current);
            }

            closedSet.add(current.x + "," + current.y);

            // 遍历四个方向
            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // 检查边界和可通行性
                if (newX < 0 || newX >= rows || newY < 0 || newY >= cols ||
                    grid[newX][newY] == null) {
                    continue;
                }

                String key = newX + "," + newY;
                
                // 计算临时g值
                int tentativeG = current.g + 1; // 假设每步代价为1

                // 如果新路径更好
                if (tentativeG < gScores.getOrDefault(key, Integer.MAX_VALUE)) {
                    Node neighbor = new Node(newX, newY);
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = manhattanDistance(new int[]{newX, newY}, end);
                    
                    if (closedSet.contains(key)) {
                        closedSet.remove(key);
                    }
                    
                    openList.removeIf(n -> n.x == newX && n.y == newY); // 移除旧节点
                    openList.add(neighbor);
                    gScores.put(key, tentativeG);
                }
            }
        }

        // 没有找到路径
        return new ArrayList<>();
    }

    // 曼哈顿距离计算
    private static int manhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    // 路径回溯
    private static ArrayList<int[]> reconstructPath(Node node) {
        ArrayList<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(new int[]{node.x, node.y});
            node = node.parent;
        }
        Collections.reverse(path);

        return path;
    }
}