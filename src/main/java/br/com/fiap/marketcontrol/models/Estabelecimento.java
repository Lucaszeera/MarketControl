package br.com.fiap.marketcontrol.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.fiap.marketcontrol.controllers.EstabelecimentoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "MC_Estabelecimento")
public class Estabelecimento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String categoria;
    @NotNull
    private String cnpj;
    @NotBlank @JsonProperty(value = "nome_proprietario")
    private String nomeProprietario;
    @NotBlank @JsonProperty(value = "cpf_proprietario", access = Access.WRITE_ONLY)
    private String cpfProprietario;

    public Estabelecimento() {
    }

    public EntityModel<Estabelecimento> toModel(){
        return EntityModel.of(
        this,
        linkTo(methodOn(EstabelecimentoController.class).getById(id)).withSelfRel(),
        linkTo(methodOn(EstabelecimentoController.class).delete(id)).withRel("delete"),
        linkTo(methodOn(EstabelecimentoController.class).getAll(null, Pageable.unpaged())).withRel("all"));
    }

}
