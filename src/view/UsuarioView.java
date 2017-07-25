/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    private JLabel jlCodigo, jlNome, jlLogin, jlSenha;
    private JTextField jtCodigo, jtNome, jtLogin;
    private JPasswordField jtSenha;

    public UsuarioView() {
        super("Cadastro de Usuário", true, true, true, true);
        setVisible(true);
        setSize(650, 500);
        this.jlCodigo = new JLabel("Código:");
        this.jlNome = new JLabel("Nome:");
        this.jlLogin = new JLabel("Login:");
        this.jlSenha = new JLabel("Senha:");
        this.jtCodigo = new JTextField(10);
        this.jtNome = new JTextField(20);
        this.jtLogin = new JTextField(20);
        this.jtSenha = new JPasswordField(20);
        this.jlTitulo.setText("Manutenção de Usuário");
        //inicio do posicionamento
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jlCodigo, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jtCodigo, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jlNome, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jtNome, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jlLogin, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jtLogin, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jlSenha, gbc);

        gbc.insets = new Insets(5, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panelCampos.add(jtSenha, gbc);
        jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(200);
    }

    public void adicionaOuvinte(ActionListener actionListener) {
        jbNovo.addActionListener(actionListener);
        jbEditar.addActionListener(actionListener);
    }
}
