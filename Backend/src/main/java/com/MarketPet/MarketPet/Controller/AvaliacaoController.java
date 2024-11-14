package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Avaliacao;
import com.MarketPet.MarketPet.Service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> listarTodas() {
        return avaliacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Avaliacao criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.salvarAvaliacao(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Integer id, @RequestBody Avaliacao avaliacao) {
        if (!avaliacaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        avaliacao.setIdAvaliacao(id);
        return ResponseEntity.ok(avaliacaoService.salvarAvaliacao(avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Integer id) {
        if (!avaliacaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoService.deletarAvaliacao(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/por-produto")
    public List<Map<String, Object>> relatorioAvaliacoesPorProduto() {
        return avaliacaoService.gerarRelatorioAvaliacoesPorProduto();
    }

    @GetMapping("/relatorio/por-comprador")
    public List<Map<String, Object>> relatorioAvaliacoesPorComprador() {
        return avaliacaoService.gerarRelatorioAvaliacoesPorComprador();
    }
}