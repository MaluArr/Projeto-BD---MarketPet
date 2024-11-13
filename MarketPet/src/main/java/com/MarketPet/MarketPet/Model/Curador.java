package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Curador {
    private Integer idCurador;
    private Long cpfFuncionario;

    // Construtores
    public Curador() {}

    public Curador(Integer idCurador, Long cpfFuncionario) {
        this.idCurador = idCurador;
        this.cpfFuncionario = cpfFuncionario;
    }

    // Validações personalizadas
    public boolean isFuncionarioValido() {
        return cpfFuncionario != null;
    }

    // Getters e Setters
    public Integer getIdCurador() {
        return idCurador;
    }

    public void setIdCurador(Integer idCurador) {
        this.idCurador = idCurador;
    }

    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
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
                ", cpfFuncionario=" + (cpfFuncionario != null ? cpfFuncionario : "N/A") +
                '}';
    }
}