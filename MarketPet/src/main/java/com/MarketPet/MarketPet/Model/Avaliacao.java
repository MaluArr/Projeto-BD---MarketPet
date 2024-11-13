package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.util.Objects;

public class Avaliacao {
    private Integer idAvaliacao;
    private Long cpfComprador;
    private Integer codigoProduto;
    private Integer idVenda;
    private BigDecimal nota;

    // Construtores
    public Avaliacao() {}

    public Avaliacao(Integer idAvaliacao, Long cpfComprador, Integer codigoProduto,
                     Integer idVenda, BigDecimal nota) {
        this.idAvaliacao = idAvaliacao;
        this.cpfComprador = cpfComprador;
        this.codigoProduto = codigoProduto;
        this.idVenda = idVenda;
        this.nota = nota;
    }

    // Getters e Setters
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
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

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    // Métodos equals, hashCode e toString
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

    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", cpfComprador=" + cpfComprador +
                ", codigoProduto=" + codigoProduto +
                ", idVenda=" + idVenda +
                ", nota=" + nota +
                '}';
    }
}