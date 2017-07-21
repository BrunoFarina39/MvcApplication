/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.TelaPrincipal;
import connection.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TableModel;
import view.UsuarioView;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController implements ActionListener {

    UsuarioView usuarioView;

    public UsuarioController(TelaPrincipal telaPrincipal) {
        this.usuarioView = new UsuarioView();
        telaPrincipal.jDesktopPane.add(this.usuarioView);
        usuarioView.adicionaOuvinte(this);
        Connection c = Database.getConnection();
        try {
            PreparedStatement ps = c.prepareStatement("select * from usuario", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            usuarioView.jTable.setModel(new TableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.usuarioView.jbNovo) {
            JOptionPane.showMessageDialog(null, "voce clicou em novo");
        }
        if (e.getSource() == this.usuarioView.jbEditar) {
            JOptionPane.showMessageDialog(null, "Voce clicou em editar");
        }
    }

    @Override
    public void salvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
