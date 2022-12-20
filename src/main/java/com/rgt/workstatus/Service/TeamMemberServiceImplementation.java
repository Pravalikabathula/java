package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.workstatus.DAO.TeamMemberDao;
import com.rgt.workstatus.Domain.TeamMember;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class TeamMemberServiceImplementation implements TeamMemberService {

	@Autowired
	TeamMemberDao teamMemberDao;
	private final static Logger logger = LoggerFactory.getLogger(TeamMemberServiceImplementation.class);

	@Transactional
	public TeamMember createTeamMemberName(TeamMember teamMember) {
		//logger.debug("createTeamMember:INVOKED");
		TeamMember teamMembers = toDomain(teamMemberDao.createTeamMemberName(toEntity(teamMember)));
		return teamMembers;
	}

	@Transactional
	public TeamMember updateTeamMember(TeamMember teamMember) {
		logger.debug("updateTeamMembers:INVOKED");
		TeamMember teamMembers = toDomain(teamMemberDao.updateTeamMember(toEntity(teamMember)));
		return teamMembers;
	}

//	@Transactional
//	public Boolean deleteTeamMemeber(Integer Id) {
//		com.rgt.workstatus.Entity.TeamMemeber getTeamMemeberById = teamMemeberDao.getTeamMemeberById(Id);
//		if (getTeamMemeberById != null) {
//			teamMemeberDao.deleteTeamMemeber(Id);
//			return true;
//		}
//		return false;
//
//	}

	@Transactional
	public List<TeamMember> getAllTeamMembers(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.TeamMember> teamMemeberss = teamMemberDao.getAllTeamMembers(offset, limit);
		List<TeamMember> teamMember = new ArrayList<TeamMember>();
		teamMemeberss.forEach(teamMemebers -> {
			TeamMember teamMemebers1 = toDomain(teamMemebers);
			teamMember.add(teamMemebers1);
		});
		return teamMember;
	}

	@Transactional
	public List<TeamMember> searchByTeamMembername(String search) {
		List<com.rgt.workstatus.Entity.TeamMember> teamMemberss = teamMemberDao.searchTeamMemberByname(search);
		List<TeamMember> teamMember = new ArrayList<TeamMember>();
		teamMemberss.forEach(teamMembers -> {
			TeamMember teamMembers1 = toDomain(teamMembers);
			teamMember.add(teamMembers1);
		});
		return teamMember;
	}

//	@Transactional
//	public List<TeamMemeber> getAll() {
//		List<com.rgt.workstatus.Entity.TeamMemeber> teamMemeberss = teamMemeberDao.getAll();
//		List<TeamMemeber> teamMemeber = new ArrayList<TeamMemeber>();
//		teamMemeberss.forEach(teamMemebers -> {
//			TeamMemeber teamMemebers1 = toDomain(teamMemebers);
//			teamMemeber.add(teamMemebers1);
//		});
//		return teamMemeber;
//	}

	@Transactional
	public TeamMember getTeamMemberById(Integer id) {
		com.rgt.workstatus.Entity.TeamMember teamMembers = teamMemberDao.getTeamMemberById(id);
		TeamMember teamMember = toDomain(teamMembers);
		return teamMember;
	}

	public com.rgt.workstatus.Entity.TeamMember toEntity(TeamMember teamMember) {
		com.rgt.workstatus.Entity.TeamMember entity = null;
		if (teamMember != null) {
			entity = new com.rgt.workstatus.Entity.TeamMember();
			Objectcopier.copyObject(teamMember, entity);
		}
		return entity;
	}

	public TeamMember toDomain(com.rgt.workstatus.Entity.TeamMember teamMember) {
		TeamMember domain = null;
		if (teamMember != null) {
			domain = new TeamMember();
			Objectcopier.copyObject(teamMember, domain);
		}
		return domain;
	}

	@Transactional
	public TeamMember deleteStatus(TeamMember teamMember) {
		return toDomain(teamMemberDao.deleteStatus(toEntity(teamMember)));
	}

}
