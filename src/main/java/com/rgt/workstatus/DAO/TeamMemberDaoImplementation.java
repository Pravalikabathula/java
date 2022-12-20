package com.rgt.workstatus.DAO;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.QTeamMember;
import com.rgt.workstatus.Entity.TeamMember;
import com.rgt.workstatus.NotFound.TeamMemeberNotFoundexception;
import com.rgt.workstatus.Repository.TeamMemberRepository;

@Repository
public class TeamMemberDaoImplementation implements TeamMemberDao {

	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@Override
	public TeamMember createTeamMemberName(TeamMember teamMember) {

		if (teamMember == null) {
			throw new IllegalArgumentException("TeamMemeber cannot be null");
		}
		if (teamMember.getEmployeeName() == null || teamMember.getEmployeeName().trim().length() == 0) {
			throw new IllegalArgumentException("TeamMemeberName cannot be null");
		}
//
//		Integer teamMemebers = teamMemeber.getTeamMemeberId();
//		if (teamMemebers == null || (teamMemebers != null && teamMemebers == 0)) {
//			throw new IllegalArgumentException("role name cannot be null or 0");
//		}

		if (teamMemberRepository.findByemployeeName(teamMember.getEmployeeName()) != null) {
			throw new IllegalArgumentException("teamMemeber name " + teamMember.getEmployeeName() + " exisist with us");
		}
		return teamMemberRepository.save(teamMember);
	}

	@Override
	public TeamMember updateTeamMember(TeamMember teamMember) {
		TeamMember update = teamMemberRepository.findOneByTeamMemberId(teamMember.getTeamMemberId());
		if (teamMember.getEmployeeName() == null) {
			throw new IllegalArgumentException("EmployeeName name cannot be null");
		}

		if (teamMember.getEmployeeName() != null && teamMember.getEmployeeName().trim().length() > 0) {
			if (update.getEmployeeName().equalsIgnoreCase(teamMember.getEmployeeName())) {
				BooleanExpression teamMembers = QTeamMember.teamMember.employeeName
						.equalsIgnoreCase(teamMember.getEmployeeName());
				if (teamMemberRepository.findOne(teamMembers) != null) {
					throw new IllegalArgumentException(
							"EmployeeName Name " + teamMember.getTeamMemberId() + " already exists with us !!");
				}
			} else {
				update.setEmployeeName(teamMember.getEmployeeName());
			}
		}
		update.setTeamMemberId(teamMember.getTeamMemberId());
		update.setStatus(teamMember.isStatus());
		return teamMemberRepository.save(update);
	}


	/*
	 * @Override public TeamMember updateTeamMemeber(TeamMemeber teamMemeber) {
	 * TeamMember update =
	 * teamMemberRepository.findOneByTeamMemeberId(teamMemeber.getTeamMemeberId());
	 * if (teamMemeber.getEmployeeName() == null) { throw new
	 * IllegalArgumentException("teamMemeber name cannot be null"); } if
	 * (teamMemeber.getEmployeeName() != null &&
	 * teamMemeber.getEmployeeName().trim().length() > 0) { if
	 * (update.getEmployeeName().equalsIgnoreCase(teamMemeber.getEmployeeName())) {
	 * BooleanExpression name = QTeamMemeber.teamMemeber.employeeName
	 * .equalsIgnoreCase(teamMemeber.getEmployeeName()); if
	 * (teamMemberRepository.findOne(name) != null) { throw new
	 * IllegalArgumentException( "teamMemeber name " + teamMemeber.getEmployeeName()
	 * + "exisist with us"); } } else {
	 * update.setEmployeeName(teamMemeber.getEmployeeName()); } }
	 * update.setTeamMemeberId(teamMemeber.getTeamMemeberId());
	 * update.setStatus(teamMemeber.getStatus());
	 * 
	 * return teamMemberRepository.save(update); }
	 */
//	@Override
//	public Boolean deleteTeamMemeber(Integer Id) {
//		if (Id != null) {
//			teamMemeberRepository.deleteById(Id);
//			return true;
//		}
//		return false;
//	}

	@Override
	public Page<TeamMember> getAllTeamMembers(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return teamMemberRepository.findAll(pageable);
	}

	@Override
	public List<TeamMember> searchTeamMemberByname(String search) {
		return teamMemberRepository.findByEmployeeNameContaining(search);
	}
//	@Override
//	public List<TeamMemeber> getAll() {
//		return teamMemeberRepository.findAll();
//	}

	@Override
	public TeamMember getTeamMemberById(Integer id) {
		Optional<TeamMember> teamMember = teamMemberRepository.findById(id);
		if (teamMember.isPresent()) {
			return teamMember.get();
		} else {
			throw new TeamMemeberNotFoundexception("TeamMemeber" + id + " Not Found ");
		}
	}

	
	@Override
	public TeamMember deleteStatus(TeamMember teamMember) {

		TeamMember deleteStatus = teamMemberRepository.findById(teamMember.getTeamMemberId()).orElseThrow(
				() -> new IllegalArgumentException("TeamMemebers detaile are already deleted or not avaliable..!!"));
		if (deleteStatus.isStatus() == false) {
			throw new IllegalArgumentException("TeamMemebers details are already deleted,please try another one...!!");
		}
		deleteStatus.setStatus(teamMember.isStatus());
		return teamMemberRepository.save(deleteStatus);
	}

}
