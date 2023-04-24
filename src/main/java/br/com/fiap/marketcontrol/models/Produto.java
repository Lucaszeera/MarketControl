package br.com.fiap.marketcontrol.models;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.marketcontrol.controllers.ProdutoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "MC_Produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Min(value=0)
    private BigDecimal valor;
    @NotNull @JsonProperty(value = "data_cadastro")
    private Calendar dataCadastro;
    @NotNull @JsonProperty(value = "data_validade")
    Calendar dataValidade;
    @NotNull
    private int quantidade;
    @NotBlank @Size(min = 3, max = 50)
    private String descricao;
    @NotNull @ManyToOne
    private Estabelecimento estabelecimento;

    public Produto() {
    }  

    public EntityModel<Produto> toModel(){
        return EntityModel.of(
        this,
        linkTo(methodOn(ProdutoController.class).getById(id)).withSelfRel(),
        linkTo(methodOn(ProdutoController.class).delete(id)).withRel("delete"),
        linkTo(methodOn(ProdutoController.class).getAll(null, Pageable.unpaged())).withRel("all"));
    }
}