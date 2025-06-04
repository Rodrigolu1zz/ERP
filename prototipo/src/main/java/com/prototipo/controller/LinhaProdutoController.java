package com.prototipo.controller;

import com.prototipo.model.LinhaProduto;
import com.prototipo.service.LinhaProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linhas-produto") // Caminho base para as linhas de produto
public class LinhaProdutoController {

    private final LinhaProdutoService linhaProdutoService;

    public LinhaProdutoController(LinhaProdutoService linhaProdutoService) {
        this.linhaProdutoService = linhaProdutoService;
    }

    @PostMapping
    public ResponseEntity<LinhaProduto> cadastrarLinhaProduto(@RequestBody LinhaProduto linhaProduto) {
        try {
            LinhaProduto novaLinhaProduto = linhaProdutoService.cadastrarLinhaProduto(linhaProduto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLinhaProduto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinhaProduto> buscarLinhaProdutoPorId(@PathVariable Integer id) {
        return linhaProdutoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LinhaProduto>> listarTodasLinhasProduto() {
        List<LinhaProduto> linhasProduto = linhaProdutoService.listarTodasLinhasProduto();
        return ResponseEntity.ok(linhasProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinhaProduto> atualizarLinhaProduto(@PathVariable Integer id, @RequestBody LinhaProduto linhaProdutoAtualizada) {
        LinhaProduto linhaProduto = linhaProdutoService.atualizarLinhaProduto(id, linhaProdutoAtualizada);
        if (linhaProduto != null) {
            return ResponseEntity.ok(linhaProduto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLinhaProduto(@PathVariable Integer id) {
        if (linhaProdutoService.excluirLinhaProduto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}