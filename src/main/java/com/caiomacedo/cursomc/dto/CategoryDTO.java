package com.caiomacedo.cursomc.dto;

import com.caiomacedo.cursomc.domain.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
//a classe DTO serve pra definir os dados básicos de categoria que quero trafegar
public class CategoryDTO  implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID=1l;

    private Integer id;
    private String nome;

    public CategoryDTO(){

    }
    public CategoryDTO(Category obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
