package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Repository
public class BuscaProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<BuscaProduto> buscaProdutoRowMapper = (rs, rowNum) -> {
        BuscaProduto buscaProduto = new BuscaProduto();
        buscaProduto.setIdBusca(rs.getInt("id_busca"));
        buscaProduto.setCpfComprador(rs.getLong("cpf_comprador"));
        buscaProduto.setCodigoProduto(rs.getInt("codigo_produto"));
        buscaProduto.setPreco(rs.getBigDecimal("preco"));
        buscaProduto.setRegiao(rs.getString("regiao"));
        buscaProduto.setCategoria(rs.getString("categoria"));
        buscaProduto.setCor(rs.getString("cor"));
        buscaProduto.setTamanho(rs.getString("tamanho"));
        buscaProduto.setAvaliacao(rs.getBigDecimal("avaliacao"));
        buscaProduto.setDescricaoBusca(rs.getString("descricao_busca"));
        return buscaProduto;
    };

    public List<BuscaProduto> findAll() {
        return jdbcTemplate.query("SELECT * FROM busca_produto", buscaProdutoRowMapper);
    }

    public Optional<BuscaProduto> findById(Integer idBusca) {
        String sql = "SELECT * FROM busca_produto WHERE id_busca = ?";
        List<BuscaProduto> results = jdbcTemplate.query(sql, new Object[]{idBusca}, buscaProdutoRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public BuscaProduto save(BuscaProduto buscaProduto) {
        String sql = "INSERT INTO busca_produto (id_busca, cpf_comprador, codigo_produto, preco, regiao, categoria, cor, tamanho, avaliacao, descricao_busca) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, preco = ?, regiao = ?, categoria = ?, cor = ?, tamanho = ?, avaliacao = ?, descricao_busca = ?";

        jdbcTemplate.update(sql,
                buscaProduto.getIdBusca(), buscaProduto.getCpfComprador(), buscaProduto.getCodigoProduto(),
                buscaProduto.getPreco(), buscaProduto.getRegiao(), buscaProduto.getCategoria(),
                buscaProduto.getCor(), buscaProduto.getTamanho(), buscaProduto.getAvaliacao(),
                buscaProduto.getDescricaoBusca(),
                buscaProduto.getCpfComprador(), buscaProduto.getCodigoProduto(), buscaProduto.getPreco(),
                buscaProduto.getRegiao(), buscaProduto.getCategoria(), buscaProduto.getCor(),
                buscaProduto.getTamanho(), buscaProduto.getAvaliacao(), buscaProduto.getDescricaoBusca()
        );
        return buscaProduto;
    }

    public void deleteById(Integer idBusca) {
        jdbcTemplate.update("DELETE FROM busca_produto WHERE id_busca = ?", idBusca);
    }

    public List<Map<String, Object>> getBuscasPorComprador() {
        String sql = "SELECT u.nome_real, COUNT(*) as total_buscas " +
                "FROM busca_produto bp " +
                "JOIN usuario u ON bp.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getBuscasPorCategoria() {
        String sql = "SELECT categoria, COUNT(*) as total_buscas " +
                "FROM busca_produto " +
                "GROUP BY categoria";
        return jdbcTemplate.queryForList(sql);
    }
}