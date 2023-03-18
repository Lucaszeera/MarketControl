package br.com.fiap.marketcontrol.models;

import java.math.BigDecimal;
import java.util.Calendar;


public class Produto {
    
    BigDecimal valor;
    Long id;
    Calendar dataCadastro;
    Calendar dataValidade;
    int quantidade;
    String descricao;

    public Produto(BigDecimal valor, Long id, Calendar dataCadastro, Calendar dataValidade, int quantidade,
            String descricao) {
        this.valor = valor;
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Calendar getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Calendar getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(Calendar dataValidade) {
        this.dataValidade = dataValidade;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
 