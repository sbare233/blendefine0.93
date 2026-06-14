package fx;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DragonLightning extends Canvas {
    
    private float f = 0;
    private GraphicsContext gc;
    static int w = 1200;
    static int h = 1200;
    private PerlinNoise noise1 = new PerlinNoise();
    AnimationTimer at;
    private double noise(float x, float y,float z) {
        return noise1.noise(x, y, z);
    }
    
    public DragonLightning() {
    	super(w, h);
        gc = getGraphicsContext2D();
        
        // 动画循环
        at=new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
                f++;
            }
        };
        at.start();
    }
    public void destroy() {
        // 停止动画定时器
        if (at != null) {
        	at.stop();
        	at = null;
        }
        gc.clearRect(0, 0, w, h);
        noise1=null;
        gc=null;

        // 强制GC（不推荐，但极端情况下可用）
//        System.gc();
    }
    private void draw() {
        // 清空画布（黑色背景）
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, w, h);
    	gc.clearRect(0, 0, w, h);
        
//    	Color color = Color.rgb(175,255,150, 1); // 近似原色
//        gc.setFill(color);
//    	gc.fillOval(585, 585, 30, 30);
    	int[] rs= {1,1,-1,0};
        int r = 4;
        while (r-- > 0) {
//            // 设置线条粗细
            gc.setLineWidth(r*3 + 1);
            
            double g = 0;
            double hPos = 625; // 重命名避免与高度变量冲突
            
            // 设置线条颜色
//            Color strokeColor = Color.rgb(175-r*30,255-r*30,150-r*30, 1); // 近似原色
            Color strokeColor = Color.rgb(205-r*30,255-r*30,180-r*30, 1); // 近似原色
            gc.setStroke(strokeColor);
            
            double a = 40 + 8 * r;
            
            //k是长度
            for (int k = 1; k <= 30; k++) {
                double y = 100+k * 17 + a * noise(f / 6, k, r) - a / 2;
                double x = 625 + 
                		30*rs[r] * Math.sin(Math.PI * k / 30) * 
                          Math.sin((k + f / 1.6 + r * 2) / 6.366) + 
                          a * noise(k, f / 6, r) - a / 2;
//                double y=200;
                
                if (k > 1) {
                    // 绘制线条
                    gc.strokeLine(g, hPos, x, y);
                }
                
                g = x;
                hPos = y;
            }
        }
    }
    private static class PerlinNoise {
        private final int[] p = new int[512];
        
        public PerlinNoise() {
            int[] permutation = new int[256];
            for (int i = 0; i < 256; i++) permutation[i] = i;
            // 随机打乱排列
            for (int i = 0; i < 256; i++) {
                int j = (int)(Math.random() * (256 - i)) + i;
                int temp = permutation[i];
                permutation[i] = permutation[j];
                permutation[j] = temp;
            }
            // 双倍排列用于快速查找
            System.arraycopy(permutation, 0, p, 0, 256);
            System.arraycopy(permutation, 0, p, 256, 256);
        }
        
        // 3D柏林噪声
        public float noise(float x, float y, float z) {
            int X = (int)Math.floor(x) & 255;
            int Y = (int)Math.floor(y) & 255;
            int Z = (int)Math.floor(z) & 255;
            x -= Math.floor(x);
            y -= Math.floor(y);
            z -= Math.floor(z);
            float u = fade(x);
            float v = fade(y);
            float w = fade(z);
            
            int A = p[X] + Y, AA = p[A] + Z, AB = p[A + 1] + Z;
            int B = p[X + 1] + Y, BA = p[B] + Z, BB = p[B + 1] + Z;
            
            return lerp(w, 
            	    lerp(v, 
            	        lerp(u, grad(p[AA], x, y, z), grad(p[BA], x-1, y, z)),
            	        lerp(u, grad(p[AB], x, y-1, z), grad(p[BB], x-1, y-1, z))
            	    ),
            	    lerp(v, 
            	        lerp(u, grad(p[AA+1], x, y, z-1), grad(p[BA+1], x-1, y, z-1)),
            	        lerp(u, grad(p[AB+1], x, y-1, z-1), grad(p[BB+1], x-1, y-1, z-1))
            	    )
            	);
//            return lerp(w, lerp(v, lerp(u, grad(p[AA], x, y, z), grad(p[BA], x - 1, y, z)),
//                lerp(v, lerp(u, grad(p[AB], x, y - 1, z),grad(p[BB], x - 1, y - 1, z))),
//                lerp(u, grad(p[AA + 1], x, y, z - 1), grad(p[BA + 1], x - 1, y, z - 1))),
//                lerp(v, lerp(u, grad(p[AB + 1], x, y - 1, z - 1), grad(p[BB + 1], x - 1, y - 1, z - 1))));
        }
        
//        private float fade(float t) {
//            return t * t * t * (t * (t * 6 - 15) + 10);
//        }
        private float fade(float t) {
            return t * t * (3 - 2 * t); // 二次插值替代三次插值
        }
        private float lerp(float t, float a, float b) {
            return a + t * (b - a);
        }
        
        private float grad(int hash, float x, float y, float z) {
            int h = hash & 15;
            float u = h < 8 ? x : y;
            float v = h < 4 ? y : h == 12 || h == 14 ? x : z;
            return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
        }
    }
}