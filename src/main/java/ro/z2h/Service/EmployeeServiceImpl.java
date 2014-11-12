package ro.z2h.Service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 11/12/2014.
 */
public class EmployeeServiceImpl implements EmployeeService
{
    public List<Employee> findAllEmployees()
    {
        Connection con = DatabaseManager.getConnection("zth_15", "passw0rd");
        EmployeeDao employeeDao = new EmployeeDao();
        ArrayList<Employee> employeeArrayList1 = null;

        try {

          employeeArrayList1 = employeeDao.getAllEmployees(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeArrayList1;
    }

    public Employee findOneEmployee(Long id){
        Employee employeeById=null;
        Connection con = DatabaseManager.getConnection("zth_15", "passw0rd");
        EmployeeDao employeeDao = new EmployeeDao();
        try {
           employeeById = employeeDao.getEmployeeById(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeById;
    }

}
