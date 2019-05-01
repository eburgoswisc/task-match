/**
 * Author: Emanuel Burgos
 */
package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Scene that shows the report of tasks assign to employees. Generates a file if desired.
 * 
 * @author emanuelburgos
 *
 */
public class ResultsScene extends Scene {
  // Main Stage
  Stage mainStage;

  // Timestamp objects
  private final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss");
  private final SimpleDateFormat date = new SimpleDateFormat("EEE dd MMM yyyy"); // ex: Mon Apr 01
                                                                                 // 2019 14:02:13
  private final SimpleDateFormat fileDate = new SimpleDateFormat("YYYY-MM-dd--HH-mm-ss");

  /**
   * Constructor
   * 
   * @param mainStage - Stage object
   * @param root      - Borderpane object
   * @param width     - int
   * @param height    - int
   */
  public ResultsScene(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    // Set property of scene
    this.mainStage.setTitle("Results");

    // Initiate Boxes
    initTop(root);
    initCenter(root);
    initBottom(root);

  }

  /**
   * Initiate the top Box of the results page.
   * 
   * @param mainStage - Main Stage object
   */
  private void initBottom(BorderPane root) {
    // Make bottom box for buttons
    HBox bottomButtons = new HBox();
    bottomButtons.setPrefWidth(150);
    bottomButtons.setAlignment(Pos.CENTER);
    bottomButtons.setPadding(new Insets(0, 60, 50, 60));
    bottomButtons.setSpacing(50);

    // Get buttons
    Button backBut = new Button("Back");
    backBut.setOnAction(e -> {
      Main.getAllEmployees().addAll(Main.getEmployeesInUnit());
      Main.getEmployeesInUnit().clear();
      Main.switchToHome(this.mainStage);
    });

    // Report button
    Button downReportBut = new Button("Download Report");
    downReportBut.setOnAction(e -> {
      downloadReport();
    });
    // Add to HBox
    bottomButtons.getChildren().addAll(downReportBut, backBut);

    // Add to root
    root.setBottom(bottomButtons);
    root.setPadding(new Insets(20));

  }

  /**
   * Set Center panels
   * 
   * @param mainStage
   */
  private void initCenter(BorderPane root) {
    // Make center box for reporting tasks
    HBox reportBox = new HBox();
    reportBox.setPadding(new Insets(0, 60, 50, 60));



    List<String> list = new ArrayList<String>();
    for (int i = 0; i < Main.getAllTasks().size(); ++i) {
      String s = "[" + Main.getAllTasks().get(i).getDescription() + "]\n\t\t";
      for (int j = 0; j < Main.getAllTasks().get(i).getEmployees().size(); ++j) {
        s = s + "[" + Main.getAllTasks().get(i).getEmployees().get(j).getName() + "]";

      }
      list.add(s);

    }

    // ListView Object
    ListView<String> reportList = new ListView<>();

    // Task Report list
    ObservableList<String> items = FXCollections.observableArrayList(list);
    // Add to reportList
    reportList.setItems(items);

    // Add stuff to box
    reportBox.getChildren().addAll(reportList);

    // Add to root
    root.setCenter(reportBox);
  }

  private void initTop(BorderPane root) {
    // Make HBox
    HBox topBox = new HBox();
    // Set Label
    Label titleLabel = new Label(); // title label for top of UI
    titleLabel.setStyle("-fx-font-size:20");
    titleLabel.setText(
        "Fair Job Planning System " + date.format(new Date()) + " " + clock.format(new Date()));
    titleLabel.setPadding(new Insets(20, 0, 20, 150));

    // Add stuff to topBox
    topBox.getChildren().addAll(titleLabel);
    // Add to BorderPane root
    root.setTop(topBox);
  }

  /**
   * Method for writing a report file in CSV format.
   * 
   * @throws JSONException
   * 
   */
  private void downloadReport() {
    // Get Employees and Tasks
    ArrayList<Employee> inUnit = Main.getEmployeesInUnit();
    ArrayList<Task> allTasks = Main.getAllTasks();

    // Create BufferedWriter Object
    String fileName = fileDate.format(new Date()) + "_report.csv";
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(fileName));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Filename already exists.");
    }

    try {
      // Write Header
      writer.write("Task ID,Task Description,Employee ID, Employee Name\n");
      // Look through employee and their task
      for (int i = 0; i < inUnit.size(); ++i) {
        Employee current = inUnit.get(i);
        for (int j = 0; j < allTasks.size(); ++j) {
          Task currentTask = allTasks.get(j);
          // If found, write to file
          if (currentTask.getEmployees().contains(current)) {
            writer.write(currentTask.getID() + "," + currentTask.getDescription() + ","
                + current.getId() + "," + current.getName() + "\n");
          }
        }
      }
      writer.close();
    } catch (IOException e) {
      // Do nothing

    }

    Alert downloadSuccess =
        new Alert(AlertType.NONE, "Successfully Downloaded Report! It will be named " + fileName
            + " and will be located in your program's local directory.");
    downloadSuccess.setTitle("Download File");
    downloadSuccess.getButtonTypes().add(ButtonType.OK);
    downloadSuccess.showAndWait();
  }

}
