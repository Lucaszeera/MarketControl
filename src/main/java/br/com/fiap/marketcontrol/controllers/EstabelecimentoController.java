package br.com.fiap.marketcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
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
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("marketcontrol/api/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public Page<EntityModel<Estabelecimento>> getAll(@RequestParam(required = false) String nome, @PageableDefault(size = 5) Pageable pageable){
        log.info("Retornando todos os estabelecimentos");
        Page<Estabelecimento> estabelecimentos;

        estabelecimentos = (nome == null) ?
            estabelecimentoRepository.findAll(pageable) :
            estabelecimentoRepository.findByNomeContaining(nome, pageable);

        return estabelecimentos.map((estabelecimento) -> 
        estabelecimento.toModel());
    }

    @GetMapping("/{id}")
    public EntityModel<Estabelecimento> getById(@PathVariable Long id){
        log.info("Pegando um produto pelo id: " + id); 

        return getEstabelecimento(id).toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Estabelecimento>> create(@RequestBody @Valid Estabelecimento estabelecimento){
        log.info("Adicionando um estabelecimento.");
        
        estabelecimentoRepository.save(estabelecimento);

        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimento.toModel());
    }
    
    @PutMapping("/{id}")
    public EntityModel<Estabelecimento> update(@RequestBody @Valid Estabelecimento estabelecimento, @PathVariable Long id){
        log.info("Atualizando o produto com id: " + id);

        getEstabelecimento(id);

        estabelecimento.setId(id);
        estabelecimentoRepository.save(estabelecimento);

        return estabelecimento.toModel();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estabelecimento> delete(@PathVariable Long id){
        log.info("Excluindo o Estabelecimento com o id: " + id);

        estabelecimentoRepository.delete(getEstabelecimento(id));

        return ResponseEntity.noContent().build();
    }

    private Estabelecimento getEstabelecimento(Long id) {
        return estabelecimentoRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Estabelecimento não encontrado."));
    }
    
}