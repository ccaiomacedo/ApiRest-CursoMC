 package com.caiomacedo.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cr;

    public Category find(Integer id){
        Optional<Category> obj =cr.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso   
        		"Objeto não encontrado! Id: "+id+" , Tipo: "+Category.class.getName()));//tipo retorna o nome da classe
    }

    public Category insert(Category obj){
        obj.setId(null);
        return cr.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        return cr.save(obj);
    }
}
