package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Model.Endereco;
import com.MarketPet.MarketPet.Model.Cartao;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    private RowMapper<Comprador> compradorRowMapper = (rs, rowNum) -> {
        Comprador comprador = new Comprador();
        comprador.setCpf(rs.getLong("CPF"));

        // Busca o endereço
        Integer idEndereco = rs.getInt("id_endereco");
        if (idEndereco != 0) {
            Optional<Endereco> enderecoOpt = enderecoRepository.findById(idEndereco);
            enderecoOpt.ifPresent(comprador::setEndereco);
        }

        // Busca o cartão
        Integer idCartao = rs.getInt("id_cartao");
        if (idCartao != 0) {
            Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);
            cartaoOpt.ifPresent(comprador::setCartao);
        }

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
                comprador.getEndereco() != null ? comprador.getEndereco().getIdEndereco() : null,
                comprador.getCartao() != null ? comprador.getCartao().getIdCartao() : null,
                // Valores para UPDATE
                comprador.getEndereco() != null ? comprador.getEndereco().getIdEndereco() : null,
                comprador.getCartao() != null ? comprador.getCartao().getIdCartao() : null
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