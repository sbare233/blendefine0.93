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

public class ParticleSystem extends Canvas {
    private static int N = 2000; // 粒子数量（根据性能调整）
    private static int WIDTH = 900;
    private static int HEIGHT = 900;
    int offsetx,offsety;
    int size=4;
    private double b = 1;
    private double time = 0;
    public AnimationTimer animationTimer; // 新增动画定时器引用
    private boolean isDestroyed = false;   // 销毁状态标记
    private List<Particle> particles = new ArrayList<>();
    
    private GraphicsContext gc;
    private Random random = new Random();
    // 新增粒子添加控制变量
    private int particlesToAdd = N;  // 剩余需要添加的粒子数
    private long lastAddTime = 0;
    private final int ADD_RATE = 100; // 每秒添加粒子数（可调）
    private int addedCount = 0;      // 已添加粒子计数器
    // 粒子类
    class Particle {
        double x, y, z;
        double lastX, lastY, lastZ;
        
        Particle(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.lastX = x;
            this.lastY = y;
            this.lastZ = z;
        }
        
        void update() {
            // 保存上一帧位置
            lastX = x;
            lastY = y;
            lastZ = z;
            
            // 应用12次迭代更新
            for (int i = 0; i < 12; i++) {
            	
            	//三角混沌线
                double dx = (Math.sin(y) - b * x) / 128.0;
                double dy = (Math.sin(z) - b * y) / 128.0;
                double dz = (Math.sin(x) - b * z) / 128.0;
            	
            	//Lorenz 无限符号洛伦兹混沌系统
//            	  double dx = 10 * (y - x) * 0.001;
//            	    double dy = (x * (28 - z) - y) * 0.001;
//            	    double dz = (x * y - 8/3 * z) * 0.001;
            	
            	//Rössler 系统 鹦鹉螺吕兹勒吸引子
//            	double dt = 0.002;
//            	double dx = (-y - z) * dt;
//            	double dy = (x + 0.2 * y) * dt;
//            	double dz = (0.2 + z * (x - 5.7)) * dt;
            	
            	//双摆
//            	double dt = 0.05;
//            	double dx = y * dt;
//            	double dy = (-Math.sin(x) + 3 * Math.sin(z) - 3 * y) * dt;
//            	double dz = x * dt;
            	
                x += dx;
                y += dy;
                z += dz;
            }
        }
    }

    public ParticleSystem(int w, int h,int offsetx,int offsety, int num,int size) {
        super(w, h);
        WIDTH = w;
        HEIGHT = h;
        this.offsetx=offsetx;
        this.offsety=offsety;
        N = num;
        this.size=size;
        particlesToAdd = N;
        gc = getGraphicsContext2D();
        // 初始化动画定时器
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isDestroyed) return;
 
                // 每秒添加粒子逻辑
                long currentTime = System.nanoTime();
                if (currentTime - lastAddTime >= 1_000_000_00) {
                    int addThisFrame = Math.min(ADD_RATE, particlesToAdd);
                    for (int i = 0; i < addThisFrame; i++) {
                        addNewParticle();
                    }
                    particlesToAdd -= addThisFrame;
                    lastAddTime = currentTime;
                }
 
