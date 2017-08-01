/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import component.CustomJPasswordField;
import component.CustomJTextField;
import controller.UsuarioController;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import model.TableModel;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    private JLabel jlCodigo, jlNome, jlLogin, jlSenha;
    private CustomJTextField jtCodigo, jtNome, jtLogin;
    private CustomJPasswordField jtSenha;

    public UsuarioView() {
        super("Cadastro de Usuário", true, true, true, true);
        setVisible(true);
        setSize(650, 500);
        this.jlCodigo = new JLabel("Código:");
        this.jlNome = new JLabel("Nome:");
        this.jlLogin = new JLabel("Login:");
        this.jlSenha = new JLabel("Senha:");
        this.jtCodigo = new CustomJTextField(10, false, true, "Código");
        this.jtNome = new CustomJTextField(20, true, true, "Nome");
        this.jtLogin = new CustomJTextField(20, true, false, "Login");
        this.jtSenha = new CustomJPasswordField(20, true, false, "Senha");
        this.jlTitulo.setText("Manutenção de Usuário");
        //inicio do posicionamento
        //int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente
        adicionaComponente(0, 0, 1, 1, 5, 5, jtCodigo);
        adicionaComponente(0, 1, 2, 1, 5, 5, jtNome);
        adicionaComponente(0, 2, 2, 1, 5, 5, jtLogin);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jlCodigo, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jtCodigo, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jlNome, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jtNome, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jlLogin, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jtLogin, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jlSenha, gbc);
//
//        gbc.insets = new Insets(5, 0, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        panelCampos.add(jtSenha, gbc);
//        jlTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        UsuarioController usuarioController = new UsuarioController(this);
        jbNovo.addActionListener(usuarioController);
        jbSalvar.addActionListener(usuarioController);
        jbEditar.addActionListener(usuarioController);
        jbExcluir.addActionListener(usuarioController);
        jbCancelar.addActionListener(usuarioController);
        jTable.addMouseListener(usuarioController);
        habilitaCampos(false);
    }

    public void povoaJtable(TableModel tb) {
        jTable.setModel(tb);
        jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable.setFillsViewportHeight(true);
    }

    public String getId() {
        return jtCodigo.getValor();
    }

    public String getLogin() {
        return jtLogin.getValor();
    }

    public String getSenha() {
        return jtSenha.getValor();
    }

    public String getNome() {
        return jtNome.getValor();
    }

    public void preencheCampos() {
        int linha = jTable.getSelectedRow();
        jtCodigo.setTexto(jTable.getValueAt(linha, 0).toString());
        jtLogin.setTexto(jTable.getValueAt(linha, 1).toString());
        jtNome.setTexto(jTable.getValueAt(linha, 2).toString());
    }
}
