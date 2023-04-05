package br.com.fiap.marketcontrol.config;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.marketcontrol.models.Estabelecimento;
import br.com.fiap.marketcontrol.models.Funcionario;
import br.com.fiap.marketcontrol.models.Produto;
import br.com.fiap.marketcontrol.models.Setor;
import br.com.fiap.marketcontrol.repository.EstabelecimentoRepository;
import br.com.fiap.marketcontrol.repository.FuncionarioRepository;
import br.com.fiap.marketcontrol.repository.ProdutoRepository;
import br.com.fiap.marketcontrol.repository.SetorRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;
    
    @Autowired
    SetorRepository setorRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        estabelecimentoRepository.saveAll(List.of(
            new Estabelecimento(1L,"Mercadinho","Alimentos","13200","Calvo","131"),
            new Estabelecimento(2L,"Mercadão","latricinios","12131","Calvo","131")
        ));
        setorRepository.saveAll(List.of(
            new Setor(1L, "nome1", "descricao1", estabelecimentoRepository.getReferenceById(1L)),
            new Setor(2L, "nome2", "descricao2", estabelecimentoRepository.getReferenceById(2L))
        ));
        funcionarioRepository.saveAll(List.of(
            new Funcionario(1L, "funcionario", "999", Calendar.getInstance(), setorRepository.getReferenceById(1L), estabelecimentoRepository.getReferenceById(1L)),
            new Funcionario(2L, "joquinha", "395", Calendar.getInstance(), setorRepository.getReferenceById(2L), estabelecimentoRepository.getReferenceById(2L))
        ));
        produtoRepository.saveAll(List.of(
            new Produto(1L, new BigDecimal(99.0), Calendar.getInstance(), Calendar.getInstance(), 14, "descricao", estabelecimentoRepository.getReferenceById(2L))
        ));
    }
    
}
