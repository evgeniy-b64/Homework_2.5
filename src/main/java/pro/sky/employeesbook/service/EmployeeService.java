package pro.sky.employeesbook.service;

import pro.sky.employeesbook.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee deleteEmployee(String firstName, String lastName);

    Employee findByName(String firstName, String lastName);

    Employee addEmployee(String firstName, String lastName, int department, int salary);

    Employee addEmployee(Employee employee);

    List<Employee> findAll();

    Map<String, Employee> autofill();
}
