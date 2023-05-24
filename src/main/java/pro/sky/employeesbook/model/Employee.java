package pro.sky.employeesbook.model;

import java.util.Objects;

public class Employee {
    private String firstName;

    private String lastName;

    public Employee(String firstName, String lastName) {  // конструктор класса Employee
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {          // сеттер имени сотрудника
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {            // сеттер фамилии сотрудника
        this.lastName = lastName;
    }

    public String getFirstName() {                      // геттер имени сотрудника
        return this.firstName;
    }

    public String getLastName() {                      // геттер фамилии сотрудника
        return this.lastName;
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
}