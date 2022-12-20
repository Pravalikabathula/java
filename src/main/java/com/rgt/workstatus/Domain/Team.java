package com.rgt.workstatus.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	private Integer teamId;
	private String teamLeadName;
	private boolean status;
	private DepartmentDomain departmentDomain;
	private Employee employee;

}
