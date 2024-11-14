package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Vendedor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VendedorRepository {

    private static final Logger logger = LoggerFactory.getLogger(VendedorRepository.class);

    public List<Vendedor> findAll() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";
        logger.info("Executando consulta para buscar todos os vendedores");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vendedor vendedor = mapRowToVendedor(rs);
                vendedores.add(vendedor);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os vendedores", e);
        }

        return vendedores;
    }

    public Optional<Vendedor> findByCpf(Long cpf) {
        String sql = "SELECT * FROM vendedor WHERE CPF = ?";
        logger.info("Executando consulta para buscar vendedor com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vendedor vendedor = mapRowToVendedor(rs);
                    return Optional.of(vendedor);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendedor com CPF: {}", cpf, e);
        }

        return Optional.empty();
    }

    public Vendedor save(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (CPF, descricao, foto_perfil, total_vendas, avaliacao_media, data_inicio_vendas) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE descricao = ?, foto_perfil = ?, total_vendas = ?, avaliacao_media = ?";
        logger.info("Salvando vendedor com CPF: {}", vendedor.getCpf());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, vendedor.getCpf());
            ps.setString(2, vendedor.getDescricao());
            ps.setString(3, vendedor.getFotoPerfil());
            ps.setBigDecimal(4, vendedor.getTotalVendas());
            ps.setBigDecimal(5, vendedor.getAvaliacaoMedia());
            ps.setDate(6, Date.valueOf(vendedor.getDataInicioVendas()));

            // Valores para UPDATE
            ps.setString(7, vendedor.getDescricao());
            ps.setString(8, vendedor.getFotoPerfil());
            ps.setBigDecimal(9, vendedor.getTotalVendas());
            ps.setBigDecimal(10, vendedor.getAvaliacaoMedia());

            ps.executeUpdate();
            logger.info("Vendedor salvo com sucesso: {}", vendedor.getCpf());

        } catch (SQLException e) {
            logger.error("Erro ao salvar vendedor com CPF: {}", vendedor.getCpf(), e);
        }

        return vendedor;
    }

    public void delete(Long cpf) {
        String sql = "DELETE FROM vendedor WHERE CPF = ?";
        logger.info("Deletando vendedor com CPF: {}", cpf);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpf);
            ps.executeUpdate();
            logger.info("Vendedor deletado com sucesso: {}", cpf);

        } catch (SQLException e) {
            logger.error("Erro ao deletar vendedor com CPF: {}", cpf, e);
        }
    }

    public List<Vendedor> findByAvaliacaoMediaGreaterThan(BigDecimal nota) {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedor WHERE avaliacao_media > ?";
        logger.info("Executando consulta para buscar vendedores com avaliação média maior que: {}", nota);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, nota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vendedor vendedor = mapRowToVendedor(rs);
                    vendedores.add(vendedor);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendedores com avaliação média maior que: {}", nota, e);
        }

        return vendedores;
    }

    public List<Vendedor> findByTotalVendasGreaterThan(BigDecimal valor) {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedor WHERE total_vendas > ?";
        logger.info("Executando consulta para buscar vendedores com total de vendas maior que: {}", valor);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vendedor vendedor = mapRowToVendedor(rs);
                    vendedores.add(vendedor);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendedores com total de vendas maior que: {}", valor, e);
        }

        return vendedores;
    }

    private Vendedor mapRowToVendedor(ResultSet rs) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setCpf(rs.getLong("CPF"));
        vendedor.setDescricao(rs.getString("descricao"));
        vendedor.setFotoPerfil(rs.getString("foto_perfil"));
        vendedor.setTotalVendas(rs.getBigDecimal("total_vendas"));
        vendedor.setAvaliacaoMedia(rs.getBigDecimal("avaliacao_media"));
        Date dataInicioVendas = rs.getDate("data_inicio_vendas");
        vendedor.setDataInicioVendas(dataInicioVendas != null ? dataInicioVendas.toLocalDate() : null);
        return vendedor;
    }
}