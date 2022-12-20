package com.rgt.workstatus.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDomain {

	private Integer departmentId;

	private String departmentName;

	private Boolean status = Boolean.FALSE;

	@JsonIgnoreProperties
	private Employee employee;

	private List<Team> team;

}