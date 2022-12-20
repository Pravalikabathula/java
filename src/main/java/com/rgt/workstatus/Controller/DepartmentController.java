package com.rgt.workstatus.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.DepartmentDomain;
import com.rgt.workstatus.Service.DepartmentService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	Response response = new Response();

	@PostMapping(value = "/saved")
	Response saveDepartment(@RequestBody DepartmentDomain departmentDomain) {
		logger.debug("Invoked : saveDepartment in controller");
		DepartmentDomain saveDepartment = departmentService.saveDepartment(departmentDomain);
		try {
			if (saveDepartment != null) {
				response.setStatus(true);
				response.setMsg("Department details saved !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping(value = "/departments")
	List<DepartmentDomain> getAllDepartment(@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize) {
		logger.debug("Invoked : getAllDepartment in controller");
		List<DepartmentDomain> allDepartment = departmentService.getAllDepartment(pageNumber, pageSize);
		return allDepartment;
	}

	/*
	 * @GetMapping(value = "/byId/{departmentId}") DepartmentDomain
	 * getDepartmentById(@PathVariable("departmentId") Integer departmentId) {
	 * return departmentService.getDepartmentById(departmentId); }
	 */

	@PutMapping(value = "/update")
	Response updateDepartment(@RequestBody DepartmentDomain departmentEntity) {
		logger.debug("Invoked : updateDepartment in controller");
		try {
			DepartmentDomain updateDepartment = departmentService.updateDepartment(departmentEntity);
			if (updateDepartment != null) {
				response.setMsg("Department details updated !!");
				response.setStatus(true);
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@DeleteMapping(value = "/delete/{departmentId}")
	Response disableDepartment(@PathVariable("departmentId") Integer departmentId) {
		logger.debug("Invoked : disableDepartment in controller");
		if (departmentId != 0) {
			DepartmentDomain domain = null;
			domain = departmentService.getDepartmentById(departmentId);
			domain.setStatus(false);
			departmentService.updateStatus(domain);
			if (domain != null) {
				response.setStatus(true);
				response.setMsg("Department Details deleted");
			}
		}
		return response;
	}

	@GetMapping(value = "/search/{search}")
	List<DepartmentDomain> searchDepartment(@PathVariable("search") String deparmentName) {
		List<DepartmentDomain> findByDepartmentName = departmentService.findByDepartmentName(deparmentName);
		return findByDepartmentName;
	}
}
