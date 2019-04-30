package application;

/**
 * Class object to represent each employee that is loaded into program. This class stores all of the
 * necessary information about each employee.
 * 
 * @author CS400 aTeam 7
 *
 */
public class Employee implements Comparable<Employee> {

  // Private fields for employee object
  private long id;
  private String name;
  private boolean exceptionReport;
  private boolean scheduling;
  private boolean wiGrow;
  private int pointTask; // To be implemented in future update

  public Employee(String name, long id) {
    this.name = name;
    this.id = id;
  }

  /**
   * This class returns the employee's name and ID number in a string
   */
  @Override
  public String toString() {
    return String.format("%-40s %20d", this.name, this.id);
  }

  /**
   * Necessary method for the comparable interface
   */
   @Override
   public int compareTo(Employee other) {
   return 0;
   }

  /**
   * Getter method for employee ID number
   * 
   * @return ID number
   */
  public long getId() {
    return id;
  }

  /**
   * Setter method for employee ID number field
   * 
   * @param ID number
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Getter method for employee name
   * 
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter method for employee name field
   * 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method for employee exception report boolean field
   * 
   * @param exception report (true or false)
   */
  public boolean isExceptionReport() {
    return exceptionReport;
  }

  /**
   * Setter method for the employee exception report field
   * 
   * @param exception report needed (true or false)
   */
  public void setExceptionReport(boolean exceptionReport) {
    this.exceptionReport = exceptionReport;
  }

  /**
   * Getter method for employee scheduling boolean field
   * 
   * @param scheduling work needed (true or false)
   */
  public boolean isScheduling() {
    return scheduling;
  }

  /**
   * Setter mehod for the employee scheduling field
   * 
   * @param scheduling (true or false)
   */
  public void setScheduling(boolean scheduling) {
    this.scheduling = scheduling;
  }

  /**
   * Getter method for employee WiGrow boolean field
   * 
   * @param WiGrow (true or false)
   */
  public boolean isWiGrow() {
    return wiGrow;
  }

  /**
   * Setter mehod for the employee WiGrow conversation field
   * 
   * @param wiGrow (true or false)
   */
  public void setWiGrow(boolean wiGrow) {
    this.wiGrow = wiGrow;
  }

  // Points system to be implemented in future update
   /**
   * Getter method for employee points field
   * @param points (integer)
   */
   public int getPointTask() {
   return pointTask;
   }
  
   /**
   * Setter method for employee points field
   * @param points (integer)
   */
   public void setPointTask(int pointTask) {
   this.pointTask = pointTask;
   }

}
