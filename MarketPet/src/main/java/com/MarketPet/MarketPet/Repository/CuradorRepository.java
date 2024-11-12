package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Curador;
import com.MarketPet.MarketPet.Model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuradorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private RowMapper<Curador> curadorRowMapper = (rs, rowNum) -> {
        Curador curador = new Curador();
        curador.setIdCurador(rs.getInt("id_curador"));

        // Busca do Funcionário
        Long cpfFuncionario = rs.getLong("cpf_funcionario");
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpfFuncionario);
        funcionarioOpt.ifPresent(curador::setFuncionario);

        return curador;
    };

    public List<Curador> findAll() {
        return jdbcTemplate.query("SELECT * FROM curador", curadorRowMapper);
    }

    public Optional<Curador> findById(Integer idCurador) {
        try {
            Curador curador = jdbcTemplate.queryForObject(
                    "SELECT * FROM curador WHERE id_curador = ?",
                    new Object[]{idCurador},
                    curadorRowMapper
            );
            return Optional.ofNullable(curador);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Curador> findByFuncionario(Long cpfFuncionario) {
        try {
            Curador curador = jdbcTemplate.queryForObject(
                    "SELECT * FROM curador WHERE cpf_funcionario = ?",
                    new Object[]{cpfFuncionario},
                    curadorRowMapper
            );
            return Optional.ofNullable(curador);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Curador save(Curador curador) {
        String sql = "INSERT INTO curador (id_curador, cpf_funcionario) " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_funcionario = ?";

        jdbcTemplate.update(sql,
                curador.getIdCurador(),
                curador.getFuncionario().getCpfFuncionario(),
                // Valor para UPDATE
                curador.getFuncionario().getCpfFuncionario()
        );

        return curador;
    }

    public void delete(Integer idCurador) {
        jdbcTemplate.update("DELETE FROM curador WHERE id_curador = ?", idCurador);
    }
}