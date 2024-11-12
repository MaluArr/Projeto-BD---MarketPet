package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Endereco;
import com.MarketPet.MarketPet.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Integer idEndereco) {
        return enderecoRepository.findById(idEndereco);
    }

    public Endereco criarEndereco(Endereco endereco) {
        // Validações
        if (!endereco.isCepValido()) {
            throw new RuntimeException("CEP inválido");
        }

        if (!endereco.isEstadoValido()) {
            throw new RuntimeException("Estado inválido");
        }

        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(Endereco endereco) {
        // Verifica se o endereço existe
        enderecoRepository.findById(endereco.getIdEndereco())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        // Validações
        if (!endereco.isCepValido()) {
            throw new RuntimeException("CEP inválido");
        }

        if (!endereco.isEstadoValido()) {
            throw new RuntimeException("Estado inválido");
        }

        return enderecoRepository.save(endereco);
    }

    public void deletarEndereco(Integer idEndereco) {
        enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        enderecoRepository.delete(idEndereco);
    }

    public List<Endereco> buscarPorEstado(String estado) {
        return enderecoRepository.findByEstado(estado);
    }

    public List<Endereco> buscarPorCidade(String cidade) {
        return enderecoRepository.findByCidade(cidade);
    }

    public List<Endereco> buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }
}