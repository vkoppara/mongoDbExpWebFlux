package com.db.mongo.exp.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.db.mongo.exp.model.Person;

public interface PersonRepository extends ReactiveCrudRepository<Person, UUID> {

}
