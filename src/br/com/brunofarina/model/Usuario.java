/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.model;

import br.com.brunofarina.annotations.Coluna;
import br.com.brunofarina.annotations.ColunaBD;
import br.com.brunofarina.connection.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

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
    public int getId() {
        return super.getId();
    }

    @Coluna(posicao = 1, nome = "Login")
    public String getLogin() {
        return login;
    }

    @ColunaBD(nome = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    @ColunaBD(nome = "senha")
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Coluna(posicao = 2, nome = "Nome")
    public String getNome() {
        return nome;
    }

    @ColunaBD(nome = "nome")
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

    @Override
    public String toString() {
        return "usuario";
    }
}
