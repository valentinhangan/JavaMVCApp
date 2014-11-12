package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangan on 11/12/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {
    List<Employee> employeelist = new ArrayList<Employee>();
    EmployeeDao empl = new EmployeeDao();
    Connection con = DatabaseManager.getConnection("ZTH_09","passw0rd");
        try {
            employeelist=empl.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeelist;
    }

    public Employee findOneEmployee(){
        List<Employee> employeelist = new ArrayList<Employee>();
        EmployeeDao empl = new EmployeeDao();
        Employee em = new Employee();
        Connection con = DatabaseManager.getConnection("ZTH_09","passw0rd");
        try {
             em = empl.getEmployeeById(con, 100l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return em;
    }
}
