package com.example.lojadecelular.dao;

import com.example.lojadecelular.model.Fornecedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao extends DaoPostgres implements Dao<Fornecedor>{
    @Override
    public List<Fornecedor> listar() throws Exception {
        String sql = "select * from fornecedor order by codigofornecedor";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ResultSet rs = ps.executeQuery();



        List<Fornecedor> fornecedores = new ArrayList<>();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setCodigoFornecedor(rs.getLong("codigofornecedor"));
            fornecedores.add(fornecedor);
        }

        return fornecedores;
    }

    @Override
    public void gravar(Fornecedor value) throws Exception {
        String sql = "INSERT INTO fornecedor (nome, cnpj) VALUES (?,?)";
        PreparedStatement ps = getPreparedStatement(sql, true);
        ps.setString(1, value.getNome());
        ps.setString(2, value.getCnpj());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        value.setCodigoFornecedor(rs.getLong("codigofornecedor"));

    }

    @Override
    public void alterar(Fornecedor value) throws Exception {
        String sql = "update fornecedor set nome = ?, cnpj = ? where codigofornecedor = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setString(1, value.getNome());
        ps.setString(2, value.getCnpj());
        ps.setLong(3, value.getCodigoFornecedor());
        ps.executeUpdate();
    }

    public Fornecedor getFornecedorById(Long id) throws Exception {
        String sql = "select * from fornecedor where codigofornecedor = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setLong(1, id);

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigoFornecedor(id);
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setCnpj(rs.getString("cnpj"));
        return fornecedor;
    }
}

