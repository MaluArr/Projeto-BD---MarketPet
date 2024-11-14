package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Produto;
import com.MarketPet.MarketPet.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{codigoProduto}")
    public ResponseEntity<Produto> buscarPorCodigo(@PathVariable Integer codigoProduto) {
        return produtoService.buscarPorCodigo(codigoProduto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Integer codigoProduto,
            @RequestBody Produto produto) {
        produto.setCodigoProduto(codigoProduto);
        Produto produtoAtualizado = produtoService.atualizarProduto(produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{codigoProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer codigoProduto) {
        produtoService.deletarProduto(codigoProduto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Produto> buscarPorCategoria(@PathVariable String categoria) {
        return produtoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/preco-abaixo/{preco}")
    public List<Produto> buscarProdutosAbaixoDe(@PathVariable BigDecimal preco) {
        return produtoService.buscarProdutosAbaixoDe(preco);
    }

    @GetMapping("/preco-acima/{preco}")
    public List<Produto> buscarProdutosAcimaDe(@PathVariable BigDecimal preco) {
        return produtoService.buscarProdutosAcimaDe(preco);
    }

    @GetMapping("/nota-acima/{nota}")
    public List<Produto> buscarProdutosComNotaMaiorQue(@PathVariable BigDecimal nota) {
        return produtoService.buscarProdutosComNotaMaiorQue(nota);
    }

    @GetMapping("/{codigoProduto}/preco-com-desconto")
    public BigDecimal calcularPrecoComDesconto(@PathVariable Integer codigoProduto) {
        return produtoService.calcularPrecoComDesconto(codigoProduto);
    }
}