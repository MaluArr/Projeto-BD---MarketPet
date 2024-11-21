package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Curador;
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
public class CuradorRepository {

    private static final Logger logger = LoggerFactory.getLogger(CuradorRepository.class);

    public List<Curador> findAll() {
        List<Curador> curadores = new ArrayList<>();
        String sql = "SELECT * FROM curador";

        logger.info("Buscando todos os curadores...");
        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                curadores.add(mapRowToCurador(rs));
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os curadores", e);
        }
        return curadores;
    }

    public Optional<Curador> findById(Integer idCurador) {
        String sql = "SELECT * FROM curador WHERE id_curador = ?";
        logger.info("Buscando curador pelo ID: {}", idCurador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToCurador(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar curador pelo ID: {}", idCurador, e);
        }
        return Optional.empty();
    }

    public Optional<Curador> findByFuncionario(Long cpfFuncionario) {
        String sql = "SELECT * FROM curador WHERE cpf_funcionario = ?";
        logger.info("Buscando curador pelo CPF do funcionário: {}", cpfFuncionario);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToCurador(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao buscar curador pelo CPF do funcionário: {}", cpfFuncionario, e);
        }
        return Optional.empty();
    }

    public Curador save(Curador curador) {
        String sql = curador.getIdCurador() == null
                ? "INSERT INTO curador (cpf_funcionario) VALUES (?)"
                : "UPDATE curador SET cpf_funcionario = ? WHERE id_curador = ?";
        logger.info("Salvando curador: {}", curador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, curador.getCpfFuncionario());
            if (curador.getIdCurador() != null) {
                ps.setInt(2, curador.getIdCurador());
            }

            ps.executeUpdate();

            if (curador.getIdCurador() == null) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        curador.setIdCurador(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao salvar curador: {}", curador, e);
            throw new RuntimeException("Erro ao salvar curador", e);
        }
        return curador;
    }

    public void delete(Integer idCurador) {
        String sql = "DELETE FROM curador WHERE id_curador = ?";
        logger.info("Deletando curador pelo ID: {}", idCurador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurador);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Erro ao deletar curador pelo ID: {}", idCurador, e);
            throw new RuntimeException("Erro ao deletar curador", e);
        }
    }

    private Curador mapRowToCurador(ResultSet rs) throws SQLException {
        Curador curador = new Curador();
        curador.setIdCurador(rs.getInt("id_curador"));
        curador.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        return curador;
    }
}
