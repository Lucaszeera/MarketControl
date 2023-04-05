package br.com.fiap.marketcontrol.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "MC_Estabelecimento")
public class Estabelecimento {
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

}
