package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.FavoritarProduto;
import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Model.Produto;
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

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private RowMapper<FavoritarProduto> favoritarProdutoRowMapper = (rs, rowNum) -> {
        FavoritarProduto favoritarProduto = new FavoritarProduto();
        favoritarProduto.setIdLista(rs.getInt("id_lista"));

        // Busca do Comprador
        Long cpfComprador = rs.getLong("cpf_comprador");
        Optional<Comprador> compradorOpt = compradorRepository.findByCpf(cpfComprador);
        compradorOpt.ifPresent(favoritarProduto::setComprador);

        // Busca do Produto
        Integer codigoProduto = rs.getInt("codigo_produto");
        Optional<Produto> produtoOpt = produtoRepository.findByCodigo(codigoProduto);
        produtoOpt.ifPresent(favoritarProduto::setProduto);

        // Conversão de Date para LocalDate
        Date dataCriacao = rs.getDate("data_criacao");
        favoritarProduto.setDataCriacao(dataCriacao != null ? dataCriacao.toLocalDate() : null);

        favoritarProduto.setNomeLista(rs.getString("nome_lista"));

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
        String sql = "INSERT INTO favoritar_produto " +
                "(id_lista, cpf_comprador, codigo_produto, nome_lista, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, nome_lista = ?, data_criacao = ?";

        jdbcTemplate.update(sql,
                favoritarProduto.getIdLista(),
                favoritarProduto.getComprador().getCpf(),
                favoritarProduto.getProduto().getCodigoProduto(),
                favoritarProduto.getNomeLista(),
                Date.valueOf(favoritarProduto.getDataCriacao()),
                // Valores para UPDATE
                favoritarProduto.getComprador().getCpf(),
                favoritarProduto.getProduto().getCodigoProduto(),
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