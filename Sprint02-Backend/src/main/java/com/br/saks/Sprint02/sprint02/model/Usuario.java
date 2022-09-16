package com.br.saks.Sprint02.sprint02.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 200)
    private String senha;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 1)
    private Integer status;
    
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Objetivo> objetivos;
    
}
