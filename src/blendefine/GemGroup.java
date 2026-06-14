// 新增宝石组管理类
package blendefine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.shape.Line;

public class GemGroup {
    public Set<define> gems = new HashSet<>();
    public Map<String, HashSet<String>> connsxy = new HashMap<>();
    //存放连接，创建连接时列优先级大于行
    public Map<String,Line> conns = new HashMap<>();//连接线
    public double pivotX, pivotY,mofx,mofy,centerx,centery;
    public define leftupd;
    public define nowd;
    int miny=999,minx=999;
    public GemGroup(define initialGem) {
        addGem(initialGem);
//        updatePivot();
    }

    public void addGem(define gem) {
        boolean added = gems.add(gem);
        gem.parentGroup = this;
    }
    
    /**
     * 生成化合物的原始名称（基于当前实际坐标）
     */
    public String makename() {
        define[][] ds = new define[49][81];
        minx = 999; miny = 999;  // 重置最小值
        gems.forEach(e -> {
            if (e.x < minx) minx = e.x;
            if (e.y < miny && e.x <= minx) miny = e.y;
            ds[e.y][e.x] = e;
        });
        StringBuilder name = new StringBuilder();
        for (int j = 0; j < 81; j++) {
            for (int i = 0; i < 49; i++) {
                if (ds[i][j] != null) {
                    define d = ds[i][j];
                    name.append(d.x).append(",").append(d.y).append(d.name);
                }
            }
        }
        return name.toString();
    }

    /**
     * 将宝石组平移到最左上角（对齐网格）后生成名称
     */
    public String makeminname() {
        define[][] ds = new define[49][81];
        minx = 999; miny = 999;
        gems.forEach(e -> {
            if (e.x < minx) minx = e.x;
            if (e.y < miny && e.x <= minx) miny = e.y;
            ds[e.y][e.x] = e;
        });
        StringBuilder name = new StringBuilder();
        for (int j = 0; j < 81; j++) {
            for (int i = 0; i < 49; i++) {
                if (ds[i][j] != null) {
                    define d = ds[i][j];
                    if ((minx + miny) % 2 == 0)
                        name.append((d.x - minx)).append(",").append((d.y - miny)).append(d.name);
                    else
                        name.append((d.x - minx + 1)).append(",").append((d.y - miny)).append(d.name);
                }
            }
        }
        return name.toString();
    }
    
