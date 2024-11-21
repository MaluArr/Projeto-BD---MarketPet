package com.MarketPet.MarketPet.Model;


import java.math.BigDecimal;
import java.util.Objects;


public class ProdutoResumo {


    private String codigoProduto;
    private BigDecimal totalVendas;
    private int quantidadeVendas;
    private BigDecimal avaliacaoMedia;


    // Construtores
    public ProdutoResumo() {}


    public ProdutoResumo(String codigoProduto, BigDecimal totalVendas, int quantidadeVendas, BigDecimal avaliacaoMedia) {
        this.codigoProduto = codigoProduto;
        this.totalVendas = totalVendas;
        this.quantidadeVendas = quantidadeVendas;
        this.avaliacaoMedia = avaliacaoMedia;
    }


    // Getters e Setters
    public String getCodigoProduto() {
        return codigoProduto;
    }


    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }


    public BigDecimal getTotalVendas() {
        return totalVendas;
    }


    public void setTotalVendas(BigDecimal totalVendas) {
        this.totalVendas = totalVendas;
    }


    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }


    public void setQuantidadeVendas(int quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }


    public BigDecimal getAvaliacaoMedia() {
        return avaliacaoMedia;
    }


    public void setAvaliacaoMedia(BigDecimal avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }


    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoResumo that = (ProdutoResumo) o;
        return quantidadeVendas == that.quantidadeVendas &&
                Objects.equals(codigoProduto, that.codigoProduto) &&
                Objects.equals(totalVendas, that.totalVendas) &&
                Objects.equals(avaliacaoMedia, that.avaliacaoMedia);
    }


    @Override
    public int hashCode() {
        return Objects.hash(codigoProduto, totalVendas, quantidadeVendas, avaliacaoMedia);
    }


    // Método toString
    @Override
    public String toString() {
        return "ProdutoResumo{" +
                "codigoProduto='" + codigoProduto + '\'' +
                ", totalVendas=" + totalVendas +
                ", quantidadeVendas=" + quantidadeVendas +
                ", avaliacaoMedia=" + avaliacaoMedia +
                '}';
    }
}
