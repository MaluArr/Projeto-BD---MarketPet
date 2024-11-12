package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Atendente;
import com.MarketPet.MarketPet.Model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AtendenteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private RowMapper<Atendente> atendenteRowMapper = (rs, rowNum) -> {
        Atendente atendente = new Atendente();
        atendente.setIdAtendente(rs.getInt("id_atendente"));

        // Busca do Funcionário
        Long cpfFuncionario = rs.getLong("cpf_funcionario");
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpfFuncionario);
        funcionarioOpt.ifPresent(atendente::setFuncionario);

        return atendente;
    };

    public List<Atendente> findAll() {
        return jdbcTemplate.query("SELECT * FROM atendente", atendenteRowMapper);
    }

    public Optional<Atendente> findById(Integer idAtendente) {
        try {
            Atendente atendente = jdbcTemplate.queryForObject(
                    "SELECT * FROM atendente WHERE id_atendente = ?",
                    new Object[]{idAtendente},
                    atendenteRowMapper
            );
            return Optional.ofNullable(atendente);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Atendente> findByFuncionario(Long cpfFuncionario) {
        try {
            Atendente atendente = jdbcTemplate.queryForObject(
                    "SELECT * FROM atendente WHERE cpf_funcionario = ?",
                    new Object[]{cpfFuncionario},
                    atendenteRowMapper
            );
            return Optional.ofNullable(atendente);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Atendente save(Atendente atendente) {
        String sql = "INSERT INTO atendente (id_atendente, cpf_funcionario) " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_funcionario = ?";

        jdbcTemplate.update(sql,
                atendente.getIdAtendente(),
                atendente.getFuncionario().getCpfFuncionario(),
                // Valor para UPDATE
                atendente.getFuncionario().getCpfFuncionario()
        );

        return atendente;
    }

    public void delete(Integer idAtendente) {
        jdbcTemplate.update("DELETE FROM atendente WHERE id_atendente = ?", idAtendente);
    }
}