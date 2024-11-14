package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.BuscaProduto;
import com.MarketPet.MarketPet.Service.BuscaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buscas-produto")
public class BuscaProdutoController {

    @Autowired
    private BuscaProdutoService buscaProdutoService;

    @GetMapping
    public List<BuscaProduto> listarTodas() {
        return buscaProdutoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscaProduto> buscarPorId(@PathVariable Integer id) {
        return buscaProdutoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BuscaProduto criarBuscaProduto(@RequestBody BuscaProduto buscaProduto) {
        return buscaProdutoService.salvarBuscaProduto(buscaProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuscaProduto> atualizarBuscaProduto(@PathVariable Integer id, @RequestBody BuscaProduto buscaProduto) {
        if (!buscaProdutoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        buscaProduto.setIdBusca(id);
        return ResponseEntity.ok(buscaProdutoService.salvarBuscaProduto(buscaProduto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBuscaProduto(@PathVariable Integer id) {
        if (!buscaProdutoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        buscaProdutoService.deletarBuscaProduto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/por-comprador")
    public List<Map<String, Object>> relatorioBuscasPorComprador() {
        return buscaProdutoService.gerarRelatorioBuscasPorComprador();
    }

    @GetMapping("/relatorio/por-categoria")
    public List<Map<String, Object>> relatorioBuscasPorCategoria() {
        return buscaProdutoService.gerarRelatorioBuscasPorCategoria();
    }
}