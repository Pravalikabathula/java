package com.rgt.workstatus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Team;
import com.rgt.workstatus.Entity.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer>, QuerydslPredicateExecutor<TeamMember> {

	TeamMember findByemployeeName(String name);

	TeamMember findOneByTeamMemberId(Integer TeamMemeberId);
	
	Optional<TeamMember> findById(Integer TeamMemeberId);

	List<TeamMember> findByEmployeeNameContaining(String employeeName);
}
