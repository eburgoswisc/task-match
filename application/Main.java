package application;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.HomeScene;
import scenes.OptionsScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("unused")
public class Main extends Application {
  
  private static Scene homeScene;
  private static Scene addEmployee;
  private static Scene optionsScene;
  private static Scene resultsScene;
  
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      homeScene = new HomeScene(primaryStage, root, 900, 600);
      primaryStage.setScene(homeScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToOptions(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      optionsScene = new OptionsScene(primaryStage, root, 750, 420);
      primaryStage.setScene(optionsScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
