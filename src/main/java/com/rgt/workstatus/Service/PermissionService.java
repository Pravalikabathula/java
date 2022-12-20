package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Permission;

public interface PermissionService {

	Permission savePermission(Permission permission);

	Permission updatePermission(Permission permission);

	Permission getPermissionById(Integer permissionId);

	List<Permission> getAllPermission(Integer offset, Integer limit);

	// Page<Permission> searchByPermissionName(String Search);

	Boolean deletePermission(Permission permission);
}
