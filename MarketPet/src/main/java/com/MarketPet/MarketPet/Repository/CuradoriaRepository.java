package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Curadoria;
import com.MarketPet.MarketPet.Model.Curador;
import com.MarketPet.MarketPet.Model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuradoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CuradorRepository curadorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private RowMapper<Curadoria> curadoriaRowMapper = (rs, rowNum) -> {
        Curadoria curadoria = new Curadoria();
        curadoria.setCodigoCuradoria(rs.getInt("codigo_curadoria"));
        curadoria.setDescricao(rs.getString("descricao"));
        curadoria.setResultadoCuradoria(rs.getString("resultado_curadoria"));

        // Busca do Curador
        Integer idCurador = rs.getInt("id_curador");
        Optional<Curador> curadorOpt = curadorRepository.findById(idCurador);
        curadorOpt.ifPresent(curadoria::setCurador);

        // Busca do Produto
        Integer codigoProduto = rs.getInt("codigo_produto");
        Optional<Produto> produtoOpt = produtoRepository.findByCodigo(codigoProduto);
        produtoOpt.ifPresent(curadoria::setProduto);

        return curadoria;
    };

    public List<Curadoria> findAll() {
        return jdbcTemplate.query("SELECT * FROM curadoria", curadoriaRowMapper);
    }

    public Optional<Curadoria> findByCodigo(Integer codigoCuradoria) {
        try {
            Curadoria curadoria = jdbcTemplate.queryForObject(
                    "SELECT * FROM curadoria WHERE codigo_curadoria = ?",
                    new Object[]{codigoCuradoria},
                    curadoriaRowMapper
            );
            return Optional.ofNullable(curadoria);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Curadoria save(Curadoria curadoria) {
        String sql = "INSERT INTO curadoria " +
                "(codigo_curadoria, descricao, resultado_curadoria, id_curador, codigo_produto) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "descricao = ?, resultado_curadoria = ?, id_curador = ?, codigo_produto = ?";

        jdbcTemplate.update(sql,
                curadoria.getCodigoCuradoria(),
                curadoria.getDescricao(),
                curadoria.getResultadoCuradoria(),
                curadoria.getCurador().getIdCurador(),
                curadoria.getProduto().getCodigoProduto(),
                // Valores para UPDATE
                curadoria.getDescricao(),
                curadoria.getResultadoCuradoria(),
                curadoria.getCurador().getIdCurador(),
                curadoria.getProduto().getCodigoProduto()
        );

        return curadoria;
    }

    public void delete(Integer codigoCuradoria) {
        jdbcTemplate.update("DELETE FROM curadoria WHERE codigo_curadoria = ?", codigoCuradoria);
    }

    public List<Curadoria> findByCurador(Integer idCurador) {
        return jdbcTemplate.query(
                "SELECT * FROM curadoria WHERE id_curador = ?",
                new Object[]{idCurador},
                curadoriaRowMapper
        );
    }

    public List<Curadoria> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM curadoria WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                curadoriaRowMapper
        );
    }
}