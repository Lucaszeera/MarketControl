package br.com.fiap.marketcontrol.controllers;

import java.math.BigDecimal;
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
import br.com.fiap.marketcontrol.models.Produto;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import br.com.fiap.marketcontrol.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("marketcontrol/api/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;


    @GetMapping
    public Page<EntityModel<Produto>> getAll(@RequestParam(required = false) BigDecimal valor, @PageableDefault(size = 5) Pageable pageable){
        log.info("Retornando uma página de produtos.");
        Page<Produto> produtos;

        produtos = (valor == null) ? 
            produtoRepository.findAll(pageable) :
            produtoRepository.findByValorContaining(valor, pageable);


        return produtos.map((produto) ->
        produto.toModel());
    }


    @GetMapping("/{id}")
    public EntityModel<Produto> getById(@PathVariable Long id){
        log.info("Pegando um produto pelo id: " + id);
    
        return getProduto(id).toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> create(@RequestBody @Valid Produto produto){
        log.info("Adicionando um produto.");

        produto.setEstabelecimento(getEstabelecimento(produto));
        produtoRepository.save(produto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(produto.toModel());
    }
    
    @PutMapping("/{id}")
    public EntityModel<Produto> update(@RequestBody @Valid Produto produto, @PathVariable Long id){
        log.info("Atualizando o produto com id: " + id);
        
        getProduto(id);   

        produto.setId(id);
        produto.setEstabelecimento(getEstabelecimento(produto));

        produtoRepository.save(produto);
        
        return produto.toModel();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id){
        log.info("Excluindo o Produto com o id: " + id);
        
        var produto = getProduto(id);   
        
        produtoRepository.delete(produto);

        return ResponseEntity.noContent().build();
    }
    
    private Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Produto não encontrado."));
    }
    private Estabelecimento getEstabelecimento(Produto produto) {
        return estabelecimentoRepository.findById(produto.getEstabelecimento().getId()).get();
    }
    
}