package ro.z2h.Service;

import ro.z2h.domain.Department;

import java.util.List;

/**
 * Created by Computer on 11/13/2014.
 */
public interface DepartmentService {
    public List<Department> findAllDepartment();
    public Department findOneDepartment();
}
