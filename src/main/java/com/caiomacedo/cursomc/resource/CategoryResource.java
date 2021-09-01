package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.dto.CategoryDTO;
import com.caiomacedo.cursomc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto){//esse request faz o json ser convertido para o objeto java, antes do dto passar pra frente tem que ser validado
        Category obj = cs.fromDTO(objDto);
        obj = cs.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//o fromCurrent... ele pega a url, e o path passa o id
        return ResponseEntity.created(uri).build();//uri é a url no postman
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto,@PathVariable Integer id){
        Category obj = cs.fromDTO(objDto);
        obj.setId(id); //seta o id que existe para o que foi passado
        obj = cs.update(obj);
        return ResponseEntity.noContent().build();//retorna que deu tudo ok, sem conteúdo
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    //retorna uma lista de categorias
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> find(){
        //o responseEntity é pq ele pode retornar qualquer tipo
        List<Category> list = cs.findAll();
        List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }
    //Serve para fazer uma busca paginada por categorias
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findPage(//o responseEntity é pq ele pode retornar qualquer tipo
            @RequestParam(value = "page",defaultValue = "0") Integer page,// o RequestParam é pra que eles sejam parametros opcionais
            @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerpage,
            @RequestParam(value = "OrderBy",defaultValue = "nome")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        Page<Category> list = cs.findPage(page,linesPerpage,orderBy,direction);
        Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }



}
