package taskMatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import application.Main;

@SuppressWarnings("unchecked")
public class JSONFileParser {

  /**
   * Helper method to read employees data and add the employees to the treeMap
   * 
   * @param jaEmployees
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
        int points = ((Number)jo.get("Points")).intValue();

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

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      // Do nothing
    }
  }

  public static void writeData(String jsonFilepath) {
    // Create JSONArray Objects
    JSONArray employeesArray = new JSONArray();
    JSONArray tasksArray = new JSONArray();

    // Name for output file: test_output.json
    FileWriter f = null;
    try {
      f = new FileWriter(jsonFilepath);
    } catch (IOException e) {
      // Do Nothing
    }

    // Place inside loop
    try {
      for(Employee e : Main.getAllEmployees()) {
        JSONObject employee = new JSONObject();
        employee.put("ID", e.getId());
        employee.put("Name", e.getName());
        employee.put("Exception Report", e.isExceptionReport());
        employee.put("Scheduling", e.isScheduling());
        employee.put("WIGrow", e.isWiGrow());

        // Add to JSON Array
        employeesArray.add(employee);
      }

      // Loop through Tasks and make JSONObjects
      for (Task t : Main.getAllTasks()) {
        JSONObject tJson = new JSONObject();
        tJson.put("ID", t.getID());
        tJson.put("Description", t.getDescription());
        tJson.put("Favorable", t.isFavorable());
      }
    } catch (Exception e) {
      // Do nothing
    }
    try {
      // Write JSONArrays
      f.write(employeesArray.toString());
      f.write(tasksArray.toString());


    } catch (IOException e) {
      System.out.println("File already exists");
    }
  }
}
