package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Repository.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    public List<Atendente> listarTodos() {
        return atendenteRepository.findAll();
    }

    public Optional<Atendente> buscarPorId(Integer idAtendente) {
        return atendenteRepository.findById(idAtendente);
    }

    public Atendente salvarAtendente(Atendente atendente) {
        return atendenteRepository.save(atendente);
    }

    public void deletarAtendente(Integer idAtendente) {
        atendenteRepository.deleteById(idAtendente);
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorAtendente() {
        return atendenteRepository.getAtendimentosPorAtendente();
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorPeriodo(String dataInicio, String dataFim) {
        return atendenteRepository.getAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}