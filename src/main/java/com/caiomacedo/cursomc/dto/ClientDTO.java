package com.caiomacedo.cursomc.dto;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.service.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
//Data Transfer Object

@ClientUpdate
public class ClientDTO implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID=1l;

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5,max=120,message ="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClientDTO(){

    }
    public ClientDTO(Client obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
