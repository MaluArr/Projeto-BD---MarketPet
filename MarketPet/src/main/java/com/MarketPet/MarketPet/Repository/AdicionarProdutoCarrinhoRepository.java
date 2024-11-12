package com.MarketPet.MarketPet.Repository;

import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import com.MarketPet.MarketPet.Model.Comprador;
import com.MarketPet.MarketPet.Model.Produto;
import com.MarketPet.MarketPet.Model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdicionarProdutoCarrinhoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    private RowMapper<AdicionarProdutoCarrinho> adicionarProdutoCarrinhoRowMapper = (rs, rowNum) -> {
        AdicionarProdutoCarrinho adicionarProdutoCarrinho = new AdicionarProdutoCarrinho();
        adicionarProdutoCarrinho.setId(rs.getInt("id"));

        // Busca do Comprador
        Long cpfComprador = rs.getLong("cpf_comprador");
        Optional<Comprador> compradorOpt = compradorRepository.findByCpf(cpfComprador);
        compradorOpt.ifPresent(adicionarProdutoCarrinho::setComprador);

        // Busca do Produto
        Integer codigoProduto = rs.getInt("codigo_produto");
        Optional<Produto> produtoOpt = produtoRepository.findByCodigo(codigoProduto);
        produtoOpt.ifPresent(adicionarProdutoCarrinho::setProduto);

        // Busca do Carrinho
        Integer idCarrinho = rs.getInt("id_carrinho");
        Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(idCarrinho);
        carrinhoOpt.ifPresent(adicionarProdutoCarrinho::setCarrinho);

        adicionarProdutoCarrinho.setQuantidade(rs.getInt("quantidade"));

        return adicionarProdutoCarrinho;
    };

    public List<AdicionarProdutoCarrinho> findAll() {
        return jdbcTemplate.query("SELECT * FROM adicionar_produto_carrinho", adicionarProdutoCarrinhoRowMapper);
    }

    public Optional<AdicionarProdutoCarrinho> findById(Integer id) {
        try {
            AdicionarProdutoCarrinho adicionarProdutoCarrinho = jdbcTemplate.queryForObject(
                    "SELECT * FROM adicionar_produto_carrinho WHERE id = ?",
                    new Object[]{id},
                    adicionarProdutoCarrinhoRowMapper
            );
            return Optional.ofNullable(adicionarProdutoCarrinho);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public AdicionarProdutoCarrinho save(AdicionarProdutoCarrinho adicionarProdutoCarrinho) {
        String sql = "INSERT INTO adicionar_produto_carrinho " +
                "(id, cpf_comprador, codigo_produto, id_carrinho, quantidade) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "cpf_comprador = ?, codigo_produto = ?, id_carrinho = ?, quantidade = ?";

        jdbcTemplate.update(sql,
                adicionarProdutoCarrinho.getId(),
                adicionarProdutoCarrinho.getComprador().getCpf(),
                adicionarProdutoCarrinho.getProduto().getCodigoProduto(),
                adicionarProdutoCarrinho.getCarrinho().getIdCarrinho(),
                adicionarProdutoCarrinho.getQuantidade(),
                // Valores para UPDATE
                adicionarProdutoCarrinho.getComprador().getCpf(),
                adicionarProdutoCarrinho.getProduto().getCodigoProduto(),
                adicionarProdutoCarrinho.getCarrinho().getIdCarrinho(),
                adicionarProdutoCarrinho.getQuantidade()
        );

        return adicionarProdutoCarrinho;
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM adicionar_produto_carrinho WHERE id = ?", id);
    }

    public List<AdicionarProdutoCarrinho> findByComprador(Long cpfComprador) {
        return jdbcTemplate.query(
                "SELECT * FROM adicionar_produto_carrinho WHERE cpf_comprador = ?",
                new Object[]{cpfComprador},
                adicionarProdutoCarrinhoRowMapper
        );
    }

    public List<AdicionarProdutoCarrinho> findByProduto(Integer codigoProduto) {
        return jdbcTemplate.query(
                "SELECT * FROM adicionar_produto_carrinho WHERE codigo_produto = ?",
                new Object[]{codigoProduto},
                adicionarProdutoCarrinhoRowMapper
        );
    }

    public List<AdicionarProdutoCarrinho> findByCarrinho(Integer idCarrinho) {
        return jdbcTemplate.query(
                "SELECT * FROM adicionar_produto_carrinho WHERE id_carrinho = ?",
                new Object[]{idCarrinho},
                adicionarProdutoCarrinhoRowMapper
        );
    }

    public Optional<AdicionarProdutoCarrinho> findByCarrinhoAndProduto(Integer idCarrinho, Integer codigoProduto) {
        try {
            AdicionarProdutoCarrinho adicionarProdutoCarrinho = jdbcTemplate.queryForObject(
                    "SELECT * FROM adicionar_produto_carrinho WHERE id_carrinho = ? AND codigo_produto = ?",
                    new Object[]{idCarrinho, codigoProduto},
                    adicionarProdutoCarrinhoRowMapper
            );
            return Optional.ofNullable(adicionarProdutoCarrinho);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}