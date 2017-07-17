/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends JInternalFrame {

    private JButton jb;

    public UsuarioView() {
        super(
                "cadastro de Cliente",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        setVisible(true);

        setSize(300, 300);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);

        Container container = getContentPane();
        container.add(panel);

        this.jb = new JButton("botão");
        panel.add(jb);
    }

    public void adicionaOuvinte(ActionListener actionListener) {
        jb.addActionListener(actionListener);
    }
}
