package taskMatch;

public class Employee {

  int id;
  String name;
  boolean exceptionReport;
  boolean scheduling;
  boolean wiGrow;
  int pointTask;
  
  public Employee(String name) {
    this.name = name;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  public String toString() {
    return String.format("%-20s %20s", this.name, this.id);
  }
}
