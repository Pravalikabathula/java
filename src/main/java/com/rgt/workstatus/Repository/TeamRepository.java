package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>, QuerydslPredicateExecutor<Team> {

	Team findByTeamLeadName(String name);

	Team findOneByTeamId(Integer teamId);

	List<Team> findByTeamLeadNameContaining(String teamName);
}
