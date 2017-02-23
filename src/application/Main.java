package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
		Font.loadFont( Main.class.getResource("voynich.ttf").toExternalForm(), 10 );

		primaryStage.setMinWidth(680);        
	    primaryStage.setMinHeight(420);
	    
        primaryStage.setTitle("Transliterate text");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
