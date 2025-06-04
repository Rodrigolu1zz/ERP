package com.prototipo.service;

import com.prototipo.model.Fabricante;
import com.prototipo.repository.FabricanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public Fabricante cadastrarFabricante(Fabricante fabricante) {
        if (fabricante.getNome() == null || fabricante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do fabricante é obrigatório.");
        }
        return fabricanteRepository.save(fabricante);
    }

    public Optional<Fabricante> buscarPorId(Integer id) {
        return fabricanteRepository.findById(id);
    }

    public List<Fabricante> listarTodosFabricantes() {
        return fabricanteRepository.findAll();
    }

    public Fabricante atualizarFabricante(Integer id, Fabricante fabricanteDadosNovos) {
        return fabricanteRepository.findById(id).map(fabricanteExistente -> {
            fabricanteExistente.setNome(fabricanteDadosNovos.getNome());
            fabricanteExistente.setAtivo(fabricanteDadosNovos.getAtivo());
            return fabricanteRepository.save(fabricanteExistente);
        }).orElse(null);
    }

    public boolean excluirFabricante(Integer id) {
        if (fabricanteRepository.existsById(id)) {
            fabricanteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}