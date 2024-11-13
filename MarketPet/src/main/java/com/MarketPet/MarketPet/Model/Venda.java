package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Venda {
    private Integer idVenda;
    private Long cpfComprador;
    private Integer codigoProduto;
    private LocalDate dataVenda;

    // Construtores
    public Venda() {}

    public Venda(Integer idVenda, Long cpfComprador, Integer codigoProduto, LocalDate dataVenda) {
        this.idVenda = idVenda;
        this.cpfComprador = cpfComprador;
        this.codigoProduto = codigoProduto;
        this.dataVenda = dataVenda;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return cpfComprador != null;
    }

    public boolean isProdutoValido() {
        return codigoProduto != null;
    }

    public boolean isDataVendaValida() {
        return dataVenda != null && !dataVenda.isAfter(LocalDate.now());
    }

    // Getters e Setters
    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
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

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(idVenda, venda.idVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenda);
    }

    // Método toString
    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", cpfComprador=" + cpfComprador +
                ", codigoProduto=" + codigoProduto +
                ", dataVenda=" + dataVenda +
                '}';
    }
}