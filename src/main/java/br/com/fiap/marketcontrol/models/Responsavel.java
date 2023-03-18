package br.com.fiap.marketcontrol.models;

import java.util.Calendar;

public class Responsavel {
    
    String nome;
    String cpf;
    Calendar dataAdmissao;
    Long id;
    int codigo_setor;
    
    public Responsavel(String nome, String cpf, Calendar dataAdmissao, Long id, int codigo_setor) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.id = id;
        this.codigo_setor = codigo_setor;
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
    public int getCodigo_setor() {
        return codigo_setor;
    }
    public void setCodigo_setor(int codigo_setor) {
        this.codigo_setor = codigo_setor;
    }

    
}
