package com.rgt.workstatus.NotFound;

public class TaskSubmissionNotFoundExcepton extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskSubmissionNotFoundExcepton() {
		super();
	}

	public TaskSubmissionNotFoundExcepton(String msg) {
		super(msg);
	}

}