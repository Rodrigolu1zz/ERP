package com.prototipo.repository;

import com.prototipo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    // List<Pessoa> findByTipo(String tipo); // Exemplo de método personalizado
}