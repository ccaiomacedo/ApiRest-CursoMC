package com.caiomacedo.cursomc.repository;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