    /**
     * 完善版：对宝石组进行6次旋转（每次60°），每次旋转后平移到最小坐标并生成名称
     * @return 长度为6的字符串数组，对应0°、60°、120°、180°、240°、300°旋转后的名称
     */
    public String[] makeminrotatename() {
        String[] names = new String[6];
        int size = gems.size();
        define[] originalGems = gems.toArray(new define[0]);

        for (int roll = 0; roll < 6; roll++) {
            // 奇数次旋转（60°的奇数倍）需要偏移一个单位以对齐三角形网格
            int dx = (roll % 2 == 1) ? 1 : 0;
            int dy = 0; // 垂直偏移在坐标转换时已通过奇偶性处理

            double sin = Math.sin(Math.toRadians(roll * 60));
            double cos = Math.cos(Math.toRadians(roll * 60));

            // 存储旋转后的网格坐标
            int[] newXs = new int[size];
            int[] newYs = new int[size];

            // 第一步：计算每个宝石旋转后的新网格坐标
            for (int i = 0; i < size; i++) {
                define d = originalGems[i];
                // 原始世界坐标（以150x260的菱形网格为基础，处理错行偏移）
                double offX = (d.x - dx) * 150.0;
                double offY = (d.y - dy) * 260.0 
                        + (((d.x - dx + d.y - dy) % 2 == 0) ? 0 
                        : ((d.x + d.y) % 2 == 0 ? -260.0 / 3 : 260.0 / 3));

                // 旋转
                double rotX = offX * cos - offY * sin;
                double rotY = offX * sin + offY * cos;

                // 还原为网格坐标（每个格子宽150，高260）
                int gridX = (int) Math.floor((rotX + 75) / 150.0);
                int gridY = (int) Math.floor((rotY + 130) / 260.0);

                newXs[i] = gridX;
                newYs[i] = gridY;
            }

            // 第二步：检查是否有重叠（如果需要，这里可以加入冲突处理；当前实现直接继续）
            // 第三步：将旋转后的坐标平移到最小位置（类似 makeminname 逻辑）
            int minRx = 999, minRy = 999;
            for (int i = 0; i < size; i++) {
                if (newXs[i] < minRx) minRx = newXs[i];
                if (newYs[i] < minRy && newXs[i] <= minRx) minRy = newYs[i];
            }

            // 构建名称
            // 为了方便排序，先按 (y,x) 收集
            Map<String, define> rotatedMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                // 这里需要保留原宝石的名称和属性，所以借用 originalGems[i]
                define d = originalGems[i];
                int tx = newXs[i] - minRx;
                int ty = newYs[i] - minRy;
                // 奇偶对齐处理
                if ((minRx + minRy) % 2 != 0) {
                    tx = tx + 1;  // 保持和 makeminname 相同的对齐策略
                }
                String key = tx + "," + ty;
                // 如果有重叠，理论上应处理，这里简单保留最后一个（实际不应重叠）
                rotatedMap.put(key, d);
            }

            // 按坐标排序输出
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < 50; y++) {
                for (int x = 0; x < 100; x++) {
                    define d = rotatedMap.get(x + "," + y);
                    if (d != null) {
                        sb.append(x).append(",").append(y).append(d.name);
                    }
                }
            }
            names[roll] = sb.toString();
        }
        return names;
    }
    // ========== 新增静态方法 ==========
    /**
     * 解析一个 minname 字符串，返回该宝石组旋转 0°、60°、120°、180°、240°、300° 后的六个 minname
     * @param minname 格式如 "0,0A1,1B..." 的字符串（由 makeminname 生成）
     * @return 长度为6的字符串数组，顺序对应0°~300°（每60°递增）
     */
    public static String[] getRotatedMinNames(String minname) {
        List<GemData> gems = parseMinName(minname);
        if (gems.isEmpty()) {
            return new String[6];
        }
//        int shouldxp=1;
        String[] result = new String[6];

        for (int roll = 0; roll < 6; roll++) {
            int dx = (roll % 2 == 1) ? 1 : 0;
            int dy = 0;
            double sin = Math.sin(Math.toRadians(roll * 60));
            double cos = Math.cos(Math.toRadians(roll * 60));

            int size = gems.size();
            int[] newXs = new int[size];
            int[] newYs = new int[size];

            // 旋转计算（完全保留原逻辑）
            for (int i = 0; i < size; i++) {
                GemData g = gems.get(i);
//                if(g.x+g.y==0)shouldxp=0;
                
                double offX = (g.x - dx) * 150.0;
                double offY = (g.y - dy) * 260.0
                        + (((g.x - dx + g.y - dy) % 2 == 0) ? 0
                                : ((g.x + g.y) % 2 == 0 ? -260.0 / 3 : 260.0 / 3));

                double rotX = offX * cos - offY * sin;
                double rotY = offX * sin + offY * cos;

                int gridX = (int) Math.floor((rotX + 75) / 150.0);
                int gridY = (int) Math.floor((rotY + 130) / 260.0);

                newXs[i] = gridX;
                newYs[i] = gridY;
            }

            // 计算全局最小坐标（修正原错误）
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                if (newXs[i] < minX) minX = newXs[i];
                if (newYs[i] < minY) minY = newYs[i];
            }

            // 平移并可能进行奇偶调整（仅 roll 偶数时）
            Map<String, String> rotatedMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                int tx = newXs[i] - minX;
                int ty = newYs[i] - minY;
//                if (roll % 2 == 1&&shouldxp==0||roll % 2 == 0&&shouldxp==1) {  // 0°,120°,240°
                    if ((minX + minY) % 2 != 0) {
                        tx++;
                    }
//                }
                // 防御：确保非负（正常情况下不会触发）
                if (tx < 0) tx = 0;
                if (ty < 0) ty = 0;
                rotatedMap.put(tx + "," + ty, gems.get(i).name);
            }

            // 排序输出（按 y 升序，x 升序）
            List<Map.Entry<String, String>> entries = new ArrayList<>(rotatedMap.entrySet());
            entries.sort((e1, e2) -> {
                String[] k1 = e1.getKey().split(",");
                String[] k2 = e2.getKey().split(",");
                int y1 = Integer.parseInt(k1[1]);
                int y2 = Integer.parseInt(k2[1]);
                if (y1 != y2) return Integer.compare(y1, y2);
                int x1 = Integer.parseInt(k1[0]);
                int x2 = Integer.parseInt(k2[0]);
                return Integer.compare(x1, x2);
            });

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : entries) {
                sb.append(entry.getKey()).append(entry.getValue());
            }
            result[roll] = sb.toString();
        }
        return result;
    }

    // 辅助方法：解析 minname 字符串
    private static List<GemData> parseMinName(String minname) {
        List<GemData> list = new ArrayList<>();
        if (minname == null || minname.isEmpty()) return list;

        int idx = 0;
        int len = minname.length();
        while (idx < len) {
            // 读取 x
            int start = idx;
            while (idx < len && Character.isDigit(minname.charAt(idx))) idx++;
            if (start == idx) break; // 没有数字，出错
            int x = Integer.parseInt(minname.substring(start, idx));

            // 期望逗号
            if (idx >= len || minname.charAt(idx) != ',') break;
            idx++; // 跳过逗号

            // 读取 y
            start = idx;
            while (idx < len && Character.isDigit(minname.charAt(idx))) idx++;
            if (start == idx) break;
            int y = Integer.parseInt(minname.substring(start, idx));

            // 读取名称（直到下一个数字或结尾）
            start = idx;
            while (idx < len && !Character.isDigit(minname.charAt(idx))) idx++;
            String name = minname.substring(start, idx);
            if (name.isEmpty()) name = "?"; // 防御

            list.add(new GemData(x, y, name));
        }
        return list;
    }

    // 内部数据类，用于存储解析出的宝石信息
    private static class GemData {
        int x, y;
        String name;
        GemData(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }
    }
}