package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Endereco;
import com.MarketPet.MarketPet.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer idEndereco) {
        return enderecoService.buscarPorId(idEndereco)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.criarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<Endereco> atualizarEndereco(
            @PathVariable Integer idEndereco,
            @RequestBody Endereco endereco) {
        endereco.setIdEndereco(idEndereco);
        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco) {
        enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public List<Endereco> buscarPorEstado(@PathVariable String estado) {
        return enderecoService.buscarPorEstado(estado);
    }

    @GetMapping("/cidade/{cidade}")
    public List<Endereco> buscarPorCidade(@PathVariable String cidade) {
        return enderecoService.buscarPorCidade(cidade);
    }

    @GetMapping("/cep/{cep}")
    public List<Endereco> buscarPorCep(@PathVariable String cep) {
        return enderecoService.buscarPorCep(cep);
    }
}