package com.rgt.workstatus.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.Employee;
import com.rgt.workstatus.Entity.QEmployee;
import com.rgt.workstatus.Repository.EmployeeRepository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	EmployeeRepository employeeRepository;
	private final static Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Override
	//public Page<Employee> getEmployeesBySort(Integer offset, Integer limit, String Sortby) {
	public Page<Employee> getEmployeesBySort(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").ascending());

		return employeeRepository.findAll(pageable);
	}

	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		logger.debug("Save Employee in Dao Details:INVOKED");
		BooleanExpression booleanExpression = QEmployee.employee.username.equalsIgnoreCase(employee.getUsername());
		/*
		 * if (employeeRepo.findOne(booleanExpression) == null) { throw new
		 * IllegalArgumentException("Entered UserName " + employee.getUsername() +
		 * " exist with us."); }
		 */
		return employeeRepository.save(employee);

	}

	@Override
	public Employee updateEmployeeProfile(Employee employee) {
		Employee update = employeeRepository.findById(employee.getEmpId()).orElseThrow();
		/*
		 * if (update.getUsername() != null && update.getUsername().trim().length() > 0)
		 * { if (update.getUsername().equalsIgnoreCase(employee.getUsername())) {
		 * BooleanExpression name =
		 * QEmployee.employee.username.equalsIgnoreCase(employee.getUsername()); if
		 * (employeeRepo.findOne(name) != null) { throw new IllegalArgumentException(
		 * "UserName " + employee.getUsername() + " already exists with us !!"); } else
		 * {
		 */
		update.setFirstName(employee.getFirstName());
		update.setMiddleName(employee.getMiddleName());
		update.setLastName(employee.getLastName());
		update.setUsername(employee.getUsername());
		update.setPassword(employee.getPassword());
		update.setEmailId(employee.getEmailId());
		update.setGender(employee.getGender());
		update.setMobilenumber(employee.getMobilenumber());
		update.setActive(employee.getActive());
		update.setJobtitle(employee.getJobtitle());
//
//				}
//			}
//		}
		return employeeRepository.saveAndFlush(update);
	}

	public Boolean deleteById(Integer empId) {
		logger.debug("deleteEmployee:Invoked with EmployeeId:  " + empId);
		// Employee employee = employeeRepo .findOne(QEmployee.employee.acitve);
		Employee employee = employeeRepository.findByEmpId(empId);
		if (employee.getActive() == true) {
			employee.setActive(false);
			employee = employeeRepository.saveAndFlush(employee);
			return true;
		}
		throw new IllegalArgumentException("Employee Already Deleted!");
	}

	@Override
	public List<Employee> searchByEmployee(String Search) {
		return employeeRepository.findByUsernameContaining(Search);
	}

	@Override
	public Employee getById(Integer employeeId) {
		return employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Not Find"));
	}

}