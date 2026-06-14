package fx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fx.ChaoticParticleSystem1.Particle;

public class xuanwuParticles extends Canvas {
    // 可配置参数
    private int NUM_PARTICLES = 500;
    private int WIDTH = 600;
    private int HEIGHT = 800;
    private int canc = 0;
    private double MAX_SPEED = 0.5;
    private double MIN_SPEED = 0.1;
    private double psize=1;
    private double CONNECTION_DISTANCE = 50.0;
    
//    private static final double MAX_DISTANCE_TO_CENTER = Math.sqrt(WIDTH * WIDTH + HEIGHT * HEIGHT);
    //不能使用静态，会导致新建玄武影响所有其他玄武
    // 新增：固定生成点列表（可自由修改）
    private List<Point2D> FIXED_POINTS = Arrays.asList(
        new Point2D(WIDTH/2, HEIGHT/2),       // 中心点
        new Point2D(WIDTH/4, HEIGHT/4),       // 左上点
        new Point2D(3*WIDTH/4, HEIGHT/4),     // 右上点
        new Point2D(WIDTH/4, 3*HEIGHT/4),     // 左下点
        new Point2D(3*WIDTH/4, 3*HEIGHT/4)    // 右下点
    );

    // 系统参数
    final double GRAVITY_CONSTANT = 800;
    final double MAX_FORCE = 0.3;
    final double MIN_DISTANCE = 20;
    
    // 实例变量
    private List<Particle> particles = new ArrayList<>();
    private Canvas canvas;
    private GraphicsContext gc;
    private Random random = new Random();
    private long startTime;
    private double time = 0;
//    private double globalHue = 0;
    private double mouseX = -1;
    private double mouseY = -1;

    // 粒子类（修改了reset方法）
    class Particle {
        double x, y;        // 当前位置
        double vx, vy;      // 速度
        double size;        // 大小
        double hue;         // 颜色色调
        double life;        // 生命周期
        double maxLife;     // 最大生命周期
        int listpos=random.nextInt(FIXED_POINTS.size());
        
        Particle() {
            reset();
        }

        void reset() {
            // 从固定点列表随机选择初始位置
        	
            Point2D selected = FIXED_POINTS.get(listpos);
            x = selected.getX()*psize;
            y = selected.getY()*psize;

            // 随机速度方向
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = MIN_SPEED + random.nextDouble() * (MAX_SPEED - MIN_SPEED);
            vx = Math.cos(angle) * speed*psize;
            vy = Math.sin(angle) * speed*psize;

            size = (64 + random.nextDouble() * 8)*psize;
//            hue = random.nextDouble() * 360;//彩色
            hue=240;//蓝色
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
            
            //绕圆心
//            vx += (random.nextDouble() - 0.5 )*psize * 0.2+(FIXED_POINTS.get(listpos).getX()-x)*0.01;
//            vy += (random.nextDouble() - 0.5)*psize * 0.2+ (FIXED_POINTS.get(listpos).getY()-y)*0.01;
            
//             添加一点随机扰动
            vx += (random.nextDouble() - 0.5 )*psize * 0.2;
            vy += (random.nextDouble() - 0.5)*psize * 0.2;
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
                    vx += Math.cos(angle) * force * 0.1*psize;  // 0.1为作用强度调节
                    vy += Math.sin(angle) * force * 0.1*psize;
         
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
            size += (random.nextDouble() - 0.5*psize) * 0.1;
            size = Math.max(1, Math.min(15*psize, size));
        }
        
