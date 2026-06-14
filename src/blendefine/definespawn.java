package blendefine;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class definespawn extends grid{
	int zang=(int)(Math.random()*10);
	definespawn(int x,int y,Group g,String name,String key){
		super(x,y,g,name);
		this.key=key;
	}
	public void remove() {
		gk.dfbpane.getChildren().remove(g);
		gk.db0[y][x]=null;
	}
	public void move(int x1,int y1) {
		if(null!=gk.db0[y][x])
			gk.db0[y][x]=null;
		x=x1;y=y1;
		g.setTranslateX(150*x-23);
		g.setTranslateY(260*y-((x+y)%2==1?0:77)-9);
		gk.db0[y][x]=this;
	}
	public Group copyg() {
		Group g1=new Group();
		g.getChildren().iterator().forEachRemaining(e->{
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
		g1.setTranslateX(g.getTranslateX());
		g1.setTranslateY(g.getTranslateY());
		g1.setScaleX(g.getScaleX());
		g1.setScaleY(g.getScaleY());
		g1.setRotate(g.getRotate());
		g1.setEffect(g.getEffect());
		return g1;
	}
}
