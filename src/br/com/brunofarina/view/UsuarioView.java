/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.component.CustomComponent;
import br.com.brunofarina.component.CustomJPasswordField;
import br.com.brunofarina.component.CustomJTextField;
import br.com.brunofarina.component.Filter;
import javax.swing.JLabel;
import br.com.brunofarina.model.TableModel;
import br.com.brunofarina.model.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    private JLabel jlCodigo, jlNome, jlLogin, jlSenha;
    private CustomJTextField jtCodigo, jtNome, jtLogin;
    private CustomJPasswordField jtSenha;
    private JRadioButton JRadioId, JRadioNome;

    public UsuarioView(ActionListener actionListener, MouseListener mouseListener) {
        super("Cadastro de Usuário", true, true, true, true);
        setVisible(true);
        setSize(650, 500);
        this.jlCodigo = new JLabel("Código:");
        this.jlNome = new JLabel("Nome:");
        this.jlLogin = new JLabel("Login:");
        this.jlSenha = new JLabel("Senha:");
        this.jtCodigo = new CustomJTextField(10, true, "Código");
        this.jtNome = new CustomJTextField(20, true, "Nome");
        this.jtLogin = new CustomJTextField(20, false, "Login");
        this.jtSenha = new CustomJPasswordField(20, false, "Senha");
        this.jlTitulo.setText("Manutenção de Usuário");
        this.JRadioId = new JRadioButton("Código");
        this.JRadioNome = new JRadioButton("Nome");
        this.JRadioNome.setSelected(true);
        //inicio do posicionamento
        //int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente
        this.panelChavePesq.add(JRadioId);
        this.panelChavePesq.add(JRadioNome);
        this.btGroup.add(JRadioId);
        this.btGroup.add(JRadioNome);

        super.adicionaArray((CustomComponent) jtCodigo);
        super.adicionaArray((CustomComponent) jtNome);
        super.adicionaArray((CustomComponent) jtLogin);
        super.adicionaArray((CustomComponent) jtSenha);

        jbNovo.addActionListener(actionListener);
        jbSalvar.addActionListener(actionListener);
        jbEditar.addActionListener(actionListener);
        jbExcluir.addActionListener(actionListener);
        jbCancelar.addActionListener(actionListener);
        jbPesquisar.addActionListener(actionListener);
        jbProximo.addActionListener(actionListener);
        jbAnterior.addActionListener(actionListener);
        jbUltimo.addActionListener(actionListener);
        jbInicio.addActionListener(actionListener);
        jTable.addMouseListener(mouseListener);
    }

    public void render() {
        super.adicionaComponente(0, 0, 1, 1, 5, 5, jtCodigo);
        super.adicionaComponente(0, 1, 2, 1, 5, 5, jtNome);
        super.adicionaComponente(0, 2, 2, 1, 5, 5, jtLogin);
        super.adicionaComponente(0, 3, 2, 1, 5, 5, jtSenha);
    }

    @Override
    public void povoaJtable(TableModel tb) {
        super.povoaJtable(tb);
        jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(300);
    }

    public CustomJTextField getJtCodigo() {
        return jtCodigo;
    }

    public void setJtCodigo(CustomJTextField jtCodigo) {
        this.jtCodigo = jtCodigo;
    }

    public CustomJTextField getJtNome() {
        return jtNome;
    }

    public void setJtNome(CustomJTextField jtNome) {
        this.jtNome = jtNome;
    }

    public CustomJTextField getJtLogin() {
        return jtLogin;
    }

    public void setJtLogin(CustomJTextField jtLogin) {
        this.jtLogin = jtLogin;
    }

    public CustomJPasswordField getJtSenha() {
        return jtSenha;
    }

    public void setJtSenha(CustomJPasswordField jtSenha) {
        this.jtSenha = jtSenha;
    }

    public JRadioButton getJRadioId() {
        return JRadioId;
    }

    public void setJRadioId(JRadioButton JRadioId) {
        this.JRadioId = JRadioId;
    }

    public JRadioButton getJRadioNome() {
        return JRadioNome;
    }

    public void setJRadioNome(JRadioButton JRadioNome) {
        this.JRadioNome = JRadioNome;
    }

    @Override
    public Object getPesquisa() {
        if (JRadioId.isSelected()) {
            try {
                return Integer.parseInt(jtPesquisar.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor digite apenas números");
                return null;
            }
        } else {
            return jtPesquisar.getText();
        }
    }

    public void preencheCampos(Usuario usuario) {
        jtCodigo.setTexto(String.valueOf(usuario.getId()));
        jtLogin.setTexto(usuario.getLogin());
        jtSenha.setTexto(usuario.getSenha());
        jtNome.setTexto(usuario.getNome());

    }

}
