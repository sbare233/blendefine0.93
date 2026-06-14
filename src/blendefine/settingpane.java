package blendefine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;
import java.util.Arrays;

public class settingpane extends Pane{
	Scene scene=blendefine.scene;
	double screenwidth = blendefine.screenwidth, screenheight =blendefine.screenheight;
	private double suofang = screenheight / 1080;
	
	Font f8=blendefine.f8,
			f4=blendefine.f4,f2=blendefine.f2;
	int language;
	
	// 语言按钮
	private final String[] TXT_LANG_BUTTON = {
	    " 语言 ",   // 中文
	    " 語言 ",   // 繁中
	    " 言語 ",   // 日语
	    " 언어 ",   // 韩语
	    " Sprache ",   // 德语
	    " Language ",  // 英语
	    " Idioma ",    // 西班牙语
	    " Langue ",    // 法语
	    " Idioma ",    // 葡萄牙语
	    " Язык "       // 俄语
	};
	private final String[] TXT_LANG_HOVER = {
	    "-语言-",    // 中文
	    "-語言-",    // 繁中
	    "-言語-",    // 日语
	    "-언어-",    // 韩语
	    "-Sprache-",   // 德语
	    "-Language-",  // 英语
	    "-Idioma-",    // 西班牙语
	    "-Langue-",    // 法语
	    "-Idioma-",    // 葡萄牙语
	    "-Язык-"       // 俄语
	};

	// 音量按钮
	private final String[] TXT_VOLUME_BUTTON = {
	    " 音量 ",   // 中文
	    " 音量 ",   // 繁中
	    " 音量 ",   // 日语
	    " 음량 ",   // 韩语
	    " Lautstärke ",   // 德语
	    " Volume ",       // 英语
	    " Volumen ",      // 西班牙语
	    " Volume ",       // 法语
	    " Volume ",       // 葡萄牙语
	    " Громкость "     // 俄语
	};
	private final String[] TXT_VOLUME_HOVER = {
	    "-音量-",    // 中文
	    "-音量-",    // 繁中
	    "-音量-",    // 日语
	    "-음량-",    // 韩语
	    "-Lautstärke-",   // 德语
	    "-Volume-",       // 英语
	    "-Volumen-",      // 西班牙语
	    "-Volume-",       // 法语
	    "-Volume-",       // 葡萄牙语
	    "-Громкость-"     // 俄语
	};

	// 特效开关标签
	private final String[] TXT_EFFECT_LABEL = {
	    "特效(关/开)",        // 中文
	    "特效(關/開)",        // 繁中
	    "エフェクト(Off/On)", // 日语
	    "이펙트(Off/On)",     // 韩语
	    "Effekte (Aus/An)",   // 德语
	    "Effects (Off/On)",   // 英语
	    "Efectos (Off/On)",   // 西班牙语
	    "Effets (Off/On)",    // 法语
	    "Efeitos (Off/On)",   // 葡萄牙语
	    "Эффекты (Выкл/Вкл)"  // 俄语
	};
	private final String[] TXT_EFFECT_HOVER = {
	    "-特效(关/开)-",        // 中文
	    "-特效(關/開)-",        // 繁中
	    "-エフェクト(Off/On)-", // 日语
	    "-이펙트(Off/On)-",     // 韩语
	    "-Effekte (Aus/An)-",   // 德语
	    "-Effects (Off/On)-",   // 英语
	    "-Efectos (Off/On)-",   // 西班牙语
	    "-Effets (Off/On)-",    // 法语
	    "-Efeitos (Off/On)-",   // 葡萄牙语
	    "-Эффекты (Выкл/Вкл)-"  // 俄语
	};

	// 图形细节标签
	private final String[] TXT_GRAPHICS_LABEL = {
	    "细节(低/高)",          // 中文
	    "細節(低/高)",          // 繁中
	    "ディテール(低/高)",    // 日语
	    "디테일(낮음/높음)",     // 韩语
	    "Details (Niedrig/Hoch)", // 德语
	    "Graphics (Low/High)",    // 英语
	    "Gráficos (Bajo/Alto)",   // 西班牙语
	    "Graphismes (Bas/Haut)",  // 法语
	    "Detalhes (Baixo/Alto)",  // 葡萄牙语
	    "Детали (Низ/Выс)"        // 俄语
	};
	private final String[] TXT_GRAPHICS_HOVER = {
	    "-细节(低/高)-",          // 中文
	    "-細節(低/高)-",          // 繁中
	    "-ディテール(低/高)-",    // 日语
	    "-디테일(낮음/높음)-",     // 韩语
	    "-Details (Niedrig/Hoch)-", // 德语
	    "-Graphics (Low/High)-",    // 英语
	    "-Gráficos (Bajo/Alto)-",   // 西班牙语
	    "-Graphismes (Bas/Haut)-",  // 法语
	    "-Detalhes (Baixo/Alto)-",  // 葡萄牙语
	    "-Детали (Низ/Выс)-"        // 俄语
	};

	// 设置按钮
	private final String[] TXT_SETTINGS_BUTTON = {
	    " 设置 ",   // 中文
	    " 設定 ",   // 繁中
	    " 設定 ",   // 日语
	    " 설정 ",   // 韩语
	    " Einstellungen ",   // 德语
	    " Settings ",        // 英语
	    " Ajustes ",         // 西班牙语
	    " Paramètres ",      // 法语
	    " Configurações ",   // 葡萄牙语
	    " Настройки "        // 俄语
	};
	private final String[] TXT_SETTINGS_HOVER = {
	    "-设置-",    // 中文
	    "-設定-",    // 繁中
	    "-設定-",    // 日语
	    "-설정-",    // 韩语
	    "-Einstellungen-",   // 德语
	    "-Settings-",        // 英语
	    "-Ajustes-",         // 西班牙语
	    "-Paramètres-",      // 法语
	    "-Configurações-",   // 葡萄牙语
	    "-Настройки-"        // 俄语
	};

