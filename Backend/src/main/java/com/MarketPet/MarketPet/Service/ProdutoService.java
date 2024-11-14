package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Produto;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorCodigo(Integer codigoProduto) {
        return produtoRepository.findByCodigo(codigoProduto);
    }

    public Produto criarProduto(Produto produto) {
        // Validações
        if (!produto.isPrecoValido()) {
            throw new RuntimeException("Preço inválido");
        }

        if (!produto.isDescontoValido()) {
            throw new RuntimeException("Desconto inválido");
        }

        if (!produto.isNotaValida()) {
            throw new RuntimeException("Nota inválida");
        }

        // Limite de 6 fotos
        if (produto.getFotos() != null && produto.getFotos().size() > 6) {
            throw new RuntimeException("Máximo de 6 fotos permitidas");
        }

        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Produto produto) {
        // Verifica se o produto existe
        produtoRepository.findByCodigo(produto.getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Validações
        if (!produto.isPrecoValido()) {
            throw new RuntimeException("Preço inválido");
        }

        if (!produto.isDescontoValido()) {
            throw new RuntimeException("Desconto inválido");
        }

        if (!produto.isNotaValida()) {
            throw new RuntimeException("Nota inválida");
        }

        // Limite de 6 fotos
        if (produto.getFotos() != null && produto.getFotos().size() > 6) {
            throw new RuntimeException("Máximo de 6 fotos permitidas");
        }

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer codigoProduto) {
        produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(codigoProduto);
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    public List<Produto> buscarProdutosAbaixoDe(BigDecimal preco) {
        return produtoRepository.findByPrecoMenorQue(preco);
    }

    public List<Produto> buscarProdutosAcimaDe(BigDecimal preco) {
        return produtoRepository.findByPrecoMaiorQue(preco);
    }

    public List<Produto> buscarProdutosComNotaMaiorQue(BigDecimal nota) {
        return produtoRepository.findByNotaMaiorQue(nota);
    }

    public BigDecimal calcularPrecoComDesconto(Integer codigoProduto) {
        Produto produto = produtoRepository.findByCodigo(codigoProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return produto.calcularPrecoComDesconto();
    }
}