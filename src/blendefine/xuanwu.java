package blendefine;

import java.util.ArrayList;
import java.util.List;

import fx.xuanwuParticles;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class xuanwu extends blend {
	xuanwu prev,next,first,last;
	xuanwuParticles star;
	public guanka gk;
	int listsize;
	List<Circle> cl;
	List<Node> added;
	xuanwu(int x, int y, String name,String key, Group g) {
		super(x, y, name, key, g);
		this.cl=new ArrayList<Circle>();
		this.added=new ArrayList<Node>();
	}
	public void remove() {
    	xuanwu now=((xuanwu)(gk.db0[y][x])).first;
    	gk.machines.remove(machineindex);
    	now.added.forEach(e->{
    		try {
    			gk.udpane.getChildren().remove(e);
    		}catch(Exception e1) {}
    	});
    	if(null!=now.star) {
    		gk.udpane.getChildren().remove(now.star);
    		now.star.destroy();
    		now.star=null;
    	}
    	for(int i=0;i<listsize;i++) {
    		gk.udpane.getChildren().remove(now.g);
        	now.g=null;
        	gk.db0[now.y][now.x]=null;
        	gk.gridv[now.y][now.x].setOpacity(1);
        	now=now.next;
    	}
    }
	public void move(int x1,int y1) {
		xuanwu now=((xuanwu)(gk.db0[y][x])).first;
		now.added.forEach(e->{
    		try {
    			e.setTranslateX(e.getTranslateX()+(x1-first.x)*150);
    			e.setTranslateY(e.getTranslateY()+(y1-first.y)*260);
    		}catch(Exception e1) {}
    	});
    	if(null!=now.star) {
    		now.star.setTranslateX(now.star.getTranslateX()+(x1-first.x)*150);
    		now.star.setTranslateY(now.star.getTranslateY()+(y1-first.y)*260);
    	}
    	for(int i=0;i<listsize;i++) {
        	now.g.setTranslateX(now.g.getTranslateX()+(x1-first.x)*150);
        	now.g.setTranslateY(now.g.getTranslateY()+(y1-first.y)*260);
        	gk.db0[now.y][now.x]=null;
        	gk.gridv[now.y][now.x].setOpacity(1);
        	if(!now.equals(first)) {
	        	now.x=now.x+x1-first.x;
	        	now.y=now.y+y1-first.y;
	        	gk.db0[now.y][now.x]=now;
	        	gk.gridv[now.y][now.x].setOpacity(1);
        	}
        	now=now.next;
    	}
    	first.x=x1;
    	first.y=y1;
    	gk.db0[first.y][first.x]=first;
    	gk.gridv[first.y][first.x].setOpacity(1);
	}
	public Group copyg() {
		Group g0=new Group();
		xuanwu now=((xuanwu)(gk.db0[y][x])).first;
		for(int i=0;i<listsize;i++) {
			Group g1=new Group();
			now.g.getChildren().iterator().forEachRemaining(e->{
				if(e instanceof ImageView) {
					ImageView iv=new ImageView(((ImageView) e).getImage());
					iv.setFitHeight(((ImageView) e).getFitHeight());
					iv.setFitWidth(((ImageView) e).getFitWidth());
					g1.getChildren().add(iv);
					iv.setTranslateX(e.getTranslateX());
					iv.setTranslateY(e.getTranslateY());
					iv.setScaleX(e.getScaleX());
					iv.setScaleY(e.getScaleY());
					iv.setRotate(e.getRotate());
					iv.setEffect(e.getEffect());
				}
				else {
					Group g2=new Group();
					((Group) e).getChildren().forEach(e1->{
						ImageView iv=new ImageView(((ImageView) e1).getImage());
						g2.getChildren().add(iv);
						iv.setTranslateX(e1.getTranslateX());
						iv.setTranslateY(e1.getTranslateY());
						iv.setScaleX(e1.getScaleX());
						iv.setScaleY(e1.getScaleY());
						iv.setRotate(e1.getRotate());
						iv.setEffect(e1.getEffect());
					});
					g1.getChildren().add(g2);
					g2.setTranslateX(e.getTranslateX());
					g2.setTranslateY(e.getTranslateY());
					g2.setScaleX(e.getScaleX());
					g2.setScaleY(e.getScaleY());
					g2.setRotate(e.getRotate());
					g2.setEffect(e.getEffect());
				}
			});
			g1.setTranslateX(now.g.getTranslateX());
			g1.setTranslateY(now.g.getTranslateY());
			g1.setScaleX(now.g.getScaleX());
			g1.setScaleY(now.g.getScaleY());
			g1.setRotate(now.g.getRotate());
			g1.setEffect(now.g.getEffect());
			g0.getChildren().add(g1);
			now=now.next;
		}
		return g0;
	}
}
