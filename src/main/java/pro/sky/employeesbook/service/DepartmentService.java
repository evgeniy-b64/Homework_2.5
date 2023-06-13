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
        Employee employeeWithMaxSalary = employeeService.findAll().stream()
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
        Employee employeeWithMinSalary = employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
        System.out.printf("Минимальная зарплата в %s отделе у %s %s\n",
                employeeWithMinSalary.getDepartment(),
                employeeWithMinSalary.getFirstName(),
                employeeWithMinSalary.getLastName());
        return employeeWithMinSalary;
    }

    public long countSalaryExpenses() {
        return employeeService.findAll().stream().mapToLong(Employee::getSalary).sum();
    }

    public long countSalaryExpenses(int department) {
        long sum = employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department).mapToInt(Employee::getSalary).sum();
        System.out.printf("Сумма расходов на зарплату в отделе №%s равна %s\n", department, sum);
        return sum;
    }

    public List<Employee> getAll(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAll() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // поиск сотрудников с зарплатой ниже указанной
    public List<Employee> findAllBelow(int salary){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getSalary() < salary)
                .collect(Collectors.toList());
    }

    // поиск сотрудников отдела с зарплатой ниже указанной
    public List<Employee> findAllBelow(int department, int salary){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .filter(employee -> employee.getSalary() < salary)
                .collect(Collectors.toList());
    }
}
