package br.com.fiap.marketcontrol.models;

import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String nome;
    @NotBlank
    String cpf;
    @NotNull
    Calendar dataAdmissao;
    @NotNull
    int codigoSetor;
    
    public Responsavel( Long id, String nome, String cpf, Calendar dataAdmissao, int codigoSetor) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.codigoSetor = codigoSetor;
    }

    protected Responsavel() {
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Calendar getDataAdmissao() {
        return dataAdmissao;
    }
    public void setDataAdmissao(Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCodigoSetor() {
        return codigoSetor;
    }
    public void setCodigoSetor(int codigoSetor) {
        this.codigoSetor = codigoSetor;
    }

    @Override
    public String toString() {
        return "Responsavel [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataAdmissao=" + dataAdmissao
                + ", codigoSetor=" + codigoSetor + "]";
    }

    
    
}
