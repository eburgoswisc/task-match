/**
 * TODO: Ryan Wenzel
 */

package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionsScene extends Scene {
  
  Stage mainStage;
  
  public OptionsScene(Stage mainStage, Parent root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
  }

}
