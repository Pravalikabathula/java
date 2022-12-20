package com.rgt.workstatus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.TaskSubmission;
import com.rgt.workstatus.NotFound.TaskSubmissionNotFoundExcepton;
import com.rgt.workstatus.Service.TaskSubmissionService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/tasksubmission")
public class TaskSubmissionController {
	@Autowired
	TaskSubmissionService taskSubmissionService;

	@PostMapping("/save")
	public Response saveTaskSubmission(@Validated @RequestBody TaskSubmission taskSubmission) {
		TaskSubmission saved = taskSubmissionService.saveTaskSubmission(taskSubmission);
		Response response = new Response();
		try {
			if (saved != null) {
				response.setStatus(true);
				response.setMsg("TaskSubmission details added !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@PutMapping("/update")
	public Response updateTaskSubmission(@Validated @RequestBody TaskSubmission taskSubmission) {
		TaskSubmission update = taskSubmissionService.updateTaskSubmission(taskSubmission);
		Response response = new Response();
		try {
			if (update != null) {
				response.setStatus(true);
				response.setMsg("TaskSubmission details updated !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneTaskSubmission(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			TaskSubmission task = taskSubmissionService.getTaskSubmissionById(id);
			resp = new ResponseEntity<TaskSubmission>(task, HttpStatus.OK);
		} catch (TaskSubmissionNotFoundExcepton e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/all")
	public List<TaskSubmission> getAllTask() {
		List<TaskSubmission> list = taskSubmissionService.getAllTaskSubmission();
		return list;

	}
}