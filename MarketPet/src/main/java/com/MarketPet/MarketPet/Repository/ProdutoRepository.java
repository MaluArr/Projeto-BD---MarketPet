package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Produto> produtoRowMapper = (rs, rowNum) -> {
        Produto produto = new Produto();
        produto.setCodigoProduto(rs.getInt("codigo_produto"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setCategoria(rs.getString("categoria"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.setDimensoes(rs.getString("dimensoes"));

        // Adicionar fotos
        List<String> fotos = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            String fotoColuna = "foto" + i;
            String foto = rs.getString(fotoColuna);
            if (foto != null && !foto.isEmpty()) {
                fotos.add(foto);
            }
        }
        produto.setFotos(fotos);

        produto.setDesconto(rs.getBigDecimal("desconto"));
        produto.setComissaoMkp(rs.getBigDecimal("comissao_mkp"));
        produto.setNota(rs.getBigDecimal("nota"));

        return produto;
    };

    public List<Produto> findAll() {
        return jdbcTemplate.query("SELECT * FROM produto", produtoRowMapper);
    }

    public Optional<Produto> findByCodigo(Integer codigoProduto) {
        try {
            Produto produto = jdbcTemplate.queryForObject(
                    "SELECT * FROM produto WHERE codigo_produto = ?",
                    new Object[]{codigoProduto},
                    produtoRowMapper
            );
            return Optional.ofNullable(produto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Produto save(Produto produto) {
        String sql = "INSERT INTO produto " +
                "(codigo_produto, descricao, categoria, preco, dimensoes, " +
                "foto1, foto2, foto3, foto4, foto5, foto6, " +
                "desconto, comissao_mkp, nota) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "descricao = ?, categoria = ?, preco = ?, dimensoes = ?, " +
                "foto1 = ?, foto2 = ?, foto3 = ?, foto4 = ?, foto5 = ?, foto6 = ?, " +
                "desconto = ?, comissao_mkp = ?, nota = ?";

        // Preparar fotos
        List<String> fotos = produto.getFotos();
        String foto1 = fotos.size() > 0 ? fotos.get(0) : null;
        String foto2 = fotos.size() > 1 ? fotos.get(1) : null;
        String foto3 = fotos.size() > 2 ? fotos.get(2) : null;
        String foto4 = fotos.size() > 3 ? fotos.get(3) : null;
        String foto5 = fotos.size() > 4 ? fotos.get(4) : null;
        String foto6 = fotos.size() > 5 ? fotos.get(5) : null;

        jdbcTemplate.update(sql,
                produto.getCodigoProduto(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDimensoes(),
                foto1, foto2, foto3, foto4, foto5, foto6,
                produto.getDesconto(),
                produto.getComissaoMkp(),
                produto.getNota(),
                // Valores para UPDATE
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDimensoes(),
                foto1, foto2, foto3, foto4, foto5, foto6,
                produto.getDesconto(),
                produto.getComissaoMkp(),
                produto.getNota()
        );

        return produto;
    }

    public void delete(Integer codigoProduto) {
        jdbcTemplate.update("DELETE FROM produto WHERE codigo_produto = ?", codigoProduto);
    }

    public List<Produto> findByCategoria(String categoria) {
        return jdbcTemplate.query(
                "SELECT * FROM produto WHERE categoria = ?",
                new Object[]{categoria},
                produtoRowMapper
        );
    }

    public List<Produto> findByPrecoMenorQue(BigDecimal preco) {
        return jdbcTemplate.query(
                "SELECT * FROM produto WHERE preco < ?",
                new Object[]{preco},
                produtoRowMapper
        );
    }

    public List<Produto> findByPrecoMaiorQue(BigDecimal preco) {
        return jdbcTemplate.query(
                "SELECT * FROM produto WHERE preco > ?",
                new Object[]{preco},
                produtoRowMapper
        );
    }

    public List<Produto> findByNotaMaiorQue(BigDecimal nota) {
        return jdbcTemplate.query(
                "SELECT * FROM produto WHERE nota > ?",
                new Object[]{nota},
                produtoRowMapper
        );
    }
}