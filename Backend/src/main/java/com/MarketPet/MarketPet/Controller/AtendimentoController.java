package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Atendimento;
import com.MarketPet.MarketPet.Service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> listarTodos() {
        return atendimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> buscarPorId(@PathVariable Integer id) {
        return atendimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Atendimento criarAtendimento(@RequestBody Atendimento atendimento) {
        return atendimentoService.salvarAtendimento(atendimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento> atualizarAtendimento(@PathVariable Integer id, @RequestBody Atendimento atendimento) {
        if (!atendimentoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atendimento.setIdAtendimento(id);
        return ResponseEntity.ok(atendimentoService.salvarAtendimento(atendimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendimento(@PathVariable Integer id) {
        if (!atendimentoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atendimentoService.deletarAtendimento(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/por-funcionario")
    public List<Map<String, Object>> relatorioAtendimentosPorFuncionario() {
        return atendimentoService.gerarRelatorioAtendimentosPorFuncionario();
    }

    @GetMapping("/relatorio/por-categoria")
    public List<Map<String, Object>> relatorioAtendimentosPorCategoria() {
        return atendimentoService.gerarRelatorioAtendimentosPorCategoria();
    }

    @GetMapping("/relatorio/por-periodo")
    public List<Map<String, Object>> relatorioAtendimentosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return atendimentoService.gerarRelatorioAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}