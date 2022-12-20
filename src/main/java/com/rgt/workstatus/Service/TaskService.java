package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Task;

public interface TaskService {
	Task saveTask(Task task);

	Task updateTask(Task task);

	Task getTaskById(Integer id);

	List<Task> getAllTask(Integer offset, Integer limit);

	List<Task> searchByTaskName(String Search);

	Task deleteTaskStatus(Task task);

	//Task updateTaskss(Task task);

}