	// 存档按钮
	private final String[] TXT_SAVE_BUTTON = {
	    " 存档 ",   // 中文
	    " 存檔 ",   // 繁中
	    " セーブ ",  // 日语
	    " 저장 ",   // 韩语
	    " Speichern ",   // 德语
	    " Save ",        // 英语
	    " Guardar ",     // 西班牙语
	    " Sauvegarder ", // 法语
	    " Salvar ",      // 葡萄牙语
	    " Сохранить "    // 俄语
	};
	private final String[] TXT_SAVE_HOVER = {
	    "-存档-",    // 中文
	    "-存檔-",    // 繁中
	    "-セーブ-",  // 日语
	    "-저장-",    // 韩语
	    "-Speichern-",   // 德语
	    "-Save-",        // 英语
	    "-Guardar-",     // 西班牙语
	    "-Sauvegarder-", // 法语
	    "-Salvar-",      // 葡萄牙语
	    "-Сохранить-"    // 俄语
	};

	// 退出按钮
	private final String[] TXT_EXIT_BUTTON = {
	    " 退出 ",   // 中文
	    " 退出 ",   // 繁中
	    " 終了 ",   // 日语
	    " 종료 ",   // 韩语
	    " Beenden ",   // 德语
	    " Exit ",      // 英语
	    " Salir ",     // 西班牙语
	    " Quitter ",   // 法语
	    " Sair ",      // 葡萄牙语
	    " Выход "      // 俄语
	};
	private final String[] TXT_EXIT_HOVER = {
	    "-退出-",    // 中文
	    "-退出-",    // 繁中
	    "-終了-",    // 日语
	    "-종료-",    // 韩语
	    "-Beenden-",   // 德语
	    "-Exit-",      // 英语
	    "-Salir-",     // 西班牙语
	    "-Quitter-",   // 法语
	    "-Sair-",      // 葡萄牙语
	    "-Выход-"      // 俄语
	};

	// 返回按钮
	private final String[] TXT_BACK_BUTTON = {
	    " 返回 ",   // 中文
	    " 返回 ",   // 繁中
	    " 戻る ",   // 日语
	    " 뒤로 ",   // 韩语
	    " Zurück ",    // 德语
	    " Back ",      // 英语
	    " Volver ",    // 西班牙语
	    " Retour ",    // 法语
	    " Voltar ",    // 葡萄牙语
	    " Назад "      // 俄语
	};
	private final String[] TXT_BACK_HOVER = {
	    "-返回-",    // 中文
	    "-返回-",    // 繁中
	    "-戻る-",    // 日语
	    "-뒤로-",    // 韩语
	    "-Zurück-",    // 德语
	    "-Back-",      // 英语
	    "-Volver-",    // 西班牙语
	    "-Retour-",    // 法语
	    "-Voltar-",    // 葡萄牙语
	    "-Назад-"      // 俄语
	};
    private Timeline blinkingTimeline;
	settingpane(guanka gk){
		language=gk.language;
		Image backg= new Image(getClass().getResourceAsStream("uipic/设置板.png"),screenheight*0.8/1028*798,screenheight*0.8,false,false),
		settingframe= new Image(getClass().getResourceAsStream("uipic/设置框.png"),88*suofang,56*suofang,false,false),
		settingbutton= new Image(getClass().getResourceAsStream("uipic/设置按钮.png"),56*suofang,32*suofang,false,false);
					
		getChildren().add(new ImageView(backg));
		setPickOnBounds(false);
		double wid=screenheight*0.8/1028*798;
		DropShadow gua=new DropShadow(5, Color.RED);
		Pane allpane=new Pane();
		getChildren().add(allpane);
		
		Pane settingpane=new Pane();
		Text fxsett=new Text(TXT_EFFECT_LABEL[language]);
		fxsett.setFont(f4);
		fxsett.setTranslateX(80*suofang);
		fxsett.setTranslateY(0.24*screenheight);
		fxsett.setFill(Color.BROWN);
		settingpane.getChildren().add(fxsett);
		Text grsett=new Text(TXT_GRAPHICS_LABEL[language]);
		grsett.setFont(f4);
		grsett.setTranslateX(80*suofang);
		grsett.setTranslateY(0.32*screenheight);
		grsett.setFill(Color.BROWN);
		settingpane.getChildren().add(grsett);
		Text back1=new Text(TXT_BACK_BUTTON[language]);
		back1.setFont(f8);
		back1.setTranslateX(wid/2-120*suofang);
		back1.setTranslateY(0.7*screenheight);
		back1.setFill(Color.BROWN);
		back1.setOnMouseEntered(e->{
			back1.setFill(Color.RED);
			back1.setEffect(gua);
			back1.setText(TXT_BACK_HOVER[language]);
		});
		back1.setOnMouseExited(e->{
			back1.setFill(Color.BROWN);
			back1.setEffect(null);
			back1.setText(TXT_BACK_BUTTON[language]);
		});
		back1.setOnMouseClicked(e->{
			settingpane.setMouseTransparent(true);
			allpane.setMouseTransparent(false);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(settingpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(allpane.opacityProperty(),1))
					);
			t.play();
		});
		
		
		ImageView fxk=new ImageView(settingframe);
		fxk.setTranslateX(wid-180*suofang);
		fxk.setTranslateY(0.2*screenheight);
		settingpane.getChildren().add(fxk);
		ImageView fxb=new ImageView(settingbutton);
		fxb.setTranslateX(wid-180*suofang+16*suofang);
		fxb.setTranslateY(0.2*screenheight+12*suofang);
		settingpane.getChildren().add(fxb);
		fxb.setClip(new Polygon(28*suofang,0,56*suofang,0,56*suofang,32*suofang,28*suofang,32*suofang));
		Pane fxp=new Pane();
		fxp.setTranslateX(80*suofang);
		fxp.setTranslateY(0.2*screenheight);
		fxp.setPrefSize(wid-150*suofang, 60*suofang);
		settingpane.getChildren().add(fxp);
		fxp.setOnMouseEntered(e->{
			fxsett.setFill(Color.RED);
			fxsett.setEffect(gua);
			fxsett.setText(TXT_EFFECT_HOVER[language]);
		});
		fxp.setOnMouseExited(e->{
			fxsett.setFill(Color.BROWN);
			fxsett.setEffect(null);
			fxsett.setText(TXT_EFFECT_LABEL[language]);
		});
		fxp.setOnMouseClicked(e->{
			if(gk.effectf) {
				gk.effectf=false;
				fxb.setClip(new Polygon(0,0,28*suofang,0,28*suofang,32*suofang,0,32*suofang));
			}
			else {
				gk.effectf=true;
				fxb.setClip(new Polygon(28*suofang,0,56*suofang,0,56*suofang,32*suofang,28*suofang,32*suofang));
			}
		});
		
