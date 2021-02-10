package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.services.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}

//	private Long id = 0L;
//	private List<User> users = new ArrayList<>();

	@GetMapping("/test")
	public String test() {
		return "Hello, World! :)";
	}

	// Create
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
	}

	// READ
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	@PutMapping("update/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable("id") Long id, @RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.update(id, task), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		// Remove Person and return it
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
