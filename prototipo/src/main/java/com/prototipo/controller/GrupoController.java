package com.prototipo.controller;

import com.prototipo.model.Grupo;
import com.prototipo.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que é um controlador REST.
@RequestMapping("/api/grupos") // Define o caminho base para os endpoints de grupo.
public class GrupoController {

    private final GrupoService grupoService; // Injeção de dependência do serviço.

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    /**
     * Endpoint para cadastrar um novo grupo.
     * POST /api/grupos
     * Corpo da requisição (JSON): { "nome": "Eletrônicos", "ativo": true }
     */
    @PostMapping
    public ResponseEntity<Grupo> cadastrarGrupo(@RequestBody Grupo grupo) {
        try {
            Grupo novoGrupo = grupoService.cadastrarGrupo(grupo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoGrupo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request em caso de validação falha
        }
    }

    /**
     * Endpoint para buscar um grupo pelo ID.
     * GET /api/grupos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> buscarGrupoPorId(@PathVariable Integer id) {
        return grupoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Retorna 200 OK com o grupo
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 Not Found
    }

    /**
     * Endpoint para listar todos os grupos.
     * GET /api/grupos
     */
    @GetMapping
    public ResponseEntity<List<Grupo>> listarTodosGrupos() {
        List<Grupo> grupos = grupoService.listarTodosGrupos();
        return ResponseEntity.ok(grupos); // Retorna 200 OK com a lista de grupos
    }

    /**
     * Endpoint para atualizar um grupo existente.
     * PUT /api/grupos/{id}
     * Corpo da requisição (JSON): { "nome": "Eletrodomésticos", "ativo": false }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> atualizarGrupo(@PathVariable Integer id, @RequestBody Grupo grupoAtualizado) {
        Grupo grupo = grupoService.atualizarGrupo(id, grupoAtualizado);
        if (grupo != null) {
            return ResponseEntity.ok(grupo); // Retorna 200 OK com o grupo atualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }

    /**
     * Endpoint para excluir um grupo.
     * DELETE /api/grupos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGrupo(@PathVariable Integer id) {
        if (grupoService.excluirGrupo(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content para exclusão bem-sucedida
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }
}