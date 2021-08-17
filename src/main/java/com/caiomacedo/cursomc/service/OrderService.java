 package com.caiomacedo.cursomc.service;

 import com.caiomacedo.cursomc.domain.Category;
 import com.caiomacedo.cursomc.domain.Orders;
 import com.caiomacedo.cursomc.repository.CategoryRepository;
 import com.caiomacedo.cursomc.repository.OrderRepository;
 import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;
 import org.hibernate.criterion.Order;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.Optional;

 @Service
 public class OrderService {
     @Autowired
     private OrderRepository or;

     public Orders find(Integer id){
         Optional<Orders> obj =or.findById(id);
         return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso
                 "Objeto não encontrado! Id: "+id+" , Tipo: "+Category.class.getName()));//tipo retorna o nome da classe
     }
 }
