package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Curadoria {
    private Integer codigoCuradoria;
    private String descricao;
    private String resultadoCuradoria;
    private Curador curador;
    private Produto produto;

    // Construtores
    public Curadoria() {
    }

    public Curadoria(Integer codigoCuradoria, String descricao, String resultadoCuradoria,
                     Curador curador, Produto produto) {
        this.codigoCuradoria = codigoCuradoria;
        this.descricao = descricao;
        this.resultadoCuradoria = resultadoCuradoria;
        this.curador = curador;
        this.produto = produto;
    }

    // Validações personalizadas
    public boolean isCuradorValido() {
        return curador != null && curador.getIdCurador() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isDescricaoValida() {
        return descricao != null && !descricao.trim().isEmpty();
    }

    // Getters e Setters
    public Integer getCodigoCuradoria() {
        return codigoCuradoria;
    }

    public void setCodigoCuradoria(Integer codigoCuradoria) {
        this.codigoCuradoria = codigoCuradoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResultadoCuradoria() {
        return resultadoCuradoria;
    }

    public void setResultadoCuradoria(String resultadoCuradoria) {
        this.resultadoCuradoria = resultadoCuradoria;
    }

    public Curador getCurador() {
        return curador;
    }

    public void setCurador(Curador curador) {
        this.curador = curador;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curadoria curadoria = (Curadoria) o;
        return Objects.equals(codigoCuradoria, curadoria.codigoCuradoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCuradoria);
    }

    // Método toString
    @Override
    public String toString() {
        return "Curadoria{" +
                "codigoCuradoria=" + codigoCuradoria +
                ", descricao='" + descricao + '\'' +
                ", resultadoCuradoria='" + resultadoCuradoria + '\'' +
                ", curador=" + (curador != null ? curador.getIdCurador() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                '}';
    }
}