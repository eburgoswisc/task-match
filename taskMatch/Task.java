package taskMatch;

import java.util.ArrayList;

public class Task {
  private int ID;
  private String description;
  private boolean favorable;
  private ArrayList<Employee> employees;
  private boolean assigned;

  Task(String description, boolean favorable) {
    this.description = description;
    this.favorable = favorable;
    this.employees = new ArrayList<Employee>();
  }

  /**
   * @return the iD
   */
  public int getID() {
    return ID;
  }

  /**
   * @param iD the iD to set
   */
  public void setID(int iD) {
    ID = iD;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the favorable
   */
  public boolean isFavorable() {
    return favorable;
  }

  /**
   * @param favorable the favorable to set
   */
  public void setFavorable(boolean favorable) {
    this.favorable = favorable;
  }

  /**
   * @return the employees
   */
  public ArrayList<Employee> getEmployees() {
    return employees;
  }

  /**
   * @param employees the employees to set
   */
  public void setEmployees(ArrayList<Employee> employees) {
    this.employees = employees;
  }

  /**
   * @return the assigned
   */
  public boolean isAssigned() {
    return assigned;
  }

  /**
   * @param assigned the assigned to set
   */
  public void setAssigned(boolean assigned) {
    this.assigned = assigned;
  }
}
