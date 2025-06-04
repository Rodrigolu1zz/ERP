package com.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "linha_produto") // Mapeia para a tabela 'linha_produto'
@Data
public class LinhaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    // Construtor padrão (necessário para JPA)
    public LinhaProduto() {
    }

    public LinhaProduto(String nome) {
        this.nome = nome;
        this.ativo = true;
    }
}