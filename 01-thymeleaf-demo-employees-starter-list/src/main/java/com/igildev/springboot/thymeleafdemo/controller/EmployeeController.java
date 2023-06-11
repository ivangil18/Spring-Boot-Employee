package com.igildev.springboot.thymeleafdemo.controller;


import com.igildev.springboot.thymeleafdemo.entity.Employee;
import com.igildev.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // wire employee Service
    private EmployeeService employeeService;


    // there is only one constructor - therefore @Autowired is optional
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get employees from the database
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // set mapping for show add form

    @GetMapping("/showFormForAdd")
    public String showFormAdd(Model theModel) {

        // create model attribute to bind form data
        Employee employee = new Employee();

        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormUpdate(Model theModel, @RequestParam("employeeId") int employeeId) {

        //find employee
        Employee employee = employeeService.findById(employeeId);

        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submission
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId) {

        // delete the employee
        employeeService.deleteById(employeeId);

        // redirect to the /employee/list
        return "redirect:/employees/list";
    }


}









