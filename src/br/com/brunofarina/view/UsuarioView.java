/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.CustomComponent;
import br.com.brunofarina.component.CustomJPasswordField;
import br.com.brunofarina.component.CustomJTextField;
import br.com.brunofarina.model.Usuario;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JViewport;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    public static UsuarioView tela;
    private CustomJTextField jtCodigo, jtNome, jtLogin;
    private CustomJPasswordField jtSenha, jtConfSenha;
    private ActionListener actionListener;

    public static UsuarioView getTela(ActionListener actionListener) {
        if (tela != null && tela.isClosed()) {
            TelaPrincipal.jDesktopPane.remove(tela);
            tela = null;
        }
        if (tela == null) {
            tela = new UsuarioView(actionListener);
            TelaPrincipal.jDesktopPane.add(tela);
        }
        TelaPrincipal.jDesktopPane.moveToFront(tela);
        TelaPrincipal.jDesktopPane.selectFrame(true);
        return tela;
    }

    public UsuarioView(ActionListener actionListener) {
        super("Cadastro de Usuário", true, true, true, true);
        super.setSize(650, 300);
        this.jtCodigo = new CustomJTextField(10, true, "ID", "Código");
        this.jtNome = new CustomJTextField(20, true, "Nome", "Nome");
        this.jtLogin = new CustomJTextField(20, false, "Login", "Login");
        this.jtSenha = new CustomJPasswordField(20, false, "Senha", "Senha");
        this.jtConfSenha = new CustomJPasswordField(20, false, "ConfSenha", "Conf.Senha");
        this.jlTitulo.setText("Manutenção de Usuário");

        super.adicionaArrayComponente((CustomComponent) jtCodigo);
        super.adicionaArrayComponente((CustomComponent) jtNome);
        super.adicionaArrayComponente((CustomComponent) jtLogin);
        super.adicionaArrayComponente((CustomComponent) jtSenha);
        super.adicionaArrayComponente((CustomComponent) jtConfSenha);
        jbNovo.addActionListener(actionListener);
        jbSalvar.addActionListener(actionListener);
        jbEditar.addActionListener(actionListener);
        jbExcluir.addActionListener(actionListener);
        jbCancelar.addActionListener(actionListener);
        jbPesquisar.addActionListener(actionListener);
        this.actionListener = actionListener;

    }

    //inicio do posicionamento
    //int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente
    public void render() {
        super.adicionaComponente(0, 0, 1, 1, 5, 5, jtCodigo);
        super.adicionaComponente(0, 1, 2, 1, 5, 5, jtNome);
        super.adicionaComponente(0, 2, 2, 1, 5, 5, jtLogin);
        super.adicionaComponente(0, 3, 2, 1, 5, 5, jtSenha);
        super.adicionaComponente(0, 4, 2, 1, 5, 5, jtConfSenha);
        statusInicial();
    }

    public CustomJTextField getJtCodigo() {
        return jtCodigo;
    }

    public CustomJTextField getJtNome() {
        return jtNome;
    }

    public CustomJTextField getJtLogin() {
        return jtLogin;
    }

    public CustomJPasswordField getJtSenha() {
        return jtSenha;
    }

    public CustomJPasswordField getJtConfSenha() {
        return jtConfSenha;
    }

    public void preencheCampos(Usuario usuario) {
        jtCodigo.setValor(String.valueOf(usuario.getId()));
        jtLogin.setValor(usuario.getLogin());
        jtNome.setValor(usuario.getNome());
    }

    public void statusEditando() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        JButton jbSenha = new JButton("Alterar Senha");
        jbSenha.addActionListener(actionListener);
        this.panelCentral.add(jbSenha, gbc);
        paintComponents(this.getGraphics());
    }
}
