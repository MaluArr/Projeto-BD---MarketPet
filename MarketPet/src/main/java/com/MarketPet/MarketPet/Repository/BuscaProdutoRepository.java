package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BuscaProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private RowMapper<BuscaProduto> buscaProdutoRowMapper = (rs, rowNum) -> {
        BuscaProduto buscaProduto = new BuscaProduto();
        buscaProduto.setIdBusca(rs.getInt("id_busca"));

        // Busca do Comprador
        Long cpfComprador = rs.getLong("cpf_comprador");
        Optional<Comprador> compradorOpt = compradorRepository.findByCpf(cpfComprador);
        compradorOpt.ifPresent(buscaProduto::setComprador);

        // Busca do Produto
        Integer codigoProduto = rs.getInt("codigo_produto");
        Optional<Produto> produtoOpt = produtoRepository.findByCodigo(codigoProduto);
        produtoOpt.ifPresent(buscaProduto::setProduto);

        buscaProduto.setPreco(rs.getFloat("preco"));
        buscaProduto.setRegiao(rs.getString("regiao"));
        buscaProduto.setCategoria(rs.getString("categoria"));
        buscaProduto.setCor(rs.getString("cor"));
        buscaProduto.setTamanho(rs.getString("tamanho"));
        buscaProduto.setAvaliacao(rs.getFloat("avaliacao"));
        buscaProduto.setDescricaoBusca(rs.getString("descricao_busca"));

        return buscaProduto;
    };

    public List<BuscaProduto> findAll() {
        return jdbcTemplate.query("SELECT * FROM busca_produto", buscaProdutoRowMapper);
    }

    public Optional<BuscaProduto> findById(Integer idBusca) {
        try {
            BuscaProduto buscaProduto = jdbcTemplate.queryForObject(
                    "SELECT * FROM busca_produto WHERE id_busca = ?",
                    new Object[]{idBusca},
                    buscaProdutoRowMapper
            );
            return Optional.ofNullable(buscaProduto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public BuscaProduto save(BuscaProduto buscaProduto) {
        String sql = "INSERT INTO busca_produto " +
                "(id_busca, cpf_comprador, codigo_produto, preco, regiao, " +
                "categoria, cor, tamanho, avaliacao, descricao_busca) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, preco = ?, regiao = ?, " +
                "categoria = ?, cor = ?, tamanho = ?, avaliacao = ?, descricao_busca = ?";

        jdbcTemplate.update(sql,
                buscaProduto.getIdBusca(),
                buscaProduto.getComprador().getCpf(),
                buscaProduto.getProduto().getCodigoProduto(),
                buscaProduto.getPreco(),
                buscaProduto.getRegiao(),
                buscaProduto.getCategoria(),
                buscaProduto.getCor(),
                buscaProduto.getTamanho(),
                buscaProduto.getAvaliacao(),
                buscaProduto.getDescricaoBusca(),
                // Valores para UPDATE
                buscaProduto.getComprador().getCpf(),
                buscaProduto.getProduto().getCodigoProduto(),
                buscaProduto.getPreco(),
                buscaProduto.getRegiao(),
                buscaProduto.getCategoria(),
                buscaProduto.getCor(),
                buscaProduto.getTamanho(),
                buscaProduto.getAvaliacao(),
                buscaProduto.getDescricaoBusca()
        );

        return buscaProduto;
    }

    public void delete(Integer idBusca) {
        jdbcTemplate.update("DELETE FROM busca_produto WHERE id_busca = ?", idBusca);
    }

    public List<BuscaProduto> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM busca_produto WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                buscaProdutoRowMapper
        );
    }

    public List<BuscaProduto> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM busca_produto WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                buscaProdutoRowMapper
        );
    }

    public List<BuscaProduto> findByCategoria(String categoria) {
        return jdbcTemplate.query(
                "SELECT * FROM busca_produto WHERE categoria = ?",
                new Object[]{categoria},
                buscaProdutoRowMapper
        );
    }

    public List<BuscaProduto> findByPrecoMenorQue(Float preco) {
        return jdbcTemplate.query(
                "SELECT * FROM busca_produto WHERE preco < ?",
                new Object[]{preco},
                buscaProdutoRowMapper
        );
    }

    public List<BuscaProduto> findByRegiao(String regiao) {
        return jdbcTemplate.query(
                "SELECT * FROM busca_produto WHERE regiao = ?",
                new Object[]{regiao},
                buscaProdutoRowMapper
        );
    }
}