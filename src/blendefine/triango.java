package blendefine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Window;
import javafx.util.Duration;
import tool.NumberToChinese;
import javafx.application.Platform;
import javafx.scene.control.Button;
import triango.MCTSNode;
import triango.TriangoBoard;
import triango.TriangoMCTS;

public class triango {
//----------本地南瓜种植基地-----------//

     //    //      //      \\     
    {{}}  {{}}   {{{}}}    {{}}
    
    //      //     //     \\     
   {{}}   {{{}}}  {{}}   {{{}}}
    
//------------禁止私自采摘------------//
	static final int MAPWIDTH = 81; // 地图宽度
	static final int MAPHEIGHT = 49; // 地图高度
	private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	 private TriangoBoard board;
	    private TriangoMCTS ai;
	    private Label statusLabel;
	    private Label scoreLabel;
	    private boolean gameOver = false;
	    private boolean aiVsAiMode = false;
	    private boolean humanVsAiMode = true;
	    private boolean isAiThinking = false;
	    // 棋盘参数
	Scene scene=blendefine.scene;
	double screenwidth = blendefine.screenwidth, screenheight =blendefine.screenheight;

	public Image rooti, zhengjini, blend1i,blend2i,
			defineud= new Image(getClass().getResourceAsStream("definepic/包圆.png")),
			defineguangzhao= new Image(getClass().getResourceAsStream("definepic/光照.png")),
			defineg = new Image(getClass().getResourceAsStream("definepic/宝石箍.png")),
			defineg1 = new Image(getClass().getResourceAsStream("definepic/宝石箍1.png")),
			guiniui = new Image(getClass().getResourceAsStream("sealpic/龟纽.png")),
			xuani0 = new Image(getClass().getResourceAsStream("gridpic/xuan0.png")),
			xuani1 = new Image(getClass().getResourceAsStream("gridpic/xuan1.png")),
			xuani2 = new Image(getClass().getResourceAsStream("gridpic/xuan2.png")),
			xuani3 = new Image(getClass().getResourceAsStream("gridpic/xuan3.png")),
			deletem = new Image(getClass().getResourceAsStream("mousepic/delete.png")),
			shizhuani = new Image(getClass().getResourceAsStream("otherpic/shizhuan.png"), screenwidth, screenheight,false, false),
//    		shizhuani=new Image(getClass().getResourceAsStream("shizhuan.png"))
					
			jiaochengi= new Image(getClass().getResourceAsStream("uipic/教程纸1.png"),screenwidth,screenheight,false,false);
	
		    
	Polygon checkcoli,checkcoli0;
	
		public Image[] triiu = new Image[10], triid = new Image[10],
			baoshibs = new Image[24], definezang=new Image[10];
		ImageView rootv = new ImageView(), alli = new ImageView(),
						
						jiaocheniv=new ImageView(jiaochengi);
								
	blend guigui;
	Blend definecolorblend = new Blend(),defineli=new Blend(),xuan0=new Blend(),xuan1=new Blend(),xuan2=new Blend(),xuan3=new Blend();
	ImageView [] caozuoan=new ImageView[10];
	ImageView[][] gridv = new ImageView[MAPHEIGHT][MAPWIDTH];
	Blend[] zangs=new Blend[10];
	ConcurrentHashMap<String,shixuan> shixuuses = new ConcurrentHashMap<String,shixuan>();
	ConcurrentHashMap<String,Image>mostpic=new ConcurrentHashMap<String,Image>(), charsi = new ConcurrentHashMap<String,Image>(),charsil = new ConcurrentHashMap<String,Image>(),
			baoshiimagemap = new ConcurrentHashMap<String,Image>(),
			baoshianimalmap=new ConcurrentHashMap<String,Image>(),baoshifxmap=new ConcurrentHashMap<String,Image>();
	ConcurrentHashMap<String,Integer> animalindex = new ConcurrentHashMap<String,Integer>();
	
	ConcurrentHashMap<String,Color> rectcolor = new ConcurrentHashMap<String,Color>();
	ConcurrentHashMap<String,Long> offscreenTimestamps= new ConcurrentHashMap<String,Long>();
	ConcurrentHashMap<String,Polygon> ts=new ConcurrentHashMap<String,Polygon>();
	ConcurrentHashMap<String,Point2D> iniitemplaces=new ConcurrentHashMap<String,Point2D>();
	ConcurrentHashMap<String,String> destinations= new ConcurrentHashMap<String,String>();
	
	HashSet<KeyCode> scenekeys= new HashSet<KeyCode>();
	HashSet<Integer> playedfu= new HashSet<Integer>();
	
	ArrayList<String> dirs=new ArrayList<String>();//用于define类中的宝石朝向
	
	ArrayList<Node>roothistory=new ArrayList<>();
	private boolean shixuoutf=false, canscroll = true,
			effectf=true,lowgrahf=true,deletef=false,
			fpsshowf=false,canswitchstagef=true;
	private int frameCount = 0,isopenfinishf = blendefine.isopenfinishf,
			hovergridx = -1, hovergridy = -1,nowplayshixupos,
					xrnowtimef,nowintro;
	private long lastTime = System.nanoTime();
	private final Text fpsText = new Text(10, 20, "FPS: 0");
	private double mouseX = 0, mouseY = 0, transX = 0, transY = 0, nowmousex =0, nowmousey = 0,nowhoverposx,nowhoverposy,
			lastframecount;
	private double suofang = screenheight / 1080, scale = 0.4 * suofang;
	// 在类中声明动画对象
	private double scalemubanl = screenheight / 688 ;
	public double nowpicvalue=-20*suofang;
	//机器运行速度 加速火把
//	double maxoffsetx = 200 * scalemubanl - screenwidth / 2 * (1 - scale),
//			maxoffsety = 25 * scalemubanl - screenheight / 2 * (1 - scale),
//			minoffsetx = maxoffsetx - 200 * scalemubanl - 12300 * scale + screenwidth,
//					minoffsetx = maxoffsetx - 200 * scalemubanl - 15300 * scale + screenwidth,
//			minoffsety = maxoffsety - 250 * scalemubanl - 12740 * scale + screenheight;
	public StackPane root,game=new StackPane() ,ui=new StackPane(), stp=blendefine.stp,ls1=new StackPane();;
	DropShadow ds1 = new DropShadow();
	DropShadow ds = new DropShadow();
	DropShadow ds0 = new DropShadow(30*suofang, 20*suofang, 10*suofang, Color.color(0, 0, 0, 0.9));
	DropShadow ds4 = new DropShadow();
	DropShadow ds3 = new DropShadow();
	DropShadow ds2 = new DropShadow(58*suofang, 3*suofang,0, Color.color(0, 0, 0, 0.6));
	DropShadow ds5 = new DropShadow(38*suofang, 13*suofang,23*suofang, Color.color(0, 0, 0, 0.9));
	DropShadow otlt0 = new DropShadow(64*suofang,0,0, Color.color(1, 1, 1, 1));
	GaussianBlur gausb=new GaussianBlur();
	Bloom bloom = new Bloom();
	
	Font f5=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),50*suofang),
		f3=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),30*suofang),
		f25=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),25*suofang),
		f2=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),20*suofang),
			ff1=Font.loadFont(getClass().getResourceAsStream("uipic/dingliezhuhaifont-20240831GengXinBan)-2.ttf"),40*suofang);
	
	private Timeline scaleTimeline = new Timeline();
	public Pane gpane,blpane,lzpane,dfbpane,dfupane,hypane,dfpane,udpane,handlepane,fxpane,fxpane1,
	rootlis;

	public Rotate leftclock, leftanti, rightclock, rightanti, downclock, downanti;
	public Rotate[] rolls;
	ConcurrentHashMap<String,Integer> rollindex = new ConcurrentHashMap<String,Integer>();
	ConcurrentHashMap<String,Integer> rollindex1 = new ConcurrentHashMap<String,Integer>();
	ConcurrentHashMap<String,Integer> rollni = new ConcurrentHashMap<String,Integer>();
	ConcurrentHashMap<String,Integer> pushi = new ConcurrentHashMap<String,Integer>();
	public int[][] rolln = { {1, 0}, { -1, 0 }, {0, 1}, {0, -1} };//x,y 旋转转移动
	public int scaleindex = 1,nowhide=0,reviewintrof=0,nowstage=1,todest=0;
	public String nowgrabname;
	public String allintro,mapsbuf;
	public double[] scales = { 0.19, 0.24, 0.3, 0.37, 0.5, 0.7, 1 };
	public String[] 
			elename = {"金", "木", "水", "火", "土" },
			animalname = {"狗", "鹿", "鲶", "牛", "蛇", "狮", "虾", "鹰", "鱼" },
			longsonname = {"囚牛", "睚眦", "嘲风", "蒲牢", "狻猊", "霸下", "狴犴", "负屃", "螭吻" };

