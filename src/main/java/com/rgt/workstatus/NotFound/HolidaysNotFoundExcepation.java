package com.rgt.workstatus.NotFound;

public class HolidaysNotFoundExcepation extends RuntimeException {

	public HolidaysNotFoundExcepation() {
		super();
	}

	public HolidaysNotFoundExcepation(String msg) {
		super(msg);
	}
}
