package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Venda;
import com.MarketPet.MarketPet.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Integer idVenda) {
        return vendaRepository.findById(idVenda);
    }

    @Transactional
    public Venda criarVenda(Venda venda) {
        if (!venda.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!venda.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (venda.getDataVenda() == null) {
            venda.setDataVenda(LocalDate.now());
        }

        if (!venda.isDataVendaValida()) {
            throw new RuntimeException("Data de venda inválida");
        }

        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda atualizarVenda(Venda venda) {
        vendaRepository.findById(venda.getIdVenda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (!venda.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!venda.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!venda.isDataVendaValida()) {
            throw new RuntimeException("Data de venda inválida");
        }

        return vendaRepository.save(venda);
    }

    public void deletarVenda(Integer idVenda) {
        vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        vendaRepository.delete(idVenda);
    }

    public List<Venda> buscarPorComprador(Long cpfComprador) {
        return vendaRepository.findByComprador(cpfComprador);
    }

    public List<Venda> buscarPorProduto(Integer codigoProduto) {
        return vendaRepository.findByProduto(codigoProduto);
    }

    public List<Venda> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return vendaRepository.findByDataVendaBetween(dataInicio, dataFim);
    }
}