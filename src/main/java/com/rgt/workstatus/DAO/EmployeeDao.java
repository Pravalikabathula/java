package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Employee;

@Repository
public interface EmployeeDao {

	Page<Employee> getEmployeesBySort(Integer offset, Integer limit);

	//Page<Employee> getEmployeesBySort(Integer offset, Integer limit, String Sortby);

	// public Page<Employee> search(Integer offset, Integer limit, String Search);

	Employee saveEmployeeDetails(Employee employee);

	Employee updateEmployeeProfile(Employee employee);

	Boolean deleteById(Integer EmpId);

	List<Employee> searchByEmployee(String Search);

	Employee getById(Integer employee);

}
