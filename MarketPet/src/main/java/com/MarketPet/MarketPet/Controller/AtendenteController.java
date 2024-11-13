package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {

    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> listarTodos() {
        return atendenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> buscarPorId(@PathVariable Integer id) {
        return atendenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Atendente criarAtendente(@RequestBody Atendente atendente) {
        return atendenteService.salvarAtendente(atendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> atualizarAtendente(@PathVariable Integer id, @RequestBody Atendente atendente) {
        if (!atendenteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atendente.setIdAtendente(id);
        return ResponseEntity.ok(atendenteService.salvarAtendente(atendente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Integer id) {
        if (!atendenteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atendenteService.deletarAtendente(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/atendimentos")
    public List<Map<String, Object>> relatorioAtendimentosPorAtendente() {
        return atendenteService.gerarRelatorioAtendimentosPorAtendente();
    }

    @GetMapping("/relatorio/atendimentos-periodo")
    public List<Map<String, Object>> relatorioAtendimentosPorPeriodo(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        return atendenteService.gerarRelatorioAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}