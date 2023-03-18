package br.com.fiap.marketcontrol.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.marketcontrol.models.Responsavel;

@RestController
@RequestMapping("/api/responsavel")
public class ResponsavelController {
    
    Logger log = LoggerFactory.getLogger(ResponsavelController.class);

    List<Responsavel> listaDeResponsaveis = new ArrayList<>();


    @GetMapping
    public List<Responsavel> getAll(){
        log.info("Retornando todos os Responsaveis");

        return listaDeResponsaveis;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> getById(@PathVariable Long id){
        log.info("Pegando um responsável pelo id: " + id);

        var responsavelEncontrado = listaDeResponsaveis.stream().filter(p -> p.getId().equals(id)).findFirst();

        if(responsavelEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(responsavelEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Responsavel> create(@RequestBody Responsavel responsavel){
        log.info("Adicionando um responsavel.");
        
        responsavel.setId(listaDeResponsaveis.size() + 1l);
        listaDeResponsaveis.add(responsavel);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsavel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> update(@RequestBody Responsavel responsavel, @PathVariable Long id){
        log.info("Atualizando o responsavel com id: " + id);

        var responsavelEncontrado = listaDeResponsaveis.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(responsavelEncontrado.isEmpty()){

        }

        listaDeResponsaveis.remove(responsavelEncontrado.get());
        responsavel.setId(listaDeResponsaveis.size() + 1l);
        listaDeResponsaveis.add(responsavel);

        return ResponseEntity.ok(responsavel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        log.info("Excluindo o responsavel com o id: " + id);

        var responsavelEncontrado = listaDeResponsaveis.stream().filter(p -> p.getId().equals(id)).findFirst();

        if( responsavelEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        listaDeResponsaveis.remove(responsavelEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
