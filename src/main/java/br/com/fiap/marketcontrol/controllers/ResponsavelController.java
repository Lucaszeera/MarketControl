package br.com.fiap.marketcontrol.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.fiap.marketcontrol.exceptions.RestNotFoundException;
import br.com.fiap.marketcontrol.models.Responsavel;
import br.com.fiap.marketcontrol.repository.ResponsavelRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("marketcontrol/api/responsavel")
public class ResponsavelController {
    
    Logger log = LoggerFactory.getLogger(ResponsavelController.class);

    @Autowired
    ResponsavelRepository responsavelRepository;

    @GetMapping
    public List<Responsavel> getAll(){
        log.info("Retornando todos os Responsaveis");

        return responsavelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> getById(@PathVariable Long id){
        log.info("Pegando um responsável pelo id: " + id);

        var responsavel = responsavelRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Responsável não encontrado."));

        return ResponseEntity.ok(responsavel);
    }

    @PostMapping
    public ResponseEntity<Responsavel> create(@RequestBody @Valid Responsavel responsavel){
        log.info("Adicionando um responsavel.");
        
        responsavelRepository.save(responsavel);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsavel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> update(@RequestBody @Valid Responsavel responsavel, @PathVariable Long id){
        log.info("Atualizando o responsavel com id: " + id);

        responsavelRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Responsável não encontrado."));

        responsavel.setId(id);
        responsavelRepository.save(responsavel);

        return ResponseEntity.ok(responsavel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Responsavel> delete(@PathVariable Long id){
        log.info("Excluindo o responsavel com o id: " + id);

        var responsavel = responsavelRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Responsável não encontrado."));

        responsavelRepository.delete(responsavel);

        return ResponseEntity.noContent().build();
    }

}
