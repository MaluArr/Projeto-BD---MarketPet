package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Atendimento {
    private Integer idAtendimento;
    private Funcionario funcionario;
    private Usuario usuario;
    private Chat chat;
    private LocalDate dataAtendimento;
    private String categoria;

    // Construtores
    public Atendimento() {
    }

    public Atendimento(Integer idAtendimento, Funcionario funcionario, Usuario usuario,
                       Chat chat, LocalDate dataAtendimento, String categoria) {
        this.idAtendimento = idAtendimento;
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.chat = chat;
        this.dataAtendimento = dataAtendimento;
        this.categoria = categoria;
    }

    // Validações personalizadas
    public boolean isFuncionarioValido() {
        return funcionario != null && funcionario.getCpfFuncionario() != null;
    }

    public boolean isUsuarioValido() {
        return usuario != null && usuario.getCpf() != null;
    }

    public boolean isChatValido() {
        return chat != null && chat.getIdChat() != null;
    }

    public boolean isCategoriaValida() {
        return categoria != null && !categoria.trim().isEmpty() && categoria.length() <= 255;
    }

    public boolean isDataAtendimentoValida() {
        return dataAtendimento != null && !dataAtendimento.isAfter(LocalDate.now());
    }

    // Getters e Setters
    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return Objects.equals(idAtendimento, that.idAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento);
    }

    // Método toString
    @Override
    public String toString() {
        return "Atendimento{" +
                "idAtendimento=" + idAtendimento +
                ", funcionario=" + (funcionario != null ? funcionario.getCpfFuncionario() : "N/A") +
                ", usuario=" + (usuario != null ? usuario.getCpf() : "N/A") +
                ", chat=" + (chat != null ? chat.getIdChat() : "N/A") +
                ", dataAtendimento=" + dataAtendimento +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}