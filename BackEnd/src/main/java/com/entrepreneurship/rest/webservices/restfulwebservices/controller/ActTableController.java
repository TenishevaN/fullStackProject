/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.ActTable;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.ActTableService;

/*
 * It is designed to handle web requests to a ActTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ActTableController {

	@Autowired
	private ActTableService actTableService;
	
	
	@GetMapping("/jpa/actTableById/{id}")
	public List<ActTable> getActTableById(@PathVariable long id){
	
		return actTableService.getActTableById(id);
	}	
	
	@GetMapping("/jpa/actTableByContractId/{id}")
	public List<ActTable> getActTableByContractId(@PathVariable long id){
	
		return actTableService.getActTableByContractId(id);
	}
	
	@GetMapping("/jpa/actTableByActId/{id}")
	public List<ActTable> getActTableByActId(@PathVariable long id){
	
		return actTableService.getActTableByActId(id);
	}
	
	
	@PutMapping("/jpa/updateActTable")
	public ResponseEntity<ActTable> updateActTable(
			 @RequestBody ActTable actTable){
				
		ActTable updatedActTable = actTableService.save(actTable);
		
		return new ResponseEntity<ActTable>(updatedActTable, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/createActTable")
	public ResponseEntity<Void> createAct(
			 @RequestBody ActTable actTable){
		
		ActTable createdActTable = actTableService.save(actTable);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdActTable.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	// DELETE /users/{username}/todos/{id}
		@DeleteMapping("/jpa/deleteActTable/{id}")
		public ResponseEntity<Void> deleteAct(
			@PathVariable long id) {

			actTableService.deleteById(id);

				return ResponseEntity.noContent().build();
		}

		
}
