package com.rgt.workstatus.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.TaskSubmission;
import com.rgt.workstatus.NotFound.TaskSubmissionNotFoundExcepton;
import com.rgt.workstatus.Repository.TaskSubmissionRepository;

@Repository
public class TaskSubmissionDaoImpl implements TaskSubmissionDaoInterface {
	@Autowired
	TaskSubmissionRepository taskSubmissionRepository;

	@Override
	public TaskSubmission saveTaskSubmission(TaskSubmission taskSubmission) {
		if (taskSubmission == null) {
			throw new IllegalArgumentException("TaskSubmission  cannot be null");
		}
		return taskSubmissionRepository.save(taskSubmission);
	}

	@Override
	public TaskSubmission updateTaskSubmission(TaskSubmission taskSubmission) {
		if (taskSubmission == null) {
			throw new IllegalArgumentException("TaskSubmission  cannot be null");
		}
		Integer tasks = taskSubmission.getTaskSubmissionId();
		if (tasks == null || (tasks != null && tasks == 0)) {
			throw new IllegalArgumentException("TaskSubmission id cannot be null or 0.");
		}

		return taskSubmissionRepository.saveAndFlush(taskSubmission);
	}

	@Override
	public TaskSubmission getTaskSubmissionById(Integer id) {
		Optional<TaskSubmission> task = taskSubmissionRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		} else {
			throw new TaskSubmissionNotFoundExcepton("TaskSubmission" + id + " Not Found ");
		}
	}

	@Override
	public List<TaskSubmission> getAllTaskSubmission() {

		return taskSubmissionRepository.findAll();
	}

}