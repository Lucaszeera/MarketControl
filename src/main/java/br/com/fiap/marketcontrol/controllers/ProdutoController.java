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

import br.com.fiap.marketcontrol.models.Produto;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(Produto.class);

    List<Produto> listaDeProdutos = new ArrayList<>();


    @GetMapping
    public List<Produto> getAll(){
        log.info("Retornando uma lista de produtos.");
        return listaDeProdutos;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        log.info("Pegando um produto pelo id: " + id);

        var produtoEncontrado = listaDeProdutos.stream().filter(p -> p.getId().equals(id)).findFirst();

        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(produtoEncontrado.get());
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        log.info("Adicionando um produto.");
        produto.setId(listaDeProdutos.size() + 1l);
        listaDeProdutos.add(produto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable Long id){
        log.info("Atualizando o produto com id: " + id);

        var produtoEncontrado = listaDeProdutos.stream().filter(p -> p.getId().equals(id)).findFirst();
        
        if(produtoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        listaDeProdutos.remove(produtoEncontrado.get());
        produto.setId(produtoEncontrado.get().getId());
        listaDeProdutos.add(produto);
        return ResponseEntity.ok(produto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        log.info("Excluindo o Produto com o id: " + id);

        var produtoEncontrado = listaDeProdutos.stream().filter(p -> p.getId().equals(id)).findFirst();

        if( produtoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        listaDeProdutos.remove(produtoEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}