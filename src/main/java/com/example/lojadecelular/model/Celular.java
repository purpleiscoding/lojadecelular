package com.example.lojadecelular.model;

public class Celular {
    private Long codigo;
    private String marca;
    private Double valor;
    private Double estoque;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public void AumentarEstoque(Double estoque){
        this.estoque += estoque;
    }

    public void DiminuirEstoque(Double estoque){
        this.estoque -= estoque;
    }

    @Override
    public String toString() {
        return this.codigo + " - " + this.marca;
    }
}
