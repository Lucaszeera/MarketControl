package br.com.fiap.marketcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.marketcontrol.models.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{
    
}
