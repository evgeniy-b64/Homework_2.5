package pro.sky.employeesbook;

public class Employee {
    private String firstName;

    private String lastName;

    public Employee(String firstName, String secondName, String lastName, int department, int salary) {          // конструктор класса Employee
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
    public String toString() {      // вывод всей информации о сотруднике
        return lastName + " " + firstName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}