package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, QuerydslPredicateExecutor<Employee> {

	//Employee findByEmployeeName(String name);

	Employee findByEmpId(Integer empId);

	List<Employee> findByUsernameContaining(String username);

}
