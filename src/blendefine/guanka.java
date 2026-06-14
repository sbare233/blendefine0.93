package blendefine;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fx.AnimatedWave1;
import fx.AshEffect;
import fx.DragonLightning;
import fx.RealisticFlameEffect;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Window;
import javafx.util.Duration;
import tool.NumberToChinese;



public class guanka {
//----------本地南瓜种植基地-----------//

     //    //      //      \\     
    {{}}  {{}}   {{{}}}    {{}}
    
    //      //     //     \\     
   {{}}   {{{}}}  {{}}   {{{}}}
    
//------------禁止私自采摘------------//
//很帅的bug一律写帅方便搜??
	static final int MAPWIDTH = 81; // 地图宽度
	static final int MAPHEIGHT = 49; // 地图高度
	static final String SAVE_DIR = "saves"; // 存档根目??
	private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	Scene scene=blendefine.scene;
	double screenwidth = blendefine.screenwidth, screenheight =blendefine.screenheight;
	double suofang = screenheight / 1080;
	double scale = 0.4 * suofang;
	
	public Image rooti, zhengjini, blend1i,blend2i,taiyangi,jinwui,
			defineud= new Image(getClass().getResourceAsStream("definepic/包圆.png")),
			defineguangzhao= new Image(getClass().getResourceAsStream("definepic/光照.png")),
    		
			defineg = new Image(getClass().getResourceAsStream("definepic/宝石箍.png")),
			defineg1 = new Image(getClass().getResourceAsStream("definepic/宝石箍1.png")),
			hyzji = new Image(getClass().getResourceAsStream("otherpic/hunyuanzhengjin.png")),
			deletem = new Image(getClass().getResourceAsStream("mousepic/delete.png")),
			hunyuanzhengjini = new Image(getClass().getResourceAsStream("otherpic/hunyuanzhengjin.png")),
			blackdown=	new Image(getClass().getResourceAsStream("gridpic/blackdown.png")),
			blackup=	new Image(getClass().getResourceAsStream("gridpic/blackup.png")),
//    		shizhuani=new Image(getClass().getResourceAsStream("shizhuan.png"))
			shizhuani = new Image(getClass().getResourceAsStream("otherpic/shizhuan.png"), screenwidth, screenheight,false, false),
					longzhuax1i,longzhuax2i,longzhuani,
					zhuquedown1i,zhuquedown2i,zhuqueup10i,zhuqueup20i,zhuquemiddle1i,zhuquemiddle2i,zhuqueupi,zhuquefire,
					xuanwuu1,xuanwuu2,xuanwu1,xuanwu2,xuanwus1,xuanwus2,
					jiulongguani,huangjii,baihui0,baihui1,baihua,dragonli,
			settingi= new Image(getClass().getResourceAsStream("uipic/设置板1.png")),
					
			shixubi = new Image(getClass().getResourceAsStream("shixupic/暂停a1.png")),
			shixuboi= new Image(getClass().getResourceAsStream("shixupic/时序拨1.png")),
			jiaochengi= new Image(getClass().getResourceAsStream("uipic/教程纸1.png"),screenwidth,screenheight,false,false),
			helpi= new Image(getClass().getResourceAsStream("uipic/帮助1.png"),100*suofang,100*suofang,false,false);
		    
	Polygon checkcoli,checkcoli0;
	Circle columnc;
	
		public Image[] triiu = new Image[10], triid = new Image[10], zhengjins = new Image[10],wuzang=new Image[6],baihuis=new Image[10],
			baoshibs = new Image[24], zhuabis = new Image[12], zhuazis = new Image[12],
			zhuazigs = new Image[12],shixuyys = new Image[24],luoshus=new Image[10],shixuani = new Image[12],
			fuzhii = new Image[10],definezang=new Image[10];
		ImageView rootv = new ImageView(), alli = new ImageView(),setting = new ImageView(settingi),
						zhengjin = new ImageView(hunyuanzhengjini), zhengjin1 = new ImageView(), lz1 = null,
						shixuyinyu,mubanl1,mubanl2,jiaocheniv=new ImageView(jiaochengi),help=new ImageView(helpi),
								ilongzhua,ixuanwu,izhuque,ihunyuan,iwuzang,ijiulongguan,iliuhe,ibaihu,shizhuan;
						
	Label shixulookl,fushil;
		
	Blend definecolorblend = new Blend(),defineli=new Blend();
	ImageView [] caozuoan=new ImageView[10];
	ImageView[][] gridv = new ImageView[MAPHEIGHT][MAPWIDTH];
	Blend[] zangs=new Blend[10];
	shixuan[] shixuans = new shixuan[12],charans=new shixuan[12];
	ConcurrentHashMap<String,shixuan> shixuuses = new ConcurrentHashMap<String,shixuan>();
	ConcurrentHashMap<String,Image>mostpic=new ConcurrentHashMap<String,Image>(), charsi = new ConcurrentHashMap<String,Image>(),charsil = new ConcurrentHashMap<String,Image>(),
			baoshiimagemap = new ConcurrentHashMap<String,Image>(),charsic= new ConcurrentHashMap<String,Image>(),charsicl= new ConcurrentHashMap<String,Image>(),
			baoshianimalmap=new ConcurrentHashMap<String,Image>(),baoshifxmap=new ConcurrentHashMap<String,Image>();
	ConcurrentHashMap<String,Integer> animalindex = new ConcurrentHashMap<String,Integer>();
	ConcurrentHashMap<String,Color> rectcolor = new ConcurrentHashMap<String,Color>();
	ConcurrentHashMap<String,Long> offscreenTimestamps= new ConcurrentHashMap<String,Long>();
	ConcurrentHashMap<String,Circle> colics= new ConcurrentHashMap<String,Circle>();
	ConcurrentHashMap<String,Polygon> ts=new ConcurrentHashMap<String,Polygon>();
	ConcurrentHashMap<String,Point2D> iniitemplaces=new ConcurrentHashMap<String,Point2D>();
	HashSet<KeyCode> scenekeys= new HashSet<KeyCode>();
	HashSet<Integer> playedfu= new HashSet<Integer>();
	HashSet<grid> selectitem=new HashSet<grid>();
	HashSet<shixuan> selectitem1=new HashSet<shixuan>(),selectitem11=new HashSet<shixuan>();
	LinkedList<grid> machines = new LinkedList<grid>();
	ConcurrentHashMap<String,Integer> machineskey=new ConcurrentHashMap<String,Integer>();
	ArrayList<String> dirs=new ArrayList<String>();//用于define类中的宝石朝??
	HashSet<String> longzhuacanlen=new HashSet<String>(),longzhuacanlen1=new HashSet<String>();//龙爪延申选取范围
	HashSet<ImageView> addedmachines=new HashSet<ImageView>();
	HashSet<ImageView> addedgems=new HashSet<ImageView>();
	Timeline refbanlistTimeline;
	TranslateTransition ssoutTransition, ssinTransition, ljoutTransition, ljinTransition;
	List<ImageView> luoshusList = new ArrayList<>();
	ArrayList<HashMap<String,grid>> dbhistory=new ArrayList<>(),db0history=new ArrayList<>();
	ArrayList<HashMap<String,define>> dfhistory=new ArrayList<>();
	ArrayList<Node>roothistory=new ArrayList<>();
	public boolean leftmubanf = false, rightmubanf = false,shixuoutf=false,lingjvhef=false, canscroll = true,
			canmakecloneblend = true, canputf = false,pause=true,deletef=false,stopputxuanwuf=false,inimachinedrag=false,
			effectf=true,lowgrahf=false,pixelf=false,canpasusef=true,longzhuadargf=true,canselectshixuf=true,canhelpmove=false,
			niulzf=true,fpsshowf=false,settingshowf=false,isdraggingf=false,copyf=false,helpmovex=true,helpmovey=true;
	int frameCount = 0,isopenfinishf = blendefine.isopenfinishf,nowpage = 0, pixelsize = 4,
			hovergridx = -1, hovergridy = -1,shouldchangepicf=0,nowlookshixu,nowlookshixuy,maxshixu,nowplayshixupos,shixulength,
					grabgridminx=0,grabgridminy=0,lastcanputgridx=0,lastcanputgridy=0,
					xrnowtimef,lastdelhx=-1,lastdelhy=-1,wuzangrollf,lastshixu,nowboindex,nowintro;
	private long pressStartTime;
	private Text fpsText = new Text(10, 20, "FPS: 0"),definenumbert= new Text("999");
;
	double mouseX = 0, mouseY = 0, transX = 0, transY = 0, nowmousex =0, nowmousey = 0,nowhoverposx,nowhoverposy,
			nowshixutransy,lastframecount;

	// 在类中声明动画对??
	private double scalemubanl = screenheight / 688 ;
	public double nowpicvalue=-20*suofang;
	public double playrate=4;
	//机器运行速度 加速火??
	double maxoffsetx = 200 * scalemubanl - screenwidth / 2 * (1 - scale),
			maxoffsety = 50 * scalemubanl - screenheight / 2 * (1 - scale),
//			minoffsetx = maxoffsetx - 200 * scalemubanl - 12300 * scale + screenwidth,
					minoffsetx = maxoffsetx - 200 * scalemubanl - 15300 * scale + screenwidth,
			minoffsety = maxoffsety - 250 * scalemubanl - 12740 * scale + screenheight;
	public StackPane root,game=new StackPane() ,ui=new StackPane(), stp=blendefine.stp,ls1=new StackPane();;
	DropShadow ds1 = new DropShadow();
	DropShadow ds = new DropShadow();
	DropShadow ds0 = new DropShadow(30*suofang, 20*suofang, 10*suofang, Color.color(0, 0, 0, 0.9));
	DropShadow ds4 = new DropShadow();
	DropShadow ds3 = new DropShadow();
	DropShadow ds2 = new DropShadow(58*suofang, 3*suofang,0, Color.color(0, 0, 0, 0.6));
	DropShadow ds5 = new DropShadow(30*suofang, 20*suofang,32*suofang, Color.color(0, 0, 0, 0.96));
	DropShadow ds6 = new DropShadow(100*suofang,0,0, Color.WHITE);
	InnerShadow ds8 = new InnerShadow(50*suofang, 0,0, Color.color(1, 1, 1, 1));
	GaussianBlur gausb=new GaussianBlur(),gsb1=new GaussianBlur(12);
	Bloom bloom = new Bloom();
	
	public Pane gpane,blpane,lzpane,dfbpane,dfupane,connectpane,hypane,dfpane,udpane,handlepane,fxpane,
	rootlis,mubanleft,mubanright,shixu,openpane=blendefine.openpane,settingpane,savepane;

	Group shixubo,zantb=new Group(),tx;
	GridPane sxgp=new GridPane();
	public Rotate leftclock, leftanti, rightclock, rightanti, downclock, downanti;
	public Rotate[] rolls;
	public int[][] rolln = { { 0, -1 }, { -1, 0 }, { -1, 0 }, { 0, 1 }, { 0, 1 }, { 0, -1 } };
	public int scaleindex = 1,nowhide=0,reviewintrof=0;
	public String nowgrabname;
	public String allintro;
	public double[] scales = { 0.19, 0.24, 0.3, 0.37, 0.5, 0.7, 1 };
	private double orgSceneX, orgSceneY,initialCenterX, initialCenterY,initialDistance,dragOffsetX, dragOffsetY;;
	
    private Rectangle selectionBox,selectionBox1;
    List<Rectangle> rects=new ArrayList<>();;
    private double startX, startY,shixuselectstartX,shixuselectstartY;
    int nowshixugraboffy,mingrabx,mingraby;
    private Map<Node, Effect> originalEffects = new HashMap<>(),originalEffects1 = new HashMap<>();
    //选择??
	
//	grid[][] grids = new grid[MAPHEIGHT][MAPWIDTH];
	@SuppressWarnings("unchecked")
	List<Segment>[][] colis=new List[MAPHEIGHT][MAPWIDTH];//碰撞体数组，实时更新
	grid[][] db = new grid[MAPHEIGHT][MAPWIDTH],//存储上层机器
			db0 = new grid[MAPHEIGHT][MAPWIDTH];//存储白虎 玄武负和朱雀??
	define[][] df=new define[MAPHEIGHT][MAPWIDTH];//宝石
//	HashMap<String,Line> conns=new HashMap<>(); //存储连接
	Node nowgrab,nowgrab1,nowgrab2,nowgrab3;
	longzhua nowniulz;
	// 新增双缓冲相关变??
	private WritableImage backBuffer;
	RotateTransition[] rttts=new RotateTransition[8];
	private Timeline charlight,opentimeline=blendefine.opentimeline,process=new Timeline(),//所有机器运作的时间轴process
			scaleTimeline = new Timeline(),showspawnnumbert,hidespawnnumbert,caltime,helpmovetime,
			nowgrabmovet,putxuanwutimeline,sst,niulzt,savet,pixer,intro,reviewintro, smoothTimeline = new Timeline(
        new KeyFrame(Duration.millis(6), e -> updateSmoothPosition())
    );
    private double targetX = 0, targetY = 0;
    private final double SMOOTH_FACTOR = 0.08;
    private xuanwu lastputxuanwu;
    AnimatedWave1 anw,anw1;
    RealisticFlameEffect firee;
    DragonLightning dragonl;
//    Bloom xuanwuputbloom=new Bloom();
    
    blendefine bdf;
	SnapshotParameters params = new SnapshotParameters(),params1 = new SnapshotParameters();
	
//	private Pane tocap;
//    private ImageView overlayImageView;
//    WritableImage overlayedImage;
//    private List<WritableImage> snapshots = new ArrayList<>();
//    private List<Rectangle> highlightAreas = new ArrayList<>();
    
    public HashSet<Timeline> tms=new HashSet<Timeline>();//供回收的绝大多数时间线；
	ConcurrentHashMap<String,String> hunyuancft=new ConcurrentHashMap<String,String>(),
			wuzangcft=new ConcurrentHashMap<String,String>(),wuzangcftni=new ConcurrentHashMap<String,String>();
	ConcurrentHashMap<String,HashSet<String>> findcraft=new ConcurrentHashMap<>(),
			findmaterial=new ConcurrentHashMap<>(),findcraftgraph=new ConcurrentHashMap<>();	
    private HashMap<String, String[]> sealMap = new HashMap<>();
    private Set<String> basicElements = new HashSet<String>(Arrays.asList("阴", "阳", "金", "木", "水", "火", "土"));
    
    @SuppressWarnings("static-access")
	Font f5=bdf.f0,f3=bdf.f3,f25=bdf.f25,f2=bdf.f2,f200=bdf.f200,ff1=bdf.f01;
    int language;
    i18n i18n;
    
    HashSet<String> qixings=new HashSet<String>(),qizis=new HashSet<String>();
   	public String[] 
   		shixuanname = { "取","围","逸","安","始","递","纵","蓄","抄","扔"},
   		shixuannamee = { "Quarry","Whirl","Emit","Abort","Start","Duplicate","Zero","Xtreme","Copy","Remove"},
   				shixuanintro= {"抓取","围转","释放","停止","起始","循环","前进","后退","复制",""},
   		ncis={"Q","W","E","A","S","D","Z","X","C","R"};
       int level=-1;//当前关卡
	String[] target=new String[6];//合成目标，需要实时检??
	HashSet<String> banlist=new HashSet<String>();//禁用的机??
	ConcurrentHashMap<String,Integer>baoshis=new ConcurrentHashMap<String,Integer>();//所有宝石，包括宝石??
	ConcurrentHashMap<String,Integer>hasbaoshis=new ConcurrentHashMap<String,Integer>();//记录当前剩余宝石
	int baoshiiniposition=0;
	Timeline listentarget;
	puzzlesolver pzs;
    /**
     * @param level 当前关卡
     * @param target 需要合成的目标
     * @param banlist 禁用的机??
     * @param baoshis 可用的宝??
     * @param pzs 复制合成??
     */
    public void inistage(int level, String[] target, HashSet<String> banlist,
                         HashMap<String, Integer> baoshis,puzzlesolver pzs,int language,i18n i18n) {
        this.language=language;
        this.i18n=i18n;
        shixuanintro=i18n.fushis[language];
        this.level = level;
        for(int i=0;i<6;i++) {
            this.target[i] = target[i];
        }
        
        
        this.banlist = (banlist == null) ? this.banlist : new HashSet<>(banlist);
        this.baoshis = (baoshis == null) ? this.baoshis : new ConcurrentHashMap<>(baoshis);
        this.hasbaoshis = (baoshis == null) ? this.baoshis : new ConcurrentHashMap<>(baoshis);
        
        this.hunyuancft = (pzs.hunyuancft == null) ? this.hunyuancft : new ConcurrentHashMap<>(pzs.hunyuancft);
        this.wuzangcft = (pzs.wuzangcft == null) ? this.wuzangcft : new ConcurrentHashMap<>(pzs.wuzangcft);
        this.wuzangcftni = (pzs.wuzangcftni == null) ? this.wuzangcftni : new ConcurrentHashMap<>(pzs.wuzangcftni);
        this.findcraft = deepCopyMapOfSet(pzs.findcraft);
        this.findmaterial = deepCopyMapOfSet(pzs.findmaterial);
        this.findcraftgraph = deepCopyMapOfSet(pzs.findcraftgraph);
        this.sealMap = deepCopyStringArrayMap(pzs.sealMap);
        this.basicElements = (pzs.basicElements == null) ? this.basicElements : new HashSet<>(pzs.basicElements);
        this.pzs=pzs;
        Collections.addAll(qixings, pzs.qixingname);
        Collections.addAll(qizis, pzs.qiziname);
        
    	inistage();
    	listentarget();
    	
    }

    // 辅助方法：深拷贝 Map<String, HashSet<String>>
    private ConcurrentHashMap<String, HashSet<String>> deepCopyMapOfSet(ConcurrentHashMap<String, HashSet<String>> original) {
        if (original == null) return null;
        ConcurrentHashMap<String, HashSet<String>> copy = new ConcurrentHashMap<>();
        for (Map.Entry<String, HashSet<String>> entry : original.entrySet()) {
            HashSet<String> setCopy = (entry.getValue() == null) ? null : new HashSet<>(entry.getValue());
            copy.put(entry.getKey(), setCopy);
        }
        return copy;
    }

    // 辅助方法：深拷贝 HashMap<String, String[]>（复制数组内容）
    private HashMap<String, String[]> deepCopyStringArrayMap(HashMap<String, String[]> original) {
        if (original == null) return null;
        HashMap<String, String[]> copy = new HashMap<>();
        for (Map.Entry<String, String[]> entry : original.entrySet()) {
            String[] arrCopy = (entry.getValue() == null) ? null : Arrays.copyOf(entry.getValue(), entry.getValue().length);
            copy.put(entry.getKey(), arrCopy);
        }
        return copy;
    }
    
    public void switchlevel(int level,String[] target,HashSet<String> banlist,HashMap<String,Integer>baoshis) {
    	this.level=level;
    	 for(int i=0;i<6;i++) {
             this.target[i] = target[i];
         }    
    	this.banlist.clear();
    	banlist.forEach(e->{
    		this.banlist.add(e);
    	});
    	this.baoshis.clear();
    	this.hasbaoshis.clear();
    	baoshis.forEach((k,v)->{
    		this.baoshis.put(k,v);
    		this.hasbaoshis.put(k,v);
    	});
    	refbaoshis();
    	refbanlist();
    	listentarget();
    }
	public void inistage() {
		if(level==-1)level=0;
		dirs.add("0,-1");dirs.add("1,0");dirs.add("1,0");dirs.add("0,1");dirs.add("-1,0");dirs.add("-1,0");//用于define类中的宝石朝??
		for(int i=0;i<6;i++) {//预制龙爪的延申范??
			int m=(int) (10-2*Math.abs(i-2.5));
			for(int j=0;j<m;j++) {
				if(i>0)longzhuacanlen.add(j-m/2+","+(i-3));
				if(i<5)longzhuacanlen1.add(j-m/2+","+(i-2));
			}
		}
		longzhuacanlen.remove("0,0");longzhuacanlen1.remove("0,0");
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
//    		else {
//    			triiu[i]=new Image(getClass().getResourceAsStream("gridpic/gridu"+i+".png"));
//        		triid[i]=new Image(getClass().getResourceAsStream("gridpic/gridd"+i+".png"));
//        		definezang[i]=new Image(getClass().getResourceAsStream("definepic/??+i+".png"));
//    		}
    		int l=i+1;
    		luoshus[i]=new Image(getClass().getResourceAsStream("shixupic/洛书"+l+".png"),300*suofang,200*suofang, true, false);
    	}
    	for(int i=0;i<5;i++) {
    		zhengjins[i]=new Image(getClass().getResourceAsStream("otherpic/hunyuanzhengjiny"+i+".png"));
    	}
    	for(int i=0;i<26;i++) {
    		charsi.put(String.valueOf((char)(65+i)),new Image(getClass().getResourceAsStream("charpic/"+(char)(65+i)+".png")));
    		charsil.put(String.valueOf((char)(65+i)),new Image(getClass().getResourceAsStream("charpic/"+(char)(65+i)+"1.png")));
    	}
    	for(int i=0;i<6;i++) {
    		wuzang[i]=new Image(getClass().getResourceAsStream("blendpic/五脏"+i+".png"));
    	}
    	if(lowgrahf) {
    		blend1i = new Image(getClass().getResourceAsStream("blendpic/三足混元器.png"),300*scale,289*scale,false,false);
    		blend2i = new Image(getClass().getResourceAsStream("blendpic/六合周天混元阵.png"),1200*scale,1040*scale,false,false);
    		taiyangi = new Image(getClass().getResourceAsStream("blendpic/太阳.png"),259*scale,268*scale,false,false);
    		jinwui = new Image(getClass().getResourceAsStream("blendpic/金乌.png"),365*scale,365*scale,false,false);
    		longzhuax1i = new Image(getClass().getResourceAsStream("grabpic/a龙爪下盘.png"),173*scale,173*scale,false,false);
    		longzhuax2i = new Image(getClass().getResourceAsStream("grabpic/a龙爪下盘上.png"),166*scale,146*scale,false,false);
    		longzhuani = new Image(getClass().getResourceAsStream("grabpic/a龙爪钮.png"),112*scale,98*scale,false,false);
    		zhuquedown1i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉10.png"),300*scale,260*scale,false,false);
    		zhuquedown2i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉20.png"),300*scale,260*scale,false,false);
    		zhuquemiddle1i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉1a.png"),300*scale,260*scale,false,false);
    		zhuquemiddle2i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉2a.png"),300*scale,260*scale,false,false);
    		zhuqueup10i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉1b.png"),300*scale,260*scale,false,false);
    		zhuqueup20i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉2b.png"),300*scale,260*scale,false,false);
    		zhuqueupi= new Image(getClass().getResourceAsStream("blendpic/朱雀纹.png"),600*scale,520*scale,false,false);
    		xuanwuu1= new Image(getClass().getResourceAsStream("blendpic/玄武u1.png"),300*scale,260*scale,false,false);
    		xuanwuu2= new Image(getClass().getResourceAsStream("blendpic/玄武u2.png"),300*scale,260*scale,false,false);
    		xuanwu1= new Image(getClass().getResourceAsStream("blendpic/玄武1.png"),300*scale,260*scale,false,false);
    		xuanwu2= new Image(getClass().getResourceAsStream("blendpic/玄武2.png"),300*scale,260*scale,false,false);
    		baihua= new Image(getClass().getResourceAsStream("blendpic/白虎a.png"),600*scale,520*scale,false,false);
    		baihui0= new Image(getClass().getResourceAsStream("blendpic/白虎0.png"),600*scale,520*scale,false,false);
    		baihui1= new Image(getClass().getResourceAsStream("blendpic/白虎1.png"),600*scale,520*scale,false,false);
    		
    		jiulongguani= new Image(getClass().getResourceAsStream("blendpic/九龙灌.png"),1800*scale,1300*scale,false,false);
//    		huangjii= new Image(getClass().getResourceAsStream("sealpic/黄极灵印.png"),882*scale,937*scale,false,false);
    		jiulongguani= new Image(getClass().getResourceAsStream("blendpic/九龙灌.png"));
    		huangjii= new Image(getClass().getResourceAsStream("sealpic/黄极灵印.png"));
    		
    		//以上缩放会影响readgrid像素计算
    		for(int i=0;i<12;i++) {
	    		zhuabis[i]=new Image(getClass().getResourceAsStream("grabpic/a爪臂"+i+".png"),1200*scale,1200*scale,false,false);
	    		zhuazis[i]=new Image(getClass().getResourceAsStream("grabpic/a爪子"+i+".png"),346*scale,346*scale,false,false);
	    		zhuazigs[i]=new Image(getClass().getResourceAsStream("grabpic/a爪子g"+i+".png"),346*scale,346*scale,false,false);
	    	}
//	    	for(int i=0;i<animalname.length;i++) {
//	    		baoshims[i]=new Image(getClass().getResourceAsStream("definepic/宝石"+animalname[i]+".png"),346*scale,346*scale,false,false);
//	    	}
    		for(int i=0;i<24;i++) {
    			baoshibs[i]=new Image(getClass().getResourceAsStream("definepic/宝石a"+i+".png"),346*scale,346*scale,false,false);
    		}
    		for(int i=0;i<5;i++) {
    			baihuis[i]=new Image(getClass().getResourceAsStream("blendpic/dc"+i+"1.png"),600*scale,520*scale,false,false);
    			baihuis[i+5]=new Image(getClass().getResourceAsStream("blendpic/xc"+i+"1.png"),156*scale,156*scale,false,false);
    		}
    	}
    	else {
    		blend1i = new Image(getClass().getResourceAsStream("blendpic/三足混元器.png"));
    		blend2i = new Image(getClass().getResourceAsStream("blendpic/六合周天混元阵.png"));
    		taiyangi = new Image(getClass().getResourceAsStream("blendpic/太阳.png"));
    		jinwui = new Image(getClass().getResourceAsStream("blendpic/金乌.png"));
    		longzhuax1i = new Image(getClass().getResourceAsStream("grabpic/a龙爪下盘.png"));
    		longzhuax2i = new Image(getClass().getResourceAsStream("grabpic/a龙爪下盘上.png"));
    		longzhuani = new Image(getClass().getResourceAsStream("grabpic/a龙爪钮.png"));
    		zhuquedown1i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉10.png"));
    		zhuquedown2i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉20.png"));
    		zhuquemiddle1i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉1a.png"));
    		zhuquemiddle2i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉2a.png"));
    		zhuqueup10i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉1b.png"));
    		zhuqueup20i= new Image(getClass().getResourceAsStream("blendpic/朱雀炉2b.png"));
    		zhuqueupi= new Image(getClass().getResourceAsStream("blendpic/朱雀纹.png"));
    		xuanwuu1= new Image(getClass().getResourceAsStream("blendpic/玄武u1.png"));
    		xuanwuu2= new Image(getClass().getResourceAsStream("blendpic/玄武u2.png"));
    		xuanwu1= new Image(getClass().getResourceAsStream("blendpic/玄武1.png"),300,260, false,false);
    		xuanwu2= new Image(getClass().getResourceAsStream("blendpic/玄武2.png"),300,260, false,false);
    		baihua= new Image(getClass().getResourceAsStream("blendpic/白虎a.png"));
    		baihui0= new Image(getClass().getResourceAsStream("blendpic/白虎0.png"));
    		baihui1= new Image(getClass().getResourceAsStream("blendpic/白虎1.png"));
    		jiulongguani= new Image(getClass().getResourceAsStream("blendpic/九龙灌.png"));
    		huangjii= new Image(getClass().getResourceAsStream("sealpic/黄极灵印.png"));
    		for(int i=0;i<12;i++) {
	    		zhuabis[i]=new Image(getClass().getResourceAsStream("grabpic/a爪臂"+i+".png"));
	    		zhuazis[i]=new Image(getClass().getResourceAsStream("grabpic/a爪子"+i+".png"));
	    		zhuazigs[i]=new Image(getClass().getResourceAsStream("grabpic/a爪子g"+i+".png"));
	    	}
    		for(int i=0;i<24;i++) {
    			baoshibs[i]=new Image(getClass().getResourceAsStream("definepic/宝石a"+i+".png"));
    		}
//    		for(int i=0;i<animalname.length;i++) {
//	    		baoshims[i]=new Image(getClass().getResourceAsStream("definepic/宝石"+animalname[i]+".png"));
//	    	}
    		for(int i=0;i<5;i++) {
    			baihuis[i]=new Image(getClass().getResourceAsStream("blendpic/dc"+i+"1.png"));
    			baihuis[i+5]=new Image(getClass().getResourceAsStream("blendpic/xc"+i+"1.png"));
    		}
    	}
    	for(int i=0;i<shixuyys.length;i++) {
    		shixuyys[i]=new Image(getClass().getResourceAsStream("shixupic/暂停ay"+i+".png"));
    	}
    	for(int i=0;i<9;i++) {
    		charsic.put(ncis[i],new Image(getClass().getResourceAsStream("charpic/"+shixuanname[i]+".png")));
    		charsicl.put(ncis[i],new Image(getClass().getResourceAsStream("charpic/"+shixuanname[i]+"1.png")));
    		shixuani[i] = new Image(getClass().getResourceAsStream("shixupic/时序按钮"+i+".png"),70*suofang,45*suofang,true,true);
    	}
    	for(int i=0;i<8;i++) {
    		fuzhii[i] = new Image(getClass().getResourceAsStream("shixupic/符纸"+i+".png"),60*suofang,100*suofang,true,true);
    	}
//    	for(int i=0;i<animalname.length;i++) {
//    		animalindex.put(animalname[i], i);
//    	}
    	if(!lowgrahf) {
	        anw=new AnimatedWave1(300, 260,xuanwu1,1,4);
	        anw1=new AnimatedWave1(300, 260,xuanwu2,0,4);
	        firee=new RealisticFlameEffect(300,260);
    	}
    	else {
    		anw=new AnimatedWave1((int)(300*scale), (int)(260*scale),xuanwu1,1,4);
 	        anw1=new AnimatedWave1((int)(300*scale), (int)(260*scale),xuanwu2,0,4);
 	        firee=new RealisticFlameEffect((int)(300*scale), (int)(260*scale));
    	}
    	for(int i=0;i<10;i++) {
    		zangs[i] =new Blend();
			zangs[i].setMode(BlendMode.OVERLAY);
			zangs[i].setTopInput(new ImageInput(definezang[i]));
    	}
    	dragonl=new DragonLightning();
    	
    	definecolorblend.setMode(BlendMode.OVERLAY);

    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(defineguangzhao));

    	rectcolor.put("青龙", Color.color(0.3, 0.6, 0.4,0.6));
    	rectcolor.put("玄武", Color.color(0.1, 0.3, 0.7,0.6));
    	rectcolor.put("朱雀", Color.color(0.9, 0.3,0.3,0.6));
    	rectcolor.put("白虎", Color.color(0.9, 0.9,0.9,0.6));
    	rectcolor.put("混元", Color.color(0.15, 0.12,0.1,0.6));
    	rectcolor.put("五脏", Color.color(0.8, 0.55,0.3,0.6));
    	rectcolor.put("六合", Color.color(0.35, 0.75,0.78,0.6));
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

        blpane.getChildren().add(hypane);
        blpane.getChildren().add(dfupane);
        blpane.getChildren().add(lzpane);
        dfpane=new Pane();
        connectpane=new Pane();
        gpane=new Pane();
       
        dfbpane.setMouseTransparent(true);
        dfpane.setMouseTransparent(true);
        blpane.setMouseTransparent(true);
        connectpane.setMouseTransparent(true);
        fxpane.setMouseTransparent(true);
//        handlepane.setMouseTransparent(true);
        
        //防止bounds计算不正确导致子节点被隐??
        lzpane.setPrefSize(12300, 12740);
        hypane.setPrefSize(12300, 12740);
        dfbpane.setPrefSize(12300, 12740);
        dfupane.setPrefSize(12300, 12740);
        udpane.setPrefSize(12300, 12740);
        handlepane.setPrefSize(12300, 12740);
        fxpane.setPrefSize(12300, 12740);

//        gpane.setCache(true);
        root = new StackPane();	
        root.setAlignment(Pos.TOP_LEFT);   
        root.setScaleX(scale);
        root.setScaleY(scale);
        root.setTranslateX(maxoffsetx);
        root.setTranslateY(maxoffsety);
//        shizhuani =imgmake.process(shizhuani,11,2);
        shizhuan=new ImageView(shizhuani);
        shizhuan.setSmooth(false);
        shizhuan.setCache(true);
        game.getChildren().add(shizhuan);
        stp.getChildren().add(game);

        // 计算密铺位置
    	   for (int i = 0; i < MAPHEIGHT; i++) {
               for (int j = 0; j < MAPWIDTH; j++) {
            	   grid nowgrid=new grid(j,i);
                   ImageView triangleView=null;
                   int r=(int)(Math.sqrt(Math.random())*10);//浅色块多
//                   int r=(int)(Math.pow(Math.random()*3, 2));//深色块多
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
                   gridv[i][j]=triangleView;
                   
                   gpane.getChildren().add(triangleView);
                   triangleView.setOnMouseEntered(e1->{
                   	hovergridx=nowgrid.x;
                   	hovergridy=nowgrid.y;
                   	if(null!=db0[hovergridy][hovergridx]&&db0[hovergridy][hovergridx] instanceof definespawn) {    
                   		if(!fxpane.getChildren().contains(definenumbert))
                		fxpane.getChildren().add(definenumbert);
                		definenumbert.setTranslateX(150*hovergridx);
                		definenumbert.setTranslateY(260*hovergridy+200);
                   		definespawn ds=(definespawn) db0[hovergridy][hovergridx];
                   		definenumbert.setText(String.valueOf(hasbaoshis.get(ds.name))+"/"+String.valueOf(baoshis.get(ds.name)));
                   		try {
                   			hidespawnnumbert.stop();
                   			showspawnnumbert.stop();
                   			definenumbert.setOpacity(0);
                   		}catch(Exception e) {}
                   		showspawnnumbert=new Timeline(new KeyFrame(Duration.seconds(0.2),
	                        new KeyValue(definenumbert.opacityProperty(),1)));
                   		showspawnnumbert.play();
                   	}
        		});
                triangleView.setOnMouseExited(e->{
        			if(null!=db0[hovergridy][hovergridx]&&db0[hovergridy][hovergridx] instanceof definespawn) {
        				try {
                   			hidespawnnumbert.stop();
                   			showspawnnumbert.stop();
                   			definenumbert.setOpacity(1);
                   		}catch(Exception e1) {}
        				hidespawnnumbert=new Timeline(new KeyFrame(Duration.seconds(0.2),
            	                new KeyValue(definenumbert.opacityProperty(),0)));
        				hidespawnnumbert.play();
        				hidespawnnumbert.setOnFinished(e1->{
            				fxpane.getChildren().remove(definenumbert);	
            			});
                   	}
        		});
               }
           }
        game.setVisible(false);
       

       
        game.getChildren().add(root);
        root.setPickOnBounds(false);
        // 动画定时器优化，使用多线程更新位??
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                long currentTime = System.nanoTime();
//                // 更新 FPS 计算
//                frameCount++;
//                if (currentTime - lastTime >= 1_000_000_000) {
//                	lastframecount=frameCount;
//                    frameCount = 0;
//                    lastTime = currentTime;
//                }
//            }
//        };
//        timer.start();
        
//以下为时序界??
        ui = new StackPane();
		shixu=new StackPane();
		shixu.setPickOnBounds(false);
		ui.setPickOnBounds(false);
		ui.setAlignment(Pos.TOP_LEFT);
		ui.getChildren().add(shixu);
		game.getChildren().add(ui);
				
		//暂停按钮
		shixu.setTranslateY(screenheight/2-80*suofang);

		zantb.getChildren().add(new ImageView(shixubi));
		shixuyinyu=new ImageView(shixuyys[18]);
		shixuyinyu.setScaleX(-1);
		shixuyinyu.getTransforms().add(new Rotate(0,118.5,118.5));
		zantb.getChildren().add(shixuyinyu);
		
		shixuyinyu.setTranslateX(178);
		shixuyinyu.setTranslateY(145);
		zantb.setScaleX(suofang/2.5);
		zantb.setScaleY(suofang/2.5);
		zantb.setTranslateY(10*suofang);

		shixuyinyu.rotateProperty().addListener(e->{
			int np1=(int) Math.floor(((-shixuyinyu.getRotate()+630)%360)/15);
			ds3.setOffsetX(10*Math.sin(Math.toRadians(-shixuyinyu.getRotate()-40)));
			ds3.setOffsetY(10*Math.cos(Math.toRadians(-shixuyinyu.getRotate()-40)));
			if(shixuyinyu.getImage()!=shixuyys[np1]) {
				shixuyinyu.setImage(shixuyys[np1]);	
			}
		});
		sst = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(shixuyinyu.rotateProperty(),0)),
					new KeyFrame(Duration.seconds(2),new KeyValue(shixuyinyu.rotateProperty(),180)),
					new KeyFrame(Duration.seconds(4),new KeyValue(shixuyinyu.rotateProperty(),360)));
        
        zantb.setOnMouseReleased(e->{
        	if (Math.abs(shixu.getTranslateY()-nowshixutransy)<10*suofang)
        		pause();
        });
		ssoutTransition = new TranslateTransition(Duration.millis(150), shixu);
		ssoutTransition.setToY(screenheight/2-270*suofang);
		ssoutTransition.setInterpolator(Interpolator.EASE_BOTH);

		ssinTransition = new TranslateTransition(Duration.millis(150), shixu);
		ssinTransition.setToY(screenheight/2-80*suofang);
		ssinTransition.setInterpolator(Interpolator.EASE_BOTH);
		sst.setCycleCount(Timeline.INDEFINITE);
//        sst.play();

		shixu.setOnMouseExited(e->{
			clearAllHighlights1();
			if(selectitem1!=null) {
				if(nowgrab1!=null) {
					leaveshixu();
					canselectshixuf=true;
				}
				selectitem1.clear();
			}
		});
        shixu.setOnMousePressed(e->{
        	nowshixutransy=shixu.getTranslateY();
        	pressStartTime = System.currentTimeMillis();
        });
        shixu.setOnMouseDragged(e -> {
        	nowmousex = e.getScreenX();
			nowmousey = e.getScreenY();
		});

        shixu.setOnMouseReleased(e -> {
			double currentPos = shixu.getTranslateY();
//			long duration = System.currentTimeMillis() - pressStartTime;
			if (Math.abs(currentPos-nowshixutransy)>10*suofang) {
//				if (currentPos < screenheight/2-150*suofang) { 
//				if(e.getScreenY()-dragoff1<0) {
//					shixuoutf=true;
//					ssinTransition.stop();
//					ssoutTransition.setFromY(currentPos);
//					ssoutTransition.play();
//				} else {
//					shixuoutf=false;
//					ssoutTransition.stop();
//					ssinTransition.setFromY(currentPos);
//					ssinTransition.play();
//				}
			}
		});

        //选择??
        selectionBox = new Rectangle();
        selectionBox.setFill(Color.TRANSPARENT);
        selectionBox.setStroke(Color.color(1,1,1,.5));
        selectionBox.setStrokeWidth(20*suofang);
        selectionBox.setVisible(false);

        fxpane.getChildren().add(selectionBox);

        root.getChildren().add(udpane);
        root.getChildren().add(gpane); 
        root.getChildren().add(dfbpane);
		root.getChildren().add(dfpane);
		root.getChildren().add(connectpane);
		root.getChildren().add(blpane);
		root.getChildren().add(fxpane);
		root.getChildren().add(handlepane);
			
		handlepane.setPickOnBounds(false);
		udpane.setPickOnBounds(false);
		dfpane.setPickOnBounds(false);
		blpane.setPickOnBounds(false);
		
		//底色块，但是会超??
		Rectangle r=new Rectangle(300*suofang,200*suofang);
		r.setFill(Color.rgb(178,161,100));
		 r.setTranslateX(r.getBoundsInLocal().getWidth() *1.5 - screenwidth / 2);
		    r.setTranslateY(180 * suofang);
		ls1.getChildren().add(r);
		
		// 存储luoshus的ImageView以便后续操作 洛书
		ls1.setPickOnBounds(false);
//		ls1.setBackground(new Background(new BackgroundFill(Color.rgb(178,161,100), null, null)));
//		ls1.setMaxHeight(200*suofang);
				for (int i = 9; i < 29; i++) {
				    ImageView iv = new ImageView(luoshus[i%10]);
				    iv.setTranslateX(iv.getBoundsInLocal().getWidth() * (i - 8.1) - screenwidth / 2);
				    iv.setTranslateY(180 * suofang);
				    ls1.getChildren().add(iv);
				    luoshusList.add(iv);
				}
				shixu.getChildren().add(ls1);
		Image juanzhou = new Image(getClass().getResourceAsStream("shixupic/juanzhou5.png"),60*scalemubanl,40*scalemubanl,true,true);
		Image juanzhoux1 = new Image(getClass().getResourceAsStream("shixupic/juanzhoux5.png"),60*scalemubanl,10*scalemubanl,true,true);
		Image shixuapi = new Image(getClass().getResourceAsStream("shixupic/时序按钮盘3.png"),600*suofang,200*suofang,true,true);
		
		//符纸
		double[] transx= {-375,-300,-225,-150,150,225,300,375},
				transy= {50,38,28,20,20,28,38,50};
		for(int i=0;i<8;i++) {
			ImageView fz0=new ImageView(fuzhii[i]);
			double tx=transx[i],ty=transy[i];
			caozuoan[i]=fz0;
			caozuoan[i].setTranslateX(tx*suofang);
			caozuoan[i].setTranslateY(ty*suofang);
			shixu.getChildren().add(caozuoan[i]);
			TranslateTransition outTransition = new TranslateTransition(Duration.millis(400), fz0);
			outTransition.setToY(0);
			outTransition.setInterpolator(Interpolator.EASE_BOTH);
			TranslateTransition inTransition = new TranslateTransition(Duration.millis(400), fz0);
			inTransition.setToY(ty*suofang);
			inTransition.setInterpolator(Interpolator.EASE_BOTH);
			
			RotateTransition rt=new RotateTransition(Duration.millis(700), fz0);
			rt.setToAngle(i<4?360:-360);
			rt.setInterpolator(Interpolator.EASE_IN);
			rttts[i]=rt;
			caozuoan[i].setOnMouseEntered(e->{
				inTransition.stop();
				outTransition.setFromY(fz0.getTranslateY());
				outTransition.play();
			});
			caozuoan[i].setOnMouseExited(e->{
				outTransition.stop();
				inTransition.setFromY(fz0.getTranslateY());
				inTransition.play();
			});
			int k=i;
			caozuoan[i].setOnMouseClicked(e->{
				if(k==0) {
					//时序拨回退一帧（到边界时联动滚动 sxgp）
//					double _wn = screenwidth / 70 / suofang;
//					int _visW = (int)_wn;
					nowplayshixupos = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70) + nowlookshixu;
					nowplayshixupos = Math.max(0, nowplayshixupos - 1);
					if (nowplayshixupos < nowlookshixu) {
						nowlookshixu = nowplayshixupos;
						refreshshixupane();
					}
					shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
					nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
					if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
					else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
					if(lastshixu != nowboindex + nowlookshixu) {
						lastshixu = nowboindex + nowlookshixu;
						readshixu();
					}
				}
				if(k==1) {
					//时序拨前进一帧（到边界时联动滚动 sxgp）
					double _wn = screenwidth / 70 / suofang;
					int _visW = (int)_wn;
					nowplayshixupos = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70) + nowlookshixu;
					nowplayshixupos = Math.min(maxshixu, nowplayshixupos + 1);
					if (nowplayshixupos >= nowlookshixu + _visW) {
						nowlookshixu = nowplayshixupos - _visW + 1;
						refreshshixupane();
					}
					shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
					nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
					if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
					else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
					if(lastshixu != nowboindex + nowlookshixu) {
						lastshixu = nowboindex + nowlookshixu;
						readshixu();
					}
				}
				if(k==2) {
					// 时序拨 0 / 最大时序位置
					double _wn = screenwidth / 70 / suofang;
					int _visW = (int)_wn;
					if (nowplayshixupos > 0) {
						nowplayshixupos = 0;
						nowlookshixu = 0;
					} else {
						nowplayshixupos = maxshixu;
						nowlookshixu = Math.max(0, maxshixu - _visW + 1);
					}
					shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
					nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
					refreshshixupane();
					if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
					else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
					if(lastshixu != nowboindex + nowlookshixu) {
						lastshixu = nowboindex + nowlookshixu;
						readshixu();
					}
				}
				if(k==3) {
					// sxgp 垂直位置 最上/最下切换
					int maxXuy = Math.max(0, machines.size() - 4);
					if (nowlookshixuy > 0) nowlookshixuy = 0;
					else nowlookshixuy = maxXuy;
					refreshshixupane();
				}
				if (k==4) {
					switchsettingshow();
				}
				if(k==5) {
					writetosaving();
				}
				if(k==6) {
					readfromsaving();
				}
				if(k==7) {
					//清空机器：确认 label（带裁剪背景，仿 maketext）
					Group confirmGroup = new Group();
					Text confirmText = new Text(i18n.clearAllMachines[language]);
					confirmText.setFont(f3);
					confirmText.setFill(Color.BROWN);
					double cw = confirmText.getLayoutBounds().getWidth() + 20 * suofang;
					double ch = confirmText.getLayoutBounds().getHeight() + 14 * suofang;
					Rectangle confirmBg = new Rectangle(cw, ch);
					confirmBg.setFill(Color.web("#f6e75a"));
					confirmBg.setArcWidth(8 * suofang);
					confirmBg.setArcHeight(8 * suofang);
					confirmBg.setEffect(ds2);
					confirmText.setTranslateX(10 * suofang);
					confirmText.setTranslateY(ch - 4 * suofang);
					confirmGroup.getChildren().addAll(confirmBg, confirmText);
					double gx = caozuoan[7].getTranslateX() - cw / 2 + 30 * suofang;
					double gy = caozuoan[7].getTranslateY() - ch - 10 * suofang;
					confirmGroup.setTranslateX(gx);
					confirmGroup.setTranslateY(gy);
					// 移除旧的同类型 label
					caozuoan[7].getParent().getChildrenUnmodifiable().forEach(n -> {
						if (n instanceof Group) {
							Group oldG = (Group)n;
							if (!oldG.getChildren().isEmpty() && oldG.getChildren().get(0) instanceof Rectangle) {
								Rectangle oldRect = (Rectangle)oldG.getChildren().get(0);
								if (Math.abs(oldRect.getWidth() - cw) < 1 && Math.abs(oldRect.getHeight() - ch) < 1) {
									((Pane)caozuoan[7].getParent()).getChildren().remove(n);
								}
							}
						}
					});
					((Pane)caozuoan[7].getParent()).getChildren().add(confirmGroup);
					// 点击 root → 移除 label
					javafx.event.EventHandler<javafx.scene.input.MouseEvent> rootClick = new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
						@Override public void handle(javafx.scene.input.MouseEvent me) {
							((Pane)caozuoan[7].getParent()).getChildren().remove(confirmGroup);
							root.removeEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, this);
						}
					};
					root.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, rootClick);
					// 点击 label → 确认删除全局机器
					confirmGroup.setOnMouseClicked(e2 -> {
						e2.consume();
						root.removeEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, rootClick);
						// 仿 cleardata + readfromsaving 清理逻辑
						List<grid> mc = new ArrayList<>(machines);
						for (grid gg : mc) { try { gg.remove(); } catch (Exception ignored) {} }
						machines.clear();
						machineskey.clear();
						// 清空网格数组
						for (int yy = 0; yy < MAPHEIGHT; yy++) {
							for (int xx = 0; xx < MAPWIDTH; xx++) {
								db[yy][xx] = null;
								db0[yy][xx] = null;
								df[yy][xx] = null;
							}
						}
						// 清空历史
						dbhistory.clear();
						db0history.clear();
						dfhistory.clear();
						roothistory.clear();
						shixuuses.clear();
						// 清空机器视觉面板（木板不动）
						hypane.getChildren().clear();
						lzpane.getChildren().clear();
						dfpane.getChildren().clear();
						dfbpane.getChildren().clear();
						dfupane.getChildren().clear();
						udpane.getChildren().clear();
						connectpane.getChildren().clear();
						sxgp.getChildren().clear();
						// 重置所有 grid 透明度
						for (int yy = 0; yy < MAPHEIGHT; yy++) {
							for (int xx = 0; xx < MAPWIDTH; xx++) {
								gridv[yy][xx].setOpacity(1);
							}
						}
						// 重置视口位置防止后续崩溃
						nowlookshixuy = 0;
						nowlookshixu = 0;
						// 隐藏 selectionBox（它属于 fxpane，不清除）
						selectionBox.setVisible(false);
						selectionBox.setOpacity(0);
						((Pane)caozuoan[7].getParent()).getChildren().remove(confirmGroup);
					});
				}
//				rt.play();
//				rt.setOnFinished(e1->{fz0.setRotate(0);});
			});
		}

		
		//横条
		for(int i=0;i<4;i++) {
			Rectangle rect=new Rectangle(screenwidth,49*suofang);
			rect.setFill(Color.color((double)i/4,(double)i/4,(double)i/4,0.2));
			rect.setTranslateY((110+49*i)*suofang);
			shixu.getChildren().add(rect);
			rects.add(rect);
		}
		
		//竖条
		Pane zongsx=new StackPane();
		zongsx.setPickOnBounds(false);
		shixu.getChildren().add(zongsx);
		zongsx.setEffect(new GaussianBlur());
		for(int i=0;i<screenwidth/140/suofang;i++) {
			Rectangle rect=new Rectangle(70*suofang,200*suofang);
			rect.setFill(Color.color(0,0,0,0.2));
			if(effectf)
				rect.setEffect(new GaussianBlur());
			rect.setTranslateX((440+140*i)*suofang- screenwidth / 2);
			rect.setTranslateY(182*suofang);
			zongsx.getChildren().add(rect);
		}
		
		//横杆
		for(int i=0;i<screenwidth/60/scalemubanl;i++) {
			for(int j=0;j<3;j++) {
				ImageView iv1=new ImageView(juanzhoux1);
				iv1.setTranslateX(iv1.getBoundsInLocal().getWidth()*(i+1)-screenwidth/2);
				iv1.setTranslateY((133+49*j)*suofang);
				shixu.getChildren().add(iv1);
			}		
		}

		//行数提示??
		columnc=new Circle(7);
		columnc.setFill(Color.color(0.1,0.02,0,0.5));
		columnc.setEffect(gausb);
		columnc.setTranslateY(252*suofang);
		columnc.setTranslateX(440*suofang-screenwidth/2);
		columnc.setMouseTransparent(true);
		shixu.getChildren().add(columnc);
		
		//右侧时序放置
//		sxgp.setPickOnBounds(false);
		shixu.getChildren().add(sxgp);
		int cols= (int)(screenwidth/70/suofang);
		setFixedGridSize(sxgp, 4,cols, 70*suofang, 49*suofang);
		sxgp.setTranslateX(405*suofang);
		sxgp.setTranslateY(screenheight/2+83*suofang);
		
//		盘面
		ImageView iv1=new ImageView(shixuapi);
		iv1.setTranslateX(115*suofang-screenwidth/2);
		iv1.setTranslateY(182*suofang);
		shixu.getChildren().add(iv1);
		
		//字母
		for(int j=0;j<9;j++) {
			shixuan iv=new shixuan(charsi.get(ncis[j]));//英文
//			shixuan iv=new shixuan(charsic.get(ncis[j]));//中文
			charans[j]=iv;
			iv.setTranslateX(197*suofang+(j%3)*96.5*suofang-screenwidth/2);
			iv.setTranslateY(110*suofang+(j/3)*47*suofang);
			iv.setScaleX(0.5*suofang);
			iv.setScaleY(0.5*suofang);
			shixu.getChildren().add(iv);
		}
		
		//云纹横条
		for(int i=0;i<screenwidth/60/scalemubanl;i++) {
			ImageView iv=new ImageView(juanzhou);
			iv.setTranslateX(iv.getBoundsInLocal().getWidth()*(i)-screenwidth/2);
			iv.setTranslateY(70*suofang);
			shixu.getChildren().add(iv);
			//时序自动展开
		}

		Pane ls2=new StackPane();
		ls2.setPickOnBounds(false);
		shixu.getChildren().add(ls2);
		
		fushil=new Label() {{
			setAlignment(Pos.BASELINE_CENTER);
	        setFont(f3);
	        setTextFill(Color.color(0.3,0.1,0));
	        setTranslateY(252*suofang);
	        setTranslateX(250*suofang-screenwidth/2);
	        setPrefWidth(250*suofang);
//	        setEffect(ds1);
		}};
		shixu.getChildren().add(fushil);
		
		//TODO 实现循环符石
		//左侧符石
		for(int i=0;i<9;i++) {
			if(i!=5) {
			Image ni=shixuani[i];
			shixuan iv2=new shixuan(ni);
			iv2.i=i;
			iv2.t=shixuanname[i];
			shixuans[i]=iv2;
//			sxgp.add(iv2, i%3, i/3);
			iv2.setTranslateX(148*suofang+(i%3)*96.5*suofang-screenwidth/2);
			iv2.setTranslateY(108*suofang+(i/3)*49*suofang);
			ls2.getChildren().add(iv2);
			iv2.setOnMouseEntered(e->{
				fushil.setText(shixuanintro[iv2.i]);
				iv2.setEffect(bloom);
			});
			iv2.setOnMouseExited(e1->{
				iv2.setEffect(null);
			});
			iv2.setOnMousePressed(e->{
				iv2.toFront();
			});
			iv2.setOnMouseDragged(e->{
			
				nowmousex= e.getScreenX();
				nowmousey= e.getScreenY();
				if(e.getButton()==MouseButton.PRIMARY) {
					if(!iv2.t.equals("抄")) {
						((ImageView)e.getSource()).setTranslateX(e.getScreenX()-screenwidth/2);
						((ImageView)e.getSource()).setTranslateY(e.getScreenY()-screenheight/2-shixu.getTranslateY());
					}
					else {
						grabupshixu1();
					}
					e.consume();
				}
			});
			
			iv2.setOnMouseReleased(e->{
				nowmousex= e.getScreenX();
				nowmousey= e.getScreenY();
				int x=(int) ((nowmousex-405*suofang)/70/suofang);
				int y=(int) ((nowmousey-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang);
				
				if(!iv2.t.equals("抄")&&nowmousex>405*suofang&&nowmousey>shixu.getTranslateY()+83*suofang+screenheight/2) {
					shixuan iv12=new shixuan(ni);
					int nx=nowlookshixu+x,ny=nowlookshixuy+y;
					shixuuses.remove(nx+","+ny);
					shixuuses.put(nx+","+ny,iv12);
					if (maxshixu<nx)maxshixu=nx;
					iv12.x= nx;
					iv12.y= ny;
					iv12.t=iv2.t;
//					System.out.println(nx+"  "+ny);
					sxgp.add(iv12, x, y);
					refreshshixupane();
					playedfu.add(nx);
					iv12.setOnMouseEntered(e1->{
						fushil.setText(shixuanintro[iv12.i]);
						iv12.setEffect(bloom);
					});
					iv12.setOnMouseExited(e1->{
						iv12.setEffect(null);
					});
					iv12.setOnMouseClicked(e1->{
						if(e1.getButton()==MouseButton.PRIMARY&&!deletef) {
							handlepane.getChildren().forEach(e2->{
								e2=null;
							});
							handlepane.getChildren().clear();
							nowplayshixupos=iv12.x;
							shixubo.setTranslateX(((nowplayshixupos-nowlookshixu)*70+442)*suofang-screenwidth/2);
							nowboindex=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70);
							if(language==0)
								shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
							else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
							readshixu();
							try {
								grid g=machines.get(iv12.y);
								try {
									if(iv12.t.equals("围")) {
										makelongzhuahandle(g);
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
							shixuuses.remove(iv12.x+","+iv12.y,iv12);
							refreshshixupane();
						}
					});
				}					
				int in=((shixuan)e.getSource()).i;
				iv2.setTranslateX(148*suofang+(in%3)*96.5*suofang-screenwidth/2);
				iv2.setTranslateY(108*suofang+(in/3)*49*suofang);
			});
			}
		}
		
		shixu.getChildren().add(zantb);
		shixu.setPickOnBounds(false);
		
		selectionBox1 = new Rectangle();
        selectionBox1.setFill(Color.TRANSPARENT);
        selectionBox1.setStroke(Color.color(1,1,1,.5));
        selectionBox1.setStrokeWidth(20*suofang);
        gpane.getChildren().add(selectionBox1);
//        selectionBox1.setVisible(false);
        selectionBox1.setEffect(new GaussianBlur(30*suofang));
        shixu.getChildren().add(selectionBox1);
		
		// 拖动处理逻辑
		final double[] dragStart = new double[2];
		sxgp.setOnMouseReleased(e->{
			if(e.getButton()==MouseButton.PRIMARY) {
				readshixu();
				if(nowgrab1!=null&&!sxgp.getChildren().contains(nowgrab1)) {
					sxgp.getChildren().add(nowgrab1);
				}
//				System.out.println(nowlookshixu+","+nowlookshixuy);
			}
//			System.out.println(nowlookshixuy);
			if(e.getButton()==MouseButton.SECONDARY) {
				selectionBox1.setOpacity(0);
				selectionBox1.setVisible(false);
				startX=0;
				startY=0;
				shixuselectstartX=0;
				shixuselectstartY=0;
				selectionBox1.setWidth(0);
	            selectionBox1.setHeight(0);
	            if(!canselectshixuf||selectitem1.size()==0) {
	            	canselectshixuf=true;
	            }
	            else {
					grabupshixu();
	            }
			}
		});
		sxgp.setOnMousePressed(e -> {
			dragStart[0] = e.getSceneX();
			dragStart[1] = e.getSceneY();
			if(e.getButton()==MouseButton.MIDDLE) {
				deleteselectitem1();
			}
			if(e.getButton() == MouseButton.PRIMARY) {
				if (nowgrab1 != null) {
					putdownshixu();
				}
			}
			if (e.getButton() == MouseButton.SECONDARY) {
				if(selectitem1!=null) {
					leaveshixu();
					clearAllHighlights1();
					canselectshixuf=true;
				}
					selectionBox1.setOpacity(1);
//              	clearAllHighlights();
					startX = e.getX();
					startY = e.getY();
					shixuselectstartX = e.getSceneX();
					shixuselectstartY = e.getSceneY();
					selectionBox1.setWidth(0);
					selectionBox1.setHeight(0);
					selectionBox1.setTranslateX(410 * suofang - screenwidth / 2 + startX);
					selectionBox1.setTranslateY(90 * suofang + startY);
					selectionBox1.setVisible(true);
					int x = (int) ((nowmousex - 405 * suofang) / 70 / suofang) + nowlookshixu;
					int y = (int) ((nowmousey - shixu.getTranslateY() - 83 * suofang - screenheight / 2) / 49 / suofang)
							+ nowlookshixuy;
					if (shixuuses.containsKey(x + "," + y)&&canselectshixuf)
						caculatehighlight1();
			}
		});
		sxgp.setOnScroll(e -> {
			double deltaY = e.getDeltaY();
			int changesxf = 0, changesyf = 0;
			if (Math.abs(deltaY) > 0) {
				dragStart[0] = e.getSceneX();
				dragStart[1] = e.getSceneY();
				if (deltaY > 0 && nowlookshixuy > 0) { // 向下拖动
					// 将最下面的矩形移到最上面
					nowlookshixuy--;
					changesyf--;
				} else if (deltaY < 0) { // 向上拖动
					changesxf = 1;
					nowlookshixuy++;
					changesyf++;	
				}
				grabshixuupdown();
			}
			if (nowlookshixu % 2 == 0) {
				zongsx.setTranslateX(0);
			} else {
				zongsx.setTranslateX(-70 * suofang);
			}
			if (changesxf != 0 || changesyf != 0) {
				refreshshixupane();
			}
		});
		sxgp.setOnMouseDragged(e -> {
		
			nowmousex = e.getScreenX();
			nowmousey = e.getScreenY();

			double deltaX = e.getSceneX() - dragStart[0];
			double deltaY = e.getSceneY() - dragStart[1];
			int changesxf = 0, changesyf = 0;
			if(e.getButton()==MouseButton.PRIMARY) {
				e.consume();
				if (deltaX < -70 * suofang) { // 向右拖动
					changesxf++;
					shixupicmoveforward();
					dragStart[0] = e.getSceneX();
					dragStart[1] = e.getSceneY();	
				} else if (deltaX > 70 * suofang) { // 向左拖动
					if (nowpicvalue > -1) {
						shouldchangepicf++;
						nowpicvalue -= 70 * suofang;
						nowlookshixu--;
						changesxf--;
						if (shouldchangepicf == 5) {
							shouldchangepicf = 0;
							ImageView l = luoshusList.remove(luoshusList.size() - 1);
							luoshusList.add(0, l);
							double w = l.getBoundsInLocal().getWidth();
							double msx = 0;
							for (Node node : ls1.getChildren()) {
								double tsx = node.getTranslateX();
								if (tsx < msx)
									msx = tsx;
							}
							l.setTranslateX(msx - w);
							if (msx - w > -9 * w) {
								l = luoshusList.remove(luoshusList.size() - 1);
								luoshusList.add(0, l);
								l.setTranslateX(msx - w * 2);
							}
						}
						for (int i = 0; i < 20; i++) {
							luoshusList.get(i).setTranslateX(luoshusList.get(i).getTranslateX() + 70 * suofang);
						}
					}
					dragStart[0] = e.getSceneX();
					dragStart[1] = e.getSceneY();
				}
				if (Math.abs(deltaY) > 40 * suofang) {
					dragStart[0] = e.getSceneX();
					dragStart[1] = e.getSceneY();
					if (deltaY > 40 * suofang && nowlookshixuy > 0) { // 向下拖动
						// 将最下面的矩形移到最上面
						nowlookshixuy--;
						changesyf--;
					} else if (deltaY < -40 * suofang) { // 向上拖动
						changesxf = 1;
						nowlookshixuy++;
						changesyf++;
					}
					grabshixuupdown();
					// 更新Y坐标
					columnc.setTranslateY(252*suofang+(-nowlookshixuy%4)*48*suofang);
				}
				if (nowlookshixu % 2 == 0) {
					zongsx.setTranslateX(0);
				} else {
					zongsx.setTranslateX(-70 * suofang);
				}
				if (changesxf != 0 || changesyf != 0) {
					refreshshixupane();
				}
				if(language==0)
					shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
				else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
				if(lastshixu!=nowboindex+nowlookshixu) {
					lastshixu=nowboindex+nowlookshixu;
					readshixu();
				}
			}
			else if(e.getButton()==MouseButton.SECONDARY&&nowgrab2==null) {
					double nowx=e.getX(),nowy=e.getY();
					double x = Math.min(startX, nowx);
	                double y = Math.min(startY, nowy);
	                double width = Math.abs(nowx - startX);
	                double height = Math.abs(nowy - startY);
	                	selectionBox1.setWidth(width);
	  	                selectionBox1.setHeight(height);
	  	                selectionBox1.setTranslateX(410*suofang-screenwidth/2+x+width/2);
	  	                selectionBox1.setTranslateY(90*suofang+y+height/2);
	                caculatehighlight1();
			}
		});
		DropShadow ds=new DropShadow();
		shixulookl=new Label() {{
			setAlignment(Pos.BASELINE_CENTER);
	        setFont(f2);
	        setTextFill(Color.BROWN);
//	        setTranslateY(5*suofang);
	        setTranslateX(-20*suofang);
	        setPrefWidth(200*suofang);
//	        setEffect(ds1);
	        setMouseTransparent(true);
		}};
		if(language==0)
			shixulookl.setText("零");
		else shixulookl.setText("0");
		shixubo=new Group(new ImageView(shixuboi){{
			setFitHeight(338.5*suofang);
			setFitWidth(120*suofang);
		}}){{
			setEffect(ds);
			setTranslateY(210*suofang);
			setTranslateX(442*suofang-screenwidth/2);
			getChildren().add(shixulookl);
			Rectangle r=new Rectangle(100*suofang,50*suofang);
			r.setTranslateX(20*suofang);
			r.setFill(Color.TRANSPARENT);
			getChildren().add(r);
		}};	
		shixu.getChildren().add(shixubo);
		
		shixubo.setOnMouseDragged(e->{
			
			if(e.getButton()==MouseButton.PRIMARY) {
				e.consume();
			nowmousex=e.getScreenX();
			nowmousey=e.getScreenY();
			shixubo.setTranslateX(Math.max(((int)(e.getScreenX())/((int)(70*suofang))*70*suofang-screenwidth/2+22*suofang),442*suofang-screenwidth/2));
			nowboindex=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70);
			if(language==0)
				shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
			else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
			if(lastshixu!=nowboindex+nowlookshixu) {
				lastshixu=nowboindex+nowlookshixu;
				readshixu();
			}
			}
		});
		shixubo.setOnMouseReleased(e->{
			readshixu();
		});

		
		Image mubani1 = new Image(getClass().getResourceAsStream("otherpic/mubanl1.png"), 278 * scalemubanl,
				688 * scalemubanl, true, true),
				mubani2 = new Image(getClass().getResourceAsStream("otherpic/mubanr1.png"), 360 * scalemubanl,
						688 * scalemubanl, true, true);
		mubanl1 = new ImageView(mubani1);
		mubanl1.setSmooth(false);
		mubanleft=new Pane();
		mubanleft.getChildren().add(mubanl1);
		mubanleft.setCache(true);
//		mubanleft.setTranslateY(30);
		ds1.setOffsetY(13);
		ds1.setOffsetX(8);
		ds1.setColor(Color.color(0.02, 0.02, 0.02, 0.9));
//		mubanleft.getChildren().add(zhengjin);
		
		mubanleft.getChildren().add(setting);
		closesetting();
		
		//TODO 关卡内选关
//		closebook();
		
//		zhengjin.setOnMouseClicked(e1->{maketext(screenwidth/2, screenheight/4, screenwidth/2,  screenheight/2, 1, "回忆", allintro);});
//		zhengjin.setScaleX(scalemubanl*0.4);
//        zhengjin.setScaleY(scalemubanl*0.4);
//        zhengjin.setTranslateX(-433/2*(1-scalemubanl*0.4)+100*suofang);
//		zhengjin.setTranslateY(-601/2*(1-scalemubanl*0.4)+50*suofang);
//        if(effectf)
//        	zhengjin.setEffect(ds1);
//        ds1.setOffsetY(13);
//		ds1.setOffsetX(8);
		//以上代码与closebook互换，为教程专用
        
		ui.getChildren().add(mubanleft);
		mubanleft.setTranslateX(-200 * scalemubanl);
		TranslateTransition outTransition = new TranslateTransition(Duration.millis(200), mubanleft);
		outTransition.setToX(-40 * scalemubanl);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		TranslateTransition inTransition = new TranslateTransition(Duration.millis(200), mubanleft);
		inTransition.setToX(-200 * scalemubanl);
		inTransition.setInterpolator(Interpolator.EASE_BOTH);
		mubanleft.translateXProperty().addListener(obs -> {
		    if (nowgrab != null) {
		        nowgrab.setTranslateX( - mubanleft.getTranslateX() + dragOffsetX);
		        nowgrab.setTranslateY( dragOffsetY);
		    }
		});
		mubanleft.setOnMouseEntered(e->{
			handlepane.getChildren().forEach(e1->{
				e1=null;
			});
			handlepane.getChildren().clear();
			inTransition.stop();
			outTransition.setFromX(mubanleft.getTranslateX());
			outTransition.play();
			leftmubanf = true;
			hovergridx = -1;
			hovergridy = -1;
			e.consume();
		});
		mubanleft.setOnMouseExited(e -> {
			outTransition.stop();
			inTransition.setFromX(mubanleft.getTranslateX());
			inTransition.play();
			leftmubanf = false;
		});
		
//		mubanl1.setOnMouseEntered(e -> {
//			
//		});
		mubanl1.setOnMouseDragged(e -> {
			if(e.getButton()==MouseButton.PRIMARY) {
			nowmousex = e.getScreenX();
			nowmousey = e.getScreenY();
			}
		});

		mubanl2 = new ImageView(mubani2);
		mubanl2.setSmooth(false);
		mubanright = new Pane();
//		mubanright.setTranslateY(30);

		
		mubanright.getChildren().add(mubanl2);
		mubanright.setTranslateX(screenwidth - 90 * scalemubanl);
		ui.getChildren().add(mubanright);
//		mubanright.setCache(true);
		TranslateTransition inTransition1 = new TranslateTransition(Duration.millis(200), mubanright);
		inTransition1.setToX(screenwidth - 90 * scalemubanl);
		inTransition1.setInterpolator(Interpolator.EASE_BOTH);
		TranslateTransition outTransition1 = new TranslateTransition(Duration.millis(200), mubanright);
		outTransition1.setToX(screenwidth - 300 * scalemubanl);
		outTransition1.setInterpolator(Interpolator.EASE_BOTH);
		//补偿拖出木板时的抖动
		mubanright.translateXProperty().addListener(obs -> {
		    if (nowgrab != null) {
		        nowgrab.setTranslateX( - mubanright.getTranslateX() + dragOffsetX);
		        nowgrab.setTranslateY( dragOffsetY);
		    }
		});
		mubanright.setOnMouseExited(e -> {
			outTransition1.stop();
			inTransition1.setFromX(mubanright.getTranslateX());
			inTransition1.play();
			rightmubanf = false;
		});
		
		mubanright.setOnMouseEntered(e -> {
			handlepane.getChildren().forEach(e1->{
				e1=null;
			});
			handlepane.getChildren().clear();
			inTransition1.stop();
			outTransition1.setFromX(mubanright.getTranslateX());
			outTransition1.play();
			rightmubanf = true;
			hovergridx = -1;
			hovergridy = -1;
			e.consume();
		});
		mubanl2.setOnMouseDragged(e -> {
			if(e.getButton()==MouseButton.PRIMARY) {
			nowmousex = e.getScreenX();
			nowmousey = e.getScreenY();
			}
		});
		mubanleft.setPickOnBounds(false);
		mubanright.setPickOnBounds(false);
        
		ljoutTransition = new TranslateTransition(Duration.millis(150), pzs.stack);
		ljoutTransition.setToY(0);
		ljoutTransition.setInterpolator(Interpolator.EASE_BOTH);
		ljinTransition = new TranslateTransition(Duration.millis(150), pzs.stack);
		ljinTransition.setToY(-770*suofang);
		ljinTransition.setInterpolator(Interpolator.EASE_BOTH);
		
		pzs.stack.setTranslateY(-770*suofang);
		pzs.stack.setOnMouseExited(e->{
			lingjvhef=false;
			ljoutTransition.stop();
			ljinTransition.setFromY(pzs.stack.getTranslateY());
			ljinTransition.play();
		});
		pzs.stack.setCache(true);
		ui.getChildren().add(pzs.stack);
		
		alli.setSmooth(false);
		alli.setFitHeight(screenheight+50);
		alli.setFitWidth(screenwidth);
		
		alli.setMouseTransparent(true);
		stp.getChildren().add(alli);	
		game.setVisible(false);
		
	    fpsText.setFill(Color.RED);
	    fpsText.setFont(new Font(20));
	    fpsText.setTranslateX(25*suofang);
	    fpsText.setTranslateY(25*suofang);
	
	
	    stp.getChildren().add(fpsText);
	    fpsText.setVisible(fpsshowf);
	
	    help.setTranslateX(3*suofang);
	    help.setTranslateY(screenheight-110*suofang);
	    help.setMouseTransparent(true);
	    ui.getChildren().add(help);
//        help.setOpacity(0);
       
	    //计时闲置并移动help
        	caltime=new Timeline(new KeyFrame(Duration.seconds(60),e1->{}));
        	helpmovetime=new Timeline(new KeyFrame(Duration.millis(10),e1->{
        		//true为右??
        		if(help.getTranslateX()<0) {
        			helpmovex=true;
        		}
        		if(help.getTranslateX()>screenwidth-100*suofang) {
        			helpmovex=false;
        		}
        		if(help.getTranslateY()<0) {
        			helpmovey=true;
        		}
        		if(help.getTranslateY()>screenheight-100*suofang) {
        			helpmovey=false;
        		}
        		if(helpmovex)help.setTranslateX(help.getTranslateX()+1);
        		else help.setTranslateX(help.getTranslateX()-1);
        		if(helpmovey)help.setTranslateY(help.getTranslateY()+1);
        		else help.setTranslateY(help.getTranslateY()-1);
        	}));
        	helpmovetime.setCycleCount(Timeline.INDEFINITE);
        	
		pixer = new Timeline(new KeyFrame(Duration.millis(64), e->{
			optimizeNode(stp,scene.getWindow());
			
			
			if(isopenfinishf>0) {
				try {
//				nowhide=0;
//				if(!lowgrahf)
				
				}catch(Exception e1) {}
			}}));
		pixer.setCycleCount(Timeline.INDEFINITE);
		pixer.setAutoReverse(true);
		pixer.play();	
		
		scene.setOnMousePressed(e->{
			orgSceneX=e.getScreenX();
			orgSceneY=e.getScreenY();
			if(e.getButton()==MouseButton.SECONDARY)
			if(Math.abs(e.getSceneX()- help.getTranslateX()-50*suofang)<50*suofang&&
        			Math.abs(e.getSceneY()- help.getTranslateY()-50*suofang)<50*suofang) canhelpmove=true;
				
		});

		scene.setOnMouseDragged(e -> {
			nowmousex=e.getScreenX();
			nowmousey=e.getScreenY();
			
			helpmovetime.stop();
        	caltime.playFromStart();
        	caltime.setOnFinished(e1->{
        		helpmovetime.play();
        	});
			if(e.getButton()==MouseButton.SECONDARY&&canhelpmove) {
				nowgrab2=help;
	            // 移动帮助按钮
	            help.setTranslateX(e.getSceneX()-50*suofang);
	            help.setTranslateY(e.getSceneY()-50*suofang);
	            
	           showunderhelp(e);

//	            if (targetNode != null && targetNode != help) {
//	            	String tutorialText = (String) targetNode.getProperties().get("tutorialText");
//	            	if(null!=tutorialText)
//	            		System.out.println(tutorialText);
//	            } else {
//	//                showAlert("提示", "没有找到可显示教程的组件");
//	            }
			}
        });

		
    	scene.setOnMouseReleased(e -> {
			mouseX = 0;
			mouseY = 0;
			transX = 0;
			transY = 0;
			if (isopenfinishf == 0) {
				game.setVisible(true);
				isopenfinishf = 2;
				stp.getChildren().remove(openpane);
				opentimeline.jumpTo(Duration.seconds(5));
			}
			
			 double clickX = e.getX();
	            double clickY = e.getY();
	            //隐藏设置??
			 if (clickX>screenwidth/2-screenheight*0.8/1028*798/2&&clickY>screenheight*0.1&&clickX<screenwidth/2+screenheight*0.8/1028*798/2&& clickY<screenheight*0.9) {
//	                System.out.println("点击在圆形边界内");
	            } else {
//	                System.out.println("点击在圆形边界外");
	            	if(settingshowf)
	            		switchsettingshow();
	            }
			 //释放帮助
			 if(e.getButton()==MouseButton.PRIMARY){
				 help.setEffect(null);
				 if(e.getScreenX()-orgSceneX<5*suofang&&e.getScreenY()-orgSceneY<5*suofang) {
					 if(bdf.playedtutorial>bdf.playedt&&nowintro<=0) {
			        		bdf.playedtutorial--;
//			        		enterintro();
			        		bdf.playedtutorial++;
			        		nowintro=0;
			        	}
		        		else nowintro-=2;
				 }
			 }
			 if(e.getButton()==MouseButton.SECONDARY){
				 	canhelpmove=false;
				 	nowgrab2=null;
		        	help.setEffect(null);
		        	if(e.getScreenX()-orgSceneX<5*suofang&&e.getScreenY()-orgSceneY<5*suofang) {
		        		showunderhelp(e);
		        	}
		        	orgSceneX =0;
		            orgSceneY =0;
	        	}
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
        	if(e.getY()<screenheight-300*suofang&&nowmousey>screenheight-300*suofang) {
				if(shixuoutf) {
					shixuoutf=false;
					ssoutTransition.stop();
					ssinTransition.setFromY(shixu.getTranslateY());
					ssinTransition.play();
				}
			}

        	helpmovetime.stop();
        	caltime.playFromStart();
        	caltime.setOnFinished(e1->{
        		helpmovetime.play();
        	});
        	
        	nowmousex=e.getX();
        	nowmousey=e.getY();
        });
    	
        // 修改后的滚轮事件：以鼠标位置为中心缩??
        root.setOnScroll(event -> {
        	double deltaY = event.getDeltaY();
        	if(null!=nowgrab&&nowgrabname.contains("五脏")) {
				wuzangrollf=(wuzangrollf+6+(deltaY>0?-1:1))%6;
				nowgrab.setRotate(wuzangrollf*60);
        	}
        	else if(canscroll&&scaleTimeline.getStatus() == Animation.Status.STOPPED) {
	            // 获取滚动方向和当前时??
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
        	
        	int x=(int) ((nowmousex-405*suofang)/70/suofang);
			int y=(int) ((nowmousey-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang);
        	for(int i=0;i<9;i++) {
        		//TODO 5实现循环
        		if(i!=5&&e.getCode().toString().equals(ncis[i])&&nowmousex>405*suofang&&nowmousey>shixu.getTranslateY()+83*suofang+screenheight/2) {
					shixuan k=charans[i];
//    					Image i0=charsicl.get(ncis[i]),i1=charsic.get(ncis[i]);//中文
        			Image i0=charsil.get(ncis[i]),i1=charsi.get(ncis[i]);//英文
        			shixuan nn=shixuans[i];	
        			if(e.getCode().toString().equals("C")) {
        					if(effectf) {
    	            			try {
    		            			charlight.stop();
    		            			for(int j=0;j<9;j++) {
    		            				charans[j].setImage(charsi.get(ncis[j]));
    		            				shixuans[j].setEffect(null);
    		            			}
    		            			}catch(Exception e1) {}
    		            			charlight=new Timeline(new KeyFrame(Duration.ZERO,e1->{k.setImage(i0);nn.setEffect(bloom);}),
    		            					new KeyFrame(Duration.seconds(0.2),e1->{k.setImage(i1);nn.setEffect(null);}));
    		            			charlight.play();
    	            			}
        					if(selectitem1.size()!=0) {
	        					copyshixu();
	        					refreshshixupane();
	        					if(nowgrab1!=null&&!sxgp.getChildren().contains(nowgrab1)) {
	        						sxgp.getChildren().add(nowgrab1);
	        					}
        					}
        					else {
        						grabupshixu1();
        					}
        				}
        				else {

	            			if(effectf) {
	            			try {
		            			charlight.stop();
		            			for(int j=0;j<9;j++) {
		            				charans[j].setImage(charsi.get(ncis[j]));
		            				shixuans[j].setEffect(null);
		            			}
		            			}catch(Exception e1) {}
		            			charlight=new Timeline(new KeyFrame(Duration.ZERO,e1->{k.setImage(i0);nn.setEffect(bloom);}),
		            					new KeyFrame(Duration.seconds(0.2),e1->{k.setImage(i1);nn.setEffect(null);}));
		            			charlight.play();
	            			}
	    					shixuan iv12=new shixuan(shixuani[i]);
	//    					sxgp.add(iv12, x, y);
	    					int nx=nowlookshixu+x,ny=nowlookshixuy+y;
	    					if (maxshixu<nx)maxshixu=nx;
	    					iv12.x= nx;
	    					iv12.y= ny;
	    					iv12.i= i;
	    					iv12.t=shixuanname[i];
	    					fushil.setText(shixuanintro[iv12.i]);
	    					if(shixuuses.containsKey(nx+","+ny)&&shixuuses.get(nx+","+ny).t.equals(iv12.t)) {
	    						shixuuses.remove(nx+","+ny);
	    						refreshshixupane();
	    					}
	    					else {
	    						shixuuses.remove(nx+","+ny);
	    						shixuuses.put(nx+","+ny,iv12);
	    						playedfu.add(nx);
	    						refreshshixupane();
	    					}
	    					iv12.setOnMouseEntered(e1->{
	    						fushil.setText(shixuanintro[iv12.i]);
	    						iv12.setEffect(bloom);
	    					});
	    					iv12.setOnMouseExited(e1->{
	    						iv12.setEffect(null);
	    					});
	    					iv12.setOnMouseClicked(e1->{
	    						if(e1.getButton()==MouseButton.PRIMARY&&!deletef) {
	    							handlepane.getChildren().forEach(e2->{
	    								e2=null;
	    							});
	    							handlepane.getChildren().clear();
	    							nowplayshixupos=iv12.x;
	    							shixubo.setTranslateX(((nowplayshixupos-nowlookshixu)*70+442)*suofang-screenwidth/2);
	    							nowboindex=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70);
	    							if(language==0)
	    								shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
	    							else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
	    							readshixu();
	    							try {
	    							grid g=machines.get(iv12.y);
	    							try {
										if(iv12.t.equals("围")) {
//											System.out.println(2);
											makelongzhuahandle(g);
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
	    							shixuuses.remove(iv12.x+","+iv12.y,iv12);
	    							refreshshixupane();
	    						}
	    					});
        				}
    				}
        	}
        	if(e.getCode().toString().equals("C")&&selectitem.size()>0) {
        		//复制机器 将所有node添加到nowgrab，中心与鼠标对其，记录最左上角node的pos和中心，放下时对每种进行make
        		copyf=true;
        		clearhideselectmachine();
        	}
        	if(e.getCode().toString().equals("ESCAPE")) {
        		switchsettingshow();
//        		if(null!=rttts[4]) {
//	        		rttts[4].stop();
//	        		rttts[4].setFromAngle(caozuoan[4].getRotate());
//	        		rttts[4].play();
//	        		ImageView iv=caozuoan[4];
//	        		rttts[4].setOnFinished(e1->{iv.setRotate(0);});
//        		}
        	}
        	if(e.getCode().toString().equals("DELETE")) {
        		deleteselectitem();
        		deleteselectitem1();
//        		rttts[7].stop();
//        		rttts[7].setFromAngle(caozuoan[7].getRotate());
//        		rttts[7].play();
//        		ImageView iv=caozuoan[7];
//        		rttts[7].setOnFinished(e1->{iv.setRotate(0);});
        	}
        	// F1-F7: 符纸功能（F4之后跳过设置/ESC，对应 charm 5-7）
        	for(int ki=0;ki<7;ki++) {
	        	if(e.getCode().toString().equals("F"+(ki+1))) {
	        		int idx = ki < 4 ? ki : ki + 1;
	        		// 执行功能
	        		if(idx==0) {
	        			//时序后退一帧（到边界联动 sxgp）
//	        			double _wn = screenwidth / 70 / suofang;
//	        			int _visW = (int)_wn;
	        			nowplayshixupos = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70) + nowlookshixu;
	        			nowplayshixupos = Math.max(0, nowplayshixupos - 1);
	        			if (nowplayshixupos < nowlookshixu) {
	        				nowlookshixu = nowplayshixupos;
	        				refreshshixupane();
	        			}
	        			shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
	        			nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
	        			if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
	        			else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
	        			if(lastshixu != nowboindex + nowlookshixu) { lastshixu = nowboindex + nowlookshixu; readshixu(); }
	        		} else if(idx==1) {
	        			//时序前进一帧（到边界联动 sxgp）
	        			double _wn = screenwidth / 70 / suofang;
	        			int _visW = (int)_wn;
	        			nowplayshixupos = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70) + nowlookshixu;
	        			nowplayshixupos = Math.min(maxshixu, nowplayshixupos + 1);
	        			if (nowplayshixupos >= nowlookshixu + _visW) {
	        				nowlookshixu = nowplayshixupos - _visW + 1;
	        				refreshshixupane();
	        			}
	        			shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
	        			nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
	        			if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
	        			else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
	        			if(lastshixu != nowboindex + nowlookshixu) { lastshixu = nowboindex + nowlookshixu; readshixu(); }
	        		} else if(idx==2) {
	        			// 时序拨 0 / 最大时序位置
	        			double _wn = screenwidth / 70 / suofang;
	        			int _visW = (int)_wn;
	        			if (nowplayshixupos > 0) {
	        				nowplayshixupos = 0;
	        				nowlookshixu = 0;
	        			} else {
	        				nowplayshixupos = maxshixu;
	        				nowlookshixu = Math.max(0, maxshixu - _visW + 1);
	        			}
	        			shixubo.setTranslateX(((nowplayshixupos - nowlookshixu) * 70 + 442) * suofang - screenwidth / 2);
	        			nowboindex = (int) (((shixubo.getTranslateX() + screenwidth / 2) / suofang - 400) / 70);
	        			refreshshixupane();
	        			if(language==0) shixulookl.setText(NumberToChinese.toChinese(nowboindex + nowlookshixu));
	        			else shixulookl.setText(String.valueOf(nowboindex + nowlookshixu));
	        			if(lastshixu != nowboindex + nowlookshixu) { lastshixu = nowboindex + nowlookshixu; readshixu(); }
	        		} else if(idx==3) {
	        			// sxgp 垂直位置 最上/最下切换
	        			int maxXuy = Math.max(0, machines.size() - 4);
	        			if (nowlookshixuy > 0) nowlookshixuy = 0;
	        			else nowlookshixuy = maxXuy;
	        			refreshshixupane();
	        		} else if(idx==4) {
	        			switchsettingshow();
	        		} else if(idx==5) {
	        			writetosaving();
	        		} else if(idx==6) {
	        			readfromsaving();
	        		} else if(idx==7) {
	        			trydeleteall();
	        		}
//	        		// 旋转动画
//	        		rttts[idx].stop();
//	        		rttts[idx].setFromAngle(caozuoan[idx].getRotate());
//	        		rttts[idx].play();
//	        		ImageView ivx = caozuoan[idx];
//	        		rttts[idx].setOnFinished(e1x->{ivx.setRotate(0);});
	        		break;
	        	}
        	}
//        	if(e.getCode().toString().equals("SHIFT")) {	
//				deletef = true;
//        	}
        	//~ BACK_QUOTE
        	if(e.getCode().toString().equals("BACK_QUOTE")) {
        		if(lingjvhef) {
	    			lingjvhef=false;
	    			ljoutTransition.stop();
	    			ljinTransition.setFromY(pzs.stack.getTranslateY());
	    			ljinTransition.play();
        		}else {
        			lingjvhef=true;
	    			ljinTransition.stop();
	    			ljoutTransition.setFromY(pzs.stack.getTranslateY());
	    			ljoutTransition.play();
        		}
        	}
        	if(e.getCode().toString().equals("TAB")) {
        		if (leftmubanf) {
					outTransition.stop();
					inTransition.setFromX(mubanleft.getTranslateX());
					inTransition.play();

				} else {
					inTransition.stop();
					outTransition.setFromX(mubanleft.getTranslateX());
					outTransition.play();
				}
				leftmubanf = !leftmubanf;
        	}
        	if(e.getCode().toString().equals("ALT")) {
        		if (rightmubanf) {
					outTransition1.stop();
					inTransition1.setFromX(mubanright.getTranslateX());
					inTransition1.play();

				} else {
					inTransition1.stop();
					outTransition1.setFromX(mubanright.getTranslateX());
					outTransition1.play();
				}
        		rightmubanf = !rightmubanf;
        	}
        	if(e.getCode().toString().equals("CONTROL")) {		//注意，robot的事件能被监听到，会自动触发一??
        		if(!shixuoutf) {
					shixuoutf=true;
					ssinTransition.stop();
					ssoutTransition.setFromY(shixu.getTranslateY());
					ssoutTransition.play();
				} else {
					shixuoutf=false;
					ssoutTransition.stop();
					ssinTransition.setFromY(shixu.getTranslateY());
					ssinTransition.play();
				}
        	}
        	if(e.getCode().toString().equals("SPACE")) pause();

        	if(e.getCode().toString().equals("F12")) {
        		if(fpsshowf) {
        			fpsshowf=false;
        			fpsText.setVisible(false);
        		}
        		else {
        			fpsshowf=true;
        			fpsText.setVisible(true);
        		}
        	}
        	// F5/F6/F7 已由上面的 F1-F7 循环处理（F5=存档, F6=读档, F7=清空）
//        	if(e.getCode().toString().equals("H")) {nowintro++;}
//        		if(allintrof) {
//        			allintrof=false;
//                	maketext(screenwidth/2, screenheight/4, screenwidth/2,  screenheight/2, 1, "回忆", allintro);
//        		}
//        		else {
//        			allintrof=true;
//        			fpsText.setVisible(true);
//        		}
//        	}
        	if(e.getCode().toString().equals("T")) {
        		nowintro--;
        	}
        });
        
        root.setOnMousePressed(event -> {
        	if(event.getButton()==MouseButton.MIDDLE) {
        		deleteselectitem();
        	}
        	if(event.getButton()==MouseButton.PRIMARY&&nowgrab2==null) {
        		if(nowgrab1!=null) {
					clearAllHighlights();
					selectionBox.setVisible(false);
		            selectionBox.setOpacity(0);
					nowgrabmovet.stop();
					nowgrabmovet=null;
					fxpane.getChildren().remove(nowgrab1);
					nowgrab1=null;
					int offx = lastcanputgridx - grabgridminx, offy = lastcanputgridy - grabgridminy;	
					
					//排序所有机器
					// definespawn 不在 machines 中，独立处理移动+恢复视觉效果
					Iterator<grid> it = selectitem.iterator();
					while (it.hasNext()) {
					    grid item = it.next();
					    if (item instanceof definespawn) {
					        definespawn d = (definespawn) item;
					        int newX = d.x + offx, newY = d.y + offy;
					        if (newX >= 0 && newX < MAPWIDTH && newY >= 0 && newY < MAPHEIGHT && db0[newY][newX] == null) {
					            d.move(newX, newY);
					        }
					        Node gn = d.g;
					        if (gn != null) {
					            gn.setOpacity(1);
					            Effect orig = originalEffects.get(gn);
					            if (orig != null) gn.setEffect(orig);
					            originalEffects.remove(gn);
					        }
					        it.remove();
					    }
					}
					Map<grid, Integer> orderMap = new HashMap<>();
					for (int i = 0; i < machines.size(); i++) 
					    orderMap.putIfAbsent(machines.get(i), i);
					List<grid> sorted = new ArrayList<>(selectitem);
					sorted.sort(Comparator.comparingInt(g -> orderMap.containsKey(g) ? orderMap.get(g) : Integer.MAX_VALUE));
					selectitem.clear();

			        
					if (copyf) {
						copyf = false;
					
						for (grid g : sorted) {
							blend g1 = null;
							if (g instanceof longzhua) {
								longzhuadargf=false;
								longzhua gg = (longzhua) g;
								longzhua ng = makelongzhua(g.x + offx, g.y + offy);
								g1=ng;
								ng.grabr = gg.grabr;
								ng.grabx = gg.grabx+offx;
								ng.graby = gg.graby+offy;
								ImageView zhuazi = (ImageView) ng.g.getChildren().get(2);
								ImageView zhuabi = (ImageView) ng.g.getChildren().get(3);
								ImageView niu = (ImageView) ng.g.getChildren().get(5);
								HashSet<String> lzcl = (ng.grabx + ng.graby) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
									int offgridx=ng.grabx-ng.x,offgridy=ng.graby-ng.y;
									if(lzcl.contains(offgridx+","+offgridy)) {
										double offx1=150*offgridx,offy1=260*offgridy+((offgridy+offgridx)%2==0?0:((ng.x+ng.y)%2==0?260/3:-260/3));
									    double length=Math.sqrt(offx1*offx1+offy1*offy1);
									    double radians = Math.atan2(offy1, offx1);
									    double degrees = (Math.toDegrees(radians)+90)%360;
									    zhuabi.setScaleY(length / 520);
										zhuabi.setRotate(degrees);
										ng.updateClawPosition(ng,ng.ng,zhuabi,zhuazi,niu);
										if((ng.grabx+ng.graby)%2==1) {
											zhuazi.setRotate(180);
										}
										else {
											zhuazi.setRotate(0);
										}
									}
							}
							else if (g instanceof zhuque) {
								g1=makezhuque(g.x + offx, g.y + offy);
							}
							else if(g instanceof baihu) {
								g1=makebaihu(g.x+offx,g.y+offy);
							}
							else if(g instanceof xuanwu) {
								xuanwu now = (xuanwu) g;
								List<Integer> lx=new ArrayList<Integer>();
								List<Integer> ly=new ArrayList<Integer>();
								List<Group> lp=new ArrayList<Group>();
								int size=now.listsize;
								for(int i=0;i<size;i++) {
			                    	lx.add(now.x+offx);
									ly.add(now.y+offy);
			                    	now=now.next;
			                	}
								g1=makexuanwu1(lx,ly,lp);
							}
							else if(g instanceof blend) {
								if(g.name.equals("混元")) {
									g1=makehunyuan(g.x+offx,g.y+offy);
								}
								else if(g.name.equals("五脏")) {
									if((g.x+offx+g.y+offy)%2==0)
										g1=makewuzang(g.x+offx+1,g.y+offy+1);
									else g1=makewuzang(g.x+offx+2,g.y+offy);
								}
								else if(g.name.equals("六合")) {
									g1=makeliuhehunyuan(g.x+offx,g.y+offy);
								}
							}
							else if(g instanceof definespawn) {
								Image nv=baoshiimagemap.get(g.name+"0");
								ImageView iv=new ImageView(defineg);
								Group g3=new Group(new ImageView(nv));
								g3.getChildren().add(iv);
								makebaoshispawn(g.x+offx,g.y+offy,g3,g.name);
							}
							if (!(g instanceof definespawn)) {
								try {
									int im = ((blend) (g)).machineindex, im1 = g1.machineindex;
									for (int j = 0; j < maxshixu + 1; j++) {
										if (shixuuses.containsKey(j + "," + im)) {
											shixuan ns = shixuuses.get(j + "," + im).copy(this);
											ns.x = j;
											ns.y = im1;
											shixuuses.put(j + "," + im1, ns);
										}
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
						refreshshixupane();
					}
					else {
						for (grid g : sorted) {
							g.move(g.x+offx,g.y+offy);
							g.g.setOpacity(1);
							if (g instanceof longzhua) {
			            		((longzhua)(g)).g0.setOpacity(1);
			            		((longzhua)(g)).g0.setOpacity(1);
							}
							if (g instanceof zhuque) {
			            		((zhuque)(g)).ash.setOpacity(1);
			            		gridv[g.y][g.x].setOpacity(0);
			            	}
							if (g instanceof xuanwu) {
			            		xuanwu now=(xuanwu)(g);
			            		now.added.forEach(e4->{
			                		try {
			                			e4.setOpacity(1);
			                		}catch(Exception e1) {
			                			e1.printStackTrace();
			                		}
			                	});
			                	if(null!=now.star) {
			                		now.star.setOpacity(1);
			                	}
			                	int size=now.listsize;
			                	for(int i=0;i<size;i++) {
			                    	now.g.setOpacity(1);
			                    	gridv[now.y][now.x].setOpacity(0);
			                    	gridv[now.y][now.x].setOpacity(0);
			                    	now=now.next;
			                	}
			            	}
						}
					}

				}
        	}
        	if(event.getButton()==MouseButton.SECONDARY&&nowgrab2==null) {
            // 点击空白区域（Pane背景或选择框本身）开始拖拽选择
//            if (event.getTarget() == root || event.getTarget() instance) {
//            	System.out.println(1);
        		selectionBox.setOpacity(1);
//                clearAllHighlights();
                startX = event.getX();
                startY = event.getY();
                selectionBox.setX(startX);
                selectionBox.setY(startY);
                selectionBox.setWidth(0);
                selectionBox.setHeight(0);
                selectionBox.setVisible(true);
//            } else {
//                // 点击到图形：可根据需要清空高亮或单独选中，本例清空高??
//                clearAllHighlights();
//                selectionBox.setVisible(false);
//            }
              //复制机器逻辑
				if(selectitem.size()!=0) {
					clearhideselectmachine();
					clearAllHighlights();
					selectionBox.setVisible(false);
			        selectionBox.setOpacity(0);
			        try {
			        	nowgrabmovet.stop();
			        }catch(Exception e1) {}
					nowgrabmovet=null;
					fxpane.getChildren().remove(nowgrab1);
					nowgrab1=null;
					selectitem.clear();
				}
        	}
        });
		root.setOnMouseDragged(event -> {
		if(event.getButton()==MouseButton.PRIMARY) {
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

			double sx = x > minoffsetx ? x : minoffsetx, sy = y > minoffsety ? y : minoffsety;
			sx = sx < maxoffsetx ? sx : maxoffsetx;
			sy = sy < maxoffsety ? sy : maxoffsety;

			root.setTranslateX(sx);
			root.setTranslateY(sy);
			isdraggingf=true;
			event.consume();
		}
		else if(event.getButton()==MouseButton.SECONDARY&&nowgrab2==null) {
			if(nowgrab1!=null) {
				fxpane.getChildren().remove(nowgrab1);
				nowgrab1=null;
			}
			 if (selectionBox.isVisible()) {
	                double currentX = event.getX();
	                double currentY = event.getY();
	                double x = Math.min(startX, currentX);
	                double y = Math.min(startY, currentY);
	                double width = Math.abs(currentX - startX);
	                double height = Math.abs(currentY - startY);
	                selectionBox.setX(x);
	                selectionBox.setY(y);
	                selectionBox.setWidth(width);
	                selectionBox.setHeight(height);
	                caculatehighlight();
	            }
			nowmousex = event.getScreenX();
			nowmousey = event.getScreenY();
			}
		
		});
		root.setOnMouseReleased(e->{
			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.1),e1->{
				isdraggingf=false;
			}));
			t.play();
			if(e.getButton()==MouseButton.SECONDARY&&nowgrab2==null) {
				if (selectionBox.isVisible()) {
					clearAllHighlights();
					copyf=false;
					selectionBox.setVisible(false);
		            selectionBox.setOpacity(0);
		            if(selectitem.size()>0) {
			            nowgrab1=new Group();
			            grabgridminx = Integer.MAX_VALUE;
			            grabgridminy = Integer.MAX_VALUE;
			            for(grid e2:selectitem) {
			            	((Group)(nowgrab1)).getChildren().add(e2.copyg());
			            	if(e2.x<grabgridminx)grabgridminx=e2.x;
			            	if(e2.y<grabgridminy)grabgridminy=e2.y;
			            	e2.g.setOpacity(0);
			            	gridv[e2.y][e2.x].setOpacity(1);
			            	try {
			            		((longzhua)(e2)).g0.setOpacity(0);
			            		((longzhua)(e2)).g0.setOpacity(0);
			            	}catch(Exception e3){}
			            	try {
			            		((zhuque)(e2)).ash.setOpacity(0);
			            	}catch(Exception e3){}
			            	try {
				            	((baihu)(e2)).g.setOpacity(0);
				        		gridv[e2.y][e2.x].setOpacity(1);
				        		gridv[e2.y][e2.x+1].setOpacity(1);
				        		gridv[e2.y][e2.x+2].setOpacity(1);
				        		gridv[e2.y+1][e2.x].setOpacity(1);
				        		gridv[e2.y+1][e2.x+1].setOpacity(1);
				        		gridv[e2.y+1][e2.x+2].setOpacity(1);
			            	}catch(Exception e3){}
			            	try {
			            		xuanwu now=(xuanwu)(e2);
			            		now.added.forEach(e4->{
			                		try {
			                			e4.setOpacity(0);
			                		}catch(Exception e1) {}
			                	});
			                	if(null!=now.star) {
			                		now.star.setOpacity(0);
			                	}
			                	for(int i=0;i<now.listsize;i++) {
			                    	now.g.setOpacity(0);
			                    	gridv[now.y][now.x].setOpacity(1);
			                    	now=now.next;
			                	}
			            	}catch(Exception e3){}
			            }
			            fxpane.getChildren().add(nowgrab1);
			            nowgrabmovet=new Timeline(new KeyFrame(Duration.millis(16),e1->{
			            	if((hovergridx+hovergridy)%2==(grabgridminx+grabgridminy)%2) {
			            		lastcanputgridx=hovergridx;
			            		lastcanputgridy=hovergridy;
			            		try {
			            		nowgrab1.setTranslateX((lastcanputgridx-grabgridminx)*150);
								nowgrab1.setTranslateY((lastcanputgridy-grabgridminy)*260);
			            		}catch(Exception e4) {
			            			clearhideselectmachine();
			            			clearAllHighlights();
			            			selectionBox.setVisible(false);
			            	        selectionBox.setOpacity(0);
			            	        try {
			            	        	nowgrabmovet.stop();
			            	        }catch(Exception e2) {}
			            			nowgrabmovet=null;
			            			fxpane.getChildren().remove(nowgrab1);
			            			nowgrab1=null;
			            			selectitem.clear();
			            		}
			            	}
				     	}));
				     	nowgrabmovet.setCycleCount(Timeline.INDEFINITE);
				     	nowgrabmovet.play();
		            }
	            }
				
			}
		});
		root.setOnMouseMoved(e->{
			nowhoverposx=e.getX();
			nowhoverposy=e.getY();
		});
//			nowhoverposx=e.getX();
//			nowhoverposy=e.getY();
//			nowhoverposx=(nowmousex-root.getBoundsInParent().getMinX())/scale;
//			nowhoverposy=(nowmousey-root.getBoundsInParent().getMinY())/scale;
		
		gpane.setOnMouseExited(e->{
			clearhideselectmachine();
			clearAllHighlights();
			selectionBox.setVisible(false);
	        selectionBox.setOpacity(0);
	        try {
	        	nowgrabmovet.stop();
	        }catch(Exception e1) {}
			nowgrabmovet=null;
			fxpane.getChildren().remove(nowgrab1);
			nowgrab1=null;
			selectitem.clear();
		});
		//取消选中机器
		
//可以使用shift+左键或者中键删??
		gpane.setOnMouseClicked(e -> {
			if((deletef&&e.getButton()==MouseButton.PRIMARY)||e.getButton()==MouseButton.MIDDLE) {
				grid g=db[hovergridy][hovergridx],g0=db0[hovergridy][hovergridx];
				if(null!=g) {
//					Class<?> clazz = db[hovergridy][hovergridx].getClass();
//					System.out.println(clazz);
					dbhistory.forEach(e1->{
						e1.remove(db[hovergridy][hovergridx].key);
					});
					int mi=-1;
					try {
						mi=((blend)(db[hovergridy][hovergridx])).machineindex;
					}catch(Exception e1) {}
					db[hovergridy][hovergridx].remove();
					if(mi!=-1)
					for(int i=mi;i<machines.size();i++) {
//						System.out.print(i+" ");
						for(int j=0;j<maxshixu+1;j++) {
							try {
							if(shixuuses.containsKey(j+","+(i+1)))
								shixuuses.put(j+","+i,shixuuses.get(j+","+(i+1)));
							else shixuuses.remove(j+","+i);
							}catch(Exception e1) {}
						}
						((blend)(machines.get(i))).machineindex--;
					}
//					System.out.println();
					for(int j=0;j<maxshixu+1;j++) {
						try {
						shixuuses.remove(j+","+machines.size());
						}catch(Exception e1) {}
					}
					refreshshixupane();
					fxpane.getChildren().clear();
//					System.out.println(db[hovergridy][hovergridx].name);		
				}
				else if(null!=g0) {
					db0history.forEach(e1->{
						e1.remove(db0[hovergridy][hovergridx].key);
					});
					int mi=-1;
					try {
						mi=((blend)(db0[hovergridy][hovergridx])).machineindex;
					}catch(Exception e1) {}
					db0[hovergridy][hovergridx].remove();
//					System.out.println(db0[hovergridy][hovergridx].name);
					if(mi!=-1)
					for(int i=mi;i<machines.size();i++) {
						for(int j=0;j<maxshixu+1;j++) {
							try {
							if(shixuuses.containsKey(j+","+(i+1)))
								shixuuses.put(j+","+i,shixuuses.get(j+","+(i+1)));
							else shixuuses.remove(j+","+i);
							}catch(Exception e1) {}
						}
						((blend)(machines.get(i))).machineindex--;
					}
					for(int j=0;j<maxshixu+1;j++) {
						try {
						shixuuses.remove(j+","+machines.size());
						}catch(Exception e1) {}
					}
					refreshshixupane();
					fxpane.getChildren().clear();
				}
			}
			
			if(e.getButton()==MouseButton.PRIMARY) {
				try {
				grid g = db[hovergridy][hovergridx];
				if(g instanceof longzhua&&	!isdraggingf) {
					shixumovezero();
				
					grid g1= db[hovergridy][hovergridx];
					longzhua l=(longzhua)g1;
					dbhistory.forEach(e2->{
		    			e2.put(l.key,l);}
					);
					niulzf=true;
					nowniulz=l;
					ImageView zhuazi = (ImageView) l.g.getChildren().get(2);
					ImageView zhuabi = (ImageView) l.g.getChildren().get(3);
					ImageView niu = (ImageView) l.g.getChildren().get(5);	
					HashSet<String> lzcl = (hovergridx + hovergridy) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
					niulzt=new Timeline(new KeyFrame(Duration.millis(16),e1->{
						int offgridx=hovergridx-l.x,offgridy=hovergridy-l.y;
						if(lzcl.contains(offgridx+","+offgridy)) {
							double offx=150*offgridx,offy=260*offgridy+((offgridy+offgridx)%2==0?0:((l.x+l.y)%2==0?260/3:-260/3));
						    double length=Math.sqrt(offx*offx+offy*offy);
						    double radians = Math.atan2(offy, offx);
						    double degrees = (Math.toDegrees(radians)+90)%360;
						    zhuabi.setScaleY(length / 520);
							zhuabi.setRotate(degrees);
							l.updateClawPosition(l,l.ng,zhuabi,zhuazi,niu);
						}
					}));
					niulzt.setCycleCount(Timeline.INDEFINITE);
					niulzt.play();
					}
					
				makelongzhuahandle(g);
				}catch(Exception e1) {}
			}
		});

			Timeline t1 = new Timeline(new KeyFrame(Duration.millis(16), e->{
//				if(isopenfinishf>0&&pixelf) {
//					startpixelProcessing();
//				}
				if(nowgrab!=null) {
					nowgrab.setScaleX(scale);
					nowgrab.setScaleY(scale);
				}
				if(!scenekeys.isEmpty()) {
					if(nowmousey<shixu.getTranslateY()+screenheight/2+60*suofang) {
						double rtx1=root.getTranslateX()-30,rtx2=root.getTranslateX()+30,
		        				rty1=root.getTranslateY()-30,rty2=root.getTranslateY()+30;
						if(scenekeys.contains(KeyCode.W))
							root.setTranslateY(rty2<maxoffsety?rty2:maxoffsety);
						if(scenekeys.contains(KeyCode.S))
							root.setTranslateY(rty1>minoffsety?rty1:minoffsety);
						if(scenekeys.contains(KeyCode.A))
							root.setTranslateX(rtx2<maxoffsetx?rtx2:maxoffsetx);
						if(scenekeys.contains(KeyCode.D))
							root.setTranslateX(rtx1>minoffsetx?rtx1:minoffsetx);
						if(scenekeys.contains(KeyCode.E))
							scaleroot(10);
						if(scenekeys.contains(KeyCode.Q))
							scaleroot(-10);
					}
				}
				if(fpsshowf)
					fpsText.setText("FPS:" + lastframecount+" 坐标x"+nowmousex+" 坐标y"+nowmousey+" 缩放"+scale+"\n\r"
	        			+" 格子x"+hovergridx+" 格子y"+hovergridy+" 操作x"+Math.floor(nowhoverposx)+" 操作y"+Math.floor(nowhoverposy));
				else fpsText.setText(null);
				

				xrnowtimef=xrnowtimef%8+1;
				if(xrnowtimef==1) {
//					if(!lowgrahf) {
						xuanwus1 = anw.snapshot(params1, null);
						xuanwus2 = anw1.snapshot(params1, null);
						zhuquefire=firee.snapshot(params1, null);
						dragonli=dragonl.snapshot(params1, null);
						for(int i=0;i<MAPHEIGHT;i++) {
							for(int j=0;j<MAPWIDTH;j++) {
								try {
									if(null!=db[i][j]&&db[i][j].name.equals("青龙")&&db[i][j].openf) {
										((ImageView)(db[i][j].g.getChildren().get(3))).setImage(dragonli);
									}	
									if(null!=db0[i][j]&&db0[i][j].name.equals("玄武")) {
										if(db0[i][j].upf==1) {
											((ImageView)(db0[i][j].g.getChildren().get(0))).setImage(xuanwus1);
										}
										else {
											((ImageView)(db0[i][j].g.getChildren().get(0))).setImage(xuanwus2);
										}
									}
									if(null!=db0[i][j]&&db0[i][j].name.equals("朱雀")&&db0[i][j].openf) {
										((ImageView)(db0[i][j].g.getChildren().get(1))).setImage(zhuquefire);
									}
								}catch(Exception e1) {}
							}
						}
//					}
				}
				if(scaleTimeline.getStatus() == Animation.Status.STOPPED) {
					canscroll=true;
				}
				if(deletef) {//叉叉指针
					try {
						grid g=db[hovergridy][hovergridx],g0=db0[hovergridy][hovergridx];
						if(null!=g||null!=g0) {
							for (int i = 0; i <18; i++) {
								for (int j = 0; j <24; j++) {
									Color c=deletem.getPixelReader().getColor(j, i);
									blendefine.wi1.getPixelWriter().setColor(j, i,c);
								}
							}
						}
					}catch(Exception e1) {}
				}
				//时序的靠近弹出收??
				if(nowmousey>screenheight-10*suofang) {
					if(!shixuoutf) {
						shixuoutf=true;
						ssinTransition.stop();
						ssoutTransition.setFromY(shixu.getTranslateY());
						ssoutTransition.play();
						handlepane.getChildren().forEach(e1->{
							e1=null;
						});
						handlepane.getChildren().clear();//清除设置角度操作??
					}
				}
				//灵具盒的靠近弹出收起
				if(nowmousey<30*suofang) {
					if(!lingjvhef) {
						lingjvhef=true;
						ljinTransition.stop();
						ljoutTransition.setFromY(pzs.stack.getTranslateY());
						ljoutTransition.play();
					}
				}
				

			}));
			t1.setCycleCount(Timeline.INDEFINITE);
			t1.play();
			tms.add(t1);
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
		
		refbaoshis();
		refbanlist();

		if(level==0) {
//			enterintro();
			iniseal("黄极灵印",10,20);
		}
		if(level==1) {
			maketext(40*suofang,screenheight-140*suofang,7,
    				i18n.jiaochengname[language],
    				i18n.jiaochengh[language]);		
		}
		
		savedbhistory(0);//初始化第0??
		
		Timeline gamehide = new Timeline(new KeyFrame(Duration.seconds(5),e->{
			blendefine.game1.setVisible(false);
			blendefine.game1.setOpacity(0);
		}));
		gamehide.play();
		
		
//以下绘制大量龙爪会有很帅的bug
//		for(int i=2;i<46;i++) {
//			for(int j=2;j<77;j++) {
//				makelongzhua(j,i);
//			}
//		}
		
//		List<List<String>>p1=findAllConnectionPaths("??,"??,3);
//		System.out.println(p1.toString());
	}
	


	//TODO 跳出选择下一关或退出的提示，下一关时选择是否保留机器，先跳出灵符盒开始解??显示排行??
	public void finishlevel() {
		listentarget.stop();
		if(level>=bdf.finishedproject)
			bdf.finishedproject=level+1;
		if(bdf.segment==0) {
			if(bdf.finishedproject>=4) {
				bdf.segment=1;
			}
		}
		else if(bdf.segment==1) {
			if(bdf.finishedproject>=7) {
				bdf.segment=2;
				bdf.loadScript("src/blendefine/files/script1.txt",0);
			}
		}
		else if(bdf.segment==2) {
			if(bdf.finishedproject>=11) {
				bdf.segment=3;
			}
		}
		else if(bdf.segment==3) {
			if(bdf.finishedproject>=15) {
				bdf.segment=4;
				bdf.loadScript("src/blendefine/files/script2.txt",0);
			}
		}
		else if(bdf.segment==4) {
			if(bdf.finishedproject>=19) {
				bdf.segment=5;
			}
		}
		else if(bdf.segment==5) {
			if(bdf.finishedproject>=22) {
				bdf.segment=6;
				bdf.loadScript("src/blendefine/files/script3.txt",0);
			}
		}
		else if(bdf.segment==6) {
			if(bdf.finishedproject>=25) {
				bdf.segment=7;
			}
		}
		else if(bdf.segment==7) {
			if(bdf.finishedproject>=30) {
				bdf.segment=8;
				bdf.loadScript("src/blendefine/files/script4.txt",0);
			}
		}
		else if(bdf.segment==8) {
			if(bdf.finishedproject>=33) {
				bdf.segment=9;
			}
		}
		else if(bdf.segment==9) {
			if(bdf.finishedproject>=36) {
				bdf.segment=10;
				bdf.loadScript("src/blendefine/files/script5.txt",0);
			}
		}
		else if(bdf.segment==10) {
			if(bdf.finishedproject>=39) {
				bdf.segment=11;
			}
		}
		else if(bdf.segment==11) {
			if(bdf.finishedproject>=43) {
				bdf.segment=12;
				bdf.loadScript("src/blendefine/files/script6.txt",0);
			}
		}
		else if(bdf.segment==12) {
			if(bdf.finishedproject>=46) {
				bdf.segment=13;
			}
		}
		else if(bdf.segment==13) {
			if(bdf.finishedproject>=81) {
				bdf.segment=14;
				bdf.loadScript("src/blendefine/files/script7.txt",0);
			}
		}
		exitguanka();
	}
	
	public void listentarget() {
		try {
			listentarget.stop();
			listentarget=null;
		}catch(Exception e) {}
		listentarget=new Timeline(new KeyFrame(Duration.millis(1000),e->{
			//TODO 其他种类关卡实现
			if(null!=target[0]) {
				if(target[0].equals("销毁")) {
					Boolean allclear=true;
					for(int i=0;i<MAPHEIGHT;i++) {
						for(int j=0;j<MAPWIDTH;j++) {
							if(df[i][j]!=null)allclear=false;
						}
					}
					if(allclear)finishlevel();
				}
				else {
					// 判断时方向需要与 target 一致（一共六方向??
					Boolean hastarget = false;
					z: for (int i = 0; i < MAPHEIGHT; i++) {
					    for (int j = 0; j < MAPWIDTH; j++) {
					        if (df[i][j] != null) {
					            String result = df[i][j].parentGroup.makeminname();
					            HashMap<String, String> resultMap = parseToMap(result);
					            
					            // 与六个方向的 target 依次比较
					            for (int k = 0; k < 6; k++) {
					                HashMap<String, String> targetMap = parseToMap(target[k]);
					                if (resultMap.equals(targetMap)) {
					                    hastarget = true;
					                    break z; // 找到匹配，跳出双重循??
					                }
					            }
					        }
					    }
					}
					if (hastarget) finishlevel();
				}
			}
		}));
		listentarget.setCycleCount(Timeline.INDEFINITE);
		listentarget.play();
	}
	// 通用解析：将 "1,0??,1??,0??,1??,0??,1?? 解析??HashMap<坐标, 元素>
	// 元素支持任意非数字、非逗号的连续字符（如汉字、英文等??
	private HashMap<String, String> parseToMap(String str) {
	    HashMap<String, String> map = new HashMap<>();
	    // 正则：匹??(数字,数字) 后跟 非数字且非逗号的任意字符（至少一个）
	    Pattern pattern = Pattern.compile("(\\d+,\\d+)([^\\d,]+)");
	    Matcher matcher = pattern.matcher(str);
	    while (matcher.find()) {
	        String coord = matcher.group(1);      // ??"1,0"
	        String element = matcher.group(2);    // ??"??, "??, "Water" ??
	        map.put(coord, element);
	    }
	    return map;
	}
	public void refbaoshis() {
		if(!baoshis.isEmpty()) {
			//TODO 销毁关和转化关直接放在盘面??
			if(baoshis.size()==1&&baoshis.keySet().iterator().next().length()>5) {
				
			}
			else {
				baoshis.forEach((k,v)->{
//					System.out.print(k+" ");
					inibaoshi(k,baoshiiniposition%2*100,baoshiiniposition/2*80);
					baoshiiniposition++;
				});
			}
		}
		else {
			String[] yss= {"阴","阳","金","木","水","火","土"};
			for(int i=0;i<7;i++) {
				inibaoshi(yss[i],i%2*100,200+i/2*80);
			}
		}
		definenumbert.setFont(f200);
		definenumbert.setFill(Color.BLACK);
		definenumbert.setEffect(ds6);
	}

	public void refbanlist() {
		if(refbanlistTimeline!=null) {
			refbanlistTimeline.stop();
			refbanlistTimeline=null;
		}
		Timeline t=new Timeline(new KeyFrame(Duration.millis(15),e->{try {if(level>-1&&!banlist.contains("青龙"))iniany("a爪子预览", 200, 190,0,0,1,
				guanka.class.getMethod("makelongzhua",int.class,int.class));} catch (Exception e2) {}}),
				new KeyFrame(Duration.millis(20),e->{try {if(level>-1&&!banlist.contains("混元"))iniany("三足混元器", 150, 144,70,500,1,
						guanka.class.getMethod("makehunyuan",int.class,int.class));} catch (Exception e2) {}}),
//				new KeyFrame(Duration.millis(20),e->{try {if(level==0&&!banlist.contains("九龙??))iniany("九龙??, 900, 650,-200,300,1,
//						guanka.class.getMethod("makejiulongguan",int.class,int.class));} catch (Exception e2) {}}),
				new KeyFrame(Duration.millis(20),e->{try {if(level>-1&&!banlist.contains("五脏"))iniany("五脏0", 300, 276,0,630,1, 
						guanka.class.getMethod("makewuzang",int.class,int.class));} catch (Exception e2) {}}),
				new KeyFrame(Duration.millis(20),e->{try {if(level>-1&&!banlist.contains("朱雀"))iniany("朱雀炉aa", 150,130,200,0,1,
						guanka.class.getMethod("makezhuque",int.class,int.class));} catch (Exception e2) {}}),
//				new KeyFrame(Duration.millis(20),e->{try {if(level>-1&&!banlist.contains("六合"))iniany("六合周天混元??, 600, 520,100,300,1, 
//						guanka.class.getMethod("makeliuhehunyuan",int.class,int.class));} catch (Exception e2) {}}),
				new KeyFrame(Duration.millis(20),e->{try {if(level>-1&&!banlist.contains("白虎"))iniany("白虎a", 300, 260,180,100,1, 
						guanka.class.getMethod("makebaihu",int.class,int.class));} catch (Exception e2) {}})
				);
		t.play();
		refbanlistTimeline = t;
		if(level>-1&&!banlist.contains("玄武"))inixuanwu();
		
	}
	public int[] postogrid(double posx,double posy) {
		 int gridX0 = (int) (posx / 150);
		    int gridX1 = (int) (posx % 150);
		    int gridY = (int) (posy / 260);
		    int gridY1 = (int) (posy % 260);
		    nowhoverposx=posx; nowhoverposy=posy;
		    int off=(gridX0+gridY)%2==1?(gridX1>(260-gridY1)/Math.sqrt(3)?0:-1):(gridX1>gridY1/Math.sqrt(3)?0:-1);
		    int gridX=gridX0+off;
		    if(gridX<0||gridY<0||gridX>=MAPWIDTH||gridY>=MAPHEIGHT)canputf=false;
		    gridX = Math.max(0, Math.min(gridX, MAPWIDTH - 1));
		    gridY = Math.max(0, Math.min(gridY, MAPHEIGHT - 1));
		return new int[]{gridX,gridY};
	}
	public void cleardata() {
		// 先清除所有机器的视觉元素（用 try-catch 包裹，因??xuanwu.remove ??
		// gk.machines.remove(machineindex) 在索引变化后可能越界??
		List<grid> machinesCopy = new ArrayList<>(machines);
		for (grid g : machinesCopy) {
			try { g.remove(); } catch (Exception ignored) {}
		}
		machines.clear();
		dfpane.getChildren().clear();
		dbhistory.clear();
		db0history.clear();
		dfhistory.clear();
		roothistory.clear();
	}

	public void shixumovezero() {
    	shixubo.setTranslateX((442)*suofang-screenwidth/2);
		nowboindex=0;
		shixupicmovezero();
    	process.getKeyFrames().clear();
    	nowplayshixupos=0;
    	if(language==0)
			shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
		else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
    	readhistory(0);
	}
    private void pause() {
//    	double currentPos = shixu.getTranslateY();
//    	if(Math.abs(currentPos-nowshixutransy)<2*suofang) {
			if(!pause) {
				try {
					//控制到当前帧播放完再暂停
//					if(process.)
					process.stop();
					savet.stop();
				}catch(Exception e1) {}
        		pause=true;
        		sst.pause();
        	}
        	else {
//        		try {
        			shixustartup();
//        			process.play();	
//        		}catch(Exception e1) {}
        		pause=false;
        		sst.play();   		
        	}
//    	}
	}

	public void shixustartup() {
//    	System.out.println(shixulength);
    	process.getKeyFrames().clear();
    	
//    	if(nowplayshixupos>=dbhistory.size()-2) {
    		nowplayshixupos=0; //自动循环
    		shixupicmovezero();
//    	}
//    		
//    	if(playedfu.size()!=0) {
//    		nowplayshixupos= Collections.min(playedfu);		
//    	}//存在末尾继续播放的bug
    		
//    	else if(nowplayshixupos==dbhistory.size()) 	
    	if(nowplayshixupos==0&&dbhistory.size()==0)//保存初始??
			savedbhistory(0);
    	else if(nowplayshixupos<dbhistory.size()) {
    		readhistory(nowplayshixupos);
    		//读取开始帧的数??
    	}
    		
//    	else if(nowplayshixupos>=dbhistory.size()) {
//    		for(int i=0;i<nowplayshixupos-dbhistory.size()+1;i++) {
//    			dbhistory.add(new HashMap<String,grid>());
//            	db0history.add(new HashMap<String,grid>());
//            	dfhistory.add(new HashMap<String,define>());
//    		}
//    	}
    	for(int i=nowplayshixupos;i<=shixulength;i++) {
    		int k=i;
    		//需要延后一段时间防止无法反??
    	
    		process.getKeyFrames().addAll(
    				new KeyFrame(Duration.seconds((k-nowplayshixupos)*2),e->{act(k);
    				savet=new Timeline(new KeyFrame(Duration.seconds(1.8/playrate),e1->{//1.9为理论所有动作最长时间，2为等待数据处理的间隔
    					savedbhistory(k+1);
        				}));
    				savet.play();})
//    				new KeyFrame(Duration.seconds((k-nowplayshixupos)*2+0.2),e->{})
    		);
    	}
    	process.setRate(playrate);
    	process.play();
    	process.setOnFinished(e->{savet=new Timeline(new KeyFrame(Duration.seconds(1.8/playrate),e1->{
//    				savedbhistory(nowplayshixupos+1);
    				nowplayshixupos=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70);
    				}));  	
    		savet.play();		
    	});
    	
    	//清空时序高光
    	for(int i=0;i<machines.size();i++) {
    		for(int j=0;j<maxshixu+1;j++) {
	    		try {
	    			shixuuses.get(j+","+i).setEffect(null);
	    		}catch(Exception e) {}
    		}
    	}
    	//清空重叠提示和龙爪设??
    	handlepane.getChildren().forEach(e2->{
			e2=null;
		});
		handlepane.getChildren().clear();
    }
    void shixumoveforward(){
    	if(nowplayshixupos<4) {
    		shixubo.setTranslateX((nowplayshixupos*70+442)*suofang-screenwidth/2);
    		nowboindex=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70);
    	}
    	else {
    		shixupicmoveforward();
    	}
    	if(language==0)
			shixulookl.setText(NumberToChinese.toChinese(nowboindex+nowlookshixu));
		else shixulookl.setText(String.valueOf(nowboindex+nowlookshixu));
    	for(int i=0;i<machines.size();i++) {
    		try {
    			shixuuses.get(nowplayshixupos+","+i).setEffect(bloom);
    		}catch(Exception e) {}
    		try {
    			shixuuses.get(nowplayshixupos-1+","+i).setEffect(null);
    		}catch(Exception e) {}
    	}
    }
    void shixupicmoveforward() {
    	shouldchangepicf--;
		nowpicvalue += 70 * suofang;
		nowlookshixu++;	
		if (shouldchangepicf == -5) {
			shouldchangepicf = 0;
			ImageView l = luoshusList.remove(0);
			luoshusList.add(l);
			double w = l.getBoundsInLocal().getWidth();
			double msx = 0;
			for (Node node : ls1.getChildren()) {
				double tsx = node.getTranslateX();
				if (tsx > msx)
					msx = tsx;
			}
			l.setTranslateX(msx + w);
			if (msx + w < 9 * w) {
				l = luoshusList.remove(0);
				luoshusList.add(l);
				l.setTranslateX(msx + w * 2);
			}
		}
		for (int i = 0; i < 20; i++) {
			luoshusList.get(i).setTranslateX(luoshusList.get(i).getTranslateX() - 70 * suofang);
		}
		refreshshixupane();
    }
    void shixupicmovezero() {
    	shouldchangepicf=0;
		nowpicvalue =-20*suofang;
		nowlookshixu=0;	
		luoshusList.clear();
		ls1.getChildren().clear();
		for (int i = 9; i < 29; i++) {
		    ImageView iv = new ImageView(luoshus[i%10]);
		    iv.setTranslateX(iv.getBoundsInLocal().getWidth() * (i - 8.1) - screenwidth / 2);
		    iv.setTranslateY(180 * suofang);
		    ls1.getChildren().add(iv);
		    luoshusList.add(iv);
		}
		refreshshixupane();
    }
    public void act(int k) {
    	//当前时序播放位置
    	nowplayshixupos=k;
    	if(playedfu.contains(nowplayshixupos)) {
    		playedfu.remove(nowplayshixupos);
    	}
    	shixumoveforward();
//    	System.out.println(nowplayshixupos);
    	for(int i=0;i<machines.size();i++) {
    		grid ma=machines.get(i);
    		if(shixuuses.containsKey(k+","+i)) {
//    			shixuanname = { "擒获","围绕","逸释","安息","始恸","递归","纵横","蓄势","抄录"};
    			shixuan ml=shixuuses.get(k+","+i);
    			String type=ml.t;
//    			System.out.println(type+" "+ma.name);
    			switch(ma.name) {
	    			case "青龙":{
	    				longzhua lz=((longzhua) (ma));
	    				ImageView gr= ((ImageView) lz.g.getChildren().get(2));
	    				switch(type) {	
	    					case "取":{
	    						Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.2/playrate),e->{
	    							if(null!=df[lz.graby][lz.grabx]) {
			    						lz.grabbs(lz,df[lz.graby][lz.grabx]);
			    						int np2=(int) Math.floor(((gr.getRotate()+360)%360)/30);
			    						lz.nowpic2=np2;
			    						gr.setImage(zhuazigs[np2]);
	    							}
	    							else if(db0[lz.graby][lz.grabx] instanceof definespawn) {
	    								if(db0[lz.graby][lz.grabx].openf) {
		    								makebaoshi(lz.grabx,lz.graby,db0[lz.graby][lz.grabx].name,((definespawn)(db0[lz.graby][lz.grabx])).zang);
				    						lz.grabbs(lz,df[lz.graby][lz.grabx]);
				    						int np2=(int) Math.floor(((gr.getRotate()+360)%360)/30);
				    						lz.nowpic2=np2;
				    						gr.setImage(zhuazigs[np2]);
	    								}
	    							}
	    						}));
	    						t.play();
	    					}break;
	    					case "围":{
	    						if(ml.aimx==0&&ml.aimy==0&&ml.aimr==0) {
//	    							int ax=(int)(lz.x+10*Math.random()-5),ay=(int)(lz.y+10*Math.random()-5);
//	    							int r=(int)(3-5*Math.random());
//	    							if((ax+ay+lz.grabx+lz.graby)%2==0)r=r-r%2;
//	    							else r=r+((r%2==0)?1:0);
//	    							System.out.print((lz.grabr)%6);
	    							Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.01/playrate),e->{
	    								lz.xuanzhuanlzb(1,1,0);
	    							}));
	    							t.play();
	    						}
	    						else {
	    							Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.01/playrate),e->{
	    								lz.xuanzhuanlzb(ml.aimx,ml.aimy,ml.aimr);
	    							}));
	    							t.play();
	    						}
	    					}break;
	    					case "逸":{
	    						Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.03/playrate),e->{
	    							lz.putbs();
    							}));
    							t.play();
	    						
	    					}break;
	    					default:break;
	    				}
	    			}break;
	    			case "玄武":{
	    				xuanwu xw=((xuanwu) (ma));
	    				switch(type) {	
	    				case "纵":{
    						xuanwumove(xw.last, 1);
    					}break;
    					case "蓄":{
    						xuanwumove(xw, 0);
    					}break;
    					default:break;
	    				}
	    			}break;
	    			case "朱雀":{
	    				zhuque zq=((zhuque)(ma));
	    				switch(type) {	
	    				case "安":{
    						zq.close();
    					}break;
    					case "始":{
    						zq.open();
    					}break;
    					default:break;
	    				}
	    			}break;
	    			case "混元":{
	    				blend hy=((blend) (ma));
	    				switch(type) {	
	    				case "安":{
    						hy.openf=false;
    					}break;
    					case "始":{
    						hy.openf=true;
    					}break;
	    					default:break;
	    				}
	    				
	    			}break;
	    			case "五脏":{
	    				blend wz=((blend) (ma));
	    				switch(type) {	
	    				case "安":{
    						wz.openf=false;
    					}break;
    					case "始":{
    						wz.openf=true;
    					}break;
	    					default:break;
	    				}
	    				
	    			}break;
	    			default:break;
    			}
    		}
    		switch(ma.name) {
	    		case "混元":{
	    			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.5/playrate),e->{
	    			blend hy=((blend) (ma));
					if(hy.openf&&!(null!=df[hy.y+1][hy.x]&&null!=df[hy.y][hy.x-1]&&null!=df[hy.y][hy.x+1])) {
						define down=null,left=null,right=null;
						define[] ds=new define[2];
						int makedir=-1;
						if(null!=df[hy.y+1][hy.x])
							down=df[hy.y+1][hy.x];
						else if(null!=db0[hy.y+1][hy.x]&&db0[hy.y+1][hy.x] instanceof definespawn) 
							down=makebaoshi(hy.x,hy.y+1,db0[hy.y+1][hy.x].name,((definespawn)(db0[hy.y+1][hy.x])).zang);
						if(null!=df[hy.y][hy.x-1])
							left= df[hy.y][hy.x-1];
						else if(null!=db0[hy.y][hy.x-1]&&db0[hy.y][hy.x-1] instanceof definespawn) 
							left=makebaoshi(hy.x-1,hy.y,db0[hy.y][hy.x-1].name,((definespawn)(db0[hy.y][hy.x-1])).zang);
						if(null!=df[hy.y][hy.x+1])
							right=df[hy.y][hy.x+1];
						else if(null!=db0[hy.y][hy.x+1]&&db0[hy.y][hy.x+1] instanceof definespawn) 
							right=makebaoshi(hy.x+1,hy.y,db0[hy.y][hy.x+1].name,((definespawn)(db0[hy.y][hy.x+1])).zang);
						if(null!=down&&null!=left&&null==right) {
							ds[0]=down;ds[1]=left;makedir=1;//??
						}
						else if(null!=down&&null!=right&&null==left) {
							ds[0]=down;ds[1]=right;makedir=0;//??
						}
						else if(null!=right&&null!=left&&null==down) {
							ds[0]=right;ds[1]=left;makedir=2;//??
						}
						if(makedir!=-1) {
							String yuansu0=ds[0].name.substring(0,1),animal0=ds[0].name.substring(1),
									yuansu1=ds[1].name.substring(0,1),animal1=ds[1].name.substring(1),
											make0=yuansu0+"，"+yuansu1,make1=yuansu1+"，"+yuansu0,
											make2=animal0+"，"+animal1,make3=animal1+"，"+animal0;
							String make=null;
							if(animal0.equals("")&&animal1.equals("")) {//元素混元
								if(hunyuancft.containsKey(make0)) make=make0;
								else if(hunyuancft.containsKey(make1)) make=make1;
							}
							else {
								if(hunyuancft.containsKey(make2))make=make2;	
								else if(hunyuancft.containsKey(make3)) make=make3;	
							}
							if(null!=make) {
									int rightofx=makedir==0?0:(makedir==1?-1:1),rightofy=makedir==0?1:0,
											leftofx=makedir==0?1:(makedir==1?0:-1),leftofy=makedir==1?1:0;
									define leftd=df[hy.y+leftofy][hy.x+leftofx],
											rightd=df[hy.y+rightofy][hy.x+rightofx];
									entermachine(leftd, 5-makedir*2, hy);
									entermachine(rightd, 5-((makedir+1)%3)*2, hy);
									makebaoshi(hy.x+(makedir==0?-1:(makedir==1?1:0)),hy.y+(makedir==2?1:0), hunyuancft.get(make),(int)(Math.random()*10));
//									System.out.println(leftd.name+" "+out.name);
							}
						}					
					}
	    			}));
					t.play();
	    		}break;
	    		case "五脏":{
	    			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.5/playrate),e1->{
					blend wz=((blend) (ma));
					if(wz.openf) {
						wz.cache="";
						ArrayList<define> l=new ArrayList<define>();
						ArrayList<Integer> faces=new ArrayList<Integer>();
						for(int j=0;j<6;j++) {
							int ri=(j+wz.face+5)%6;
							int x=wz.x+(int) (-Math.abs(ri-2.5)+2.5),y=wz.y+ri/3;
							define d=null;
	    					if(null!=df[y][x])d=df[y][x];
	    					else if(null!=db0[y][x]&&db0[y][x] instanceof definespawn)d=makebaoshi(x,y,db0[y][x].name,((definespawn)(db0[y][x])).zang);
	    					if(null!=d) {
	    						faces.add(ri);
	    						l.add(d);
	    					}
						}
						int i1=0;
						for(int j=0;j<6;j++) {//判断合成表，空位填入空字符串，连续的元素才能合成
							int ri=(j+wz.face+5)%6;
							if(j!=0)wz.cache+="，";
							if(faces.contains(ri)) {
								String yuansu=l.get(i1).name.substring(0,1),animal=l.get(i1).name.substring(1);
								if(animal.equals(""))wz.cache+=yuansu;
								else wz.cache+=animal;
								i1++;
							}
							else {
								wz.cache+="空";
							}
						}
						while(wz.cache.charAt(0)=='空') {
							wz.cache=wz.cache.substring(2);
						}
						while(wz.cache.charAt(wz.cache.length()-1)=='空') {
							wz.cache=wz.cache.substring(0,wz.cache.length()-2);
						}
//						new p(wz.cache);
						int[] xs= {1,-1,-1,1,3,3},ys= {2,1,0,-1,0,1};
						int outx=wz.x+xs[wz.face],outy=wz.y+ys[wz.face];
						
						if(wuzangcft.containsKey(wz.cache)&&null==df[outy][outx]) {	
//							System.out.printf(df[outx][outx].name);
							for(int j=0;j<l.size();j++) {
								entermachine(l.get(j),(7-faces.get(j))%6,wz);
							};
//							System.out.println(wuzangcft.get(wz.cache));
							makebaoshi(outx,outy, wuzangcft.get(wz.cache),(int)(Math.random()*10));
						}
					}
	    			}));
					t.play();
				}break;
//	    		case "朱雀":{
//					//连接判断在宝石move中，这里断开连接 也就是说只需瞬间就能完成连接，而时间一长就会过热断开
//					//当放置一回合时，断开上方宝石的所有周围连??
//					zhuque zq=((zhuque)(ma));
//					if(zq.openf) {
//						Timeline t=new Timeline(new KeyFrame(Duration.seconds(1/playrate),e1->{
////							System.out.print(zq.y+" "+zq.x);
//							define d=df[zq.y][zq.x];
//							if(null!=d) {
//								d.disconnectall();
//							}
//						}));
//						t.play();
//					}
//				}break;
	    		case "白虎":{
					//连接判断在宝石move中，这里断开连接 也就是说只需瞬间就能完成连接，而时间一长就会过热断开
					//当放置一回合时，断开上方宝石的所有周围连??
					baihu bh=((baihu)(ma));
					if(bh.openf) {
						Timeline t=new Timeline(new KeyFrame(Duration.seconds(1/playrate),e1->{
							for(int j=0;j<1;j++) {
								for(int z=0;z<2;z++) {
									if(null!=df[bh.y+j][bh.x+z]) {
										//销毁宝??
										define d=df[bh.y+j][bh.x+z];
										dfpane.getChildren().remove(d.g);
										if(null!=d.parentGroup)d.parentGroup.gems.remove(d);
										d.g=null;d.gk=null;d.parentGroup=null;
										df[bh.y+j][bh.x+z]=null;
									}
								}
							}		
						}));
						t.play();
					}
				}break;
    		}
    	}
    }
    
    public void trydeleteall() {
    	// 清空机器：同符纸点击（用 Group+Rectangle 背景）
		Group confirmG = new Group();
		Text confirmT = new Text(i18n.clearAllMachines[language]);
		confirmT.setFont(f3);
		confirmT.setFill(Color.BROWN);
		double cw2 = confirmT.getLayoutBounds().getWidth() + 20 * suofang;
		double ch2 = confirmT.getLayoutBounds().getHeight() + 14 * suofang;
		Rectangle bg2 = new Rectangle(cw2, ch2);
		bg2.setFill(Color.web("#f6e75a"));
		bg2.setArcWidth(8 * suofang);
		bg2.setArcHeight(8 * suofang);
		bg2.setEffect(ds2);
		confirmT.setTranslateX(10 * suofang);
		confirmT.setTranslateY(ch2 - 4 * suofang);
		confirmG.getChildren().addAll(bg2, confirmT);
		confirmG.setTranslateX(caozuoan[7].getTranslateX() - cw2 / 2 + 30 * suofang);
		confirmG.setTranslateY(caozuoan[7].getTranslateY() - ch2 - 10 * suofang);
		// 移除旧的同类型
		((Pane)caozuoan[7].getParent()).getChildrenUnmodifiable().forEach(n -> {
			if (n instanceof Group) {
				Group og = (Group)n;
				if (!og.getChildren().isEmpty() && og.getChildren().get(0) instanceof Rectangle) {
					Rectangle oldR = (Rectangle)og.getChildren().get(0);
					if (Math.abs(oldR.getWidth() - cw2) < 1 && Math.abs(oldR.getHeight() - ch2) < 1) {
						((Pane)caozuoan[7].getParent()).getChildren().remove(n);
					}
				}
			}
		});
		((Pane)caozuoan[7].getParent()).getChildren().add(confirmG);
		// 点击 root → 移除 label
		javafx.event.EventHandler<MouseEvent> rootClick2 = new javafx.event.EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent me) {
				((Pane)caozuoan[7].getParent()).getChildren().remove(confirmG);
				root.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
			}
		};
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, rootClick2);
		confirmG.setOnMouseClicked(e2 -> {
			e2.consume();
			root.removeEventFilter(MouseEvent.MOUSE_CLICKED, rootClick2);
			List<grid> mc2 = new ArrayList<>(machines);
			for (grid gg : mc2) { try { gg.remove(); } catch (Exception ignored) {} }
			machines.clear(); machineskey.clear();
			for (int yy = 0; yy < MAPHEIGHT; yy++) {
				for (int xx = 0; xx < MAPWIDTH; xx++) {
					db[yy][xx] = null; db0[yy][xx] = null; df[yy][xx] = null;
				}
			}
			dbhistory.clear(); db0history.clear(); dfhistory.clear(); roothistory.clear();
			shixuuses.clear();
			hypane.getChildren().clear(); lzpane.getChildren().clear();
			dfpane.getChildren().clear(); dfbpane.getChildren().clear();
			dfupane.getChildren().clear(); udpane.getChildren().clear();
			connectpane.getChildren().clear(); sxgp.getChildren().clear();
			for (int yy2 = 0; yy2 < MAPHEIGHT; yy2++) {
				for (int xx2 = 0; xx2 < MAPWIDTH; xx2++) {
					gridv[yy2][xx2].setOpacity(1);
				}
			}
			nowlookshixuy = 0; nowlookshixu = 0;
			selectionBox.setVisible(false); selectionBox.setOpacity(0);
			((Pane)caozuoan[7].getParent()).getChildren().remove(confirmG);
		});
		refreshshixupane();
    }
    public void savedbhistory(int i) {
    	HashMap<String,grid> ndb=new HashMap<String,grid>();
    	HashMap<String,grid> ndb0=new HashMap<String,grid>();
    	HashMap<String,define> ndf=new HashMap<String,define>();
    	for(int j=0;j<MAPHEIGHT;j++) {
    		for(int k=0;k<MAPWIDTH;k++) {
        		if(null!=db[j][k]) {
        			ndb.put(db[j][k].key, db[j][k].clone());
        		}
        		if(null!=df[j][k]) {
        			ndf.put(df[j][k].key, df[j][k].clone());
//        			System.out.println(df[j][k].key);
        		}
        		if(null!=db0[j][k]&&db0[j][k].name.equals("朱雀")) {//只克隆朱雀
        			ndb0.put(db0[j][k].key, db0[j][k].clone());
        		}
        	}
    	}
    	if(i>dbhistory.size())
    		System.out.println("超限");
    	else if(i==dbhistory.size()) {
    		dbhistory.add(ndb);
        	db0history.add(ndb0);
        	dfhistory.add(ndf);
    	}
    	else {
        	dbhistory.set(i,ndb);
        	db0history.set(i,ndb0);
        	dfhistory.set(i,ndf);
    	}	
    }
    public void readshixu() {
    	if(!pause)pause();
    	
    	nowplayshixupos=(int) (((shixubo.getTranslateX()+ screenwidth / 2)/suofang-400)/70)+nowlookshixu;

		if(dbhistory.size()>nowplayshixupos) {
			try {
				readhistory(nowplayshixupos);
				//读取当前帧已经完成的状??
			}catch(Exception e) {}
		}
    	for(int i=0;i<machines.size();i++) {
			for(int j=0;j<maxshixu+1;j++) {
				if(shixuuses.containsKey(j+","+i)) {
					if(j==nowplayshixupos) shixuuses.get(j+","+i).setEffect(bloom);
					else shixuuses.get(j+","+i).setEffect(null);
				}
			}
		}
    }
    public void readhistory(int i) {
    	if(i==0) {
    		hasbaoshis.clear();
    		baoshis.forEach((k,v)->{
    			hasbaoshis.put(k, v);
    		});
    	}
    	HashMap<String,grid> ndb=dbhistory.get(i);
    	HashMap<String,grid> ndb0=db0history.get(i);
    	HashMap<String,define> ndf=dfhistory.get(i);
    	dfpane.getChildren().clear();
    	if(ndb.size()==0&&ndb0.size()==0&&ndf.size()==0)return;
    	HashMap<String,longzhua> lznotfit=new HashMap<String,longzhua>();
    	lzpane.getChildren().clear();
    	
//    	hypane.getChildren().clear();
    	dfupane.getChildren().clear();
    	connectpane.getChildren().clear();
    	for(int j=0;j<MAPHEIGHT;j++) {
    		for(int k=0;k<MAPWIDTH;k++) {
    			if(db[j][k] instanceof longzhua) {		
    				dfbpane.getChildren().remove(((longzhua)db[j][k]).g0);
    			}
    		}
    	}
    	for(int j=0;j<MAPHEIGHT;j++) {
    		for(int k=0;k<MAPWIDTH;k++) {
    			df[j][k]=null;
        		if(null!=db[j][k]) {
        			if(db[j][k] instanceof blend&&!(db[j][k] instanceof longzhua)) {//混元器只传数??
        				try {
        				db[j][k].openf=ndb.get(db[j][k].key).openf;
        				}catch(Exception e) {}
        			}
	        		else{
//	        			System.out.println(db[j][k].key+","+ndb.containsKey(db[j][k].key));
	        			try {
		        			db[j][k]=ndb.get(db[j][k].key).clone();
		        			if(db[j][k] instanceof longzhua) {
	        					
		        				int orix=db[j][k].x,oriy=db[j][k].y;
		        				if(orix!=k||oriy!=j) {
		        					lznotfit.put(orix+","+oriy,(longzhua) ndb.get(db[j][k].key).clone());
	//	        					db[orix][oriy]=db[j][k];
		        					db[j][k]=null;
		        				}else{
		        					longzhua lz=((longzhua)(db[j][k]));
			        				machines.set(lz.machineindex, lz);
			        				lzpane.getChildren().add(lz.g);
			        				dfbpane.getChildren().add(lz.g0);
			        				if(null!=lz.ng) {
			        					dfupane.getChildren().add(lz.ng);
			        				}
		        				}
		        				
		        			}
	        			}catch(Exception e) {db[j][k]=null;}
	        		}
        		}
        		
        		//对于db0只需要读取数??
//        		if(null!=db0[j][k]) {
//        			db0[j][k].remove();//朱雀的删除特??
//        			dfupane.getChildren().remove(db0[j][k].g);//朱雀删除
//        			db0[j][k]=ndb0[j][k].clone();
//        			dfupane.getChildren().add(db0[j][k].g);
//        		}
        	}
    	}
    	ndf.keySet().forEach(e->{
    			define d= ndf.get(e);
//    			String[] pos=e.split(",");
//    			int k=Integer.parseInt(pos[0]),j=Integer.parseInt(pos[1]);
//    			System.out.println(k+" "+j);
    			df[d.y][d.x]=d;
				dfpane.getChildren().add(d.g);
    	});
    	ndb0.keySet().forEach(e->{
    		String[] pos=e.split(",");
			int k=Integer.parseInt(pos[0]),j=Integer.parseInt(pos[1]);
    		if(ndb0.get(e) instanceof zhuque) {
    			//会导致火焰材质错误，不弄开关了
//				zhuque z1=(zhuque) ndb0.get(e);
//				zhuque z=(zhuque) db0[j][k];
//				if(z1.openf) z.open();
//				else z.close();
			}
    		if(ndb0.get(e) instanceof guanzhu) {
    			try {
    			guanzhu g1=(guanzhu) ndb0.get(e);
    			guanzhu g=(guanzhu) db0[j][k];
				HashMap<String,Integer> store=g.store,store1=g1.store;
				store.clear();
				store1.keySet().forEach(e1->{
					store.put(e1, store.get(e1));
				});
    			}catch(Exception e1) {}
			}
    	});
    	if(lznotfit.size()!=0) {
    		lznotfit.keySet().forEach(e->{
    			longzhua n=lznotfit.get(e);
    			String[] pos=e.split(",");
    			int k=Integer.parseInt(pos[0]),j=Integer.parseInt(pos[1]);
				db[j][k] = n;
				machines.set(n.machineindex, n);
				lzpane.getChildren().add(n.g);
				dfbpane.getChildren().add(n.g0);
				if (null != n.ng) {
					dfupane.getChildren().add(n.ng);
				}
			});
    	}
    }
    private void seteffect() {
    	if(effectf) {
			ui.setEffect(ds0);
			ds4.setColor(Color.color(0, 0, 0,1));
			ds4.setOffsetY(-10*suofang);
			ds4.setRadius(20*suofang);
			shixu.setEffect(ds4);
			ds3.setColor(Color.color(0, 0, 0));
			shixuyinyu.setEffect(ds3);
			ds.setOffsetY(3*suofang);
			ds.setColor(Color.color(0, 0, 0));
			ds2.setSpread(0.4);
			ds6.setSpread(0.7);
			mubanleft.setEffect(ds2);
			mubanright.setEffect(ds2);
			handlepane.setEffect(new Bloom());
			ds8.setChoke(0.7);
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
	        dfpane.setEffect(is2);
	        selectionBox.setEffect(new GaussianBlur(30*suofang));
	        
//	        InnerShadow ins= new InnerShadow(20, Color.color(0, 0, 0,1));
//	        ins.setHeight(255);
//	        ins.setChoke(0.4);           // 增加收缩比例
//	        udpane.setEffect(ins);
		}
    	else {
    		ui.setEffect(null);
    		shixu.setEffect(null);
    		shixuyinyu.setEffect(null);
//    		mouseiv.setEffect(null);
    		mubanleft.setEffect(null);
			mubanright.setEffect(null);
    	}
	}
    
	void refreshshixupane() {
    	 double wn=screenwidth/70/suofang;
	   	  sxgp.getChildren().clear();
	   	  grabshixuupdown();
	   	  for(int i=0;i<4;i++) {
	   		  for(int j=0;j<wn;j++) {
	   			  int nx=nowlookshixu+j; 
	   			  int ny=nowlookshixuy+i;
	   			  String k=nx+","+ny;
	   			  shixuan sxNode = shixuuses.get(k);
	   			  if (sxNode != null && !sxgp.getChildren().contains(sxNode))
	   				  sxgp.add(sxNode, j, i);
	   		  }
	   	  }
	   	  shixulength=0;
	   	  shixuuses.keySet().forEach(e->{
//	   		  System.out.println(e);
	   		  int m=Integer.parseInt(e.split(",")[0])+1;
	   		  if(m>shixulength)shixulength=m;
	   	  });
//	   	  System.out.println(shixulength);
	}
//	public HashSet<Point2D> readgrid(Image im){
//		HashSet<Point2D> ps=new HashSet<Point2D>();
//		PixelReader reader = im.getPixelReader();
//        for (double y = 130.0*scale1; y < im.getHeight(); y+=260.0*scale1) {
//            for (double x =  140*scale1; x < im.getWidth(); x+=150.0*scale1) {
//              if(reader.getColor((int)x, (int)y).getOpacity()!=0) {
//            	  int nx=(int)(x/(150.0/scale));
//            	  int ny=(int)(y/(260.0/scale));
//            	  ps.add(new Point2D(nx,ny));
//              }
//            }
//        }
//		return ps;
//	}
	public HashSet<Point2D> readgrid(Image im){
		HashSet<Point2D> ps=new HashSet<Point2D>();
		PixelReader reader = im.getPixelReader();
        for (int y = 130; y < im.getHeight(); y+=260) {
            for (int x =  140; x < im.getWidth(); x+=150) {
              if(reader.getColor(x, y).getOpacity()!=0) {
            	  int nx=(int)(x/150);
            	  int ny=(int)(y/260);
            	  ps.add(new Point2D(nx,ny));
              }
            }
        }
		return ps;
	}
	//注意这里使用的直线公
    public blend makehunyuan(int x,int y) {
    	if((x+y)%2==0) {
    		double ofx=nowhoverposx-x*150;
    		double ofy=nowhoverposy-y*260;
    		double sqt3=Math.sqrt(3);
    		if(ofy<ofx/sqt3) {
    			if(ofy<(-ofx+300)/sqt3) y--;
    			else x++;
    		}
    		else {
    			if(ofx<150) x--;
    			else x++;
    		}
    	}
    	if(x<MAPWIDTH&&y<MAPHEIGHT&&db[y][x]==null) {
	    	GaussianBlur gb1= new GaussianBlur();
	        gb1.setRadius(80);
	    	Group hunyuan=new Group();
			ImageView xuan=new ImageView(blend1i);
			xuan.setFitWidth(300);
			xuan.setFitHeight(289);
			hunyuan.setTranslateX(x*150);
			hunyuan.setTranslateY(y*260);
	        hunyuan.getChildren().add(xuan);
	        hypane.getChildren().add(hunyuan);
	        blend hy=new blend(x, y,"混元",x+","+y+","+System.currentTimeMillis(), hunyuan);
	        hy.gk=this;
			db[y][x]=hy;
			dbhistory.forEach(e1->{
				e1.put(hy.key,hy);
			});
	    	hy.machineindex=machines.size();
	    	machines.add(hy);//添加到机器链

			grabshixuupdown();//刷新时序面板
			return hy;
    	}
		return null;
    }
    public boolean makeseal(String name, guanzhu gz, int x,int y) {
    	 	Image iv=new Image(getClass().getResourceAsStream("sealpic/"+name+".png"));
    	 	Image iv1=new Image(getClass().getResourceAsStream("sealpic/"+name+"1.png"));
            ImageView g2=new ImageView(iv),g3=new ImageView(iv1);
            Group g0=new Group(g3,g2);    
//            System.out.println(0);
            if(gz.finishf) {
            	if(gz.name.equals("九龙灌")) {
//            	System.out.println(1);
				if (name.equals("黄极灵印")) {
//					System.out.println(2);
					g0.setTranslateX(550+gz.g.getTranslateX());
					g0.setTranslateY(620+gz.g.getTranslateY());
					fxpane.getChildren().add(g0);
					g0.toBack();
//					gz.fxs.forEach(e -> {
//						Rectangle fullBounds = new Rectangle(0, 0, 1800, 1300);
//						Rectangle originalClip = new Rectangle(600, 700, 565, 565);
//						Shape inverseClip = Shape.subtract(fullBounds, originalClip);
//						e.setClip(inverseClip);
//					});
//					try {
//						Rectangle fullBounds = new Rectangle(0, 0, 1800, 1300);
//						Rectangle originalClip = new Rectangle(600, 700, 565, 565);
//						Shape inverseClip = Shape.subtract(fullBounds, originalClip);
//						gz.ps.setClip(inverseClip);
//					}catch(Exception e) {}
					Bloom b = new Bloom();
					b.setThreshold(1);
					g0.setEffect(b);
					exitguanka();
					return true;
            	}
				return false;
            }	
            return false;
            }
            return false;
    }
	public void makejiulongguan(int x,int y) {
		x-=6;y-=2;
		double ofx=nowhoverposx-x*150;
		if((x+y)%2==1) {
    		if(ofx<=150) {}
    		else x+=2;
		}
		else {
			if(ofx<=150)x--;
			else x++;
		}
		if(x<MAPWIDTH-11&&y<MAPHEIGHT-4&&x>-1&&y>-1) {
			ImageView jli=new ImageView(jiulongguani);
			jli.setFitWidth(1800);
			jli.setFitHeight(1300);
			Group g=new Group(jli);
			int kx=x;int ky=y;
			int ctx=3,cty=2;//印放置点
			HashSet<Point2D> gs=readgrid(jiulongguani);
			guanzhu jl=new guanzhu(x, y,ctx,cty, g, "九龙灌",x+","+y+","+System.currentTimeMillis(), gs,this);
			jl.gk=this;
			//左上角第一个空格是它的本体(0,0)
//			System.out.println(x+","+y);
			String[] gss={"0,4空囚牛1,4空囚牛2,4空囚牛","0,3空睚眦1,3空睚眦2,3空睚眦","0,2空嘲风1,2空嘲风2,2空嘲风",
					"2,0空蒲牢2,1空蒲牢3,1空蒲牢","4,1空狻猊5,1空狻猊6,1空狻猊","7,1空霸下8,0空霸下8,1空霸下",
					"8,2空狴犴9,2空狴犴10,2空狴犴","8,3空负屃9,3空负屃10,3空负屃","8,4空螭吻9,4空螭吻10,4空螭吻"};
			try {
			for(int i=0;i<gss.length;i++) {
				String name=gss[i].substring(3,6);
				String[] poss=gss[i].split(name);
//				System.out.println(name);
				int[] xs=new int[3],ys=new int[3];
				for(int j=0;j<3;j++) {
					String[] ps=poss[j].split(",");
					xs[j]=Integer.parseInt(ps[0])+x;
					ys[j]=Integer.parseInt(ps[1])+y;
				}
				String key="";
				for(int j=0;j<poss.length;j++) {
					key+=xs[j]+","+ys[j]+name;
				}
//				System.out.println(key);
				jl.store.put(key, 0);
			}
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			jl.guanzhueffect();
			gs.iterator().forEachRemaining(e->{
				int y0=(int)e.getY()+ky,x0=(int)e.getX()+kx;
//				System.out.println(x0+","+y0);
				if(null!=db0[y0][x0])
		            db0[y0][x0].remove();
				db0[y0][x0]=jl;
				gridv[y0][x0].setOpacity(0.01);
				});
			db0history.forEach(e1->{
				e1.put(jl.key,jl);
			});
			udpane.getChildren().add(g);
			g.setTranslateX(jl.posx);
			g.setTranslateY(jl.posy);	
		}
	}
	public blend makeliuhehunyuan(int x,int y) {
    	Group hunyuan=new Group();
    	double ofx=nowhoverposx-x*150;
		double ofy=nowhoverposy-y*260;
		double sqt3=Math.sqrt(3);
    	if((x+y)%2==1) {
    		if(ofy>=(ofx-300)/sqt3+260&&ofx<=150)x-=2;
    		if(ofy<=(ofx-300)/sqt3+260&&ofy<=260-ofx/sqt3) {x--;y--;}
//    		if(ofy>=260-ofx/sqt3&&ofx>=150) {}
    	}
    	else {
    		if(ofy<=ofx/sqt3&&ofx>=150) {y--;}
    		if(ofy<=(-ofx+300)/sqt3&&ofx<=150) {x-=2;y--;}
    		if(ofy>=ofx/sqt3&&ofy>=(-ofx+300)/sqt3) {x--;}
    	}
    	y--;x--;
    	if(y%2==1||(y%2==0&&x>0))
    	if(x<MAPWIDTH-2&&y<MAPHEIGHT-1&&db[y][x]==null&&db[y][x+1]==null&&db[y][x+2]==null&&
		db[y+1][x]==null&&db[y+1][x+1]==null&&db[y+1][x+2]==null) {
			ImageView taiyang=new ImageView(taiyangi) ;
			taiyang.setTranslateX(177+300);
			taiyang.setTranslateY(127+260);
			taiyang.setFitHeight(268);
			taiyang.setFitWidth(259);
			taiyang.setScaleX(2);
			taiyang.setScaleY(2);
			taiyang.getTransforms().add(new Rotate(0,129.5,134));
			ImageView jinwu=new ImageView(jinwui) ;
			jinwu.setTranslateX(125+300);
			jinwu.setTranslateY(78+260);
			jinwu.setFitHeight(365);
			jinwu.setFitWidth(365);
			jinwu.setScaleX(2);
			jinwu.setScaleY(2);
			jinwu.getTransforms().add(new Rotate(0,182.5,182.5));
			ImageView liuhe=new ImageView(blend2i) ;
			liuhe.setFitHeight(1040);
			liuhe.setFitWidth(1200);
			Timeline t = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(taiyang.rotateProperty(),0),new KeyValue(jinwu.rotateProperty(),0)),
					new KeyFrame(Duration.seconds(2),new KeyValue(taiyang.rotateProperty(),180),new KeyValue(jinwu.rotateProperty(),-180)),
					new KeyFrame(Duration.seconds(4),new KeyValue(taiyang.rotateProperty(),360),new KeyValue(jinwu.rotateProperty(),-360)));
	      
	        t.setCycleCount(-1);
	        t.play();
	        hunyuan.getChildren().addAll(liuhe,taiyang,jinwu);
	        hunyuan.setTranslateX(x*150-150);
	        hunyuan.setTranslateY(y*260);
			hypane.getChildren().add(hunyuan);
			blend lh=new blend(x, y, "六合",x+","+y+","+System.currentTimeMillis(), hunyuan);
			db[y][x]=lh;db[y][x+1]=lh;db[y][x+2]=lh;db[y][x+3]=lh;db[y][x+4]=lh;
			db[y+1][x-1]=lh;db[y+1][x]=lh;db[y+1][x+1]=lh;db[y+1][x+2]=lh;db[y+1][x+3]=lh;db[y+1][x+4]=lh;db[y+1][x+5]=lh;
			db[y+2][x-1]=lh;db[y+2][x]=lh;db[y+2][x+1]=lh;db[y+2][x+2]=lh;db[y+2][x+3]=lh;db[y+2][x+4]=lh;db[y+2][x+5]=lh;
			db[y+3][x]=lh;db[y+3][x+1]=lh;db[y+3][x+2]=lh;db[y+3][x+3]=lh;db[y+3][x+4]=lh;
			lh.gk=this;
			dbhistory.forEach(e1->{
				e1.put(lh.key,lh);
			});
			lh.machineindex=machines.size();
			machines.add(lh);//添加到机器链
			grabshixuupdown();//刷新时序面板
			return lh;
    	}
		return null;
    }

    public blend makewuzang(int x,int y) {
    	double ofx=nowhoverposx-x*150;
		double ofy=nowhoverposy-y*260;
		double sqt3=Math.sqrt(3);
    	if((x+y)%2==1) {
    		if(ofy>=(ofx-300)/sqt3+260&&ofx<=150)x-=2;
    		if(ofy<=(ofx-300)/sqt3+260&&ofy<=260-ofx/sqt3) {x--;y--;}
//    		if(ofy>=260-ofx/sqt3&&ofx>=150) {}
    	}
    	else {
    		if(ofy<=ofx/sqt3&&ofx>=150) {y--;}
    		if(ofy<=(-ofx+300)/sqt3&&ofx<=150) {x-=2;y--;}
    		if(ofy>=ofx/sqt3&&ofy>=(-ofx+300)/sqt3) {x--;}
    	}
    	if(y%2==1||(y%2==0&&x>0))
    	if(x<MAPWIDTH-2&&y<MAPHEIGHT-1&&db[y][x]==null&&db[y][x+1]==null&&db[y][x+2]==null&&
		db[y+1][x]==null&&db[y+1][x+1]==null&&db[y+1][x+2]==null) {
//    		System.out.print(x+" "+y);
	    	Group w0=new Group();
			ImageView w1=new ImageView(wuzang[wuzangrollf]);
			w0.getChildren().add(w1);
			if(wuzangrollf==1) {
				w0.setTranslateY(y*260-8);
				w0.setTranslateX(x*150-15);
				
			}
			else if(wuzangrollf==2) {
				w0.setTranslateY(y*260-15);
				w0.setTranslateX(x*150-10);
			}
			else if(wuzangrollf==3) {
				w0.setTranslateY(y*260-24);
				w0.setTranslateX(x*150);
			}
			else if(wuzangrollf==5) {
				w0.setTranslateY(y*260-3);
				w0.setTranslateX(x*150+5);
			}
			else if(wuzangrollf>2&&wuzangrollf<5){
				w0.setTranslateY(y*260-20);
				w0.setTranslateX(x*150);
			}
			else {
				w0.setTranslateY(y*260);
				w0.setTranslateX(x*150);
			}
			if(wuzangrollf==0||wuzangrollf==3) {
				w1.setFitWidth(600);
				w1.setFitHeight(553);
			}
			else {
				w1.setFitWidth(606);
				w1.setFitHeight(530);
			}
			w0.setRotate(wuzangrollf*60);
			hypane.getChildren().add(w0);
			
			blend wz=new blend(x, y,"五脏",x+","+y+","+System.currentTimeMillis(), w0);
			wz.gk=this;
			wz.face=wuzangrollf;
			db[y][x]=wz;db[y][x+1]=wz;db[y][x+2]=wz;
			db[y+1][x]=wz;db[y+1][x+1]=wz;db[y+1][x+2]=wz;
			dbhistory.forEach(e1->{
				e1.put(wz.key,wz);
			});
			wz.machineindex=machines.size();
			machines.add(wz);//添加到机器链
			grabshixuupdown();//刷新时序面板
			return wz;
    	}
    	return null;
    }
    public baihu makebaihu(int x,int y) {
    	double ofx=nowhoverposx-x*150;
		double ofy=nowhoverposy-y*260;
		double sqt3=Math.sqrt(3);
    	if((x+y)%2==1) {
    		if(ofy>=(ofx-300)/sqt3+260&&ofx<=150)x-=2;
    		if(ofy<=(ofx-300)/sqt3+260&&ofy<=260-ofx/sqt3) {x--;y--;}
//    		if(ofy>=260-ofx/sqt3&&ofx>=150) {}
    	}
    	else {
    		if(ofy<=ofx/sqt3&&ofx>=150) {y--;}
    		if(ofy<=(-ofx+300)/sqt3&&ofx<=150) {x-=2;y--;}
    		if(ofy>=ofx/sqt3&&ofy>=(-ofx+300)/sqt3) {x--;}
    	}
    	if(y%2==1||(y%2==0&&x>0))
    	if(x<MAPWIDTH-2&&y<MAPHEIGHT-1&&db0[y][x]==null&&db0[y][x+1]==null&&db0[y][x+2]==null&&
		db0[y+1][x]==null&&db0[y+1][x+1]==null&&db0[y+1][x+2]==null) {
//    		System.out.print(x+" "+y);
	    	Group b0=new Group();
			ImageView bd=new ImageView(baihui0);
			bd.setFitWidth(600);
			bd.setFitHeight(520);
			Group b1=new Group();
			ImageView dc=new ImageView(baihuis[0]);
			dc.setFitWidth(600);
			dc.setFitHeight(520);
			ImageView xc0=new ImageView(baihuis[5]);
			xc0.setFitWidth(156);
			xc0.setFitHeight(156);
			xc0.setTranslateX(238);
			xc0.setTranslateY(366);
			ImageView xc1=new ImageView(baihuis[5]);
			xc1.setFitWidth(156);
			xc1.setFitHeight(156);
			xc1.setTranslateX(74);
			xc1.setTranslateY(288);
			ImageView xc2=new ImageView(baihuis[5]);
			xc2.setFitWidth(156);
			xc2.setFitHeight(156);
			xc2.setTranslateX(56);
			xc2.setTranslateY(106);
			ImageView xc3=new ImageView(baihuis[5]);
			xc3.setFitWidth(156);
			xc3.setFitHeight(156);
			xc3.setTranslateX(204);
			xc3.setTranslateY(-1);
			ImageView xc4=new ImageView(baihuis[5]);
			xc4.setFitWidth(156);
			xc4.setFitHeight(156);
			xc4.setTranslateX(368);
			xc4.setTranslateY(77);
			ImageView xc5=new ImageView(baihuis[5]);
			xc5.setFitWidth(156);
			xc5.setFitHeight(156);
			xc5.setTranslateX(390);
			xc5.setTranslateY(260);
			b1.getChildren().addAll(xc0,xc1,xc2,xc3,xc4,xc5,dc);
			
			ImageView bu=new ImageView(baihui1);
			bu.setFitWidth(600);
			bu.setFitHeight(520);
			
			Pane b2=new Pane(b1);
//			b2.getChildren().add(bu);
			b2.setEffect(ds1);
			//Group套group容易导致渲染失败
			
			b0.getChildren().addAll(bd,b2,bu);

			b0.setTranslateX(150*x);
			b0.setTranslateY(260*y);
			b0.setClip(new Polygon(150,0,450,0,600,260,450,520,150,520,0,260));
			udpane.getChildren().add(b0);
//			tocap=udpane;
			Timeline t1 = new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(b1.rotateProperty(),0)),
//							new KeyValue(xc0.rotateProperty(),0),new KeyValue(xc1.rotateProperty(),0),
//							new KeyValue(xc2.rotateProperty(),0),new KeyValue(xc3.rotateProperty(),0),
//							new KeyValue(xc4.rotateProperty(),0),new KeyValue(xc5.rotateProperty(),0)),
							new KeyFrame(Duration.seconds(2),new KeyValue(b1.rotateProperty(),-180)
//							new KeyValue(xc0.rotateProperty(),360),new KeyValue(xc1.rotateProperty(),360),
//							new KeyValue(xc2.rotateProperty(),360),new KeyValue(xc3.rotateProperty(),360),
//							new KeyValue(xc4.rotateProperty(),360),new KeyValue(xc5.rotateProperty(),360)
							));
			t1.setCycleCount(Timeline.INDEFINITE);
			t1.play();
			tms.add(t1);
//			t1.setOnFinished(e->{
//				overlaySnapshots();
//				;
//				 File outputFile = new File("output.png");
//		            
//		            // 转换并保存图
//		            BufferedImage bImage = SwingFXUtils.fromFXImage(overlayedImage, null);
//		            try {
//						ImageIO.write(bImage, "png", outputFile);
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//			});
			baihu bh=new baihu(x, y,"白虎",x+","+y+","+System.currentTimeMillis(), b0);
			bh.upf=0;
			bh.tl=t1;
			b1.rotateProperty().addListener(e->{
				if(bh.upf!=(-b1.getRotate())/15) {
					bh.upf=(int) ((-b1.getRotate())/15);
//					dc.setRotate(-b1.getRotate());
				}
				dc.setRotate(-((-b1.getRotate())%15)*2-b1.getRotate());
				xc0.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				xc1.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				xc2.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				xc3.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				xc4.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				xc5.setRotate(((-b1.getRotate())%30)-b1.getRotate());
				
//				new p(5+((int)(xc0.getRotate()/2))%5);
				dc.setImage(baihuis[4-(-(int)(dc.getRotate()/3+b1.getRotate()/3))%5]);
				xc0.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
				xc1.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
				xc2.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
				xc3.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
				xc4.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
				xc5.setImage(baihuis[5+((int)(xc0.getRotate()))%5]);
//				captureSnapshot();
			});

			bh.gk=this;
			gridv[y][x].setOpacity(0);gridv[y][x+1].setOpacity(0);gridv[y][x+2].setOpacity(0);
			gridv[y+1][x].setOpacity(0);gridv[y+1][x+1].setOpacity(0);gridv[y+1][x+2].setOpacity(0);
			db0[y][x]=bh;db0[y][x+1]=bh;db0[y][x+2]=bh;
			db0[y+1][x]=bh;db0[y+1][x+1]=bh;db0[y+1][x+2]=bh;
			db0history.forEach(e1->{
				e1.put(bh.key,bh);
			});
			bh.machineindex=machines.size();
			machines.add(bh);//添加到机器链
			grabshixuupdown();//刷新时序面板
			return bh;
    	}
		return null;
    }
    public zhuque makezhuque(int x,int y) {
    	ImageView z0,z,z1,z2;
    	if(db0[y][x]==null){
    	if((x+y)%2==1) {
    		z0=new ImageView(zhuquedown1i);
    		z=new ImageView();
    		z.setRotate(180);
    		z1=new ImageView(zhuquemiddle1i);
    		z2=new ImageView(zhuqueup10i);
    		z2.setOpacity(0.1);
//    		ash.setTranslateX(-150);
//    		ash.setTranslateY(-87);
    	}
    	else {
    		z0=new ImageView(zhuquedown2i);
    		z=new ImageView();
    		z1=new ImageView(zhuquemiddle2i);
    		z2=new ImageView(zhuqueup20i);
    		z2.setOpacity(0.1);
//    		ash.setTranslateX(-150);
//    		ash.setTranslateY(-173);
    	}
    	z0.setFitWidth(300);
    	z0.setFitHeight(260);
    	z.setFitWidth(300);
    	z.setFitHeight(260);
    	z1.setFitWidth(300);
    	z1.setFitHeight(260);
    	z2.setFitWidth(300);
    	z2.setFitHeight(260);
    	Group zhuque=new Group();
    	AshEffect ash=null;
    	if(lowgrahf) {
    		zhuque.getChildren().addAll(z0,z,z1,z2);
    	}
    	else {
    		ash=new AshEffect();
    		zhuque.getChildren().addAll(z0,z,z1,z2,ash);
    	}
    		
    	zhuque.setTranslateX(x*150);
    	zhuque.setTranslateY(y*260);
		udpane.getChildren().add(zhuque);
//		gpane.getChildren().remove(gridv[y][x]);
		gridv[y][x].setOpacity(0);
		zhuque zq=new zhuque(x, y,"朱雀",x+","+y+","+System.currentTimeMillis(), zhuque,ash);
		zq.gk=this;
		zq.openf=true;
		zq.machineindex=machines.size();
		machines.add(zq);//添加到机器链
		grabshixuupdown();//刷新时序面板
		db0[y][x]=zq;
		db0history.forEach(e1->{
			e1.put(zq.key,zq);
		});
		return zq;
    	}
		return null;
    }

    public void stop() {
        executor.shutdown();
    }
//    private void startpixelProcessing() {
//        Image snapshot = game.snapshot(params, null);
//        Task<Image> processingTask = new Task<Image>() {
//            protected Image call() throws Exception {
//                // 在后台线程执行（此处可替换为实际参数
//                return imgmake.process2(snapshot,pixelsize);
////                iv.setSmooth(true); // 启用抗锯齿平
////            	return imgmake.fastpixeler(snapshot,pixelsize);
//            }
//        };
//        processingTask.setOnSucceeded(e -> {
////        		targetX=root.getTranslateX();
////        		targetY=root.getTranslateY();
//        		rooti=processingTask.getValue();
//          		alli.setImage(rooti);
//        });
//        processingTask.setOnFailed(e -> {
//            Throwable ex = processingTask.getException();
//            ex.printStackTrace();
//        });
//        new Thread(processingTask).start();
//    }
    //调制特效，没用到
//    public Effect makeblend(BlendMode bm,Effect e1,Effect e2,double op) {
//		Blend b=new Blend();
//		b.setMode(bm);
//		b.setOpacity(op);
//		b.setTopInput(e1);
//		b.setBottomInput(e2);
//		return b;
//	}
    //宝石进入特效
    /**int方向dir下，1右下右上上，4左上左下*/
    public void entermachine(define d,int dir,blend b) {
    	double angle=60*dir,tx=260*Math.sin(Math.toRadians(angle)),ty=260*Math.cos(Math.toRadians(angle));
    	 Polygon p=new Polygon(23,86,0,0,23,86,323,86,346,0,323,86,173,346);
	     d.g.setClip(p);
    	Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.8/playrate),
    			new KeyValue(d.g.opacityProperty(),0.5),
    			new KeyValue(d.g.translateXProperty(),d.g.getTranslateX()+tx),
    			new KeyValue(d.g.translateYProperty(),d.g.getTranslateY()+ty),
    			new KeyValue(p.translateXProperty(),d.upf==1?tx:-tx),
    			new KeyValue(p.translateYProperty(),d.upf==1?ty:-ty)));
    	t.play();
    	t.setOnFinished(e->{
    		b.store.put(dir, d.name);
    		df[d.y][d.x]=null;
    	});
    }
    private void updateSmoothPosition() {
        double deltaX =  root.getTranslateX()-targetX;
        double deltaY = root.getTranslateY()-targetY;
 
        rootv.setTranslateX(deltaX * SMOOTH_FACTOR);
        rootv.setTranslateY(deltaY * SMOOTH_FACTOR);
    }
    
    public void openbook(){
    	ds1.setOffsetY(13);
		ds1.setOffsetX(8);
    	double sc=screenheight/601/1.5;
    	Rotate r1= (Rotate) zhengjin.getTransforms().get(0);
        r1.setAngle(0);
    	
    	zhengjin.setOnMouseDragged(null);
    	zhengjin.setOnMouseClicked(e->{
    		if(e.getButton()==MouseButton.PRIMARY) {
    			nowpage=(nowpage+1)%5;
    			zhengjin.setImage(zhengjins[nowpage]);
    		}
    		else {
    			if(nowpage>0) {
    				nowpage=(nowpage-1)%5;
    				zhengjin.setImage(zhengjins[nowpage]);
    			}
    			else {
    				closebook();
    			}
    		}
    	});
    	zhengjin.setScaleX(sc);
    	zhengjin.setScaleY(sc);
    	if(leftmubanf)
    		zhengjin.setTranslateX(screenwidth/2-433/2*(sc+1)+40 * scalemubanl-50);
    	else
    		zhengjin.setTranslateX(screenwidth/2-433/2*(sc+1)+200 * scalemubanl-50);
    	zhengjin.setTranslateY(601/2*(sc-1)+40);
    	
    }
    
    public void closesetting() {
    	setting.setScaleX(scalemubanl*0.2);
    	setting.setScaleY(scalemubanl*0.2);
        Rotate r=new Rotate(0,798/ 2,1028/ 2);
        setting.getTransforms().add(r);
        if(effectf)
        	setting.setEffect(ds1);
        ds1.setOffsetY(13);
		ds1.setOffsetX(8);
   // 鼠标按下事件：记录初始位
		setting.setTranslateX(-798/2*(1-scalemubanl*0.2)+100*suofang);
		setting.setTranslateY(-1028/2*(1-scalemubanl*0.2)+50*suofang);
		setting.setOnMouseClicked(null);
		setting.setOnMousePressed(event -> {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            pressStartTime= System.currentTimeMillis(); 
            // 记录组件中心初始位置
            initialCenterX = setting.getTranslateX()+ 798/ 2+mubanleft.getTranslateX();
            initialCenterY = setting.getTranslateY()+ 1028/ 2;
            // 计算初始距离
            initialDistance = Math.hypot(
                initialCenterX - orgSceneX,
                initialCenterY - orgSceneY
            );
            event.consume();
        });

        // 鼠标拖动事件：计算反向延长线位置和旋转角
		setting.setOnMouseDragged(event -> {
			if(event.getButton()==MouseButton.PRIMARY) {
        	nowmousex=event.getScreenX();
        	nowmousey=event.getScreenY();
        	 double currentX = event.getSceneX();
             double currentY = event.getSceneY();
             // 计算从初始点到当前点的向
             double deltaX = currentX - orgSceneX;
             double deltaY = currentY - orgSceneY;
             double distance = Math.hypot(deltaX, deltaY);
             if (distance == 0) return; // 防止除以
             // 计算反向单位向量
             double unitX = -deltaX / distance;
             double unitY = -deltaY / distance;
             // 计算新中心位
             double newCenterX = currentX + unitX * initialDistance;
             double newCenterY = currentY + unitY * initialDistance;
             // 更新组件位置
             setting.setTranslateX(newCenterX - 798/ 2-mubanleft.getTranslateX());
             setting.setTranslateY(newCenterY - 1028 / 2);
             // 计算旋转角度（组件指向拖动点
             double angle = Math.toDegrees(Math.atan2(
                 currentY - newCenterY,   // Y方向分量（注意坐标系方向
                 currentX - newCenterX    // X方向分量
             ));
             // 设置旋转角度（JavaFX角度系统需要调整）
             Rotate r1= (Rotate) setting.getTransforms().get(0);
             r1.setAngle(angle ); // 补偿坐标系差
             event.consume();
            ds1.setOffsetX(20*Math.sin(Math.toRadians(angle+30)));
            ds1.setOffsetY(20*Math.cos(Math.toRadians(angle+30)));
            event.consume();
			}
        });
		setting.setOnMouseReleased(e->{
			e.consume();
        	double deltaX = e.getSceneX()- orgSceneX;
            double deltaY = e.getSceneY()- orgSceneY;
        	long duration = System.currentTimeMillis() - pressStartTime;
        	if(e.getScreenX()>278*scalemubanl) switchsettingshow();
        	else if(deltaX==0&&deltaY==0&&duration<300)switchsettingshow();
        });
    }
    
    public void closebook() {
    	zhengjin.setImage(hunyuanzhengjini);
    	zhengjin.setScaleX(scalemubanl*0.4);
        zhengjin.setScaleY(scalemubanl*0.4);
        Rotate r=new Rotate(0,433/ 2,601/ 2);
        zhengjin.getTransforms().add(r);
        if(effectf)
        	zhengjin.setEffect(ds1);
        ds1.setOffsetY(13);
		ds1.setOffsetX(8);
   // 鼠标按下事件：记录初始位
		zhengjin.setTranslateX(-433/2*(1-scalemubanl*0.4)+100*suofang);
		zhengjin.setTranslateY(-601/2*(1-scalemubanl*0.4)+50*suofang);
		zhengjin.setOnMouseClicked(null);
		zhengjin.setOnMousePressed(event -> {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            pressStartTime= System.currentTimeMillis(); 
            // 记录组件中心初始位置
            initialCenterX = zhengjin.getTranslateX()+ 433/ 2+mubanleft.getTranslateX();
            initialCenterY = zhengjin.getTranslateY()+ 601/ 2;
            // 计算初始距离
            initialDistance = Math.hypot(
                initialCenterX - orgSceneX,
                initialCenterY - orgSceneY
            );
            event.consume();
        });

        // 鼠标拖动事件：计算反向延长线位置和旋转角
		zhengjin.setOnMouseDragged(event -> {
			if(event.getButton()==MouseButton.PRIMARY) {
        	nowmousex=event.getScreenX();
        	nowmousey=event.getScreenY();
        	 double currentX = event.getSceneX();
             double currentY = event.getSceneY();
             // 计算从初始点到当前点的向
             double deltaX = currentX - orgSceneX;
             double deltaY = currentY - orgSceneY;
             double distance = Math.hypot(deltaX, deltaY);
             if (distance == 0) return; // 防止除以
             // 计算反向单位向量
             double unitX = -deltaX / distance;
             double unitY = -deltaY / distance;
             // 计算新中心位
             double newCenterX = currentX + unitX * initialDistance;
             double newCenterY = currentY + unitY * initialDistance;
             // 更新组件位置
             zhengjin.setTranslateX(newCenterX - 433/ 2-mubanleft.getTranslateX());
             zhengjin.setTranslateY(newCenterY - 601 / 2);
             // 计算旋转角度（组件指向拖动点
             double angle = Math.toDegrees(Math.atan2(
                 currentY - newCenterY,   // Y方向分量（注意坐标系方向
                 currentX - newCenterX    // X方向分量
             ));
             // 设置旋转角度（JavaFX角度系统需要调整）
             Rotate r1= (Rotate) zhengjin.getTransforms().get(0);
             r1.setAngle(angle ); // 补偿坐标系差
             event.consume();
            ds1.setOffsetX(20*Math.sin(Math.toRadians(angle+30)));
            ds1.setOffsetY(20*Math.cos(Math.toRadians(angle+30)));
            event.consume();
			}
        });
		zhengjin.setOnMouseReleased(e->{
			e.consume();
        	double deltaX = e.getSceneX()- orgSceneX;
            double deltaY = e.getSceneY()- orgSceneY;
        	long duration = System.currentTimeMillis() - pressStartTime;
        	if(e.getScreenX()>278*scalemubanl) openbook();
        	else if(deltaX==0&&deltaY==0&&duration<300)openbook();
        });
    }
    
    public void inixuanwu() {
    	Image xuanwui=new Image(getClass().getResourceAsStream("blendpic/玄武ini.png"));
        ImageView xw=new ImageView(xuanwui);
        ixuanwu=xw;
        xw.setSmooth(false);
        if(effectf)
        	xw.setEffect(ds5);
        double iniscale=scale;
        xw.setScaleX(iniscale);
        xw.setScaleY(iniscale);

        xw.setTranslateX(-262/2*(1-iniscale));
        xw.setTranslateY(-230/2*(1-iniscale)+230*suofang);
//        double px=262,py=260;
        double px=262,py=200+262/2*scale;
//        double px=302-262/2*scale,py=230+262/2*scale;
        xw.setOnMousePressed(e->{
        	stopputxuanwuf=false;
        	if(putxuanwutimeline!=null)
        		putxuanwutimeline.stop();
        	putxuanwutimeline=null;
        	lastputxuanwu=null;
        	lastdelhy=-1;
        	lastdelhx=-1;
        	shixumovezero();
        });
        xw.setOnMouseDragged(e->{
        	if(e.getButton()==MouseButton.PRIMARY) {
        	inimachinedrag=true;
        	nowmousex=e.getScreenX();
        	nowmousey=e.getScreenY();
        	hovergrid(xw, xuanwui, e,px,py,iniscale,"blendpic/玄武ini.png");
        	}
        });
        xw.setOnMouseReleased(e->{     	
        	xw.setOpacity(1);
        	if(canputf&&hovergridx!=-1&&hovergridy!=-1&&nowmousex<mubanright.getTranslateX()) {
        		canputf=false;
        		if(iniitemplaces.containsKey("玄武")) {
					xw.setTranslateX(iniitemplaces.get("玄武").getX());
				    xw.setTranslateY(iniitemplaces.get("玄武").getY());
				}
				else {
					xw.setTranslateX(-262/2*(1-iniscale));
	                xw.setTranslateY(-230/2*(1-iniscale)+300*suofang);
				}
        		linexuanwu();
        		
        	}
        	else if (nowmousex>mubanright.getTranslateX()) {
        		Point2D p=new Point2D(xw.getTranslateX(),xw.getTranslateY());
        		iniitemplaces.put("玄武",p);
        	}
        	canmakecloneblend=true;
        	mubanright.getChildren().remove(nowgrab);
         	nowgrab=null;
			nowgrabname=null;
        });
        mubanright.getChildren().add(xw);
        addedmachines.add(xw);
    }

    public Group prevxuanwu(int x,int y) {
    	ImageView x1=null;
    	Group xuanwu=new Group();
    	if((x+y)%2==1) {
    		x1=new ImageView(xuanwu1);
    	}
    	else {
    		x1=new ImageView(xuanwu2);
    	}
    	x1.setFitWidth(300);
    	x1.setFitHeight(260);
    	xuanwu.getChildren().add(x1);
    	xuanwu.setTranslateX(x*150);
    	xuanwu.setTranslateY(y*260);
		gpane.getChildren().add(xuanwu);
//		xuanwu.setOpacity(0.5);
		xuanwu.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
				stopputxuanwuf=true;
		});
		xuanwu.setOnMouseEntered(e->{
			hovergridx=x;
			hovergridy=y;
		});
		return xuanwu;
    }
    public void xuanwumove(xuanwu xw,int dir) {
    	xuanwu now=xw;
    	if(dir==0) {
	    	for(int i=0;i<xw.listsize;i++) {
	    		if(null!=db[now.y][now.x]&&db[now.y][now.x].name.equals("青龙")) {
	    			if(null!=now.next&&((null!=db[now.next.y][now.next.x]&&db[now.next.y][now.next.x] instanceof longzhua
	    					&&((longzhua)(db[now.next.y][now.next.x])).stuckf==false)||null==db[now.next.y][now.next.x])) {
	    				((longzhua)(db[now.y][now.x])).stuckf=false;
//	    				System.out.println(1+" "+(now.next.x)+" "+(now.next.y));
	    				longzhua tomovelz=(longzhua)(db[now.y][now.x]);
	    				tomovelz.movelongzhua(now.next.x-now.x, now.next.y-now.y);
	    			}
	    			else ((longzhua)(db[now.y][now.x])).stuckf=true;
	    		}
	    		now=now.next;
	    	}
    	}
    	else if(dir==1) {
    		for(int i=0;i<xw.listsize;i++) {
	    		if(null!=db[now.y][now.x]&&db[now.y][now.x].name.equals("青龙")) {
	    			if(null!=now.prev&&((null!=db[now.prev.y][now.prev.x]&&db[now.prev.y][now.prev.x] instanceof longzhua
	    					&&((longzhua)(db[now.prev.y][now.prev.x])).stuckf==false)||null==db[now.prev.y][now.prev.x])) {
	    				((longzhua)(db[now.y][now.x])).stuckf=false;
//	    				System.out.println(2+" "+(now.prev.x)+" "+(now.prev.y));
	    				longzhua tomovelz=(longzhua)(db[now.y][now.x]);
	    				tomovelz.movelongzhua(now.prev.x-now.x, now.prev.y-now.y);
	    			}
	    			else ((longzhua)(db[now.y][now.x])).stuckf=true;
	    		}
	    		now=now.prev;
	    	}
    	}
    }
    public void linexuanwu() {
    	Group np=prevxuanwu(hovergridx,hovergridy);
		List<Integer> lx=new ArrayList<Integer>();
		List<Integer> ly=new ArrayList<Integer>();
		List<Group> lp=new ArrayList<Group>();
	    lx.add(hovergridx);
	    ly.add(hovergridy);
	    lp.add(np);
	    Bloom xuanwuputbloom=new Bloom();
	    np.setEffect(xuanwuputbloom);
	
	    //放置时右键可拖动画面
	    putxuanwutimeline=new Timeline(new KeyFrame(Duration.millis(1),e1->{
	    	if(stopputxuanwuf) {
	    		if(lx.size()==1) {
	    			gpane.getChildren().remove(lp.get(0));
	    			return;
	    		}
	    		else {
	    			makexuanwu1(lx, ly, lp);
	    		}	
	    		putxuanwutimeline.stop();
	    	}
	    	else {
	    		if(lx.size()>1&&(hovergridx==(int)lx.get(lx.size()-2)&&hovergridy==(int)ly.get(ly.size()-2))) {
	    			lastdelhx=hovergridx;lastdelhy=hovergridy;
	    			gpane.getChildren().remove(lp.get(lp.size()-1));
	    			lp.remove(lp.size()-1);
	    			lx.remove(lx.size()-1);
	    			ly.remove(ly.size()-1);
	    		}
	    		else if(hovergridx!=-1&&hovergridy!=-1
	    				&&!(lastdelhx==hovergridx&&lastdelhy==hovergridy)
	    				&&!(lx.indexOf(hovergridx)!=-1&&lx.indexOf(hovergridx)==ly.indexOf(hovergridy))
	    				&&Math.abs(hovergridx-lx.get(lx.size()-1))<=1
	    				&&Math.abs(hovergridy-ly.get(ly.size()-1))<=1
	    				&&!((hovergridx+hovergridy)%2==0&&hovergridy-ly.get(ly.size()-1)==-1)
	    				&&!((hovergridx+hovergridy)%2==1&&hovergridy-ly.get(ly.size()-1)==1)
	    				&&(Math.abs(hovergridx+hovergridy-lx.get(lx.size()-1)-ly.get(ly.size()-1))==1)) {
	    			lx.add(hovergridx);
	    			ly.add(hovergridy);
	    			Group np1=prevxuanwu(hovergridx,hovergridy);
//	    			System.out.println(hovergridx+" "+hovergridy);
	    			Bloom xuanwuputbloom1=new Bloom();
	    			np1.setEffect(xuanwuputbloom1);
	    			lp.add(np1);
	    		}
	    	}
	    	for(int i=0;i<lp.size();i++) {
	    		((Bloom)(lp.get(i).getEffect())).setThreshold(Math.sin(System.currentTimeMillis()/100));
	    	}
	    }));
	    putxuanwutimeline.setCycleCount(Timeline.INDEFINITE);
	    putxuanwutimeline.play();
    }
    public xuanwu makexuanwu1(List<Integer> lx,List<Integer> ly,List<Group> lp) {
    	int x1=lx.get(lx.size()-1),x2=lx.get(0),y1=ly.get(ly.size()-1),y2=ly.get(0);
		int maxx=-1,maxy=-1,minx=9999,miny=9999;
		int cancircle=0;
		xuanwu first=null,last=null;
		if(Math.abs(x1+y1-x2-y2)==1&&!((x1+y1)%2==0&&y1-y2==-1)&&!((x1+y1)%2==1&&y1-y2==1)){
			cancircle=1;
		}
		int size=lx.size();
		List<Point2D> pts=new ArrayList<Point2D>();
		List<Point2D>pts1=new ArrayList<Point2D>();
		List<xuanwu> xws=new ArrayList<xuanwu>();
		for(int i=0;i<size;i++) {
			xws.add(makexuanwu(lx.get(i),ly.get(i)));
			if(i==0)first=xws.get(0);
			try {
				gpane.getChildren().remove(lp.get(i));
			}catch(Exception e1) {}
			if(maxx<lx.get(i))maxx=lx.get(i);
			if(maxy<ly.get(i))maxy=ly.get(i);
			if(minx>lx.get(i))minx=lx.get(i);
			if(miny>ly.get(i))miny=ly.get(i);
			first.listsize++;
    	}
		last=xws.get(size-1);
		for(int i=0;i<size;i++) {
			xws.get(i).first=first;
			xws.get(i).last=last;
			xws.get(i).listsize=first.listsize;
			xws.get(i).machineindex=machines.size();//添加在机器链表中的位
		}	
		if(cancircle==1) {//闭合环线
			last.next=first;
			first.prev=last;
		}
		int lstx=-1,lsty=-1;
		
		machines.add(first);//添加到机器链
		grabshixuupdown();//刷新时序面板
		xuanwu f=first;
		db0history.forEach(e2->{
			e2.put(f.key,f);
		});
		
		//渲染闭环的那条线
		int lastx=lx.get(lx.size()-1),lasty=ly.get(ly.size()-1),firstx=lx.get(0),firsty=ly.get(0);
		boolean b=false;
		if((firstx+firsty)%2==0) {
			if((lastx-firstx==0&&lasty-firsty==-1)||(lastx-firstx==1&&lasty-firsty==0)||(lastx-firstx==-1&&lasty-firsty==0))
				b=true;
		}
		else{
			if((lastx-firstx==0&&lasty-firsty==1)||(lastx-firstx==1&&lasty-firsty==0)||(lastx-firstx==-1&&lasty-firsty==0))
				b=true;
		}
		if(lx.size()==2)b=false;
		if(b) {
			Line l=new Line(lastx*150+150,lasty*260+((lastx+lasty)%2==0?87:173), firstx*150+150, firsty*260+((firstx+firsty)%2==0?87:173));
			l.setStrokeWidth(20);
			l.setStroke(Color.color(0.6,0.6,0.9));
			udpane.getChildren().add(l);
			l.setEffect(gausb);
			f.added.add(l);
		}
		
		//以下包含将所有三角形外接边变成一个整体shape
		for(int i=0;i<lx.size();i++) {
			int x0=lx.get(i),y0=ly.get(i);
			pts.add(new Point2D(((x0-minx)*150+150),((y0-miny)*260+((x0+y0)%2==0?87:173))));
			//star的替代方法，加圆
			
			if(i!=lx.size()-1) {
				int x=lx.get(i+1),y=ly.get(i+1);
				Line l=new Line(x0*150+150,y0*260+((x0+y0)%2==0?87:173), x*150+150, y*260+((x+y)%2==0?87:173));
				l.setStrokeWidth(20);
				l.setStroke(Color.hsb(240, 1-(double)i/(double)(lx.size()), (double)i/(double)(lx.size()), 0.9));
				udpane.getChildren().add(l);
				l.setEffect(gausb);
				f.added.add(l);
			}
			
			if(i!=0) {
				if(i==1)
					pts1.add(new Point2D(((lstx*2-x0-minx)*150+150),
    					((lsty-miny)*260+(lsty-y0)*173+((lstx+lsty)%2==0?(87-Math.abs(lstx-x0)*87):(173+Math.abs(lstx-x0)*87)))));
			if(x0-lstx==1) pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?300:150)),((lsty-miny)*260)));
			else if(x0-lstx==-1) pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?150:0)),((lsty-miny)*260+260)));
			else pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?0:300)),((lsty-miny)*260+((lstx+lsty)%2==0?0:260))));
			}
			lstx=x0;lsty=y0;
		}
		
		for(int i=0;i<lx.size();i++) {
			int x0=lx.get(i),y0=ly.get(i);
			Circle c=new Circle(x0*150+150,y0*260+((x0+y0)%2==0?87:173),28);
			udpane.getChildren().add(c);
			f.added.add(c);
			c.setFill(Color.hsb(240, 1-(double)i/(double)(lx.size()), (double)i/(double)(lx.size()), 0.9));
			c.setEffect(gausb);
			first.cl.add(c);
		}
		
		for(int i=lx.size()-1;i>-1;i--) {
			int x0=lx.get(i),y0=ly.get(i);
			if(i!=lx.size()-1) {
    			if(i==lx.size()-2)
	    			pts1.add(new Point2D(((lstx*2-x0-minx)*150+150),
	    					((lsty-miny)*260+(lsty-y0)*173+((lstx+lsty)%2==0?(87-Math.abs(lstx-x0)*87):(173+Math.abs(lstx-x0)*87)))));	
    			if(x0-lstx==1) pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?300:150)),((lsty-miny)*260)));
				else if(x0-lstx==-1) pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?150:0)),((lsty-miny)*260+260)));	
				else pts1.add(new Point2D(((lstx-minx)*150+((lstx+lsty)%2==0?0:300)),((lsty-miny)*260+((lstx+lsty)%2==0?0:260))));
			}
			lstx=x0;lsty=y0;
		}
		
		
//		double scle=0.3;
		double[] polypts=new double[pts1.size()*2];
		for(int i=0;i<pts1.size();i++) {
			polypts[i*2]=pts1.get(i).getX()+minx*150;
			polypts[i*2+1]=pts1.get(i).getY()+miny*260;
		}		
		
		//内描二选一
		Polygon p=new Polygon(polypts);
//		p.setStrokeWidth(12);
//		p.setStrokeLineJoin(StrokeLineJoin.ROUND);
//		p.setStrokeType(StrokeType.INSIDE);
//		p.setStroke(Color.color(0.6,0.6,0.9));
//		p.setFill(Color.TRANSPARENT);
//		udpane.getChildren().add(p);
		
//		Map<Point2D, Long> countMap = pts1.stream()
//	                .collect(Collectors.groupingBy(p1 -> p1, Collectors.counting()));
//	    // 保留只出现一次的元素
//		pts1.removeIf(p1 -> countMap.get(p1) > 2);
		//画线边缘 二选一 可以调色
		Group gp=new Group();
		for(int i=0;i<pts1.size()-1;i++) {
			if(!(b&&(pts1.get(i).getX()==pts1.get(0).getX()&&pts1.get(i).getY()==pts1.get(0).getY()&&
					pts1.get(i+1).getX()==pts1.get(pts1.size()-1).getX()&&pts1.get(i+1).getY()==pts1.get(pts1.size()-1).getY()||
					pts1.get(i+1).getX()==pts1.get(0).getX()&&pts1.get(i+1).getY()==pts1.get(0).getY()&&
					pts1.get(i).getX()==pts1.get(pts1.size()-1).getX()&&pts1.get(i).getY()==pts1.get(pts1.size()-1).getY()))) {
    			Line l=new Line(pts1.get(i).getX()+minx*150,pts1.get(i).getY()+miny*260,pts1.get(i+1).getX()+minx*150,pts1.get(i+1).getY()+miny*260);
				l.setStrokeWidth(28);
				l.setStrokeLineCap(StrokeLineCap.ROUND);
				l.setStroke(Color.color(0.6,0.6,0.9));
				gp.getChildren().add(l);
				
			}
//			else new p(1);
		}
//		for(int i=0;i<pts1.size();i++) {
//			new p(pts1.get(i).getX()+" "+pts1.get(i).getY()+"\n");
//		}
		
//		if(!b) {
			double x0=pts1.get(0).getX()+minx*150,y0=pts1.get(0).getY()+miny*260,
					x01=pts1.get(pts1.size()-1).getX()+minx*150,y01=pts1.get(pts1.size()-1).getY()+miny*260;
			if(pts1.get(0).distance(pts1.get(pts1.size()-1))<320) {
	    		Line l=new Line(x0,y0,x01,y01);
				l.setStrokeWidth(28);
				l.setStrokeLineCap(StrokeLineCap.ROUND);
				l.setStroke(Color.color(0.6,0.6,0.9));
				gp.getChildren().add(l);
			}
//		}
		gp.setClip(p);
		gp.setEffect(gausb);
		udpane.getChildren().add(gp);
		f.added.add(gp);
		
//		画点点星星，渲染canvas非常消耗性能
//		first.star=new xuanwuParticles(pts,5,scle,(int) ((maxx-minx)*150+300),(int) ((maxy-miny)*260+520),1,0.7,300,cancircle);	
		
//		udpane.getChildren().add(first.star);
//		first.star.setScaleX(1/scle);
//		first.star.setScaleY(1/scle);
//		double ofx=first.star.getBoundsInLocal().getWidth(), ofy=first.star.getBoundsInLocal().getHeight();
//		first.star.setTranslateX(minx*150+(1/scle/2-0.5)*ofx);
//		first.star.setTranslateY(miny*260+(1/scle/2-0.5)*ofy);
//		first.star.setClip(p);
//		first.star.setCache(false);
		
		lp.clear();
		lx.clear();
		ly.clear();
		return f;
    }
        public xuanwu makexuanwu(int x,int y) {
//			System.out.println(x+" "+y);
        	ImageView x1=null;
        	Group xuanwu=new Group();
        	xuanwu xw=new xuanwu(x, y,"玄武",x+","+y+","+System.currentTimeMillis(), xuanwu);
            xw.gk=this;
            if(null!=db0[y][x])
            	db0[y][x].remove();
    		db0[y][x]=xw;
//    		int islist=0;
    		if(lastputxuanwu!=null) {
//    			islist=1;
    			xw.prev=lastputxuanwu;
    			lastputxuanwu.next=xw;
    		}
        	if((x+y)%2==1) {
//        		x0=new ImageView(xuanwuu1);
//        		if(lowgrahf) 
//        			x1=new ImageView(xuanwu1);
//        		else 
        			x1=new ImageView(xuanwus1);
        		xw.upf=1;
//        		if(islist==1) {
//    	    		if(x-1>-1&&db0[y][x-1]!=null&&db0[y][x-1].name.equals("玄武")&&db0[y][x-1]!=lastputxuanwu) {
//    	    			xw.next=(xuanwu)db0[y][x-1];
//    	    			((xuanwu)(db0[y][x-1])).prev=xw;
//    	    		}
//    	    		if(x+1<MAPWIDTH&&db0[y][x+1]!=null&&db0[y][x+1].name.equals("玄武")&&db0[y][x+1]!=lastputxuanwu) {
//    	    			xw.next=(xuanwu)db0[y][x+1];
//    	    			((xuanwu)(db0[y][x+1])).prev=xw;
//    	    		}
//    	    		if(y+1<MAPHEIGHT&&db0[y+1][x]!=null&&db0[y+1][x].name.equals("玄武")&&db0[y+1][x]!=lastputxuanwu){
//    	    			xw.next=(xuanwu)db0[y+1][x];
//    	    			((xuanwu)(db0[y+1][x])).prev=xw;
//    	    		}
//        		}
        	}
        	else {
//        		x0=new ImageView(xuanwuu2);
//        		if(lowgrahf)
//        			x1=new ImageView(xuanwu2);
//        		else
        			x1=new ImageView(xuanwus2);
        		xw.upf=0;
//        		if(islist==1) {
//        			if(x-1>-1&&db0[y][x-1]!=null&&db0[y][x-1].name.equals("玄武")&&db0[y][x-1]!=lastputxuanwu) {
//    	    			xw.next=(xuanwu)db0[y][x-1];
//    	    			((xuanwu)(db0[y][x-1])).prev=xw;
//    	    		}
//    	    		if(x+1<MAPWIDTH&&db0[y][x+1]!=null&&db0[y][x+1].name.equals("玄武")&&db0[y][x+1]!=lastputxuanwu) {
//    	    			xw.next=(xuanwu)db0[y][x+1];
//    	    			((xuanwu)(db0[y][x+1])).prev=xw;
//    	    		}
//    	    		if(y-1>-1&&db0[y-1][x]!=null&&db0[y-1][x].name.equals("玄武")&&db0[y-1][x]!=lastputxuanwu){
//    	    			xw.next=(xuanwu)db0[y-1][x];
//    	    			((xuanwu)(db0[y-1][x])).prev=xw;
//    	    		}
//        		}
        	}
//        	if(islist==0) {
//        		
//    		}
//        	x0.setFitWidth(300);
//        	x0.setFitHeight(260);
        	x1.setFitWidth(300);
        	x1.setFitHeight(260);
        	xuanwu.getChildren().add(x1);
        	xuanwu.setTranslateX(x*150);
        	xuanwu.setTranslateY(y*260);
    		udpane.getChildren().add(xuanwu);
//    		gpane.getChildren().remove(gridv[y][x]);
    		gridv[y][x].setOpacity(0);
    		lastputxuanwu=xw;
    		return xw;
        }
        
       public void iniany(String path,double x,double y,int i, int j,int move, Method m) {
    	   	Image longzhuai=new Image(getClass().getResourceAsStream("blendpic/"+path+".png"));
	        ImageView lz=new ImageView(longzhuai);
	        //将右侧木板装置静态化
	        if(path.contains("爪子")) {
	        	ilongzhua=lz;
	        }
	        else if(path.contains("三足")) {
	        	ihunyuan=lz;
	        }
	        else if(path.contains("五脏")) {
	        	iwuzang=lz;
	        }
	        else if(path.contains("九龙灌")) {
	        	ijiulongguan=lz;
	        }
	        else if(path.contains("朱雀")) {
	        	izhuque=lz;
	        }
	        else if(path.contains("六合")) {
	        	iliuhe=lz;
	        }
	        else if(path.contains("白虎")) {
	        	ibaihu=lz;
	        }
	        lz.setSmooth(false);
	        if(effectf)
	        	lz.setEffect(ds5);
	        double iniscale=scale;
	        lz.setScaleX(iniscale);
	        lz.setScaleY(iniscale);
	        
	        lz.setTranslateX(-x/2*(1-iniscale)+i*suofang-40);
	        lz.setTranslateY(-y/2*(1-iniscale)+j*suofang);
	        double px=move==0?x-x/2*scale:x,py=move==0?y+x/2*scale:y;
	        lz.setMouseTransparent(false);
	        lz.setOnMouseDragged(e->{
	        	if(e.getButton()==MouseButton.PRIMARY) {
		        	lz.toFront();
		        	inimachinedrag=true;
		        	nowmousex=e.getScreenX();
		        	nowmousey=e.getScreenY();
		        	hovergrid(lz, longzhuai, e,px,py,iniscale,path);
	        	}
	        });
	        lz.setOnMousePressed(e->{
	        	shixumovezero();
	        });
	        lz.setOnMouseReleased(e->{
	        	if(e.getButton()==MouseButton.PRIMARY) {
	        	lz.setOpacity(1);
	        	  if(effectf)
	  	        	lz.setEffect(ds5);
	        	if(canputf&&hovergridx!=-1&&hovergridy!=-1&&nowmousex<mubanright.getTranslateX()) {
	        		canputf=false;
	        		try {
						m.invoke(this,hovergridx, hovergridy);
						if(iniitemplaces.containsKey(path)) {
							lz.setTranslateX(iniitemplaces.get(path).getX());
						    lz.setTranslateY(iniitemplaces.get(path).getY());
						}
						else {
							lz.setTranslateX(-x/2*(1-iniscale)+i*suofang);
							lz.setTranslateY(-y/2*(1-iniscale)+j*suofang);
						}
					} catch (Exception e2) {}
//	        		makelongzhua(hovergridx,hovergridy);
	        	}
	        	else if (nowmousex>mubanright.getTranslateX()) {
	        		Point2D p=new Point2D(lz.getTranslateX(),lz.getTranslateY());
	        		iniitemplaces.put(path,p);
	        	}
	        	canmakecloneblend=true;
	        	mubanright.getChildren().remove(nowgrab);
	        	nowgrab=null;
	        	nowgrabname=null;
	        	}
	        });
	        mubanright.getChildren().add(lz);
        addedmachines.add(lz);
       }
	
	//悬浮处理方法
	 private void hovergrid(ImageView iv, Image im,MouseEvent e,double px,double py,double iniscale,String path) {
     	if((rightmubanf&&e.getScreenX()<screenwidth-300 * scalemubanl||!rightmubanf&&e.getScreenX()<screenwidth-90 * scalemubanl)&&
     			(leftmubanf&&e.getScreenX()>238 * scalemubanl||!leftmubanf&&e.getScreenX()>78 * scalemubanl)) {
     		if(canmakecloneblend) {  //注释掉这个条件会生成一堆，很帅
      			iv.setOpacity(0.01);
      			canmakecloneblend=false;
      			nowgrab=new ImageView(im);
	        	if(path.contains("五脏")) {
	        		((ImageView)(nowgrab)).setImage(wuzang[wuzangrollf]);
	        		nowgrab.setRotate(60*wuzangrollf);
	        	}
      			nowgrabname=path;
//      			if(nowgrabname.contains("五脏"))nowgrab.setRotate(wuzangrollf*60);
      			nowgrab.setEffect(ds5);
      			((ImageView) nowgrab).setSmooth(false);
              	mubanright.getChildren().add(nowgrab);
              	nowgrab.setScaleX(scale);
              	nowgrab.setScaleY(scale);
              	nowgrab.setMouseTransparent(true);
      		}
      		if(nowgrab!=null) {
      			if (gpane != null) {
      				makegridevent(e);
      			}
      			dragOffsetX=e.getSceneX()-px;
      			dragOffsetY=e.getSceneY()-py;
      			nowgrab.setTranslateX(e.getSceneX()-mubanright.getTranslateX()-px);
      			nowgrab.setTranslateY(e.getSceneY()-py);
      		}	
     	}
     	else if(leftmubanf&&e.getScreenX()<238 * scalemubanl||!leftmubanf&&e.getScreenX()<78 * scalemubanl) {
     		hovergridx=-1;
         	hovergridy=-1;
     	}
     	else {
     		hovergridx=-1;
         	hovergridy=-1;
     		iv.setOpacity(1);
     		canmakecloneblend=true;
         	mubanright.getChildren().remove(nowgrab);
         	nowgrab=null;
			nowgrabname=null;
//        	double sx=e.getSceneX()-mubanright.getTranslateX()-px,sy=e.getSceneY()-py;
//     		iv.setTranslateX(Math.max(sx,-211*(1-iniscale)));
//         	iv.setTranslateY(sy-30);
         	iv.setTranslateX(nowmousex-mubanright.getTranslateX()-px);
         	iv.setTranslateY(nowmousey-py);
     	}
	}

	 private void makegridevent(MouseEvent e) {
		
		 Point2D localPoint = gpane.sceneToLocal(e.getSceneX(), e.getSceneY());
		    int gridX0 = (int) (localPoint.getX() / 150);
		    int gridX1 = (int) (localPoint.getX() % 150);
		    int gridY = (int) (localPoint.getY() / 260);
		    int gridY1 = (int) (localPoint.getY() % 260);
		    nowhoverposx=localPoint.getX(); nowhoverposy=localPoint.getY();
		    int off=(gridX0+gridY)%2==1?(gridX1>(260-gridY1)/Math.sqrt(3)?0:-1):(gridX1>gridY1/Math.sqrt(3)?0:-1);
		    int gridX=gridX0+off;
		    if(gridX<0||gridY<0||gridX>=MAPWIDTH||gridY>=MAPHEIGHT)canputf=false;
		    else canputf=true;
		    gridX = Math.max(0, Math.min(gridX, MAPWIDTH - 1));
		    gridY = Math.max(0, Math.min(gridY, MAPHEIGHT - 1));
		    ImageView targetView = gridv[gridY][gridX]; // 注意行列顺序
		    targetView.setSmooth(false);
		    if (targetView != null) {
					MouseEvent simulatedEvent = new MouseEvent(MouseEvent.MOUSE_ENTERED, localPoint.getX(),
							localPoint.getY(), e.getScreenX(), e.getScreenY(), MouseButton.NONE, 0,
							e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), false, false,
							false, false, false, false, null);
		        Platform.runLater(() -> {
		            targetView.fireEvent(simulatedEvent);
		        });
		    }
	 }
	 
	 //放下时序
	 void putdownshixu() {
		 canselectshixuf=false;
			nowgrabmovet.stop();
			nowgrabmovet=null;
			nowgrab1=null;
			clearAllHighlights1();
			int x=(int) ((nowmousex-405*suofang)/70/suofang)+nowlookshixu;
			int y=(int) ((nowmousey-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang)+nowlookshixuy;
			if(selectitem1.size()==0) {
				selectitem11.forEach(e1->{
					e1.x=e1.x+x-mingrabx;
					e1.y=e1.y+y-mingraby;
					shixuuses.remove(e1.x+","+e1.y);
					shixuuses.put(e1.x+","+e1.y,e1.copy(this));
		        });
			}
			else {
				selectitem1.forEach(e1->{
					e1.x=e1.x+x-mingrabx;
					e1.y=e1.y+y-mingraby;
					shixuuses.remove(e1.x+","+e1.y);
					shixuuses.put(e1.x+","+e1.y,e1);
		        });
				selectitem1.clear();
			}
			mingrabx=-1;mingraby=-1;
			
			refreshshixupane();
	 }
	 
	 void leaveshixu() {
		 	canselectshixuf=false;
		 	try {
		 		nowgrabmovet.stop();
		 	}catch(Exception e) {}
			nowgrabmovet=null;
			nowgrab1=null;
			selectitem1.forEach(e1->{
				shixuuses.put(e1.x+","+e1.y,e1);
	        });
			mingrabx=-1;mingraby=-1;
			selectitem1.clear();
			refreshshixupane();
	 }
	 
	 void copyshixu() {
		 selectitem1.forEach(e1->{
			 shixuuses.put(e1.x+","+e1.y,e1.copy(this));
		 });
	 }
	 
	 void grabshixumove() {
		 try {
			 nowgrabmovet.stop();
			 nowgrabmovet=null;
		 }catch(Exception e) {}
		 nowgrabmovet=new Timeline(new KeyFrame(Duration.millis(16),e1->{
	     		int x=(int) ((nowmousex-405*suofang)/70/suofang);
					int y=(int) ((nowmousey-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang);
					nowgrab1.setTranslateX(x*70*suofang);
//					if(selectitem1.size()!=0)
						nowgrab1.setTranslateY(y*49*suofang+25*suofang*nowshixugraboffy);
//					else nowgrab1.setTranslateY(y*49*suofang);
	     	}));
	     	nowgrabmovet.setCycleCount(Timeline.INDEFINITE);
	     	nowgrabmovet.play();
	     	Timeline t1=new Timeline(new KeyFrame(Duration.millis(16),e1->{
	     		if(nowgrab1!=null&&!sxgp.getChildren().contains(nowgrab1)) {
						sxgp.getChildren().add(nowgrab1);
				}
	     	}));
	     	t1.play();
	     	nowgrab1.setMouseTransparent(true);
	 }
	 
	 //移动时序1复制
	 void grabupshixu1() {
		 if(selectitem11.size()!=0) {
			 nowgrab1=new Group();
	         ((Group) nowgrab1).getChildren().addAll(selectitem11);
	        
	         mingrabx=-1;mingraby=-1;
	         selectitem11.forEach(e1->{
	         	if(mingrabx==-1||mingrabx>e1.x)mingrabx=e1.x;
	         	if(mingraby==-1||mingraby>e1.y)mingraby=e1.y;
	         });
	         selectitem11.forEach(e1->{
		         	e1.setTranslateX((e1.x-mingrabx)*70*suofang);
		         	e1.setTranslateY((e1.y-mingraby)*49*suofang);
		         });
	         nowshixugraboffy=(int) Math.round((nowgrab1.getBoundsInLocal().getHeight())/(49*suofang)-1);
		        
	         grabshixumove();
		 }
	 }
	 
	 //移动时序
	 void grabupshixu() {
		 nowgrab1=new Group();
         ((Group) nowgrab1).getChildren().addAll(selectitem1);
         nowshixugraboffy=(int) Math.round((nowgrab1.getBoundsInLocal().getHeight())/(49*suofang)-1);
        
         mingrabx=-1;mingraby=-1;
         selectitem1.forEach(e1->{
         	if(mingrabx==-1||mingrabx>e1.x)mingrabx=e1.x;
         	if(mingraby==-1||mingraby>e1.y)mingraby=e1.y;
         	shixuuses.remove(e1.x+","+e1.y);
         });
         grabshixumove();
	 }
	 	 
	 //龙爪在加到玄武上只能在第零帧
	public longzhua makelongzhua(int x,int y) {
	    	if(x>-1&&y>-1&&(null==db0[y][x]||(db0[y][x].name.equals("玄武")&&nowplayshixupos==0))) {
		    	ImageView baseImage = new ImageView(longzhuax1i);
		    	baseImage.setFitHeight(173);
		    	baseImage.setFitWidth(173);
				baseImage.setTranslateX(63);
				
				ImageView middleImage1 = new ImageView(zhuazis[0]);
				middleImage1.setTranslateX(-23);
				middleImage1.setTranslateY(-520-87);
				middleImage1.setFitHeight(346);
				middleImage1.setFitWidth(346);
				middleImage1.setEffect(ds);
//				ImageView middleImage = new ImageView(zhuabis[0]);
				ImageView middleImage = new ImageView();
				middleImage.setTranslateX(-600+148);
				middleImage.setTranslateY(-600+85);
				middleImage.setFitHeight(1200);
				middleImage.setFitWidth(1200);
				
//				middleImage.setEffect(gausb);
				ImageView topImage = new ImageView(longzhuax2i);
				topImage.setFitHeight(146);
				topImage.setFitWidth(166);
				topImage.setTranslateX(68);
				topImage.setTranslateY(15);
				ImageView upImage = new ImageView(longzhuani);
				upImage.setFitHeight(98);
				upImage.setFitWidth(112);
				upImage.setTranslateX(97);
				upImage.setTranslateY(-520+45);
	
				Group g0=new Group();
				g0.getChildren().addAll(baseImage,topImage);
		        g0.setTranslateX(150*x);
		        if((x+y)%2==0) {
		        	g0.setTranslateY(260*y);
		        }
		        else {
		        	g0.setTranslateY(260*y+260/3);
		        }
		        dfbpane.getChildren().add(g0);
		        
		        Group g1=new Group();
		        g1.getChildren().add(new Group());
		        baseImage.setSmooth(false);
		        middleImage1.setSmooth(false);
		        middleImage.setSmooth(false);
		        topImage.setSmooth(false);
		        upImage.setSmooth(false);
		       
		        Circle c=new Circle(12);
		        Color color = Color.rgb(175,255,150, 1);
		        c.setFill(color);
		        c.setTranslateX(148);
		        c.setTranslateY(85);
		        g1.getChildren().addAll(new ImageView(),middleImage1,middleImage,new ImageView(),upImage);
		        g1.setTranslateX(150*x);
		        if((x+y)%2==0) {
		        	g1.setTranslateY(260*y);
		        }
		        else {
		        	middleImage1.setRotate(180);
					middleImage1.setImage(zhuazis[5]);
		        	g1.setTranslateY(260*y+260/3);
		        }
		        lzpane.getChildren().add(g1);
		        longzhua l=new longzhua(x,y,"青龙",x+","+y+","+System.currentTimeMillis(),g1,g0);
		        l.gk=this;
		        l.machineindex=machines.size();
		        machines.add(l);//添加到机器链
		        db[y][x]=l;//添加到网
		        grabshixuupdown();//刷新时序面板
		    	dbhistory.forEach(e->{
	    			e.put(l.key,l);
	    		});
		    	if(longzhuadargf) {
			    	niulzf=true;
					nowniulz=l;
					ImageView zhuazi = (ImageView) l.g.getChildren().get(2);
					ImageView zhuabi = (ImageView) l.g.getChildren().get(3);
					ImageView niu = (ImageView) l.g.getChildren().get(5);	
					HashSet<String> lzcl = (hovergridx + hovergridy) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
				
					niulzt=new Timeline(new KeyFrame(Duration.millis(16),e1->{
						int offgridx=hovergridx-l.x,offgridy=hovergridy-l.y;
						if(lzcl.contains(offgridx+","+offgridy)) {
							double offx=150*offgridx,offy=260*offgridy+((offgridy+offgridx)%2==0?0:((l.x+l.y)%2==0?260/3:-260/3));
						    double length=Math.sqrt(offx*offx+offy*offy);
						    double radians = Math.atan2(offy, offx);
						    double degrees = (Math.toDegrees(radians)+90)%360;
						    zhuabi.setScaleY(length / 520);
							zhuabi.setRotate(degrees);
							l.updateClawPosition(l,l.ng,zhuabi,zhuazi,niu);
						}
					}));
					niulzt.setCycleCount(Timeline.INDEFINITE);
					niulzt.play();
		    	}
//		        colis[y][x]=new ArrayList<Segment>();//添加碰撞
//		        double ox=x*150,oy=y*260+((x+y)%2==0?0:94.8);
//		       
//		        colis[y][x].add(new Segment(new Point(ox+115.8,oy),new Point(ox+184.2,oy)));
//		        colis[y][x].add(new Segment(new Point(ox+184.2,oy),new Point(ox+232.6,oy+48.4)));
//		        colis[y][x].add(new Segment(new Point(ox+232.6,oy+48.4),new Point(ox+232.6,oy+116.8)));
//		        colis[y][x].add(new Segment(new Point(ox+232.6,oy+116.8),new Point(ox+184.2,oy+165.2)));
//		        colis[y][x].add(new Segment(new Point(ox+184.2,oy+165.2),new Point(ox+115.8,oy+165.2)));
//		        colis[y][x].add(new Segment(new Point(ox+115.8,oy+165.2),new Point(ox+67.4,oy+116.8)));
//		        colis[y][x].add(new Segment(new Point(ox+67.4,oy+116.8),new Point(ox+67.4,oy+48.4)));
//		        colis[y][x].add(new Segment(new Point(ox+67.4,oy+48.4),new Point(ox+115.8,oy)));
				return l;
	    	}
	    	return null;
	    }
	public void grabshixuupdown() {
		
		for (int i = 0; i < 4; i++) {
			double c=(nowlookshixuy+i)%4;
			try {
			String name=machines.get(nowlookshixuy+i).name;
			
			Color c1=rectcolor.get(name);
//			Color c2=Color.color(Math.min(1,c1.getRed()*(0.9+c/2)),Math.min(1,c1.getGreen()*(0.9+c/2)),Math.min(1,c1.getBlue()*(0.9+c/2)),c1.getOpacity());
			if(rectcolor.containsKey(name))rects.get(i).setFill(c1);
//			else rects.get(i).setFill(Color.color((double)i/4,(double)i/4,(double)i/4,0.2));
			}catch(Exception e1) {	
				rects.get(i).setFill(Color.color(c/4,c/4,c/4,0.2));	
			}
		}
	}
	
	 public ImageView iniseal(String name,double x,double y) {
		 	Image iv=new Image(getClass().getResourceAsStream("sealpic/"+name+".png"));
		 	Image iv1=new Image(getClass().getResourceAsStream("sealpic/"+name+"1.png"));
	        ImageView g2=new ImageView(iv),g3=new ImageView(iv1);
	        g3.setMouseTransparent(true);
	        double iniscale=scale;
	        Group g0=new Group(g3,g2);
	        g2.setSmooth(false);
	        g2.setEffect(ds5);

	        g0.setScaleX(iniscale);
	        g0.setScaleY(iniscale);
	        g0.setTranslateX(x*scalemubanl-g0.getBoundsInLocal().getWidth()/2*(1-scalemubanl*0.4)-20*suofang);
	        g0.setTranslateY(y*scalemubanl-g0.getBoundsInLocal().getHeight()/2*(1-scalemubanl*0.4)-70*suofang);
//	        g0.setTranslateX(x*scalemubanl-g0.getBoundsInParent().getWidth()/2);
//	        g0.setTranslateY(y*scalemubanl-g0.getBoundsInParent().getHeight()/2);
//	        g2.setOpacity(0.5);
	        mubanleft.getChildren().add(g0);
	        g2.setPickOnBounds(false);
	        g2.setOnMouseDragged(e->{
	        	if(e.getButton()==MouseButton.PRIMARY) {
	        	g2.toFront();
	        	inimachinedrag=true;
	        	nowmousex=e.getScreenX();
	        	nowmousey=e.getScreenY();
	        	double px=g0.getBoundsInLocal().getWidth()/2,py=g0.getBoundsInLocal().getHeight()/2;
	        	double sx=e.getSceneX()-mubanleft.getTranslateX()-px,sy=e.getSceneY()-py;
	        	if(e.getScreenX()>screenwidth*0.75-90 * scalemubanl) {
	        		scenekeys.add(KeyCode.D);
	        	}
	        	else if(e.getScreenX()<screenwidth*0.25+65 * scalemubanl) {
	        		scenekeys.add(KeyCode.A);
	        	}
	        	else {
	        		scenekeys.remove(KeyCode.A);
	        		scenekeys.remove(KeyCode.D);
	        	}
	        	if(e.getScreenY()>shixu.getTranslateY()+screenheight*0.25) {
	        		scenekeys.add(KeyCode.S);
	        	}
	        	else if(e.getScreenY()<screenheight*0.25) {
	        		scenekeys.add(KeyCode.W);
	        	}
	        	else {
	        		scenekeys.remove(KeyCode.W);
	        		scenekeys.remove(KeyCode.S);
	        	}
//	        	System.out.println(shixu.getTranslateY()+screenheight/2);
	        	if((rightmubanf&&e.getScreenX()<screenwidth-300 * scalemubanl||!rightmubanf&&e.getScreenX()<screenwidth-90 * scalemubanl)&&
	        			(leftmubanf&&e.getScreenX()>225 * scalemubanl||!leftmubanf&&e.getScreenX()>65 * scalemubanl)) {
	        		if(canmakecloneblend) {  
	        			g0.setOpacity(0.01);
	        			canmakecloneblend=false;
	        			Group g1=new Group(new ImageView(iv1),new ImageView(iv));
	        			nowgrab=g1;
//	        			nowgrab.setEffect(ds5);
	        			mubanleft.getChildren().add(nowgrab);
//	        			nowgrab.setOpacity(0.8);
	                	nowgrab.setScaleX(scale);
	                	nowgrab.setScaleY(scale);
	                	nowgrab.setMouseTransparent(true);
	        		}
	        		if(nowgrab!=null) {
	        			if (gpane != null) {
	        			    Point2D localPoint = gpane.sceneToLocal(e.getSceneX(), e.getSceneY());
	        			    int gridX0 = (int) (localPoint.getX() / 150);
	        			    int gridX1 = (int) (localPoint.getX() % 150);
	        			    int gridY = (int) (localPoint.getY() / 260);
	        			    int gridY1 = (int) (localPoint.getY() % 260);
	        			    int off=(gridX0+gridY)%2==1?(gridX1>(260-gridY1)/Math.sqrt(3)?0:-1):(gridX1>gridY1/Math.sqrt(3)?0:-1);
	        			    int gridX=gridX0+off;
	        			    if(gridX<0||gridY<0||gridX>=MAPWIDTH||gridY>=MAPHEIGHT)canputf=false;
	        			    else canputf=true;
	        			    gridX = Math.max(0, Math.min(gridX, MAPWIDTH - 1));
	        			    gridY = Math.max(0, Math.min(gridY, MAPHEIGHT - 1));
	        			    ImageView targetView = gridv[gridY][gridX]; // 注意行列顺序
	        			    targetView.setSmooth(false);
	        			    if (targetView != null) {
								MouseEvent simulatedEvent = new MouseEvent(MouseEvent.MOUSE_ENTERED, localPoint.getX(),
										localPoint.getY(), e.getScreenX(), e.getScreenY(), MouseButton.NONE, 0,
										e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), false, false,
										false, false, false, false, null);
	        			        Platform.runLater(() -> {
	        			            targetView.fireEvent(simulatedEvent);
	        			        });
	        			    }
	        			}
	        			nowgrab.setTranslateX(sx);
	        			nowgrab.setTranslateY(sy);
	        		}	
	        	}
	        	else if(rightmubanf&&e.getScreenX()>screenwidth-300 * scalemubanl||!rightmubanf&&e.getScreenX()>screenwidth-90 * scalemubanl) {
	        		hovergridx=-1;
	            	hovergridy=-1;
	        	}
	        	else {
	        		hovergridx=-1;
	            	hovergridy=-1;
	                g2.setEffect(ds5);
	        		g0.setOpacity(1);
	        		
	        		canmakecloneblend=true;
	            	mubanleft.getChildren().remove(nowgrab);
	             	nowgrab=null;
	    			nowgrabname=null;
	        		g0.setTranslateX(Math.max(sx,-1600*(1-iniscale)));
	            	g0.setTranslateY(sy);
	        	}
	        	}
	        });
	        g2.setOnMouseReleased(e->{
	        	
	        	if(canputf) {
	        		canputf=false;
	        		g0.setTranslateX(x*scalemubanl-g0.getBoundsInLocal().getWidth()/2*(1-scalemubanl*0.4)-20*suofang);
	     	        g0.setTranslateY(y*scalemubanl-g0.getBoundsInLocal().getHeight()/2*(1-scalemubanl*0.4)-70*suofang);
	    	        if(hovergridx!=-1&&hovergridy!=-1&&null!=db0[hovergridy][hovergridx]&&db0[hovergridy][hovergridx].name.contains("灌")) {
	    	        	guanzhu gz=((guanzhu)(db0[hovergridy][hovergridx]));
//	    	        	int ctx=gz.ctx,cty=gz.cty;
//	    	        	makeseal(name,hovergridx+ctx,hovergridy+cty);
	    	        	if(!makeseal(name,gz,hovergridx,hovergridy))g0.setOpacity(1);
	    	        	
	    	        }
	    	        else g0.setOpacity(1);
	        	}
	        	canmakecloneblend=true;
	        	mubanleft.getChildren().remove(nowgrab);
	         	nowgrab=null;
				nowgrabname=null;
	        	scenekeys.remove(KeyCode.W);
        		scenekeys.remove(KeyCode.S);
        		scenekeys.remove(KeyCode.A);
        		scenekeys.remove(KeyCode.D);
	        });
	        return g2;
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
	        	  ImageView middleImage2=null;
	              try {
	              	Image ai=new Image(getClass().getResourceAsStream("definepic/宝石"+name+".png"));
	              	baoshianimalmap.put(name, ai);
	              	middleImage2 = new ImageView(ai);
	              }
	       	   	catch(Exception e) {middleImage2 = new ImageView();}
	       	   	middleImage2.setSmooth(false);
	       	   	middleImage2.setFitHeight(346);
	       	  	middleImage2.setFitWidth(346);
	       	 try {
	       	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+name+".png"))));
	       	  	}catch(Exception e1) {
	       	  		if(qixings.contains(name)) {
	       	  			definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/星.png")))); 
	       	  			System.out.print(1);
	       	  		}
	       	  		else definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png")))); 	
	       	  	}
    	        g0.getChildren().addAll(middleImage2,baseImage2);
    	        g0.setEffect(definecolorblend);      
    	        g0.setRotate(15*i);
    	        //设置多边形中心为17373
    	        Polygon p=new Polygon(23,86,0,0,23,86,323,86,346,0,323,86,173,346);
    	        p.setRotate(15*i);
    	        Group g1=new Group(g0);
    	        g1.setClip(p);      
    	        g1.setEffect(defineli);
    	        g1.setRotate(-15*i);
    	        Group g3=new Group(new ImageView(defineud),g1);
    			Image si=g3.snapshot(params1, null);
    			baoshiimagemap.put(name+i, si);		        	
//    			ImageView bsi=new ImageView(si);
//    			bsi.setScaleX(0.3);
//    			bsi.setScaleY(0.3);
//    			bsi.setTranslateX(100);
//    			bsi.setTranslateY(60*i);
//    			mubanleft.getChildren().add(bsi);
    		}
		}catch(Exception e1) {}
	        
	}

    public define makebaoshi(int x,int y,String name,int zang){
    	if(x>=0&&x<MAPWIDTH&&y>=0&&y<MAPHEIGHT) {
    		if(db0[y][x] instanceof definespawn) {
    			//产生宝石动画
    			definespawn ds=(definespawn)(db0[y][x]);
    			ds.openf=false;
    			ds.zang=(int)(Math.random()*10);
    			ImageView dsd=(ImageView)(ds.g.getChildren().get(0));
    			dsd.setEffect(zangs[ds.zang]);
    			ImageView dsb=(ImageView)(ds.g.getChildren().get(1));
    			dsb.setOpacity(1);
    			Timeline t=new Timeline(new KeyFrame(Duration.seconds(1.2/playrate),new KeyValue(dsb.opacityProperty(),0)));
    			t.play();
    			hasbaoshis.put(name, hasbaoshis.get(name)-1);
    			t.setOnFinished(e->{
    				ds.openf=true;
    			});
    		}
    		
    		makebaoshiimage(name);
    		
	        Group g2=new Group(new ImageView((x+y)%2==0?baoshiimagemap.get(name+"0"):baoshiimagemap.get(name+"12")));
	        g2.setEffect(zangs[zang]);
	        g2.setRotate((x+y)%2==0?0:180);
	       
			define d=new define(x,y,g2,name,x+","+y+","+System.currentTimeMillis());
//			dfhistory.get(nowplayshixupos).put(d.key,d);
			//不需要放
			d.gk=this;
	        d.zang=zang;
	        df[y][x]=d;
	        dfpane.getChildren().add(g2);
	        g2.setTranslateX(x*150-23);
	        if((x+y)%2==0) {
	        	d.face=0;
	        	d.upf=0;
	        	g2.setTranslateY(y*260-87);
	        }
	        else {
	        	d.face=3;
	        	d.upf=1;
//	        	((Group)(g2.getChildren().get(0))).getChildren().get()
	        	g2.setTranslateY(y*260);
	        }
//	        try {
//	        	d.baoshiconnect();
//	        }catch(Exception e) {
//	        	e.printStackTrace();
//	        }
	        try {
				if (null != db0[y][x] && db0[y][x].name.contains("灌")) {
					String s1 = d.parentGroup.makename();
//					System.out.println(s1);
					((guanzhu) db0[y][x]).baoshiguanzhu(s1);	
				}
			} catch (Exception e1) {
		    	e1.printStackTrace();
		    }
//	        d.baoshiconnect();
			return d;
    	}
    	return null;
    }

    public ImageView inibaoshi(String name,double x,double y) {
    	makebaoshiimage(name);
    	Image nv=baoshiimagemap.get(name+"0");
    	Group g0=new Group(new ImageView(nv));
    	ImageView iv=new ImageView(defineg);
    	iv.setEffect(new DropShadow());
    	iv.setTranslateX(23);
    	iv.setTranslateY(86);
    	g0.getChildren().add(iv);
        SnapshotParameters sn1 = new SnapshotParameters();
		sn1.setFill(Color.TRANSPARENT);
        ImageView g2=new ImageView(g0.snapshot(sn1,null));
        g2.setSmooth(false);
        g2.setEffect(ds5);
        double iniscale=scale;
        g2.setScaleX(iniscale);
        g2.setScaleY(iniscale);
        g2.setTranslateX(x*scalemubanl-346/2*(1-scalemubanl*0.4)+70*suofang);
        g2.setTranslateY(y*scalemubanl-346/2*(1-scalemubanl*0.4)+70*suofang);
//        g2.setTranslateX(x*scalemubanl);
//        g2.setTranslateY(y*scalemubanl);
//        g2.setOpacity(0.5);
        
        mubanleft.getChildren().add(g2);
        addedgems.add(g2);
        g2.setPickOnBounds(false);
        g2.setOnMouseDragged(e->{
        	if(e.getButton()==MouseButton.PRIMARY) {
			g2.toFront();
        	inimachinedrag=true;
        	nowmousex=e.getScreenX();
        	nowmousey=e.getScreenY();
        	double px=173,py=173;
        	double sx=e.getSceneX()-mubanleft.getTranslateX()-px,sy=e.getSceneY()-py;
        	
        	if((rightmubanf&&e.getScreenX()<screenwidth-300 * scalemubanl||!rightmubanf&&e.getScreenX()<screenwidth-90 * scalemubanl)&&
        			(leftmubanf&&e.getScreenX()>225 * scalemubanl||!leftmubanf&&e.getScreenX()>65 * scalemubanl)) {
        		if(canmakecloneblend) {  
        			g2.setOpacity(0.01);
        			canmakecloneblend=false;
        			Group g1=new Group(new ImageView(nv));
        			g1.getChildren().add(iv);
        			nowgrab=g1;
        			nowgrab.setEffect(ds5);
        			mubanleft.getChildren().add(nowgrab);

//        			nowgrab.setOpacity(0.8);
                	nowgrab.setScaleX(scale);
                	nowgrab.setScaleY(scale);
                	nowgrab.setMouseTransparent(true);
        		}
        		if(nowgrab!=null) {
        			if (gpane != null) {
        			    Point2D localPoint = gpane.sceneToLocal(e.getSceneX(), e.getSceneY());
        			    int gridX0 = (int) (localPoint.getX() / 150);
        			    int gridX1 = (int) (localPoint.getX() % 150);
        			    int gridY = (int) (localPoint.getY() / 260);
        			    int gridY1 = (int) (localPoint.getY() % 260);
        			    int off=(gridX0+gridY)%2==1?(gridX1>(260-gridY1)/Math.sqrt(3)?0:-1):(gridX1>gridY1/Math.sqrt(3)?0:-1);
        			    int gridX=gridX0+off;
        			    if(gridX<0||gridY<0||gridX>=MAPWIDTH||gridY>=MAPHEIGHT)canputf=false;
        			    else canputf=true;
        			    gridX = Math.max(0, Math.min(gridX, MAPWIDTH - 1));
        			    gridY = Math.max(0, Math.min(gridY, MAPHEIGHT - 1));
        			    ImageView targetView = gridv[gridY][gridX]; // 注意行列顺序
        			    targetView.setSmooth(false);
        			    if (targetView != null) {
							MouseEvent simulatedEvent = new MouseEvent(MouseEvent.MOUSE_ENTERED, localPoint.getX(),
									localPoint.getY(), e.getScreenX(), e.getScreenY(), MouseButton.NONE, 0,
									e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), false, false,
									false, false, false, false, null);
        			        Platform.runLater(() -> {
        			            targetView.fireEvent(simulatedEvent);
        			        });
        			    }
        			}
        			dragOffsetX=e.getSceneX()-px;
        			dragOffsetY=e.getSceneY()-py;
        			nowgrab.setTranslateX(e.getSceneX()-mubanleft.getTranslateX()-px);
        			nowgrab.setTranslateY(e.getSceneY()-py);
        		}	
        	}
        	else if(rightmubanf&&e.getScreenX()>screenwidth-300 * scalemubanl||!rightmubanf&&e.getScreenX()>screenwidth-90 * scalemubanl) {
        		hovergridx=-1;
            	hovergridy=-1;
        	}
        	else {
        		hovergridx=-1;
            	hovergridy=-1;
//            	g0.setEffect(definecolorblend);
                g2.setEffect(ds5);
        		g2.setOpacity(1);
        		
        		canmakecloneblend=true;
            	mubanleft.getChildren().remove(nowgrab);
             	nowgrab=null;
    			nowgrabname=null;
        		g2.setTranslateX(Math.max(sx,-211*(1-iniscale)));
            	g2.setTranslateY(sy);
        	}
        	}
        });
        g2.setOnMouseReleased(e->{
        	g2.setOpacity(1);
        	if(canputf) {
        		canputf=false;
        		Group g1=new Group(new ImageView(nv));
    			g1.getChildren().add(iv);
    			if(iniitemplaces.containsKey(name)) {
    				g2.setTranslateX(iniitemplaces.get(name).getX());
    				g2.setTranslateY(iniitemplaces.get(name).getY());
				}
				else {
					 g2.setTranslateX(x*scalemubanl-346/2*(1-scalemubanl*0.4)+70*suofang);
				     g2.setTranslateY(y*scalemubanl-346/2*(1-scalemubanl*0.4)+70*suofang);
				}
    	        if(hovergridx!=-1&&hovergridy!=-1) {
//    	        	makebaoshi(hovergridx,hovergridy,name);
//    	        System.out.println(name);
        		makebaoshispawn(hovergridx,hovergridy,g1,name);
    	        }
        	}
        	else {
        		Point2D p=new Point2D(g2.getTranslateX(),g2.getTranslateY());
        		iniitemplaces.put(name,p);
        	}
        	canmakecloneblend=true;
        	mubanleft.getChildren().remove(nowgrab);
         	nowgrab=null;
			nowgrabname=null;
        });
        g2.setOnMousePressed(e->{
        	shixumovezero();
        });
        return g2;
        
    }
    private void makebaoshispawn(int x, int y, Group g,String name) {
    	if(null!=db0[y][x])
    	db0[y][x].remove();
    	definespawn dsp=new definespawn(x,y,g,name,x+","+y+","+System.currentTimeMillis());
    	db0[y][x]=dsp;
		double ofy=0;
		
		if((x+y)%2==1) {
			g.getChildren().clear();
			g.getChildren().add(new ImageView(baoshiimagemap.get(name+"12")));
			ImageView bl=new ImageView(blackdown);
			bl.setTranslateX(23);
			bl.setTranslateY(86);
			bl.setOpacity(0);
			ImageView iv=new ImageView(defineg1);
			iv.setEffect(new DropShadow());
			iv.setTranslateX(23);
			iv.setTranslateY(86);
			g.getChildren().addAll(bl,iv);
			g.setRotate(180);
		}
		else {
			g.getChildren().remove(1);
			ImageView bl=new ImageView(blackdown);
			bl.setOpacity(0);
			bl.setTranslateX(23);
			bl.setTranslateY(86);
			ImageView iv=new ImageView(defineg);
			iv.setEffect(new DropShadow());
			iv.setTranslateX(23);
			iv.setTranslateY(86);
			g.getChildren().addAll(bl,iv);
//			g.setRotate(180);
			ofy=77;
		}
		dsp.zang=(int)(Math.random()*10);
		ImageView dsd=(ImageView)(dsp.g.getChildren().get(0));
		dsd.setEffect(zangs[dsp.zang]);
		g.setTranslateX(150*x-23);
		g.setTranslateY(260*y-ofy-9);
//		gridv[y][x].setOpacity(0.01);
		g.setMouseTransparent(true);
		dsp.gk=this;
	

		dfbpane.getChildren().add(g);
	}
    public void makelongzhuahandle(grid g) {		
    	if(handlepane.getChildren().size()>0) {
    		handlepane.getChildren().forEach(e1->{
				e1=null;
			});
    		handlepane.getChildren().clear();
    	}
		if(niulzf) {
			try {
				HashSet<String> lzcl0 = (nowniulz.x + nowniulz.y) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
		    	String clg=(hovergridx-nowniulz.x) +","+ (hovergridy-nowniulz.y);
		    	if(lzcl0.contains(clg)) {
					niulzt.stop();
					niulzt=null;
					if((hovergridx+hovergridy)%2==1) {
						nowniulz.grabr=3;
						ImageView zhuazi = (ImageView) nowniulz.g.getChildren().get(2);
						zhuazi.setRotate(180);
					}
					else {
						nowniulz.grabr=0;
						ImageView zhuazi = (ImageView) nowniulz.g.getChildren().get(2);
						zhuazi.setRotate(0);
					}
					nowniulz.grabx=hovergridx;
					nowniulz.graby=hovergridy;
					niulzf=false;
					playedfu.add(nowplayshixupos);//修改后加入播放队
		    	}
			}catch(Exception e1) {}
		}
		
		else if (null != g && g instanceof longzhua) {
			ts.clear();
			longzhua lz=(longzhua) g;
			shixuan sx=shixuuses.get(nowplayshixupos+","+lz.machineindex);
			handlepane.getChildren().forEach(e1->{
				e1=null;
			});
			handlepane.getChildren().clear();
			if(null!=sx&&sx.t.equals("围")) {
				Light.Distant light = new Light.Distant();
		        light.setAzimuth(-135.0f);
		        light.setElevation(30.0f);
		        Lighting l = new Lighting();
		        l.setLight(light);
		        l.setSurfaceScale(5.0f);
		       
				Color c0=rectcolor.get("青龙"),c1=c0.brighter().brighter().brighter().brighter().brighter();	
				HashSet<String> lzcl = (g.x + g.y) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
				lzcl.forEach(e1 -> {
					String[] ss = e1.split(",");
					int nx = Integer.parseInt(ss[0]), ny = Integer.parseInt(ss[1]);
					int px=nx + g.x,py=ny + g.y;
					if(px>=0&&px<81&&py>=0&&py<49) {
						grid gd = new grid(px,py);
						Polygon t = new Polygon(gd.posx + gd.ptx1, gd.posy + gd.pty1, gd.posx + gd.ptx2,
								gd.posy + gd.pty2, gd.posx + gd.ptx3, gd.posy + gd.pty3);
						if(nx==sx.aimx&&ny==sx.aimy) {
							t.setFill(c1);
						}
						else t.setFill(c0);
						t.setEffect(l);
						ts.put(nx+","+ny,t);
						handlepane.getChildren().add(t);
						t.setOnMouseClicked(e2->{
							e2.consume();
							try {
								ts.get(sx.aimx+","+sx.aimy).setFill(c0);
								ts.get(nx+","+ny).setFill(c1);
							}catch(Exception e3) {}
							sx.aimx=nx;
							sx.aimy=ny;
//							if((py+px+lz.grabr)%2==1) {
//								for(int i=0;i<3;i++) {
//									ts.get(i*2+"").setOpacity(0);
//									ts.get((i*2+1)+"").setOpacity(1);
//								}
//								sx.aimr=1;
//								ts.get(0+"").setFill(c0);
//								ts.get(1+"").setFill(c1);
//							}
//							else {
//								for(int i=0;i<3;i++) {
//									ts.get(i*2+"").setOpacity(1);
//									ts.get((i*2+1)+"").setOpacity(0);
//								}
//								sx.aimr=0;
//								ts.get(0+"").setFill(c1);
//								ts.get(1+"").setFill(c0);
//							}
						});
					}
				});
				double ctx=lz.posx+820+((lz.x+lz.y)%2)*100,cty=lz.posy+520+((lz.x+lz.y)%2)*100;
				Circle r=new Circle(ctx,cty,200);
				r.setFill(Color.TRANSPARENT);
				handlepane.getChildren().add(r);
				for(int i=0;i<6;i++) {
					Polygon t1 = new Polygon(ctx,cty+200,ctx,cty,ctx-40,cty-80,ctx,cty-200,ctx+40,cty-80,ctx,cty,ctx,cty+200);
					handlepane.getChildren().add(t1);
					ts.put(i+"",t1);
					t1.setRotate(i*60);
					t1.setEffect(l);
					if(i==sx.aimr)t1.setFill(c1);
					else t1.setFill(c0);
					int k=i;
					t1.setOnMouseClicked(e2->{
						e2.consume();
						ts.get(sx.aimr+"").setFill(c0);
						ts.get(k+"").setFill(c1);
						sx.aimr=k;
					});
				}
			}
//			else {
//				原先的niulongzhua已放到makelongzhua
//			}
		}
    }
    public void scaleroot(double deltaY) {
        double currentScale = root.getScaleX();
        double scaleFactor = (deltaY > 0) ? 1.25 : 0.8;
        double targetScale = currentScale * scaleFactor;
        targetScale=(double)Math.round(targetScale*1000)/1000;
        // 计算鼠标坐标
        scale=targetScale;
        
        maxoffsetx=200*scalemubanl-screenwidth/2*(1-scale);
      	maxoffsety=25*scalemubanl-screenheight/2*(1-scale);
        minoffsetx=maxoffsetx-200*scalemubanl-15300*scale+screenwidth;
        minoffsety=maxoffsety-750-12740*scale+screenheight;
        double mouseX = nowmousex - screenwidth / 2;
        double mouseY = nowmousey - screenheight / 2;
        // 计算目标平移
        double currentTranslateX = root.getTranslateX();
        double currentTranslateY = root.getTranslateY();
        double targetTranslateX = currentTranslateX + (1 - scaleFactor) * (mouseX - currentTranslateX);
        double targetTranslateY = currentTranslateY + (1 - scaleFactor) * (mouseY - currentTranslateY);
        double sx=targetTranslateX>minoffsetx?targetTranslateX:minoffsetx,sy=targetTranslateY>minoffsety?targetTranslateY:minoffsety;
        sx=sx<maxoffsetx?sx:maxoffsetx;
        sy=sy<maxoffsety?sy:maxoffsety;
        // 创建新动
    	canscroll=false;
        scaleTimeline.getKeyFrames().setAll(
            new KeyFrame(Duration.seconds(0.2),
                new KeyValue(root.scaleXProperty(), targetScale, Interpolator.LINEAR),
                new KeyValue(root.scaleYProperty(), targetScale, Interpolator.LINEAR),
                new KeyValue(root.translateXProperty(), sx, Interpolator.LINEAR),
                new KeyValue(root.translateYProperty(), sy, Interpolator.LINEAR)
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
            // 递归处理子节
            if (node instanceof Parent) {
                setBordersToAllPanes((Parent) node);
            }
        }
    }
    
    private static void applyBorder(Pane pane) {
        // 定义边框样式
        BorderStroke stroke = new BorderStroke(
            Color.RED,                   // 颜色
            BorderStrokeStyle.SOLID,       // 样式（SOLID/DASHED/DOTTED
            CornerRadii.EMPTY,             // 圆角
            new BorderWidths(10)            // 宽度（左/下）
        );
        pane.setBorder(new Border(stroke));
    }
    public void showallchild(Node node) {
    	node.setVisible(true);
    	 try {
         	if(node instanceof Parent) {
 		        for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
 		        	showallchild(child);
 		        }
         	}
         }catch(Exception e) {}
    }
    // 递归遍历所有子节点
    public void optimizeScene(Scene scene) {
        optimizeNode(scene.getRoot(), scene.getWindow());
    }
    
    public void optimizeNode(Node node, Window ownerWindow) {
        // 跳过非渲染节点和不可见节
        // 计算屏幕坐标系下的节点边
    	
        Bounds screenBounds = node.localToScreen(node.getBoundsInLocal());
        // 判断是否完全在屏幕外
        if (isCompletelyOffscreen(screenBounds)) {
        	node.setVisible(false);
            return; // 隐藏后无需处理子节
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
    private void setFixedGridSize(GridPane grid, int rows, int cols, 
            double cellWidth, double cellHeight) {
		// 设置列约
		for (int i = 0; i < cols; i++) {
		ColumnConstraints column = new ColumnConstraints();
		column.setPrefWidth(cellWidth);
		column.setMinWidth(cellWidth);
		column.setMaxWidth(cellWidth);
		column.setHgrow(Priority.NEVER); // 禁止水平拉伸
		grid.getColumnConstraints().add(column);
		}
		
		// 设置行约
		for (int i = 0; i < rows; i++) {
		RowConstraints row = new RowConstraints();
		row.setPrefHeight(cellHeight);
		row.setMinHeight(cellHeight);
		row.setMaxHeight(cellHeight);
		row.setVgrow(Priority.NEVER); // 禁止垂直拉伸
		grid.getRowConstraints().add(row);
		}	
		// 可选：设置网格间距
//		grid.setHgap(5);
//		grid.setVgap(5);
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
    
	private void deleteselectitem1() {
		if (selectitem1.size() > 0) {
			selectitem1.forEach(e1 -> {
				shixuuses.remove(e1.x + "," + e1.y);
			});
			selectitem1.clear();
			leaveshixu();
		}
	}
    private void deleteselectitem() {
    	if(selectitem.size()>0) {
			clearAllHighlights();
			selectionBox.setVisible(false);
            selectionBox.setOpacity(0);
			nowgrabmovet.stop();
			nowgrabmovet=null;
			fxpane.getChildren().remove(nowgrab1);
			nowgrab1=null;
			selectitem.forEach(g->{
				if(g instanceof xuanwu||g instanceof zhuque||g instanceof baihu) {
					db0history.forEach(e1->{
						e1.remove(g.key);
					});
					int mi=-1;
					try {
						mi=((blend)(g)).machineindex;
					}catch(Exception e1) {}
					g.remove();
					if(mi!=-1)
					for(int i=mi;i<machines.size();i++) {
						for(int j=0;j<maxshixu+1;j++) {
							try {
							if(shixuuses.containsKey(j+","+(i+1)))
								shixuuses.put(j+","+i,shixuuses.get(j+","+(i+1)));
							else shixuuses.remove(j+","+i);
							}catch(Exception e1) {}
						}
						((blend)(machines.get(i))).machineindex--;
					}
					for(int j=0;j<maxshixu+1;j++) {
						try {
						shixuuses.remove(j+","+machines.size());
						}catch(Exception e1) {}
					}
				}
				else if(g instanceof longzhua||g instanceof blend) {
					dbhistory.forEach(e1->{
						e1.remove(g.key);
					});
					int mi=-1;
					try {
						mi=((blend)(g)).machineindex;
					}catch(Exception e1) {}
					g.remove();
					if(mi!=-1)
					for(int i=mi;i<machines.size();i++) {
						for(int j=0;j<maxshixu+1;j++) {
							try {
							if(shixuuses.containsKey(j+","+(i+1)))
								shixuuses.put(j+","+i,shixuuses.get(j+","+(i+1)));
							else shixuuses.remove(j+","+i);
							}catch(Exception e1) {}
						}
						((blend)(machines.get(i))).machineindex--;
					}
					for(int j=0;j<maxshixu+1;j++) {
						try {
						shixuuses.remove(j+","+machines.size());
						}catch(Exception e1) {}
					}
				}
				else g.remove();
				//仅当为spawn时无需动时
			});
			refreshshixupane();	
			selectitem.clear();
			fxpane.getChildren().clear();
		}
    }
    
    private void clearhideselectmachine() {
    	for(grid e2:selectitem) {
    		e2.g.setOpacity(1);
        	try {
        		((longzhua)(e2)).g0.setOpacity(1);
        		((longzhua)(e2)).g0.setOpacity(1);
        	}catch(Exception e3){}
        	try {
        		((zhuque)(e2)).ash.setOpacity(1);
        		gridv[e2.y][e2.x].setOpacity(0);
        	}catch(Exception e3){}
        	try {
        		((baihu)(e2)).g.setOpacity(1);
        		gridv[e2.y][e2.x].setOpacity(0);
        		gridv[e2.y][e2.x+1].setOpacity(0);
        		gridv[e2.y][e2.x+2].setOpacity(0);
        		gridv[e2.y+1][e2.x].setOpacity(0);
        		gridv[e2.y+1][e2.x+1].setOpacity(0);
        		gridv[e2.y+1][e2.x+2].setOpacity(0);
        	}catch(Exception e3){}
        	try {
        		xuanwu now=(xuanwu)(e2);
        		now.added.forEach(e4->{
            		try {
            			e4.setOpacity(1);
            		}catch(Exception e1) {}
            	});
            	if(null!=now.star) {
            		now.star.setOpacity(1);
            	}
            	for(int i=0;i<now.listsize;i++) {
                	now.g.setOpacity(1);
                	gridv[now.y][now.x].setOpacity(0);
                	gridv[now.y][now.x].setOpacity(0);
                	now=now.next;
            	}
        	}catch(Exception e3){}
		}
		
	}
    
    //选中高光
    private void clearAllHighlights() {
        for (Map.Entry<Node, Effect> entry : originalEffects.entrySet()) {
            entry.getKey().setEffect(entry.getValue());
        }
        originalEffects.clear();
    }
//    private void selectHighlight(Node node,double selX,double selY,double selW,double selH) {
//    	javafx.geometry.Bounds bounds = node.getBoundsInParent();
////        System.out.println(node.getClass().getSimpleName() + " bounds: " + bounds);
//        if (bounds.intersects(selX, selY, selW, selH)) {
//            originalEffects.putIfAbsent(node, node.getEffect());
//            node.setEffect(new Bloom(0));
////            System.out.println("选中: " + node);
//        }
//    }
    
    private void clearAllHighlights1() {
    	 for (Map.Entry<Node, Effect> entry : originalEffects1.entrySet()) {
             entry.getKey().setEffect(entry.getValue());
         }
         originalEffects1.clear();
    }

    private void selectHighlight1(Node node) {
    	 originalEffects.putIfAbsent(node, node.getEffect());
         node.setEffect(ds8);
    }
    
    void caculatehighlight1() {
    	
        clearAllHighlights1();
        selectitem1.clear();
        selectitem11.clear();
        int x0=(int) ((shixuselectstartX-405*suofang)/70/suofang);
		int y0=(int) ((shixuselectstartY-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang);
        
        int x1=(int) ((nowmousex-405*suofang)/70/suofang);
		int y1=(int) ((nowmousey-shixu.getTranslateY()-83*suofang-screenheight/2)/49/suofang);
    	
		int nx0=nowlookshixu+x0,ny0=nowlookshixuy+y0;
		if (maxshixu<nx0)maxshixu=nx0;
		
		int nx1=nowlookshixu+x1,ny1=nowlookshixuy+y1;
		if (maxshixu<nx1)maxshixu=nx1;
		
		int temp=0;
		if(nx0>nx1) {
			temp=nx1;
			nx1=nx0;
			nx0=temp;
		}
		if(ny0>ny1) {
			temp=ny1;
			ny1=ny0;
			ny0=temp;
		}
		
//		System.out.println(nx0+" "+nx1+" "+ny0+" "+ny1);
		for(int i=nx0;i<=nx1;i++) {
			for(int j=ny0;j<=ny1;j++) {
				if(shixuuses.containsKey(i+","+j)) {
					shixuan iv=shixuuses.get(i+","+j);
					originalEffects1.putIfAbsent(iv, iv.getEffect());
					iv.setEffect(bloom);
					selectitem1.add(iv);
					selectitem11.add(iv.copy(this));
				}
			}
		}
		
    }
    
    void caculatehighlight() {
    	// 获取选择框的实际边界（相对于 root
        double selX = selectionBox.getX();
        double selY = selectionBox.getY();
        double selW = selectionBox.getWidth();
        double selH = selectionBox.getHeight();
        
//        Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.1),e1->{
//            selectionBox.setVisible(false);
//            selectionBox.setOpacity(0);
//        }));
//        t1.play();
        // 调试输出
//        System.out.println("Selection box: x=" + selX + ", y=" + selY + ", w=" + selW + ", h=" + selH);

        clearAllHighlights();
        selectitem.clear();
//        // 遍历所有图
//        for (javafx.scene.Node node : hypane.getChildren()) {
//            if ( node instanceof ImageView||node instanceof Group) {
//            	selectHighlight(node, selX, selY, selW, selH);
//            }
//        }
//        for (javafx.scene.Node node : udpane.getChildren()) {
//        	 if ( node instanceof ImageView||node instanceof Group) {
//            	selectHighlight(node, selX, selY, selW, selH);
//            }
//        }
//        for (javafx.scene.Node node : dfbpane.getChildren()) {
//        	 if ( node instanceof ImageView||node instanceof Group) {
//            	selectHighlight(node, selX, selY, selW, selH);
//            }
//        }
        
        int minx=0,miny=0,maxx=0,maxy=0;
        
        int[] point1=postogrid(selX,selY),point2=postogrid(selX,selY+selH),
        		point3=postogrid(selX+selW,selY),point4=postogrid(selX+selW,selY+selH);
	    
	    
	    boolean firstdown=true,seconddown=true,thirddown=true,forthdown=true,
	    		leftout=false,rightout=false,leftout1=false,rightout1=false;
	    if((point1[0]+point1[1])%2==1)firstdown=false;
	    if((point2[0]+point2[1])%2==1)seconddown=false;
	    if((point3[0]+point3[1])%2==1)thirddown=false;
	    if((point4[0]+point4[1])%2==1)forthdown=false;
	    
	    miny=point1[1];
	    maxy=point4[1];
	    if(selX-point1[0]*150<150) {
	    	minx=point1[0]-1;
	    	leftout=true;
	    }
	    else minx=point1[0];
	    if(selX+selW-point4[0]*150>150) {
	    	maxx=point4[0]+1;
	    	rightout=true;
	    }
	    else  maxx=point4[0];
	    if(selX-point2[0]*150<150) {
	    	leftout1=true;
	    }
	    if(selX+selW-point3[0]*150>150) {
	    	rightout1=true;
	    }

	    boolean hasleftup=firstdown||!firstdown&&!leftout,
	    		hasleftdown=!seconddown||seconddown&&!leftout1,
	    		hasrightup=thirddown||!thirddown&&!rightout1,
	    		hasrightdown=!forthdown||forthdown&&!rightout;
	    
	    if(miny==maxy) {
	    	minx=point1[0]<point2[0]?point1[0]:point2[0];
	    	maxx=point3[0]>point4[0]?point3[0]:point4[0];
	    	hasleftup=hasleftdown=hasrightup=hasrightdown=true;
	    }

	    for(int i=miny;i<maxy+1;i++) {
	    	for(int j=minx;j<maxx+1;j++) {
		    	if(!((i==miny&&j==minx)||(i==miny&&j==maxx)||(i==maxy&&j==minx)||(i==maxy&&j==maxx))||
		    			(i==miny&&j==minx&&hasleftup)||
		    			(i==miny&&j==maxx&&hasrightup)||
		    			(i==maxy&&j==minx&&hasleftdown)||
		    			(i==maxy&&j==maxx&&hasrightdown)) {
		    		if(i>-1&&i<MAPHEIGHT&&j>-1&&j<MAPWIDTH) {
    		    		if(db[i][j]!=null) {
    		    			if(db[i][j] instanceof longzhua) {
    		    				selectitem.add(db[i][j]);
    		    				longzhua l=(longzhua)db[i][j];
    		    				selectHighlight1(l.g);
    		    				selectHighlight1(l.g0);
    		    			}
    		    			else {
    		    				if(!selectitem.contains(db[i][j])) {
    		    					selectitem.add(db[i][j]);
    		    					selectHighlight1(db[i][j].g);
    		    				}
    		    			}		
    		    		}
    		    		if(db0[i][j]!=null) {
    		    			if(!selectitem.contains(db0[i][j])) {
    		    				
    		    				if(db0[i][j] instanceof xuanwu) {
    		    					xuanwu fs=((xuanwu) (db0[i][j])).first;
    		    					selectitem.add(fs);
    		    					fs.cl.forEach(e1->{
//    		    			    		try {
    		    			    			selectHighlight1(e1);
//    		    			    		}catch(Exception e2) {}
    		    			    	});
    		    				}
    		    				else {
    		    					selectitem.add(db0[i][j]);
    		    					selectHighlight1(db0[i][j].g);
    		    				}
    		    					
    		    			}
    		    		
    		    		}
//    		    		selectHighlight1(gridv[i][j]);
    		    		//网格
		    		}
		    	}
		    }
	    }
//	    System.out.println();
    }
    
    //TODO
    void showunderhelp(MouseEvent e) {
    	if(nowgrab2==null) return;
    	 PickResult pickResult = e.getPickResult();
         Node targetNode = pickResult.getIntersectedNode();
         
         int isinbutton=0;
         //设置
         if(targetNode.equals(setting)) {
        	isinbutton=1;
         	if(null!=nowgrab3&&nowgrab3.equals(setting)) {
         		tx.setTranslateX(help.getTranslateX()+100*suofang);
         		tx.setTranslateY(help.getTranslateY());
         	}
         	else{
         		ui.getChildren().remove(tx);
         		nowgrab3=setting;
         		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
         				i18n.jiaochengname[language],i18n.jiaochengd[language][0]);
         	}
         }
         //真经
         else if(targetNode.equals(zhengjin)) {
         	isinbutton=1;
          	if(null!=nowgrab3&&nowgrab3.equals(zhengjin)) {
          		tx.setTranslateX(help.getTranslateX()+100*suofang);
          		tx.setTranslateY(help.getTranslateY());
          	}
          	else{
          		ui.getChildren().remove(tx);
          		nowgrab3=zhengjin;
          		maketext(help.getTranslateX(),help.getTranslateY(),7,
          				i18n.jiaochengname[language],i18n.jiaochengd[language][1]);
          	}
         }
         //灵印供应
         else if(mubanleft.getChildren().contains(targetNode)&&!targetNode.equals(mubanl1)) {
          	isinbutton=1;
           	if(null!=nowgrab3&&nowgrab3.equals(targetNode)) {
           		tx.setTranslateX(help.getTranslateX()+100*suofang);
           		tx.setTranslateY(help.getTranslateY());
           	}
           	else{
           		ui.getChildren().remove(tx);
           		nowgrab3=targetNode;
           		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
           				i18n.jiaochengname[language],i18n.jiaochengd[language][2]);
           	}
         }
         //灵盒
         else if(isDescendant(targetNode,pzs.stack)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(pzs.stack)) {
            		tx.setTranslateX(help.getTranslateX()+100*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=pzs.stack;
            		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][3]);
            	}
         }
         //龙爪
         else if(targetNode.equals(ilongzhua)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(ilongzhua)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=ilongzhua;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][4]);
            	}
         }
         else if(targetNode.equals(izhuque)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(izhuque)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=izhuque;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][5]);
            	}
         }
         else if(targetNode.equals(ibaihu)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(ibaihu)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=ibaihu;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][6]);
            	}
         }
         else if(targetNode.equals(ixuanwu)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(ixuanwu)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=ixuanwu;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][7]);
            	}
         }
         else if(targetNode.equals(ihunyuan)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(ihunyuan)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=ihunyuan;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][8]);
            	}
         }
         else if(targetNode.equals(iwuzang)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(iwuzang)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=iwuzang;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][9]);
            	}
         }
         else if(targetNode.equals(iliuhe)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(iliuhe)) {
            		tx.setTranslateX(help.getTranslateX()-550*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=iliuhe;
            		maketext(help.getTranslateX()-550*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][10]);
            	}
         }
         
         //灵盘
         else if(isDescendant(targetNode,root)) {
        	 isinbutton=1;
            	if(null!=nowgrab3&&nowgrab3.equals(root)) {
            		tx.setTranslateX(help.getTranslateX()+100*suofang);
            		tx.setTranslateY(help.getTranslateY());
            	}
            	else{
            		ui.getChildren().remove(tx);
            		nowgrab3=root;
            		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
            				i18n.jiaochengname[language],i18n.jiaochengd[language][11]);
            	}
         }
         
         //暂停按钮
         else if(isDescendant(targetNode,zantb)) {
        	isinbutton=1;
        	if(null!=nowgrab3&&nowgrab3.equals(zantb)) {
        		tx.setTranslateX(help.getTranslateX()+100*suofang);
        		tx.setTranslateY(help.getTranslateY());
        	}
        	else{
        		ui.getChildren().remove(tx);
        		nowgrab3=zantb;
        		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
        				i18n.jiaochengname[language],i18n.jiaochengd[language][12]);
        	}
         }
         else if(isDescendant(targetNode,sxgp)) {
        	isinbutton=1;
         	if(null!=nowgrab3&&nowgrab3.equals(sxgp)) {
         		tx.setTranslateX(help.getTranslateX()+100*suofang);
         		tx.setTranslateY(help.getTranslateY());
         	}
         	else{
         		ui.getChildren().remove(tx);
         		nowgrab3=sxgp;
         		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
         				i18n.jiaochengname[language],i18n.jiaochengd[language][13]);
         	}
         }
         else if(isDescendant(targetNode,shixubo)) {
        	isinbutton=1;
          	if(null!=nowgrab3&&nowgrab3.equals(shixubo)) {
          		tx.setTranslateX(help.getTranslateX()+100*suofang);
          		tx.setTranslateY(help.getTranslateY());
          	}
          	else{
          		ui.getChildren().remove(tx);
          		nowgrab3=shixubo;
          		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
          				i18n.jiaochengname[language],i18n.jiaochengd[language][14]);
          	}
          }
         //提示
         else if(targetNode.equals(fushil)) {
        	isinbutton=1;
           	if(null!=nowgrab3&&nowgrab3.equals(fushil)) {
           		tx.setTranslateX(help.getTranslateX()+100*suofang);
           		tx.setTranslateY(help.getTranslateY());
           	}
           	else{
           		ui.getChildren().remove(tx);
           		nowgrab3=fushil;
           		maketext(help.getTranslateX()+100*suofang,help.getTranslateY(),7,
           				i18n.jiaochengname[language],i18n.jiaochengd[language][15]);
           	}
         }
         for(int i=0;i<9;i++) {
         	if(targetNode.equals(shixuans[i])) {
         		isinbutton=1;
               	if(null!=nowgrab3&&nowgrab3.equals(shixuans[i])) {
               		tx.setTranslateX(help.getTranslateX()+100*suofang);
               		tx.setTranslateY(help.getTranslateY()-100*suofang);
               	}
               	else{
               		ui.getChildren().remove(tx);
               		nowgrab3=shixuans[i];
               		maketext(help.getTranslateX()+100*suofang,help.getTranslateY()-100*suofang,7,
               				i18n.jiaochengname[language],i18n.jiaochengd[language][16+i]);
               	}
         	}
         }
         try {
        	 tx.setMouseTransparent(true);
         }catch(Exception e1) {}
         if(isinbutton==0) {
        	 ui.getChildren().remove(tx);
        	 nowgrab3=null;
         }
    }
    
    /**d=0向右下弹出，1下，2左下左，4左上上，6右上*/
    public Group maketext(double x,double y,int d,String title,String word) {
    	if(language<4)
    		return maketext(x,y,550*suofang,((word.length()-2)/20)*43*suofang+((null!=title)?110*suofang:60*suofang),d,title,word);
    	else return maketext(x,y,550*suofang,((word.length()-1)/40)*43*suofang+((null!=title)?110*suofang:60*suofang),d,title,word);
    }

    /**x y弹出x y=0时在正中w h对话框宽d=0向右下弹出，1下，2左下，直向右 */
    public Group maketext(double x,double y,double w,double h,int d,String title,String word) {
    	tx=new Group();
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
    		e.consume();
    		
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
    	return tx;
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
			t.setFill(Color.color(1, 1, 1, 0.6));
		}
		else if(c==1) {
			t.setFill(Color.color(0.4, 0.8, 1, 0.6));
		}
		fxpane.getChildren().add(t);
    }
    
    void switchsettingshow() {
		if(settingshowf) {
			closesetting();
			setting.setOpacity(1);
			settingshowf=false;
			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.15),new KeyValue(settingpane.translateYProperty(),-screenheight)));
    		t.play();
    		t.setOnFinished(e1->{
    			game.getChildren().remove(settingpane);
    		});	
		}
		else {
			setting.setOpacity(0);
			settingshowf=true;
			System.out.println(1);
			if(null==settingpane) {
    			settingpane=new settingpane(this);
    			settingpane.setEffect(ds0);
			}
			settingpane.setTranslateX(screenwidth/2-screenheight*0.8/1028*798/2);
    		settingpane.setTranslateY(-screenheight);
			game.getChildren().add(settingpane);
			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.15),new KeyValue(settingpane.translateYProperty(),0.1*screenheight)));
    		t.play();
		}	
    }
    
    public void exitguanka() {
    	blendefine.game1.setVisible(true);
//		try {bdf.aw.timer.start();}catch(Exception e) {}
		 if(bdf.isbookshowf==0)bdf.zhengjin.setOpacity(0);
	        if(bdf.iswuxiaoshowf==0)bdf.wuxiaojian.setOpacity(0);
	        if(bdf.isboxshowf==0)bdf.lingjvhe.setOpacity(0);
	        if(bdf.isguishowf==0)bdf.guiniu.setOpacity(0);
		try {bdf.ae1.animationTimer.start();}catch(Exception e) {}
		Timeline t = new Timeline(
				new KeyFrame(Duration.seconds(0.5),
						new KeyValue(blendefine.game1.opacityProperty(), 1),
						new KeyValue(game.opacityProperty(), 1)),
				new KeyFrame(Duration.seconds(1),e->{

					bdf.clearguanka();
					game.setMouseTransparent(true);
					ui.getChildren().remove(pzs.stack);
					pzs.gk=null;
					pzs.destroy();
					pzs=null;
					
				}),
				
				
				new KeyFrame(Duration.seconds(3),
						new KeyValue(game.opacityProperty(), 0)),
				new KeyFrame(Duration.seconds(3.2)));
		t.play();
		t.setOnFinished(e -> {
			destroy();
		});
    }
    public void destroy() {
        // 1. 停止所有动画效
        stopAllAnimations();
        // 2. 清空场景布局
        clearSceneGraph();
        // 3. 关闭线程
        shutdownExecutorService();
        // 4. 移除事件监听
        removeEventHandlers();
        // 5. 销毁自定义游戏对象
        releaseContainersAndArrays();
    }
    private void stopAllAnimations() {
        // 停止所有正在运行的动画
        if (scaleTimeline != null) {
            scaleTimeline.stop();
            scaleTimeline=null;
        }
        if (process != null) {
            process.stop();
            process=null;
        }
        if (pixer != null) {
        	pixer.stop();
        	pixer=null;
        }
        if (intro != null) {
        	intro.stop();
        	intro=null;
        }
        if (charlight != null) {
        	charlight.stop();
        	charlight=null;
        }
        if (opentimeline != null) {
        	opentimeline.stop();
        	opentimeline=null;
        }
        if (process != null ) {
        	process.stop();
        	process=null;
        }
        if (nowgrabmovet != null) {
        	nowgrabmovet.stop();
        	nowgrabmovet=null;
        }
        if (putxuanwutimeline != null) {
        	putxuanwutimeline.stop();
        	putxuanwutimeline=null;
        }
        if (sst != null ) {
        	sst.stop();
        	sst=null;
        }
        if (niulzt != null) {
        	niulzt.stop();
        	niulzt=null;
        }
        if (savet != null) {
        	savet.stop();
        	savet=null;
        }
        if (pixer != null) {
        	pixer.stop();
        	pixer=null;
        }
        if (reviewintro != null) {
        	reviewintro.stop();
        	reviewintro=null;
        }
        if (smoothTimeline != null) {
        	smoothTimeline.stop();
        	smoothTimeline=null;
        }
        if (listentarget != null) {
        	listentarget.stop();
        	listentarget=null;
        }
        if (showspawnnumbert != null) {
        	showspawnnumbert.stop();
        	showspawnnumbert=null;
        }
        if (hidespawnnumbert != null) {
        	hidespawnnumbert.stop();
        	hidespawnnumbert=null;
        }
        if (caltime != null) {
        	caltime.stop();
        	caltime=null;
        }
        if (helpmovetime != null) {
        	helpmovetime.stop();
        	helpmovetime=null;
        }
        
        tms.forEach(e->{
        	if (e != null) {
            	e.stop();
            	e=null;
            }
        });
        tms.clear();
        tms=null;
        try {
        	anw.destroy();
        	anw1.destroy();
        	firee.destroy();
        	dragonl.destroy();
        }catch(Exception e) {}
    }
    private void clearSceneGraph() {
        // 递归释放所有节点资
        releaseNodeRecursively(game);
        releaseNodeRecursively(alli);
        releaseNodeRecursively(fpsText);
        // 清空根节点的所有子节点
        game.getChildren().clear();
        // 显式置空关键布局容器（根据实际需求可继续添加
        game = null;
        stp.getChildren().remove(game);
        stp = null;
    }
    /**
     * 递归释放节点及其子节点的资源
     * @param node 待释放的节点
     */
    private void releaseNodeRecursively(Node node) {
        if (node == null) {
            return;
        }
        node.setEffect(null);
        node.setClip(null);
        // 1. 处理 ImageView：清空图
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;
            Image image = imageView.getImage();
            if (image != null) {
                // 如果图像是后台加载的，可调用 cancel() 停止加载
                image.cancel();
                // 解除 ImageView Image 的引
                imageView.setImage(null);
            }
            // 可选：清除其他属性（如效果、变换等）以帮助 GC

        }
        // 2. 如果节点是一个容器（Parent），递归处理其所有子节点
        if (node instanceof Group) {
            Group parent = (Group) node;
            // 复制一份子节点列表，避免递归时并发修
            for (Node child : parent.getChildren()) {
                releaseNodeRecursively(child);
            }
        }
        else if (node instanceof Pane) {
        	Pane parent = (Pane) node;
            for (Node child : parent.getChildren()) {
                releaseNodeRecursively(child);
            }
        }
        // 3. 处理其他特殊节点（可选扩展）
        // 例如：MediaView 可释放媒体资源，WebView 可清理页面等
    }

    private void shutdownExecutorService() {
        // 优雅关闭线程
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
        // 移除根节点的事件监听
        scene.setOnKeyReleased(null);
        scene.setOnKeyPressed(null);
    }
    /**
     * 释放所有数组、集合及可能泄漏的资源（不包括已处理Timeline Node
     */
    private void releaseContainersAndArrays() {
        // 1. 清理所Image 数组：取消加载并置空元素
        cleanupImageArray(triiu);
        cleanupImageArray(triid);
        cleanupImageArray(zhengjins);
        cleanupImageArray(wuzang);
        cleanupImageArray(baihuis);
        cleanupImageArray(baoshibs);
        cleanupImageArray(zhuabis);
        cleanupImageArray(zhuazis);
        cleanupImageArray(zhuazigs);
        cleanupImageArray(shixuyys);
        cleanupImageArray(luoshus);
        cleanupImageArray(shixuani);
        cleanupImageArray(fuzhii);
        cleanupImageArray(definezang);
        triiu = triid = zhengjins = wuzang = baihuis = baoshibs = zhuabis = zhuazis = zhuazigs = shixuyys = luoshus = shixuani = fuzhii = definezang = null;

        // 2. 清理 ImageView 数组
        if (caozuoan != null) {
            for (int i = 0; i < caozuoan.length; i++) {
                if (caozuoan[i] != null) {
                    caozuoan[i].setImage(null);
                    caozuoan[i] = null;
                }
            }
            caozuoan = null;
        }

        // 3. 清理二维 ImageView 数组
        if (gridv != null) {
            for (int i = 0; i < gridv.length; i++) {
                if (gridv[i] != null) {
                    for (int j = 0; j < gridv[i].length; j++) {
                        if (gridv[i][j] != null) {
                            gridv[i][j].setImage(null);
                            gridv[i][j] = null;
                        }
                    }
                    gridv[i] = null;
                }
            }
            gridv = null;
        }

        // 4. 清理 Blend 数组
        if (zangs != null) {
            Arrays.fill(zangs, null);
            zangs = null;
        }

        // 5. 清理自定义对象数(shixuan 类型)
        if (shixuans != null) {
            Arrays.fill(shixuans, null);
            shixuans = null;
        }
        if (charans != null) {
            Arrays.fill(charans, null);
            charans = null;
        }

        // 6. 清理所ConcurrentHashMap
        clearConcurrentHashMap(shixuuses);
        clearConcurrentHashMap(mostpic);
        clearConcurrentHashMap(charsi);
        clearConcurrentHashMap(charsil);
        clearConcurrentHashMap(baoshiimagemap);
        clearConcurrentHashMap(charsic);
        clearConcurrentHashMap(charsicl);
        clearConcurrentHashMap(baoshianimalmap);
        clearConcurrentHashMap(baoshifxmap);
        clearConcurrentHashMap(animalindex);
        clearConcurrentHashMap(rectcolor);
        clearConcurrentHashMap(offscreenTimestamps);
        clearConcurrentHashMap(colics);
        clearConcurrentHashMap(ts);
        clearConcurrentHashMap(iniitemplaces);
        clearConcurrentHashMap(hunyuancft);
        clearConcurrentHashMap(wuzangcft);
        clearConcurrentHashMap(wuzangcftni);
        clearConcurrentHashMap(findcraft);
        clearConcurrentHashMap(findmaterial);
        clearConcurrentHashMap(findcraftgraph);
        clearConcurrentHashMap(machineskey);

        // 7. 清理 HashSet
        clearHashSet(scenekeys);
        clearHashSet(playedfu);
        clearHashSet(selectitem);
        clearHashSet(selectitem1);
        clearHashSet(selectitem11);
        clearHashSet(longzhuacanlen);
        clearHashSet(longzhuacanlen1);
        clearHashSet(addedmachines);
        clearHashSet(banlist);

        // 8. 清理 List / LinkedList / ArrayList
        if (machines != null) {
            machines.clear();
            machines = null;
        }
        if (dirs != null) {
            dirs.clear();
            dirs = null;
        }
        if (luoshusList != null) {
            // 如果列表中包ImageView，先清空图像
            for (ImageView iv : luoshusList) {
                if (iv != null) iv.setImage(null);
            }
            luoshusList.clear();
            luoshusList = null;
        }
        if (dbhistory != null) {
            dbhistory.clear();
            dbhistory = null;
        }
        if (db0history != null) {
            db0history.clear();
            db0history = null;
        }
        if (dfhistory != null) {
            dfhistory.clear();
            dfhistory = null;
        }
        if (roothistory != null) {
            roothistory.clear();
            roothistory = null;
        }
        if (rects != null) {
            rects.clear();
            rects = null;
        }
        if (originalEffects != null) {
            originalEffects.clear();
            originalEffects = null;
        }
        if (originalEffects1 != null) {
            originalEffects1.clear();
            originalEffects1 = null;
        }
        if (tms != null) {
            // 注意：tms HashSet<Timeline>，虽Timeline 不主动清理，但将引用置空即可
            tms.clear();
            tms = null;
        }
        if (sealMap != null) {
            sealMap.clear();
            sealMap = null;
        }
        if (baoshis != null) {
            baoshis.clear();
            baoshis = null;
        }

        // 9. 清理二维碰撞数组 colis db/db0/df（这些是对象数组，需要逐元素置空）
        if (colis != null) {
            for (int i = 0; i < colis.length; i++) {
                if (colis[i] != null) {
                    for (int j = 0; j < colis[i].length; j++) {
                        if (colis[i][j] != null) {
                            colis[i][j].clear();
                            colis[i][j] = null;
                        }
                    }
                    colis[i] = null;
                }
            }
            colis = null;
        }

        if (db != null) {
            for (int i = 0; i < db.length; i++) {
                if (db[i] != null) {
                    Arrays.fill(db[i], null);
                    db[i] = null;
                }
            }
            db = null;
        }

        if (db0 != null) {
            for (int i = 0; i < db0.length; i++) {
                if (db0[i] != null) {
                    Arrays.fill(db0[i], null);
                    db0[i] = null;
                }
            }
            db0 = null;
        }

        if (df != null) {
            for (int i = 0; i < df.length; i++) {
                if (df[i] != null) {
                    Arrays.fill(df[i], null);
                    df[i] = null;
                }
            }
            df = null;
        }

        // 10. 清理其它可能持有资源的对象（Effect、Font、Rotate 等，置空即可
        ds1 = ds = ds0 = ds4 = ds3 = ds2 = ds5 = null;
        ds8 = null;
        gausb = gsb1 = null;
        bloom = null;
        f5 = f3 = f25 = f2 = ff1 = null;
        leftclock = leftanti = rightclock = rightanti = downclock = downanti = null;
        rolls = null;
        selectionBox = selectionBox1 = null;
        nowgrab = nowgrab1 = null;
        nowniulz = null;
        backBuffer = null;
        rttts = null;  // RotateTransition 数组，非 Timeline 但无特殊资源，置空即
        anw = anw1 = null;
        firee = null;
        dragonl = null;
        params = params1 = null;
        charlight = null;

        // 注意：不清理 Timeline 变量（process, smoothTimeline, opentimeline 等）及其引用的对

        // 11. 如果 blendefine 实例不再需要，可置空（谨慎，若有其他引用则不要
        // bdf = null;
    }

    // 辅助方法：清Image 数组
    private void cleanupImageArray(Image[] arr) {
        if (arr == null) return;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                arr[i].cancel();
                arr[i] = null;
            }
        }
    }

    // 辅助方法：清ConcurrentHashMap
    private void clearConcurrentHashMap(ConcurrentHashMap<?, ?> map) {
        if (map != null) {
            map.clear();
            // 注意：不能直map = null，因为传入的是引用副本，需要在调用处置 null
        }
    }

    // 辅助方法：清HashSet
    private void clearHashSet(HashSet<?> set) {
        if (set != null) {
            set.clear();
        }
    }

    
//    private void captureSnapshot() {
//        // 捕获演示 UI 的快
//    	SnapshotParameters pa1=new SnapshotParameters();
//    	pa1.setFill(Color.TRANSPARENT);
//    	pa1.setViewport(new Rectangle2D(100,0,700,600));
//        WritableImage snapshot = tocap.snapshot(pa1, null);
//        snapshots.add(snapshot);
//        
//        // 显示提示信息
//        new p("快照已捕, "已成功捕获当UI 状态的快照。\n当前快照数量: " + snapshots.size());
//    }
//
//    private void overlaySnapshots() {
//        if (snapshots.isEmpty()) {
//            new p("无快, "请先捕获至少一个快照);
//            return;
//        }
//        
//        // 创建叠加图像
//        WritableImage baseImage = snapshots.get(0);
//        int width = 700;
//        int height =600;
//        
//        overlayedImage = new WritableImage(width, height);
//        
//        // 将每个快照绘制到叠加图像上（使用半透明效果
//        for (WritableImage snapshot : snapshots) {
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    Color originalColor = overlayedImage.getPixelReader().getColor(x, y);
//                    Color snapshotColor = snapshot.getPixelReader().getColor(x, y);
//                    
//                    // 如果快照像素不是透明的，则混合颜
//                    if (snapshotColor.getOpacity() > 0.1) {
//                        // 使用半透明混合
//                        Color blendedColor = originalColor.interpolate(snapshotColor, 0.5);
//                        overlayedImage.getPixelWriter().setColor(x, y, blendedColor);
//                    }
//                }
//            }
//        }   
//        new p("快照已叠, "已成功叠" + snapshots.size() + " 个快照);
//    }  
   
    public List<List<String>> findAllConnectionPaths(String start, String end, int maxDepth) {
        List<List<String>> allPaths = new ArrayList<>();
        
        if (!findcraftgraph.containsKey(start) || !findcraftgraph.containsKey(end)) {
            return allPaths;
        }
        
        Set<String> visited = new HashSet<>();
        List<String> currentPath = new ArrayList<>();
        
        dfsFindAllPathsWithDepth(start, end, visited, currentPath, allPaths, 0, maxDepth);
        
        return allPaths;
    }

    private void dfsFindAllPathsWithDepth(String current, String end,
                                        Set<String> visited,
                                        List<String> currentPath,
                                        List<List<String>> allPaths,
                                        int currentDepth, int maxDepth) {
        // 检查深度限
        if (currentDepth > maxDepth) {
            return;
        } 
        visited.add(current);
        currentPath.add(current);
        if (current.equals(end)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (String neighbor : findcraftgraph.get(current)) {
                if (!visited.contains(neighbor)) {
                    dfsFindAllPathsWithDepth(neighbor, end, visited, currentPath, 
                                           allPaths, currentDepth + 1, maxDepth);
                }
            }
        }  
        visited.remove(current);
        currentPath.remove(currentPath.size() - 1);
    }
    
    public static boolean isDescendant(Node potentialDescendant, Node potentialAncestor) {
        Node current = potentialDescendant;
        while (current != null) {
            if (current == potentialAncestor) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }
    
    // ========== 存档/读档辅助方法 ==========
    
    /** 将机器类型名转为类型使用机器对象实际name 字段匹配 */
    private int machineTypeCode(String name) {
        switch(name) {
            case "混元": return 0;
            case "五脏": return 1;
            case "六合": return 2;
            case "青龙": return 3; // longzhua name "青龙" 而非 "龙爪"
            case "白虎": return 4;
            case "朱雀": return 5;
            case "玄武": return 6;
            case "九龙": return 7;
            default: return 8; // definespawn / guanzhu / 其它
        }
    }
    
    // ========== 读档 ==========
    
    /** 快速读取当前关卡的最新存*/
    public void readfromsaving() {
        String dirPath = SAVE_DIR + "/level_" + level;
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("读档失败: 未找到关" + level + " 的存档目" + dirPath);
            return;
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files == null || files.length == 0) {
            System.out.println("读档失败: 关卡 " + level + " 下没有存档文");
            return;
        }
        // 按最后修改时间排序，取最新的
        Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
        readfromsaving(files[0].getAbsolutePath());
    }
    
    /** 读取指定存档文件 */
    public void readfromsaving(String filePath) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(filePath));
            
            // 1. 验证文件
            int magic = dis.readInt();
            if (magic != 0x53415645) {
                System.out.println("读档失败: 无效的存档文" + filePath);
                return;
            }
            int version = dis.readInt();
            if (version != 1) {
                System.out.println("读档失败: 不支持的存档版本 " + version);
                return;
            }
            
            // 2. 读取关卡数据
            int savedLevel = dis.readInt();
            String[] savedTarget = new String[6];
            for (int i = 0; i < 6; i++) savedTarget[i] = dis.readUTF();
            int banSize = dis.readInt();
            HashSet<String> savedBanlist = new HashSet<>();
            for (int i = 0; i < banSize; i++) savedBanlist.add(dis.readUTF());
            int baosSize = dis.readInt();
            HashMap<String, Integer> savedBaoshis = new HashMap<>();
            for (int i = 0; i < baosSize; i++) {
                String k = dis.readUTF();
                int v = dis.readInt();
                savedBaoshis.put(k, v);
            }
            int hasBaosSize = dis.readInt();
            HashMap<String, Integer> savedHasBaoshis = new HashMap<>();
            for (int i = 0; i < hasBaosSize; i++) {
                String k = dis.readUTF();
                int v = dis.readInt();
                savedHasBaoshis.put(k, v);
            }
            
            // 3. 读取 db 网格数据
            @SuppressWarnings("unused")
            class SavedMachine {
                String key, name;
                int y, x;
                boolean openf;
                int typeCode;
				int machineindex, face, nowpic;
                HashMap<Integer, String> store = new HashMap<>();
                boolean isLongzhua, isXuanwu;
                int grabx, graby, grabr, nowpic1, nowpic2;
                boolean stuckf, hasGem;
                double rotater;
                int xwSize;
                int[] xwXs, xwYs;
            }
            int dbCount = dis.readInt();
            SavedMachine[] savedDbMachines = new SavedMachine[dbCount];
            for (int i = 0; i < dbCount; i++) {
                SavedMachine sm = new SavedMachine();
                sm.key = dis.readUTF();
                sm.name = dis.readUTF();
                sm.y = dis.readInt();
                sm.x = dis.readInt();
                sm.openf = dis.readBoolean();
                sm.typeCode = dis.readInt();
                sm.isLongzhua = false;
                sm.isXuanwu = false;
                if (sm.typeCode <= 6) {
                    sm.machineindex = dis.readInt();
                    sm.face = dis.readInt();
                    sm.nowpic = dis.readInt();
                    int stSize = dis.readInt();
                    for (int s = 0; s < stSize; s++) {
                        int sk = dis.readInt();
                        String sv = dis.readUTF();
                        sm.store.put(sk, sv);
                    }
                    if (sm.typeCode == 3) { // longzhua
                        sm.isLongzhua = true;
                        sm.grabx = dis.readInt();
                        sm.graby = dis.readInt();
                        sm.grabr = dis.readInt();
                        sm.nowpic1 = dis.readInt();
                        sm.nowpic2 = dis.readInt();
                        sm.stuckf = dis.readBoolean();
                        sm.rotater = dis.readDouble();
                        sm.hasGem = dis.readBoolean();
                    }
                    if (sm.typeCode == 6) { // xuanwu
                        sm.isXuanwu = true;
                        sm.xwSize = dis.readInt();
                        sm.xwXs = new int[sm.xwSize];
                        sm.xwYs = new int[sm.xwSize];
                        for (int s = 0; s < sm.xwSize; s++) {
                            sm.xwXs[s] = dis.readInt();
                            sm.xwYs[s] = dis.readInt();
                        }
                    }
                }
                savedDbMachines[i] = sm;
            }
            
            // 4. 读取 db0 网格数据
            @SuppressWarnings("unused")
            class SavedDb0 {
                String key, name, gemName;
                int y, x;
				boolean openf, finishf;
                int zang;
                HashMap<String, Integer> store = new HashMap<>();
                boolean isXwFirst;
                int xwChainSize;
                int[] xwXs, xwYs;
            }
            int db0Count = dis.readInt();
            SavedDb0[] savedDb0 = new SavedDb0[db0Count];
            for (int i = 0; i < db0Count; i++) {
                SavedDb0 sd = new SavedDb0();
                sd.key = dis.readUTF();
                sd.name = dis.readUTF();
                sd.y = dis.readInt();
                sd.x = dis.readInt();
                sd.openf = dis.readBoolean();
                sd.zang = dis.readInt();
                sd.gemName = dis.readUTF();
                sd.finishf = dis.readBoolean();
                int gzStSize = dis.readInt();
                for (int s = 0; s < gzStSize; s++) {
                    String gk = dis.readUTF();
                    int gv = dis.readInt();
                    sd.store.put(gk, gv);
                }
                sd.isXwFirst = dis.readBoolean();
                sd.xwChainSize = dis.readInt();
                sd.xwXs = new int[sd.xwChainSize];
                sd.xwYs = new int[sd.xwChainSize];
                if (sd.isXwFirst) {
                    for (int ci = 0; ci < sd.xwChainSize; ci++) {
                        sd.xwXs[ci] = dis.readInt();
                        sd.xwYs[ci] = dis.readInt();
                    }
                }
                savedDb0[i] = sd;
            }
            
            // 5. 读取 df 网格数据
            class SavedGem {
                String name, key;
                int y, x, face, zang, upf, iscf;
                double nowangle, tx, ty, rot;
                int groupSize, connsSize;
                String[] groupKeys;
                String[] connKeys;
                String[][] connValues;
            }
            int dfCount = dis.readInt();
            SavedGem[] savedGems = new SavedGem[dfCount];
            for (int i = 0; i < dfCount; i++) {
                SavedGem sg = new SavedGem();
                sg.name = dis.readUTF();
                sg.key = dis.readUTF();
                sg.y = dis.readInt();
                sg.x = dis.readInt();
                sg.face = dis.readInt();
                sg.zang = dis.readInt();
                sg.upf = dis.readInt();
                sg.iscf = dis.readInt();
                sg.nowangle = dis.readDouble();
                sg.tx = dis.readDouble();
                sg.ty = dis.readDouble();
                sg.rot = dis.readDouble();
                sg.groupSize = dis.readInt();
                sg.groupKeys = new String[sg.groupSize];
                for (int g = 0; g < sg.groupSize; g++) sg.groupKeys[g] = dis.readUTF();
                sg.connsSize = dis.readInt();
                sg.connKeys = new String[sg.connsSize];
                sg.connValues = new String[sg.connsSize][];
                for (int c = 0; c < sg.connsSize; c++) {
                    sg.connKeys[c] = dis.readUTF();
                    int nSize = dis.readInt();
                    sg.connValues[c] = new String[nSize];
                    for (int n = 0; n < nSize; n++) sg.connValues[c][n] = dis.readUTF();
                }
                savedGems[i] = sg;
            }
            
            // 6. 读取 machines 顺序
            @SuppressWarnings("unused")
            class SavedMachineOrder {
                String key;
                int x, y;
            }
            int orderCount = dis.readInt();
            SavedMachineOrder[] savedOrder = new SavedMachineOrder[orderCount];
            for (int i = 0; i < orderCount; i++) {
                SavedMachineOrder smo = new SavedMachineOrder();
                smo.key = dis.readUTF();
                smo.x = dis.readInt();
                smo.y = dis.readInt();
                savedOrder[i] = smo;
            }
            
            // 7. 读取 shixuuses
            class SavedShixuan {
                String key;  // "frameIdx,machineKey"
                double tx, ty, sx, sy;
                int aimx, aimy, aimr;
                String type; // shixuanname type like ",",...
            }
            int sxCount = dis.readInt();
            SavedShixuan[] savedShixuans = new SavedShixuan[sxCount];
            for (int i = 0; i < sxCount; i++) {
                SavedShixuan ss = new SavedShixuan();
                ss.key = dis.readUTF();
                ss.tx = dis.readDouble();
                ss.ty = dis.readDouble();
                ss.sx = dis.readDouble();
                ss.sy = dis.readDouble();
                ss.aimx = dis.readInt();
                ss.aimy = dis.readInt();
                ss.aimr = dis.readInt();
                ss.type = dis.readUTF();
                savedShixuans[i] = ss;
            }
            
            // 8. 读取设置
            boolean se = dis.readBoolean();
            boolean sl = dis.readBoolean();
            boolean sp = dis.readBoolean();
            boolean sf = dis.readBoolean();
            boolean ss = dis.readBoolean();
            int sn = dis.readInt();
            int si = dis.readInt();
//            double savedScale = dis.readDouble();
            dis.close();
            
            // ========== 重建游戏状==========
            System.out.println("读档: 开始重建游戏状..");
            
            // 暂停当前游戏，停止所有时间轴
            try { pause(); } catch (Exception e) {}
            try { process.stop(); process.getKeyFrames().clear(); } catch (Exception e) {}
            try { savet.stop(); } catch (Exception e) {}
            try { sst.stop(); } catch (Exception e) {}
            if (refbanlistTimeline != null) {
                refbanlistTimeline.stop();
                refbanlistTimeline = null;
            }
            // 停止所tms 中的 Timeline（如 listentarget 的合成检测）
            for (Timeline t : tms) {
                try { t.stop(); } catch (Exception ignored) {}
            }
            tms.clear();
            
            // 清理当前状
            cleardata();
            for (int j = 0; j < MAPHEIGHT; j++) {
                for (int k = 0; k < MAPWIDTH; k++) {
                    db[j][k] = null;
                    db0[j][k] = null;
                    df[j][k] = null;
                }
            }
            hypane.getChildren().clear();
            lzpane.getChildren().clear();
            dfpane.getChildren().clear();
            dfbpane.getChildren().clear();
            dfupane.getChildren().clear();
            udpane.getChildren().clear();
            connectpane.getChildren().clear();
            sxgp.getChildren().clear();
            // 移除左右木板上的机器/宝石模板图标
            for (ImageView iv : addedmachines) {
                mubanright.getChildren().remove(iv);
            }
            addedmachines.clear();
            for (ImageView iv : addedgems) {
                mubanleft.getChildren().remove(iv);
            }
            addedgems.clear();
            machines.clear();
            machineskey.clear();
            shixuuses.clear();
            for (int j = 0; j < MAPHEIGHT; j++) {
                for (int k = 0; k < MAPWIDTH; k++) {
                    gridv[j][k].setOpacity(1);
                }
            }
            
            // 恢复关卡数据
            this.level = savedLevel;
            for (int i = 0; i < 6; i++) this.target[i] = savedTarget[i];
            this.banlist.clear();
            for (String s : savedBanlist) this.banlist.add(s);
            this.baoshis.clear();
            this.hasbaoshis.clear();
            for (Map.Entry<String, Integer> e : savedBaoshis.entrySet()) {
                this.baoshis.put(e.getKey(), e.getValue());
                this.hasbaoshis.put(e.getKey(), e.getValue());
            }
            baoshiiniposition = 0;
            
            scale = 0.4*suofang;
            seteffect();
            refbaoshis();
            refbanlist();
            listentarget();
            
            System.out.println("读档: 重建 " + dbCount + " 个机..");
            // 关闭龙爪拖拽标记，防makelongzhua 时创niulzt 时间
            boolean savedLongzhuadargf = longzhuadargf;
            longzhuadargf = false;
            // 重建 db 机器
            HashMap<String, grid> createdMachineMap = new HashMap<>();
            for (SavedMachine sm : savedDbMachines) {
                grid newMachine = null;
                double cx = sm.x * 150 + 150;
                double cy = sm.y * 260 + ((sm.x + sm.y) % 2 == 0 ? 86.667 : 173.334);
                nowhoverposx = cx;
                nowhoverposy = cy;
                hovergridx = sm.x;
                hovergridy = sm.y;
                
                try {
                    switch (sm.typeCode) {
                        case 0: newMachine = makehunyuan(sm.x, sm.y); break;
                        case 1: newMachine = makewuzang(sm.x, sm.y); break;
                        case 2: newMachine = makeliuhehunyuan(sm.x, sm.y); break;
                        case 3:
                            newMachine = makelongzhua(sm.x, sm.y);
                            if (newMachine instanceof longzhua) {
                                longzhua lz = (longzhua) newMachine;
                                lz.grabx = sm.grabx;
                                lz.graby = sm.graby;
                                lz.grabr = sm.grabr;
                                lz.stuckf = sm.stuckf;
                                lz.rotater = sm.rotater;
                                // 恢复龙爪爪臂位置
                                try {
                                    ImageView zhuazi = (ImageView) lz.g.getChildren().get(2);
                                    ImageView zhuabi = (ImageView) lz.g.getChildren().get(3);
                                    ImageView niu = (ImageView) lz.g.getChildren().get(5);
    								HashSet<String> lzcl = (lz.grabx + lz.graby) % 2 == 0 ? longzhuacanlen : longzhuacanlen1;
    									int offgridx=lz.grabx-lz.x,offgridy=lz.graby-lz.y;
    									if(lzcl.contains(offgridx+","+offgridy)) {
    										double offx1=150*offgridx,offy1=260*offgridy+((offgridy+offgridx)%2==0?0:((lz.x+lz.y)%2==0?260/3:-260/3));
    									    double length=Math.sqrt(offx1*offx1+offy1*offy1);
    									    double radians = Math.atan2(offy1, offx1);
    									    double degrees = (Math.toDegrees(radians)+90)%360;
    									    zhuabi.setScaleY(length / 520);
    										zhuabi.setRotate(degrees);
    										lz.updateClawPosition(lz,lz.ng,zhuabi,zhuazi,niu);
    										// 不调updateClawPosition（它会用 zhuazis[np2] 覆盖 parity 图片
    										// 改用奇偶格旋转处
    										if((lz.grabx+lz.graby)%2==1) {
    											zhuazi.setRotate(180);
    										}
    										else {
    											zhuazi.setRotate(0);
    										}
    									}
                                    // 奇偶格旋转（必须在爪臂定位之后，因为 zhuabi.setRotate 不影zhuazi
                                    // g 子节 [0]=Group, [1]= [2]=middleImage1(, [3]=middleImage(, [4]= [5]=upImage
                                   
                                    
                                } catch (Exception clawEx) {
                                    System.out.println("读档: 设置龙爪爪臂位置失败: " + clawEx.getMessage());
                                }
                            }
                            break;
                        case 4: newMachine = makebaihu(sm.x, sm.y); break;
                        case 5: newMachine = makezhuque(sm.x, sm.y); break;
                    }
                } catch (Exception e) {
                    System.out.println("读档: 重建 " + sm.name + " 失败: " + e.getMessage());
                }
                if (newMachine != null) {
                    try {
                        if (newMachine.x != sm.x || newMachine.y != sm.y) {
                            newMachine.move(sm.x, sm.y);
                        }
                    } catch (Exception e) {}
                    newMachine.openf = sm.openf;
                    if (newMachine instanceof blend) {
                        blend b = (blend) newMachine;
                        b.store.clear();
                        for (Map.Entry<Integer, String> storeEntry : sm.store.entrySet()) {
                            b.store.put(storeEntry.getKey(), storeEntry.getValue());
                        }
                    }
                    createdMachineMap.put(sm.key, newMachine);
                }
            }
            
            System.out.println("读档: 重建 " + db0Count + " 个特殊机..");
            // 重建 db0 机器
            // db0 中同一个机器（白虎/朱雀/玄武）占据多个格子，读取时去
            HashSet<String> processedDb0Keys = new HashSet<>();
            for (SavedDb0 sd : savedDb0) {
                if (processedDb0Keys.contains(sd.key)) continue;
                processedDb0Keys.add(sd.key);
                try {
                    // 设置鼠标位置到目标网格中心，使工厂方法正确吸
                    double cx = sd.x * 150 + 150;
                    double cy = sd.y * 260 + ((sd.x + sd.y) % 2 == 0 ? 86.667 : 173.334);
                    nowhoverposx = cx;
                    nowhoverposy = cy;
                    hovergridx = sd.x;
                    hovergridy = sd.y;
                    
                    if (sd.name.equals("朱雀")) {
                        grid zq = makezhuque(sd.x, sd.y);
                        if (zq != null) {
                            if (zq.x != sd.x || zq.y != sd.y) {
                                zq.move(sd.x, sd.y);
                            }
                            zq.openf = sd.openf;
                            zq.key = sd.key;
                            createdMachineMap.put(sd.key, zq);
                        }
                    } else if (sd.name.equals("白虎")) {
                        grid bh = makebaihu(sd.x, sd.y);
                        if (bh != null) {
                            if (bh.x != sd.x || bh.y != sd.y) {
                                bh.move(sd.x, sd.y);
                            }
                            bh.key = sd.key;
                            createdMachineMap.put(sd.key, bh);
                        }
                    } else if (sd.name.equals("玄武")) {
                        if (sd.isXwFirst) {
                            List<Integer> lx = new ArrayList<>();
                            List<Integer> ly = new ArrayList<>();
                            List<Group> lp = new ArrayList<>();
                            for (int s = 0; s < sd.xwChainSize; s++) {
                                lx.add(sd.xwXs[s]);
                                ly.add(sd.xwYs[s]);
                                lp.add(new Group());
                            }
                            xuanwu xwChain = makexuanwu1(lx, ly, lp);
                            if (xwChain != null) {
                                xwChain.key = sd.key;
                                createdMachineMap.put(sd.key, xwChain);
                            }
                        }
                    } else if (sd.name.contains("九龙")) {
                        iniseal("黄极灵印", sd.x, sd.y);
                    } else {
                        Image nv = baoshiimagemap.get(sd.gemName + "0");
                        if (nv != null) {
                            Group g0 = new Group(new ImageView(nv));
                            ImageView iv = new ImageView(defineg);
                            g0.getChildren().add(iv);
                            makebaoshispawn(sd.x, sd.y, g0, sd.gemName);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("读档: 重建 db0 " + sd.name + " 失败: " + e.getMessage());
                }
            }
            
            longzhuadargf = savedLongzhuadargf;
            
            System.out.println("读档: 重建 " + dfCount + " 个宝..");
            // 重建宝石
            HashMap<String, define> createdGemMap = new HashMap<>();
            for (SavedGem sg : savedGems) {
                try {
                    String imgKey = sg.name + (sg.face * 4);
                    Image img = baoshiimagemap.get(imgKey);
                    if (img == null) img = baoshiimagemap.get(sg.name + "0");
                    Group gemGroup = new Group();
                    ImageView bv = new ImageView(img);
                    gemGroup.getChildren().add(bv);
                    ImageView zangIv = new ImageView(definezang[sg.zang % 10]);
                    gemGroup.getChildren().add(zangIv);
                    gemGroup.setTranslateX(sg.tx);
                    gemGroup.setTranslateY(sg.ty);
                    gemGroup.setRotate(sg.rot);
                    
                    define d = new define(sg.x, sg.y, gemGroup, sg.name, sg.key);
                    d.face = sg.face;
                    d.zang = sg.zang;
                    d.upf = sg.upf;
                    d.iscf = sg.iscf;
                    d.nowangle = sg.nowangle;
                    d.gk = this;
                    df[sg.y][sg.x] = d;
                    dfpane.getChildren().add(gemGroup);
                    createdGemMap.put(sg.key, d);
                } catch (Exception e) {
                    System.out.println("读档: 重建宝石 " + sg.name + " 失败: " + e.getMessage());
                }
            }
            
            // 重建 GemGroup
            for (SavedGem sg : savedGems) {
                define d = createdGemMap.get(sg.key);
                if (d != null && sg.groupSize > 0) {
                    try {
                        for (String gk : sg.groupKeys) {
                            define other = createdGemMap.get(gk);
                            if (other != null && other != d) {
                                d.connect(other);
                            }
                        }
                        for (int c = 0; c < sg.connsSize; c++) {
                            String fromKey = sg.connKeys[c];
                            for (String toKey : sg.connValues[c]) {
                                d.parentGroup.connsxy
                                    .computeIfAbsent(fromKey, k -> new HashSet<>())
                                    .add(toKey);
                            }
                        }
                    } catch (Exception e) {}
                }
            }
            
            // 重建 machines 顺序
            machines.clear();
            machineskey.clear();
            for (SavedMachineOrder smo : savedOrder) {
                grid g = createdMachineMap.get(smo.key);
                if (g != null) {
                    if (g instanceof blend) {
                        ((blend)g).machineindex = machines.size();
                    }
                    machines.add(g);
                    if (g instanceof blend) {
                        machineskey.put(smo.key, ((blend)g).machineindex);
                    }
                }
            }
            // 同步所有玄武链节点machineindex（只有一个节点在 machines 中）
            for (int j = 0; j < MAPHEIGHT; j++) {
                for (int k = 0; k < MAPWIDTH; k++) {
                    if (db0[j][k] instanceof xuanwu) {
                        xuanwu xwNode = (xuanwu) db0[j][k];
                        xuanwu first = xwNode.first;
                        if (first != null) {
                            int idx = machines.indexOf(first);
                            if (idx >= 0) {
                                xwNode.machineindex = idx;
                            }
                        }
                    }
                }
            }
            try { grabshixuupdown(); } catch (Exception e) {}
            
            // 重建 shixuuses machineKey 映射到新 machineIndex
            for (SavedShixuan sxEntry : savedShixuans) {
                try {
                    String[] parts = sxEntry.key.split(",", 2);
                    String frameIdx = parts[0];
                    String machineKey = parts.length > 1 ? parts[1] : "";
                    grid mappedMachine = createdMachineMap.get(machineKey);
                    if (mappedMachine != null) {
                        int newIdx = machines.indexOf(mappedMachine);
                        if (newIdx >= 0) {
                            String newKey = frameIdx + "," + newIdx;
                            // 使用 shixuans[].copy(this) 创建完整功能的时序按钮副
                            shixuan sx = null;
                            for (int ti = 0; ti < shixuanname.length; ti++) {
                                if (shixuanname[ti].equals(sxEntry.type) && ti < shixuans.length && shixuans[ti] != null) {
                                    sx = shixuans[ti].copy(this);
                                    break;
                                }
                            }
                            if (sx == null) continue;
                            sx.setTranslateX(sxEntry.tx);
                            sx.setTranslateY(sxEntry.ty);
                            sx.setScaleX(sxEntry.sx);
                            sx.setScaleY(sxEntry.sy);
                            sx.aimx = sxEntry.aimx;
                            sx.aimy = sxEntry.aimy;
                            sx.aimr = sxEntry.aimr;
                            shixuuses.put(newKey, sx);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("读档: 重建时序失败: " + e.getMessage());
                }
            }
            // 刷新时序盘（shixuuses 重建 sxgp 中的图标
            try { refreshshixupane(); } catch (Exception e) {}
            
            // 更新 history
            dbhistory.clear();
            db0history.clear();
            dfhistory.clear();
            savedbhistory(0);
            
            // 再次停止 process（listentarget 可能重建），但保留机器动画（白虎旋转等）
            try { process.stop(); process.getKeyFrames().clear(); } catch (Exception e) {}
            try { sst.stop(); } catch (Exception e) {}
            try { sst.stop(); } catch (Exception e) {}
            
            // 恢复时序面板状态（clearguanka 会覆scene.setOnMouseMoved
            // EventFilter 确保弹出逻辑不会被替换掉
            shixuoutf = false;
            shixu.setTranslateY(screenheight/2-80*suofang);
            if (ssoutTransition != null) {
                ssoutTransition.stop();
                ssoutTransition.setFromY(shixu.getTranslateY());
                ssoutTransition.setToY(screenheight/2-270*suofang);
            }
            if (ssinTransition != null) {
                ssinTransition.stop();
                ssinTransition.setFromY(shixu.getTranslateY());
                ssinTransition.setToY(screenheight/2-80*suofang);
            }
            scene.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
                nowmousex = e.getX();
                nowmousey = e.getY();
                // 时序面板靠近底部弹出
                if (nowmousey > screenheight - 10 * suofang) {
                    if (!shixuoutf && ssoutTransition != null) {
                        shixuoutf = true;
                        ssinTransition.stop();
                        ssoutTransition.setFromY(shixu.getTranslateY());
                        ssoutTransition.play();
                    }
                }
                // 靠近顶部收起时序
                if (nowmousey < screenheight - 300 * suofang) {
                    if (shixuoutf && ssinTransition != null) {
                        shixuoutf = false;
                        ssoutTransition.stop();
                        ssinTransition.setFromY(shixu.getTranslateY());
                        ssinTransition.play();
                    }
                }
                // 灵具盒靠近顶部弹
                if (nowmousey < 30 * suofang) {
                    if (!lingjvhef && ljoutTransition != null && pzs != null) {
                        lingjvhef = true;
                        ljinTransition.stop();
                        ljoutTransition.setFromY(pzs.stack.getTranslateY());
                        ljoutTransition.play();
                    }
                }
            });
            // 复位灵具盒状
            lingjvhef = false;
            if (pzs != null) pzs.stack.setTranslateY(-770 * suofang);
            
            // 恢复设置
            effectf = se;
            lowgrahf = sl;
            pixelf = sp;
            fpsshowf = sf;
            settingshowf = ss;
            nowpage = sn;
            scaleindex = si;

            
            System.out.println("读档成功！已" + filePath + " 恢复存档");
            
        } catch (FileNotFoundException e) {
            System.out.println("读档失败: 未找到存档文" + filePath);
        } catch (IOException e) {
            System.out.println("读档失败: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("读档失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (dis != null) try { dis.close(); } catch (IOException e) {}
        }
    }
    
    // ========== 存档 ==========
    
    /** 截图：截取 game 的 (0,0,screenwidth,screenheight) 区域 */
    public WritableImage captureScreenshot() {
        try {
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            sp.setViewport(new Rectangle2D(0, 0, screenwidth, screenheight));
            return game.snapshot(sp, null);
        } catch (Exception e) {
            return null;
        }
    }
    
    public void writetosaving() {
        // 预先创建目录和生成时间戳（在 doSave 之外也需要）
        String dirPath = SAVE_DIR + "/level_" + level;
        File dir = new File(dirPath);
        if (!dir.exists()) dir.mkdirs();
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        final String fDirPath = dirPath;
        final String fTimestamp = timestamp;
        // 先在当前帧截图（机器完整可见），然后保存到帧 0 稳定态
        Platform.runLater(() -> {
            final WritableImage snapshot = captureScreenshot();
            // 再复位到时序 0，保存第 0 帧的稳定状
            try { pause(); } catch (Exception ignored) {}
            executor.submit(() -> {
                try { Thread.sleep(150); } catch (InterruptedException ignored) {}
                Platform.runLater(() -> {
                    try { shixumovezero(); } catch (Exception ignored) {}
                    new Timeline(new KeyFrame(Duration.millis(50), ev -> {
                        executor.submit(() -> {
                            doSave(fDirPath, fTimestamp);
                            if (snapshot != null) {
                                String pngPath = fDirPath + "/save_" + fTimestamp + ".png";
                                try {
                                    // 缩放至较小尺寸再保存（减少 png 文件体积）
                                    java.awt.image.BufferedImage bi = SwingFXUtils.fromFXImage(snapshot, null);
                                    int ow = bi.getWidth(), oh = bi.getHeight();
                                    double sf = Math.min(400.0 / ow, 300.0 / oh);
                                    if (sf < 1.0) {
                                        int nw = (int)(ow * sf);
                                        int nh = (int)(oh * sf);
                                        java.awt.Image scaled = bi.getScaledInstance(nw, nh, java.awt.Image.SCALE_SMOOTH);
                                        java.awt.image.BufferedImage small = new java.awt.image.BufferedImage(nw, nh, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                                        small.getGraphics().drawImage(scaled, 0, 0, null);
                                        ImageIO.write(small, "png", new File(pngPath));
                                    } else {
                                        ImageIO.write(bi, "png", new File(pngPath));
                                    }
                                } catch (Exception e) {
                                    System.out.println("存档截图失败: " + e.getMessage());
                                }
                            }
                        });
                    })).play();
                });
            });
        });
    }
    
    private void doSave(String dirPath, String timestamp) {
        String savePath = dirPath + "/save_" + timestamp + ".dat";
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(savePath));
            // 1. 文件
            dos.writeInt(0x53415645); // "SAVE" 魔数
            dos.writeInt(1);          // 版本
            
            // 2. 关卡数据
            dos.writeInt(level);
            for (int i = 0; i < 6; i++) {
                dos.writeUTF(target[i] != null ? target[i] : "");
            }
            // banlist
            dos.writeInt(banlist.size());
            for (String s : banlist) dos.writeUTF(s);
            // baoshis
            dos.writeInt(baoshis.size());
            for (Map.Entry<String, Integer> e : baoshis.entrySet()) {
                dos.writeUTF(e.getKey());
                dos.writeInt(e.getValue());
            }
            // hasbaoshis
            dos.writeInt(hasbaoshis.size());
            for (Map.Entry<String, Integer> e : hasbaoshis.entrySet()) {
                dos.writeUTF(e.getKey());
                dos.writeInt(e.getValue());
            }
            
            // 3. db 网格 上层机器
            {
                // 先统计非空数
                int cnt = 0;
                for (int j = 0; j < MAPHEIGHT; j++)
                    for (int k = 0; k < MAPWIDTH; k++)
                        if (db[j][k] != null) cnt++;
                dos.writeInt(cnt);
                for (int j = 0; j < MAPHEIGHT; j++) {
                    for (int k = 0; k < MAPWIDTH; k++) {
                        if (db[j][k] != null) {
                            grid g = db[j][k];
                            dos.writeUTF(g.key);
                            dos.writeUTF(g.name != null ? g.name : "");
                            dos.writeInt(j); // 实际存储y)
                            dos.writeInt(k); // 实际存储x)
                            dos.writeBoolean(g.openf);
                            int tc = machineTypeCode(g.name);
                            dos.writeInt(tc);
                            if (g instanceof blend) {
                                blend b = (blend) g;
                                dos.writeInt(b.machineindex);
                                dos.writeInt(b.face);
                                dos.writeInt(b.nowpic);
                                dos.writeInt(b.store.size());
                                for (Map.Entry<Integer, String> se : b.store.entrySet()) {
                                    dos.writeInt(se.getKey());
                                    dos.writeUTF(se.getValue() != null ? se.getValue() : "");
                                }
                                if (g instanceof longzhua) {
                                    longzhua lz = (longzhua) g;
                                    dos.writeInt(lz.grabx);
                                    dos.writeInt(lz.graby);
                                    dos.writeInt(lz.grabr);
                                    dos.writeInt(lz.nowpic1);
                                    dos.writeInt(lz.nowpic2);
                                    dos.writeBoolean(lz.stuckf);
                                    dos.writeDouble(lz.rotater);
                                    dos.writeBoolean(lz.d != null);
                                }
                                if (g instanceof xuanwu) {
                                    xuanwu xw = (xuanwu) g;
                                    // 保存玄武链表长度
                                    int size = 0;
                                    xuanwu cur = xw;
                                    while (cur != null) { size++; cur = cur.next; }
                                    dos.writeInt(size);
                                    cur = xw;
                                    while (cur != null) {
                                        dos.writeInt(cur.x);
                                        dos.writeInt(cur.y);
                                        cur = cur.next;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            // 4. db0 网格 特殊机器
            {
                int cnt = 0;
                for (int j = 0; j < MAPHEIGHT; j++)
                    for (int k = 0; k < MAPWIDTH; k++)
                        if (db0[j][k] != null) cnt++;
                dos.writeInt(cnt);
                for (int j = 0; j < MAPHEIGHT; j++) {
                    for (int k = 0; k < MAPWIDTH; k++) {
                        if (db0[j][k] != null) {
                            grid g = db0[j][k];
                            dos.writeUTF(g.key);
                            dos.writeUTF(g.name != null ? g.name : "");
                            dos.writeInt(j);
                            dos.writeInt(k);
                            dos.writeBoolean(g.openf);
                            // 重要：所db0 条目都必须写入相同数量的字段
                            // 否则读取时格式会错位导致 EOFException
                            // definespawn/non-guanzhu 写入默认值占位
                            int zang = 0;
                            String gemName = "";
                            boolean finishf = false;
                            int storeSize = 0;
                            HashMap<String, Integer> storeData = new HashMap<>();
                            if (g instanceof definespawn) {
                                definespawn ds = (definespawn) g;
                                zang = ds.zang;
                                gemName = ds.name != null ? ds.name : "";
                            }
                            if (g instanceof guanzhu) {
                                guanzhu gz = (guanzhu) g;
                                finishf = gz.finishf;
                                storeSize = gz.store.size();
                                storeData = gz.store;
                            }
                            dos.writeInt(zang);
                            dos.writeUTF(gemName);
                            dos.writeBoolean(finishf);
                            dos.writeInt(storeSize);
                            for (Map.Entry<String, Integer> se : storeData.entrySet()) {
                                dos.writeUTF(se.getKey());
                                dos.writeInt(se.getValue());
                            }
                            // 玄武链数据（仅第一个节点写入整条链
                            boolean isXwFirst = false;
                            int xwChainSize = 0;
                            int[] xwXs = null, xwYs = null;
                            if (g instanceof xuanwu) {
                                xuanwu xw = (xuanwu) g;
                                if (xw == xw.first) {
                                    isXwFirst = true;
                                    xuanwu cur = xw;
                                    while (cur != null) { xwChainSize++; cur = cur.next; }
                                    xwXs = new int[xwChainSize];
                                    xwYs = new int[xwChainSize];
                                    cur = xw;
                                    for (int ci = 0; ci < xwChainSize; ci++) {
                                        xwXs[ci] = cur.x;
                                        xwYs[ci] = cur.y;
                                        cur = cur.next;
                                    }
                                }
                            }
                            dos.writeBoolean(isXwFirst);
                            dos.writeInt(xwChainSize);
                            if (isXwFirst) {
                                for (int ci = 0; ci < xwChainSize; ci++) {
                                    dos.writeInt(xwXs[ci]);
                                    dos.writeInt(xwYs[ci]);
                                }
                            }
                        }
                    }
                }
            }
            
            // 5. df 网格 宝石
            {
                int cnt = 0;
                for (int j = 0; j < MAPHEIGHT; j++)
                    for (int k = 0; k < MAPWIDTH; k++)
                        if (df[j][k] != null) cnt++;
                dos.writeInt(cnt);
                for (int j = 0; j < MAPHEIGHT; j++) {
                    for (int k = 0; k < MAPWIDTH; k++) {
                        if (df[j][k] != null) {
                            define d = df[j][k];
                            dos.writeUTF(d.name != null ? d.name : "");
                            dos.writeUTF(d.key != null ? d.key : "");
                            dos.writeInt(j); dos.writeInt(k);
                            dos.writeInt(d.face);
                            dos.writeInt(d.zang);
                            dos.writeInt(d.upf);
                            dos.writeInt(d.iscf);
                            dos.writeDouble(d.nowangle);
                            dos.writeDouble(d.g.getTranslateX());
                            dos.writeDouble(d.g.getTranslateY());
                            dos.writeDouble(d.g.getRotate());
                            // 保存 GemGroup 连接关系
                            if (d.parentGroup != null) {
                                dos.writeInt(d.parentGroup.gems.size());
                                for (define gem : d.parentGroup.gems) {
                                    dos.writeUTF(gem.key);
                                }
                                dos.writeInt(d.parentGroup.connsxy.size());
                                for (Map.Entry<String, HashSet<String>> ce : d.parentGroup.connsxy.entrySet()) {
                                    dos.writeUTF(ce.getKey());
                                    dos.writeInt(ce.getValue().size());
                                    for (String n : ce.getValue()) {
                                        dos.writeUTF(n);
                                    }
                                }
                            } else {
                                dos.writeInt(0); // group size = 0
                                dos.writeInt(0); // conns size = 0
                            }
                        }
                    }
                }
            }
            
            // 6. machines 顺序
            {
                int totalMachines = 0;
                for (grid g : machines) {
                    if (g != null) totalMachines++;
                }
                dos.writeInt(totalMachines);
                for (grid g : machines) {
                    if (g != null) {
                        dos.writeUTF(g.key);
                        dos.writeInt(g.x);
                        dos.writeInt(g.y);
                    }
                }
            }
            
            // 7. shixuuses machineKey 替代 machineIndex
            dos.writeInt(shixuuses.size());
            for (Map.Entry<String, shixuan> e : shixuuses.entrySet()) {
                String oldKey = e.getKey(); // "frameIndex,machineIndex"
                String[] parts = oldKey.split(",");
                String frameIdx = parts[0];
                int mi = Integer.parseInt(parts[1]);
                String machKey = "";
                if (mi >= 0 && mi < machines.size() && machines.get(mi) != null) {
                    machKey = machines.get(mi).key;
                }
                // key: "frameIndex,machineKey"
                dos.writeUTF(frameIdx + "," + machKey);
                shixuan sx = e.getValue();
                dos.writeDouble(sx.getTranslateX());
                dos.writeDouble(sx.getTranslateY());
                dos.writeDouble(sx.getScaleX());
                dos.writeDouble(sx.getScaleY());
                dos.writeInt(sx.aimx);
                dos.writeInt(sx.aimy);
                dos.writeInt(sx.aimr);
                if (sx.t != null) dos.writeUTF(sx.t);
                else dos.writeUTF("");
            }
            
            // 8. 设置标志
            dos.writeBoolean(effectf);
            dos.writeBoolean(lowgrahf);
            dos.writeBoolean(pixelf);
            dos.writeBoolean(fpsshowf);
            dos.writeBoolean(settingshowf);
            dos.writeInt(nowpage);
            dos.writeInt(scaleindex);
//            dos.writeDouble(scale);
            
            dos.flush();
            Platform.runLater(() -> { try { pause(); } catch (Exception ignored) {} });
//            System.out.println("存档成功！已保存" + savePath);
        } catch (IOException e) {
            System.out.println("存档失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (dos != null) try { dos.close(); } catch (IOException e) {}
        }
    }
}
