package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.Roles;

public interface RolesDao {

	Roles createEmployeeRolesName(Roles roles);

	Roles updateEmployeeRoles(Roles roles);

	// Boolean deleteRoles(Integer id);
	Roles deleteStatus(Roles roles);

	Roles getEmployeeRolesById(Integer rolesId);

	Page<Roles> getAllRoles(Integer offset, Integer limit);

	List<Roles> searchByRolename(String search);
}
