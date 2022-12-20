package com.rgt.workstatus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Task;
import com.rgt.workstatus.NotFound.TaskNotFoundException;
import com.rgt.workstatus.Service.TaskService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/save")
	public Response saveTask(@RequestBody Task task) {
		Task saved = taskService.saveTask(task);
		Response response = new Response();
		try {
			if (saved != null) {
				response.setStatus(true);
				response.setMsg("task details added !!");
			}
		} catch (TaskNotFoundException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping("/search/{search}")
	public List<Task> getTaskBySearch(@PathVariable(value = "search") String search) {
		return taskService.searchByTaskName(search);

	}

	@GetMapping("/all")
	public List<Task> getAllTask(@RequestParam(value = "pageNumber", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit) {
		return taskService.getAllTask(offset, limit);

	}

	// FOR SHOWING RESPONSE IF DATA IS FETCHING
//	@GetMapping( "task/find/{id}")
//	public Response getOneTask(@PathVariable Integer id) {
//        Task getTaskById = taskService.getTaskById(id);
//        Response response = new Response();
//        try {
//            if (getTaskById != null) {
//                response.setStatus(true);
//                response.setMsg("task details fetching !!");
//            }
//        } catch (IllegalArgumentException e) {
//            response.setStatus(false);
//            response.setMsg(e.getMessage());
//        }
//        return response;
//	} 
	// RETURN DATA IN POSTMAN IF WE ARE FETCHING
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneTask(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Task task = taskService.getTaskById(id);
			resp = new ResponseEntity<Task>(task, HttpStatus.OK);
		} catch (TaskNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@PutMapping("/update/{id}")
	public Response updateTask(@Validated @RequestBody Task task) {
		Task update = taskService.updateTask(task);
		Response response = new Response();
		try {
			if (update != null) {
				response.setStatus(true);
				response.setMsg("task details updated !!");
			}
		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

//	@PutMapping("task/date/{id}")
//	public Response updateTaskdate(@Validated @RequestBody Task task) {
//		Task update = taskService.updateTaskss(task);
//		Response response = new Response();
//		try {
//			if (update != null) {
//				response.setStatus(true);
//				response.setMsg("task details date  updated !!");
//			}
//		} catch (IllegalArgumentException e) {
//			response.setStatus(false);
//			response.setMsg(e.getMessage());
//		}
//		return response;
//	}

	@DeleteMapping("/delete/{id}")
	public Response deleteTask(@PathVariable("id") Integer id) {
		// taskService.deleteTask(id);
		Response response = new Response();
		Task task = null;
		if (id != 0) {
			try {
				task = taskService.getTaskById(id);
				task.setStatus(false);
				taskService.deleteTaskStatus(task);
				if (id != null) {
					response.setStatus(true);
					response.setMsg("Task Record deleted");
				}
			} catch (IllegalArgumentException e) {
				response.setMsg(e.getMessage());
				response.setStatus(true);
			}
		}
		return response;
	}

}