package ro.z2h.controller;

import ro.z2h.Service.EmployeeServiceImpl;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 11/11/2014.
 */
@MyController(urlPath = "/employee")
public class EmployeeController extends EmployeeServiceImpl {

    @MyRequestMethod(methodType = "GET", urlPath = "/all")
    public List<Employee> getAllEmployees(){

//        Employee employee1 = new Employee();
//        employee1.setFirstName("Employee 1");
//        employee1.setId(10l);
//
//        Employee employee2 = new Employee();
//        employee2.setFirstName("Employee 2");
//        employee2.setId(11l);
//
//        ArrayList<Employee> employeeList = new ArrayList<Employee>();
//        employeeList.add(employee1);
//        employeeList.add(employee2);

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employeeServiceAllEmployees = employeeService.findAllEmployees();

        return employeeServiceAllEmployees;
    }
    @MyRequestMethod(methodType = "GET", urlPath = "/one")

    public Employee getOneEmployee (String id){

//        Employee employee= new Employee();
//        employee.setFirstName("Test");
//        return employee;
//        return "oneRandomEmployee";
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Employee oneEmployee = employeeService.findOneEmployee(Long.parseLong(id));
        return oneEmployee;
    }

}
