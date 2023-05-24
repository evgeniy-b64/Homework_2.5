package pro.sky.employeesbook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbook.exceptions.EmployeeNotFoundException;
import pro.sky.employeesbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.employeesbook.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int maxEmployeesNumber = 10;
    private List<Employee> employeeList = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employeeList.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException();
        }
        if (employeeList.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже добавлен");
        }
        employeeList.add(employeeToAdd);
        System.out.printf("Сотрудник '%s %s' добавлен\n", employeeToAdd.getFirstName(), employeeToAdd.getLastName());
        return employeeToAdd;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        String fullName = firstName + lastName;
        Employee employeeToDelete = new Employee(firstName, lastName);
        if (!employeeList.contains(employeeToDelete)) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
        }
        employeeList.remove(employeeToDelete);
        System.out.printf("Сотрудник '%s %s' удалён\n", employeeToDelete.getFirstName(), employeeToDelete.getLastName());
        return employeeToDelete;
    }

    public Employee findByName(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        if (employeeList.contains(employeeToFind)) {
            return employeeToFind;
        }
        /*for (Employee employee : employeeList) {
            if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                return employee;
            }
        }*/
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден");
    }

    public List<Employee> printEmployeeList() {
        return employeeList;
    }
}
