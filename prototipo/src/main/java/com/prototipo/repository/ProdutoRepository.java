package com.prototipo.repository;

import com.prototipo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um componente de repositório do Spring.
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // O Spring Data JPA automaticamente fornece métodos CRUD básicos (save, findById, findAll, delete, etc.)
    // Você pode adicionar métodos de consulta personalizados aqui, se precisar, seguindo as convenções de nomes do Spring Data JPA.
    // Por exemplo:
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
    // Optional<Produto> findByCodigo(String codigo);
}