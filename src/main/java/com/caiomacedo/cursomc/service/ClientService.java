 package com.caiomacedo.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.repository.ClientRepository;
import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
    @Autowired
    private ClientRepository cr;

    public Client find(Integer id){
        Optional<Client> obj =cr.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso   
        		"Objeto não encontrado! Id: "+id+" , Tipo: "+Client.class.getName()));
    }
}
