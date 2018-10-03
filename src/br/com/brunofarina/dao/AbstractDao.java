/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import br.com.brunofarina.connection.Database;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.brunofarina.model.AbstractModel;

/**
 *
 * @author Bruno
 */
public abstract class AbstractDao<T extends AbstractModel> {

    protected Connection conexao;
    protected ResultSet rs;
    private Class<T> entyClass;
    protected int tamanhoRs = 0;

    public AbstractDao(Class<T> entityClass) {
        this.conexao = Database.getConnection();
        this.entyClass = entityClass;
    }

    public ResultSet listar(String sql) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return ps.executeQuery();
    }

    public T buscaPorId(T absModel) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(entyClass.getSimpleName());
        sql.append(" where id=?");
        PreparedStatement ps;
        ps = conexao.prepareStatement(sql.toString());
        ps.setInt(1, absModel.getId());
        this.rs = ps.executeQuery();
        this.rs.next();
        return absModel;
    }

    public boolean excluir(T absModel) throws SQLException {
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(absModel.getClass().getSimpleName());
        sql.append(" where id=?");

        PreparedStatement ps = conexao.prepareStatement(sql.toString());
        ps.setInt(1, absModel.getId());
        ps.execute();
        return true;
    }
}
