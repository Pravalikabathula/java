package com.rgt.workstatus.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.QRoles;
import com.rgt.workstatus.Entity.Roles;
import com.rgt.workstatus.NotFound.RolesNotFoundException;
import com.rgt.workstatus.Repository.RolesRepository;

@Repository
public class RolesDaoImplementation implements RolesDao {

	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public Roles createEmployeeRolesName(Roles roles) {

		if (roles == null) {
			throw new IllegalArgumentException("Roles cannot be null");

		}

		if (roles.getRoleName() == null || roles.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("EmployeeRolesName cannot be null");

		}

		Roles roleName = rolesRepository.findByRoleName(roles.getRoleName());
		
		if ( roleName != null) {
			throw new IllegalArgumentException("roles name " + roles.getRoleName() + " already exisist with us");
		}
		return rolesRepository.save(roles);
	}

	@Override
	public Roles updateEmployeeRoles(Roles roles) {
		Roles update = rolesRepository.findOneByRoleId(roles.getRoleId());
		if (roles.getRoleName() == null) {
			throw new IllegalArgumentException("Roles name cannot be null");
		}
		if (roles.getRoleName() != null && roles.getRoleName().trim().length() > 0) {
			if (update.getRoleName().equalsIgnoreCase(roles.getRoleName())) {
				BooleanExpression role = QRoles.roles.roleName.equalsIgnoreCase(roles.getRoleName());
				if (rolesRepository.findOne(role) == null) {
					throw new IllegalArgumentException(
							"Roles Name " + roles.getEmpById() + " already exists with us !!");
				}
			} else {
				update.setEmpById(roles.getEmpById());
			}
		}
		update.setRoleName(roles.getRoleName());
		update.setStatus(roles.getStatus());
		return rolesRepository.save(update);
	}

//
//	@Override
//	public Roles updateEmployeeRoles(Roles roles) {
//		Roles update = rolesRepository.findById(roles.getRoleId()).orElseThrow(
//				() -> new IllegalArgumentException("Roles detailed are not availbale , Please try another one !!"));
//		if (roles.getRoleName() == null) {
//			throw new IllegalArgumentException("Roles name cannot be null");
//		}
//		/*
//		 * if (roles.getRoleName() != null && roles.getRoleName().trim().length() > 0) {
//		 * if (update.getRoleName().equalsIgnoreCase(roles.getRoleName())) {
//		 * BooleanExpression role =
//		 * QRoles.roles.roleName.equalsIgnoreCase(roles.getRoleName()); if
//		 * (rolesRepository.findOne(role) != null) { throw new
//		 * IllegalArgumentException("roles name " + roles.getRoleName() +
//		 * "already exisist with us"); } } else {
//		 * update.setRoleName(roles.getRoleName()); } }
//		 */
//		update.setRoleName(roles.getRoleName());
//		update.setStatus(roles.getStatus());
//		return rolesRepository.save(update);
//	}

	@Override
	public Page<Roles> getAllRoles(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return rolesRepository.findAll(pageable);
	}

	@Override
	public List<Roles> searchByRolename(String search) {

		return rolesRepository.findByRoleNameContaining(search);
	}

	/*
	 * @Override public Boolean deleteRoles(Integer rolesId) { if (rolesId != null)
	 * { rolesRepository.deleteById(rolesId); return true; } return false; }
	 */

	@Override
	public Roles getEmployeeRolesById(Integer rolesId) {
		Roles roles = rolesRepository.findById(rolesId).orElseThrow(
				() -> new IllegalArgumentException("Roles detaile are not found , Please try another one !!"));
		return roles;
	}

	@Override
	public Roles deleteStatus(Roles roles) {
		Roles deleteStatus = rolesRepository.findById(roles.getRoleId()).orElseThrow(
				() -> new IllegalArgumentException("Roles detaile are already deleted or not avaliable..!!"));
		if (deleteStatus.getStatus() == false) {
			throw new IllegalArgumentException("Roles details are already deleted,please try another one...!!");
		}
		deleteStatus.setStatus(roles.getStatus());
		return rolesRepository.save(deleteStatus);
	}

}
