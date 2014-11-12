package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;
import ro.z2h.service.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangan on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all")

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList();

        DepartmentServiceImpl dep = new DepartmentServiceImpl();
        departments = dep.findAllDepartments();

        return departments;
    }

    @MyRequestMethod(urlPath = "/one")
    public Department getOneDepartment(){
        Department d = new Department();
        DepartmentServiceImpl dep = new DepartmentServiceImpl();
        d = dep.findOneDepartment();
        return d;
    }
    }
