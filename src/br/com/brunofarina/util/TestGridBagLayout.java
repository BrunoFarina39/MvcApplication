package br.com.brunofarina.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestGridBagLayout {

    public static void insereComponente(int linhas, int colunas, JPanel painelCadastro) {
        JLabel jl;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i <= linhas; i++) {
            jl = new JLabel("" + i);
            jl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jl.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            gbc.gridy = i;
            gbc.gridx = colunas + 1;
            painelCadastro.add(jl, gbc);
        }

        for (int i = 0; i <= colunas; i++) {
            jl = new JLabel("" + i);
            jl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jl.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            gbc.gridy = linhas + 1;
            gbc.gridx = i;
            painelCadastro.add(jl, gbc);
        }
    }
}