                update();
                render();
                time += 0.016;
            }
        };
        animationTimer.start();
    }
    // 新增粒子添加方法
    private void addNewParticle() {
        if (addedCount >= N) return;
 
        // 保持原有初始化逻辑
        double x = random.nextDouble() * 2 - 1;
        double y = random.nextDouble() * 2 - 1;
        double z = 2.0 * addedCount / N - 1; // 保持原有z值分布
        
        particles.add(new Particle(x, y, z));
        addedCount++;
    }
    public void removeParticle() {
//        for(int i=0;i<100;i--) {
//        	try {
//        	particles.remove(i);
//        	}catch(Exception e) {}
//        }
    	int removeCount = Math.min(100, particles.size());
    	  particles.subList(particles.size() - removeCount, particles.size()).clear();
    }
    private void initializeParticles() {
        particles.clear();
        for (int i = 0; i < N; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;
            double z = 2.0 * i / N - 1;
            particles.add(new Particle(x, y, z));
        }
    }
    public void destroy() {
        isDestroyed = true;
        
        // 停止动画定时器
        if (animationTimer != null) {
            animationTimer.stop();
            animationTimer = null;
        }
 
        // 清理粒子系统
        particles.clear();
        
        // 清理图形上下文（可选）
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.TRANSPARENT);
    }
    private void update() {
        // 更新b值
//    	b=0.1;
//    	b=0.2;
//    	b=0.25;
//    	b=0.18;
//    	b = 0.15 + 0.06 * Math.sin(2 * Math.PI * time / 37.018);
//    	b = 0.15 + 0.08 * Math.sin(2 * Math.PI * time / 37.018);
//        b = 0.2 + 0.06 * Math.sin(2 * Math.PI * time / 37.018);
    	b = 0.11 + 0.04 * Math.sin(0.5 * time);
//    	b = 0.1 + 0.1 * random.nextDouble();//跳变
//    	b = 0.166;
        // 更新粒子位置
        for (Particle p : particles) {
            p.update();
        }
    }
    
    private void render() {
        // 半透明黑色背景（创建拖尾效果）
//        gc.setFill(Color.rgb(0, 0, 0, 0.15));
        gc.clearRect(0, 0, WIDTH, HEIGHT);
//        gc.fillRect(0, 0, WIDTH, HEIGHT);
    	gc.setFill(Color.rgb(0, 0, 0, 0));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        // 设置3D投影参数
        double cameraZ = -3.0;
        double fov = Math.PI / 3.0; // 视野
        double aspectRatio = (double) WIDTH / HEIGHT;
        double scale = 1.6 * b;
        
        // 应用旋转（绕X轴0.5弧度，绕Y轴随时间旋转）
        double rotationX = 0.5;
        double rotationY = 2 * Math.PI * 0.05 * time;
        double cosY = Math.cos(rotationY);
        double sinY = Math.sin(rotationY);
        double cosX = Math.cos(rotationX);
        double sinX = Math.sin(rotationX);
        
        // 绘制所有粒子轨迹
        for (Particle p : particles) {
            // 将当前位置和上一帧位置转换为屏幕坐标
            double[] screenPos = projectToScreen(p.x * scale, p.y * scale, p.z * scale, 
                                                cameraZ, fov, aspectRatio, cosX, sinX, cosY, sinY);
            double[] lastScreenPos = projectToScreen(p.lastX * scale, p.lastY * scale, p.lastZ * scale, 
                                                    cameraZ, fov, aspectRatio, cosX, sinX, cosY, sinY);
            
            // 计算颜色（基于粒子索引和深度）
            double hue = (double) particles.indexOf(p) / N * 360.0;
            double saturation = 0.7;
            double zFactor = Math.max(0, Math.min(1, (4.5 + p.z) / 2.5));
//            double brightness = Math.pow(zFactor, 1.0);
            
            // 设置线条颜色
            gc.setStroke(Color.hsb(hue, saturation, 1));
            gc.setLineWidth(size);
            // 绘制从上一帧位置到当前位置的线段
            gc.strokeLine(lastScreenPos[0]+offsetx, lastScreenPos[1]+offsety, screenPos[0]+offsetx, screenPos[1]+offsety);
        }
        
        // 绘制信息
//        drawInfo();
    }
    
    private double[] projectToScreen(double x, double y, double z, 
                                    double cameraZ, double fov, double aspectRatio,
                                    double cosX, double sinX, double cosY, double sinY) {
        // 应用旋转（先绕Y轴，再绕X轴）
        double x1 = x * cosY - z * sinY;
        double z1 = x * sinY + z * cosY;
        double y1 = y;
        
        double y2 = y1 * cosX - z1 * sinX;
        double z2 = y1 * sinX + z1 * cosX;
        
        // 应用透视投影
        double factor = fov / (cameraZ - z2);
        double screenX = WIDTH / 2 + x1 * factor * aspectRatio * WIDTH / 2;
        double screenY = HEIGHT / 2 + y2 * factor * HEIGHT / 2;
        
        return new double[]{screenX, screenY};
    }
}