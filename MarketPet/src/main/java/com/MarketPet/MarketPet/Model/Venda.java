package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Venda {
    private Integer idVenda;
    private Comprador comprador;
    private Produto produto;
    private LocalDate dataVenda;

    // Construtores
    public Venda() {
    }

    public Venda(Integer idVenda, Comprador comprador, Produto produto, LocalDate dataVenda) {
        this.idVenda = idVenda;
        this.comprador = comprador;
        this.produto = produto;
        this.dataVenda = dataVenda;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isDataVendaValida() {
        return dataVenda != null && !dataVenda.isAfter(LocalDate.now());
    }

    // Getters e Setters
    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(idVenda, venda.idVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenda);
    }

    // Método toString
    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                ", dataVenda=" + dataVenda +
                '}';
    }
}