package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.TaskSubmission;

public interface TaskSubmissionService {
	TaskSubmission saveTaskSubmission(TaskSubmission taskSubmission);

	TaskSubmission updateTaskSubmission(TaskSubmission taskSubmission);

	TaskSubmission getTaskSubmissionById(Integer id);

	List<TaskSubmission> getAllTaskSubmission();

}
