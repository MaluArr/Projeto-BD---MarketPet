package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Curadoria;
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

    private RowMapper<Curadoria> curadoriaRowMapper = (rs, rowNum) -> {
        Curadoria curadoria = new Curadoria();
        curadoria.setCodigoCuradoria(rs.getInt("codigo_curadoria"));
        curadoria.setDescricao(rs.getString("descricao"));
        curadoria.setResultadoCuradoria(rs.getString("resultado_curadoria"));
        curadoria.setIdCurador(rs.getInt("id_curador"));
        curadoria.setCodigoProduto(rs.getInt("codigo_produto"));
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
        String sql = "INSERT INTO curadoria (codigo_curadoria, descricao, resultado_curadoria, id_curador, codigo_produto) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "descricao = ?, resultado_curadoria = ?, id_curador = ?, codigo_produto = ?";

        jdbcTemplate.update(sql,
                curadoria.getCodigoCuradoria(),
                curadoria.getDescricao(),
                curadoria.getResultadoCuradoria(),
                curadoria.getIdCurador(),
                curadoria.getCodigoProduto(),
                curadoria.getDescricao(),
                curadoria.getResultadoCuradoria(),
                curadoria.getIdCurador(),
                curadoria.getCodigoProduto()
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