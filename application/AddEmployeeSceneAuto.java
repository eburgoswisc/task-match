
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

/**
 * This class allows the user to add employees to the unit after a JSON file has been loaded.
 * 
 * @author aTeam7
 *
 */
public class AddEmployeeSceneAuto extends Scene {

  Stage mainStage;

  /**
   * Constructor to initialize the scene correctly
   * 
   * @param mainStage the root stage being used
   * @param root the BorderPane layout of the root
   * @param width the desired window width
   * @param height the desired window height
   */
  public AddEmployeeSceneAuto(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Employees working today");

    // Three HBoxes are displayed within one VBox for this scene
    HBox topLine = new HBox(10);
    HBox selectEmployeesLine = new HBox(10);
    HBox optionsButtonsLine = new HBox(40);
    VBox verticalSpacing = new VBox(10);

    root.setPadding(new Insets(20));

    // Top label to show which file is loaded
    Label inputFile = new Label("File loaded: ");
    inputFile.setFont(new Font(15));
    Label fileName = new Label(Main.getCurFileOpenName());
    fileName.setFont(new Font(15));

    // Middle button to add employee that has been chosen in ComboBox to unit
    Button addToUnit = new Button("Add to Unit");

    // Bottom button to create a new employee
    Button addNewEmployee = new Button("Add New Employee");

    // Switch to new employee scene when button clicked
    addNewEmployee.setOnAction(e -> {
      Main.switchToAddEmployeesManual(this.mainStage);
    });

    // Bottom button that returns to Home scene when clicked
    Button back = new Button("Back to Home");
    back.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
    });

    // ComboBox showing an ObservableList of employees that were loaded from JSON file
    ObservableList<Employee> employees = FXCollections.observableArrayList();
    employees.addAll(Main.getAllEmployees());
    ComboBox<Employee> comboBox = new ComboBox<Employee>();
    comboBox.setItems(employees);

    // Updates the current employees in unit list when button clicked
    addToUnit.setOnAction(e -> {
      if (comboBox.getSelectionModel().getSelectedItem() instanceof Employee) {
        Main.getEmployeesInUnit().add(comboBox.getSelectionModel().getSelectedItem());
        Main.getAllEmployees().remove(comboBox.getSelectionModel().getSelectedItem());
        employees.clear();
        employees.addAll(Main.getAllEmployees());
        comboBox.setItems(employees);
      }
    });

    // Adds elements to the three HBoxes
    
    // Sets a red border around the current file label
    topLine.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
        new CornerRadii(4), new BorderWidths(2))));
    
    topLine.setSpacing(10);
    topLine.getChildren().addAll(inputFile, fileName);

    selectEmployeesLine.getChildren().addAll(comboBox, addToUnit);

    optionsButtonsLine.setAlignment(Pos.BOTTOM_RIGHT);
    optionsButtonsLine.getChildren().addAll(addNewEmployee, back);

    // Adds the three HBoxes to the one VBox
    verticalSpacing.getChildren().addAll(topLine, selectEmployeesLine, optionsButtonsLine);
    verticalSpacing.setSpacing(30);
    root.setPadding(new Insets(15, 15, 15, 15));

    // Sets the VBox to the top of the root BorderPane
    root.setTop(verticalSpacing);

  }

}
