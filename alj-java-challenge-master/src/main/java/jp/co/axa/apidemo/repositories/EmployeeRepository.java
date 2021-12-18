package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EmployeeRepository that does basic CRUD operations
 * 
 * @author Jimmie
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
