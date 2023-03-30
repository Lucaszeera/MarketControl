package br.com.fiap.marketcontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    @NotBlank
    @NotNull
    String cnpj;
    String nomeProprietario;
    @NotBlank
    @NotNull
    String cpfProprietario;

    public Estabelecimento(String nome, Long id, String cnpj, String nomeProprietario, String cpfProprietario) {
        this.nome = nome;
        this.id = id;
        this.cnpj = cnpj;
        this.nomeProprietario = nomeProprietario;
        this.cpfProprietario = cpfProprietario;
    }

    protected Estabelecimento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    @Override
    public String toString() {
        return "Estabelecimento [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", nomeProprietario="
                + nomeProprietario + ", cpfProprietario=" + cpfProprietario + "]";
    }

}
