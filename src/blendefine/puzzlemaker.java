package blendefine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class puzzlemaker extends Application {
    
	ConcurrentHashMap<String,String> hunyuancft=new ConcurrentHashMap<String,String>(),wuzangcft=new ConcurrentHashMap<String,String>(),wuzangcftni=new ConcurrentHashMap<String,String>();
	ConcurrentHashMap<String,HashSet<String>> findcraft=new ConcurrentHashMap<>(),findmaterial=new ConcurrentHashMap<>(),findcraftgraph=new ConcurrentHashMap<>();	
    private HashMap<String, String[]> sealMap = new HashMap<>();
	public String[] 
			jichuelename = {"阳","阴","金","水","木","火","土"},
//			sixiangname = {"青龙","白虎","朱雀","玄武","黄极"},
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
    private Boolean[][] isingroup=new Boolean[9][17];
    private Polygon[][] selectpoly=new Polygon[9][17];
    
    // 三角形图像数组（示例，实际需要您提供图像）
    private Image[] triid = new Image[10]; // 向下的三角形
    private Image[] triiu = new Image[10]; // 向上的三角形
    
    private Pane rootPane,selectpane;
    private GridPane gpane;
    private int makepathf=-1;
    private Boolean numlimitf=true,basicf=true;
    private boolean filterBasicElementsOnly = true;
    
    // 新功能相关变量
    private String currentSelectedSeal = null; // 当前选中的灵印
    private String startSeal = null; // 起点灵印
    private String endSeal = null;   // 终点灵印
    private List<List<String>> currentPaths = new ArrayList<>(); // 当前找到的路径
    private int currentPathIndex = -1; // 当前显示的路径索引
    
    private List<String> curpath;
    private List<Integer[]> curgridpath=new ArrayList<Integer[]>();
    
    // 控制面板组件
    private VBox connectPane,outputPane;
    private Label startLabel;
    private Label endLabel;
    private TextField pathLengthField;
    TextArea exportTextArea;
    private Button findPathButton;
//    private Button showPathButton;
    TabPane tabPane,tabPane1;
    
    HashMap<String,Image> sealpics=new  HashMap<String,Image>();
    
    // 页签分类
    private final String[] categories = {"五行","七字","七星","八仙", "九宫", "天干", "地支", "九龙", "生灵"},
    		categories1 = {"连接","导出"};
    
    public Node[] righttabs;
    
    private ListView<String> pathListView; // 路径列表显示组件
    private Label pathCountLabel; // 路径数量标签
    @Override
    public void start(Stage primaryStage) {
        initializeSealMap();
        initializeImages();
        inicraftable();
        
        rootPane = new Pane();
        rootPane.setPrefSize(1500, 800); // 增加宽度以容纳控制面板
        
        // 创建左侧页签栏
        tabPane = createLeftTabPane();
        
        Pane stack=new Pane();
        // 创建右侧网格区域
        gpane = createRightGridPane();
        stack.getChildren().add(gpane);
        stack.getChildren().add(selectpane=new Pane());
        stack.setPickOnBounds(false);
        selectpane.setLayoutX(350);
        selectpane.setMouseTransparent(true);
       
        // 创建右侧控制面板
        connectPane = createConnectPanel();
        connectPane.setPickOnBounds(false);
        
        // 创建右侧控制面板
        outputPane = createOutputPanel();
        outputPane.setPickOnBounds(false);
        
        righttabs=new Node[]{connectPane,outputPane};
        
        // 创建右侧页签栏
        tabPane1 = createRightTabPane();
        
        // 设置布局
        rootPane.getChildren().addAll(tabPane, stack, tabPane1);
        
        // 读取示例数据
        readExampleData();
//		List<List<String>>p1=findAllConnectionPaths("金","马",4);
//		System.out.println(p1.toString());
        Scene scene = new Scene(rootPane);
        primaryStage.setTitle("灵印系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createOutputPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #3c3c3c; -fx-border-color: #555; -fx-border-width: 2;");
        
        Label titleLabel = new Label("导出关卡信息");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        //清空宝石功能
        Button clearbutton = new Button("清空灵印");
        clearbutton.setPrefWidth(230);
        clearbutton.setOnAction(e -> {
            clearAllSeal();
        });
        
        // 第一行：清空按钮
        HBox firstRow = new HBox(10);
        firstRow.setAlignment(Pos.CENTER_LEFT);
        Button clearButton = new Button("清空所有选择");
        clearButton.setPrefWidth(115);
        clearButton.setOnAction(e -> clearAllSelections());
        Button copygridfromsealButton = new Button("选择非空格");
        copygridfromsealButton.setPrefWidth(115);
        copygridfromsealButton.setOnAction(e -> copygridfromseal());
        firstRow.getChildren().addAll(clearButton,copygridfromsealButton);
        
        // 第二行：生成灵印按钮和单选框
        HBox secondRow = new HBox(10);
        secondRow.setAlignment(Pos.CENTER_LEFT);
        
        Button generateSealButton = new Button("生成目标灵印");
        generateSealButton.setPrefWidth(115);
        
        Button clearfirstButton=new Button("清空目标");
        clearfirstButton.setPrefWidth(115);
        
        secondRow.getChildren().addAll(generateSealButton,clearfirstButton);
        
        // 第坤行：生成坐标按钮
        HBox kunRow = new HBox(10);
        kunRow.setAlignment(Pos.CENTER_LEFT);
        Button generatemetarialButton = new Button("生成提供材料");
        generatemetarialButton.setPrefWidth(115);
        Button clearsecondButton=new Button("清空材料");
        clearsecondButton.setPrefWidth(115);
        kunRow.getChildren().addAll(generatemetarialButton, clearsecondButton);
        
        HBox kunkunRow = new HBox(10);
        ToggleGroup sealTypeGroup = new ToggleGroup();
        RadioButton numlimitRadio = new RadioButton("有限材料");
        RadioButton nonumlimitRadio = new RadioButton("无限");
        numlimitRadio.setToggleGroup(sealTypeGroup);
        nonumlimitRadio.setToggleGroup(sealTypeGroup);
        numlimitRadio.setSelected(true);
        CheckBox basicmaterialb = new CheckBox("基础");
        basicmaterialb.setOnAction(e->{
        	if(basicmaterialb.isSelected())basicf=true;
        	else basicf=false;
        });
        basicmaterialb.setSelected(true);
        
        nonumlimitRadio.setStyle("-fx-text-fill: white;");
        numlimitRadio.setStyle("-fx-text-fill: white;");
        basicmaterialb.setStyle("-fx-text-fill: white;");
        
        kunkunRow.getChildren().addAll(nonumlimitRadio, numlimitRadio,basicmaterialb);
        
        
        // 第三行：生成坐标按钮
        HBox thirdRow = new HBox(10);
        thirdRow.setAlignment(Pos.CENTER_LEFT);
        Button generateCoordButton = new Button("生成限位灵印");
        generateCoordButton.setPrefWidth(115);
        Button clearthirdButton=new Button("清空限位");
        clearthirdButton.setPrefWidth(115);
        thirdRow.getChildren().addAll(generateCoordButton, clearthirdButton);
        
        // 第四行：销毁关单选框
        HBox fourthRow = new HBox(10);
        fourthRow.setAlignment(Pos.CENTER_LEFT);
        Label destroyLabel = new Label("销毁关:");
        destroyLabel.setStyle("-fx-text-fill: white;");
        
        ToggleGroup destroyGroup = new ToggleGroup();
        RadioButton notDestroyRadio = new RadioButton("不是");
        RadioButton isDestroyRadio = new RadioButton("是");
        notDestroyRadio.setToggleGroup(destroyGroup);
        isDestroyRadio.setToggleGroup(destroyGroup);
        notDestroyRadio.setSelected(true);
        
        notDestroyRadio.setStyle("-fx-text-fill: white;");
        isDestroyRadio.setStyle("-fx-text-fill: white;");
        
        fourthRow.getChildren().addAll(destroyLabel, notDestroyRadio, isDestroyRadio);
        
        // 第五行：格子限制单选框
        HBox fifthRow = new HBox(10);
        fifthRow.setAlignment(Pos.CENTER_LEFT);
        Button generateLimitButton = new Button("生成格子限制");
        generateLimitButton.setPrefWidth(115);
        Button noLimitButton=new Button("清空格子");
        noLimitButton.setPrefWidth(115);
        fifthRow.getChildren().addAll(generateLimitButton, noLimitButton);
        
        // 第六行：导出文本区域
        Label exportLabel = new Label("导出信息:");
        exportLabel.setStyle("-fx-text-fill: white;");
        
        exportTextArea = new TextArea();
        exportTextArea.setPrefHeight(200);
        exportTextArea.setWrapText(true);
        exportTextArea.setStyle("-fx-control-inner-background: #555; -fx-text-fill: white;");
        
        // 初始化导出文本区域
        updateExportTextArea(exportTextArea, "-", "-", "-", "-", "-");
        
        // 第五行：格子限制单选框
        HBox lastRow = new HBox(10);
        lastRow.setAlignment(Pos.CENTER_LEFT);
        TextField exporttext = new TextField("zhu");
        exporttext.setPrefWidth(115);
        Button exportbutton=new Button("导出");
        exportbutton.setPrefWidth(115);
        exportbutton.setOnAction(e->{
        	save(new File("src/blendefine/files/"+exporttext.getText()+".txt"));
        });
        lastRow.getChildren().addAll(exporttext, exportbutton);
        
        // 按钮事件处理
        generateSealButton.setOnAction(e -> {
            String sealName = makeminname1();
            if (sealName != null && !sealName.isEmpty()) {
                    updateExportTextArea(exportTextArea, sealName , getCurrentPart2(exportTextArea), 
                                       getCurrentPart3(exportTextArea), getCurrentPart4(exportTextArea), 
                                       getCurrentPart5(exportTextArea));
            }
        });
        
        clearfirstButton.setOnAction(e -> {
                    updateExportTextArea(exportTextArea, "-" , getCurrentPart2(exportTextArea), 
                                       getCurrentPart3(exportTextArea), getCurrentPart4(exportTextArea), 
                                       getCurrentPart5(exportTextArea));
        });
        
        generatemetarialButton.setOnAction(e -> {
        	String mInfo="";
        	Boolean isselect=false;
        	for(int j=0;j<17;j++) {	
    			for(int i=0;i<9;i++) {
    				if(null!=isingroup[i][j]&&isingroup[i][j])isselect=true;
    			}
        	}
        	if(isselect) {
        		mInfo = makeminname1();
        		if(getCurrentPart2(exportTextArea).indexOf(",")!=-1)
        		updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
        				getCurrentPart2(exportTextArea)+" "+mInfo, getCurrentPart3(exportTextArea), 
                       getCurrentPart4(exportTextArea), getCurrentPart5(exportTextArea));
        		else
        			updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
            				mInfo, getCurrentPart3(exportTextArea), 
                           getCurrentPart4(exportTextArea), getCurrentPart5(exportTextArea));
        	}
        	else {
        		mInfo = makeminname();
        		updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
        				mInfo, getCurrentPart3(exportTextArea), 
                       getCurrentPart4(exportTextArea), getCurrentPart5(exportTextArea));
        	}
        });
        
        clearsecondButton.setOnAction(e -> {
            updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea) ,"-" , 
                               getCurrentPart3(exportTextArea), getCurrentPart4(exportTextArea), 
                               getCurrentPart5(exportTextArea));
        });
        
        generateCoordButton.setOnAction(e -> {
            String coordInfo = makeminname3();
            updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
                               getCurrentPart2(exportTextArea), coordInfo, 
                               getCurrentPart4(exportTextArea), getCurrentPart5(exportTextArea));
        });
        
        clearthirdButton.setOnAction(e -> {
            updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), getCurrentPart2(exportTextArea), 
            					"-", getCurrentPart4(exportTextArea), 
                               getCurrentPart5(exportTextArea));
        });
        
        numlimitRadio.setOnAction(e -> {
           numlimitf=true;
        });
        nonumlimitRadio.setOnAction(e -> {
           numlimitf=false;
        });
        
        // 单选框事件处理
        notDestroyRadio.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
                                   getCurrentPart2(exportTextArea), getCurrentPart3(exportTextArea), 
                                   "-", getCurrentPart5(exportTextArea));
            }
        });
        
        isDestroyRadio.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
                                   getCurrentPart2(exportTextArea), getCurrentPart3(exportTextArea), 
                                   "--", getCurrentPart5(exportTextArea));
            }
        });
        
        generateLimitButton.setOnAction(e -> {
            String coordInfo = makeminname2();
            updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
                               getCurrentPart2(exportTextArea), getCurrentPart3(exportTextArea),
                               getCurrentPart4(exportTextArea), coordInfo);
        });
        
        noLimitButton.setOnAction(e -> {
            updateExportTextArea(exportTextArea, getCurrentPart1(exportTextArea), 
            		 	getCurrentPart2(exportTextArea), getCurrentPart3(exportTextArea),
            		 	getCurrentPart4(exportTextArea),"-");
        });
        
        panel.getChildren().addAll(
        	clearbutton,
            titleLabel, 
            firstRow,
            secondRow,
            kunRow,
            kunkunRow,
            thirdRow,
            fourthRow,
            fifthRow,
            exportLabel,
            exportTextArea,
            lastRow
        );
        
        return panel;
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

    // 更新导出文本区域的方法
    private void updateExportTextArea(TextArea textArea, String part1, String part2, 
                                     String part3, String part4, String part5) {
        String stageInfo = part1 + "\n" + part2 + "\n" + part3 + "\n" + part4 + "\n" + part5;
        textArea.setText(stageInfo);
    }

    // 从当前文本中获取各个部分的方法
    private String getCurrentPart1(TextArea textArea) {
        String[] lines = textArea.getText().split("\n");
        return lines.length > 0 ? lines[0] : "-";
    }

    private String getCurrentPart2(TextArea textArea) {
        String[] lines = textArea.getText().split("\n");
        return lines.length > 1 ? lines[1] : "-";
    }

    private String getCurrentPart3(TextArea textArea) {
        String[] lines = textArea.getText().split("\n");
        return lines.length > 2 ? lines[2] : "-";
    }

    private String getCurrentPart4(TextArea textArea) {
        String[] lines = textArea.getText().split("\n");
        return lines.length > 3 ? lines[3] : "-";
    }

    private String getCurrentPart5(TextArea textArea) {
        String[] lines = textArea.getText().split("\n");
        return lines.length > 4 ? lines[4] : "-";
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

	private TabPane createLeftTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setPrefWidth(350);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        for (String category : categories) {
            Tab tab = new Tab(category);
            tab.setContent(createCategoryGrid(category));
            tabPane.getTabs().add(tab);
        }
        
        return tabPane;
    }
    
    private TabPane createRightTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setPrefWidth(250);
        tabPane.setLayoutX(1250);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        for (int i=0;i<categories1.length;i++) {
            Tab tab = new Tab(categories1[i]);
            tab.setContent(righttabs[i]);
            tabPane.getTabs().add(tab);
        }
        
        return tabPane;
    }
    
    private GridPane createCategoryGrid(String category) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(30);
        gridPane.setVgap(-20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        
        // 获取该类别的所有灵印
        String[] seals = sealMap.get(category);
        
        int row = 0;
        int col = 0;
        for (String sealName : seals) {
            ImageView sealView = createSealImageView(sealName);
            createSealImageView1(sealName);
            // 创建包含图像和标签的VBox
            VBox sealBox = new VBox(5);
            sealBox.setAlignment(Pos.CENTER);
            
            Label nameLabel = new Label(sealName);
            nameLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");
            
            sealBox.getChildren().addAll(sealView, nameLabel);
            
            gridPane.add(sealBox, col, row);
            
            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
            
            // 最多显示6行
            if (row >= 6) break;
        }
        
        return gridPane;
    }
    
    private ImageView createSealImageView(String sealName) {
    	Group g0=new Group();
    	Blend definecolorblend = new Blend();
    	definecolorblend.setMode(BlendMode.OVERLAY);
        ImageView baseImage2= new ImageView(new Image(getClass().getResourceAsStream("definepic/宝石a0"+".png")));
        baseImage2.setSmooth(false);
        baseImage2.setFitHeight(346);
        baseImage2.setFitWidth(346);
//        System.out.println(sealName);
        ImageView middleImage2=null;
        try {
        	Image ai=new Image(getClass().getResourceAsStream("definepic/宝石"+sealName+".png"));
 	   		middleImage2 = new ImageView(ai);
        }
 	   	catch(Exception e) {middleImage2 = new ImageView();}
 	   	middleImage2.setSmooth(false);
 	   	middleImage2.setFitHeight(346);
 	  	middleImage2.setFitWidth(346);
 	  	
 		try {
 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+sealName+".png"))));
 	  	}catch(Exception e1) {
 	  		if(sealName.equals("天枢")||sealName.equals("天玑")||sealName.equals("天璇")||sealName.equals("天权")||
 	  				sealName.equals("开阳")||sealName.equals("玉衡")||sealName.equals("摇光")) {
 	  			definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/星.png")))); 
 	  			System.out.print(1);
 	  		}
 	  		else definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png")))); 	
 	  	}
 	  	
        g0.getChildren().addAll(middleImage2,baseImage2);
        g0.setEffect(definecolorblend);      
        //设置多边形中心为173，173
        Polygon p=new Polygon(23,86,0,0,23,86,323,86,346,0,323,86,173,346);
        Group g1=new Group(g0);
        g1.setClip(p);
        Blend defineli = new Blend();
    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/光照.png"))));
        g1.setEffect(defineli);
        Group g3=new Group(new ImageView(new Image(getClass().getResourceAsStream("definepic/包圆.png"))),g1);
        SnapshotParameters params1 = new SnapshotParameters();
        params1.setFill(Color.TRANSPARENT);
		Image si=g3.snapshot(params1, null);
		sealpics.put(sealName, si);
        ImageView imageView = new ImageView(si);
        imageView.setFitWidth(115);
        imageView.setFitHeight(115);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        
        // 设置点击事件 - 选择灵印
        imageView.setOnMouseClicked(e -> {
            System.out.println("选中的灵印: " + sealName);
            currentSelectedSeal = sealName;
        });
        return imageView;
    }
    
    private ImageView createSealImageView1(String sealName) {
    	Group g0=new Group();
    	Blend definecolorblend = new Blend();
    	definecolorblend.setMode(BlendMode.OVERLAY);
        ImageView baseImage2= new ImageView(new Image(getClass().getResourceAsStream("definepic/宝石a12"+".png")));
        baseImage2.setSmooth(false);
        baseImage2.setFitHeight(346);
        baseImage2.setFitWidth(346);
//        System.out.println(sealName);
        ImageView middleImage2=null;
        try {
        	Image ai=new Image(getClass().getResourceAsStream("definepic/宝石"+sealName+".png"));
 	   		middleImage2 = new ImageView(ai);
        }
 	   	catch(Exception e) {middleImage2 = new ImageView();}
 	   	middleImage2.setSmooth(false);
 	   	middleImage2.setFitHeight(346);
 	  	middleImage2.setFitWidth(346);
 	  	
 	  	try {
 	  		definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/"+sealName+".png"))));
 	  	}catch(Exception e1) {
 	  		if(sealName.equals("天枢")||sealName.equals("天玑")||sealName.equals("天璇")||sealName.equals("天权")||
 	  				sealName.equals("开阳")||sealName.equals("玉衡")||sealName.equals("摇光")) {
 	  			definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/星.png")))); 
 	  			System.out.print(1);
 	  		}
 	  		else definecolorblend.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/字.png")))); 	
 	  	}
 	  	
        g0.getChildren().addAll(middleImage2,baseImage2);
        g0.setEffect(definecolorblend);      
        //设置多边形中心为173，173
        Polygon p=new Polygon(23,86,0,0,23,86,323,86,346,0,323,86,173,346);
        Group g1=new Group(g0);
        g1.setClip(p);
        Blend defineli = new Blend();
    	defineli.setMode(BlendMode.OVERLAY);
    	defineli.setTopInput(new ImageInput(new Image(getClass().getResourceAsStream("definepic/光照0.png"))));
        g1.setEffect(defineli);
        Group g3=new Group(new ImageView(new Image(getClass().getResourceAsStream("definepic/包圆.png"))),g1);
        SnapshotParameters params1 = new SnapshotParameters();
        params1.setFill(Color.TRANSPARENT);
		Image si=g3.snapshot(params1, null);
		sealpics.put(sealName+"1", si);
        ImageView imageView = new ImageView(si);
        imageView.setFitWidth(115);
        imageView.setFitHeight(115);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        
        // 设置点击事件 - 选择灵印
        imageView.setOnMouseClicked(e -> {
            System.out.println("选中的灵印: " + sealName);
            currentSelectedSeal = sealName;
        });
        return imageView;
    }
    
    private GridPane createRightGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(350);
        gridPane.setPrefSize(900, 800);
        gridPane.setStyle("-fx-background-color: #2b2b2b;");
        
        return gridPane;
    }
    
    private VBox createConnectPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: #3c3c3c; -fx-border-color: #555; -fx-border-width: 2;");
        
        //清空宝石功能
        Button clearbutton = new Button("清空灵印");
        clearbutton.setPrefWidth(230);
        clearbutton.setOnAction(e -> {
            clearAllSeal();
        });
        
        // 起点选择
        startLabel = new Label("未选择");
        startLabel.setStyle("-fx-text-fill: #90EE90; -fx-background-color: #555; -fx-padding: 5px;");
        startLabel.setPrefWidth(230);
        startLabel.setOnMouseClicked(e -> {
            startSeal = null;
            startLabel.setText("请点击网格上的灵印");
            System.out.println("请点击网格上的灵印作为起点");
        });
        
        // 终点选择
        endLabel = new Label("未选择");
        endLabel.setStyle("-fx-text-fill: #FFB6C1; -fx-background-color: #555; -fx-padding: 5px;");
        endLabel.setPrefWidth(230);
        endLabel.setOnMouseClicked(e -> {
            endSeal = null;
            endLabel.setText("请点击网格上的灵印");
            System.out.println("请点击网格上的灵印作为终点");
        });
        
        // 路径长度输入
        Label lengthLabel = new Label("路径长度:");
        lengthLabel.setStyle("-fx-text-fill: white;");
        pathLengthField = new TextField();
        pathLengthField.setPromptText("输入最大深度");
        pathLengthField.setPrefWidth(230);
        pathLengthField.setText("3");
        pathLengthField.setOnMouseEntered(e->{
            pathLengthField.requestFocus();
            pathLengthField.selectAll();
        });
        
        // 添加筛选复选框
        CheckBox filterCheckBox = new CheckBox("仅显示基础元素路径");
        filterCheckBox.setStyle("-fx-text-fill: white;");
        filterCheckBox.setPrefWidth(230);
        filterCheckBox.setSelected(true);
        filterCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            filterBasicElementsOnly = newVal;
            // 如果已经有路径显示，重新应用筛选
            if (!currentPaths.isEmpty()) {
                findPaths();
            }
        });
        
        // 查找路径按钮
        findPathButton = new Button("查找路径");
        findPathButton.setPrefWidth(230);
        findPathButton.setOnAction(e -> findPaths());
        
        // 路径数量标签
        pathCountLabel = new Label("找到 0 条路径");
        pathCountLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        
        // 路径列表
        Label pathListLabel = new Label("路径列表:");
        pathListLabel.setStyle("-fx-text-fill: white;");
        
        pathListView = new ListView<>();
        pathListView.setPrefHeight(200);
        pathListView.setPrefWidth(230);
        pathListView.setStyle("-fx-control-inner-background: #555; -fx-text-fill: white;");
        
        TextArea pathTextarea=new TextArea();
        pathTextarea.setWrapText(true);
        pathTextarea.setOnMouseEntered(e->{
            pathTextarea.requestFocus();
            pathTextarea.selectAll();
        });
        
        // 列表选择监听器
        pathListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                int selectedIndex = pathListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < currentPaths.size()) {
                    List<String> selectedPath = currentPaths.get(selectedIndex);
                    System.out.println("选中路径: " + selectedPath);
                    // 这里可以添加高亮显示选中路径的逻辑
                    pathTextarea.setText(selectedPath.toString());
                    curpath=selectedPath;
                }
            }
        });
        
        Button makepathBtn=new Button("创建路径");
        makepathBtn.setPrefWidth(230);
        makepathBtn.setOnAction(e->{
            makepathf=0;
        });
        
        panel.getChildren().addAll(
            clearbutton, 
            startLabel,
            endLabel,
            lengthLabel, pathLengthField,
            filterCheckBox, // 添加复选框
            findPathButton, pathCountLabel,
            pathListLabel, pathListView, makepathBtn, pathTextarea
        );
        
        return panel;
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
            triangleView.setTranslateY(87*y);
            triangleView.setTranslateX(50*x);
            triangleView.setFitWidth(100);
            triangleView.setFitHeight(87);
            
            // 确保数组大小足够
            if (y < gridv.length && x < gridv[y].length) {
                gridv[y][x] = triangleView;
            }
            
            // 为三角形添加点击事件
            final int finalX = x;
            final int finalY = y;
            triangleView.setOnMouseClicked(e -> {
            	
            	if(e.getButton()==MouseButton.PRIMARY) {
//            		if (gridSeals[y][x] == null) {
            			handleGridClick(finalX, finalY);
//            		}
            	}
            	else if(e.getButton()==MouseButton.SECONDARY){
            		
            		if (gridSeals[y][x] == null) {
            			rootPane.getChildren().removeIf(node -> node instanceof Canvas);
                        showConnectionCanvas(x, y);
            		}
            		else removeSealFromGrid(x,y);
            	}
            	
                else if (e.getButton() == MouseButton.MIDDLE) {
                	rootPane.getChildren().removeIf(node -> node instanceof Canvas);
                    showConnectionCanvas(x, y);
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
            				selectpoly[y][x]=new Polygon(50,0,100,87,0,87);
            			}
            			else {
            				selectpoly[y][x]=new Polygon(0,0,100,0,50,87);
            				
            			}
            			selectpoly[y][x].setTranslateX(50*x);
        				selectpoly[y][x].setTranslateY(87*y);
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
        } 
        
    }
    
    // 处理网格点击事件
    private void handleGridClick(int x, int y) {
        System.out.println("点击网格: (" + x + ", " + y + ")");
        
        if(makepathf==0) {
        	curgridpath.add(new Integer[]{x,y});
        	makepathf=1;
        }
        else if(makepathf==1) {
        	makepathf=-1;
        	createpath();
        }
        // 功能2: 选择起点/终点
        else if (gridSeals[y][x] != null) {
            String sealName = gridSeals[y][x];
            if (startLabel.getText().equals("请点击网格上的灵印")) {
                startSeal = sealName;
                startLabel.setText(sealName);
                System.out.println("起点设置为: " + sealName);
            } else if (endLabel.getText().equals("请点击网格上的灵印")) {
                endSeal = sealName;
                endLabel.setText(sealName);
                System.out.println("终点设置为: " + sealName);
            }
            else if (currentSelectedSeal != null) {
            	removeSealFromGrid(x,y);
                placeSealOnGrid(x, y, currentSelectedSeal);
            }
        }
        else if (currentSelectedSeal != null) {
        	removeSealFromGrid(x,y);
            placeSealOnGrid(x, y, currentSelectedSeal);
        }
    }
