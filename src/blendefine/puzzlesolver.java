package blendefine;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class puzzlesolver extends Application {
	Screen screen = Screen.getPrimary();
	double screenwidth = screen.getBounds().getWidth();
	double screenheight = screen.getBounds().getHeight();
	ConcurrentHashMap<String,String> hunyuancft=new ConcurrentHashMap<String,String>(),wuzangcft=new ConcurrentHashMap<String,String>(),wuzangcftni=new ConcurrentHashMap<String,String>();
	ConcurrentHashMap<String,HashSet<String>> findcraft=new ConcurrentHashMap<>(),findmaterial=new ConcurrentHashMap<>(),findcraftgraph=new ConcurrentHashMap<>();	
    HashMap<String, String[]> sealMap = new HashMap<>();
    HashMap<String, Integer> minDepth = new HashMap<>();
    HashMap<String, String[]> bestRecipeMaterials = new HashMap<>();
    HashMap<String, String> bestRecipeType = new HashMap<>(); // "wuzang" 或 "hunyuan"
    Set<String> basicElements = new HashSet<String>(Arrays.asList("阴", "阳", "金", "木", "水", "火", "土"));

	public String[] 
			jichuelename = {"阳","阴","金","水","木","火","土"},
			sixiangname = {"青龙","白虎","朱雀","玄武","黄极"},
			qiziname= {"文","武","灾","器","烟","山","海"},
			animalname = {"豺","鸟","蟾","狮","龟","鱼","鹿", "鲶","狮", "虾", "鹰"},
			dizhiname = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴" , "鸡" , "狗" , "猪" },
			tianganname = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"},
			jiugongname = {"乾", "兑", "离", "震", "巽", "坎", "艮", "坤", "中"},
			baxianname = {"宝剑", "葫芦", "扇子", "鱼鼓", "花篮", "竹笛", "玉板", "荷花"},
			qixingname = {"天枢", "天璇", "天玑", "天权", "玉衡", "开阳", "摇光"},
			longsonname = {"囚牛", "睚眦", "嘲风", "蒲牢", "狻猊", "霸下", "狴犴", "负屃", "螭吻","青龙" };
    // 用于存储右侧网格的ImageView
    private ImageView[][] gridv = new ImageView[9][17]; // 假设10x10网格
    private ImageView[][] seali = new ImageView[9][17]; // 假设10x10网格
    // 存储每个网格上的灵印名称
    private String[][] gridSeals = new String[9][17];
    private Boolean[][] isingroup=new Boolean[9][17],canput=new Boolean[9][17];
    private Polygon[][] selectpoly=new Polygon[9][17];
    Rectangle dr,dr1;
    Rotate rotate = new Rotate(),rotate1 = new Rotate();
    // 三角形图像数组（示例，实际需要您提供图像）
    private Image[] triid = new Image[10]; // 向下的三角形
    private Image[] triiu = new Image[10]; // 向上的三角形
    
    private Pane rootPane,selectpane,connectpane;
    Pane gpane;
	Pane stack;
    private int makepathf=-1;
	int ctpf=-1;
	private int maxx=0;
	private int maxy=0;
	private int offx=0;
	private int offy=0;
    private Boolean numlimitf=true,basicf=true;
    private boolean canputf=false,showConnections=true;
    
    private String currentSelectedSeal = null; // 当前选中的灵印

    private int nowlayi=0;
    public double scale=screenheight/1080;
    
    private List<String> curpath;
    private List<Integer[]> curgridpath=new ArrayList<Integer[]>();
	Image lingjvhei = new Image(getClass().getResourceAsStream("otherpic/灵具盒.png"),420*scale,800*scale,false,false);
	ImageView lingjvhe=new ImageView(lingjvhei);
	Image lingjvhebaoguangi = new Image(getClass().getResourceAsStream("otherpic/灵具盒宝光.png"),420*scale,800*scale,false,false);
	ImageView lingjvhebaoguang=new ImageView(lingjvhebaoguangi);
    
	HashMap<String,ImageView> choutiseals=new HashMap<String,ImageView>();
    HashMap<String,Image> sealpics=new  HashMap<String,Image>();
    
    // 页签分类
    public final String[] categories = {"五行","七字","地支", "七星","八仙","天干","九宫","九龙","生灵"};
    public String[][] sealtype= {jichuelename,qiziname,dizhiname,qixingname,baxianname,tianganname,jiugongname,longsonname,animalname};
    Timeline stopdoubleclick;
//    public Node[] righttabs;
	Pane[] ps=new Pane[4];
    
	Canvas nowcanvas;
    DropShadow dsb=new DropShadow(),dsb1=new DropShadow(),dsb2=new DropShadow();
    GaussianBlur gsb=new GaussianBlur(),gsb1=new GaussianBlur();
    
    public String target,handlesource;//分别为目标灵印集和材料灵印集
    public boolean isdestroy,anifinishf=false;
    public ArrayList<String> materialsn=new ArrayList<String>();//单个灵印材料
    public ArrayList<Integer> materials=new ArrayList<Integer>();//单个灵印材料数量
    HashMap<String,Integer>baoshis=new HashMap<String,Integer>();//所有宝石，包括宝石组
    public HashMap<Integer[],String> targets=new HashMap<Integer[],String>(),fixed=new HashMap<Integer[],String>();//固定位置的灵印
    public HashMap<String,List<Line>> connects=new HashMap<String,List<Line>>();
    public HashSet<Integer[]> area=new HashSet<Integer[]>();//格子限制
    public HashSet<Timeline> tms=new HashSet<Timeline>();
    
    private Stage levelSelectStage;
    private VBox levelSelectBox;
    private List<String> levelFiles = new ArrayList<>();
    private String currentLevelDir = "src/blendefine/files"; // 关卡目录
    private HashMap<String,HashSet<String>> sealconnects=new HashMap<>(); // 出入连接
    private HashMap<String,Label> sealnumlabels=new HashMap<>(); // 数量提示
    private Map<String, String[]> localizedSealNames = new HashMap<>();
    
    Font f0=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),50*scale),
    		f01=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),30*scale),
			f1=Font.loadFont(getClass().getResourceAsStream("uipic/dingliezhuhaifont-20240831GengXinBan)-2.ttf"),40*scale);
    
    blendefine bdf;
    guanka gk;
    boolean isfinished=false,canenter=false;
    int nowlevel,language=0;
    Timeline finishtimeline,checktimeline;
    
    public void start(Stage primaryStage) {
        rootPane = new Pane();
        rootPane.setPrefSize(1700*scale, 900*scale); // 增加宽度以容纳控制面板

        stack=makesolver();
        stack.setTranslateY(20*scale);
        stack.setTranslateX(380*scale);
        
        // 设置布局
        rootPane.getChildren().addAll(stack);
        
        // 读取示例数据
//		List<List<String>>p1=findAllConnectionPaths("金","马",4);
//		System.out.println(p1.toString());
        Scene scene = new Scene(rootPane,Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("灵印系统");
        primaryStage.setScene(scene);
        primaryStage.show();
        readpuzzle(findFileContainingString(new File("src/blendefine/files/"),"zhu30"));
    }
    public static File findFileContainingString(File dir, String part) {
        if (dir == null || !dir.isDirectory()) return null;
        File[] children = dir.listFiles();
        if (children == null) return null;

        for (File f : children) {
            if (f.isFile() && f.getName().contains(part)) {
                return f;
            }
        }
        return null;
    }
    public void inistage(int i) {
    	f0=bdf.f0;
    	f01=bdf.f01;
    	f1=bdf.f1;
    	language=bdf.language;
    	stack=makesolver();
    	stack.setTranslateY(40*scale);
        stack.setTranslateX(screenwidth/2-600*scale);
        try {
        	readpuzzle(findFileContainingString(new File("src/blendefine/files/"),"zhu"+i+"-"));
        }catch(Exception e1) {
        	readpuzzle(findFileContainingString(new File("src/blendefine/files/"),"zhu"+i));
        }
    	stack.setOpacity(0);
    	blendefine.lingjvpane.getChildren().add(stack);
    	nowlevel=i;
//    	stack.setVisible(false);
    }
    public Pane makesolver() {
    	initializeSealMap();
        initializeImages();
        inicraftable();
		
    	Pane stack=new Pane();
    	gpane = createRightGridPane();
        stack.getChildren().add(gpane);
        stack.getChildren().add(selectpane=new Pane());
        stack.setPickOnBounds(false);
        selectpane.setLayoutX(350*scale);
        selectpane.setMouseTransparent(true);
        
        stack.getChildren().add(connectpane=new Pane());
        connectpane.setLayoutX(350*scale);
        connectpane.setMouseTransparent(true);
        
        readExampleData();
        
        Timeline cat=new Timeline(new KeyFrame(Duration.seconds(5),e->canenter=true));
        cat.play();
        tms.add(cat);
        
        lingjvhe.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
            	if(gk!=null)gk.openbook();
            	else if(bdf!=null)bdf.openbook();
            	else showLevelSelectMenu();
            }
            else if(e.getButton() ==MouseButton.PRIMARY) {
            	if(isfinished&&canenter) {
            		String[] name=GemGroup.getRotatedMinNames(makeminname1());
//            		for(int i=0;i<6;i++)
//                	System.out.println(name[i]);
            		
            		//测试
//            		for(int y=0;y<9;y++) {
//            			for(int x=0;x<17;x++) {
//            				System.out.println("删除"+x+" "+y);
//            	    		 removeSealConnections(x, y);
//            	    		 try {
//            	    		int index=materialsn.indexOf(gridSeals[y][x]);
//            	        	if(index!=-1) {
//            	        		int num=materials.get(index);
//            	    	        materials.set(index, num+1);
//            	    	        sealnumlabels.get(gridSeals[y][x]).setText(String.valueOf(num+1));
//            	    	        if(num+1==1) {
//            	    		        if(num+1==1) {
//            	    		        	choutiseals.get(gridSeals[y][x]).setEffect(dsb);
//            	    		        }
//            	    	        }
//            	        	}
//            	    		 }catch(Exception e1) {
//            	    	    	}
//            		    	gpane.getChildren().remove(seali[y][x]);
//            		    	
//            		    	seali[y][x]=null;
//            		        gridSeals[y][x] = null;
//            		        updateNeighborConnections(x, y);
//            			}
//            		}
//            		name[5]=name[5].replaceAll("阴"," ");
//            		name[5]=name[5].replaceAll("阳"," ");
//            		String[] shouldput=name[5].split(" ");
//            		for(int i=0;i<shouldput.length;i++) {
//            			String[] xy=shouldput[i].split(",");
//            			int x=Integer.parseInt(xy[0]),y=Integer.parseInt(xy[1]);
//            			if(x<17&&y<9) {
//            				ImageView sealView;
//            		        if(!sealpics.containsKey("阴")) {
//            			        createSealImageView("阴");
//            			        createSealImageView1("阴");
//            		        }
//            		        if((x+y)%2==0) 
//            		        	sealView = new ImageView(sealpics.get("阴"));
//            		        
//            		        else sealView = new ImageView(sealpics.get("阴"+"1"));
//            		        sealView.setMouseTransparent(true);
//            		        sealView.setOnMouseClicked(null);
//            		        sealView.setFitWidth(115*scale);
//            		        sealView.setFitHeight(115*scale);
//            		        
//            		        new grid(x, y);
//            		        
//            		        seali[y][x]=sealView;
//            		        sealView.setTranslateX(50*x*scale-10*scale); // 居中，30是灵印宽度的一半
//            		        sealView.setTranslateY(87*y*scale-29*scale); // 居中，30是灵印高度的一半  87/3=29
//            		        if((x+y)%2==1) {
//            		        	sealView.setRotate(180);
//            		        	sealView.setTranslateY(87*y*scale);
//            		        }
//            		        
//            		        gpane.getChildren().add(sealView);
//            		        gridSeals[y][x] = "阴";
//            		        
//            			}
//            		}
            		
            		//不ban机器

                		//TODO
//                		if(gk!=null)gk.switchlevel(nowlevel, name, null, baoshis);
//                		else 
                			if(bdf!=null) {
                				if(isdestroy)bdf.enterguanka(nowlevel,new String[]{"销毁"},null,baoshis,this);
                				else bdf.enterguanka(nowlevel,name,null,baoshis,this);
                			}

                			
            	}
            }
        });
        isfinished=false;
        finishtimeline=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(lingjvhebaoguang.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(0.5),new KeyValue(lingjvhebaoguang.opacityProperty(),1)));
        finishtimeline.setAutoReverse(true);
        finishtimeline.setCycleCount(Timeline.INDEFINITE);
        
       
        
        checktimeline=new Timeline(new KeyFrame(Duration.seconds(0.2),e->{
            //判断完成方法
        	if(null!=target&&!target.equals("-")&&!target.equals("--")||materialsn.size()>0&&materialsn.get(0).contains(",")) {
        		isfinished=true;
        	}//合成给定关卡或者销毁关，来料为灵印组关
        	else {
            	boolean isusedout=true;
            	for(int i=0;i<materials.size();i++) {
            		if(materials.get(i)!=0)isusedout=false;
            	}
            	if(isusedout)
            		if(isGraphConnected())isfinished=true;
            }
        	if(isfinished&&finishtimeline.getStatus()==Status.STOPPED) {
        		finishtimeline.play();
        		if(ctpf!=-1) {
        			Timeline resetDrawer = new Timeline(
                        new KeyFrame(Duration.seconds(0.2), 
                        new KeyValue(ps[ctpf].translateXProperty(), -320*scale, Interpolator.EASE_BOTH)),
                        new KeyFrame(Duration.seconds(0.25), e1 -> { 
                            lingjvhe.toFront();
                            lingjvhebaoguang.toFront();
                            ps[ctpf].setEffect(null); 
                        }),
                        new KeyFrame(Duration.seconds(0.6), 
                        new KeyValue(ps[ctpf].translateXProperty(), 0, Interpolator.EASE_BOTH))
                    );
                    tms.add(resetDrawer);
                    resetDrawer.play();
        		}
        	}
        	else if(!isfinished){
        		finishtimeline.stop();
        		lingjvhebaoguang.setOpacity(0);
        	}
        }));
        checktimeline.setCycleCount(Timeline.INDEFINITE);
        checktimeline.play();
        
        makegridlayanimation(stack);
        dsb.setRadius(30*scale);
        dsb.setOffsetX(12*scale);
        dsb.setOffsetY(20*scale);
        
        dsb1.setRadius(40*scale);
        dsb1.setOffsetX(4*scale);
        dsb1.setOffsetY(15*scale);
        dsb1.setSpread(0.5);
        
        dsb2.setRadius(3*scale);
        dsb2.setColor(Color.WHITE);
        dsb2.setSpread(0.8);
        
        gsb.setRadius(40*scale);
        gsb1.setRadius(12*scale);
        
        DropShadow dsk=new DropShadow();
        dsk.setRadius(50*scale);
        dsk.setOffsetX(20*scale);
        dsk.setOffsetY(30*scale); 
        stack.setEffect(dsk);

        return stack;
    }
    
