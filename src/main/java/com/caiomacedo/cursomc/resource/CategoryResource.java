package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.dto.CategoryDTO;
import com.caiomacedo.cursomc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

    @Autowired
    private CategoryService cs;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)//o value{/id} é pq tem que informar o id através da requisição
    public ResponseEntity<Category > find(@PathVariable Integer id){//para o spring receber o id da url
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

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Category obj,@PathVariable Integer id){
        obj.setId(id); //seta o id que existe para o que foi passado
        obj = cs.update(obj);
        return ResponseEntity.noContent().build();//retorna que deu tudo ok, sem conteúdo
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)//o value{/id} é pq tem que informar o id através da requisição
    public ResponseEntity<List<CategoryDTO>> find(){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        List<Category> list = cs.findAll();
        List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }


}
