package application;

import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.AddEmployeeSceneAuto;
import scenes.AddEmployeeSceneManual;
import scenes.AddTaskSceneAuto;
import scenes.AddTaskSceneManual;
import scenes.HomeScene;
import taskMatch.Employee;
import scenes.OptionsScene;
import scenes.ResultsScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("unused")
public class Main extends Application {
  
  private static Scene currentScene;
  public static List<Employee> employeeList;
  
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new HomeScene(primaryStage, root, 920, 600);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToOptions(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new OptionsScene(primaryStage, root, 630, 380);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToResults(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new ResultsScene(primaryStage, root, 820, 420);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToHome(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new HomeScene(primaryStage, root, 920, 600); //TODO: add list instead of null
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddEmployeesAuto(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneAuto(primaryStage, root, 500, 500);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddEmployeesManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneManual(primaryStage, root, 500, 500);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddTasksManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddTaskSceneManual(primaryStage, root, 500, 500);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void switchToAddTasksAuto(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddTaskSceneAuto(primaryStage, root, 500, 500);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
