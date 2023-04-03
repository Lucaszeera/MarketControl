package br.com.fiap.marketcontrol.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String nome;
    @NotBlank
    String cnpj;
    @NotBlank
    String nomeProprietario;
    @NotBlank
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
