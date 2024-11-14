package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Atendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Repository
public class AtendenteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Atendente> atendenteRowMapper = (rs, rowNum) -> {
        Atendente atendente = new Atendente();
        atendente.setIdAtendente(rs.getInt("id_atendente"));
        atendente.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        return atendente;
    };

    public List<Atendente> findAll() {
        return jdbcTemplate.query("SELECT * FROM atendente", atendenteRowMapper);
    }

    public Optional<Atendente> findById(Integer idAtendente) {
        String sql = "SELECT * FROM atendente WHERE id_atendente = ?";
        List<Atendente> results = jdbcTemplate.query(sql, new Object[]{idAtendente}, atendenteRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Atendente save(Atendente atendente) {
        String sql = "INSERT INTO atendente (id_atendente, cpf_funcionario) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_funcionario = ?";
        jdbcTemplate.update(sql, atendente.getIdAtendente(), atendente.getCpfFuncionario(), atendente.getCpfFuncionario());
        return atendente;
    }

    public void deleteById(Integer idAtendente) {
        jdbcTemplate.update("DELETE FROM atendente WHERE id_atendente = ?", idAtendente);
    }

    public List<Map<String, Object>> getAtendimentosPorAtendente() {
        String sql = "SELECT a.id_atendente, fm.nome, COUNT(at.id_atendimento) as total_atendimentos " +
                "FROM atendente a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "LEFT JOIN atendimento at ON fm.cpf_funcionario = at.cpf_funcionario " +
                "GROUP BY a.id_atendente, fm.nome";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAtendimentosPorPeriodo(String dataInicio, String dataFim) {
        String sql = "SELECT a.id_atendente, fm.nome, COUNT(at.id_atendimento) as total_atendimentos " +
                "FROM atendente a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "LEFT JOIN atendimento at ON fm.cpf_funcionario = at.cpf_funcionario " +
                "WHERE at.data_atendimento BETWEEN ? AND ? " +
                "GROUP BY a.id_atendente, fm.nome";
        return jdbcTemplate.queryForList(sql, dataInicio, dataFim);
    }
}