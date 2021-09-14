package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Orders;
import com.caiomacedo.cursomc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value="/orders")
public class OrderResource {

    @Autowired
    private OrderService os;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Orders> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Orders obj = os.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Orders obj){//esse request faz o json ser convertido para o objeto java, antes do dto passar pra frente tem que ser validado
        obj = os.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//o fromCurrent... ele pega a url, e o path passa o id
        return ResponseEntity.created(uri).build();//uri é a url no postman
    }
}
