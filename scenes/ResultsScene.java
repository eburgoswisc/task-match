package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResultsScene extends Scene {
  
  Stage mainStage;
  
  public ResultsScene(Stage mainStage, Parent root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
  }

}
