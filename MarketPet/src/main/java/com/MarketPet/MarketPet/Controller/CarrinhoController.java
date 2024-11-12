package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Carrinho;
import com.MarketPet.MarketPet.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public List<Carrinho> listarTodos() {
        return carrinhoService.listarTodos();
    }

    @GetMapping("/{idCarrinho}")
    public ResponseEntity<Carrinho> buscarPorId(@PathVariable Integer idCarrinho) {
        return carrinhoService.buscarPorId(idCarrinho)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(@RequestBody Carrinho carrinho) {
        Carrinho novoCarrinho = carrinhoService.criarCarrinho(carrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinho);
    }

    @PutMapping("/{idCarrinho}")
    public ResponseEntity<Carrinho> atualizarCarrinho(
            @PathVariable Integer idCarrinho,
            @RequestBody Carrinho carrinho) {
        carrinho.setIdCarrinho(idCarrinho);
        Carrinho carrinhoAtualizado = carrinhoService.atualizarCarrinho(carrinho);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    @DeleteMapping("/{idCarrinho}")
    public ResponseEntity<Void> deletarCarrinho(@PathVariable Integer idCarrinho) {
        carrinhoService.deletarCarrinho(idCarrinho);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public ResponseEntity<Carrinho> buscarPorComprador(@PathVariable Long cpfComprador) {
        return carrinhoService.buscarPorComprador(cpfComprador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/valor-acima/{valorMinimo}")
    public List<Carrinho> buscarCarrinhosComValorAcimaDe(@PathVariable BigDecimal valorMinimo) {
        return carrinhoService.buscarCarrinhosComValorAcimaDe(valorMinimo);
    }
}