// // 新增方法: 显示连接悬浮窗
//    private void showConnectionPopup(int x, int y) {
//        // 获取周围三个格子的坐标
//        List<int[]> neighbors = getTriangleNeighbors(x, y);
//        
//        // 收集所有非空格子的可连接元素
//        Set<String> commonConnections = null;
//        List<String> neighborSeals = new ArrayList<>();
//        
//        for (int[] neighbor : neighbors) {
//            int nx = neighbor[0];
//            int ny = neighbor[1];
//            
//            // 检查坐标是否有效且格子非空
//            if (isValidCoordinate(nx, ny) && gridSeals[ny][nx] != null) {
//                String neighborSeal = gridSeals[ny][nx];
//                neighborSeals.add(neighborSeal);
//                
//                // 从findcraftgraph获取可连接的元素
//                Set<String> connections = findcraftgraph.get(neighborSeal);
//                if (connections != null) {
//                    if (commonConnections == null) {
//                        commonConnections = new HashSet<>(connections);
//                    } else {
//                        commonConnections.retainAll(connections); // 取交集
//                    }
//                }
//            }
//        }
//        
//        // 如果没有找到共同的连接元素，直接返回
//        if (commonConnections == null || commonConnections.isEmpty()) {
//            System.out.println("没有找到可连接的共同元素");
//            return;
//        }
//        
//        // 创建悬浮窗
//        createConnectionPopup(x, y, commonConnections, neighborSeals);
//    }

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

