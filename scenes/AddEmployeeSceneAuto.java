/**
 * TODO: Akovi
 */
package scenes;

import java.io.File;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import taskMatch.Employee;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddEmployeeSceneAuto extends Scene {

  Stage mainStage;

  public AddEmployeeSceneAuto(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Employees working today");
    
    root.autosize();
    root.setPadding(new Insets(10));

    Label inputFile = new Label("File loaded: ");
//
//    FileChooser fileChooser = new FileChooser();
//
//    fileChooser.setTitle("Input File");
//
//    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
//    inputFile.setOnAction(e -> {
//      File selectedFile = fileChooser.showOpenDialog(mainStage);
//    });

    Button addToUnit = new Button("Add to Unit");

    Button addNewEmployee = new Button("Add New Employee");
    addNewEmployee.setOnAction(e -> {
      Main.switchToAddEmployeesManual(this.mainStage);
    });

    Button back = new Button("Back");
    back.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
    });

    ObservableList<Employee> employees = FXCollections.observableArrayList();
    employees.addAll(Main.getAllEmployees());
    ComboBox<Employee> comboBox = new ComboBox<Employee>(); // create a combo box object
    comboBox.setItems(employees);
    
    addToUnit.setOnAction(e -> {
      Main.getEmployeesInUnit().add(comboBox.getSelectionModel().getSelectedItem());
      Main.getAllEmployees().remove(comboBox.getSelectionModel().getSelectedItem());
      employees.clear();
      employees.addAll(Main.getAllEmployees());
      comboBox.setItems(employees);
    });
    
    HBox line1 = new HBox(10);
    HBox line2 = new HBox(10);
    HBox line3 = new HBox(100);

    line1.setPadding(new Insets(0, 0, 30, 0));
    
    line1.getChildren().addAll(inputFile, new Label("data.json"));

    line2.getChildren().addAll(comboBox, addToUnit);

    line3.getChildren().addAll(addNewEmployee, back);

    root.setTop(line1);
    root.setCenter(line2);
    root.setBottom(line3);



  }

}
