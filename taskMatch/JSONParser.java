package taskMatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.*;
import java.util.Map.Entry;
import application.Main;

public class JSONParser {

  /**
   * Helper method to read employees data and add the employees to the treeMap
   * 
   * @param jaEmployees
   */
  private void addEmployees(JSONArray jaEmployees) {
    // go throw the JSONArray jaEmployees .
    for (int i = 0; i < jaEmployees.size(); i++) {
      JSONObject jo = null;
      try {
        jo = (JSONObject) jaEmployees.get(i);

        // get and save ID, name, exceptionReport,scheduling, WIGrow,points
        int ID = (int) jo.get("ID");
        String name = (String) jo.get("name");
        Boolean exceptionReport = (Boolean) jo.get("Exception Report");
        Boolean scheduling = (Boolean) jo.get("Scheduling");
        Boolean WIGrow = (Boolean) jo.get("WIGrow");
        int points = (int) jo.get("points");

        // Create employee variable and set WIGrow,points,scheduling,exceptionReport, name,ID
        Employee employee = new Employee(name, ID);
        employee.setExceptionReport(exceptionReport);
        employee.setScheduling(scheduling);
        employee.setWiGrow(WIGrow);
        employee.setPointTask(points);
        // add employee to the treeMap
        Main.allEmployees.put(employee.getName(), employee);
      } catch (Exception e) {
      } // cast to json object
    }

  }


  /**
   * Helper method to read Task data and add the tasks to the tasks list
   * 
   * @param jaTasks
   */
  private void addTasks(JSONArray jaTasks) {
    // go throw the JSONArray of tasks
    for (int i = 0; i < jaTasks.size(); i++) {
      JSONObject jo = null;
      try {
        jo = (JSONObject) jaTasks.get(i);

        // get and save ID, description, favorable
        int ID = (int) jo.get("ID");
        String description = (String) jo.get("Description");
        boolean favorable = (boolean) jo.get("Favorable");
        // create task object and set description, favorable, task,ID
        Task task = new Task(description, favorable);
        task.setID(ID);
        Main.allTasks.add(task);
      } catch (Exception e) {
        // Do Nothing
      } // cast to json object
    }


  }

  /**
   * Parse and read data from file and store employees in the treeMap and task in the tasks list
   * 
   * @param jsonFilepath
   * @throws FileNotFoundException
   */
  void readData(String jsonFilepath) throws FileNotFoundException {
    try {

      // Parse the content of the file as an object
      Object obj = (Object) new JSONParser().parse(new FileReader(jsonFilepath));
      JSONObject jo = (JSONObject) obj; // cast(convert) to jSON object
      // stores employees in the JSON array employees
      JSONArray jaEmployees = (JSONArray) jo.get("employees");
      // stores tasks in the JSON array tasks
      JSONArray jaTasks = (JSONArray) jo.get("employees");
      // parse each object and add to treeMap or tasks list
      this.addEmployees(jaEmployees);
      this.addTasks(jaTasks);

    } catch (FileNotFoundException e) {
      throw e;
    } catch (Exception e) {
      // Do nothing
    }
  }

  private Object parse(FileReader fileReader) {
    // TODO Auto-generated method stub
    return null;
  }

  public static void writeData() {
    // Main.allEmployees;
    // Main.allTasks;
    // Get iterator for treemap
    Set<Entry<String, Employee>> set = Main.allEmployees.entrySet();;
    Iterator<Entry<String, Employee>> employeeIt = set.iterator();

    // Create JSONArray Objects
    JSONArray employeesArray = new JSONArray();
    JSONArray tasksArray = new JSONArray();

    // Name for output file: test_output.json
    FileWriter f = null;
    try {
      f = new FileWriter("test_output.json");
    } catch (IOException e) {
      // Do Nothing
    }

    // Place inside loop
    try {
      while (employeeIt.hasNext()) {
        Entry<String, Employee> node = employeeIt.next();
        Employee e = node.getValue();
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
      for (Task t : Main.allTasks) {
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
