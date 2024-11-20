package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRepository.class);

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        logger.info("Executando consulta para buscar todos os usuários");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = mapRowToUsuario(rs);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os usuários", e);
        }

        return usuarios;
    }

    public Optional<Usuario> findByCpf(Long cpf) {
        String sql = "SELECT * FROM usuario WHERE CPF = ?";
        logger.info("Executando consulta para buscar usuário com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = mapRowToUsuario(rs);
                    return Optional.of(usuario);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar usuário com CPF: {}", cpf, e);
        }

        return Optional.empty();
    }

    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (CPF, nome_real, nome_usuario, email, senha, telefone1, telefone2) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nome_real = ?, nome_usuario = ?, email = ?, senha = ?, telefone1 = ?, telefone2 = ?";
        logger.info("Salvando usuário com CPF: {}", usuario.getCpf());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, usuario.getCpf());
            ps.setString(2, usuario.getNomeReal());
            ps.setString(3, usuario.getNomeUsuario());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());
            ps.setLong(6, usuario.getTelefone1());
            ps.setLong(7, usuario.getTelefone2());

            // Valores para UPDATE
            ps.setString(8, usuario.getNomeReal());
            ps.setString(9, usuario.getNomeUsuario());
            ps.setString(10, usuario.getEmail());
            ps.setString(11, usuario.getSenha());
            ps.setLong(12, usuario.getTelefone1());
            ps.setLong(13, usuario.getTelefone2());

            ps.executeUpdate();
            logger.info("Usuário salvo com sucesso: {}", usuario.getCpf());

        } catch (SQLException e) {
            logger.error("Erro ao salvar usuário com CPF: {}", usuario.getCpf(), e);
        }

        return usuario;
    }

    public void delete(Long cpf) {
        String sql = "DELETE FROM usuario WHERE CPF = ?";
        logger.info("Deletando usuário com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            ps.executeUpdate();
            logger.info("Usuário deletado com sucesso: {}", cpf);

        } catch (SQLException e) {
            logger.error("Erro ao deletar usuário com CPF: {}", cpf, e);
        }
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        logger.info("Verificando existência de usuário com email: {}", email);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao verificar existência de usuário com email: {}", email, e);
        }

        return false;
    }

    public boolean existsByNomeUsuario(String nomeUsuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nome_usuario = ?";
        logger.info("Verificando existência de usuário com nome de usuário: {}", nomeUsuario);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao verificar existência de usuário com nome de usuário: {}", nomeUsuario, e);
        }

        return false;
    }

    private Usuario mapRowToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setCpf(rs.getLong("CPF"));
        usuario.setNomeReal(rs.getString("nome_real"));
        usuario.setNomeUsuario(rs.getString("nome_usuario"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTelefone1(rs.getLong("telefone1"));
        usuario.setTelefone2(rs.getLong("telefone2"));
        return usuario;
    }
}