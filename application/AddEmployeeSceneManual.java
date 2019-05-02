
package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * AddEmployeeSceneManual Scene, this scene show when the user click on
 * Add Employee button on the AddEmployeeAutoScene.
 * 
 * @author Akovi Mensah
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class AddEmployeeSceneManual extends Scene {

	Stage mainStage; //the main stage
	Label name; //prompt the user for the name of new Employee
	Label id; //prompt the user for the id of new Employee
	Label exceptionReport; //prompt the user for the exceptionReport of new Employee
	Label scheduling; //prompt the user for the scheduling of new Employee
	Label wigrow; //prompt the user for the wigrow of new Employee

	TextField nameText; //TextField to get name from the user
	TextField idText; //TextField to get id from the user

	ChoiceBox exceptionCB; // exception ChoiceBox
	ChoiceBox schedulingCB; // scheduling ChoiceBox
	ChoiceBox wigrowCB; // wigrowCB ChoiceBox

	Button cancel; //cancel button
	Button add; //add button to add new employee

	/**
	 * 4 arguments constructore for AddEmployeeSceneManual
	 * @param mainStage
	 * @param root, the border pane
	 * @param width, the width of GUI
	 * @param height,the height of the GUI
	 */
	public AddEmployeeSceneManual(Stage mainStage, BorderPane root, double width, double height) {
		//call superclass 3 args constructor and initialize mainStage
		super(root, width, height);
		this.mainStage = mainStage;
		this.mainStage.setTitle("Add a New Employee");
		//make root to autosize and set padding
		root.autosize();
		root.setPadding(new Insets(10));
		//initialize name,id,exceptionReport,scheduling,wigrow fields and set text if needed
		this.name = new Label("Employee first name: ");
		this.id = new Label("ID: ");
		this.exceptionReport = new Label();
		this.scheduling = new Label();
		this.wigrow = new Label();
		exceptionReport.setText("Is an exception report needed? ");
		scheduling.setText("Are scheduling requirements incomplete? ");
		wigrow.setText("Is a WiGrow conversation needed? ");

		//calls methods to finish setup
		setupInputFields();
		setupCancelAndAdd();
		finalSetup(root);

	}

	/**
	 * Organize elements in boxes and set their position in the GUI
	 * @param root
	 */
	private void finalSetup(BorderPane root) {
		//create 3 VBox and 2 HBox
		VBox col1 = new VBox(40);
		VBox col2 = new VBox(30);
		HBox line = new HBox(50);
		HBox line2 = new HBox(50);
		VBox all = new VBox(30);

		//set padding and add elements
		col1.setPadding(new Insets(30, 0, 0, 10));
		col2.setPadding(new Insets(22, 0, 0, 0));
		//labels in col1
		col1.getChildren().addAll(name, id, exceptionReport, scheduling, wigrow);
		//textfiels and choice boxes in col2
		col2.getChildren().addAll(nameText, idText, exceptionCB, schedulingCB, wigrowCB);
		//add col1 and col2 to one box(line)
		line.getChildren().addAll(col1, col2);
		//add buttons cancel and add to line2
		line2.getChildren().addAll(cancel, add);
		line2.setAlignment(Pos.CENTER_RIGHT);
		line2.setPadding(new Insets(0, 10, 0, -40));

		//add line and line2 to all (vertical box)
		all.getChildren().addAll(line, line2);

		root.setCenter(all);//set all to the center
	}

	/**
	 * Makes textfields and choice boxes to get the user input
	 */
	private void setupInputFields() {
		//initialize nameText and idText
		this.nameText = new TextField();
		this.idText = new TextField();

		// ChoiceBoxes for each of the three options
		exceptionCB =
				new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
		exceptionCB.getSelectionModel().selectFirst();
		exceptionCB.setStyle("-fx-font-size:15");

		schedulingCB =
				new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
		schedulingCB.getSelectionModel().selectFirst();
		schedulingCB.setStyle("-fx-font-size:15");

		wigrowCB =
				new ChoiceBox(FXCollections.observableArrayList("No", new Separator(), "Yes"));
		wigrowCB.getSelectionModel().selectFirst();
		wigrowCB.setStyle("-fx-font-size:15");

	}

	/**
	 * Helper method to make cancel and add buttons work
	 * cancel go back to AddEmployeesAuto without adding any new employee
	 * and add will add the new employee and switch to AddEmployeesAuto
	 */
	private void setupCancelAndAdd() {
		cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			Main.switchToAddEmployeesAuto(this.mainStage); //go back to addEmployeesAuto
		});
		this.add = new Button("Add");
		add.setOnAction(e -> {
			// Get values entered
			boolean exceptionChoice = getBooleanFromCB((String) exceptionCB.getValue());
			boolean schedulingChoice = getBooleanFromCB((String) schedulingCB.getValue());
			boolean wigrowChoice = getBooleanFromCB((String) wigrowCB.getValue());

			try {
				//get the id
				long idEntered = Long.parseLong(idText.getText());
				//create the new employee with the inputs provided bu the user
				Employee newEmployee = new Employee(nameText.getText(), idEntered, exceptionChoice,
						schedulingChoice, wigrowChoice);
				//add the new employee and switch to AddEmployeeAuto
				Main.getAllEmployees().add(newEmployee);
				Main.switchToAddEmployeesAuto(this.mainStage);

			} catch(NumberFormatException f) {
				//Display alert pop up if id was not entered
				Alert incorrectFormat = new Alert(AlertType.WARNING, "Please enter a 9 digit integer ID");
				incorrectFormat.showAndWait();
			}
		});
	}

	/**
	 * Helper method to convert the choice to boolean
	 * 
	 * @param choice of the user
	 * @return true if the user select yes and
	 * false, if the user select no
	 */
	private boolean getBooleanFromCB(String choice) {
		if (choice.equalsIgnoreCase("Yes")) {
			return true;
		} else {
			return false;
		}
	}
}
