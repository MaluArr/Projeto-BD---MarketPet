package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Cartao;
import com.MarketPet.MarketPet.Service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public List<Cartao> listarTodos() {
        return cartaoService.listarTodos();
    }

    @GetMapping("/{idCartao}")
    public ResponseEntity<Cartao> buscarPorId(@PathVariable Integer idCartao) {
        return cartaoService.buscarPorId(idCartao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Cartao cartao) {
        Cartao novoCartao = cartaoService.criarCartao(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCartao);
    }

    @PutMapping("/{idCartao}")
    public ResponseEntity<Cartao> atualizarCartao(
            @PathVariable Integer idCartao,
            @RequestBody Cartao cartao) {
        cartao.setIdCartao(idCartao);
        Cartao cartaoAtualizado = cartaoService.atualizarCartao(cartao);
        return ResponseEntity.ok(cartaoAtualizado);
    }

    @DeleteMapping("/{idCartao}")
    public ResponseEntity<Void> deletarCartao(@PathVariable Integer idCartao) {
        cartaoService.deletarCartao(idCartao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bandeira/{bandeira}")
    public List<Cartao> buscarPorBandeira(@PathVariable String bandeira) {
        return cartaoService.buscarPorBandeira(bandeira);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Cartao> buscarPorNumero(@PathVariable String numero) {
        return cartaoService.buscarPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}