package blendefine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fx.AshEffect;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

public class killnote{

    	public Background backtm = new Background(new BackgroundFill(new Color(0, 0, 0, 0), null, null));
        public Background backbl = new Background(new BackgroundFill(Color.BLACK, null, null));
        public Background backlg = new Background(new BackgroundFill(Color.LIGHTGREY, null, null));
        public Background backg = new Background(new BackgroundFill(Color.color(0, 0, 0, 0.6), null, null));
        public Background backwt = new Background(new BackgroundFill(Color.WHITE, null, null));
        double speed = 1000,rotates=5, suofang = blendefine.suofang, yyheight = 1080 * suofang, yywidth = 1920 * suofang,latency=0;
        Font f0 = Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"), 50 * suofang),
        f1 = Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"), 30 * suofang),
        f2 = Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"), 80 * suofang),
        f3 = Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"), 16 * suofang),
        f4 = Font.loadFont(getClass().getResourceAsStream("uipic/HongLeiXingShuJianTi-2.otf"), 20 * suofang);
        Label l = new Label();
        GridPane b;
        BorderPane contentLayer = new BorderPane();
        HBox infoPanel = new HBox();
        VBox textBox,hardBox;
        Pane yy,root,game;
        Group gyy;
        Scene scene;
        String nowplayname,lastselectsong;
		public StackPane stp=blendefine.stp;
        int songf = 0,maxf=0,pausef=0,hard=0;
        int[] guidaoonuse = new int[4], key = new int[4];
        double ms,songlength,nowtime;
        double notetime;
        double nowmousex =0, nowmousey = 0;
    	double red = 255, green = 0, blue = 0, cf = 0;
        // UI 组件

        private ImageView backgroundViewer; // 背景图层
        ListView<Song> songListView; // 歌曲列表
        private Label titleLabel;           // 歌曲标题显示
        private Label artistLabel;          // 歌曲艺术家显示
        private Label scoreLabel;          // 分数显示
        private Label hardLabel;          // 难度显示
        private ImageView foreimage;
        Slider latencyslider,speedslider;
        
        // 数据
        private List<Song> songs;
       
        // 动画相关
        private Timeline bgSwitchTimeline,bgkira,mouseanict;  // 背景切换动画
        note[] ontap = new note[4];
        Rectangle[] yys = new Rectangle[4];
       
        Timeline scrollupt = new Timeline(new KeyFrame(Duration.seconds(0.15), e1 -> {
            key[1] = 0;
        })),
                scrolldownt = new Timeline(new KeyFrame(Duration.seconds(0.15), e1 -> {
                    key[2] = 0;
                })),
                leftyyt = new Timeline(new KeyFrame(Duration.seconds(0.15), e1 -> {
                    key[0] = 0;
                })),
                rightyyt = new Timeline(new KeyFrame(Duration.seconds(0.15), e1 -> {
                    key[3] = 0;
                })),
                checkhide=new  Timeline(new KeyFrame(Duration.seconds(0.4), new KeyValue(l.opacityProperty(),0.95))
                		,new KeyFrame(Duration.seconds(0.6), new KeyValue(l.opacityProperty(),0)));
        Timeline tml;
        HashSet<Timeline> ottimeline = new HashSet<Timeline>(),ottimeline1 = new HashSet<Timeline>();
        HashSet<TranslateTransition> transs = new HashSet<TranslateTransition>();
        MotionBlur mb;
        GaussianBlur gb;
        DropShadow ds,ds1,ds2,ds3;
        Bloom bl,bl1,bl2;
        HashMap<String,String> songsscores =new HashMap<String,String>();
        // 统计变量
        int exactCount,perfectCount, earlyCount, lateCount, missCount;
        int combo, maxCombo;
        int totalNotes;
        boolean gameFinished,exiting;
        StackPane scorePane; // 结算面板
        AshEffect ash;
        blendefine bdf;
        RadialGradient[] rg=new RadialGradient[4];
        public void inistage() {
        	 // 1. 初始化根布局
        	
        	Screen screen = Screen.getPrimary();
            yywidth = screen.getBounds().getWidth();
    		yyheight = screen.getBounds().getHeight();
    		suofang = yyheight / 1080;
//            this.initStyle(StageStyle.TRANSPARENT);
//            this.setFullScreen(true);
//            this.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//            this.setFullScreenExitHint("");
    		Stop[] stops = new Stop[] { new Stop(0, Color.ALICEBLUE), new Stop(0.9, Color.DEEPSKYBLUE) ,new Stop(1, Color.TRANSPARENT)};
            rg[0] = new RadialGradient(0, 0, yywidth / 8  , 0, yywidth / 8, false, CycleMethod.NO_CYCLE, stops);
            rg[1] = new RadialGradient(0, 0, yywidth / 8 + yywidth / 4 , 0, yywidth / 8, false, CycleMethod.NO_CYCLE, stops);
            rg[2] = new RadialGradient(0, 0, yywidth / 8 + yywidth / 4*2 , 0, yywidth / 8, false, CycleMethod.NO_CYCLE, stops);
            rg[3] = new RadialGradient(0, 0, yywidth / 8 + yywidth / 4*3, 0, yywidth / 8, false, CycleMethod.NO_CYCLE, stops);
    		root=new Pane();
            game = new Pane();
            root.getChildren().add(game);
            game.setStyle("-fx-background-color: #000000;"); // 默认纯黑背景

            // 2. 初始化背景层 (全屏模糊效果)
            initBackgroundLayer();

            // 3. 初始化数据
            loadSongs();

            // 4. 初始化前景 UI (左侧列表 + 右侧信息)
            initForegroundUI();
               
            yy = new Pane();
            yy.setPrefSize(yywidth, yyheight);
            yy.setBackground(backtm);
            yy.setBackground(backg);
            // 创建透视变换
            PerspectiveTransform pt = new PerspectiveTransform();
            pt.setUlx(yywidth/3);    pt.setUly(0);
            pt.setUrx(yywidth/3*2);  pt.setUry(0);
            pt.setLrx(yywidth);  pt.setLry(yyheight);
            pt.setLlx(0);    pt.setLly(yyheight);
            Rectangle rc=new Rectangle(yywidth, yyheight);
            // 应用变换
            yy.setClip(rc);
            gyy=new Group(yy);
            
//            gyy.setTranslateY(-400);
            gyy.setEffect(pt);

//            yys[0] = new Rectangle(2 * suofang, 0, yywidth / 4 - 3 * suofang, yyheight);
//            yys[1] = new Rectangle(yywidth / 4 + 2 * suofang, 0, yywidth / 4 - 3 * suofang, yyheight);
//            yys[2] = new Rectangle(yywidth / 2 + 2 * suofang, 0, yywidth / 4 - 3 * suofang, yyheight);
//            yys[3] = new Rectangle(yywidth / 4 * 3 + 2 * suofang, 0, yywidth / 4 - 3 * suofang, yyheight);
            yys[0] = new Rectangle(yywidth / 8-5*suofang, 0, 10 * suofang, yyheight);
            yys[1] = new Rectangle(yywidth / 8*3-5*suofang, 0,10 * suofang, yyheight);
            yys[2] = new Rectangle(yywidth / 8*5-5*suofang, 0,10 * suofang, yyheight);
            yys[3] = new Rectangle(yywidth / 8*7-5*suofang, 0,10 * suofang, yyheight);
            yys[0].setFill(new Color(0.6, 0.6, 0.6, 0.5));
            yys[1].setFill(new Color(0.6, 0.6, 0.6, 0.5));
            yys[2].setFill(new Color(0.6, 0.6, 0.6, 0.5));
            yys[3].setFill(new Color(0.6, 0.6, 0.6, 0.5));
            yy.getChildren().add(yys[0]);
            yy.getChildren().add(yys[1]);
            yy.getChildren().add(yys[2]);
            yy.getChildren().add(yys[3]);
            gb=new GaussianBlur(20*suofang);
            yys[0].setEffect(gb);yys[1].setEffect(gb);yys[2].setEffect(gb);yys[3].setEffect(gb);
            Rectangle line=new Rectangle(0,yyheight,yywidth,0);
            line.setStroke(Color.CORNFLOWERBLUE);
            line.setStrokeWidth(6*suofang);
            line.setEffect(gb);
            yy.getChildren().add(line);
//            Bloom bl = new Bloom();
//            bl.setThreshold(0.7);
//            yy.setEffect(bl);
            mb = new MotionBlur();
            mb.setRadius(25 * suofang);
            ds = new DropShadow(50 * suofang, Color.color(1, 1, 1, 1));
            ds.setSpread(0.7);
            ds1 = new DropShadow(20 * suofang, Color.color(1, 1, 1, 1));
            ds1.setSpread(0.8);
            ds2 = new DropShadow(6 * suofang, Color.color(1, 1, 1, 1));
            ds2.setSpread(0.7);
            ds3 = new DropShadow(80 * suofang, Color.color(1, 0.2, 0, 1));
            ds3.setSpread(0.8);
            bl=new Bloom();
            bl1=new Bloom();
            bl2=new Bloom();
            yy.setOnMousePressed(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    leftyyt.stop();
                    key[0] = 1;
                    leftyyt.play();
                }
                if (e.getButton() == MouseButton.SECONDARY) {
                    rightyyt.stop();
                    key[3] = 1;
                    rightyyt.play();
                }
                if (e.getButton()==MouseButton.MIDDLE) {
                	if(pausef==0) {
                		pausef=1;
	                	tml.pause();
	                	game.getChildren().remove(scorePane);
	                    showScore(nowplayname);
	                    ottimeline.forEach(e1->{
	                    	e1.pause();
	                    });
	                    transs.forEach(e1->{
	                    	e1.pause();
	                    });
                	}
                	else {
                		pausef=0;
	                	tml.play();
	                	game.getChildren().remove(scorePane);
	                    ottimeline.forEach(e1->{
	                    	e1.play();
	                    });
	                    transs.forEach(e1->{
	                    	e1.play();
	                    });
                	}
                }
            });
            yy.setOnScroll(e -> {
                if (e.getDeltaY() > 0) {
                    scrollupt.stop();
                    key[1] = 1;
                    scrollupt.play();
                }
                if (e.getDeltaY() < 0) {
                    scrolldownt.stop();
                    key[2] = 1;
                    scrolldownt.play();
                }
            });
            yy.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.Q||e.getCode() == KeyCode.D||e.getCode() == KeyCode.LEFT) {
                    leftyyt.stop();
                    key[0] = 1;
                    leftyyt.play();
                }
                if (e.getCode() == KeyCode.W||e.getCode() == KeyCode.F||e.getCode() == KeyCode.UP) {
                    scrollupt.stop();
                    key[1] = 1;
                    scrollupt.play();
                }
                if (e.getCode() == KeyCode.O||e.getCode() == KeyCode.J||e.getCode() == KeyCode.DOWN) {
                    scrolldownt.stop();
                    key[2] = 1;
                    scrolldownt.play();
                }
                if (e.getCode() == KeyCode.P||e.getCode() == KeyCode.K||e.getCode() == KeyCode.RIGHT) {
                    rightyyt.stop();
                    key[3] = 1;
                    rightyyt.play();
                }
                if(e.getCode()==KeyCode.SPACE) {
                	if(pausef==0) {
                		pausef=1;
	                	tml.pause();
	                	game.getChildren().remove(scorePane);
	                    showScore(nowplayname);
	                    ottimeline.forEach(e1->{
	                    	e1.pause();
	                    });
	                    transs.forEach(e1->{
	                    	e1.pause();
	                    });
                	}
                	else {
                		pausef=0;
	                	tml.play();
	                	game.getChildren().remove(scorePane);
	                    ottimeline.forEach(e1->{
	                    	e1.play();
	                    });
	                    transs.forEach(e1->{
	                    	e1.play();
	                    });
                	}
                }
                if(e.getCode().equals(KeyCode.ESCAPE)) {
                	back();
                }
            });


            b = new GridPane();
            b.setPickOnBounds(false);
            for (int j = 0; j < 19; j++) {
                Rectangle r = new Rectangle(0, 0, 21 * suofang, 70 * suofang);
                r.setFill(Color.TRANSPARENT);
                r.setMouseTransparent(true);
                if (j != 1)
                    b.add(r, j, 0);
            }
            Polygon backb = new Polygon(0, 25 * suofang,
                    50 * suofang, 0,
                    50 * suofang, 50 * suofang);
            backb.setFill(Color.color(1,1,1,0.7));
            b.add(backb, 1, 0);
            backb.setTranslateY(20*suofang);
            backb.setOnMouseClicked(e -> {
                back();
            });

            game.getChildren().add(b);
            
            ash= new AshEffect((int)yywidth,(int)yyheight,(int)(yywidth+30*suofang),(int)(yyheight+30*suofang),50*suofang,300,0);
            ash.setMouseTransparent(true);
            game.getChildren().add(ash);
            ash.setOpacity(0);
            
            l.setText("none");
