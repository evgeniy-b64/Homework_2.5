package pro.sky.employeesbook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbook.exceptions.EmployeeNotFoundException;
import pro.sky.employeesbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.employeesbook.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int maxEmployeesNumber = 10;
    private Map<String, Employee> employees = new HashMap<>();

    private String makeKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    // добавление сотрудника
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employeeToAdd = new Employee(firstName, lastName, department, salary);
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException();     // если штат заполнен
        }
        if (employees.containsValue(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже добавлен");
        }
        String key = makeKey(firstName, lastName);
        employees.put(key, employeeToAdd);
        System.out.printf("Сотрудник '%s %s' добавлен\n", employeeToAdd.getFirstName(), employeeToAdd.getLastName());
        return employeeToAdd;
    }

    // удаление сотрудника по имени и фамилии
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employeeToDelete = findByName(firstName, lastName);
        if (!employees.containsValue(employeeToDelete)) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
        }
        employees.remove(makeKey(firstName, lastName));
        System.out.printf("Сотрудник '%s %s' удалён\n", firstName, lastName);
        return employeeToDelete;
    }

    // поиск сотрудника по имени и фамилии
    public Employee findByName(String firstName, String lastName) {
        String employeeToFind = makeKey(firstName, lastName);
        if (employees.containsKey(employeeToFind)) {
            System.out.printf("Найден сотрудник: '%s %s' \n", firstName, lastName);
            return employees.get(employeeToFind);
        }
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
    }

    // создание полного списка сотрудников
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }
}
