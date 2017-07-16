/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Bruno
 */
public class Cliente extends JInternalFrame {

    public Cliente() {
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
    }
}
