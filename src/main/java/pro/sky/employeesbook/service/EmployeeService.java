package pro.sky.employeesbook.service;

import pro.sky.employeesbook.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee deleteEmployee(String firstName, String lastName);

    Employee findByName(String firstName, String lastName);

    Employee addEmployee(String firstName, String lastName, int department, int salary);

    List<Employee> findAll();
}
