package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.workstatus.DAO.EmployeeDao;
import com.rgt.workstatus.Domain.Employee;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;

	private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	//public List<Employee> getAllEmployeesDetails(Integer offset, Integer limit, String Sortby) {
	public List<Employee> getAllEmployeesDetails(Integer offset, Integer limit) {

		logger.debug("Getall employee details in service");
		try {
			Page<com.rgt.workstatus.Entity.Employee> page = employeeDao.getEmployeesBySort(--offset, limit);
			List<Employee> list = new ArrayList<Employee>();
			page.forEach(employeeDetails -> {
				Employee employee = toDomain(employeeDetails);
				list.add(employee);
			});

			if (list.isEmpty())
				throw new IllegalArgumentException("List is Empty !  Nothing will be there to return! ");

			return list;

		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Something went wrong in  service Layer while featch all customers" + e.getMessage());
		}

	}

	@Transactional
	public Employee saveEmployeeDetails(Employee employee) {
		logger.info("Inside saveEmployeeDetails:INVIKED ");
		try {
			if (employee.getUsername().isEmpty() || employee.getUsername().trim().length() == 0) {
				throw new IllegalArgumentException("Enter the Detalind properly ");
			}
			if (employee.getFirstName().isEmpty() || employee.getFirstName().trim().length() == 0) {
				throw new IllegalArgumentException("Enter Name ");
			}
			if (employee.getEmailId().isEmpty() || employee.getEmailId().trim().length() == 0) {
				throw new IllegalArgumentException("Enter EmailId ");
			}
			return toDomain(employeeDao.saveEmployeeDetails(toEntity(employee)));

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Given Customer is Null" + e.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@Transactional
	public Employee updateEmployeeProfile(Employee employee) {
		logger.info("update Employee in service  Profile:INVOKED ");

		try {
			if (employee == null) {
				throw new IllegalArgumentException("UserName cannot be null.");
			}
			if (employee.getUsername() == null || employee.getUsername().trim().length() == 0) {
				throw new IllegalArgumentException("UserName cannot be null or empty.");
			}
			// Integer empId = employee.getEmpId();
			if (employee.getEmpId() == null || (employee.getEmpId() != null && employee.getEmpId() == 0)) {
				throw new IllegalArgumentException("Employee id cannot be null or 0.");
			}

			return toDomain(employeeDao.updateEmployeeProfile(toEntity(employee)));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Given Customer is Null" + e.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@Transactional
	public Boolean deleteEmployeeById(Integer EmpId) {
		logger.debug("deleteEmployeeDetails in service");
		try {
			if (EmpId != null) {
				employeeDao.deleteById(EmpId);
				return true;
			}
			return false;

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Given Employee id is Null" + e.getMessage());
		} catch (java.util.NoSuchElementException e) {
			throw new IllegalArgumentException("Given Customer id  doesn't Exist in DataBase" + e.getMessage());
		}
	}

	public Employee toDomain(com.rgt.workstatus.Entity.Employee employeeEntity) {
		Employee domain = null;
		if (employeeEntity != null) {
			domain = new Employee();
			Objectcopier.copyObject(employeeEntity, domain);
		}
		return domain;
	}

	public com.rgt.workstatus.Entity.Employee toEntity(Employee employee) {
		com.rgt.workstatus.Entity.Employee entity = null;

		if (employee != null) {
			entity = new com.rgt.workstatus.Entity.Employee();
			Objectcopier.copyObject(employee, entity);
		}
		return entity;
	}

	@Transactional
	public List<Employee> searchByEmployee(String Search) {
		List<com.rgt.workstatus.Entity.Employee> lists = employeeDao.searchByEmployee(Search);
		List<Employee> list = new ArrayList<Employee>();
		lists.forEach(employee -> {
			Employee employee1 = toDomain(employee);
			list.add(employee1);
		});
		return list;
	}

	@Transactional
	public Employee getById(Integer employeeId) {
		return toDomain(employeeDao.getById(employeeId));
	}

}