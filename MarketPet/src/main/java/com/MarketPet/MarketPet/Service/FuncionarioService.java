package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Funcionario;
import com.MarketPet.MarketPet.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorCpf(Long cpfFuncionario) {
        return funcionarioRepository.findByCpf(cpfFuncionario);
    }

    public Funcionario criarFuncionario(Funcionario funcionario) {
        // Validações
        if (!funcionario.isNomeValido()) {
            throw new RuntimeException("Nome de funcionário inválido");
        }

        // Verifica se já existe funcionário com o mesmo CPF
        if (funcionarioRepository.findByCpf(funcionario.getCpfFuncionario()).isPresent()) {
            throw new RuntimeException("Já existe um funcionário com este CPF");
        }

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        // Verifica se o funcionário existe
        funcionarioRepository.findByCpf(funcionario.getCpfFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Validações
        if (!funcionario.isNomeValido()) {
            throw new RuntimeException("Nome de funcionário inválido");
        }

        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long cpfFuncionario) {
        funcionarioRepository.findByCpf(cpfFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionarioRepository.delete(cpfFuncionario);
    }

    public List<Funcionario> buscarPorNome(String parteNome) {
        return funcionarioRepository.findByNomeContaining(parteNome);
    }
}