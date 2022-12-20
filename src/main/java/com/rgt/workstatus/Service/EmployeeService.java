package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployeesDetails(Integer offset, Integer limit);

	//List<Employee> getAllEmployeesDetails(Integer offset, Integer limit, String Sortby);

	// public Employee getByEmpId(Integer employeeId);

	Employee updateEmployeeProfile(Employee employee);

	Employee saveEmployeeDetails(Employee employee);

	Boolean deleteEmployeeById(Integer EmpId);

	List<Employee> searchByEmployee(String Search);

	Employee getById(Integer employeeId);

}
