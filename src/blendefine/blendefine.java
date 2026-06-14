package blendefine;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import fx.AshEffect;
import fx.ParticleSystem;
import javafx.animation.Animation.Status;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class blendefine extends Application {
//----------本地南瓜种植基地-----------//

    //    //      //      \\     
   {{}}  {{}}   {{{}}}    {{}}
   
   //      //     //     \\     
  {{}}   {{{}}}  {{}}   {{{}}}
   
//------------禁止私自采摘------------//

	public static boolean fpsshowf=false,shubiaof=true,guankaf=false,moveboxf=false,triangof=false,yyf=false,lingjvf=false,
			settingshowf=false,xxyf=false;
	//是否进入关卡
	static double screenwidth;
	static double screenheight;
	static double mouserotate1 = 0, mouserotate2 = 0,
			mouserotate3 = 0, mouserollspeed1 = Math.random() * 3 + 0.3, mouserollspeed2 = Math.random() * 3 + 0.3,
			mouserollspeed3 = Math.random() * 3 + 0.3;
	static double nowmousex,nowmousey,lastframecount,frameCount;
	public static double suofang ,scale ;
	private long lastTime = System.nanoTime();
	
	public static int mouserollcount = 0,isopenfinishf = 0, 
			mousetype = 1,fxf=0,savef=0,nowpage=0,zhengjingopenf=0;
	private static WritableImage CURSOR_BUFFER = new WritableImage(72, 66);
	static WritableImage wi1 = CURSOR_BUFFER;
	private static WritableImage wi2;
    SnapshotParameters sn1 = new SnapshotParameters(),sn2 = new SnapshotParameters();
    
    static Font f0,f01,f1,f2,f3,f4,f25,f200,f8;
    		
    Image yangyu = new Image(getClass().getResourceAsStream("mousepic/yangyu.png")),
			crystalm = new Image(getClass().getResourceAsStream("mousepic/crystal.png")),
			metalm = new Image(getClass().getResourceAsStream("mousepic/metal.png")),
			darken = new Image(getClass().getResourceAsStream("mousepic/darken.png")),
			ico= new Image(getClass().getResourceAsStream("uipic/黄极灵印1.png"),256,256,false,false),
			huanyuiconi = new Image(getClass().getResourceAsStream("otherpic/sixiangicon.png")),
			huanyutitlei= new Image(getClass().getResourceAsStream("otherpic/huanyutitle.png")),
			hunyuani= new Image(getClass().getResourceAsStream("otherpic/混元灵印.png")),
			cailani = new Image(getClass().getResourceAsStream("otherpic/expr2.png")),
			hunyuanzhengjini = new Image(getClass().getResourceAsStream("otherpic/hunyuanzhengjin.png")),
			guiniui = new Image(getClass().getResourceAsStream("sealpic/龟纽.png")),
			lingjvhei = new Image(getClass().getResourceAsStream("otherpic/灵具盒.png")),
			wuxiaojiani = new Image(getClass().getResourceAsStream("otherpic/兀肖剑.png")),
			jiaochengi,helpi;
			
	Image[] zhengjins = new Image[10];

	Group guangbiaog = new Group();
	EventHandler<javafx.scene.input.KeyEvent> scenepresskey;
	EventHandler<MouseEvent> gameclick;
	static ImageView charview=new ImageView();
	Circle mousetestc1;
	public static StackPane stp=new StackPane();
	public static Pane stpane=new Pane();
	public static Pane game1=new Pane(),backp=new Pane();
    Pane galpane=new Pane(),ui=new Pane();

	static Pane lingjvpane=new Pane();
    GridPane mainBox;
	public static Group bg=new Group(),newBg=new Group();
	static Scene scene;
	VBox levelSelectBox;
	public static Pane openpane = new Pane(),dp=new Pane();
    Text startgame1text;
	ImageView yy = new ImageView(yangyu),out = new ImageView(new Image(getClass().getResourceAsStream("mousepic/mouse2.png"))),
			in = new ImageView(new Image(getClass().getResourceAsStream("mousepic/mouse1.png"))),
			help,jiaocheniv,
					guiniu = new ImageView(guiniui),lingjvhe = new ImageView(lingjvhei),
					zhengjini = new ImageView(hunyuanzhengjini),wuxiaojian = new ImageView(wuxiaojiani);
	Group zhengjin=new Group(zhengjini);
	
	private Label dialogLabel;
	static ImageView mouseiv = new ImageView(wi1);

	ImageView save,load,back,auto,jump,item,sets;
	private final Text fpsText = new Text("FPS: 0");
	static int nowdialogline=0,lastpointofview=0,nowintro=0,reviewintrof=0;
	public double volume = 0.7;      // 默认音量 70%
    double dragx=0,dragy=0;
	String nowactor,nowactorchapter;
	private Map<String, ImageView> chars = new HashMap<>();
	private Map<String, Image> charall = new HashMap<>();
	private Map<String, Group> dps = new HashMap<>();
	private static Map<Integer, String> dialoglines = new HashMap<>();
    private List<String> levelFiles = new ArrayList<>();
    boolean canhandlebookf=true;
    int playedt=0;
    int segment,finishedproject,chapter,sentence,playedtutorial,isbookshowf,isboxshowf,iswuxiaoshowf,isguishowf,language,finishedmovebox=-1;
    private List<Integer> segmentstartproject = new ArrayList<>(),unlockedproject = new ArrayList<>();
    
	static Timeline opentimeline,dialogdelay,dialogshow=new Timeline(new KeyFrame(Duration.seconds(0.2),
			new KeyValue(charview.opacityProperty(),1),new KeyValue(dp.opacityProperty(),1))),
			dialoghide=new Timeline(new KeyFrame(Duration.seconds(0.2),
				new KeyValue(charview.opacityProperty(),0),new KeyValue(dp.opacityProperty(),0))),
			charentert=new Timeline(new KeyFrame(Duration.millis(0), new KeyValue(charview.opacityProperty(),0)),
				new KeyFrame(Duration.millis(350), new KeyValue(charview.opacityProperty(),1))),
			    dialog = new Timeline(),backtl= new Timeline(),autoplay,autoplay1,autosave,intro;
	Timeline mouseanict,reviewintro;
	
	String currentLevelDir = "src/blendefine/files"; // 关卡目录
	MediaPlayer mp;
	SoundManager sm = SoundManager.getInstance();
	settingpane settingpane;
     
	Stage pstg;
	DropShadow ds8,ds9,ds5;
	GaussianBlur gausb=new GaussianBlur();
	
//	AnimatedWave1 aw;
	AshEffect ae1;
	ParticleSystem pss;
	guanka gk;
	movebox mb;
	triango tg;
	killnote kn;
	puzzlesolver pzs;
	blendefine bdf;
	i18n i18n;
	
	public void start(Stage primaryStage) {
//        params.setTransform(javafx.scene.transform.Transform.scale(1, 1));
		screenwidth= Screen.getPrimary().getBounds().getWidth();
		screenheight= Screen.getPrimary().getBounds().getHeight();
		suofang= screenheight / 1080;
		scale=0.4*suofang;
		jiaochengi= new Image(getClass().getResourceAsStream("uipic/教程纸1.png"),screenwidth,screenheight,false,false);
		helpi= new Image(getClass().getResourceAsStream("uipic/帮助1.png"),100*suofang,100*suofang,false,false);
		jiaocheniv=new ImageView(jiaochengi);
		help=new ImageView(helpi);
		ds8 = new DropShadow(20*suofang, 0,0, Color.color(1, 1, 1, 0.5));
		ds9 = new DropShadow(30*suofang, 0,0, Color.color(0, 0, 0, 1));
		ds5 = new DropShadow(30*suofang, -20*suofang,10*suofang, Color.color(0, 0, 0, 0.96));
		bdf=this;
		pstg=primaryStage;
		DropShadow ds4 = new DropShadow(58*suofang, 0,0, Color.color(0, 0, 0, 0.9));
		DropShadow ds6 = new DropShadow(20*suofang, 0,0, Color.color(0, 0, 0, 0.9));
		DropShadow ds7 = new DropShadow(5*suofang, 2*suofang,3*suofang, Color.color(0, 0, 0, 0.6));
		
		i18n=new i18n();
		
		primaryStage.getIcons().add(ico);
    	stp.getChildren().add(game1);
    	game1.setPickOnBounds(false);
    	stp.setPrefSize(screenwidth,screenheight);
    	stp.setAlignment(Pos.TOP_LEFT);
        stp.setStyle("-fx-background:transparent;");
        scene = new Scene(stp, Color.TRANSPARENT);
		scene.setOnMouseMoved(e->{
		      nowmousex=e.getX();
		      nowmousey=e.getY();
		 });
		loadSave();
		Timeline autosave=new Timeline((new KeyFrame(Duration.seconds(5),e->{
			saveSave();
		})));
		autosave.setCycleCount(Timeline.INDEFINITE);
		autosave.play();
		
        if(language==-1) {
			String langCode = Locale.getDefault().getLanguage();
			switch (langCode) {
			    case "zh":  language = 0; break;
			    case "ja":  language = 2; break;
			    case "ko":  language = 2; break;
			    case "de":  language = 4; break;
			    case "en":  language = 5; break;
			    case "es":  language = 6; break;
			    case "fr":  language = 7; break;
			    case "pt":  language = 8; break;
			    case "ru":  language = 9; break;
			    default:    language = 0; break; // 默认中文（或根据需要设为0）
			}
		}
   		loadfont();
		
		//点击事件与按键事件定义
		gameclick=e->{
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
			else if(zhengjingopenf==1&&canhandlebookf) {
				closebook();
			}
			else if(lingjvf) {
				if(null!=pzs) {
					pzs.close();
		    		Timeline tl=new Timeline(new KeyFrame(Duration.seconds(0.6),new KeyValue(pzs.stack.opacityProperty(),1)),
		    				new KeyFrame(Duration.seconds(0.4),e2->{lingjvhe.toFront();}),
				        new KeyFrame(Duration.seconds(0.75),new KeyValue(pzs.stack.opacityProperty(),0)));
		    		tl.play();
		    		
		    		tl.setOnFinished(e2->{
		    			closehe();
		    			lingjvpane.getChildren().remove(pzs.stack);
		    		});
				}
			}
			else if(settingshowf) {
				switchsetting();
			}
			else {
				processLine(dialoglines.get(nowdialogline));
				try {
					preloadLine(dialoglines.get(nowdialogline));
				} catch (Exception e2) {}
			}
		};
		scenepresskey = e -> {
			if(e.getCode().toString().equals("F11")) {
				if(pstg.isFullScreen())
					pstg.setFullScreen(false);
				else pstg.setFullScreen(true);
        	}
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
		 if(e.getCode().toString().equals("SPACE")) {
			 processLine(dialoglines.get(nowdialogline));
				try {
					preloadLine(dialoglines.get(nowdialogline));
				} catch (Exception e2) {}
			}
		 if(e.getCode().toString().equals("ESCAPE")) {
			 if(zhengjingopenf==1&&canhandlebookf) {
					closebook();
				}
			 else if(lingjvf) {
				 pzs.close();
		    		Timeline tl=new Timeline(new KeyFrame(Duration.seconds(0.6),new KeyValue(pzs.stack.opacityProperty(),1)),
		    				new KeyFrame(Duration.seconds(0.4),e2->{lingjvhe.toFront();}),
				        new KeyFrame(Duration.seconds(0.75),new KeyValue(pzs.stack.opacityProperty(),0)));
		    		tl.play();
		    		
		    		tl.setOnFinished(e2->{
		    			closehe();
		    			lingjvpane.getChildren().remove(pzs.stack);
		    		});
			 }
				else {
					switchsetting();
				}
		 }
		 if(e.getCode().toString().equals("BACK_QUOTE")) {
			 TextField tf=new TextField();
			 game1.getChildren().add(tf);
			 tf.requestFocus();
			 tf.setOnKeyPressed(e1->{
				 if(e1.getCode().toString().equals("ESCAPE")) {
					 game1.getChildren().remove(tf);
				 }
				 if(e1.getCode().toString().equals("ENTER")) {
					 finishedproject=Integer.parseInt(tf.getText());
					 game1.getChildren().remove(tf);
				 }
			 });
		 }
        };
		sn1.setFill(Color.TRANSPARENT);
		sn2.setFill(Color.TRANSPARENT);
        game1.setOpacity(0);
        Node game1title=null;
        
        DropShadow ds100=new DropShadow(100*suofang, 0,0, Color.color(0, 0, 0, 1));
        ds100.setSpread(0.7);
        if(language>0) {
	        game1title =new Text(i18n.hunyuanLingyin[language]);
	        ((Text) game1title).setFont(f200);
	        ((Shape) game1title).setFill(Color.WHITE);
	        game1title.setEffect(ds100);
	        game1title.setTranslateY(screenheight/4);
	        game1title.setTranslateX(screenwidth/2-game1title.getBoundsInLocal().getWidth()*0.5+50*suofang);
        }
        else {
        	game1title=new ImageView(hunyuani);
        	 ((ImageView) game1title).setFitWidth(798*suofang);
        	 ((ImageView) game1title).setFitHeight(256.5*suofang);
        	 game1title.setTranslateY(screenheight/8);
 	       	game1title.setTranslateX(screenwidth/2-450*suofang);
        }
       
        
        startgame1text=new Text("- "+i18n.startGame[language]+" -");
        startgame1text.setFill(Color.WHITE);
        startgame1text.setFont(f0);
        ds4.setSpread(0.88);
        startgame1text.setEffect(ds4);
        startgame1text.setTranslateY(screenheight-90*suofang);
        startgame1text.setTranslateX(screenwidth/2-166*suofang);
        
        game1.getChildren().add(galpane);
        game1.getChildren().add(lingjvpane);
        
        help.setTranslateX(70*suofang);
	    help.setTranslateY(screenheight/2-50*suofang);
	    lingjvpane.getChildren().add(help);
//        help.setOpacity(0);
	    

        help.setOnMouseEntered(e->{
        	help.setEffect(ds8);
        	dragx=e.getScreenX();
        	dragy=e.getScreenY();
        });
        help.setOnMouseMoved(e->{
        	nowmousex=e.getScreenX();
        	nowmousey=e.getScreenY();
        });
//        help.setOnMouseDragged(e->{
//        	nowmousex=e.getScreenX();
//        	nowmousey=e.getScreenY();
//        	help.setTranslateX(e.getScreenX()-50*suofang);
//        	help.setTranslateY(e.getScreenY()-50*suofang);
//        });
        help.setOnMouseExited(e->{
        	help.setEffect(null);
        });
        help.setOnMouseReleased(e->{
        	help.setEffect(null);
//        	if(e.getScreenX()-dragx<5*suofang&&e.getScreenY()-dragy<5*suofang) {
        		
        		if(playedtutorial>playedt) {
        			nowintro=0;
	        		playedtutorial--;
	        		enterintro();
	        		playedtutorial++;
	        	
	        	}
//        		else nowintro-=2;
//        	}
        });
	    
        lingjvpane.setEffect(ds5);
        lingjvpane.getChildren().add(zhengjin);
        closebook();
        lingjvpane.getChildren().add(wuxiaojian);
        wuxiaoback();
        lingjvpane.getChildren().add(lingjvhe);
        closehe();
        lingjvpane.getChildren().add(guiniu);
        closexiaoyouxi();
        //根据进度切换是否显示小游戏
        if(isbookshowf==0)zhengjin.setVisible(false);
        if(iswuxiaoshowf==0)wuxiaojian.setVisible(false);
        if(isboxshowf==0)lingjvhe.setVisible(false);
        if(isguishowf==0)guiniu.setVisible(false);
        
        game1.getChildren().add(stpane);
       
        galpane.setPickOnBounds(false);
        stpane.setPickOnBounds(false);
        lingjvpane.setPickOnBounds(false);

        Rectangle rc=new Rectangle(screenwidth,screenheight);
        rc.setFill(Color.color(0.1, 0.1, 0.1,0.1));
        stpane.getChildren().add(rc);
        stpane.getChildren().add(game1title);
        stpane.getChildren().add(startgame1text);

        game1.setOnMouseClicked(e->{
//        	System.out.println(e.getScreenX()+" "+e.getSceneY());
        	if(isopenfinishf == 2) {startmenu();}});
        scene.setOnKeyPressed(e->{
        	
        	if(e.getCode().toString().equals("F11")) {
				if(primaryStage.isFullScreen())
					primaryStage.setFullScreen(false);
				else primaryStage.setFullScreen(true);
        	}
        	else if(isopenfinishf == 2) {startmenu();}
        });
        Timeline tl=new Timeline(new KeyFrame(Duration.seconds(1.5),new KeyValue(startgame1text.opacityProperty(),0.3)),
        		new KeyFrame(Duration.seconds(3),new KeyValue(startgame1text.opacityProperty(),1,Interpolator.EASE_BOTH)));

        // 初始化UI组件

        dialogLabel = new Label() {{
        	setStyle(null);
        	setPrefWidth(screenwidth*0.6);
        	setPrefHeight(screenheight*0.2);
        	setAlignment(Pos.BASELINE_LEFT);
//            setAlignment(Pos.BASELINE_CENTER);
            setPadding(new Insets(35*suofang, 90*suofang,45*suofang, 90*suofang));
            setWrapText(true);
            setTextFill(Color.BLACK);
//             dialogLabel.setLineSpacing(nowdialogline);
            setFont(f1);
//             dialogLabel.setTextAlignment(TextAlignment.CENTER);
            setTranslateX(screenwidth*0.2);
            setTranslateY(screenheight*0.8);
//             ds6.setSpread(0.8);
            setEffect(ds7);
        }};

        dp.getChildren().add(dialogLabel);
        
        charview.setTranslateX(screenwidth*0.7-screenheight*0.8*3680/5120*0.5);
        charview.setTranslateY(screenheight*0.2);
        charview.setEffect(ds6);
        
//        galpane.setTranslateY(30);
        galpane.getChildren().add(backp);
        backp.getChildren().add(bg);
        galpane.getChildren().add(charview);
        galpane.getChildren().add(dp);
        dp.setEffect(ds6);
        dp.setPickOnBounds(false);
        
//        save=new ImageView(new Image(getClass().getResourceAsStream("uipic/保存.png"),50*suofang,50*suofang,false,false));
//        save.setTranslateY(screenheight-70*suofang); save.setTranslateX(screenwidth*0.4-25*suofang);
//        save.setOnMouseClicked(e->{
//        	e.consume();
//        });
//        load=new ImageView(new Image(getClass().getResourceAsStream("uipic/读取.png"),50*suofang,50*suofang,false,false));
//        load.setTranslateY(screenheight-70*suofang); load.setTranslateX(screenwidth*0.433-25*suofang);
//        load.setOnMouseClicked(e->{
//        	e.consume();
//        });
        back=new ImageView(new Image(getClass().getResourceAsStream("uipic/回退.png"),50*suofang,50*suofang,false,false));
        back.setTranslateY(screenheight-70*suofang); back.setTranslateX(screenwidth*0.4-25*suofang);
        back.setOnMouseClicked(e->{
        	if(nowdialogline>1) {
	        	nowdialogline-=2;
	        	try {
	        		autoplay.stop();
	        		autoplay1.stop();
	        		auto.setOpacity(1);
	        		processLine(dialoglines.get(nowdialogline));
	        		dialog.jumpTo(dialog.getTotalDuration());
	        	}catch(Exception e1) {nowdialogline++;}
        	}
        	e.consume();
        });
        auto=new ImageView(new Image(getClass().getResourceAsStream("uipic/自动.png"),50*suofang,50*suofang,false,false));
        auto.setTranslateY(screenheight-70*suofang); auto.setTranslateX(screenwidth*0.5-25*suofang);
        autoplay1=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(auto.opacityProperty(),1)),
	    		new KeyFrame(Duration.seconds(0.6),new KeyValue(auto.opacityProperty(),0.25)));
        autoplay=new Timeline(
	    		new KeyFrame(Duration.seconds(0.1),e->{if(dialog.getStatus()!=Status.RUNNING&&
	    		backtl.getStatus()!=Status.RUNNING) {
	    			try {if(dialogdelay==null||dialogdelay.getStatus()!=Status.RUNNING)
	    			dialogdelay=new Timeline(new KeyFrame(Duration.seconds(0.75),e1->{
	    				processLine(dialoglines.get(nowdialogline));
			    		try {preloadLine(dialoglines.get(nowdialogline));}
			        		 catch(Exception e2) {};
	    			}));
	    			dialogdelay.play();}catch(Exception e2) {}
	    		}}));
        auto.setOnMouseClicked(e->{
        	e.consume();
        	if(autoplay.getStatus()!=Status.RUNNING) {
        		autoplay.setCycleCount(Timeline.INDEFINITE);
        		autoplay1.setCycleCount(Timeline.INDEFINITE);
        		autoplay1.setAutoReverse(true);
        		autoplay.play();
        		autoplay1.play();
        	}
        	else {
        		autoplay.stop();
        		autoplay1.stop();
        		auto.setOpacity(1);
        	}
        });
        jump=new ImageView(new Image(getClass().getResourceAsStream("uipic/跳过.png"),50*suofang,50*suofang,false,false));
        jump.setTranslateY(screenheight-70*suofang); jump.setTranslateX(screenwidth*0.6-25*suofang);
        jump.setOnMouseClicked(e->{
        	nowdialogline=dialoglines.size();
        		processLine(dialoglines.get(nowdialogline));
        	e.consume();
        });
