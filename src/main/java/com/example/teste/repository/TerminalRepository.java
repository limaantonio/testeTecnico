package com.example.teste.repository;

import com.example.teste.domain.Terminal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalRepository extends  JpaRepository<Terminal, Long> {

    @Query("SELECT b FROM Terminal b WHERE b.logic = :logic")
    Optional<Terminal> findByLogic(@Param("logic") Integer logic);




}
