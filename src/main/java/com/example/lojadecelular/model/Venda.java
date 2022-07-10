package com.example.lojadecelular.model;

import java.time.LocalDate;

public class Venda {

    private Long codigoVenda;
    private LocalDate dataVenda;

    private Celular celular;
    private Cliente cliente;
    private DetalheVenda detalheVenda;

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DetalheVenda getDetalheVenda() {
        return detalheVenda;
    }

    public void setDetalheVenda(DetalheVenda detalheVenda) {
        this.detalheVenda = detalheVenda;
    }

    public Long getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(Long codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
