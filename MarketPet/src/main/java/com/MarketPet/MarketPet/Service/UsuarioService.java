package com.MarketPet.MarketPet.Service;

import com.MarketPet.MarketPet.Model.Usuario;
import com.MarketPet.MarketPet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorCpf(Long cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (usuarioRepository.existsByNomeUsuario(usuario.getNomeUsuario())) {
            throw new RuntimeException("Nome de usuário já existe");
        }

        if (usuario.getSenha().length() < 8) {
            throw new RuntimeException("Senha deve ter no mínimo 8 caracteres");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        usuarioRepository.findByCpf(usuario.getCpf())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long cpf) {
        usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(cpf);
    }
}
