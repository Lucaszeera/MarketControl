package br.com.fiap.marketcontrol.models;

public class Estabelecimento {
    String nome;
    Long id;
    String cnpj;
    String nomeProprietario;
    String cpfProprietario;

    public Estabelecimento(String nome, Long id, String cnpj, String nomeProprietario, String cpfProprietario) {
        this.nome = nome;
        this.id = id;
        this.cnpj = cnpj;
        this.nomeProprietario = nomeProprietario;
        this.cpfProprietario = cpfProprietario;
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

}
