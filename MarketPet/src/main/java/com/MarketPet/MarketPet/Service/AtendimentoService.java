package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Atendimento;
import com.MarketPet.MarketPet.Repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> buscarPorId(Integer idAtendimento) {
        return atendimentoRepository.findById(idAtendimento);
    }

    public Atendimento salvarAtendimento(Atendimento atendimento) {
        // Adicione validações conforme necessário
        return atendimentoRepository.save(atendimento);
    }

    public void deletarAtendimento(Integer idAtendimento) {
        atendimentoRepository.deleteById(idAtendimento);
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorFuncionario() {
        return atendimentoRepository.getAtendimentosPorFuncionario();
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorCategoria() {
        return atendimentoRepository.getAtendimentosPorCategoria();
    }

    public List<Map<String, Object>> gerarRelatorioAtendimentosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return atendimentoRepository.getAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}