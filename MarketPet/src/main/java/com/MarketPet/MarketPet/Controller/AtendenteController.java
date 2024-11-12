package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteController {

    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> listarTodos() {
        return atendenteService.listarTodos();
    }

    @GetMapping("/{idAtendente}")
    public ResponseEntity<Atendente> buscarPorId(@PathVariable Integer idAtendente) {
        return atendenteService.buscarPorId(idAtendente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atendente> criarAtendente(@RequestBody Atendente atendente) {
        Atendente novoAtendente = atendenteService.criarAtendente(atendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtendente);
    }

    @PutMapping("/{idAtendente}")
    public ResponseEntity<Atendente> atualizarAtendente(
            @PathVariable Integer idAtendente,
            @RequestBody Atendente atendente) {
        atendente.setIdAtendente(idAtendente);
        Atendente atendenteAtualizado = atendenteService.atualizarAtendente(atendente);
        return ResponseEntity.ok(atendenteAtualizado);
    }

    @DeleteMapping("/{idAtendente}")
    public ResponseEntity<Void> deletarAtendente(@PathVariable Integer idAtendente) {
        atendenteService.deletarAtendente(idAtendente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/funcionario/{cpfFuncionario}")
    public ResponseEntity<Atendente> buscarPorCpfFuncionario(@PathVariable Long cpfFuncionario) {
        return atendenteService.buscarPorCpfFuncionario(cpfFuncionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}