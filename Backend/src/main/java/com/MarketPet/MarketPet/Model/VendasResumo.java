package com.MarketPet.MarketPet.Model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


public class VendasResumo {
    private Date data;
    private BigDecimal totalVendas;
    private int quantidadeVendas;


    // Construtores
    public VendasResumo() {}


    public VendasResumo(Date data, BigDecimal totalVendas, int quantidadeVendas) {
        this.data = data;
        this.totalVendas = totalVendas;
        this.quantidadeVendas = quantidadeVendas;
    }


    // Getters e Setters
    public Date getData() {
        return data;
    }


    public void setData(Date data) {
        this.data = data;
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


    // Métodos equals, hashCode e toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendasResumo that = (VendasResumo) o;
        return quantidadeVendas == that.quantidadeVendas &&
                Objects.equals(data, that.data) &&
                Objects.equals(totalVendas, that.totalVendas);
    }


    @Override
    public int hashCode() {
        return Objects.hash(data, totalVendas, quantidadeVendas);
    }


    @Override
    public String toString() {
        return "VendasResumo{" +
                "data=" + data +
                ", totalVendas=" + totalVendas +
                ", quantidadeVendas=" + quantidadeVendas +
                '}';
    }
}
