/**
 * TODO: Akovi
 */
package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddEmployeeSceneAuto extends Scene {
  
  Stage mainStage;
  
  AddEmployeeSceneAuto(Stage mainStage, Parent root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
  }

}
