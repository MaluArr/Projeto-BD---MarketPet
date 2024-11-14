package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Avaliacao;
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
public class AvaliacaoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoRepository.class);

    public List<Avaliacao> findAll() {
        List<Avaliacao> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao";
        logger.info("Executando consulta para buscar todas as avaliações");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Avaliacao avaliacao = mapRowToAvaliacao(rs);
                avaliacoes.add(avaliacao);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todas as avaliações", e);
        }

        return avaliacoes;
    }

    public Optional<Avaliacao> findById(Integer idAvaliacao) {
        String sql = "SELECT * FROM avaliacao WHERE id_avaliacao = ?";
        logger.info("Executando consulta para buscar avaliação com ID: {}", idAvaliacao);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAvaliacao);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Avaliacao avaliacao = mapRowToAvaliacao(rs);
                    return Optional.of(avaliacao);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar avaliação com ID: {}", idAvaliacao, e);
        }

        return Optional.empty();
    }

    public Avaliacao save(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (id_avaliacao, cpf_comprador, codigo_produto, id_venda, nota) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, id_venda = ?, nota = ?";
        logger.info("Salvando avaliação com ID: {}", avaliacao.getIdAvaliacao());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, avaliacao.getIdAvaliacao());
            ps.setLong(2, avaliacao.getCpfComprador());
            ps.setInt(3, avaliacao.getCodigoProduto());
            ps.setInt(4, avaliacao.getIdVenda());
            ps.setBigDecimal(5, avaliacao.getNota());

            ps.setLong(6, avaliacao.getCpfComprador());
            ps.setInt(7, avaliacao.getCodigoProduto());
            ps.setInt(8, avaliacao.getIdVenda());
            ps.setBigDecimal(9, avaliacao.getNota());

            ps.executeUpdate();
            logger.info("Avaliação salva com sucesso: {}", avaliacao.getIdAvaliacao());

        } catch (SQLException e) {
            logger.error("Erro ao salvar avaliação com ID: {}", avaliacao.getIdAvaliacao(), e);
        }

        return avaliacao;
    }

    public void deleteById(Integer idAvaliacao) {
        String sql = "DELETE FROM avaliacao WHERE id_avaliacao = ?";
        logger.info("Deletando avaliação com ID: {}", idAvaliacao);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAvaliacao);
            ps.executeUpdate();
            logger.info("Avaliação deletada com sucesso: {}", idAvaliacao);

        } catch (SQLException e) {
            logger.error("Erro ao deletar avaliação com ID: {}", idAvaliacao, e);
        }
    }

    public List<Map<String, Object>> getAvaliacoesPorProduto() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT p.codigo_produto, p.descricao, AVG(a.nota) as media_avaliacao " +
                "FROM avaliacao a " +
                "JOIN produto p ON a.codigo_produto = p.codigo_produto " +
                "GROUP BY p.codigo_produto, p.descricao";
        logger.info("Executando consulta para buscar avaliações por produto");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "codigo_produto", rs.getInt("codigo_produto"),
                        "descricao", rs.getString("descricao"),
                        "media_avaliacao", rs.getBigDecimal("media_avaliacao")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar avaliações por produto", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getAvaliacoesPorComprador() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT u.nome_real, COUNT(*) as total_avaliacoes, AVG(a.nota) as media_avaliacao " +
                "FROM avaliacao a " +
                "JOIN usuario u ON a.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        logger.info("Executando consulta para buscar avaliações por comprador");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "nome_real", rs.getString("nome_real"),
                        "total_avaliacoes", rs.getInt("total_avaliacoes"),
                        "media_avaliacao", rs.getBigDecimal("media_avaliacao")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar avaliações por comprador", e);
        }

        return resultados;
    }

    private Avaliacao mapRowToAvaliacao(ResultSet rs) throws SQLException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
        avaliacao.setCpfComprador(rs.getLong("cpf_comprador"));
        avaliacao.setCodigoProduto(rs.getInt("codigo_produto"));
        avaliacao.setIdVenda(rs.getInt("id_venda"));
        avaliacao.setNota(rs.getBigDecimal("nota"));
        return avaliacao;
    }
}