/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import br.com.brunofarina.annotations.ColunaBD;
import br.com.brunofarina.connection.Database;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.brunofarina.model.AbstractModel;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String sql = "select * from ".concat(entyClass.getSimpleName()).concat(" where id=?");
        PreparedStatement ps;
        ps = conexao.prepareStatement(sql);
        ps.setInt(1, absModel.getId());
        this.rs = ps.executeQuery();
        this.rs.next();

        for (Method method : entyClass.getDeclaredMethods()) {

            if (method.isAnnotationPresent(ColunaBD.class)) {
                ColunaBD anotacao = method.getAnnotation(ColunaBD.class);
                method.invoke(absModel, rs.getString(anotacao.nome()));
            }
        }
        return absModel;
    }

    public boolean excluir(T absModel) throws SQLException {
        String sql = "delete from ".concat(absModel.getClass().getSimpleName()).concat(" where id=?");

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, absModel.getId());
        ps.execute();
        return true;
    }

    public void avancaItem() {
        try {
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void voltaItem() {
        try {
            rs.previous();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void primeiroItem() {
        try {
            rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ultimoItem() {
        try {
            rs.last();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int retornaIndiceRs() {
        try {
            return rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void setItemRs(int indice) {
        try {
            rs.absolute(indice);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isFirst() {
        try {
            if (rs.isFirst()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isLast() {
        try {
            if (rs.isLast()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
