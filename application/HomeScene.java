/**
 * This is the main scene for the program. It is this scene that is first viewed when the program
 * first opens.
 */

package application;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeScene extends Scene {

  Stage mainStage;

  // sets new date formats for
  // time stamp in titleLabel
  // in 24 hour format
  private final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss");
  private final SimpleDateFormat date = new SimpleDateFormat("EEE dd MMM yyyy"); // ex: Mon Apr 01
                                                                                 // 2019 14:02:13
  /**
   * primary constructor for HomeScene
   * @param mainStage is the stage that all Scenes are displayed on
   * @param root is the LayoutManager being used
   * @param width is width of the window
   * @param height is the height of the window
   */
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
   * 
   * @param root is the BorderPane to modify
   */
  private void initTop(BorderPane root) {
    HBox topControl = new HBox(10); // holds all the elements for the top part of the Scene

    VBox topButtons = new VBox(10); // holds all the buttons in the top part of the Scene
    topButtons.setPrefWidth(150);

    // Changes the scene to allow user to add employees to the unit
    Button addEmployeesButton = new Button("Choose Employees");
    addEmployeesButton.setMinWidth(topButtons.getPrefWidth());
    addEmployeesButton.setOnAction(e -> {
      Main.switchToAddEmployeesAuto(this.mainStage);
    });

    // Changes the scene to allow users to add new, non-existing tasks
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

    // timeline dynamically updates the label every second for a real-time clock
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
      titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
          + clock.format(new Date()));
    }));
    timeline.setCycleCount(Animation.INDEFINITE); // sets the timeline to update until program stops
    timeline.play();

    topButtons.getChildren().addAll(addEmployeesButton, addTasksButton);

    topControl.getChildren().addAll(topButtons, titleLabel);

    root.setTop(topControl);
    root.setPadding(new Insets(20));
  }

  /**
   * private method initializes center element of BorderPane
   * 
   * @param root is the BorderPane to modify
   */
  private void initCenter(BorderPane root) {
    VBox centerControls = new VBox(10); // holds all elements for center part
    centerControls.setPadding(new Insets(0, 60, 10, 60)); // adds padding to shrink center control

    Label header = new Label("Employees Added to Unit:"); // header for center control
    header.setFont(new Font(20));
    header.setPadding(new Insets(20, 20, 0, 0)); // adds padding around header

    // creates a clickable list view
    ListView<Employee> employeeList = new ListView<>();

    ObservableList<Employee> items = FXCollections.observableArrayList();
    if (!Main.getEmployeesInUnit().isEmpty()) {
      items.addAll(Main.getEmployeesInUnit()); 
    }

    employeeList.setItems(items);
    employeeList.setOnMouseClicked(e -> {
      if (employeeList.getSelectionModel().getSelectedItem() instanceof Employee) {
        Main.switchToOptions(this.mainStage, employeeList.getSelectionModel().getSelectedItem());
      }
    });

    centerControls.getChildren().addAll(header, employeeList);

    root.setCenter(centerControls);
  }

  /**
   * private method initializes bottom element of BorderPane
   * 
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
      this.produceResult();
      Main.switchToResults(this.mainStage);
    });

    Button fileChooseButton = new Button("Load Data");
    fileChooseButton.setWrapText(true);
    fileChooseButton.setText("Load Data");
    fileChooseButton.setStyle("-fx-font-size:20");
    fileChooseButton.setMinWidth(200);
    fileChooseButton.setMinHeight(100);

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Input File");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));

    fileChooseButton.setOnAction(e -> {
      Main.getAllEmployees().clear();
      Main.getEmployeesInUnit().clear();
      Main.getAllTasks().clear();
      File selectedFile = fileChooser.showOpenDialog(mainStage);

      try {
        JSONFileParser.readData(selectedFile.getAbsolutePath());
        Main.setCurFileOpen(selectedFile.getAbsolutePath());
        Main.setCurFileOpenName(selectedFile.getName());
      } catch (Exception e1) {
        // won't happen
      }
    });

    HBox logoPosition = new HBox(10);
    logoPosition.setAlignment(Pos.BOTTOM_RIGHT);

    // If logo image is not found, a label will display instead
    try {
      ImageView imv = new ImageView();
      Image logo = new Image("Residence Hall Facilities_color-flush.png");
      imv.setImage(logo);
      imv.setFitWidth(400);
      imv.setPreserveRatio(true);
      imv.setSmooth(true);
      imv.setCache(true);
      logoPosition.getChildren().add(imv);
    } catch (IllegalArgumentException e) {
      Label error = new Label();
      error.setText("UW Housing Logo");
      logoPosition.getChildren().add(error);
    }


    bottomControls.setSpacing(40);
    bottomControls.getChildren().addAll(fileChooseButton, generateReport, logoPosition);
    root.setBottom(bottomControls);
  }

  private void produceResult() {
    for(Employee e : Main.getEmployeesInUnit()) {
      Random rng = new Random();
      while(true) {
        int jobIndex = rng.nextInt(Main.getAllTasks().size());
        
        if(Main.getAllTasks().get(jobIndex).getEmployees().isEmpty()) {
          Main.getAllTasks().get(jobIndex).getEmployees().add(e);
          break;
        }
      }
    }
//    while (!Main.getEmployeesInUnit().isEmpty()) {
//      for (int i = 0; i < Main.getAllTasks().size(); ++i) {
//        if (!Main.getEmployeesInUnit().isEmpty()) {
//          Random random = new Random();
//          int index = random.nextInt(Main.getEmployeesInUnit().size());
//          Task t = Main.getAllTasks().get(i);
//          t.assignTo(Main.getEmployeesInUnit().get(index));
//          Main.setTask(i, t);
//          Main.removeEmployeeFromTheUnit(Main.getEmployeesInUnit().get(index));
//        }
//      }
//    }
  }
}
