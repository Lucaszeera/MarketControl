package br.com.fiap.marketcontrol.models;

import java.math.BigDecimal;
import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "MC_Produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull @Min(value=0)
    BigDecimal valor;
    @NotNull
    Calendar dataCadastro;
    Calendar dataValidade;
    @NotNull
    int quantidade;
    @NotBlank @Size(min = 3, max = 40)
    String descricao;

    public Produto() {
    }

}
