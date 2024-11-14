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
        logger.info("Executando consulta para buscar todos os curadores");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curador curador = mapRowToCurador(rs);
                curadores.add(curador);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os curadores", e);
        }

        return curadores;
    }

    public Optional<Curador> findById(Integer idCurador) {
        String sql = "SELECT * FROM curador WHERE id_curador = ?";
        logger.info("Executando consulta para buscar curador com ID: {}", idCurador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Curador curador = mapRowToCurador(rs);
                    return Optional.of(curador);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar curador com ID: {}", idCurador, e);
        }

        return Optional.empty();
    }

    public Optional<Curador> findByFuncionario(Long cpfFuncionario) {
        String sql = "SELECT * FROM curador WHERE cpf_funcionario = ?";
        logger.info("Executando consulta para buscar curador com CPF do funcionário: {}", cpfFuncionario);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Curador curador = mapRowToCurador(rs);
                    return Optional.of(curador);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar curador com CPF do funcionário: {}", cpfFuncionario, e);
        }

        return Optional.empty();
    }

    public Curador save(Curador curador) {
        String sql = "INSERT INTO curador (id_curador, cpf_funcionario) " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_funcionario = ?";
        logger.info("Salvando curador com ID: {}", curador.getIdCurador());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, curador.getIdCurador());
            ps.setLong(2, curador.getCpfFuncionario());
            ps.setLong(3, curador.getCpfFuncionario());

            ps.executeUpdate();
            logger.info("Curador salvo com sucesso: {}", curador.getIdCurador());

        } catch (SQLException e) {
            logger.error("Erro ao salvar curador com ID: {}", curador.getIdCurador(), e);
        }

        return curador;
    }

    public void delete(Integer idCurador) {
        String sql = "DELETE FROM curador WHERE id_curador = ?";
        logger.info("Deletando curador com ID: {}", idCurador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurador);
            ps.executeUpdate();
            logger.info("Curador deletado com sucesso: {}", idCurador);

        } catch (SQLException e) {
            logger.error("Erro ao deletar curador com ID: {}", idCurador, e);
        }
    }

    private Curador mapRowToCurador(ResultSet rs) throws SQLException {
        Curador curador = new Curador();
        curador.setIdCurador(rs.getInt("id_curador"));
        curador.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        return curador;
    }
}