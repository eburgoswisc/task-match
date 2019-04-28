package taskMatch;

public class Employee implements Comparable<Employee> {

  private int id;
  private String name;
  private boolean exceptionReport;
  private boolean scheduling;
  private boolean wiGrow;
  private int pointTask;
  
  public Employee(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public String toString() {
    return String.format("%-40s %20d", this.name, this.id);
  }
  
  public int compareTo(Employee other) {
    return 0;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isExceptionReport() {
    return exceptionReport;
  }

  public void setExceptionReport(boolean exceptionReport) {
    this.exceptionReport = exceptionReport;
  }

  public boolean isScheduling() {
    return scheduling;
  }

  public void setScheduling(boolean scheduling) {
    this.scheduling = scheduling;
  }

  public boolean isWiGrow() {
    return wiGrow;
  }

  public void setWiGrow(boolean wiGrow) {
    this.wiGrow = wiGrow;
  }

  public int getPointTask() {
    return pointTask;
  }

  public void setPointTask(int pointTask) {
    this.pointTask = pointTask;
  }
}
