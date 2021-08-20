package com.caiomacedo.cursomc.dto;

import com.caiomacedo.cursomc.domain.Category;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
//a classe DTO serve pra definir os dados básicos de categoria que quero trafegar
public class CategoryDTO  implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID=1l;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5,max=80,message ="O tamanho deve ser entre 5 e 80 caracteres")
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
