package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.workstatus.DAO.TeamDao;
import com.rgt.workstatus.Domain.Roles;
import com.rgt.workstatus.Domain.Team;
import com.rgt.workstatus.Entity.TeamMember;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class TeamServiceImplementation implements TeamService {
	@Autowired
	TeamDao teamDao;
	private final static Logger logger = LoggerFactory.getLogger(TeamServiceImplementation.class);

	@Transactional
	public Team createTeamLeadName(Team team) {
		Team teams = toDomain(teamDao.createTeamLeadName(toEntity(team)));
		return teams;
	}

//		logger.debug("createTeams:INVOKED");
//		com.rgt.workstatus.Entity.Team tm = toEntity(team);
//		List<TeamMember> mem = tm.getTeamMember();
//		for (TeamMember n : mem) {
//			n.setTeam(tm);
//		}
//		Team teams = toDomain(teamDao.createTeamName(tm));
//		return teams;
//	}
		
			

	@Transactional
	public Team updateTeam(Team team) {
		logger.debug("updateTeams:INVOKED");

		Team teams = toDomain(teamDao.updateTeam(toEntity(team)));
		return teams;
	}

	@Transactional
	public List<Team> getAllTeam(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.Team> teamss = teamDao.getAllTeam(offset, limit);
		List<Team> team = new ArrayList<Team>();
		teamss.forEach(teams -> {
			Team teams1 = toDomain(teams);
			team.add(teams1);
		});
		return team;
	}

	@Transactional
	public List<Team> searchByTeamname(String search) {
		List<com.rgt.workstatus.Entity.Team> teamss = teamDao.searchByTeamname(search);
		List<Team> team = new ArrayList<Team>();
		teamss.forEach(teams -> {
			Team teams1 = toDomain(teams);
			team.add(teams1);
		});
		return team;

	}

	@Override
	public Team getTeamById(Integer TeamId) {
		Team domain = toDomain(teamDao.getTeamById(TeamId));
		if (domain.isStatus() == false) {
			throw new IllegalArgumentException("Team details are not available , Please select another team !!");
		}
		return domain;
	}

//
//	@Transactional
//	public Boolean deleteTeam(Integer id) {
//		 Boolean deleteTeam = teamDao.deleteTeam(id);
//		 if (deleteTeam != null) {
//			return true;
//		}
//		return false;
//	}

//	@Transactional
//	public List<Team> getAll() {
//		List<com.rgt.workstatus.Entity.Team> teamss = teamDao.getAll();
//		List<Team> team = new ArrayList<Team>();
//		teamss.forEach(teams -> {
//			Team teams1 = toDomain(teams);
//			team.add(teams1);
//		});
//		return team;
//	}

	public com.rgt.workstatus.Entity.Team toEntity(Team team) {
		com.rgt.workstatus.Entity.Team entity = null;
		if (team != null) {
			entity = new com.rgt.workstatus.Entity.Team();
			Objectcopier.copyObject(team, entity);
		}
		return entity;
	}

	public Team toDomain(com.rgt.workstatus.Entity.Team team) {
		Team domain = null;
		if (team != null) {
			domain = new Team();
			Objectcopier.copyObject(team, domain);
		}
		return domain;
	}

	@Transactional
	public Team deleteStatus(Team team) {
		return toDomain(teamDao.deleteStatus(toEntity(team)));
	}

}
