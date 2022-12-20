package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.Team;
import com.rgt.workstatus.Entity.TeamMember;

public interface TeamDao {

	Team createTeamLeadName(Team team);

	Team updateTeam(Team team);

	// Boolean deleteTeam(Integer id);
	Team deleteStatus(Team team);

	Team getTeamById(Integer TeamId);

	Page<Team> getAllTeam(Integer offset, Integer limit);

	List<Team> searchByTeamname(String search);

}
