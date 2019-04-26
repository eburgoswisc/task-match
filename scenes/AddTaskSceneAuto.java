/**
 * TODO: Ritik Goyal
 */

package scenes;

import java.io.File;
import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddTaskSceneAuto extends Scene {
  Stage mainStage;

  public AddTaskSceneAuto(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Today's Tasks");

    root.setPadding(new Insets(20));

    // Top Label
    Label addTaskTop = new Label("Add Tasks");
    root.setTop(addTaskTop);
    BorderPane.setAlignment(addTaskTop, Pos.TOP_CENTER);
    
    // Input field Center
    HBox inputs = new HBox(10);
    inputs.setPadding(new Insets(20, 20, 20, 20));

    FileChooser fileChooser = new FileChooser();
    Button fileChooseButton = new Button("Input File");

    fileChooser.setTitle("Input File");

    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
    fileChooseButton.setOnAction(e -> {
      File selectedFile = fileChooser.showOpenDialog(mainStage);
    });

    Label fileName = new Label("input.json"); // TODO change to actual filename

    // if (selectedFile != null) {
    // mainStage.display(selectedFile);
    // }

    inputs.getChildren().addAll(fileChooseButton, fileName);
    inputs.setPadding(new Insets(200, 300, 100, 300));
    root.setCenter(inputs);

    // Bottom nav bar and buttons
    HBox navBar = new HBox(10);
    Button addNewTask = new Button("Add New Task");
    addNewTask.setOnAction(e -> {
      Main.switchToAddTasksManual(this.mainStage);
    });
    Button back = new Button("Back");
    back.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
    });
    navBar.getChildren().addAll(addNewTask, back);
    root.setBottom(navBar);
    // mainStage.show();
  }
}
