/**
 * TODO: Akovi
 */
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddEmployeeSceneAuto extends Scene {

  Stage mainStage;

  public AddEmployeeSceneAuto(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Employees working today");

    root.autosize();
    root.setPadding(new Insets(10));

    Label inputFile = new Label("File loaded: ");
    inputFile.setFont(new Font(15));

    Button addToUnit = new Button("Add to Unit");

    Button addNewEmployee = new Button("Add New Employee");
    addNewEmployee.setOnAction(e -> {
      Main.switchToAddEmployeesManual(this.mainStage);
    });

    Button back = new Button("Back to Home");
    back.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
    });

    ObservableList<Employee> employees = FXCollections.observableArrayList();
    employees.addAll(Main.getAllEmployees());
    ComboBox<Employee> comboBox = new ComboBox<Employee>(); // create a combo box object
    comboBox.setItems(employees);

    addToUnit.setOnAction(e -> {
      if (comboBox.getSelectionModel().getSelectedItem() instanceof Employee) {
        Main.getEmployeesInUnit().add(comboBox.getSelectionModel().getSelectedItem());
        Main.getAllEmployees().remove(comboBox.getSelectionModel().getSelectedItem());
        employees.clear();
        employees.addAll(Main.getAllEmployees());
        comboBox.setItems(employees);
      }
    });

    HBox line1 = new HBox(10);
    HBox line2 = new HBox(10);
    HBox line3 = new HBox(40);
    VBox spacing = new VBox(10);

    Label fileName = new Label(Main.getCurFileOpenName());
    fileName.setFont(new Font(15));

    line1.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
        new CornerRadii(4), new BorderWidths(2))));

    line1.setSpacing(10);

    line1.getChildren().addAll(inputFile, fileName);

    line2.getChildren().addAll(comboBox, addToUnit);

    line3.setAlignment(Pos.BOTTOM_RIGHT);
    line3.getChildren().addAll(addNewEmployee, back);


    spacing.getChildren().addAll(line1, line2, line3);
    spacing.setSpacing(30);
    root.setPadding(new Insets(15, 15, 15, 15));

    root.setTop(spacing);

  }

}
