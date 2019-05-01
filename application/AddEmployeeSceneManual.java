/**
 * TODO: Akovi
 */
package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddEmployeeSceneManual extends Scene {

  Stage mainStage;

  @SuppressWarnings("unchecked")
  public AddEmployeeSceneManual(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Add a New Employee");

    root.autosize();
    root.setPadding(new Insets(10));

    Label name = new Label("Employee first name: ");
    Label id = new Label("ID: ");
    Label exceptionReport = new Label();
    Label scheduling = new Label();
    Label wigrow = new Label();

    exceptionReport.setText("Is an exception report needed? ");
    scheduling.setText("Are scheduling requirements incomplete? ");
    wigrow.setText("Is a WiGrow conversation needed? ");
    TextField nameText = new TextField();
    TextField idText = new TextField();

    // ChoiceBoxes for each of the three options
    ChoiceBox exceptionCB =
        new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    exceptionCB.getSelectionModel().selectFirst();
    exceptionCB.setStyle("-fx-font-size:15");

    ChoiceBox schedulingCB =
        new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    schedulingCB.getSelectionModel().selectFirst();
    schedulingCB.setStyle("-fx-font-size:15");

    ChoiceBox wigrowCB =
        new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    wigrowCB.getSelectionModel().selectFirst();
    wigrowCB.setStyle("-fx-font-size:15");

    Button cancel = new Button("Cancel");
    cancel.setOnAction(e -> {
      Main.switchToAddEmployeesAuto(this.mainStage);
    });
    Button add = new Button("Add");
    add.setOnAction(e -> {
      // Get values entered
      boolean exceptionChoice = getBooleanFromCB((String) exceptionCB.getValue());
      boolean schedulingChoice = getBooleanFromCB((String) schedulingCB.getValue());
      boolean wigrowChoice = getBooleanFromCB((String) wigrowCB.getValue());
      
      try {
      long idEntered = Long.parseLong(idText.getText());
      

      Employee newEmployee = new Employee(nameText.getText(), idEntered, exceptionChoice,
          schedulingChoice, wigrowChoice);

      Main.getAllEmployees().add(newEmployee);

      Main.switchToAddEmployeesAuto(this.mainStage);
      
      } catch(NumberFormatException f) {
        Alert incorrectFormat = new Alert(AlertType.WARNING, "Please enter a 9 digit integer ID");
        incorrectFormat.showAndWait();
      }
    });


    VBox col1 = new VBox(40);
    VBox col2 = new VBox(30);
    HBox line = new HBox(50);
    HBox line2 = new HBox(50);
    VBox all = new VBox(30);

    col1.setPadding(new Insets(30, 0, 0, 10));
    col2.setPadding(new Insets(22, 0, 0, 0));

    col1.getChildren().addAll(name, id, exceptionReport, scheduling, wigrow);
    col2.getChildren().addAll(nameText, idText, exceptionCB, schedulingCB, wigrowCB);

    line.getChildren().addAll(col1, col2);

    line2.getChildren().addAll(cancel, add);
    line2.setAlignment(Pos.CENTER_RIGHT);
    line2.setPadding(new Insets(0, 10, 0, -40));

    all.getChildren().addAll(line, line2);

    root.setCenter(all);

  }

  private boolean getBooleanFromCB(String choice) {
    if (choice.equalsIgnoreCase("Yes")) {
      return true;
    } else {
      return false;
    }
  }
}
