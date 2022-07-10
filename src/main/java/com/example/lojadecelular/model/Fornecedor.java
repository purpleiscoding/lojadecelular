package com.example.lojadecelular.model;

public class Fornecedor {

    private Long codigoFornecedor;
    private String nome;
    private String cnpj;

    public Long getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(Long codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) { this.cnpj = cnpj;}

    @Override
    public String toString() {
        return this.nome + " - " + this.cnpj;
    }
}
