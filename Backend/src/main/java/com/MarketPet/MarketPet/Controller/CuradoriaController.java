package com.MarketPet.MarketPet.Controller;

import com.MarketPet.MarketPet.Model.Curadoria;
import com.MarketPet.MarketPet.Service.CuradoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curadorias")
public class CuradoriaController {

    @Autowired
    private CuradoriaService curadoriaService;

    @GetMapping
    public List<Curadoria> listarTodos() {
        return curadoriaService.listarTodos();
    }

    @GetMapping("/{codigoCuradoria}")
    public ResponseEntity<Curadoria> buscarPorCodigo(@PathVariable Integer codigoCuradoria) {
        return curadoriaService.buscarPorCodigo(codigoCuradoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curadoria> criarCuradoria(@RequestBody Curadoria curadoria) {
        Curadoria novaCuradoria = curadoriaService.criarCuradoria(curadoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCuradoria);
    }

    @PutMapping("/{codigoCuradoria}")
    public ResponseEntity<Curadoria> atualizarCuradoria(
            @PathVariable Integer codigoCuradoria,
            @RequestBody Curadoria curadoria) {
        curadoria.setCodigoCuradoria(codigoCuradoria);
        Curadoria curadoriaAtualizada = curadoriaService.atualizarCuradoria(curadoria);
        return ResponseEntity.ok(curadoriaAtualizada);
    }

    @DeleteMapping("/{codigoCuradoria}")
    public ResponseEntity<Void> deletarCuradoria(@PathVariable Integer codigoCuradoria) {
        curadoriaService.deletarCuradoria(codigoCuradoria);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/curador/{idCurador}")
    public List<Curadoria> buscarPorCurador(@PathVariable Integer idCurador) {
        return curadoriaService.buscarPorCurador(idCurador);
    }

    @GetMapping("/produto/{codigoProduto}")
    public List<Curadoria> buscarPorProduto(@PathVariable Integer codigoProduto) {
        return curadoriaService.buscarPorProduto(codigoProduto);
    }
}