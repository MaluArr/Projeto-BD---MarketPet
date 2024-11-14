package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Cartao {
    private Integer idCartao;
    private String numero;
    private String nomeTitular;
    private LocalDate validade;
    private String cvv;
    private String bandeira;

    // Construtores
    public Cartao() {}

    public Cartao(Integer idCartao, String numero, String nomeTitular,
                  LocalDate validade, String cvv, String bandeira) {
        this.idCartao = idCartao;
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
        this.bandeira = bandeira;
    }

    // Validações personalizadas
    public boolean isNumeroCartaoValido() {
        return numero != null && numero.matches("\\d{16}");
    }

    public boolean isCvvValido() {
        return cvv != null && cvv.matches("\\d{3,4}");
    }

    public boolean isValidadeValida() {
        return validade != null && !validade.isBefore(LocalDate.now());
    }

    // Getters e Setters
    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(idCartao, cartao.idCartao) &&
                Objects.equals(numero, cartao.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCartao, numero);
    }

    // Método toString
    @Override
    public String toString() {
        return "Cartao{" +
                "idCartao=" + idCartao +
                ", numero='" + numero.replaceAll("\\d{12}", "************") + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", validade=" + validade +
                ", bandeira='" + bandeira + '\'' +
                '}';
    }
}