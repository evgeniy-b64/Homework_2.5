package pro.sky.employeesbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeesbook.model.Employee;
import pro.sky.employeesbook.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // поиск сотрудников  с минимальной зарплатой в отделе
    @GetMapping("/min-salary")
    public Employee findMinSalary(@RequestParam("departmentID") int departmentID) {
        return departmentService.findMinSalary(departmentID);
    }

    // поиск сотрудников  с максимальной зарплатой в отделе
    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam("departmentID") int departmentID) {
        return departmentService.findMaxSalary(departmentID);
    }

    // вычисление расходов на зарплату всех сотрудников в штате
    @GetMapping("/salaryexpenses")
    public long countSalaryExpenses() {
        return departmentService.countSalaryExpenses();
    }

    // вычисление расходов на зарплату всех сотрудников в отделе
    @GetMapping(value = "/salaryexpenses", params = "departmentID")
    public long countSalaryExpenses(@RequestParam("departmentID") int departmentID) {
        return departmentService.countSalaryExpenses(departmentID);
    }

    // вывод всех сотрудников в отделе
    @GetMapping(value = "/all", params = "departmentID")
    public List<Employee> getAll(@RequestParam("departmentID") int departmentID) {
        return departmentService.getAll(departmentID);
    }

    // вывод всех сотрудников в штате
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }

    // вывод сотрудников с зарплатой ниже указанной
    @GetMapping("/allbelow")
    public List<Employee> allBelow(@RequestParam("salary") int salary) {
        return departmentService.findAllBelow(salary);
    }

    // вывод сотрудников отдела с зарплатой ниже указанной
    @GetMapping(value = "/allbelow", params = "departmentID")
    public List<Employee> allBelow(@RequestParam("departmentID") int departmentID, @RequestParam("salary") int salary) {
        return departmentService.findAllBelow(departmentID, salary);
    }
}
