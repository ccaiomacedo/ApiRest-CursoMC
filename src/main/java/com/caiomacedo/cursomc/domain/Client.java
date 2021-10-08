package com.caiomacedo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.caiomacedo.cursomc.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {// serve para dizer que o objeto pode ser convertido em bytes
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true)//faz o banco de dados garantir que não irá haver repetição nesse campo
	private String email;

	private String cpfOuCnpj;
	private Integer tipo;

	@JsonIgnore
	private String senha;

	 // mais pra frente tirar pra ver da colé
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")//essas anotações servem para criar outra tabela a parte dessa entidade e armazenar esse atributo
	private Set<String> telefone = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)//Pra garantir que sempre que for buscado um cliente no banco de dados, vai buscar perfis tb
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();


	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Orders> pedidos = new ArrayList<>();

	public Client() {
		addPerfil(Profile.CLIENTE);
	}

	public Client(Integer id, String nome, String email, String cpfOuCnpj, ClientType tipo,String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo =(tipo==null)?null : tipo.getCod();//se o tipo for nulo atribua nulo, caso contrário atribua o código
		this.senha = senha;
		addPerfil(Profile.CLIENTE);
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


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public List<Orders> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Orders> pedidos) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Profile> getPerfis() {
		return perfis.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());//está percorrendo a coleção e está transformando tod mundo para o tipo enumerado perfil
	}

	public void addPerfil(Profile perfil) {
		perfis.add(perfil.getCod());
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
