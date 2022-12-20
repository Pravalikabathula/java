package com.rgt.workstatus.Util;

public class Response {

	private String msg;
	private Boolean status;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String msg, Boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}

}
