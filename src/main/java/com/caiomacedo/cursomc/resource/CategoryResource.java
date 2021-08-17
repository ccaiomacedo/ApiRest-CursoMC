package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Category obj){//esse request faz o json ser convertido para o objeto java
        obj = cs.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//o fromCurrent... ele pega a url, e o path passa o id
        return ResponseEntity.created(uri).build();
    }
}
