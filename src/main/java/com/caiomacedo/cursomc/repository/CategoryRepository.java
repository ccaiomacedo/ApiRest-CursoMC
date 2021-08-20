package com.caiomacedo.cursomc.repository;

import com.caiomacedo.cursomc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // é a camada de acesso a dados
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
