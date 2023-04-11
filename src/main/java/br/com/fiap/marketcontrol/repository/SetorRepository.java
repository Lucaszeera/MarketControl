package br.com.fiap.marketcontrol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.marketcontrol.models.Estabelecimento;
import br.com.fiap.marketcontrol.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    
    Page<Setor> findByEstabelecimentoContaining(Estabelecimento estabelecimento, Pageable pageable);
}
