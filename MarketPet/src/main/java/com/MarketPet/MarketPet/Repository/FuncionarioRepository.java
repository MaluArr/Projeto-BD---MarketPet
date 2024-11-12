package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Funcionario> funcionarioRowMapper = (rs, rowNum) -> {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        return funcionario;
    };

    public List<Funcionario> findAll() {
        return jdbcTemplate.query("SELECT * FROM funcionario_marketpet", funcionarioRowMapper);
    }

    public Optional<Funcionario> findByCpf(Long cpfFuncionario) {
        try {
            Funcionario funcionario = jdbcTemplate.queryForObject(
                    "SELECT * FROM funcionario_marketpet WHERE cpf_funcionario = ?",
                    new Object[]{cpfFuncionario},
                    funcionarioRowMapper
            );
            return Optional.ofNullable(funcionario);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Funcionario save(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario_marketpet (cpf_funcionario, nome) " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "nome = ?";

        jdbcTemplate.update(sql,
                funcionario.getCpfFuncionario(),
                funcionario.getNome(),
                // Valor para UPDATE
                funcionario.getNome()
        );

        return funcionario;
    }

    public void delete(Long cpfFuncionario) {
        jdbcTemplate.update("DELETE FROM funcionario_marketpet WHERE cpf_funcionario = ?", cpfFuncionario);
    }

    public List<Funcionario> findByNomeContaining(String parteNome) {
        return jdbcTemplate.query(
                "SELECT * FROM funcionario_marketpet WHERE nome LIKE ?",
                new Object[]{"%" + parteNome + "%"},
                funcionarioRowMapper
        );
    }
}