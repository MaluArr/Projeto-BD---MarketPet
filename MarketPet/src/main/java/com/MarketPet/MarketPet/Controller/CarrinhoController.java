package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Carrinho;
import com.MarketPet.MarketPet.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public List<Carrinho> listarTodos() {
        return carrinhoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarPorId(@PathVariable Integer id) {
        return carrinhoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrinho criarCarrinho(@RequestBody Carrinho carrinho) {
        return carrinhoService.salvarCarrinho(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrinho> atualizarCarrinho(@PathVariable Integer id, @RequestBody Carrinho carrinho) {
        if (!carrinhoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carrinho.setIdCarrinho(id);
        return ResponseEntity.ok(carrinhoService.salvarCarrinho(carrinho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarrinho(@PathVariable Integer id) {
        if (!carrinhoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carrinhoService.deletarCarrinho(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/por-comprador")
    public List<Map<String, Object>> relatorioCarrinhosPorComprador() {
        return carrinhoService.gerarRelatorioCarrinhosPorComprador();
    }

    @GetMapping("/relatorio/por-valor")
    public List<Map<String, Object>> relatorioCarrinhosPorValor() {
        return carrinhoService.gerarRelatorioCarrinhosPorValor();
    }
}