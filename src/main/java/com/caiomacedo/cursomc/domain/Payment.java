package com.caiomacedo.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.caiomacedo.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // forma de mapear uma herança
public abstract class Payment implements Serializable {// serve para dizer que o objeto pode ser convertido em bytes
	private static final long serialVersionUID = 1l;

	@Id
	private Integer id;
	private Integer estado;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId // para o id de pedidos ser o mesmo que o de pagamento
	private Orders pedido;

	public Payment() {

	}

	public Payment(Integer id, PaymentStatus estado, Orders pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatus getEstado() {
		return PaymentStatus.toEnum(estado);
	}

	public void setEstado(PaymentStatus estado) {
		this.estado = estado.getCod();
	}

	public Orders getPedido() {
		return pedido;
	}

	public void setPedido(Orders pedido) {
		this.pedido = pedido;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
