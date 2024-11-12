package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Funcionario;
import com.MarketPet.MarketPet.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{cpfFuncionario}")
    public ResponseEntity<Funcionario> buscarPorCpf(@PathVariable Long cpfFuncionario) {
        return funcionarioService.buscarPorCpf(cpfFuncionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.criarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    @PutMapping("/{cpfFuncionario}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
            @PathVariable Long cpfFuncionario,
            @RequestBody Funcionario funcionario) {
        funcionario.setCpfFuncionario(cpfFuncionario);
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{cpfFuncionario}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long cpfFuncionario) {
        funcionarioService.deletarFuncionario(cpfFuncionario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<Funcionario> buscarPorNome(@RequestParam String nome) {
        return funcionarioService.buscarPorNome(nome);
    }
}