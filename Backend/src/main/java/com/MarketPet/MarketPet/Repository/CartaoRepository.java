package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CartaoRepository {

    private static final Logger logger = LoggerFactory.getLogger(CartaoRepository.class);

    public List<Cartao> findAll() {
        List<Cartao> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM cartao";
        logger.info("Executando consulta para buscar todos os cartões");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cartao cartao = mapRowToCartao(rs);
                cartoes.add(cartao);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os cartões", e);
        }

        return cartoes;
    }

    public Optional<Cartao> findById(Integer idCartao) {
        String sql = "SELECT * FROM cartao WHERE id_cartao = ?";
        logger.info("Executando consulta para buscar cartão com ID: {}", idCartao);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCartao);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cartao cartao = mapRowToCartao(rs);
                    return Optional.of(cartao);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar cartão com ID: {}", idCartao, e);
        }

        return Optional.empty();
    }

    public Optional<Cartao> findByNumero(String numero) {
        String sql = "SELECT * FROM cartao WHERE numero = ?";
        logger.info("Executando consulta para buscar cartão com número: {}", numero);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cartao cartao = mapRowToCartao(rs);
                    return Optional.of(cartao);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar cartão com número: {}", numero, e);
        }

        return Optional.empty();
    }

    public Cartao save(Cartao cartao) {
        String sql = "INSERT INTO cartao (id_cartao, numero, nome_titular, validade, cvv, bandeira) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "numero = ?, nome_titular = ?, validade = ?, cvv = ?, bandeira = ?";
        logger.info("Salvando cartão com ID: {}", cartao.getIdCartao());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartao.getIdCartao());
            ps.setString(2, cartao.getNumero());
            ps.setString(3, cartao.getNomeTitular());
            ps.setDate(4, Date.valueOf(cartao.getValidade()));
            ps.setString(5, cartao.getCvv());
            ps.setString(6, cartao.getBandeira());

            ps.setString(7, cartao.getNumero());
            ps.setString(8, cartao.getNomeTitular());
            ps.setDate(9, Date.valueOf(cartao.getValidade()));
            ps.setString(10, cartao.getCvv());
            ps.setString(11, cartao.getBandeira());

            ps.executeUpdate();
            logger.info("Cartão salvo com sucesso: {}", cartao.getIdCartao());

        } catch (SQLException e) {
            logger.error("Erro ao salvar cartão com ID: {}", cartao.getIdCartao(), e);
        }

        return cartao;
    }

    public void delete(Integer idCartao) {
        String sql = "DELETE FROM cartao WHERE id_cartao = ?";
        logger.info("Deletando cartão com ID: {}", idCartao);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCartao);
            ps.executeUpdate();
            logger.info("Cartão deletado com sucesso: {}", idCartao);

        } catch (SQLException e) {
            logger.error("Erro ao deletar cartão com ID: {}", idCartao, e);
        }
    }

    public List<Cartao> findByBandeira(String bandeira) {
        List<Cartao> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM cartao WHERE bandeira = ?";
        logger.info("Executando consulta para buscar cartões com bandeira: {}", bandeira);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bandeira);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cartao cartao = mapRowToCartao(rs);
                    cartoes.add(cartao);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar cartões com bandeira: {}", bandeira, e);
        }

        return cartoes;
    }

    public boolean existsByNumero(String numero) {
        String sql = "SELECT COUNT(*) FROM cartao WHERE numero = ?";
        logger.info("Verificando existência de cartão com número: {}", numero);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao verificar existência de cartão com número: {}", numero, e);
        }

        return false;
    }

    private Cartao mapRowToCartao(ResultSet rs) throws SQLException {
        Cartao cartao = new Cartao();
        cartao.setIdCartao(rs.getInt("id_cartao"));
        cartao.setNumero(rs.getString("numero"));
        cartao.setNomeTitular(rs.getString("nome_titular"));
        Date validade = rs.getDate("validade");
        cartao.setValidade(validade != null ? validade.toLocalDate() : null);
        cartao.setCvv(rs.getString("cvv"));
        cartao.setBandeira(rs.getString("bandeira"));
        return cartao;
    }
}