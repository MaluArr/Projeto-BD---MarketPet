package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Endereco;
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
public class EnderecoRepository {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoRepository.class);

    public List<Endereco> findAll() {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        logger.info("Executando consulta para buscar todos os endereços");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = mapRowToEndereco(rs);
                enderecos.add(endereco);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os endereços", e);
        }

        return enderecos;
    }

    public Optional<Endereco> findById(Integer idEndereco) {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        logger.info("Executando consulta para buscar endereço com ID: {}", idEndereco);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEndereco);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = mapRowToEndereco(rs);
                    return Optional.of(endereco);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar endereço com ID: {}", idEndereco, e);
        }

        return Optional.empty();
    }

    public Endereco save(Endereco endereco) {
        String insertSql = "INSERT INTO endereco (cep, rua, numero, bairro, cidade, estado, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE endereco SET cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, complemento = ? WHERE id_endereco = ?";

        try (Connection con = JDBC_Connection.getConnection()) {
            if (endereco.getIdEndereco() == null) {
                // Inserir novo endereço
                try (PreparedStatement ps = con.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, endereco.getCep());
                    ps.setString(2, endereco.getRua());
                    ps.setString(3, endereco.getNumero());
                    ps.setString(4, endereco.getBairro());
                    ps.setString(5, endereco.getCidade());
                    ps.setString(6, endereco.getEstado());
                    ps.setString(7, endereco.getComplemento());

                    ps.executeUpdate();

                    // Obter ID gerado automaticamente
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            endereco.setIdEndereco(rs.getInt(1));
                        }
                    }
                }
            } else {
                // Atualizar endereço existente
                try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                    ps.setString(1, endereco.getCep());
                    ps.setString(2, endereco.getRua());
                    ps.setString(3, endereco.getNumero());
                    ps.setString(4, endereco.getBairro());
                    ps.setString(5, endereco.getCidade());
                    ps.setString(6, endereco.getEstado());
                    ps.setString(7, endereco.getComplemento());
                    ps.setInt(8, endereco.getIdEndereco());

                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao salvar endereço", e);
            throw new RuntimeException("Erro ao salvar endereço", e);
        }

        return endereco;
    }



    public void delete(Integer idEndereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        logger.info("Deletando endereço com ID: {}", idEndereco);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEndereco);
            ps.executeUpdate();
            logger.info("Endereço deletado com sucesso: {}", idEndereco);

        } catch (SQLException e) {
            logger.error("Erro ao deletar endereço com ID: {}", idEndereco, e);
        }
    }

    public List<Endereco> findByEstado(String estado) {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco WHERE estado = ?";
        logger.info("Executando consulta para buscar endereços por estado: {}", estado);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = mapRowToEndereco(rs);
                    enderecos.add(endereco);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar endereços por estado: {}", estado, e);
        }

        return enderecos;
    }

    public List<Endereco> findByCidade(String cidade) {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco WHERE cidade = ?";
        logger.info("Executando consulta para buscar endereços por cidade: {}", cidade);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cidade);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = mapRowToEndereco(rs);
                    enderecos.add(endereco);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar endereços por cidade: {}", cidade, e);
        }

        return enderecos;
    }

    public List<Endereco> findByCep(String cep) {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco WHERE cep = ?";
        logger.info("Executando consulta para buscar endereços por CEP: {}", cep);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cep);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = mapRowToEndereco(rs);
                    enderecos.add(endereco);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar endereços por CEP: {}", cep, e);
        }

        return enderecos;
    }

    private Endereco mapRowToEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(rs.getInt("id_endereco"));
        endereco.setCep(rs.getString("cep"));
        endereco.setRua(rs.getString("rua"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setEstado(rs.getString("estado"));
        endereco.setComplemento(rs.getString("complemento"));
        return endereco;
    }
}