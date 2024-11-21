package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Repository.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AtendenteService {

    private static final Logger logger = LoggerFactory.getLogger(AtendenteService.class);

    @Autowired
    private AtendenteRepository atendenteRepository;

    public List<Atendente> listarTodos() {
        logger.info("Listando todos os atendentes.");
        return atendenteRepository.findAll();
    }

    public Optional<Atendente> buscarPorId(Integer idAtendente) {
        logger.info("Buscando atendente com ID: {}", idAtendente);
        return atendenteRepository.findById(idAtendente);
    }

    public Atendente salvarAtendente(Atendente atendente) {
        logger.info("Salvando atendente.");
        return atendenteRepository.save(atendente);
    }

    public void deletarAtendente(Integer idAtendente) {
        logger.info("Deletando atendente com ID: {}", idAtendente);
        atendenteRepository.deleteById(idAtendente);
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorAtendente() {
        logger.info("Gerando relatório de atendimentos por atendente.");
        return atendenteRepository.getAtendimentosPorAtendente();
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorPeriodo(String dataInicio, String dataFim) {
        logger.info("Gerando relatório de atendimentos por período: {} - {}", dataInicio, dataFim);
        return atendenteRepository.getAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}
