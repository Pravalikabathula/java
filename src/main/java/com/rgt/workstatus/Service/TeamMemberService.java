package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.TeamMember;

public interface TeamMemberService {

	TeamMember createTeamMemberName(TeamMember teamMember);

	TeamMember updateTeamMember(TeamMember teamMember);

	// Boolean deleteTeamMemeber(Integer id);

	TeamMember deleteStatus(TeamMember teamMember);

	TeamMember getTeamMemberById(Integer id);

	List<TeamMember> getAllTeamMembers(Integer offset, Integer limit);

	List<TeamMember> searchByTeamMembername(String search);
}
