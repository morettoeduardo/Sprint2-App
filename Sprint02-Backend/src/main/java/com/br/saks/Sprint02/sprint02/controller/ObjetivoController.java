package com.br.saks.Sprint02.sprint02.controller;

import com.br.saks.Sprint02.sprint02.model.Objetivo;
import com.br.saks.Sprint02.sprint02.repository.ObjetivoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/objetivos")
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository repo;

    @GetMapping
    private List<Objetivo> getObjetivos() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    private Optional<Objetivo> getObjetivoById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public Objetivo postInvestimento(@RequestBody Objetivo objetivo) {
        return repo.save(objetivo);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity editar (@PathVariable Long id, @RequestBody Objetivo objetivo) {
        return repo.findById(id)
                .map(record -> {
                    record.setNome(objetivo.getNome());
                    record.setValorEntrada(objetivo.getValorEntrada());
                    record.setValorEstimado(objetivo.getValorEstimado());
                    record.setTempoConclusao(objetivo.getTempoConclusao());
                    record.setValorMensal(objetivo.getValorMensal());
                    record.setInvestimento(objetivo.getInvestimento());
                    Objetivo objetivoUpdated = repo.save(record);
                    return ResponseEntity.ok().body(objetivoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteObjetivo(@PathVariable Long id) {
        return repo.findById(id).map(record -> {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