//        item=new ImageView(new Image(getClass().getResourceAsStream("uipic/背包.png"),50*suofang,50*suofang,false,false));
//        item.setTranslateY(screenheight-70*suofang); item.setTranslateX(screenwidth*0.566-25*suofang);
//        item.setOnMousePressed(e->{
//        	e.consume();
//        });
//        sets=new ImageView(new Image(getClass().getResourceAsStream("uipic/设置.png"),50*suofang,50*suofang,false,false));
//        sets.setTranslateY(screenheight-70*suofang); sets.setTranslateX(screenwidth*0.4-25*suofang);
//        sets.setOnMouseClicked(e->{
//        	e.consume();
//        });
      
        dp.getChildren().addAll(back,auto,jump);
        dp.setOpacity(0);
        
    	for(int i=0;i<5;i++) {
    		zhengjins[i]=new Image(getClass().getResourceAsStream("otherpic/hunyuanzhengjiny"+i+".png"));
    	}

        loadScript("src/blendefine/files/script0.txt",0);
        
        tl.setAutoReverse(true);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
//设置透明GPU性能消耗巨大
        
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setFullScreenExitHint("");
//		primaryStage.centerOnScreen();
    	primaryStage.setTitle(i18n.hunyuanLingyin[language]);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
//        primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
		primaryStage.requestFocus();
		

		InputContext inputcontext=InputContext.getInstance();
