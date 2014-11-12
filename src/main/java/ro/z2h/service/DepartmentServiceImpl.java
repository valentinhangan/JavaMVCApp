package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Department;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangan on 11/12/2014.
 */
public class DepartmentServiceImpl implements DepartmentService{

    @Override
    public List<Department> findAllDepartments() {
        List<Department> departmentslist = new ArrayList<Department>();
        DepartmentDao dep = new DepartmentDao();
        Connection con = DatabaseManager.getConnection("ZTH_09", "passw0rd");
        try {
            departmentslist=dep.getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentslist;
    }

    public Department findOneDepartment(){
        List<Department> departmentslist = new ArrayList<Department>();
        DepartmentDao dep = new DepartmentDao();
        Department d = new Department();
        Connection con = DatabaseManager.getConnection("ZTH_09","passw0rd");
        try {
            d = dep.getDepartmentById(con, 100l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}
