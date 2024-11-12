package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compradores")
public class CompradorController {

    @Autowired
    private CompradorService compradorService;

    @GetMapping
    public List<Comprador> listarTodos() {
        return compradorService.listarTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Comprador> buscarPorCpf(@PathVariable Long cpf) {
        return compradorService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comprador> criarComprador(@RequestBody Comprador comprador) {
        Comprador novoComprador = compradorService.criarComprador(comprador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComprador);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Comprador> atualizarComprador(
            @PathVariable Long cpf,
            @RequestBody Comprador comprador) {
        comprador.setCpf(cpf);
        Comprador compradorAtualizado = compradorService.atualizarComprador(comprador);
        return ResponseEntity.ok(compradorAtualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarComprador(@PathVariable Long cpf) {
        compradorService.deletarComprador(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/endereco/{idEndereco}")
    public List<Comprador> buscarPorEndereco(@PathVariable Integer idEndereco) {
        return compradorService.buscarPorEndereco(idEndereco);
    }

    @GetMapping("/cartao/{idCartao}")
    public List<Comprador> buscarPorCartao(@PathVariable Integer idCartao) {
        return compradorService.buscarPorCartao(idCartao);
    }
}