/**
 * Author: Adam Jackson
 * 
 * This scene is the main options scene where the program first opens up.
 */

package scenes;

import java.text.SimpleDateFormat;
import java.util.Date;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;

public class HomeScene extends Scene {

  Stage mainStage;
  
  // sets new date formats for
  // time stamp in titleLabel
  // in 24 hour format
  private final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss"); 
  private final SimpleDateFormat date = new SimpleDateFormat("EEE dd MMM yyyy"); // ex: Mon Apr 01 2019 14:02:13
  
  public HomeScene(Stage mainStage, BorderPane root, int width, int height) {
    super(root, width, height);

    this.mainStage = mainStage;
    this.mainStage.setTitle("Home");
    
    initTop(root);
    initCenter(root);
    initBottom(root);
  }
  
  /**
   * private method initializes top element of BorderPane
   * @param root is the BorderPane to modify
   */
  private void initTop(BorderPane root) {
    HBox topControl = new HBox(10);

    VBox topButtons = new VBox(10);
    topButtons.setPrefWidth(150);

    Button addEmployeesButton = new Button("Add Employees");
    addEmployeesButton.setMinWidth(topButtons.getPrefWidth());

    Button addTasksButton = new Button("Add Tasks");
    addTasksButton.setMinWidth(topButtons.getPrefWidth());

    Label titleLabel = new Label(); // title label for top of UI
    titleLabel.setStyle("-fx-font-size:20");
    titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
        + clock.format(new Date()));
    titleLabel.setPadding(new Insets(20, 0, 20, 150));

    //timeline dynamically updates the label every second for a real-time clock
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
      titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
          + clock.format(new Date()));
    }));
    timeline.setCycleCount(Animation.INDEFINITE); //sets the timeline to update until program stops
    timeline.play();

    topButtons.getChildren().addAll(addEmployeesButton, addTasksButton);

    topControl.getChildren().addAll(topButtons, titleLabel);

    root.setTop(topControl);
    root.setPadding(new Insets(20));
  }
  
  /**
   * private method initializes center element of BorderPane
   * @param root is the BorderPane to modify
   */
  private void initCenter(BorderPane root) {
    VBox centerControls = new VBox(10); // holds all elements for center part
    centerControls.setPadding(new Insets(0, 60, 50, 60)); // adds padding to shrink center control

    Label header = new Label("Employees Added to Unit:"); // header for center control
    header.setPadding(new Insets(20, 20, 10, 0)); // adds padding around header

    // creates a clickable list view
    // TODO: dynamically change employee list
    ListView<String> employeeList = new ListView<>();

    ObservableList<String> items =
        FXCollections.observableArrayList("[employee1Name]\t\t\t[employee1ID]",
            "[employee2Name]\t\t\t[employee2ID]",
            "[employee3Name]\t\t\t[employee3ID]");

    employeeList.setItems(items);
    
    employeeList.setOnMouseClicked(e -> { 
      Main.switchToOptions(this.mainStage); 
    });

    centerControls.getChildren().addAll(header, employeeList);

    root.setCenter(centerControls);
  }
  
  /**
   * private method initializes bottom element of BorderPane
   * @param root is the BorderPane to modify
   */
  private void initBottom(BorderPane root) {
    HBox bottomControls = new HBox(10);
    bottomControls.setPadding(new Insets(0, 0, 20, 0));
    bottomControls.setAlignment(Pos.CENTER);

    Button generateReport = new Button();
    generateReport.setWrapText(true);
    generateReport.setText("Generate Report");
    generateReport.setStyle("-fx-font-size:20");
    generateReport.setMinWidth(200);
    generateReport.setMinHeight(100);
    generateReport.setOnAction(e -> {
      Main.switchToResults(this.mainStage);
    });

    bottomControls.getChildren().addAll(generateReport);
    root.setBottom(bottomControls);
  }
}
