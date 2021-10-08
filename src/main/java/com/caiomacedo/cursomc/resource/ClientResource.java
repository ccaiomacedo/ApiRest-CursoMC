package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.dto.ClientDTO;
import com.caiomacedo.cursomc.dto.ClientNewDTO;
import com.caiomacedo.cursomc.service.ClientService;
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
@RequestMapping(value="/clients")
public class ClientResource {

    @Autowired
    private ClientService cs;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Client obj = cs.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDto){//esse request faz o json ser convertido para o objeto java, antes do dto passar pra frente tem que ser validado
        Client obj = cs.fromDTO(objDto);
        obj = cs.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//o fromCurrent... ele pega a url, e o path passa o id
        return ResponseEntity.created(uri).build();//uri é a url no postman
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id){
        Client obj = cs.fromDTO(objDto);
        obj.setId(id); //seta o id que existe para o que foi passado
        obj = cs.update(obj);
        return ResponseEntity.noContent().build();//retorna que deu tudo ok, sem conteúdo
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    //retorna uma lista de clientes
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll(){
        //o responseEntity é pq ele pode retornar qualquer tipo
        List<Client> list = cs.findAll();
        List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(//o responseEntity é pq ele pode retornar qualquer tipo
                                                      @RequestParam(value = "page",defaultValue = "0") Integer page,// o RequestParam é pra que eles sejam parametros opcionais
                                                      @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerpage,
                                                      @RequestParam(value = "OrderBy",defaultValue = "nome")String orderBy,
                                                      @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        Page<Client> list = cs.findPage(page,linesPerpage,orderBy,direction);
        Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }

}
