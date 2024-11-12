package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Endereco> enderecoRowMapper = (rs, rowNum) -> {
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
    };

    public List<Endereco> findAll() {
        return jdbcTemplate.query("SELECT * FROM endereco", enderecoRowMapper);
    }

    public Optional<Endereco> findById(Integer idEndereco) {
        try {
            Endereco endereco = jdbcTemplate.queryForObject(
                    "SELECT * FROM endereco WHERE id_endereco = ?",
                    new Object[]{idEndereco},
                    enderecoRowMapper
            );
            return Optional.ofNullable(endereco);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Endereco save(Endereco endereco) {
        String sql = "INSERT INTO endereco " +
                "(id_endereco, cep, rua, numero, bairro, cidade, estado, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, complemento = ?";

        jdbcTemplate.update(sql,
                endereco.getIdEndereco(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getComplemento(),
                // Valores para UPDATE
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getComplemento()
        );

        return endereco;
    }

    public void delete(Integer idEndereco) {
        jdbcTemplate.update("DELETE FROM endereco WHERE id_endereco = ?", idEndereco);
    }

    public List<Endereco> findByEstado(String estado) {
        return jdbcTemplate.query(
                "SELECT * FROM endereco WHERE estado = ?",
                new Object[]{estado},
                enderecoRowMapper
        );
    }

    public List<Endereco> findByCidade(String cidade) {
        return jdbcTemplate.query(
                "SELECT * FROM endereco WHERE cidade = ?",
                new Object[]{cidade},
                enderecoRowMapper
        );
    }

    public List<Endereco> findByCep(String cep) {
        return jdbcTemplate.query(
                "SELECT * FROM endereco WHERE cep = ?",
                new Object[]{cep},
                enderecoRowMapper
        );
    }
}