//		Locale[] la=Locale.getAvailableLocales();
		String lang=inputcontext.getLocale().toString();
		String lang1=lang.substring(0,2);
		//注意，robot的事件能被监听到
		if(!lang1.equals("en")) {//如果为中文输入法
			  try {
			        Robot robot = new Robot();
			        // 模拟按下Ctrl+空格（Windows常见切换键）
			        robot.keyPress(KeyEvent.VK_CONTROL);
			        robot.keyPress(KeyEvent.VK_SPACE);
			        robot.keyRelease(KeyEvent.VK_SPACE);
			        robot.keyRelease(KeyEvent.VK_CONTROL);
			    } catch (Exception e) {}
		}
//		for(int i=0;i<la.length;i++) {
//			System.out.println(la[i]);
//		}
//    	inputcontext.selectInputMethod(la[la.length-1]);
//    	System.out.println();
		
		loadLevelFiles();
		
		preloadLine(dialoglines.get(nowdialogline));
		processLine(dialoglines.get(nowdialogline));
		preloadLine(dialoglines.get(nowdialogline));
		scene.setOnMouseReleased(e -> {
			if (isopenfinishf == 0) {
				game1.setVisible(true);
				isopenfinishf = 2;
				stp.getChildren().remove(openpane);
				opentimeline.jumpTo(Duration.seconds(7));
			}
		});
		
//		ImageView huanyutitle = new ImageView(huanyutitlei);
		ImageView huanyuicon = new ImageView(huanyuiconi);

//		byte[] bytes;
//		try {
//			bytes = Files.readAllBytes(Paths.get("src/blendefine/huanyutitle.txt"));
//			String content = new String(bytes, StandardCharsets.UTF_8);
//			huanyutitle.setContent(content);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//        openpane.setMouseTransparent(true);   
		
		Rectangle rc1=new Rectangle(screenwidth,screenheight);
        rc1.setFill(Color.color(0.1, 0.1, 0.1,0.1));
        openpane.getChildren().add(rc1);
        huanyuicon.setScaleX(screenheight/2000);
        huanyuicon.setScaleY(screenheight/2000);
        huanyuicon.setTranslateX(screenwidth/2-650*suofang-900/2*(1-screenheight/2000));
        huanyuicon.setTranslateY(screenheight/2-350*suofang-900/2*(1-screenheight/2000));
        openpane.getChildren().add(huanyuicon);
//        huanyutitle.setScaleX(screenheight/4000);
//        huanyutitle.setScaleY(screenheight/4000);
//        huanyutitle.setTranslateX(screenwidth/2-1355*suofang/2-900*(1-screenheight/4000));
//        huanyutitle.setTranslateY(screenheight/2-145*suofang/2-50*(1-screenheight/4000));
//		openpane.getChildren().add(huanyutitle);
		openpane.setPrefSize(screenwidth,screenheight+50);
		ImageView cailan = new ImageView(cailani);
		cailan.setScaleX(screenheight/1600);
		cailan.setScaleY(screenheight/1600);
		cailan.setTranslateX(screenwidth/2-1061/2*(1-screenheight/1600)-50*suofang);
		cailan.setTranslateY(screenheight/2-420/2*(1-screenheight/1600)-200*suofang);
        openpane.getChildren().add(cailan);
//        Label jiankang=new Label("抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防受骗上当。\r\n适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。");
//        jiankang.setFont(f1);
//        jiankang.setPrefWidth(screenwidth);
//        jiankang.setTranslateY(screenheight-260*suofang);
//        jiankang.setAlignment(Pos.BASELINE_CENTER);
//        jiankang.setTextAlignment(TextAlignment.RIGHT);
//        jiankang.setTextFill(Color.BLACK);
//        openpane.getChildren().add(jiankang);   
        Label xugou=new Label(i18n.opentip[language][0]);
        xugou.setFont(f25);
        xugou.setPrefWidth(screenwidth);
        xugou.setTranslateY(screenheight-150*suofang);
        xugou.setAlignment(Pos.BASELINE_CENTER);
        xugou.setTextAlignment(TextAlignment.RIGHT);
        xugou.setTextFill(Color.BLACK);
        xugou.setEffect(ds8);
        openpane.getChildren().add(xugou);
        Label guangmin=new Label(i18n.opentip[language][1]);
        guangmin.setFont(f25);
        guangmin.setPrefWidth(screenwidth);
        guangmin.setTranslateY(screenheight-100*suofang);
        guangmin.setAlignment(Pos.BASELINE_CENTER);
        guangmin.setTextAlignment(TextAlignment.RIGHT);
        guangmin.setTextFill(Color.BLACK);
        guangmin.setEffect(ds8);
        openpane.getChildren().add(guangmin);
        Label Javafx=new Label(i18n.opentip[language][2]);
        Javafx.setFont(f1);
        Javafx.setPrefWidth(screenwidth);
        Javafx.setTranslateY(screenheight-60*suofang);
        Javafx.setAlignment(Pos.TOP_CENTER);
        Javafx.setTextAlignment(TextAlignment.RIGHT);
        Javafx.setTextFill(Color.BLACK);
        Javafx.setEffect(ds8);
        openpane.getChildren().add(Javafx);
		stp.getChildren().add(openpane);
		
		ds8.setSpread(0.87);
		
		//开屏背景色
		openpane.setBackground(new Background(new BackgroundFill(new Color(0.6,0.6,0.6,0), null, null)));
//		Image w1=imgmake.pixeler(openpane,3);
//		w1=imgmake.sharpenimage(w1);
//		openpane.getChildren().clear();
//		ImageView openwv1=new ImageView(w1);
//		openpane.getChildren().add(openwv1);		
		opentimeline = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(openpane.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(0.1),e->{openpane.requestFocus();}),
				new KeyFrame(Duration.seconds(1),new KeyValue(openpane.opacityProperty(),1)),
				new KeyFrame(Duration.seconds(7.2),e->{
					isopenfinishf=1;
					game1.setVisible(true);
					},new KeyValue(openpane.opacityProperty(),1),new KeyValue(game1.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(9.2),new KeyValue(openpane.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(9.4),new KeyValue(game1.opacityProperty(),1)));  
		opentimeline.setOnFinished(e->{
			isopenfinishf=2;
			stp.getChildren().remove(openpane);
		});
		opentimeline.play();	
		openpane.setOnKeyReleased(e->{
			if (isopenfinishf == 0) {
				game1.setVisible(true);
				isopenfinishf = 2;
				stp.getChildren().remove(openpane);
				opentimeline.jumpTo(Duration.seconds(7));
			}
		});
		
	    fpsText.setFill(Color.RED);
	    fpsText.setFont(new Font(20));
	    fpsText.setTranslateX(25*suofang);
	    fpsText.setTranslateY(25*suofang);
	    stp.getChildren().add(fpsText);
	    fpsText.setVisible(fpsshowf);
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
	    Timeline fpscount = new Timeline(new KeyFrame(Duration.millis(8), e->{
	    if(fpsshowf) {
			fpsText.setText("FPS:" + lastframecount+" 坐标x"+nowmousex+" 坐标y"+nowmousey);
			fpsText.toFront();
	    }
		else fpsText.setText(null);
	    }));
	    fpscount.setCycleCount(Timeline.INDEFINITE);
	    fpscount.play();
	   
	    game1.getChildren().add(ui);
	    ui.setPickOnBounds(false);
	    
		mouseiv.setMouseTransparent(true);
    	mouseiv.setEffect( new DropShadow());
		stp.getChildren().add(mouseiv);
        mousetestc1=new Circle(0,0,1);
		wi2=mousetestc1.snapshot(null, null);
		wi2.getPixelWriter().setColor(0, 0, Color.color(1,1,1,0.1));
		wi2.getPixelWriter().setColor(0, 1, Color.TRANSPARENT);
		wi2.getPixelWriter().setColor(1, 0, Color.TRANSPARENT);
		wi2.getPixelWriter().setColor(1,1, Color.TRANSPARENT);
		out.setScaleX(0.08*suofang);
		out.setScaleY(0.08*suofang);
		in.setScaleX(0.08*suofang);
		in.setScaleY(0.08*suofang);
		out.setTranslateX(-250);
		out.setTranslateY(-250);
		in.setTranslateX(-190);
		in.setTranslateY(-190);
		yy.setTranslateX(-149);
		yy.setTranslateY(-150);
		yy.setScaleX(0.08*suofang);
		yy.setScaleY(0.08*suofang);

		sn1.setViewport(new Rectangle2D(-40, -30,70,60));//防止方形截图旋转导致的斜向移动
		guangbiaog.getChildren().add(out);
		guangbiaog.getChildren().add(in);
		guangbiaog.getChildren().add(yy);
		guangbiaog.setScaleX(0.9);
		guangbiaog.setRotate(50);
		//鼠标动画
				if(shubiaof) {
				Timeline t1 = new Timeline(new KeyFrame(Duration.millis(16), e->{
					
					if (mouserollcount > 60) {
						mouserollcount=0;				
						mouserollspeed1=Math.random()*7+0.3;
						mouserollspeed2=Math.random()*7+0.3;
						mouserollspeed3=Math.random()*7+0.3;
					}
					else
						mouserollcount+=1;
					if (mouserotate1 > 360)
						mouserotate1 = 0;
					else
						mouserotate1+=mouserollspeed1;
					if (mouserotate2 > 360)
						mouserotate2 = 0;
					else
						mouserotate2+=mouserollspeed2;
					if (mouserotate3 > 360)
						mouserotate3 = 0;
					else
						mouserotate3+=mouserollspeed3;	
					out.setRotate(mouserotate1);
					in.setRotate(-mouserotate2);
					yy.setRotate(mouserotate3);

					Platform.runLater(() -> {
					   // 交换缓冲区
					wi1=guangbiaog.snapshot(sn1, CURSOR_BUFFER);
					if(mousetype==0)for (int i = 0; i <18; i++) {//精准指针
						for (int j = 0; j <24; j++) {
							Color c=crystalm.getPixelReader().getColor(j, i);
							wi1.getPixelWriter().setColor(j, i,c);
						}
					}
					if(mousetype==1)for (int i = 0; i <18; i++) {//选择指针
						for (int j = 0; j <24; j++) {
							Color c=metalm.getPixelReader().getColor(j, i);
							wi1.getPixelWriter().setColor(j, i,c);
						}
					}
					if(mousetype==2)//纱布指针
					for (int i = 0; i < 18; i++) {
						for (int j = 0; j < 24; j++) {
							if (i + j*0.8 < 22&&(i+j*0.8)%3!=0&&(i+(j-1)*0.8)%3!=0&&(i+(j-2)*0.8)%3!=0&&(i+(j-3)*0.8)%3!=0&&(i<6||j<9)) {
								double c=1-((double)i+(double)j)/26;
								wi1.getPixelWriter().setColor(j, i, Color.color(c,c,c));
							}
						}
					}
					
					for(int i = 18; i < wi1.getHeight(); i++) {
						for(int j = 24; j < wi1.getWidth(); j++) {
							int c1=wi1.getPixelReader().getArgb(j, i),c2=darken.getPixelReader().getArgb((int)((j*14)/screenheight*screenheight), (int)((i*14+80)/screenheight*screenheight));
							int a1 = (c1 >> 24) & 0xFF;
							int a2 = (c2 >> 24) & 0xFF;
							double c3=1-(double)a1*(double)a2/256/256;
							wi1.getPixelWriter().setColor(j, i, Color.TRANSPARENT);
							
							if(a1>200)
								wi1.getPixelWriter().setColor(j, i, Color.color(c3,c3,c3));
						}
					}
					});
					ImageCursor icursor1=new ImageCursor(wi2);
					scene.setCursor(icursor1);

				}));
				t1.setCycleCount(Timeline.INDEFINITE);
				t1.setAutoReverse(true);
				t1.play();
				
				mouseanict = new Timeline(new KeyFrame(Duration.millis(1),e->{
					mouseiv.setTranslateX(nowmousex);
					mouseiv.setTranslateY(nowmousey);
				}));
				
				mouseanict.setCycleCount(Timeline.INDEFINITE);
				mouseanict.play();
				}

				//TODO 初始化所有音效
//				sm.load("shixubo","");
    }
    public void enterguanka(int level, String[] target, HashSet<String> banlist,
            HashMap<String, Integer> baoshis,puzzlesolver pzs) {
		gk = new guanka();
		gk.inistage(level,target, banlist, baoshis, pzs,language,i18n);
		gk.bdf = this;
		this.pzs.bdf=null;
		this.pzs.gk=gk;
		this.pzs=null;
		playMusic("src/blendefine/music/Processyin.mp3");
		mp.setVolume(volume);
		mouseanict.stop();
		try {ae1.animationTimer.stop();}catch(Exception e) {}
		try {pss.animationTimer.stop();}catch(Exception e) {}
    }
	public void clearguanka() {
		if(gk!=null) {
			gk=null;
			lingjvpane.getChildren().remove(lingjvhe);
			lingjvhe = new ImageView(lingjvhei);
			lingjvpane.getChildren().add(lingjvhe);
	        closehe();
		}
		game1.setOnMouseClicked(gameclick);
		game1.getChildren().remove(stpane);
		scene.setOnKeyPressed(scenepresskey);
		playMusic("src/blendefine/music/监督下的天师.mp3");
        mp.setVolume(volume);
        scene.setOnMouseMoved(e->{
        	nowmousex=e.getX();
        	nowmousey=e.getY();
        });
		mouseanict.play();	
//		processLine(dialoglines.get(nowdialogline));
	}
	public void startmenu() {
		game1.setOnMouseClicked(null);
		scene.setOnKeyPressed(null);
		Timeline tl1=new Timeline(
        		new KeyFrame(Duration.seconds(0.4),new KeyValue(stpane.opacityProperty(),0),
        				new KeyValue(startgame1text.scaleXProperty(),1.3),new KeyValue(startgame1text.scaleYProperty(),1.3)));
		tl1.setOnFinished(e->{
			game1.getChildren().remove(stpane);
		});
		tl1.play();
		if(guankaf) {
			enterguanka(1,null,null,null,pzs);
		}
		else if(moveboxf) {
			mb = new movebox();
			mb.inistage();
			mb.bdf = this;
//			try {aw.timer.stop();}catch(Exception e) {}
			try {ae1.animationTimer.stop();}catch(Exception e) {}
			try {pss.animationTimer.stop();}catch(Exception e) {}
		}
		else if(triangof) {
			tg = new triango();
			tg.inistage();
			tg.bdf = this;
//			try {aw.timer.stop();}catch(Exception e) {}
			try {ae1.animationTimer.stop();}catch(Exception e) {}
			try {pss.animationTimer.stop();}catch(Exception e) {}
		}
		else if(isopenfinishf==2){
			game1.setOnMouseClicked(gameclick);
			scene.setOnKeyPressed(scenepresskey);
		}			
	}

