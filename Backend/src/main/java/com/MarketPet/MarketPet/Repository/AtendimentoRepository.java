package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Atendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Repository
public class AtendimentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Atendimento> atendimentoRowMapper = (rs, rowNum) -> {
        Atendimento atendimento = new Atendimento();
        atendimento.setIdAtendimento(rs.getInt("id_atendimento"));
        atendimento.setCpfFuncionario(rs.getLong("cpf_funcionario"));
        atendimento.setCpfUsuario(rs.getLong("cpf_usuario"));
        atendimento.setIdChat(rs.getInt("id_chat"));
        atendimento.setDataAtendimento(rs.getDate("data_atendimento").toLocalDate());
        atendimento.setCategoria(rs.getString("categoria"));
        return atendimento;
    };

    public List<Atendimento> findAll() {
        return jdbcTemplate.query("SELECT * FROM atendimento", atendimentoRowMapper);
    }

    public Optional<Atendimento> findById(Integer idAtendimento) {
        String sql = "SELECT * FROM atendimento WHERE id_atendimento = ?";
        List<Atendimento> results = jdbcTemplate.query(sql, new Object[]{idAtendimento}, atendimentoRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Atendimento save(Atendimento atendimento) {
        String sql = "INSERT INTO atendimento (id_atendimento, cpf_funcionario, cpf_usuario, id_chat, data_atendimento, categoria) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_funcionario = ?, cpf_usuario = ?, id_chat = ?, data_atendimento = ?, categoria = ?";

        jdbcTemplate.update(sql,
                atendimento.getIdAtendimento(), atendimento.getCpfFuncionario(), atendimento.getCpfUsuario(),
                atendimento.getIdChat(), Date.valueOf(atendimento.getDataAtendimento()), atendimento.getCategoria(),
                atendimento.getCpfFuncionario(), atendimento.getCpfUsuario(), atendimento.getIdChat(),
                Date.valueOf(atendimento.getDataAtendimento()), atendimento.getCategoria()
        );
        return atendimento;
    }

    public void deleteById(Integer idAtendimento) {
        jdbcTemplate.update("DELETE FROM atendimento WHERE id_atendimento = ?", idAtendimento);
    }

    public List<Map<String, Object>> getAtendimentosPorFuncionario() {
        String sql = "SELECT fm.nome, COUNT(*) as total_atendimentos " +
                "FROM atendimento a " +
                "JOIN funcionario_marketpet fm ON a.cpf_funcionario = fm.cpf_funcionario " +
                "GROUP BY fm.cpf_funcionario, fm.nome";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAtendimentosPorCategoria() {
        String sql = "SELECT categoria, COUNT(*) as total " +
                "FROM atendimento " +
                "GROUP BY categoria";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAtendimentosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        String sql = "SELECT DATE(data_atendimento) as data, COUNT(*) as total " +
                "FROM atendimento " +
                "WHERE data_atendimento BETWEEN ? AND ? " +
                "GROUP BY DATE(data_atendimento)";
        return jdbcTemplate.queryForList(sql, Date.valueOf(dataInicio), Date.valueOf(dataFim));
    }
}