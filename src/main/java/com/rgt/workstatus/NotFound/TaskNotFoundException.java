package com.rgt.workstatus.NotFound;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String msg) {
		super(msg);
	}

}