//            l.setFont(f0);
            l.setFont(f0);
            l.setTextFill(Color.TRANSPARENT);
            l.setEffect(ds);
            l.setPrefWidth(300 * suofang);
            l.setPrefHeight(100 * suofang);
//            l.setTranslateX(yywidth / 2 - 150 * suofang);
//            l.setTranslateY(-yyheight / 4 - 50 * suofang);
            l.setAlignment(Pos.BASELINE_CENTER);
            // 5. 设置场景
//            scene = new Scene(game, yywidth, yyheight,Color.TRANSPARENT);
//            setScene(scene);
//            new p(yyheight / speed*1000+latency);//播放延迟
            // 默认选中第一项
            if (!songs.isEmpty()) {
                songListView.getSelectionModel().select(0);
            }
            stp.getChildren().add(root);
            root.setOpacity(0);
//            game.setVisible(false);
            
            ImageView mouseiv=blendefine.mouseiv;
			stp.getChildren().remove(mouseiv);
			stp.getChildren().add(mouseiv);
			mouseanict = new Timeline(new KeyFrame(Duration.millis(1),e->{
				mouseiv.setTranslateX(nowmousex);
				mouseiv.setTranslateY(nowmousey);
			}));
			mouseanict.setCycleCount(Timeline.INDEFINITE);
			mouseanict.play();
			songListView.setOnMouseMoved(e->{
				nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
			});
			root.setOnMouseMoved(e->{
				nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
			});
			
        }
        void back() {
        	if(exiting) return;
        	exiting=true;
        	if (songf == 1) {
        		exiting=false;
                clearyy();
                Timeline t1 = new Timeline(new KeyFrame(Duration.seconds(0.3),
                		new KeyValue(contentLayer.opacityProperty(),1),
                		new KeyValue(yy.opacityProperty(),0),
                		new KeyValue(l.opacityProperty(),0)));
//        		ottimeline.add(t1);
                t1.setOnFinished(e1->{
                	game.getChildren().remove(l);
                    game.getChildren().remove(gyy);
                });
        		t1.play();
                // 移除结算面板（如果存在）
                if (scorePane != null) {
                    game.getChildren().remove(scorePane);
                    scorePane = null;
                }
            }
        	else {
        		Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.7),new KeyValue(game.opacityProperty(),0)));
        		t.setOnFinished(e1->{
        			bdf.clearguanka();
        			bdf.wuxiaoback();
        			stp.getChildren().remove(root);
        			close();
        		});
        		t.play();
        	}
        }
        void clearyy() {
            // 停止所有可能正在运行的 Timeline
            ottimeline.forEach(e1 -> {
                e1.stop();
            });
            ottimeline1.forEach(e1 -> {
                e1.stop();
            });
            ottimeline.clear();
            ottimeline1.clear();
            scrollupt.stop();
            scrolldownt.stop();
            leftyyt.stop();
            rightyyt.stop();
            if (tml != null) {
                tml.stop();
            }
            yy.getChildren().remove(5, yy.getChildren().size());

            for (int i = 0; i < 4; i++) {
                ontap[i] = null;
                guidaoonuse[i] = 0;
                key[i] = 0; // 同时清除按键状态
            }
            l.setText("");
            songf = 0; // 标记已退出音游模式
            ms = 0;
            gameFinished = false;
            nowplayname="";
        }

        void yyfx(int guidao) {
            if (key[guidao] == 1) {
                EventHandler<ActionEvent> blink = e -> {
                    try {
                        yys[guidao].setFill(new Color(0.6, 0.6, 0.6, 0.5 + 0.1 * ms % 2));
                    } catch (Exception ee) {
                    }
                };
                EventHandler<ActionEvent> normal = eee -> {
                    yys[guidao].setFill(new Color(0.6, 0.6, 0.6, 0.5));
                };
                Timeline fx = new Timeline(new KeyFrame(Duration.millis(16), blink));
                ottimeline.add(fx);
                fx.setOnFinished(e1 -> {
                    ottimeline.remove(fx);
                });
                fx.setCycleCount(10);
                fx.setAutoReverse(true);
                fx.play();
                fx.setOnFinished(normal);
                if (ontap[guidao] != null) {
                    key[guidao] = 0;
//                    int d = (int) ontap[guidao].getTranslateY();
                    double t=ontap[guidao].time;
                    // 判定
//                    if (d < (yyheight - yyheight * 0.1 * speed / 1500)) {
                    if (nowtime-1000<t-81) {
                        l.setTextFill(Color.color(0, 0.8, 0));
                        l.setText("意");
                        earlyCount++;
                        checkhide.stop();
                        l.setOpacity(1);
                        checkhide.play();
//                    } else if (d > (yyheight + yyheight * 0.1 * speed / 1500)) {
                    } else if (nowtime-1000>t+81) {
                        l.setTextFill(Color.GOLD);
                        l.setText("势");
                        lateCount++;
                        checkhide.stop();
                        l.setOpacity(1);
                        checkhide.play();
                    } else {
                        l.setTextFill(Color.DEEPSKYBLUE);
                        l.setText("灵");
                        checkhide.stop();
                        l.setOpacity(1);
                        checkhide.play();
                        perfectCount++;
                        if(nowtime-1000<t+12&&nowtime-1000>t-12)
//                        if(d>(yyheight - yyheight * 0.03 * speed / 1500)&&d<(yyheight + yyheight * 0.03 * speed / 1500))
                        	exactCount++;
                    }
                    // 更新连击
                    combo++;
                    if (combo > maxCombo) maxCombo = combo;

                    yy.getChildren().remove(ontap[guidao]);
                    
                    Ellipse re = new Ellipse(yywidth / 8 - 3 * suofang + guidao * yywidth / 4, yyheight, yywidth / 8 - 3 * suofang, yyheight / 4);
                    re.setFill(new Color(0.9, 0.9, 0.9, 0.3));
                    GaussianBlur gb = new GaussianBlur();
                    gb.setRadius(62 * suofang);
                    re.setEffect(gb);
                    yy.getChildren().add(re);
                   
                    EventHandler<ActionEvent> del = e2 -> {
                        yy.getChildren().remove(re);
                    };
                    Timeline fx1 = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(re.translateYProperty(), 0)),
                            new KeyFrame(Duration.seconds(1), new KeyValue(re.translateYProperty(), yyheight / 2)));
                    ottimeline.add(fx1);
                    fx1.setOnFinished(e1 -> {
                        ottimeline.remove(fx1);
                    });
                    fx1.setCycleCount(1);
                    fx1.play();
                    fx1.setOnFinished(del);
                }
            }
        }

        private void yy(String string) {
            // 重置统计
        	exactCount=0;
            perfectCount = 0;
            earlyCount = 0;
            lateCount = 0;
            missCount = 0;
            combo = 0;
            maxCombo = 0;
            gameFinished = false;
            nowtime=0;
            EventHandler<ActionEvent> listen = e0 -> {
            	nowtime++;
                yyfx(0);
                yyfx(1);
                yyfx(2);
                yyfx(3);           
            };
            Timeline fx = new Timeline(new KeyFrame(Duration.millis(1), listen));
            fx.setCycleCount(Timeline.INDEFINITE);
//            fx.setAutoReverse(true);
            fx.play();
            ottimeline.add(fx);
            fx.setOnFinished(e1 -> {
                ottimeline.remove(fx);
            });
//            g.getChildren().remove(m);
            game.getChildren().add(gyy);
            game.getChildren().add(l);
            songf = 1;

            // 读取谱面文件
            pu pu = new pu();
            String filePath = "src/blendefine/files/pumian"+(hard==0?"---":"-+-")+string+".txt";
            try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
                String line;
                int index = 0;
//                double lastTime = 0;
//                String name=br.readLine();
//                System.out.println(name);
                while ((line = br.readLine()) != null) {
//                    line = line.trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split(" ");
                    if (parts.length < 5) continue;
                    double time = Double.parseDouble(parts[0]);
                    for (int i = 0; i < 4; i++) {
                        int hasNote = Integer.parseInt(parts[i + 1]);
                        if (hasNote == 1) {
                            note n = new note(i, time);
                            pu.notes.put(index++, n);
//                            System.out.println(time);
                        }
                    }
//                    lastTime = time;
                }
                totalNotes = index;
//                pu.time = 100000000;
                pu.nownote = 0;
            } catch (Exception e) {
                e.printStackTrace();
                // 如果文件读取失败，回退到随机生成
//                pu.time = 100000000;
                int last = 0;
                for (int i = 0; i < 1000; i++) {
                    Random r = new Random();
                    int p = r.nextInt(4);
                    int s = r.nextInt(100) + 10;
                    note no = new note(p, last);
                    no.setEffect(mb);
                    pu.notes.put(i, no);
                    last += s;
                }
                totalNotes = 1000;
                pu.nownote = 0;
            }

            EventHandler<ActionEvent> pumian = e -> {
                guidaoonuse[0] = 0;
                guidaoonuse[1] = 0;
                guidaoonuse[2] = 0;
                guidaoonuse[3] = 0;
                for (int notenum = 5; notenum < yy.getChildren().size(); notenum++) {
                    if (yy.getChildren().get(notenum) instanceof note) {
                        note n = (note) yy.getChildren().get(notenum);
                        int d = (int) n.getTranslateY();
                        if (n.ondrop == 0) {
//                            Timeline tml1 = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(n.translateYProperty(), 0)),
//                                    new KeyFrame(Duration.seconds(((yyheight + yyheight * 0.2 * speed / 1500))/ speed), new KeyValue(n.translateYProperty(), (yyheight + yyheight * 0.2 * speed / 1500))));
//                            tml1.setCycleCount(1);
                            
                        	TranslateTransition inTransition = new TranslateTransition(Duration.seconds(((yyheight + yyheight * 0.2 * speed / 1500))/ speed), n);
                        	inTransition.setFromY(0);
            				inTransition.setToY((yyheight + yyheight * 0.2 * speed / 1500));
//            				inTransition.setInterpolator(Interpolator.EASE_BOTH);
            				transs.add(inTransition);
            				inTransition.play();
            				inTransition.setOnFinished(e1 -> {
                                transs.remove(inTransition);
                            });
            				inTransition.play();
                            n.ondrop = 1;
                        }
                        if (d <=0)
                            guidaoonuse[n.guidao] = 1;
//                        if (d > (yyheight + yyheight * 0.2 * speed / 1500)-10*suofang) {
                        if (nowtime-1000>n.time+360) {
                            // Miss
                            yy.getChildren().remove(notenum);
                            if (ontap[n.guidao] == n) {
                                ontap[n.guidao] = null;
                            }
                            l.setTextFill(Color.RED);
                            l.setText("伤");
                            checkhide.stop();
                            l.setOpacity(1);
                            checkhide.play();
                            missCount++;
                            combo = 0; // 重置连击
                        }
                        if (d + n.getHeight() > (yyheight - yyheight * 0.2 * speed / 1500) && ontap[n.guidao] == null) {
                            ontap[n.guidao] = n;
                            Timeline delnownote = new Timeline(new KeyFrame(Duration.seconds(0.2), e1 -> {
                                if (null != ontap[n.guidao] && ontap[n.guidao].equals(n)) {
                                    ontap[n.guidao] = null;
                                }
                            }));
                            ottimeline.add(delnownote);
                            delnownote.setOnFinished(e1 -> {
                                ottimeline.remove(delnownote);
                            });
                            delnownote.play();
                        }
                    }
                }
                if (pu.notes.containsKey(pu.nownote)) {
                    note n = pu.notes.get(pu.nownote);
                    notetime = n.time;
                    if (ms*2 >= notetime) {
                        if (guidaoonuse[n.guidao] == 0) {
                            yy.getChildren().add(n);
                        }
                        pu.nownote++;
                        while(pu.notes.containsKey(pu.nownote)&&pu.notes.get(pu.nownote).time==notetime) {
                        	if (guidaoonuse[pu.notes.get(pu.nownote).guidao] == 0) {
                                yy.getChildren().add(pu.notes.get(pu.nownote));
                            }
                        	pu.nownote++;
                        }
                    }
                    
                }
//                new p(songlength+" "+nowtime+'\n');
                // 检测游戏结束
                if (nowtime>songlength
//                		||pu.nownote >= totalNotes
                		) {
                	if(pausef!=2) {
                		pausef=2;
	                	Timeline t = new Timeline(new KeyFrame(Duration.millis(yyheight/speed*1000+700),e1->{
	                    	new p("kk\n");
	//                      boolean hasNote = false;
	//                      for (int j = 5; j < yy.getChildren().size(); j++) {
	//                          if (yy.getChildren().get(j) instanceof note) {
	//                              hasNote = true;
	//                              break;
	//                          }
	//                      }
	//                      if (!hasNote) {
	//                      	new p("nn\n");
	                          gameFinished = true;
	                          tml.stop();
	                          showScore(string);
	//                      }
	                	}));
	                	t.play();
                	}
                }
                
            };
            tml = new Timeline(new KeyFrame(Duration.ZERO,e->{ms ++;}),new KeyFrame(Duration.millis(1), pumian));
            tml.setCycleCount(Timeline.INDEFINITE);
            tml.setAutoReverse(true);
            tml.play();
        }
        private void showScore(String name) {
	            // 计算分数
	            double baseScore = (perfectCount * 100/totalNotes + (earlyCount + lateCount) * 80/totalNotes); // 基础分（百分比）
	            double comboBonus = 8.0 * maxCombo / totalNotes+2.0*exactCount/ totalNotes; // 连击加分最多8%
	            double totalScore = baseScore + comboBonus;
	            if (totalScore > 110) totalScore = 110;
	            double wscore=totalScore;
	            // 创建结算面板
	            scorePane = new StackPane();
	            scorePane.setPrefSize(yywidth, yyheight);
	            scorePane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));
	            scorePane.setTranslateY(0);
	
	            VBox vbox = new VBox(10 * suofang);
	            vbox.setAlignment(Pos.CENTER);
	            vbox.setMaxWidth(400 * suofang);
	            vbox.setMaxHeight(600 * suofang);
	//            vbox.setStyle("-fx-background-color: rgba(50,50,50,0.9); -fx-background-radius: 20; -fx-padding: 30;");
	//            vbox.setEffect(ds);
	            
	            Label title = new Label("剑凭");
	            title.setFont(f0);
	            title.setTextFill(Color.WHITE);
	            title.setEffect(ds);
	
	            Label perf = new Label("灵 " + perfectCount);
	            perf.setFont(f1);
	            perf.setTextFill(Color.DEEPSKYBLUE);
	            perf.setEffect(ds);
	            Label early = new Label("意 " + earlyCount);
	            early.setFont(f1);
	            early.setTextFill(Color.color(0.2,0.9,0.2));
	            early.setEffect(ds);
	            Label late = new Label("势 " + lateCount);
	            late.setFont(f1);
	            late.setTextFill(Color.GOLD);
	            late.setEffect(ds);
	            Label comboLabel = new Label("连 " + maxCombo);
	            comboLabel.setFont(f1);
	            comboLabel.setTextFill(Color.ORANGE);
	            comboLabel.setEffect(ds);
	            Label miss = new Label("伤 " + missCount);
	            miss.setFont(f1);
	            miss.setTextFill(Color.color(1,0.2,0.2));
	            miss.setEffect(ds);
	            String sname="";
	            if(wscore>=109)sname="混元";
	            else if(wscore>=108)sname="灵元";
	            else if(wscore>=103)sname="天元";
	            else if(wscore>=95)sname="地元";
	            else if(wscore>=81)sname="玄元";
	            else if(wscore>0)sname="黄元";
	            else sname="未元";
	            String wsname=sname;
	            Label score = new Label(sname+String.format(" %.2f%%", wscore));
	            score.setFont(f2);
	            DropShadow ds1 = new DropShadow(3 * suofang, Color.color(0,0,0, 0.9));
	            ds1.setSpread(0.7);
	            score.setEffect(ds1);
	            Group gl=new Group(score);
	            gl.setEffect(ds);
	            
	            songs.forEach(e->{
	         		if(e.getTitle().equals(name)) {
		            	Timeline bgkira1=new Timeline(
		                		new KeyFrame(Duration.millis(4000/(e.getbpm()/60)), new KeyValue(ds1.radiusProperty(),80)));
		                bgkira1.setAutoReverse(true);
		                bgkira1.setCycleCount(Timeline.INDEFINITE);
		                bgkira1.setDelay(Duration.millis(e.getBpmlate()));
		         		ottimeline.add(bgkira1);
		         		bgkira1.play();
	         		}
	            });
	           
	            setscorecolor(wscore,score);
	
	            double lscore=0;
	            if(songsscores.containsKey(name+(hard==0?"":"h"))) {
		            String sc=songsscores.get(name+(hard==0?"":"h"));
		            String[] scps=sc.split("\n");
		            String[] scps1=scps[5].split(" ");
		            lscore=Double.parseDouble(scps1[1].replace("%",""));
	            }
	            if(wscore>lscore) {
	//            Button saveBtn = new Button("\\\\保存\\\\");
	//            saveBtn.setFont(f1);
	//            saveBtn.setTextFill(Color.WHITE);
	//            saveBtn.setBackground(backtm);
	//            saveBtn.setOnAction(e -> {
	//                // 保存分数到文件
	            	String result="Perfect " + perfectCount + "\n"+"Early " + earlyCount + "\n"+
	                 		"Late " + lateCount + "\n"+"Miss " + missCount + "\n"+"MaxCombo " + maxCombo + "\n"+
	                 		wsname+String.format(" %.2f%%\n", wscore)+latency+" "+speed;
	                String resultPath = "src/blendefine/files/pumian"+(hard==0?"===":"=+=")+name+".txt";
	                try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(resultPath))) {
	                    bw.write(result);
	                    bw.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                songsscores.remove(name+(hard==0?"":"h"));
	                songsscores.put(name+(hard==0?"":"h"), result);
	                lastselectsong="";      
	//                back();
	//            });
	        	}
	            vbox.getChildren().addAll(title, perf, early, late,comboLabel, miss, gl);
	            scorePane.getChildren().add(vbox);
	            scorePane.setOnMouseClicked(e->{
	            	if (e.getButton()==MouseButton.MIDDLE) {
	                	if(pausef==0) {
	                		pausef=1;
		                	tml.pause();
		                	game.getChildren().remove(scorePane);
		                    showScore(name);
		                    ottimeline.forEach(e1->{
		                    	e1.pause();
		                    });
	                	}
	                	else {
	                		pausef=0;
		                	tml.play();
		                	game.getChildren().remove(scorePane);
		                    ottimeline.forEach(e1->{
		                    	e1.play();
		                    });
	                	}
	                }
	            });
	           
	            Platform.runLater(()->{
	            	 game.getChildren().add(scorePane);
	            	 b.toFront();
	            	 Timeline t=new Timeline(new KeyFrame(Duration.seconds(0),new KeyValue(scorePane.opacityProperty(),0),new KeyValue(b.opacityProperty(),0)),
	                 		new KeyFrame(Duration.seconds(0.8),new KeyValue(scorePane.opacityProperty(),1),new KeyValue(b.opacityProperty(),1)));
	                 t.play();
	            });
        }

        class note extends Rectangle {
            int guidao, len, ondrop;
            double time;

            note(int i, double tm) {
                super(yywidth / 4 * i + 2 * suofang, -20 * suofang, yywidth / 4 - 5 * suofang, 20 * suofang);
                guidao = i;
                time = tm;
                setFill(rg[i]);
            }

            String getName() {
                return "note";
            }
        }

        class pu {
            int time;
            int nownote;
            HashMap<Integer, note> notes = new HashMap<Integer, note>();
            pu() {
            }
        }
        /**
         * 初始化背景层：包含图片、模糊效果、暗角遮罩
         */
        private void initBackgroundLayer() {
            backgroundViewer = new ImageView();
            backgroundViewer.setPreserveRatio(true); // 拉伸填满
            backgroundViewer.setSmooth(true);
//            backgroundViewer.setTranslateY(-yyheight/4);
//            // 让图片跟随窗口大小
//            backgroundViewer.fitWidthProperty().bind(game.widthProperty());
//            backgroundViewer.fitHeightProperty().bind(game.heightProperty());
//            backgroundViewer.setPreserveRatio(true);
//            Platform.runLater(()->{
//            	try {
//            	 Image image = backgroundViewer.getImage();
//                 double imageRatio = image.getWidth() / image.getHeight();
//
//                 // 监听根节点大小变化
//                 game.widthProperty().addListener((obs, oldVal, newVal) -> updateFit(imageRatio));
//                 game.heightProperty().addListener((obs, oldVal, newVal) -> updateFit(imageRatio));
//            	}catch(Exception e) {}
//            });
           
            
            
            // 设置模糊和变暗效果
            GaussianBlur blur = new GaussianBlur(30*suofang); // 模糊半径
            ColorAdjust darken = new ColorAdjust(0, 0, -0.8, 0); // 亮度降低40%
            darken.setInput(blur);
            backgroundViewer.setEffect(darken);
            backgroundViewer.setOnMouseClicked(e->{
            	startyy();
            });
            
            game.getChildren().add(backgroundViewer);
        }
        public void startyy() {
        	 Song selected = songListView.getSelectionModel().getSelectedItem();
//             System.out.println("开始游戏: " + selected.getTitle());
             yy.setOpacity(0);
             nowplayname=selected.getTitle();
             songlength=selected.getMedia().getDuration().toMillis();
             yy(selected.getTitle());
             tml.pause();
             ottimeline.forEach(e1->{
             	e1.pause();
             });
             try {
             bdf.mp.stop();
             }catch(Exception e1) {}
            	 Timeline t1 = new Timeline(
                		 new KeyFrame(Duration.seconds(0),
                          		new KeyValue(contentLayer.opacityProperty(),1),
                          		new KeyValue(yy.opacityProperty(),0),
                          		new KeyValue(l.opacityProperty(),0)),
                		 new KeyFrame(Duration.seconds(0.3),
                 		new KeyValue(contentLayer.opacityProperty(),0),
                 		new KeyValue(yy.opacityProperty(),1),
                 		new KeyValue(l.opacityProperty(),1)));
//         		ottimeline.add(t1);
            	 double ltc=yyheight/speed;
                 t1.setOnFinished(e1->{ 
                     b.toFront();
                     yy.requestFocus();
                    
                     Timeline t2= new Timeline(
                    		
                    		 new KeyFrame(Duration.seconds(1+ltc),e->{
                    			 try {
                    			 bdf.mp.play();
                    			 }catch(Exception e2) {}
                    		 }));
                     t2.play();
                     Timeline t3= new Timeline(
                    		 new KeyFrame(Duration.millis(1000+latency),e->{
                    			 tml.play();
                                 ottimeline.forEach(e2->{
                                 	e2.play();
                                 });
                    		 }));
                     t3.play(); 
                 });
         		t1.play();
        }
        
        public void setscorecolor(double wscore,Node score) {
        	if(wscore>=109) {
        		if(score.getClass().equals(Label.class)) {
	            	EventHandler<ActionEvent> ha = e -> {
	        			Color c1;
	        			double i = 0.3;
	        			if (green < 255 && blue < 255 && cf == 0)
	        				green += i;
	        			if (green > 254 && red > 1 && blue < 255)
	        				red -= i;
	        			if (red < 2 && blue < 255)
	        				blue += i;
	        			if (blue > 254 && green > 1)
	        				green -= i;
	        			if (green < 2 && red < 255)
	        				red += i;
	        			if (red > 254 && green < 2 && blue > 1) {
	        				blue -= i;
	        				cf = 1;
	        			}
	        			if (red > 254 && blue < 2)
	        				cf = 0;
	        			c1 = Color.color(red / 256, green / 256, blue / 256);
	        			
	        				((Labeled) score).setTextFill(c1);
	        			
	        		};
	        		Timeline rgbt = new Timeline(new KeyFrame(Duration.millis(3), ha));
	        		rgbt.setCycleCount(Timeline.INDEFINITE);
	        		ottimeline1.add(rgbt);
	        		rgbt.play();
        		}
        		else ((Shape) score).setFill(Color.color(1, 0, 1));
            }
            else if(wscore>=108) {
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.DEEPSKYBLUE);
    			else ((Shape) score).setFill(Color.DEEPSKYBLUE);
            }
            else if(wscore>=103) {
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.WHITE);
    			else ((Shape) score).setFill(Color.WHITE);
            }
            else if(wscore>=95) {
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.BLACK);
    			else ((Shape) score).setFill(Color.BLACK);
            }
            else if(wscore>=81) {
//            	if(score.getClass().equals(Label.class))
//    				((Labeled) score).setTextFill(Color.color(0.21, 0.03, 0.03));
//    			else ((Shape) score).setFill(Color.color(0.21, 0.03, 0.03));
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.color(0.61, 0.23, 0.23));
    			else ((Shape) score).setFill(Color.color(0.61, 0.23, 0.23));
            }
            else if(wscore>0) {
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.GOLD);
    			else ((Shape) score).setFill(Color.GOLD);
            }
            else {
            	if(score.getClass().equals(Label.class))
    				((Labeled) score).setTextFill(Color.RED);
    			else ((Shape) score).setFill(Color.RED);
            }
        }
        /**
         * 初始化前景UI：左侧列表和底部信息栏
         */
        private void initForegroundUI() {
            // --- 左侧：歌曲列表 ---
            songListView = new ListView<>();
            songListView.setItems(javafx.collections.FXCollections.observableArrayList(songs));
            
            // 关键：设置列表透明背景
            songListView.setBackground(Background.EMPTY);
            songListView.setPadding(new Insets(10*suofang));
            songListView.setStyle("-fx-background-color: transparent; ");
            songListView.setStyle(
                    "-fx-background-color: rgba(0, 0, 0, 0.4);" + // 半透明黑色
                    "-fx-background-radius: 10;" +                // 圆角
                    "-fx-border-color: rgba(255, 255, 255, 0.5);" +// 微弱边框
                    "-fx-border-width:  " + (37 * suofang) + ";"+
                    "-fx-border-radius: 10;"
                );
            songListView.setCellFactory(list -> new SongCell());

            Platform.runLater(()->{
            	 // 获取垂直ScrollBar
                ScrollBar vBar = (ScrollBar) songListView.lookup(".scroll-bar:vertical");
                if (vBar != null) {
                	vBar.setOpacity(0);
                }

            });
        
            // 设置列表宽度，让其靠左
            songListView.setMaxWidth(320*suofang);
            songListView.setMinWidth(320*suofang);
            songListView.setMinHeight(yyheight*1.5);
            songListView.setTranslateX(80*suofang);
            songListView.setPickOnBounds(false);
            songListView.setRotate(rotates);
//            songListView.setPadding(new Insets(50*suofang, 10*suofang, 50*suofang, 10*suofang));
//            songListView.setPrefHeight(1000*suofang);

//            songListView.setOnScroll(e->{
//            	if(e.getDeltaY()>20) {
//            		songListView.getSelectionModel().selectPrevious();
//            	}
//            	if(e.getDeltaY()<20) {
//            		songListView.getSelectionModel().selectNext();
//            	}
//            });
            songListView.setOnKeyPressed(e->{
            	if(e.getCode().equals(KeyCode.SPACE)) {
            		startyy();
            	}
            	if(e.getCode().equals(KeyCode.W)) {
            		songListView.getSelectionModel().selectPrevious();
            	}
            	if(e.getCode().equals(KeyCode.S)) {
            		songListView.getSelectionModel().selectNext();
            	}
            	if(e.getCode().equals(KeyCode.ESCAPE)) {
            		back();
            	}
            });
            // --- 右侧：底部信息面板 ---
           

            // 标题和艺术家标签
            titleLabel = new Label();
            titleLabel.setFont(f1); // 字体大小
            titleLabel.setTextFill(Color.WHITE);
            titleLabel.setStyle("-fx-font-weight: bold;");
            
            artistLabel = new Label();
            artistLabel.setFont(f1);
            artistLabel.setTextFill(Color.LIGHTGRAY);
            
            scoreLabel = new Label();
            scoreLabel.setFont(f0);
            scoreLabel.setTextFill(Color.LIGHTGRAY);
            
            
            textBox= new VBox(5* suofang, titleLabel, artistLabel, scoreLabel);
            textBox.setStyle(
                    "-fx-background-color: rgba(0, 0, 0, 0.4);" + // 半透明黑色
                    "-fx-background-radius: 10;" +                // 圆角
                    "-fx-border-color: rgba(255, 255, 255, 0.1);" +// 微弱边框
                    "-fx-border-width: 0;"+
                    "-fx-border-radius: 10;"
                );
            textBox.setPadding(new Insets(10* suofang,30*suofang,10*suofang,30*suofang));
            textBox.setAlignment(Pos.TOP_CENTER);
            textBox.setTranslateX(-140*suofang);
            textBox.setTranslateY(-20*suofang);
            
            hardLabel = new Label("点水");
            hardLabel.setFont(f2);
            hardLabel.setStyle("-fx-font-weight: bold;");
            hardBox= new VBox(5* suofang, hardLabel);
            
            hardLabel.setTextFill(Color.color(0.2,0.7,0,1));
            hardBox.setStyle(
                    "-fx-background-color: rgba(0, 77, 0, 0.2);" + // 半透明黑色
                    "-fx-background-radius: 10;" +                // 圆角
                    "-fx-border-color: rgba(51, 179, 0, 0.4);" +// 微弱边框
                    "-fx-border-width:0;"+
                    "-fx-border-radius: 10;"
                );
            
            hardBox.setPadding(new Insets(25* suofang,40*suofang,25*suofang,40*suofang));
//            hardBox.setMaxHeight(50*suofang);
            hardBox.setAlignment(Pos.TOP_CENTER);
            hardBox.setTranslateX(-100*suofang);
            hardBox.setTranslateY(-20*suofang);
            
            Timeline switchhard1=new Timeline(new KeyFrame(Duration.seconds(0.1),new KeyValue(backgroundViewer.opacityProperty(),0),
            		new KeyValue(hardBox.opacityProperty(),0),new KeyValue(hardBox.scaleXProperty(),0.9),new KeyValue(hardBox.scaleYProperty(),0.9)));
            Timeline switchhard2=new Timeline(new KeyFrame(Duration.seconds(0),
            		new KeyValue(hardBox.opacityProperty(),0.3),new KeyValue(backgroundViewer.opacityProperty(),0.3),
            		new KeyValue(hardBox.scaleXProperty(),1.1),new KeyValue(hardBox.scaleYProperty(),1.1)),
            		new KeyFrame(Duration.seconds(0.1),
            		new KeyValue(hardBox.opacityProperty(),1),new KeyValue(backgroundViewer.opacityProperty(),1),
            		new KeyValue(hardBox.scaleXProperty(),1),new KeyValue(hardBox.scaleYProperty(),1)));
            
            hardBox.setOnMouseClicked(e->{
            	switchhard1.play();
            	switchhard1.setOnFinished(e1->{
            		if(hard==0) {
                		hard=1;
                		Blend b=new Blend(BlendMode.RED);
                		contentLayer.setEffect(b);
//                		yy.setEffect(b);
                		hardLabel.setText("燎原");
                		hardLabel.setEffect(ds3);
                		hardLabel.setTextFill(Color.color(0.5,0.5,0,1));
                        hardBox.setStyle(
                                "-fx-background-color: rgba(77,0 , 0, 0.4);" + // 半透明黑色
                                "-fx-background-radius: 10;" +                // 圆角
                                "-fx-border-color: rgba(179, 51, 0, 0.4);" +// 微弱边框
                                "-fx-border-width:0;"+
                                "-fx-border-radius: 10;"
                        );
                        GaussianBlur blur = new GaussianBlur(30*suofang); // 模糊半径
                        ColorAdjust darken = new ColorAdjust(0, 0, -0.65, 0.3); // 亮度降低40%
                        darken.setInput(blur);
                        backgroundViewer.setEffect(darken);
                        Timeline asht=new Timeline(new KeyFrame(Duration.seconds(1),new KeyValue(ash.opacityProperty(),1)));
                        asht.play();
                	}
                	else {
                		hard=0;
                		contentLayer.setEffect(null);
                		hardLabel.setText("点水");
                		hardLabel.setEffect(null);
//                		yy.setEffect(null);
                		hardLabel.setTextFill(Color.color(0.2,0.7,0,1));
                        hardBox.setStyle(
                                "-fx-background-color: rgba(0, 77, 0, 0.2);" + // 半透明黑色
                                "-fx-background-radius: 10;" +                // 圆角
                                "-fx-border-color: rgba(51, 179, 0, 0.4);" +// 微弱边框
                                "-fx-border-width:0;"+
                                "-fx-border-radius: 10;"
                        );
                        GaussianBlur blur = new GaussianBlur(30*suofang); // 模糊半径
                        ColorAdjust darken = new ColorAdjust(0, 0, -0.8, 0); // 亮度降低40%
                        darken.setInput(blur);
                        backgroundViewer.setEffect(darken);
                        Timeline asht=new Timeline(new KeyFrame(Duration.seconds(1),new KeyValue(ash.opacityProperty(),0)));
                        asht.play();
                	}
            		switchhard2.play();
            		try {
	                    String sc=songsscores.get(songListView.getSelectionModel().getSelectedItem().getTitle()+(hard==0?"":"h"));
	                    double score=0;
	                    String[] scps=sc.split("\n");
	                    String[] scps1=scps[5].split(" ");
	                    scoreLabel.setText(scps[5]);
	                    score=Double.parseDouble(scps1[1].replace("%",""));      
	                    try {
    	                    String[] scps2=scps[6].split(" ");
    	                    latency=Double.parseDouble(scps2[0]);
    	                    speed=Double.parseDouble(scps2[1]);
    	                    latencyslider.setValue(50+latency*0.5);
    	                    speedslider.setValue(speed/40);
	                    }catch(Exception e2) {
	                    	e2.printStackTrace();
	                    }
	                    if(score>=109) {
	                    	setscorecolor(score,scoreLabel);
	                    }
	                    else {
	                    	double sc1=score;
	                    	ottimeline1.forEach(e2->{
                        		e2.pause();
                        		e2=null;
                        	});
	                    	Platform.runLater(()->{
	                    		setscorecolor(sc1,scoreLabel);
	                    	});
	                    }
                	
	                }catch(Exception e2) {
	                	ottimeline1.forEach(e3->{
	                		e3.pause();
	                		e3=null;
	                	});
	                	Platform.runLater(()->{
	                		scoreLabel.setTextFill(Color.RED);
	                		scoreLabel.setText("未元");
	                	});   	
	                }
            	});
            });
            
            latencyslider = new Slider();
            speedslider = new Slider();
            Platform.runLater(()->{
            	bl1.setThreshold(0);
            	hardBox.setEffect(bl1);
            	 Node track = latencyslider.lookup(".track");
                 if (track != null) {
                     track.setStyle(
                         "-fx-background-color: rgba(30, 30, 36, 0.85);" +
                         "-fx-background-radius: 4px;"
                     );
                 }
                 // 查找滑块 (thumb)
                 Node thumb = latencyslider.lookup(".thumb");
                 if (thumb != null) {
                     thumb.setStyle(
                    		 "-fx-background-color: rgba(255, 255, 255, 0.4);" + // 滑块颜色：白色半透明
                 	                "-fx-background-radius: 10;" +                     // 滑块圆形
                 	                "-fx-pref-width: 16px;" +                          // 滑块宽度
                 	                "-fx-pref-height: 16px;" +                         // 滑块高度
                 	                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0, 0, 1);" // 阴影效果
                     );
                 }
                 // 查找刻度标签区域 (axis)
                 Node axis = latencyslider.lookup(".axis");
                 if (axis != null) {
                     axis.setStyle("-fx-tick-label-fill: rgba(220, 220, 240, 0.7);");
                 }
                 Node track1 = speedslider.lookup(".track");
                 if (track1 != null) {
                     track1.setStyle(
                         "-fx-background-color: rgba(30, 30, 36, 0.85);" +
                         "-fx-background-radius: 4px;"
                     );
                 }
                 // 查找滑块 (thumb)
                 Node thumb1 = speedslider.lookup(".thumb");
                 if (thumb1 != null) {
                     thumb1.setStyle(
                    		 "-fx-background-color: rgba(255, 255, 255, 0.4);" + // 滑块颜色：白色半透明
                 	                "-fx-background-radius: 10;" +                     // 滑块圆形
                 	                "-fx-pref-width: 16px;" +                          // 滑块宽度
                 	                "-fx-pref-height: 16px;" +                         // 滑块高度
                 	                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0, 0, 1);" // 阴影效果
                     );
                 }
                 // 查找刻度标签区域 (axis)
                 Node axis1 = speedslider.lookup(".axis");
                 if (axis1 != null) {
                     axis1.setStyle("-fx-tick-label-fill: rgba(220, 220, 240, 0.7);");
                 }
            });
                // 将样式应用到Slider
            
            latencyslider.setPrefWidth(100*suofang);
            speedslider.setPrefWidth(100*suofang);
            
            latencyslider.setValue(50+latency*0.5);
            speedslider.setValue(speed/40);
            
            latencyslider.valueProperty().addListener((observable, oldValue, newValue) -> {
            	latency=(latencyslider.getValue()-50)*2;
            });
            latencyslider.setOnMouseClicked(e->{
            	if(e.getButton()==MouseButton.SECONDARY) {
	            	latencyslider.setValue(50);
	            	latencyslider.setEffect(bl2);
	            	Timeline backnormal=new Timeline(new KeyFrame(Duration.seconds(0),
	                		new KeyValue(bl2.thresholdProperty(),0))
	            			,new KeyFrame(Duration.seconds(0.3),
	                		new KeyValue(bl2.thresholdProperty(),1)));
	            	backnormal.setOnFinished(e1->{
	            		latencyslider.setEffect(null);
	            	});
	            	backnormal.play();
	            	latency=0;
            	}
            });
            latencyslider.setOnMouseMoved(e->{
            	nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
            });
            latencyslider.setOnMouseDragged(e->{
            	nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
            });
            
            speedslider.valueProperty().addListener((observable, oldValue, newValue) -> {
                speed=speedslider.getValue()*40;
            });
            speedslider.setOnMouseClicked(e->{
            	if(e.getButton()==MouseButton.SECONDARY) {
	            	speedslider.setValue(25);
	            	speedslider.setEffect(bl2);
	            	Timeline backnormal=new Timeline(new KeyFrame(Duration.seconds(0),
	                		new KeyValue(bl2.thresholdProperty(),0))
	            			,new KeyFrame(Duration.seconds(0.3),
	                		new KeyValue(bl2.thresholdProperty(),1)));
	            	backnormal.setOnFinished(e1->{
	            		speedslider.setEffect(null);
	            	});
	            	backnormal.play();
	            	speed=1000;
            	}
            });
            speedslider.setOnMouseMoved(e->{
            	nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
            });
            speedslider.setOnMouseDragged(e->{
            	nowmousex=e.getScreenX();
				nowmousey=e.getScreenY();
            });
            
            Label latencyLabel = new Label();
            latencyLabel.setFont(f1);
            latencyLabel.setTextFill(Color.LIGHTGRAY);
            latencyLabel.setText("先决");
            Label speedLabel = new Label();
            speedLabel.setFont(f1);
            speedLabel.setTextFill(Color.LIGHTGRAY);
            speedLabel.setText("识流");
            
            VBox adjustBox = new VBox(5* suofang,latencyLabel, latencyslider,speedLabel, speedslider);
            adjustBox.setStyle(
                    "-fx-background-color: rgba(0, 0, 0, 0.4);" + // 半透明黑色
                    "-fx-background-radius: 10;" +                // 圆角
                    "-fx-border-color: rgba(255, 255, 255, 0.1);" +// 微弱边框
                    "-fx-border-width: 0;"+
                    "-fx-border-radius: 10;"
                );
            adjustBox.setPadding(new Insets(20* suofang));
            adjustBox.setAlignment(Pos.TOP_CENTER);
            adjustBox.setTranslateX(-120*suofang);
            adjustBox.setTranslateY(-20*suofang);
            
            infoPanel.setAlignment(Pos.TOP_RIGHT);
            infoPanel.getChildren().add(textBox);
            infoPanel.getChildren().add(adjustBox);
            
            infoPanel.getChildren().add(hardBox);
            infoPanel.setTranslateY(-yyheight/2+130*suofang);

            
            // --- 布局组合 ---
            // 使用 BorderPane 将列表放在左侧，信息放在底部
           
            contentLayer.setPrefSize(yywidth, yyheight);
            contentLayer.setLeft(songListView);
            contentLayer.setCenter(foreimage=new ImageView());
            foreimage.setFitWidth(yywidth/5*2);
            foreimage.setTranslateX(yywidth/10);
            foreimage.setPreserveRatio(true);
