package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Carrinho;
import com.MarketPet.MarketPet.Model.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class CarrinhoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CompradorRepository compradorRepository;

    private RowMapper<Carrinho> carrinhoRowMapper = (rs, rowNum) -> {
        Carrinho carrinho = new Carrinho();
        carrinho.setIdCarrinho(rs.getInt("id_carrinho"));
        carrinho.setValorTotal(rs.getBigDecimal("valor_total"));

        // Busca do Comprador
        Long cpfComprador = rs.getLong("cpf_comprador");
        Optional<Comprador> compradorOpt = compradorRepository.findByCpf(cpfComprador);
        compradorOpt.ifPresent(carrinho::setComprador);

        return carrinho;
    };

    public List<Carrinho> findAll() {
        return jdbcTemplate.query("SELECT * FROM carrinho_de_compras", carrinhoRowMapper);
    }

    public Optional<Carrinho> findById(Integer idCarrinho) {
        try {
            Carrinho carrinho = jdbcTemplate.queryForObject(
                    "SELECT * FROM carrinho_de_compras WHERE id_carrinho = ?",
                    new Object[]{idCarrinho},
                    carrinhoRowMapper
            );
            return Optional.ofNullable(carrinho);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Carrinho save(Carrinho carrinho) {
        String sql = "INSERT INTO carrinho_de_compras " +
                "(id_carrinho, valor_total, cpf_comprador) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "valor_total = ?, cpf_comprador = ?";

        jdbcTemplate.update(sql,
                carrinho.getIdCarrinho(),
                carrinho.getValorTotal(),
                carrinho.getComprador().getCpf(),
                // Valores para UPDATE
                carrinho.getValorTotal(),
                carrinho.getComprador().getCpf()
        );

        return carrinho;
    }

    public void delete(Integer idCarrinho) {
        jdbcTemplate.update("DELETE FROM carrinho_de_compras WHERE id_carrinho = ?", idCarrinho);
    }

    public Optional<Carrinho> findByComprador(Long cpfComprador) {
        try {
            Carrinho carrinho = jdbcTemplate.queryForObject(
                    "SELECT * FROM carrinho_de_compras WHERE cpf_comprador = ?",
                    new Object[]{cpfComprador},
                    carrinhoRowMapper
            );
            return Optional.ofNullable(carrinho);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Carrinho> findByValorTotalMaiorQue(BigDecimal valorMinimo) {
        return jdbcTemplate.query(
                "SELECT * FROM carrinho_de_compras WHERE valor_total > ?",
                new Object[]{valorMinimo},
                carrinhoRowMapper
        );
    }
}