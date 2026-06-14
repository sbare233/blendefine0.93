package blendefine;

import fx.AshEffect;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class zhuque extends blend{
	AshEffect ash;
	zhuque(int x, int y, String name,String key, Group g,AshEffect ash) {
		super(x, y, name, key, g);
		this.ash=ash;
		this.openf=true;
	}
	zhuque(int x, int y, String name,String key,boolean openf,int machineindex){
		super(x, y, name, key);
		this.openf=openf;
		this.machineindex=machineindex;
	}
	public zhuque clone() {	
		zhuque z= new zhuque(x,y,name,key, openf,machineindex);
		z.gk=this.gk;
		return z;
	}
    public void remove() {
    	if(ash!=null) {
    		ash.destroy();
    		ash=null;
    	}
    	gk.machines.remove(machineindex);
    	gk.db0[y][x]=null;
    	gk.gridv[y][x].setOpacity(1);
    }
    public void move(int x1,int y1) {
    	gk.db0[y][x]=null;
    	y=y1;x=x1;
		g.setTranslateY(y*260);
		g.setTranslateX(x*150);
		gk.db[y][x]=this;
    }
    public void close() {
    	openf=false;
    	if(ash!=null) {
    		g.getChildren().remove(4);
    		ash.destroy();
    		ash=null;
    	}
    	ImageView f=(ImageView)(g.getChildren().get(1));
    	ImageView f1=(ImageView)(g.getChildren().get(3));
    	Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.3),new KeyValue(f.opacityProperty(),0),new KeyValue(f1.opacityProperty(),0.3)));
    	t.setOnFinished(e->{
    		g.getChildren().set(1,new ImageView());
    		f1.setOpacity(0.3);
    	});
    	
    }
    public void open() {
    	openf=true;
    	g.getChildren().set(1,new ImageView(gk.zhuquefire));
    	g.getChildren().get(3).setOpacity(0.1);
    }
}
