package br.com.fiap.marketcontrol.models;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@Table(name = "MC_Funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank @JsonProperty(access = Access.WRITE_ONLY)
    private String cpf;
    @NotNull @JsonProperty(value = "data_admissao")
    private Calendar dataAdmissao;
    @NotNull @ManyToOne
    private Setor setor;
    @NotNull @ManyToOne
    private Estabelecimento estabelecimento;

    public Funcionario() {
    }   
    
}
