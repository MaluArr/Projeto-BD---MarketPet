package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Curador;
import com.MarketPet.MarketPet.Service.CuradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curadores")
public class CuradorController {

    @Autowired
    private CuradorService curadorService;

    @GetMapping
    public List<Curador> listarTodos() {
        return curadorService.listarTodos();
    }

    @GetMapping("/{idCurador}")
    public ResponseEntity<Curador> buscarPorId(@PathVariable Integer idCurador) {
        return curadorService.buscarPorId(idCurador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curador> criarCurador(@RequestBody Curador curador) {
        Curador novoCurador = curadorService.criarCurador(curador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurador);
    }

    @PutMapping("/{idCurador}")
    public ResponseEntity<Curador> atualizarCurador(
            @PathVariable Integer idCurador,
            @RequestBody Curador curador) {
        curador.setIdCurador(idCurador);
        Curador curadorAtualizado = curadorService.atualizarCurador(curador);
        return ResponseEntity.ok(curadorAtualizado);
    }

    @DeleteMapping("/{idCurador}")
    public ResponseEntity<Void> deletarCurador(@PathVariable Integer idCurador) {
        curadorService.deletarCurador(idCurador);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/funcionario/{cpfFuncionario}")
    public ResponseEntity<Curador> buscarPorCpfFuncionario(@PathVariable Long cpfFuncionario) {
        return curadorService.buscarPorCpfFuncionario(cpfFuncionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}