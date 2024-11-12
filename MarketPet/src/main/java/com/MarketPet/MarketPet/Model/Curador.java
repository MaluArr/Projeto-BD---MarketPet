package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Curador {
    private Integer idCurador;
    private Funcionario funcionario;

    // Construtores
    public Curador() {
    }

    public Curador(Integer idCurador, Funcionario funcionario) {
        this.idCurador = idCurador;
        this.funcionario = funcionario;
    }

    // Validações personalizadas
    public boolean isFuncionarioValido() {
        return funcionario != null && funcionario.getCpfFuncionario() != null;
    }

    // Getters e Setters
    public Integer getIdCurador() {
        return idCurador;
    }

    public void setIdCurador(Integer idCurador) {
        this.idCurador = idCurador;
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
        Curador curador = (Curador) o;
        return Objects.equals(idCurador, curador.idCurador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurador);
    }

    // Método toString
    @Override
    public String toString() {
        return "Curador{" +
                "idCurador=" + idCurador +
                ", funcionario=" + (funcionario != null ? funcionario.getCpfFuncionario() : "N/A") +
                '}';
    }
}