package com.rgt.workstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.TaskSubmission;

@Repository
public interface TaskSubmissionRepository extends JpaRepository<TaskSubmission, Integer>, QuerydslPredicateExecutor<TaskSubmission> {
	TaskSubmission findOneByTaskSubmissionId(Integer taskSubmissionId);
	
}
