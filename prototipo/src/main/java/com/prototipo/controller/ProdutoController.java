package com.prototipo.controller;

import com.prototipo.model.Produto;
import com.prototipo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST.
@RequestMapping("/api/produtos") // Define o caminho base para todos os endpoints deste controlador.
public class ProdutoController {

    private final ProdutoService produtoService; // Injeta o serviço de produto.

    // Construtor para injeção de dependência do ProdutoService.
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Endpoint para cadastrar um novo produto.
     * Recebe um objeto Produto no corpo da requisição (JSON).
     * Retorna o produto salvo e o status HTTP 201 Created.
     * Ex: POST http://localhost:8080/api/produtos
     * Corpo da requisição (JSON):
     * {
     * "nome": "Smartphone XYZ",
     * "codigo": "SMART001",
     * "precoVenda": 1500.00,
     * "ativo": true
     * // ... outros campos do produto
     * }
     */
    @PostMapping // Mapeia requisições POST para este método.
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    /**
     * Endpoint para buscar um produto pelo ID.
     * Recebe o ID do produto como uma variável no caminho da URL.
     * Retorna o produto encontrado e o status HTTP 200 OK, ou 404 Not Found se não existir.
     * Ex: GET http://localhost:8080/api/produtos/1
     */
    @GetMapping("/{id}") // Mapeia requisições GET com um ID na URL.
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(produto)) // Se encontrar, retorna 200 OK com o produto.
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found.
    }

    /**
     * Endpoint para listar todos os produtos.
     * Retorna uma lista de produtos e o status HTTP 200 OK.
     * Ex: GET http://localhost:8080/api/produtos
     */
    @GetMapping // Mapeia requisições GET para este método.
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos); // Retorna 200 OK com a lista de produtos.
    }

    /**
     * Endpoint para atualizar um produto existente.
     * Recebe o ID do produto na URL e os dados atualizados no corpo da requisição (JSON).
     * Retorna o produto atualizado e o status HTTP 200 OK, ou 404 Not Found se não existir.
     * Ex: PUT http://localhost:8080/api/produtos/1
     * Corpo da requisição (JSON):
     * {
     * "nome": "Smartphone XYZ Pro",
     * "precoVenda": 1800.00,
     * "ativo": true
     * // ... outros campos a serem atualizados
     * }
     */
    @PutMapping("/{id}") // Mapeia requisições PUT com um ID na URL.
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
        if (produto != null) {
            return ResponseEntity.ok(produto); // Se atualizar, retorna 200 OK com o produto.
        }
        return ResponseEntity.notFound().build(); // Se não encontrar, retorna 404 Not Found.
    }

    /**
     * Endpoint para excluir um produto.
     * Recebe o ID do produto como uma variável no caminho da URL.
     * Retorna o status HTTP 204 No Content se a exclusão for bem-sucedida,
     * ou 404 Not Found se o produto não existir.
     * Ex: DELETE http://localhost:8080/api/produtos/1
     */
    @DeleteMapping("/{id}") // Mapeia requisições DELETE com um ID na URL.
    public ResponseEntity<Void> excluirProduto(@PathVariable Integer id) {
        if (produtoService.excluirProduto(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content para exclusão bem-sucedida.
        }
        return ResponseEntity.notFound().build(); // 404 Not Found se não existir.
    }
}