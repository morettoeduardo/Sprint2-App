/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.saks.Sprint02.sprint02.repository;

import com.br.saks.Sprint02.sprint02.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eduar
 */
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long>{
    
}