//	private Label createDialog() {
//        Label label = new Label();
//        label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); " +
//                      "-fx-padding: 10; " +
//                      "-fx-border-radius: 5; " +
//                      "-fx-background-radius: 5;");
//        label.setFont(Font.font("SimHei", 20));
//        label.setTextFill(Color.WHITE);
//        label.setTranslateY(250);
//        return label;
//    }
	 
    void loadScript(String path, int jump) {
    	 try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
    		for(int j=0;j<jump;j++)br.readLine();
            String line;
            int i=0;
            dialoglines.clear();
            while ((line = br.readLine()) != null) {
            	dialoglines.put(i, line);
            	i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
    	if(dialog.getStatus()==Status.RUNNING) {
    		dialog.jumpTo(dialog.getTotalDuration());
    	}//跳过文字加载
    	else if(backtl.getStatus()==Status.RUNNING) {
    		backtl.jumpTo(backtl.getTotalDuration());
    	}//跳过背景加载
    	else {
    		if(null==line) {
    			Timeline t=new Timeline(new KeyFrame(Duration.seconds(1),
    					new KeyValue(dp.opacityProperty(),0)));
    			t.setOnFinished(e->{
    				dp.setVisible(false);
    			});
    			t.play();
    		}
    		else if (line.equals("显示按钮")) {
    			auto.setVisible(true);
    			back.setVisible(true);
    			jump.setVisible(true);
    			sets.setVisible(true);
    			auto.setOpacity(0);
    			back.setOpacity(0);
    			jump.setOpacity(0);
    			sets.setOpacity(0);
    			Timeline t=new Timeline(new KeyFrame(Duration.seconds(1),
    					new KeyValue(auto.opacityProperty(),1),
    					new KeyValue(back.opacityProperty(),1),
    					new KeyValue(jump.opacityProperty(),1),
    					new KeyValue(sets.opacityProperty(),1)
    					));
    			t.play();
    			nowdialogline++;
                processLine(dialoglines.get(nowdialogline));
    		}
    		else if (line.startsWith("背景切换")) {
	            String bgName = line.substring(4);
	            switchBackground(bgName);
	        } else if (line.contains("说")) {
	            String[] parts = line.split("说", 2);
	            if(line.contains("（"))showCharacter(parts[0].trim(),1);
	            else if(line.contains("【"))showCharacter(parts[0].trim(),2);
	            else showCharacter(parts[0].trim(),0);
	            showDialog(parts[1].trim());
	        }
	        nowdialogline++;
    	}
    }
    private void preloadLine(String line) {
    	try {
    	if (line.startsWith("播放")) {
            String bgName = line.substring(2);
            playMusic("src/blendefine/music/"+bgName+".mp3");
            mp.setVolume(volume);
            nowdialogline++;
            preloadLine(dialoglines.get(nowdialogline));
        } 
    	if (line.equals("隐藏按钮")) {
    		auto.setVisible(false);
			back.setVisible(false);
			jump.setVisible(false);
			sets.setVisible(false);
    		 nowdialogline++;
             preloadLine(dialoglines.get(nowdialogline));
		}
    	if (line.startsWith("背景切换")) {
            String bgName = line.substring(4);
            loadBackground(bgName);
        } 
    	else if (line.contains("说")) {
            String[] parts = line.split("说", 2);
            loadCharacter(parts[0].trim());
//            showDialog(parts[1].trim());
        }
    	}catch(Exception e1) {}
    }
    public void charenter(Image i,String name) {
    	if(!name.equals(nowactor)) {
	    	charview.setOpacity(0);
	    	charview.setImage(i);
	    	Platform.runLater(()->{//防止图片加载前调用导致无响应然后延迟触发造成闪现
		    	charentert.play();
		    	TranslateTransition inTransition = new TranslateTransition(Duration.millis(300), charview);
				inTransition.setToX(screenwidth*0.7-screenheight*0.8*3680/5120*0.5);
				inTransition.setInterpolator(Interpolator.EASE_BOTH);
				inTransition.setFromX(screenwidth*0.7-screenheight*0.8*3380/5120*0.5);
				inTransition.play();
	    	});
    	}
    }
    private void loadCharacter(String charName) {
    	Thread t1 = new Thread(new Runnable() {
    	      public void run() {
    	        try {
//    	        	Thread.sleep(1500);
    	        	if(!charall.containsKey(charName)) {
    	        		Group nowchar=new Group();
				    	String[] ns=charName.split(" ");
				        for(int i=0;i<ns.length;i++) {
				        	if(i>0) ns[i]=ns[0]+" "+ns[i];
				        	 if(!chars.containsKey(ns[i])){
				//        		System.out.println(ns[i]);
				 	        	ImageView im=new ImageView(new Image(getClass().getResourceAsStream("uipic/man/"+ns[i]+".png")));
				 	        	im.setFitWidth(screenheight*0.8*im.getBoundsInLocal().getWidth()/im.getBoundsInLocal().getHeight());
				 	        	im.setFitHeight(screenheight*0.8);
				 	        	nowchar.getChildren().add(im);
				 	        	chars.put(ns[i],im);
				 	        }
				        }
				        Image ci=nowchar.snapshot(sn2, null);
				        charenter(ci,charName);
				        charall.put(charName,ci);
    	        	}
    	        } catch (Exception e) {}
    	      }
    	    });
    	    t1.start();
    }
    private void loadBackground(String bgName) {
    	newBg= new Group();
//    	newBg.getChildren().clear();
    	Thread t1 = new Thread(new Runnable() {
  	      public void run() {
  	        try {
//        if(bgName.equals("泡水")) {
//        	fxf=1;
//        	        	Image im=new Image(getClass().getResourceAsStream("uipic/back/泡水.png"),screenwidth/2,screenwidth*3680/5120/2,false,false);
//        	        	aw=new AnimatedWave1((int)screenwidth/2, (int)screenwidth*3680/5120/2,im,2,4);
//        	        	aw.setScaleX(2.1);
//        	        	aw.setScaleX(2.1);
//        	        	aw.setScaleY(2.1);
//        	        	aw.setTranslateX(screenwidth/4);
//        	        	aw.setTranslateY(screenheight/4);
//        	        	Platform.runLater(()->{
//        	        		newBg.getChildren().add(aw);
//        	        	});
//        }
//        else {
//            fxf=0;
	        //使用背景时 室内：一张图层，室内 1：室内和室内 1
	        String[] ns=bgName.split(" ");
	        for(int i=0;i<ns.length;i++) {
	        	if(i>0) ns[i]=ns[0]+" "+ns[i];
	        	ImageView im;

		        	if(screenwidth/screenheight<=16/9) {
			        	im=new ImageView(new Image(getClass().getResourceAsStream("uipic/back/"+ns[i]+".png")));
//			        	im.setTranslateX((screenwidth-screenheight/9*16)/2);
			        	im.setTranslateY((screenheight-screenwidth/16*9)/2);
			        	
			        	Platform.runLater(()->{
			        		newBg.getChildren().add(im);
			        	});
			        	im.setFitHeight(screenwidth/16*9);
			        	im.setFitWidth(screenwidth);
		        	}
		        	else {
		        		im=new ImageView(new Image(getClass().getResourceAsStream("uipic/back/"+ns[i]+".png")));
			        	Platform.runLater(()->{
			        		newBg.getChildren().add(im);
			        	});
			        	
			        	im.setFitHeight(screenheight);
			        	im.setFitWidth(screenheight/9*16);
		        	}
//	        	im.setPickOnBounds(false);
	        	if(ns[i].contains("室内 ")) {
	        		if(ns[i].contains("真经")&&isbookshowf==1||ns[i].contains("盒")&&isboxshowf==1||
	        				ns[i].contains("兀肖")&&iswuxiaoshowf==1||ns[i].contains("龟")&&isguishowf==1) {
	        			im.setVisible(false);
	        			im.setOpacity(0);
	        		}
	        		else {
	        			if(ns[i].equals("室内 真经")) 
	        				im.setOnMouseClicked(e->{
	        					e.consume();
	        					isbookshowf=1;
	        					zhengjin.setOpacity(0);
	        					zhengjin.setVisible(true);
	        					Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.1),
        								new KeyValue(zhengjin.opacityProperty(),1),
        								new KeyValue(im.opacityProperty(),0)));
        						t.play();
        						t.setOnFinished(e1->{
        							newBg.getChildren().remove(im);
        						});
	        				});
	        			else if(ns[i].equals("室内 盒")) 
	        				im.setOnMouseClicked(e->{
	        					e.consume();
	        					isboxshowf=1;
	        					lingjvhe.setOpacity(0);
	        					lingjvhe.setVisible(true);
	        					Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.1),
        								new KeyValue(lingjvhe.opacityProperty(),1),
        								new KeyValue(im.opacityProperty(),0)));
        						t.play();
        						t.setOnFinished(e1->{
        							newBg.getChildren().remove(im);
        						});
	        				});
	        			else if(ns[i].equals("室内 兀肖")) 
	        				im.setOnMouseClicked(e->{
	        					e.consume();
	        					iswuxiaoshowf=1;
	        					wuxiaojian.setOpacity(0);
	        					wuxiaojian.setVisible(true);
	        					Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.1),
        								new KeyValue(wuxiaojian.opacityProperty(),1),
        								new KeyValue(im.opacityProperty(),0)));
        						t.play();
        						t.setOnFinished(e1->{
        							newBg.getChildren().remove(im);
        						});
	        				});
	        			else if(ns[i].equals("室内 龟")) 
	        				im.setOnMouseClicked(e->{
	        					e.consume();
	        					isguishowf=1;
	        					guiniu.setOpacity(0);
	        					guiniu.setVisible(true);
	        					Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.1),
        								new KeyValue(guiniu.opacityProperty(),1),
        								new KeyValue(im.opacityProperty(),0)));
        						t.play();
        						t.setOnFinished(e1->{
        							newBg.getChildren().remove(im);
        						});
	        				});
	        			else if(ns[i].equals("室内 板"))
	        	        	im.setOnMouseClicked(e->{
	        	        		e.consume();
	        	        		switchsetting();
	        	        	});
	        			
		        		Timeline t = new Timeline(new KeyFrame(Duration.seconds(0),
								new KeyValue(im.opacityProperty(),0.2)),
		        				new KeyFrame(Duration.seconds(0.5),
								new KeyValue(im.opacityProperty(),1)));
		        		t.setAutoReverse(true);
		        		t.setCycleCount(Timeline.INDEFINITE);
		        		if(!ns[i].equals("室内 板")||playedtutorial==0) t.play();
		        		im.setOnMouseEntered(e->{
		        			t.stop();
		        			im.setOpacity(1);
		        			im.setEffect(ds8);
		        		});
		        		im.setOnMouseExited(e->{
		        			im.setEffect(null);
		        		});
	        		}
	        	}
	        	
