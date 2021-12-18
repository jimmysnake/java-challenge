package jp.co.axa.apidemo.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	Employee employee1 = new Employee();
	Employee employee2 = new Employee();

	@Before
	public void init() {

		employee1.setId(1L);
		employee1.setName("T1");
		employee1.setSalary(1000);
		employee1.setDepartment("D1");

		employee2.setId(2L);
		employee2.setName("T2");
		employee2.setSalary(2000);
		employee2.setDepartment("D2");
	}

	@Test
	public void testRetrieveEmployees() {
		employeeService.saveEmployee(employee1);
		employeeService.saveEmployee(employee2);

		List<Employee> retList = employeeService.retrieveEmployees();
		assertEquals(employee1, retList.get(0));
		assertEquals(employee2, retList.get(1));

		employeeService.deleteEmployee(1L);
		employeeService.deleteEmployee(2L);

	}

	@Test
	public void testGetEmployee() {
		employeeService.saveEmployee(employee1);

		Employee ret = employeeService.getEmployee(1L);
		assertEquals(employee1, ret);

		employeeService.deleteEmployee(1L);
	}


	@Test
	public void testSaveEmployee() {
		employeeService.saveEmployee(employee1);

		Employee ret = employeeService.getEmployee(1L);
		assertEquals(employee1, ret);

		employeeService.deleteEmployee(1L);
	}

	@Test
	public void testDeleteEmployee() {
		employeeService.saveEmployee(employee1);

		employeeService.deleteEmployee(1L);

		Employee ret = employeeService.getEmployee(1L);
		assertEquals(null, ret);

	}

	@Test
	public void testUpdateEmployee() {
		employeeService.saveEmployee(employee1);

		Employee ret = employeeService.getEmployee(1L);
		assertEquals(employee1, ret);

		employee1.setName("Modified");
		employeeService.updateEmployee(employee1);

		Employee ret2 = employeeService.getEmployee(1L);
		assertEquals(employee1, ret2);

	}
}
