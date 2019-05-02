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

/**
 * main
 * 
 * @author CS400 aTeam 7
 * 
 *         Main class for the Fair Job Planning System. This class serves as the main driver class
 *         for the program, and handles things such as switching scenes and initializing variables
 *         that are used by the other classes.
 */
public class Main extends Application {

  private static Scene currentScene; // only need one scene field since it will be used to change
  private static ArrayList<Employee> allEmployees; // list of all employees loaded from json file
  private static ArrayList<Employee> employeesInUnit; // list of only employees who are to be assigned tasks
  private static ArrayList<Task> allTasks; // list of all tasks loaded from json file
  private static String curFileOpenPath; // current file open filepath
  private static String curFileOpenName; // current file open name

  /**
   * starts the program, and initializes field variables
   */
  @Override
  public void start(Stage primaryStage) {
    curFileOpenPath = "";
    curFileOpenName = "file not found";
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

  /**
   * Control method handles switching to OptionsScene
   * 
   * @param primaryStage is the main stage of the program
   * @param curEmployee  is the employee whose information needs to be edited
   */
  protected static void switchToOptions(Stage primaryStage, Employee curEmployee) {
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

  /**
   * Control method handles switching to ResultsScene
   * 
   * @param primaryStage is the main stage of the program
   */
  protected static void switchToResults(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new ResultsScene(primaryStage, root, 770, 420);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Control method handles switching back to HomeScene after another Scene is finished with its
   * duty.
   * 
   * @param primaryStage is the main stage of the program
   */
  protected static void switchToHome(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new HomeScene(primaryStage, root, 920, 600);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Control method handles switch to AddEmployeesSceneAuto
   * 
   * @param primaryStage is the main stage of the program
   */
  protected static void switchToAddEmployeesAuto(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneAuto(primaryStage, root, 500, 170);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Control method handles the switch to AddEmployeesManualScene
   * 
   * @param primaryStage is the main stage of the program
   */
  protected static void switchToAddEmployeesManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddEmployeeSceneManual(primaryStage, root, 550, 380);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Control method handles the switch to AddTasksSceneManual
   * 
   * @param primaryStage is the main stage of the program
   */
  protected static void switchToAddTasksManual(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      currentScene = new AddTaskSceneManual(primaryStage, root, 500, 200);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Main method, only makes a call to launch(args) to start the GUI
   * 
   * @param args command line args
   */
  protected static void main(String[] args) {
    launch(args);
  }

  /**
   * Getter for the list of all employees
   * 
   * @return allEmployees
   */
  protected static ArrayList<Employee> getAllEmployees() {
    return allEmployees;
  }

  /**
   * Getter for the list of employees who have to be assigned tasks
   * 
   * @return employeesInUnit
   */
  protected static ArrayList<Employee> getEmployeesInUnit() {
    return employeesInUnit;
  }

  /**
   * Getter for the list of all tasks
   * 
   * @return allTasks
   */
  protected static ArrayList<Task> getAllTasks() {
    return allTasks;
  }

  /**
   * Setter for the file path of the currently opened file
   * 
   * @param curFileOpen is what to set the file path
   */

  protected static void setCurFileOpen(String curFileOpen) {
    Main.curFileOpenPath = curFileOpen;
  }


  /**
   * Getter for the current file's open path
   * 
   * @return curFileOpenPath
   */
  protected static String getCurFileOpen() {
    return curFileOpenPath;
  }

  /**
   * Getter for the current file open's name
   * 
   * @return curFileOpenName
   */

  protected static String getCurFileOpenName() {
    return curFileOpenName;
  }


  /**
   * Setter for the current file open's name
   * 
   * @param curFileOpenName is what to set the file's name to
   */
  protected static void setCurFileOpenName(String curFileOpenName) {
    Main.curFileOpenName = curFileOpenName;
  }

  /**
   * Control method for adding a new task to the task list, called in AddTasksSceneManual
   * 
   * @param task is the task to add
   */

  protected static void addTask(Task task) {
    allTasks.add(task);
  }

  /**
   * Private method for defining behavior on when the program closes
   * 
   * @param e is the event that occurs when the program closes
   */

  private void closeReq(Event e) {

    if (!curFileOpenName.equals("file not found")) {
      // Alert message before closing for asking if the user if they want to save
      Alert closeAlert = new Alert(AlertType.NONE);
      closeAlert.setTitle("Save before quitting?");
      closeAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
      closeAlert.setContentText("Would you like to save \'" + curFileOpenName + "\'?");
      closeAlert.showAndWait();

      if (closeAlert.getResult().equals(ButtonType.YES)) {
        JSONFileParser.write(curFileOpenPath); // saves the file if user chooses yes
      }
      if (closeAlert.getResult().equals(ButtonType.CANCEL)) {
        e.consume(); // doesn't close the program, and doesn't save
      }
    }
  }

  /**
   * Control method for assigning tasks to the employees in unit
   */

  protected static void produceResult() {
    ArrayList<Task> tempTasks = new ArrayList<Task>(allTasks); // creates a deep copy of allTasks
    Random rng = new Random(); // random number generator
    int jobIndex;
    for (Employee e : getEmployeesInUnit()) {
      if (tempTasks.isEmpty()) {
        break; // ends if there are less employees than tasks
      }
      if (tempTasks.size() == 1) { // if there is only one task left, the index has to be zero
        jobIndex = 0;
      } else {
        jobIndex = rng.nextInt(tempTasks.size()); // generates a random index from 0 to the max
                                                  // index of
                                                  // tasks remaining
      }
      tempTasks.get(jobIndex).getEmployees().add(e); // adds employee to the task's employee list
      tempTasks.remove(jobIndex); // removes the task from tempTasks
    }
  }
}
