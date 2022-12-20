package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.Task;

public interface TaskDaoInterface {

	Task saveTask(Task task);

	Task updateTask(Task task);

	Task getTaskById(Integer id);

	Page<Task> getAllTask(Integer offset, Integer limit);

	List<Task> searchByTaskName(String Search);

	Task deleteTaskStatus(Task task);

	//Task updateTaskss(Task task);

	// List <Task>getAllTask();

}
