package com.rgt.workstatus.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author PravalikaBathula
 *
 */
@Entity
@Table(name = "ROLES")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private Integer roleId;
	@Column(name = "ROLE_NAME")
	private String roleName;
	@Column(name = "EMPLOYEE_BY_ID")
	private String empById;
	@Column(name = "ROLE_STATUS")
	private Boolean status;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmpById() {
		return empById;
	}

	public void setEmpById(String empById) {
		this.empById = empById;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Roles(Integer roleId, String roleName, String empById, Boolean status) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.empById = empById;
		this.status = status;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

}
