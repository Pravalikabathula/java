package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>, QuerydslPredicateExecutor<Task> {

	Task findBytaskId(Integer taskid);

	Task findByTaskName(String taskName);

	List<Task> findByTaskNameContaining(String taskName);

}