//            foreimage.setFitWidth(yyheight/2);
            foreimage.setOnMouseClicked(e->{
            	startyy();
            });
            
            
            contentLayer.setBottom(infoPanel);
            BorderPane.setMargin(infoPanel, new Insets(0, 20* suofang, 20* suofang, 340* suofang)); // 左边距留出列表空间
            contentLayer.setPickOnBounds(false);
            // 设置左侧列表的内边距
            BorderPane.setMargin(songListView, new Insets(50* suofang, 0, 0, 20* suofang));
            
            // 添加到根布局
            game.getChildren().add(contentLayer);
            contentLayer.setTranslateY(-yyheight*3/8);
            // --- 监听器 ---
            setupListeners();
        }
      
        /**
         * 自定义列表单元格样式 (Phigros 风格)
         */
        private class SongCell extends ListCell<Song> {
            private final HBox hbox = new HBox();
            private final Label label = new Label();
            private final Rectangle indicator = new Rectangle(4* suofang, 20* suofang);
            
            // 定义字体大小常量
//            private double FONT_SIZE_NORMAL = 16* suofang;
//            private double FONT_SIZE_LARGE = 18* suofang;

            public SongCell() {
                super();
                
                setPadding(new Insets(0));

                hbox.setSpacing(10* suofang);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setPadding(new Insets(10* suofang));
                hbox.setMaxWidth(Double.MAX_VALUE);
                
                indicator.setFill(Color.TRANSPARENT);
                indicator.setArcWidth(2* suofang);
                indicator.setArcHeight(2* suofang);
                indicator.setEffect(ds2);
                
                // 设置基础字体（后续在 updateItem 中会覆盖）
                label.setFont(f3);
                label.setTextFill(Color.rgb(200, 200, 200));

                hbox.getChildren().addAll(indicator, label);
                setGraphic(hbox);
                setRotate(-rotates);
                setTranslateY(yyheight/2-40*suofang);
                setPickOnBounds(false);
                // 监听 hbox 的 hover 状态，变化时刷新单元格样式
               
                Timeline t=new Timeline(new KeyFrame(Duration.millis(32),e->{
                	  updateItem(getItem(), isEmpty());
                }));
                t.setCycleCount(Timeline.INDEFINITE);
                t.play();
                ottimeline.add(t);
            }

            @Override
            protected void updateItem(Song item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    label.setText(item.getTitle());
                    setGraphic(hbox);
//                    if(item.index==999&&maxf==0) {
//                        	maxf=1;
//                        	label.setPrefHeight(999);
//                    }
                    String qname=item.getTitle();
                    if (isSelected()&&!item.getTitle().equals("")) {
                    	String name=item.getTitle();
                    	if(!name.equals(lastselectsong)) {
                    		
                    		textBox.setEffect(bl);
                    		songListView.setEffect(bl);
                    		if(bgkira!=null)bgkira.stop();
                            bgkira=new Timeline(new KeyFrame(Duration.millis(0),new KeyValue(bl.thresholdProperty(),0.5))
                            		,new KeyFrame(Duration.millis(500/(item.getbpm()/60)), new KeyValue(bl.thresholdProperty(),1)));
                            bgkira.setAutoReverse(true);
                            bgkira.setCycleCount(Timeline.INDEFINITE);
                            bgkira.setDelay(Duration.millis(item.getBpmlate()));
                            bgkira.play();
                            
                    		lastselectsong=name;
	                        // 选中状态：字体放大、加粗、亮色指示器、半透明背景
	                        label.setFont(f4);
	                        label.setTextFill(Color.WHITE);
	                        label.setStyle("-fx-font-weight: bold;");
	                        indicator.setFill(Color.web("#00DDFF"));
	                        scoreLabel.setEffect(ds2);
	                        try {
		    	                    String sc=songsscores.get(item.getTitle()+(hard==0?"":"h"));
		    	                    double score=0;
		    	                    String[] scps=sc.split("\n");
		    	                    String[] scps1=scps[5].split(" ");
		    	                    scoreLabel.setText(scps[5]);
		    	                    score=Double.parseDouble(scps1[1].replace("%",""));      
		    	                   
		    	                    try {
			    	                    String[] scps2=scps[6].split(" ");
			    	                    latency=Double.parseDouble(scps2[0]);
			    	                    speed=Double.parseDouble(scps2[1]);
			    	                    latencyslider.setValue(50+latency*0.5);
			    	                    speedslider.setValue(speed/40);
		    	                    }catch(Exception e2) {
		    	                    	e2.printStackTrace();
		    	                    }
		    	                    if(score>=109) {
//		    	                    	if(ottimeline1.size()>0) {
//			                        		ottimeline1.forEach(e->{
//			                            		e.play();
//			                            	});
//			                        	}
		    	                    	setscorecolor(score,scoreLabel);
		    	                    }
		    	                    else {
		    	                    	double sc1=score;
		    	                    	setscorecolor(sc1,indicator);
//		    	                    	System.out.println(sc1);
		    	                    	ottimeline1.forEach(e->{
		                            		e.pause();
		                            		e=null;
		                            	});
		    	                    	Platform.runLater(()->{
		    	                    		setscorecolor(sc1,scoreLabel);
		    	                    	});
		    	                    }
	                        	
	                        }catch(Exception e1) {
//	                        	e1.printStackTrace();
	                        	ottimeline1.forEach(e->{
                            		e.pause();
                            		e=null;
                            	});
	                        	Platform.runLater(()->{
	                        		scoreLabel.setTextFill(Color.RED);
	                        		scoreLabel.setText("未元");
//		                        	indicator.setFill(Color.web("#FF0000"));
    	                    	});   	
	                        }
	                        hbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 5;");
                    	}
                    } else {
                        // 非选中状态：根据是否悬停决定字体大小和背景
                        if (hbox.isHover()&&!item.getTitle().equals("")) {
                            // 悬停样式：字体放大，背景轻微高亮，颜色保持灰色
                            label.setFont(f4);
                            label.setTextFill(Color.rgb(200, 200, 200));
                            label.setStyle("-fx-font-weight: normal;");
                            indicator.setFill(Color.TRANSPARENT);
                            hbox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); -fx-background-radius: 5;");
                        } else {
                            // 默认非选中样式：完全透明背景，正常字体
                            label.setFont(f3);
                            label.setTextFill(Color.rgb(200, 200, 200));
                            label.setStyle("-fx-font-weight: normal;");
                            indicator.setFill(Color.TRANSPARENT);
                            hbox.setStyle("-fx-background-color: transparent;");
                        }
                    }
                    if(!qname.equals(""))
                        try {
    	                    String sc=songsscores.get(item.getTitle()+(hard==0?"":"h"));
    	                    double score=0;
    	                    String[] scps=sc.split("\n");
    	                    String[] scps1=scps[5].split(" ");
    	                    score=Double.parseDouble(scps1[1].replace("%",""));
//    	                    scoreLabel.setText(scps[5]);
    	                    setscorecolor(score,indicator);
    	                }catch(Exception e) {indicator.setFill(Color.TRANSPARENT);}
                }
            }
        }

        /**
         * 设置事件监听
         */
        private void setupListeners() {
            songListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    // 1. 更新背景图 (带动画)
                    switchBackground(newVal.getImage());
                    
                    // 2. 更新底部信息
                    titleLabel.setText(newVal.getTitle());
                    artistLabel.setText(newVal.getArtist());
                    
                    try {
	                    String sc=songsscores.get(newVal.getTitle()+(hard==0?"":"h"));
	                    String[] scps=sc.split("\n");
	                    String[] scps1=scps[5].split(" ");
	                    double score=Double.parseDouble(scps1[1].replace("%",""));
	                    scoreLabel.setText(scps[5]);
	                    setscorecolor(score,scoreLabel);
                    }catch(Exception e1) {
//                    	e1.printStackTrace();
                    }
                    
                    // 3. 播放音乐
                    bdf.playMusic(newVal.getMedia());
                    
                    // 4. 点击反馈动画
                    animateInfoPanel();
                }
            });
            
            // 点击事件 (双击开始游戏)
