package fx;

import fx.ChaoticParticleSystem.Particle;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class test extends Application {
	@Override
    public void start(Stage stage) {
		ParticleSystem canvas=new ParticleSystem(700,700,0,0,3000,2);
        
        StackPane root = new StackPane(canvas);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Scene scene = new Scene(root, 1000, 1000);
        
        
        stage.setTitle("Particle System");
        stage.setScene(scene);
        stage.show();
    }
	public static void main(String[] args) {
        launch(args);
    }
}
