package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Avaliacao {
    private Integer idAvaliacao;
    private Comprador comprador;
    private Produto produto;
    private Venda venda;
    private BigDecimal nota;

    // Construtores
    public Avaliacao() {
    }

    public Avaliacao(Integer idAvaliacao, Comprador comprador, Produto produto,
                     Venda venda, BigDecimal nota) {
        this.idAvaliacao = idAvaliacao;
        this.comprador = comprador;
        this.produto = produto;
        this.venda = venda;
        this.nota = nota;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isVendaValida() {
        return venda != null && venda.getIdVenda() != null;
    }

    public boolean isNotaValida() {
        return nota != null &&
                nota.compareTo(BigDecimal.ZERO) >= 0 &&
                nota.compareTo(new BigDecimal("5.00")) <= 0;
    }

    // Verifica se a avaliação corresponde ao comprador e produto da venda
    public boolean isAvaliacaoConsistente() {
        return isCompradorValido() &&
                isProdutoValido() &&
                isVendaValida() &&
                venda.getComprador().getCpf().equals(comprador.getCpf()) &&
                venda.getProduto().getCodigoProduto().equals(produto.getCodigoProduto());
    }

    // Getters e Setters
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(idAvaliacao, avaliacao.idAvaliacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAvaliacao);
    }

    // Método toString
    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                ", venda=" + (venda != null ? venda.getIdVenda() : "N/A") +
                ", nota=" + nota +
                '}';
    }
}