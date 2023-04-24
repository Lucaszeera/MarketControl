package br.com.fiap.marketcontrol.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.marketcontrol.controllers.SetorController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "MC_Setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    private String descricao;
    @NotNull @ManyToOne
    private Estabelecimento estabelecimento;

    public Setor() {
    }

    public EntityModel<Setor> toModel(){
        return EntityModel.of(
        this,
        linkTo(methodOn(SetorController.class).getById(id)).withSelfRel(),
        linkTo(methodOn(SetorController.class).getById(this.getEstabelecimento().getId())).withRel("estabelecimento"),
        linkTo(methodOn(SetorController.class).delete(id)).withRel("delete"),
        linkTo(methodOn(SetorController.class).getAll(null, Pageable.unpaged())).withRel("all"));
    }

}
