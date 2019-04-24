/**
 * Scene class for the options window. This window will be activated when an employee is clicked in
 * the HomeScreen employeeList ListView. It displays optional flags for each employee.
 * 
 * @author Ryan Wenzel
 */

package scenes;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;

public class OptionsScene extends Scene {

  Stage mainStage;

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
  public OptionsScene(Stage mainStage, BorderPane root, double width, double height) {

    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Employee Options");
    this.mainStage.setWidth(width);
    this.mainStage.setHeight(height);
    this.mainStage.setResizable(false);

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
    HBox topControl = new HBox(10);
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
    employeeInfoLabel.setText("[employeeName]       [employeeID]");
    employeeInfoLabel.setStyle("-fx-font-size:15");
    employeeInfoLabel.setPadding(new Insets (-10, 0, 0, 0));
    // TODO display the employeeName and employeeID for selected employee

    // Heading label instantiation and settings
    Label headingLabel = new Label();
    headingLabel.setText("Employee options: ");
    headingLabel.setStyle("-fx-font-size:20");
    headingLabel.setPadding(new Insets (-20, 0, 5, 0));

    topLabel.getChildren().addAll(titleLabel, employeeInfoLabel, headingLabel);
    topControl.getChildren().addAll(topLabel);

    root.setTop(topControl);
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
    ChoiceBox cb1 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb1.getSelectionModel().selectFirst();
    cb1.setStyle("-fx-font-size:15");

    ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb2.getSelectionModel().selectFirst();
    cb2.setStyle("-fx-font-size:15");

    ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb3.getSelectionModel().selectFirst();
    cb3.setStyle("-fx-font-size:15");

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

    // TODO Make button functional

    // Set elements in the HBox and set the BorderPane bottom panel
    bottomControls.getChildren().addAll(backButton);
    root.setBottom(bottomControls);
  }
}
