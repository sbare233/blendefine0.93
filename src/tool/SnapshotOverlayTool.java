package tool;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnapshotOverlayTool extends Application {

    private BorderPane root;
    private PanoUI demoUI;
    private ImageView overlayImageView;
    private List<WritableImage> snapshots = new ArrayList<>();
    private List<Rectangle> highlightAreas = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        demoUI = new PanoUI();
        
        // 创建顶部控制面板
        HBox controlPanel = createControlPanel();
        
        // 创建图像显示区域
        overlayImageView = new ImageView();
        overlayImageView.setFitWidth(600);
        overlayImageView.setFitHeight(400);
        overlayImageView.setPreserveRatio(true);
        
        StackPane imageContainer = new StackPane(overlayImageView);
        imageContainer.setPadding(new Insets(10));
        imageContainer.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px;");
        
        root.setTop(controlPanel);
        root.setCenter(imageContainer);
        root.setBottom(demoUI);
        
        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("JavaFX 快照叠加工具 - UI 覆盖分析");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel() {
        Button captureBtn = new Button("捕获快照");
        captureBtn.setOnAction(e -> captureSnapshot());
        
        Button overlayBtn = new Button("叠加快照");
        overlayBtn.setOnAction(e -> overlaySnapshots());
        
        Button clearBtn = new Button("清除快照");
        clearBtn.setOnAction(e -> clearSnapshots());
        
        Button highlightBtn = new Button("高亮未覆盖区域");
        highlightBtn.setOnAction(e -> highlightUncoveredAreas());
        
        HBox controlPanel = new HBox(10, captureBtn, overlayBtn, clearBtn, highlightBtn);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #dddddd; -fx-border-width: 0 0 1px 0;");
        
        return controlPanel;
    }

    private void captureSnapshot() {
        // 捕获演示 UI 的快照
        WritableImage snapshot = demoUI.snapshot(new SnapshotParameters(), null);
        snapshots.add(snapshot);
        
        // 显示提示信息
        showAlert("快照已捕获", "已成功捕获当前 UI 状态的快照。\n当前快照数量: " + snapshots.size());
    }

    private void overlaySnapshots() {
        if (snapshots.isEmpty()) {
            showAlert("无快照", "请先捕获至少一个快照。");
            return;
        }
        
        // 创建叠加图像
        WritableImage baseImage = snapshots.get(0);
        int width = (int) baseImage.getWidth();
        int height = (int) baseImage.getHeight();
        
        WritableImage overlayedImage = new WritableImage(width, height);
        
        // 将每个快照绘制到叠加图像上（使用半透明效果）
        for (WritableImage snapshot : snapshots) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color originalColor = overlayedImage.getPixelReader().getColor(x, y);
                    Color snapshotColor = snapshot.getPixelReader().getColor(x, y);
                    
                    // 如果快照像素不是透明的，则混合颜色
                    if (snapshotColor.getOpacity() > 0.1) {
                        // 使用半透明混合
                        Color blendedColor = originalColor.interpolate(snapshotColor, 0.5);
                        overlayedImage.getPixelWriter().setColor(x, y, blendedColor);
                    }
                }
            }
        }
        
        overlayImageView.setImage(overlayedImage);
        showAlert("快照已叠加", "已成功叠加 " + snapshots.size() + " 个快照。");
    }

    private void clearSnapshots() {
        snapshots.clear();
        overlayImageView.setImage(null);
        clearHighlights();
        showAlert("已清除", "所有快照已清除。");
    }

    private void highlightUncoveredAreas() {
        if (snapshots.isEmpty()) {
            showAlert("无快照", "请先捕获至少一个快照。");
            return;
        }
        
        clearHighlights();
        
        // 获取第一个快照作为基础
        WritableImage baseImage = snapshots.get(0);
        int width = (int) baseImage.getWidth();
        int height = (int) baseImage.getHeight();
        
        // 创建覆盖图
        boolean[][] covered = new boolean[width][height];
        
        // 标记所有被覆盖的像素
        for (WritableImage snapshot : snapshots) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = snapshot.getPixelReader().getColor(x, y);
                    if (color.getOpacity() > 0.1) {
                        covered[x][y] = true;
                    }
                }
            }
        }
        
        // 查找并高亮显示未覆盖的区域
        for (int y = 0; y < height; y += 5) { // 使用步长减少计算量
            for (int x = 0; x < width; x += 5) {
                if (!covered[x][y]) {
                    Rectangle highlight = new Rectangle(x, y, 10, 10);
                    highlight.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                    highlight.setStroke(Color.RED);
                    highlight.setMouseTransparent(true);
                    demoUI.getChildren().add(highlight);
                    highlightAreas.add(highlight);
                }
            }
        }
        
        showAlert("未覆盖区域已高亮", "红色半透明矩形标记了未覆盖的区域。");
    }

    private void clearHighlights() {
        demoUI.getChildren().removeAll(highlightAreas);
        highlightAreas.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // 演示用的 UI 组件
    class PanoUI extends Pane {
        public PanoUI() {
            setPrefSize(600, 200);
            setStyle("-fx-background-color: #e8e8e8; -fx-border-color: #aaaaaa; -fx-border-width: 1px;");
            
            // 添加一些示例 UI 控件
            Button button1 = new Button("按钮 1");
            button1.setLayoutX(50);
            button1.setLayoutY(30);
            
            Button button2 = new Button("按钮 2");
            button2.setLayoutX(50);
            button2.setLayoutY(80);
            
            TextField textField = new TextField();
            textField.setLayoutX(150);
            textField.setLayoutY(30);
            textField.setPrefWidth(150);
            textField.setPromptText("输入文本");
            
            CheckBox checkBox = new CheckBox("选择选项");
            checkBox.setLayoutX(150);
            checkBox.setLayoutY(80);
            
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("选项 1", "选项 2", "选项 3");
            comboBox.setValue("选项 1");
            comboBox.setLayoutX(320);
            comboBox.setLayoutY(30);
            
            Slider slider = new Slider();
            slider.setLayoutX(320);
            slider.setLayoutY(80);
            slider.setPrefWidth(150);
            
            // 添加一些随机矩形来模拟复杂 UI
            for (int i = 0; i < 5; i++) {
                Rectangle rect = new Rectangle(100 + i*80, 120, 50, 50);
                rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1, 1, 0.7));
                rect.setStroke(Color.DARKBLUE);
                getChildren().add(rect);
            }
            
            getChildren().addAll(button1, button2, textField, checkBox, comboBox, slider);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}