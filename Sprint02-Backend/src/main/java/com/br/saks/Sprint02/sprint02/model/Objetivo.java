package com.br.saks.Sprint02.sprint02.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@Entity
public class Objetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = false, length = 200)
    private String nome;
    
    @Column(nullable = false)
    private int tempoConclusao;
    
    @Column(nullable = false, unique = false, length = 30)
    private float valorEstimado;
    
    @Column(nullable = false, unique = false, length = 20)
    private float valorEntrada;
    
    @Column(nullable = true, unique = false, length = 20)
    private String valorMensal;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_investimento")
    private Investimento investimento;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario usuario;
}
