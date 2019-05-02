package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Class for a json parser. It reads in json and 
 * writes it out as output
 * 
 *
 */
@SuppressWarnings("unchecked")
public class JSONFileParser {

  /**
   * Helper method to read employees data and add the employees to the treeMap
   * 
   * @param jaEmployees - JSONArray
   */
  private static void addEmployees(JSONArray jaEmployees) {
    // go throw the JSONArray jaEmployees .
    for (int i = 0; i < jaEmployees.size(); i++) {
      JSONObject jo = null;
      try {
        jo = (JSONObject) jaEmployees.get(i);

        // get and save ID, name, exceptionReport,scheduling, WIGrow,points
        long ID = (long) jo.get("ID");
        String name = (String) jo.get("Name");
        Boolean exceptionReport = (Boolean) jo.get("Exception Report");
        Boolean scheduling = (Boolean) jo.get("Scheduling");
        Boolean WIGrow = (Boolean) jo.get("WIGrow");
        int points = ((Number) jo.get("Points")).intValue();

        // Create employee variable and set WIGrow,points,scheduling,exceptionReport, name,ID
        Employee employee = new Employee(name, ID);
        employee.setExceptionReport(exceptionReport);
        employee.setScheduling(scheduling);
        employee.setWiGrow(WIGrow);
        employee.setPointTask(points);
        // add employee to the treeMap
        Main.getAllEmployees().add(employee);
      } catch (Exception e) {
        e.printStackTrace();
      } // cast to json object
    }

  }


  /**
   * Helper method to read Task data and add the tasks to the tasks list
   * 
   * @param jaTasks
   */
  private static void addTasks(JSONArray jaTasks) {
    // go throw the JSONArray of tasks
    for (int i = 0; i < jaTasks.size(); i++) {
      JSONObject jo = (JSONObject) jaTasks.get(i);

      // get and save ID, description, favorable
      int ID = ((Number) jo.get("ID")).intValue();
      String description = (String) jo.get("Description");
      boolean favorable = (boolean) jo.get("Favorable");
      // create task object and set description, favorable, task,ID
      Task task = new Task(description, favorable);
      task.setID(ID);
      Main.getAllTasks().add(task);
    }


  }

  /**
   * Parse and read data from file and store employees in the treeMap and task in the tasks list
   * 
   * @param jsonFilepath
   * @throws FileNotFoundException
   */
  public static void readData(String jsonFilepath) throws FileNotFoundException {
    try {

      // Parse the content of the file as an object
      Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
      JSONObject jo = (JSONObject) obj; // cast(convert) to jSON object
      // stores employees in the JSON array employees
      JSONArray jaEmployees = (JSONArray) jo.get("employees");
      // stores tasks in the JSON array tasks
      JSONArray jaTasks = (JSONArray) jo.get("tasks");
      // parse each object and add to treeMap or tasks list
      addEmployees(jaEmployees);
      addTasks(jaTasks);
      // Should never happen
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      // Do nothing
    }
  }

  /**
   * Method for writing out a JSON file
   * 
 * @param jsonFilePath
 */
public static void write(String jsonFilePath) {
	// Get JSON objects
    JSONObject doc = new JSONObject();
    JSONArray employeesArray = new JSONArray();
    JSONArray tasksArray = new JSONArray();
    // Get all the employees in a list
    ArrayList<Employee> allEmployees = Main.getAllEmployees();
    allEmployees.addAll(Main.getEmployeesInUnit()); // done because employees in unit do not show up
                                                    // in allEmployees
    // Loop through employees and add them to a json object
    for (Employee e : allEmployees) {
      JSONObject curEmployee = new JSONObject();
      curEmployee.put("ID", e.getId());
      curEmployee.put("Name", e.getName());
      curEmployee.put("Exception Report", e.isExceptionReport());
      curEmployee.put("Scheduling", e.isScheduling());
      curEmployee.put("WIGrow", e.isWiGrow());
      curEmployee.put("Points", e.getPointTask());
      employeesArray.add(curEmployee);
    }
    // Add employees to overall json document
    doc.put("employees", employeesArray);

    // Loop through tasks and add to json object
    for (Task t : Main.getAllTasks()) {
      JSONObject curTask = new JSONObject();
      curTask.put("ID", t.getID());
      curTask.put("Description", t.getDescription());
      curTask.put("Favorable", t.isFavorable());
      tasksArray.add(curTask);
    }
    // Add Tasks to json document
    doc.put("tasks", tasksArray);

    try (FileWriter file = new FileWriter(jsonFilePath)) {
    	// Write to file
      file.write(doc.toJSONString());
      file.flush();
   // Should not happen
    } catch (IOException e) {
      e.printStackTrace();
      JSONFileParser.write(jsonFilePath);
    }
  }
}
