package com.rgt.workstatus.DAO;

import java.util.List;

import com.rgt.workstatus.Entity.TaskSubmission;

public interface TaskSubmissionDaoInterface {
	TaskSubmission saveTaskSubmission(TaskSubmission taskSubmission);

	TaskSubmission updateTaskSubmission(TaskSubmission taskSubmission);

	TaskSubmission getTaskSubmissionById(Integer id);

	List<TaskSubmission> getAllTaskSubmission();
}
