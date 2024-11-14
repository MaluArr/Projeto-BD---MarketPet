package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class VendedorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Vendedor> vendedorRowMapper = (rs, rowNum) -> {
        Vendedor vendedor = new Vendedor();
        vendedor.setCpf(rs.getLong("CPF"));
        vendedor.setDescricao(rs.getString("descricao"));
        vendedor.setFotoPerfil(rs.getString("foto_perfil"));
        vendedor.setTotalVendas(rs.getBigDecimal("total_vendas"));
        vendedor.setAvaliacaoMedia(rs.getBigDecimal("avaliacao_media"));
        Date dataInicioVendas = rs.getDate("data_inicio_vendas");
        vendedor.setDataInicioVendas(dataInicioVendas != null ? dataInicioVendas.toLocalDate() : null);
        return vendedor;
    };

    public List<Vendedor> findAll() {
        return jdbcTemplate.query("SELECT * FROM vendedor", vendedorRowMapper);
    }

    public Optional<Vendedor> findByCpf(Long cpf) {
        try {
            Vendedor vendedor = jdbcTemplate.queryForObject(
                    "SELECT * FROM vendedor WHERE CPF = ?",
                    new Object[]{cpf},
                    vendedorRowMapper
            );
            return Optional.ofNullable(vendedor);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Vendedor save(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (CPF, descricao, foto_perfil, total_vendas, avaliacao_media, data_inicio_vendas) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE descricao = ?, foto_perfil = ?, total_vendas = ?, avaliacao_media = ?";

        jdbcTemplate.update(sql,
                vendedor.getCpf(),
                vendedor.getDescricao(),
                vendedor.getFotoPerfil(),
                vendedor.getTotalVendas(),
                vendedor.getAvaliacaoMedia(),
                Date.valueOf(vendedor.getDataInicioVendas()),
                vendedor.getDescricao(),
                vendedor.getFotoPerfil(),
                vendedor.getTotalVendas(),
                vendedor.getAvaliacaoMedia()
        );

        return vendedor;
    }

    public void delete(Long cpf) {
        jdbcTemplate.update("DELETE FROM vendedor WHERE CPF = ?", cpf);
    }

    public List<Vendedor> findByAvaliacaoMediaGreaterThan(BigDecimal nota) {
        return jdbcTemplate.query(
                "SELECT * FROM vendedor WHERE avaliacao_media > ?",
                new Object[]{nota},
                vendedorRowMapper
        );
    }

    public List<Vendedor> findByTotalVendasGreaterThan(BigDecimal valor) {
        return jdbcTemplate.query(
                "SELECT * FROM vendedor WHERE total_vendas > ?",
                new Object[]{valor},
                vendedorRowMapper
        );
    }
}