//    // 新增方法: 创建连接悬浮窗
//    private void createConnectionPopup(int x, int y, Set<String> connections, List<String> neighborSeals) {
//        // 创建悬浮窗口
//        Stage popupStage = new Stage();
//        popupStage.initModality(Modality.NONE);
//        popupStage.initStyle(StageStyle.UTILITY);
//        popupStage.setTitle("可连接元素 - 来自: " + String.join(", ", neighborSeals));
//        
//        VBox popupContent = new VBox(10);
//        popupContent.setPadding(new Insets(15));
//        popupContent.setStyle("-fx-background-color: #3c3c3c; -fx-border-color: #555; -fx-border-width: 2;");
//        
//        Label titleLabel = new Label("可连接到当前格子的元素:");
//        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
//        
//        // 创建可连接元素的按钮列表
//        FlowPane buttonsPane = new FlowPane();
//        buttonsPane.setHgap(10);
//        buttonsPane.setVgap(10);
//        buttonsPane.setPrefWidth(300);
//        
//        for (String seal : connections) {
//            Button sealButton = new Button(seal);
//            sealButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-border-color: #777;");
//            sealButton.setOnAction(e -> {
//                // 点击按钮将元素添加到当前格子
//                placeSealOnGrid(x, y, seal);
//                popupStage.close();
//                System.out.println("在中键格子(" + x + "," + y + ")添加灵印: " + seal);
//            });
//            
//            // 鼠标悬停效果
//            sealButton.setOnMouseEntered(mouseEvent -> 
//                sealButton.setStyle("-fx-background-color: #666; -fx-text-fill: white; -fx-border-color: #888;")
//            );
//            sealButton.setOnMouseExited(mouseEvent -> 
//                sealButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-border-color: #777;")
//            );
//            
//            buttonsPane.getChildren().add(sealButton);
//        }
//        
//        Button closeButton = new Button("关闭");
//        closeButton.setStyle("-fx-background-color: #777; -fx-text-fill: white;");
//        closeButton.setOnAction(e -> popupStage.close());
//        
//        popupContent.getChildren().addAll(titleLabel, buttonsPane, closeButton);
//        
//        // 计算悬浮窗位置（在点击的格子附近）
//        Scene mainScene = rootPane.getScene();
//        Point2D sceneCoord = new Point2D(350 + 50 * x, 87 * y); // 网格起始位置 + 格子偏移
//        double screenCoord = mainScene.getWindow().getX() + sceneCoord.getX();
//        
//        popupStage.setX(screenCoord + 50); // 稍微偏移避免遮挡
//        popupStage.setY(mainScene.getWindow().getY() + sceneCoord.getY() + 100);
//        
//        Scene popupScene = new Scene(popupContent);
//        popupStage.setScene(popupScene);
//        popupStage.show();
//        
//        // 点击外部关闭悬浮窗
//        popupStage.focusedProperty().addListener((obs, oldVal, newVal) -> {
//            if (!newVal) {
//                popupStage.close();
//            }
//        });
//    }
    
