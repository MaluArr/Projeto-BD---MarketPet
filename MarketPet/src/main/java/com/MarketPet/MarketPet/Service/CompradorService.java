package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import com.MarketPet.MarketPet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompradorService {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Comprador> listarTodos() {
        return compradorRepository.findAll();
    }

    public Optional<Comprador> buscarPorCpf(Long cpf) {
        return compradorRepository.findByCpf(cpf);
    }

    public Comprador criarComprador(Comprador comprador) {
        // Verifica se o CPF existe como usuário
        usuarioRepository.findByCpf(comprador.getCpf())
                .orElseThrow(() -> new RuntimeException("CPF não encontrado como usuário"));

        // Validações
        if (comprador.getEndereco() != null && !comprador.isEnderecoValido()) {
            throw new RuntimeException("Endereço inválido");
        }

        if (comprador.getCartao() != null && !comprador.isCartaoValido()) {
            throw new RuntimeException("Cartão inválido");
        }

        return compradorRepository.save(comprador);
    }

    public Comprador atualizarComprador(Comprador comprador) {
        // Verifica se o comprador existe
        compradorRepository.findByCpf(comprador.getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Validações
        if (comprador.getEndereco() != null && !comprador.isEnderecoValido()) {
            throw new RuntimeException("Endereço inválido");
        }

        if (comprador.getCartao() != null && !comprador.isCartaoValido()) {
            throw new RuntimeException("Cartão inválido");
        }

        return compradorRepository.save(comprador);
    }

    public void deletarComprador(Long cpf) {
        compradorRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        compradorRepository.delete(cpf);
    }

    public List<Comprador> buscarPorEndereco(Integer idEndereco) {
        return compradorRepository.findByEnderecoId(idEndereco);
    }

    public List<Comprador> buscarPorCartao(Integer idCartao) {
        return compradorRepository.findByCartaoId(idCartao);
    }
}