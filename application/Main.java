package application;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.AddEmployeeSceneAuto;
import scenes.HomeScene;
import scenes.OptionsScene;
import scenes.ResultsScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("unused")
public class Main extends Application {
  
  private static Scene homeScene;
  private static Scene addEmployee;
  private static Scene optionsScene;
  private static Scene resultsScene;
  private static Scene addEmployeesAuto;
  private static Scene addEmployeesManual;
  
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      homeScene = new HomeScene(primaryStage, root, 920, 600);
      primaryStage.setScene(homeScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToOptions(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      optionsScene = new OptionsScene(primaryStage, root, 630, 380);
      primaryStage.setScene(optionsScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToResults(Stage primaryStage) {
	    try {
	      BorderPane root = new BorderPane();
	      resultsScene = new ResultsScene(primaryStage, root, 620, 380);
	      primaryStage.setScene(resultsScene);
	      primaryStage.show();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
  
  public static void switchToHome(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      homeScene = new HomeScene(primaryStage, root, 9000, 6080);
      primaryStage.setScene(homeScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddEmployeesAuto(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      addEmployeesAuto = new AddEmployeeSceneAuto(primaryStage, root, 9000, 6080);
      primaryStage.setScene(addEmployeesAuto);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddEmployeesManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      addEmployeesManual = new HomeScene(primaryStage, root, 9000, 6080);
      primaryStage.setScene(addEmployeesManual);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
