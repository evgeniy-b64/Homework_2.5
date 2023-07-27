package pro.sky.employeesbook.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.employeesbook.model.Employee;
import pro.sky.employeesbook.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // возвращает минимальную зарплату по департаменту
    @GetMapping("{id}/salary/min")
    public Integer findMinSalary(@PathVariable Integer id) {
        return departmentService.findMinSalary(id);
    }

    // возвращает максимальную зарплату по департаменту
    @GetMapping("{id}/salary/max")
    public Integer findMaxSalary(@PathVariable Integer id) {
        return departmentService.findMaxSalary(id);
    }

    // вычисление расходов на зарплату всех сотрудников в штате
    @GetMapping("/salary/sum")
    public long sum() {
        return departmentService.countSalaryExpenses();
    }

    // возвращает сумму зарплат по департаменту
    @GetMapping(value = "{id}/salary/sum")
    public long sum(@PathVariable int id) {
        return departmentService.countSalaryExpenses(id);
    }

    // возвращает список сотрудников по департаменту
    @GetMapping("{id}/employees")
    public List<Employee> employees(@PathVariable Long id) {
        return departmentService.getAll(id);
    }

    // возвращает сотрудников, сгруппированых по отделам
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> employees() {
        return departmentService.getAll();
    }

    // вывод сотрудников компании с зарплатой ниже указанной
    @GetMapping("/allbelow")
    public List<Employee> allBelow(@RequestParam("salary") int salary) {
        return departmentService.findAllBelow(salary);
    }

    // вывод сотрудников отдела с зарплатой ниже указанной
    @GetMapping(value = "{id}/allbelow")
    public List<Employee> allBelow(@PathVariable Long id, @RequestParam("salary") int salary) {
        return departmentService.findAllBelow(id, salary);
    }
}
