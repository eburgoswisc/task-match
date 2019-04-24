/**
 * TODO: Akovi
 */
package scenes;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    root.autosize();
    root.setPadding(new Insets(10));
    
    Label name = new Label("Employee Name");
    Label except = new Label("Exception Report");
    Label scheduling = new Label("Scheduling");
    Label WiGrow = new Label("WiGrow");
    TextField text1 = new TextField();
    TextField text2 = new TextField();
    TextField text3 = new TextField();
    TextField text4 = new TextField();
    
    Button cancel = new Button("Cancel");
    Button add = new Button("Add");
    
    
    VBox col1= new VBox(30);
    VBox col2= new VBox(30);
    HBox line = new HBox(50);
    HBox line2 = new HBox(50);
    
    col1.getChildren().addAll(name, except, scheduling, WiGrow);
    col2.getChildren().addAll(text1, text2, text3, text4);
    line2.getChildren().addAll(cancel, add);
    
    line.getChildren().addAll(col1,col2);
    
    root.setCenter(line);
    root.setBottom(line2);
    
    
    
  }
}
