package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Carrinho;
import com.MarketPet.MarketPet.Repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public List<Carrinho> listarTodos() {
        return carrinhoRepository.findAll();
    }

    public Optional<Carrinho> buscarPorId(Integer idCarrinho) {
        return carrinhoRepository.findById(idCarrinho);
    }

    public Carrinho salvarCarrinho(Carrinho carrinho) {
        // Adicione validações conforme necessário
        return carrinhoRepository.save(carrinho);
    }

    public void deletarCarrinho(Integer idCarrinho) {
        carrinhoRepository.deleteById(idCarrinho);
    }

    public List<Map<String, Object>> gerarRelatorioCarrinhosPorComprador() {
        return carrinhoRepository.getCarrinhosPorComprador();
    }

    public List<Map<String, Object>> gerarRelatorioCarrinhosPorValor() {
        return carrinhoRepository.getCarrinhosPorValor();
    }
}