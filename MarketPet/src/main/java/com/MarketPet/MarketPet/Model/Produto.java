package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Produto {
    private Integer codigoProduto;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private String dimensoes;
    private List<String> fotos;
    private BigDecimal desconto;
    private BigDecimal comissaoMkp;
    private BigDecimal nota;

    // Construtores
    public Produto() {
        this.fotos = new ArrayList<>(6);
    }

    public Produto(Integer codigoProduto, String descricao, String categoria,
                   BigDecimal preco, String dimensoes, List<String> fotos,
                   BigDecimal desconto, BigDecimal comissaoMkp, BigDecimal nota) {
        this.codigoProduto = codigoProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.dimensoes = dimensoes;
        this.fotos = fotos != null ? fotos : new ArrayList<>(6);
        this.desconto = desconto;
        this.comissaoMkp = comissaoMkp;
        this.nota = nota;
    }

    // Validações personalizadas
    public boolean isPrecoValido() {
        return preco != null && preco.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isDescontoValido() {
        return desconto != null &&
                desconto.compareTo(BigDecimal.ZERO) >= 0 &&
                desconto.compareTo(new BigDecimal("100")) <= 0;
    }

    public boolean isNotaValida() {
        return nota != null &&
                nota.compareTo(BigDecimal.ZERO) >= 0 &&
                nota.compareTo(new BigDecimal("5")) <= 0;
    }

    // Métodos para manipulação de fotos
    public void adicionarFoto(String foto) {
        if (fotos == null) {
            fotos = new ArrayList<>(6);
        }
        if (fotos.size() < 6) {
            fotos.add(foto);
        }
    }

    public BigDecimal calcularPrecoComDesconto() {
        if (preco == null || desconto == null) {
            return preco;
        }
        BigDecimal descontoDecimal = desconto.divide(new BigDecimal("100"));
        return preco.subtract(preco.multiply(descontoDecimal));
    }

    // Getters e Setters
    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getComissaoMkp() {
        return comissaoMkp;
    }

    public void setComissaoMkp(BigDecimal comissaoMkp) {
        this.comissaoMkp = comissaoMkp;
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
        Produto produto = (Produto) o;
        return Objects.equals(codigoProduto, produto.codigoProduto) &&
                Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoProduto, descricao);
    }

    // Método toString
    @Override
    public String toString() {
        return "Produto{" +
                "codigoProduto=" + codigoProduto +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                ", desconto=" + desconto +
                ", nota=" + nota +
                ", fotos=" + (fotos != null ? fotos.size() : "0") +
                '}';
    }
}