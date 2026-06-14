package fx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AshEffect extends Canvas {
    
    private static int WIDTH = 300;
    private static int HEIGHT = 260;
    private static double PARTICAL_SIZE = 1;
    private static int MAX_PARTICLES = 10;
    private static int COLOR=10;
//    private static final double GRAVITY = 0.05;
    public AnimationTimer animationTimer;
    private final Random random = new Random();
    private List<AshParticle> particles = new ArrayList<>();
    private GraphicsContext gc;

    public AshEffect (){
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        PARTICAL_SIZE=0.8;
        gc = this.getGraphicsContext2D();
        // 初始化灰烬粒子
        generateParticles();
        // 创建动画循环
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateParticles();
                render();
            }
        };
        animationTimer.start();
    }
    public AshEffect (int w,int h,int centerx,int centery,double particalsize,int particalnum,int color){
        this.setWidth(w);
        this.setHeight(h);
        WIDTH=w;HEIGHT=h;
        PARTICAL_SIZE=particalsize;
        MAX_PARTICLES=particalnum;
        COLOR=color;
        gc = this.getGraphicsContext2D();
        // 初始化灰烬粒子
        generateParticles(centerx,centery);
        // 创建动画循环
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
            	gc.clearRect(0, 0, w, h);
                updateParticles(centerx,centery);
                render();
            }
        };
        animationTimer.start();
    }

    private void generateParticles() {
        for (int i = 0; i < MAX_PARTICLES; i++) {
            particles.add(new AshParticle(
//                WIDTH / 2,  // 从中心区域生成
//                HEIGHT / 2,
            		 WIDTH / 2 + random.nextDouble() * 100 - 50,
                     HEIGHT / 2 + random.nextDouble() * 100 - 50,
                     random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE,  // X速度 (-1到1)
                     random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE, 
                random.nextDouble() * 0.04*PARTICAL_SIZE + 0.02*PARTICAL_SIZE,  // 粒子大小 (5-15)
                random.nextDouble() * 0.5 + 0.3  // 初始透明度 (0.3-0.8)
            ));
        }
    }
    private void generateParticles(int centerx,int centery) {
        for (int i = 0; i < MAX_PARTICLES; i++) {
            particles.add(new AshParticle(
//                WIDTH / 2,  // 从中心区域生成
//                HEIGHT / 2,
            		centerx,
            		centery,
//            		Math.min(Math.max(centerx + random.nextDouble() * WIDTH -WIDTH/2,0),WIDTH),
//            		Math.min(Math.max(centery + random.nextDouble() * HEIGHT -HEIGHT/2,0),WIDTH),
                     random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE,  // X速度 (-1到1)
                     random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE, 
                random.nextDouble() * 0.04*PARTICAL_SIZE + 0.02*PARTICAL_SIZE,  // 粒子大小 (5-15)
                random.nextDouble() * 0.5 + 0.3  // 初始透明度 (0.3-0.8)
            ));
        }
    }private void updateParticles(int centerx,int centery) {
        particles.removeIf(AshParticle::isDead);
        
        // 补充新粒子
        while (particles.size() < MAX_PARTICLES) {
            particles.add(new AshParticle(
            		centerx,
            		centery,
//            		Math.min(Math.max(centerx + random.nextDouble() * WIDTH -WIDTH/2,0),WIDTH),
//            		Math.min(Math.max(centery + random.nextDouble() * HEIGHT -HEIGHT/2,0),WIDTH),
                random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE,  // X速度 (-1到1)
              random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE, 
              random.nextDouble() * 0.04*PARTICAL_SIZE + 0.02*PARTICAL_SIZE,  // 粒子大小 (5-15)
              random.nextDouble() * 0.5 + 0.3  // 初始透明度 (0.3-0.8)
            ));
        }
        
        // 更新所有粒子
        particles.forEach(p -> {
//            p.vy += GRAVITY;  // 重力
        	p.ax+=(random.nextDouble() * 0.2-0.1);
        	p.ay+=(random.nextDouble() * 0.2-0.1);
        	p.vx += p.ax*PARTICAL_SIZE;
        	p.vy += p.ay*PARTICAL_SIZE;
        	p.vx *= 0.4/Math.sqrt(PARTICAL_SIZE);// 空气阻力
        	p.vy *= 0.4/Math.sqrt(PARTICAL_SIZE);
            p.x += p.vx;
            p.y += p.vy;
//           if(p.hasmax) {
//            p.size *= 0.995;  // 逐渐缩小
            p.alpha *= 0.999;  // 逐渐变透明
//           }
//           else {
//        	   if(p.size>=2*PARTICAL_SIZE)p.hasmax=true;
//        	   p.size *= 1.01;  // 逐渐缩小
//               p.alpha *= 1.1;  // 逐渐变透明
//           }
            // 随机旋转
            p.rotation += p.rotationSpeed;
        });
    }

    private void updateParticles() {
        particles.removeIf(AshParticle::isDead);
        
        // 补充新粒子
        while (particles.size() < MAX_PARTICLES) {
            particles.add(new AshParticle(
                WIDTH / 2 + random.nextDouble() * 100 - 50,
                HEIGHT / 2 + random.nextDouble() * 100 - 50,
                random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE,  // X速度 (-1到1)
              random.nextDouble() * 2*PARTICAL_SIZE - 1*PARTICAL_SIZE, 
              random.nextDouble() * 0.04*PARTICAL_SIZE + 0.02*PARTICAL_SIZE,  // 粒子大小 (5-15)
              random.nextDouble() * 0.5 + 0.3  // 初始透明度 (0.3-0.8)
            ));
        }
        
        // 更新所有粒子
        particles.forEach(p -> {
//            p.vy += GRAVITY;  // 重力
        	p.ax+=(random.nextDouble() * 0.2-0.1);
        	p.ay+=(random.nextDouble() * 0.2-0.1);
        	p.vx += p.ax*PARTICAL_SIZE;
        	p.vy += p.ay*PARTICAL_SIZE;
        	p.vx *= 0.4/Math.sqrt(PARTICAL_SIZE);// 空气阻力
        	p.vy *= 0.4/Math.sqrt(PARTICAL_SIZE);
            p.x += p.vx;
            p.y += p.vy;
           if(p.hasmax) {
            p.size *= 0.995;  // 逐渐缩小
            p.alpha *= 0.999;  // 逐渐变透明
           }
           else {
        	   if(p.size>=2*PARTICAL_SIZE)p.hasmax=true;
        	   p.size *= 1.01;  // 逐渐缩小
               p.alpha *= 1.1;  // 逐渐变透明
           }
            // 随机旋转
            p.rotation += p.rotationSpeed;
        });
    }

    private void render() {
        // 半透明背景刷新（创建拖尾效果）
//        gc.setFill(Color.rgb(255, 255, 255, 0.001));
//        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        // 绘制所有灰烬粒子
        particles.forEach(p -> {
            gc.save();
            gc.setGlobalAlpha(p.alpha);
            gc.setFill(p.color);
            
            // 应用旋转和平移
            gc.translate(p.x, p.y);
            gc.rotate(p.rotation);
            
            // 绘制椭圆形灰烬
            gc.fillOval(-p.size, -p.size / 4, p.size * 4, p.size);
            
            gc.restore();
        });
    }

    // 灰烬粒子类
    class AshParticle {
        double x, y;
        double vx, vy;
        double ax,ay;
        double size;
        double alpha;
        Color color;
        double rotation = 0;
        boolean hasmax;
        double rotationSpeed = random.nextDouble() * 4 - 2;  // -2到2度/帧
        
        AshParticle(double x, double y, double vx, double vy, double size, double alpha) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.size = size;
            this.alpha = alpha;
            this.ax=0;
            this.ay=0;
            this.hasmax=false;
            // 随机灰烬颜色（灰黑色系）
            int grayValue = COLOR + random.nextInt(30);
            this.color = Color.rgb(grayValue, grayValue, grayValue);
        }
        
        boolean isDead() {
            return alpha < 0.05 || size < 0.01 || y > HEIGHT + 50;
        }
    }
 // 销毁方法实现
    public void destroy() {
        // 1. 停止动画定时器
        if (animationTimer != null) {
            animationTimer.stop();
            animationTimer = null;
        }
 
        // 2. 清空粒子系统
        particles.clear();
        particles = null; // 显式置空帮助GC
 
        // 3. 清理画布内容
        if (gc != null) {
            gc.clearRect(0, 0, getWidth(), getHeight());
            gc = null; // 置空图形上下文
        }
 
        // 4. 可选：从父容器移除（需根据实际使用场景决定）
//        StackPane parent = (StackPane) getParent();
//        if (parent != null) {
//            parent.getChildren().remove(this);
//        }
 
        // 5. 清理静态变量（如果存在跨实例共享需求时）
        WIDTH = 0;
        HEIGHT = 0;
        PARTICAL_SIZE = 0;
        MAX_PARTICLES = 0;
        COLOR = 0;
 
        // 6. 强制垃圾回收（仅在极端情况下建议，通常由JVM自动管理）
        System.gc();
    }
}