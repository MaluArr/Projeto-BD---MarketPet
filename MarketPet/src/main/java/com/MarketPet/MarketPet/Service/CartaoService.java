package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Cartao;
import com.MarketPet.MarketPet.Repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public List<Cartao> listarTodos() {
        return cartaoRepository.findAll();
    }

    public Optional<Cartao> buscarPorId(Integer idCartao) {
        return cartaoRepository.findById(idCartao);
    }

    public Cartao criarCartao(Cartao cartao) {
        // Validações
        if (!cartao.isNumeroCartaoValido()) {
            throw new RuntimeException("Número do cartão inválido");
        }

        if (!cartao.isCvvValido()) {
            throw new RuntimeException("CVV inválido");
        }

        if (!cartao.isValidadeValida()) {
            throw new RuntimeException("Cartão expirado ou data de validade inválida");
        }

        // Verifica se o número do cartão já existe
        if (cartaoRepository.existsByNumero(cartao.getNumero())) {
            throw new RuntimeException("Número de cartão já cadastrado");
        }

        return cartaoRepository.save(cartao);
    }

    public Cartao atualizarCartao(Cartao cartao) {
        // Verifica se o cartão existe
        cartaoRepository.findById(cartao.getIdCartao())
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        // Validações
        if (!cartao.isNumeroCartaoValido()) {
            throw new RuntimeException("Número do cartão inválido");
        }

        if (!cartao.isCvvValido()) {
            throw new RuntimeException("CVV inválido");
        }

        if (!cartao.isValidadeValida()) {
            throw new RuntimeException("Cartão expirado ou data de validade inválida");
        }

        return cartaoRepository.save(cartao);
    }

    public void deletarCartao(Integer idCartao) {
        cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        cartaoRepository.delete(idCartao);
    }

    public List<Cartao> buscarPorBandeira(String bandeira) {
        return cartaoRepository.findByBandeira(bandeira);
    }

    public Optional<Cartao> buscarPorNumero(String numero) {
        return cartaoRepository.findByNumero(numero);
    }
}