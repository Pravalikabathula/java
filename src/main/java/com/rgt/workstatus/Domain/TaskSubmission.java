package com.rgt.workstatus.Domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSubmission {
	private Integer taskSubmissionId;
	private Date startDate;
	private Date endDate;
	private Boolean status;
	private String comments;
	private String remark;

	private Task task;
	private Team team;
	private TeamMember teammember;

}