package pro.sky.employeesbook.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public Employee(String firstName, String lastName, int department, int salary) {  // конструктор класса Employee
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
/*
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
*/
/*    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
*/
  /*  public void setDepartment(int department) {
        this.department = department;
    }
*/
  /*  public void setSalary(int salary) {
        this.salary = salary;
    }

   */
}