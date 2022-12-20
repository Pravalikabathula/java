package com.rgt.workstatus.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Team;
import com.rgt.workstatus.Service.TeamService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	Response response = new Response();
	private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

	@PostMapping(value = "/create")
	public Response addTeam(@RequestBody Team team) {
		logger.debug("addTeams:INVOKED");
		Team teams = teamService.createTeamLeadName(team);
		try {
			if (teams != null) {
				response.setStatus(true);
				response.setMsg("Teams deatils are created successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping(value = "/all")
	public @ResponseBody List<Team> getAllTeam(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
		logger.debug("getAllTeams: INVOKED");
		return teamService.getAllTeam(offset, limit);

	}

	@GetMapping(value = "/search/{search}")
	public List<Team> getTeamBySearch(@PathVariable(value = "search") String search) {
		return teamService.searchByTeamname(search);
	}

	@PutMapping(value = "/update/{id}")
	public Response updateTeams(@RequestBody Team team) {
		logger.debug("updateTeams:INVOKED");
		Team teams = teamService.updateTeam(team);
		try {
			if (teams != null) {
				response.setStatus(true);
				response.setMsg("Teams deatils are updated successfully !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}

		return response;

	}

	@DeleteMapping(value = "/delete/{teamsId}")
	public Response deleteTeams(@PathVariable("teamsId") Integer teamsId) {
		logger.debug("deleteTeams:INVOKED");
		if (teamsId != 0) {
			Team team = null;
			team = teamService.getTeamById(teamsId);
			team.setStatus(false);
			teamService.deleteStatus(team);
			if (team != null) {
				response.setStatus(true);
				response.setMsg("Team Details deleted");
			}
		}

		return response;
	}

}
