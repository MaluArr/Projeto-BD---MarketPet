package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Repository
public class AvaliacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Avaliacao> avaliacaoRowMapper = (rs, rowNum) -> {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
        avaliacao.setCpfComprador(rs.getLong("cpf_comprador"));
        avaliacao.setCodigoProduto(rs.getInt("codigo_produto"));
        avaliacao.setIdVenda(rs.getInt("id_venda"));
        avaliacao.setNota(rs.getBigDecimal("nota"));
        return avaliacao;
    };

    public List<Avaliacao> findAll() {
        return jdbcTemplate.query("SELECT * FROM avaliacao", avaliacaoRowMapper);
    }

    public Optional<Avaliacao> findById(Integer idAvaliacao) {
        String sql = "SELECT * FROM avaliacao WHERE id_avaliacao = ?";
        List<Avaliacao> results = jdbcTemplate.query(sql, new Object[]{idAvaliacao}, avaliacaoRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Avaliacao save(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (id_avaliacao, cpf_comprador, codigo_produto, id_venda, nota) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, id_venda = ?, nota = ?";

        jdbcTemplate.update(sql,
                avaliacao.getIdAvaliacao(), avaliacao.getCpfComprador(), avaliacao.getCodigoProduto(),
                avaliacao.getIdVenda(), avaliacao.getNota(),
                avaliacao.getCpfComprador(), avaliacao.getCodigoProduto(), avaliacao.getIdVenda(), avaliacao.getNota()
        );
        return avaliacao;
    }

    public void deleteById(Integer idAvaliacao) {
        jdbcTemplate.update("DELETE FROM avaliacao WHERE id_avaliacao = ?", idAvaliacao);
    }

    public List<Map<String, Object>> getAvaliacoesPorProduto() {
        String sql = "SELECT p.codigo_produto, p.descricao, AVG(a.nota) as media_avaliacao " +
                "FROM avaliacao a " +
                "JOIN produto p ON a.codigo_produto = p.codigo_produto " +
                "GROUP BY p.codigo_produto, p.descricao";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAvaliacoesPorComprador() {
        String sql = "SELECT u.nome_real, COUNT(*) as total_avaliacoes, AVG(a.nota) as media_avaliacao " +
                "FROM avaliacao a " +
                "JOIN usuario u ON a.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        return jdbcTemplate.queryForList(sql);
    }
}