//	        }
	        if(ns[0].equals("室内")) {
//	        	fxf=1;
	        	 ae1=new AshEffect((int)screenwidth,(int)screenheight,(int)(screenheight*5120/2880*1.1),(int)(screenheight*0.4),10,1000,220);
	             Polygon g1=new Polygon(screenwidth/2+0.15*screenheight,0.44*screenheight,screenwidth/2+0.05*screenheight,0.7*screenheight,
	             		screenwidth/2+0.13*screenheight,screenheight,screenwidth/2+screenheight*5120/2888/2,screenheight/2 ,screenwidth/2+screenheight*5120/2888/2,screenheight*0.15);
	             ae1.setClip(g1);
	             ae1.setMouseTransparent(true);
	             Platform.runLater(()->{newBg.getChildren().add(ae1);});
	        }
	        if(ns[0].equals("混元")||ns[0].equals("四象")) {
	        	pss=new ParticleSystem((int)screenheight,(int)screenheight,0,0,3000,2);
	        	pss.setTranslateX((screenwidth-screenheight)/2);
	        	pss.setTranslateY(screenheight*0.1);
//	        	GaussianBlur gb=new GaussianBlur(4);

	        	Blend sx=new Blend();
				sx.setMode(BlendMode.SRC_ATOP);
				sx.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("uipic/back/四象.png"),
						screenheight/1080*1920,screenheight,false,false),-(screenwidth-screenheight)/2,-screenheight*0.1));
				pss.setEffect(sx);
	            Platform.runLater(()->{newBg.getChildren().add(pss);});
	        }
        }  

  	      } catch (Exception e) {}
	      }
	    });
	    t1.start();
    }
    private void switchBackground(String bgName) {
    	dialoghide.play();
//        newBg= new Group();
        if(bgName.equals("泡水")) {
        	fxf=1;
//        	Image im=new Image(getClass().getResourceAsStream("uipic/back/泡水.png"),screenwidth/2,screenwidth*3680/5120/2,false,false);
//        	aw=new AnimatedWave1((int)screenwidth/2, (int)screenheight/2,im,2,3);
//        	aw.setScaleX(2.1);
//        	aw.setScaleY(2.1);
//        	aw.setTranslateX(screenwidth/4);
//        	aw.setTranslateY(screenheight/4);
//        	newBg.getChildren().add(aw);
        }
        else {
            fxf=0;
//	        //使用背景时 室内：一张图层，室内 1：室内和室内 1
	        String[] ns=bgName.split(" ");
//	        for(int i=0;i<ns.length;i++) {
//	        	if(i>0) ns[i]=ns[0]+" "+ns[i];
//		        if(bgs.containsKey(ns[i]))
//		        	newBg.getChildren().add(bgs.get(ns[i]));
//		        else {
//		        	ImageView im=new ImageView(new Image(getClass().getResourceAsStream("uipic/back/"+ns[i]+".png"),screenwidth,screenheight,false,false));
//		        	newBg.getChildren().add(im);
//		        	bgs.put(ns[i],im);
//		        }
//	        }
	        if(ns[0].equals("室内")) {
	        	fxf=1;
//	        	 ae1=new AshEffect((int)screenwidth,(int)screenheight,(int)(screenheight*5120/2888*0.95),(int)(screenheight*0.4),10,1000,220);
//	        	 Polygon g1=new Polygon(screenwidth/2+0.15*screenheight,0.44*screenheight,screenwidth/2+0.05*screenheight,0.7*screenheight,
//	             		screenwidth/2+0.13*screenheight,screenheight,screenwidth/2+screenheight*5120/2888/2,screenheight/2 ,screenwidth/2+screenheight*5120/2888/2,screenheight*0.15);
//	             ae1.setClip(g1);
//	             newBg.getChildren().add(ae1);
	        }
	        if(ns[0].equals("混元")||ns[0].equals("四象")) {
	        	fxf=2;
	        }
        }
        // 创建裁剪动画
//        GaussianBlur gb=new GaussianBlur();
//        gb.setRadius(50);
//        newBg.setEffect(gb);
        backp.getChildren().add(1, newBg); // 添加到最底层

        
    	int size=80;
		int h=(int) (screenheight/size)+1,w=(int) (screenwidth/size)+1;
		double th=Math.sqrt(3),ht=th*size;
		Group g=new Group();
		Polygon[][] ps=new Polygon[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if((i+j)%2==0) {
					ps[i][j]=new Polygon(-size-2,0,size+2,0,0,ht+1);
					ps[i][j].setTranslateY(ht*i);
					ps[i][j].getTransforms().add(new Rotate(0,0,ht/3));
				}
				else {
					ps[i][j]=new Polygon(0,-1,size+2,ht,-size-2,ht);
					ps[i][j].setTranslateY(ht*i+ht/3);
					ps[i][j].getTransforms().add(new Rotate(0,0,ht*2/3));
				}
				ps[i][j].setFill(Color.GRAY);
				ps[i][j].setTranslateX(size*j);
				
				ps[i][j].setScaleX(0);
				ps[i][j].setScaleY(0);
				g.getChildren().add(ps[i][j]);
				
				RotateTransition rt=new RotateTransition(Duration.millis(300),ps[i][j]);
				rt.setByAngle(180); 
				rt.setDelay(Duration.millis(50*j));
				rt.play();
				
				TranslateTransition tt=new TranslateTransition(Duration.millis(300),ps[i][j]);
				if((i+j)%2==1) {
					tt.setToY(ht*i); 
				}
				tt.setDelay(Duration.millis(50*j));
				tt.play();
				
				ScaleTransition st = new ScaleTransition(Duration.millis(300), ps[i][j]);
				st.setByX(1);
				st.setByY(1);
				st.setDelay(Duration.millis(50*j));
//				st.setCycleCount(2);
//				st.setAutoReverse(true);
				st.play();
				
			}
		}

		newBg.setClip(g);
		
	    backtl = new Timeline(
	    new KeyFrame(Duration.seconds(1.6))
	    );
		
        backtl.setOnFinished(e -> {
        	newBg.setClip(null);
        	if(fxf==0) {//当前不在特效页
//	        	if (aw!=null) {
//	        		System.out.print(2);
//	        		newBg.getChildren().remove(aw);
//	        		aw.destroy();
//	        		Timeline t = new Timeline(new KeyFrame(Duration.seconds(1),e1->{
//	        			aw=null;
//	        		}));
//	        		t.play();
//	        	}
	        	if(ae1!=null) {
	        		newBg.getChildren().remove(ae1);
	        		ae1.destroy();
	        		Timeline t = new Timeline(new KeyFrame(Duration.seconds(1),e1->{
	        			ae1=null;
	        		}));
	        		t.play();
	        	}
	        	if(pss!=null) {
	        		newBg.getChildren().remove(pss);
	        		pss.destroy();
	        		Timeline t = new Timeline(new KeyFrame(Duration.seconds(1),e1->{
	        			pss=null;
	        		}));
	        		t.play();
	        	}
        	}
        	backp.getChildren().remove(bg);
        	bg = newBg;
        });

        backtl.play();
    }

    private void showCharacter(String charName, int j) {
    	if(!charName.equals(nowactor)||j!=lastpointofview) {
    		lastpointofview=j;
    	if(charall.containsKey(charName)) {
    		 charenter(charall.get(charName),charName);
    	}
    	else {
	        String[] ns=charName.split(" ");
	        Group nowchar=new Group();
//	        charview.getChildren().clear();
	        for(int i=0;i<ns.length;i++) {
	        	if(i>0) ns[i]=ns[0]+" "+ns[i];
	        	 if(chars.containsKey(ns[i]))
	        		 nowchar.getChildren().add(chars.get(ns[i]));
	        	 else {
//	        		System.out.print(ns[i]);
	 	        	ImageView im=new ImageView(new Image(getClass().getResourceAsStream("uipic/man/"+ns[i]+".png")));
	 	        	im.setFitWidth(screenheight*0.8*im.getBoundsInLocal().getWidth()/im.getBoundsInLocal().getHeight());
	 	        	im.setFitHeight(screenheight*0.8);
	 	        	nowchar.getChildren().add(im);
	 	        	chars.put(ns[i],im);
	 	        }
	        }
	        Image ci=nowchar.snapshot(sn2, null);
	        charenter(ci,charName);
	        charall.put(charName,ci);
    	}
	        if(dp.getChildren().get(0).getClass().toString().equals("class javafx.scene.Group")) {
        		dp.getChildren().remove(0);
        	}
	        if(charName.contains("蒲月")) {
	        	Group g=null;
	        	if(dps.containsKey("蒲月")){
	        		g=dps.get("蒲月");
	        	}
	        	else {
	        		g=new Group();
		        	Image yunb=new Image(getClass().getResourceAsStream("uipic/云2.png"),screenwidth*0.6,screenheight*0.2,false,false);
		            ImageView dialogback=new ImageView(yunb);
		            dialogback.setOpacity(0.82);
		            dialogback.setTranslateX(screenwidth*0.2);
		            dialogback.setTranslateY(screenheight*0.8);
		            Image yue=new Image(getClass().getResourceAsStream("uipic/月.png"),150*suofang,150*suofang,false,false);
		            ImageView yueliang=new ImageView(yue);
		            if(nowactorchapter==null) {
		            	yueliang.setOpacity(0.5);
		            }
		            yueliang.setTranslateX(screenwidth*0.74);
		            yueliang.setTranslateY(screenheight*0.8);
		            g.getChildren().addAll(dialogback,yueliang);
		            dps.put("蒲月",g);
	        	}
	            dp.getChildren().add(0,g);
	        }
	        else if(charName.equals("我")) {
	        	Group g=null;
	        	if(dps.containsKey("我")){
	        		g=dps.get("我");
	        	}
	        	else {
	        		g=new Group();
	        		Rectangle r=new Rectangle(screenwidth*0.15,screenheight*0.8,screenwidth*0.7,screenheight*0.18);
	        		 RadialGradient gradient = new RadialGradient(
	        	                0, 0,                       // 焦点角度和距离
	        	                screenwidth*0.5,screenheight*0.9,                   // 焦点位置（中心）
	        	                screenwidth*0.3,               // 半径
	        	                false,                       // 按比例缩放
	        	                CycleMethod.NO_CYCLE,       // 循环方式
	        	                new Stop(0, Color.color(1,1,1,0.81)),
	        	                new Stop(0.86, Color.color(0.9,0.9,0.9,0.81)),      
	        	                new Stop(1, Color.color(0,0,0,0.1)) 
	        	        );
	        		r.setFill(gradient);
//	        		r.setStroke(Color.color(0,0,0,0.3));
//	        		r.setStrokeWidth(10*suofang);
	        		Polygon p=new Polygon(75,0,90,60,150,75,90,90,75,150,60,90,0,75,60,60,75,0);//利剑开刃寒光锋芒的银星
	        		p.setScaleX(suofang);
	        		p.setScaleY(suofang);
	        		p.setTranslateX(screenwidth*0.5-75*suofang-75*(1-suofang));
	        		p.setTranslateY(screenheight*0.89-75*suofang-75*(1-suofang));
	        		p.setFill(Color.AZURE);//天青色等烟雨，而我在等你
	        		p.setOpacity(0.3);
	        		g.getChildren().addAll(r,p);
	        		g.setEffect(new GaussianBlur());
		            dps.put("我",g);
	        	}
	        	try {
	        		g.getChildren().remove(2);
	        	}catch(Exception e1) {}
	        	if(j==1) {
	        		Rectangle r1=new Rectangle(screenwidth*0.15,screenheight*0.8,screenwidth*0.7,screenheight*0.18);
	        		r1.setFill(Color.color(0,0,0,0.25));
	        		g.getChildren().add(r1);
	        	}
	        	if(j==2) {
	        		Rectangle r1=new Rectangle(screenwidth*0.15,screenheight*0.8,screenwidth*0.7,screenheight*0.18);
	        		r1.setFill(Color.color(0,0,0,0.2));
	        		g.getChildren().add(r1);
	        	}
	            dp.getChildren().add(0,g);
	        }
	        else {
	        	Group g=null;
	        	if(dps.containsKey("路人")){
	        		g=dps.get("路人");
	        	}
	        	else {
	        		g=new Group();
	        		Rectangle r=new Rectangle(screenwidth*0.15,screenheight*0.8,screenwidth*0.7,screenheight*0.18);
	        		LinearGradient gradient = new LinearGradient(
	        				0, screenheight*0.8, 0, screenheight, false,
	        				CycleMethod.NO_CYCLE,
	        				  new Stop(0, Color.color(1, 0.96, 0.85,0.1)) ,
	        				 new Stop(0.3, Color.color(1, 0.96, 0.85,0.7))
	        				);
	        		r.setFill(gradient);
//	        		r.setFill(Color.color(1, 0.96, 0.85,0.7));
//	        		r.setStroke(Color.color(0,0,0,0.3));
//	        		r.setStrokeWidth(10*suofang);
	        		g.getChildren().add(r);
	        		g.setEffect(new GaussianBlur());
		            dps.put("路人",g);
	        	}
	            dp.getChildren().add(0,g);
	        }
    		nowactor=charName;
    	}
//        charImageView.setFitWidth(300);
//        charImageView.setFitHeight(400);
//        charImageView.setTranslateX(-300);
//        charImageView.setTranslateY(100);    
    }

    private void showDialog(String text) {
    	dialogshow.play();
        dialogLabel.setText("");

        dialog.stop();
        dialog.getKeyFrames().clear();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            dialog.getKeyFrames().add(
                new KeyFrame(Duration.millis(85 * i),
                    e -> dialogLabel.setText(text.substring(0, index + 1)))
            );
        }
        dialog.play();
    }

    void wuxiaochuqiao() {
    	yyf=true;
    	wuxiaojian.setOnMouseEntered(null);
		wuxiaojian.setOnMouseExited(null);
    	TranslateTransition outTransition = new TranslateTransition(Duration.millis(400), wuxiaojian);
		outTransition.setToY(-2000*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		outTransition.play();
		outTransition.setOnFinished(e->{
			kn=new killnote();
			kn.bdf=this;
			kn.inistage();
			scene.setOnKeyPressed(null);
			Platform.runLater(()->{
				kn.songListView.setTranslateY(-kn.songListView.getHeight());
			});
			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.7),new KeyValue(kn.root.opacityProperty(),1)));
    		t.play();
    		t.setOnFinished(e1->{
    			TranslateTransition outTransition1 = new TranslateTransition(Duration.millis(120), kn.songListView);
    			outTransition1.setToY(0);
    			outTransition1.setInterpolator(Interpolator.EASE_OUT);
    			outTransition1.play();
    		});
		});
    }
    void wuxiaoback() {
    	yyf=false;
    	wuxiaojian.setPickOnBounds(false);   	
    	wuxiaojian.setTranslateX((-1000/2/suofang+240)*suofang-(1920.0/1080.0-screenwidth/screenheight)*100*suofang);
    	wuxiaojian.setTranslateY(screenheight-(2000/2/suofang-400)*suofang);
    	Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.3),new KeyValue(wuxiaojian.translateYProperty(),screenheight-(2000/2/suofang-200)*suofang)));
		t.play();
    	wuxiaojian.setScaleX(0.4*suofang);
    	wuxiaojian.setScaleY(0.4*suofang);
    	TranslateTransition outTransition = new TranslateTransition(Duration.millis(400), wuxiaojian);
		outTransition.setToY(screenheight-(2000/2/suofang-100)*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		TranslateTransition inTransition = new TranslateTransition(Duration.millis(400), wuxiaojian);
		inTransition.setToY(screenheight-(2000/2/suofang-200)*suofang);
		inTransition.setInterpolator(Interpolator.EASE_BOTH);
		wuxiaojian.setOnMousePressed(e->{
			wuxiaochuqiao();
			e.consume();
		});
		wuxiaojian.setOnMouseEntered(e->{
			wuxiaojian.toFront();
			inTransition.stop();
			outTransition.setFromY(wuxiaojian.getTranslateY());
			outTransition.play();
		});
		wuxiaojian.setOnMouseExited(e->{
			outTransition.stop();
			inTransition.setFromY(wuxiaojian.getTranslateY());
			inTransition.play();
		});
    }
    public void openbook(){
    	zhengjingopenf=1;
    	double sc=screenheight/601/1.8;
    	Rotate r1= (Rotate) zhengjin.getTransforms().get(0);
        r1.setAngle(0);
        zhengjin.setEffect(ds9);
        ds9.setRadius(0);
        canhandlebookf=false;
    	Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.4)
    			,new KeyValue(zhengjin.scaleXProperty(),sc),new KeyValue(zhengjin.scaleYProperty(),sc)
    			,new KeyValue(zhengjin.translateXProperty(),screenwidth/3-433/2*(1/1.8+1))
    			,new KeyValue(zhengjin.translateYProperty(),screenheight/3-300/2*(1/1.8+1))
    			,new KeyValue(ds9.radiusProperty(),100*suofang)));
    	t1.play();
    	t1.setOnFinished(e1->{
    		canhandlebookf=true;
    		zhengjini.setImage(zhengjins[(int)(chapter/2)+1]);
//    		System.out.print(chapter);
    		nowpage=(int)(chapter/2)+1;
    		showLevelSelectMenu();
    		zhengjin.setOnMouseClicked(e->{
    			if(canhandlebookf) {
    			e.consume();
    			zhengjin.toFront();
        		if(e.getButton()==MouseButton.PRIMARY) {
        			nowpage=(nowpage+1)%5;
        			zhengjini.setImage(zhengjins[nowpage]);
        			showLevelSelectMenu();
        		}
        		else {
        			if(nowpage>0) {
        				nowpage=(nowpage-1)%5;
        				zhengjini.setImage(zhengjins[nowpage]);
        				showLevelSelectMenu();
        			}
//        			else {
//        				closebook();
//        			}
        		}
    			}
        	});
    	});
    }
    
    public void closebook() {
    	zhengjingopenf=0;
    	zhengjini.setImage(hunyuanzhengjini);
        Rotate r=new Rotate(0,433/ 2,601/ 2);
        zhengjin.getTransforms().add(r);
   // 鼠标按下事件：记录初始位置
        Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.2)
    			,new KeyValue(zhengjin.scaleXProperty(),suofang/3.5),new KeyValue(zhengjin.scaleYProperty(),suofang/3.5)
    			,new KeyValue(zhengjin.translateXProperty(),(-433/2/suofang+100)*suofang),
    			new KeyValue(zhengjin.translateYProperty(),screenheight+(-601/2/suofang-100)*suofang)));
        t1.play();
        try {
        	zhengjin.getChildren().remove(mainBox);
        }catch(Exception e) {}
        zhengjin.setPickOnBounds(false);
		ScaleTransition outTransition = new ScaleTransition(Duration.millis(400), zhengjin);
		outTransition.setToY(suofang/3);
		outTransition.setToX(suofang/3);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		ScaleTransition inTransition = new ScaleTransition(Duration.millis(400), zhengjin);
		inTransition.setToY(suofang/3.5);
		inTransition.setToX(suofang/3.5);
		inTransition.setInterpolator(Interpolator.EASE_BOTH);
		
		  t1.setOnFinished(e->{
			  zhengjin.setOnMouseClicked(e1->{
				  if(canhandlebookf) {
		 			e1.consume();
		    		 zhengjin.setOnMouseClicked(null);
		 			 zhengjin.setOnMouseEntered(null);
		 		        zhengjin.setOnMouseExited(null);
		 		    	zhengjin.setOnMouseDragged(null);
		 			openbook();
				  }
		 		});
	        	 zhengjin.setOnMouseEntered(e1->{
	        		 if(canhandlebookf) {
	     			zhengjin.toFront();
	     			inTransition.stop();
	     			outTransition.setFromY(zhengjin.getScaleY());
	     			outTransition.setFromX(zhengjin.getScaleX());
	     			outTransition.play();
	        		 }
	     		});
	     		zhengjin.setOnMouseExited(e1->{
	     			if(canhandlebookf) {
	     			outTransition.stop();
	     			inTransition.setFromY(zhengjin.getScaleY());
	     			inTransition.setFromX(zhengjin.getScaleX());
	     			inTransition.play();
	     			}
	     		});
	         }) ;	
		
      
    }
 // 新增方法：显示关卡选择菜单
    private void showLevelSelectMenu() {
        // 读取关卡目录
    	try {
    		zhengjin.getChildren().remove(mainBox);
	    }catch(Exception e) {}
        mainBox = new GridPane();
        mainBox.setVgap(10*suofang);
        mainBox.setHgap(40*suofang);
        mainBox.setPickOnBounds(false);
//        mainBox.setPadding(new Insets(10*suofang));
//        mainBox.setStyle("-fx-background-color: transparent; -fx-border-color: #bb0000; -fx-border-width: 10;");
        mainBox.setStyle("-fx-background-color: transparent;");
        mainBox.setPrefSize(807, 601);
        // 标题
//        Label titleLabel = new Label("选择关卡");
//        titleLabel.setStyle("-fx-text-fill: #e0d0b8; -fx-font-size: 18px; -fx-font-weight: bold;");
        
        int i=0;
        // 添加关卡按钮
        for (String levelFile : levelFiles) {
        	
        	if(nowpage==1) {
        		if(i>=unlockedproject.get(0))break;
        		Button levelButton = createLevelButton(levelFile);
                mainBox.add(levelButton,i/8,i%8);
        	}
        	else if(nowpage>1) {
        		if(i>=unlockedproject.get(nowpage*2-2))break;
        		if(segment>nowpage*2-4)
	        		if(i>=segmentstartproject.get(nowpage*2-3)-1&&i<unlockedproject.get(nowpage*2-3)) {
		        		Button levelButton = createLevelButton(levelFile);
		                mainBox.add(levelButton,(i-segmentstartproject.get(nowpage*2-3)+1)/8+2,(i-segmentstartproject.get(nowpage*2-3)+1)%8);
	        		}
        		if(segment>nowpage*2-3)
        			if(i>=segmentstartproject.get(nowpage*2-2)-1&&i<unlockedproject.get(nowpage*2-2)) {
    	        		Button levelButton = createLevelButton(levelFile);
    	                mainBox.add(levelButton,(i-segmentstartproject.get(nowpage*2-2)+1)/8,(i-segmentstartproject.get(nowpage*2-2)+1)%8);
            		}
        			if(mainBox.getChildren().size()<16) {
        				Label b=new Label();
        				b.setPrefSize(160,30);
        				mainBox.add(b, 1, 0);
        			}
        	}
        	i++;
        }
        mainBox.setOnMouseClicked(e->{
        	e.consume();
        });
        // 刷新按钮
//        Button refreshButton = new Button("刷新关卡列表");
//        refreshButton.setStyle("-fx-background-color: #5d4c3a; -fx-text-fill: white; -fx-padding: 8 15;");
//        refreshButton.setOnAction(e ->   refreshLevelList());
        mainBox.setTranslateX(10);
        mainBox.setTranslateY(10);
        zhengjin.getChildren().add(mainBox);
    }

    // 新增方法：刷新关卡列表
    @SuppressWarnings("unused")
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
    // 新增方法：创建关卡按钮
    private Button createLevelButton(String levelFile) {
        // 从文件名提取关卡名称（去掉.txt后缀）
        String levelName = levelFile.replace(".txt", "");
        List<String> tiaojian = Arrays.asList(levelName.split("-"));
        int level=Integer.parseInt(tiaojian.get(0).replaceAll("zhu",""))-1;
        Button button = new Button(i18n.levels[language][level]);
        button.setPadding(new Insets(3));
        button.setPrefSize(160,30);
//        button.setMatgin(new Insets(10*suofang));
        button.setStyle("-fx-background-color:transparent; -fx-text-fill: #990000;");
        button.setFont(f01);
        button.setPickOnBounds(false);
        if(finishedproject>level) {
	        button.setOnAction(e -> {
	        	try {
	        		if(pzs.anifinishf)
	        			pzs.loadSelectedLevel(levelFile);
	        	}catch(Exception e1) {}
	        	if(lingjvf==false) {
	        		closebook();
	        		openhe();
	        		Timeline t1=new Timeline(new KeyFrame(Duration.seconds(4.8),e1->{
	        			pzs.loadSelectedLevel(levelFile);
	        		}));
	        		t1.play();
	        	}
	        });
	        button.setOnMouseEntered(e->{
	        	button.setStyle("-fx-background-color: #ffffff33; -fx-text-fill: #990000;");
	        });
	        button.setOnMousePressed(e->{
	        	button.setStyle("-fx-background-color: #00000011; -fx-text-fill: #990000;");
	        });
	        button.setOnMouseReleased(e->{
	        	button.setStyle("-fx-background-color: #ffffff33; -fx-text-fill: #990000;");
	        });
	        button.setOnMouseExited(e->{
	        	button.setStyle("-fx-background-color:transparent; -fx-text-fill: #990000;");
	        });
        }
        else {
        	button.setStyle("-fx-background-color:transparent; -fx-text-fill: #000000;");
            
        }
        button.setOnMouseMoved(e->{
        	nowmousex=e.getScreenX();
        	nowmousey=e.getScreenY();
        });
        
        return button;
    }
    

    // 新增方法：加载关卡文件列表
    private void loadLevelFiles() {
        levelFiles.clear();
        
        File levelDir = new File(currentLevelDir);
        if (levelDir.exists() && levelDir.isDirectory()) {
            File[] files = levelDir.listFiles((dir, name) -> name.matches("zhu\\d+.*\\.txt"));
            if (files != null) {
                // 按数字排序
                Arrays.sort(files, (f1, f2) -> {
                    int num1 = extractLevelNumber(f1.getName());
                    int num2 = extractLevelNumber(f2.getName());
                    return Integer.compare(num1, num2);
                });
                
                for (File file : files) {
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

    void openhe() {
    	lingjvf=true;
    	lingjvhe.setOnMouseEntered(null);
    	lingjvhe.setOnMouseExited(null);
    	lingjvhe.toFront();
    	TranslateTransition outTransition = new TranslateTransition(Duration.millis(400), lingjvhe);
		outTransition.setToY((-2400/2/suofang+436)*suofang);
		outTransition.setToX(screenwidth/2-(1260/2/suofang+456.2)*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		outTransition.play();
		ScaleTransition scaleTransition=new ScaleTransition(Duration.millis(400), lingjvhe);
		scaleTransition.setToY(suofang/3);
		scaleTransition.setToX(suofang/3);
		scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
		scaleTransition.play();
		
		outTransition.setOnFinished(e->{
			Timeline t1=new Timeline(new KeyFrame(Duration.seconds(0.2)));
    		t1.play();
    		t1.setOnFinished(e3->{
    			if(pzs!=null)pzs.destroy();
    			pzs=new puzzlesolver();
    			pzs.bdf=this;
    			pzs.inistage(finishedproject);
				lingjvhe.toFront();
				pzs.stack.setOnMouseClicked(e2->{
					try {
//					pzs.stack.toFront();
					if(zhengjingopenf==1) {
						closebook();
					}
					e2.consume();
					}catch(Exception e1) {};
				});
				Timeline t=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(pzs.stack.opacityProperty(),0)),
			        new KeyFrame(Duration.seconds(0.5),new KeyValue(pzs.stack.opacityProperty(),1)),
			        		new KeyFrame(Duration.seconds(0.5),e2->{lingjvhe.toBack();}),
			        		new KeyFrame(Duration.seconds(4),e2->{
			        			if(finishedproject==3) {
			    					bdf.enterintro();
			    					bdf.playedtutorial=2;
			    					playedt=0;
			    				}
			        			if(finishedproject==10) {
			    					bdf.enterintro();
			    					bdf.playedtutorial=3;
			    					playedt=0;
			    				}
			        		}));
	    		t.play();
	    		
	    	
//				pzs.gpane.setOnMouseClicked(e1->{
//					e.consume();
//					
//				});
    		});
    		
		});
    }
    void closehe() {
    	lingjvf=false;
    	lingjvhe.setPickOnBounds(false);
    	
    	TranslateTransition in1Transition = new TranslateTransition(Duration.millis(400), lingjvhe);
    	in1Transition.setToX(screenwidth-(1260/2/suofang+250)*suofang-(1920.0/1080.0-screenwidth/screenheight)*100*suofang);
    	in1Transition.setToY(screenheight-(2400/2/suofang+70)*suofang);
    	in1Transition.setInterpolator(Interpolator.EASE_BOTH);
    	in1Transition.play();
    	
    	lingjvhe.setScaleX(0.1*suofang);
    	lingjvhe.setScaleY(0.1*suofang);
		
		ScaleTransition outTransition = new ScaleTransition(Duration.millis(400), lingjvhe);
		outTransition.setToY(0.12*suofang);
		outTransition.setToX(0.12*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		ScaleTransition inTransition = new ScaleTransition(Duration.millis(400), lingjvhe);
		inTransition.setToY(0.1*suofang);
		inTransition.setToX(0.1*suofang);
		inTransition.setInterpolator(Interpolator.EASE_BOTH);
		
		lingjvhe.setOnMouseClicked(e->{
			openhe();
			e.consume();
		});
		lingjvhe.setOnMouseEntered(e->{
			lingjvhe.toFront();
			inTransition.stop();
			outTransition.setFromY(lingjvhe.getScaleY());
			outTransition.setFromX(lingjvhe.getScaleX());
			outTransition.play();
		});
		lingjvhe.setOnMouseExited(e->{
			outTransition.stop();
			inTransition.setFromY(lingjvhe.getScaleY());
			inTransition.setFromX(lingjvhe.getScaleX());
			inTransition.play();
		});
    }
    void openxiaoyouxi() {
    	xxyf=true;
    	guiniu.setOnMouseEntered(null);
    	guiniu.setOnMouseExited(null);
    	TranslateTransition outTransition = new TranslateTransition(Duration.millis(400), guiniu);
		outTransition.setToY(-2000*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		outTransition.play();
		outTransition.setOnFinished(e->{
			mb = new movebox();
			mb.bdf = this;
			int startStage = finishedmovebox+1;
			if(startStage>8) startStage=0;
			mb.nowstage = startStage;
			mb.inistage();
			mb.game.setOpacity(0);
			mb.game.setVisible(true);
			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.7),new KeyValue(mb.game.opacityProperty(),1)));
    		t.play();
		});
    }
    void xiaoyouxiback() {
    	xxyf=false;
    	blendefine.game1.setVisible(true);
    	if(isbookshowf==0)zhengjin.setOpacity(0);
        if(iswuxiaoshowf==0)wuxiaojian.setOpacity(0);
        if(isboxshowf==0)lingjvhe.setOpacity(0);
        if(isguishowf==0)guiniu.setOpacity(0);
        try {ae1.animationTimer.start();}catch(Exception e1) {}
        try {pss.animationTimer.start();}catch(Exception e1) {}
        // 龟纽归位
        guiniu.setVisible(true);
        guiniu.setOpacity(1);
        guiniu.setScaleX(0.8);
        guiniu.setScaleY(0.8);
        closexiaoyouxi();
        
    	Timeline t = new Timeline(
    		new KeyFrame(Duration.seconds(0.5),
    			new KeyValue(blendefine.game1.opacityProperty(), 1),
    			new KeyValue(mb.game.opacityProperty(), 1)),
    		new KeyFrame(Duration.seconds(1),e->{
    			game1.setOnMouseClicked(gameclick);
    			scene.setOnKeyPressed(scenepresskey);
    			scene.setOnMouseMoved(ev->{
    		    	nowmousex=ev.getX();
    		    	nowmousey=ev.getY();
    		    });
    			mouseiv.setMouseTransparent(true);
    	        stp.getChildren().remove(mouseiv);
    	        stp.getChildren().add(mouseiv);
    	    	mouseanict.play();
    	    	playMusic("src/blendefine/music/监督下的天师.mp3");
    	        mp.setVolume(volume);
    	        mb.game.setMouseTransparent(true);
    		}),
    		new KeyFrame(Duration.seconds(3),
    			new KeyValue(mb.game.opacityProperty(), 0)),
    		new KeyFrame(Duration.seconds(3.2))
    	);
    	t.play();
    	t.setOnFinished(e -> {
    		stp.getChildren().remove(mb.game);
        	mb.destroy();
        });
    }
    void closexiaoyouxi() {
    	guiniu.setOnMouseClicked(e->{
    		openxiaoyouxi();
			e.consume();
		});
    	TranslateTransition in1Transition = new TranslateTransition(Duration.millis(400), guiniu);
    	in1Transition.setToX(screenwidth-(300/2/suofang+100)*suofang-(1920.0/1080.0-screenwidth/screenheight)*100*suofang);
    	in1Transition.setToY(screenheight-(260/2/suofang+60)*suofang);
    	in1Transition.setInterpolator(Interpolator.EASE_BOTH);
    	in1Transition.play();
    	guiniu.setScaleX(0.8*suofang);
    	guiniu.setScaleY(0.8*suofang);
    	
    	ScaleTransition outTransition = new ScaleTransition(Duration.millis(400), guiniu);
		outTransition.setToY(1*suofang);
		outTransition.setToX(1*suofang);
		outTransition.setInterpolator(Interpolator.EASE_BOTH);
		ScaleTransition inTransition = new ScaleTransition(Duration.millis(400), guiniu);
		inTransition.setToY(0.8*suofang);
		inTransition.setToX(0.8*suofang);
		inTransition.setInterpolator(Interpolator.EASE_BOTH);
		guiniu.setOnMouseEntered(e->{
			guiniu.toFront();
			inTransition.stop();
			outTransition.setFromY(guiniu.getScaleY());
			outTransition.setFromX(guiniu.getScaleX());
			outTransition.play();
		});
		guiniu.setOnMouseExited(e->{
			outTransition.stop();
			inTransition.setFromY(guiniu.getScaleY());
			inTransition.setFromX(guiniu.getScaleX());
			inTransition.play();
		});
    }
    
    public void switchsetting() {
    	if (settingshowf) {
			settingshowf = false;
			Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.15),
					new KeyValue(settingpane.translateYProperty(), -screenheight)));
			t.play();
			t.setOnFinished(e1 -> {
				game1.getChildren().remove(settingpane);
		    	if(bdf.playedtutorial==0) {
					bdf.enterintro();
					bdf.playedtutorial=1;
					playedt=0;
				}
			});
		} else {
			settingshowf = true;
			if (null == settingpane) {
				settingpane = new settingpane(bdf);
			}
			settingpane.setTranslateX(screenwidth / 2 - screenheight * 0.8 / 1028 * 798 / 2);
			settingpane.setTranslateY(-screenheight);
			game1.getChildren().add(settingpane);
			settingpane.toFront();
			Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.15),
					new KeyValue(settingpane.translateYProperty(), 0.1 * screenheight)));
			t.play();
		}
    }
    
    /**d=0向右下弹出，1下，2左下，3左，4左上，5上，6右上，7右 */
    public Group maketext(double x,double y,int d,String title,String word) {
    	if(language<4)
    		return maketext(x,y,550*suofang,((word.length()-2)/20)*43*suofang+((null!=title)?110*suofang:60*suofang),d,title,word);
    	else return maketext(x,y,550*suofang,((word.length()-1)/40)*43*suofang+((null!=title)?110*suofang:60*suofang),d,title,word);
    }

    /**x y弹出点 x y=0时在正中间 w h对话框宽高 d=0向右下弹出，1下，2左下，直到7向右 */
    public Group maketext(double x,double y,double w,double h,int d,String title,String word) {
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
	        t1.setTextFill(Color.color(0.7, 0, 0));
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
    
    public void enterintro() {
    	intro=new Timeline(new KeyFrame(Duration.millis(8),e->{
    		if(ui.getChildren().size()==0) {
    			try {
	    		maketext(i18n.jiaochengpos[playedtutorial-1][nowintro][0],
	    				i18n.jiaochengpos[playedtutorial-1][nowintro][1],
	    				i18n.jiaochengpos[playedtutorial-1][nowintro][2],
	    				i18n.jiaochengname[language],
	    				i18n.jiaocheng[playedtutorial-1][language][nowintro]);		
    			}catch(Exception e1) {}
    		}
    	}));
    	intro.setCycleCount(Timeline.INDEFINITE);
    	intro.play();
    }
    
    /**src/blendefine/music/xx.mp3*/
    void playMusic(String path) {
    	File f = new File(path);
    	Media media;
        if(f.exists()) {
            media = new Media(f.toURI().toString());
        } else {
            media = null; 
        }
        playMusic(media);
    }

    void playMusic(Media media) {
        if (mp != null) {
        	mp.stop();
        }
        // 注意：实际使用请确保文件存在，否则会报错
        try {
        	mp = new MediaPlayer(media);
        	mp.setCycleCount(MediaPlayer.INDEFINITE);
        	mp.setVolume(volume);
        	mp.play();
        } catch (Exception e) {
            System.err.println("无法播放音乐: " + e.getMessage());
        }
    }
    
    private void loadSave() {
   	 try (BufferedReader br = Files.newBufferedReader(Paths.get("src/blendefine/files/save.txt"))) {
           String line;
           int i=0;
           while ((line = br.readLine()) != null) {
           	if(i==0) {
           		String[] ss=line.split(" ");
           		int[] is= new int[10];
           		for(int j=0;j<10;j++) {
           			is[j]=Integer.parseInt(ss[j]);
           		}
           		segment=is[0];//玩到几页
           		finishedproject=is[1];//上一个玩的工程
           		chapter=is[2];//玩到第几个剧本
           		sentence=is[3];//玩到第几句话
           		playedtutorial=is[4];//玩过几个教程
           		isbookshowf=is[5];
           		isboxshowf=is[6];
           		iswuxiaoshowf=is[7];
           		isguishowf=is[8];
           		language=is[9];
           		volume=Double.parseDouble(ss[10]);
           		finishedmovebox=(ss.length>11)?Integer.parseInt(ss[11]):-1;
           	}
           	else {
           		//TODO 成就
           	}
           	i++;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   	
	   	try (BufferedReader br = Files.newBufferedReader(Paths.get("src/blendefine/files/savesegmentstartproject.txt"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
		        	String[] ss=line.split(" ");
		        	int[] is= new int[3];
	           		for(int j=0;j<ss.length;j++) {
	           			is[j]=Integer.parseInt(ss[j]);
	           		}
		        	segmentstartproject.add(is[1]);
		        	unlockedproject.add(is[2]);
	        	
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
   }
    void loadfont() {
    	if(language==0) {
   		    f0=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),50*suofang);
   		    f01=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),30*suofang);
   			f1=Font.loadFont(getClass().getResourceAsStream("uipic/dingliezhuhaifont-20240831GengXinBan)-2.ttf"),40*suofang);
   			f2=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),20*suofang);	
   			f3=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),30*suofang);
   			f4=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),40*suofang);
   			f8=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),80*suofang);   
   		    f25=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),25*suofang);
   			f200=Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"),200*suofang);
    	}else if(language==2) {
   			f0=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),50*suofang);
   		    f01=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),30*suofang);
   			f1=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),40*suofang);
   			f2=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),20*suofang);	
   			f3=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),30*suofang);
   			f4=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),40*suofang);
   			f8=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),80*suofang);   
   		    f25=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),25*suofang);
   			f200=Font.loadFont(getClass().getResourceAsStream("uipic/sawarabi-mincho-medium.otf"),200*suofang);
    	}else if(language==1||language==3) {
    		f0=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),50*suofang);
   		    f01=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),30*suofang);
   			f1=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),40*suofang);
   			f2=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),20*suofang);	
   			f3=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),30*suofang);
   			f4=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),40*suofang);
   			f8=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),80*suofang);   
   		    f25=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),25*suofang);
   			f200=Font.loadFont(getClass().getResourceAsStream("uipic/SourceHanSansSC-Regular-2.otf"),200*suofang);
    		
    	}else {
   			f0=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),50*suofang);
   		    f01=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),30*suofang);
   			f1=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),40*suofang);
   			f2=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),20*suofang);	
   			f3=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),30*suofang);
   			f4=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),40*suofang);
   			f8=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),80*suofang);   
   		    f25=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),25*suofang);
   			f200=Font.loadFont(getClass().getResourceAsStream("uipic/YsabeauInfant-SemiBoldItalic.otf"),200*suofang);
    	}
    }
    private void saveSave() {
    	//TODO 读取关卡后判断有无完成存档是否要增加finishedproject
    	try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("src/blendefine/files/save.txt"))) {
            bw.append(segment+" "+finishedproject+" "+chapter+" "+sentence+" "+playedtutorial+" "+
            		isbookshowf+" "+isboxshowf+" "+iswuxiaoshowf+" "+isguishowf+" "+language+" "+volume+" "+finishedmovebox+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	    launch(args);
    }
}
