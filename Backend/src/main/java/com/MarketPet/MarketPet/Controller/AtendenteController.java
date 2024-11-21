package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {

    private static final Logger logger = LoggerFactory.getLogger(AtendenteController.class);

    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> listarTodos() {
        logger.info("Listando todos os atendentes.");
        return atendenteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> buscarPorId(@PathVariable Integer id) {
        logger.info("Buscando atendente com ID: {}", id);
        return atendenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Atendente com ID {} não encontrado.", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Atendente> criarAtendente(@RequestBody Atendente atendente) {
        try {
            logger.info("Criando novo atendente.");
            Atendente novoAtendente = atendenteService.salvarAtendente(atendente);
            return ResponseEntity.ok(novoAtendente);
        } catch (Exception e) {
            logger.error("Erro ao criar atendente.", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> atualizarAtendente(@PathVariable Integer id, @RequestBody Atendente atendente) {
        logger.info("Atualizando atendente com ID: {}", id);
        if (!atendenteService.buscarPorId(id).isPresent()) {
            logger.warn("Atendente com ID {} não encontrado.", id);
            return ResponseEntity.notFound().build();
        }
        atendente.setIdAtendente(id);
        Atendente atendenteAtualizado = atendenteService.salvarAtendente(atendente);
        return ResponseEntity.ok(atendenteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Integer id) {
        logger.info("Deletando atendente com ID: {}", id);
        if (!atendenteService.buscarPorId(id).isPresent()) {
            logger.warn("Atendente com ID {} não encontrado.", id);
            return ResponseEntity.notFound().build();
        }
        atendenteService.deletarAtendente(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/atendimentos")
    public List<Map<String, Object>> relatorioAtendimentosPorAtendente() {
        logger.info("Gerando relatório de atendimentos por atendente.");
        return atendenteService.gerarRelatorioAtendimentosPorAtendente();
    }

    @GetMapping("/relatorio/atendimentos-periodo")
    public List<Map<String, Object>> relatorioAtendimentosPorPeriodo(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        logger.info("Gerando relatório de atendimentos por período: {} - {}", dataInicio, dataFim);
        return atendenteService.gerarRelatorioAtendimentosPorPeriodo(dataInicio, dataFim);
    }
}
