package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Carrinho {
    private Integer idCarrinho;
    private BigDecimal valorTotal;
    private Long cpfComprador;

    // Construtores
    public Carrinho() {}

    public Carrinho(Integer idCarrinho, BigDecimal valorTotal, Long cpfComprador) {
        this.idCarrinho = idCarrinho;
        this.valorTotal = valorTotal;
        this.cpfComprador = cpfComprador;
    }

    // Getters e Setters
    public Integer getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(Long cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    // Métodos equals, hashCode e toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(idCarrinho, carrinho.idCarrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrinho);
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "idCarrinho=" + idCarrinho +
                ", valorTotal=" + valorTotal +
                ", cpfComprador=" + cpfComprador +
                '}';
    }
}