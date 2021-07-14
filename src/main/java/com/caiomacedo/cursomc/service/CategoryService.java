package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cr;

    public Category find(Integer id){
        Optional<Category> obj =cr.findById(id);
        return obj.orElse(null);
    }
}
