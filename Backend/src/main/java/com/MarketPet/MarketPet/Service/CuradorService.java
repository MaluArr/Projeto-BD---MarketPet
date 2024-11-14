package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Curador;
import com.MarketPet.MarketPet.Repository.CuradorRepository;
import com.MarketPet.MarketPet.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuradorService {

    @Autowired
    private CuradorRepository curadorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Curador> listarTodos() {
        return curadorRepository.findAll();
    }

    public Optional<Curador> buscarPorId(Integer idCurador) {
        return curadorRepository.findById(idCurador);
    }

    public Curador criarCurador(Curador curador) {
        if (!curador.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        funcionarioRepository.findByCpf(curador.getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        curadorRepository.findByFuncionario(curador.getCpfFuncionario())
                .ifPresent(c -> {
                    throw new RuntimeException("Funcionário já é um curador");
                });

        return curadorRepository.save(curador);
    }

    public Curador atualizarCurador(Curador curador) {
        curadorRepository.findById(curador.getIdCurador())
                .orElseThrow(() -> new RuntimeException("Curador não encontrado"));

        if (!curador.isFuncionarioValido()) {
            throw new RuntimeException("Funcionário inválido");
        }

        funcionarioRepository.findByCpf(curador.getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        return curadorRepository.save(curador);
    }

    public void deletarCurador(Integer idCurador) {
        curadorRepository.findById(idCurador)
                .orElseThrow(() -> new RuntimeException("Curador não encontrado"));

        curadorRepository.delete(idCurador);
    }

    public Optional<Curador> buscarPorCpfFuncionario(Long cpfFuncionario) {
        return curadorRepository.findByFuncionario(cpfFuncionario);
    }
}