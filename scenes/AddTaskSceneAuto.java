/**
 * TODO: Ritik Goyal
 */

package scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddTaskSceneAuto extends Scene {
  Stage mainStage;
  
  public AddTaskSceneAuto(Stage mainStage, Parent root, double width, double height) {
    super(root, width, height);
    this.mainStage = mainStage;
  }
}