//            songListView.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2) {
//                    Song selected = songListView.getSelectionModel().getSelectedItem();
//                    System.out.println("开始游戏: " + selected.getTitle());
//                    yy(selected.getIndex());
                    // 这里可以跳转场景
//                }
//            });
           
        }

        /**
         * 切换背景图动画
         */
        private void switchBackground(Image newImage) {
            if (bgSwitchTimeline != null) bgSwitchTimeline.stop();
            
            Timeline timeline = new Timeline();
            
            // 第一阶段：旧图淡出
            KeyFrame kfOut = new KeyFrame(Duration.millis(200),  new KeyValue(backgroundViewer.opacityProperty(), 0),new KeyValue(foreimage.opacityProperty(), 0));
            
            // 第二阶段：更换图片并淡入
            timeline.getKeyFrames().add(kfOut);
            timeline.setOnFinished(e -> {
                backgroundViewer.setImage(newImage);
                foreimage.setImage(newImage);
                Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.millis(400), new KeyValue(backgroundViewer.opacityProperty(), 1),new KeyValue(foreimage.opacityProperty(), 1))
                );
                fadeIn.play();
            });
            
            timeline.play();
        }

        /**
         * 信息面板动画
         */
        private void animateInfoPanel() {
            // 简单的缩放弹跳效果
            ScaleTransition st = new ScaleTransition(Duration.millis(150), titleLabel);
            st.setFromX(1.1);
            st.setFromY(1.1);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        }



        /**
         * 加载歌曲数据
         */
        private void loadSongs() {
            songs = new ArrayList<>();
            // 为了演示代码可运行，使用了 JavaFX 内置的占位图
            // 实际开发中，请替换回你的 File 路径代码，例如:
            // new Image(new File("src/blendefine/bg_a.jpg").toURI().toString())
            
//            Image placeholderImg = new Image("https://placehold.co/1920x1080/222222/FFFFFF/png?text=Background",yywidth,yyheight, false, false); 
            String keyword="pumian";
            Path currentDir = Paths.get("src/blendefine/files");
            try {
                // 列出所有文件（不包括子目录）
                List<Path> files = Files.list(currentDir)
                        .filter(Files::isRegularFile)          // 只取普通文件
                        .collect(Collectors.toList());

                for (Path file : files) {
                    String fileName = file.getFileName().toString();
                    // 过滤文件名包含关键字（忽略大小写）
                    if (fileName.toLowerCase().contains(keyword.toLowerCase())) {
                        // 按空格分割文件名
                    	if(fileName.indexOf("---")!=-1) {
	                        String[] parts = fileName.split("---");
	                        // 取前三个部分，不足的用空字符串填充（可根据需求调整）
	                        parts[1]= parts[1].replace(".txt", "");
	                        try (BufferedReader br = Files.newBufferedReader(file)) {
	                            String info=br.readLine();
	                            String[] parts1 = info.split(" ");
	                            Image placeholderImg ;
//	                            System.out.println(parts[1]);
	                            try {
	                            	placeholderImg = new Image("file:src/blendefine/music/"+parts[1]+".jpg"); 
	                            }catch(Exception e) {
	                            	try {
	                            	placeholderImg = new Image("file:src/blendefine/music/"+parts[1]+".png"); 
	                            	}catch(Exception e1) {
	                            		placeholderImg = new Image("file:src/blendefine/music/SIM破.jpg");
//	                            		placeholderImg = new Image("https://placehold.co/1920x1080/222222/FFFFFF/png?text=Background"); 
	                            	}
	                            }
	                            songs.add(new Song(parts[1],parts1[0], placeholderImg,"src/blendefine/music/"+parts[1]+".mp3",Integer.parseInt(parts1[1]),Integer.parseInt(parts1[2])));
	                            br.close();
	                        }catch(Exception e1) {e1.printStackTrace();}
                    	}
                    	else if(fileName.indexOf("===")!=-1) {
                    		try (BufferedReader br = Files.newBufferedReader(file)) {
                    			String[] parts = fileName.split("===");
                    			parts[1]= parts[1].replace(".txt", "");
                    			String info=br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine();
//	                            System.out.print(info);
                    			songsscores.put(parts[1],info);
                    			br.close();
	                        }
                    	}
                    	else if(fileName.indexOf("=+=")!=-1) {
                    		try (BufferedReader br = Files.newBufferedReader(file)) {
                    			String[] parts = fileName.split("=\\+=");
                    			parts[1]= parts[1].replace(".txt", "");
                    			String info=br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine()+"\n"+br.readLine();
//	                            System.out.print(info);
                    			songsscores.put(parts[1]+"h",info);
                    			br.close();
	                        }
                    	}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            	
//            songs.add(new Song(1,"Lights of Muse", "Akira Complex", placeholderImg, "src/blendefine/music_a.mp3",100));
//            songs.add(new Song(2,"Crossover", " StatiCnull", placeholderImg, "src/blendefine/music_b.mp3",100));
//            songs.add(new Song(3,"风屿", "VeetaCrush", placeholderImg, "src/blendefine/music_c.mp3",100));
//            songs.add(new Song(4,"Igallta", "Se-U-Ra", placeholderImg, "src/blendefine/music_d.mp3",100));  
//            double offsetRange = songs.size()*40*suofang;

            // 在 songs 末尾添加一个空白项（如果你的数据模型不允许，可以单独处理列表末尾的占位单元格）
            for(int i=0;i<yyheight/(40*suofang);i++) {
	            Song placeholder=new Song("", "", null, "",0,0);
	            songs.add(placeholder); // 添加一个不可见的占位
            }
        }
        public class Song {
            private final String title;
            private final String artist;
            private final Image image;
            private final Media media;
            private final double bpm;
            private final double bpmlate;
            
            public Song(String title, String artist, Image image, String musicPath,double bpm,double bpmlate) {
                this.title = title;
                this.artist = artist;
                this.image = image;
                this.bpm=bpm;
                this.bpmlate=bpmlate;
                // 音乐文件仍尝试从本地加载，如果不存在则为 null
                File f = new File(musicPath);
                if(f.exists()) {
                    this.media = new Media(f.toURI().toString());
                } else {
                    // 防止空指针，实际应用请处理错误
                    this.media = null; 
                }
            }
            public String getTitle() { return title; }
            public String getArtist() { return artist; }
            public Image getImage() { return image; }
            public Media getMedia() { return media; }
            public double getbpm() { return bpm; }
			public double getBpmlate() {
				return bpmlate;
			}
        }
//        private void updateFit(double imageRatio) {
//            double gameWidth = game.getWidth();
//            double gameHeight = game.getHeight();
//            if (gameWidth == 0 || gameHeight == 0) return;
//
//            double gameRatio = gameWidth / gameHeight;
//            if (gameRatio > imageRatio) {
//                // 容器更宽：以高度为基准，宽度自适应（可能裁剪左右）
//                backgroundViewer.setFitHeight(gameHeight);
//                backgroundViewer.setFitWidth(-1); // 重置宽度自动计算
//            } else {
//                // 容器更高：以宽度为基准，高度自适应（可能裁剪上下）
//                backgroundViewer.setFitWidth(gameWidth);
//                backgroundViewer.setFitHeight(-1);
//            }
//        }
        public void close() {
            // 1. 停止所有正在运行的动画和过渡效果
            if (scrollupt != null) scrollupt.stop();
            if (scrolldownt != null) scrolldownt.stop();
            if (leftyyt != null) leftyyt.stop();
            if (rightyyt != null) rightyyt.stop();
            if (checkhide != null) checkhide.stop();
            if (tml != null) tml.stop();
            if (bgSwitchTimeline != null) bgSwitchTimeline.stop();
            if (bgkira != null) bgkira.stop();
            if (mouseanict != null) mouseanict.stop();

            // 停止并清空 Timeline 集合
            for (Timeline t : ottimeline) {
                if (t != null) t.stop();
            }
            ottimeline.clear();

            for (Timeline t : ottimeline1) {
                if (t != null) t.stop();
            }
            ottimeline1.clear();

            // 停止并清空 TranslateTransition 集合
            for (TranslateTransition tt : transs) {
                if (tt != null) tt.stop();
            }
            transs.clear();

//            // 2. 释放媒体播放器资源
//            if (bdf != null && bdf.mp != null) {
//                try {
//                    bdf.mp.stop();
//                    bdf.mp.dispose();
//                } catch (Exception e) {
//                    // 忽略释放过程中的异常
//                }
//                bdf.mp = null;
//            }

            // 3. 清空数据集合
            if (songs != null) songs.clear();
            if (songsscores != null) songsscores.clear();

            // 4. 移除所有子节点并清空容器引用
            if (game != null) {
                game.getChildren().clear();
            }
            if (root != null) {
                root.getChildren().clear();
            }
            if (stp != null) {
                stp.getChildren().remove(root);
            }

            // 5. 将 UI 组件引用置空，帮助 GC 回收
            backgroundViewer = null;
            songListView = null;
            titleLabel = null;
            artistLabel = null;
            scoreLabel = null;
            hardLabel = null;
            foreimage = null;
            latencyslider = null;
            speedslider = null;
            contentLayer = null;
            infoPanel = null;
            textBox = null;
            hardBox = null;
            yy = null;
            gyy = null;
            scene = null;
            bdf = null;

            // 6. 清空轨道数组和按键状态数组
            for (int i = 0; i < ontap.length; i++) ontap[i] = null;
            for (int i = 0; i < yys.length; i++) yys[i] = null;
            for (int i = 0; i < key.length; i++) key[i] = 0;
            for (int i = 0; i < guidaoonuse.length; i++) guidaoonuse[i] = 0;

            // 7. 重置游戏状态标志
            songf = 0;
            gameFinished = true;
            nowplayname = null;
            lastselectsong = null;
        }
        public static void main(String[] args) {
    		String result="";
    		for(int i=0;i<3419;i++) {
    			//60秒除以BPM加延时
    			result+=Math.round((0.0+i)*6000/92+124)+"\n";
    		}
            String resultPath = "src/blendefine/files/pumian.txt";
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(resultPath))) {
                bw.write(result);
                bw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    	}
}