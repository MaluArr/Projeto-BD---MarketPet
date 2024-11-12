package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Carrinho {
    private Integer idCarrinho;
    private BigDecimal valorTotal;
    private Comprador comprador;

    // Construtores
    public Carrinho() {
    }

    public Carrinho(Integer idCarrinho, BigDecimal valorTotal, Comprador comprador) {
        this.idCarrinho = idCarrinho;
        this.valorTotal = valorTotal;
        this.comprador = comprador;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isValorTotalValido() {
        return valorTotal != null && valorTotal.compareTo(BigDecimal.ZERO) >= 0;
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

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    // Métodos equals e hashCode
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

    // Método toString
    @Override
    public String toString() {
        return "Carrinho{" +
                "idCarrinho=" + idCarrinho +
                ", valorTotal=" + valorTotal +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                '}';
    }
}