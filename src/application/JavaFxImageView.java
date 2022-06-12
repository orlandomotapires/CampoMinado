package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFxImageView extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new HBox(15);
		
		Image img = new Image("bombaLaranja.png");
		pane.getChildren().add(new ImageView(img));
		
		Scene scene = new Scene(pane, 600, 300);
		
		primaryStage.setTitle("An image view Pane App");
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
