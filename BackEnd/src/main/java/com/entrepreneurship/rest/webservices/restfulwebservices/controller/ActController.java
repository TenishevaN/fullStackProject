/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;

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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Act;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.ActService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.ContractService;

/*
 * It is designed to handle web requests to a Act class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ActController {

	@Autowired
	private ActService actService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ContractService contractService;

	@GetMapping("/jpa/{username}/acts")
	public List<Act> getAllActs(@PathVariable String username) {

		return actService.getAllAtsByUser(username);
	}

	@GetMapping("/jpa/{username}/contractActs/{id}")
	public List<Act> getAllContractActs(@PathVariable String username, @PathVariable long id) {

		return actService.getAllContractActsByUser(username, id);
	}

	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/{username}/acts/{id}")
	public ResponseEntity<Void> deleteAct(@PathVariable String username, @PathVariable long id) {

		actService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/jpa/{username}/acts/{id}")
	public Act getAct(@PathVariable String username, @PathVariable long id) {
		return actService.getAct(username, id);
	}

	@PutMapping("/jpa/{username}/acts/{id}/{contract_id}")
	public ResponseEntity<Act> updateAct(@PathVariable String username, @PathVariable long id,
			@PathVariable int contract_id, @RequestBody Act act) {

		act.setUser(userDao.findByUsername(username));

		act.setContract(contractService.getContractById((long) contract_id));

		Act updatedAct = actService.save(act);

		return new ResponseEntity<Act>(updatedAct, HttpStatus.OK);
	}

	@PostMapping("/jpa/{username}/acts/")
	public ResponseEntity<Act> createAct(@PathVariable String username, @RequestBody Act act) {

		act.setUser(userDao.findByUsername(username));
		act.setContract(contractService.getContractById((long) 1));
		Act createdAct = actService.save(act);

		ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAct.getId())
				.toUri();

		return new ResponseEntity<Act>(createdAct, HttpStatus.OK);
	}

}
