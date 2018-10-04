/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.CustomJTextField;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

/**
 *
 * @author Bruno
 */
public class ClienteView extends JInternalFrame {

    public static ClienteView tela;
    private CustomJTextField jtCodigo, jtNome, jtRgIe, jtCpfCnpj, jtEndereco, jtNumero, jtBairro, jtCep, jtCidade, jtObs;
    private ActionListener actionListener;

    public static ClienteView getTela(ActionListener actionListener) {
        if (tela != null && tela.isClosed()) {
            TelaPrincipal.jDesktopPane.remove(tela);
            tela = null;
        }
        if (tela == null) {
            tela = new ClienteView(actionListener);
            TelaPrincipal.jDesktopPane.add(tela);
        }
        TelaPrincipal.jDesktopPane.moveToFront(tela);
        TelaPrincipal.jDesktopPane.selectFrame(true);
        return tela;
    }

    public ClienteView(ActionListener actionListener) {
        super("Cadastro de Cliente", true, true, true, true);
        super.setSize(650, 300);
    }

}
