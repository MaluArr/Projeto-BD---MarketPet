package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Avaliacao;
import com.MarketPet.MarketPet.Repository.AvaliacaoRepository;
import com.MarketPet.MarketPet.Repository.CompradorRepository;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import com.MarketPet.MarketPet.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Integer idAvaliacao) {
        return avaliacaoRepository.findById(idAvaliacao);
    }

    @Transactional
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        // Validações
        if (!avaliacao.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!avaliacao.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!avaliacao.isVendaValida()) {
            throw new RuntimeException("Venda inválida");
        }

        if (!avaliacao.isNotaValida()) {
            throw new RuntimeException("Nota inválida");
        }

        // Verifica se a avaliação é consistente
        if (!avaliacao.isAvaliacaoConsistente()) {
            throw new RuntimeException("Avaliação inconsistente");
        }

        // Verifica existência do comprador
        compradorRepository.findByCpf(avaliacao.getComprador().getCpf())
                .orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(avaliacao.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Verifica existência da venda
        vendaRepository.findById(avaliacao.getVenda().getIdVenda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        // Verifica se a venda já foi avaliada
        avaliacaoRepository.findByVenda(avaliacao.getVenda().getIdVenda())
                .ifPresent(a -> {
                    throw new RuntimeException("Venda já avaliada");
                });

        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
        // Verifica se a avaliação existe
        avaliacaoRepository.findById(avaliacao.getIdAvaliacao())
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        // Validações
        if (!avaliacao.isCompradorValido()) {
            throw new RuntimeException("Comprador inválido");
        }

        if (!avaliacao.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        if (!avaliacao.isVendaValida()) {
            throw new RuntimeException("Venda inválida");
        }

        if (!avaliacao.isNotaValida()) {
            throw new RuntimeException("Nota inválida");
        }

        // Verifica se a avaliação é consistente
        if (!avaliacao.isAvaliacaoConsistente()) {
            throw new RuntimeException("Avaliação inconsistente");
        }

        return avaliacaoRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Integer idAvaliacao) {
        avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        avaliacaoRepository.delete(idAvaliacao);
    }

    public List<Avaliacao> buscarPorComprador(Long cpfComprador) {
        return avaliacaoRepository.findByComprador(cpfComprador);
    }

    public List<Avaliacao> buscarPorProduto(Integer codigoProduto) {
        return avaliacaoRepository.findByProduto(codigoProduto);
    }

    public Optional<Avaliacao> buscarPorVenda(Integer idVenda) {
        return avaliacaoRepository.findByVenda(idVenda);
    }

    public List<Avaliacao> buscarPorIntervaloNota(BigDecimal notaMinima, BigDecimal notaMaxima) {
        return avaliacaoRepository.findByNotaEntre(notaMinima, notaMaxima);
    }
}