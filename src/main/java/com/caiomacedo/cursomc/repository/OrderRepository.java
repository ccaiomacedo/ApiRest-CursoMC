package com.caiomacedo.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
