package pro.sky.employeesbook.service;

import org.junit.jupiter.api.Test;
import pro.sky.employeesbook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbook.exceptions.EmployeeInvalidName;
import pro.sky.employeesbook.exceptions.EmployeeNotFoundException;
import pro.sky.employeesbook.exceptions.EmployeeStorageIsFullException;
import pro.sky.employeesbook.model.Employee;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private final String testFirstName = "Firstname";
    private final String testLastName = "Lastname";
    private final int testDepartment = 1;
    private final int testSalary = 10000;
    private final Employee testDummy = new Employee(testFirstName, testLastName, testDepartment, testSalary);
    EmployeeService employeeService = new EmployeeServiceImpl();

    // тестирование метода addEmployee
    @Test
    void addMethodWorks() {
        employeeService.addEmployee(testDummy);
        assertTrue(employeeService.findAll().contains(testDummy));
    }

    @Test
    void addMethodThrowsExceptionFull() {   // выбрасывает исключение, если хранилище заполнено
        employeeService.autofill();
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(testDummy));
    }

    @Test
    void addMethodThrowsExceptionAlreadyAdded() {   // выбрасывает исключение, если такой сотрудник уже добавлен
        employeeService.addEmployee(testDummy);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(testDummy));
    }

    @Test
    void addMethodThrowsExceptionInvalidName() {    // выбрасывает исключение, если имя задано некорректно
        assertThrows(EmployeeInvalidName.class, () -> employeeService.addEmployee("1qa/", testLastName, testDepartment, testSalary));
    }

    // тестирование метода deleteEmployee
    @Test
    void deleteMethodThrowsExceptionNotFound() {    // выбрасывает исключение, если сотрудник не найден
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(testFirstName, testLastName));
    }

    @Test
    void deleteMethodWorks() {    // метод работает корректно
        employeeService.addEmployee(testDummy);
        employeeService.deleteEmployee(testFirstName, testLastName);
        assertTrue(employeeService.findAll().isEmpty());
    }

    // тестирование метода findByName
    @Test
    void findMethodThrowsExceptionNotFound() {    // выбрасывает исключение, если сотрудник не найден
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findByName(testFirstName, testLastName));
    }

    @Test
    void findMethodWorks() {    // метод работает корректно
        employeeService.addEmployee(testDummy);
        assertNotNull(employeeService.findByName(testFirstName, testLastName));
        assertEquals(employeeService.findByName(testFirstName, testLastName), testDummy);
    }

    // тестирование метода findAll
    @Test
    void findAllMethodWorks() {
        employeeService.addEmployee(testDummy);
        assertNotNull(employeeService.findAll());
    }


    // тестирование метода autofill
    @Test
    void autofillMethodWorks() {
        assertNotNull(employeeService.autofill());
        assertEquals(employeeService.findAll().size(), 10);
    }
}