//	grid[][] grids = new grid[MAPHEIGHT][MAPWIDTH];
	@SuppressWarnings("unchecked")
	List<Segment>[][] colis=new List[MAPHEIGHT][MAPWIDTH];//碰撞体数组，实时更新
	grid[][] db = new grid[MAPHEIGHT][MAPWIDTH];//存储上层机器

	define[][] df=new define[MAPHEIGHT][MAPWIDTH];//宝石
	Node nowgrab;
	// 新增双缓冲相关变量
	private WritableImage backBuffer;
	RotateTransition[] rttts=new RotateTransition[8];
	private Timeline process=new Timeline(),//所有机器运作的时间轴process
			pixer,intro,reviewintro;
    // 平滑运动系统参数
    private final Timeline smoothTimeline = new Timeline(
        new KeyFrame(Duration.millis(6), e -> updateSmoothPosition())
    );
    private double targetX = 0, targetY = 0;
    private final double SMOOTH_FACTOR = 0.08;

//    Bloom xuanwuputbloom=new Bloom();
    
    blendefine bdf;
	SnapshotParameters params = new SnapshotParameters(),params1 = new SnapshotParameters();
	
	public void inistage() {
		
		dirs.add("0,-1");dirs.add("1,0");dirs.add("1,0");dirs.add("0,1");dirs.add("-1,0");dirs.add("-1,0");//用于define类中的宝石朝向
	
		params.setFill(Color.TRANSPARENT);
//      params.setTransform(new javafx.scene.transform.Affine()); // 禁用变换缓存
//      params.setDepthBuffer(false); // 禁用深度缓冲
        params.setViewport(new Rectangle2D(0, 0, screenwidth, screenheight+50));
        params1.setFill(Color.TRANSPARENT);

		for(int i=0;i<10;i++) {
			//默认使用低分辨率提高性能
//    		if(lowgrahf) {
    			triiu[i]=new Image(getClass().getResourceAsStream("gridpic/gridu"+i+".png"),300*scale,260*scale,false,false);
    			triid[i]=new Image(getClass().getResourceAsStream("gridpic/gridd"+i+".png"),300*scale,260*scale,false,false);
    			definezang[i]=new Image(getClass().getResourceAsStream("definepic/脏"+i+".png"));	
//    		}
    	}
    	
    	for(int i=0;i<26;i++) {
    		charsi.put(String.valueOf((char)(65+i)),new Image(getClass().getResourceAsStream("charpic/"+(char)(65+i)+".png")));
    		charsil.put(String.valueOf((char)(65+i)),new Image(getClass().getResourceAsStream("charpic/"+(char)(65+i)+"1.png")));
    	}
    
    	if(lowgrahf) {

    		//以上缩放会影响readgrid像素计算
    		for(int i=0;i<24;i++) {
	    		baoshibs[i]=new Image(getClass().getResourceAsStream("definepic/宝石a"+i+".png"),346*scale,346*scale,false,false);
	    	}

    	}
    	else {

    		for(int i=0;i<24;i++) {
	    		baoshibs[i]=new Image(getClass().getResourceAsStream("definepic/宝石a"+i+".png"));
	    	}
//    		for(int i=0;i<animalname.length;i++) {
//	    		baoshims[i]=new Image(getClass().getResourceAsStream("definepic/宝石"+animalname[i]+".png"));
//	    	}
    	}

    	for(int i=0;i<10;i++) {
    		zangs[i] =new Blend();
			zangs[i].setMode(BlendMode.OVERLAY);
			zangs[i].setTopInput(new ImageInput(definezang[i]));
    	}
    	
    	definecolorblend.setMode(BlendMode.OVERLAY);

    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(defineguangzhao));
    	
    	xuan0.setMode(BlendMode.OVERLAY);
    	xuan0.setTopInput(new ImageInput(xuani0));
    	xuan1.setMode(BlendMode.OVERLAY);
    	xuan1.setTopInput(new ImageInput(xuani1));
    	xuan2.setMode(BlendMode.OVERLAY);
    	xuan2.setTopInput(new ImageInput(xuani2));
    	xuan3.setMode(BlendMode.OVERLAY);
    	xuan3.setTopInput(new ImageInput(xuani3));
    	
    	 // 初始化双缓冲
    	backBuffer = new WritableImage((int) screenwidth, (int) screenheight);
    	rootv.setImage(backBuffer);
    	smoothTimeline.setCycleCount(Animation.INDEFINITE);
    	smoothTimeline.play();
        udpane=new Pane();
        lzpane=new Pane();
        dfupane=new Pane();
        dfbpane=new Pane();
        hypane=new Pane();
        blpane=new Pane();
        handlepane=new Pane();
        fxpane=new Pane();
        
        fxpane1=new Pane();

        blpane.getChildren().add(hypane);
        blpane.getChildren().add(dfupane);
        blpane.getChildren().add(lzpane);
        dfpane=new Pane();
        gpane=new Pane();
       
        dfbpane.setMouseTransparent(true);
        dfpane.setMouseTransparent(true);
        blpane.setMouseTransparent(true);
        fxpane.setMouseTransparent(true);
        fxpane1.setMouseTransparent(true);
//        handlepane.setMouseTransparent(true);
        
        //防止bounds计算不正确导致子节点被隐藏
        lzpane.setPrefSize(12300, 12740);
        hypane.setPrefSize(12300, 12740);
        dfbpane.setPrefSize(12300, 12740);
        dfupane.setPrefSize(12300, 12740);
        udpane.setPrefSize(12300, 12740);
        handlepane.setPrefSize(12300, 12740);
        fxpane.setPrefSize(12300, 12740);
        fxpane1.setPrefSize(12300, 12740);
//        gpane.setCache(true);
        root = new StackPane();	
        root.setAlignment(Pos.TOP_LEFT);   
        root.setScaleX(scale);
        root.setScaleY(scale);
        double maxoffsetx = 200 * scalemubanl - screenwidth / 2 * (1 - scale),
    			maxoffsety = 25 * scalemubanl - screenheight / 2 * (1 - scale);
        root.setTranslateX(maxoffsetx);
        root.setTranslateY(maxoffsety);
//        shizhuani =imgmake.process(shizhuani,11,2);
        ImageView shizhuan=new ImageView(shizhuani);
        shizhuan.setSmooth(false);
        shizhuan.setCache(true);
        game.getChildren().add(shizhuan);
        stp.getChildren().add(game);


        game.setVisible(false);
       
        if(effectf) {
        	DropShadow is = new DropShadow();
            is.setOffsetX(50);
            is.setOffsetY(100);
            is.setSpread(0.2);
            is.setRadius(500);
            DropShadow is1 = new DropShadow(50, Color.color(0, 0, 0,0.8));
            is1.setOffsetX(15);
            is1.setOffsetY(30);
            is1.setSpread(0.3);
            DropShadow is2 = new DropShadow(20, Color.color(0, 0, 0,0.7));
            is2.setOffsetX(7);
            is2.setOffsetY(15);
//            is2.setSpread(0.2);
//	        root.setEffect(is);
	        gpane.setEffect(is1);
	        blpane.setEffect(is1);
	        dfbpane.setEffect(is1);
	        lzpane.setEffect(is2);
	        dfpane.setEffect(is2);
	        
        }
       
        game.getChildren().add(root);
        root.setPickOnBounds(false);
        // 动画定时器优化，使用多线程更新位置
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long currentTime = System.nanoTime();
                // 更新 FPS 计算
                frameCount++;
                if (currentTime - lastTime >= 1_000_000_000) {
                	lastframecount=frameCount;
                    frameCount = 0;
                    lastTime = currentTime;
                }
            }
        };
        timer.start();
             
        
