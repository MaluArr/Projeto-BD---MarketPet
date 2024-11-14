package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AdicionarProdutoCarrinhoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AdicionarProdutoCarrinhoRepository.class);

    public AdicionarProdutoCarrinho salvarProdutoCarrinho(AdicionarProdutoCarrinho produtoCarrinho) {
        String sql = "INSERT INTO adicionar_produto_carrinho (cpf_comprador, codigo_produto, id_carrinho, quantidade) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE quantidade = ?";
        logger.info("Salvando produto no carrinho: {}", produtoCarrinho.getId());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, produtoCarrinho.getComprador().getCpf());
            ps.setInt(2, produtoCarrinho.getProduto().getCodigoProduto());
            ps.setInt(3, produtoCarrinho.getCarrinho().getIdCarrinho());
            ps.setInt(4, produtoCarrinho.getQuantidade());
            ps.setInt(5, produtoCarrinho.getQuantidade());

            ps.executeUpdate();
            logger.info("Produto salvo com sucesso no carrinho: {}", produtoCarrinho.getId());

        } catch (SQLException e) {
            logger.error("Erro ao salvar produto no carrinho: {}", produtoCarrinho.getId(), e);
        }

        return produtoCarrinho;
    }

    public List<AdicionarProdutoCarrinho> buscarTodosProdutosCarrinho() {
        List<AdicionarProdutoCarrinho> produtosCarrinho = new ArrayList<>();
        String sql = "SELECT * FROM adicionar_produto_carrinho";
        logger.info("Buscando todos os produtos no carrinho");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AdicionarProdutoCarrinho produtoCarrinho = mapRowToProdutoCarrinho(rs);
                produtosCarrinho.add(produtoCarrinho);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os produtos no carrinho", e);
        }

        return produtosCarrinho;
    }

    public Optional<AdicionarProdutoCarrinho> buscarProdutoCarrinhoPorId(Integer id) {
        String sql = "SELECT * FROM adicionar_produto_carrinho WHERE id = ?";
        logger.info("Buscando produto no carrinho com ID: {}", id);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AdicionarProdutoCarrinho produtoCarrinho = mapRowToProdutoCarrinho(rs);
                    return Optional.of(produtoCarrinho);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produto no carrinho com ID: {}", id, e);
        }

        return Optional.empty();
    }

    public void deletarProdutoCarrinho(Integer id) {
        String sql = "DELETE FROM adicionar_produto_carrinho WHERE id = ?";
        logger.info("Deletando produto do carrinho com ID: {}", id);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Produto deletado com sucesso do carrinho com ID: {}", id);

        } catch (SQLException e) {
            logger.error("Erro ao deletar produto do carrinho com ID: {}", id, e);
        }
    }

    private AdicionarProdutoCarrinho mapRowToProdutoCarrinho(ResultSet rs) throws SQLException {
        AdicionarProdutoCarrinho produtoCarrinho = new AdicionarProdutoCarrinho();
        produtoCarrinho.setId(rs.getInt("id"));
        // Preencha os demais campos de acordo com o banco de dados
        return produtoCarrinho;
    }
}