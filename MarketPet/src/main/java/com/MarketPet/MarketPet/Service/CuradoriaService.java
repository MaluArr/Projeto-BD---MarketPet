package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Curadoria;
import com.MarketPet.MarketPet.Repository.CuradoriaRepository;
import com.MarketPet.MarketPet.Repository.CuradorRepository;
import com.MarketPet.MarketPet.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuradoriaService {

    @Autowired
    private CuradoriaRepository curadoriaRepository;

    @Autowired
    private CuradorRepository curadorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Curadoria> listarTodos() {
        return curadoriaRepository.findAll();
    }

    public Optional<Curadoria> buscarPorCodigo(Integer codigoCuradoria) {
        return curadoriaRepository.findByCodigo(codigoCuradoria);
    }

    public Curadoria criarCuradoria(Curadoria curadoria) {
        // Validações
        if (!curadoria.isDescricaoValida()) {
            throw new RuntimeException("Descrição da curadoria inválida");
        }

        if (!curadoria.isCuradorValido()) {
            throw new RuntimeException("Curador inválido");
        }

        if (!curadoria.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        // Verifica existência do curador
        curadorRepository.findById(curadoria.getCurador().getIdCurador())
                .orElseThrow(() -> new RuntimeException("Curador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(curadoria.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return curadoriaRepository.save(curadoria);
    }

    public Curadoria atualizarCuradoria(Curadoria curadoria) {
        // Verifica se a curadoria existe
        curadoriaRepository.findByCodigo(curadoria.getCodigoCuradoria())
                .orElseThrow(() -> new RuntimeException("Curadoria não encontrada"));

        // Validações
        if (!curadoria.isDescricaoValida()) {
            throw new RuntimeException("Descrição da curadoria inválida");
        }

        if (!curadoria.isCuradorValido()) {
            throw new RuntimeException("Curador inválido");
        }

        if (!curadoria.isProdutoValido()) {
            throw new RuntimeException("Produto inválido");
        }

        // Verifica existência do curador
        curadorRepository.findById(curadoria.getCurador().getIdCurador())
                .orElseThrow(() -> new RuntimeException("Curador não encontrado"));

        // Verifica existência do produto
        produtoRepository.findByCodigo(curadoria.getProduto().getCodigoProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return curadoriaRepository.save(curadoria);
    }

    public void deletarCuradoria(Integer codigoCuradoria) {
        curadoriaRepository.findByCodigo(codigoCuradoria)
                .orElseThrow(() -> new RuntimeException("Curadoria não encontrada"));

        curadoriaRepository.delete(codigoCuradoria);
    }

    public List<Curadoria> buscarPorCurador(Integer idCurador) {
        return curadoriaRepository.findByCurador(idCurador);
    }

    public List<Curadoria> buscarPorProduto(Integer codigoProduto) {
        return curadoriaRepository.findByProduto(codigoProduto);
    }
}