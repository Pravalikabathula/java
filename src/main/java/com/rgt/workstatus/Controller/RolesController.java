
package com.rgt.workstatus.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Roles;
import com.rgt.workstatus.Service.RolesService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;
	Response response = new Response();
	private final static Logger logger = LoggerFactory.getLogger(RolesController.class);

	@PostMapping(value = "/create")
	public Response addRoles(@RequestBody Roles roles) {
		logger.debug("addEmployeeRoles:INVOKED");
		Roles role = rolesService.createEmployeeRolesName(roles);
		try {
			if (role != null) {
				response.setStatus(true);
				response.setMsg("Roles details are saved successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		
		return response;
	}

	@GetMapping(value = "/all")
	public @ResponseBody List<Roles> getAllRoles(@RequestParam(value = "pageNumber", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("getAllRoles: INVOKED");
		return rolesService.getAllRoles(offset, limit);

	}

	@GetMapping(value = "/search/{search}")
	public List<Roles> getRolesBySearch(@PathVariable(value = "search") String search) {
		return rolesService.searchByRolename(search);
	}

	@GetMapping(value = "/getBy/{rolesId}")
	public Roles getRolesById(@PathVariable(value = "rolesId") Integer rolesId) {
		logger.debug("showRoles:INVOKED");
		return rolesService.getEmployeeRolesById(rolesId);
	}

	@PutMapping(value = "/update")
	public Response updateRoles(@Validated @RequestBody Roles roles) {
		logger.debug("updateEmployeeRoles:INVOKED");
		Roles role = rolesService.updateEmployeeRoles(roles);
		try {

			if (role != null) {
				response.setStatus(true);
				response.setMsg("Roles details are updated successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}
//
//	@RequestMapping(value = "/delete/{rolesId}", method = RequestMethod.DELETE)
//	public Response deleteRoles(@PathVariable("rolesId") Integer rolesId) {
//		logger.debug("deleteEmployeeRoles:INVOKED");
//		Roles roles = null;
//		if (rolesId != 0) {
//			try {
//				roles = rolesService.getEmployeeRolesById(rolesId);
//				roles.setStatus(false);
//				rolesService.updateEmployeeRoles(roles);
//				if (roles != null) {
//					response.setStatus(true);
//					response.setMsg("Role details deleted !!");
//				}
//
//		} catch (IllegalArgumentException e) {
//			response.setMsg(e.getMessage());
//			response.setStatus(true);
//		}
//		}
//		return response;
//	}

	@DeleteMapping(value = "/delete/{rolesId}")
	public Response deleteEmployeeRoles(@PathVariable("rolesId") Integer rolesId) {
		logger.debug("deleteEmployeeRoles:INVOKED");
		if (rolesId != 0) {
			Roles roles = null;
			roles = rolesService.getEmployeeRolesById(rolesId);
			roles.setStatus(false);
			rolesService.deleteStatus(roles);
			if (roles != null) {
				response.setStatus(true);
				response.setMsg("Role details deleted !!");
			}
		}
		return response;
	}
}