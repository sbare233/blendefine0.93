package blendefine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import tool.NumberToChinese;

public class shixuan extends ImageView{
	int i,x,y;
	String t;
	int aimx=0,aimy=-2,aimr=0;
	Label lbx,lby,lbr;
	shixuan(Image img){
		super(img);
	}
	
	public shixuan(Image img,int i, int x, int y, String t, int aimx, int aimy, int aimr, Label lbx, Label lby, Label lbr) {
		super(img);
		this.i = i;
		this.x = x;
		this.y = y;
		this.t = t;
		this.aimx = aimx;
		this.aimy = aimy;
		this.aimr = aimr;
		this.lbx = lbx;
		this.lby = lby;
		this.lbr = lbr;
	}

	shixuan copy(guanka gk){
		shixuan xx=new shixuan(this.getImage(),i,x,y,t,aimx,aimy,aimr,lbx,lby,lbr); 
		xx.setOnMouseEntered(e1->{
			gk.fushil.setText(gk.shixuanname[xx.i]);
			xx.setEffect(gk.bloom);
		});
		xx.setOnMouseExited(e1->{
			xx.setEffect(null);
		});
		xx.setOnMouseClicked(e1->{
			if(e1.getButton()==MouseButton.PRIMARY&&!gk.deletef) {
				gk.handlepane.getChildren().forEach(e2->{
					e2=null;
				});
				gk.handlepane.getChildren().clear();
				gk.nowplayshixupos=xx.x;
				gk.shixubo.setTranslateX(((gk.nowplayshixupos-gk.nowlookshixu)*70+442)*gk.suofang-gk.screenwidth/2);
				gk.nowboindex=(int) (((gk.shixubo.getTranslateX()+ gk.screenwidth / 2)/gk.suofang-400)/70);
				gk.shixulookl.setText(NumberToChinese.toChinese(gk.nowboindex+gk.nowlookshixu));
				gk.readshixu();
				try {
					grid g=gk.machines.get(xx.y);
					try {
						if(xx.t.equals("围")) {
							gk.makelongzhuahandle(g);
						}
					}catch(Exception e2) {}
					Bloom b=new Bloom();
					g.g.setEffect(b);
					Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.8),new KeyValue(b.thresholdProperty(),1)));
					t.setOnFinished(e2->{
						g.g.setEffect(null);
					});
					t.play();
				}catch(Exception e2) {}
			}
			else {
				gk.shixuuses.remove(xx.x+","+xx.y,this);
				gk.refreshshixupane();
			}
		});
		return xx;
	}
}
