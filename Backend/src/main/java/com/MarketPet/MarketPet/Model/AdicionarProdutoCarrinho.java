package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class AdicionarProdutoCarrinho {
    private Integer id;
    private Comprador comprador;
    private Produto produto;
    private Carrinho carrinho;
    private Integer quantidade;

    // Construtores
    public AdicionarProdutoCarrinho() {
    }

    public AdicionarProdutoCarrinho(Integer id, Comprador comprador, Produto produto,
                                    Carrinho carrinho, Integer quantidade) {
        this.id = id;
        this.comprador = comprador;
        this.produto = produto;
        this.carrinho = carrinho;
        this.quantidade = quantidade;
    }

    // Validações personalizadas
    public boolean isCompradorValido() {
        return comprador != null && comprador.getCpf() != null;
    }

    public boolean isProdutoValido() {
        return produto != null && produto.getCodigoProduto() != null;
    }

    public boolean isCarrinhoValido() {
        return carrinho != null && carrinho.getIdCarrinho() != null;
    }

    public boolean isQuantidadeValida() {
        return quantidade != null && quantidade > 0;
    }

    // Verifica se o produto pertence ao comprador do carrinho
    public boolean isRelacaoCorreta() {
        return isCompradorValido() && isCarrinhoValido() &&
                carrinho.getComprador().getCpf().equals(comprador.getCpf());
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdicionarProdutoCarrinho that = (AdicionarProdutoCarrinho) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Método toString
    @Override
    public String toString() {
        return "AdicionarProdutoCarrinho{" +
                "id=" + id +
                ", comprador=" + (comprador != null ? comprador.getCpf() : "N/A") +
                ", produto=" + (produto != null ? produto.getCodigoProduto() : "N/A") +
                ", carrinho=" + (carrinho != null ? carrinho.getIdCarrinho() : "N/A") +
                ", quantidade=" + quantidade +
                '}';
    }
}