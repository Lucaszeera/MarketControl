package br.com.fiap.marketcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.marketcontrol.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    
}
