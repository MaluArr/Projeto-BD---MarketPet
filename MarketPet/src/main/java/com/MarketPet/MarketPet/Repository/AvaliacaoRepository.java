package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.Avaliacao;
import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Model.Produto;
import com.MarketPet.MarketPet.Model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class AvaliacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    private RowMapper<Avaliacao> avaliacaoRowMapper = (rs, rowNum) -> {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
        avaliacao.setNota(rs.getBigDecimal("nota"));

        // Busca do Comprador
        Long cpfComprador = rs.getLong("cpf_comprador");
        Optional<Comprador> compradorOpt = compradorRepository.findByCpf(cpfComprador);
        compradorOpt.ifPresent(avaliacao::setComprador);

        // Busca do Produto
        Integer codigoProduto = rs.getInt("codigo_produto");
        Optional<Produto> produtoOpt = produtoRepository.findByCodigo(codigoProduto);
        produtoOpt.ifPresent(avaliacao::setProduto);

        // Busca da Venda
        Integer idVenda = rs.getInt("id_venda");
        Optional<Venda> vendaOpt = vendaRepository.findById(idVenda);
        vendaOpt.ifPresent(avaliacao::setVenda);

        return avaliacao;
    };

    public List<Avaliacao> findAll() {
        return jdbcTemplate.query("SELECT * FROM avaliacao", avaliacaoRowMapper);
    }

    public Optional<Avaliacao> findById(Integer idAvaliacao) {
        try {
            Avaliacao avaliacao = jdbcTemplate.queryForObject(
                    "SELECT * FROM avaliacao WHERE id_avaliacao = ?",
                    new Object[]{idAvaliacao},
                    avaliacaoRowMapper
            );
            return Optional.ofNullable(avaliacao);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Avaliacao save(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao " +
                "(id_avaliacao, cpf_comprador, codigo_produto, id_venda, nota) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, id_venda = ?, nota = ?";

        jdbcTemplate.update(sql,
                avaliacao.getIdAvaliacao(),
                avaliacao.getComprador().getCpf(),
                avaliacao.getProduto().getCodigoProduto(),
                avaliacao.getVenda().getIdVenda(),
                avaliacao.getNota(),
                // Valores para UPDATE
                avaliacao.getComprador().getCpf(),
                avaliacao.getProduto().getCodigoProduto(),
                avaliacao.getVenda().getIdVenda(),
                avaliacao.getNota()
        );

        return avaliacao;
    }

    public void delete(Integer idAvaliacao) {
        jdbcTemplate.update("DELETE FROM avaliacao WHERE id_avaliacao = ?", idAvaliacao);
    }

    public List<Avaliacao> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM avaliacao WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                avaliacaoRowMapper
        );
    }

    public List<Avaliacao> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM avaliacao WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                avaliacaoRowMapper
        );
    }

    public Optional<Avaliacao> findByVenda(Integer idVenda) {
        try {
            Avaliacao avaliacao = jdbcTemplate.queryForObject(
                    "SELECT * FROM avaliacao WHERE id_venda = ?",
                    new Object[]{idVenda},
                    avaliacaoRowMapper
            );
            return Optional.ofNullable(avaliacao);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Avaliacao> findByNotaEntre(BigDecimal notaMinima, BigDecimal notaMaxima) {
        return jdbcTemplate.query(
                "SELECT * FROM avaliacao WHERE nota BETWEEN ? AND ?",
                new Object[]{notaMinima, notaMaxima},
                avaliacaoRowMapper
        );
    }
}