package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoRepository.class);

    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        logger.info("Executando consulta para buscar todos os produtos");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto produto = mapRowToProduto(rs);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os produtos", e);
        }

        return produtos;
    }

    public Optional<Produto> findByCodigo(Integer codigoProduto) {
        String sql = "SELECT * FROM produto WHERE codigo_produto = ?";
        logger.info("Executando consulta para buscar produto com código: {}", codigoProduto);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto produto = mapRowToProduto(rs);
                    return Optional.of(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produto com código: {}", codigoProduto, e);
        }

        return Optional.empty();
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
        logger.info("Salvando produto com código: {}", produto.getCodigoProduto());

        // Preparar fotos
        List<String> fotos = produto.getFotos();
        String foto1 = fotos.size() > 0 ? fotos.get(0) : null;
        String foto2 = fotos.size() > 1 ? fotos.get(1) : null;
        String foto3 = fotos.size() > 2 ? fotos.get(2) : null;
        String foto4 = fotos.size() > 3 ? fotos.get(3) : null;
        String foto5 = fotos.size() > 4 ? fotos.get(4) : null;
        String foto6 = fotos.size() > 5 ? fotos.get(5) : null;

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, produto.getCodigoProduto());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getCategoria());
            ps.setBigDecimal(4, produto.getPreco());
            ps.setString(5, produto.getDimensoes());
            ps.setString(6, foto1);
            ps.setString(7, foto2);
            ps.setString(8, foto3);
            ps.setString(9, foto4);
            ps.setString(10, foto5);
            ps.setString(11, foto6);
            ps.setBigDecimal(12, produto.getDesconto());
            ps.setBigDecimal(13, produto.getComissaoMkp());
            ps.setBigDecimal(14, produto.getNota());

            // Valores para UPDATE
            ps.setString(15, produto.getDescricao());
            ps.setString(16, produto.getCategoria());
            ps.setBigDecimal(17, produto.getPreco());
            ps.setString(18, produto.getDimensoes());
            ps.setString(19, foto1);
            ps.setString(20, foto2);
            ps.setString(21, foto3);
            ps.setString(22, foto4);
            ps.setString(23, foto5);
            ps.setString(24, foto6);
            ps.setBigDecimal(25, produto.getDesconto());
            ps.setBigDecimal(26, produto.getComissaoMkp());
            ps.setBigDecimal(27, produto.getNota());

            ps.executeUpdate();
            logger.info("Produto salvo com sucesso: {}", produto.getCodigoProduto());

        } catch (SQLException e) {
            logger.error("Erro ao salvar produto com código: {}", produto.getCodigoProduto(), e);
        }

        return produto;
    }

    public void delete(Integer codigoProduto) {
        String sql = "DELETE FROM produto WHERE codigo_produto = ?";
        logger.info("Deletando produto com código: {}", codigoProduto);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoProduto);
            ps.executeUpdate();
            logger.info("Produto deletado com sucesso: {}", codigoProduto);

        } catch (SQLException e) {
            logger.error("Erro ao deletar produto com código: {}", codigoProduto, e);
        }
    }

    public List<Produto> findByCategoria(String categoria) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE categoria = ?";
        logger.info("Executando consulta para buscar produtos por categoria: {}", categoria);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = mapRowToProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos por categoria: {}", categoria, e);
        }

        return produtos;
    }

    public List<Produto> findByPrecoMenorQue(BigDecimal preco) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE preco < ?";
        logger.info("Executando consulta para buscar produtos com preço menor que: {}", preco);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, preco);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = mapRowToProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos com preço menor que: {}", preco, e);
        }

        return produtos;
    }

    public List<Produto> findByPrecoMaiorQue(BigDecimal preco) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE preco > ?";
        logger.info("Executando consulta para buscar produtos com preço maior que: {}", preco);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, preco);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = mapRowToProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos com preço maior que: {}", preco, e);
        }

        return produtos;
    }

    public List<Produto> findByNotaMaiorQue(BigDecimal nota) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE nota > ?";
        logger.info("Executando consulta para buscar produtos por nota maior que: {}", nota);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, nota);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = mapRowToProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos por nota maior que: {}", nota, e);
        }

        return produtos;
    }

    private Produto mapRowToProduto(ResultSet rs) throws SQLException {
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
    }
}