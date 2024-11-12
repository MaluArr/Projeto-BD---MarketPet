package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CartaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Cartao> cartaoRowMapper = (rs, rowNum) -> {
        Cartao cartao = new Cartao();
        cartao.setIdCartao(rs.getInt("id_cartao"));
        cartao.setNumero(rs.getString("numero"));
        cartao.setNomeTitular(rs.getString("nome_titular"));

        // Conversão de Date para LocalDate
        Date validade = rs.getDate("validade");
        cartao.setValidade(validade != null ? validade.toLocalDate() : null);

        cartao.setCvv(rs.getString("cvv"));
        cartao.setBandeira(rs.getString("bandeira"));
        return cartao;
    };

    public List<Cartao> findAll() {
        return jdbcTemplate.query("SELECT * FROM cartao", cartaoRowMapper);
    }

    public Optional<Cartao> findById(Integer idCartao) {
        try {
            Cartao cartao = jdbcTemplate.queryForObject(
                    "SELECT * FROM cartao WHERE id_cartao = ?",
                    new Object[]{idCartao},
                    cartaoRowMapper
            );
            return Optional.ofNullable(cartao);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Cartao> findByNumero(String numero) {
        try {
            Cartao cartao = jdbcTemplate.queryForObject(
                    "SELECT * FROM cartao WHERE numero = ?",
                    new Object[]{numero},
                    cartaoRowMapper
            );
            return Optional.ofNullable(cartao);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Cartao save(Cartao cartao) {
        String sql = "INSERT INTO cartao " +
                "(id_cartao, numero, nome_titular, validade, cvv, bandeira) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "numero = ?, nome_titular = ?, validade = ?, cvv = ?, bandeira = ?";

        jdbcTemplate.update(sql,
                cartao.getIdCartao(),
                cartao.getNumero(),
                cartao.getNomeTitular(),
                Date.valueOf(cartao.getValidade()),
                cartao.getCvv(),
                cartao.getBandeira(),
                // Valores para UPDATE
                cartao.getNumero(),
                cartao.getNomeTitular(),
                Date.valueOf(cartao.getValidade()),
                cartao.getCvv(),
                cartao.getBandeira()
        );

        return cartao;
    }

    public void delete(Integer idCartao) {
        jdbcTemplate.update("DELETE FROM cartao WHERE id_cartao = ?", idCartao);
    }

    public List<Cartao> findByBandeira(String bandeira) {
        return jdbcTemplate.query(
                "SELECT * FROM cartao WHERE bandeira = ?",
                new Object[]{bandeira},
                cartaoRowMapper
        );
    }

    public boolean existsByNumero(String numero) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM cartao WHERE numero = ?",
                Integer.class,
                numero
        );
        return count != null && count > 0;
    }
}