//以下为时序界面
        ui = new StackPane();
	
		ui.setPickOnBounds(false);
		ui.setAlignment(Pos.TOP_LEFT);
		
    
		game.getChildren().add(ui);
		
        root.getChildren().add(udpane);
        root.getChildren().add(gpane); 
        root.getChildren().add(dfbpane);
        
        root.getChildren().add(fxpane);
		root.getChildren().add(dfpane);
		root.getChildren().add(blpane);
		root.getChildren().add(fxpane1);
		root.getChildren().add(handlepane);
		
		
		handlepane.setPickOnBounds(false);
		udpane.setPickOnBounds(false);
		dfpane.setPickOnBounds(false);
		blpane.setPickOnBounds(false);
		

		alli.setSmooth(false);
		alli.setFitHeight(screenheight+50);
		alli.setFitWidth(screenwidth);
		
		alli.setMouseTransparent(true);
		stp.getChildren().add(alli);	
		game.setVisible(false);
		
	    fpsText.setFill(Color.RED);
	    fpsText.setFont(new Font(20));
	    fpsText.setTranslateX(25);
	    fpsText.setTranslateY(25);
	    stp.getChildren().add(fpsText);
	    fpsText.setVisible(fpsshowf);
	
		pixer = new Timeline(new KeyFrame(Duration.millis(8), e->{
			int dss=destinations.size();
			if(dss!=0) {
				todest=0;
				destinations.forEach((k,v)->{
					String[] xy=k.split(",");
					int x=Integer.parseInt(xy[0]),y=Integer.parseInt(xy[1]);
					if(df[y][x]!=null) {
						if(v.equals("正")&&(df[y][x].name.equals("木")||df[y][x].name.equals("火")||df[y][x].name.equals("土")))
							todest++;
						if(v.equals("逆")&&(df[y][x].name.equals("金")||df[y][x].name.equals("水")))
							todest++;
					}
				});
				if(todest==dss&&canswitchstagef) {
					canswitchstagef=false;
					Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.6),e1->{
						nowstage++;
						loadScript("src/blendefine/files/triango.txt");
						canswitchstagef=true;
					}
					));
					t.play();
				}
			}
			if(nowgrab!=null) {
				nowgrab.setScaleX(scale);
				nowgrab.setScaleY(scale);
			}
			if(!scenekeys.isEmpty()) {
				if(!shixuoutf) {
					if(scenekeys.contains(KeyCode.SHIFT)) {
						double rtx1=root.getTranslateX()-30,rtx2=root.getTranslateX()+30,
		        				rty1=root.getTranslateY()-30,rty2=root.getTranslateY()+30;
						if(scenekeys.contains(KeyCode.W))
							root.setTranslateY(rty2);
						if(scenekeys.contains(KeyCode.S))
							root.setTranslateY(rty1);
						if(scenekeys.contains(KeyCode.A))
							root.setTranslateX(rtx2);
						if(scenekeys.contains(KeyCode.D))
							root.setTranslateX(rtx1);
						if(scenekeys.contains(KeyCode.Q))
							scaleroot(10);
						if(scenekeys.contains(KeyCode.E))
							scaleroot(-10);
					}
					else scenekeys.clear();
				}
			}
			if(fpsshowf)
				fpsText.setText("FPS:" + lastframecount+" 坐标x"+nowmousex+" 坐标y"+nowmousey+" 缩放"+scale+"\n\r"
        			+" 格子x"+hovergridx+" 格子y"+hovergridy+" 操作x"+Math.floor(nowhoverposx)+" 操作y"+Math.floor(nowhoverposy));
			else fpsText.setText(null);
			

			xrnowtimef=xrnowtimef%4+1;
			if(xrnowtimef==1) {
				optimizeNode(stp,scene.getWindow());
				
			}
			
			if(isopenfinishf>0) {
				try {
//				nowhide=0;
//				if(!lowgrahf)
				
				}catch(Exception e1) {}
			}}));
		pixer.setCycleCount(Timeline.INDEFINITE);
		pixer.setAutoReverse(true);
		pixer.play();	
		
		
    	scene.setOnMouseReleased(e -> {
			mouseX = 0;
			mouseY = 0;
			transX = 0;
			transY = 0;
		});
    	
    	game.setOnMouseClicked(e->{
    		if(e.getButton()==MouseButton.SECONDARY) {
	    		if(null!=reviewintro&&reviewintro.getStatus()==Status.RUNNING) {
	    			reviewintrof++;
	    		}
	    		else {
	    			reviewintro=new Timeline(new KeyFrame(Duration.seconds(1)));
	    			reviewintro.setOnFinished(e1->{
	    				if(reviewintrof>1)nowintro--;
	    				reviewintrof=0;
	    			});
	    			reviewintro.play();
	    		}
    		}
    	});
    	
        scene.setOnMouseMoved(e->{
        	nowmousex=e.getX();
        	nowmousey=e.getY();
        });
		gpane.setOnMouseClicked(e -> {
			if((deletef&&e.getButton()==MouseButton.PRIMARY)||e.getButton()==MouseButton.MIDDLE) {
				try {
				grid g=db[hovergridy][hovergridx];
				define g0=df[hovergridy][hovergridx];
				if(null!=g) {
					lzpane.getChildren().remove(g.g);
					db[hovergridy][hovergridx]=null;
				}
				else if(null!=g0) {
					dfpane.getChildren().remove(g0.g);
					df[hovergridy][hovergridx]=null;
				}
				}catch(Exception e1) {}
			}
			else {
				if (gameOver || isAiThinking || aiVsAiMode) return;
	            // 将屏幕坐标转换为棋盘坐标
	            if (hovergridy!=-1 &&hovergridx!= -1) {
	                int moveSuccess = board.makeMove(hovergridx,hovergridy);
	                if (moveSuccess!=-1) {
	                    drawBoard();
	                    updateStatus();
	                    TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动
	                    // 检查游戏是否结束
	                    if (board.isGameOver()) {
	                        gameOver = true;
	                        int[] scores =board.scores;
	                        int winner = board.getWinner();
	                      
	                        statusLabel.setText("游戏结束! " + 
	                                (winner == TriangoBoard.BLACK ? "黑方胜" : 
	                                 winner == TriangoBoard.WHITE ? "白方胜" : "平局"));
	                        scoreLabel.setText("黑方: " + scores[0] + ", 白方: " + scores[1]);
	                    } else if (humanVsAiMode && board.getCurrentPlayer() == TriangoBoard.WHITE) {
	                        // AI回合
	                        new Thread(this::aiMakeMove).start();
	                    }
	                }
	            }
			}
		});
        root.setOnScroll(event -> {
        	double deltaY = event.getDeltaY();
        	if(canscroll&&scaleTimeline.getStatus() == Animation.Status.STOPPED) {
	            // 获取滚动方向和当前时间
	            
	            if(!(deltaY<0&&scale<0.17*suofang)&&!(deltaY>0&&scale>0.9*suofang))
	            scaleroot(deltaY);
	            else canscroll=true;   
        	}  
        });
        scene.setOnKeyReleased(e->{
        	try {
        		scenekeys.remove(e.getCode());
        	}catch(Exception e1) {}
        	if(e.getCode().toString().equals("SHIFT")) {	
				deletef = false;
        	}
        });
        scene.setOnKeyPressed(e->{
        	scenekeys.add(e.getCode());
        	String code=e.getCode().toString();
//        	System.out.println(e.getCode());
        	if(code.contains("DIGIT")) {     		
        		String[] makes= {"金","木","水","火","土"};
        		int i=Integer.parseInt(code.substring(5))-1;
        		if(i==-1) {
        			 maptostring();
        		}
        		else if(i<5) {
        			if(df[hovergridy][hovergridx]!=null||db[hovergridy][hovergridx]!=null) {
        				try {
	        					grid g=db[hovergridy][hovergridx];
	        					define g0=df[hovergridy][hovergridx];
	        					if(null!=g) {
	        						lzpane.getChildren().remove(g.g);
	        						db[hovergridy][hovergridx]=null;
	        						
	        					}
	        					else if(null!=g0) {
	        						dfpane.getChildren().remove(g0.g);
	        						df[hovergridy][hovergridx]=null;
	        						
	        					}
        					}catch(Exception e1) {}
        			}
        			makebaoshi(hovergridx,hovergridy,makes[i]);
        		}
        		else if(i<7) {
        			if(destinations.containsKey(hovergridx+","+hovergridy)) {
        				gridv[hovergridy][hovergridx].setEffect(null);
        				destinations.remove(hovergridx+","+hovergridy);
        			}
						
					else {
						if(i==5) {
							if((hovergridx+hovergridy)%2==0) 
	    						gridv[hovergridy][hovergridx].setEffect(xuan0);
	    					else gridv[hovergridy][hovergridx].setEffect(xuan1);
							destinations.put(hovergridx+","+hovergridy,"正");
						}
						else {
							if((hovergridx+hovergridy)%2==0) 
	    						gridv[hovergridy][hovergridx].setEffect(xuan2);
	    					else gridv[hovergridy][hovergridx].setEffect(xuan3);
							destinations.put(hovergridx+","+hovergridy,"逆");
						}
					}	
        		}
        		else if(i==7){
        			if(df[hovergridy][hovergridx]!=null||db[hovergridy][hovergridx]!=null) {
        				try {
	        					grid g=db[hovergridy][hovergridx];
	        					define g0=df[hovergridy][hovergridx];
	        					if(null!=g) {
	        						lzpane.getChildren().remove(g.g);
	        						db[hovergridy][hovergridx]=null;
	        						
	        					}
	        					else if(null!=g0) {
	        						dfpane.getChildren().remove(g0.g);
	        						df[hovergridy][hovergridx]=null;
	        						
	        					}
        					}catch(Exception e1) {}
        			}
        		}
        	}
        	if(code.equals("ESCAPE")) {     		
//        		rttts[4].stop();
//        		rttts[4].setFromAngle(caozuoan[4].getRotate());
//        		rttts[4].play();
//        		ImageView iv=caozuoan[4];
//        		rttts[4].setOnFinished(e1->{iv.setRotate(0);});
        	}
        	if(code.equals("DELETE")) {
        		rttts[7].stop();
        		rttts[7].setFromAngle(caozuoan[7].getRotate());
        		rttts[7].play();
        		ImageView iv=caozuoan[7];
        		rttts[7].setOnFinished(e1->{iv.setRotate(0);});
        	}
        	if(code.equals("F11")) {
        		if(bdf.pstg.isFullScreen())
        			bdf.pstg.setFullScreen(false);
				else bdf.pstg.setFullScreen(true);
			}
        	for(int i=0;i<6;i++) {
	        	if(code.equals("F"+(i+1))) {
	        		int j=i<4?i:i+1;
	        		rttts[j].stop();
	        		rttts[j].setFromAngle(caozuoan[j].getRotate());
	        		rttts[j].play();
	        		ImageView iv=caozuoan[j];
	        		rttts[j].setOnFinished(e1->{iv.setRotate(0);});
	        	}
        	}
        	if(code.equals("SHIFT")) {	
				deletef = true;
        	}
        	
        	if(code.equals("DOWN")) {	
        		loadScript("src/blendefine/files/triango.txt");
        	}
        	
        	if(code.equals("F12")) {
        		if(fpsshowf) {
        			fpsshowf=false;
        			fpsText.setVisible(false);
        		}
        		else {
        			fpsshowf=true;
        			fpsText.setVisible(true);
        		}
        	}
        	if(code.equals("T")) {
        		nowintro--;
        	}
        });
		root.setOnMouseDragged(event -> {
		if(event.getButton()==MouseButton.PRIMARY) {
			nowmousex = event.getScreenX();
			nowmousey = event.getScreenY();
		}
		else if(event.getButton()==MouseButton.SECONDARY) {
			nowmousex = event.getScreenX();
			nowmousey = event.getScreenY();
			scaleTimeline.stop();
			if (mouseX == 0) {
				mouseX = event.getScreenX();
				mouseY = event.getScreenY();
				transX = root.getTranslateX();
				transY = root.getTranslateY();
			}

			double x = event.getScreenX() - mouseX + transX;
			double y = event.getScreenY() - mouseY + transY;


			root.setTranslateX(x);
			root.setTranslateY(y);
			}
		});
		
		root.setOnMouseMoved(e->{
			nowhoverposx=e.getX();
			nowhoverposy=e.getY();
		});

		
		
