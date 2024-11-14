package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Comprador;
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
public class CompradorRepository {

    private static final Logger logger = LoggerFactory.getLogger(CompradorRepository.class);

    public List<Comprador> findAll() {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM comprador";
        logger.info("Executando consulta para buscar todos os compradores");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Comprador comprador = new Comprador();
                comprador.setCpf(rs.getLong("CPF"));
                comprador.setIdEndereco(rs.getInt("id_endereco"));
                comprador.setIdCartao(rs.getInt("id_cartao"));
                compradores.add(comprador);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os compradores", e);
        }

        return compradores;
    }

    public Optional<Comprador> findByCpf(Long cpf) {
        String sql = "SELECT * FROM comprador WHERE CPF = ?";
        logger.info("Executando consulta para buscar comprador com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Comprador comprador = new Comprador();
                    comprador.setCpf(rs.getLong("CPF"));
                    comprador.setIdEndereco(rs.getInt("id_endereco"));
                    comprador.setIdCartao(rs.getInt("id_cartao"));
                    return Optional.of(comprador);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar comprador com CPF: {}", cpf, e);
        }

        return Optional.empty();
    }

    public Comprador save(Comprador comprador) {
        String sql = "INSERT INTO comprador (CPF, id_endereco, id_cartao) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "id_endereco = ?, id_cartao = ?";
        logger.info("Salvando comprador com CPF: {}", comprador.getCpf());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, comprador.getCpf());
            ps.setInt(2, comprador.getIdEndereco());
            ps.setInt(3, comprador.getIdCartao());
            ps.setInt(4, comprador.getIdEndereco());
            ps.setInt(5, comprador.getIdCartao());

            ps.executeUpdate();
            logger.info("Comprador salvo com sucesso: {}", comprador.getCpf());

        } catch (SQLException e) {
            logger.error("Erro ao salvar comprador com CPF: {}", comprador.getCpf(), e);
        }

        return comprador;
    }

    public void delete(Long cpf) {
        String sql = "DELETE FROM comprador WHERE CPF = ?";
        logger.info("Deletando comprador com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            ps.executeUpdate();
            logger.info("Comprador deletado com sucesso: {}", cpf);

        } catch (SQLException e) {
            logger.error("Erro ao deletar comprador com CPF: {}", cpf, e);
        }
    }

    public List<Comprador> findByEnderecoId(Integer idEndereco) {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM comprador WHERE id_endereco = ?";
        logger.info("Buscando compradores com id_endereco: {}", idEndereco);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEndereco);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprador comprador = new Comprador();
                    comprador.setCpf(rs.getLong("CPF"));
                    comprador.setIdEndereco(rs.getInt("id_endereco"));
                    comprador.setIdCartao(rs.getInt("id_cartao"));
                    compradores.add(comprador);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar compradores com id_endereco: {}", idEndereco, e);
        }

        return compradores;
    }

    public List<Comprador> findByCartaoId(Integer idCartao) {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "SELECT * FROM comprador WHERE id_cartao = ?";
        logger.info("Buscando compradores com id_cartao: {}", idCartao);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCartao);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprador comprador = new Comprador();
                    comprador.setCpf(rs.getLong("CPF"));
                    comprador.setIdEndereco(rs.getInt("id_endereco"));
                    comprador.setIdCartao(rs.getInt("id_cartao"));
                    compradores.add(comprador);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar compradores com id_cartao: {}", idCartao, e);
        }

        return compradores;
    }
}