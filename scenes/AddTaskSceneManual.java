/**
 * TODO: Ritik
 */
package scenes;

import application.Main;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddTaskSceneManual extends Scene {

  Stage mainStage;

  public AddTaskSceneManual(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Add a New Task");

    root.autosize();
    root.setPadding(new Insets(20));

    Label taskLabel = new Label("Task");
    TextField tasksInput = new TextField();

    Label favLabel = new Label("Favorable");

    ChoiceBox favInput =
        new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    favInput.getSelectionModel().selectFirst();
    favInput.setStyle("-fx-font-size:15");

    VBox labels = new VBox(20); // Label column
    labels.getChildren().addAll(taskLabel, favLabel);

    VBox textInputs = new VBox(10); // Textfield column
    textInputs.getChildren().addAll(tasksInput, favInput);

    labels.setPadding(new Insets(5, 0, 0, 10));
    textInputs.setPadding(new Insets(0, 0, 0, 0));

    HBox inputs = new HBox(10); // All central inputs
    inputs.setAlignment(Pos.BASELINE_CENTER);
    inputs.setPadding(new Insets(150, 0, 0, 0));
    inputs.getChildren().addAll(labels, textInputs);

    Button cancel = new Button("Cancel");
    cancel.setOnAction(e -> {
      Main.switchToAddTasksAuto(this.mainStage);
    });

    Button add = new Button("Add");
    add.setOnAction(e -> {
      Main.switchToAddTasksAuto(this.mainStage);
    });

    HBox navButtons = new HBox(30);
    navButtons.setAlignment(Pos.CENTER_RIGHT);
    navButtons.setPadding(new Insets(20, 20, 100, 20));
    navButtons.getChildren().addAll(cancel, add);

    root.setCenter(inputs);
    root.setBottom(navButtons);
  }

}
