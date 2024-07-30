package repository;

import entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
   Employee findById(Long id);
    List<Employee> findAll();
    Employee findByUserNameAndPassword(String userName, String password);
}
