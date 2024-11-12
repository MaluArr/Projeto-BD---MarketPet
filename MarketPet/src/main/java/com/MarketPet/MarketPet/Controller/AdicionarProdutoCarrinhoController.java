package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.AdicionarProdutoCarrinho;
import com.MarketPet.MarketPet.Service.AdicionarProdutoCarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adicionar-produto-carrinho")
public class AdicionarProdutoCarrinhoController {

    @Autowired
    private AdicionarProdutoCarrinhoService adicionarProdutoCarrinhoService;

    @GetMapping
    public List<AdicionarProdutoCarrinho> listarTodos() {
        return adicionarProdutoCarrinhoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdicionarProdutoCarrinho> buscarPorId(@PathVariable Integer id) {
        return adicionarProdutoCarrinhoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdicionarProdutoCarrinho> adicionarProdutoCarrinho(@RequestBody AdicionarProdutoCarrinho adicionarProdutoCarrinho) {
        AdicionarProdutoCarrinho novoProdutoCarrinho = adicionarProdutoCarrinhoService.adicionarProdutoCarrinho(adicionarProdutoCarrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProdutoCarrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdicionarProdutoCarrinho> atualizarProdutoCarrinho(
            @PathVariable Integer id,
            @RequestBody AdicionarProdutoCarrinho adicionarProdutoCarrinho) {
        adicionarProdutoCarrinho.setId(id);
        AdicionarProdutoCarrinho produtoCarrinhoAtualizado = adicionarProdutoCarrinhoService.atualizarProdutoCarrinho(adicionarProdutoCarrinho);
        return ResponseEntity.ok(produtoCarrinhoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoCarrinho(@PathVariable Integer id) {
        adicionarProdutoCarrinhoService.deletarProdutoCarrinho(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<AdicionarProdutoCarrinho> buscarPorComprador(@PathVariable Long cpfComprador) {
        return adicionarProdutoCarrinhoService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<AdicionarProdutoCarrinho> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return adicionarProdutoCarrinhoService.buscarPorProduto(codigoProduto);
    }

    @GetMapping("/carrinho/{idCarrinho}")
    public List<AdicionarProdutoCarrinho> buscarPorCarrinho(@PathVariable Integer idCarrinho) {
        return adicionarProdutoCarrinhoService.buscarPorCarrinho(idCarrinho);
    }
}