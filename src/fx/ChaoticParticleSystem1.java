package fx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaoticParticleSystem1 extends Application {
    private static final int NUM_PARTICLES = 500; // 减少粒子数量以提高性能
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private static final double MAX_SPEED = 0.5; // 最大速度
    private static final double MIN_SPEED = 0.1; // 最小速度
    private static final double CONNECTION_DISTANCE = 50.0; // 连接线最大距离
    private static final double MAX_DISTANCE_TO_CENTER = Math.sqrt(WIDTH * WIDTH + HEIGHT * HEIGHT) *1; // 最大距离到中心
    final double GRAVITY_CONSTANT = 800;  // 引力强度系数
    final double MAX_FORCE = 0.3;         // 最大作用力限制
    final double MIN_DISTANCE = 20;       // 最小有效距离
    
    private List<Particle> particles = new ArrayList<>();
    private Canvas canvas;
    private GraphicsContext gc;
    private Random random = new Random();
    private long startTime;
    private double time = 0;
    private double globalHue = 0;
    private double centerX = WIDTH / 2.0;
    private double centerY = HEIGHT / 2.0;
    private double mouseX = -1;
    private double mouseY = -1;
    // 粒子类
    class Particle {
        double x0, y0,x,y; // 位置
        double vx, vy; // 速度
        double size; // 大小
        double hue; // 颜色色调
        double life; // 生命周期
        double maxLife; // 最大生命周期
        double angle;
        
        Particle() {
            reset();
        }
        
        void reset() {
            x0 = random.nextDouble() * WIDTH/3;//方形边界
            y0 = random.nextDouble() * HEIGHT/3;
            angle=random.nextDouble()*2*Math.PI;
            
        	double sin=Math.sin(angle),cos=Math.cos(angle);
        	x=x0*cos-y0*sin+WIDTH/2;
    		y=x0*sin+y0*cos+HEIGHT/2;
            
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
            // 添加鼠标吸引力逻辑
            if (mouseX >= 0 && mouseY >= 0) {
                double dx = mouseX - x;
                double dy = mouseY - y;
                double dist = Math.hypot(dx, dy);
         
                if (dist > 0) {
                    // 吸引力参数配置
                   
         
                    // 计算基础引力（反比关系）
                    double force = GRAVITY_CONSTANT / (dist * dist + 1);
                    force = Math.min(force, MAX_FORCE);    // 限制最大力
                    force = Math.max(force, 0.01);         // 保证最小作用力
         
                    // 计算方向向量并应用力
                    double angle = Math.atan2(dy, dx);
                    vx += Math.cos(angle) * force * 0.1;  // 0.1为作用强度调节
                    vy += Math.sin(angle) * force * 0.1;
         
                    // 距离衰减处理（越近作用越弱）
                    if (dist < MIN_DISTANCE) {
                        double damp = dist / MIN_DISTANCE;
                        vx *= damp;
                        vy *= damp;
                    }
                }
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
            // 计算到屏幕中心的距离
            double dx = x - centerX;
            double dy = y - centerY;
            double distToCenter = Math.sqrt(dx * dx + dy * dy);
            
            // 根据距离计算透明度因子 (离中心越远越透明)
            double centerAlpha = 1.0 - Math.min(1.0, distToCenter / MAX_DISTANCE_TO_CENTER) * 0.7;
            
            // 计算颜色（基于生命周期和全局色调）
            double ageFactor = 1 - (life / maxLife);
            double saturation = 0.6 + 0.4 * Math.sin(time * 0.5);
            double brightness = 0.7 + 0.3 * Math.cos(time * 0.7);
            
            // 动态颜色混合
            double mixedHue = (hue + globalHue) % 360;
            
            // 应用距离透明度因子
            Color color = Color.hsb(mixedHue, saturation, brightness, centerAlpha * ageFactor * 0.8);
            gc.setFill(color);
            
            // 绘制粒子
            gc.fillOval(x - size/2, y - size/2, size, size);
            
            // 绘制粒子轨迹
            if (random.nextDouble() < 0.3) {
                double trailSize = size * 0.6;
                gc.setFill(Color.hsb(mixedHue, saturation * 0.8, brightness * 0.9, centerAlpha * ageFactor * 0.3));
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
        scene.setFill(null);
      	stage.initStyle(StageStyle.TRANSPARENT);
        // 初始化粒子
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Particle());
        }
        scene.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
        scene.setOnMouseExited(e -> {
            mouseX = -1;
            mouseY = -1;
        });
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
        
        stage.setTitle("Chaotic Particle System with Connections");
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
//        gc.setFill(Color.rgb(10, 5, 20, 0.08));
//        gc.fillRect(0, 0, WIDTH, HEIGHT);
    	gc.clearRect(0, 0, WIDTH, HEIGHT);
        // 绘制连接线（在绘制粒子之前）
        drawConnections();
        
        // 绘制所有粒子
        for (Particle p : particles) {
            p.draw();
        }
        
        // 绘制信息
        drawInfo();
    }
    
    // 绘制粒子间的连接线
    private void drawConnections() {
        int connections = 0;
        
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            
            // 计算p1到中心的距离
            double dx1 = p1.x - centerX;
            double dy1 = p1.y - centerY;
            double distToCenter1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
            double centerAlpha1 = 1.0 - Math.min(1.0, distToCenter1 / MAX_DISTANCE_TO_CENTER) * 0.7;
            
            for (int j = i + 1; j < particles.size(); j++) {
                Particle p2 = particles.get(j);
                
                // 计算两个粒子之间的距离
                double dx = p1.x - p2.x;
                double dy = p1.y - p2.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                
                // 如果距离小于阈值，绘制连接线
                if (distance < CONNECTION_DISTANCE) {
                    // 计算p2到中心的距离
                    double dx2 = p2.x - centerX;
                    double dy2 = p2.y - centerY;
                    double distToCenter2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
                    double centerAlpha2 = 1.0 - Math.min(1.0, distToCenter2 / MAX_DISTANCE_TO_CENTER) * 0.7;
                    
                    // 计算平均透明度（基于两个粒子到中心的距离）
                    double avgCenterAlpha = (centerAlpha1 + centerAlpha2) / 2.0;
                    
                    // 根据距离计算线的不透明度（距离越远越透明）
                    double distanceFactor = 1.0 - (distance / CONNECTION_DISTANCE);
                    double lineOpacity = distanceFactor * avgCenterAlpha * 0.8;
                    
                    // 设置线条样式
                    gc.setStroke(Color.rgb(255, 255, 255, lineOpacity));
                    gc.setLineWidth(0.5);
                    
                    // 绘制连接线
                    gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
                    connections++;
                }
            }
        }
    }
    
    private void drawInfo() {
        gc.setFill(Color.WHITE);
        gc.fillText("Connected Particle System", 20, 20);
        gc.fillText("Particles: " + NUM_PARTICLES, 20, 40);
        
        // 绘制标题装饰
        double wave = Math.sin(time * 3) * 5;
        gc.setFill(Color.hsb((globalHue + 180) % 360, 0.8, 0.9));
        gc.fillText("CONNECTED MOTION", WIDTH/2 - 100, 30 + wave);
    }

    public static void main(String[] args) {
        launch(args);
    }
}