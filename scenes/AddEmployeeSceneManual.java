/**
 * TODO: Akovi
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

public class AddEmployeeSceneManual extends Scene {

  Stage mainStage;
  
  public AddEmployeeSceneManual(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    this.mainStage.setTitle("Add a New Employee");
    
    root.autosize();
    root.setPadding(new Insets(10));
    
    Label name = new Label("Employee first name: ");
    Label exceptionReport = new Label();
    Label scheduling = new Label();
    Label wigrow = new Label();

    exceptionReport.setText("Is an exception report needed? ");
    scheduling.setText("Are scheduling requirements incomplete? ");
    wigrow.setText("Is a WiGrow conversation needed? ");
    TextField text1 = new TextField();
    
    // ChoiceBoxes for each of the three options
    ChoiceBox cb1 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb1.getSelectionModel().selectFirst();
    cb1.setStyle("-fx-font-size:15");

    ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb2.getSelectionModel().selectFirst();
    cb2.setStyle("-fx-font-size:15");

    ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
    cb3.getSelectionModel().selectFirst();
    cb3.setStyle("-fx-font-size:15");
    
    Button cancel = new Button("Cancel");
    cancel.setOnAction(e ->{
      Main.switchToAddTasksAuto(this.mainStage);
    });
    Button add = new Button("Add");
    add.setOnAction(e ->{
      Main.switchToAddTasksAuto(this.mainStage);
    });
    
    
    VBox col1= new VBox(30);
    VBox col2= new VBox(30);
    HBox line = new HBox(50);
    HBox line2 = new HBox(50);
    VBox all = new VBox(30);
    
    col1.setPadding(new Insets(45,0,0,10));
    col2.setPadding(new Insets(22,0,0,0));
    
    col1.getChildren().addAll(name, exceptionReport, scheduling, wigrow);
    col2.getChildren().addAll(text1, cb1, cb2, cb3);

    line.getChildren().addAll(col1,col2);
    
    line2.getChildren().addAll(cancel, add);
    line2.setAlignment(Pos.CENTER_RIGHT);
    line2.setPadding(new Insets(0,10,0,-40));
    
    all.getChildren().addAll(line, line2);
    
    root.setCenter(all);
    
  }
}
