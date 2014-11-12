package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

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
        int i=10;
        while(i!=0){
            i--;
            Department d = new Department();
            departments.add(d);
        }



        return departments;

    }
    }
