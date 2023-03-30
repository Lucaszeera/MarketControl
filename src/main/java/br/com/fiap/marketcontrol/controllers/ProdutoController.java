package br.com.fiap.marketcontrol.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.marketcontrol.models.Produto;
import br.com.fiap.marketcontrol.models.RestError;
import br.com.fiap.marketcontrol.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("marketcontrol/api/produto")
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(Produto.class);

    @Autowired
    ProdutoRepository produtoRepository;


    @GetMapping
    public List<Produto> getAll(){
        log.info("Retornando uma lista de produtos.");
        return produtoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        log.info("Pegando um produto pelo id: " + id);

        var produtoEncontrado = produtoRepository.findById(id);    

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto, BindingResult result){
         if ( result.hasErrors()) return ResponseEntity.badRequest().body(new RestError("Total de erros = " + result.getErrorCount()));
        log.info("Adicionando um produto.");

        produtoRepository.save(produto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable Long id){
        log.info("Atualizando o produto com id: " + id);

        var produtoEncontrado = produtoRepository.findById(id);
        
        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        produtoRepository.save(produto);

        return ResponseEntity.ok(produto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id){
        log.info("Excluindo o Produto com o id: " + id);

        var produtoEncontrado = produtoRepository.findById(id);

        if( produtoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        produtoRepository.delete(produtoEncontrado.get());

        return ResponseEntity.noContent().build();
    }
    
}