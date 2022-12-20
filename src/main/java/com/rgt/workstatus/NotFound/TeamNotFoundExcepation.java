package com.rgt.workstatus.NotFound;

public class TeamNotFoundExcepation extends RuntimeException {

	public TeamNotFoundExcepation() {
		super();
	}

	public TeamNotFoundExcepation(String msg) {
		super(msg);
	}
}
