package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Repository.AtendenteRepository;
import com.MarketPet.MarketPet.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Atendente> listarTodos() {
        return atendenteRepository.findAll();
    }

    public Optional<Atendente> buscarPorId(Integer idAtendente) {
        return atendenteRepository.findById(idAtendente);
    }

    public Atendente criarAtendente(Atendente atendente) {
        // Validações
        if (!atendente.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        // Verifica se o funcionário existe
        funcionarioRepository.findByCpf(atendente.getFuncionario().getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Verifica se o funcionário já é um atendente
        atendenteRepository.findByFuncionario(atendente.getFuncionario().getCpfFuncionario())
                .ifPresent(a -> {
                    throw new RuntimeException("Funcionário já é um atendente");
                });

        return atendenteRepository.save(atendente);
    }

    public Atendente atualizarAtendente(Atendente atendente) {
        // Verifica se o atendente existe
        atendenteRepository.findById(atendente.getIdAtendente())
                .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));

        // Validações
        if (!atendente.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        // Verifica se o funcionário existe
        funcionarioRepository.findByCpf(atendente.getFuncionario().getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        return atendenteRepository.save(atendente);
    }

    public void deletarAtendente(Integer idAtendente) {
        atendenteRepository.findById(idAtendente)
                .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));

        atendenteRepository.delete(idAtendente);
    }

    public Optional<Atendente> buscarPorCpfFuncionario(Long cpfFuncionario) {
        return atendenteRepository.findByFuncionario(cpfFuncionario);
    }
}