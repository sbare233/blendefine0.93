package fx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AnimatedWave1 extends Group {
    private final FloatMap map = new FloatMap();
    private final DisplacementMap dm = new DisplacementMap();
    private float time = 0;
    private PerlinNoise perlinRefraction = new PerlinNoise();
    private  PerlinNoise perlinCaustics = new PerlinNoise(); // 焦散效果噪声
    private  PerlinNoise perlinShadow = new PerlinNoise();    // 阴影效果噪声

    // 参数配置
    private float refractionScale = 0.02f;		//折射效果密度
    private float refractionSpeed = 1.5f;    //折射效果速度
    private float refractionStrength = 10;   //折射效果强度
    private float causticsScale = 0.02f;      // 焦散噪声密度
    private float causticsSpeed = 1f;        // 焦散动画速度
    private float causticsStrength = 0.6f;     // 焦散强度
    private float shadowScale = 0.03f;         // 阴影噪声密度
    private float shadowSpeed = 1f;          // 阴影动画速度
    private float shadowStrength = 1.9f;       // 阴影强度
    private final float[] alphaMap; // 存储每个像素的透明度
    private static final float ALPHA_THRESHOLD = 0.1f; // 透明阈值
    private int frameCounter = -1;
    private WritableImage refractionImage;
    private WritableImage causticsImage;
    private WritableImage shadowImage;
    private PixelWriter refractionWriter;
    private PixelWriter causticsWriter;
    private PixelWriter shadowWriter;
    private final float[] displacementBuffer;
    private final float[][] trigradiant;
    private final int[] refractionBuffer;
    private final int[] causticsBuffer;
    private final int[] shadowBuffer;
    public AnimationTimer timer;
    private final Canvas ca;
    public AnimatedWave1(int w, int h,Image i,int upf,int fxlv) {
        dm.setMapData(map);
       
        map.setWidth(w);
        map.setHeight(h);
        
        // 初始化重用资源
        refractionImage = new WritableImage(w, h);
        causticsImage = new WritableImage(w, h);
        shadowImage = new WritableImage(w, h);
        refractionWriter = refractionImage.getPixelWriter();
        causticsWriter = causticsImage.getPixelWriter();
        shadowWriter = shadowImage.getPixelWriter();
        displacementBuffer = new float[w * h * 3];
        refractionBuffer = new int[w * h];
        causticsBuffer = new int[w * h];
        shadowBuffer = new int[w * h];
        trigradiant=new float[w][h];
        ca=new Canvas();
        // 初始化透明度映射
        alphaMap = new float[w * h];
        PixelReader reader = i.getPixelReader();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                alphaMap[y * w + x] = (float) reader.getColor(x, y).getOpacity();
                if(upf==0)
                	trigradiant[x][y]=(float) Math.sqrt(triangleGradient(w,h,x,y));
                else if(upf==1)
                	trigradiant[x][y]=(float) Math.sqrt(triangleGradient1(w,h,x,y));
                else
                	trigradiant[x][y]=1;
//                System.out.println(x+" "+y+" "+ trigradiant[x][y]);
            }
        }

     // 添加基础图像
        ImageView baseImageView = new ImageView(i);
        getChildren().add(baseImageView);
        
        // 添加阴影效果层
        Rectangle shadowLayer = new Rectangle(w, h);
        shadowLayer.setFill(new ImagePattern(shadowImage));
        shadowLayer.setOpacity(0.7);
        getChildren().add(shadowLayer);
        
        // 添加焦散效果层
        Rectangle causticsLayer = new Rectangle(w, h);
        causticsLayer.setFill(new ImagePattern(causticsImage));
        causticsLayer.setOpacity(0.7);
        getChildren().add(causticsLayer);
        
        setEffect(dm);
        
        // 启用硬件加速和缓存
        setCache(true);
        baseImageView.setCache(true);
        causticsLayer.setCache(true);
        shadowLayer.setCache(true);
        startAnimation(i,fxlv);
    }
    public void destroy() {
        // 停止动画定时器
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        // 清理图像资源
        refractionImage = null;
        causticsImage = null;
        shadowImage = null;
        refractionWriter = null;
        causticsWriter = null;
        shadowWriter = null;

        // 移除所有子节点
        getChildren().clear();

        // 清理位移映射数据
        if (map != null) {
            map.setWidth(0);
            map.setHeight(0);
        }

        // 清理柏林噪声实例（可选）
        perlinRefraction = null;
        perlinCaustics = null;
        perlinShadow = null;

        // 强制GC（不推荐，但极端情况下可用）
//        System.gc();
    }
    private void startAnimation(Image originImage,int fxlv) {
    	timer = new AnimationTimer() {
            private long lastUpdate = 0;
            
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) lastUpdate = now;
                float deltaSeconds = (float) ((now - lastUpdate) / 1_000_000_000.0);
                time += deltaSeconds;
                lastUpdate = now;
                
                // 减少更新频率：每2帧更新一次效果层
                frameCounter = (frameCounter + 1) % 2;

                if (frameCounter == 0) {
                	if(fxlv>0)
                		 updateCaustics();
                	if(fxlv>1)
                		updateShadow();
                	if(fxlv>2)
                		 updateWaveColor(originImage);
                	if(fxlv>3)
                		updateWaveDisplacement();              	
                }
            }
        };
        timer.start();
    }
    // 柏林噪声生成器（支持3D噪声）
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

    private void updateWaveDisplacement() {
        final int w = map.getWidth();
        final int h = map.getHeight();
        float[][] tempMap = new float[w * h][3]; // 临时存储数组
     
        // 使用线程池并行处理
        ExecutorService executor = Executors.newWorkStealingPool();
        IntStream.range(0, h).parallel().forEach(y -> {
            for (int x = 0; x < w; x++) {
            	 // 使用预计算的透明度信息
            	int index = y * w + x;
                if (alphaMap[index] < ALPHA_THRESHOLD) {
                    refractionBuffer[index] = 0; // 完全透明
                    continue;
                }
                float noiseX = perlinRefraction.noise(
                    x * 0.05f, 
                    y * 0.05f, 
                    time * 0.5f
                );
                float noiseY = perlinRefraction.noise(
                    x * 0.05f + 20, 
                    y * 0.05f + 20, 
                    time * 0.5f + 1
                );
                tempMap[index][0] = (float)((noiseX - 0.5f) * 0.02f)*trigradiant[x][y];
                tempMap[index][1] = (float)((noiseY - 0.5f) * 0.02f)*trigradiant[x][y];
                tempMap[index][2] = 0;
            }
        });
     
        // 批量更新FloatMap
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int index = y * w + x;
                map.setSamples(x, y, tempMap[index][0], tempMap[index][1], 0);
            }
        }
        executor.shutdown();
    }
    
    // 应用折射效果（优化版）
    private void updateWaveColor(Image originImage) {
        final int w = (int) map.getWidth();
        final int h = (int) map.getHeight();
        final PixelReader reader = originImage.getPixelReader();
        final float timeFactor = time * refractionSpeed;
        
        // 使用并行流优化
        IntStream.range(0, h).parallel().forEach(y -> {
        	for (int x = 0; x < w; x++) {
                // 检查当前像素透明度
        		  int index = y * w + x;
                  
                  // 使用预计算的透明度信息
                  if (alphaMap[index] < ALPHA_THRESHOLD) {
                      refractionBuffer[index] = 0; // 完全透明
                      continue;
                  }
                
                float refraction = perlinRefraction.noise(
                    x * refractionScale,
                    y * refractionScale, timeFactor
                );
                
                int offsetX = (int)(refraction * refractionStrength*trigradiant[x][y]);
                int offsetY = (int)(refraction * refractionStrength*trigradiant[x][y]);
                
                int sampleX = Math.min(Math.max(x + offsetX, 0), w - 1);
                int sampleY = Math.min(Math.max(y + offsetY, 0), h - 1);
                
                int argb = reader.getArgb(sampleX, sampleY);
                refractionBuffer[y * w + x] = argb;
            }
        });
        refractionWriter.setPixels(0, 0, w, h, PixelFormat.getIntArgbInstance(), 
                refractionBuffer, 0, w);
        ((ImageView)getChildren().get(0)).setImage(refractionImage);
    }
 // 更新焦散效果（优化版）
    private void updateCaustics() {
        final int w = (int) causticsImage.getWidth();
        final int h = (int) causticsImage.getHeight();
        final float timeFactor = time * causticsSpeed;
        // 使用并行流优化
        IntStream.range(0, h).parallel().forEach(y -> {
            for (int x = 0; x < w; x++) {
            	int index = y * w + x;
                
                // 使用预计算的透明度信息
                if (alphaMap[index] < ALPHA_THRESHOLD) {
                    causticsBuffer[index] = 0; // 完全透明
                    continue;
                }
                float noise = perlinCaustics.noise(
                    x * causticsScale,
                    y * causticsScale, timeFactor
                );
                
                float intensity = (float) Math.max(0, (Math.sqrt(Math.abs(noise)) - 0.7f) * 2);
                float alpha = Math.min(1, intensity * 2*trigradiant[x][y]);
                
                // 直接计算ARGB值（避免创建Color对象）
                int r = 255;
                int g = 255;
                int b = 255;
                int a = (int) (alpha * 255);
                int argb = (a << 24) | (r << 16) | (g << 8) | b;
                causticsBuffer[y * w + x] = argb;
                
            }
        });
        
        // 批量写入图像
        causticsWriter.setPixels(0, 0, w, h, PixelFormat.getIntArgbInstance(), 
                                causticsBuffer, 0, w);
    }
 // 更新阴影效果（优化版）
    private void updateShadow() {
        final int w = (int) shadowImage.getWidth();
        final int h = (int) shadowImage.getHeight();
        final float timeFactor = time * shadowSpeed;
        
        // 使用并行流优化
        IntStream.range(0, h).parallel().forEach(y -> {
            float waveEffect = (float) (Math.sin(y * 0.03f + time * 2) * 0.1f);
            for (int x = 0; x < w; x++) {
            	int index = y * w + x;
                
                // 使用预计算的透明度信息
                if (alphaMap[index] < ALPHA_THRESHOLD) {
                    causticsBuffer[index] = 0; // 完全透明
                    continue;
                }
                float noise = perlinShadow.noise(
                    x * shadowScale + 100,
                    y * shadowScale + 100 , timeFactor
                );
                
                float intensity = Math.abs(noise) + waveEffect;
                float shadowValue = (float) (1 - Math.min(Math.sqrt(intensity * shadowStrength - 0.1f), 0.8f));
                float alpha = (float) Math.max((1 - shadowValue) * 0.9f*trigradiant[x][y], 0);
                
                // 直接计算ARGB值（避免创建Color对象）
                int r = 25;
                int g = 25;
                int b = 25;
                int a = (int) (alpha * 255);
                int argb = (a << 24) | (r << 16) | (g << 8) | b;
                shadowBuffer[y * w + x] = argb;
            }
        });
        
        // 批量写入图像
        shadowWriter.setPixels(0, 0, w, h, PixelFormat.getIntArgbInstance(), 
                              shadowBuffer, 0, w);
    }
    public static float triangleGradient(float w,float h,float x, float y) {
        // 三角形顶点坐标
        final float[] A = {0, 0};
        final float[] B = {w, 0};
        final float[] C = {w/2, h};
        final float areaABC = w*h/2;  // 原三角形面积
 
        // 计算三个子三角形面积
        float areaPBC = area(new float[]{x, y}, B, C);
        float areaAPC = area(new float[]{x, y}, A, C);
        float areaABP = area(new float[]{x, y}, A, B);
 
        // 计算重心坐标
        float alpha = areaPBC / areaABC;
        float beta = areaAPC / areaABC;
        float gamma = areaABP / areaABC;
 
        // 返回渐变值（取最小值并放大3倍）
        return (float) (3 * Math.min(Math.min(alpha, beta), gamma));
    }
    public static float triangleGradient1(float w,float h,float x, float y) {
        // 三角形顶点坐标
        final float[] A = {0, h};
        final float[] B = {w, h};
        final float[] C = {w/2, 0};
        final float areaABC = w*h/2;  // 原三角形面积
 
        // 计算三个子三角形面积
        float areaPBC = area(new float[]{x, y}, B, C);
        float areaAPC = area(new float[]{x, y}, A, C);
        float areaABP = area(new float[]{x, y}, A, B);
 
        // 计算重心坐标
        float alpha = areaPBC / areaABC;
        float beta = areaAPC / areaABC;
        float gamma = areaABP / areaABC;
 
        // 返回渐变值（取最小值并放大3倍）
        return (float) (3 * Math.min(Math.min(alpha, beta), gamma));
    }
    // 计算三角形面积的辅助方法
    private static float area(float[] fs, float[] a, float[] c) {
        return (float) (0.5 * Math.abs(
            (a[0] - c[0]) * (fs[1] - c[1]) - 
            (a[1] - c[1]) * (fs[0] - c[0])
        ));
    }
}