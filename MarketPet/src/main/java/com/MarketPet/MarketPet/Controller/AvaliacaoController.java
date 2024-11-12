package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Avaliacao;
import com.MarketPet.MarketPet.Service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> listarTodos() {
        return avaliacaoService.listarTodos();
    }

    @GetMapping("/{idAvaliacao}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer idAvaliacao) {
        return avaliacaoService.buscarPorId(idAvaliacao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoService.criarAvaliacao(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }

    @PutMapping("/{idAvaliacao}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(
            @PathVariable Integer idAvaliacao,
            @RequestBody Avaliacao avaliacao) {
        avaliacao.setIdAvaliacao(idAvaliacao);
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao(avaliacao);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @DeleteMapping("/{idAvaliacao}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Integer idAvaliacao) {
        avaliacaoService.deletarAvaliacao(idAvaliacao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<Avaliacao> buscarPorComprador(@PathVariable Long cpfComprador) {
        return avaliacaoService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<Avaliacao> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return avaliacaoService.buscarPorProduto(codigoProduto);
    }

    @GetMapping("/venda/{idVenda}")
    public ResponseEntity<Avaliacao> buscarPorVenda(@PathVariable Integer idVenda) {
        return avaliacaoService.buscarPorVenda(idVenda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nota")
    public List<Avaliacao> buscarPorIntervaloNota(
            @RequestParam BigDecimal notaMinima,
            @RequestParam BigDecimal notaMaxima) {
        return avaliacaoService.buscarPorIntervaloNota(notaMinima, notaMaxima);
    }
}