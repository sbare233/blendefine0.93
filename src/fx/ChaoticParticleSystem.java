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

public class ChaoticParticleSystem extends Application {
    private static final int NUM_PARTICLES = 1000; // 粒子数量
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final double MAX_SPEED = 2.5; // 最大速度
    private static final double MIN_SPEED = 0.5; // 最小速度
    
    private List<Particle> particles = new ArrayList<>();
    private Canvas canvas;
    private GraphicsContext gc;
    private Random random = new Random();
    private long startTime;
    private double time = 0;
    private double globalHue = 0;

    // 粒子类
    class Particle {
        double x, y; // 位置
        double vx, vy; // 速度
        double size; // 大小
        double hue; // 颜色色调
        double life; // 生命周期
        double maxLife; // 最大生命周期
        
        Particle() {
            reset();
        }
        
        void reset() {
            x = random.nextDouble() * WIDTH;
            y = random.nextDouble() * HEIGHT;
            
            // 随机速度方向
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = MIN_SPEED + random.nextDouble() * (MAX_SPEED - MIN_SPEED);
            vx = Math.cos(angle) * speed;
            vy = Math.sin(angle) * speed;
            
            size = 3 + random.nextDouble() * 8;
            hue = random.nextDouble() * 360;
            life = 0;
            maxLife = 100 + random.nextDouble() * 400;
        }
        
        void update() {
            // 更新位置
            x += vx;
            y += vy;
            
            // 边界反弹
            if (x < 0) {
                x = 0;
                vx = -vx * 0.8; // 能量损失
            } else if (x > WIDTH) {
                x = WIDTH;
                vx = -vx * 0.8;
            }
            
            if (y < 0) {
                y = 0;
                vy = -vy * 0.8;
            } else if (y > HEIGHT) {
                y = HEIGHT;
                vy = -vy * 0.8;
            }
            
            // 添加一点随机扰动
            vx += (random.nextDouble() - 0.5) * 0.2;
            vy += (random.nextDouble() - 0.5) * 0.2;
            
            // 速度限制
            double speed = Math.sqrt(vx * vx + vy * vy);
            if (speed > MAX_SPEED) {
                vx = vx * MAX_SPEED / speed;
                vy = vy * MAX_SPEED / speed;
            }
            
            // 更新生命周期
            life++;
            if (life > maxLife) {
                reset();
            }
            
            // 随机改变大小
            size += (random.nextDouble() - 0.5) * 0.1;
            size = Math.max(1, Math.min(15, size));
        }
        
        void draw() {
            // 计算颜色（基于生命周期和全局色调）
            double ageFactor = 1 - (life / maxLife);
            double saturation = 0.6 + 0.4 * Math.sin(time * 0.5);
            double brightness = 0.7 + 0.3 * Math.cos(time * 0.7);
            
            // 动态颜色混合
            double mixedHue = (hue + globalHue) % 360;
            
            Color color = Color.hsb(mixedHue, saturation, brightness, ageFactor * 0.8);
            gc.setFill(color);
            
            // 绘制粒子
            gc.fillOval(x - size/2, y - size/2, size, size);
            
            // 绘制粒子轨迹
            if (random.nextDouble() < 0.3) {
                double trailSize = size * 0.6;
                gc.setFill(Color.hsb(mixedHue, saturation * 0.8, brightness * 0.9, ageFactor * 0.3));
                gc.fillOval(x - vx - trailSize/2, y - vy - trailSize/2, trailSize, trailSize);
            }
        }
        
        // 检查与其他粒子的碰撞
        void checkCollision(Particle other) {
            double dx = x - other.x;
            double dy = y - other.y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            // 碰撞检测
            double minDistance = (size + other.size) / 2;
            if (distance < minDistance) {
                // 碰撞响应
                double angle = Math.atan2(dy, dx);
                double targetX = x + Math.cos(angle) * minDistance;
                double targetY = y + Math.sin(angle) * minDistance;
                
                double ax = (targetX - other.x) * 0.05;
                double ay = (targetY - other.y) * 0.05;
                
                vx -= ax;
                vy -= ay;
                other.vx += ax;
                other.vy += ay;
                
                // 碰撞时交换部分颜色
                double hueDiff = (hue - other.hue) * 0.1;
                hue -= hueDiff;
                other.hue += hueDiff;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        // 初始化粒子
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Particle());
        }
        
        // 设置动画循环
        startTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
                time += 0.016; // 假设60fps
            }
        }.start();
        
        stage.setTitle("Chaotic Particle System");
        stage.setScene(scene);
        stage.show();
    }
    
    private void update() {
        // 更新全局色调
        globalHue = (globalHue + 0.2) % 360;
        
        // 更新粒子
        for (Particle p : particles) {
            p.update();
        }
        
        // 检查碰撞（简化版，仅检查相邻粒子）
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            for (int j = i + 1; j < Math.min(i + 10, particles.size()); j++) {
                Particle p2 = particles.get(j);
                p1.checkCollision(p2);
            }
        }
    }
    
    private void render() {
        // 半透明黑色背景（创建拖尾效果）
        gc.setFill(Color.rgb(10, 5, 20, 0.08));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        // 绘制所有粒子
        for (Particle p : particles) {
            p.draw();
        }
        
        // 绘制信息
        drawInfo();
    }
    
    private void drawInfo() {
        gc.setFill(Color.WHITE);
        gc.fillText("Chaotic Particle System", 20, 20);
        gc.fillText("Particles: " + NUM_PARTICLES, 20, 40);
        gc.fillText("Press R to reset", 20, HEIGHT - 20);
        
        // 绘制标题装饰
        double wave = Math.sin(time * 3) * 5;
        gc.setFill(Color.hsb((globalHue + 180) % 360, 0.8, 0.9));
        gc.fillText("CHAOTIC MOTION", WIDTH/2 - 100, 30 + wave);
    }

    public static void main(String[] args) {
        launch(args);
    }
}