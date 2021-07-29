package com.caiomacedo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
