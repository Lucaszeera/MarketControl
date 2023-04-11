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
            new Estabelecimento(2L,"Mercadão","latricinios","12131","Calvo","131"),
            Estabelecimento.builder().nome("Mercado sao jose").categoria("Tabacaria").cnpj("12344").nomeProprietario("Calvo").cpfProprietario("131").build()
        ));
        setorRepository.saveAll(List.of(
            new Setor(1L, "Material de construção", "materiais de construção para construção", estabelecimentoRepository.getReferenceById(1L)),
            new Setor(2L, "Eletrica", "eletricidade", estabelecimentoRepository.getReferenceById(2L)),
            Setor.builder().nome("Frios").descricao("Alimentos Congelados").estabelecimento(estabelecimentoRepository.getReferenceById(3L)).build()
        ));
        funcionarioRepository.saveAll(List.of(
            new Funcionario(1L, "funcionario", "999", Calendar.getInstance(), setorRepository.getReferenceById(1L), estabelecimentoRepository.getReferenceById(1L)),
            new Funcionario(2L, "joquinha", "395", Calendar.getInstance(), setorRepository.getReferenceById(2L), estabelecimentoRepository.getReferenceById(2L)),
            Funcionario.builder().nome("Camila").cpf("3970").dataAdmissao(Calendar.getInstance()).setor(setorRepository.getReferenceById(3L)).estabelecimento(estabelecimentoRepository.getReferenceById(3L)).build()
        ));
        produtoRepository.saveAll(List.of(
            new Produto(1L, new BigDecimal(99.0), Calendar.getInstance(), Calendar.getInstance(), 14, "Escova de massagear crânio", estabelecimentoRepository.getReferenceById(2L)),
            Produto.builder().valor(new BigDecimal(13.0)).dataCadastro(Calendar.getInstance()).dataValidade(Calendar.getInstance()).quantidade(3).descricao("Enxada").estabelecimento(estabelecimentoRepository.getReferenceById(1L)).build()
        ));
    }
    
}
