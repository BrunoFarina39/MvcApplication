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
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    public static UsuarioView tela;
    private CustomJTextField jtCodigo, jtNome, jtLogin;
    private CustomJPasswordField jtSenha;

    public static UsuarioView getTela() {
        if (tela != null && tela.isClosed()) {
            TelaPrincipal.jDesktopPane.remove(tela);
            tela = null;
        }
        if (tela == null) {
            tela = new UsuarioView();
            TelaPrincipal.jDesktopPane.add(tela);
        }
        TelaPrincipal.jDesktopPane.moveToFront(tela);
        TelaPrincipal.jDesktopPane.selectFrame(true);
        return tela;
    }

    public UsuarioView() {
        super("Cadastro de Usuário", true, true, true, true);
        super.setSize(650, 300);
        this.jtCodigo = new CustomJTextField(10, true, "ID", "Código");
        this.jtNome = new CustomJTextField(20, true, "Nome", "Nome");
        this.jtLogin = new CustomJTextField(20, false, "Login", "Login");
        this.jtSenha = new CustomJPasswordField(20, false, "Senha", "Senha");
        this.jlTitulo.setText("Manutenção de Usuário");

        super.adicionaArrayComponente((CustomComponent) jtCodigo);
        super.adicionaArrayComponente((CustomComponent) jtNome);
        super.adicionaArrayComponente((CustomComponent) jtLogin);
        super.adicionaArrayComponente((CustomComponent) jtSenha);

    }

    public void AddAction(ActionListener actionListener) {
        jbNovo.addActionListener(actionListener);
        jbSalvar.addActionListener(actionListener);
        jbEditar.addActionListener(actionListener);
        jbExcluir.addActionListener(actionListener);
        jbCancelar.addActionListener(actionListener);
        jbPesquisar.addActionListener(actionListener);
    }

    //inicio do posicionamento
    //int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente
    public void render() {
        super.adicionaComponente(0, 0, 1, 1, 5, 5, jtCodigo);
        super.adicionaComponente(0, 1, 2, 1, 5, 5, jtNome);
        super.adicionaComponente(0, 2, 2, 1, 5, 5, jtLogin);
        super.adicionaComponente(0, 3, 2, 1, 5, 5, jtSenha);
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

    public void preencheCampos(Usuario usuario) {
        jtCodigo.setTexto(String.valueOf(usuario.getId()));
        jtLogin.setTexto(usuario.getLogin());
        jtSenha.setTexto(usuario.getSenha());
        jtNome.setTexto(usuario.getNome());
    }

}
