package br.com.fiap.marketcontrol.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.marketcontrol.exceptions.RestNotFoundException;
import br.com.fiap.marketcontrol.models.Estabelecimento;
import br.com.fiap.marketcontrol.models.Setor;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import br.com.fiap.marketcontrol.repository.SetorRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("marketcontrol/api/setor")
public class SetorController {

    @Autowired
    SetorRepository setorRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;


    @GetMapping
    public Page<Setor> getAll(@RequestParam(required = false) Estabelecimento estabelecimento, @PageableDefault(size = 5) Pageable pageable){
        log.info("Retornando uma página de setores.");
        if(estabelecimento == null) return setorRepository.findAll(pageable);
        
        return setorRepository.findByEstabelecimentoContaining(estabelecimento, pageable);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Setor> getById(@PathVariable Long id){
        log.info("Pegando um setor pelo id: " + id);
    
        return ResponseEntity.ok(getSetor(id));
    }

    @PostMapping
    public ResponseEntity<Setor> create(@RequestBody @Valid Setor setor){
        log.info("Adicionando um setor.");

        setor.setEstabelecimento(getEstabelecimento(setor));
        setorRepository.save(setor);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(setor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Setor> update(@RequestBody @Valid Setor setor, @PathVariable Long id){
        log.info("Atualizando o setor com id: " + id);
        
        getSetor(id);   
        
        setor.setId(id);
        setorRepository.save(setor);
        
        return ResponseEntity.ok(setor);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Setor> delete(@PathVariable Long id){
        log.info("Excluindo o Setor com o id: " + id);
        
        var setor = getSetor(id);   
        
        setorRepository.delete(setor);

        return ResponseEntity.noContent().build();
    }
    
    private Setor getSetor(Long id) {
        return setorRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Setor não encontrado."));
    }
    private Estabelecimento getEstabelecimento(Setor setor) {
        return estabelecimentoRepository.findById(setor.getEstabelecimento().getId()).get();
    }
    
}