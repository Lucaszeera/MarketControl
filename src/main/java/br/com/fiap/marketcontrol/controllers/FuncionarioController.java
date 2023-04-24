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
import br.com.fiap.marketcontrol.models.Funcionario;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import br.com.fiap.marketcontrol.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("marketcontrol/api/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public Page<EntityModel<Funcionario>> getAll(@RequestParam(required = false) Estabelecimento estabelecimento, @PageableDefault(size = 5) Pageable pageable){
        log.info("Retornando uma página de funcionarios");
        Page<Funcionario> funcionarios;
        
        funcionarios = (estabelecimento == null) ? 
        funcionarioRepository.findAll(pageable) : funcionarioRepository.findByEstabelecimentoContaining(estabelecimento, pageable);

        return funcionarios.map((funcionario) -> 
            funcionario.toModel());
        
    }

    @GetMapping("/{id}")
    public EntityModel<Funcionario> getById(@PathVariable Long id){
        log.info("Pegando um funcionario pelo id: " + id);

        return getFuncionario(id).toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Funcionario>> create(@RequestBody @Valid Funcionario funcionario){
        log.info("Adicionando um funcionario.");
        
        funcionario.setEstabelecimento(getEstabelecimento(funcionario));
        funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario.toModel());
    }

    
    @PutMapping("/{id}")
    public EntityModel<Funcionario> update(@RequestBody @Valid Funcionario funcionario, @PathVariable Long id){
        log.info("Atualizando o funcionario com id: " + id);
        
        getFuncionario(id);

        funcionario.setId(id);
        funcionario.setEstabelecimento(getEstabelecimento(funcionario));

        funcionarioRepository.save(funcionario);
        
        return funcionario.toModel();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id){
        log.info("Excluindo o funcionario com o id: " + id);
        
        funcionarioRepository.delete(getFuncionario(id));
        
        return ResponseEntity.noContent().build();
    }
    
    private Funcionario getFuncionario(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Funcionario não encontrado."));
    }
    private Estabelecimento getEstabelecimento(Funcionario funcionario) {
        return estabelecimentoRepository.findById(funcionario.getEstabelecimento().getId()).get();
    }
    
}
