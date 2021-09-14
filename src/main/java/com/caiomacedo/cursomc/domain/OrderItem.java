package com.caiomacedo.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.criterion.Order;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable {//serve para dizer que o objeto pode ser convertido em bytes
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId//passando como chave primaria um atributo composto
    private OrderItemPK id = new OrderItemPK(); // o id é um atributo composto

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public OrderItem() {

    }

    public OrderItem(Orders pedido, Product produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal() {
        return (preco - desconto) * quantidade;
    }


    @JsonIgnore
    public Orders getPedido() {
        return id.getPedido();
    }

    public void setPedido(Orders pedido) {
        id.setPedido(pedido);
    }

    public Product getProduto() {
        return id.getProduto();
    }

    public void setProduto(Product produto) {
        id.setProduto(produto);
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        StringBuilder sb = new StringBuilder();
        sb.append(getProduto().getName());
        sb.append(", Qte:");
        sb.append(getQuantidade());
        sb.append("Preço unitário: ");
        sb.append(nf.format(getPreco()));
        sb.append(", Subtotal: ");
        sb.append(nf.format(getSubTotal()));
        sb.append("/n");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