//	private void inisealpic() {
//		for (String category : categories) {
//	        String[] seals = sealMap.get(category);
//	        for (String sealName : seals) {
//	            createSealImageView(sealName);
//	            createSealImageView1(sealName);
//	        }
//		}
//	}

	private void makegridevent(MouseEvent e) {
		
		 Point2D localPoint = gpane.sceneToLocal(e.getSceneX(), e.getSceneY());
		    int gridX0 = (int) (localPoint.getX() / (50*scale));
		    int gridX1 = (int) (localPoint.getX() % (50*scale));
		    int gridY = (int) (localPoint.getY() / (87*scale));
		    int gridY1 = (int) (localPoint.getY() % (87*scale));
//		    nowhoverposx=localPoint.getX(); nowhoverposy=localPoint.getY();
//		    new p(localPoint.getX()+" "+localPoint.getY());
		    int off=(gridX0+gridY)%2==1?(gridX1>(87*scale-gridY1)/Math.sqrt(3)?0:-1):(gridX1>gridY1/Math.sqrt(3)?0:-1);
		    int gridX=gridX0+off;
//		    if(gridX<0||gridY<0||gridX>=17||gridY>=9)canputf=false;
//		    else canputf=true;
//		    gridX = Math.max(0, Math.min(gridX, 17 - 1));
//		    gridY = Math.max(0, Math.min(gridY, 9 - 1));
		    ImageView targetView = gridv[gridY][gridX]; // 注意行列顺序
		    targetView.setSmooth(false);
		    if (targetView != null) {
					MouseEvent simulatedEvent = new MouseEvent(MouseEvent.MOUSE_RELEASED, localPoint.getX(),
							localPoint.getY(), e.getScreenX(), e.getScreenY(), MouseButton.NONE, 0,
							e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), false, false,
							false, false, false, false, null);
		        Platform.runLater(() -> {
		            targetView.fireEvent(simulatedEvent);
		        });
		    }
	 }
	public void close() {
		tms.forEach(e->{
			e.stop();
		});
		if(ctpf!=-1) {
			Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(ps[ctpf].opacityProperty(),1)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(ps[ctpf].opacityProperty(),0)));
			t1.play();
		}
		Timeline t0=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(connectpane.opacityProperty(),1)),
				new KeyFrame(Duration.seconds(0.2),new KeyValue(connectpane.opacityProperty(),0)));
		t0.play();
		gpane.getChildren().forEach(e1->{
			if(!e1.equals(dr)&&!e1.equals(dr1)) {
				TranslateTransition inTransition = new TranslateTransition(Duration.millis(300),e1);
				inTransition.setToX(-130*scale);
				inTransition.setInterpolator(Interpolator.EASE_BOTH);
				inTransition.play();
			}
		});
		selectpane.getChildren().forEach(e1->{
			TranslateTransition inTransition = new TranslateTransition(Duration.millis(300),e1);
			inTransition.setToX(-130*scale);
			inTransition.setInterpolator(Interpolator.EASE_BOTH);
			inTransition.play();
		});
		Timeline t2=new Timeline(new KeyFrame(Duration.seconds(0.3)),
				new KeyFrame(Duration.seconds(0.5),new KeyValue(rotate.angleProperty(),-0)
						,new KeyValue(rotate1.angleProperty(),0)));
		t2.play();
		Timeline t3=new Timeline(new KeyFrame(Duration.seconds(0.4),new KeyValue(ps[0].translateXProperty(),0)
				,new KeyValue(ps[1].translateXProperty(),0)
				,new KeyValue(ps[2].translateXProperty(),0)
				,new KeyValue(ps[3].translateXProperty(),0)),
				new KeyFrame(Duration.seconds(0.5),new KeyValue(ps[0].translateXProperty(),80*scale)
						,new KeyValue(ps[1].translateXProperty(),80*scale)
						,new KeyValue(ps[2].translateXProperty(),80*scale)
						,new KeyValue(ps[3].translateXProperty(),80*scale)));
		t3.play();
	}
	private void makegridlayanimation(Pane stack) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<17;j++) {
//				if(j%2==0)gridv[i][j].setTranslateX(0);
//				else gridv[i][j].setTranslateX(50);
				gridv[i][j].setTranslateX(-150*scale);
			}
		}
		
		for(int i=0;i<4;i++) {
			int k=i;
			Pane ctp=new Pane();
			ps[i]=ctp;
			ps[i].setTranslateX(80*scale);
			Image lingjvbsi = new Image(getClass().getResourceAsStream("otherpic/灵具抽屉把手.png"),420*scale,800*scale,false,false);
			ImageView lingjvbs=new ImageView(lingjvbsi);
			ctp.getChildren().add(lingjvbs);
			ctp.setTranslateY(-4*scale);
			lingjvbs.setTranslateX(-230*scale);
			lingjvbs.setTranslateY(i*200*scale);
			
			Image lingjvcti = new Image(getClass().getResourceAsStream("otherpic/灵具抽屉.png"),420*scale,800*scale,false,false);
			ImageView lingjvct=new ImageView(lingjvcti);
			ctp.getChildren().add(lingjvct);
			lingjvct.setTranslateX(-230*scale);	
			
			Image lingjvctki = new Image(getClass().getResourceAsStream("otherpic/灵具抽屉框.png"),420*scale,800*scale,false,false);
			ImageView lingjvctk=new ImageView(lingjvctki);
			ctp.getChildren().add(lingjvctk);
			lingjvctk.setTranslateX(-230*scale);
			
			stack.getChildren().add(ctp);
			ctp.setPickOnBounds(false);
			ctp.setOnMouseClicked(e->{
				if(e.getButton()==MouseButton.PRIMARY) {
					Timeline t=new Timeline(
							new KeyFrame(Duration.seconds(0.2),e1->{ctp.toFront();
//							dsb.setRadius(0);
							ctp.setEffect(dsb1);
							}),
//							new KeyFrame(Duration.seconds(0.2),new KeyValue(dsb1.radiusProperty(),0)),
							
							new KeyFrame(Duration.seconds(0.2),new KeyValue(ctp.translateXProperty(),-320*scale,Interpolator.EASE_BOTH)),
							new KeyFrame(Duration.seconds(0.6),new KeyValue(ctp.translateXProperty(),150*scale,Interpolator.EASE_BOTH)));
					 tms.add(t);
					if(ctpf!=-1&&ctpf!=k) {
						Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.2),new KeyValue(ps[ctpf].translateXProperty(),-320*scale,Interpolator.EASE_BOTH)),
								new KeyFrame(Duration.seconds(0.25),e1->{lingjvhe.toFront();lingjvhebaoguang.toFront();ps[ctpf].setEffect(null);}),
								new KeyFrame(Duration.seconds(0.6),new KeyValue(ps[ctpf].translateXProperty(),0,Interpolator.EASE_BOTH)));
						t1.setOnFinished(e1->{
							t.play();
							ctpf=k;
						});
						t1.play();
						 tms.add(t1);
					}
					else if(ctpf!=k) {
						ctpf=k;
						t.play();
					}		
				}
				else {
					Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.2),new KeyValue(ctp.translateXProperty(),-320*scale,Interpolator.EASE_BOTH)),
							new KeyFrame(Duration.seconds(0.25),e1->{lingjvhe.toFront();lingjvhebaoguang.toFront();ctp.setEffect(null);}),
							new KeyFrame(Duration.seconds(0.6),new KeyValue(ctp.translateXProperty(),0,Interpolator.EASE_BOTH)));
					t1.play();
					 tms.add(t1);
					ctpf=-1;
				}
			});
		}
		
		stack.getChildren().add(lingjvhe);
		lingjvhe.setTranslateX(-66*scale);
		lingjvhe.setTranslateY(-4*scale);
		DropShadow dss=new DropShadow();
		dss.setRadius(20*scale);
		dss.setOffsetX(10*scale);
		dss.setOffsetY(15*scale);
		lingjvhe.setEffect(dss);
		
		stack.getChildren().add(lingjvhebaoguang);
		lingjvhebaoguang.setTranslateX(-66*scale);
		lingjvhebaoguang.setTranslateY(-4*scale);
		lingjvhebaoguang.setOpacity(0);
		lingjvhebaoguang.setMouseTransparent(true);

		dr=new Rectangle(0,0,6*scale,400*scale);
//		dr.setTranslateY();
		dr.setFill(Color.color(0.7,0.6,0.25));
		dr.setEffect(new InnerShadow());
		rotate.setPivotX(0);
		rotate.setPivotY(0); 
		rotate.setAngle(0);
		dr.getTransforms().add(rotate);
		gpane.getChildren().add(dr);
		dr1=new Rectangle(0,0,6*scale,400*scale);
		dr1.setTranslateY(394*scale);
		dr1.setFill(Color.color(0.7,0.6,0.25));
		dr1.setEffect(new InnerShadow());
		rotate1.setPivotX(0);
		rotate1.setPivotY(400*scale); 
		rotate1.setAngle(0);
		dr1.getTransforms().add(rotate1);
		gpane.getChildren().add(dr1);
		
		Rectangle[] rs=new Rectangle[8];
		for(int j=0;j<8;j++) {
			Rectangle r=new Rectangle(0,0,120*scale,6*scale);
			r.setTranslateY(783*scale);
			r.setTranslateX(-150*scale);
			if(j%2==0)r.setTranslateY((783+6)*scale);
			r.setFill(Color.color(0.7,0.6,0.25));
			rs[j]=r;
			rs[j].setEffect(new InnerShadow());
			gpane.getChildren().add(r);
		}
		DropShadow is=new DropShadow();
		Polygon p=new Polygon(50*scale,0,145*scale,0,95*scale,87*scale,145*scale,174*scale,
				95*scale,261*scale,145*scale,348*scale,95*scale,435*scale,145*scale,522*scale,
				95*scale,609*scale,145*scale,696*scale,95*scale,783*scale,
				0,783*scale,50*scale,696*scale,0,609*scale,50*scale,522*scale,
				0,435*scale,50*scale,348*scale,0,261*scale,50*scale,174*scale,0,87*scale);
		p.setFill(Color.color(0.3,0.2,0,1));
		p.setTranslateX(-150*scale);
		p.setEffect(is);
		
		gpane.getChildren().add(p);
		nowlayi=-100;
		Timeline t=new Timeline(new KeyFrame(Duration.millis(8),e->{
			nowlayi+=4;
			double lx=10*Math.cos(nowlayi*Math.PI/50);
			for(int i=0;i<9;i++) {
				for(int j=nowlayi/100;j<8;j++) {
					gridv[i][1+j*2].setTranslateX((-50+nowlayi+lx-10)*scale);
					gridv[i][2+j*2].setTranslateX((nowlayi+lx-10)*scale);
					if(j==7) {
						gridv[i][1+j*2].toFront();
						gridv[i][2+j*2].toFront();
					}
					if(i==0) {
						rs[j].setTranslateX((nowlayi+lx-120)*scale);
						if(j==nowlayi/100) {
							rs[j].setScaleX(0.5+lx/20);
							rs[j].setFill(Color.color(0.45+Math.max(0,lx/40),0.35+Math.max(0,lx/40),Math.max(0,lx/40)));
						}
					}
				}
			}
			p.setTranslateX((-50+nowlayi+lx-10)*scale);
			is.setOffsetX((lx*0.7-7)*scale);
			is.setRadius((7-lx*0.7)*scale);
		}));
		t.setCycleCount(200);
		 tms.add(t);
		
		t.setOnFinished(e->{
			gpane.getChildren().remove(p);
			makepuzzle();
			anifinishf=true;
		});
		
		Timeline t0=new Timeline(new KeyFrame(Duration.millis(8),e->{
			nowlayi+=0.5;
			for(int i=0;i<9;i++) {
				gridv[i][0].setTranslateX(nowlayi*scale);
				for(int j=0;j<8;j++) {
					gridv[i][1+j*2].setTranslateX((nowlayi-50)*scale);
					gridv[i][2+j*2].setTranslateX((nowlayi)*scale);
					if(j==7) {
						gridv[i][1+j*2].toFront();
						gridv[i][2+j*2].toFront();
					}
					if(i==0) {
						rs[j].setTranslateX((nowlayi-120)*scale);
					}
				}
			}
			p.setTranslateX((nowlayi-50)*scale);
		}));
		t0.setCycleCount(200);
		 tms.add(t0);
		t0.setOnFinished(e->{
			t.play();
//			Timeline t1=new Timeline(new KeyFrame(Duration.seconds(1),e1->{
//				t.pause();
//				t.getKeyFrames().add(1, new KeyFrame(Duration.millis(8),e2->{new p(2);}));
//				t.play();
//			}));
//			t1.play();
		});
		
		Timeline t2=new Timeline(new KeyFrame(Duration.seconds(0.3),new KeyValue(rotate.angleProperty(),-90)),
				new KeyFrame(Duration.seconds(0.3),new KeyValue(rotate1.angleProperty(),90)));
		t2.play();
		t2.setOnFinished(e->{
			t0.play();
		});
		 tms.add(t2);
		Platform.runLater(()->{
			Timeline t3=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(ps[0].translateXProperty(),80*scale)
					,new KeyValue(ps[1].translateXProperty(),80*scale)
					,new KeyValue(ps[2].translateXProperty(),80*scale)
					,new KeyValue(ps[3].translateXProperty(),80*scale)),
					new KeyFrame(Duration.seconds(0.4),new KeyValue(ps[0].translateXProperty(),0)
							,new KeyValue(ps[1].translateXProperty(),0)
							,new KeyValue(ps[2].translateXProperty(),0)
							,new KeyValue(ps[3].translateXProperty(),0)));
			t3.play();
			 tms.add(t3);
		});
		
	}
	
	// 清空所有选择的方法
    private void clearAllSelections() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
//                gridSeals[i][j] = null;
                isingroup[i][j] = false;
                if (selectpoly[i][j] != null) {
                    selectpane.getChildren().remove(selectpoly[i][j]);
                    selectpoly[i][j] = null;
                }
//                if (seali[i][j] != null) {
//                    gpane.getChildren().remove(seali[i][j]);
//                    seali[i][j] = null;
//                }
            }
        }
    }
    
    // 清空所有选择的方法
    private void clearAllSeal() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
                gridSeals[i][j] = null;
              
                if (seali[i][j] != null) {
                	gpane.getChildren().remove(seali[i][j]);
                    seali[i][j] = null;
                    
                }
            }
        }
        
    }

    // 修改makeminname方法，返回灵印名称而不是坐标信息
    public String makeminname() {
    	HashMap<String,Integer> sealnum=new HashMap<>();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
                if (gridSeals[i][j] != null) {
                	String sn=gridSeals[i][j];
                	if(sealnum.containsKey(sn)) {
                		sealnum.put(sn,sealnum.get(sn)+1);
                	}
                	else {
                		sealnum.put(sn,1);
                	}
                }
            }
        }
        if(basicf) {
        	for(int g=0;g<3;g++) {//将最高为三级合成灵印转换为基础元素
        		List<String> ks=new ArrayList<String>(); 
	        	 sealnum.forEach((k,v)->{
	        		if(wuzangcftni.containsKey(k)) {
	        			ks.add(k);
	        		}
	 	        });
	        	 ks.forEach(e->{
	        		 int iniv=sealnum.get(e);
	        			String s=wuzangcftni.get(e);
	        			String[] ss=s.split("，");
	        			for(int i=0;i<ss.length;i++) {
	        				if(sealnum.containsKey(ss[i])) {
	        					sealnum.put(ss[i],sealnum.get(ss[i])+iniv);
	        				}
	        				else {
	        					sealnum.put(ss[i],iniv);
	        				}
	        			}
	        		 sealnum.remove(e);
	        	 });
        	}
        }
        if(numlimitf)
	        sealnum.forEach((k,v)->{
	        	name.append(k+" "+v+" ");
	        });
        else
        	sealnum.forEach((k,v)->{
 	        	name.append(k+" - ");
 	        });
        return name.length() > 0 ? name.toString() : "-";
    }
    
    private void createSealImageView(String sealName) {
    	Group g0=new Group();
    	Blend definecolorblend = new Blend();
    	definecolorblend.setMode(BlendMode.OVERLAY);
        ImageView baseImage2= new ImageView(new Image(getClass().getResourceAsStream("definepic/宝石a0"+".png"),346*scale,346*scale,false,false));
        baseImage2.setSmooth(false);
        baseImage2.setFitHeight(346*scale);
        baseImage2.setFitWidth(346*scale);
//        System.out.println(sealName);
        ImageView middleImage2=null;
        try {
        	Image ai=new Image(getClass().getResourceAsStream("definepic/宝石"+sealName+".png"),346*scale,346*scale,false,false);
 	   		middleImage2 = new ImageView(ai);
        }
 	   	catch(Exception e) {middleImage2 = new ImageView();}
 	   	middleImage2.setSmooth(false);
 	   	middleImage2.setFitHeight(346*scale);
 	  	middleImage2.setFitWidth(346*scale);
 	  	
 		try {
 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+sealName+".png"),346*scale,346*scale,false,false)));
 	  	}catch(Exception e1) {
 	  		if(sealName.equals("天枢")||sealName.equals("天玑")||sealName.equals("天璇")||sealName.equals("天权")||
 	  				sealName.equals("开阳")||sealName.equals("玉衡")||sealName.equals("摇光")) {
 	  			definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/星.png"),346*scale,346*scale,false,false))); 
// 	  			System.out.print(1);
 	  		}
 	  		else definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png"),346*scale,346*scale,false,false))); 	
 	  	}
 	  	
        g0.getChildren().addAll(middleImage2,baseImage2);
        g0.setEffect(definecolorblend);      
        //设置多边形中心为173，173
        Polygon p=new Polygon(23*scale,86*scale,0,0,23*scale,86*scale,323*scale
        		,86*scale,346*scale,0,323*scale,86*scale,173*scale,346*scale);
        Group g1=new Group(g0);
        g1.setClip(p);
        Blend defineli = new Blend();
    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/光照.png"),346*scale,346*scale,false,false)));
        g1.setEffect(defineli);
        Group g3=new Group(new ImageView(new Image(getClass().getResourceAsStream("definepic/包圆.png"),346*scale,346*scale,false,false)),g1);
        SnapshotParameters params1 = new SnapshotParameters();
        params1.setFill(Color.TRANSPARENT);
		Image si=g3.snapshot(params1, null);
		sealpics.put(sealName, si);
//        ImageView imageView = new ImageView(si);
//        imageView.setFitWidth(115*scale);
//        imageView.setFitHeight(115*scale);
//        imageView.setSmooth(true);
//        imageView.setPreserveRatio(true);
//        // 设置点击事件 - 选择灵印
//        imageView.setOnMouseClicked(e -> {
//            System.out.println("选中的灵印: " + sealName);
//            currentSelectedSeal = sealName;
//        });
//        return imageView;
    }
    
    private void createSealImageView1(String sealName) {
    	Group g0=new Group();
    	Blend definecolorblend = new Blend();
    	definecolorblend.setMode(BlendMode.OVERLAY);
        ImageView baseImage2= new ImageView(new Image(getClass().getResourceAsStream("definepic/宝石a12"+".png"),346*scale,346*scale,false,false));
        baseImage2.setSmooth(false);
        baseImage2.setFitHeight(346*scale);
        baseImage2.setFitWidth(346*scale);
//        System.out.println(sealName);
        ImageView middleImage2=null;
        try {
        	Image ai=new Image(getClass().getResourceAsStream("definepic/宝石"+sealName+".png"),346*scale,346*scale,false,false);
 	   		middleImage2 = new ImageView(ai);
        }
 	   	catch(Exception e) {middleImage2 = new ImageView();}
 	   	middleImage2.setSmooth(false);
 	   	middleImage2.setFitHeight(346*scale);
 	  	middleImage2.setFitWidth(346*scale);
 	  	
 	  	try {
 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+sealName+".png"),346*scale,346*scale,false,false)));
 	  	}catch(Exception e1) {
 	  		if(sealName.equals("天枢")||sealName.equals("天玑")||sealName.equals("天璇")||sealName.equals("天权")||
 	  				sealName.equals("开阳")||sealName.equals("玉衡")||sealName.equals("摇光")) {
 	  			definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/星.png"),346*scale,346*scale,false,false))); 
// 	  			System.out.print(1);
 	  		}
 	  		else definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png"),346*scale,346*scale,false,false))); 	
 	  	}
 	  	
        g0.getChildren().addAll(middleImage2,baseImage2);
        g0.setEffect(definecolorblend);      
        //设置多边形中心为173，173
        Polygon p=new Polygon(23*scale,86*scale,0,0,23*scale,86*scale,323*scale,86*scale,
        		346*scale,0,323*scale,86*scale,173*scale,346*scale);
        Group g1=new Group(g0);
        g1.setClip(p);
        Blend defineli = new Blend();
    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/光照0.png"),346*scale,346*scale,false,false)));
        g1.setEffect(defineli);
        Group g3=new Group(new ImageView(new Image(getClass().getResourceAsStream("definepic/包圆.png"),346*scale,346*scale,false,false)),g1);
        SnapshotParameters params1 = new SnapshotParameters();
        params1.setFill(Color.TRANSPARENT);
		Image si=g3.snapshot(params1, null);
		sealpics.put(sealName+"1", si);
