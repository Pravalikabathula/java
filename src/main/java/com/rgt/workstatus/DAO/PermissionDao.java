package com.rgt.workstatus.DAO;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.Permission;

public interface PermissionDao {

	Permission savePermission(Permission permission);

	Permission updatePermission(Permission permission);

	Permission getPermissionById(Integer permissionId);

	Page<Permission> getAllPermission(Integer offset, Integer limit);

	// Page<Permission> searchByPermissionName(String Search);

	Boolean deletePermission(Permission permission);
}
