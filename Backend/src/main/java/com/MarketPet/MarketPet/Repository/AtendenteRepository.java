package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Atendente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AtendenteRepository {

    private static final Logger logger = LoggerFactory.getLogger(AtendenteRepository.class);

    public List<Atendente> findAll() {
        List<Atendente> atendentes = new ArrayList<>();
        String sql = "SELECT * FROM atendente";
        logger.info("Executando consulta para buscar todos os atendentes");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Atendente atendente = mapRowToAtendente(rs);
                atendentes.add(atendente);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os atendentes", e);
        }

        return atendentes;
    }

    public Optional<Atendente> findById(Integer idAtendente) {
        String sql = "SELECT * FROM atendente WHERE id_atendente = ?";
        logger.info("Executando consulta para buscar atendente com ID: {}", idAtendente);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAtendente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Atendente atendente = mapRowToAtendente(rs);
                    return Optional.of(atendente);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendente com ID: {}", idAtendente, e);
        }

        return Optional.empty();
    }

    public Atendente save(Atendente atendente) {
        String sql = "INSERT INTO atendente (id_atendente, cpf_funcionario) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_funcionario = ?";
        logger.info("Salvando atendente com ID: {}", atendente.getIdAtendente());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, atendente.getIdAtendente());
            ps.setLong(2, atendente.getCpfFuncionario());
            ps.setLong(3, atendente.getCpfFuncionario());

            ps.executeUpdate();
            logger.info("Atendente salvo com sucesso: {}", atendente.getIdAtendente());

        } catch (SQLException e) {
            logger.error("Erro ao salvar atendente com ID: {}", atendente.getIdAtendente(), e);
        }

        return atendente;
    }

    public void deleteById(Integer idAtendente) {
        String sql = "DELETE FROM atendente WHERE id_atendente = ?";
        logger.info("Deletando atendente com ID: {}", idAtendente);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAtendente);
            ps.executeUpdate();
            logger.info("Atendente deletado com sucesso: {}", idAtendente);

        } catch (SQLException e) {
            logger.error("Erro ao deletar atendente com ID: {}", idAtendente, e);
        }
    }

    public List<Map<String, Object>> getAtendimentosPorAtendente() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT a.id_atendente, fm.nome, COUNT(at.id_atendimento) as total_atendimentos " +
                "FROM atendente a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "LEFT JOIN atendimento at ON fm.cpf_funcionario = at.cpf_funcionario " +
                "GROUP BY a.id_atendente, fm.nome";
        logger.info("Executando consulta para buscar atendimentos por atendente");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "id_atendente", rs.getInt("id_atendente"),
                        "nome", rs.getString("nome"),
                        "total_atendimentos", rs.getInt("total_atendimentos")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimentos por atendente", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getAtendimentosPorPeriodo(String dataInicio, String dataFim) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT a.id_atendente, fm.nome, COUNT(at.id_atendimento) as total_atendimentos " +
                "FROM atendente a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "LEFT JOIN atendimento at ON fm.cpf_funcionario = at.cpf_funcionario " +
                "WHERE at.data_atendimento BETWEEN ? AND ? " +
                "GROUP BY a.id_atendente, fm.nome";
        logger.info("Executando consulta para buscar atendimentos por período: {} a {}", dataInicio, dataFim);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dataInicio);
            ps.setString(2, dataFim);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.add(Map.of(
                            "id_atendente", rs.getInt("id_atendente"),
                            "nome", rs.getString("nome"),
                            "total_atendimentos", rs.getInt("total_atendimentos")
                    ));
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimentos por período", e);
        }

        return resultados;
    }

    private Atendente mapRowToAtendente(ResultSet rs) throws SQLException {
        Atendente atendente = new Atendente();
        atendente.setIdAtendente(rs.getInt("id_atendente"));
        atendente.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        return atendente;
    }
}