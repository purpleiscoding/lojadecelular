package com.example.lojadecelular.dao;

import com.example.lojadecelular.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends DaoPostgres implements Dao<Cliente>{
//TODO:
    @Override
    public List<Cliente> listar() throws Exception {
        String sql = "select * from cliente order by codigocliente";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ResultSet rs = ps.executeQuery();

        List<Cliente> clientes = new ArrayList<Cliente>();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setCodigoCliente(rs.getLong("codigocliente"));
            clientes.add(cliente);
        }

        return clientes;
    }

    @Override
    public void gravar(Cliente value) throws Exception {
        String sql = "INSERT INTO cliente (nome, cpf) VALUES (?,?)";
        PreparedStatement ps = getPreparedStatement(sql, true);
        ps.setString(1, value.getNome());
        ps.setString(2, value.getCpf());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        value.setCodigoCliente(rs.getLong("codigocliente"));

    }

    @Override
    public void alterar(Cliente value) throws Exception {
        String sql = "update cliente set nome = ?, cpf = ? where codigocliente = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setString(1, value.getNome());
        ps.setString(2, value.getCpf());
        ps.setLong(3, value.getCodigoCliente());
        ps.executeUpdate();
    }

    public Cliente clientebyid(Long id) throws Exception {
        String sql = "select * from cliente where codigocliente = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setLong(1, id);

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(id);
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        return cliente;
    }
}
