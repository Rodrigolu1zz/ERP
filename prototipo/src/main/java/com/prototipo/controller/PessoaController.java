package com.prototipo.controller;

import com.prototipo.model.Pessoa;
import com.prototipo.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa novaPessoa = pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
        } catch (IllegalArgumentException e) {
            // Em um sistema real, você pode retornar e.getMessage() no corpo do erro
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Integer id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Integer id, @RequestBody Pessoa pessoaAtualizada) {
        Pessoa pessoa = pessoaService.atualizarPessoa(id, pessoaAtualizada);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable Integer id) {
        if (pessoaService.excluirPessoa(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}