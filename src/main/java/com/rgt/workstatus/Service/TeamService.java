package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Team;

public interface TeamService 
{

	Team createTeamLeadName(Team team);

	Team updateTeam(Team team);

	// Boolean deleteTeam(Integer id);
	Team deleteStatus(Team team);

	Team getTeamById(Integer TeamId);

	List<Team> getAllTeam(Integer offset, Integer limit);

	List<Team> searchByTeamname(String search);
}
