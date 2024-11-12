package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Atendente {
    private Integer idAtendente;
    private Funcionario funcionario;

    // Construtores
    public Atendente() {
    }

    public Atendente(Integer idAtendente, Funcionario funcionario) {
        this.idAtendente = idAtendente;
        this.funcionario = funcionario;
    }

    // Validações personalizadas
    public boolean isFuncionarioValido() {
        return funcionario != null && funcionario.getCpfFuncionario() != null;
    }

    // Getters e Setters
    public Integer getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Integer idAtendente) {
        this.idAtendente = idAtendente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendente atendente = (Atendente) o;
        return Objects.equals(idAtendente, atendente.idAtendente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendente);
    }

    // Método toString
    @Override
    public String toString() {
        return "Atendente{" +
                "idAtendente=" + idAtendente +
                ", funcionario=" + (funcionario != null ? funcionario.getCpfFuncionario() : "N/A") +
                '}';
    }
}