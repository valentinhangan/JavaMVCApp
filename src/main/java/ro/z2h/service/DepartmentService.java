package ro.z2h.service;

import ro.z2h.domain.Department;

import java.util.List;

/**
 * Created by hangan on 11/12/2014.
 */
public interface DepartmentService {
    public List<Department> findAllDepartments();
    public Department findOneDepartment(Long Id);
}
