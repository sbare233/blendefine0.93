package tool;

public class NumberToChinese {
    private static final String[] DIGITS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] BASE_UNITS = {"", "十", "百", "千"};
    private static final String[] SECTION_UNITS = {"", "万", "亿"};

    //一千以下是准的
    public static String toChinese(int number) {
        if (number == 0) return "零";
        
        boolean isNegative = false;
        long num = number;
        if (num < 0) {
            isNegative = true;
            num = -num;
        }
        
        int[] sections = new int[3];
        for (int i = 0; i < 3; i++) {
            sections[i] = (int)(num % 10000);
            num /= 10000;
            if (num == 0) break;
        }
        
        StringBuilder result = new StringBuilder();
        int lastNonZeroIndex = -1;  // 记录上一个非零段的索引
        
        for (int i = 2; i >= 0; i--) {
            int section = sections[i];
            if (section == 0) continue;
            
            // 检查是否需要添加段间的"零"
            if (lastNonZeroIndex != -1 && lastNonZeroIndex - i > 1) {
                result.append("零");
            }
            
            result.append(convertSection(section));
            result.append(SECTION_UNITS[i]);
            lastNonZeroIndex = i;  // 更新最后一个非零段的索引
        }
        
        return isNegative ? "负" + result : result.toString();
    }

    private static String convertSection(int section) {
        if (section == 0) return "";
        
//        int qian = section / 1000;        // 千位
        int bai = (section % 1000) / 100; // 百位
        int shi = (section % 100) / 10;   // 十位
        int ge = section % 10;            // 个位
        
        StringBuilder sb = new StringBuilder();
        boolean needZero = false;  // 是否需要添加"零"
        boolean hasPrevNonZero = false;  // 高位是否有非零数字
//        
//        // 处理千位
//        if (qian != 0) {
//            sb.append(DIGITS[qian]).append(BASE_UNITS[3]);
//            hasPrevNonZero = true;
//        } else if (bai != 0 || shi != 0 || ge != 0) {
//            needZero = true; // 千位是0但后面有非零数字
//        }
//        
        // 处理百位
        if (bai != 0) {
            if (needZero) {
                sb.append("零");
                needZero = false;
            }
            sb.append(DIGITS[bai]).append(BASE_UNITS[2]);
            hasPrevNonZero = true;
        } else if (hasPrevNonZero && (shi != 0 || ge != 0)) {
            needZero = true; // 百位是0但前面有非零且后面有非零
        }else  needZero = false;
        
        // 处理十位
        if (shi != 0) {
            if (needZero) {
                sb.append("零");
                needZero = false;
            }
            // 特殊规则：十位的"一"不发音（如"十"而不是"一十"）
            if (shi == 1 && !hasPrevNonZero) {
                sb.append(BASE_UNITS[1]); // 只添加"十"
            } else {
                sb.append(DIGITS[shi]).append(BASE_UNITS[1]); // 正常添加
            }
            hasPrevNonZero = true;
        } else if (hasPrevNonZero && ge != 0) {
            needZero = true; // 十位是0但前面有非零且个位非零
        }else  needZero = false;
        
        // 处理个位
        if (ge != 0) {
            if (needZero) {
                sb.append("零");
            }
            sb.append(DIGITS[ge]);
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] testCases = {
            0, 1, 10, 11, 100, 101, 110, 111, 999, 1001, 1010, 1100, 1111,
            10000, 10001, 10010, 10100, 11000, 100000, 1000000, 10000000,
            123456789, 100000000, 2147483647, -123456789
        };
        
        for (int num : testCases) {
            System.out.printf("%12d → %s\n", num, toChinese(num));
        }
    }
}