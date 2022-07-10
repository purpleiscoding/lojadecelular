package com.example.lojadecelular.dao;

import com.example.lojadecelular.model.Celular;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CelularDao extends DaoPostgres implements Dao<Celular> {
    @Override
    public List<Celular> listar() throws Exception {
        String sql = "select * from celular order by codigo";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ResultSet rs = ps.executeQuery();


        List<Celular> celulars = new ArrayList<Celular>();
        while (rs.next()){
            Celular celular = new Celular();

            celular.setCodigo(rs.getLong("codigo"));
            celular.setEstoque(rs.getDouble("estoque"));
            celular.setValor(rs.getDouble("valor"));
            celular.setMarca(rs.getString("marca"));

            celulars.add(celular);
        }
        return celulars;
    }

    @Override
    public void gravar(Celular value) throws Exception {
        String sql = "INSERT INTO celular (valor, marca, estoque) VALUES (?,?,?)";
        PreparedStatement ps = getPreparedStatement(sql, true);

        ps.setDouble(1, value.getValor());
        ps.setString(2, value.getMarca());
        ps.setDouble(3, value.getEstoque());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        value.setCodigo(rs.getLong(1));
    }

    @Override
    public void alterar(Celular value) throws Exception {
        String sql = "update celular set valor = ?, marca = ?, estoque = ? where codigo = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);

        ps.setDouble(1, value.getValor());
        ps.setString(2, value.getMarca());
        ps.setDouble(3, value.getEstoque());
        ps.executeUpdate();
    }

    public Celular celularbyid(Long id) throws Exception {
        String sql = "select * from celular where codigo = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setLong(1, id);

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        Celular celular = new Celular();

        celular.setCodigo(id);
        celular.setValor(rs.getDouble("valor"));
        celular.setMarca(rs.getString("marca"));
        celular.setEstoque(rs.getDouble("estoque"));
        return celular;
    }

    public void atualizarEstoque(Celular value) throws Exception{
        String sql = "update celular set estoque = ? where codigo = ?";
        PreparedStatement ps = getPreparedStatement(sql, false);
        ps.setDouble(1, value.getEstoque());
        ps.setLong(2, value.getCodigo());
        ps.executeUpdate();
        System.out.println(value.getCodigo());

    }
}
