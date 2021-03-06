/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.CustomComponent;
import br.com.brunofarina.component.CustomJPasswordField;
import br.com.brunofarina.component.ExceptionPassword;
import br.com.brunofarina.component.Filter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Bruno
 */
public class AlteraSenhaView extends JInternalFrame {

    private JPanel panel, panelNorte, panelCentral, panelSul;
    private JLabel jlTitulo;
    private CustomJPasswordField jtSenhaAtual, jtSenha, jtConfSenha;
    private JButton jbSalvar;
    private ArrayList<CustomComponent> campos;

    public AlteraSenhaView(ActionListener actionListener) {
        super("Manutenção de Senha", true, true, true, true);
        setSize(350, 200);
        setVisible(true);
        TelaPrincipal.jDesktopPane.add(this);
        TelaPrincipal.jDesktopPane.moveToFront(this);
        this.panel = new JPanel();
        this.panelNorte = new JPanel();
        this.panelCentral = new JPanel();
        this.panelSul = new JPanel();
        this.jlTitulo = new JLabel("Manutenção de senha");
        this.jtSenhaAtual = new CustomJPasswordField(20, false, "SenhaAtual", "Senha Atual");
        this.jtSenha = new CustomJPasswordField(20, false, "Senha", "Senha");
        this.jtConfSenha = new CustomJPasswordField(20, false, "ConfSenha", "Conf. Senha");
        this.jbSalvar = new JButton("Salvar");
        this.jbSalvar.addActionListener(actionListener);
        this.panel.setLayout(new BorderLayout());
        this.panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.panelNorte.add(jlTitulo);
        this.campos = new ArrayList<>();
        this.campos.add(jtSenhaAtual);
        this.campos.add(jtSenha);
        this.campos.add(jtConfSenha);
        this.panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.panelCentral.add(new JLabel("Senha Atual:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.panelCentral.add(jtSenhaAtual, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.panelCentral.add(new JLabel("Nova Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.panelCentral.add(jtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.panelCentral.add(new JLabel("Conf. Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.panelCentral.add(jtConfSenha, gbc);

        this.panelSul.setLayout(new FlowLayout());
        this.panelSul.add(jbSalvar);

        this.panel.add(panelNorte, BorderLayout.NORTH);
        this.panel.add(panelCentral, BorderLayout.CENTER);
        this.panel.add(panelSul, BorderLayout.SOUTH);
        this.getContentPane().add(this.panel);
    }

    public String getSenhaAtual() {
        return jtSenhaAtual.getValor();
    }

    public String getNovaSenha() {
        return jtSenha.getValor();
    }

    public String getConfSenha() {
        return jtConfSenha.getValor();
    }

    public boolean valida() {
        StringBuilder obr = new StringBuilder();
        boolean retorno = true;
        for (int i = 0; i < this.campos.size(); i++) {
            if (this.campos.get(i).getObrigatorio() && campos.get(i).getVazio()) {
                obr.append(this.campos.get(i).getRotulo()).append("\n");
                retorno = false;
            }
        }
        if (obr.length() > 0) {
            JOptionPane.showMessageDialog(null, "Campo(s) obrigatório(s):\n" + obr.toString());
        }
        return retorno;
    }

    public ArrayList<Filter> getCamposFilter() {
        ArrayList<Filter> filter = new ArrayList<>();
        for (int i = 0; i < this.campos.size(); i++) {
            filter.add(this.campos.get(i));
        }
        return filter;
    }

    public void statusManutencao(Object status) {
        if (status instanceof ExceptionPassword) {
            JOptionPane.showMessageDialog(null, ((ExceptionPassword) status).getMessage());
        } else {
            if ((boolean) status) {
                JOptionPane.showMessageDialog(null, "Sua senha foi altarada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel alterar sua senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
