package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class BuscaProduto {
    private Integer idBusca;
    private Comprador comprador;
    private Produto produto;
    private Float preco;
    private String regiao;
    private String categoria;
    private String cor;
    private String tamanho;
    private Float avaliacao;
    private String descricaoBusca;

    // Construtores
    public BuscaProduto() {
    }

    public BuscaProduto(Integer idBusca, Comprador comprador, Produto produto,
                        Float preco, String regiao, String categoria,
                        String cor, String tamanho, Float avaliacao,
                        String descricaoBusca) {
        this.idBusca = idBusca;
        this.comprador = comprador;
        this.produto = produto;
        this.preco = preco;
        this.regiao = regiao;
        this.categoria = categoria;
        this.cor = cor;
        this.tamanho = tamanho;
        this.avaliacao = avaliacao;
        this.descricaoBusca = descricaoBusca;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isPrecoValido() {
        return preco != null && preco > 0;
    }

    public boolean isCategoriaValida() {
        return categoria != null && !categoria.trim().isEmpty() && categoria.length() <= 255;
    }

    public boolean isTamanhoValido() {
        return tamanho != null && !tamanho.trim().isEmpty() && tamanho.length() <= 255;
    }

    public boolean isAvaliacaoValida() {
        return avaliacao == null ||
                (avaliacao >= 0 && avaliacao <= 5);
    }

    public boolean isDescricaoBuscaValida() {
        return descricaoBusca != null && !descricaoBusca.trim().isEmpty();
    }

    // Getters e Setters
    public Integer getIdBusca() {
        return idBusca;
    }

    public void setIdBusca(Integer idBusca) {
        this.idBusca = idBusca;
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

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
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

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricaoBusca() {
        return descricaoBusca;
    }

    public void setDescricaoBusca(String descricaoBusca) {
        this.descricaoBusca = descricaoBusca;
    }

    // Métodos equals e hashCode
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

    // Método toString
    @Override
    public String toString() {
        return "BuscaProduto{" +
                "idBusca=" + idBusca +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                ", preco=" + preco +
                ", regiao='" + regiao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", avaliacao=" + avaliacao +
                '}';
    }
}