// // 新增方法: 创建连接悬浮窗（图形版，行星环绕排列）
//    private void createConnectionPopup(int x, int y, Set<String> connections, List<String> neighborSeals) {
//        // 创建悬浮窗口
//        Stage popupStage = new Stage();
//        popupStage.initModality(Modality.NONE);
//        popupStage.initStyle(StageStyle.UTILITY);
//        popupStage.setTitle("可连接元素 - 来自: " + String.join(", ", neighborSeals));
//        
//        Pane popupContent = new Pane();
//        popupContent.setPrefSize(400, 400);
//        popupContent.setStyle("-fx-background-color: rgba(60, 60, 60, 0.95); -fx-border-color: #777; -fx-border-width: 2; -fx-background-radius: 10;");
//        
//        // 中心点（点击的格子位置）
//        double centerX = 200;
//        double centerY = 200;
//        
//        // 绘制中心点指示器
//        Circle centerIndicator = new Circle(centerX, centerY, 8, Color.TRANSPARENT);
//        centerIndicator.setStroke(Color.YELLOW);
//        centerIndicator.setStrokeWidth(2);
//        popupContent.getChildren().add(centerIndicator);
//        
//        // 将连接元素转换为列表以便按顺序排列
//        List<String> connectionList = new ArrayList<>(connections);
//        
//        // 定义三个环的半径
//        double[] ringRadii = {80, 120, 160};
//        int[] ringCounts = {3, 5, 8};
//        
//        int elementIndex = 0;
//        
//        // 为每个环创建元素
//        for (int ring = 0; ring < ringCounts.length && elementIndex < connectionList.size(); ring++) {
//            int elementsInThisRing = Math.min(ringCounts[ring], connectionList.size() - elementIndex);
//            double radius = ringRadii[ring];
//            
//            for (int i = 0; i < elementsInThisRing && elementIndex < connectionList.size(); i++) {
//                String sealName = connectionList.get(elementIndex);
//                
//                // 创建图形元素
//                ImageView sealView = createSealGraphic(sealName, 40); // 较小的尺寸用于悬浮窗
//                
//                // 计算在环上的位置
//                double angle = 2 * Math.PI * i / elementsInThisRing;
//                double posX = centerX + radius * Math.cos(angle) - 20; // 减去一半宽度居中
//                double posY = centerY + radius * Math.sin(angle) - 20; // 减去一半高度居中
//                
//                sealView.setLayoutX(posX);
//                sealView.setLayoutY(posY);
//                
//                // 添加点击事件
//                final String seal = sealName;
//                sealView.setOnMouseClicked(e -> {
//                    placeSealOnGrid(x, y, seal);
//                    popupStage.close();
//                    System.out.println("在中键格子(" + x + "," + y + ")添加灵印: " + seal);
//                });
//                
//                // 添加悬停效果
//                sealView.setOnMouseEntered(mouseEvent -> {
//                    sealView.setEffect(new javafx.scene.effect.Glow(0.5));
//                    sealView.setCursor(javafx.scene.Cursor.HAND);
//                });
//                sealView.setOnMouseExited(mouseEvent -> {
//                    sealView.setEffect(null);
//                    sealView.setCursor(javafx.scene.Cursor.DEFAULT);
//                });
//                
//                // 添加工具提示
//                Tooltip tooltip = new Tooltip(sealName);
//                Tooltip.install(sealView, tooltip);
//                
//                // 绘制连接线（可选）
//                Line connectionLine = new Line(centerX, centerY, posX + 20, posY + 20);
//                connectionLine.setStroke(Color.GRAY);
//                connectionLine.setStrokeWidth(1);
//                connectionLine.setOpacity(0.5);
//                popupContent.getChildren().add(connectionLine);
//                
//                popupContent.getChildren().add(sealView);
//                elementIndex++;
//            }
//        }
//        
//        // 添加标题标签
//        Label titleLabel = new Label("可连接到当前格子的元素");
//        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
//        titleLabel.setLayoutX(centerX - 100);
//        titleLabel.setLayoutY(20);
//        popupContent.getChildren().add(titleLabel);
//        
//        // 添加来源信息标签
//        Label sourceLabel = new Label("来源: " + String.join(", ", neighborSeals));
//        sourceLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 11px;");
//        sourceLabel.setLayoutX(centerX - 80);
//        sourceLabel.setLayoutY(380);
//        popupContent.getChildren().add(sourceLabel);
//        
//        // 添加关闭按钮
//        Button closeButton = new Button("关闭");
//        closeButton.setStyle("-fx-background-color: #777; -fx-text-fill: white;");
//        closeButton.setLayoutX(centerX - 25);
//        closeButton.setLayoutY(350);
//        closeButton.setOnAction(e -> popupStage.close());
//        popupContent.getChildren().add(closeButton);
//        
//        // 计算悬浮窗位置（在点击的格子附近）
//        Scene mainScene = rootPane.getScene();
//        Point2D sceneCoord = new Point2D(350 + 50 * x, 87 * y);
//        double screenCoord = mainScene.getWindow().getX() + sceneCoord.getX();
//        
//        popupStage.setX(screenCoord - 200); // 居中显示
//        popupStage.setY(mainScene.getWindow().getY() + sceneCoord.getY() - 200);
//        
//        Scene popupScene = new Scene(popupContent);
//        popupStage.setScene(popupScene);
//        popupStage.show();
//        
//        // 点击外部关闭悬浮窗
//        popupStage.focusedProperty().addListener((obs, oldVal, newVal) -> {
//            if (!newVal) {
//                popupStage.close();
//            }
//        });
//    }

 // 新增方法: 创建连接画布（透明覆盖，行星环绕排列）
    private void createConnectionCanvas(int x, int y, Set<String> connections, List<String> neighborSeals) {
        // 创建透明画布
        Canvas connectionCanvas = new Canvas(rootPane.getWidth(), rootPane.getHeight());
        connectionCanvas.setMouseTransparent(false); // 允许接收鼠标事件
        connectionCanvas.setStyle("-fx-background-color: transparent;");
        
        GraphicsContext gc = connectionCanvas.getGraphicsContext2D();
        
        // 计算中心点在屏幕上的位置（网格起始位置 + 格子偏移 + 半个格子居中）
        double centerX = 350 + 50 * x + 50;
        double centerY = 87 * y + 43;
        if((x+y)%2==1) {
        	centerY=87 * y + 70;
        }
        
        // 存储灵印位置信息用于点击检测
        List<SealPosition> sealPositions = new ArrayList<>();
        
        // 绘制半透明背景（可选，提供视觉区分）
        gc.setFill(Color.rgb(30, 30, 30, 0.7));
        gc.fillRect(0, 0, connectionCanvas.getWidth(), connectionCanvas.getHeight());
        
        // 绘制中心点指示器
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(2);
        gc.strokeOval(centerX - 10, centerY - 10, 20, 20);
        gc.fillText("目标", centerX - 15, centerY - 15);
        
        // 将连接元素转换为列表以便按顺序排列
        List<String> connectionList = new ArrayList<>(connections);
        
        // 定义三个环的半径
        double[] ringRadii = {70, 110, 180,230,330};
        int[] ringCounts = {3,3,6,6,12};
        double[] ringrotate = {0,Math.PI/3,Math.PI/6,Math.PI/3,0};
        
        int elementIndex = 0;
        
        // 为每个环创建元素
        for (int ring = 0; ring < ringCounts.length && elementIndex < connectionList.size(); ring++) {
            int elementsInThisRing = Math.min(ringCounts[ring], connectionList.size() - elementIndex);
            double radius = ringRadii[ring];
            
            // 绘制环的轮廓（可选）
            gc.setStroke(Color.rgb(100, 100, 100, 0.3));
            gc.setLineWidth(1);
            gc.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            
            for (int i = 0; i < elementsInThisRing && elementIndex < connectionList.size(); i++) {
                String sealName = connectionList.get(elementIndex);
                
                // 计算在环上的位置
                double angle = 2 * Math.PI * i / ringCounts[ring]+ringrotate[ring];
                double posX = centerX + radius * Math.cos(angle) - 50; // 减去一半宽度居中
                double posY = centerY + radius * Math.sin(angle) - 50; // 减去一半高度居中
                
                // 创建灵印图像视图
                ImageView sealView = createSealGraphic(sealName, 100);
                sealView.setLayoutX(posX);
                sealView.setLayoutY(posY);
                sealView.setMouseTransparent(true); // 让画布处理点击事件
                
                // 存储位置信息用于点击检测
                sealPositions.add(new SealPosition(sealName, posX, posY, 100, 100));
                
                // 将图像绘制到画布上
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                Image sealImage = sealView.snapshot(params, null);
                gc.drawImage(sealImage, posX, posY);
                
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
                rootPane.getChildren().remove(connectionCanvas);
            } else if (e.getButton() == MouseButton.PRIMARY) {
                // 左键检测是否点击了灵印
                for (SealPosition sealPos : sealPositions) {
                    if (sealPos.contains(e.getX(), e.getY())) {
                        placeSealOnGrid(x, y, sealPos.sealName);
                        rootPane.getChildren().remove(connectionCanvas);
                        System.out.println("在中键格子(" + x + "," + y + ")添加灵印: " + sealPos.sealName);
                        break;
                    }
                }
            }
        });
        
        // 添加鼠标移动事件用于高亮
        connectionCanvas.setOnMouseMoved(e -> {
            boolean found = false;
            for (SealPosition sealPos : sealPositions) {
                if (sealPos.contains(e.getX(), e.getY())) {
                    connectionCanvas.setCursor(javafx.scene.Cursor.HAND);
                    found = true;
                    break;
                }
            }
            if (!found) {
                connectionCanvas.setCursor(javafx.scene.Cursor.DEFAULT);
            }
        });
        
        // 将画布添加到rootPane最上层
        rootPane.getChildren().add(connectionCanvas);
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
                if (connections != null) {
                    if (commonConnections == null) {
                        commonConnections = new HashSet<>(connections);
                    } else {
                        commonConnections.retainAll(connections); // 取交集
                    }
                }
            }
        }
        
        // 如果没有找到共同的连接元素，直接返回
        if (commonConnections == null || commonConnections.isEmpty()) {
            System.out.println("没有找到可连接的共同元素");
            return;
        }
        
        // 创建连接画布
        createConnectionCanvas(x, y, commonConnections, neighborSeals);
    }
    
    // 新增方法: 创建灵印图形（用于悬浮窗）
    private ImageView createSealGraphic(String sealName, double size) {
        ImageView sealView;
        
        // 使用已有的sealpics中的图像
        if (sealpics.containsKey(sealName)) {
            sealView = new ImageView(sealpics.get(sealName));
        } else {
            // 如果找不到图像，创建一个默认的占位符
            sealView = new ImageView();
            System.out.println("警告: 未找到灵印图像: " + sealName);
        }
        
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
        // 移除该位置原有的灵印
        removeSealFromGrid(x, y);
        
        // 创建新的灵印ImageView
        ImageView sealView;
        
        if((x+y)%2==0) 
        	sealView = new ImageView(sealpics.get(sealName));
        
        else sealView = new ImageView(sealpics.get(sealName+"1"));
        sealView.setMouseTransparent(true);
        sealView.setOnMouseClicked(null);
        sealView.setFitWidth(115);
        sealView.setFitHeight(115);
        
        // 计算位置偏移使灵印居中显示在三角形上
//        grid nowgrid = new grid(x, y);
        
        seali[y][x]=sealView;
        sealView.setTranslateX(50*x-8); // 居中，30是灵印宽度的一半
        sealView.setTranslateY(87*y-15); // 居中，30是灵印高度的一半
        if((x+y)%2==1) {
        	sealView.setRotate(180);
        	sealView.setTranslateY(87*y+14);
        }
        
        gpane.getChildren().add(sealView);
        gridSeals[y][x] = sealName;
        
        System.out.println("在网格(" + x + "," + y + ")放置灵印: " + sealName);
    }
    
    // 移除网格上的灵印
    private void removeSealFromGrid(int x, int y) {
        // 这里需要实现从gpane中移除对应的ImageView
        // 由于代码结构限制，这个功能需要更复杂的实现来跟踪已添加的灵印ImageView
    	try {
	    	gpane.getChildren().remove(seali[y][x]);
	    	seali[y][x]=null;
	        gridSeals[y][x] = null;
    	}catch(Exception e1) {}
    }
    private void findPaths() {
        if (startSeal == null || endSeal == null) {
            System.out.println("请先选择起点和终点");
            return;
        }
        
        try {
            int maxDepth = Integer.parseInt(pathLengthField.getText());
            List<List<String>> allPaths = findAllConnectionPaths(startSeal, endSeal, maxDepth);
            
            // 应用筛选
            if (filterBasicElementsOnly) {
                currentPaths = filterBasicElementPaths(allPaths);
            } else {
                currentPaths = allPaths;
            }
            currentPaths.sort((path1, path2) -> path2.size() - path1.size());
            updatePathListView();
            pathCountLabel.setText("找到 " + currentPaths.size() + " 条路径");
            
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的路径长度");
        }
    }
    private List<List<String>> filterBasicElementPaths(List<List<String>> allPaths) {
        List<List<String>> filteredPaths = new ArrayList<>();
        Set<String> basicElements = new HashSet<>(Arrays.asList("阴", "阳", "金", "木", "水", "火", "土"));
        
        for (List<String> path : allPaths) {
            if (isBasicElementPath(path, basicElements)) {
                filteredPaths.add(path);
            }
        }
        
        return filteredPaths;
    }

    private boolean isBasicElementPath(List<String> path, Set<String> basicElements) {
        // 路径长度至少为3（起点-中间节点-终点）
        if (path.size() < 3) {
            return false;
        }
        
        // 检查中间节点（排除起点和终点）是否都是基础元素
        for (int i = 1; i < path.size() - 1; i++) {
            if (!basicElements.contains(path.get(i))) {
                return false;
            }
        }
        
        return true;
    }
 // 新增方法：更新路径列表显示
    private void updatePathListView() {
        pathListView.getItems().clear();
        
        for (int i = 0; i < currentPaths.size(); i++) {
            List<String> path = currentPaths.get(i);
            String pathString = "路径 " + (i + 1) + ": " + String.join(" → ", path);
            pathListView.getItems().add(pathString);
        }
        
        pathCountLabel.setText("找到 " + currentPaths.size() + " 条路径");
    }

 // 修改 showNextPath 方法（如果需要保留此功能）
    @SuppressWarnings("unused")
	private void showNextPath() {
        if (currentPaths.isEmpty()) {
            System.out.println("没有可显示的路径");
            return;
        }
        
        currentPathIndex = (currentPathIndex + 1) % currentPaths.size();
        List<String> path = currentPaths.get(currentPathIndex);
        
        // 在列表中选择当前路径
        pathListView.getSelectionModel().select(currentPathIndex);
        
        System.out.println("显示路径 " + (currentPathIndex + 1) + "/" + currentPaths.size() + ": " + path);
    }
    private void initializeSealMap() {
        // 初始化灵印数据 - 这里只是示例，您需要根据实际情况填充
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
        			triiu[i]=new Image(getClass().getResourceAsStream("gridpic/gridu"+i+".png"),300,260,false,false);
        			triid[i]=new Image(getClass().getResourceAsStream("gridpic/gridd"+i+".png"),300,260,false,false);
        			
        	}
        } catch (Exception e) {
            System.out.println("图像加载失败: " + e.getMessage());
        }
    }
    
    void inicraftable() {
        //五行循环
        hunyuancft.put("阳，阳","空阴");hunyuancft.put("阴，阴","空阳");
        hunyuancft.put("阳，金","水");hunyuancft.put("阳，水","木");hunyuancft.put("阳，木","火");
        hunyuancft.put("阳，火","土");hunyuancft.put("阳，土","金");
        hunyuancft.put("阴，金","土");hunyuancft.put("阴，水","金");hunyuancft.put("阴，木","水");
        hunyuancft.put("阴，火","木");hunyuancft.put("阴，土","火");
        //天干循环
        hunyuancft.put("阳，癸","字甲");hunyuancft.put("阳，甲","字乙");hunyuancft.put("阳，乙","字丙");hunyuancft.put("阳，丙","字丁");
        hunyuancft.put("阳，丁","字戊");hunyuancft.put("阳，戊","字己");hunyuancft.put("阳，己","字庚");hunyuancft.put("阳，庚","字辛");
        hunyuancft.put("阳，辛","字壬");hunyuancft.put("阳，壬","字癸");
        hunyuancft.put("阴，乙","字甲");hunyuancft.put("阴，丙","字乙");hunyuancft.put("阴，丁","字丙");hunyuancft.put("阴，戊","字丁");
        hunyuancft.put("阴，己","字戊");hunyuancft.put("阴，庚","字己");hunyuancft.put("阴，辛","字庚");hunyuancft.put("阴，壬","字辛");
        hunyuancft.put("阴，癸","字壬");hunyuancft.put("阴，甲","字癸");
        //生肖循环
        hunyuancft.put("阳，猪","空鼠");hunyuancft.put("阳，鼠","空牛");hunyuancft.put("阳，牛","空虎");hunyuancft.put("阳，虎","空兔");
        hunyuancft.put("阳，兔","空龙");hunyuancft.put("阳，龙","空蛇");hunyuancft.put("阳，蛇","空马");hunyuancft.put("阳，马","空羊");
        hunyuancft.put("阳，羊","空猴");hunyuancft.put("阳，猴","空鸡");hunyuancft.put("阳，鸡","空狗");hunyuancft.put("阳，狗","空猪");
        hunyuancft.put("阴，牛","空鼠");hunyuancft.put("阴，虎","空牛");hunyuancft.put("阴，兔","空虎");hunyuancft.put("阴，龙","空兔");
        hunyuancft.put("阴，蛇","空龙");hunyuancft.put("阴，马","空蛇");hunyuancft.put("阴，羊","空马");hunyuancft.put("阴，猴","空羊");
        hunyuancft.put("阴，鸡","空猴");hunyuancft.put("阴，狗","空鸡");hunyuancft.put("阴，猪","空狗");hunyuancft.put("阴，鼠","空猪");
        //八卦循环 邵雍法
        //乾   兑   离  震   巽  坎   艮  坤
        //111 011 101 001 110 010 100 000 阳1阴0 由上进位到下
        //000 001 010 011 100 101 110 111 二进制 由右进位到左
        hunyuancft.put("阳，兑","字乾");hunyuancft.put("阳，离","字兑");hunyuancft.put("阳，震","字离");hunyuancft.put("阳，巽","字震");
        hunyuancft.put("阳，坎","字巽");hunyuancft.put("阳，艮","字坎");hunyuancft.put("阳，坤","字艮");hunyuancft.put("阳，乾","字坤");
        hunyuancft.put("阴，坤","字乾");hunyuancft.put("阴，乾","字兑");hunyuancft.put("阴，兑","字离");hunyuancft.put("阴，离","字震");
        hunyuancft.put("阴，震","字巽");hunyuancft.put("阴，巽","字坎");hunyuancft.put("阴，坎","字艮");hunyuancft.put("阴，艮","字坤");
        
        //有序凝炼 注意复杂元素放在第二位用来阻断简单的自发合成
        //基础字符元素
        wuzangcft.put("水，木","字文");wuzangcft.put("金，金","字武");wuzangcft.put("水，火","字灾");wuzangcft.put("金，木","字器");
        wuzangcft.put("火，土","字烟");wuzangcft.put("土，土","字山");wuzangcft.put("水，水","字海");
        //中宫与十天干
        wuzangcft.put("阴，阳","字中");
        wuzangcft.put("阳，木","字甲");wuzangcft.put("阴，木","字乙");wuzangcft.put("阳，火","字丙");wuzangcft.put("阴，火","字丁");
        wuzangcft.put("阳，土","字戊");wuzangcft.put("阴，土","字己");wuzangcft.put("阳，金","字庚");wuzangcft.put("阴，金","字辛");
        wuzangcft.put("阳，水","字壬");wuzangcft.put("阴，水","字癸");
        //十二生肖
        wuzangcft.put("水，水，水","空鼠");wuzangcft.put("土，水，金","空牛");wuzangcft.put("木，火，土","空虎");wuzangcft.put("木，木，木","空兔");
        wuzangcft.put("土，木，水","空龙");wuzangcft.put("火，金，土","空蛇");wuzangcft.put("火，火，土","空马");wuzangcft.put("土，火，木","空羊");
        wuzangcft.put("金，水，土","空猴");wuzangcft.put("金，金，金","空鸡");wuzangcft.put("土，金，火","空狗");wuzangcft.put("水，水，木","空猪");
        //八卦
        wuzangcft.put("阳，阳，阳","字乾");wuzangcft.put("阳，阳，阴","字巽");wuzangcft.put("阴，阳，阴","字坎");wuzangcft.put("阳，阴，阴","字艮");
        wuzangcft.put("阴，阴，阴","字坤");wuzangcft.put("阴，阴，阳","字震");wuzangcft.put("阳，阴，阳","字离");wuzangcft.put("阴，阳，阳","字兑");
        wuzangcft.put("阳，阴","字中");wuzangcft.put("阴，阳","字中");
        //八仙
        wuzangcft.put("阳，乾，金","空宝剑");wuzangcft.put("阳，兑，金","空葫芦");wuzangcft.put("阴，离，火","空扇子");wuzangcft.put("阴，震，木","空鱼鼓");
        wuzangcft.put("阳，巽，木","空花篮");wuzangcft.put("阳，坎，水","空竹笛");wuzangcft.put("阴，艮，土","空玉板");wuzangcft.put("阴，坤，土","空荷花");
        //七星
        wuzangcft.put("阳，坎，土，艮，木","星天枢");wuzangcft.put("阳，坎，土，巽，土","星天璇");wuzangcft.put("阳，坎，土，乾，木","星天玑");wuzangcft.put("阳，坎，土，离，水","星天权");
        wuzangcft.put("阳，坎，土，震，火","星玉衡");wuzangcft.put("阳，坎，土，兑，金","星开阳");wuzangcft.put("阳，坎，土，坎，金","星摇光");
        //四象
        wuzangcft.put("金，金，金，金，金","空白虎");wuzangcft.put("木，木，木，木，木","空青龙");
        wuzangcft.put("水，水，水，水，水","空玄武");wuzangcft.put("火，火，火，火，火","空朱雀");wuzangcft.put("土，土，土，土，土","空黄极");
        //常见动物
        wuzangcft.put("木，火，土，火","空豺");wuzangcft.put("火，金，木，土","空鸟");wuzangcft.put("金，水，土，土","空蟾");wuzangcft.put("木，火，土，金","空狮");
        wuzangcft.put("火，水，火，金","空龟");wuzangcft.put("水，水，水，水","空鱼");
        //异兽
        wuzangcft.put("龙，牛","空囚牛");wuzangcft.put("龙，豺","空睚眦");wuzangcft.put("龙，鸟","空嘲风");
        wuzangcft.put("龙，蟾","空蒲牢");wuzangcft.put("龙，狮","空狻猊");wuzangcft.put("龙，龟","空霸下");
        wuzangcft.put("龙，虎","空狴犴");wuzangcft.put("龙，青龙","空负屃");wuzangcft.put("龙，鱼","空螭吻");
        
        wuzangcft.forEach((k,v)->{
        	wuzangcftni.put(v.substring(1), k);
        });
        //创建合成表查找
        makecraftfind();
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

	private void dfsFindAllPathsWithDepth(String current, String end, Set<String> currentPathSet,
			List<String> currentPath, List<List<String>> allPaths, int currentDepth, int maxDepth) {
		if (currentDepth > maxDepth) {
			return;
		}
		// 更严格的环检测：记录父节点避免立即回溯
		String parent = currentPath.isEmpty() ? null : currentPath.get(currentPath.size() - 1);
		currentPathSet.add(current);
		currentPath.add(current);
		if (current.equals(end)) {
			allPaths.add(new ArrayList<>(currentPath));
		} else {
			for (String neighbor : findcraftgraph.get(current)) {
				// 避免立即回溯到父节点（在无向图中这是常见的环）
				if (!neighbor.equals(parent) && !currentPathSet.contains(neighbor)) {
					dfsFindAllPathsWithDepth(neighbor, end, currentPathSet, currentPath, allPaths, currentDepth + 1,
							maxDepth);
				}
			}
		}
		currentPathSet.remove(current);
		currentPath.remove(currentPath.size() - 1);
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
    
    public String makeminname2() {
    	String name="";
    	int minx=99,miny=99;
	    for(int j=0;j<17;j++) {	
			for(int i=0;i<9;i++) {
				if(null!=isingroup[i][j]&&isingroup[i][j]) {
					if(j<minx)minx=j;
					if(i<miny)miny=i;
				}
			}
		}
    	for(int j=0;j<17;j++) {	
			for(int i=0;i<9;i++) {
				if(null!=isingroup[i][j]&&isingroup[i][j]) {
					if ((minx + miny) % 2 == 0)
						name += (j - minx) + "," + (i - miny)+ " " ;
					else
						name += (j - minx + 1) + "," + (i - miny)+ " " ;
				}
			}
    	}
		return name;
    }
    
    private String makeminname3() {
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
					if(null!=gridSeals[i][j]) {
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
		return name;
	}
    
    public String copygridfromseal() {
    	clearAllSelections();
    	String name="";
    	for(int j=0;j<17;j++) {	
			for(int i=0;i<9;i++) {
				if(null!=gridSeals[i][j]) {
					isingroup[i][j]=true;
					if((i+j)%2==1) {
        				selectpoly[i][j]=new Polygon(50,0,100,87,0,87);
        			}
        			else {
        				selectpoly[i][j]=new Polygon(0,0,100,0,50,87);
        				
        			}
        			selectpoly[i][j].setTranslateX(50*j);
    				selectpoly[i][j].setTranslateY(87*i);
        			selectpoly[i][j].setFill(Color.color(1,1,1,0.7));
        			selectpane.getChildren().add(selectpoly[i][j]);
				}
			}
    	}
		return name;
    }
    void save(File f){
		try {
				if(!f.exists())
					f.createNewFile();
				 OutputStream fw=new FileOutputStream(f);
				 BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fw,"UTF-8"));
					bw.write(exportTextArea.getText());
		            bw.close();
		            fw.close();
		            //System.out.print("saved");   	
        } 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    public static void main(String[] args) {
        launch(args);
    }  
}