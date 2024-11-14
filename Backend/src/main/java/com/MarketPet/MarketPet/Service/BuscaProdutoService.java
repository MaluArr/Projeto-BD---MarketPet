package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import com.MarketPet.MarketPet.Repository.BuscaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class BuscaProdutoService {

    @Autowired
    private BuscaProdutoRepository buscaProdutoRepository;

    public List<BuscaProduto> listarTodas() {
        return buscaProdutoRepository.findAll();
    }

    public Optional<BuscaProduto> buscarPorId(Integer idBusca) {
        return buscaProdutoRepository.findById(idBusca);
    }

    public BuscaProduto salvarBuscaProduto(BuscaProduto buscaProduto) {
        // Adicione validações conforme necessário
        return buscaProdutoRepository.save(buscaProduto);
    }

    public void deletarBuscaProduto(Integer idBusca) {
        buscaProdutoRepository.deleteById(idBusca);
    }

    public List<Map<String, Object>> gerarRelatorioBuscasPorComprador() {
        return buscaProdutoRepository.getBuscasPorComprador();
    }

    public List<Map<String, Object>> gerarRelatorioBuscasPorCategoria() {
        return buscaProdutoRepository.getBuscasPorCategoria();
    }
}