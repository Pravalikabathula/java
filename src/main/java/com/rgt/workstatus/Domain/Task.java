package com.rgt.workstatus.Domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	private Integer taskId;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private Boolean status;

	private Employee employee;
	private Team team;

}
