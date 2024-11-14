package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Usuario;
import com.MarketPet.MarketPet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable Long cpf) {
        return usuarioService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long cpf,
            @RequestBody Usuario usuario) {
        usuario.setCpf(cpf);
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long cpf) {
        usuarioService.deletarUsuario(cpf);
        return ResponseEntity.noContent().build();
    }
}