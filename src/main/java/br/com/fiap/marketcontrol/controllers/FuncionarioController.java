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
import br.com.fiap.marketcontrol.models.Estabelecimento;
import br.com.fiap.marketcontrol.models.Funcionario;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import br.com.fiap.marketcontrol.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("marketcontrol/api/funcionario")
public class FuncionarioController {
    
    Logger log = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public List<Funcionario> getAll(){
        log.info("Retornando todos os funcionarios");

        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id){
        log.info("Pegando um funcionario pelo id: " + id);

        return ResponseEntity.ok(getFuncionario(id));
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody @Valid Funcionario funcionario){
        log.info("Adicionando um funcionario.");
        
        funcionario.setEstabelecimento(getEstabelecimento(funcionario));
        funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@RequestBody @Valid Funcionario funcionario, @PathVariable Long id){
        log.info("Atualizando o funcionario com id: " + id);
        
        getFuncionario(id);

        funcionario.setId(id);
        funcionario.setEstabelecimento(getEstabelecimento(funcionario));

        funcionarioRepository.save(funcionario);
        
        return ResponseEntity.ok(funcionario);
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
