/**
 * TODO: Adam Jackson
 */

package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScene extends Scene {
  
  Stage mainStage;
  
  public HomeScene(Stage mainStage, Parent root, int width, int height) {
    super(root,width,height);
    this.mainStage = mainStage;
  }  
}
