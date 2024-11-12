package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class FavoritarProduto {
    private Integer idLista;
    private Comprador comprador;
    private Produto produto;
    private String nomeLista;
    private LocalDate dataCriacao;

    // Construtores
    public FavoritarProduto() {
    }

    public FavoritarProduto(Integer idLista, Comprador comprador, Produto produto,
                            String nomeLista, LocalDate dataCriacao) {
        this.idLista = idLista;
        this.comprador = comprador;
        this.produto = produto;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isNomeListaValido() {
        return nomeLista != null && !nomeLista.trim().isEmpty() && nomeLista.length() <= 255;
    }

    public boolean isDataCriacaoValida() {
        return dataCriacao != null && !dataCriacao.isAfter(LocalDate.now());
    }

    // Getters e Setters
    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
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

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritarProduto that = (FavoritarProduto) o;
        return Objects.equals(idLista, that.idLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLista);
    }

    // Método toString
    @Override
    public String toString() {
        return "FavoritarProduto{" +
                "idLista=" + idLista +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                ", nomeLista='" + nomeLista + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}