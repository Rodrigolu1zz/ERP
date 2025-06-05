// prototipo/src/main/java/com/prototipo/controller/ProdutoController.java
package com.prototipo.controller;

import com.prototipo.model.Produto;
import com.prototipo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*") // Permite todas as origens (para desenvolvimento local)
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para listar todos os produtos ATIVOS (não eliminados logicamente).
     * Retorna uma lista de produtos e o status HTTP 200 OK.
     * Ex: GET http://localhost:8080/api/produtos
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos(); // Chama o método que filtra
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para "excluir" um produto logicamente (marcar como eliminado).
     * Recebe o ID do produto como uma variável no caminho da URL.
     * Retorna o status HTTP 204 No Content se a exclusão lógica for bem-sucedida,
     * ou 404 Not Found se o produto não existir.
     * Ex: DELETE http://localhost:8080/api/produtos/1
     */
    @DeleteMapping("/{id}") // Mantemos DELETE para simplicidade do frontend, mas a operação é lógica
    public ResponseEntity<Void> excluirProduto(@PathVariable Integer id) {
        if (produtoService.excluirProduto(id)) { // Chama o método de exclusão lógica
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}