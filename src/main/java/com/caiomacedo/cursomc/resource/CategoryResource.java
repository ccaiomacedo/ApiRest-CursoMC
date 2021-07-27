package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService cs;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Category obj = cs.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto

    }
}