//可以使用shift+左键或者中键删除
		
			Timeline t1 = new Timeline(new KeyFrame(Duration.millis(16), e->{
				if(scaleTimeline.getStatus() == Animation.Status.STOPPED) {
					canscroll=true;
				}
				if(deletef) {//叉叉指针
					try {
						grid g=db[hovergridy][hovergridx];
						define f=df[hovergridy][hovergridx];
						if(null!=g||null!=f) {
							for (int i = 0; i <18; i++) {
								for (int j = 0; j <24; j++) {
									Color c=deletem.getPixelReader().getColor(j, i);
									blendefine.wi1.getPixelWriter().setColor(j, i,c);
								}
							}
						}
					}catch(Exception e1) {}
				}
			}));
			t1.setCycleCount(Timeline.INDEFINITE);
			t1.play();
			
			ImageView mouseiv=blendefine.mouseiv;
			stp.getChildren().remove(mouseiv);
			stp.getChildren().add(mouseiv);
			Timeline mouseanict = new Timeline(new KeyFrame(Duration.millis(1),e->{
				mouseiv.setTranslateX(nowmousex);
				mouseiv.setTranslateY(nowmousey);
			}));
			mouseanict.setCycleCount(Timeline.INDEFINITE);
			mouseanict.play();
			
		seteffect();

