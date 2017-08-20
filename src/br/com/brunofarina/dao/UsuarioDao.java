/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.brunofarina.model.Usuario;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class UsuarioDao extends AbstractDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public boolean salvar(Usuario usuario) {
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
        }
    }

    public boolean editar(Usuario usuario) {
        String sql = "update usuario set login=?, nome=?, senha=? where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Usuario> listarUsuario() {
        tamanhoRs=0;
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
                tamanhoRs++;
            }
            return usuarios;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuario(String chave) {
        tamanhoRs=0;
        String sql = "select id,login,nome,senha from usuario where nome ilike ? limit 30";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                tamanhoRs++;
            }
            return usuarios;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Usuario> listarUsuario(int id) {
        tamanhoRs=0;
        String sql = "select id,login,nome,senha from usuario where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ArrayList<Usuario> usuarios = new ArrayList();
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
                tamanhoRs++;
            }
            return usuarios;
        } catch (SQLException e) {
            return null;
        }
    }

    public Usuario retornaUsuario() {
        Usuario usuario = new Usuario();
        try {
            usuario.setId(rs.getInt("id"));
            usuario.setLogin(rs.getString("login"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public int retornaTamanhoRs(){
        return tamanhoRs;
    }
}
