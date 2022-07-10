package com.example.lojadecelular.dao;

import com.example.lojadecelular.model.Compra;
import com.example.lojadecelular.model.DetalheCompra;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDao extends DaoPostgres implements Dao<Compra> {

    @Override
    public List<Compra> listar() throws Exception {
        String sql = "select * from compra order by codigocompra";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ResultSet rs = ps.executeQuery();

        CelularDao celularDao = new CelularDao();
        FornecedorDao fornecedorDao = new FornecedorDao();

        List<Compra> compras = new ArrayList<Compra>();
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setCodigoCompra(rs.getLong("codigocompra"));
            compra.setDataCompra(rs.getDate("datacompra").toLocalDate());
            compra.setCelular(celularDao.celularbyid(rs.getLong("idcelular")));
            compra.setFornecedor(fornecedorDao.getFornecedorById(rs.getLong("idfornecedor")));

            compras.add(compra);
        }
        return compras;
    }

    @Override
    public void gravar(Compra value) throws Exception {
        String sql = "INSERT INTO compra (datacompra, idcelular, idfornecedor) VALUES (?,?,?)";
        PreparedStatement ps = getPreparedStatement(sql, true);

        ps.setDate(1, Date.valueOf(value.getDataCompra()));
        ps.setLong(2, value.getCelular().getCodigo());
        ps.setLong(3, value.getFornecedor().getCodigoFornecedor());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        value.setCodigoCompra(rs.getLong(1));



        sql = "INSERT INTO detalhecompra (valorpedido, quantidade, idcompra, idcelular) VALUES (?,?,?,?)";
        ps = getPreparedStatement(sql, false);
        ps.setDouble(1, value.getDetalheCompra().getValorpedido());
        ps.setDouble(2, value.getDetalheCompra().getQuantidade());
        ps.setLong(3, value.getCodigoCompra());
        ps.setLong(4, value.getCelular().getCodigo());
        ps.executeUpdate();


        value.getCelular().AumentarEstoque(value.getDetalheCompra().getQuantidade());
        CelularDao celularDao = new CelularDao();
        celularDao.atualizarEstoque(value.getCelular());
    }

    @Override
    public void alterar(Compra value) throws Exception {
        String sql = "update compra set datacompra = ?, idcelular = ?, idfornecedor = ? where codigocompra = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);

        ps.setDate(1, Date.valueOf(value.getDataCompra()));
        ps.setLong(2, value.getCelular().getCodigo());
        ps.setLong(3, value.getFornecedor().getCodigoFornecedor());

        ps.executeUpdate();
    }
}
