package com.caiomacedo.cursomc.repository;

import com.caiomacedo.cursomc.domain.Client;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiomacedo.cursomc.domain.Orders;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Transactional(readOnly = true)
    Page<Orders> findByClient(Client client, Pageable pageRequest);
}
