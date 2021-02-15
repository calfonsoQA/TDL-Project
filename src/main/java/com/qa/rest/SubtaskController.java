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

import com.qa.persistence.domain.SubtaskDomain;
import com.qa.persistence.dtos.SubtaskDTO;
import com.qa.services.SubtaskService;

@RestController
@RequestMapping("/subtask")
@CrossOrigin
public class SubtaskController {

	private SubtaskService service;

	@Autowired
	public SubtaskController(SubtaskService service) {
		super();
		this.service = service;
	}

	@GetMapping("/test")
	public String test() {
		return "Hello, World! :)";
	}

	// Create
	@PostMapping("/create")
	public ResponseEntity<SubtaskDTO> create(@RequestBody SubtaskDomain subtask) {
		return new ResponseEntity<SubtaskDTO>(this.service.create(subtask), HttpStatus.CREATED);
	}

	// READ
	@GetMapping("/readAll")
	public ResponseEntity<List<SubtaskDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<SubtaskDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody SubtaskDTO subtask) {
		return new ResponseEntity<>(this.service.update(id, subtask), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
