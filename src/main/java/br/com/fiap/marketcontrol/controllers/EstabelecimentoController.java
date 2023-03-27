package br.com.fiap.marketcontrol.controllers;

import java.util.ArrayList;
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

import br.com.fiap.marketcontrol.models.Estabelecimento;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("marketcontrol/api/estabelecimento")
public class EstabelecimentoController {
    
    Logger log = LoggerFactory.getLogger(EstabelecimentoController.class);

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public List<Estabelecimento> getAll(){
        log.info("Retornando todos os estabelecimentos");

        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> getById(@PathVariable Long id){
        log.info("Pegando um produto pelo id: " + id);

        var estabelecimentoEncontrado = estabelecimentoRepository.findById(id);

        if(estabelecimentoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estabelecimentoEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> create(@RequestBody Estabelecimento estabelecimento){
        log.info("Adicionando um estabelecimento.");
        
        estabelecimentoRepository.save(estabelecimento);

        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> update(@RequestBody Estabelecimento estabelecimento, @PathVariable Long id){
        log.info("Atualizando o produto com id: " + id);

        var estabelecimentoEncontrado = estabelecimentoRepository.findById(id);

        if(estabelecimentoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        estabelecimento.setId(id);
        estabelecimentoRepository.save(estabelecimento);

        return ResponseEntity.ok(estabelecimento);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estabelecimento> delete(@PathVariable Long id){
        log.info("Excluindo o Estabelecimento com o id: " + id);
        var estabelecimentoEncontrado = estabelecimentoRepository.findById(id);

        if( estabelecimentoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        estabelecimentoRepository.delete(estabelecimentoEncontrado.get());

        return ResponseEntity.noContent().build();
    }
    
}