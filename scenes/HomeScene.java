/**
 * TODO: Adam Jackson
 */

package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;


public class HomeScene extends Scene {
  
  Stage mainStage;
  
  public HomeScene(Stage mainStage, BorderPane root, int width, int height) {
    super(root,width,height);
    
    this.mainStage = mainStage;
    this.mainStage.setResizable(false);
    this.mainStage.setTitle("Home");

    //top part of BorderPane
    //TODO: dynamically change the time/date
    HBox topControl = new HBox(10);
    
    VBox topButtons = new VBox(10);
    topButtons.setPrefWidth(150);

    Button addEmp = new Button("Add Employees");
    addEmp.setMinWidth(topButtons.getPrefWidth());
    
    Button addTasks = new Button("Add Tasks");    
    addTasks.setMinWidth(topButtons.getPrefWidth());
    
    Label titleLabel = new Label(); //title label for top of UI
    titleLabel.setStyle("-fx-font-size:20");
    titleLabel.setText("Fair Job Planning System");
    titleLabel.setPadding(new Insets(20, 0, 20, 150));
    
    topButtons.getChildren().addAll(addEmp, addTasks);
    
    topControl.getChildren().addAll(topButtons, titleLabel);
    
    root.setTop(topControl);
    root.setPadding(new Insets(20));
    
    //center
    VBox centerControls = new VBox(10); //holds all elements for center part
    centerControls.setPadding(new Insets(0, 60, 50, 60)); //adds padding to shrink center control
    
    Label header = new Label("Employees Added to Unit:"); //header for center control
    header.setPadding(new Insets(20,20,10,0)); //adds padding around header
    
    
    //creates a clickable list view
    //TODO: dynamically change employee list
    ListView<String> employeeList = new ListView<>();
    
    ObservableList<String> items = FXCollections.observableArrayList(
        "[employee1Name]\t\t\t[employee1ID]",
        "[employee2Name]\t\t\t[employee2ID]",
        "[employee3Name]\t\t\t[employee3ID]");
    
    employeeList.setItems(items);
    
    centerControls.getChildren().addAll(header, employeeList);
    
    root.setCenter(centerControls);
    
    //bottom
    HBox bottomControls = new HBox(10);
    bottomControls.setPadding(new Insets(0, 0, 20, 0));
    bottomControls.setAlignment(Pos.CENTER);
    
    Button generateReport = new Button();
    generateReport.setWrapText(true);
    generateReport.setText("Generate Report");
    generateReport.setStyle("-fx-font-size:20");
    generateReport.setMinWidth(200);
    generateReport.setMinHeight(100);
    
    bottomControls.getChildren().addAll(generateReport);
    root.setBottom(bottomControls);

  }  
}
