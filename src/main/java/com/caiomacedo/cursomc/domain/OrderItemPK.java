package com.caiomacedo.cursomc.domain;

import org.hibernate.criterion.Order;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
@Embeddable //isso é pra dizer que essa classe é um subtipo da classe orderItem, ou seja uma classe auxiliar
public class OrderItemPK implements Serializable {
    private static final long serialVersionUID = 1l;

    @ManyToOne
    @JoinColumn(name ="pedido_id")
    private Orders pedido;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Product produto;

    public Orders getPedido() {
        return pedido;
    }

    public void setPedido(Orders pedido) {
        this.pedido = pedido;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return pedido.equals(that.pedido) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
