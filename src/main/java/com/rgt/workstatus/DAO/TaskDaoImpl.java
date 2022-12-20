package com.rgt.workstatus.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.QTask;
import com.rgt.workstatus.Entity.Task;
import com.rgt.workstatus.NotFound.TaskNotFoundException;
import com.rgt.workstatus.Repository.TaskRepository;

@Repository
public class TaskDaoImpl implements TaskDaoInterface {
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task saveTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		if (task.getTaskName() == null || task.getTaskName().trim().length() == 0) {
			throw new IllegalArgumentException("Task name cannot be null");
		}
		// BooleanExpression name =
		// QTask.task.taskName.equalsIgnoreCase(task.getTaskName());

		if (taskRepository.findByTaskName(task.getTaskName()) != null) {
			throw new IllegalArgumentException("task name " + task.getTaskName() + "exisist with us");
		}
		return taskRepository.saveAndFlush(task);
	}

	@Override
	public Task updateTask(Task task) {
		Task update = taskRepository.findBytaskId(task.getTaskId());
		if (task.getTaskName() == null) {
			throw new IllegalArgumentException("Task name cannot be null");
		}
		if (task.getTaskName() != null && task.getTaskName().trim().length() > 0) {
			if (update.getTaskName().equalsIgnoreCase(task.getTaskName())) {
				BooleanExpression name = QTask.task.taskName.equalsIgnoreCase(task.getTaskName());
				if (taskRepository.findOne(name) == null) {
					throw new IllegalArgumentException(
							"Task Name " + task.getTaskName() + " already exists with us !!");
				}
			} else {
				update.setTaskName(task.getTaskName());
			}
		}
		update.setStartDate(task.getStartDate());
		update.setEndDate(task.getEndDate());
		update.setStatus(task.getStatus());
		return taskRepository.save(update);
	}

//	@Override
//	public Task updateTaskss(Task task) {
//		Task update = taskRepository.findBytaskId(task.getTaskId());
//		if (task.getStartDate() == null && task.getEndDate() == null) {
//			throw new IllegalArgumentException("Task name cannot be null");
//		}
//
//		update.setStartDate(task.getStartDate());
//		update.setEndDate(task.getEndDate());
//		return taskRepository.save(update);
//	}

	@Override
	public Task deleteTaskStatus(Task task) {
		Task deleteStatus = taskRepository.findById(task.getTaskId()).orElseThrow(
				() -> new IllegalArgumentException("task detaile are  not avaliable please try another one..!!"));
		if (deleteStatus.getStatus() == false) {
			throw new IllegalArgumentException("task details are already deleted,please try another one...!!");
		}
		deleteStatus.setStatus(task.getStatus());
		return taskRepository.save(deleteStatus);
	}

	@Override
	public Task getTaskById(Integer id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		} else {
			throw new TaskNotFoundException("Task" + id + " Not Found ");
		}
	}

//	@Override
//	public List<Task> getAllTask() {
//		return taskRepository.findAll();
//	}

	@Override
	public Page<Task> getAllTask(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return taskRepository.findAll(pageable);
	}

	@Override
	public List<Task> searchByTaskName(String Search) {

		return taskRepository.findByTaskNameContaining(Search);
	}

}
