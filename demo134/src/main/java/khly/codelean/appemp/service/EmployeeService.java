package khly.codelean.appemp.service;

import java.util.List;

import khly.codelean.appemp.entity.Employee;


public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
}
