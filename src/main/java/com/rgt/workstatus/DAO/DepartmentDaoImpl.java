package com.rgt.workstatus.DAO;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.DepartmentEntity;
import com.rgt.workstatus.Entity.QDepartmentEntity;
import com.rgt.workstatus.Repository.DepartmentRepository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	private final static Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
		logger.debug("INVOKED : saveDepartment in Dao layer ");
		BooleanExpression dName = QDepartmentEntity.departmentEntity.departmentName
				.equalsIgnoreCase(departmentEntity.getDepartmentName());
		if (departmentRepository.findOne(dName) == null) {
			throw new IllegalArgumentException(
					"Department Name " + departmentEntity.getDepartmentName() + " has already exists !!");
		}
		return departmentRepository.saveAndFlush(departmentEntity);
	}

	@Override
	public Page<DepartmentEntity> getAllDepartment(Integer offset, Integer limit) {
		logger.debug("INVOKED : getAllDepartment in Dao layer ");
		Pageable pageable = PageRequest.of(--offset, limit);
		return departmentRepository.findAll(pageable);
	}

	@Override
	public DepartmentEntity getDepartmentById(Integer departmentId) {
		logger.debug("INVOKED : getDepartmentById in Dao layer ");

		Optional<DepartmentEntity> findById = departmentRepository.findById(departmentId);
		if (findById.isEmpty()) {
			throw new IllegalArgumentException(
					"Given record " + departmentId + " is not available Please try different one !!");
		}
		if (findById.isPresent()) {
			DepartmentEntity departmentEntity = findById.get();
			return departmentEntity;
		}
		return null;
	}

	@Override
	public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity) {
		logger.debug("INVOKED : updateDepartment in Dao layer ");
		DepartmentEntity update = departmentRepository.findById(departmentEntity.getDepartmentId())
				.orElseThrow(() -> new IllegalArgumentException("Department details are not available !!"));
		if (departmentEntity.getDepartmentName() != null && departmentEntity.getDepartmentName().trim().length() > 0) {
			if (update.getDepartmentName().equalsIgnoreCase(departmentEntity.getDepartmentName())) {
				BooleanExpression dName = QDepartmentEntity.departmentEntity.departmentName
						.equalsIgnoreCase(departmentEntity.getDepartmentName());
				if (departmentRepository.findOne(dName) == null) {
					throw new IllegalArgumentException(
							"Department Name " + departmentEntity.getDepartmentName() + " already exists with us !!");
				} else {
					update.setDepartmentName(departmentEntity.getDepartmentName());
				}
			}
		}
		update.setStatus(departmentEntity.getStatus());
		return departmentRepository.save(update);
	}

	@Override
	public List<DepartmentEntity> findByDepartmentName(String search) {
		if (search == null) {
			departmentRepository.findAll();
		}
		return departmentRepository.findByDepartmentNameContaining(search);
	}

//for delete method purpose created
	@Override
	public DepartmentEntity updateStatus(DepartmentEntity departmentEntity) {
		DepartmentEntity statusUpdate = departmentRepository.findById(departmentEntity.getDepartmentId()).orElseThrow(
				() -> new IllegalArgumentException("Department Details already deleted or not avaliable !!"));
		if (statusUpdate.getStatus() == false) {
			throw new IllegalArgumentException("Department details are already deleted , Please try another record !!");
		}
		statusUpdate.setStatus(departmentEntity.getStatus());
		return departmentRepository.save(statusUpdate);
	}
}