		ImageView grk=new ImageView(settingframe);
		grk.setTranslateX(wid-180*suofang);
		grk.setTranslateY(0.28*screenheight);
		settingpane.getChildren().add(grk);
		ImageView grb=new ImageView(settingbutton);
		grb.setTranslateX(wid-180*suofang+16*suofang);
		grb.setTranslateY(0.28*screenheight+12*suofang);
		settingpane.getChildren().add(grb);
		grb.setClip(new Polygon(28*suofang,0,56*suofang,0,56*suofang,32*suofang,28*suofang,32*suofang));
		Pane grp=new Pane();
		grp.setTranslateX(80*suofang);
		grp.setTranslateY(0.28*screenheight);
		grp.setPrefSize(wid-150*suofang, 60*suofang);
		settingpane.getChildren().add(grp);
		grp.setOnMouseEntered(e->{
			grsett.setFill(Color.RED);
			grsett.setEffect(gua);
			grsett.setText(TXT_GRAPHICS_HOVER[language]);
		});
		grp.setOnMouseExited(e->{
			grsett.setFill(Color.BROWN);
			grsett.setEffect(null);
			grsett.setText(TXT_GRAPHICS_LABEL[language]);
		});
		grp.setOnMouseClicked(e->{
			if(gk.lowgrahf) {
				gk.lowgrahf=false;
				grb.setClip(new Polygon(0,0,28*suofang,0,28*suofang,32*suofang,0,32*suofang));
			}
			else {
				gk.lowgrahf=true;
				grb.setClip(new Polygon(28*suofang,0,56*suofang,0,56*suofang,32*suofang,28*suofang,32*suofang));
			}
		});
		
		
		settingpane.getChildren().add(back1);
		settingpane.setOpacity(0);
		settingpane.setMouseTransparent(true);
//		getChildren().add(settingpane);

		// ========= 存档面板 =========
		double boardScale = screenheight * 0.8 / 1028;
		Pane savepane = new Pane();
		Image savebg = new Image(getClass().getResourceAsStream("uipic/存档板.png"), screenheight*0.8/1028*798, screenheight*0.8, false, false);
		savepane.getChildren().add(new ImageView(savebg));
		
		// 存档槽位容器（6个 ImageView + Label 在 refreshSaveSlots 中创建）
		Pane slotsContainer = new Pane();
		slotsContainer.setPickOnBounds(false);
		savepane.getChildren().add(slotsContainer);
		
		// 换页圆形按钮容器
		Pane pageButtonsContainer = new Pane();
		pageButtonsContainer.setPickOnBounds(false);
		savepane.getChildren().add(pageButtonsContainer);
		
		// 存档面板的返回按钮
		Text saveBack = new Text(TXT_BACK_BUTTON[language]);
		saveBack.setFont(f4);
		saveBack.setTranslateX(wid/2 - 120*suofang);
		saveBack.setTranslateY(180*suofang);
		saveBack.setFill(Color.BROWN);
		saveBack.setOnMouseEntered(e->{
			saveBack.setFill(Color.RED);
			saveBack.setEffect(gua);
			saveBack.setText(TXT_BACK_HOVER[language]);
		});
		saveBack.setOnMouseExited(e->{
			saveBack.setFill(Color.BROWN);
			saveBack.setEffect(null);
			saveBack.setText(TXT_BACK_BUTTON[language]);
		});
		saveBack.setOnMouseClicked(e->{
			savepane.setMouseTransparent(true);
			allpane.setMouseTransparent(false);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(savepane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(allpane.opacityProperty(),1))
					);
			t.play();
			t.setOnFinished(e2 -> {
				savepane.setOpacity(0);
				allpane.setOpacity(1);
			});
		});
		savepane.getChildren().add(saveBack);
		
		savepane.setOpacity(0);
		savepane.setMouseTransparent(true);
		getChildren().add(savepane);
		
		// 初始创建6个存档槽位（Image + Label）
		for (int i = 0; i < 6; i++) {
			int col = i % 2;
			int row = i / 2;
			double sx = (col == 0 ? 132 : 422) * boardScale;
			double sy = (252 + row * 210) * boardScale;
			double sw = 222 * boardScale;
			double sh = 166 * boardScale;
			
			ImageView slotIv = new ImageView();
			slotIv.setFitWidth(sw);
			slotIv.setFitHeight(sh);
			slotIv.setTranslateX(sx);
			slotIv.setTranslateY(sy);
			slotIv.setPreserveRatio(false);
			slotIv.setUserData(i);
			slotsContainer.getChildren().add(slotIv);
			
			Text slotLabel = new Text("0");
			slotLabel.setFont(blendefine.f2);
			slotLabel.setFill(Color.WHITE);
			slotLabel.setTranslateX(sx + 5*suofang);
			slotLabel.setTranslateY(sy + sh - 15*suofang);
			slotLabel.setUserData(i);
			slotLabel.setMouseTransparent(true);
			slotsContainer.getChildren().add(slotLabel);
			
			// 点击透明覆盖区
			final int si = i;
			slotIv.setOnMouseClicked(e -> {
				@SuppressWarnings("unchecked")
				java.util.List<File> saves = (java.util.List<File>) slotsContainer.getUserData();
				if (saves != null && si < saves.size() && saves.get(si) != null) {
					onSaveSlotClicked(gk, saves.get(si).getAbsolutePath(), savepane, allpane);
				}
			});
		}
		
		GaussianBlur gb=new GaussianBlur();
		// 6个圆形换页按钮
		for (int pi = 0; pi < 7; pi++) {
			double cx = (256 + pi * 45) * boardScale;
			double cy = 894 * boardScale;
			Circle pageBtn = new Circle(cx, cy, 20 * boardScale);
			pageBtn.setFill(Color.TRANSPARENT);
			pageBtn.setEffect(gb);
//			pageBtn.setStroke(Color.DARKGRAY);
//			pageBtn.setStrokeWidth(1.5);
			final int pageIdx = pi;
			pageBtn.setOnMouseClicked(e -> {
				refreshSaveSlotsForPage(gk, slotsContainer, pageIdx, boardScale);
				// 更新所有页面按钮样式
				for (javafx.scene.Node n : pageButtonsContainer.getChildren()) {
					if (n instanceof Circle) {
						((Circle)n).setFill(Color.TRANSPARENT);
					}
				}
				pageBtn.setFill(Color.color(0,0,0,0.4));
			});
//			pageBtn.setOnMouseEntered(e -> pageBtn.setFill(Color.DODGERBLUE));
//			pageBtn.setOnMouseExited(e -> {
//				if (!pageBtn.getFill().equals(Color.DODGERBLUE)) {
//					pageBtn.setFill(Color.LIGHTGRAY);
//				}
//			});
			pageButtonsContainer.getChildren().add(pageBtn);
		}
		// 默认高亮第一页
		if (!pageButtonsContainer.getChildren().isEmpty()) {
			((Circle)pageButtonsContainer.getChildren().get(0)).setFill(Color.color(0,0,0,0.4));
		}
		// ========= 存档面板结束 =========
		
		Text setting=new Text(TXT_SETTINGS_BUTTON[language]);
		Text saving=new Text(TXT_SAVE_BUTTON[language]);
		Text exit=new Text(TXT_EXIT_BUTTON[language]);
		Text back=new Text(TXT_BACK_BUTTON[language]);
		setting.setFont(f8);
		setting.setTranslateX(wid/2-120*suofang);
		setting.setTranslateY(0.3*screenheight);
		setting.setFill(Color.BROWN);
		setting.setOnMouseEntered(e->{
			setting.setFill(Color.RED);
			setting.setEffect(gua);
			setting.setText(TXT_SETTINGS_HOVER[language]);
		});
		setting.setOnMouseExited(e->{
			setting.setFill(Color.BROWN);
			setting.setEffect(null);
			setting.setText(TXT_SETTINGS_BUTTON[language]);
		});
		setting.setOnMouseClicked(e->{
			settingpane.setMouseTransparent(false);
			allpane.setMouseTransparent(true);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(settingpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(settingpane.opacityProperty(),1))
					);
			t.play();
		});
