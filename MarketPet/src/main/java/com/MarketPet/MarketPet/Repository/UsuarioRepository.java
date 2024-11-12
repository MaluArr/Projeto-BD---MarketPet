package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) -> {
        Usuario usuario = new Usuario();
        usuario.setCpf(rs.getLong("CPF"));
        usuario.setNomeReal(rs.getString("nome_real"));
        usuario.setNomeUsuario(rs.getString("nome_usuario"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTelefone1(rs.getInt("telefone1"));
        usuario.setTelefone2(rs.getInt("telefone2"));
        return usuario;
    };

    public List<Usuario> findAll() {
        return jdbcTemplate.query("SELECT * FROM usuario", usuarioRowMapper);
    }

    public Optional<Usuario> findByCpf(Long cpf) {
        try {
            Usuario usuario = jdbcTemplate.queryForObject(
                    "SELECT * FROM usuario WHERE CPF = ?",
                    new Object[]{cpf},
                    usuarioRowMapper
            );
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (CPF, nome_real, nome_usuario, email, senha, telefone1, telefone2) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                usuario.getCpf(),
                usuario.getNomeReal(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone1(),
                usuario.getTelefone2()
        );

        return usuario;
    }

    public void delete(Long cpf) {
        jdbcTemplate.update("DELETE FROM usuario WHERE CPF = ?", cpf);
    }

    public boolean existsByEmail(String email) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM usuario WHERE email = ?",
                Integer.class,
                email
        );
        return count != null && count > 0;
    }

    public boolean existsByNomeUsuario(String nomeUsuario) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM usuario WHERE nome_usuario = ?",
                Integer.class,
                nomeUsuario
        );
        return count != null && count > 0;
    }
}