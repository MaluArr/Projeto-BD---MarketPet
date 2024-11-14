package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class FavoritarProduto {
    private Integer idLista;
    private Long cpfComprador;
    private Integer codigoProduto;
    private String nomeLista;
    private LocalDate dataCriacao;

    // Construtores
    public FavoritarProduto() {}

    public FavoritarProduto(Integer idLista, Long cpfComprador, Integer codigoProduto,
                            String nomeLista, LocalDate dataCriacao) {
        this.idLista = idLista;
        this.cpfComprador = cpfComprador;
        this.codigoProduto = codigoProduto;
        this.nomeLista = nomeLista;
        this.dataCriacao = dataCriacao;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return cpfComprador != null;
    }

    public boolean isProdutoValido() {
        return codigoProduto != null;
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

    public Long getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(Long cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
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
                ", cpfComprador=" + cpfComprador +
                ", codigoProduto=" + codigoProduto +
                ", nomeLista='" + nomeLista + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}