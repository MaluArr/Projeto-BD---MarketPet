package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Chat {
    private Integer idChat;
    private Long cpfVendedor;
    private Long cpfComprador;
    private String codigoChatvc;
    private String conteudo;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualizacao;

    // Construtores
    public Chat() {}

    public Chat(Integer idChat, Long cpfVendedor, Long cpfComprador,
                String codigoChatvc, String conteudo,
                LocalDate dataCriacao, LocalDate ultimaAtualizacao) {
        this.idChat = idChat;
        this.cpfVendedor = cpfVendedor;
        this.cpfComprador = cpfComprador;
        this.codigoChatvc = codigoChatvc;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Validações personalizadas
    public boolean isCodigoChatvcValido() {
        return codigoChatvc != null && codigoChatvc.length() <= 20;
    }

    public boolean isDatasValidas() {
        return dataCriacao != null &&
                ultimaAtualizacao != null &&
                !ultimaAtualizacao.isBefore(dataCriacao);
    }

    // Getters e Setters
    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Long getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(Long cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    public Long getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(Long cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public String getCodigoChatvc() {
        return codigoChatvc;
    }

    public void setCodigoChatvc(String codigoChatvc) {
        this.codigoChatvc = codigoChatvc;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(idChat, chat.idChat) &&
                Objects.equals(codigoChatvc, chat.codigoChatvc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChat, codigoChatvc);
    }

    // Método toString
    @Override
    public String toString() {
        return "Chat{" +
                "idChat=" + idChat +
                ", cpfVendedor=" + cpfVendedor +
                ", cpfComprador=" + cpfComprador +
                ", codigoChatvc='" + codigoChatvc + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", ultimaAtualizacao=" + ultimaAtualizacao +
                '}';
    }
}