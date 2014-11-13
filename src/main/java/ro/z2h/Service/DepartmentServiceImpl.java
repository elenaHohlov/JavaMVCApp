package ro.z2h.Service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.domain.Department;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 11/13/2014.
 */
public class DepartmentServiceImpl implements DepartmentService {
    public List<Department> findAllDepartment ()

    {
        ArrayList<Department> allDepartments = null;
        Connection con = DatabaseManager.getConnection("zth_15", "passw0rd");
        DepartmentDao departmentDao = new DepartmentDao();
        try {
           allDepartments = departmentDao.getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDepartments;
    }

    public Department findOneDepartment (){
        Department department= null;
        Connection con = DatabaseManager.getConnection("zth_15", "passw0rd");
        DepartmentDao departmentDao = new DepartmentDao();
        try {
            department = departmentDao.getDepartmentById(con, 10l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;

    }
}
