package com.prototipo.controller;

import com.prototipo.model.Fabricante;
import com.prototipo.service.FabricanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @PostMapping
    public ResponseEntity<Fabricante> cadastrarFabricante(@RequestBody Fabricante fabricante) {
        try {
            Fabricante novoFabricante = fabricanteService.cadastrarFabricante(fabricante);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFabricante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> buscarFabricantePorId(@PathVariable Integer id) {
        return fabricanteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Fabricante>> listarTodosFabricantes() {
        List<Fabricante> fabricantes = fabricanteService.listarTodosFabricantes();
        return ResponseEntity.ok(fabricantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> atualizarFabricante(@PathVariable Integer id, @RequestBody Fabricante fabricanteAtualizado) {
        Fabricante fabricante = fabricanteService.atualizarFabricante(id, fabricanteAtualizado);
        if (fabricante != null) {
            return ResponseEntity.ok(fabricante);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFabricante(@PathVariable Integer id) {
        if (fabricanteService.excluirFabricante(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}