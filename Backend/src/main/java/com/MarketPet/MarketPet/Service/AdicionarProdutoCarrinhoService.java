package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import com.MarketPet.MarketPet.Repository.AdicionarProdutoCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdicionarProdutoCarrinhoService {

    @Autowired
    private AdicionarProdutoCarrinhoRepository repository;

    public List<AdicionarProdutoCarrinho> listarTodos() {
        return repository.buscarTodosProdutosCarrinho();
    }

    public Optional<AdicionarProdutoCarrinho> buscarPorId(Integer id) {
        return repository.buscarProdutoCarrinhoPorId(id);
    }

    @Transactional
    public AdicionarProdutoCarrinho adicionarProdutoCarrinho(AdicionarProdutoCarrinho produtoCarrinho) {
        validarProdutoCarrinho(produtoCarrinho);
        return repository.salvarProdutoCarrinho(produtoCarrinho);
    }

    @Transactional
    public AdicionarProdutoCarrinho atualizarProdutoCarrinho(AdicionarProdutoCarrinho produtoCarrinho) {
        repository.buscarProdutoCarrinhoPorId(produtoCarrinho.getId())
                .orElseThrow(() -> new RuntimeException("Item de carrinho não encontrado"));
        validarProdutoCarrinho(produtoCarrinho);
        return repository.salvarProdutoCarrinho(produtoCarrinho);
    }

    public void deletarProdutoCarrinho(Integer id) {
        repository.buscarProdutoCarrinhoPorId(id)
                .orElseThrow(() -> new RuntimeException("Item de carrinho não encontrado"));
        repository.deletarProdutoCarrinho(id);
    }

    private void validarProdutoCarrinho(AdicionarProdutoCarrinho produtoCarrinho) {
        if (produtoCarrinho == null || !produtoCarrinho.isCompradorValido() ||
                !produtoCarrinho.isProdutoValido() || !produtoCarrinho.isCarrinhoValido() ||
                !produtoCarrinho.isQuantidadeValida()) {
            throw new RuntimeException("Dados inválidos do produto no carrinho");
        }
    }
}