package com.caiomacedo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
