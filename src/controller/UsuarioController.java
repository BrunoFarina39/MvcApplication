/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, e.getActionCommand());
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
