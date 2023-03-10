package br.com.fiap.marketcontrol.models;

public class Estabelecimento {
    String nome;
    int categoria_id;
    String cnpj;
    String nome_proprietario;
    
    public Estabelecimento(String nome, int categoria_id, String cnpj, String nome_proprietario) {
        this.nome = nome;
        this.categoria_id = categoria_id;
        this.cnpj = cnpj;
        this.nome_proprietario = nome_proprietario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCategoria_id() {
        return categoria_id;
    }
    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getNome_proprietario() {
        return nome_proprietario;
    }
    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }


    
}
