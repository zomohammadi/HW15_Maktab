package service;

import entity.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);
    void update(Employee employee);
    void delete(Long id);
    Employee findById(Long id);
    List<Employee> findAll();
    Employee findByUserNameAndPassword(String userName, String password);
    Employee findByCode(String code);

}
