package com.prototipo.repository;

import com.prototipo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // Adiciona um método para buscar produtos que NÃO foram eliminados logicamente
    List<Produto> findByEliminadoFalse();
}