package com.caiomacedo.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Serializable {// serve para dizer que o objeto pode ser convertido em bytes
	private static final long serialVersionUID = 1l;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUCT_CATEGORY", // essa tabela que vai fazer o muitos pra muitos
			joinColumns = @JoinColumn(name = "product_id")// chave estrangeira
			, inverseJoinColumns = @JoinColumn(name = "category_id")) // chave estrangeira que se referencia a categoria

	private List<Category> categories = new ArrayList<>();

	public Product() {

	}

	public Product(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
