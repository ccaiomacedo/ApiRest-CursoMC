package com.caiomacedo.cursomc.domain;

import javax.persistence.Entity;

import com.caiomacedo.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1l;
	private Integer numeroDeParcelas;

	public CardPayment() {

	}

	public CardPayment(Integer id, PaymentStatus estado, Orders pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	
}