//		maketext(screenwidth/2, screenheight/2,1,"操作", "wsad或者点击格子移动，上方向键隐藏推动提示，下方向键重置关卡，左右方向键跳关，点此关闭");

		loadScript("src/blendefine/files/triango.txt");
		
		Timeline gamehide = new Timeline(new KeyFrame(Duration.seconds(5),e->{
			blendefine.game1.setVisible(false);
			blendefine.game1.setOpacity(0);
		}));
		gamehide.play();
		
		
		 board = new TriangoBoard();
	        ai = new TriangoMCTS();

	        statusLabel = new Label("当前玩家: 黑方");
	        statusLabel.setFont(Font.font(16));
	        
	        scoreLabel = new Label("黑方: 0, 白方: 0");
	        scoreLabel.setFont(Font.font(16));

	        drawBoard();

	        // 游戏控制按钮
	        Button passButton = new Button("放弃落子");
	        passButton.setOnAction(event -> {
	            if (gameOver || isAiThinking || aiVsAiMode) return; 
	            board.pass();
	            drawBoard();
	            updateStatus();
	            
	            // 检查游戏是否结束
	            if (board.isGameOver()) {
	                gameOver = true;
	                int[] scores = board.scores;
	                int winner = board.getWinner();
	                
	                statusLabel.setText("游戏结束! " + 
	                        (winner == TriangoBoard.BLACK ? "黑方胜" : 
	                         winner == TriangoBoard.WHITE ? "白方胜" : "平局"));
	                scoreLabel.setText("黑方: " + scores[0] + ", 白方: " + scores[1]);
	            } else if (humanVsAiMode && board.getCurrentPlayer() == TriangoBoard.WHITE) {
	                // AI回合
	                new Thread(this::aiMakeMove).start();
	            }
	        });
	        
	        Button resetButton = new Button("重新开始");
	        resetButton.setOnAction(event -> {
	            resetGame();
	        });

	        Button humanVsAiButton = new Button("人机对局");
	        humanVsAiButton.setOnAction(event -> {
	            resetGame();
	            humanVsAiMode = true;
	            aiVsAiMode = false;
	            statusLabel.setText("人机对局 - 您执黑先行");
	        });
	        
	        Button stopAiButton = new Button("停止AI");
	        stopAiButton.setOnAction(event -> {
	            aiVsAiMode = false;
	            isAiThinking = false;
	            statusLabel.setText("已停止AI");
	        });

		
	}
	
	 private void drawBoard() {
	       
	        // 绘制棋子
	        for(int i=0;i<9;i++) {
	        	for(int j=0;j<9;j++) {
	        		int zi=board.board[j][i];
	        		int[] pos= board.indexToCoordinate(j,i);
	        		int x=pos[0],y=pos[1];
	        		if(zi==0) {
	        			define g0=df[y][x];
	    				if(null!=g0) {
	    					dfpane.getChildren().remove(g0.g);
	    					df[y][x]=null;
	    				}
	        		}
	        		else if(zi==1) {
	        			if(df[y][x]==null)
	        				makebaoshi(x, y, "水");
	        		}
	        		else if(zi==2) {
	        			if(df[y][x]==null)
	        				makebaoshi(x, y, "金");
	        		}
	        	}
	        }
	       
	        // 显示分数
	        int[] scores = board.scores;
	        scoreLabel.setText("第"+board.moves+"手  "+"黑方: " + scores[0] + ", 白方: " + scores[1]);
	    }
	   
	    private void updateStatus() {
	        statusLabel.setText("当前玩家: " + (board.getCurrentPlayer() == TriangoBoard.BLACK ? "黑方" : "白方"));
	    }
	    
	    private void resetGame() {
	        board.reset();
	        gameOver = false;
	        isAiThinking = false;
	        drawBoard();
	        updateStatus();

	    }
	    
	    private void aiMakeMove() {
	        isAiThinking = true;
	        Platform.runLater(() -> {
	            statusLabel.setText("AI思考中...");
	        });
	        
	        // AI思考并落子
	        MCTSNode bestMoveNode = ai.mcts.findNextMove(board,TriangoMCTS.MCTS_ITERATIONS+1000);
	        if (bestMoveNode != null) {
	            
	            Platform.runLater(() -> {
	            	int[] move = bestMoveNode.getMove();
	                if (move[0] == -1) {
	                    // 放弃落子
	                    board.pass();
	                    statusLabel.setText("AI选择放弃落子");
	                } else {
	                	// 检查移动是否有效且非自杀
	                    if (board.getPosition(move[0], move[1]) != TriangoBoard.EMPTY 
	                    		|| board.isSuicideMove(move[0], move[1])
	                    		) {
	                        statusLabel.setText("AI移动无效，尝试其他移动");
	                        
	                        // 尝试找到下一个最佳移动
	                        MCTSNode nextBestNode = findNextBestMove(bestMoveNode, board);
	                        if (nextBestNode != null) {
	                            move = nextBestNode.getMove();
	                            if (move[0] == -1) {
	                                board.pass();
	                                statusLabel.setText("AI选择放弃落子");
	                            } else {
	                                int moveSuccess = board.makeMove(move[0], move[1]);
	                                if (moveSuccess!=-1) {
	                                    statusLabel.setText("AI移动: (" + move[0] + ", " + move[1] + ")");
	                                    TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动
	                                } else {
	                                    statusLabel.setText("AI移动失败，放弃落子");
	                                    board.pass();
	                                }
	                            }
	                        } else {
	                            statusLabel.setText("AI没有找到有效移动，放弃落子");
	                            board.pass();
	                        }
	                    } else {
	                        int moveSuccess = board.makeMove(move[0], move[1]);
	                        if (moveSuccess!=-1) {
	                        	TriangoBoard.cachedLegalMoves = board.getLegalMoves(); // 预计算合法移动
	                            statusLabel.setText("AI移动: (" + move[0] + ", " + move[1] + ")");
	                        } else {
	                            statusLabel.setText("AI移动失败，放弃落子");
	                            board.pass();
	                        }
	                    }
	                
	                }
	                
	                drawBoard();
	                
	                // 检查游戏是否结束
	                if (board.isGameOver()) {
	                    gameOver = true;
	                    int[] scores = board.scores;
	                    int winner = board.getWinner();
	                    
	                    statusLabel.setText("游戏结束! " + 
	                            (winner == TriangoBoard.BLACK ? "黑方胜" : 
	                             winner == TriangoBoard.WHITE ? "白方胜" : "平局"));
	                    scoreLabel.setText("黑方: " + scores[0] + ", 白方: " + scores[1]);
	                } else {
	                    updateStatus();
	                }
	                
	                isAiThinking = false;
	            });
	        } else {
	            Platform.runLater(() -> {
	                statusLabel.setText("AI无法找到有效移动，放弃落子");
	                board.pass();
	                drawBoard();
	                updateStatus();
	                isAiThinking = false;
	            });
	        }
	    }
	 // 查找下一个最佳移动（排除自杀移动）
	    private MCTSNode findNextBestMove(MCTSNode bestNode, TriangoBoard board) {
	        MCTSNode parent = bestNode.getParent();
	        if (parent == null) {
	            return null;
	        }
	        
	        // 获取所有子节点并按访问次数排序
	        List<MCTSNode> children = parent.getChildren();
	        children.sort((a, b) -> Integer.compare(b.getVisitCount(), a.getVisitCount()));
	        
	        // 找到下一个最佳非自杀移动
	        for (MCTSNode child : children) {
	            if (child == bestNode) {
	                continue; // 跳过当前最佳节点
	            }
	            
	            int[] move = child.getMove();
	            if (move[0] == -1) {
	                return child; // 放弃落子总是可用的
	            }
	            
	            // 检查移动是否有效且非自杀
	            if (board.getPosition(move[0], move[1]) == TriangoBoard.EMPTY 
	            		&& !board.isSuicideMove(move[0], move[1])
	                ) {
	                return child;
	            }
	        }
	        
	        return null;
	    }
	
	 private void loadScript(String path) {
		 gpane.getChildren().clear();
		 lzpane.getChildren().clear();
		 dfpane.getChildren().clear();
		 for(int i=0;i<MAPHEIGHT;i++) { 
			 for(int j=0;j<MAPWIDTH;j++) {
//				 System.out.print(j+" "+i+" ");
				 gridv[i][j]=null;
				 df[i][j]=null;
				 db[i][j]=null;
			 }
//			 System.out.println();
		 }
		 try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	            	 // 计算密铺位置
	            		readgrid(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }
	 
	 public void readgrid(String line) {
		String[] parts = line.split(" ");
 		for(int i=0;i<parts.length;i+=2) {
 			int x=Integer.parseInt(parts[i]),y=Integer.parseInt(parts[i+1]);//x在前y在后
 		 	grid nowgrid=new grid(x,y);
             ImageView triangleView=null;
             int r=(int)(Math.sqrt(Math.random())*10);
             if(nowgrid.upf==1) {      	
             	triangleView = new ImageView(triid[r]);  	
             }
             else {
             	triangleView = new ImageView(triiu[r]);  	
             }
             triangleView.setSmooth(false);
             triangleView.setTranslateY(nowgrid.posy);
             triangleView.setTranslateX(nowgrid.posx);
             triangleView.setFitWidth(300);
             triangleView.setFitHeight(260);
             gridv[y][x]=triangleView;
             
             gpane.getChildren().add(triangleView);
             triangleView.setOnMouseEntered(e1->{
             	hovergridx=nowgrid.x;
             	hovergridy=nowgrid.y;
             });                   
 	  	} 
	 }
	
	public String maptostring() {
		String maps = "";
		mapsbuf = "";
		for (int i = 0; i < MAPHEIGHT; i++) {
			for (int j = 0; j < MAPWIDTH; j++) {
				if (null != gridv[i][j])
					maps += j + " " + i + " ";
			}
		}
		maps += "\n";
		destinations.forEach((k, v) -> {
			String[] xy = k.split(",");
			mapsbuf += v + " " + xy[0] + " " + xy[1] + " ";
		});
		maps += mapsbuf;
		mapsbuf = null;
		maps += "\n";
		for (int i = 0; i < MAPHEIGHT; i++) {
			for (int j = 0; j < MAPWIDTH; j++) {
				if (null != df[i][j])
					maps += df[i][j].name + " " + j + " " + i + " ";
				if (null != db[i][j])
					maps += db[i][j].name + " " + j + " " + i + " ";
			}
		}
		System.out.println(maps);
		return maps;
	}
    private void seteffect() {
    	if(effectf) {
			ui.setEffect(ds0);
			ds4.setColor(Color.color(0, 0, 0,1));
			ds4.setOffsetY(-10*suofang);
			ds4.setRadius(20*suofang);
			ds3.setColor(Color.color(0, 0, 0));
			ds.setOffsetY(3*suofang);
			ds.setColor(Color.color(0, 0, 0));
			ds2.setSpread(0.4);
			handlepane.setEffect(new Bloom());
		}
    	else {
    		ui.setEffect(null);
    	}
	}
    
	
    private void updateSmoothPosition() {
        double deltaX =  root.getTranslateX()-targetX;
        double deltaY = root.getTranslateY()-targetY;
 
        rootv.setTranslateX(deltaX * SMOOTH_FACTOR);
        rootv.setTranslateY(deltaY * SMOOTH_FACTOR);
    }
  

    public void makebaoshiimage(String name) {
		 try {
	        if(!baoshiimagemap.containsKey(name+"0"))
   		for(int i=0;i<24;i++) {
   	        Group g0=new Group();
   	        ImageView baseImage2= new ImageView(baoshibs[i]);
   	        baseImage2.setSmooth(false);
   	        baseImage2.setFitHeight(346);
   	        baseImage2.setFitWidth(346);
   	        
   	        String yuansu=name.substring(0,1),animal=name.substring(1);
   	        Image ai=null;
   	        if(animal.equals("")) {

   	        }
   	        else {
	    	        if(baoshianimalmap.containsKey(animal)) ai=baoshianimalmap.get(animal);
	    	        else {
	    	        	ai=new Image(getClass().getResourceAsStream("definepic/宝石"+animal+".png"));
	    	        	baoshianimalmap.put(animal, ai);
	    	        }
   	        }
   	 	   	ImageView middleImage2 = new ImageView(ai);
   	 	   	middleImage2.setSmooth(false);
   	 	   	middleImage2.setFitHeight(346);
   	 	  	middleImage2.setFitWidth(346);
   	 	  	
   	 	  	if(yuansu.equals("空"))
   	 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+animal+".png"))));
   	 	  	else if(yuansu.equals("字"))
   	 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png"))));
   	 	  	else {
   	 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+yuansu+".png"))));
   	 	  	}
   	 	  	
   	        g0.getChildren().addAll(middleImage2,baseImage2);
   	        g0.setEffect(definecolorblend);      
   	        g0.setRotate(15*i);
   	        //设置多边形中心为173，173
   	        Polygon p=new Polygon(23,86,0,0,23,86,323,86,346,0,323,86,173,346);
   	        p.setRotate(15*i);
   	        Group g1=new Group(g0);
   	        g1.setClip(p);      
   	        g1.setEffect(defineli);
   	        g1.setRotate(-15*i);
   	        Group g3=new Group(new ImageView(defineud),g1);
   			Image si=g3.snapshot(params1, null);
   			baoshiimagemap.put(name+i, si);		        	
