package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class VendaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Venda> vendaRowMapper = (rs, rowNum) -> {
        Venda venda = new Venda();
        venda.setIdVenda(rs.getInt("id_venda"));
        venda.setCpfComprador(rs.getLong("cpf_comprador"));
        venda.setCodigoProduto(rs.getInt("codigo_produto"));
        Date dataVenda = rs.getDate("data_venda");
        venda.setDataVenda(dataVenda != null ? dataVenda.toLocalDate() : null);
        return venda;
    };

    public List<Venda> findAll() {
        return jdbcTemplate.query("SELECT * FROM venda", vendaRowMapper);
    }

    public Optional<Venda> findById(Integer idVenda) {
        try {
            Venda venda = jdbcTemplate.queryForObject(
                    "SELECT * FROM venda WHERE id_venda = ?",
                    new Object[]{idVenda},
                    vendaRowMapper
            );
            return Optional.ofNullable(venda);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Venda save(Venda venda) {
        String sql = "INSERT INTO venda (id_venda, cpf_comprador, codigo_produto, data_venda) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_comprador = ?, codigo_produto = ?, data_venda = ?";

        jdbcTemplate.update(sql,
                venda.getIdVenda(),
                venda.getCpfComprador(),
                venda.getCodigoProduto(),
                Date.valueOf(venda.getDataVenda()),
                venda.getCpfComprador(),
                venda.getCodigoProduto(),
                Date.valueOf(venda.getDataVenda())
        );

        return venda;
    }

    public void delete(Integer idVenda) {
        jdbcTemplate.update("DELETE FROM venda WHERE id_venda = ?", idVenda);
    }

    public List<Venda> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM venda WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                vendaRowMapper
        );
    }

    public List<Venda> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM venda WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                vendaRowMapper
        );
    }

    public List<Venda> findByDataVendaBetween(LocalDate dataInicio, LocalDate dataFim) {
        return jdbcTemplate.query(
                "SELECT * FROM venda WHERE data_venda BETWEEN ? AND ?",
                new Object[]{Date.valueOf(dataInicio), Date.valueOf(dataFim)},
                vendaRowMapper
        );
    }
}