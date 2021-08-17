package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.domain.Orders;
import com.caiomacedo.cursomc.service.ClientService;
import com.caiomacedo.cursomc.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/orders")
public class OrderResource {

    @Autowired
    private OrderService os;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Orders obj = os.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto

    }
}
