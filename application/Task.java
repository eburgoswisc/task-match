package application;

import java.util.ArrayList;

/**
 * Class to store information for each task object that is loaded into program
 * 
 * @author CS400 aTeam7
 *
 */
public class Task {

  // Private fields for task object
  private int ID; // ID is not the same as Employee ID
  private String description;
  private boolean favorable;
  private ArrayList<Employee> employees;
  private boolean assigned;

  /**
   * Constructor for creating a new task object
   * 
   * @param description Name of task
   * @param favorable   Whether task is favorable or not
   */
  public Task(String description, boolean favorable) {
    this.description = description;
    this.favorable = favorable;
    this.employees = new ArrayList<Employee>();
    this.ID = Main.getAllTasks().size() + 1;
  }

  /**
   * Getter method for task ID
   * 
   * @return the ID
   */
  public int getID() {
    return ID;
  }

  /**
   * Setter method for task ID
   * 
   * @param the ID to set
   */
  public void setID(int iD) {
    ID = iD;
  }

  /**
   * Getter method for task description
   * 
   * @return the name of the task
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter method for task ID
   * 
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter method for favorable boolean
   * 
   * @return if task is favorable or not
   */
  public boolean isFavorable() {
    return favorable;
  }

  /**
   * Setter method for favorable boolean
   * 
   * @param boolean if task is favorable or not
   */
  public void setFavorable(boolean favorable) {
    this.favorable = favorable;
  }

  /**
   * Getter method for the list of employees who will complete this task
   * 
   * @return the list of employees assigned this task
   */
  public ArrayList<Employee> getEmployees() {
    return employees;
  }

  /**
   * Setter method for the list of employees who will complete this task
   * 
   * @param employees the employees to set
   */
  public void setEmployees(ArrayList<Employee> employees) {
    this.employees = employees;
  }

  /**
   * Getter method for if this task has been assigned yet
   * 
   * @return boolean if assigned
   */
  public boolean isAssigned() {
    return assigned;
  }

  /**
   * Setter method for if this task has been assigned yet
   * 
   * @param boolean if assigned
   */
  public void setAssigned(boolean assigned) {
    this.assigned = assigned;
  }

  /**
   * Assign this task to an employee by adding the employee to employees list
   * 
   * @param employee to assign the task to
   */
  public void assignTo(Employee employee) {
    employees.add(employee);
    this.setAssigned(true);
  }
}
