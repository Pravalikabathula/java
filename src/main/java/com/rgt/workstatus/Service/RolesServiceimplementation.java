package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.workstatus.DAO.RolesDao;
import com.rgt.workstatus.Domain.Roles;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class RolesServiceimplementation implements RolesService {

	@Autowired
	RolesDao rolesDao;

	private final static Logger logger = LoggerFactory.getLogger(RolesServiceimplementation.class);

	@Transactional
	public Roles createEmployeeRolesName(Roles roles) {
		logger.debug("createRolesname:INVOKED");

		Roles roless = toDomain(rolesDao.createEmployeeRolesName(toEntity(roles)));

		return roless;
	}

	@Transactional
	public Roles updateEmployeeRoles(Roles roles) {
		logger.debug("updateRoles:INVOKED");

		Roles roless = toDomain(rolesDao.updateEmployeeRoles(toEntity(roles)));

		return roless;
	}

	/*
	 * @Transactional public Boolean deleteRoles(Integer id) {
	 * com.rgt.workstatus.Entity.Roles employeeRolesById =
	 * rolesDao.getEmployeeRolesById(id); if (employeeRolesById != null) {
	 * rolesDao.deleteRoles(id); return true; } return false;
	 * 
	 * }
	 */

	@Transactional
	public Roles getEmployeeRolesById(Integer rolesId) {
		com.rgt.workstatus.Entity.Roles roles = rolesDao.getEmployeeRolesById(rolesId);
		if (roles.getStatus() == false) {
			throw new IllegalArgumentException("Roles detaile already deleted , Please try another one !!");
		}
		if (roles.getRoleId() == null) {
			throw new IllegalArgumentException("Roles detaile are not found , Please try another one !!");
		}
		Roles roless = toDomain(roles);
		return roless;
	}

	@Transactional
	public List<Roles> getAllRoles(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.Roles> lists = rolesDao.getAllRoles(offset, limit);
		List<Roles> list = new ArrayList<Roles>();
		lists.forEach(roles -> {
			Roles roles1 = toDomain(roles);
			list.add(roles1);
		});
		return list;

	}

	@Transactional
	public List<Roles> searchByRolename(String search) {
		List<com.rgt.workstatus.Entity.Roles> lists = rolesDao.searchByRolename(search);
		List<Roles> list = new ArrayList<Roles>();
		lists.forEach(roles -> {
			Roles roles1 = toDomain(roles);
			list.add(roles1);
		});
		return list;

	}

	@Transactional
	public Roles deleteStatus(Roles roles) {
		return toDomain(rolesDao.deleteStatus(toEntity(roles)));
	}
	
//
//	@Transactional
//	public List<Roles> getAll() {
//		List<com.rgt.workstatus.Entity.Roles> lists = rolesDao.getAll();
//		List<Roles> list = new ArrayList<Roles>();
//		lists.forEach(roles -> {
//			Roles roles1 = toDomain(roles);
//			list.add(roles1);
//		});
//		return list;
//
//	}

	public com.rgt.workstatus.Entity.Roles toEntity(Roles roles) {
		com.rgt.workstatus.Entity.Roles entity = null;
		if (roles != null) {
			entity = new com.rgt.workstatus.Entity.Roles();
			Objectcopier.copyObject(roles, entity);
		}
		return entity;
	}

	public Roles toDomain(com.rgt.workstatus.Entity.Roles roles) {
		Roles domain = null;
		if (roles != null) {
			domain = new Roles();
			Objectcopier.copyObject(roles, domain);
		}
		return domain;
	}
}