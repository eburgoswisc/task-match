/**
 * Scene class for the options window. This window will be activated when an employee is clicked in
 * the HomeScreen employeeList ListView. It displays optional flags for each employee.
 * 
 * @author Ryan Wenzel
 */

package scenes;

import java.text.SimpleDateFormat;
import java.util.Date;
import application.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import taskMatch.Employee;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

public class OptionsScene extends Scene {

  Stage mainStage;
  Employee curEmployee;
  
  private ChoiceBox<String> cb1;
  private ChoiceBox<String> cb2;
  private ChoiceBox<String> cb3;
  
  // sets new date formats for time stamp in titleLabel in 24 hour format
  private final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss");
  private final SimpleDateFormat date = new SimpleDateFormat("EEE dd MMM yyyy"); // ex: Mon Apr 01
                                                                                 // 2019 14:02:13

  /**
   * Constructor that creates scene with input width and height.
   * 
   * @param mainStage - The primary window being used
   * @param root - the stage's BorderPane layout
   * @param width - The desired width of the scene window
   * @param height - The desired height of the scene window
   */
  public OptionsScene(Stage mainStage, BorderPane root, double width, double height, Employee curEmployee) {

    super(root, width, height);
    this.curEmployee = curEmployee;
    this.mainStage = mainStage;
    this.mainStage.setTitle("Employee Options");

    // BorderPane region methods
    initTop(root);
    initCenter(root);
    initBottom(root);
  }

  /**
   * Builds the top pane of the BorderPane layout. Displays the program title, time, employee label,
   * and options heading.
   * 
   * @param root - The BorderPane object
   */
  private void initTop(BorderPane root) {

    // One HBox holds the label elements in the VBox
    
    HBox specificEmployee = new HBox(10);
    HBox mainHBox = new HBox(10);
    VBox topLabel = new VBox(40);

    // Program title label instantiation and settings
    Label titleLabel = new Label();
    titleLabel.setStyle("-fx-font-size:20");
    titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
        + clock.format(new Date()));
    titleLabel.setPadding(new Insets(0, 0, -10, 20));

