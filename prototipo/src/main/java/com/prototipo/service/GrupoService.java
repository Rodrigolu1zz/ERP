package com.prototipo.service;

import com.prototipo.model.Grupo;
import com.prototipo.repository.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca esta classe como um serviço Spring.
public class GrupoService {

    private final GrupoRepository grupoRepository; // Injeção de dependência do repositório.

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    /**
     * Cadastra um novo grupo.
     * @param grupo O objeto Grupo a ser salvo.
     * @return O grupo salvo.
     */
    public Grupo cadastrarGrupo(Grupo grupo) {
        // Você pode adicionar validações de negócio aqui antes de salvar
        if (grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do grupo é obrigatório.");
        }
        return grupoRepository.save(grupo);
    }

    /**
     * Busca um grupo pelo ID.
     * @param id O ID do grupo.
     * @return Um Optional contendo o grupo, se encontrado.
     */
    public Optional<Grupo> buscarPorId(Integer id) {
        return grupoRepository.findById(id);
    }

    /**
     * Lista todos os grupos.
     * @return Uma lista de grupos.
     */
    public List<Grupo> listarTodosGrupos() {
        return grupoRepository.findAll();
    }

    /**
     * Atualiza um grupo existente.
     * @param id O ID do grupo a ser atualizado.
     * @param grupoDadosNovos Os dados do grupo para atualização.
     * @return O grupo atualizado, ou null se não encontrado.
     */
    public Grupo atualizarGrupo(Integer id, Grupo grupoDadosNovos) {
        return grupoRepository.findById(id).map(grupoExistente -> {
            grupoExistente.setNome(grupoDadosNovos.getNome());
            grupoExistente.setAtivo(grupoDadosNovos.getAtivo());
            // Adicione outras regras de negócio ou campos para atualização aqui
            return grupoRepository.save(grupoExistente);
        }).orElse(null);
    }

    /**
     * Exclui um grupo pelo ID.
     * @param id O ID do grupo a ser excluído.
     * @return true se excluído com sucesso, false caso contrário.
     */
    public boolean excluirGrupo(Integer id) {
        if (grupoRepository.existsById(id)) {
            grupoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}