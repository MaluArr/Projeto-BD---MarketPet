package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.FavoritarProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class FavoritarProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FavoritarProduto> favoritarProdutoRowMapper = (rs, rowNum) -> {
        FavoritarProduto favoritarProduto = new FavoritarProduto();
        favoritarProduto.setIdLista(rs.getInt("id_lista"));
        favoritarProduto.setCpfComprador(rs.getLong("cpf_comprador"));
        favoritarProduto.setCodigoProduto(rs.getInt("codigo_produto"));
        favoritarProduto.setNomeLista(rs.getString("nome_lista"));
        Date dataCriacao = rs.getDate("data_criacao");
        favoritarProduto.setDataCriacao(dataCriacao != null ? dataCriacao.toLocalDate() : null);
        return favoritarProduto;
    };

    public List<FavoritarProduto> findAll() {
        return jdbcTemplate.query("SELECT * FROM favoritar_produto", favoritarProdutoRowMapper);
    }

    public Optional<FavoritarProduto> findById(Integer idLista) {
        try {
            FavoritarProduto favoritarProduto = jdbcTemplate.queryForObject(
                    "SELECT * FROM favoritar_produto WHERE id_lista = ?",
                    new Object[]{idLista},
                    favoritarProdutoRowMapper
            );
            return Optional.ofNullable(favoritarProduto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public FavoritarProduto save(FavoritarProduto favoritarProduto) {
        String sql = "INSERT INTO favoritar_produto (id_lista, cpf_comprador, codigo_produto, nome_lista, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE cpf_comprador = ?, codigo_produto = ?, nome_lista = ?, data_criacao = ?";

        jdbcTemplate.update(sql,
                favoritarProduto.getIdLista(),
                favoritarProduto.getCpfComprador(),
                favoritarProduto.getCodigoProduto(),
                favoritarProduto.getNomeLista(),
                Date.valueOf(favoritarProduto.getDataCriacao()),
                favoritarProduto.getCpfComprador(),
                favoritarProduto.getCodigoProduto(),
                favoritarProduto.getNomeLista(),
                Date.valueOf(favoritarProduto.getDataCriacao())
        );

        return favoritarProduto;
    }

    public void delete(Integer idLista) {
        jdbcTemplate.update("DELETE FROM favoritar_produto WHERE id_lista = ?", idLista);
    }

    public List<FavoritarProduto> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM favoritar_produto WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                favoritarProdutoRowMapper
        );
    }

    public List<FavoritarProduto> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM favoritar_produto WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                favoritarProdutoRowMapper
        );
    }

    public List<FavoritarProduto> findByNomeLista(String nomeLista) {
        return jdbcTemplate.query(
                "SELECT * FROM favoritar_produto WHERE nome_lista = ?",
                new Object[]{nomeLista},
                favoritarProdutoRowMapper
        );
    }
}