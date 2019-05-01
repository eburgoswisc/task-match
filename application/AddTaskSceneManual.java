/**
 * TODO: Ritik
 */
package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

<<<<<<< HEAD
	Stage mainStage;

	public AddTaskSceneManual(Stage mainStage, BorderPane root, double width, double height) {
		super(root, width, height);
		this.mainStage = mainStage;
		this.mainStage.setTitle("Add a New Task");

		//root.autosize();
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
		inputs.setPadding(new Insets(20, 0, 0, 0));
		inputs.getChildren().addAll(labels, textInputs);

		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			Main.switchToHome(this.mainStage);
		});

		Button add = new Button("Add");
		add.setOnAction(e -> {
			boolean fav = getBoolean((String) favInput.getValue());
			Task task = new Task(tasksInput.getText(), fav);
			Main.addTask(task);
			Main.switchToHome(this.mainStage);
		});

		HBox navButtons = new HBox(30);
		navButtons.setAlignment(Pos.CENTER_RIGHT);
		navButtons.setPadding(new Insets(20, 20, 100, 20));
		navButtons.getChildren().addAll(cancel, add);

		root.setTop(inputs);
		root.setCenter(navButtons);
	}

	private boolean getBoolean(String choice) {
		if (choice.equalsIgnoreCase("Yes")) {
			return true;
		} else {
			return false;
		}
	}
=======
  Stage mainStage;

  public AddTaskSceneManual(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Add a New Task");

    // root.autosize();
    root.setPadding(new Insets(20));

    Label taskLabel = new Label("Task");
    TextField tasksInput = new TextField();

    Label favLabel = new Label("Favorable");
    Label idLabel = new Label("Task ID");
    TextField idInput = new TextField();
    
    ChoiceBox<String> favInput =
        new ChoiceBox<String>(FXCollections.observableArrayList("No", "Yes"));
    favInput.getSelectionModel().selectFirst();
    favInput.setStyle("-fx-font-size:15");

    VBox labels = new VBox(20); // Label column
    labels.getChildren().addAll(taskLabel, favLabel, idLabel);

    VBox textInputs = new VBox(10); // Textfield column
    textInputs.getChildren().addAll(tasksInput, favInput, idInput);

    labels.setPadding(new Insets(5, 0, 0, 10));
    textInputs.setPadding(new Insets(0, 0, 0, 0));

    HBox inputs = new HBox(10); // All central inputs
    inputs.setAlignment(Pos.BASELINE_CENTER);
    inputs.setPadding(new Insets(20, 0, 0, 0));
    inputs.getChildren().addAll(labels, textInputs);

    Button cancel = new Button("Cancel");

    cancel.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
    });

    Button add = new Button("Add");
    add.setOnAction(e -> {
      try {
        Task toAdd = new Task(tasksInput.getText(), false);
        toAdd.setID(Integer.parseInt(idInput.getText()));
        if (favInput.getSelectionModel().getSelectedItem().equals("Yes")) {
          toAdd.setFavorable(true);
        }
  
        Main.getAllTasks().add(toAdd);
      }
      catch(Exception ex) {
        
      }
      Main.switchToHome(this.mainStage);
    });

    HBox navButtons = new HBox(30);
    navButtons.setAlignment(Pos.CENTER_RIGHT);
    navButtons.setPadding(new Insets(20, 20, 100, 20));
    navButtons.getChildren().addAll(cancel, add);

    root.setTop(inputs);
    root.setCenter(navButtons);
  }

>>>>>>> deb1ce38ef60b3be7a6cf24d85a7cb18c8ec7dbd
}
