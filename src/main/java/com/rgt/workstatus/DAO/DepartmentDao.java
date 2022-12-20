package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.DepartmentEntity;

public interface DepartmentDao {

	DepartmentEntity saveDepartment(DepartmentEntity departmentEntity);

	Page<DepartmentEntity> getAllDepartment(Integer offset, Integer limit);

	DepartmentEntity getDepartmentById(Integer departmentId);

	DepartmentEntity updateDepartment(DepartmentEntity departmentEntity);

	List<DepartmentEntity> findByDepartmentName(String search);

	// Page<DepartmentEntity> getAll(Integer offset, Integer limit , String search);
	DepartmentEntity updateStatus(DepartmentEntity departmentEntity);
}