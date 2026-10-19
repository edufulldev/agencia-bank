package edu.dev.domain.http;

public class AgenciaBank {

    private String nome;
    private String cnpj;
    private String razaoSocial;
    private SituacaoCadastral situacaoCadastral;

    public AgenciaBank(String nome, String cnpj, String razaoSocial, SituacaoCadastral situacaoCadastral) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.situacaoCadastral = situacaoCadastral;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public SituacaoCadastral getSituacaoCadastral() {
        return situacaoCadastral;
    }
}
