package com.br.saks.Sprint02.sprint02.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@Entity
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float rentabilidade;

    @Column
    private String investimento;

    @ManyToOne
    @JoinColumn(name="id_risco")
    private Risco risco;
    
    @JsonIgnore
    @OneToMany(mappedBy = "investimento")
    private List<Objetivo> objetivos; 

    @Transient
    private String pmt; 
}
