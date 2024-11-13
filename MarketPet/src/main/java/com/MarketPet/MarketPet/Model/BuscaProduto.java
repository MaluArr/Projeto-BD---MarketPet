package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class BuscaProduto {
    private Integer idBusca;
    private Long cpfComprador;
    private Integer codigoProduto;
    private BigDecimal preco;
    private String regiao;
    private String categoria;
    private String cor;
    private String tamanho;
    private BigDecimal avaliacao;
    private String descricaoBusca;

    // Construtores
    public BuscaProduto() {}

    public BuscaProduto(Integer idBusca, Long cpfComprador, Integer codigoProduto,
                        BigDecimal preco, String regiao, String categoria,
                        String cor, String tamanho, BigDecimal avaliacao,
                        String descricaoBusca) {
        this.idBusca = idBusca;
        this.cpfComprador = cpfComprador;
        this.codigoProduto = codigoProduto;
        this.preco = preco;
        this.regiao = regiao;
        this.categoria = categoria;
        this.cor = cor;
        this.tamanho = tamanho;
        this.avaliacao = avaliacao;
        this.descricaoBusca = descricaoBusca;
    }

    // Getters e Setters
    public Integer getIdBusca() {
        return idBusca;
    }

    public void setIdBusca(Integer idBusca) {
        this.idBusca = idBusca;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(BigDecimal avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricaoBusca() {
        return descricaoBusca;
    }

    public void setDescricaoBusca(String descricaoBusca) {
        this.descricaoBusca = descricaoBusca;
    }

    // Métodos equals, hashCode e toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuscaProduto that = (BuscaProduto) o;
        return Objects.equals(idBusca, that.idBusca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBusca);
    }

    @Override
    public String toString() {
        return "BuscaProduto{" +
                "idBusca=" + idBusca +
                ", cpfComprador=" + cpfComprador +
                ", codigoProduto=" + codigoProduto +
                ", preco=" + preco +
                ", regiao='" + regiao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", avaliacao=" + avaliacao +
                ", descricaoBusca='" + descricaoBusca + '\'' +
                '}';
    }
}