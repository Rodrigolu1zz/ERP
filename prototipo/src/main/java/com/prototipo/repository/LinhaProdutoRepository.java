package com.prototipo.repository;

import com.prototipo.model.LinhaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaProdutoRepository extends JpaRepository<LinhaProduto, Integer> {
}