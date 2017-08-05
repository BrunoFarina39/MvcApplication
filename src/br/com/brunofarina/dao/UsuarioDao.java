/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.brunofarina.model.Usuario;

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

    public ArrayList<Usuario> listar() {
        try {
            ResultSet rs = super.listar("select id,login,nome from usuario limit 30");
            ArrayList<Usuario> usuarios = new ArrayList();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            return null;
        }
    }
}
