package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Vendedor;
import com.MarketPet.MarketPet.Service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<Vendedor> listarTodos() {
        return vendedorService.listarTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Vendedor> buscarPorCpf(@PathVariable Long cpf) {
        return vendedorService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vendedor> criarVendedor(@RequestBody Vendedor vendedor) {
        Vendedor novoVendedor = vendedorService.criarVendedor(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Vendedor> atualizarVendedor(
            @PathVariable Long cpf,
            @RequestBody Vendedor vendedor) {
        vendedor.setCpf(cpf);
        Vendedor vendedorAtualizado = vendedorService.atualizarVendedor(vendedor);
        return ResponseEntity.ok(vendedorAtualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarVendedor(@PathVariable Long cpf) {
        vendedorService.deletarVendedor(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/avaliacao-superior/{nota}")
    public List<Vendedor> buscarVendedoresPorAvaliacaoSuperior(@PathVariable BigDecimal nota) {
        return vendedorService.buscarVendedoresPorAvaliacaoSuperior(nota);
    }

    @GetMapping("/vendas-superior/{valor}")
    public List<Vendedor> buscarVendedoresPorTotalVendasSuperior(@PathVariable BigDecimal valor) {
        return vendedorService.buscarVendedoresPorTotalVendasSuperior(valor);
    }
}