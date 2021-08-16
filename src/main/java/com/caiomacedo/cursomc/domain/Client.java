package com.caiomacedo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {// serve para dizer que o objeto pode ser convertido em bytes
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;

	private String cpfOuCnpj;
	private Integer tipo;

	@JsonIgnore // mais pra frente tirar pra ver da colé
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefone = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private List<Request> pedidos = new ArrayList<>();

	public Client() {

	}

	public Client(Integer id, String nome, String email, String cpfOuCnpj, ClientType tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public List<Request> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Request> pedidos) {
		this.pedidos = pedidos;
	}

	public ClientType getTipo() {
		return ClientType.toEnum(tipo);
	}

	public void setTipo(ClientType tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Set<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(Set<String> telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}