package com.example.teste.infoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.domain.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {

}
