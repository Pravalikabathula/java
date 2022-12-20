package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Roles;

public interface RolesService {

	Roles createEmployeeRolesName(Roles roles);

	Roles updateEmployeeRoles(Roles roles);

	// Boolean deleteRoles(Integer id);
	Roles deleteStatus(Roles roles);

	Roles getEmployeeRolesById(Integer rolesId);

	List<Roles> getAllRoles(Integer offset, Integer limit);

	List<Roles> searchByRolename(String search);
}
