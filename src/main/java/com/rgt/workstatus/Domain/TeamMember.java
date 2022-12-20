package com.rgt.workstatus.Domain;

import com.rgt.workstatus.Entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {

	private Integer teamMemberId;
	private String employeeName;
	private boolean status;
	private Team team;

}
