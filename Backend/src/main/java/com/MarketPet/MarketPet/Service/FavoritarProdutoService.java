package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.FavoritarProduto;
import com.MarketPet.MarketPet.Repository.FavoritarProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritarProdutoService {

    @Autowired
    private FavoritarProdutoRepository favoritarProdutoRepository;

    public List<FavoritarProduto> listarTodos() {
        return favoritarProdutoRepository.findAll();
    }

    public Optional<FavoritarProduto> buscarPorId(Integer idLista) {
        return favoritarProdutoRepository.findById(idLista);
    }

    public FavoritarProduto criarFavoritarProduto(FavoritarProduto favoritarProduto) {
        if (!favoritarProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!favoritarProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!favoritarProduto.isNomeListaValido()) {
            throw new RuntimeException("Nome da lista inválido");
        }

        if (favoritarProduto.getDataCriacao() == null) {
            favoritarProduto.setDataCriacao(LocalDate.now());
        }

        return favoritarProdutoRepository.save(favoritarProduto);
    }

    public FavoritarProduto atualizarFavoritarProduto(FavoritarProduto favoritarProduto) {
        favoritarProdutoRepository.findById(favoritarProduto.getIdLista())
                .orElseThrow(() -> new RuntimeException("Favoritar produto não encontrado"));

        if (!favoritarProduto.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!favoritarProduto.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!favoritarProduto.isNomeListaValido()) {
            throw new RuntimeException("Nome da lista inválido");
        }

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