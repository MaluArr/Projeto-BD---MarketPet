package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Config.JDBC_Connection;
import com.MarketPet.MarketPet.Model.FavoritarProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FavoritarProdutoRepository {

    private static final Logger logger = LoggerFactory.getLogger(FavoritarProdutoRepository.class);

    public List<FavoritarProduto> findAll() {
        List<FavoritarProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM favoritar_produto";
        logger.info("Executando consulta para buscar todos os produtos favoritos");

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FavoritarProduto produto = mapRowToFavoritarProduto(rs);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar todos os produtos favoritos", e);
        }

        return produtos;
    }

    public Optional<FavoritarProduto> findById(Integer idLista) {
        String sql = "SELECT * FROM favoritar_produto WHERE id_lista = ?";
        logger.info("Executando consulta para buscar produto favorito com ID: {}", idLista);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLista);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FavoritarProduto produto = mapRowToFavoritarProduto(rs);
                    return Optional.of(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produto favorito com ID: {}", idLista, e);
        }

        return Optional.empty();
    }

    public FavoritarProduto save(FavoritarProduto favoritarProduto) {
        String sql = "INSERT INTO favoritar_produto (id_lista, cpf_comprador, codigo_produto, nome_lista, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_comprador = ?, codigo_produto = ?, nome_lista = ?, data_criacao = ?";
        logger.info("Salvando produto favorito com ID de lista: {}", favoritarProduto.getIdLista());

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, favoritarProduto.getIdLista());
            ps.setLong(2, favoritarProduto.getCpfComprador());
            ps.setInt(3, favoritarProduto.getCodigoProduto());
            ps.setString(4, favoritarProduto.getNomeLista());
            ps.setDate(5, Date.valueOf(favoritarProduto.getDataCriacao()));

            ps.setLong(6, favoritarProduto.getCpfComprador());
            ps.setInt(7, favoritarProduto.getCodigoProduto());
            ps.setString(8, favoritarProduto.getNomeLista());
            ps.setDate(9, Date.valueOf(favoritarProduto.getDataCriacao()));

            ps.executeUpdate();
            logger.info("Produto favorito salvo com sucesso: {}", favoritarProduto.getIdLista());

        } catch (SQLException e) {
            logger.error("Erro ao salvar produto favorito com ID de lista: {}", favoritarProduto.getIdLista(), e);
        }

        return favoritarProduto;
    }

    public void delete(Integer idLista) {
        String sql = "DELETE FROM favoritar_produto WHERE id_lista = ?";
        logger.info("Deletando produto favorito com ID de lista: {}", idLista);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLista);
            ps.executeUpdate();
            logger.info("Produto favorito deletado com sucesso: {}", idLista);

        } catch (SQLException e) {
            logger.error("Erro ao deletar produto favorito com ID de lista: {}", idLista, e);
        }
    }

    public List<FavoritarProduto> findByComprador(Long cpfComprador) {
        List<FavoritarProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM favoritar_produto WHERE cpf_comprador = ?";
        logger.info("Executando consulta para buscar produtos favoritos por comprador com CPF: {}", cpfComprador);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, cpfComprador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FavoritarProduto produto = mapRowToFavoritarProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos favoritos por comprador com CPF: {}", cpfComprador, e);
        }

        return produtos;
    }

    public List<FavoritarProduto> findByProduto(Integer codigoProduto) {
        List<FavoritarProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM favoritar_produto WHERE codigo_produto = ?";
        logger.info("Executando consulta para buscar produtos favoritos por código de produto: {}", codigoProduto);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FavoritarProduto produto = mapRowToFavoritarProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos favoritos por código de produto: {}", codigoProduto, e);
        }

        return produtos;
    }

    public List<FavoritarProduto> findByNomeLista(String nomeLista) {
        List<FavoritarProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM favoritar_produto WHERE nome_lista = ?";
        logger.info("Executando consulta para buscar produtos favoritos por nome de lista: {}", nomeLista);

        try (Connection con = JDBC_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomeLista);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FavoritarProduto produto = mapRowToFavoritarProduto(rs);
                    produtos.add(produto);
                }
            }

        } catch (SQLException e) {
            logger.error("Erro ao buscar produtos favoritos por nome de lista: {}", nomeLista, e);
        }

        return produtos;
    }

    private FavoritarProduto mapRowToFavoritarProduto(ResultSet rs) throws SQLException {
        FavoritarProduto favoritarProduto = new FavoritarProduto();
        favoritarProduto.setIdLista(rs.getInt("id_lista"));
        favoritarProduto.setCpfComprador(rs.getLong("cpf_comprador"));
        favoritarProduto.setCodigoProduto(rs.getInt("codigo_produto"));
        favoritarProduto.setNomeLista(rs.getString("nome_lista"));
        Date dataCriacao = rs.getDate("data_criacao");
        favoritarProduto.setDataCriacao(dataCriacao != null ? dataCriacao.toLocalDate() : null);
        return favoritarProduto;
    }
}