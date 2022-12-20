package com.rgt.workstatus.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "TEAM_MEMBER_ID")
	private Integer teamMemberId;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "TEAM_MEMBER_STATUS")
	private boolean status;

	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "FK_TEAM_ID")
	private Team team;

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}	
}

	