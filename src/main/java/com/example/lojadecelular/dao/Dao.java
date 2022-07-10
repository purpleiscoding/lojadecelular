package com.example.lojadecelular.dao;

import java.util.List;

public interface Dao<T> {
    public List<T> listar() throws Exception;
    public void gravar(T value) throws Exception;
    public void alterar(T value) throws Exception;
}
