package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.Product;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import com.caiomacedo.cursomc.repository.ProductRepository;
import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository pr;

    @Autowired
    private CategoryRepository cr;

    public Product find(Integer id) {
        Optional<Product> obj = pr.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso
                "Objeto não encontrado! Id: " + id + " , Tipo: " + Category.class.getName()));//tipo retorna o nome da classe
    }

    //classe responsável por paginação, que serve para não sobrecarregar o sistema
    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerpage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerpage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categorias = cr.findAllById(ids);// para retornar uma categoria a partir dos ids
        return pr.findDistinctByNameContainingAndCategoriesIn(name,categorias,pageRequest);
    }
}