//        ImageView imageView = new ImageView(si);
//        imageView.setFitWidth(115*scale);
//        imageView.setFitHeight(115*scale);
//        imageView.setSmooth(true);
//        imageView.setPreserveRatio(true);
//        // 设置点击事件 - 选择灵印
//        imageView.setOnMouseClicked(e -> {
//            System.out.println("选中的灵印: " + sealName);
//            currentSelectedSeal = sealName;
//        });
//        return imageView;
    }
    
    private Pane createRightGridPane() {
        Pane gridPane = new Pane();
        gridPane.setLayoutX(350*scale);
        gridPane.setPrefSize(900*scale, 800*scale);
//        gridPane.setStyle("-fx-background-color: #2b2b2b;");
        
        return gridPane;
    }
    
    public void readgrid(String line) {
        String[] parts = line.split(" ");
        for(int i = 0; i < parts.length; i += 2) {
            int x = Integer.parseInt(parts[i]);
            int y = Integer.parseInt(parts[i + 1]);
            
            grid nowgrid = new grid(x, y);
            ImageView triangleView = null;
            int r = (int)(Math.sqrt(Math.random()) * 10);
            
            if(nowgrid.upf == 1) {      
                triangleView = new ImageView(triid[r]);  
            } else {
                triangleView = new ImageView(triiu[r]);  
            }
            
            triangleView.setSmooth(false);
            triangleView.setTranslateY(87*y*scale);
            triangleView.setTranslateX(50*x*scale);
            triangleView.setFitWidth(100*scale);
            triangleView.setFitHeight(87*scale);
            
            // 确保数组大小足够
            if (y < gridv.length && x < gridv[y].length) {
                gridv[y][x] = triangleView;
            }
            
            // 为三角形添加点击事件
//            final int finalX = x;
//            final int finalY = y;
            
            triangleView.setOnMousePressed(e -> {
//            	System.out.print(2);
            	if(e.getButton()==MouseButton.PRIMARY) {

                    if(makepathf==0) {
                    	curgridpath.add(new Integer[]{x,y});
                    	makepathf=1;
                    }
                    else if(makepathf==1) {
                    	makepathf=-1;
                    	createpath();
                    }
                    
                    else if (gridSeals[y][x] != null) {
                    		removeSealFromGrid(x,y);
                    }
                    else if (currentSelectedSeal != null) {
                    	canputf=false;
                    		 placeSealOnGrid(x, y, currentSelectedSeal);
                           
                    }
            	}
            	else if(e.getButton()==MouseButton.SECONDARY){
            		
            		if (gridSeals[y][x] == null&&canput[y][x]) {
            			try {
            				rootPane.getChildren().removeIf(node -> node instanceof Canvas);
            			}catch(Exception e1) {
            				blendefine.dp.getChildren().removeIf(node -> node instanceof Canvas);
            			}
                        showConnectionCanvas(x, y);
            		}
            		  // 新增：当右键点击有灵印的格子时，显示合成链路图
            	    else if (gridSeals[y][x] != null) {
            	        try {
            	            rootPane.getChildren().removeIf(node -> node instanceof Canvas);
            	        } catch(Exception e1) {
            	            blendefine.dp.getChildren().removeIf(node -> node instanceof Canvas);
            	        }
            	        showSynthesisPathCanvas(x, y);
            	    }
            	}
            });
            
            triangleView.setOnScroll(e->{
            		if(e.getDeltaY()>0&&null!=isingroup[y][x]&&isingroup[y][x]) {
            			isingroup[y][x]=false;
            			selectpane.getChildren().remove(selectpoly[y][x]);
            			selectpoly[y][x]=null;
            		}
            		else if(e.getDeltaY()<0&&(null==isingroup[y][x]||!isingroup[y][x])){
            			isingroup[y][x]=true;
            			if((x+y)%2==1) {
            				selectpoly[y][x]=new Polygon(50*scale,0,100*scale,87*scale,0,87*scale);
            			}
            			else {
            				selectpoly[y][x]=new Polygon(0,0,100*scale,0,50*scale,87*scale);
            				
            			}
            			selectpoly[y][x].setTranslateX(50*x*scale);
        				selectpoly[y][x].setTranslateY(87*y*scale);
            			selectpoly[y][x].setFill(Color.color(1,1,1,0.7));
            			selectpane.getChildren().add(selectpoly[y][x]);
            		}
            });
            
            gpane.getChildren().add(triangleView);
            
            triangleView.setOnMouseEntered(e1 -> {
                if(makepathf==1) {
                	curgridpath.add(new Integer[]{x,y});
                }
//                System.out.println("悬停在网格: (" + hovergridx + ", " + hovergridy + ")");
            });
            
            triangleView.setOnMouseReleased(e->{
            	if(canputf) {
    				canputf=false;
	            	removeSealFromGrid(x,y);
	                placeSealOnGrid(x, y, currentSelectedSeal);
            	}
            });
        } 
        
    }
    
    // 新增方法: 获取三角形周围三个格子坐标
    private List<int[]> getTriangleNeighbors(int x, int y) {
        List<int[]> neighbors = new ArrayList<>();
        
        // 根据三角形方向确定相邻格子
        if ((x + y) % 2 == 0) {
            // 向上三角形 - 相邻格子为上方、左上方、右上方
            neighbors.add(new int[]{x, y - 1});     // 上方
            neighbors.add(new int[]{x - 1, y}); // 左上方  
            neighbors.add(new int[]{x + 1, y}); // 右上方
        } else {
            // 向下三角形 - 相邻格子为下方、左下方、右下方
            neighbors.add(new int[]{x, y + 1});     // 下方
            neighbors.add(new int[]{x - 1, y}); // 左下方
            neighbors.add(new int[]{x + 1, y}); // 右下方
        }
        
        return neighbors;
    }

    // 新增方法: 检查坐标是否有效
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < 17 && y >= 0 && y < 9;
    }

 // 新增方法: 创建连接画布（透明覆盖，行星环绕排列）
    private void createConnectionCanvas(int x, int y, Set<String> connections) {
        // 创建透明画布
        Canvas connectionCanvas = new Canvas();
        try {
        	connectionCanvas.setWidth(rootPane.getWidth());
        	connectionCanvas.setHeight(rootPane.getHeight());
        }catch(Exception e) {
        	connectionCanvas.setWidth(blendefine.game1.getWidth());
        	connectionCanvas.setHeight(blendefine.game1.getHeight());
        }
        
        connectionCanvas.setEffect(dsb);
        connectionCanvas.setMouseTransparent(false); // 允许接收鼠标事件
        connectionCanvas.setStyle("-fx-background-color: transparent;");
        
        GraphicsContext gc = connectionCanvas.getGraphicsContext2D();
        
        // 计算中心点在屏幕上的位置（网格起始位置 + 格子偏移 + 半个格子居中）
        double centerX = 350*scale + 50*scale * x + 46*scale;
        double centerY = 87*scale * y + 30*scale;
        if((x+y)%2==1) {
        	centerY=87*scale * y + 58*scale;
        }
        
        // 存储灵印位置信息用于点击检测
        List<SealPosition> sealPositions = new ArrayList<>();
        
        // 绘制半透明背景（可选，提供视觉区分）
//        gc.setFill(Color.rgb(30, 30, 30, 0.7));
//        gc.fillRect(0, 0, connectionCanvas.getWidth(), connectionCanvas.getHeight());
        
        // 绘制中心点指示器
        if (gridSeals[y][x] != null) {
	        gc.setFill(Color.color(1,0,0,0.7));
	        gc.setStroke(Color.color(1,0,0,0.7));
        }
        else {
        	gc.setFill(Color.color(1,1,1,0.7));
	        gc.setStroke(Color.color(1,1,1,0.7));
        }
        gc.setLineWidth(4*scale);
        gc.strokeOval(centerX - 10*scale, centerY - 10*scale, 20*scale, 20*scale);
//        gc.fillText("相生", centerX - 15*scale, centerY - 15*scale);
        
        // 将连接元素转换为列表以便按顺序排列
        List<String> connectionList = new ArrayList<>(connections);
        
        // 定义三个环的半径
        double[] ringRadii = {70*scale, 110*scale, 180*scale,230*scale,330*scale};
        int[] ringCounts = {3,3,6,6,12};
        double[] ringrotate = {0,Math.PI/3,Math.PI/6,Math.PI/3,0};
        
        int elementIndex = 0;
        
        // 为每个环创建元素
        for (int ring = 0; ring < ringCounts.length && elementIndex < connectionList.size(); ring++) {
            int elementsInThisRing = Math.min(ringCounts[ring], connectionList.size() - elementIndex);
            double radius = ringRadii[ring];
            
            // 绘制环的轮廓（可选）
            gc.setStroke(Color.color(1,1,1,0.3));
            gc.setLineWidth(2*scale);
            gc.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            
            for (int i = 0; i < elementsInThisRing && elementIndex < connectionList.size(); i++) {
                String sealName = connectionList.get(elementIndex);
                
                // 计算在环上的位置
                double angle = 2 * Math.PI * i / ringCounts[ring]+ringrotate[ring];
                double posX = centerX + radius * Math.cos(angle) - 50*scale; // 减去一半宽度居中
                double posY = centerY + radius * Math.sin(angle) - 50*scale; // 减去一半高度居中
                
                // 创建灵印图像视图
                Group sg=new Group();
                ImageView sealView = createSealGraphic(sealName, 100*scale);
                if(materials.get(materialsn.indexOf(sealName))==0)sealView.setVisible(false);
                sealView.setMouseTransparent(true); // 让画布处理点击事件

                Label sl0=sealnumlabels.get(sealName),sl=new Label(sl0.getText());
                sl.setTextFill(Color.BLACK);
                sl.setFont(f01);
                sl.setEffect(dsb2);
                
//                ps[0].getChildren().add(sl);
                sl.setTranslateX(70*scale);
                sl.setTranslateY(60*scale);
            	sg.getChildren().add(sealView);
            	
            	if(!sl.getText().equals("0"))
                sg.getChildren().add(sl);
                sg.setLayoutX(posX);
                sg.setLayoutY(posY);
                // 存储位置信息用于点击检测
                sealPositions.add(new SealPosition(sealName, posX, posY, 100*scale, 100*scale));
                @SuppressWarnings("unused")
				Scene tempScene = new Scene(sg);
                //强制执行一次完全布局脉冲来防止label消失
//                sg.applyCss();
//                sg.layout();
//                System.out.println("Label text: '" + sl.getTranslateX() + "'");
              
                    // 将图像绘制到画布上
                    SnapshotParameters params = new SnapshotParameters();
                    params.setFill(Color.TRANSPARENT);
                    Image sealImage = sg.snapshot(params, null);
                    gc.drawImage(sealImage, posX, posY);
                
               
//                try {
//					javax.imageio.ImageIO.write(SwingFXUtils.fromFXImage(sealImage, null), "png", new File("debug.png"));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
               
                
                // 绘制连接线
//                gc.setStroke(Color.rgb(200, 200, 200, 0.5));
//                gc.setLineWidth(1);
//                gc.strokeLine(centerX, centerY, posX + 50, posY + 50);
                
                elementIndex++;
            }
        }
        
//        // 添加标题
//        gc.setFill(Color.WHITE);
//        gc.setFont(javafx.scene.text.Font.font(14));
//        gc.fillText("可连接到当前格子的元素", centerX - 70, centerY - 120);
//        
//        // 添加来源信息
//        gc.setFill(Color.LIGHTGRAY);
//        gc.setFont(javafx.scene.text.Font.font(11));
//        String sourceText = "来源: " + String.join(", ", neighborSeals);
//        gc.fillText(sourceText, centerX - 80, centerY + 180);
//        
//        // 添加关闭提示
//        gc.setFill(Color.WHITE);
//        gc.fillText("右键点击关闭", centerX - 40, centerY + 210);
        
        // 设置鼠标点击事件
       
        
      
        connectionCanvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                // 右键关闭
                stack.getChildren().remove(connectionCanvas);
            } else if (e.getButton() == MouseButton.PRIMARY) {
                // 左键检测是否点击了灵印
                for (SealPosition sealPos : sealPositions) {
                    if (sealPos.contains(e.getX(), e.getY())) {
                    	removeSealFromGrid(x,y);
                        placeSealOnGrid(x, y, sealPos.sealName);
                        stack.getChildren().remove(connectionCanvas);
//                        System.out.println("在中键格子(" + x + "," + y + ")添加灵印: " + sealPos.sealName);
                        break;
                    }
                }
            }
        });
        
        // 添加鼠标移动事件用于高亮
//        connectionCanvas.setOnMouseMoved(e -> {
//            boolean found = false;
//            for (SealPosition sealPos : sealPositions) {
//                if (sealPos.contains(e.getX(), e.getY())) {
//                    connectionCanvas.setCursor(javafx.scene.Cursor.HAND);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                connectionCanvas.setCursor(javafx.scene.Cursor.DEFAULT);
//            }
//        });
        
        // 将画布添加到rootPane最上层
        stack.getChildren().add(connectionCanvas);
    }

    // 辅助类：存储灵印位置信息
    private static class SealPosition {
        String sealName;
        double x, y, width, height;
        
        SealPosition(String sealName, double x, double y, double width, double height) {
            this.sealName = sealName;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        boolean contains(double pointX, double pointY) {
            return pointX >= x && pointX <= x + width && 
                   pointY >= y && pointY <= y + height;
        }
    }

    // 修改方法名以保持一致性
    private void showConnectionCanvas(int x, int y) {
        // 获取周围三个格子的坐标
        List<int[]> neighbors = getTriangleNeighbors(x, y);
        
        // 收集所有非空格子的可连接元素
        Set<String> commonConnections = null;
        List<String> neighborSeals = new ArrayList<>();
        
        for (int[] neighbor : neighbors) {
            int nx = neighbor[0];
            int ny = neighbor[1];
            
            // 检查坐标是否有效且格子非空
            if (isValidCoordinate(nx, ny) && gridSeals[ny][nx] != null) {
                String neighborSeal = gridSeals[ny][nx];
                neighborSeals.add(neighborSeal);
                
                // 从findcraftgraph获取可连接的元素
                Set<String> connections = findcraftgraph.get(neighborSeal);
                if(neighborSeal.equals("震")) {
                	connections.forEach(e->{
                		System.out.print(e+" ");
                	});
                }
                if (connections != null) {
                    if (commonConnections == null) {
                        commonConnections = new HashSet<>(connections);
                    } else {
                        commonConnections.retainAll(connections); // 取交集
//                    	commonConnections.addAll(connections); // 取并集
                    }
                }
            }
        }
        try {
        commonConnections.retainAll(materialsn);//可以使用的元素
        }catch(Exception e1) {}
        // 如果没有找到共同的连接元素，直接返回
        if (commonConnections == null || commonConnections.isEmpty()) {
            System.out.println("没有找到可连接的共同元素");
        return;
        }
        
        // 创建连接画布
        createConnectionCanvas(x, y, commonConnections);
    }
    
    // 新增方法: 创建灵印图形（用于悬浮窗）
    private ImageView createSealGraphic(String sealName, double size) {
        ImageView sealView;
        
        // 使用已有的sealpics中的图像
        if (!sealpics.containsKey(sealName)) {
        	createSealImageView(sealName);
            createSealImageView1(sealName);
        } 

        sealView = new ImageView(sealpics.get(sealName));
        sealView.setFitWidth(size);
        sealView.setFitHeight(size);
        sealView.setPreserveRatio(true);
        sealView.setSmooth(true);
        
        return sealView;
    }
    
    
    private void createpath() {	
    	try {
			for(int i=0;i<curpath.size();i++) {
				Integer[] cgp=curgridpath.get(i);
				placeSealOnGrid(cgp[0],cgp[1],curpath.get(i));
			}
    	}catch(Exception e) {}
		curgridpath.clear();
//		curpath.clear();
	}

	// 在网格上放置灵印
    private void placeSealOnGrid(int x, int y, String sealName) {
    	if(canput[y][x]) {
    	int index=materialsn.indexOf(sealName);
    	
    	if(index!=-1) {
    		try {
	    		int num=materials.get(index);
	    		if(num==0)return;
		        materials.set(index, num-1);
		        int k=num-1;
		        if(k==0) {
		        	choutiseals.get(sealName).setEffect(gsb);
		        }
		        sealnumlabels.get(sealName).setText(String.valueOf(k));
    		}catch(Exception e) {}
    	}
//    	  else{
//    		  HashMap<String,Integer> sealnum=new HashMap<>();
//    		  sealnum.put(sealName, 1);
//	        	for(int g=0;g<3;g++) {//将最高为三级合成灵印转换为基础元素
//	        		List<String> ks=new ArrayList<String>(); 
//		        	sealnum.forEach((k,v)->{
//		        		if(wuzangcftni.containsKey(k)) {
//		        			ks.add(k);
//		        		}
//		 	        });
//		        	 ks.forEach(e->{
//		        		 int iniv=sealnum.get(e);
//		        			String s=wuzangcftni.get(e);
//		        			String[] ss=s.split("，");
//		        			for(int i=0;i<ss.length;i++) {
//		        				if(sealnum.containsKey(ss[i])) {
//		        					sealnum.put(ss[i],sealnum.get(ss[i])+iniv);
//		        				}
//		        				else {
//		        					sealnum.put(ss[i],iniv);
//		        				}
//		        			}
//		        		 sealnum.remove(e);
//		        	 });
//	        	}
//	        	sealnum.forEach((k,v)->{
//	        		int index1=materialsn.indexOf(k);
//	        		if(index1!=-1) {
//	            		try {
//	        	    		int num=materials.get(index1);
//	        	    		if(num==0)return;
//	        		        materials.set(index1, num-v);
//	        		        if(num-v==0) {
//	        		        	choutiseals.get(sealName).setEffect(gsb);
//	        		        }
//	            		}catch(Exception e) {}
//	            	}
//	        	});
//	        }
    	  // 移除该位置原有的灵印
//        removeSealFromGrid(x, y);
        
        // 创建新的灵印ImageView
        ImageView sealView;
        if(!sealpics.containsKey(sealName)) {
	        createSealImageView(sealName);
	        createSealImageView1(sealName);
        }
        if((x+y)%2==0) 
        	sealView = new ImageView(sealpics.get(sealName));
        
        else sealView = new ImageView(sealpics.get(sealName+"1"));
        sealView.setMouseTransparent(true);
        sealView.setOnMouseClicked(null);
        sealView.setFitWidth(115*scale);
        sealView.setFitHeight(115*scale);
        
        new grid(x, y);
        
        seali[y][x]=sealView;
        sealView.setTranslateX(50*x*scale-10*scale); // 居中，30是灵印宽度的一半
        sealView.setTranslateY(87*y*scale-29*scale); // 居中，30是灵印高度的一半  87/3=29
        if((x+y)%2==1) {
        	sealView.setRotate(180);
        	sealView.setTranslateY(87*y*scale);
        }
        
        gpane.getChildren().add(sealView);
        gridSeals[y][x] = sealName;
        
        Bloom b=new Bloom();
        sealView.setEffect(b);
        Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.6),new KeyValue(b.thresholdProperty(),1)));
        t.setOnFinished(e->{
        	sealView.setEffect(null);
        });
        t.play();
        tms.add(t);