//   			ImageView bsi=new ImageView(si);
//   			bsi.setScaleX(0.3);
//   			bsi.setScaleY(0.3);
//   			bsi.setTranslateX(100);
//   			bsi.setTranslateY(60*i);
//   			mubanleft.getChildren().add(bsi);
   		}
		}catch(Exception e1) {}
	        
	}
	class define{
		Group g;
		int x;
		int y;
		int upf;
		int face;
		int nowpic;
		int iscf=0;
		int zang;
		String name,key;
		double nowangle;
		guanka gk;
		int upx,upy,leftx,lefty,rightx,righty;

		
		define(int x, int y,Group g,String name,String key) {
			this.x=x;
			this.y=y;
			this.g=g;
			this.name=name;
			this.key=key;
//			parentGroup.calculateArithmeticMean();
		}
		define(int x, int y,Group g,int upf,int face,int iscf,int nowpic,double nowangle,int zang,int upx,int upy,int leftx,int lefty,int rightx,int righty,String name,String key) {
			this.x=x;
			this.y=y;
			this.g=g;
			this.name=name;
			this.key=key;
			this.face=face;
			this.iscf=iscf;
			this.nowangle=nowangle;
			this.nowpic=nowpic;
			this.upf=upf;
			this.zang=zang;
			this.upx=upx;
			this.upy=upy;
			this.leftx=leftx;
			this.lefty=lefty;
			this.rightx=rightx;
			this.righty=righty;
		}
	
		 public define clone(define this) {
			 Group g1=new Group();
			 g.getChildren().iterator().forEachRemaining(e->{
				 ImageView iv=new ImageView(((ImageView) e).getImage());
				 g1.getChildren().add(iv); 
			 });
			 g1.setEffect(g.getEffect());
			 g1.setTranslateX(x*150-23);
		        if((x+y)%2==0) {
		        	g1.setTranslateY(y*260-85);
		        }
		        else {
		        	g1.setTranslateY(y*260);
		        }
		     g1.setRotate(g.getRotate());
			return new define(x,y,g1,upf,face,iscf,nowpic,nowangle,zang,upx,upy,leftx,lefty,rightx,righty, name, key);
		 }
	}
    public define makebaoshi(int x,int y,String name){
    	if(x>=0&&x<MAPWIDTH&&y>=0&&y<MAPHEIGHT) {
    		makebaoshiimage(name);
    		int zang=(int)(Math.random()*10);
	        Group g2=new Group(new ImageView((x+y)%2==0?baoshiimagemap.get(name+"0"):baoshiimagemap.get(name+"12")));
	        g2.setEffect(zangs[zang]);
	        g2.setRotate((x+y)%2==0?0:180);
	       
			define d=new define(x,y,g2,name,x+","+y+","+System.currentTimeMillis());
//			dfhistory.get(nowplayshixupos).put(d.key,d);
			//不需要放入
			  d.zang=zang;
	        df[y][x]=d;
	        dfpane.getChildren().add(g2);
	        g2.setTranslateX(x*150-23);
	        if((x+y)%2==0) {
	        	d.face=0;
	        	d.upf=0;
	        	g2.setTranslateY(y*260-85);
	        }
	        else {
	        	d.face=3;
	        	d.upf=1;
//	        	((Group)(g2.getChildren().get(0))).getChildren().get()
	        	g2.setTranslateY(y*260);
	        }
			return d;
    	}
    	return null;
    }


    public void scaleroot(double deltaY) {
        double currentScale = root.getScaleX();
        double scaleFactor = (deltaY > 0) ? 1.25 : 0.8;
        double targetScale = currentScale * scaleFactor;
        targetScale=(double)Math.round(targetScale*1000)/1000;
        // 计算鼠标坐标
        scale=targetScale;
        

        double mouseX = nowmousex - screenwidth / 2;
        double mouseY = nowmousey - screenheight / 2;
        // 计算目标平移值
        double currentTranslateX = root.getTranslateX();
        double currentTranslateY = root.getTranslateY();
        double targetTranslateX = currentTranslateX + (1 - scaleFactor) * (mouseX - currentTranslateX);
        double targetTranslateY = currentTranslateY + (1 - scaleFactor) * (mouseY - currentTranslateY);

        // 创建新动画
    	canscroll=false;
        scaleTimeline.getKeyFrames().setAll(
            new KeyFrame(Duration.seconds(0.5),
                new KeyValue(root.scaleXProperty(), targetScale, Interpolator.LINEAR),
                new KeyValue(root.scaleYProperty(), targetScale, Interpolator.LINEAR),
                new KeyValue(root.translateXProperty(), targetTranslateX, Interpolator.LINEAR),
                new KeyValue(root.translateYProperty(), targetTranslateY, Interpolator.LINEAR)
            )
        );
        if(scaleTimeline.getStatus() == Animation.Status.STOPPED)
        	scaleTimeline.play();
        
        scaleTimeline.setOnFinished(e->{
        	canscroll=true;
        });

        
 
    }
	public static void setBordersToAllPanes(Parent root) {
        // 遍历所有子节点
        for (Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof Pane) {
                applyBorder((Pane) node);
            }
            // 递归处理子节点
            if (node instanceof Parent) {
                setBordersToAllPanes((Parent) node);
            }
        }
    }
    
    private static void applyBorder(Pane pane) {
        // 定义边框样式
        BorderStroke stroke = new BorderStroke(
            Color.RED,                   // 颜色
            BorderStrokeStyle.SOLID,       // 样式（SOLID/DASHED/DOTTED）
            CornerRadii.EMPTY,             // 圆角
            new BorderWidths(10)            // 宽度（左/右/上/下）
        );
        pane.setBorder(new Border(stroke));
    }
    // 递归遍历所有子节点
    public void optimizeScene(Scene scene) {
        optimizeNode(scene.getRoot(), scene.getWindow());
    }
    
    public void optimizeNode(Node node, Window ownerWindow) {
        // 跳过非渲染节点和不可见节点
        // 计算屏幕坐标系下的节点边界
    	
        Bounds screenBounds = node.localToScreen(node.getBoundsInLocal());
        // 判断是否完全在屏幕外
        if (isCompletelyOffscreen(screenBounds)) {
        	node.setVisible(false);
            return; // 隐藏后无需处理子节点
        } 
        else {
        	node.setVisible(true);
        }
        // 递归处理子节点（使用BFS避免栈溢出）
        try {
        	if(node instanceof Parent) {
		        for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
		            optimizeNode(child, ownerWindow);
		        }
        	}
        }catch(Exception e) {}
    }

    // 判断是否完全在屏幕外（包含所有屏幕）
    private boolean isCompletelyOffscreen(Bounds nodeBounds) {
        // 获取所有屏幕的可见区域
    	double 
           x0= nodeBounds.getMinX(),
           y0= nodeBounds.getMinY(), 
           x1= nodeBounds.getWidth(), 
           y1= nodeBounds.getHeight();
    	double outer=300*suofang;
        if(x0>screenwidth+outer||x0+x1<-outer||y0>screenheight+outer||y0+y1<-outer) return true;
        return false;   
    }
    //点与线段判断碰撞
    static class Point {
        double x, y;
        Point(double x, double y) {this.x = x;this.y = y;}
    }
    static class Segment {
        Point start, end;
        Segment(Point start, Point end) {this.start = start;this.end = end;}
    }
    private static boolean isOverlap(Segment s1, Segment s2) {
        return Math.max(s1.start.x, s1.end.x) >= Math.min(s2.start.x, s2.end.x) &&
               Math.max(s2.start.x, s2.end.x) >= Math.min(s1.start.x, s1.end.x) &&
               Math.max(s1.start.y, s1.end.y) >= Math.min(s2.start.y, s2.end.y) &&
               Math.max(s2.start.y, s2.end.y) >= Math.min(s1.start.y, s1.end.y);
    }
    private static double crossProduct(Point o, Point a, Point b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
    }
    private static boolean isCrossing(Segment s1, Segment s2) {
        double c1 = crossProduct(s1.start, s1.end, s2.start);
        double c2 = crossProduct(s1.start, s1.end, s2.end);
        double c3 = crossProduct(s2.start, s2.end, s1.start);
        double c4 = crossProduct(s2.start, s2.end, s1.end);
        return (c1 * c2 <= 1e-8) && (c3 * c4 <= 1e-8);
    }
    private static boolean isPointOnSegment(Point p, Segment s) {
        if (p.x < Math.min(s.start.x, s.end.x) - 1e-8 || 
            p.x > Math.max(s.start.x, s.end.x) + 1e-8 ||
            p.y < Math.min(s.start.y, s.end.y) - 1e-8 || 
            p.y > Math.max(s.start.y, s.end.y) + 1e-8) {
            return false;
        }
        double t = ((p.x - s.start.x) * (s.end.x - s.start.x) + 
                   (p.y - s.start.y) * (s.end.y - s.start.y)) / 
                  (Math.pow(s.end.x - s.start.x, 2) + Math.pow(s.end.y - s.start.y, 2));
        return t >= -1e-8 && t <= 1 + 1e-8;
    }
    // 对外接口方法
    public static boolean isIntersect(Segment s1, Segment s2) {
        if (!isOverlap(s1, s2)) return false;
        if (isCrossing(s1, s2)) return true;
        
        return isPointOnSegment(s2.start, s1) || isPointOnSegment(s2.end, s1) ||
               isPointOnSegment(s1.start, s2) || isPointOnSegment(s1.end, s2);
    }
    public static boolean doShapesIntersect(List<Segment> shape1, List<Segment> shape2) {
        for (Segment s1 : shape1) {
            for (Segment s2 : shape2) {
                if (isIntersect(s1, s2)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**d=0向右下弹出，1下，2左下，3左，4左上，5上，6右上，7右 */
    public Group maketext(double x,double y,int d,String title,String word) {
    	return maketext(x,y,550*suofang,((word.length()-1)/20)*43*suofang+((null!=title)?110*suofang:60*suofang),d,title,word);
    }
    /**d=0向右下弹出，1下，2左下，直到7向右 */
    public Group maketext(double x,double y,double w,double h,int d,String title,String word) {
    	return maketext(x,y,w,h,d,title,word,null);
    }
    /**x y弹出点 x y=0时在正中间 w h对话框宽高 d=0向右下弹出，1下，2左下，直到7向右 */
    public Group maketext(double x,double y,double w,double h,int d,String title,String word,longzhua lz) {
    	Group tx=new Group();
    	tx.setEffect(ds5);
    	Rectangle r=new Rectangle(w,h), r1=new Rectangle(w,h);
    	double ofx=0,ofy=0;
    	if(d==1) {ofx=w/2;}
    	else if(d==2) {ofx=w;}
    	else if(d==3) {ofx=w;ofy=h/2;}
    	else if(d==4) {ofx=w;ofy=h;}
    	else if(d==5) {ofx=w/2;ofy=h;}
    	else if(d==6) {ofy=h;}
    	else if(d==7) {ofy=h/2;}
    	r.setTranslateX(x-ofx);r.setTranslateX(y-ofy);
    	SnapshotParameters pa = new SnapshotParameters();
    	pa.setFill(Color.TRANSPARENT);
    	pa.setViewport(new Rectangle2D(r.getTranslateX(), r.getTranslateY(),r.getWidth(),r.getHeight()));
    	ImageView iv=new ImageView(jiaocheniv.snapshot(pa, null));
    	iv.setEffect(new Bloom());
    	r1.setScaleX(0);
    	r1.setScaleY(0);
    	tx.setClip(r1);
    	r1.setEffect(gausb);
    	tx.getChildren().add(iv);
    	FlowPane fp=new FlowPane();
    	fp.setPadding(new Insets(10*suofang,10*suofang,10*suofang,10*suofang));
//    	fp.setHgap(5*suofang);
//    	fp.setVgap(5*suofang);
    	tx.getChildren().add(fp);
    	fp.setPrefSize(w, h);
    	ui.getChildren().add(tx);
    	tx.setTranslateX(x-ofx);tx.setTranslateY(y-ofy);
//    	r1.setTranslateX(-ofx);r1.setTranslateY(-ofy);
    	Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.3),
    			new KeyValue(r1.translateXProperty(),0),new KeyValue(r1.translateYProperty(),0),
    			new KeyValue(r1.scaleXProperty(),1),new KeyValue(r1.scaleYProperty(),1)));
    	t.play();
    	double ofx1=ofx,ofy1=ofy;
    	tx.setOnMouseClicked(e->{
    		Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.3),
    				new KeyValue(r1.translateXProperty(),-ofx1),new KeyValue(r1.translateYProperty(),-ofy1),
    				new KeyValue(r1.scaleXProperty(),0),new KeyValue(r1.scaleYProperty(),0)));
        	t1.play();
        	t1.setOnFinished(e1->{
        		ui.getChildren().remove(tx);
        		nowintro++;
        	});
    	});
    	
    	List<Node> cs=fp.getChildren();
       
    	if(null!=title) {
	    	Label t1=new Label(title);
	    	t1.setPrefWidth(w-10*suofang);
	//    	t1.setWrappingWidth(w-20*suofang);
	        t1.setFont(f3);
	        t1.setTextFill(Color.BROWN);
	    	cs.add(t1);
    	}
    	if(null!=word) {
	    	Label t2=new Label(word);
	    	t2.setPrefWidth(w-10*suofang);
	//    	t2.setPrefHeight(50);
	    	t2.setWrapText(true);
	    	t2.setTextFill(Color.BLACK);
	    	t2.setFont(f25);
	    	cs.add(t2);
    	}
    	if(null!=lz) {
//    		TextField tfx=new TextField(),tfy=new TextField(),tfr=new TextField();
    		shixuan sa=shixuuses.get(nowplayshixupos+","+lz.machineindex);
    		sa.lbx=new Label();
    		sa.lbx.setPrefWidth(60*suofang);
    		sa.lbx.setWrapText(true);
    		sa.lbx.setTextFill(Color.BLACK);
    		sa.lbx.setFont(f25);
	    	cs.add(sa.lbx);
	    	sa.lbx.setText("横  "+NumberToChinese.toChinese(sa.aimx));

	    	sa.lby=new Label();
	    	sa.lby.setPrefWidth(60*suofang);
	    	sa.lby.setWrapText(true);
	    	sa.lby.setTextFill(Color.BLACK);
	    	sa.lby.setFont(f25);
	    	cs.add(sa.lby);
	    	sa.lby.setText("纵  "+NumberToChinese.toChinese(sa.aimy));
	    	
	    	sa.lbr=new Label();
	    	sa.lbr.setPrefWidth(60*suofang);
	    	sa.lbr.setWrapText(true);
	    	sa.lbr.setTextFill(Color.BLACK);
	    	sa.lbr.setFont(f25);
	    	cs.add(sa.lbr);
	    	sa.lbr.setText("角  "+NumberToChinese.toChinese(sa.aimr));
    	}
    	return tx;
    }
    
    public void enterintro() {
    	intro=new Timeline(new KeyFrame(Duration.millis(8),e->{
    		if(ui.getChildren().size()==3) {
	    		switch(nowintro) {
	    			case 0:{maketext(screenwidth/2, screenheight/2,1,"回忆", "离那阿城还有一段距离（请点击此窗口关闭，按T或者连点三次右键可以再次打开）");}break;
	    			
	    		}
    		}
    	}));
    	intro.setCycleCount(Timeline.INDEFINITE);
    	intro.play();
    }
    public void makefxgrid(int px,int py,HashSet<Point2D> gs) {
    	gs.forEach(e->{
    		makefxgrid((int)(px+e.getX()),(int)(py+e.getY()),0);
    	});
    }
    public void makefxgrid(int px,int py) {
    	makefxgrid(px,py,0);
    }
    public void makefxgrid(int px,int py,int c) {
    	grid gd = new grid(px,py);
		Polygon t = new Polygon(gd.posx + gd.ptx1, gd.posy + gd.pty1, gd.posx + gd.ptx2,
				gd.posy + gd.pty2, gd.posx + gd.ptx3, gd.posy + gd.pty3);
		if(c==0) {
			RadialGradient gradient = new RadialGradient(
	                0, 0,                       // 焦点角度和距离
	                px*150+150, py*260+87+87*((px+py)%2),                   // 焦点位置（中心）
	                120,               // 半径
	                false,                       // 按比例缩放
	                CycleMethod.NO_CYCLE,       // 循环方式
	                new Stop(0, Color.color(1,1,1,0)),
	                new Stop(0.4, Color.color(0,0,0,0.3)),
	                new Stop(0.8, Color.color(0,0,0,0.7)) 
	        );
			t.setFill(gradient);
			fxpane.getChildren().add(t);
//			t.setFill(Color.color(1, 1, 1, 0.3));
		}
		if(c==1) {
			RadialGradient gradient = new RadialGradient(
	                0, 0,                       // 焦点角度和距离
	                px*150+150, py*260+87+87*((px+py)%2),                   // 焦点位置（中心）
	                180,               // 半径
	                false,                       // 按比例缩放
	                CycleMethod.NO_CYCLE,       // 循环方式
	                new Stop(0, Color.color(1,1,1,0)),
	                new Stop(0.3, Color.color(1,1,1,0.8)),
	                new Stop(0.6, Color.color(1,1,1,0)) 
	        );
			t.setFill(gradient);
			fxpane1.getChildren().add(t);
//			t.setFill(Color.color(1, 1, 1, 0.3));
		}
		else if(c==2) {
			t.setFill(Color.color(0.4, 1, 0.6, 0.3));
			fxpane.getChildren().add(t);
		}
//		t.setEffect(gausb);
		
    }
    public void destroy() {
        // 1. 停止所有动画效果
        stopAllAnimations();
        // 2. 清理图像资源
        clearImageResources();
        // 3. 清空场景布局
        clearSceneGraph();
        // 4. 关闭线程池
        shutdownExecutorService();
        // 5. 移除事件监听器
        removeEventHandlers();
        // 6. 销毁自定义游戏对象
        destroyCustomObjects();
        // 7. 清理静态资源（如有需要）
//        cleanupStaticResources();
    }
    private void stopAllAnimations() {
        // 停止所有正在运行的动画
        if (scaleTimeline != null && scaleTimeline.getStatus() != Animation.Status.STOPPED) {
            scaleTimeline.stop();
            scaleTimeline=null;
        }
        if (process != null && process.getStatus() != Animation.Status.STOPPED) {
            process.stop();
            process=null;
        }
        if (pixer != null && pixer.getStatus() != Animation.Status.STOPPED) {
        	pixer.stop();
        	pixer=null;
        }
        if (intro != null && intro.getStatus() != Animation.Status.STOPPED) {
        	intro.stop();
        	intro=null;
        }
        try {
       }catch(Exception e) {}
        // 添加其他动画实例的停止逻辑...
    }
    private void clearImageResources() {
        // 清除所有ImageView的图像引用
        if (rootv != null) {
            rootv.setImage(null);
        }
//        if (root != null) {
//            alli.getChildren().forEach(node -> {
//                if (node instanceof ImageView) {
//                    ((ImageView) node).setImage(null);
//                }
//            });
//        }
        // 显式置空Image对象（如果持有直接引用）
//        backgroundImage = null;
//        buttonImage = null;
        // 添加其他图像资源的清理...
    }
    private void clearSceneGraph() {
        // 清空根节点所有子元素
        if (root != null) {
            root.getChildren().clear();
        }
        // 显式置空关键布局容器
        game = null;
        stp = null;
        // 添加其他布局容器的清理...
    }
    private void shutdownExecutorService() {
        // 优雅关闭线程池
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    private void removeEventHandlers() {
        // 移除根节点的事件监听器
        if (root != null) {
            root.setOnMouseClicked(null);
            root.setOnKeyPressed(null);
            // 添加其他事件处理程序的移除...
        }
        // 移除自定义对象的事件监听器（需保持引用）
    }
    private void destroyCustomObjects() {
        // 销毁所有游戏对象
    }
}
