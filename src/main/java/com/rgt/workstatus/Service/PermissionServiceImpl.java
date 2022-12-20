package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.rgt.workstatus.DAO.PermissionDao;
import com.rgt.workstatus.Domain.Permission;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	private final static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Transactional
	public Permission savePermission(Permission permission) {
		logger.debug("invoked : savePermission in service layer");
		Permission domain = toDomain(permissionDao.savePermission(toEntity(permission)));
		if (permission.getTitle().isEmpty() || permission.getTitle().length() == 0
				|| permission.getTitle().contains("0")) {
			throw new IllegalArgumentException(
					"Please send proper Title '" + permission.getTitle() + "' It's blank !!");
		}
		if (permission.getType().isEmpty() && permission.getType().trim().length() == 0
				&& permission.getType().isBlank()) {
			throw new IllegalArgumentException("Please send proper Permission Type !!");
		}
		return domain;
	}

//	@Transactional
//	public List<Permission> getAllPermission(Integer pageNumber, Integer pageSize) {
//		logger.debug("Invoked : getAllPermission in service layer");
//		Page<Permission> page = permissionDao.getAllPermission(pageNumber, pageSize);
//		List<Permission> list = new ArrayList<Permission>();
//		page.forEach(permissionDetails -> {
//			Permission domain = toDomain(permissionDetails);
//			list.add(domain);
//		});
//		return list;
//	}

	@Transactional
	public List<Permission> getAllPermission(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.Permission> permissionss = permissionDao.getAllPermission(offset, limit);
		List<Permission> permission = new ArrayList<Permission>();
		permissionss.forEach(permissions -> {
			Permission permissions1 = toDomain(permissions);
			permission.add(permissions1);
		});
		return permission;
	}

	@Transactional
	public Permission getPermissionById(Integer permissionId) {
		logger.debug("Invoked : getPermissionById in service layer");
		Permission domain = toDomain(permissionDao.getPermissionById(permissionId));
		if (domain.getStatus() == false) {
			throw new IllegalArgumentException("Given record " + permissionId + " is already deleted !!");
		}
		return domain;
	}

	@Transactional
	public Permission updatePermission(Permission permission) {
		logger.debug("Invoked : updatePermission in service layer");
		Integer id = permission.getPermissionId();
		Permission domain = toDomain(permissionDao.updatePermission(toEntity(permission)));
		if (id == null || (id != null && id == 0)) {
			throw new IllegalArgumentException("Permission Id can not be null or empty !!");
		}
		if (id != permission.getPermissionId()) {
			throw new IllegalArgumentException("Permission Id does not exists !!");
		}
		if (domain.getTitle().isEmpty() || domain.getTitle().length() == 0 || domain.getTitle().contains("0")) {
			throw new IllegalArgumentException("Please send proper Title '" + domain.getTitle() + "' It's blank !!");
		}
		return domain;
	}

	@Transactional
	public Boolean deletePermission(Permission permissionDomain) {
		logger.debug("Invoked : delete in service layer");
		Boolean deletePermission = permissionDao.deletePermission(toEntity(permissionDomain));
		if (deletePermission == null) {
			throw new IllegalArgumentException("Details are not available , Please give another details !!");
		}
		return deletePermission;
	}

	/*
	 * toDomain and toEntity are used for communication between entity and domain
	 * layers
	 */
	// Creating To Domain method
	@Validated
	public com.rgt.workstatus.Entity.Permission toEntity(Permission permission) {
		com.rgt.workstatus.Entity.Permission entity = null;
		if (permission != null) {
			entity = new com.rgt.workstatus.Entity.Permission();
			Objectcopier.copyObject(permission, entity);
		}
		return entity;
	}

	public Permission toDomain(com.rgt.workstatus.Entity.Permission permission) {
		Permission domain = null;
		if (permission != null) {
			domain = new Permission();
			Objectcopier.copyObject(permission, domain);
		}
		return domain;
	}

}
