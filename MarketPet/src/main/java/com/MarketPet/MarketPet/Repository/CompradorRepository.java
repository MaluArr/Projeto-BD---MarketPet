package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompradorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Comprador> compradorRowMapper = (rs, rowNum) -> {
        Comprador comprador = new Comprador();
        comprador.setCpf(rs.getLong("CPF"));
        comprador.setIdEndereco(rs.getInt("id_endereco"));
        comprador.setIdCartao(rs.getInt("id_cartao"));
        return comprador;
    };

    public List<Comprador> findAll() {
        return jdbcTemplate.query("SELECT * FROM comprador", compradorRowMapper);
    }

    public Optional<Comprador> findByCpf(Long cpf) {
        try {
            Comprador comprador = jdbcTemplate.queryForObject(
                    "SELECT * FROM comprador WHERE CPF = ?",
                    new Object[]{cpf},
                    compradorRowMapper
            );
            return Optional.ofNullable(comprador);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Comprador save(Comprador comprador) {
        String sql = "INSERT INTO comprador (CPF, id_endereco, id_cartao) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "id_endereco = ?, id_cartao = ?";

        jdbcTemplate.update(sql,
                comprador.getCpf(),
                comprador.getIdEndereco(),
                comprador.getIdCartao(),
                comprador.getIdEndereco(),
                comprador.getIdCartao()
        );

        return comprador;
    }

    public void delete(Long cpf) {
        jdbcTemplate.update("DELETE FROM comprador WHERE CPF = ?", cpf);
    }

    public List<Comprador> findByEnderecoId(Integer idEndereco) {
        return jdbcTemplate.query(
                "SELECT * FROM comprador WHERE id_endereco = ?",
                new Object[]{idEndereco},
                compradorRowMapper
        );
    }

    public List<Comprador> findByCartaoId(Integer idCartao) {
        return jdbcTemplate.query(
                "SELECT * FROM comprador WHERE id_cartao = ?",
                new Object[]{idCartao},
                compradorRowMapper
        );
    }
}