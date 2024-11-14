package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Venda;
import com.MarketPet.MarketPet.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> listarTodos() {
        return vendaService.listarTodos();
    }

    @GetMapping("/{idVenda}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Integer idVenda) {
        return vendaService.buscarPorId(idVenda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.criarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @PutMapping("/{idVenda}")
    public ResponseEntity<Venda> atualizarVenda(
            @PathVariable Integer idVenda,
            @RequestBody Venda venda) {
        venda.setIdVenda(idVenda);
        Venda vendaAtualizada = vendaService.atualizarVenda(venda);
        return ResponseEntity.ok(vendaAtualizada);
    }

    @DeleteMapping("/{idVenda}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Integer idVenda) {
        vendaService.deletarVenda(idVenda);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<Venda> buscarPorComprador(@PathVariable Long cpfComprador) {
        return vendaService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<Venda> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return vendaService.buscarPorProduto(codigoProduto);
    }

    @GetMapping("/periodo")
    public List<Venda> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return vendaService.buscarPorPeriodo(dataInicio, dataFim);
    }
}