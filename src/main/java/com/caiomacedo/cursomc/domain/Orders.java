package com.caiomacedo.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Orders implements Serializable {// serve para dizer que o objeto pode ser convertido em bytes
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")//formatando a hora pra n ficar em milisegundos
	private Date instante;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") // pra n dar o erro de entidade transiente
	private Payment pagamento;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Address enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido")
	private Set<OrderItem> itens = new HashSet<>();

	public Orders() {

	}

	public Orders(Integer id, Date instante, Client cliente, Address enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.client = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public double getValorTotal(){
		double soma = 0.0;
		for(OrderItem ip : itens){
			soma+=ip.getSubTotal();
		}
		return soma;
	}


	public Set<OrderItem> getItens() {
		return itens;
	}

	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Payment getPagamento() {
		return pagamento;
	}

	public void setPagamento(Payment pagamento) {
		this.pagamento = pagamento;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client cliente) {
		client = cliente;
	}

	public Address getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Address enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Orders other = (Orders) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("Pedido número: ");
		sb.append(getId());
		sb.append(", Instante: ");
		sb.append(sdf.format(getInstante()));
		sb.append(", Cliente: ");
		sb.append(getClient().getNome());
		sb.append(", Situação do pagamento: ");
		sb.append(getPagamento().getEstado().getDescricao());
		sb.append("/nDetalhes/n");
		for(OrderItem ip : getItens()){
			sb.append(ip.toString());
		}
		sb.append("Valor Total: ");
		sb.append(nf.format(getValorTotal()));
		return sb.toString();
	}


}