//        System.out.println("在网格(" + x + "," + y + ")放置灵印: " + sealName);
        
        // 更新连接线显示
        updateSealConnections(x, y, sealName);
        // 同时更新周围邻居的连接线
//        updateNeighborConnections(x, y);
        
    	}
    }
    
    public boolean isGraphConnected() {
        if (sealconnects.isEmpty()) {
            return false; // 空图被认为是不连通的
        }
        Set<String> visited = new HashSet<>();
        String startNode = sealconnects.keySet().iterator().next();
        
        // 从任意节点开始DFS遍历
        dfs(startNode, visited);
        // 如果访问过的节点数等于图中所有节点数，则图是连通的
        return visited.size() == sealconnects.size();
    }

    private void dfs(String node, Set<String> visited) {
        visited.add(node);
        // 遍历所有邻居节点
        for (String neighbor : sealconnects.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }
    
	// 移除网格上的灵印
    private void removeSealFromGrid(int x, int y) {
    	if(canput[y][x]) {
        // 这里需要实现从gpane中移除对应的ImageView
        // 由于代码结构限制，这个功能需要更复杂的实现来跟踪已添加的灵印ImageView
//    	System.out.println("删除"+x+" "+y);
    		 removeSealConnections(x, y);
    		 try {
    		int index=materialsn.indexOf(gridSeals[y][x]);
        	if(index!=-1) {
        		int num=materials.get(index);
    	        materials.set(index, num+1);
    	        sealnumlabels.get(gridSeals[y][x]).setText(String.valueOf(num+1));
    	        if(num+1==1) {
    		        if(num+1==1) {
    		        	choutiseals.get(gridSeals[y][x]).setEffect(dsb);
    		        }
    	        }
        	}
    		 }catch(Exception e1) {
    	    	}
	    	gpane.getChildren().remove(seali[y][x]);
	    	
	    	seali[y][x]=null;
	        gridSeals[y][x] = null;
	        updateNeighborConnections(x, y);
    	
    	}
    }
    private void updateNeighborConnections(int x, int y) {
        List<int[]> neighbors = getTriangleNeighbors(x, y);
        for (int[] neighbor : neighbors) {
            int nx = neighbor[0];
            int ny = neighbor[1];
            if (isValidCoordinate(nx, ny) && gridSeals[ny][nx] != null) {
                updateSealConnections(nx, ny, gridSeals[ny][nx]);
            }
        }
    }
    private void initializeSealMap() {
        // 初始化灵印数据 - 这里只是示例，您需要根据实际情况填充
    		sealMap.put("四象",sixiangname);
    		sealMap.put("五行",jichuelename);
    		sealMap.put("七字",qiziname);
    		sealMap.put("七星",qixingname);
    		sealMap.put("八仙",baxianname);
    		sealMap.put("九宫",jiugongname);
    		sealMap.put("天干",tianganname);
    		sealMap.put("地支",dizhiname);
    		sealMap.put("九龙",longsonname);
    		sealMap.put("生灵",animalname);
    }
    
    private void initializeImages() {
        // 初始化三角形图像 - 这里需要您提供实际的图像文件
        try {
        	for(int i=0;i<10;i++) {
        			triiu[i]=new Image(getClass().getResourceAsStream("gridpic/gridu"+i+".png"),300*scale,260*scale,false,false);
        			triid[i]=new Image(getClass().getResourceAsStream("gridpic/gridd"+i+".png"),300*scale,260*scale,false,false);
        			
        	}
        } catch (Exception e) {
            System.out.println("图像加载失败: " + e.getMessage());
        }
    }
    
    void inicraftable() {
		// 五行循环
		hunyuancft.put("阳，阳", "空阴");
		hunyuancft.put("阴，阴", "空阳");
		hunyuancft.put("阳，金", "水");
		hunyuancft.put("阳，水", "木");
		hunyuancft.put("阳，木", "火");
		hunyuancft.put("阳，火", "土");
		hunyuancft.put("阳，土", "金");
		hunyuancft.put("阴，金", "土");
		hunyuancft.put("阴，水", "金");
		hunyuancft.put("阴，木", "水");
		hunyuancft.put("阴，火", "木");
		hunyuancft.put("阴，土", "火");
		// 天干循环
		hunyuancft.put("阳，癸", "字甲");
		hunyuancft.put("阳，甲", "字乙");
		hunyuancft.put("阳，乙", "字丙");
		hunyuancft.put("阳，丙", "字丁");
		hunyuancft.put("阳，丁", "字戊");
		hunyuancft.put("阳，戊", "字己");
		hunyuancft.put("阳，己", "字庚");
		hunyuancft.put("阳，庚", "字辛");
		hunyuancft.put("阳，辛", "字壬");
		hunyuancft.put("阳，壬", "字癸");
		hunyuancft.put("阴，乙", "字甲");
		hunyuancft.put("阴，丙", "字乙");
		hunyuancft.put("阴，丁", "字丙");
		hunyuancft.put("阴，戊", "字丁");
		hunyuancft.put("阴，己", "字戊");
		hunyuancft.put("阴，庚", "字己");
		hunyuancft.put("阴，辛", "字庚");
		hunyuancft.put("阴，壬", "字辛");
		hunyuancft.put("阴，癸", "字壬");
		hunyuancft.put("阴，甲", "字癸");
		// 生肖循环
		hunyuancft.put("阳，猪", "空鼠");
		hunyuancft.put("阳，鼠", "空牛");
		hunyuancft.put("阳，牛", "空虎");
		hunyuancft.put("阳，虎", "空兔");
		hunyuancft.put("阳，兔", "空龙");
		hunyuancft.put("阳，龙", "空蛇");
		hunyuancft.put("阳，蛇", "空马");
		hunyuancft.put("阳，马", "空羊");
		hunyuancft.put("阳，羊", "空猴");
		hunyuancft.put("阳，猴", "空鸡");
		hunyuancft.put("阳，鸡", "空狗");
		hunyuancft.put("阳，狗", "空猪");
		hunyuancft.put("阴，牛", "空鼠");
		hunyuancft.put("阴，虎", "空牛");
		hunyuancft.put("阴，兔", "空虎");
		hunyuancft.put("阴，龙", "空兔");
		hunyuancft.put("阴，蛇", "空龙");
		hunyuancft.put("阴，马", "空蛇");
		hunyuancft.put("阴，羊", "空马");
		hunyuancft.put("阴，猴", "空羊");
		hunyuancft.put("阴，鸡", "空猴");
		hunyuancft.put("阴，狗", "空鸡");
		hunyuancft.put("阴，猪", "空狗");
		hunyuancft.put("阴，鼠", "空猪");
		// 八卦循环 邵雍法
		// 乾 兑 离 震 巽 坎 艮 坤
		// 111 011 101 001 110 010 100 000 阳1阴0 由上进位到下
		// 000 001 010 011 100 101 110 111 二进制 由右进位到左
		hunyuancft.put("阳，兑", "字乾");
		hunyuancft.put("阳，离", "字兑");
		hunyuancft.put("阳，震", "字离");
		hunyuancft.put("阳，巽", "字震");
		hunyuancft.put("阳，坎", "字巽");
		hunyuancft.put("阳，艮", "字坎");
		hunyuancft.put("阳，坤", "字艮");
		hunyuancft.put("阳，乾", "字坤");
		hunyuancft.put("阴，坤", "字乾");
		hunyuancft.put("阴，乾", "字兑");
		hunyuancft.put("阴，兑", "字离");
		hunyuancft.put("阴，离", "字震");
		hunyuancft.put("阴，震", "字巽");
		hunyuancft.put("阴，巽", "字坎");
		hunyuancft.put("阴，坎", "字艮");
		hunyuancft.put("阴，艮", "字坤");

		// 八仙阳循环
		hunyuancft.put("阳，宝剑", "空葫芦");
		hunyuancft.put("阳，葫芦", "空扇子");
		hunyuancft.put("阳，扇子", "空鱼鼓");
		hunyuancft.put("阳，鱼鼓", "空花篮");
		hunyuancft.put("阳，花篮", "空竹笛");
		hunyuancft.put("阳，竹笛", "空玉板");
		hunyuancft.put("阳，玉板", "空荷花");
		hunyuancft.put("阳，荷花", "空宝剑");
		// 八仙阴循环（逆序）
		hunyuancft.put("阴，宝剑", "空荷花");
		hunyuancft.put("阴，荷花", "空玉板");
		hunyuancft.put("阴，玉板", "空竹笛");
		hunyuancft.put("阴，竹笛", "空花篮");
		hunyuancft.put("阴，花篮", "空鱼鼓");
		hunyuancft.put("阴，鱼鼓", "空扇子");
		hunyuancft.put("阴，扇子", "空葫芦");
		hunyuancft.put("阴，葫芦", "空宝剑");
		// 七星阳循环
		hunyuancft.put("阳，天枢", "星天璇");
		hunyuancft.put("阳，天璇", "星天玑");
		hunyuancft.put("阳，天玑", "星天权");
		hunyuancft.put("阳，天权", "星玉衡");
		hunyuancft.put("阳，玉衡", "星开阳");
		hunyuancft.put("阳，开阳", "星摇光");
		hunyuancft.put("阳，摇光", "星天枢");
		// 七星阴循环（逆序）
		hunyuancft.put("阴，天枢", "星摇光");
		hunyuancft.put("阴，摇光", "星开阳");
		hunyuancft.put("阴，开阳", "星玉衡");
		hunyuancft.put("阴，玉衡", "星天权");
		hunyuancft.put("阴，天权", "星天玑");
		hunyuancft.put("阴，天玑", "星天璇");
		hunyuancft.put("阴，天璇", "星天枢");
		// 四象阳循环（含黄极，五行相生）
		hunyuancft.put("阳，青龙", "空朱雀");
		hunyuancft.put("阳，朱雀", "空黄极");
		hunyuancft.put("阳，黄极", "空白虎");
		hunyuancft.put("阳，白虎", "空玄武");
		hunyuancft.put("阳，玄武", "空青龙");
		// 四象阴循环（含黄极，逆五行相生）
		hunyuancft.put("阴，青龙", "空玄武");
		hunyuancft.put("阴，玄武", "空白虎");
		hunyuancft.put("阴，白虎", "空黄极");
		hunyuancft.put("阴，黄极", "空朱雀");
		hunyuancft.put("阴，朱雀", "空青龙");

		// 有序凝炼 注意复杂元素放在第二位用来阻断简单的自发合成
		// 基础字符元素
		wuzangcft.put("水，木", "字文");
		wuzangcft.put("金，金", "字武");
		wuzangcft.put("水，火", "字灾");
		wuzangcft.put("金，木", "字器");
		wuzangcft.put("火，土", "字烟");
		wuzangcft.put("土，土", "字山");
		wuzangcft.put("水，水", "字海");
		// 中宫与十天干
		wuzangcft.put("阴，阳", "字中");
		wuzangcft.put("阳，木", "字甲");
		wuzangcft.put("阴，木", "字乙");
		wuzangcft.put("阳，火", "字丙");
		wuzangcft.put("阴，火", "字丁");
		wuzangcft.put("阳，土", "字戊");
		wuzangcft.put("阴，土", "字己");
		wuzangcft.put("阳，金", "字庚");
		wuzangcft.put("阴，金", "字辛");
		wuzangcft.put("阳，水", "字壬");
		wuzangcft.put("阴，水", "字癸");
		// 十二生肖
		wuzangcft.put("水，水，水", "空鼠");
		wuzangcft.put("土，水，金", "空牛");
		wuzangcft.put("木，火，土", "空虎");
		wuzangcft.put("木，木，木", "空兔");
		wuzangcft.put("土，木，水", "空龙");
		wuzangcft.put("火，金，土", "空蛇");
		wuzangcft.put("火，火，土", "空马");
		wuzangcft.put("土，火，木", "空羊");
		wuzangcft.put("金，水，土", "空猴");
		wuzangcft.put("金，金，金", "空鸡");
		wuzangcft.put("土，金，火", "空狗");
		wuzangcft.put("水，水，木", "空猪");
		// 八卦
		wuzangcft.put("阳，阳，阳", "字乾");
		wuzangcft.put("阳，阳，阴", "字巽");
		wuzangcft.put("阴，阳，阴", "字坎");
		wuzangcft.put("阳，阴，阴", "字艮");
		wuzangcft.put("阴，阴，阴", "字坤");
		wuzangcft.put("阴，阴，阳", "字震");
		wuzangcft.put("阳，阴，阳", "字离");
		wuzangcft.put("阴，阳，阳", "字兑");
		wuzangcft.put("阳，阴", "字中");
		wuzangcft.put("阴，阳", "字中");
		// 八仙
		wuzangcft.put("阳，乾，金", "空宝剑");
		wuzangcft.put("阳，兑，金", "空葫芦");
		wuzangcft.put("阴，离，火", "空扇子");
		wuzangcft.put("阴，震，木", "空鱼鼓");
		wuzangcft.put("阳，巽，木", "空花篮");
		wuzangcft.put("阳，坎，水", "空竹笛");
		wuzangcft.put("阴，艮，土", "空玉板");
		wuzangcft.put("阴，坤，土", "空荷花");
		// 七星
		wuzangcft.put("阳，坎，土，艮，木", "星天枢");
		wuzangcft.put("阳，坎，土，巽，土", "星天璇");
		wuzangcft.put("阳，坎，土，乾，木", "星天玑");
		wuzangcft.put("阳，坎，土，离，水", "星天权");
		wuzangcft.put("阳，坎，土，震，火", "星玉衡");
		wuzangcft.put("阳，坎，土，兑，金", "星开阳");
		wuzangcft.put("阳，坎，土，坎，金", "星摇光");
		// 四象
		wuzangcft.put("金，金，金，金，金", "空白虎");
		wuzangcft.put("木，木，木，木，木", "空青龙");
		wuzangcft.put("水，水，水，水，水", "空玄武");
		wuzangcft.put("火，火，火，火，火", "空朱雀");
		wuzangcft.put("土，土，土，土，土", "空黄极");
		// 常见动物
		wuzangcft.put("木，火，土，火", "空豺");
		wuzangcft.put("火，金，木，土", "空鸟");
		wuzangcft.put("金，水，土，土", "空蟾");
		wuzangcft.put("木，火，土，金", "空狮");
		wuzangcft.put("火，水，火，金", "空龟");
		wuzangcft.put("水，水，水，水", "空鱼");
		// 异兽
		wuzangcft.put("龙，牛", "空囚牛");
		wuzangcft.put("龙，豺", "空睚眦");
		wuzangcft.put("龙，鸟", "空嘲风");
		wuzangcft.put("龙，蟾", "空蒲牢");
		wuzangcft.put("龙，狮", "空狻猊");
		wuzangcft.put("龙，龟", "空霸下");
		wuzangcft.put("龙，虎", "空狴犴");
		wuzangcft.put("龙，青龙", "空负屃");
		wuzangcft.put("龙，鱼", "空螭吻");
        
        wuzangcft.forEach((k,v)->{
        	wuzangcftni.put(v.substring(1), k);
        });
        //创建合成表查找
        makecraftfind();
        computeMinDepths();
        addi18n();
    }
    void addi18n() {
    	// 五行 + 阴阳
    	localizedSealNames.put("阴", new String[]{"阴","陰","陰","음","Yin","Yin","Yin","Yin","Yin","Инь"});
    	localizedSealNames.put("阳", new String[]{"阳","陽","陽","양","Yang","Yang","Yang","Yang","Yang","Ян"});
    	localizedSealNames.put("金", new String[]{"金","金","金","금","Metall","Metal","Metal","Métal","Metal","Металл"});
    	localizedSealNames.put("木", new String[]{"木","木","木","목","Holz","Wood","Madera","Bois","Madeira","Дерево"});
    	localizedSealNames.put("水", new String[]{"水","水","水","수","Wasser","Water","Agua","Eau","Água","Вода"});
    	localizedSealNames.put("火", new String[]{"火","火","火","화","Feuer","Fire","Fuego","Feu","Fogo","Огонь"});
    	localizedSealNames.put("土", new String[]{"土","土","土","토","Erde","Earth","Tierra","Terre","Terra","Земля"});
    	// 七字
    	localizedSealNames.put("文", new String[]{"文","文","文","문","Schrift","Script","Escritura","Écriture","Escrita","Письменность"});
    	localizedSealNames.put("武", new String[]{"武","武","武","무","Kampf","Combat","Combate","Combat","Combate","Бой"});
    	localizedSealNames.put("灾", new String[]{"灾","災","災","재","Unheil","Disaster","Desastre","Fléau","Desastre","Бедствие"});
    	localizedSealNames.put("器", new String[]{"器","器","器","기","Werkzeug","Tool","Herramienta","Outil","Ferramenta","Инструмент"});
    	localizedSealNames.put("烟", new String[]{"烟","煙","煙","연","Rauch","Smoke","Humo","Fumée","Fumaça","Дым"});
    	localizedSealNames.put("山", new String[]{"山","山","山","산","Berg","Mountain","Montaña","Montagne","Montanha","Гора"});
    	localizedSealNames.put("海", new String[]{"海","海","海","해","Meer","Sea","Mar","Mer","Mar","Море"});
    	// 十二地支（生肖）
    	localizedSealNames.put("鼠", new String[]{"鼠","鼠","鼠","쥐","Ratte","Rat","Rata","Rat","Rato","Крыса"});
    	localizedSealNames.put("牛", new String[]{"牛","牛","牛","소","Rind","Ox","Buey","Bœuf","Boi","Бык"});
    	localizedSealNames.put("虎", new String[]{"虎","虎","虎","호랑이","Tiger","Tiger","Tigre","Tigre","Tigre","Тигр"});
    	localizedSealNames.put("兔", new String[]{"兔","兔","兎","토끼","Hase","Rabbit","Conejo","Lapin","Coelho","Кролик"});
    	localizedSealNames.put("龙", new String[]{"龙","龍","竜","용","Drache","Dragon","Dragón","Dragon","Dragão","Дракон"});
    	localizedSealNames.put("蛇", new String[]{"蛇","蛇","蛇","뱀","Schlange","Snake","Serpiente","Serpent","Serpente","Змея"});
    	localizedSealNames.put("马", new String[]{"马","馬","馬","말","Pferd","Horse","Caballo","Cheval","Cavalo","Лошадь"});
    	localizedSealNames.put("羊", new String[]{"羊","羊","羊","양","Ziege","Goat","Cabra","Chèvre","Cabra","Коза"});
    	localizedSealNames.put("猴", new String[]{"猴","猴","猿","원숭이","Affe","Monkey","Mono","Singe","Macaco","Обезьяна"});
    	localizedSealNames.put("鸡", new String[]{"鸡","雞","鶏","닭","Hahn","Rooster","Gallo","Coq","Galo","Петух"});
    	localizedSealNames.put("狗", new String[]{"狗","狗","犬","개","Hund","Dog","Perro","Chien","Cão","Собака"});
    	localizedSealNames.put("猪", new String[]{"猪","猪","猪","돼지","Schwein","Pig","Cerdo","Cochon","Porco","Свинья"});
    	// 十天干
    	localizedSealNames.put("甲", new String[]{"甲","甲","甲","갑","Jia","Jia","Jia","Jia","Jia","Цзя"});
    	localizedSealNames.put("乙", new String[]{"乙","乙","乙","을","Yi","Yi","Yi","Yi","Yi","И"});
    	localizedSealNames.put("丙", new String[]{"丙","丙","丙","병","Bing","Bing","Bing","Bing","Bing","Бин"});
    	localizedSealNames.put("丁", new String[]{"丁","丁","丁","정","Ding","Ding","Ding","Ding","Ding","Дин"});
    	localizedSealNames.put("戊", new String[]{"戊","戊","戊","무","Wu","Wu","Wu","Wu","Wu","У"});
    	localizedSealNames.put("己", new String[]{"己","己","己","기","Ji","Ji","Ji","Ji","Ji","Цзи"});
    	localizedSealNames.put("庚", new String[]{"庚","庚","庚","경","Geng","Geng","Geng","Geng","Geng","Гэн"});
    	localizedSealNames.put("辛", new String[]{"辛","辛","辛","신","Xin","Xin","Xin","Xin","Xin","Синь"});
    	localizedSealNames.put("壬", new String[]{"壬","壬","壬","임","Ren","Ren","Ren","Ren","Ren","Жэнь"});
    	localizedSealNames.put("癸", new String[]{"癸","癸","癸","계","Gui","Gui","Gui","Gui","Gui","Гуй"});
    	// 九宫八卦
    	localizedSealNames.put("乾", new String[]{"乾","乾","乾","건","Qian","Qian","Qian","Qian","Qian","Цянь"});
    	localizedSealNames.put("兑", new String[]{"兑","兌","兌","태","Dui","Dui","Dui","Dui","Dui","Дуй"});
    	localizedSealNames.put("离", new String[]{"离","離","離","리","Li","Li","Li","Li","Li","Ли"});
    	localizedSealNames.put("震", new String[]{"震","震","震","진","Zhen","Zhen","Zhen","Zhen","Zhen","Чжэнь"});
    	localizedSealNames.put("巽", new String[]{"巽","巽","巽","손","Xun","Xun","Xun","Xun","Xun","Сюнь"});
    	localizedSealNames.put("坎", new String[]{"坎","坎","坎","감","Kan","Kan","Kan","Kan","Kan","Кань"});
    	localizedSealNames.put("艮", new String[]{"艮","艮","艮","간","Gen","Gen","Gen","Gen","Gen","Гэнь"});
    	localizedSealNames.put("坤", new String[]{"坤","坤","坤","곤","Kun","Kun","Kun","Kun","Kun","Кунь"});
    	localizedSealNames.put("中", new String[]{"中","中","中","중","Mitte","Center","Centro","Centre","Centro","Центр"});
    	// 八仙法器
    	localizedSealNames.put("宝剑", new String[]{"宝剑","寶劍","剣","보검","Schwert","Sword","Espada","Épée","Espada","Меч"});
    	localizedSealNames.put("葫芦", new String[]{"葫芦","葫蘆","瓢","호로","Kürbisflasche","Gourd","Calabaza","Calebasse","Cabacinha","Тыква-горлянка"});
    	localizedSealNames.put("扇子", new String[]{"扇子","扇子","扇","부채","Fächer","Fan","Abanico","Éventail","Ventarola","Веер"});
    	localizedSealNames.put("鱼鼓", new String[]{"鱼鼓","魚鼓","魚鼓","어고","Fischtrommel","Fish Drum","Tambor de pez","Tambour à poisson","Tambor de peixe","Рыбный барабан"});
    	localizedSealNames.put("花篮", new String[]{"花篮","花籃","花籠","화란","Blumenkorb","Flower Basket","Cesta de flores","Corbeille à fleurs","Cesto de flores","Цветочная корзина"});
    	localizedSealNames.put("竹笛", new String[]{"竹笛","竹笛","竹笛","죽적","Bambusflöte","Bamboo Flute","Flauta de bambú","Flûte en bambou","Flauta de bambu","Бамбуковая флейта"});
    	localizedSealNames.put("玉板", new String[]{"玉板","玉板","玉板","옥판","Jadeplatte","Jade Slab","Placa de jade","Plaque de jade","Placa de jade","Нефритовая плита"});
    	localizedSealNames.put("荷花", new String[]{"荷花","荷花","蓮の花","하화","Lotosblume","Lotus","Loto","Lotus","Lótus","Лотос"});
    	// 七星
    	localizedSealNames.put("天枢", new String[]{"天枢","天樞","天枢","천추","Dubhe","Dubhe","Dubhe","Dubhe","Dubhe","Дубхе"});
    	localizedSealNames.put("天璇", new String[]{"天璇","天璇","天璇","천선","Merak","Merak","Merak","Merak","Merak","Мерак"});
    	localizedSealNames.put("天玑", new String[]{"天玑","天璣","天璣","천기","Phecda","Phecda","Phecda","Phecda","Phecda","Фекда"});
    	localizedSealNames.put("天权", new String[]{"天权","天權","天権","천권","Megrez","Megrez","Megrez","Megrez","Megrez","Мегрец"});
    	localizedSealNames.put("玉衡", new String[]{"玉衡","玉衡","玉衡","옥형","Alioth","Alioth","Alioth","Alioth","Alioth","Алиот"});
    	localizedSealNames.put("开阳", new String[]{"开阳","開陽","開陽","개양","Mizar","Mizar","Mizar","Mizar","Mizar","Мицар"});
    	localizedSealNames.put("摇光", new String[]{"摇光","搖光","揺光","요광","Alkaid","Alkaid","Alkaid","Alkaid","Alkaid","Алькаид"});
    	// 四象 + 黄极
    	localizedSealNames.put("青龙", new String[]{"青龙","青龍","青竜","청룡","Azurblauer Drache","Azure Dragon","Dragón Azur","Dragon d'azur","Dragão Azul","Лазурный дракон"});
    	localizedSealNames.put("白虎", new String[]{"白虎","白虎","白虎","백호","Weißer Tiger","White Tiger","Tigre Blanco","Tigre Blanc","Tigre Branco","Белый тигр"});
    	localizedSealNames.put("朱雀", new String[]{"朱雀","朱雀","朱雀","주작","Roter Vogel","Vermilion Bird","Ave Bermellón","Oiseau Vermillon","Pássaro Vermelho","Алый птица"});
    	localizedSealNames.put("玄武", new String[]{"玄武","玄武","玄武","현무","Schwarze Schildkröte","Black Tortoise","Tortuga Negra","Tortue Noire","Tartaruga Negra","Чёрная черепаха"});
    	localizedSealNames.put("黄极", new String[]{"黄极","黃極","黄極","황극","Gelber Pol","Yellow Pole","Polo Amarillo","Pôle Jaune","Polo Amarelo","Жёлтый полюс"});
    	// 九龙子
    	localizedSealNames.put("囚牛", new String[]{"囚牛","囚牛","囚牛","수우","Qiuniu","Qiuniu","Qiuniu","Qiuniu","Qiuniu","Цюню"});
    	localizedSealNames.put("睚眦", new String[]{"睚眦","睚眥","睚眦","애자","Yazhi","Yazhi","Yazhi","Yazhi","Yazhi","Ячжи"});
    	localizedSealNames.put("嘲风", new String[]{"嘲风","嘲風","嘲風","조풍","Chaofeng","Chaofeng","Chaofeng","Chaofeng","Chaofeng","Чаофэн"});
    	localizedSealNames.put("蒲牢", new String[]{"蒲牢","蒲牢","蒲牢","포뢰","Pulao","Pulao","Pulao","Pulao","Pulao","Пулао"});
    	localizedSealNames.put("狻猊", new String[]{"狻猊","狻猊","狻猊","산예","Suanni","Suanni","Suanni","Suanni","Suanni","Суаньни"});
    	localizedSealNames.put("霸下", new String[]{"霸下","霸下","霸下","파하","Baxia","Baxia","Baxia","Baxia","Baxia","Бася"});
    	localizedSealNames.put("狴犴", new String[]{"狴犴","狴犴","狴犴","비안","Bian","Bian","Bian","Bian","Bian","Бянь"});
    	localizedSealNames.put("负屃", new String[]{"负屃","負屓","負屓","부희","Fuxi","Fuxi","Fuxi","Fuxi","Fuxi","Фуси"});
    	localizedSealNames.put("螭吻", new String[]{"螭吻","螭吻","螭吻","리문","Chiwen","Chiwen","Chiwen","Chiwen","Chiwen","Чивэнь"});
    	// 生灵动物
    	localizedSealNames.put("豺", new String[]{"豺","豺","豺","시랑","Schakal","Jackal","Chacal","Chacal","Chacal","Шакал"});
    	localizedSealNames.put("鸟", new String[]{"鸟","鳥","鳥","조","Vogel","Bird","Pájaro","Oiseau","Pássaro","Птица"});
    	localizedSealNames.put("蟾", new String[]{"蟾","蟾","蟾","섬","Kröte","Toad","Sapo","Crapaud","Sapo","Жаба"});
    	localizedSealNames.put("狮", new String[]{"狮","獅","獅子","사자","Löwe","Lion","León","Lion","Leão","Лев"});
    	localizedSealNames.put("龟", new String[]{"龟","龜","亀","귀","Schildkröte","Turtle","Tortuga","Tortue","Tartaruga","Черепаха"});
    	localizedSealNames.put("鱼", new String[]{"鱼","魚","魚","어","Fisch","Fish","Pez","Poisson","Peixe","Рыба"});
    	localizedSealNames.put("鹿", new String[]{"鹿","鹿","鹿","록","Hirsch","Deer","Ciervo","Cerf","Cervo","Олень"});
    	localizedSealNames.put("鲶", new String[]{"鲶","鯰","鯰","념","Wels","Catfish","Bagre","Poisson-chat","Peixe-gato","Сом"});
    	localizedSealNames.put("虾", new String[]{"虾","蝦","蝦","하","Garnele","Shrimp","Camarón","Crevette","Camarão","Креветка"});
    	localizedSealNames.put("鹰", new String[]{"鹰","鷹","鷹","응","Adler","Eagle","Águila","Aigle","Águia","Орёл"});
    }
    private void showSynthesisPathCanvas(int gridX, int gridY) {
        String sealName = gridSeals[gridY][gridX];
        if (sealName == null) return;
        showSynthesisPathCanvas(sealName);
    }
 // 显示当前灵印的最短合成链路图
    private void showSynthesisPathCanvas(String sealName) {
    	try {
    		stack.getChildren().remove(nowcanvas);
    	}catch(Exception e1) {}
    	List<NodeClickArea> clickAreas = new ArrayList<>();
        SynTreeNode root = buildSynTree(sealName, true);
        Map<Integer, List<SynTreeNode>> layers = new LinkedHashMap<>();
        buildLayers(root, 0, layers);
        int maxDepth = layers.size() - 1;

        double canvasWidth = 800 * scale;
        double canvasHeight = 700 * scale;
        double nodeSize = 80 * scale;
        double layerHeight = 120 * scale;
        double margin = 50 * scale;
        double stemLength = 22 * scale;
        double cycleOffset = 130 * scale; // 循环子节点与父节点的垂直距离

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        nowcanvas=canvas;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(30, 20, 10, 0.6));
        gc.fillRoundRect(0, 0, canvasWidth, canvasHeight, 30 * scale, 30 * scale);
        drawWenshanBorder(gc, 0, 0, canvasWidth, canvasHeight, 5 * scale, scale);
        // 正常节点坐标
        Map<SynTreeNode, Point2D> nodePositions = new HashMap<>();
        double usedHeight = maxDepth * layerHeight + nodeSize;
        double totalUsed = usedHeight + margin;
        double offsetY = Math.max(0, (canvasHeight - totalUsed) / 2)+layerHeight/2;

        for (int depth = 0; depth <= maxDepth; depth++) {
            List<SynTreeNode> layerNodes = layers.get(depth);
            int count = layerNodes.size();
            double startX = (canvasWidth - (count - 1) * (nodeSize + 40 * scale)) / 2;
            double y = margin + offsetY + depth * layerHeight;
            for (int i = 0; i < count; i++) {
                SynTreeNode node = layerNodes.get(i);
                double x = startX + i * (nodeSize + 40 * scale);
                nodePositions.put(node, new Point2D(x, y));
            }
        }

     // 循环子节点坐标（仅根节点有，位于其上方，阴在左，阳在右）
        Map<SynTreeNode, Point2D> cyclePositions = new HashMap<>();
        if (!root.children.isEmpty()) {
            List<SynTreeNode> cycleChildren = new ArrayList<>();
            for (SynTreeNode child : root.children) {
                if (child.isCycleNode) cycleChildren.add(child);
            }

            if (!cycleChildren.isEmpty()) {
                Point2D rootPos = nodePositions.get(root);
                double y = rootPos.getY() - cycleOffset;

                List<SynTreeNode> yinNodes = new ArrayList<>();
                List<SynTreeNode> yangNodes = new ArrayList<>();
                for (SynTreeNode child : cycleChildren) {
                    if ("阴".equals(child.cycleMate)) yinNodes.add(child);
                    else if ("阳".equals(child.cycleMate)) yangNodes.add(child);
                }

                double hSpacing = nodeSize + 160 * scale;
                // 阴循环在左侧
                if (!yinNodes.isEmpty()) {
                    double startX = rootPos.getX() - hSpacing / 2 - (yinNodes.size() - 1) * hSpacing / 2;
                    for (int i = 0; i < yinNodes.size(); i++) {
                        cyclePositions.put(yinNodes.get(i), new Point2D(startX + i * hSpacing, y));
                    }
                }
                // 阳循环在右侧
                if (!yangNodes.isEmpty()) {
                    double startX = rootPos.getX() + hSpacing / 2 - (yangNodes.size() - 1) * hSpacing / 2;
                    for (int i = 0; i < yangNodes.size(); i++) {
                        cyclePositions.put(yangNodes.get(i), new Point2D(startX + i * hSpacing, y));
                    }
                }
            }
        }

        // ---------- 绘制普通合成连线 ----------
        Map<SynTreeNode, Point2D> convergencePoints = new HashMap<>();
        for (int depth = 0; depth < maxDepth; depth++) {
            List<SynTreeNode> parentLayer = layers.get(depth);
            for (SynTreeNode parent : parentLayer) {
                if (parent.synthesisType == null || parent.children.isEmpty()) continue;
                Point2D parentPos = nodePositions.get(parent);
                if (parentPos == null) continue;

                double sumX = 0, sumY = 0;
                int count = 0;
                for (SynTreeNode child : parent.children) {
                    if (child.isCycleNode) continue;   // 跳过循环子节点
                    Point2D childPos = nodePositions.get(child);
                    if (childPos != null) {
                        sumX += childPos.getX();
                        sumY += childPos.getY();
                        count++;
                    }
                }
                if (count == 0) continue;
                double convergeX = sumX / count;
                double convergeY = sumY / count - 50 * scale;
                convergencePoints.put(parent, new Point2D(convergeX, convergeY));
            }
        }

        gc.setStroke(Color.rgb(200, 200, 200, 0.7));
        gc.setLineWidth(3 * scale);

        for (int depth = 0; depth < maxDepth; depth++) {
            List<SynTreeNode> parentLayer = layers.get(depth);
            for (SynTreeNode parent : parentLayer) {
                if (parent.synthesisType == null) {
                    Point2D parentPos = nodePositions.get(parent);
                    for (SynTreeNode child : parent.children) {
                        if (child.isCycleNode) continue;
                        Point2D childPos = nodePositions.get(child);
                        if (parentPos != null && childPos != null) {
                            Point2D stemEnd = new Point2D(childPos.getX(), childPos.getY() - stemLength);
                            gc.strokeLine(childPos.getX(), childPos.getY(), stemEnd.getX(), stemEnd.getY());
                            gc.strokeLine(stemEnd.getX(), stemEnd.getY(), parentPos.getX(), parentPos.getY());
                        }
                    }
                } else {
                    Point2D convergePoint = convergencePoints.get(parent);
                    Point2D parentPos = nodePositions.get(parent);
                    if (convergePoint == null || parentPos == null) continue;
                    for (SynTreeNode child : parent.children) {
                        if (child.isCycleNode) continue;
                        Point2D childPos = nodePositions.get(child);
                        if (childPos != null) {
                            Point2D stemEnd = new Point2D(childPos.getX(), childPos.getY() - stemLength);
                            gc.strokeLine(childPos.getX(), childPos.getY(), stemEnd.getX(), stemEnd.getY());
                            gc.strokeLine(stemEnd.getX(), stemEnd.getY(), convergePoint.getX(), convergePoint.getY());
                        }
                    }
                    gc.strokeLine(convergePoint.getX(), convergePoint.getY(),
                                  parentPos.getX(), parentPos.getY());
                }
            }
        }

        // 绘制普通合成多边形（五边形/三角形）
        for (Map.Entry<SynTreeNode, Point2D> entry : convergencePoints.entrySet()) {
            drawPolygonAt(gc, entry.getValue().getX(), entry.getValue().getY(), entry.getKey().synthesisType);
        }

        // ---------- 绘制循环子节点连线（从上向下） ----------
        for (Map.Entry<SynTreeNode, Point2D> entry : cyclePositions.entrySet()) {
            SynTreeNode child = entry.getKey();
            Point2D childPos = entry.getValue();
            SynTreeNode parent = null;
            for (Map.Entry<SynTreeNode, Point2D> pEntry : nodePositions.entrySet()) {
                if (pEntry.getKey().children.contains(child)) {
                    parent = pEntry.getKey();
                    break;
                }
            }
            if (parent == null) continue;
            Point2D parentPos = nodePositions.get(parent);
            
            gc.setStroke(Color.rgb(200, 200, 200, 0.7));
            gc.setLineWidth(3 * scale);
            double midY = (childPos.getY() + parentPos.getY()) / 2;
            // 子节点向下竖线
            gc.strokeLine(childPos.getX(), childPos.getY(), childPos.getX(), midY);
            // 中点连到父节点
            gc.strokeLine(childPos.getX(), midY, parentPos.getX(), parentPos.getY());
            // 在中点绘制小三角形（混元专用）
            drawPolygonAt(gc, childPos.getX(), midY, "hunyuan");
        }

        // ---------- 绘制所有节点（正常节点 + 循环节点） ----------
        List<SynTreeNode> allNodes = new ArrayList<>(nodePositions.keySet());
        allNodes.addAll(cyclePositions.keySet());
        for (SynTreeNode node : allNodes) {
            Point2D pos = nodePositions.containsKey(node) ? nodePositions.get(node) : cyclePositions.get(node);
            double x = pos.getX() - nodeSize / 2;
            double y = pos.getY() - nodeSize / 2;

            ImageView sealView = createSealGraphic(node.element, nodeSize);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image sealImg = sealView.snapshot(params, null);
            gc.drawImage(sealImg, x, y);
            // 新增：记录点击区域
            clickAreas.add(new NodeClickArea(pos.getX(), pos.getY(), nodeSize / 2, node.element));
            gc.setFill(Color.WHITE);
            gc.setFont(f01);
            String localizedName = getLocalizedSealName(node.element, language);
            gc.fillText(localizedName, x + nodeSize/2 + 15*scale, y + nodeSize + 5*scale);

            // 如果是循环节点，额外显示阴阳标签
            if (node.isCycleNode && node.cycleMate != null) {
                gc.setFill(Color.rgb(255, 200, 100));
                gc.setFont(f01);
//                if(node.cycleMate.equals("阳")){
                String localizedName1 = getLocalizedSealName(node.cycleMate, language);
                	gc.fillText(localizedName1, x + nodeSize + 5 * scale, y + nodeSize / 2);
//            	}
//                else {
//                	gc.fillText(node.cycleMate, x - nodeSize/2 , y + nodeSize / 2);
//                }
            }
        }

        canvas.setLayoutX(400 * scale);
        canvas.setLayoutY(50 * scale);
        canvas.setEffect(dsb);
        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                stack.getChildren().remove(canvas);
            } else if (e.getButton() == MouseButton.PRIMARY) {
                for (NodeClickArea area : clickAreas) {
                    if (area.contains(e.getX(), e.getY())) {
                        stack.getChildren().remove(canvas);          // 关闭当前画布
                        showSynthesisPathCanvas(area.elementName);   // 打开新元素的合成图
                        return;
                    }
                }
            }
        });
        stack.getChildren().add(canvas);
    }
    private void drawWenshanBorder(GraphicsContext gc, double x, double y, double w, double h, double arc, double scale) {
        gc.save();
        gc.setStroke(Color.rgb(230, 210, 150, 0.5)); // 古铜色半透明
        gc.setLineWidth(2 * scale);
        double inset = 8 * scale;        // 内缩距离
        double step = 20 * scale;        // 单个山形宽度
        double height = 12 * scale;      // 山形高度

        // 绘制上边山形
        double startX = x + arc/2 + inset;
        double endX = x + w - arc/2 - inset;
        for (double px = startX; px < endX; px += step) {
            double x1 = px;
            double x2 = Math.min(px + step/2, endX);
            double x3 = Math.min(px + step, endX);
            double yBase = y + inset;
            gc.strokeLine(x1, yBase, x2, yBase - height);
            gc.strokeLine(x2, yBase - height, x3, yBase);
        }
        // 下边山形（倒置）
        double yBaseBottom = y + h - inset;
        for (double px = startX; px < endX; px += step) {
            double x1 = px;
            double x2 = Math.min(px + step/2, endX);
            double x3 = Math.min(px + step, endX);
            gc.strokeLine(x1, yBaseBottom, x2, yBaseBottom + height);
            gc.strokeLine(x2, yBaseBottom + height, x3, yBaseBottom);
        }
        // 左边山形（纵向）
        double startY = y + arc/2 + inset;
        double endY = y + h - arc/2 - inset;
        for (double py = startY; py < endY; py += step) {
            double y1 = py;
            double y2 = Math.min(py + step/2, endY);
            double y3 = Math.min(py + step, endY);
            double xBase = x + inset;
            gc.strokeLine(xBase, y1, xBase - height, y2);
            gc.strokeLine(xBase - height, y2, xBase, y3);
        }
        // 右边山形（纵向，方向相反）
        double xBaseRight = x + w - inset;
        for (double py = startY; py < endY; py += step) {
            double y1 = py;
            double y2 = Math.min(py + step/2, endY);
            double y3 = Math.min(py + step, endY);
            gc.strokeLine(xBaseRight, y1, xBaseRight + height, y2);
            gc.strokeLine(xBaseRight + height, y2, xBaseRight, y3);
        }
        gc.restore();
    }
    // 辅助：将树按层分组
    private void buildLayers(SynTreeNode node, int depth, Map<Integer, List<SynTreeNode>> layers) {
        layers.computeIfAbsent(depth, k -> new ArrayList<>()).add(node);
        for (SynTreeNode child : node.children) {
            if (!child.isCycleNode) {   // 忽略循环子节点
                buildLayers(child, depth + 1, layers);
            }
        }
    }

    // 绘制汇入多边形（五边形或三角形）
    private void drawPolygonAt(GraphicsContext gc, double centerX, double centerY, String type) {
        int sides = type.equals("wuzang") ? 5 : 3;
        double radius = 12 * scale;
        double[] xPoints = new double[sides];
        double[] yPoints = new double[sides];
        double startAngle = -Math.PI / 2; // 顶点朝上
        for (int i = 0; i < sides; i++) {
            double angle = startAngle + 2 * Math.PI * i / sides;
            xPoints[i] = centerX + radius * Math.cos(angle);
            yPoints[i] = centerY + radius * Math.sin(angle);
        }
        // 填充颜色区分
        gc.setFill(type.equals("wuzang") ? Color.rgb(250, 150, 100, 0.8) : Color.rgb(100, 100, 100, 0.8));
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1.5 * scale);
        gc.fillPolygon(xPoints, yPoints, sides);
        gc.strokePolygon(xPoints, yPoints, sides);
    }
    private SynTreeNode buildSynTree(String element, boolean isRoot) {
        SynTreeNode root = new SynTreeNode(element, null);
        if (basicElements.contains(element)) {
            if (isRoot) addCycleChildren(root); // 仅根节点添加循环
            return root;
        }
        String[] materials = bestRecipeMaterials.get(element);
        String type = bestRecipeType.get(element);
        if (materials != null && type != null) {
            root.synthesisType = type;
            for (String mat : materials) {
                root.children.add(buildSynTree(mat, false)); // 子树不添加循环
            }
        }
        if (isRoot) addCycleChildren(root);
        return root;
    }

    private void addCycleChildren(SynTreeNode node) {
        String element = node.element;
        if (element == null) return;

        // 用于去重
        Set<String> addedCycleElements = new HashSet<>();

        for (Map.Entry<String, String> entry : hunyuancft.entrySet()) {
            String value = entry.getValue();
            String pureProduct = value.length() > 1 ? value.substring(1) : value;
            if (!pureProduct.equals(element)) continue;

            String[] mats = entry.getKey().split("，");
            // 特殊循环：两个相同的阴/阳 (如 阳，阳 -> 空阴)
            if (mats.length == 2 && mats[0].equals(mats[1]) && (mats[0].equals("阴") || mats[0].equals("阳"))) {
                String mateElement = mats[0]; // 阴或阳
                if (!addedCycleElements.contains(mateElement)) {
                    SynTreeNode cycleChild = new SynTreeNode(mateElement, "hunyuan");
                    cycleChild.isCycleNode = true;
                    cycleChild.cycleMate = mateElement; // 标记为“阳”或“阴”，显示时可注明双阳/双阴
                    node.children.add(cycleChild);
                    addedCycleElements.add(mateElement);
                }
                continue;
            }

            // 标准混元配方：阴/阳 + 另一元素
            String yinYang = null;
            String other = null;
            for (String mat : mats) {
                if (mat.equals("阴") || mat.equals("阳")) {
                    yinYang = mat;
                } else {
                    other = mat;
                }
            }
            if (yinYang != null && other != null) {
                // 避免重复添加同一元素（例如同一产物可能有多个阴/阳组合？）
                if (!addedCycleElements.contains(other)) {
                    SynTreeNode cycleChild = new SynTreeNode(other, "hunyuan");
                    cycleChild.isCycleNode = true;
                    cycleChild.cycleMate = yinYang;
                    node.children.add(cycleChild);
                    addedCycleElements.add(other);
                }
            }
        }
    }
    
 // 在 inicraftable() 中调用，位于 makecraftfind() 之后
    private void computeMinDepths() {
        minDepth.clear();
        bestRecipeMaterials.clear();
        bestRecipeType.clear();
        for (String basic : basicElements) {
            minDepth.put(basic, 0);
        }
        for (String product : findmaterial.keySet()) {
            if (!basicElements.contains(product)) {
                getMinDepth(product);
            }
        }
    }

    private int getMinDepth(String element) {
        return getMinDepthHelper(element, new HashSet<>());
    }

    private int getMinDepthHelper(String element, Set<String> visiting) {
        if (minDepth.containsKey(element)) {
            return minDepth.get(element);
        }
        if (visiting.contains(element)) {
            return Integer.MAX_VALUE; // 环，不可达
        }
        visiting.add(element);

        if (basicElements.contains(element)) {
            visiting.remove(element);
            minDepth.put(element, 0);
            return 0;
        }

        List<Recipe> recipes = getAllRecipesFor(element);
        if (recipes.isEmpty()) {
            visiting.remove(element);
            minDepth.put(element, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        int bestDepth = Integer.MAX_VALUE;
        String[] bestMats = null;
        String bestType = null;

        for (Recipe recipe : recipes) {
            int maxChildDepth = 0;
            boolean reachable = true;
            for (String mat : recipe.materials) {
                int d = getMinDepthHelper(mat, visiting);
                if (d == Integer.MAX_VALUE) {
                    reachable = false;
                    break;
                }
                if (d > maxChildDepth) maxChildDepth = d;
            }
            if (reachable) {
                int depth = maxChildDepth + 1;
                if (depth < bestDepth) {
                    bestDepth = depth;
                    bestMats = recipe.materials;
                    bestType = recipe.type;
                }
            }
        }

        visiting.remove(element);

        if (bestDepth == Integer.MAX_VALUE) {
            minDepth.put(element, Integer.MAX_VALUE);
        } else {
            minDepth.put(element, bestDepth);
            bestRecipeMaterials.put(element, bestMats);
            bestRecipeType.put(element, bestType);
        }
        return minDepth.get(element);
    }

    // 获取某元素的所有合成配方（同时返回合成类型）
    private List<Recipe> getAllRecipesFor(String product) {
        List<Recipe> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : wuzangcft.entrySet()) {
            String value = entry.getValue();
            String pure = value.length() > 1 ? value.substring(1) : value;
            if (pure.equals(product)) {
                list.add(new Recipe(entry.getKey().split("，"), "wuzang"));
            }
        }
        for (Map.Entry<String, String> entry : hunyuancft.entrySet()) {
            String value = entry.getValue();
            String pure = value.length() > 1 ? value.substring(1) : value;
            if (pure.equals(product)) {
                list.add(new Recipe(entry.getKey().split("，"), "hunyuan"));
            }
        }
        return list;
    }
   
    public void makecraftfind() {//将合成表变为双向查找
    	wuzangcft.forEach((k,v)->{
    		String[] mates=k.split("，");
//    		if(v.length()>1)v=v.substring(1);
    		v=v.replaceAll("空","");
    		v=v.replaceAll("字","");
    		v=v.replaceAll("星","");
    		if(!findmaterial.containsKey(v))
    			findmaterial.put(v, new HashSet<String>());
    		for(int i=0;i<mates.length;i++) {
    			if(!findcraft.containsKey(mates[i]))
    				findcraft.put(mates[i],new HashSet<String>());
    			findcraft.get(mates[i]).add(v);
    			findmaterial.get(v).add(mates[i]);
//    			if(v.equals("鱼鼓"))System.out.println(mates[i]);
    		}
    	});
    	hunyuancft.forEach((k,v)->{
    		k=k.replaceAll("阴，", "");
    		k=k.replaceAll("阳，", "");
    		v=v.replaceAll("空","");
    		v=v.replaceAll("字","");
    		v=v.replaceAll("星","");
    		if(!findmaterial.containsKey(k))
    			findmaterial.put(k, new HashSet<String>());
    		findmaterial.get(k).add(v);
    	});
		try {
	    	findcraft.forEach((k,v)->{
//	    		if(k.equals("龙"))new p(v);
	    		if(findcraftgraph.containsKey(k)) {
	    			findcraftgraph.get(k).addAll(v);
	    		}
	    		else findcraftgraph.put(k, v);
	    	});
	    	findmaterial.forEach((k,v)->{
	//    		if(k.equals("龙"))new p(v);
	    		if(findcraftgraph.containsKey(k)) {
	    			findcraftgraph.get(k).addAll(v);
	    		}
	    		else findcraftgraph.put(k, v);

    		//如果先放用产物找材料的会导致无向图产生圈？
	    	});
		}catch(Exception e1) {};
    	findcraftgraph.get("阴").add("阳");
    	findcraftgraph.get("阴").add("金");
    	findcraftgraph.get("阴").add("水");
    	findcraftgraph.get("阴").add("土");
    	findcraftgraph.get("阳").add("阴");
    	findcraftgraph.get("阳").add("木");
    	findcraftgraph.get("阳").add("火");
    	findcraftgraph.get("金").add("水");
    	findcraftgraph.get("金").add("土");
    	findcraftgraph.get("金").add("阴");
    	findcraftgraph.get("水").add("金");
    	findcraftgraph.get("水").add("木");
    	findcraftgraph.get("水").add("阴");
    	findcraftgraph.get("木").add("水");
    	findcraftgraph.get("木").add("火");
    	findcraftgraph.get("木").add("阳");
    	findcraftgraph.get("火").add("木");
    	findcraftgraph.get("火").add("土");
    	findcraftgraph.get("火").add("阳");
    	findcraftgraph.get("土").add("火");
    	findcraftgraph.get("土").add("金");
    	findcraftgraph.get("土").add("阴");
    }
    
    private void readExampleData() {
        // 示例数据
        String exampleData = "";
        for(int i=0;i<9;i++) {
        	 for(int j=0;j<17;j++) {
        		 exampleData+=j+" "+i+" ";
             }
        }
        readgrid(exampleData);
    }
    
    public String makeminname1() {
    	String name="";
    	int minx=99,miny=99;
    	Boolean isselect=false;
    	for(int j=0;j<17;j++) {	
			for(int i=0;i<9;i++) {
				if(null!=isingroup[i][j]&&isingroup[i][j])isselect=true;
			}
    	}
    	if(isselect) {
	    	for(int j=0;j<17;j++) {	
				for(int i=0;i<9;i++) {
					if(null!=isingroup[i][j]&&isingroup[i][j]) {
						if(j<minx)minx=j;
						if(i<miny)miny=i;
					}
				}
			}
			for (int j = 0; j < 17; j++) {
				for (int i = 0; i < 9; i++) {
					if (null != isingroup[i][j]&&isingroup[i][j]) {
						String sn = gridSeals[i][j];
						// 加上坐标和属性，动物名字
						if ((minx + miny) % 2 == 0)
							name += (j - minx) + "," + (i - miny) + sn;
						else
							name += (j - minx + 1) + "," + (i - miny) + sn;
						// 移动到双数位置防止错位
					}
				}
			}
    	}
    	else {
    		for(int j=0;j<17;j++) {	
				for(int i=0;i<9;i++) {
					if(null!=gridSeals[i][j]) {
						if(j<minx)minx=j;
						if(i<miny)miny=i;
					}
				}
			}
			for (int j = 0; j < 17; j++) {
				for (int i = 0; i < 9; i++) {
					if (null != gridSeals[i][j]) {
						String sn = gridSeals[i][j];
						// 加上坐标和属性，动物名字
						if ((minx + miny) % 2 == 0)
							name += (j - minx) + "," + (i - miny) + sn;
						else
							name += (j - minx + 1) + "," + (i - miny) + sn;
						// 移动到双数位置防止错位
					}
				}
			}
    	}
		return name;
    }
    
    public void readpuzzle(File f){
		try {
			 	InputStream fi=new FileInputStream(f);
	            Scanner sc=new Scanner(fi);
	            String target=sc.nextLine(),material=sc.nextLine(),
	            		fixed=sc.nextLine(),isdestroy=sc.nextLine(),area=sc.nextLine();
	            sc.close();
	            fi.close();
		        this.target=target;
		        targets.clear();
		        if(!target.equals("-")) {
		            List<int[]> coordinates = new ArrayList<>();
		            List<String> chineseTexts = new ArrayList<>();
		            // 改进的正则表达式：匹配"数字,数字"后跟一个或多个中文字符
		            // (\\d+),(\\d+) 匹配坐标（支持多位数）
		            // ([\\u4e00-\\u9fa5]+) 匹配一个或多个中文字符
		            Pattern pattern = Pattern.compile("(\\d+),(\\d+)([\\u4e00-\\u9fa5]+)");
		            Matcher matcher = pattern.matcher(target);
		            while (matcher.find()) {
		                // 解析坐标（支持多位数）
		                int x = Integer.parseInt(matcher.group(1));
		                int y = Integer.parseInt(matcher.group(2));
		                coordinates.add(new int[]{x, y});
		                
		                // 解析中文字符（支持多个字符）
		                String chs=matcher.group(3);
		                chineseTexts.add(chs);
		                targets.put(new Integer[]{x, y}, chs);
		                
		            }
		        }
		        baoshis.clear();
		        materialsn.clear();
		        materials.clear();
		        if(material.indexOf(" ")!=-1) {
		        	String[] mts=material.split(" ");
		        	if(mts[0].length()>=4)
		        		for(int i=0;i<mts.length;i++) {
		        			materialsn.add(mts[i]);
		        		}
		        	else
			        	for(int i=0;i<mts.length/2;i++) {
			        		materialsn.add(mts[i*2]);
			        		String num=mts[i*2+1];
			        		if(num.equals("-"))materials.add(99);
			        		else materials.add(Integer.parseInt(num));
			        	}
		        }
		        else materialsn.add(material);
		        
        		if(materials.size()==0)baoshis.put(materialsn.get(0),1);
        		else for(int i=0;i<materialsn.size();i++) {
        			baoshis.put(materialsn.get(i), materials.get(i));
        		}
		        this.fixed.clear();
		        if(!fixed.equals("-")) {
		            List<int[]> coordinates = new ArrayList<>();
		            List<String> chineseTexts = new ArrayList<>();
		            Pattern pattern = Pattern.compile("(\\d+),(\\d+)([\\u4e00-\\u9fa5]+)");
		            Matcher matcher = pattern.matcher(fixed);
		            while (matcher.find()) {
		                int x = Integer.parseInt(matcher.group(1));
		                int y = Integer.parseInt(matcher.group(2));
		                coordinates.add(new int[]{x, y});
		                String chs=matcher.group(3);
		                chineseTexts.add(chs);
		                this.fixed.put(new Integer[]{x, y}, chs);
		            }
		        }
		        
		        if(isdestroy.equals("--"))this.isdestroy=true;
		        else this.isdestroy=false;
		        
	            this.area.clear();
	            if(!area.equals("-")) {
		            String[] aes=area.split(" ");
		        	for(int i=0;i<aes.length;i++) {
		        		String[] xys=aes[i].split(",");
		        		int x=Integer.parseInt(xys[0]),y=Integer.parseInt(xys[1]);
		        		this.area.add(new Integer[]{x, y});
		        	}
	            }
	            isfinished=false;
	            try {
	            	finishtimeline.stop();
	            }catch(Exception e1) {}
        } 
		catch (IOException e) {}
	}
    
    public void makepuzzle() {
      	connectpane.getChildren().clear();
    	maxx=0;maxy=0;offx=0;offy=0;
   	 	for(int i=0;i<9;i++) {
			 for(int j=0;j<17;j++) {
				 canput[i][j]=true;
				 gridSeals[i][j]=null;
			 }
		 }
   	 	
   	 	for(int i=0;i<4;i++) {
   	 		if(ps[i].getChildren().size()>1) {
//   	 			for(int j=ps[i].getChildren().size()-1;j>-1;j--)
//   	 				if(choutiseals.containsValue(ps[i].getChildren().get(j))) {
//   	 					ps[i].getChildren().remove(j);
//   	 				}
   	 			ps[i].getChildren().remove(3, ps[i].getChildren().size());
   	 		}
   	 	}
   	 	choutiseals.clear();
   	 	
   	 	// 注意：不要在这里扣 fixed 灵印的材料。
   	 	// fixed 灵印后续会通过 placeSealOnGrid() 放置（见第 2821 行），
   	 	// 而 placeSealOnGrid() 内部已经扣过一次材料了。
   	 	// 如果这里再扣一次，会导致固定灵印的材料被扣双倍。
   	 	
    	 if(target.equals("--")||materialsn.get(0).equals("-")){
    		 //太极关 不限制材料
    		 for(int i=0;i<4;i++) {
    			 int transi=0;
	 			for(int j=0;j<3;j++) {
	 				try {
	 					String[] sns=sealtype[i*3+j];
	 					for(int p=0;p<sns.length;p++) {
	 						String sealName=sns[p];
	 						if(!sealpics.containsKey(sealName)) {
	 					        createSealImageView(sealName);
	 					        createSealImageView1(sealName);
	 				        }
	 						ImageView seal=new ImageView(sealpics.get(sealName));
	 						seal.setScaleX(0.333);
	 						seal.setScaleY(0.333);
	 						seal.setEffect(dsb);
	 						seal.setPickOnBounds(false);
	 		        		seal.setOnMousePressed(e -> {
	 				            System.out.println("选中的灵印: " + sealName);
	 				            currentSelectedSeal = sealName;
	 				            if(e.getButton()==MouseButton.SECONDARY) {
	 				            	showSynthesisPathCanvas(sealName);
	 				            }
	 		        		});
	 		        		seal.setOnMouseClicked(e->{
	 		        			e.consume();
	 		        		});
	 		        		seal.setTranslateX((transi%3)*67*scale-180*scale);
	 		        		seal.setTranslateY((transi/3)*74*scale-120*scale);
	 		        		Label sealnum=new Label(String.valueOf(materials.get(materialsn.indexOf(sealName))));
	 		        		sealnum.setEffect(dsb2);
	 		        		sealnum.setFont(f01);
	 		        		sealnum.setTextFill(Color.BLACK);
	 		        		sealnum.setTranslateX((transi%3)*67*scale+33*scale);
	 		        		sealnum.setTranslateY((transi/3)*74*scale+62*scale);
	 		        		sealnumlabels.put(sealName, sealnum);
	 		        		sealnum.setMouseTransparent(true);
	 		        		seal.setOnMouseDragged(e->{
	 		        			try {
			        				blendefine.mouseiv.setVisible(false);
			        			}catch(Exception e1) {}
	 		        			Node parent=((Node)e.getSource()).getParent();
	 		        			seal.setTranslateX(e.getSceneX()-parent.getTranslateX()-parent.getParent().getTranslateX()-173*scale);
	 		        			seal.setTranslateY(e.getSceneY()-parent.getTranslateY()-parent.getParent().getTranslateY()-173*scale);
	 		        			sealnum.setTranslateX(e.getSceneX()-parent.getTranslateX()-parent.getParent().getTranslateX()+30*scale);
		 		        		sealnum.setTranslateY(e.getSceneY()-parent.getTranslateY()-parent.getParent().getTranslateY()+10*scale);
	 		        			blendefine.nowmousex=e.getScreenX();
	 		        			blendefine.nowmousey=e.getScreenY();
	 		        		});
	 		        		int ti=transi;
	 		        		seal.setOnMouseReleased(e->{
	 		        			try {
	 		        				blendefine.mouseiv.setVisible(true);
	 		        				canputf=true;
	 		        				makegridevent(e);
	 		        			}catch(Exception e1) {}
	 		        			seal.setTranslateX((ti%3)*67*scale-180*scale);
	 			        		seal.setTranslateY((ti/3)*74*scale-120*scale);
	 			        		sealnum.setTranslateX((ti%3)*67*scale+33*scale);
		 		        		sealnum.setTranslateY((ti/3)*74*scale+62*scale);
	 		        		});
	 			            ps[i].getChildren().add(seal);
	 			           ps[i].getChildren().add(sealnum);
	 			           choutiseals.put(sealName, seal);
	 			          materialsn.add(sealName);
	 			            seal.setOpacity(0);
	 			            Timeline t=new Timeline(new KeyFrame(Duration.millis((transi+1)*300),new KeyValue(seal.opacityProperty(),1)));
	 			            t.play();
	 			           tms.add(t);
	 			            transi++;
	 					}
	 				}catch(Exception e) {}
	 			}
    		 }
    	 }
    	 else if(!target.equals("-")) {
    		 //直接生产目标关
    		 targets.forEach((k,v)->{
    			 if(k[0]>maxx)maxx=k[0];
    			 if(k[1]>maxy)maxy=k[1];
    		 });
    		 offx=((int)(17-maxx)/4)*2;
    		 offy=((int)(9-maxy)/4)*2;
    		 targets.forEach((k,v)->{
    			 int x=k[0]+offx,y=k[1]+offy;
    			 placeSealOnGrid(x, y, v);
    			 	seali[y][x].setOpacity(0);
    			 	canput[y][x]=false;
		            Timeline t=new Timeline(new KeyFrame(Duration.millis((x+y+1)*100),new KeyValue(seali[y][x].opacityProperty(),1)));
		            t.play();
		            tms.add(t);
    		 });
    	 }

    	 else if(materials.size()>0&&materialsn.get(0).length()<4) {
    		 //普通关
    		 for(int i=0;i<materials.size();i++) {
    			 	String sealName=materialsn.get(i);
    			 	if(!sealpics.containsKey(sealName)) {
    			        createSealImageView(sealName);
    			        createSealImageView1(sealName);
    		        }
						ImageView seal=new ImageView(sealpics.get(sealName));
						seal.setScaleX(0.333);
						seal.setScaleY(0.333);
						seal.setEffect(dsb);
						seal.setPickOnBounds(false);
						Label sealnum=new Label(String.valueOf(materials.get(materialsn.indexOf(sealName))));
 		        		sealnum.setFont(f01);
 		        		sealnum.setEffect(dsb2);
 		        		sealnum.setTextFill(Color.BLACK);
 		        		sealnumlabels.put(sealName, sealnum);
 		        		sealnum.setMouseTransparent(true);
		        		seal.setOnMousePressed(e -> {
				            System.out.println("选中的灵印: " + sealName);
				            currentSelectedSeal = sealName;
				            if(e.getButton()==MouseButton.SECONDARY) {
 				            	showSynthesisPathCanvas(sealName);
 				            }
		        		});
		        		seal.setOnMouseClicked(e->{
 		        			e.consume();
 		        		});
		        		if(materials.size()>14) {
		        			seal.setTranslateX((i%3)*67*scale-180*scale);
 			        		seal.setTranslateY((i/3)*74*scale-120*scale);
 			        		sealnum.setTranslateX((i%3)*67*scale+33*scale);
 			        		sealnum.setTranslateY((i/3)*74*scale+62*scale);
		        		}
		        		else {
			        		seal.setTranslateX((i%2)*120*scale-170*scale);
			        		seal.setTranslateY((i/2)*95*scale-120*scale+(int)(7-materials.size()/2)*48*scale);
			        		sealnum.setTranslateX((i%2)*120*scale+33*scale);
			        		sealnum.setTranslateY((i/2)*95*scale+63*scale+(int)(7-materials.size()/2)*48*scale);
		        		}
		        		seal.setOnMouseDragged(e->{
		        			try {
		        				blendefine.mouseiv.setVisible(false);
		        			}catch(Exception e1) {}
		        			Node parent=((Node)e.getSource()).getParent();
		        			seal.setTranslateX(e.getSceneX()-parent.getTranslateX()-parent.getParent().getTranslateX()-173*scale);
		        			seal.setTranslateY(e.getSceneY()-parent.getTranslateY()-parent.getParent().getTranslateY()-173*scale);
		        			sealnum.setTranslateX(e.getSceneX()-parent.getTranslateX()-parent.getParent().getTranslateX()+30*scale);
	 		        		sealnum.setTranslateY(e.getSceneY()-parent.getTranslateY()-parent.getParent().getTranslateY()+10*scale);
 		        			
		        			blendefine.nowmousex=e.getScreenX();
 		        			blendefine.nowmousey=e.getScreenY();
		        		});
		        		int k=i;
		        		seal.setOnMouseReleased(e->{
		        			try {
		        				blendefine.mouseiv.setVisible(true);
		        				canputf=true;
		        				makegridevent(e);
		        			}catch(Exception e1) {}
		        			if(materials.size()>14) {
			        			seal.setTranslateX((k%3)*67*scale-180*scale);
	 			        		seal.setTranslateY((k/3)*74*scale-120*scale);
	 			        		sealnum.setTranslateX((k%3)*67*scale+33*scale);
	 			        		sealnum.setTranslateY((k/3)*74*scale+62*scale);
			        		}
			        		else {
			        			seal.setTranslateX((k%2)*120*scale-170*scale);
				        		seal.setTranslateY((k/2)*95*scale-120*scale+(int)(7-materials.size()/2)*48*scale);
				        		sealnum.setTranslateX((k%2)*120*scale+33*scale);
				        		sealnum.setTranslateY((k/2)*95*scale+62*scale+(int)(7-materials.size()/2)*48*scale);
			        		}
		        		});
			            ps[0].getChildren().add(seal);
			            ps[0].getChildren().add(sealnum);
			            choutiseals.put(sealName, seal);
			            seal.setOpacity(0);
				        Timeline t=new Timeline(new KeyFrame(Duration.millis((i+1)*300*scale),new KeyValue(seal.opacityProperty(),1)));
				        t.play();
				        tms.add(t);
    		 }
    	 }
    	 connects.clear();
    	 sealconnects.clear();
    	 selectpane.getChildren().clear();
    	 if(area.size()>0) {
    		 if(offx==0&&offy==0) {
    			 area.forEach(k->{
        			 if(k[0]>maxx)maxx=k[0];
        			 if(k[1]>maxy)maxy=k[1];
        		 });
        		 offx=((int)(17-maxx)/4)*2;
        		 offy=((int)(9-maxy)/4)*2;
    		 }
    		 for(int i=0;i<9;i++) {
    			 for(int j=0;j<17;j++) {
    				 canput[i][j]=false;
    			 }
    		 }
    		 area.forEach(k->{
    			 int x=k[0]+offx,y=k[1]+offy; 
    			 canput[y][x]=true;
    		 });
    		 
    		 for(int i=0;i<9;i++) {
    			 for(int j=0;j<17;j++) {
    				 //阴影
    				 if(null==canput[i][j]||canput[i][j]==false) {
	        			Polygon cant;
	         			if((i+j)%2==1) {
	         				cant=new Polygon(50*scale,0,100*scale,87*scale,0,87*scale);
	         			}
	         			else {
	         				cant=new Polygon(0,0,100*scale,0,50*scale,87*scale);
	         			}
	         			cant.setTranslateX(50*j*scale-3*scale);
	         			cant.setTranslateY(87*i*scale);
	         			RadialGradient gradient = new RadialGradient(
	        	                0, 0,                       // 焦点角度和距离
	        	                50*scale, 29*scale+29*scale*((i+j)%2),                   // 焦点位置（中心）
	        	                80*scale,               // 半径
	        	                false,                       // 按比例缩放
	        	                CycleMethod.NO_CYCLE,       // 循环方式
	        	                new Stop(0, Color.color(0,0,0,0.4)),
	        	                new Stop(0.2, Color.color(0,0,0,0.6)),
	        	                new Stop(0.8, Color.color(0,0,0,0.8)) 
	        	        );
	         			cant.setFill(gradient);
	         			selectpane.getChildren().add(cant);
	         			cant.setOpacity(0);
			            Timeline t=new Timeline(new KeyFrame(Duration.millis((i+j+1)*100),new KeyValue(cant.opacityProperty(),1)));
			            t.play();
			            tms.add(t);
    				 }
    			 }
    		 }
    	 }
    	 
    	 if(fixed.size()>0) {
    		 if(offx==0&&offy==0) {
    			 fixed.forEach((k,v)->{
        			 if(k[0]>maxx)maxx=k[0];
        			 if(k[1]>maxy)maxy=k[1];
        		 });
        		 offx=((int)(17-maxx)/4)*2;
        		 offy=((int)(9-maxy)/4)*2;
    		 }
    		 fixed.forEach((k,v)->{
    			 int x=k[0]+offx,y=k[1]+offy;
    			 placeSealOnGrid(x,y,v);
    			 seali[y][x].setOpacity(0);
		         Timeline t=new Timeline(new KeyFrame(Duration.millis((x+y+1)*100),new KeyValue(seali[y][x].opacityProperty(),1)));
		         t.play();
		         tms.add(t);
		         Polygon cant;
      			if((x+y)%2==1) {
      				cant=new Polygon(50*scale,0,100*scale,87*scale,0,87*scale);
      			}
      			else {
      				cant=new Polygon(0,0,100*scale,0,50*scale,87*scale);
      			}
      			cant.setTranslateX(50*x*scale);
      			cant.setTranslateY(87*y*scale);
      			cant.setStrokeWidth(5*scale);
      			cant.setStrokeLineCap(StrokeLineCap.ROUND);
      			cant.setStrokeLineJoin(StrokeLineJoin.ROUND);
      			cant.setStroke(Color.color(1,0.2,0,0.7));
      			cant.setFill(null);
      			cant.setEffect(gsb1);
      			selectpane.getChildren().add(cant);
      			cant.setOpacity(0);
		            Timeline t1=new Timeline(new KeyFrame(Duration.millis((x+y+1)*100),new KeyValue(cant.opacityProperty(),1)));
		            t1.play();
		            tms.add(t1);
		         canput[y][x]=false;
    		 });
    	 }
    	 
    	 if(target.length()>4) {
 	   	 	for(int i=0;i<9;i++) {
 				 for(int j=0;j<17;j++) {
 					 canput[i][j]=false;
 				 }
 			 }
    	 }
    	
    }
    
 // 新增方法：显示关卡选择菜单
    private void showLevelSelectMenu() {
        if (levelSelectStage != null && levelSelectStage.isShowing()) {
            levelSelectStage.close();
            return;
        }
        
        // 读取关卡目录
        loadLevelFiles();
        
        // 创建关卡选择界面
        levelSelectStage = new Stage();
        levelSelectStage.setTitle("选择关卡");
        levelSelectStage.initModality(Modality.APPLICATION_MODAL);
        
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: #2b2b2b; -fx-border-color: #8b7355; -fx-border-width: 2;");
        
        // 标题
        Label titleLabel = new Label("选择关卡");
        titleLabel.setStyle("-fx-text-fill: #e0d0b8; -fx-font-size: 18px; -fx-font-weight: bold;");
        
        // 关卡列表容器
        ScrollPane scrollPane = new ScrollPane();
        levelSelectBox = new VBox(5);
        levelSelectBox.setPadding(new Insets(10));
        
        // 添加关卡按钮
        for (String levelFile : levelFiles) {
            Button levelButton = createLevelButton(levelFile);
            levelSelectBox.getChildren().add(levelButton);
        }
        
        scrollPane.setContent(levelSelectBox);
        scrollPane.setPrefSize(300, 400);
        scrollPane.setStyle("-fx-background: #1a1a1a; -fx-border-color: #5d4c3a;");
        
        // 刷新按钮
        Button refreshButton = new Button("刷新关卡列表");
        refreshButton.setStyle("-fx-background-color: #5d4c3a; -fx-text-fill: white; -fx-padding: 8 15;");
        refreshButton.setOnAction(e -> refreshLevelList());
        
        mainBox.getChildren().addAll(titleLabel, scrollPane, refreshButton);
        
        Scene scene = new Scene(mainBox);
        levelSelectStage.setScene(scene);
        levelSelectStage.show();
    }

    // 新增方法：创建关卡按钮
    private Button createLevelButton(String levelFile) {
        // 从文件名提取关卡名称（去掉.txt后缀）
        String levelName = levelFile.replace(".txt", "");
        
        Button button = new Button(levelName);
        button.setStyle("-fx-background-color: #3a2c1e; -fx-text-fill: #e0d0b8; -fx-padding: 10 20; -fx-min-width: 200;");
        button.setOnAction(e -> {
            loadSelectedLevel(levelFile);
            levelSelectStage.close();
        });
        
        return button;
    }

    // 新增方法：加载关卡文件列表
    private void loadLevelFiles() {
        levelFiles.clear();
        
        File levelDir = new File(currentLevelDir);
        if (levelDir.exists() && levelDir.isDirectory()) {
        	//包含主线和创意工坊
            File[] files = levelDir.listFiles((dir, name) -> name.contains("zhu")||name.contains("creativelevel"));
            if (files != null) {
                // 按数字排序
                Arrays.sort(files, (f1, f2) -> {
                    int num1 = extractLevelNumber(f1.getName());
                    int num2 = extractLevelNumber(f2.getName());
                    return Integer.compare(num1, num2);
                });
                
                for (File file : files) {
                	if(file.isFile())
                		levelFiles.add(file.getName());
                }
            }
        }
        
        // 如果没有找到文件，添加一些示例
        if (levelFiles.isEmpty()) {
            levelFiles.add("zhu1.txt");
            levelFiles.add("zhu50.txt");
        }
    }

    // 新增方法：从文件名提取关卡数字
    private int extractLevelNumber(String fileName) {
        try {
            String numberStr = fileName.replaceAll("[^0-9]", "");
            return Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // 新增方法：刷新关卡列表
    private void refreshLevelList() {
        if (levelSelectBox != null) {
            levelSelectBox.getChildren().clear();
            loadLevelFiles();
            
            for (String levelFile : levelFiles) {
                Button levelButton = createLevelButton(levelFile);
                levelSelectBox.getChildren().add(levelButton);
            }
        }
    }
    
    // 新增方法：加载选中的关卡
    void loadSelectedLevel(String levelFile) {
        try {
            File levelPath = new File(currentLevelDir, levelFile);
            
            // 清空当前状态
            clearAllSeal();
            clearAllSelections();
            String digits = levelFile.replaceAll("\\D+", ""); // 替换所有非数字为空
//            System.out.println(digits);
            if (!digits.isEmpty()) {
                int result = Integer.parseInt(digits);
//                System.out.println(result);
                nowlevel=result;
            }
            
            
            
            // 重置灵具抽屉
            if (ctpf != -1) {
            	Timeline resetDrawer = new Timeline(
                    new KeyFrame(Duration.seconds(0.2), 
                    new KeyValue(ps[ctpf].translateXProperty(), -320*scale, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(0.25), e -> { 
                        lingjvhe.toFront();
                        lingjvhebaoguang.toFront();
                        ps[ctpf].setEffect(null); 
                    }),
                    new KeyFrame(Duration.seconds(0.6), 
                    new KeyValue(ps[ctpf].translateXProperty(), 0, Interpolator.EASE_BOTH))
                );
                tms.add(resetDrawer);
                resetDrawer.setOnFinished(e -> {
                    ctpf = -1;
                    // 读取新关卡
                    readpuzzle(levelPath);
                    makepuzzle();
                });
                resetDrawer.play();
            } else {
                // 直接读取新关卡
                readpuzzle(levelPath);
                makepuzzle();
            }
            
            System.out.println("已加载关卡: " + levelFile);
            
        } catch (Exception e) {
//            System.err.println("加载关卡失败: " + e.getMessage());
        	e.printStackTrace();
            // 可以在这里添加错误提示
        }
    }
 // 创建带动画的连接线
    private Line createAnimatedConnectionLine(Point2D start, Point2D end,Color c) {
    	//TODO 连接改成圆形或三角
        Line line = createConnectionLine(start, end,c);
        
        // 初始长度为0
        Line animatedLine = new Line(start.getX(), start.getY(), start.getX(), start.getY());
        animatedLine.setStrokeWidth(5*scale);
        animatedLine.setStrokeLineCap(StrokeLineCap.ROUND);
        animatedLine.setStroke(line.getStroke());
        animatedLine.setEffect(line.getEffect());
        
        // 创建动画：从起点扩展到终点
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, 
                new KeyValue(animatedLine.endXProperty(), start.getX()),
                new KeyValue(animatedLine.endYProperty(), start.getY())
            ),
            new KeyFrame(Duration.millis(300),
                new KeyValue(animatedLine.endXProperty(), end.getX()),
                new KeyValue(animatedLine.endYProperty(), end.getY())
            )
        );
        tms.add(timeline);
//        timeline.setOnFinished(e -> {
//            // 动画结束后替换为静态线
//            connectpane.getChildren().remove(animatedLine);
//            connectpane.getChildren().add(line);
//        });
        
        connectpane.getChildren().add(animatedLine);
        timeline.play();
        
        return animatedLine;
    }

    // 在 updateSealConnections 中使用动画连接线
    private void updateSealConnections(int x, int y, String sealName) {
        removeSealConnections(x, y);
        
        if (sealName == null || !showConnections) {
            return;
        }
        
        List<int[]> neighbors = getTriangleNeighbors(x, y);
        List<Line> lines = new ArrayList<>();
        
        for (int[] neighbor : neighbors) {
            int nx = neighbor[0];
            int ny = neighbor[1];
            
            if (isValidCoordinate(nx, ny) && gridSeals[ny][nx] != null) {
                String neighborSeal = gridSeals[ny][nx];
                Point2D startPoint = new Point2D(x*50*scale+50*scale,y*87*scale+((x+y)%2==0?29*scale:58*scale));
                Point2D endPoint = new Point2D(nx*50*scale+50*scale,ny*87*scale+((nx+ny)%2==0?29*scale:58*scale));
                Set<String> connections = findcraftgraph.get(neighborSeal);
                
                if(!sealconnects.containsKey(nx+","+ny))
                	sealconnects.put(nx+","+ny, new HashSet<>());
                if(!sealconnects.containsKey(x+","+y))
                	sealconnects.put(x+","+y, new HashSet<>());
                sealconnects.get(nx+","+ny).add(x+","+y);
                sealconnects.get(x+","+y).add(nx+","+ny);
                
                if (connections != null && connections.contains(sealName)) {
                    Line line = createAnimatedConnectionLine(startPoint, endPoint,Color.color(1,1,1,1));
                    lines.add(line);
                }
                //相同的为朱雀连接
//                else if (sealName.equals(neighborSeal)) {
//                    
//                    Line line = createAnimatedConnectionLine(startPoint, endPoint,Color.color(1,0.6,0,0.85));
//                    lines.add(line);
//                }
                //强化朱雀连接
                else {
                    Line line = createAnimatedConnectionLine(startPoint, endPoint,Color.color(1,0,0,0.75));
                    lines.add(line);
                }
            }
        }
        
        connects.put(x + "," + y, lines);
    }
 
 // 创建连接线
    private Line createConnectionLine(Point2D start, Point2D end,Color c) {
        Line line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        line.setStrokeWidth(2*scale);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setStrokeLineJoin(StrokeLineJoin.ROUND);
        line.setEffect(gsb1);
        // 可以根据连接类型设置不同颜色
        line.setStroke(c);
        
        // 添加发光效果
//        Bloom bloom = new Bloom();
//        bloom.setThreshold(0.3);
//        line.setEffect(bloom);
        
        return line;
    }

    // 移除单个灵印的所有连接线
    private void removeSealConnections(int x, int y) {
        String key = x + "," + y;
        List<Line> lines = connects.get(key);
        if (lines != null) {
            connectpane.getChildren().removeAll(lines);
            connects.remove(key);
            if(sealconnects.containsKey(key)) {
	            sealconnects.get(key).forEach(e->{
	            	sealconnects.get(e).remove(key);
	            });;
	            sealconnects.remove(key);
            }
        }
    }

    // 更新所有连接线
    private void updateAllConnections() {
        // 清空所有连接线
        connectpane.getChildren().clear();
        connects.clear();
        sealconnects.clear();
        if (!showConnections) return;
        
        // 重新绘制所有连接线
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 17; x++) {
                if (gridSeals[y][x] != null) {
                    updateSealConnections(x, y, gridSeals[y][x]);
                }
            }
        }
    }

    // 切换连接线显示状态
    @SuppressWarnings("unused")
	private void toggleConnections() {
        showConnections = !showConnections;
        if (showConnections) {
            updateAllConnections();
        } else {
            connectpane.getChildren().clear();
            connects.clear();
        }
    }
    
 // 合成树节点（增加合成类型字段）
    private static class SynTreeNode {
        String element;
        String synthesisType;        // "wuzang", "hunyuan", 或 null（基础/循环叶节点）
        List<SynTreeNode> children;  // 包含五脏子节点 + 混元循环子节点
        boolean isCycleNode;         // 标记该节点本身是否是一个混元循环叶节点（不再展开）
        String cycleMate;            // 如果是循环节点，存储配对的另一个材料（阴/阳）

        SynTreeNode(String element, String synthesisType) {
            this.element = element;
            this.synthesisType = synthesisType;
            this.children = new ArrayList<>();
            this.isCycleNode = false;
        }
    }

 // 配方辅助类
    private static class Recipe {
        String[] materials;
        String type; // "wuzang" 或 "hunyuan"
        Recipe(String[] materials, String type) {
            this.materials = materials;
            this.type = type;
        }
    }
    private static class NodeClickArea {
        double centerX, centerY, halfSize;
        String elementName;
        NodeClickArea(double centerX, double centerY, double halfSize, String elementName) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.halfSize = halfSize;
            this.elementName = elementName;
        }
        boolean contains(double px, double py) {
            return px >= centerX - halfSize && px <= centerX + halfSize &&
                   py >= centerY - halfSize && py <= centerY + halfSize;
        }
    }
    /**
     * 回收所有资源，停止动画，清空节点与数据，避免内存泄漏。
     * 调用后该实例不应再被使用。
     */
    
    public String getLocalizedSealName(String chineseName, int lang) {
        // 去除前缀（根据你的数据格式：前缀如“空”、“星”、“字”等后接真实名称）
        String coreName = chineseName;
        if (coreName.startsWith("空") || coreName.startsWith("星") || coreName.startsWith("字")) {
            coreName = coreName.substring(1);
        }
        // 边缘情况：“空阴” -> “阴”，但“阴”已存在；类似处理
        if (coreName.equals("阴") || coreName.equals("阳")) {
            // 保持原样
        }
        
        String[] translations = localizedSealNames.get(coreName);
        if (translations != null && lang >= 0 && lang < translations.length) {
            return translations[lang];
        }
        // 未找到翻译则返回原中文名（或去掉前缀后的）
        return coreName;
    }
    
    public void destroy() {
        // 1. 停止所有动画时间线
        if (tms != null) {
            tms.forEach(Timeline::stop);
            tms.clear();
        }
        if (checktimeline != null) {
            checktimeline.stop();
            checktimeline = null;
        }
        if (finishtimeline != null) {
            finishtimeline.stop();
            finishtimeline = null;
        }
        if (stopdoubleclick != null) {
            stopdoubleclick.stop();
            stopdoubleclick = null;
        }

        // 2. 关闭关卡选择窗口
        if (levelSelectStage != null) {
            levelSelectStage.close();
            levelSelectStage = null;
        }

        // 3. 移除动态画布（合成链路图等）
        if (nowcanvas != null) {
            Node parent = nowcanvas.getParent();
            if (parent instanceof Pane) {
                ((Pane) parent).getChildren().remove(nowcanvas);
            }
            nowcanvas = null;
        }
        // 清理 stack 中可能残留的 Canvas
        if (stack != null) {
            stack.getChildren().removeIf(n -> n instanceof Canvas);
        }

        // 4. 释放图片与 ImageView 引用
        if (sealpics != null) sealpics.clear();
        if (choutiseals != null) choutiseals.clear();

        // 5. 清理网格相关数组
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
                if (gridv != null && gridv[i] != null) gridv[i][j] = null;
                if (seali != null && seali[i] != null) seali[i][j] = null;
                if (selectpoly != null && selectpoly[i] != null) selectpoly[i][j] = null;
                if (gridSeals != null && gridSeals[i] != null) gridSeals[i][j] = null;
                if (isingroup != null && isingroup[i] != null) isingroup[i][j] = null;
                if (canput != null && canput[i] != null) canput[i][j] = null;
            }
        }

        // 6. 清除所有面板上的子节点
        if (connectpane != null) connectpane.getChildren().clear();
        if (selectpane != null) selectpane.getChildren().clear();
        if (gpane != null) gpane.getChildren().clear();
        if (ps != null) {
            for (Pane p : ps) {
                if (p != null) p.getChildren().clear();
            }
        }
        if (stack != null) stack.getChildren().clear();
        if (rootPane != null) rootPane.getChildren().clear();

        // 7. 清空连接与数据集合
        if (connects != null) connects.clear();
        if (sealconnects != null) sealconnects.clear();
        if (hunyuancft != null) hunyuancft.clear();
        if (wuzangcft != null) wuzangcft.clear();
        if (wuzangcftni != null) wuzangcftni.clear();
        if (findcraft != null) findcraft.clear();
        if (findmaterial != null) findmaterial.clear();
        if (findcraftgraph != null) findcraftgraph.clear();
        if (sealMap != null) sealMap.clear();
        if (minDepth != null) minDepth.clear();
        if (bestRecipeMaterials != null) bestRecipeMaterials.clear();
        if (bestRecipeType != null) bestRecipeType.clear();
        if (basicElements != null) basicElements.clear();
        if (materialsn != null) materialsn.clear();
        if (materials != null) materials.clear();
        if (targets != null) targets.clear();
        if (fixed != null) fixed.clear();
        if (area != null) area.clear();
        if (curgridpath != null) curgridpath.clear();
        if (curpath != null) curpath.clear();
        if (sealnumlabels != null) sealnumlabels.clear();

        // 8. 释放三角图片引用
        triid = null;
        triiu = null;

        // 9. 重置关键变量，断开外部引用
        bdf = null;
        gk = null;
        currentSelectedSeal = null;
        rootPane = null;
        stack = null;
        gpane = null;
        selectpane = null;
        connectpane = null;
        dr = null;
        dr1 = null;
        rotate = null;
        rotate1 = null;
        lingjvhe = null;
        lingjvhebaoguang = null;

        // 10. 其他状态重置
        isfinished = false;
        anifinishf = false;
        makepathf = -1;
        ctpf = -1;
        showConnections = false;
        canputf = false;
    }
    public static void main(String[] args) {
        launch(args);
    }  
    
}
