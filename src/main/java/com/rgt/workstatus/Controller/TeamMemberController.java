package com.rgt.workstatus.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.rgt.workstatus.Domain.TeamMember;
import com.rgt.workstatus.NotFound.TeamMemeberNotFoundexception;
import com.rgt.workstatus.Service.TeamMemberService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/teamMember")
public class TeamMemberController {

	@Autowired
	private TeamMemberService teamMemberService;
	Response response = new Response();
	private final static Logger logger = LoggerFactory.getLogger(TeamMemberController.class);

	
	@PostMapping(value="/create", consumes={"application/json"})
	//@PostMapping(value = "/create")
	public Response addTeamMember(@RequestBody TeamMember teamMember) {
		
		 // logger.debug("addTeamMember:INVOKED"); 
		  //Response response = new Response();
		  TeamMember teamMembers = teamMemberService.createTeamMemberName(teamMember);
		 
		try {
			if (teamMembers != null) {
				response.setStatus(true);
				response.setMsg("TeamMember deatils are created successfully !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;

	}

	@GetMapping(value = "/all")
	public @ResponseBody List<TeamMember> getAllTeamMember(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit)

	{
		logger.debug("getAllTeamMembers: INVOKED");
		return teamMemberService.getAllTeamMembers(offset, limit);

	}

	@GetMapping(value = "/search/{search}")
	public List<TeamMember> getAllTeamMemeber(@PathVariable(value = "search") String search) {
		return teamMemberService.searchByTeamMembername(search);
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> getByTeamMemeber(@PathVariable Integer id) {
		ResponseEntity<?> response = null;
		try {
			TeamMember teamMember = teamMemberService.getTeamMemberById(id);
			response = new ResponseEntity<TeamMember>(teamMember, HttpStatus.OK);
		} catch (TeamMemeberNotFoundexception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PutMapping(value = "/update/{id}")
	public Response updateTeamMember(@RequestBody TeamMember teamMember) {
		logger.debug("updateTeamMember:INVOKED");
		TeamMember teamMembers = teamMemberService.updateTeamMember(teamMember);
		Response response = new Response();
		try {
			if (teamMembers != null) {
				response.setStatus(true);
				response.setMsg("TeamMember deatils are updated successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;

	}

	@DeleteMapping(value = "/delete/{teamMemberId}")
	public Response deleteTeamMemeber(@PathVariable("teamMemberId") Integer teamMemberId) {
		logger.debug("deleteTeamMemebers:INVOKED");
		if (teamMemberId != 0) {
			TeamMember teamMembers = null;
			teamMembers = teamMemberService.getTeamMemberById(teamMemberId);
			teamMembers.setStatus(false);
			teamMemberService.deleteStatus(teamMembers);
			if (teamMembers != null) {
				response.setStatus(true);
				response.setMsg("TeamMember details are deleted !!");
			}
		}
		return response;
	}
}
