package com.example.lojadecelular.model;

import java.time.LocalDate;

public class Compra {

    private Long codigoCompra;
    private LocalDate dataCompra;

    private Celular celular;
    private Fornecedor fornecedor;
    private DetalheCompra detalheCompra;

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public DetalheCompra getDetalheCompra() {
        return detalheCompra;
    }

    public void setDetalheCompra(DetalheCompra detalheCompra) {
        this.detalheCompra = detalheCompra;
    }

    public Long getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(Long codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
