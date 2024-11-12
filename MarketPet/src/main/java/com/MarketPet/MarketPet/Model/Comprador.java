package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Comprador {
    private Long cpf;
    private Endereco endereco;
    private Cartao cartao;

    // Construtores
    public Comprador() {
    }

    public Comprador(Long cpf, Endereco endereco, Cartao cartao) {
        this.cpf = cpf;
        this.endereco = endereco;
        this.cartao = cartao;
    }

    // Validações personalizadas
    public boolean isEnderecoValido() {
        return endereco != null && endereco.getIdEndereco() != null;
    }

    public boolean isCartaoValido() {
        return cartao != null && cartao.getIdCartao() != null;
    }

    // Getters e Setters
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
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
                ", endereco=" + (endereco != null ? endereco.getIdEndereco() : "N/A") +
                ", cartao=" + (cartao != null ? cartao.getIdCartao() : "N/A") +
                '}';
    }
}