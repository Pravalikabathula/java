package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rgt.workstatus.Entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer>, QuerydslPredicateExecutor<DepartmentEntity> {

	List<DepartmentEntity> findByDepartmentNameContaining(String departmentName);
}
