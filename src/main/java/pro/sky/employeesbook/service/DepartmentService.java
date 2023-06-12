package pro.sky.employeesbook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbook.exceptions.EmployeeNotFoundException;
import pro.sky.employeesbook.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    // конструктор DepartmentsService
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // поиск сотрудника с максимальной зарплатой в заданном отделе
    public Employee findMaxSalary(int department) {
        Employee employeeWithMaxSalary = employeeService.printEmployeeList().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        System.out.printf("Максимальная зарплата в %s отделе у %s %s\n",
                employeeWithMaxSalary.getDepartment(),
                employeeWithMaxSalary.getFirstName(),
                employeeWithMaxSalary.getLastName());
        return employeeWithMaxSalary;
    }

    // поиск сотрудника с минимальной зарплатой в заданном отделе
    public Employee findMinSalary(int department) {
        Employee employeeWithMinSalary = employeeService.printEmployeeList().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        System.out.printf("Минимальная зарплата в %s отделе у %s %s\n",
                employeeWithMinSalary.getDepartment(),
                employeeWithMinSalary.getFirstName(),
                employeeWithMinSalary.getLastName());
        return employeeWithMinSalary;
    }

    public List<Employee> getAll(int department) {
        return employeeService.printEmployeeList().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List< Employee>> getAll(){
        return employeeService.printEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
