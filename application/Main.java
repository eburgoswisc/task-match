package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

  private static Scene currentScene;
  private static ArrayList<Employee> allEmployees;
  private static ArrayList<Employee> employeesInUnit;
  private static ArrayList<Task> allTasks;
  private static String curFileOpenPath;
  private static String curFileOpenName;

  @Override
  public void start(Stage primaryStage) {
    curFileOpenPath = "";
    setCurFileOpenName("file not found");
    allTasks = new ArrayList<>();
    allEmployees = new ArrayList<>();
    employeesInUnit = new ArrayList<>();

    primaryStage.setOnCloseRequest(e -> {
      closeReq(e);
    });

    try {
      BorderPane root = new BorderPane();
      currentScene = new HomeScene(primaryStage, root, 920, 600);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToOptions(Stage primaryStage, Employee curEmployee) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new OptionsScene(primaryStage, root, 630, 380, curEmployee);
      // currentScene = new OptionsScene(primaryStage, root, 630, 380, curEmployee);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToResults(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new ResultsScene(primaryStage, root, 770, 420);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToHome(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new HomeScene(primaryStage, root, 920, 600);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToAddEmployeesAuto(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneAuto(primaryStage, root, 500, 170);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToAddEmployeesManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneManual(primaryStage, root, 550, 380);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void switchToAddTasksManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddTaskSceneManual(primaryStage, root, 500, 200);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static ArrayList<Employee> getAllEmployees() {
    return allEmployees;
  }

  public static ArrayList<Employee> getEmployeesInUnit() {
    return employeesInUnit;
  }

  public static ArrayList<Task> getAllTasks() {
    return allTasks;
  }

  public static void setCurFileOpen(String curFileOpen) {
    Main.curFileOpenPath = curFileOpen;
  }

  public static String getCurFileOpen() {
    return curFileOpenPath;
  }

  public static String getCurFileOpenName() {
    return curFileOpenName;
  }

  public static void setCurFileOpenName(String curFileOpenName) {
    Main.curFileOpenName = curFileOpenName;
  }

  public static void setTask(int i, Task t) {
    allTasks.set(i, t);
  }

  public static void addTask(Task task) {
    allTasks.add(task);
  }

  public static void removeEmployeeFromTheUnit(Employee e) {
    employeesInUnit.remove(e);
  }

  protected static void closeReq(Event e) {

    if (!curFileOpenName.equals("file not found")) {
      Alert closeAlert = new Alert(AlertType.NONE);
      closeAlert.setTitle("Save before quitting?");
      closeAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
      closeAlert.setContentText("Would you like to save \'" + curFileOpenName + "\'?");
      closeAlert.showAndWait();
      
      if (closeAlert.getResult().equals(ButtonType.YES)) {
        JSONFileParser.write(curFileOpenPath);
      }
      if (closeAlert.getResult().equals(ButtonType.CANCEL)) {
        e.consume();
      }
    }
  }

  protected static void produceResult() {
    ArrayList<Task> tempTasks = new ArrayList<Task>(allTasks);
    Random rng = new Random();
    int jobIndex;
    for (Employee e : getEmployeesInUnit()) {
      if (tempTasks.isEmpty()) {
        break;
      }
      if (tempTasks.size() == 1) {
        jobIndex = 0;
      } else {
        jobIndex = rng.nextInt(tempTasks.size());
      }
      tempTasks.get(jobIndex).getEmployees().add(e);
      tempTasks.remove(jobIndex);
    }
  }
}
