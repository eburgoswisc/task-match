/**
 * Author: Emanuel
 */
package scenes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ResultsScene extends Scene {
  
  Stage mainStage;
  
  private final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss"); 
  private final SimpleDateFormat date = new SimpleDateFormat("EEE dd MMM yyyy"); // ex: Mon Apr 01 2019 14:02:13
  
  public ResultsScene(Stage mainStage, BorderPane root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
    // Set property of scene
    this.mainStage.setTitle("Results");
    
    // Initiate Boxes
    initTop(root);
    initCenter(root);
    initBottom(root);
    
  }
  
  /**
   * Initiate the top Box of the results page.
   * 
 * @param mainStage - Main Stage object
 */
private void initBottom(BorderPane root) {
	  // Make bottom box for buttons
	  HBox bottomButtons = new HBox();
	  bottomButtons.setPrefWidth(150);
	  bottomButtons.setAlignment(Pos.CENTER);
	  bottomButtons.setPadding(new Insets(0, 60, 50, 60)); 
	  
	  // Get buttons
	  Button backBut = new Button("Back");

	  Button downReportBut = new Button("Download Report");
	  // Add to HBox
	  bottomButtons.getChildren().addAll(downReportBut, backBut);
	  
	  // Add to root
	  root.setBottom(bottomButtons);
	  root.setPadding(new Insets(20));
	  
  }

/**
 * Set Center panels
 * 
 * @param mainStage
 */
private void initCenter(BorderPane root) {
	// Make center box for reporting tasks
	HBox reportBox = new HBox();
	reportBox.setPadding(new Insets(0, 60, 50, 60));
	// ListView Object
	ListView<String> reportList = new ListView<>();
	
	// Task Report list
	ObservableList<String> items =
	        FXCollections.observableArrayList("[Task1]\n\t\t[employee1Name]\n\t\t[employee2Name]",
	            "[Task2]\n\t\t[employee1Name]");
	// Add to reportList
	reportList.setItems(items);
	
	// Add stuff to box
	reportBox.getChildren().addAll(reportList);
	
	// Add to root
	root.setCenter(reportBox);
}

private void initTop(BorderPane root) {
	// Make HBox
	HBox topBox = new HBox();
	// Set Label
	Label titleLabel = new Label(); // title label for top of UI
    titleLabel.setStyle("-fx-font-size:20");
    titleLabel.setText("Fair Job Planning System            " + date.format(new Date()) + " "
        + clock.format(new Date()));
    titleLabel.setPadding(new Insets(20, 0, 20, 150));
    
    // Add stuff to topBox
    topBox.getChildren().addAll(titleLabel);
    // Add to BorderPane root
    root.setTop(topBox);
}

}
