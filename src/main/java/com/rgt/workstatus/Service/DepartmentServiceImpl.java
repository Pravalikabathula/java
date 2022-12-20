package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rgt.workstatus.DAO.DepartmentDao;
import com.rgt.workstatus.Domain.DepartmentDomain;
import com.rgt.workstatus.Entity.DepartmentEntity;
import com.rgt.workstatus.Entity.Team;
import com.rgt.workstatus.Entity.TeamMember;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public DepartmentDomain saveDepartment(DepartmentDomain departmentDomain) {
		logger.debug("invoked : saveDepartment in service layer");
//		DepartmentEntity dept = toEntity(departmentDomain);
//		List<Team> tem = dept.getTeam();
//		for (Team d : tem) {
//			d.setDepartmentEntity(dept);
//		}
//		DepartmentDomain departments = toDomain(departmentDao.saveDepartment(dept));
//		return departments;
//	}
		DepartmentDomain domain = toDomain(departmentDao.saveDepartment(toEntity(departmentDomain)));
		if (domain.getDepartmentName() == null) {
			throw new IllegalArgumentException(
					"Department Name " + departmentDomain.getDepartmentName() + " has already exists !!");
		}
		return domain;
	}

	@Override
	public List<DepartmentDomain> getAllDepartment(Integer pageNumber, Integer pageSize) {
		logger.debug("invoked : getAllDepartment in service layer");
		Page<DepartmentEntity> page = departmentDao.getAllDepartment(pageNumber, pageSize);
		List<DepartmentDomain> list = new ArrayList<DepartmentDomain>();
		page.forEach(departmentDetails -> {
			DepartmentDomain domain = toDomain(departmentDetails);
			list.add(domain);
		});
		return list;
	}

	@Override
	public DepartmentDomain getDepartmentById(Integer departmentId) {
		logger.debug("invoked : getDepartmentById in service layer");
		return toDomain(departmentDao.getDepartmentById(departmentId));
	}

	@Override
	public DepartmentDomain updateDepartment(DepartmentDomain departmentDomain) {
		logger.debug("invoked : updateDepartment in service layer");
		if (departmentDomain.getDepartmentName().isEmpty()) {
			throw new IllegalArgumentException("Department Name can not be blank !!");
		}
		return toDomain(departmentDao.updateDepartment(toEntity(departmentDomain)));
	}

	/*
	 * @Override public Boolean disableDepartment(Integer departmentId) {
	 * logger.debug("invoked : disableDepartment in service layer"); return
	 * departmentDao.disableDepartment(departmentId); }
	 */

	// search
	@Override
	public List<DepartmentDomain> findByDepartmentName(String search) {
		List<DepartmentEntity> entity = departmentDao.findByDepartmentName(search);
		List<DepartmentDomain> list = new ArrayList<DepartmentDomain>();
		entity.forEach(departmentDetails -> {
			DepartmentDomain domain = toDomain(departmentDetails);
			list.add(domain);
		});
		return list;
	}

	// object inject domain to entity and entity to domain
	public DepartmentEntity toEntity(DepartmentDomain departmentDomain) {
		DepartmentEntity entity = null;
		if (departmentDomain != null) {
			entity = new DepartmentEntity();
			Objectcopier.copyObject(departmentDomain, entity);
		}
		return entity;
	}

	public DepartmentDomain toDomain(DepartmentEntity departmentEntity) {
		DepartmentDomain domain = null;
		if (departmentEntity != null) {
			domain = new DepartmentDomain();
			Objectcopier.copyObject(departmentEntity, domain);
		}
		return domain;
	}

	@Override
	public DepartmentDomain updateStatus(DepartmentDomain departmentDomain) {
		return toDomain(departmentDao.updateStatus(toEntity(departmentDomain)));
	}
}