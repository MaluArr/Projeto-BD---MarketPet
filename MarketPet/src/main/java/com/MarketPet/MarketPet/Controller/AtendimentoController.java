package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Atendimento;
import com.MarketPet.MarketPet.Service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> listarTodos() {
        return atendimentoService.listarTodos();
    }

    @GetMapping("/{idAtendimento}")
    public ResponseEntity<Atendimento> buscarPorId(@PathVariable Integer idAtendimento) {
        return atendimentoService.buscarPorId(idAtendimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atendimento> criarAtendimento(@RequestBody Atendimento atendimento) {
        Atendimento novoAtendimento = atendimentoService.criarAtendimento(atendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtendimento);
    }

    @PutMapping("/{idAtendimento}")
    public ResponseEntity<Atendimento> atualizarAtendimento(
            @PathVariable Integer idAtendimento,
            @RequestBody Atendimento atendimento) {
        atendimento.setIdAtendimento(idAtendimento);
        Atendimento atendimentoAtualizado = atendimentoService.atualizarAtendimento(atendimento);
        return ResponseEntity.ok(atendimentoAtualizado);
    }

    @DeleteMapping("/{idAtendimento}")
    public ResponseEntity<Void> deletarAtendimento(@PathVariable Integer idAtendimento) {
        atendimentoService.deletarAtendimento(idAtendimento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/funcionario/{cpfFuncionario}")
    public List<Atendimento> buscarPorFuncionario(@PathVariable Long cpfFuncionario) {
        return atendimentoService.buscarPorFuncionario(cpfFuncionario);
    }

    @GetMapping("/usuario/{cpfUsuario}")
    public List<Atendimento> buscarPorUsuario(@PathVariable Long cpfUsuario) {
        return atendimentoService.buscarPorUsuario(cpfUsuario);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Atendimento> buscarPorCategoria(@PathVariable String categoria) {
        return atendimentoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/chat/{idChat}")
    public ResponseEntity<Atendimento> buscarPorChat(@PathVariable Integer idChat) {
        return atendimentoService.buscarPorChat(idChat)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}