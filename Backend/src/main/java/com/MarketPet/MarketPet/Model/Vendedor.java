package com.MarketPet.MarketPet.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Vendedor {
    private Long cpf;
    private String descricao;
    private String fotoPerfil;
    private BigDecimal totalVendas;
    private BigDecimal avaliacaoMedia;
    private LocalDate dataInicioVendas;

    // Construtores
    public Vendedor() {}

    public Vendedor(Long cpf, String descricao, String fotoPerfil,
                    BigDecimal totalVendas, BigDecimal avaliacaoMedia,
                    LocalDate dataInicioVendas) {
        this.cpf = cpf;
        this.descricao = descricao;
        this.fotoPerfil = fotoPerfil;
        this.totalVendas = totalVendas;
        this.avaliacaoMedia = avaliacaoMedia;
        this.dataInicioVendas = dataInicioVendas;
    }

    // Validações personalizadas
    public boolean isAvaliacaoValida() {
        return avaliacaoMedia != null &&
                avaliacaoMedia.compareTo(BigDecimal.ZERO) >= 0 &&
                avaliacaoMedia.compareTo(new BigDecimal("5.0")) <= 0;
    }

    public boolean isTotalVendasValido() {
        return totalVendas != null && totalVendas.compareTo(BigDecimal.ZERO) >= 0;
    }

    // Getters e Setters
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public BigDecimal getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(BigDecimal totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(BigDecimal avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public LocalDate getDataInicioVendas() {
        return dataInicioVendas;
    }

    public void setDataInicioVendas(LocalDate dataInicioVendas) {
        this.dataInicioVendas = dataInicioVendas;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(cpf, vendedor.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    // Método toString
    @Override
    public String toString() {
        return "Vendedor{" +
                "cpf=" + cpf +
                ", descricao='" + descricao + '\'' +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                ", totalVendas=" + totalVendas +
                ", avaliacaoMedia=" + avaliacaoMedia +
                ", dataInicioVendas=" + dataInicioVendas +
                '}';
    }
}