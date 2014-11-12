package ro.z2h.Service;

import ro.z2h.domain.Employee;

import java.util.List;

/**
 * Created by Computer on 11/12/2014.
 */
public interface EmployeeService {
    public List<Employee> findAllEmployees();

    public  Employee findOneEmployee(Long id);
}
