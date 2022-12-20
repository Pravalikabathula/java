package com.rgt.workstatus.DAO;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Permission;
import com.rgt.workstatus.Entity.TeamMember;
import com.rgt.workstatus.Repository.PermissionRepository;

@Repository
public class PermissionDaoImpl implements PermissionDao {

	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PermissionDaoImpl.class);

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission savePermission(Permission permission) {
		logger.debug("INVOKED : savePermission in Dao layer ");
		Permission saveAndFlush = permissionRepository.saveAndFlush(permission);
		/*
		 * if (permissionEntity == null) { throw new
		 * IllegalArgumentException("Permission Details can not be null !!", null); } if
		 * (saveAndFlush.getTitle().isEmpty() || saveAndFlush.getTitle().length() == 0
		 * || saveAndFlush.getTitle().contains("0")) { throw new
		 * IllegalArgumentException("Please send proper Title '"
		 * +saveAndFlush.getTitle() + "' It's blank !!"); } if
		 * (saveAndFlush.getType().isEmpty() || saveAndFlush.getType().length() == 0) {
		 * throw new IllegalArgumentException("Please send proper Permission Type !!");
		 * }
		 */
		return saveAndFlush;
	}

//	@Override
//	public Page<Permission> getAllPermission(Integer offset, Integer limit) {
//		logger.debug("INVOKED : getAllPermission in Dao layer ");
//		Pageable pageable = PageRequest.of(--offset, limit);
//		Page<Permission> findAll = permissionRepository.findAll(pageable);
//		if (findAll.isEmpty()) {
//			throw new IllegalArgumentException("Hey list is completely empty we have nothing to retrieve !!");
//		}
//		return findAll;
//	}
  
	@Override
	public Page<Permission> getAllPermission(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return permissionRepository.findAll(pageable);
	}

	
	@Override
	public Permission getPermissionById(Integer permissionId) {
		logger.debug("INVOKED : getPermissionById in Dao layer " + permissionId);
		Optional<Permission> findById = permissionRepository.findById(permissionId);

		if (findById.isEmpty()) {
			throw new IllegalArgumentException("Permission Id " + permissionId + " does not exists with us !!");
		}
		if (findById.isPresent()) {
			Permission permission = findById.get();
			return permission;
		}
		return null;
	}

	@Override
	public Permission updatePermission(Permission permission) {
		logger.debug("INVOKED : updatePermission in Dao layer ");
		Permission update = permissionRepository.findById(permission.getPermissionId())
				.orElseThrow(() -> new IllegalArgumentException(
						"Permission Id " + permission.getPermissionId() + " can not be blank"));
		update.setTitle(permission.getTitle());
		update.setType(permission.getType());
		update.setDescription(permission.getDescription());
		update.setStatus(permission.getStatus());
		return permissionRepository.save(update);
	}

	@Override
	public Boolean deletePermission(Permission  permission) {
		logger.debug("INVOKED : deletePermission in Dao layer ");
		if (permission.getStatus()) {
			throw new IllegalArgumentException("Permission record already deleted , Please select another one !!");
		} else if (permission.getStatus() != null) {
			permissionRepository.deleteById(permission.getPermissionId());
			return true;
		}
		return false;
	}

}
