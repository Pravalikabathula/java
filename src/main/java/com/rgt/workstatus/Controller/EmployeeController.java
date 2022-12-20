package com.rgt.workstatus.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Employee;
import com.rgt.workstatus.Domain.Roles;
import com.rgt.workstatus.Domain.Team;
import com.rgt.workstatus.Repository.EmployeeRepository;
import com.rgt.workstatus.Service.EmployeeService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	Response response = new Response();

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringtiTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringtiTrimmerEditor);
	}

//	@GetMapping("/getall")
//	public List<Employee> getAllEmployes(@RequestParam(value = "pageNumber", required = false) Integer offset,
//			@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "id") String sort) {
//		return employeeService.getAllEmployeesDetails(offset, limit, "id");
//
//	}
//	

	@GetMapping(value = "/all")
	public @ResponseBody List<Employee> getAllEmployes(
			@RequestParam(value = "pageNumber", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("getAllEmployes: INVOKED");
		return employeeService.getAllEmployeesDetails(offset, limit);

	}

	@PostMapping(value = "/create")
	public Response createEmployee(@RequestBody Employee employee) {// , BindingResult bindingResult) {
		try {

			/*
			 * if (bindingResult.hasErrors()) {
			 * response.setMsg("Enter Details With out White Spaces"); return response; }
			 * else {
			 */
			Employee emp = employeeService.saveEmployeeDetails(employee);
			if (emp != null) {
				response.setStatus(true);
				response.setMsg("Employee detailed saved !!");
			}
			// }
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping(value = "/getBy/{empId}")
	Employee getEmployeeById(@PathVariable Integer empId) {
		return employeeService.getById(empId);
	}

	@GetMapping(value = "/search/{search}")
	public List<Employee> getEmployeeBySearch(@PathVariable(value = "search") String search) {
		return employeeService.searchByEmployee(search);
	}

	@PutMapping(value = "/update")
	public @ResponseBody Response updateEmployee(@RequestBody Employee employee) {
		Employee byId = employeeService.getById(employee.getEmpId());
		try {
			if (byId != null) {
				employeeService.updateEmployeeProfile(employee);
				response.setStatus(true);
				response.setMsg("Employee details updated !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@DeleteMapping(value = "/delete/{empId}")
	public @ResponseBody Response deleteEmployee(@PathVariable("empId") Integer empId, HttpServletRequest request) {
		logger.debug("delete Employee Details:INVOKED");
		Boolean value = false;
		try {
			if ((empId != 0) && (empId != null)) {
				value = employeeService.deleteEmployeeById(empId);
				if (value == true) {
					response.setStatus(false);
					response.setMsg("Employee deleted successfully!.");
				}
			}
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			response.setStatus(false);
			response.setMsg(e.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			response.setStatus(false);
			response.setMsg(ex.getMessage());
		}
		return response;
	}

}