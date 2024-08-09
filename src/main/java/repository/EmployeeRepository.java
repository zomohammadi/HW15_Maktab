
package repository;

import entity.Employee;

public interface EmployeeRepository {
    Employee findByCode(String code);
}


