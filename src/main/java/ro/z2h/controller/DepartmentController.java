package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(methodType = "Get", urlPath = "/all")
    public List<Department> getAllDepartments ()
    {

        Department department1 = new Department();
        department1.setId(1l);
        department1.setDepartmentName("Dept 1");

        Department department2 = new Department();
        department2.setId(2l);
        department2.setDepartmentName("Dept 2");

        ArrayList<Department> departments = new ArrayList<Department>();
        departments.add(department1);
        departments.add(department2);

        return departments;
    }
}
