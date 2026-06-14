package blendefine;

import java.util.HashMap;
import java.util.HashSet;

import fx.IceshootEffect;
import fx.ParticleSystem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.Bloom;
import javafx.util.Duration;

public class guanzhu extends grid{
	HashSet<Point2D> gs;
	HashMap<String,Integer> store;//存储的是宝石类型和数量
	HashSet<String> canguan=new HashSet<String>();//存储的是宝石类型和数量
	HashSet<String> guanfxs=new HashSet<String>();//存储的是宝石类型和数量
	HashSet<IceshootEffect> fxs=new HashSet<IceshootEffect>();
	Timeline gze;
	ParticleSystem ps;
	boolean finishf=false;
	int ctx,cty,num=0;
	guanzhu(int x, int y,int ctx,int cty,Group g,String name,String key, HashSet<Point2D> gs,guanka gk) {
		super(x, y,g);
		store=new HashMap<String,Integer>();
		this.name=name;
		this.key=key;
		this.gk=gk;
		this.gs=gs;
		this.ctx=ctx;
		this.cty=cty;
	}
	guanzhu(int x, int y,String name,String key, HashMap<String,Integer> store,HashSet<IceshootEffect> fxs,ParticleSystem ps,boolean finishf,guanka gk) {
		super(x, y);
		this.name=name;
		this.key=key;
		this.gk=gk;
		this.store=new HashMap<String,Integer>();
		store.keySet().forEach(e->{
			this.store.put(e, store.get(e));
		});
	}
	void remove() {
		gk.udpane.getChildren().remove(g);
		gs.iterator().forEachRemaining(e->{
			int y0=(int)e.getY()+y,x0=(int)e.getX()+x;
			gk.gridv[y0][x0].setOpacity(1);
			gk.db0[y0][x0]=null;
		});
		
		fxs.forEach(e->{
			try {
			e.destroy();
			e=null;
			}catch(Exception e1) {}
		});
		try {
		ps.destroy();
		ps=null;
		}catch(Exception e1) {}
	}
	public guanzhu clone(){
		HashMap<String,Integer> store1=new HashMap<String,Integer>();
		store.keySet().forEach(e->{
			store1.put(e, store.get(e));
		});
		HashSet<IceshootEffect> fxs1=new HashSet<IceshootEffect>();
		fxs.forEach(e->{
			fxs1.add(e);
		});
		return new guanzhu(x,y,name,key, store1,fxs1, ps, finishf, gk);
	}
	public void baoshiguanzhu(String s1) {
		if(canguan.contains(s1))return;
		else {
			canguan.add(s1);
			if (store.containsKey(s1) && store.get(s1) == 0) {
				store.put(s1, 1);
				num++;
//				System.out.println(num);
				Bloom b=new Bloom();
				g.setEffect(b);
				Timeline t=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(b.thresholdProperty(),0.7)),
						new KeyFrame(Duration.seconds(1),new KeyValue(b.thresholdProperty(),1)));
				t.setOnFinished(e->{
					g.setEffect(null);
				});
				t.play();
//				guanzhueffect(s1);
			}
			if (num == store.size()&&!finishf) {
//				System.out.println(finishf);
				Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.5),e->{sealeffect();}));
				t1.play();
			}	
		}
	}
	public void guanzhueffect() {
		gze=new Timeline(new KeyFrame(Duration.seconds(0.1),e->{
			if(this.name.equals("九龙灌")) {
				store.keySet().forEach(e1->{
					if(e1.contains("囚牛")&&store.get(e1)==1&&!guanfxs.contains("囚牛")) {
						guanfxs.add("囚牛");
						IceshootEffect ic=new IceshootEffect(1800,1300,510,1160,0.25,35);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("睚眦")&&store.get(e1)==1&&!guanfxs.contains("睚眦")) {
						guanfxs.add("睚眦");
						IceshootEffect ic=new IceshootEffect(1800,1300,510,935,0.25,-1);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("嘲风")&&store.get(e1)==1&&!guanfxs.contains("嘲风")) {
						guanfxs.add("嘲风");
						IceshootEffect ic=new IceshootEffect(1800,1300,600,778,0.292,210);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("蒲牢")&&store.get(e1)==1&&!guanfxs.contains("蒲牢")) {
						guanfxs.add("蒲牢");
						IceshootEffect ic=new IceshootEffect(1800,1300,580,520,0.417,190);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("狻猊")&&store.get(e1)==1&&!guanfxs.contains("狻猊")) {
						guanfxs.add("狻猊");
						IceshootEffect ic=new IceshootEffect(1800,1300,900,520,0.5,20);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("霸下")&&store.get(e1)==1&&!guanfxs.contains("霸下")) {
						guanfxs.add("霸下");
						IceshootEffect ic=new IceshootEffect(1800,1300,1240,520,0.583,150);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("狴犴")&&store.get(e1)==1&&!guanfxs.contains("狴犴")) {
						guanfxs.add("狴犴");
						IceshootEffect ic=new IceshootEffect(1800,1300,1200,780,0.702,50);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("负屃")&&store.get(e1)==1&&!guanfxs.contains("负屃")) {
						guanfxs.add("负屃");
						IceshootEffect ic=new IceshootEffect(1800,1300,1300,945,0.75,144);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
					else if(e1.contains("螭吻")&&store.get(e1)==1&&!guanfxs.contains("螭吻")) {
						guanfxs.add("螭吻");
						IceshootEffect ic=new IceshootEffect(1800,1300,1300,1160,0.75,197);
						g.getChildren().add(ic);
						ic.setEffect(new Bloom());
						fxs.add(ic);
					}
				});	
			}
		}));
		gze.setCycleCount(Timeline.INDEFINITE);
		gze.play();
	}
	public void sealeffect() {
		finishf=true;
		if(this.name.equals("九龙灌")) {
			ps=new ParticleSystem(1800,1800,0,0,6000, 10);
			ps.setEffect(gk.gausb);
			ps.setTranslateX(g.getTranslateX());
			ps.setTranslateY(g.getTranslateY());
			gk.fxpane.getChildren().add(ps);
		}
	}
}
