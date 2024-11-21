package com.MarketPet.MarketPet.Model;


import java.math.BigDecimal;
import java.util.Objects;


public class VendedorResumo {
    private Long cpfVendedor;
    private BigDecimal totalVendas;
    private Integer quantidadeVendas;
    private BigDecimal avaliacaoMedia;


    // Construtores
    public VendedorResumo() {}


    public VendedorResumo(Long cpfVendedor, BigDecimal totalVendas, Integer quantidadeVendas, BigDecimal avaliacaoMedia) {
        this.cpfVendedor = cpfVendedor;
        this.totalVendas = totalVendas;
        this.quantidadeVendas = quantidadeVendas;
        this.avaliacaoMedia = avaliacaoMedia;
    }


    // Getters e Setters
    public Long getCpfVendedor() {
        return cpfVendedor;
    }


    public void setCpfVendedor(Long cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }


    public BigDecimal getTotalVendas() {
        return totalVendas;
    }


    public void setTotalVendas(BigDecimal totalVendas) {
        this.totalVendas = totalVendas;
    }


    public Integer getQuantidadeVendas() {
        return quantidadeVendas;
    }


    public void setQuantidadeVendas(Integer quantidadeVendas) {
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
        VendedorResumo that = (VendedorResumo) o;
        return Objects.equals(cpfVendedor, that.cpfVendedor);
    }


    @Override
    public int hashCode() {
        return Objects.hash(cpfVendedor);
    }


    // Método toString
    @Override
    public String toString() {
        return "VendedorResumo{" +
                "cpfVendedor=" + cpfVendedor +
                ", totalVendas=" + totalVendas +
                ", quantidadeVendas=" + quantidadeVendas +
                ", avaliacaoMedia=" + avaliacaoMedia +
                '}';
    }
}

