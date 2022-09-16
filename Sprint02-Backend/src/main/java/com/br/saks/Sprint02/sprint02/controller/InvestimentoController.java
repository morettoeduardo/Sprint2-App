package com.br.saks.Sprint02.sprint02.controller;

import java.text.DecimalFormat;
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
import com.br.saks.Sprint02.sprint02.model.Investimento;
import com.br.saks.Sprint02.sprint02.repository.InvestimentoRepository;

@RestController
@RequestMapping("/api/investimentos")
public class InvestimentoController {
    @Autowired
    private InvestimentoRepository repo;

    @GetMapping
    private List<Investimento> getInvestimentos(){
        return repo.findAll();
    }

    @GetMapping(value="/{id}")
    private Optional<Investimento> getInvestimentoById(@PathVariable Long id){
        return repo.findById(id);
    }

    @GetMapping(value="/getInvestimentosComPmt/{tempoInvestimento}/{valorEntrada}/{valorEstimado}")
    private List<Investimento> getInvestimentosComPmt(@PathVariable int tempoInvestimento, @PathVariable double valorEntrada, @PathVariable double valorEstimado){
        List<Investimento> investimentos = repo.findAll();

        for(Investimento investimento : investimentos){
            investimento.setPmt(calcularInvestimentoMensal(valorEstimado, valorEntrada, investimento.getRentabilidade(), tempoInvestimento));
        }

        return investimentos;
    }

    private String calcularInvestimentoMensal(double vf, double vp, double i, int n){
        double umMaisINaN = Math.pow((1.0 + i), n);

        double pmt = ((vf - (vp * umMaisINaN))/((umMaisINaN - 1.0)/i));
        
        DecimalFormat df = new DecimalFormat("0.00");

        String pmtFormatada;

        pmtFormatada = df.format(pmt);

        return pmtFormatada;
    }

    @PostMapping
    public Investimento postInvestimento(@RequestBody Investimento investimento){
        return repo.save(investimento);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Investimento investimento) {
        return repo.findById(id)
                .map(record -> {
                    record.setRentabilidade(investimento.getRentabilidade());
                    Investimento investimentoUpdated = repo.save(record);
                    return ResponseEntity.ok().body(investimentoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteInvestimento(@PathVariable Long id){
        return repo.findById(id).map(record->{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
