/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import br.com.brunofarina.component.ExceptionPassword;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.brunofarina.model.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Bruno
 */
public class UsuarioDao extends AbstractDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public Object salvar(Usuario usuario) {
        String sql = "insert into usuario (login,nome,senha) values(?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        } catch (ExceptionPassword ex) {
            return ex;
        }
    }

    public boolean editar(Usuario usuario) {
        String sql = "update usuario set login=?, nome=? where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getNome());
            ps.setInt(3, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public Object editarSenha(Usuario usuario) {
        String sql = "update usuario set senha=? where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setInt(2, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        } catch (ExceptionPassword ex) {
            return ex;
        }
    }

    public ArrayList<Usuario> listarUsuario() {
        try {
            rs = super.listar("select id,login,nome,senha from usuario order by nome limit 30");
            ArrayList<Usuario> usuarios = new ArrayList();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuario(String chave) {
        String sql = "select id,login,nome,senha from usuario where nome ilike ? limit 30";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, "%".concat(chave).concat("%"));
            ArrayList<Usuario> usuarios = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuario(int id) {
        String sql = "select id,login,nome,senha from usuario where id=?";
        ArrayList<Usuario> usuarios = new ArrayList();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            return null;
        }
    }
}
