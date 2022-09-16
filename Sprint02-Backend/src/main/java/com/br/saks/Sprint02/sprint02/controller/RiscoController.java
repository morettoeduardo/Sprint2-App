package com.br.saks.Sprint02.sprint02.controller;

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
import com.br.saks.Sprint02.sprint02.model.Risco;
import com.br.saks.Sprint02.sprint02.repository.RiscoRepository;

@RestController
@RequestMapping("/api/riscos")
public class RiscoController {
    @Autowired
    private RiscoRepository repo;

    @GetMapping
    public List<Risco> getRiscos(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Risco> getRiscoById(@PathVariable long id){
        return repo.findById(id);
    }

    @PostMapping
    public Risco postRisco(@RequestBody Risco risco){
        return repo.save(risco);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Risco risco) {
        return repo.findById(id)
                .map(record -> {
                    record.setNome(risco.getNome());
                    Risco riscoUpdated = repo.save(record);
                    return ResponseEntity.ok().body(riscoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteRisco(@PathVariable Long id){
        return repo.findById(id).map(record->{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