        void draw() {
            // 计算到屏幕中心的距离
//            double dx = x - centerX;
//            double dy = y - centerY;
//            double distToCenter = Math.sqrt(dx * dx + dy * dy);
            
            // 根据距离计算透明度因子 (离中心越远越透明)
//            double centerAlpha = 1.0 - Math.min(1.0, distToCenter / MAX_DISTANCE_TO_CENTER) * 0.7;
            
            // 计算颜色（基于生命周期和全局色调）
//            double ageFactor = 1 - (life / maxLife);
//            double saturation = 0.6 + 0.4 * Math.sin(time * 0.5);
            double saturation = 1-(double)listpos/(double)(FIXED_POINTS.size());
//            double brightness = 0.7 + 0.3 * Math.cos(time * 0.7);
            double brightness =(double)listpos/(double)(FIXED_POINTS.size());
            // 动态颜色混合
            double mixedHue = (hue ) % 360;
            
            // 应用距离透明度因子
//            Color color = Color.hsb(mixedHue, saturation, brightness, 1);
            RadialGradient color = new RadialGradient(
	                0, 50*psize,                       // 焦点角度和距离
	                x-size/2,y-size/2,                   // 焦点位置（中心）
	                size,               // 半径
	                false,                       // 按比例缩放
	                CycleMethod.NO_CYCLE,       // 循环方式
	                new Stop(0, Color.hsb(mixedHue, saturation, 1, 1)),
	                new Stop(0.7, Color.hsb(mixedHue, saturation, brightness, 0.9)),      
	                new Stop(1, Color.color(0,0,0,0.1)) 
	        );
            gc.setFill(color);
//            
            // 绘制粒子
            gc.fillOval(x - size/2, y - size/2, size, size);
            
//            // 绘制粒子轨迹
//            if (random.nextDouble() < 0.3) {
//                double trailSize = size * 0.6;
//                gc.setFill(Color.hsb(mixedHue, saturation , brightness * 0.9, 1 ));
//                gc.fillOval(x - vx - trailSize/2, y - vy - trailSize/2, trailSize, trailSize);
//            }
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
                
//                // 碰撞时交换部分颜色
//                double hueDiff = (hue - other.hue) * 0.1;
//                hue -= hueDiff;
//                other.hue += hueDiff;
            }
        }
    }

 

    private void update() {
//        globalHue = (globalHue + 0.2) % 360;
        
        for (Particle p : particles) {
            p.update();
        }

        // 碰撞检测（简化版）
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            for (int j = i + 1; j < Math.min(i + 10, particles.size()); j++) {
                Particle p2 = particles.get(j);
                p1.checkCollision(p2);
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        drawConnections();
        
        for (Particle p : particles) {
            p.draw();
        }
//        drawInfo();
    }

    private void drawConnections() {
        for (int i = 0; i < particles.size(); i++) {
            Particle p1 = particles.get(i);
            for (int j = i + 1; j < particles.size(); j++) {
                Particle p2 = particles.get(j);
                
                double dx = p1.x - p2.x;
                double dy = p1.y - p2.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < CONNECTION_DISTANCE&&((Math.abs(p1.listpos-p2.listpos)==1||((Math.abs(p1.listpos-p2.listpos)==FIXED_POINTS.size()-1)&&canc==1)))) {
//                    double lineOpacity = (1 - distance / CONNECTION_DISTANCE) ;
                    gc.setStroke(Color.rgb(0, 0, (int) (255*p1.listpos/FIXED_POINTS.size()*0.7), 1));
                    gc.setLineWidth(0.5*psize);
                    gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

//    private void drawInfo() {
//        gc.setFill(Color.WHITE);
//        gc.fillText("Fixed-Point Particle System", 20, 20);
//        gc.fillText("Particles: " + NUM_PARTICLES, 20, 40);
//        
//        double wave = Math.sin(time * 3) * 5;
//        gc.setFill(Color.hsb((globalHue + 180) % 360, 0.8, 0.9));
//        gc.fillText("CONNECTED MOTION", WIDTH/2 - 100, 30 + wave);
//    }

    // 可选：动态设置生成点的方法
//    public void setFixedPoints(List<Point2D> points) {
//        FIXED_POINTS.clear();
//        FIXED_POINTS.addAll(points);
//    }
    public xuanwuParticles(List<Point2D> points,int num,double scale,int w,int h,double maxspeed,double minspeed,double distance,int cancircle) {
        super((int) (w*scale), (int) (h*scale));
        psize=scale;
        FIXED_POINTS=points;
        NUM_PARTICLES=points.size()*num;
        WIDTH = (int) (w*scale);
        HEIGHT = (int) (h*scale);
       	MAX_SPEED = maxspeed*scale;
        MIN_SPEED = minspeed*scale;
        CONNECTION_DISTANCE = distance*scale;
        canc=cancircle;
        gc = getGraphicsContext2D();

        // 初始化粒子
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Particle());
        }
        setupMouseHandlers();
        // 动画循环
        startTime = System.nanoTime();
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
                time += 0.016;
            }
        };
        animationTimer.start();
    }
    // 优化后的事件处理（解耦事件监听）
    private final EventHandler<MouseEvent> mouseHandler = e -> {
        mouseX = e.getX();
        mouseY = e.getY();
    };
 
    private final EventHandler<MouseEvent> mouseExitHandler = e -> {
        mouseX = -1;
        mouseY = -1;
    };
 
    private void setupMouseHandlers() {
        addEventHandler(MouseEvent.MOUSE_MOVED, mouseHandler);
        addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitHandler);
    }
 // 新增销毁方法
    private AnimationTimer animationTimer;
    
    public void destroy() {
        // 停止动画循环
        if (animationTimer != null) {
            animationTimer.stop();
        }
        // 清除所有粒子
        particles.clear();
        // 清除画布内容
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc=null;
        canvas=null;
        // 移除事件监听器（防止内存泄漏）
        removeEventHandler(MouseEvent.MOUSE_MOVED, mouseHandler);
        removeEventHandler(MouseEvent.MOUSE_EXITED, mouseExitHandler);
    }
}