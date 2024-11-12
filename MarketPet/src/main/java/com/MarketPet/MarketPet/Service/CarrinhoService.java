package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Carrinho;
import com.MarketPet.MarketPet.Repository.CarrinhoRepository;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    public List<Carrinho> listarTodos() {
        return carrinhoRepository.findAll();
    }

    public Optional<Carrinho> buscarPorId(Integer idCarrinho) {
        return carrinhoRepository.findById(idCarrinho);
    }

    public Carrinho criarCarrinho(Carrinho carrinho) {
        // Validações
        if (!carrinho.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!carrinho.isValorTotalValido()) {
            throw new RuntimeException("Valor total inválido");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(carrinho.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica se já existe um carrinho para este comprador
        carrinhoRepository.findByComprador(carrinho.getComprador().getCpf())
                .ifPresent(c -> {
                    throw new RuntimeException("Já existe um carrinho para este comprador");
                });

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho atualizarCarrinho(Carrinho carrinho) {
        // Verifica se o carrinho existe
        carrinhoRepository.findById(carrinho.getIdCarrinho())
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Validações
        if (!carrinho.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!carrinho.isValorTotalValido()) {
            throw new RuntimeException("Valor total inválido");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(carrinho.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        return carrinhoRepository.save(carrinho);
    }

    public void deletarCarrinho(Integer idCarrinho) {
        carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        carrinhoRepository.delete(idCarrinho);
    }

    public Optional<Carrinho> buscarPorComprador(Long cpfComprador) {
        return carrinhoRepository.findByComprador(cpfComprador);
    }

    public List<Carrinho> buscarCarrinhosComValorAcimaDe(BigDecimal valorMinimo) {
        return carrinhoRepository.findByValorTotalMaiorQue(valorMinimo);
    }
}