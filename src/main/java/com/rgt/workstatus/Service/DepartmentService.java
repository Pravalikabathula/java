package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.DepartmentDomain;

public interface DepartmentService {

	DepartmentDomain saveDepartment(DepartmentDomain departmentDomain);

	List<DepartmentDomain> getAllDepartment(Integer offset, Integer limit);

	DepartmentDomain getDepartmentById(Integer departmentId);

	DepartmentDomain updateDepartment(DepartmentDomain departmentDomain);

	DepartmentDomain updateStatus(DepartmentDomain departmentDomain);

	List<DepartmentDomain> findByDepartmentName(String search);
}