/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import javax.swing.JInternalFrame;

/**
 *
 * @author Bruno
 */
public class AbstractViewPesquisa extends JInternalFrame {

    public void AbstractViewPesquisa() {
        this.setVisible(true);
        this.setSize(300, 300);
        pack();
        TelaPrincipal.jDesktopPane.add(this);
        System.out.println("br.com.brunofarina.view.AbstractViewPesquisa.AbstractViewPesquisa()");
    }
}
