package com.caiomacedo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
}