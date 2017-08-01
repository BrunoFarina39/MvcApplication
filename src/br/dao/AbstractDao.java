/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import connection.Database;
import java.sql.Connection;

/**
 *
 * @author Bruno
 */
public abstract class AbstractDao {

    protected Connection conexao;

    public AbstractDao() {
        this.conexao = Database.getConnection();
    }
}
