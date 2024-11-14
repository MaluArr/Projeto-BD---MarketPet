package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Comprador {
    private Long cpf;
    private Integer idEndereco;
    private Integer idCartao;

    // Construtores
    public Comprador() {}

    public Comprador(Long cpf, Integer idEndereco, Integer idCartao) {
        this.cpf = cpf;
        this.idEndereco = idEndereco;
        this.idCartao = idCartao;
    }

    // Validações personalizadas
    public boolean isEnderecoValido() {
        return idEndereco != null;
    }

    public boolean isCartaoValido() {
        return idCartao != null;
    }

    // Getters e Setters
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return Objects.equals(cpf, comprador.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    // Método toString
    @Override
    public String toString() {
        return "Comprador{" +
                "cpf=" + cpf +
                ", idEndereco=" + (idEndereco != null ? idEndereco : "N/A") +
                ", idCartao=" + (idCartao != null ? idCartao : "N/A") +
                '}';
    }
}