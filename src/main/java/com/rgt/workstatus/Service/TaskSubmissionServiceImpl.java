package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.workstatus.DAO.TaskSubmissionDaoInterface;
import com.rgt.workstatus.Domain.TaskSubmission;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class TaskSubmissionServiceImpl implements TaskSubmissionService {
	@Autowired
	TaskSubmissionDaoInterface taskSubmissionDao;

	@Transactional
	public TaskSubmission saveTaskSubmission(TaskSubmission taskSubmission) {
		TaskSubmission taskSubmissions = toDomain(taskSubmissionDao.saveTaskSubmission(toEntity(taskSubmission)));
		return taskSubmissions;
	}

	@Transactional
	public TaskSubmission updateTaskSubmission(TaskSubmission taskSubmission) {
		TaskSubmission taskSubmissions = toDomain(taskSubmissionDao.updateTaskSubmission(toEntity(taskSubmission)));
		return taskSubmissions;
	}

	@Transactional
	public TaskSubmission getTaskSubmissionById(Integer id) {
		com.rgt.workstatus.Entity.TaskSubmission tasks = taskSubmissionDao.getTaskSubmissionById(id);
		// List<Task> lists = new ArrayList<Task>();
		TaskSubmission task = toDomain(tasks);
		return task;
	}

	@Transactional
	public List<TaskSubmission> getAllTaskSubmission() {
		{
			List<com.rgt.workstatus.Entity.TaskSubmission> lists = taskSubmissionDao.getAllTaskSubmission();
			List<TaskSubmission> listss = new ArrayList<TaskSubmission>();
			lists.forEach(tasks -> {
				TaskSubmission task = toDomain(tasks);
				listss.add(task);
			});
			return listss;
		}
	}

	// TO DOMAIN
	public com.rgt.workstatus.Entity.TaskSubmission toEntity(TaskSubmission taskSubmission) {
		com.rgt.workstatus.Entity.TaskSubmission entity = null;
		if (taskSubmission != null) {
			entity = new com.rgt.workstatus.Entity.TaskSubmission();
			Objectcopier.copyObject(taskSubmission, entity);
		}
		return entity;
	}

	// TO ENTITY
	public TaskSubmission toDomain(com.rgt.workstatus.Entity.TaskSubmission taskSubmission) {
		TaskSubmission domain = null;
		if (taskSubmission != null) {
			domain = new TaskSubmission();
			Objectcopier.copyObject(taskSubmission, domain);
		}
		return domain;
	}

}
