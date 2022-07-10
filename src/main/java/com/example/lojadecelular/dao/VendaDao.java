package com.example.lojadecelular.dao;

import com.example.lojadecelular.model.Venda;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaDao extends DaoPostgres implements Dao<Venda> {

    @Override
    public List<Venda> listar() throws Exception {
        String sql = "select * from venda order by codigovenda";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ResultSet rs = ps.executeQuery();

        CelularDao celularDao = new CelularDao();
        ClienteDao clienteDao = new ClienteDao();

        List<Venda> vendas = new ArrayList<Venda>();
        while (rs.next()) {
            Venda venda = new Venda();
            venda.setCodigoVenda(rs.getLong("codigovenda"));
            venda.setDataVenda(rs.getDate("datavenda").toLocalDate());
            venda.setCelular(celularDao.celularbyid(rs.getLong("idcelular")));
            venda.setCliente(clienteDao.clientebyid(rs.getLong("idcliente")));

            vendas.add(venda);
        }
        return vendas;
    }

    @Override
    public void gravar(Venda value) throws Exception {
        String sql = "INSERT INTO venda (datavenda, idcelular, idcliente) VALUES (?,?,?)";
        PreparedStatement ps = getPreparedStatement(sql, true);

        ps.setDate(1, Date.valueOf(value.getDataVenda()));
        ps.setLong(2, value.getCelular().getCodigo());
        ps.setLong(3, value.getCliente().getCodigoCliente());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        value.setCodigoVenda(rs.getLong(1));


        sql = "INSERT INTO detalhevenda (valorpedido, quantidade, idcompra, idcelular) VALUES (?,?,?,?)";
        ps = getPreparedStatement(sql, false);
        ps.setDouble(1, value.getDetalheVenda().getValorpedido());
        ps.setDouble(2, value.getDetalheVenda().getQuantidade());
        ps.setLong(3, value.getCodigoVenda());
        ps.setLong(4, value.getCelular().getCodigo());
        ps.executeUpdate();

        value.getCelular().DiminuirEstoque(value.getDetalheVenda().getQuantidade());
        CelularDao celularDao = new CelularDao();
        celularDao.atualizarEstoque(value.getCelular());
    }

    @Override
    public void alterar(Venda value) throws Exception {
        String sql = "update venda set datavenda = ?, idcelular = ?, idcliente = ? where codigovenda = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);

        ps.setDate(1, Date.valueOf(value.getDataVenda()));
        ps.setLong(2, value.getCelular().getCodigo());
        ps.setLong(3, value.getCliente().getCodigoCliente());

        ps.executeUpdate();
    }
}
