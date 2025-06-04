package com.prototipo.repository;

import com.prototipo.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marca esta interface como um repositório Spring.
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    // Métodos CRUD básicos para a entidade Grupo são fornecidos automaticamente.
    // Você pode adicionar métodos de busca personalizados aqui se precisar, por exemplo:
    // List<Grupo> findByAtivoTrue();
    // Optional<Grupo> findByNomeIgnoreCase(String nome);
}