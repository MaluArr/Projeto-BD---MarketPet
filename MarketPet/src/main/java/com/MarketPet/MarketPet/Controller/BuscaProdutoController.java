package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import com.MarketPet.MarketPet.Service.BuscaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buscas-produto")
public class BuscaProdutoController {

    @Autowired
    private BuscaProdutoService buscaProdutoService;

    @GetMapping
    public List<BuscaProduto> listarTodos() {
        return buscaProdutoService.listarTodos();
    }

    @GetMapping("/{idBusca}")
    public ResponseEntity<BuscaProduto> buscarPorId(@PathVariable Integer idBusca) {
        return buscaProdutoService.buscarPorId(idBusca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BuscaProduto> criarBuscaProduto(@RequestBody BuscaProduto buscaProduto) {
        BuscaProduto novaBuscaProduto = buscaProdutoService.criarBuscaProduto(buscaProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaBuscaProduto);
    }

    @PutMapping("/{idBusca}")
    public ResponseEntity<BuscaProduto> atualizarBuscaProduto(
            @PathVariable Integer idBusca,
            @RequestBody BuscaProduto buscaProduto) {
        buscaProduto.setIdBusca(idBusca);
        BuscaProduto buscaProdutoAtualizada = buscaProdutoService.atualizarBuscaProduto(buscaProduto);
        return ResponseEntity.ok(buscaProdutoAtualizada);
    }

    @DeleteMapping("/{idBusca}")
    public ResponseEntity<Void> deletarBuscaProduto(@PathVariable Integer idBusca) {
        buscaProdutoService.deletarBuscaProduto(idBusca);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{cpfComprador}")
    public List<BuscaProduto> buscarPorComprador(@PathVariable Long cpfComprador) {
        return buscaProdutoService.buscarPorComprador(cpfComprador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<BuscaProduto> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return buscaProdutoService.buscarPorProduto(codigoProduto);
    }

    @GetMapping("/categoria/{categoria}")
    public List<BuscaProduto> buscarPorCategoria(@PathVariable String categoria) {
        return buscaProdutoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/preco-abaixo/{preco}")
    public List<BuscaProduto> buscarProdutosAbaixoDe(@PathVariable Float preco) {
        return buscaProdutoService.buscarProdutosAbaixoDe(preco);
    }

    @GetMapping("/regiao/{regiao}")
    public List<BuscaProduto> buscarPorRegiao(@PathVariable String regiao) {
        return buscaProdutoService.buscarPorRegiao(regiao);
    }
}