package com.db.mongo.exp.controller;

import java.time.Duration;
import java.util.concurrent.Delayed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.mongo.exp.model.Person;
import com.db.mongo.exp.repository.PersonRepository;

import reactor.core.publisher.Flux;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository repository;
	
	
	@GetMapping(value = "/getPersons", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Person> getPersons(){
		return repository.findAll();
		
	}
	
	@GetMapping(value = "/getDelayedPersons", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Person> getPersonsDelayed(){
		Flux<Person> persons = repository.findAll();
		return persons.delayElements(Duration.ofMillis(500));
		
	}

}
