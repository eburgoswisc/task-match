package taskMatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import application.Main;

public class JSONParser {
  
  /**
   * Helper method to read employees data and add the employees to
   * the treeMap
   * @param jaEmployees
   */
  private void addEmployees(JSONArray jaEmployees) {
      //go throw the JSONArray jaEmployees 
      for (Object o : jaEmployees) {
          JSONObject jo = (JSONObject) o;//cast to json object
          //get and save ID, name, exceptionReport,scheduling, WIGrow,points
          int ID = (int) jo.get("ID");
          String name = (String) jo.get("name");
          Boolean exceptionReport = (Boolean) jo.get("Exception Report");
          Boolean scheduling = (Boolean) jo.get("Scheduling");
          Boolean WIGrow = (Boolean) jo.get("WIGrow");
          int points = (int) jo.get("points");
          
          //Create employee variable and set WIGrow,points,scheduling,exceptionReport, name,ID
          Employee employee = new  Employee(name, ID);
          employee.setExceptionReport(exceptionReport);
          employee.setScheduling(scheduling);
          employee.setWiGrow(WIGrow);
          employee.setPointTask(points);
          //add employee to the treeMap
          Main.allEmployees.put(employee.getName(), employee);
      }
      
  }
  

  /**
   * Helper method to read Task data and add the tasks to the 
   * tasks list
   * @param jaTasks
   */
  private void addTasks(JSONArray jaTasks)  {
      //go throw the JSONArray of tasks
      for (Object o : jaTasks) {
          JSONObject jo = (JSONObject) o;//cast to json object
          //get and save ID, description, favorable
          int ID = (int) jo.get("ID");
          String description = (String) jo.get("Description");
          boolean favorable = (boolean) jo.get("Favorable");
          //create task object and set description, favorable, task,ID
          Task task = new Task(description, favorable);
          task.setID(ID);
          Main.allTasks.add(task);
      }
  
  
  }
  
  /**
   * Parse and read data from file and store employees in the treeMap
   * and task in the tasks list
   * @param jsonFilepath
   * @throws FileNotFoundException
   */
  void readData(String jsonFilepath) throws FileNotFoundException {
      try {
          
          //Parse the content of the file as an object
          Object obj = (Object) new JSONParser().parse(new FileReader(jsonFilepath));
          JSONObject jo = (JSONObject) obj; //cast(convert) to jSON object
          //stores employees in the JSON array employees
          JSONArray jaEmployees = (JSONArray) jo.get("employees"); 
          //stores tasks in the JSON array tasks
          JSONArray jaTasks = (JSONArray) jo.get("employees");
          //parse each object and add to treeMap or tasks list
          this.addEmployees(jaEmployees);
          this.addTasks(jaTasks);
      
      } catch (FileNotFoundException e) {
          throw e;
      } catch (Exception e) {
          throw e;
      }
  }

  private Object parse(FileReader fileReader) {
      // TODO Auto-generated method stub
      return null;
  }
}
