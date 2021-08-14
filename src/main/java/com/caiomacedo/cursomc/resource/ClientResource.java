package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/clients")
public class ClientResource {

    @Autowired
    private ClientService cs;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Client obj = cs.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto

    }
}
