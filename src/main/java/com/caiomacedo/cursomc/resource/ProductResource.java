package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.Product;
import com.caiomacedo.cursomc.dto.CategoryDTO;
import com.caiomacedo.cursomc.dto.ProductDTO;
import com.caiomacedo.cursomc.resource.utils.URL;
import com.caiomacedo.cursomc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/products")
public class ProductResource {

    @Autowired
    private ProductService ps;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){//para o spring receber o id da url
        //o responseEntity é pq ele pode retornar qualquer tipo
        Product obj = ps.find(id);
        return ResponseEntity.ok().body(obj); // está retornando um objeto

    }
    //Serve para fazer uma busca paginada por produtos
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(//o responseEntity é pq ele pode retornar qualquer tipo
                                                     @RequestParam(value = "name",defaultValue = "") String name,
                                                     @RequestParam(value = "categories",defaultValue = "") String categories,
                                                     @RequestParam(value = "page",defaultValue = "0") Integer page,// o RequestParam é pra que eles sejam parametros opcionais
                                                     @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerpage,
                                                     @RequestParam(value = "OrderBy",defaultValue = "nome")String orderBy,
                                                     @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        String nomeDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = ps.search(nomeDecoded,ids,page,linesPerpage,orderBy,direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));//essa linha converte uma lista pra outra lista
        return ResponseEntity.ok().body(listDto); // está retornando um objeto

    }


}
