/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.application;

import br.com.brunofarina.model.Usuario;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Bruno Farina
 */
public class TelaLogin implements ActionListener {

    JFrame jFrame;
    JLabel jlLogin, jlSenha;
    public JTextField jtLogin;
    public JPasswordField jtSenha;
    JPanel jpBotoes;
    JButton jbLogin;
    JButton jbCancelar;

    public TelaLogin() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        jFrame = new JFrame("Autenticação no Sistema");
        jlLogin = new JLabel("Login:");
        jlSenha = new JLabel("Senha:");
        jtLogin = new JTextField(20);
        jtSenha = new JPasswordField(20);
        jpBotoes = new JPanel();
        jbLogin = new JButton("Login");
        jbCancelar = new JButton("Cancelar");

        Container container = jFrame.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        container.add(jlLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        container.add(jlSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        container.add(jtLogin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        container.add(jtSenha, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        container.add(jlSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        container.add(jpBotoes, gbc);
        jpBotoes.setLayout(new FlowLayout());
        jpBotoes.add(jbLogin);
        jpBotoes.add(jbCancelar);
        jFrame.setSize(400, 200);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jbLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {;
        if (e.getActionCommand().equals("Login")) {
            Usuario usuario = new Usuario();
            usuario.setLogin(jtLogin.getText());
            usuario.setSenha(String.valueOf(jtSenha.getPassword()));
            if (usuario.autentica()) {
                jFrame.dispose();
                new TelaPrincipal().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário/Senha incorreto(s)", "Autenticação no Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
