package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.FavoritarProduto;
import com.MarketPet.MarketPet.Repository.FavoritarProdutoRepository;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritarProdutoService {

    @Autowired
    private FavoritarProdutoRepository favoritarProdutoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<FavoritarProduto> listarTodos() {
        return favoritarProdutoRepository.findAll();
    }

    public Optional<FavoritarProduto> buscarPorId(Integer idLista) {
        return favoritarProdutoRepository.findById(idLista);
    }

    public FavoritarProduto criarFavoritarProduto(FavoritarProduto favoritarProduto) {
        // Validações
        if (!favoritarProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!favoritarProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!favoritarProduto.isNomeListaValido()) {
            throw new RuntimeException("Nome da lista inválido");
        }

        // Define data de criação como hoje se não for especificada
        if (favoritarProduto.getDataCriacao() == null) {
            favoritarProduto.setDataCriacao(LocalDate.now());
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(favoritarProduto.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(favoritarProduto.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return favoritarProdutoRepository.save(favoritarProduto);
    }

    public FavoritarProduto atualizarFavoritarProduto(FavoritarProduto favoritarProduto) {
        // Verifica se o favoritar produto existe
        favoritarProdutoRepository.findById(favoritarProduto.getIdLista())
                .orElseThrow(() -> new RuntimeException("Favoritar produto não encontrado"));

        // Validações
        if (!favoritarProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!favoritarProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!favoritarProduto.isNomeListaValido()) {
            throw new RuntimeException("Nome da lista inválido");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(favoritarProduto.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(favoritarProduto.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return favoritarProdutoRepository.save(favoritarProduto);
    }

    public void deletarFavoritarProduto(Integer idLista) {
        favoritarProdutoRepository.findById(idLista)
                .orElseThrow(() -> new RuntimeException("Favoritar produto não encontrado"));

        favoritarProdutoRepository.delete(idLista);
    }

    public List<FavoritarProduto> buscarPorComprador(Long cpfComprador) {
        return favoritarProdutoRepository.findByComprador(cpfComprador);
    }

    public List<FavoritarProduto> buscarPorProduto(Integer codigoProduto) {
        return favoritarProdutoRepository.findByProduto(codigoProduto);
    }

    public List<FavoritarProduto> buscarPorNomeLista(String nomeLista) {
        return favoritarProdutoRepository.findByNomeLista(nomeLista);
    }
}