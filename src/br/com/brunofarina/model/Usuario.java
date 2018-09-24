/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.model;

import br.com.brunofarina.annotations.Coluna;
import br.com.brunofarina.component.Filter;
import br.com.brunofarina.connection.Database;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import br.com.brunofarina.annotations.CampoObr;

/**
 *
 * @author Bruno Farina
 */
public class Usuario extends AbstractModel {

    private String login;
    private String senha;
    private String nome;
    private Date ultimoAcesso;

    public Usuario() {
    }

    @Coluna(posicao = 0, nome = "Código")
    @Override
    public int getId() {
        return super.getId();
    }

    @Coluna(posicao = 1, nome = "Login")
    @CampoObr
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @CampoObr
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Coluna(posicao = 2, nome = "Nome")
    @CampoObr
    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public boolean autentica() {
        String sql = "select * from usuario where login=? and senha=?";
        try {
            PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    public void setInputFilter(ArrayList<Filter> c) {
        for (Method method : Usuario.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CampoObr.class)) {
                for (int i = 0; i < c.size(); i++) {
                    if (c.get(i).getNome().equalsIgnoreCase(method.getName().replace("get", ""))) {
                        c.get(i).setObrigatorio(true);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "usuario";
    }
}
