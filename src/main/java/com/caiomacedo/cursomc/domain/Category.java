package com.caiomacedo.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID=1l;
    private Integer id;
    private String nome;

    public Category(){

    }

    public Category(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
