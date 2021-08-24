 package com.caiomacedo.cursomc.service;

import java.util.List;
import java.util.Optional;


import com.caiomacedo.cursomc.domain.Address;
import com.caiomacedo.cursomc.domain.City;
import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.caiomacedo.cursomc.dto.ClientDTO;
import com.caiomacedo.cursomc.dto.ClientNewDTO;
import com.caiomacedo.cursomc.repository.AddressRepository;
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
import org.springframework.transaction.annotation.Transactional;


 @Service
public class ClientService {
    @Autowired
    private ClientRepository cr;

     @Autowired
     private AddressRepository ar;

    public Client find(Integer id){
        Optional<Client> obj =cr.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso   
        		"Objeto não encontrado! Id: "+id+" , Tipo: "+Client.class.getName()));
    }

    @Transactional//isso é pra garantir que vai salvar tanto clientes quanto endereços na mesma transação de dados
    public Client insert(Client obj){
        obj.setId(null);
        obj = cr.save(obj);
        ar.saveAll(obj.getAddress());
        return obj;

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
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas");
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
    public Client fromDTO(ClientNewDTO objDto) {
        Client cli = new Client(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(), ClientType.toEnum(objDto.getTipo()));
        City cid = new City(objDto.getCidadeId(),null,null);
        Address end = new Address(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid);
        cli.getAddress().add(end);
        cli.getTelefone().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!=null){
            cli.getTelefone().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3()!=null){
            cli.getTelefone().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Client newObj,Client obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
