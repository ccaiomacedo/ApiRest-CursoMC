 package com.caiomacedo.cursomc.service;

import java.util.List;
import java.util.Optional;

import com.caiomacedo.cursomc.dto.CategoryDTO;
import com.caiomacedo.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;

@Service // classe de serviço serve para executar os serviços observando as regras de negócio
//responsável pelas regras de negócio
public class CategoryService {
    @Autowired
    private CategoryRepository cr;

    public Category find(Integer id){
        Optional<Category> obj =cr.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso   
        		"Objeto não encontrado! Id: "+id+" , Tipo: "+Category.class.getName()));//tipo retorna o nome da classe
    }

    public Category insert(Category obj){
        obj.setId(null);
        return cr.save(obj);
    }

    public Category update(Category obj){
        Category newObj = find(obj.getId());
        updateData(newObj,obj);
        return cr.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try {
            cr.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
        }
    }

    public List<Category> findAll(){
        return cr.findAll();
    }
    //classe responsável por páginação, que serve pra n sobrecarregar o sistema
    public Page<Category> findPage(Integer page, Integer linesPerpage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerpage, Sort.Direction.valueOf(direction),orderBy);
        return cr.findAll(pageRequest);
    }

    //passando as informações de categoria pra a dto
    public Category fromDTO(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getNome());
    }

    private void updateData(Category newObj,Category obj){
        newObj.setNome(obj.getNome());
    }

}
