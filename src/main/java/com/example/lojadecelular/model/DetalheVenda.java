package com.example.lojadecelular.model;

public class DetalheVenda {

    private Double valorpedido;
    private Double quantidade;

    private Celular celular;

    public Double getValorpedido() {
        return valorpedido;
    }

    public void setValorpedido(Double valorpedido) {
        this.valorpedido = valorpedido;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return this.valorpedido + " - " + this.quantidade + " - " + this.celular;
    }
}
