package com.caiomacedo.cursomc.repository;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories") //pra fazer a consulta com jpql no banco de dodos
    Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name,@Param("categories") List<Category> categorias, Pageable pageRequest);
}
