package com.caiomacedo.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.caiomacedo.cursomc.domain.enums.PaymentStatus;

@Entity
public class BilletPayment extends Payment {
	private static final long serialVersionUID = 1l;
	private Date dataVencimento;
	private Date dataPagamento;

	BilletPayment() {

	}

	public BilletPayment(Integer id, PaymentStatus estado, Request pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
