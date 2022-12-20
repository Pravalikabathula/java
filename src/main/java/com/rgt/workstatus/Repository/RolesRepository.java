package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>,QuerydslPredicateExecutor<Roles> {

	// Optional<Roles> findById(long rolesId);
	Roles findByRoleName(String name);

	Roles findOneByRoleId(Integer roleId);
	
	List<Roles>findByRoleNameContaining(String roleName);
}
