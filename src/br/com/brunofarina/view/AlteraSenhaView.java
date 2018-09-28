/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.CustomJPasswordField;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bruno
 */
public class AlteraSenhaView extends JInternalFrame {

    private JPanel panel, panelCentral;
    private JLabel jlTitulo;
    private CustomJPasswordField jtSenhaAtual, jtSenha, jtConfSenha;

    public AlteraSenhaView() {
        setSize(300, 300);
        setVisible(true);
        TelaPrincipal.jDesktopPane.add(this);
        TelaPrincipal.jDesktopPane.moveToFront(this);
        this.panel = new JPanel();
        this.panelCentral = new JPanel();
        this.jlTitulo = new JLabel("Manutenção de senha");
        this.jtSenhaAtual = new CustomJPasswordField(20, false, "SenhaAtual", "Senha Atual:");
        this.jtSenha = new CustomJPasswordField(20, false, "Senha", "Senha");
        this.jtSenhaAtual = new CustomJPasswordField(20, false, "Senha", "Senha");
        this.panel.setLayout(new GridBagLayout());
        this.panel.add(jlTitulo);
        this.getContentPane().add(this.panel);
    }
}
