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

public class IceshootEffect extends Canvas {
    int WIDTH = 0;
    int HEIGHT = 0;
    private final int MAX_PARTICLES = 500;
    private final double SPAWN_RATE = 0.3; // 每帧生成概率
    private final double BRANCH_ANGLE = Math.PI/4; // 分支角度
    private final double BRANCH_PROBABILITY = 0.15; // 分支概率
    private final double LIFE_DECAY = 0.97; // 生命周期衰减率
    public double iniangle=-1;
    public double inihue=-1;
    public double spawnx=-1,spawny=-1;
    private AnimationTimer animationTimer; // 新增动画定时器引用
    private boolean isDestroyed = false;   // 销毁状态标记
    private GraphicsContext gc;
    private Random random = new Random();
    private List<LightningParticle> particles = new ArrayList<>();

    class LightningParticle {
        double x, y;
        double vx, vy;
        double life;
        double angle;
        int depth; // 分支深度

        LightningParticle(double x, double y, double angle, int depth) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.depth = depth;
            reset();
        }

        void reset() {
            double speed = 8 - depth * 1.5; // 随深度递减速度
            vx = Math.cos(angle) * speed;
            vy = Math.sin(angle) * speed;
            life = 1;
        }

        void update() {
            x += vx;
            y += vy;
            life *= LIFE_DECAY;
            
            // 随机分支
            if (random.nextDouble() < BRANCH_PROBABILITY && depth < 5) {
                createBranch();
            }
        }

        void createBranch() {
            double branchAngle = angle + (random.nextDouble() - 0.5) * BRANCH_ANGLE;
            particles.add(new LightningParticle(x, y, branchAngle, depth + 1));
        }

        void draw() {
            double alpha = life * 0.8;
            double thickness = (4 - life) * 30;
            
            // 颜色随生命周期变化（蓝白渐变）
//            double hue = 180 + (1 - life) * 60;
            double hue;
            double stra=1;
            if(inihue==-1) {
            	hue=100;
            	stra=0;
            }
            else hue = inihue + (1 - life) * 60;
            Color color = Color.hsb(hue, stra, 1.0, alpha);
            
            gc.setStroke(color);
            gc.setLineWidth(thickness);
            gc.strokeLine(x, y, x - vx*2, y - vy*2); // 绘制运动轨迹
            
            // 末端闪光效果
//            if (random.nextDouble() < 0.1) {
//                double size = 15 + random.nextDouble() * 3;
//                gc.setFill(color.brighter());
//                gc.fillOval(x - size/2, y - size/2, size, size);
//            }
        }
    }

    public IceshootEffect() {
    	 super(1000, 1000);
         gc = getGraphicsContext2D();
         // 修改动画循环初始化（保存定时器引用）
         animationTimer = new AnimationTimer() {
             @Override
             public void handle(long now) {
                 if (isDestroyed) return; // 销毁后停止处理
                 update();
                 render();
             }
         };
         animationTimer.start();
    }
    
    /**角度范围从0-1*/
	public IceshootEffect(int w, int h, double x, double y, double angle, int hue) {
		super(w, h);
		WIDTH = w;
		HEIGHT = h;
		spawnx=x;
		spawny=y;
		iniangle = angle-0.25;
		inihue = hue;
		gc = getGraphicsContext2D();
		// 修改动画循环初始化（保存定时器引用）
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (isDestroyed)
					return; // 销毁后停止处理
				update();
				render();
			}
		};
		animationTimer.start();
	}
    private void update() {
        // 限制最大粒子数（使用独立操作避免并发修改）
        while (particles.size() > MAX_PARTICLES) {
            particles.remove(0);
        }

        // 创建临时集合用于安全遍历
        List<LightningParticle> tempParticles = new ArrayList<>(particles);
        
        // 随机生成新闪电（使用独立操作）
        if (random.nextDouble() < SPAWN_RATE) {
        	double angle;
        	if(iniangle==-1)
        		angle= random.nextDouble() * 2 * Math.PI;
        	else angle=iniangle* 2 * Math.PI;
            particles.add(new LightningParticle(spawnx,spawny, angle, 0));
        }

        // 更新所有粒子（遍历临时集合）
        for (LightningParticle p : tempParticles) {
            p.update();
        }

        // 清理失效粒子（使用迭代器安全删除）
        particles.removeIf(p -> p.life < 0.01);
    }
    private void render() {
        // 半透明背景擦除
//        gc.setFill(Color.rgb(0, 0, 0, 0.02));//没有以下两行就是静态结冰
//        gc.fillRect(0, 0, WIDTH, HEIGHT);//发射雪花
        gc.clearRect(0, 0, WIDTH, HEIGHT);//发射的小点
        // 创建临时集合用于安全绘制
        List<LightningParticle> tempParticles = new ArrayList<>(particles);
        
        // 绘制所有粒子（遍历临时集合）
        for (LightningParticle p : tempParticles) {
            p.draw();
        }
    }

    // 新增销毁方法
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
}