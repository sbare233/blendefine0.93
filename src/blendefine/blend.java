package blendefine;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class blend extends grid{
	int face;
	int nowpic;
	int machineindex;
	String cache;
	HashMap<Integer,String> store=new HashMap<Integer,String>();//存储的是方向和宝石类型

	blend(int x, int y,String name,String key,Group g) {
		super(x, y,g);
		this.name=name;
		this.key=key;
	}
	blend(int x, int y,String name,String key) {
		super(x, y);
		this.name=name;
		this.key=key;
	}
	blend(int x, int y,String name,String key,HashMap<Integer,String> store,boolean openf,int face,int nowpic,int machineindex) {
		super(x, y);
		this.openf=openf;
		this.key=key;
		store.keySet().forEach(e->{
			this.store.put(e, store.get(e));
		});
		this.face=face;
		this.nowpic=nowpic;
		this.machineindex=machineindex;
	}
	public void remove() {
		gk.machines.remove(machineindex);
		gk.hypane.getChildren().remove(g);
		if(name.equals("混元"))gk.db[y][x]=null;
		else if(name.equals("五脏")) {
			gk.db[y][x]=null;gk.db[y][x+1]=null;gk.db[y][x+2]=null;
			gk.db[y+1][x]=null;gk.db[y+1][x+1]=null;gk.db[y+1][x+2]=null;
		}
		else if(name.equals("六合")) {
			gk.db[y][x]=null;gk.db[y][x+1]=null;gk.db[y][x+2]=null;gk.db[y][x+3]=null;gk.db[y][x+4]=null;
			gk.db[y+1][x-1]=null;gk.db[y+1][x]=null;gk.db[y+1][x+1]=null;gk.db[y+1][x+2]=null;gk.db[y+1][x+3]=null;gk.db[y+1][x+4]=null;gk.db[y+1][x+5]=null;
			gk.db[y+2][x-1]=null;gk.db[y+2][x]=null;gk.db[y+2][x+1]=null;gk.db[y+2][x+2]=null;gk.db[y+2][x+3]=null;gk.db[y+2][x+4]=null;gk.db[y+2][x+5]=null;
			gk.db[y+3][x]=null;gk.db[y+3][x+1]=null;gk.db[y+3][x+2]=null;gk.db[y+3][x+3]=null;gk.db[y+3][x+4]=null;
		}
	}
	public void move(int x1,int y1) {
		if(name.equals("混元")) {
			gk.db[y][x]=null;
			y=y1;x=x1;
			g.setTranslateY(y*260);
			g.setTranslateX(x*150);
			gk.db[y][x]=this;
		}
		else if(name.equals("五脏")) {
			gk.db[y][x]=null;gk.db[y][x+1]=null;gk.db[y][x+2]=null;
			gk.db[y+1][x]=null;gk.db[y+1][x+1]=null;gk.db[y+1][x+2]=null;
			x=x1;y=y1;
			gk.db[y][x]=this;gk.db[y][x+1]=this;gk.db[y][x+2]=this;
			gk.db[y+1][x]=this;gk.db[y+1][x+1]=this;gk.db[y+1][x+2]=this;
			if(face==1) {
				g.setTranslateY(y*260-8);
				g.setTranslateX(x*150-15);
			}
			else if(face==2) {
				g.setTranslateY(y*260-15);
				g.setTranslateX(x*150-10);
			}
			else if(face==3) {
				g.setTranslateY(y*260-24);
				g.setTranslateX(x*150);
			}
			else if(face==5) {
				g.setTranslateY(y*260-3);
				g.setTranslateX(x*150+5);
			}
			else if(face>2&&face<5){
				g.setTranslateY(y*260-20);
				g.setTranslateX(x*150);
			}
			else {
				g.setTranslateY(y*260);
				g.setTranslateX(x*150);
			}
		}
		else if(name.equals("六合")) {
			gk.db[y][x]=null;gk.db[y][x+1]=null;gk.db[y][x+2]=null;gk.db[y][x+3]=null;gk.db[y][x+4]=null;
			gk.db[y+1][x-1]=null;gk.db[y+1][x]=null;gk.db[y+1][x+1]=null;gk.db[y+1][x+2]=null;gk.db[y+1][x+3]=null;gk.db[y+1][x+4]=null;gk.db[y+1][x+5]=null;
			gk.db[y+2][x-1]=null;gk.db[y+2][x]=null;gk.db[y+2][x+1]=null;gk.db[y+2][x+2]=null;gk.db[y+2][x+3]=null;gk.db[y+2][x+4]=null;gk.db[y+2][x+5]=null;
			gk.db[y+3][x]=null;gk.db[y+3][x+1]=null;gk.db[y+3][x+2]=null;gk.db[y+3][x+3]=null;gk.db[y+3][x+4]=null;
			x=x1;y=y1;
			g.setTranslateX(x*150-150);
			g.setTranslateY(y*260);
			gk.db[y][x]=this;gk.db[y][x+1]=this;gk.db[y][x+2]=this;gk.db[y][x+3]=this;gk.db[y][x+4]=this;
			gk.db[y+1][x-1]=this;gk.db[y+1][x]=this;gk.db[y+1][x+1]=this;gk.db[y+1][x+2]=this;gk.db[y+1][x+3]=this;gk.db[y+1][x+4]=this;gk.db[y+1][x+5]=this;
			gk.db[y+2][x-1]=this;gk.db[y+2][x]=this;gk.db[y+2][x+1]=this;gk.db[y+2][x+2]=this;gk.db[y+2][x+3]=this;gk.db[y+2][x+4]=this;gk.db[y+2][x+5]=this;
			gk.db[y+3][x]=this;gk.db[y+3][x+1]=this;gk.db[y+3][x+2]=this;gk.db[y+3][x+3]=this;gk.db[y+3][x+4]=this;
			
		}
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
				try {
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
				}catch(Exception e1) {}
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
	public blend clone(){
		HashMap<Integer,String> store1=new HashMap<Integer,String>();
		store.keySet().forEach(e->{
			store1.put(e,store.get(e));
		});
		blend bl= new blend(x,y,name,key, store1,openf, face, nowpic, machineindex);
		bl.gk=this.gk;
		return bl;
	}
}
