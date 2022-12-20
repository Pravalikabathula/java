package com.rgt.workstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>, QuerydslPredicateExecutor<Permission> {

	Permission findByPermissionId(Integer permissionId);

}
