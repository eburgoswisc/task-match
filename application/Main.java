package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
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
			//JSONFileParser.writeData(curFileOpen);
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
			//currentScene = new OptionsScene(primaryStage, root, 630, 380, curEmployee);
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
			currentScene = new AddEmployeeSceneManual(primaryStage, root, 550, 330);
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

	//  public static void switchToAddTasksAuto(Stage primaryStage) {
	//    try {
	//      BorderPane root = new BorderPane();
	//      currentScene = new AddTaskSceneAuto(primaryStage, root, 900, 500);
	//      primaryStage.setScene(currentScene);
	//      primaryStage.show();
	//    } catch (Exception e) {
	//      e.printStackTrace();
	//    }
	//  }

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
		allTasks.set(i,t);
	}

	public static void addTask(Task task) {
		allTasks.add(task);
	}
	
	public static void removeEmployeeFromTheUnit(Employee e) {
		employeesInUnit.remove(e);
	}
}
