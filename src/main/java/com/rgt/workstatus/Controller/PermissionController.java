package com.rgt.workstatus.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Permission;
import com.rgt.workstatus.Service.PermissionService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PermissionController.class);

	Response response = new Response();

	@PostMapping(value = "/createPermission")
	public Response savePermission(@Validated @RequestBody Permission permission) {
		logger.debug("Invoked : savePermission in controller");
		try {
			permissionService.savePermission(permission);
			response.setStatus(true);
			response.setMsg("Permission details added !!");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping(value = "/getPermission")
	public List<Permission> getAllPermission(@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize) {
		logger.debug("Invoked : getAllPermission in controller");
		List<Permission> allPermission = null;
		try {
			allPermission = permissionService.getAllPermission(pageNumber, pageSize);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return allPermission;
	}

	@PutMapping(value = "/update")
	public Response updatePermission(@RequestBody Permission permission) {
		logger.debug("Invoked : updatePermission in controller");
		try {
			permission = permissionService.updatePermission(permission);
			response.setStatus(true);
			response.setMsg("Permission Details updated !!");
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@DeleteMapping(value = "/deletePermission/{permissionId}")
	public Response deletePermission(@PathVariable("permissionId") Integer permissionId, HttpServletRequest request) {
		logger.debug("Invoked : deletePermission in controller" + permissionId);
		Permission domain = null;
		if (permissionId != 0) {
			try {
				domain = permissionService.getPermissionById(permissionId);
				domain.setStatus(false);
				permissionService.updatePermission(domain);
				if (domain != null) {
					response.setStatus(true);
					response.setMsg("Permission Details deleted");
				}
			} catch (IllegalArgumentException e) {
				response.setMsg(e.getMessage());
				response.setStatus(false);
			}

		}
		return response;
	}

}
