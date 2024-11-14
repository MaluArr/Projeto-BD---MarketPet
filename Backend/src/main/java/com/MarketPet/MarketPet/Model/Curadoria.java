package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Curadoria {
    private Integer codigoCuradoria;
    private String descricao;
    private String resultadoCuradoria;
    private Integer idCurador;
    private Integer codigoProduto;

    // Construtores
    public Curadoria() {}

    public Curadoria(Integer codigoCuradoria, String descricao, String resultadoCuradoria,
                     Integer idCurador, Integer codigoProduto) {
        this.codigoCuradoria = codigoCuradoria;
        this.descricao = descricao;
        this.resultadoCuradoria = resultadoCuradoria;
        this.idCurador = idCurador;
        this.codigoProduto = codigoProduto;
    }

    // Validações personalizadas
    public boolean isCuradorValido() {
        return idCurador != null;
    }

    public boolean isProdutoValido() {
        return codigoProduto != null;
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

    public Integer getIdCurador() {
        return idCurador;
    }

    public void setIdCurador(Integer idCurador) {
        this.idCurador = idCurador;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
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
                ", idCurador=" + (idCurador != null ? idCurador : "N/A") +
                ", codigoProduto=" + (codigoProduto != null ? codigoProduto : "N/A") +
                '}';
    }
}