    // timeline dynamically updates the label every second for a real-time clock
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
      titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
          + clock.format(new Date()));
    }));
    timeline.setCycleCount(Animation.INDEFINITE); // sets the timeline to update until program stops
    timeline.play();

    Label employeeInfoLabel = new Label();
    employeeInfoLabel.setText(curEmployee.toString());
    employeeInfoLabel.setFont(new Font(15));
    employeeInfoLabel.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));

    Button remove = new Button();
    remove.setText("Remove from unit");
    remove.setOnAction(e -> {
      
      Main.getEmployeesInUnit().remove(curEmployee);
      Main.getAllEmployees().add(curEmployee);
      
      Main.switchToHome(this.mainStage);
      });
    
    // Heading label instantiation and settings
    Label headingLabel = new Label();
    headingLabel.setText("Employee options: ");
    headingLabel.setStyle("-fx-font-size:20");
    headingLabel.setPadding(new Insets (-20, 0, 5, 0));

    specificEmployee.getChildren().addAll(employeeInfoLabel, remove);
    specificEmployee.setSpacing(20);
    topLabel.getChildren().addAll(titleLabel, specificEmployee, headingLabel);
    mainHBox.getChildren().addAll(topLabel);

    root.setTop(mainHBox);
    root.setPadding(new Insets(20));
  }

  /**
   * Builds the center pane of the BorderPane layout. Displays the option labels and associated
   * ChoiceBoxes.
   * 
   * @param root - The BorderPane object
   */
  private void initCenter(BorderPane root) {

    // An HBox displays two VBox columns, one with option labels, and one with ChoiceBoxes
    HBox midObjects = new HBox(50);
    VBox midChoices = new VBox(30);
    VBox midCBs = new VBox(20);

    // Font for labels
    Font labelFont = new Font(15);

    // Label instantiation and settings
    Label exceptionReport = new Label();
    Label scheduling = new Label();
    Label wigrow = new Label();

    exceptionReport.setText("      Is an exception report needed? ");
    scheduling.setText("      Are scheduling requirements incomplete? ");
    wigrow.setText("      Is a WiGrow conversation needed? ");

    exceptionReport.setFont(labelFont);
    scheduling.setFont(labelFont);
    wigrow.setFont(labelFont);

    
    // ChoiceBoxes for each of the three options
    cb1 = new ChoiceBox<String>();
    cb1.getItems().addAll("No","Yes");
    cb1.setStyle("-fx-font-size:15");
    
    if (curEmployee.isExceptionReport()) {
      cb1.getSelectionModel().selectLast();
    }
    else {
      cb1.getSelectionModel().selectFirst();
    }
    
    cb2 = new ChoiceBox<String>();
    cb2.getItems().addAll("No","Yes");
    cb2.setStyle("-fx-font-size:15");
    
    if (curEmployee.isScheduling()) {
      cb2.getSelectionModel().selectLast();
    }
    else {
      cb2.getSelectionModel().selectFirst();
    }
    
    cb3 = new ChoiceBox<String>();
    cb3.getItems().addAll("No", "Yes");
    cb3.setStyle("-fx-font-size:15");
    
    if (curEmployee.isWiGrow()) {
      cb3.getSelectionModel().selectLast();
    }
    else {
      cb3.getSelectionModel().selectFirst();
    }
    
    if (cb1.getSelectionModel().getSelectedIndex() == 0) {
      curEmployee.setExceptionReport(false);
    }
    if (cb1.getSelectionModel().getSelectedIndex() == 1) {
      curEmployee.setExceptionReport(true);
    }
    if (cb2.getSelectionModel().getSelectedIndex() == 0) {
      curEmployee.setScheduling(false);
    }
    if (cb2.getSelectionModel().getSelectedIndex() == 1) {
      curEmployee.setScheduling(true);
    }
    if (cb1.getSelectionModel().getSelectedIndex() == 0) {
      curEmployee.setWiGrow(false);
    }
    if (cb1.getSelectionModel().getSelectedIndex() == 1) {
      curEmployee.setWiGrow(true);
    }

    // Set elements in the HBox and two VBoxes, and set the BorderPane center panel with HBox
    midObjects.getChildren().addAll(midChoices, midCBs); // HBox
    midChoices.getChildren().addAll(exceptionReport, scheduling, wigrow); // VBox 1
    midCBs.getChildren().addAll(cb1, cb2, cb3);
    root.setCenter(midObjects);

  }

  /**
   * Builds the bottom pane of the BorderPane layout. Displays the back button.
   * 
   * @param root - The BorderPane object
   */
  private void initBottom(BorderPane root) {

    // One HBox holds the back button
    HBox bottomControls = new HBox(10);

    // Set the button in the center of the screen
    bottomControls.setAlignment(Pos.CENTER);

    // Button instantiation and settings
    Button backButton = new Button();
    backButton.setWrapText(true);
    backButton.setText("Back");
    backButton.setStyle("-fx-font-size:20");
    backButton.setMinWidth(100);
    backButton.setMinHeight(50);
    
    backButton.setOnAction(e -> {
      
      if (cb1.getSelectionModel().getSelectedIndex() == 0) {
        curEmployee.setExceptionReport(false);
      }
      if (cb1.getSelectionModel().getSelectedIndex() == 1) {
        curEmployee.setExceptionReport(true);
      }
      if (cb2.getSelectionModel().getSelectedIndex() == 0) {
        curEmployee.setScheduling(false);
      }
      if (cb2.getSelectionModel().getSelectedIndex() == 1) {
        curEmployee.setScheduling(true);
      }
      if (cb1.getSelectionModel().getSelectedIndex() == 0) {
        curEmployee.setWiGrow(false);
      }
      if (cb1.getSelectionModel().getSelectedIndex() == 1) {
        curEmployee.setWiGrow(true);
      }

      Main.switchToHome(this.mainStage);
      });

    // Set elements in the HBox and set the BorderPane bottom panel
    bottomControls.getChildren().addAll(backButton);
    root.setBottom(bottomControls);
  }
}
