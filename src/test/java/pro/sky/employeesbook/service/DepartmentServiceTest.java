package pro.sky.employeesbook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeesbook.model.Employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    public static final List<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Александр", "Пушкин", 1, 10000),
            new Employee("Михаил", "Лермонтов", 1, 11000),
            new Employee("Афанасий", "Фет", 2, 12000),
            new Employee("Владимир", "Маяковский", 2, 13000),
            new Employee("Сергей", "Есенин", 3, 14000),
            new Employee("Николай", "Заболоцкий", 3, 15000),
            new Employee("Анна", "Ахматова", 4, 16000),
            new Employee("Марина", "Цветаева", 4, 17000),
            new Employee("Иосиф", "Бродский", 5, 18000),
            new Employee("Борис", "Пастернак", 5, 19000)
    );
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
    }

    @Test
        // тестирование метода findMaxSalary
    void findMaxSalaryTest() {
        Integer actual = departmentService.findMaxSalary(1);
        assertEquals(11000, actual);
    }

    // тестирование метода findMinSalary
    @Test
    void findMinSalaryTest() {
        Integer actual = departmentService.findMinSalary(1);
        assertEquals(10000, actual);
    }

    // тестирование метода countSalaryExpenses по отделам
    @Test
    void countSalaryExpensesTest() {
        Long actual = departmentService.countSalaryExpenses(1);
        assertEquals(21000, actual);
        actual = departmentService.countSalaryExpenses();
        assertEquals(145000, actual);
    }

    // тестирование метода getAll
    @Test
    void getAllTest() {
        assertNotNull(departmentService.getAll());
        assertEquals(departmentService.getAll().size(),5);
        System.out.println(departmentService.getAll());
    }

    // тестирование метода findAllBelow
    @Test
    void findAllBelowTest() {
        assertNotNull(departmentService.findAllBelow(11000));               // выдаёт непустой список
        assertEquals(departmentService.findAllBelow(15000).size(),5);
    }
}