//		allpane.getChildren().add(setting);
		
		saving.setFont(f8);
		saving.setTranslateX(wid/2-120*suofang);
		saving.setTranslateY(0.34*screenheight);
		saving.setFill(Color.BROWN);
		saving.setOnMouseEntered(e->{
			saving.setFill(Color.RED);
			saving.setEffect(gua);
			saving.setText(TXT_SAVE_HOVER[language]);
		});
		saving.setOnMouseExited(e->{
			saving.setFill(Color.BROWN);
			saving.setEffect(null);
			saving.setText(TXT_SAVE_BUTTON[language]);
		});
		saving.setOnMouseClicked(e->{
			savepane.setMouseTransparent(false);
			allpane.setMouseTransparent(true);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(savepane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(savepane.opacityProperty(),1))
					);
			t.play();
			refreshSaveSlots(gk, slotsContainer, boardScale);
		});
		allpane.getChildren().add(saving);
		
		exit.setFont(f8);
		exit.setTranslateX(wid/2-120*suofang);
		exit.setTranslateY(0.5*screenheight);
		exit.setFill(Color.BROWN);
		exit.setOnMouseEntered(e->{
			exit.setFill(Color.RED);
			exit.setEffect(gua);
			exit.setText(TXT_EXIT_HOVER[language]);
		});
		exit.setOnMouseExited(e->{
			exit.setFill(Color.BROWN);
			exit.setEffect(null);
			exit.setText(TXT_EXIT_BUTTON[language]);
		});
		allpane.getChildren().add(exit);
		exit.setOnMouseClicked(e->{
			gk.writetosaving();
			gk.settingshowf=false;
 			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.15),new KeyValue(this.translateYProperty(),-screenheight)));
     		t.play();
     		t.setOnFinished(e1->{
     			gk.game.getChildren().remove(this);
     		});
     		gk.exitguanka();
		});
		
		back.setFont(f8);
		back.setTranslateX(wid/2-120*suofang);
		back.setTranslateY(0.7*screenheight);
		back.setFill(Color.BROWN);
		back.setOnMouseEntered(e->{
			back.setFill(Color.RED);
			back.setEffect(gua);
			back.setText(TXT_BACK_HOVER[language]);
		});
		back.setOnMouseExited(e->{
			back.setFill(Color.BROWN);
			back.setEffect(null);
			back.setText(TXT_BACK_BUTTON[language]);
		});
		allpane.getChildren().add(back);
		back.setOnMouseClicked(e->{
			gk.settingshowf=false;
 			Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.15),new KeyValue(this.translateYProperty(),-screenheight)));
     		t.play();
     		t.setOnFinished(e1->{
     			gk.game.getChildren().remove(this);
     			gk.closesetting();
     		});	
		});
		
		centerAllTextsExceptEffects(wid);
	}
	settingpane(blendefine bdf){
		language=bdf.language;
		
		Image backg= new Image(getClass().getResourceAsStream("uipic/设置板.png"),screenheight*0.8/1028*798,screenheight*0.8,false,false),
		settingframe= new Image(getClass().getResourceAsStream("uipic/设置框.png"),88*suofang,56*suofang,false,false),
		settingbutton= new Image(getClass().getResourceAsStream("uipic/设置按钮.png"),56*suofang,32*suofang,false,false);
		this.setOnMouseClicked(e->{
			e.consume();
		});
		getChildren().add(new ImageView(backg));
		setPickOnBounds(false);
		double wid=screenheight*0.8/1028*798;
		DropShadow gua=new DropShadow(5, Color.RED);
		Pane allpane=new Pane();
		getChildren().add(allpane);
		
		Pane langpane=new Pane();
		
		
		Text back1=new Text(TXT_BACK_BUTTON[language]);
		back1.setFont(f8);
		back1.setTranslateX(wid/2-120*suofang);
		back1.setTranslateY(0.7*screenheight);
		back1.setFill(Color.BROWN);
		back1.setOnMouseEntered(e->{
			back1.setFill(Color.RED);
			back1.setEffect(gua);
			back1.setText(TXT_BACK_HOVER[language]);
		});
		back1.setOnMouseExited(e->{
			back1.setFill(Color.BROWN);
			back1.setEffect(null);
			back1.setText(TXT_BACK_BUTTON[language]);
		});
		back1.setOnMouseClicked(e->{
			langpane.setMouseTransparent(true);
			allpane.setMouseTransparent(false);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(langpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(allpane.opacityProperty(),1))
					);
			t.play();
		});
		langpane.getChildren().add(back1);
		langpane.setOpacity(0);
		langpane.setMouseTransparent(true);
		
		getChildren().add(langpane);

		
		Text langs=new Text(TXT_LANG_BUTTON[language]);
		Text setting=new Text(TXT_SETTINGS_BUTTON[language]);
		Text saving=new Text(TXT_SAVE_BUTTON[language]);
		Text exit=new Text(TXT_EXIT_BUTTON[language]);
		Text back=new Text(TXT_BACK_BUTTON[language]);
		
		if(bdf.playedtutorial==0) {
			blinkingTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5),
					new KeyValue(langs.opacityProperty(),0)));
			blinkingTimeline.setAutoReverse(true);
			blinkingTimeline.setCycleCount(Timeline.INDEFINITE);
			blinkingTimeline.play();
			langs.setOnMousePressed(e1->{
				blinkingTimeline.stop();
				langs.setOpacity(1);
			});
		}
		langs.setFont(f8);
		langs.setTranslateX(wid/2-120*suofang);
		langs.setTranslateY(0.3*screenheight);
		langs.setFill(Color.BROWN);
		langs.setOnMouseEntered(e->{
			langs.setFill(Color.RED);
			langs.setEffect(gua);
			langs.setText(TXT_LANG_HOVER[language]);
		});
		langs.setOnMouseExited(e->{
			langs.setFill(Color.BROWN);
			langs.setEffect(null);
			langs.setText(TXT_LANG_BUTTON[language]);
		});
		langs.setOnMouseClicked(e->{
			langpane.setMouseTransparent(false);
			allpane.setMouseTransparent(true);
			Timeline t=new Timeline(
					new KeyFrame(Duration.seconds(0.1),new KeyValue(langpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.3),new KeyValue(allpane.opacityProperty(),0)),
					new KeyFrame(Duration.seconds(0.5),new KeyValue(langpane.opacityProperty(),1))
					);
			t.play();
		});
		allpane.getChildren().add(langs);
		

		// 1. 创建音量子面板（初始透明不可见）
		Pane volumepane = new Pane();
		volumepane.setOpacity(0);
		volumepane.setMouseTransparent(true);
		getChildren().add(volumepane);

		// 2. 音量面板中的返回按钮
		Text back3 = new Text(TXT_BACK_BUTTON[language]);
		back3.setFont(f8);
		back3.setTranslateX(wid/2 - 120*suofang);
		back3.setTranslateY(0.7 * screenheight);
		back3.setFill(Color.BROWN);
		back3.setOnMouseEntered(e -> {
		    back3.setFill(Color.RED);
		    back3.setEffect(gua);
		    back3.setText(TXT_BACK_HOVER[language]);
		});
		back3.setOnMouseExited(e -> {
		    back3.setFill(Color.BROWN);
		    back3.setEffect(null);
		    back3.setText(TXT_BACK_BUTTON[language]);
		});
		back3.setOnMouseClicked(e -> {
		    volumepane.setMouseTransparent(true);
		    allpane.setMouseTransparent(false);
		    Timeline t = new Timeline(
		        new KeyFrame(Duration.seconds(0.1), new KeyValue(allpane.opacityProperty(), 0)),
		        new KeyFrame(Duration.seconds(0.3), new KeyValue(volumepane.opacityProperty(), 0)),
		        new KeyFrame(Duration.seconds(0.5), new KeyValue(allpane.opacityProperty(), 1))
		    );
		    t.play();
		});
		volumepane.getChildren().add(back3);

		// 3. 调用方法添加音量控制组件（滑块、数值、加减按钮）
		addVolumeControls(bdf,volumepane, gua, wid);

		// 4. 在主菜单 allpane 中添加“音量”按钮
		Text volumeBtn = new Text(TXT_VOLUME_BUTTON[language]);
		volumeBtn.setFont(f8);
		volumeBtn.setTranslateX(wid/2 - 120*suofang);
		volumeBtn.setTranslateY(0.4 * screenheight);  // 放在语言和键位之间
		volumeBtn.setFill(Color.BROWN);
		volumeBtn.setOnMouseEntered(e -> {
		    volumeBtn.setFill(Color.RED);
		    volumeBtn.setEffect(gua);
		    volumeBtn.setText(TXT_VOLUME_HOVER[language]);
		});
		volumeBtn.setOnMouseExited(e -> {
		    volumeBtn.setFill(Color.BROWN);
		    volumeBtn.setEffect(null);
		    volumeBtn.setText(TXT_VOLUME_BUTTON[language]);
		});
		volumeBtn.setOnMouseClicked(e -> {
		    volumepane.setMouseTransparent(false);
		    allpane.setMouseTransparent(true);
		    Timeline t = new Timeline(
		        new KeyFrame(Duration.seconds(0.1), new KeyValue(volumepane.opacityProperty(), 0)),
		        new KeyFrame(Duration.seconds(0.3), new KeyValue(allpane.opacityProperty(), 0)),
		        new KeyFrame(Duration.seconds(0.5), new KeyValue(volumepane.opacityProperty(), 1))
		    );
		    t.play();
		});
		allpane.getChildren().add(volumeBtn);
		
		saving.setFont(f8);
		saving.setTranslateX(wid/2-120*suofang);
		saving.setTranslateY(0.5*screenheight);
		saving.setFill(Color.BROWN);
		saving.setOnMouseEntered(e->{
			saving.setFill(Color.RED);
			saving.setEffect(gua);
			saving.setText(TXT_SAVE_HOVER[language]);
		});
		saving.setOnMouseExited(e->{
			saving.setFill(Color.BROWN);
			saving.setEffect(null);
			saving.setText(TXT_SAVE_BUTTON[language]);
		});
		saving.setOnMouseClicked(e->{
			
		});
