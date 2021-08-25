package com.caiomacedo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.Client;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Transactional(readOnly = true)//ela n necessita de ser uma transação de banco de dados
    Client findByEmail(String email);
}
