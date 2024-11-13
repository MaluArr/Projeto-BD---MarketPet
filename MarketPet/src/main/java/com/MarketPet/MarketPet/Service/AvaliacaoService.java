package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Avaliacao;
import com.MarketPet.MarketPet.Repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Integer idAvaliacao) {
        return avaliacaoRepository.findById(idAvaliacao);
    }

    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        // Adicione validações conforme necessário
        return avaliacaoRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Integer idAvaliacao) {
        avaliacaoRepository.deleteById(idAvaliacao);
    }

    public List<Map<String, Object>> gerarRelatorioAvaliacoesPorProduto() {
        return avaliacaoRepository.getAvaliacoesPorProduto();
    }

    public List<Map<String, Object>> gerarRelatorioAvaliacoesPorComprador() {
        return avaliacaoRepository.getAvaliacoesPorComprador();
    }
}