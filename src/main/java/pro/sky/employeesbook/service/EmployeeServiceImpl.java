package pro.sky.employeesbook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employeesbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbook.exceptions.EmployeeInvalidName;
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

    // проверка корректности ввода имени нового сотрудника
    private static boolean checkData(Employee newEmployee) {
        return StringUtils.isAlpha(newEmployee.getFirstName()) && StringUtils.isAlpha(newEmployee.getLastName());
    }

    // приведение имени нового сотрудника к стандартному виду
    private static Employee correctData(Employee newEmployee) {
        newEmployee.setFirstName(StringUtils.capitalize(newEmployee.getFirstName().toLowerCase()));
        newEmployee.setLastName(StringUtils.capitalize(newEmployee.getLastName().toLowerCase()));
        System.out.printf("Имя сотрудника %s %s приведено к стандартному виду\n", newEmployee.getFirstName(), newEmployee.getLastName());
        return newEmployee;
    }

    // добавление сотрудника
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employeeToAdd = new Employee(firstName, lastName, department, salary);
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException();     // если штат заполнен
        }
        if (checkData(employeeToAdd)) { // проверка правильности ввода имени нового сотрудника
            System.out.println("Имя сотрудника задано корректно");
            correctData(employeeToAdd);
        } else
            throw new EmployeeInvalidName("Имя сотрудника задано неверно");
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

    // автозаполнение списка сотрудников
    public Map<String, Employee> autofill() {
        addEmployee("Александр", "Сергеевич", 1, 56000);
        addEmployee("Михаил", "Лермонтов", 1, 55000);
        addEmployee("Афанасий", "Фет", 2, 44000);
        addEmployee("Владимир", "Маяковский", 3, 58000);
        addEmployee("Сергей", "Есенин", 3, 54000);
        addEmployee("Николай", "Заболоцкий", 4, 44000);
        addEmployee("Анна", "Ахматова", 5, 52000);
        addEmployee("Марина", "Цветаева", 5, 52000);
        addEmployee("Иосиф", "Бродский", 5, 59000);
        addEmployee("Борис", "Пастернак", 5, 59000);
        return  employees;
    }
}
