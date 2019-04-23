package application;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.HomeScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
  
  Scene homeScene;
  Scene addEmployee;
  
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      Scene homeScene = new HomeScene(primaryStage, root, 400, 400);
      homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(homeScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
