package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implemetation class of Employee Service
 *
 * @author Jimmie
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;


    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all Employees' information from database
     * @return List<Employee>
     */
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * Get certain Employee's information by id
     *
     *  @param employeeId
     *  @return Employee
     */
    public Employee getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return employee;
    }

    /**
     * Save new Employee's information into database
     *
     * @param employee
     *
     */
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     * Delete certain Employee's information by id
     *
     *  @param employeeId
     */
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Update certain Employee's information
     *
     * @param employee
     */
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}