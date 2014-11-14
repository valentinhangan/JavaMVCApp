package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangan on 11/11/2014.
 */
@MyController(urlPath = "/employee")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")

    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList();

        EmployeeServiceImpl empl = new EmployeeServiceImpl();
        employees = empl.findAllEmployees();

        return employees;
   }
    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(String Id){
        Employee e = new Employee();
        EmployeeServiceImpl empl = new EmployeeServiceImpl();
        e = empl.findOneEmployee(Long.valueOf(Id));
        return e;
    }





}
