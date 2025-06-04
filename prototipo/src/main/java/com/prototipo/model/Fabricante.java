package com.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fabricante")
@Data
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo = true;

    // Construtor padrão (necessário para JPA)
    public Fabricante() {
    }

    public Fabricante(String nome) {
        this.nome = nome;
        this.ativo = true;
    }
}