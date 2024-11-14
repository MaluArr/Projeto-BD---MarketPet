package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.FavoritarProduto;
import com.MarketPet.MarketPet.Service.FavoritarProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritar-produtos")
public class FavoritarProdutoController {

    @Autowired
    private FavoritarProdutoService favoritarProdutoService;

    @GetMapping
    public List<FavoritarProduto> listarTodos() {
        return favoritarProdutoService.listarTodos();
    }

    @GetMapping("/{idLista}")
    public ResponseEntity<FavoritarProduto> buscarPorId(@PathVariable Integer idLista) {
        return favoritarProdutoService.buscarPorId(idLista)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FavoritarProduto> criarFavoritarProduto(@RequestBody FavoritarProduto favoritarProduto) {
        FavoritarProduto novoFavoritarProduto = favoritarProdutoService.criarFavoritarProduto(favoritarProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFavoritarProduto);
    }

    @PutMapping("/{idLista}")
    public ResponseEntity<FavoritarProduto> atualizarFavoritarProduto(
            @PathVariable Integer idLista,
            @RequestBody FavoritarProduto favoritarProduto) {
        favoritarProduto.setIdLista(idLista);
        FavoritarProduto favoritarProdutoAtualizado = favoritarProdutoService.atualizarFavoritarProduto(favoritarProduto);
        return ResponseEntity.ok(favoritarProdutoAtualizado);
    }

    @DeleteMapping("/{idLista}")
    public ResponseEntity<Void> deletarFavoritarProduto(@PathVariable Integer idLista) {
        favoritarProdutoService.deletarFavoritarProduto(idLista);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<FavoritarProduto> buscarPorComprador(@PathVariable Long cpfComprador) {
        return favoritarProdutoService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<FavoritarProduto> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return favoritarProdutoService.buscarPorProduto(codigoProduto);
    }

    @GetMapping("/nome-lista/{nomeLista}")
    public List<FavoritarProduto> buscarPorNomeLista(@PathVariable String nomeLista) {
        return favoritarProdutoService.buscarPorNomeLista(nomeLista);
    }
}