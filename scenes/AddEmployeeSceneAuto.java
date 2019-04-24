/**
 * TODO: Akovi
 */
package scenes;

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
import javafx.stage.Stage;

public class AddEmployeeSceneAuto extends Scene {
  
  Stage mainStage;
  
  public AddEmployeeSceneAuto(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    
    
	root.autosize();
    root.setPadding(new Insets(10));
    
    Button inputFile = new Button("Input file");
    
    Button addToUnit = new Button("Add to Unit");
    
    Button addNewEmployee = new Button("Add New Employee");
    addNewEmployee.setOnAction(e -> {
      Main.switchToAddEmployeesManual(this.mainStage);
      });
    
    Button back = new Button("Back");
    back.setOnAction(e -> {
      Main.switchToHome(this.mainStage);
      });
    
    ObservableList<String> employees = FXCollections.observableArrayList(
			"employee1", "employee2", "employee3", "employee4", "employee5");
	ComboBox comboBox = new ComboBox(employees); //create a combo box object
	Label l = new Label("                                                  "
			+ "                                 "
			+ "        ");
    
    HBox line1 = new HBox(10);
    HBox line2 = new HBox(10);
    HBox line3 = new HBox(10);
    HBox empty = new HBox(50);
    
    
    line1.getChildren().addAll(inputFile, new Label("data.json"));
    
    line2.getChildren().addAll(comboBox, addToUnit);
    
    line3.getChildren().addAll(addNewEmployee, empty, l, back);
    empty.getChildren().addAll(l);
    
    
    line1.setPrefSize(500, 250);
    root.setTop(line1);
    root.setCenter(line2);
    root.setBottom(line3);
    
    
    
    
  }

}
