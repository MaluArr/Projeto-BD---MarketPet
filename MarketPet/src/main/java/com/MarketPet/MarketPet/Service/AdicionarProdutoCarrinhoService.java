package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import com.MarketPet.MarketPet.Repository.AdicionarProdutoCarrinhoRepository;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import com.MarketPet.MarketPet.Repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AdicionarProdutoCarrinhoService {

    @Autowired
    private AdicionarProdutoCarrinhoRepository adicionarProdutoCarrinhoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public List<AdicionarProdutoCarrinho> listarTodos() {
        return adicionarProdutoCarrinhoRepository.findAll();
    }

    public Optional<AdicionarProdutoCarrinho> buscarPorId(Integer id) {
        return adicionarProdutoCarrinhoRepository.findById(id);
    }

    @Transactional
    public AdicionarProdutoCarrinho adicionarProdutoCarrinho(AdicionarProdutoCarrinho adicionarProdutoCarrinho) {
        // Validações
        if (!adicionarProdutoCarrinho.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!adicionarProdutoCarrinho.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!adicionarProdutoCarrinho.isCarrinhoValido()) {
            throw new RuntimeException("Carrinho inválido");
        }

        if (!adicionarProdutoCarrinho.isQuantidadeValida()) {
            throw new RuntimeException("Quantidade inválida");
        }

        if (!adicionarProdutoCarrinho.isRelacaoCorreta()) {
            throw new RuntimeException("Carrinho não pertence ao comprador");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(adicionarProdutoCarrinho.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(adicionarProdutoCarrinho.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Verifica existência do carrinho
        carrinhoRepository.findById(adicionarProdutoCarrinho.getCarrinho().getIdCarrinho())
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Verifica se o produto já está no carrinho
        Optional<AdicionarProdutoCarrinho> produtoExistente = adicionarProdutoCarrinhoRepository
                .findByCarrinhoAndProduto(
                        adicionarProdutoCarrinho.getCarrinho().getIdCarrinho(),
                        adicionarProdutoCarrinho.getProduto().getCodigoProduto()
                );

        if (produtoExistente.isPresent()) {
            // Atualiza a quantidade se o produto já existir
            AdicionarProdutoCarrinho existente = produtoExistente.get();
            existente.setQuantidade(existente.getQuantidade() + adicionarProdutoCarrinho.getQuantidade());
            return adicionarProdutoCarrinhoRepository.save(existente);
        }

        return adicionarProdutoCarrinhoRepository.save(adicionarProdutoCarrinho);
    }

    @Transactional
    public AdicionarProdutoCarrinho atualizarProdutoCarrinho(AdicionarProdutoCarrinho adicionarProdutoCarrinho) {
        // Verifica se o item de carrinho existe
        adicionarProdutoCarrinhoRepository.findById(adicionarProdutoCarrinho.getId())
                .orElseThrow(() -> new RuntimeException("Item de carrinho não encontrado"));

        // Validações
        if (!adicionarProdutoCarrinho.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!adicionarProdutoCarrinho.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!adicionarProdutoCarrinho.isCarrinhoValido()) {
            throw new RuntimeException("Carrinho inválido");
        }

        if (!adicionarProdutoCarrinho.isQuantidadeValida()) {
            throw new RuntimeException("Quantidade inválida");
        }

        if (!adicionarProdutoCarrinho.isRelacaoCorreta()) {
            throw new RuntimeException("Carrinho não pertence ao comprador");
        }

        return adicionarProdutoCarrinhoRepository.save(adicionarProdutoCarrinho);
    }

    public void deletarProdutoCarrinho(Integer id) {
        adicionarProdutoCarrinhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de carrinho não encontrado"));

        adicionarProdutoCarrinhoRepository.delete(id);
    }

    public List<AdicionarProdutoCarrinho> buscarPorComprador(Long cpfComprador) {
        return adicionarProdutoCarrinhoRepository.findByComprador(cpfComprador);
    }

    public List<AdicionarProdutoCarrinho> buscarPorProduto(Integer codigoProduto) {
        return adicionarProdutoCarrinhoRepository.findByProduto(codigoProduto);
    }

    public List<AdicionarProdutoCarrinho> buscarPorCarrinho(Integer idCarrinho) {
        return adicionarProdutoCarrinhoRepository.findByCarrinho(idCarrinho);
    }
}