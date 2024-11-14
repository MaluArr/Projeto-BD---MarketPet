package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AdicionarProdutoCarrinhoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdicionarProdutoCarrinho salvarProdutoCarrinho(AdicionarProdutoCarrinho produtoCarrinho) {
        String sql = "INSERT INTO adicionar_produto_carrinho " +
                "(cpf_comprador, codigo_produto, id_carrinho, quantidade) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "quantidade = ?";
        jdbcTemplate.update(sql,
                produtoCarrinho.getComprador().getCpf(),
                produtoCarrinho.getProduto().getCodigoProduto(),
                produtoCarrinho.getCarrinho().getIdCarrinho(),
                produtoCarrinho.getQuantidade(),
                produtoCarrinho.getQuantidade()
        );
        return produtoCarrinho;
    }

    public List<AdicionarProdutoCarrinho> buscarTodosProdutosCarrinho() {
        String sql = "SELECT * FROM adicionar_produto_carrinho";
        return jdbcTemplate.query(sql, this::mapRowToProdutoCarrinho);
    }

    public Optional<AdicionarProdutoCarrinho> buscarProdutoCarrinhoPorId(Integer id) {
        String sql = "SELECT * FROM adicionar_produto_carrinho WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, this::mapRowToProdutoCarrinho)
                .stream().findFirst();
    }

    public void deletarProdutoCarrinho(Integer id) {
        String sql = "DELETE FROM adicionar_produto_carrinho WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private AdicionarProdutoCarrinho mapRowToProdutoCarrinho(ResultSet rs, int rowNum) throws SQLException {
        AdicionarProdutoCarrinho produtoCarrinho = new AdicionarProdutoCarrinho();
        produtoCarrinho.setId(rs.getInt("id"));
        // Preencha os demais campos de acordo com o banco de dados
        return produtoCarrinho;
    }
}