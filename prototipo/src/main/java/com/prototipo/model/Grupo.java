package com.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Indica que esta classe é uma entidade JPA.
@Table(name = "grupo") // Mapeia para a tabela 'grupo' no banco de dados.
@Data // Anotação do Lombok para gerar getters, setters, etc.
public class Grupo {

    @Id // Marca o atributo como a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de autoincremento.
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false) // Nome do grupo, não pode ser nulo.
    private String nome;

    @Column(name = "ativo") // Campo para indicar se o grupo está ativo.
    private Boolean ativo = true; // Valor padrão como 'true'

    // Construtor padrão (necessário para JPA)
    public Grupo() {
    }

    // Construtor com nome (útil para criar novos grupos)
    public Grupo(String nome) {
        this.nome = nome;
        this.ativo = true;
    }
}