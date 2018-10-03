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
import br.com.brunofarina.component.ExceptionPassword;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Farina
 */
public class Usuario extends AbstractModel {

    private String login;
    private String senha;
    private String confSenha;
    private String senhaAtual;
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
    public String getSenha() throws ExceptionPassword {
        if (this.senha.equals(this.confSenha)) {
            String sql = "select senha from usuario where id=?";
            try {
                PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                if (rs.getString("senha").equals(senhaAtual)) {
                    return senha;
                } else {
                    throw new ExceptionPassword();
                }
            } catch (SQLException ex) {
                return null;
            }
        } else {
            throw new ExceptionPassword();
        }
    }

    public void setSenha(String senha) {
        senha = encriptar(senha);
        this.senha = senha;
    }

    @CampoObr
    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        confSenha = encriptar(confSenha);
        this.confSenha = confSenha;
    }

    @CampoObr
    public String getSenhaAtual() {
        return this.senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = encriptar(senhaAtual);
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

    public String encriptar(String password) {
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte digestMessage[] = algoritmo.digest(password.getBytes("UTF-8"));
            StringBuilder hexPassword = new StringBuilder();
            for (byte aByte : digestMessage) {
                hexPassword.append(String.format("%02X", 0xFF & aByte));
            }
            return hexPassword.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "usuario";
    }
}
