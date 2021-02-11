package com.example.teste.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.domain.Info;

@Repository
public interface InfoRepository extends MongoRepository<Info, String>{
	
}
