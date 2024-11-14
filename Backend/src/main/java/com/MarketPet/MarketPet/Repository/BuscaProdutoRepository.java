package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.BuscaProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BuscaProdutoRepository {

    private static final Logger logger = LoggerFactory.getLogger(BuscaProdutoRepository.class);

    public List<BuscaProduto> findAll() {
        List<BuscaProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM busca_produto";
        logger.info("Executando consulta para buscar todos os produtos");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BuscaProduto produto = mapRowToBuscaProduto(rs);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os produtos", e);
        }

        return produtos;
    }

    public Optional<BuscaProduto> findById(Integer idBusca) {
        String sql = "SELECT * FROM busca_produto WHERE id_busca = ?";
        logger.info("Executando consulta para buscar produto com ID: {}", idBusca);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBusca);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BuscaProduto produto = mapRowToBuscaProduto(rs);
                    return Optional.of(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produto com ID: {}", idBusca, e);
        }

        return Optional.empty();
    }

    public BuscaProduto save(BuscaProduto buscaProduto) {
        String sql = "INSERT INTO busca_produto (id_busca, cpf_comprador, codigo_produto, preco, regiao, categoria, cor, tamanho, avaliacao, descricao_busca) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, preco = ?, regiao = ?, categoria = ?, cor = ?, tamanho = ?, avaliacao = ?, descricao_busca = ?";
        logger.info("Salvando produto com ID de busca: {}", buscaProduto.getIdBusca());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, buscaProduto.getIdBusca());
            ps.setLong(2, buscaProduto.getCpfComprador());
            ps.setInt(3, buscaProduto.getCodigoProduto());
            ps.setBigDecimal(4, buscaProduto.getPreco());
            ps.setString(5, buscaProduto.getRegiao());
            ps.setString(6, buscaProduto.getCategoria());
            ps.setString(7, buscaProduto.getCor());
            ps.setString(8, buscaProduto.getTamanho());
            ps.setBigDecimal(9, buscaProduto.getAvaliacao());
            ps.setString(10, buscaProduto.getDescricaoBusca());

            ps.setLong(11, buscaProduto.getCpfComprador());
            ps.setInt(12, buscaProduto.getCodigoProduto());
            ps.setBigDecimal(13, buscaProduto.getPreco());
            ps.setString(14, buscaProduto.getRegiao());
            ps.setString(15, buscaProduto.getCategoria());
            ps.setString(16, buscaProduto.getCor());
            ps.setString(17, buscaProduto.getTamanho());
            ps.setBigDecimal(18, buscaProduto.getAvaliacao());
            ps.setString(19, buscaProduto.getDescricaoBusca());

            ps.executeUpdate();
            logger.info("Produto salvo com sucesso com ID de busca: {}", buscaProduto.getIdBusca());

        } catch (SQLException e) {
            logger.error("Erro ao salvar produto com ID de busca: {}", buscaProduto.getIdBusca(), e);
        }

        return buscaProduto;
    }

    public void deleteById(Integer idBusca) {
        String sql = "DELETE FROM busca_produto WHERE id_busca = ?";
        logger.info("Deletando produto com ID de busca: {}", idBusca);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBusca);
            ps.executeUpdate();
            logger.info("Produto deletado com sucesso com ID de busca: {}", idBusca);

        } catch (SQLException e) {
            logger.error("Erro ao deletar produto com ID de busca: {}", idBusca, e);
        }
    }

    public List<Map<String, Object>> getBuscasPorComprador() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT u.nome_real, COUNT(*) as total_buscas " +
                "FROM busca_produto bp " +
                "JOIN usuario u ON bp.cpf_comprador = u.CPF " +
                "GROUP BY u.CPF, u.nome_real";
        logger.info("Executando consulta para buscar total de buscas por comprador");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "nome_real", rs.getString("nome_real"),
                        "total_buscas", rs.getInt("total_buscas")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar total de buscas por comprador", e);
        }

        return resultados;
    }

    public List<Map<String, Object>> getBuscasPorCategoria() {
        List<Map<String, Object>> resultados = new ArrayList<>();
        String sql = "SELECT categoria, COUNT(*) as total_buscas FROM busca_produto GROUP BY categoria";
        logger.info("Executando consulta para buscar total de buscas por categoria");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultados.add(Map.of(
                        "categoria", rs.getString("categoria"),
                        "total_buscas", rs.getInt("total_buscas")
                ));
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar total de buscas por categoria", e);
        }

        return resultados;
    }

    private BuscaProduto mapRowToBuscaProduto(ResultSet rs) throws SQLException {
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
    }
}