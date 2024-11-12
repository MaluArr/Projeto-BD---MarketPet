package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import com.MarketPet.MarketPet.Repository.BuscaProdutoRepository;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscaProdutoService {

    @Autowired
    private BuscaProdutoRepository buscaProdutoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<BuscaProduto> listarTodos() {
        return buscaProdutoRepository.findAll();
    }

    public Optional<BuscaProduto> buscarPorId(Integer idBusca) {
        return buscaProdutoRepository.findById(idBusca);
    }

    public BuscaProduto criarBuscaProduto(BuscaProduto buscaProduto) {
        // Validações
        if (!buscaProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!buscaProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!buscaProduto.isPrecoValido()) {
            throw new RuntimeException("Preço inválido");
        }

        if (!buscaProduto.isCategoriaValida()) {
            throw new RuntimeException("Categoria inválida");
        }

        if (!buscaProduto.isTamanhoValido()) {
            throw new RuntimeException("Tamanho inválido");
        }

        if (!buscaProduto.isAvaliacaoValida()) {
            throw new RuntimeException("Avaliação inválida");
        }

        if (!buscaProduto.isDescricaoBuscaValida()) {
            throw new RuntimeException("Descrição da busca inválida");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(buscaProduto.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(buscaProduto.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return buscaProdutoRepository.save(buscaProduto);
    }

    public BuscaProduto atualizarBuscaProduto(BuscaProduto buscaProduto) {
        // Verifica se a busca de produto existe
        buscaProdutoRepository.findById(buscaProduto.getIdBusca())
                .orElseThrow(() -> new RuntimeException("Busca de produto não encontrada"));

        // Validações
        if (!buscaProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!buscaProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!buscaProduto.isPrecoValido()) {
            throw new RuntimeException("Preço inválido");
        }

        if (!buscaProduto.isCategoriaValida()) {
            throw new RuntimeException("Categoria inválida");
        }

        if (!buscaProduto.isTamanhoValido()) {
            throw new RuntimeException("Tamanho inválido");
        }

        if (!buscaProduto.isAvaliacaoValida()) {
            throw new RuntimeException("Avaliação inválida");
        }

        if (!buscaProduto.isDescricaoBuscaValida()) {
            throw new RuntimeException("Descrição da busca inválida");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(buscaProduto.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(buscaProduto.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return buscaProdutoRepository.save(buscaProduto);
    }

    public void deletarBuscaProduto(Integer idBusca) {
        buscaProdutoRepository.findById(idBusca)
                .orElseThrow(() -> new RuntimeException("Busca de produto não encontrada"));

        buscaProdutoRepository.delete(idBusca);
    }

    public List<BuscaProduto> buscarPorComprador(Long cpfComprador) {
        return buscaProdutoRepository.findByComprador(cpfComprador);
    }

    public List<BuscaProduto> buscarPorProduto(Integer codigoProduto) {
        return buscaProdutoRepository.findByProduto(codigoProduto);
    }

    public List<BuscaProduto> buscarPorCategoria(String categoria) {
        return buscaProdutoRepository.findByCategoria(categoria);
    }

    public List<BuscaProduto> buscarProdutosAbaixoDe(Float preco) {
        return buscaProdutoRepository.findByPrecoMenorQue(preco);
    }

    public List<BuscaProduto> buscarPorRegiao(String regiao) {
        return buscaProdutoRepository.findByRegiao(regiao);
    }
}