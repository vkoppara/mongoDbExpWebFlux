package com.db.mongo.exp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.db.mongo.exp.model.Address;
import com.db.mongo.exp.model.Person;
import com.db.mongo.exp.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class BulkLoadConfigurer {
	
	@Autowired
	private PersonRepository personRepository;
	
	@PostConstruct
	public void init() {
		log.info("initialize..");
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<100;i++) {
			persons.add(new Person(UUID.randomUUID(),"FirstName-"+i,"LastName-"+i,"email"+i+"@gmail.com","residential",
					UUID.randomUUID(),new Address("firstline+"+i,"secondLine"+i,"street","country","city",String.valueOf(Math.abs(new Random().nextInt()%1000))),null));
			
		}
		Flux<Person> personSave = personRepository.saveAll(persons);
		personSave.subscribe();
		Mono<Long> countPersons = personRepository.count();
		personSave.then(countPersons);
		countPersons.subscribe();
		
		
	}

}
