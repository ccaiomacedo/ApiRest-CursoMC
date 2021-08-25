package com.caiomacedo.cursomc.resource.exception;

import java.io.Serializable;

public class FieldMessage  implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID=1l;

    private String nome;
    private String message;

    public FieldMessage(){
    }

    public FieldMessage(String nome, String message) {
        this.nome = nome;
        this.message = message;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}