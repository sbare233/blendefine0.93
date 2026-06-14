package blendefine;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class longzhua extends blend{
	Group ng,g0;
	define d;
	ParallelTransition ani;
	guanka gk;
	long nowtime;
	int movef=0;
	double rotater=0;
	double boundsx1,boundsx2,boundsy1,boundsy2,boundsl,boundst;
	Timeline t;
	Boolean stuckf=false;
	int grabx,graby,grabr,nowpic1=0,nowpic2=0;
	longzhua(int x, int y,String name,String key,Group g,Group g0) {
		super(x, y,name, key, g);
		grabx=x;
		graby=y-2;
		grabr=(x+y)%2==0?0:3;
		this.g=g;
		this.g0=g0;
	}
	longzhua(int x, int y,int grabx,int graby,String name,String key,Group g,Group g0) {
		super(x, y,name, key, g);
		this.grabx=grabx;
		this.graby=graby;
		this.g=g;
		this.g0=g0;
	}
	public longzhua(int x, int y, String name,String key,Group g0,Group g,Group ng,define d,guanka gk,
			long nowtime, int movef, double rotater, int grabx, int graby, int grabr,
			int nowpic1, int nowpic2,int machineindex,boolean stuckf) {
		super(x, y,name,key, g);
		this.key=key;
		this.g0=g0;
		this.ng=ng;
		this.d=d;
		this.gk = gk;
		this.nowtime = nowtime;
		this.movef = movef;
		this.rotater = rotater;
		this.grabx = grabx;
		this.graby = graby;
		this.grabr = grabr;
		this.nowpic1 = nowpic1;
		this.nowpic2 = nowpic2;
		this.machineindex=machineindex;
		this.stuckf=stuckf;
	}
	public longzhua clone() {
		Group g1=new Group();
		Group g0=new Group();
//		try {
//		d.baoshiconnect();
//		}catch(Exception e) {}
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
		
		this.g0.getChildren().iterator().forEachRemaining(e->{
			if(e instanceof ImageView) {
				ImageView iv=new ImageView(((ImageView) e).getImage());
				iv.setFitHeight(((ImageView) e).getFitHeight());
				iv.setFitWidth(((ImageView) e).getFitWidth());
				g0.getChildren().add(iv);
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
				g0.getChildren().add(g2);
				g2.setTranslateX(e.getTranslateX());
				g2.setTranslateY(e.getTranslateY());
				g2.setScaleX(e.getScaleX());
				g2.setScaleY(e.getScaleY());
				g2.setRotate(e.getRotate());
				g2.setEffect(e.getEffect());
			}
		});
		g0.setTranslateX(this.g0.getTranslateX());
		g0.setTranslateY(this.g0.getTranslateY());
		g0.setScaleX(this.g0.getScaleX());
		g0.setScaleY(this.g0.getScaleY());
		g0.setRotate(this.g0.getRotate());
		g0.setEffect(this.g0.getEffect());
		Group ng1=new Group();
		if(null!=ng&&ng.getChildren().size()>0) {
	    	int l=ng.getChildren().size();
//	    	define[] ds=d.parentGroup.gems.toArray(new define[l]); 
	    	for(int i=0;i<l;i++) {
	    		try {
	    		Group dsg=new Group();
//	    		Group ds1=ds[i].g;
	    		Group ds1=(Group) ng.getChildren().get(i);  
				ds1.getChildren().forEach(e1 -> {
					ImageView iv = new ImageView(((ImageView) e1).getImage());
					dsg.getChildren().add(iv);
					iv.setTranslateX(e1.getTranslateX());
					iv.setTranslateY(e1.getTranslateY());
					iv.setScaleX(e1.getScaleX());
					iv.setScaleY(e1.getScaleY());
					iv.setRotate(e1.getRotate());
					iv.setEffect(e1.getEffect());
				});
				dsg.setTranslateX(ds1.getTranslateX());
	    		dsg.setTranslateY(ds1.getTranslateY());
				dsg.setScaleX(ds1.getScaleX());
				dsg.setScaleY(ds1.getScaleY());
				dsg.setRotate(ds1.getRotate());
				dsg.setEffect(ds1.getEffect());
	    		ng1.getChildren().add(dsg);
	    		}catch(Exception e1) {
	    			Line l1=(Line)ng.getChildren().get(i);
	    			Line l2=new Line(l1.getStartX(),l1.getStartY(),l1.getEndX(),l1.getEndY());
	    			l2.setEffect(l1.getEffect());
	    			ng1.getChildren().add(l2);
	    		}
	    	}
			
	    	ng1.setTranslateX(ng.getTranslateX());
	    	ng1.setTranslateY(ng.getTranslateY());
	    	ng1.setRotate(ng.getRotate());
	    	try {
//	    		ng1.setRotate(grabr*60);
	    	}catch(Exception e) {
//	    		ng1.setRotate(ng.getRotate());
	    	}
		}
//		define d1=null;
//		try {
//			d1=d.clone();
//		}catch(Exception e) {}
		return new longzhua(x,  y,  name, key,g0, g1, ng1,d, gk,
				 nowtime,  movef,  rotater, grabx,  graby,  grabr,
				 nowpic1,  nowpic2,machineindex,stuckf);
	}
	 /**
	    * @description: $desc$
	    * @author: $author$
	    * @date: $date$ $time$
	    * @param: $param$
	    * @return: $return$
	    */

	    /**
		    * @description: dir0到5
		    */
		public void movelongzhua(int mx,int my){
//			System.out.println(mx+" "+my+" "+key);
			double ofx=150*(mx+x),ofy=0;
			if((mx+my+x+y)%2==0) ofy=260*(y+my);
		    else ofy=260*(y+my)+260/3;
			ImageView zhuazi = (ImageView) g.getChildren().get(2);
			ImageView zhuabi = (ImageView) g.getChildren().get(3);
			ImageView niu = (ImageView) g.getChildren().get(5);	
			g.translateYProperty().addListener(obs -> updateClawPosition(this,ng,zhuabi,zhuazi,niu));
			t=new Timeline(new KeyFrame(Duration.seconds(0.25/gk.playrate),e->{
				gk.db[y][x]=null;
				x+=mx;y+=my;
				gk.db[y][x]=this;
			}),
					new KeyFrame(Duration.seconds(1/gk.playrate),new KeyValue(g.translateXProperty(),ofx)
					,new KeyValue(g.translateYProperty(),ofy)
					,new KeyValue(g0.translateXProperty(),ofx)
					,new KeyValue(g0.translateYProperty(),ofy)));
			t.setOnFinished(e->{
				grabx+=mx;graby+=my;
				g.setTranslateX(150*x);
			    if((x+y)%2==0) g.setTranslateY(260*y);
			    else g.setTranslateY(260*y+260/3);
			   
			});
			t.play();
		}

	   
	    
	    public void updateClawPosition(longzhua lz,Group baoshi,ImageView zhuabi,ImageView zhuazi,ImageView niu) {
	        // 获取爪臂当前状态
		        double angle = zhuabi.getRotate();
		        double angle1 = zhuazi.getRotate();

		        double scale = zhuabi.getScaleY();
		        // 计算爪臂顶端坐标（基于旋转中心）
		        double ofx=0,ofy=0;
//		        double ofx1=0,ofy1=0;
		        Point2D armEnd = calculateArmEnd(angle, scale);
		        
		        //龙爪的碰撞实际上相当难做，存在多层级问题，已放弃
//龙爪臂碰撞体生成		        
//	        	List<Segment> lzshape = new ArrayList<>();
//	        	double lx0=lz.x*150+150,ly0=lz.y*260+((lz.x+lz.y)%2==0?86.667:173.334);
//	        	double sin0=Math.sin(Math.toRadians(angle)),cos0=Math.cos(Math.toRadians(angle));
//	        	double ofx0=30*cos0,ofy0=30*sin0,ofx2=40*cos0+130*sin0,ofx3=-40*cos0+130*sin0,ofy2=40*sin0-130*cos0,ofy3=-40*sin0-130*cos0;
//	        	Point lp0=new Point(armEnd.getX()+lx0+ofx0,armEnd.getY()+ly0+ofy0),
//	        		lp1=new Point(lx0+ofx2,ly0+ofy2),
//	        		lp2=new Point(lx0+ofx3,ly0+ofy3),
//	        		lp3=new Point(armEnd.getX()+lx0-ofx0,armEnd.getY()+ly0-ofy0);	
//	        	lzshape.add(new Segment(lp0,lp1));
//	        	lzshape.add(new Segment(lp1,lp2));
//	        	lzshape.add(new Segment(lp2,lp3));
//	        	lzshape.add(new Segment(lp3,lp1));
//	        	try {
//	        	lzpane.getChildren().remove(checkcoli);
//	        	}catch(Exception e1) {}
//	        	checkcoli=new Polygon(armEnd.getX()+lx0+ofx0,armEnd.getY()+ly0+ofy0,lx0+ofx2,ly0+ofy2,lx0+ofx3,ly0+ofy3,
//	        			armEnd.getX()+lx0-ofx0,armEnd.getY()+ly0-ofy0);
//	        	checkcoli.setFill(Color.color(0, 1, 1,0.4));
//	        	lzpane.getChildren().add(checkcoli);
	        	
	        	//检测碰撞
//	        	for(int i=Math.max(0,lz.x-6);i<Math.min(81,lz.x+6);i++) {
//					for(int j=Math.max(0,lz.y-6);j<Math.min(81,lz.y+6);j++) {
//						try {
//							if(guanka.doShapesIntersect(lzshape,gk.colis[j][i])) {
//								if(!gk.colics.containsKey(i+","+j)) {
//									Circle cx0=new Circle(150,(i+j)%2==0?87:174,90,Color.color(1,0,0,0.4));
//									cx0.setTranslateX(i*150);	
//									cx0.setTranslateY(j*260);
//									gk.lzpane.getChildren().add(cx0);
//									gk.colics.put(i+","+j, cx0);
//								}
//							}
//							else {
//								if(gk.colics.containsKey(i+","+j)) {
//									gk.lzpane.getChildren().remove(gk.colics.get(i+","+j));
//									gk.colics.remove(i+","+j);
//								}
//							}
//						}catch(Exception e1) {}
//					}
//				}
	        	
		        try {
		        if(baoshi.getBoundsInParent().getWidth()!=-1) {
			        double angle2 = baoshi.getRotate();
		    		double s=Math.sin(Math.toRadians(angle2)),c=Math.cos(Math.toRadians(angle2));
//		    		double size=lz.d.parentGroup.gems.size();
		    		double mx=lz.d.parentGroup.mofx,my=lz.d.parentGroup.mofy;
		    		double x1=boundsx2,y1=boundsy2;

		    		double x=x1+mx,y=y1+my;
		    		ofx=x*c-y*s;
		    		ofy=x*s+y*c;

		    		baoshi.setTranslateX(lz.g.getTranslateX()+armEnd.getX()-23+ofx-boundsl-x1+173);
		    		baoshi.setTranslateY(lz.g.getTranslateY()+armEnd.getY()-87.5+ofy-boundst-y1+173);			
		    		
		    		
		    		
//					int gs=lz.d.parentGroup.gems.size();
//					define[] ds=lz.d.parentGroup.gems.toArray(new define[gs]);
//					double upofy=(lz.d.upf)%2==0?86.667:173.334;
//					double upofy1=(lz.x+lz.y)%2==0?0:86.667;
//					double ax=armEnd.getX()+lz.x*150+150,ay=armEnd.getY()+lz.y*260+87.5;
//					int wi=(int) ((Math.max(baoshi.getBoundsInLocal().getWidth(),baoshi.getBoundsInLocal().getHeight())*2)/300);
//					int mwx=(int) ((lz.g.getTranslateX()+armEnd.getX()+ofx+ofx1)/150)+1,
//							mwy=(int) ((lz.g.getTranslateY()+armEnd.getY()+ofy+ofy1)/260)+1;
					
//宝石碰撞体生成（存在连接顺序错误）
//					List<Segment> baoshishape = new ArrayList<>();
//					try {
//			        	lzpane.getChildren().remove(checkcoli0);
//			        	}catch(Exception e1) {}
//					gk.checkcoli0=new Polygon();
//					for(int i=0;i<gs;i++) {
//						int soffx=ds[i].x-lz.d.x;
//						int soffy=ds[i].y-lz.d.y;
//						double sofx0=soffx*150-150,sofx1=soffx*150,sofx2=soffx*150+150;
//						double sofy0=(soffy+ds[i].upf)*260-upofy,sofy1=(soffy-ds[i].upf+1)*260-upofy,sofy2=sofy0;
//						double sofx01=sofx0*c-sofy0*s+ax,sofx11=sofx1*c-sofy1*s+ax,sofx21=sofx2*c-sofy2*s+ax;
//						double sofy01=sofx0*s+sofy0*c+ay+upofy1,sofy11=sofx1*s+sofy1*c+ay+upofy1,sofy21=sofx2*s+sofy2*c+ay+upofy1;
//						Segment s0=new Segment(new Point(sofx01,sofy01),new Point(sofx11,sofy11));
//						Segment s1=new Segment(new Point(sofx21,sofy21),new Point(sofx11,sofy11));
//						Segment s2=new Segment(new Point(sofx01,sofy01),new Point(sofx21,sofy21));
//						baoshishape.add(s0);baoshishape.add(s1);baoshishape.add(s2);	
//						checkcoli0.getPoints().addAll(sofx01,sofy01,sofx11,sofy11,sofx21,sofy21);
//						Circle c0=new Circle(0,0,10,Color.RED);
//						c0.setTranslateX(sofx01);	
//						c0.setTranslateY(sofy01);
//						lzpane.getChildren().add(c0);
//						Circle c1=new Circle(0,0,10,Color.BLUE);
//						c1.setTranslateX(sofx11);	
//						c1.setTranslateY(sofy11);
//						lzpane.getChildren().add(c1);
//						Circle c2=new Circle(0,0,10,Color.GREEN);
//						c2.setTranslateX(sofx21);	
//						c2.setTranslateY(sofy21);
//						lzpane.getChildren().add(c2);
//					}
//		        	checkcoli0.setFill(Color.color(0, 1, 1,0.4));
//		        	lzpane.getChildren().add(checkcoli0);
					
					//检测碰撞
//					for(int i=Math.max(0,mwx-wi);i<Math.min(81,mwx+wi);i++) {
//						for(int j=Math.max(0,mwy-wi);j<Math.min(81,mwy+wi);j++) {
//							try {
//								if(guanka.doShapesIntersect(baoshishape,gk.colis[j][i])) {
//									if(!gk.colics.containsKey(i+","+j)) {
//										Circle cx0=new Circle(150,(i+j)%2==0?87:174,86,Color.color(1,0,0,0.4));
//										cx0.setTranslateX(i*150);	
//										cx0.setTranslateY(j*260);
//										gk.lzpane.getChildren().add(cx0);
//										gk.colics.put(i+","+j, cx0);
//									}
//								}
//								else {
//									if(gk.colics.containsKey(i+","+j)) {
//										gk.lzpane.getChildren().remove(gk.colics.get(i+","+j));
//										gk.colics.remove(i+","+j);
//									}
//								}
//							}catch(Exception e1) {}
//						}
//					}
		        }
		        }catch(Exception e2) {}
//				long lasttime=lz.nowtime,nowtime=System.currentTimeMillis();
//		    	if(nowtime-lasttime>16) {
//		    		lz.nowtime=nowtime;
//		    		try {
		    		 // 更新爪子位置（考虑爪臂自身坐标系偏移）

			        zhuazi.setTranslateX(armEnd.getX() - 23);  // 补偿爪子自身宽度
			        zhuazi.setTranslateY(armEnd.getY() - 87);  // 补偿爪子自身高度
					niu.setTranslateX(97+armEnd.getX());
					niu.setTranslateY(45+armEnd.getY());
			
//				}catch(Exception e1) {}
//		    	}
				
				//暂时不用的老的爪臂方法
				int np1=(int) Math.floor(((angle+360)%360)/30),np2=(int) Math.floor(((angle1+360)%360)/30);
				try {
//					if(lz.nowpic1!=np1) {
//						lz.nowpic1=np1;
//						zhuabi.setImage(gk.zhuabis[np1]);
//					}
					if(lz.nowpic2!=np2) {
						lz.nowpic2=np2;
						 if(lz.d!=null) {
							 zhuazi.setImage(gk.zhuazigs[np2]);
						 }
						 else {
							 zhuazi.setImage(gk.zhuazis[np2]);
						 }
					}
				}catch(Exception e) {}
	    }
	    public void remove() {
	    	ng=null;
	    	d=null;
	    	ani=null;
	    	gk.lzpane.getChildren().remove(g);
	    	gk.lzpane.getChildren().remove(ng);
	    	gk.dfbpane.getChildren().remove(g0);
	    	gk.machines.remove(machineindex);
	    	gk.db[y][x]=null;
	    	gk=null;
	    	if(null!=t) {
	    		t.stop();
	    		t=null;
	    	}
	    }
	    public void remove1() {
	    	gk.lzpane.getChildren().remove(g);
	    	gk.lzpane.getChildren().remove(ng);
	    	gk.dfbpane.getChildren().remove(g0);
	    }
	    public void move(int x1,int y1) {
	    	gk.db[y][x]=null;
	    	grabx=grabx-x+x1;
			graby=graby-y+y1;
	    	x=x1;y=y1;
	    	gk.db[y][x]=this;
	    	 g0.setTranslateX(150*x);
		        if((x+y)%2==0) {
		        	g0.setTranslateY(260*y);
		        }
		        else {
		        	g0.setTranslateY(260*y+260/3);
		        }
		        g.setTranslateX(150*x);
		        if((x+y)%2==0) {
		        	g.setTranslateY(260*y);
		        }
		        else {
		        	g.setTranslateY(260*y+260/3);
		        }
	    }
	    public Group copyg() {
	    	ImageView baseImage = new ImageView(gk.longzhuax1i);
	    	baseImage.setFitHeight(173);
	    	baseImage.setFitWidth(173);
			baseImage.setTranslateX(63);
			ImageView topImage = new ImageView(gk.longzhuax2i);
			topImage.setFitHeight(146);
			topImage.setFitWidth(166);
			topImage.setTranslateX(68);
			topImage.setTranslateY(15);
	    	Group g0=new Group();
			g0.getChildren().addAll(baseImage,topImage);
	        g0.setTranslateX(150*x);
	        if((x+y)%2==0) {
	        	g0.setTranslateY(260*y);
	        }
	        else {
	        	g0.setTranslateY(260*y+260/3);
	        }
	    	return g0;
	    }
	    private Point2D calculateArmEnd(double angleDeg, double scale) {
	        // 将角度转换为弧度
	        double angleRad = Math.toRadians(-angleDeg+90);
	        // 计算实际长度（考虑缩放）
	        double length = 520 * scale;
	        // 计算顶端坐标（基于旋转中心）
	        double endX = length * Math.cos(angleRad);
	        double endY = -length * Math.sin(angleRad); // Y轴向下为正，需取反
	        return new Point2D(endX,endY);
	    }
	    
	    public void grabbs(longzhua lz,define d) {
	    	if(lz.grabr%2==(lz.grabx+lz.graby)%2) {//方向不对抓不起来
	    	this.d=d;
	    	gk.df[d.y][d.x]=null;
	    	movef=1;
	    	ImageView zhuazi = (ImageView) lz.g.getChildren().get(2);
			ImageView zhuabi = (ImageView) lz.g.getChildren().get(3);
			ImageView niu = (ImageView) lz.g.getChildren().get(5);
//			zhuazi.setImage(gk.zhuazigs[lz.grabr]);
	    	try {
	    		lz.d.parentGroup.nowd=lz.d;
	    	}catch(Exception e1) {}
	    	Group ng=new Group();
	    	lz.ng=ng;
	    	gk.dfupane.getChildren().add(ng);
	    	int l=lz.d.parentGroup.gems.size();
	    	
	    	define[] ds=lz.d.parentGroup.gems.toArray(new define[l]);
//	    	System.out.println(ds.length);
	    	
	    	double ofx=lz.d.g.getTranslateX(),ofy=lz.d.g.getTranslateY();
	    
	    	double mofx=0,mofy=0;
	    	int mofx1=99,mofy1=99;
	    	for(int i=0;i<l;i++) {
//	    		gk.dfpane.getChildren().remove(gk.df[ds[i].y][ds[i].x].g);
	    		gk.df[ds[i].y][ds[i].x]=null;
//	    		dfpane.getChildren().add(nd.g);
	    		ng.getChildren().add(ds[i].g);
	    		double nofx=ds[i].g.getTranslateX()-ofx,nofy=ds[i].g.getTranslateY()-ofy;
//	    		ds[i].g.setTranslateX(nofx);
//	    		ds[i].g.setTranslateY(nofy);
	    		if(nofx<mofx) 
	    			mofx=nofx;
	    		if(nofy<mofy) 
	    			mofy=nofy;
	    		if(ds[i].x<mofx1)mofx1=ds[i].x;
	    		if(ds[i].y<mofy1)mofy1=ds[i].y;
	    	}
	    	
	    	int xf=0,yf=0;
	    	for(int i=0;i<l;i++) {
	    		if(ds[i].x==mofx1&&ds[i].g.getRotate()%180!=0) {
	    			xf=1;
	    		}
	    		if(ds[i].y==mofy1&&(ds[i].g.getRotate()==240||ds[i].g.getRotate()==120||
	    				ds[i].g.getRotate()==300||ds[i].g.getRotate()==60)) {
	    			yf=1;
	    		}
//	    		new p(xf+" "+yf+" "+ds[i].g.getRotate()+"\n");
	    	}
	    	if(xf==1)mofx-=63.3223948547;
	    	if(yf==1)mofy-=63.3223948547;
	    	//旋转突出边缘偏移补偿
	    	
	    	//固定半宽补偿
	    	mofx-=173;mofy-=173;
	    	
	    	
//   			double angle3 = lz.d.g.getRotate();
//	    	double s1=Math.sin(Math.toRadians(angle3)),c1=Math.cos(Math.toRadians(angle3));
//	    	double centerlen=346*(s1+c1)/2;
//	    	Point2D centerInGroup = lz.d.g.localToParent(
//   			centerlen,
//   			centerlen
//	    	);
//
//	    	// 3. 计算偏移量
//	    	double mx = centerInGroup.getX() - boundsl;
//	    	double my = centerInGroup.getY() - boundst;
//	    	new p(mx+" "+my+" "+centerInGroup.getX()+"\n");

	    	lz.d.parentGroup.mofx=mofx;
	    	lz.d.parentGroup.mofy=mofy;
	    	boundsx1=lz.d.g.getBoundsInParent().getWidth()/2;
	    	boundsy1=lz.d.g.getBoundsInParent().getHeight()/2;
	    	boundsx2=ng.getBoundsInLocal().getWidth()/2;
	    	boundsy2=ng.getBoundsInLocal().getHeight()/2;
	    	boundsl=ng.getBoundsInLocal().getMinX();
	    	boundst=ng.getBoundsInLocal().getMinY();
	    	
//	    	new p(mofx+" "+mofy+" "+boundsx1+" "+boundsy1+" "+boundsx2+" "+boundsy2+"\n");
	    	
	    	//添加连接
	    	lz.d.parentGroup.conns.forEach((k,v)->{
	    		gk.connectpane.getChildren().remove(v);
//	    		double nofx=v.getTranslateX()-ofx,nofy=v.getTranslateY()-ofy;
//	    		v.setTranslateX(nofx);
//	    		v.setTranslateY(nofy);
	    		ng.getChildren().add(v);
	    	});
	    	lz.d.parentGroup.conns.clear();
	    	
	    	updateClawPosition(lz,ng, zhuabi, zhuazi, niu);
	    	
	    	ng.rotateProperty().addListener(obs ->{
	    		try {
//	    			if(lz.d==null)
//	    				lz.g.getChildren().set(0,null); 
	        		for(int i=0;i<l;i++) {
	        			int np1=((int) Math.floor(((ng.getRotate()+(ds[i].face)*60+375)%360)/15)+24)%24;
			        	if(ds[i].nowpic!=np1) {
			        		ds[i].nowpic=np1;
//			        		 int k=np1%12;
			        		((ImageView) ds[i].g.getChildren().get(0)).setImage(gk.baoshiimagemap.get(ds[i].name+np1));
			        	}
	        		}
	    		}catch(Exception e1) {}
	        });
//	    	ng.setEffect(new GaussianBlur());
//	    	Image v=ng.snapshot(params1, null);
//	    	ng.setEffect(null);
//	    	ng.getChildren().add(new ImageView(v));
//	    	lz.g.getChildren().set(0,ng);  
	    	}
	    }
	    public void putbs() {
	    	 try {
//	    		Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.2),e->{
	    		 boolean movesuccessf=false;
	    			define d1=this.d;
//		         	System.out.println(this.grabr);

		         	if(d1.parentGroup.gems.size()==1) {
		         		if(this.grabr%2==(this.grabx+this.graby)%2) {
		         			if(d1.movebaoshi(d1,this.grabx,this.graby,this.grabr+d1.face))
		         				movesuccessf=true;
		         		}	
		         	}
		         	else {
		         		if(d1.movebaoshi(d1,this.grabx,this.graby,(int) (ng.getRotate()/60)))
		         			movesuccessf=true;
		         		System.out.println(d1.parentGroup.makeminname());
		         		String[] names=d1.parentGroup.makeminrotatename();
//		         		for(int i=0;i<6;i++) {
//		         			System.out.println(names[i]);
//		         		}
		         	}
		         	ImageView gr= ((ImageView) this.g.getChildren().get(2));
		            int np2=(int) Math.floor(((gr.getRotate()+360)%360)/30);
					this.nowpic2=np2;
		         	if(movesuccessf) {
		         		gk.dfupane.getChildren().remove(ng);
	         			this.d=null;
			            this.movef = 0;
//			            g.getChildren().set(0,new ImageView());
			            this.ng=null;
						gr.setImage(gk.zhuazis[np2]);
		         	}
		         	else gr.setImage(gk.zhuazigs[np2]);
//	    		}));
//	         	t.play();
	         }catch(Exception e1){
//	         	e1.printStackTrace();
	         }
	    }
		public void xuanzhuanlzb( int offgridx, int offgridy,int roll) {
//			if(!pause) {
			 int lx=this.x,ly=this.y;
		        double offx=150*offgridx,offy=260*offgridy+((offgridy+offgridx)%2==0?0:((lx+ly)%2==0?260/3:-260/3));
		        double length=Math.sqrt(offx*offx+offy*offy);
				double radians = Math.atan2(offy, offx);
				double degrees = Math.toDegrees(radians)%360;
				double rd=Math.abs(degrees + 90-this.rotater)>180?degrees-270:degrees + 90;
				ImageView zhuazi = (ImageView) this.g.getChildren().get(2);
				ImageView zhuabi = (ImageView) this.g.getChildren().get(3);
				ImageView niu = (ImageView) this.g.getChildren().get(5);	
	        if(this.movef==1) {
	        	this.movef=0;
//	        	grabbs(longzhua lz)   	
	        }
	        Group ng=this.ng;  
			zhuabi.rotateProperty().addListener(obs -> updateClawPosition(this,ng,zhuabi,zhuazi,niu));
			zhuabi.scaleYProperty().addListener(obs -> updateClawPosition(this,ng,zhuabi,zhuazi,niu));
	    	  // 配置动画参数
	        final Duration DURATION = Duration.seconds(1.6/gk.playrate);
	        final Interpolator EASING = Interpolator.EASE_BOTH; // 缓动效果
	        RotateTransition zhuabiRotateAnim = new RotateTransition(DURATION, zhuabi);
	        zhuabiRotateAnim.setToAngle(rd);
	        ScaleTransition zhuabiScaleAnim = new ScaleTransition(DURATION, zhuabi);
	        zhuabiScaleAnim.setToY(length / 520);
	        RotateTransition zhuaziRotateAnim = new RotateTransition(DURATION, zhuazi);
	        RotateTransition baoshiRotateAnim = new RotateTransition(DURATION, ng);
	        double ra1=zhuazi.getRotate()+roll*60;
	        
//	        zhuaziRotateAnim.setFromAngle(ng.getRotate()+(upf==1?60:0));
//	        double r1=ra2>180+zhuazi.getRotate()?ra1-360:ra1;
	        zhuaziRotateAnim.setToAngle(roll*60>180?ra1-360:ra1);
	     
//	        zhuaziRotateAnim.setToAngle(ra2>180?ra1-360:ra1);
//	        baoshiRotateAnim.setToAngle(ra2>180?ra2-360:ra2);
	        if(null!=this.ng) {
	        	double ra2=ng.getRotate()+roll*60;
	   	        baoshiRotateAnim.setToAngle(roll*60>180?ra2-360:ra2);
	   	     this.ani = new ParallelTransition(
	 	            zhuabiRotateAnim,
	 	            zhuaziRotateAnim,
	 	            zhuabiScaleAnim,
	 	            baoshiRotateAnim
	 	        );
	        }
	       
	        else
	        	this.ani = new ParallelTransition(
	                  zhuabiRotateAnim,
	                  zhuaziRotateAnim,
	                  zhuabiScaleAnim
	              );
	        try {
	        if(ng.getChildren().size()!=0) {
		        int np2=(int) Math.floor(((zhuazi.getRotate()+360)%360)/30);
		        zhuazi.setImage(gk.zhuazigs[np2]);
	        }
	        }catch(Exception e1) {}
	        this.ani.setInterpolator(EASING);
	        this.ani.play();
	        this.ani.setOnFinished(e->{
	        	zhuabi.setRotate(zhuabi.getRotate()%360);
	        	zhuazi.setRotate(zhuazi.getRotate()%360);
	        	this.rotater=rd;
	        	this.grabx+=this.x-this.grabx+offgridx;
	        	this.graby+=this.y-this.graby+offgridy;
	        	this.grabr= (this.grabr+roll)%6;
//	            lz.grabr= roll;
	        });      
//			}
		}
}
