/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import annotations.Coluna;
import annotations.ColunaBD;
import connection.Database;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AbstractModel;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public abstract class AbstractDao {

    protected Connection conexao;
    ResultSet rs;

    public AbstractDao() {
        this.conexao = Database.getConnection();
    }

    public Object busca(AbstractModel obj) {

        String sql = "select * from usuario where id=" + obj.getId();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(sql);
            this.rs = ps.executeQuery();
            this.rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Class<?> classe = obj.getClass();
            obj = (Usuario) obj;
            System.out.println(classe.getClass());
            for (Method method : classe.getDeclaredMethods()) {

                if (method.isAnnotationPresent(ColunaBD.class)) {
                    ColunaBD anotacao = method.getAnnotation(ColunaBD.class);
                    method.invoke(obj, rs.getString(anotacao.nome()));
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
