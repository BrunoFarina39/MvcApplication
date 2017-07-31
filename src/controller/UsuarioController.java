/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.TelaPrincipal;
import br.dao.UsuarioDao;
import connection.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TableModel;
import model.Usuario;
import view.UsuarioView;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController implements ActionListener, MouseListener {

    private UsuarioView usuarioView;
    private Usuario usuario;
    private UsuarioDao usuarioDao;

    public UsuarioController(UsuarioView usuarioView) {
        this.usuarioView = usuarioView;
        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            salvar();
        } else if (e.getActionCommand().equals("Editar")) {
            editar();
        } else if (e.getActionCommand().equals("Excluir")) {
            excluir();
        }
    }

    @Override
    public void salvar() {
        usuario.setLogin(usuarioView.getLogin());
        usuario.setSenha(usuarioView.getSenha());
        usuario.setNome(usuarioView.getNome());
        JOptionPane.showMessageDialog(usuarioView, usuarioDao.salvar(usuario));
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));
    }

    @Override
    public void editar() {
        usuario.setId(Integer.parseInt(usuarioView.getId()));
        usuario.setLogin(usuarioView.getLogin());
        usuario.setSenha(usuarioView.getSenha());
        usuario.setNome(usuarioView.getNome());
        JOptionPane.showMessageDialog(usuarioView, usuarioDao.editar(usuario));
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));
    }

    @Override
    public void excluir() {
        usuario.setId(Integer.parseInt(usuarioView.getId()));
        JOptionPane.showMessageDialog(usuarioView, usuarioDao.excluir(usuario));
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));

    }

    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "ola");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
