package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.QTeam;
import com.rgt.workstatus.Entity.Team;
import com.rgt.workstatus.Repository.TeamRepository;

@Repository
public class TeamDaoImplementation implements TeamDao {

	@Autowired
	private TeamRepository teamRepository;
	
//	@Autowired
//	private TeamMemberRepository memberRepository;

	@Override
	public Team createTeamLeadName(Team team ) {

	/*	
		TeamMember teamMember = memberRepository.findById(teamMemberId).
				orElseThrow(()-> new IllegalArgumentException("Team Member details are required !!"));
		
		team.setTeamMember(teamMember);
	*/	
		if (team == null) {
			throw new IllegalArgumentException("Teams cannot be null");
		}
		if (team.getTeamLeadName() == null || team.getTeamLeadName().trim().length() == 0) {
			throw new IllegalArgumentException("TeamName cannot be null");
		}
//		Integer teams = team.getTeamId();
//		if (teams == null || (teams != null && teams == 0)) {
//			throw new IllegalArgumentException("team name cannot be null or 0");
//		}

		if (teamRepository.findByTeamLeadName(team.getTeamLeadName()) != null) {
			throw new IllegalArgumentException("team name " + team.getTeamLeadName() + " already exisist with us");
		}
		return teamRepository.save(team);
	}

	@Override
	public Team updateTeam(Team team) {
		Team update = teamRepository.findOneByTeamId(team.getTeamId());
		if(team.getTeamLeadName()== null) {
			throw new IllegalArgumentException("Team name cannot be null");
		}
		if (team.getTeamLeadName() != null && team.getTeamLeadName().trim().length() > 0) {
			if (update.getTeamLeadName().equalsIgnoreCase(team.getTeamLeadName())) {
				BooleanExpression name = QTeam.team.teamLeadName.equalsIgnoreCase(team.getTeamLeadName());
				if (teamRepository.findOne(name) == null) {
					throw new IllegalArgumentException("team name " + team.getTeamId() + "already exisist with us");
				}
			} else {
				update.setTeamId(team.getTeamId());
			}
		}

		update.setTeamLeadName(team.getTeamLeadName());
		update.setStatus(team.getStatus());
		return teamRepository.save(update);
	}

//	@Override
//	public Boolean deleteTeam(Integer teamId) {
//		if (teamId != null) {
//			teamRepository.deleteById(teamId);
//			return true;
//		}
//		return false;
//	}

	@Override
	public List<Team> searchByTeamname(String search) {
		return teamRepository.findByTeamLeadNameContaining(search);
	}

//	@Override
//	public List<Team> getAll() {
//		return teamRepository.findAll();
//
//	}

	@Override
	public Page<Team> getAllTeam(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return teamRepository.findAll(pageable);

	}

	@Override
	public Team getTeamById(Integer TeamId) {
		return teamRepository.findOneByTeamId(TeamId);
	}

	@Override
	public Team deleteStatus(Team team) {
		Team deleteStatus = teamRepository.findById(team.getTeamId()).orElseThrow(
				() -> new IllegalArgumentException("Team detaile are already deleted or not avaliable..!!"));
		if (deleteStatus.getStatus() == false) {
			throw new IllegalArgumentException("Team details are already deleted,please try another one...!!");
		}
		deleteStatus.setStatus(team.getStatus());
		return teamRepository.save(deleteStatus);
	}
}
