package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Vendedor;
import com.MarketPet.MarketPet.Repository.VendedorRepository;
import com.MarketPet.MarketPet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> buscarPorCpf(Long cpf) {
        return vendedorRepository.findByCpf(cpf);
    }

    public Vendedor criarVendedor(Vendedor vendedor) {
        usuarioRepository.findByCpf(vendedor.getCpf())
                .orElseThrow(() -> new RuntimeException("CPF não encontrado como usuário"));

        if (!vendedor.isAvaliacaoValida()) {
            throw new RuntimeException("Avaliação média inválida");
        }

        if (!vendedor.isTotalVendasValido()) {
            throw new RuntimeException("Total de vendas inválido");
        }

        return vendedorRepository.save(vendedor);
    }

    public Vendedor atualizarVendedor(Vendedor vendedor) {
        vendedorRepository.findByCpf(vendedor.getCpf())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        if (!vendedor.isAvaliacaoValida()) {
            throw new RuntimeException("Avaliação média inválida");
        }

        if (!vendedor.isTotalVendasValido()) {
            throw new RuntimeException("Total de vendas inválido");
        }

        return vendedorRepository.save(vendedor);
    }

    public void deletarVendedor(Long cpf) {
        vendedorRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        vendedorRepository.delete(cpf);
    }

    public List<Vendedor> buscarVendedoresPorAvaliacaoSuperior(BigDecimal nota) {
        return vendedorRepository.findByAvaliacaoMediaGreaterThan(nota);
    }

    public List<Vendedor> buscarVendedoresPorTotalVendasSuperior(BigDecimal valor) {
        return vendedorRepository.findByTotalVendasGreaterThan(valor);
    }
}