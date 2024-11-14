package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Venda;
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
import java.util.Optional;

@Repository
public class VendaRepository {

    private static final Logger logger = LoggerFactory.getLogger(VendaRepository.class);

    public List<Venda> findAll() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        logger.info("Executando consulta para buscar todas as vendas");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venda venda = mapRowToVenda(rs);
                vendas.add(venda);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todas as vendas", e);
        }

        return vendas;
    }

    public Optional<Venda> findById(Integer idVenda) {
        String sql = "SELECT * FROM venda WHERE id_venda = ?";
        logger.info("Executando consulta para buscar venda com ID: {}", idVenda);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenda);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venda venda = mapRowToVenda(rs);
                    return Optional.of(venda);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar venda com ID: {}", idVenda, e);
        }

        return Optional.empty();
    }

    public Venda save(Venda venda) {
        String sql = "INSERT INTO venda (id_venda, cpf_comprador, codigo_produto, data_venda) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_comprador = ?, codigo_produto = ?, data_venda = ?";
        logger.info("Salvando venda com ID: {}", venda.getIdVenda());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venda.getIdVenda());
            ps.setLong(2, venda.getCpfComprador());
            ps.setInt(3, venda.getCodigoProduto());
            ps.setDate(4, Date.valueOf(venda.getDataVenda()));

            ps.setLong(5, venda.getCpfComprador());
            ps.setInt(6, venda.getCodigoProduto());
            ps.setDate(7, Date.valueOf(venda.getDataVenda()));

            ps.executeUpdate();
            logger.info("Venda salva com sucesso: {}", venda.getIdVenda());

        } catch (SQLException e) {
            logger.error("Erro ao salvar venda com ID: {}", venda.getIdVenda(), e);
        }

        return venda;
    }

    public void delete(Integer idVenda) {
        String sql = "DELETE FROM venda WHERE id_venda = ?";
        logger.info("Deletando venda com ID: {}", idVenda);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenda);
            ps.executeUpdate();
            logger.info("Venda deletada com sucesso: {}", idVenda);

        } catch (SQLException e) {
            logger.error("Erro ao deletar venda com ID: {}", idVenda, e);
        }
    }

    public List<Venda> findByComprador(Long cpfComprador) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE cpf_comprador = ?";
        logger.info("Executando consulta para buscar vendas por comprador com CPF: {}", cpfComprador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfComprador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venda venda = mapRowToVenda(rs);
                    vendas.add(venda);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendas por comprador com CPF: {}", cpfComprador, e);
        }

        return vendas;
    }

    public List<Venda> findByProduto(Integer codigoProduto) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE codigo_produto = ?";
        logger.info("Executando consulta para buscar vendas por código de produto: {}", codigoProduto);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venda venda = mapRowToVenda(rs);
                    vendas.add(venda);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendas por código de produto: {}", codigoProduto, e);
        }

        return vendas;
    }

    public List<Venda> findByDataVendaBetween(LocalDate dataInicio, LocalDate dataFim) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE data_venda BETWEEN ? AND ?";
        logger.info("Executando consulta para buscar vendas entre: {} e {}", dataInicio, dataFim);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(dataInicio));
            ps.setDate(2, Date.valueOf(dataFim));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venda venda = mapRowToVenda(rs);
                    vendas.add(venda);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar vendas entre: {} e {}", dataInicio, dataFim, e);
        }

        return vendas;
    }

    private Venda mapRowToVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        venda.setIdVenda(rs.getInt("id_venda"));
        venda.setCpfComprador(rs.getLong("cpf_comprador"));
        venda.setCodigoProduto(rs.getInt("codigo_produto"));
        Date dataVenda = rs.getDate("data_venda");
        venda.setDataVenda(dataVenda != null ? dataVenda.toLocalDate() : null);
        return venda;
    }
}