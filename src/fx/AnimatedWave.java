package fx;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AnimatedWave extends Group {
    private final FloatMap map = new FloatMap();
    private final DisplacementMap dm = new DisplacementMap();
    private double time = 0;
    private final PerlinNoise perlinRefraction = new PerlinNoise();
    private final PerlinNoise perlinCaustics = new PerlinNoise(); // 焦散效果噪声
    private final PerlinNoise perlinShadow = new PerlinNoise();    // 阴影效果噪声

    // 参数配置
    private double refractionScale = 0.02;		//折射效果密度
    private double refractionSpeed = 1.5;    //折射效果速度
    private double refractionStrength = 10;   //折射效果强度
    private double causticsScale = 0.02;      // 焦散噪声密度
    private double causticsSpeed = 1.5;        // 焦散动画速度
    private double causticsStrength = 0.6;     // 焦散强度
    private double shadowScale = 0.03;         // 阴影噪声密度
    private double shadowSpeed = 2;          // 阴影动画速度
    private double shadowStrength = 1.9;       // 阴影强度

    public AnimatedWave(int w, int h,Image i) {
        dm.setMapData(map);
       
        map.setWidth(w);
        map.setHeight(h);
        
        // 添加水底基础图像
        ImageView baseImageView = new ImageView(i);
        getChildren().add(baseImageView);
        
        // 添加阴影效果层（半透明叠加）
        Rectangle shadowLayer = new Rectangle(w, h);
        shadowLayer.setOpacity(1); // 半透明效果
        getChildren().add(shadowLayer);
        
        
        // 添加焦散效果层（半透明叠加）
        Rectangle causticsLayer = new Rectangle(w, h);
        causticsLayer.setOpacity(1); // 半透明效果
        getChildren().add(causticsLayer);
        
      
        setEffect(dm);
//        setCache(true);
        startAnimation(i, causticsLayer, shadowLayer);
    }

    private void startAnimation(Image originImage, Rectangle causticsLayer, Rectangle shadowLayer) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.03; // 大约60FPS
                updateWaveDisplacement();
                updateWaveColor(originImage);
                
                updateCaustics(causticsLayer);  // 更新焦散效果
                updateShadow(shadowLayer);      // 更新阴影效果
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
        public double noise(double x, double y, double z) {
            int X = (int)Math.floor(x) & 255;
            int Y = (int)Math.floor(y) & 255;
            int Z = (int)Math.floor(z) & 255;
            x -= Math.floor(x);
            y -= Math.floor(y);
            z -= Math.floor(z);
            double u = fade(x);
            double v = fade(y);
            double w = fade(z);
            
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
        
        private double fade(double t) {
            return t * t * t * (t * (t * 6 - 15) + 10);
        }
        
        private double lerp(double t, double a, double b) {
            return a + t * (b - a);
        }
        
        private double grad(int hash, double x, double y, double z) {
            int h = hash & 15;
            double u = h < 8 ? x : y;
            double v = h < 4 ? y : h == 12 || h == 14 ? x : z;
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
                double noiseX = perlinRefraction.noise(
                    x * 0.05, 
                    y * 0.05, 
                    time * 0.5
                );
                double noiseY = perlinRefraction.noise(
                    x * 0.05 + 5, 
                    y * 0.05 + 5, 
                    time * 0.5 + 2
                );
                
                int index = y * w + x;
                tempMap[index][0] = (float)((noiseX - 0.5) * 0.02);
                tempMap[index][1] = (float)((noiseY - 0.5) * 0.02);
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
    
    // 应用折射效果
    private void applyRefractionEffect(Image originImage, WritableImage image, int w, int h) {
        PixelReader reader = originImage.getPixelReader();
        PixelWriter writer = image.getPixelWriter();
        
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double animatedY = y * refractionScale + time * refractionSpeed;
                
                double refraction = perlinRefraction.noise(
                    x * refractionScale,
                    animatedY,
                    time * 0.3
                );
                
                int offsetX = (int)(refraction * refractionStrength);
                int offsetY = (int)(refraction * refractionStrength * 0.7);
                
                int sampleX = Math.min(Math.max(x + offsetX, 0), w - 1);
                int sampleY = Math.min(Math.max(y + offsetY, 0), h - 1);
                
                Color color = reader.getColor(sampleX, sampleY);
                writer.setColor(x, y, color);
            }
        }
    }

    // 更新焦散效果（水底光斑）
    private void updateCaustics(Rectangle layer) {
        int w = (int)layer.getWidth();
        int h = (int)layer.getHeight();
        WritableImage causticsImage = new WritableImage(w, h);
        PixelWriter writer = causticsImage.getPixelWriter();
        
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                // 使用3D噪声创建动态焦散图案
                double noise = perlinCaustics.noise(
                    x * causticsScale,
                    y * causticsScale,
                    time * causticsSpeed
                );
                
                // 增强对比度，创建明亮光斑效果
//                double intensity = Math.pow(Math.abs(noise), 2) * 2.5;
//                double intensity = Math.pow(Math.abs(noise)-0.15, 2) * 2;
//                double intensity = (Math.sqrt(Math.abs(noise)-0.6) * 2);
                double intensity = Math.max(0,(Math.sqrt(Math.abs(noise))-0.7) * 2);
//                intensity = Math.min(intensity * causticsStrength, 1.0);
                
                // 创建蓝绿色调的光斑
//                Color color = Color.color(
//                    0.2,  // R
//                    0.8 + intensity * 0.2,  // G
//                    1.0,  // B
//                    intensity * 0.6  // 透明度
//                );
                Color color = Color.color(
                        1,  // R
                        1 ,  // G
                        1,  // B
                        Math.min(1,intensity * 2 ) // 透明度
                    );
                writer.setColor(x, y, color);
            }
        }
        
        // 应用焦散图案
        layer.setFill(new ImagePattern(causticsImage));
    }

    // 更新阴影效果（水底暗区）
    private void updateShadow(Rectangle layer) {
        int w = (int)layer.getWidth();
        int h = (int)layer.getHeight();
        WritableImage shadowImage = new WritableImage(w, h);
        PixelWriter writer = shadowImage.getPixelWriter();
        
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                // 使用3D噪声创建动态阴影
                double noise = perlinShadow.noise(
                    x * shadowScale + 100,
                    y * shadowScale + 100,
                    time * shadowSpeed
                );
                
                // 创建波浪形阴影效果
                double waveEffect = Math.sin(x * 0.05 + time * 2) * 0.1;
                double intensity = Math.abs(noise) + waveEffect;
                
                // 映射到0.5-1.0范围并反转
              //前一个参数调节总体参数，后一个常数可以调节深度
                double shadowValue = 1.0 - Math.min(Math.sqrt(intensity * shadowStrength-0.1), 0.8);
//                double shadowValue = 1.0 - Math.min(Math.pow(intensity * shadowStrength,2), 0.8);
//                double shadowValue = 1.0 - Math.min(intensity * shadowStrength, 0.8);
                
                // 创建蓝色调的阴影
//                Color color = Color.color(
//                    0.1, 
//                    0.1, 
//                    0.1, 
//                    Math.max((1 - shadowValue) * 0.6,0)
//                );
                Color color = Color.color(
                        0.1, 
                        0.1, 
                        0.1, 
                        Math.max((1 - shadowValue) * 0.9,0)
                    );
                writer.setColor(x, y, color);
            }
        }
        
        // 应用阴影图案
        layer.setFill(new ImagePattern(shadowImage));
    }

    // 更新水底颜色（包含折射效果）
    private void updateWaveColor(Image originImage) {
        final int w = map.getWidth();
        final int h = map.getHeight();
        WritableImage tempImage = new WritableImage(w, h);
        applyRefractionEffect(originImage, tempImage, w, h);
        ((ImageView)getChildren().get(0)).setImage(tempImage);
    }
}