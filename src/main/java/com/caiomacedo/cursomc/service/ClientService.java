 package com.caiomacedo.cursomc.service;

import java.util.List;
import java.util.Optional;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.dto.CategoryDTO;
import com.caiomacedo.cursomc.dto.ClientDTO;
import com.caiomacedo.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Client update(Client obj){
        Client newObj = find(obj.getId());
        updateData(newObj,obj);
        return cr.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            cr.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<Client> findAll(){
        return cr.findAll();
    }
    //classe responsável por páginação, que serve pra n sobrecarregar o sistema
    public Page<Client> findPage(Integer page, Integer linesPerpage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerpage, Sort.Direction.valueOf(direction),orderBy);
        return cr.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
    }

    private void updateData(Client newObj,Client obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
