package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Atendimento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AtendimentoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AtendimentoRepository.class);

    public List<Atendimento> findAll() {
        List<Atendimento> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM atendimento";
        logger.info("Executando consulta para buscar todos os atendimentos");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Atendimento atendimento = mapRowToAtendimento(rs);
                atendimentos.add(atendimento);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os atendimentos", e);
        }

        return atendimentos;
    }

    public Optional<Atendimento> findById(Integer idAtendimento) {
        String sql = "SELECT * FROM atendimento WHERE id_atendimento = ?";
        logger.info("Executando consulta para buscar atendimento com ID: {}", idAtendimento);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAtendimento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Atendimento atendimento = mapRowToAtendimento(rs);
                    return Optional.of(atendimento);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimento com ID: {}", idAtendimento, e);
        }

        return Optional.empty();
    }

    public Atendimento save(Atendimento atendimento) {
        String sql = "INSERT INTO atendimento (id_atendimento, cpf_funcionario, cpf_usuario, id_chat, data_atendimento, categoria) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_funcionario = ?, cpf_usuario = ?, id_chat = ?, data_atendimento = ?, categoria = ?";
        logger.info("Salvando atendimento com ID: {}", atendimento.getIdAtendimento());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, atendimento.getIdAtendimento());
            ps.setLong(2, atendimento.getCpfFuncionario());
            ps.setLong(3, atendimento.getCpfUsuario());
            ps.setInt(4, atendimento.getIdChat());
            ps.setDate(5, Date.valueOf(atendimento.getDataAtendimento()));
            ps.setString(6, atendimento.getCategoria());

            ps.setLong(7, atendimento.getCpfFuncionario());
            ps.setLong(8, atendimento.getCpfUsuario());
            ps.setInt(9, atendimento.getIdChat());
            ps.setDate(10, Date.valueOf(atendimento.getDataAtendimento()));
            ps.setString(11, atendimento.getCategoria());

            ps.executeUpdate();
            logger.info("Atendimento salvo com sucesso: {}", atendimento.getIdAtendimento());

        } catch (SQLException e) {
            logger.error("Erro ao salvar atendimento com ID: {}", atendimento.getIdAtendimento(), e);
        }

        return atendimento;
    }

    public void deleteById(Integer idAtendimento) {
        String sql = "DELETE FROM atendimento WHERE id_atendimento = ?";
        logger.info("Deletando atendimento com ID: {}", idAtendimento);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAtendimento);
            ps.executeUpdate();
            logger.info("Atendimento deletado com sucesso: {}", idAtendimento);

        } catch (SQLException e) {
            logger.error("Erro ao deletar atendimento com ID: {}", idAtendimento, e);
        }
    }

    public List<Map<String, Object>> getAtendimentosPorFuncionario() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT fm.nome, COUNT(*) as total_atendimentos " +
                "FROM atendimento a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "GROUP BY fm.cpf_funcionario, fm.nome";
        logger.info("Executando consulta para buscar atendimentos por funcionário");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "nome", rs.getString("nome"),
                        "total_atendimentos", rs.getInt("total_atendimentos")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimentos por funcionário", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getAtendimentosPorCategoria() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT categoria, COUNT(*) as total FROM atendimento GROUP BY categoria";
        logger.info("Executando consulta para buscar atendimentos por categoria");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "categoria", rs.getString("categoria"),
                        "total", rs.getInt("total")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimentos por categoria", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getAtendimentosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT DATE(data_atendimento) as data, COUNT(*) as total " +
                "FROM atendimento " +
                "WHERE data_atendimento BETWEEN ? AND ? " +
                "GROUP BY DATE(data_atendimento)";
        logger.info("Executando consulta para buscar atendimentos por período: {} a {}", dataInicio, dataFim);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(dataInicio));
            ps.setDate(2, Date.valueOf(dataFim));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultados.add(Map.of(
                            "data", rs.getDate("data").toLocalDate(),
                            "total", rs.getInt("total")
                    ));
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar atendimentos por período", e);
        }

        return resultados;
    }

    private Atendimento mapRowToAtendimento(ResultSet rs) throws SQLException {
        Atendimento atendimento = new Atendimento();
        atendimento.setIdAtendimento(rs.getInt("id_atendimento"));
        atendimento.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        atendimento.setCpfUsuario(rs.getLong("cpf_usuario"));
        atendimento.setIdChat(rs.getInt("id_chat"));
        atendimento.setDataAtendimento(rs.getDate("data_atendimento").toLocalDate());
        atendimento.setCategoria(rs.getString("categoria"));
        return atendimento;
    }
}