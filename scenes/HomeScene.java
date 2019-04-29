/**
 * Author: Adam Jackson 
 * 
 * This scene is the main options scene where the program first opens up.
 */

package scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import taskMatch.Employee;
import taskMatch.JSONFileParser;
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

    Button addEmployeesButton = new Button("Choose Employees");
    addEmployeesButton.setMinWidth(topButtons.getPrefWidth());
    addEmployeesButton.setOnAction(e -> {
      Main.switchToAddEmployeesAuto(this.mainStage);
    });

    Button addTasksButton = new Button("Add New Task");
    addTasksButton.setMinWidth(topButtons.getPrefWidth());
    addTasksButton.setOnAction(e -> {
      Main.switchToAddTasksManual(this.mainStage);
    });
    
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
    centerControls.setPadding(new Insets(0, 60, 10, 60)); // adds padding to shrink center control

    Label header = new Label("Employees Added to Unit:"); // header for center control
    header.setFont(new Font(20));
    header.setPadding(new Insets(20, 20, 0, 0)); // adds padding around header

    // creates a clickable list view
    // TODO: dynamically change employee list
    ListView<Employee> employeeList = new ListView<>();

    ObservableList<Employee> items = FXCollections.observableArrayList();
    if(!Main.getEmployeesInUnit().isEmpty()) {
      items.addAll(Main.getEmployeesInUnit()); //TODO: uncomment when functionality is finished
    }
    
    employeeList.setItems(items);
    employeeList.setOnMouseClicked(e -> {
      if(employeeList.getSelectionModel().getSelectedItem() instanceof Employee) {
        Main.switchToOptions(this.mainStage, employeeList.getSelectionModel().getSelectedItem()); 
      }
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
    bottomControls.setAlignment(Pos.BOTTOM_CENTER);

    Button generateReport = new Button();
    generateReport.setWrapText(true);
    generateReport.setText("Generate Report");
    generateReport.setStyle("-fx-font-size:20");
    generateReport.setMinWidth(200);
    generateReport.setMinHeight(100);
    generateReport.setOnAction(e -> {
      Main.switchToResults(this.mainStage);
      JSONFileParser.writeData(Main.getCurFileOpen());
    });
    
    FileChooser fileChooser = new FileChooser();
    Button fileChooseButton = new Button("Load Data");
    generateReport.setWrapText(true);
    fileChooseButton.setText("Load Data");
    fileChooseButton.setStyle("-fx-font-size:20");
    fileChooseButton.setMinWidth(200);
    fileChooseButton.setMinHeight(100);
    fileChooser.setTitle("Input File");

    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
    fileChooseButton.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(mainStage);
      
      try {
        JSONFileParser.readData(selectedFile.getAbsolutePath());
      } catch (FileNotFoundException e1) {
        //won't happen
      }
      
      Main.setCurFileOpen(selectedFile.getAbsolutePath());
      Main.setCurFileOpenName(selectedFile.getName());
    });

    HBox logoPosition = new HBox(10);
    logoPosition.setAlignment(Pos.BOTTOM_RIGHT);
    
    // TODO: Fix so logo is at bottom left of window
    ImageView imv = new ImageView();
    Image logo = new Image("Residence Hall Facilities_color-flush.png");
    imv.setImage(logo);
    imv.setFitWidth(400);
    imv.setPreserveRatio(true);
    imv.setSmooth(true);
    imv.setCache(true);
    
    bottomControls.setSpacing(40);
    logoPosition.getChildren().add(imv);
    bottomControls.getChildren().addAll(fileChooseButton, generateReport, logoPosition);
    root.setBottom(bottomControls);
  }
}
