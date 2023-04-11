package br.com.fiap.marketcontrol.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.marketcontrol.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    Page<Produto> findByValorContaining(BigDecimal valor, Pageable pageable);
}
