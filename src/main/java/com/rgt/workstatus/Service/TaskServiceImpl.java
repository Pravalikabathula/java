package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rgt.workstatus.DAO.TaskDaoInterface;
import com.rgt.workstatus.Domain.Task;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDaoInterface taskDaoInterface;

	@Transactional
	public Task saveTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		if (task.getTaskName() == null || task.getTaskName().trim().length() == 0) {
			throw new IllegalArgumentException("Task name cannot be null");
		}
		Task tasks = toDomain(taskDaoInterface.saveTask(toEntity(task)));
		return tasks;
		// return toDomain(taskDaoInterface.saveTask(toEntity(task)));
	}

	@Transactional
	public Task updateTask(Task tasks) {
		Task taskss = toDomain(taskDaoInterface.updateTask(toEntity(tasks)));
		return taskss;
	}

	@Transactional
	public Task getTaskById(Integer id) {
		com.rgt.workstatus.Entity.Task tasks = taskDaoInterface.getTaskById(id);
		// List<Task> lists = new ArrayList<Task>();
		// Task task = toDomain(tasks);
		if (tasks.getStatus()== false) {
			throw new IllegalArgumentException("Given record already deleted please try another one");
		}
		if (tasks.getTaskId() == null) {
			throw new IllegalArgumentException("Task details not found please try another one");
		}
		Task taskss = toDomain(tasks);
		return taskss;
	}

	@Transactional
	public List<Task> getAllTask(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.Task> page = taskDaoInterface.getAllTask(offset, limit);
		List<Task> list = new ArrayList<Task>();
		page.forEach(taskDetails -> {
			Task task = toDomain(taskDetails);
			list.add(task);
		});
		return list;
	}

	// TO DOMAIN
	public com.rgt.workstatus.Entity.Task toEntity(Task task) {
		com.rgt.workstatus.Entity.Task entity = null;
		if (task != null) {
			entity = new com.rgt.workstatus.Entity.Task();
			Objectcopier.copyObject(task, entity);
		}
		return entity;
	}

	// TO ENTITY
	public Task toDomain(com.rgt.workstatus.Entity.Task tasks) {
		Task domain = null;
		if (tasks != null) {
			domain = new Task();
			Objectcopier.copyObject(tasks, domain);
		}
		return domain;
	}

	@Override
	public List<Task> searchByTaskName(String Search) {
		List<com.rgt.workstatus.Entity.Task> lists = taskDaoInterface.searchByTaskName(Search);
		List<Task> listss = new ArrayList<Task>();
		lists.forEach(tasks -> {
			Task task = toDomain(tasks);
			listss.add(task);
		});
		return listss;
	}

	@Override
	public Task deleteTaskStatus(Task task) {

		return toDomain(taskDaoInterface.deleteTaskStatus(toEntity(task)));
	}

//	@Override
//	public Task updateTaskss(Task task) {
//		Task taskss = toDomain(taskDaoInterface.updateTaskss(toEntity(task)));
//		return taskss;
//
//	}

//	@Transactional
//	public List<Task> getAllTask() 
//	{	
//		List<com.employeemanagmentdata.entity.Task> lists = taskDaoInterface.getAllTask();
//		List<Task> listss = new ArrayList<Task>();
//		lists.forEach(tasks->
//		{
//			Task task = toDomain(tasks);
//			listss.add(task);
//		});	
//		return listss;
//		
//	}

}