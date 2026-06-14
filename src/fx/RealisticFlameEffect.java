package fx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RealisticFlameEffect extends Group {

    private static int WIDTH = 300;
    private static int HEIGHT = 260;
    private static final int NUM_PARTICLES = 1500;
    private static final double FLAME_INTENSITY = 1.5;
    private static final double OFFSETY =43.5;
    
    private List<FlameParticle> particles = new ArrayList<>();
    private Canvas canvas;
    private GraphicsContext gc;
    private Random random = new Random();
    private double time = 0;
    AnimationTimer at;
    // 火焰粒子类
    class FlameParticle {
        double x, y;         // 位置
        double vx, vy;       // 速度
        double size;         // 粒子大小
        double life;         // 当前生命周期
        double maxLife;      // 最大生命周期
        Color color;         // 粒子颜色
        double rotation;     // 粒子旋转角度
        double rotationSpeed;// 旋转速度

        FlameParticle() {
            reset();
        }

        void reset() {
            // 从底部中心生成粒子
            x = WIDTH / 2+ (random.nextDouble() - 0.5) * 80;
            y = HEIGHT/2-43.5 + (random.nextDouble() - 0.5) * 80;
            
            // 初始速度 - 向上为主，加上随机水平扩散
//            vy = -3.5 - random.nextDouble() * 2.0;
//            vx = (random.nextDouble() - 0.5) * 1.8;
            vy = ( random.nextDouble()-0.5 )* 4;
            vx = (random.nextDouble() - 0.5) * 4;
            size = 3 + random.nextDouble() * 8;
            maxLife = 40 + random.nextDouble() * 60;
            life = maxLife;
            
            // 随机旋转
            rotation = random.nextDouble() * 360;
            rotationSpeed = (random.nextDouble() - 0.5) * 4;
            
            // 初始颜色为亮黄色
            color = Color.YELLOW.deriveColor(0, 1, 1, 0.8);
        }

        void update() {
            // 应用物理运动
            x += vx;
            y += vy;
            
            // 添加湍流效果 - 水平随机扰动
            vx += (random.nextDouble() - 0.5) * 0.25;
            
            // 添加浮力效果 - 上升加速度
//            vy -= 0.05;
            vy+= (random.nextDouble() - 0.5) * 0.25;
            // 空气阻力 - 速度衰减
            vx *= 0.98;
            vy *= 0.98;
            
            // 粒子旋转
            rotation += rotationSpeed;
            
            // 更新生命周期
            life--;
            if (life <= 0) {
                reset();
            }
            
            // 根据生命周期改变粒子属性
            double lifeRatio = life / maxLife;
            
            // 大小变化 - 粒子逐渐变大然后变小
            if (lifeRatio > 0.5) {
                size += 0.15;
            } else {
                size *= 0.95;
            }
            
            // 颜色变化 - 从黄色到橙色到红色再到暗红色
            if (lifeRatio > 0.7) {
                // 亮黄色到橙色
                color = Color.color(1.0, lifeRatio, 0.0, 0.9);
            } else if (lifeRatio > 0.4) {
                // 橙色到红色
                color = Color.color(1.0, lifeRatio * 0.8, 0.0, 0.8);
            } else if (lifeRatio > 0.2) {
                // 红色到暗红色
                color = Color.color(1.0, 0.0, 0.0, lifeRatio * 0.7);
            } else {
                // 暗红色到消失
                color = Color.color(0.5, 0.0, 0.0, lifeRatio * 0.4);
            }
        }

        void draw() {
            // 绘制火焰粒子
            gc.save();
            gc.setFill(color);
            gc.translate(x, y);
            gc.rotate(rotation);
            
            // 绘制不同形状的粒子 - 火焰的不规则性
            if (random.nextDouble() > 0.3) {
                // 椭圆形粒子
                gc.fillOval(-size / 2, -size / 4, size, size / 2);
            } else {
                // 矩形粒子
                gc.fillRect(-size / 2, -size / 4, size, size / 2);
            }
            
            gc.restore();
        }
    }

    public RealisticFlameEffect(int w,int h) {
    	WIDTH=w;
    	HEIGHT=h;
        // 创建Canvas作为绘制表面
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
     // 通过路径命令定义裁剪区域
      
        	  gc.beginPath();
              gc.moveTo(0, 0);    // 顶点
              gc.lineTo(w, 0);   // 右下点
              gc.lineTo(w/2, h);     // 左下点
              gc.closePath();        // 闭合路径
              gc.clip();              // 应用裁剪
    	

   
        // 创建带模糊效果的Group
        getChildren().add(canvas);
        // 应用高斯模糊效果
        GaussianBlur blur = new GaussianBlur(5);
        setEffect(blur);
        // 设置混合模式为ADD，增强火焰亮度
        setBlendMode(BlendMode.ADD);
        // 初始化粒子
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new FlameParticle());
        }
        // 设置动画循环
        at=new AnimationTimer() {
            @Override
            public void handle(long now) {
//            	gc.setFill(Color.color(0,0,0));
//            	gc.fillRect(0, 0, 0, 0);
                update();
                render();
                time += 0.016;
            }
        };
        at.start();
        // 设置舞台
    }
    
    private void update() {
        // 更新所有粒子
        for (FlameParticle particle : particles) {
            particle.update();
        }
    }
    private void render() {
        for (FlameParticle particle : particles) {
            particle.draw();
        }
    }
    public void destroy() {
        // 停止动画定时器
        if (at != null) {
        	at.stop();
        	at = null;
        }
        particles .clear();
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        particles=null;
        canvas=null;
        gc=null;
        random = null;
        // 移除所有子节点
        getChildren().clear();

        // 强制GC（不推荐，但极端情况下可用）
//        System.gc();
    }
}