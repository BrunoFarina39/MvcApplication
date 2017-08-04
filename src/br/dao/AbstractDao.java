/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import annotations.ColunaBD;
import connection.Database;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AbstractModel;

/**
 *
 * @author Bruno
 */
public abstract class AbstractDao<T extends AbstractModel> {

    protected Connection conexao;
    private ResultSet rs;
    private Class<T> entyClass;

    public AbstractDao(Class<T> entityClass) {
        this.conexao = Database.getConnection();
        this.entyClass = entityClass;
    }

    public T buscaPorId(T absModel) {
        String sql = "select * from " + entyClass.getSimpleName() + " where id=" + absModel.getId();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(sql);
            this.rs = ps.executeQuery();
            this.rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (Method method : entyClass.getDeclaredMethods()) {

                if (method.isAnnotationPresent(ColunaBD.class)) {
                    ColunaBD anotacao = method.getAnnotation(ColunaBD.class);
                    method.invoke(absModel, rs.getString(anotacao.nome()));
                }
            }
            return absModel;
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | SQLException e) {
            return absModel;
        }
    }

    public boolean excluir(T absModel) {
        String sql = "delete from usuario where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, absModel.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }
}