//		allpane.getChildren().add(saving);
		
		exit.setFont(f8);
		exit.setTranslateX(wid/2-120*suofang);
		exit.setTranslateY(0.55*screenheight);
		exit.setFill(Color.BROWN);
		exit.setOnMouseEntered(e->{
			exit.setFill(Color.RED);
			exit.setEffect(gua);
			exit.setText(TXT_EXIT_HOVER[language]);
		});
		exit.setOnMouseExited(e->{
			exit.setFill(Color.BROWN);
			exit.setEffect(null);
			exit.setText(TXT_EXIT_BUTTON[language]);
		});
		allpane.getChildren().add(exit);
		exit.setOnMouseClicked(e->{
			System.exit(0);
		});
		
		back.setFont(f8);
		back.setTranslateX(wid/2-120*suofang);
		back.setTranslateY(0.7*screenheight);
		back.setFill(Color.BROWN);
		back.setOnMouseEntered(e->{
			back.setFill(Color.RED);
			back.setEffect(gua);
			back.setText(TXT_BACK_HOVER[language]);
		});
		back.setOnMouseExited(e->{
			back.setFill(Color.BROWN);
			back.setEffect(null);
			back.setText(TXT_BACK_BUTTON[language]);
		});
		allpane.getChildren().add(back);
		back.setOnMouseClicked(e->{
			bdf.switchsetting();
		});
		
		centerAllTextsExceptEffects(wid);
		
		addLanguageOptions(bdf,langpane, allpane,gua);
		
	}
	
	public void addLanguageOptions(blendefine bdf, Pane langpane, Pane allpane, Effect gua) {
	    // 八国语言数据：显示文本、语言代码、悬停文本
	    String[][] languages = {
	        {"中文",            "zh",   "-中文-"},
	        {"繁中",            "zht",   "-繁中-"},
	        {"日本語",          "ja",   "-日本語-"},
	        {"한국어",          "ko",   "-한국어-"},
	        {"Deutsch",        "de",   "-Deutsch-"},
	        {"English",        "en",   "-English-"},
	        {"Español",        "es",   "-Español-"},
	        {"Français",       "fr",   "-Français-"},
	        {"Português",      "pt",   "-Português-"},
	        {"Русский язык",   "ru",   "-Русский язык-"},
	    };

	    double startY = 240 * suofang;      // 起始 Y 坐标
	    double spacing = 60 * suofang;      // 垂直间距
	    double x1 = 80 * suofang;           // 第一列 X 坐标
	    double x2 = 320 * suofang;          // 第二列 X 坐标（根据实际需要调整）

	    for (int i = 0; i < languages.length; i++) {
	        String displayText = languages[i][0];
	        String langCode = languages[i][1];
	        String hoverText = languages[i][2];

	        // 确定列：前5个放在第一列，后5个放在第二列
	        double currentX = (i < 4) ? x1 : x2;
	        int rowIndex = i<4?i:i-4;           // 行索引（0-4）
	        double currentY = startY + rowIndex * spacing;

	        Text text = new Text(displayText);
	        text.setFont(Font.font(40));
	        text.setTranslateX(currentX);
	        text.setTranslateY(currentY);
	        text.setFill(Color.BROWN);
	        text.setUserData(langCode);
	        int k = i;

	        // 鼠标进入
	        text.setOnMouseEntered(e -> {
	            text.setFill(Color.RED);
	            text.setEffect(gua);
	            text.setText(hoverText);
	        });
	        // 鼠标离开
	        text.setOnMouseExited(e -> {
	            text.setFill(Color.BROWN);
	            text.setEffect(null);
	            text.setText(displayText);
	        });
	        // 鼠标点击
	        text.setOnMouseClicked(e -> {
	            langpane.setMouseTransparent(true);
	            allpane.setMouseTransparent(false);

	            switchLanguage(bdf, k);

	            Timeline t = new Timeline(
	                new KeyFrame(Duration.seconds(0.1), new KeyValue(allpane.opacityProperty(), 0)),
	                new KeyFrame(Duration.seconds(0.3), new KeyValue(langpane.opacityProperty(), 0)),
	                new KeyFrame(Duration.seconds(0.5), new KeyValue(allpane.opacityProperty(), 1))
	            );
	            t.play();
	        });

	        langpane.getChildren().add(text);
	    }
	}
	private void addVolumeControls(blendefine bdf,Pane volumepane, Effect gua, double wid) {
	    // 音量初始值（假设全局音量变量为 blendefine.volume，范围 0.0~1.0）
	    double currentVolume = bdf.volume;  // 请根据实际情况调整

	    // 显示音量的数值文本
	    Text volumeValue = new Text(String.format("%.0f%%", currentVolume * 100));
	    volumeValue.setFont(f8);
	    volumeValue.setTranslateX(wid/2 - 70*suofang);
	    volumeValue.setTranslateY(0.3 * screenheight);
	    volumeValue.setFill(Color.BROWN);
	    volumeValue.setUserData(currentVolume);

	    // 音量滑块 (JavaFX Slider)
	    Slider volumeSlider = new Slider(0, 1, currentVolume);
	    volumeSlider.setPrefWidth(300 * suofang);
	    volumeSlider.setTranslateX(wid/2 - 150*suofang);
	    volumeSlider.setTranslateY(0.38 * screenheight);
//	    volumeSlider.setShowTickLabels(true);
	    volumeSlider.setShowTickMarks(true);
	    volumeSlider.setMajorTickUnit(0.25);
	    volumeSlider.setMinorTickCount(4);
	    volumeSlider.setBlockIncrement(0.05);
	    volumeSlider.setOnMouseMoved(e->{
	    	blendefine.nowmousex=e.getScreenX();
	    	blendefine.nowmousey=e.getScreenY();
	    });
	    volumeSlider.setOnMouseDragged(e->{
	    	blendefine.nowmousex=e.getScreenX();
	    	blendefine.nowmousey=e.getScreenY();
	    });
	    // 滑块样式（简易，可根据需要美化）
//	    volumeSlider.setStyle(
//	        "-fx-control-inner-background: #8B4513;" +
//	        "-fx-track-color: #D2B48C;" +
//	        "-fx-thumb-color: #FF0000;"
//	    );
	    Platform.runLater(() -> {
	        // 1. 替换滑块按钮(Thumb)的图片
	        StackPane thumb = (StackPane) volumeSlider.lookup(".thumb");
	        if (thumb != null) {
	            ImageView thumbImage = new ImageView(new Image(getClass().getResourceAsStream("uipic/设置按钮.png")));
	            thumbImage.setFitWidth(60*suofang);   // 按需设置图片显示大小
	            thumbImage.setFitHeight(40*suofang);
	            thumbImage.setPreserveRatio(true);
	            thumb.getChildren().clear();
	            thumb.getChildren().add(thumbImage);
	            thumb.setPrefSize(60*suofang, 40*suofang);
	            thumb.setFocusTraversable(false);
	            thumb.setStyle(
	            		"    -fx-background-radius: 9px;"
	            		+ "    -fx-background-color: transparent;"
	            		+ "    -fx-focus-color: transparent;"
	            		+ "    -fx-faint-focus-color: transparent;");
	        }
	        // 2. 替换滑块轨道的背景图片 (如果需要)
	        StackPane track = (StackPane) volumeSlider.lookup(".track");
	        if (track != null) {
	            track.setStyle(
	                           "-fx-background-size: stretch; " +
	                           "-fx-background-color: transparent;");
	        }
	    });

	    // 滑块值变化时更新数值文本和全局音量
	    volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
	        double val = newVal.doubleValue();
	        volumeValue.setText(String.format("%.0f%%", val * 100));
	        volumeValue.setUserData(val);
	        // 更新全局音量（请根据实际变量名修改）
	        bdf.volume = val;
	        // 如果有音频播放器，可在此处设置音量
	        bdf.mp.setVolume(val);
	    });

	    // 鼠标悬停滑块时高亮（可选）
	    volumeSlider.setOnMouseEntered(e -> volumeSlider.setStyle(
	        "-fx-control-inner-background: #8B4513;" +
	        "-fx-track-color: #FF6347;" +
	        "-fx-thumb-color: #FF0000;"
	    ));
	    volumeSlider.setOnMouseExited(e -> volumeSlider.setStyle(
	        "-fx-control-inner-background: #8B4513;" +
	        "-fx-track-color: #D2B48C;" +
	        "-fx-thumb-color: #FF0000;"
	    ));

	    Image indicatorImage = new Image(getClass().getResourceAsStream("uipic/设置条1.png"));
	    ImageView indicator = new ImageView(indicatorImage);
	    indicator.setFitWidth(320 * suofang);   // 指示器完整宽度
	    indicator.setFitHeight(30 * suofang);   // 指示器高度
	    indicator.setPreserveRatio(false);
	    indicator.setTranslateX(wid/2 - 160 * suofang);  // 与滑块对齐的位置
	    indicator.setTranslateY(0.38 * screenheight+5*suofang);  // 放在滑块下方适当位置
	 // 2. 创建裁剪矩形（初始宽度根据当前音量值计算）
	    double initialVolume = bdf.volume;
	    Rectangle clipRect = new Rectangle(
	        0, 0, 
	        indicator.getFitWidth() * initialVolume,  // 初始裁剪宽度
	        indicator.getFitHeight()
	    );
	    indicator.setClip(clipRect);

	    // 4. 绑定滑块值变化，动态裁剪指示器
	    volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
	        double percent = newVal.doubleValue();  // 假设滑块范围 0.0~1.0
	        double newWidth = indicator.getFitWidth() * percent;
	        // 更新裁剪矩形的宽度，同时保留高度
	        clipRect.setWidth(newWidth);
	        
	        // 可选：添加一个平移动画让裁剪更平滑（不需要的话直接setWidth即可）
	        // 如果需要瞬间变化，这已经够了，因为滑块拖动本身是连续的，视觉上就是动画。
	    });
	    
	    Image outImage = new Image(getClass().getResourceAsStream("uipic/设置条.png"));
	    ImageView outiv = new ImageView(outImage);
	    outiv.setFitWidth(330 * suofang);   // 指示器完整宽度
	    outiv.setFitHeight(50 * suofang);   // 指示器高度
	    outiv.setPreserveRatio(false);
	    outiv.setTranslateX(wid/2 - 165 * suofang);  // 与滑块对齐的位置
	    outiv.setTranslateY(0.38 * screenheight-5*suofang);  // 放在滑块下方适当位置
	    
	    // 将所有控件添加到音量子面板
	    volumepane.getChildren().addAll(indicator,outiv,volumeSlider, volumeValue);
	}
	
	// 语言切换方法示例（根据实际需求实现）
	private void switchLanguage(blendefine bdf,int l) {
		// 例如：更新全局 Locale、重新加载资源文件等
		bdf.language=l;
		bdf.switchsetting();
		System.out.println("切换到语言: " + l);
		Timeline t = new Timeline(new KeyFrame(Duration.seconds(0.5), e->{
			bdf.loadfont();
			bdf.settingpane=new settingpane(bdf);
			bdf.switchsetting();
			destroy();
		}));
		t.play();
	}
	
	private void centerAllTextsExceptEffects(double backgroundWidth) {
	    // 递归遍历所有子节点
	    for (javafx.scene.Node node : getChildren()) {
	        centerTextIfNeeded(node, backgroundWidth);
	    }
	}

	private void centerTextIfNeeded(javafx.scene.Node node, double backgroundWidth) {
	    if (node instanceof Text) {
	        Text text = (Text) node;
	        String content = text.getText();
	        // 如果文本不包含 '('，也不包含数字（存档时间戳），则居中
	        if (content != null && !content.contains("(") && !content.matches(".*\\d.*")) {
	            double textWidth = text.getLayoutBounds().getWidth();
	            double x = (backgroundWidth - textWidth) / 2;
	            text.setTranslateX(x);
	        }
	    } else if (node instanceof Pane) {
	        // 如果是容器，继续递归遍历其子节点
	        for (javafx.scene.Node child : ((Pane) node).getChildren()) {
	            centerTextIfNeeded(child, backgroundWidth);
	        }
	    }
	}
	
	// ========== 存档界面辅助方法 ==========
	
	/** 列出当前关卡的所有存档文件（按修改时间倒序） */
	private java.util.List<File> listSaveFiles(guanka gk) {
		String dirPath = "saves/level_" + gk.level;
		File dir = new File(dirPath);
		if (!dir.exists() || !dir.isDirectory()) return new java.util.ArrayList<>();
		File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
		if (files == null) return new java.util.ArrayList<>();
		Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
		return Arrays.asList(files);
	}
	
	/** 刷新存档槽位（显示第 0 页） */
	private void refreshSaveSlots(guanka gk, Pane slotsContainer, double boardScale) {
		refreshSaveSlotsForPage(gk, slotsContainer, 0, boardScale);
	}
	
	/** 刷新存档槽位（指定页） */
	private void refreshSaveSlotsForPage(guanka gk, Pane slotsContainer, int page, double boardScale) {
		java.util.List<File> allSaves = listSaveFiles(gk);
		// 存到容器 userData 供点击使用
		slotsContainer.setUserData(allSaves);
		
		int start = page * 6;
		int end = Math.min(start + 6, allSaves.size());
		
		// 每个槽位 2 个子节点：0=slot0_iv, 1=slot0_label, 2=slot1_iv, 3=slot1_label, ...
		for (int i = 0; i < 6; i++) {
			int idx2 = i * 2;
			ImageView iv = (ImageView) slotsContainer.getChildren().get(idx2);
			Text label = (Text) slotsContainer.getChildren().get(idx2 + 1);
			
			if (start + i < end) {
				File saveFile = allSaves.get(start + i);
				// 加载截图（优先 png，兼容 jpg）
				String imgPath = saveFile.getAbsolutePath().replace(".dat", ".png");
				File imgFile = new File(imgPath);
				if (!imgFile.exists()) {
					String jpgPath = saveFile.getAbsolutePath().replace(".dat", ".jpg");
					imgFile = new File(jpgPath);
				}
				if (imgFile.exists()) {
					try {
						Image img = new Image(imgFile.toURI().toString());
						iv.setImage(img);
						// 保持比例居中显示，多余裁剪（cover 模式）
						double iw = img.getWidth(), ih = img.getHeight();
						double sw = iv.getFitWidth(), sh = iv.getFitHeight();
						if (iw > 0 && ih > 0 && sw > 0 && sh > 0) {
							double slotRatio = sw / sh;
							double imgRatio = iw / ih;
							if (imgRatio > slotRatio) {
								// 图片更宽：裁剪左右，高度填满
								double vw = ih * slotRatio;
								double vx = (iw - vw) / 2;
								iv.setViewport(new Rectangle2D(vx, 0, vw, ih));
							} else {
								// 图片更高：裁剪上下，宽度填满
								double vh = iw / slotRatio;
								double vy = (ih - vh) / 2;
								iv.setViewport(new Rectangle2D(0, vy, iw, vh));
							}
						}
					} catch (Exception e) {
						iv.setImage(null);
					}
				} else {
					iv.setImage(null);
				}
				// 存档名称（去掉前缀 "save_" 和扩展名）
				String name = saveFile.getName();
				if (name.startsWith("save_")) name = name.substring(5);
				if (name.endsWith(".dat")) name = name.substring(0, name.length() - 4);
				label.setText(name);
				iv.setVisible(true);
				label.setVisible(true);
			} else {
				iv.setImage(null);
				label.setText("");
				iv.setVisible(false);
				label.setVisible(false);
			}
		}
	}
	
	/** 点击存档槽位：读取存档并关闭面板 */
	private void onSaveSlotClicked(guanka gk, String filePath, Pane savepane, Pane allpane) {
		gk.readfromsaving(filePath);
		// 重置面板状态，下次复用时不挡住下层按钮
		savepane.setOpacity(0);
		savepane.setMouseTransparent(true);
		allpane.setOpacity(1);
		allpane.setMouseTransparent(false);
		// 关闭设置面板回到游戏
		gk.settingshowf = false;
		// 先从 game 中移除设置面板（存档已读取，场景已重建）
		if (savepane.getParent() != null && gk.game.getChildren().contains(savepane.getParent())) {
			gk.game.getChildren().remove(savepane.getParent());
		}
		gk.closesetting();
	}
	
	public void destroy() {
        // 1. 停止所有可能持续运行的动画
        if (blinkingTimeline != null) {
            blinkingTimeline.stop();
            blinkingTimeline = null;
        }

        // 2. 清理所有子节点中的图像和事件监听（可选）
        for (javafx.scene.Node node : getChildren()) {
            if (node instanceof ImageView) {
                ImageView iv = (ImageView) node;
                if (iv.getImage() != null) {
                    iv.setImage(null);  // 帮助 GC 回收图像
                }
            }
            // 如果需要，可以递归清理更深层的节点
            // 但通常节点被移除后即可被回收
            node.setOnMouseClicked(null);
            node.setOnMouseEntered(null);
            node.setOnMouseExited(null);
            node.setOnMousePressed(null);
            node.setOnMouseDragged(null);
        }

        // 3. 从父容器中移除自身
        if (getParent() != null) {
            ((Pane) getParent()).getChildren().remove(this);
        }

        // 4. 清空所有子节点
        getChildren().clear();
    }
}
