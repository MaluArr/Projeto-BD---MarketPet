package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Repository
public class CarrinhoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Carrinho> carrinhoRowMapper = (rs, rowNum) -> {
        Carrinho carrinho = new Carrinho();
        carrinho.setIdCarrinho(rs.getInt("id_carrinho"));
        carrinho.setValorTotal(rs.getBigDecimal("valor_total"));
        carrinho.setCpfComprador(rs.getLong("cpf_comprador"));
        return carrinho;
    };

    public List<Carrinho> findAll() {
        return jdbcTemplate.query("SELECT * FROM carrinho_de_compras", carrinhoRowMapper);
    }

    public Optional<Carrinho> findById(Integer idCarrinho) {
        String sql = "SELECT * FROM carrinho_de_compras WHERE id_carrinho = ?";
        List<Carrinho> results = jdbcTemplate.query(sql, new Object[]{idCarrinho}, carrinhoRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Carrinho save(Carrinho carrinho) {
        String sql = "INSERT INTO carrinho_de_compras (id_carrinho, valor_total, cpf_comprador) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "valor_total = ?, cpf_comprador = ?";

        jdbcTemplate.update(sql,
                carrinho.getIdCarrinho(), carrinho.getValorTotal(), carrinho.getCpfComprador(),
                carrinho.getValorTotal(), carrinho.getCpfComprador()
        );
        return carrinho;
    }

    public void deleteById(Integer idCarrinho) {
        jdbcTemplate.update("DELETE FROM carrinho_de_compras WHERE id_carrinho = ?", idCarrinho);
    }

    public List<Map<String, Object>> getCarrinhosPorComprador() {
        String sql = "SELECT u.nome_real, COUNT(*) as total_carrinhos, SUM(c.valor_total) as valor_total " +
                "FROM carrinho_de_compras c " +
                "JOIN usuario u ON c.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getCarrinhosPorValor() {
        String sql = "SELECT CASE " +
                "    WHEN valor_total < 100 THEN 'Até R$100' " +
                "    WHEN valor_total >= 100 AND valor_total < 500 THEN 'R$100 a R$500' " +
                "    ELSE 'Acima de R$500' " +
                "END AS faixa_valor, " +
                "COUNT(*) as total_carrinhos " +
                "FROM carrinho_de_compras " +
                "GROUP BY faixa_valor";
        return jdbcTemplate.queryForList(sql);
    }
}