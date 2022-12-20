package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.TeamMember;

public interface TeamMemberDao {

	TeamMember createTeamMemberName(TeamMember teamMember);

	TeamMember updateTeamMember(TeamMember teamMember);

	// Boolean deleteTeamMemeber(Integer Id);
	TeamMember deleteStatus(TeamMember teamMember);

	TeamMember getTeamMemberById(Integer id);

	Page<TeamMember> getAllTeamMembers(Integer offset, Integer limit);

	List<TeamMember> searchTeamMemberByname(String search);

}
