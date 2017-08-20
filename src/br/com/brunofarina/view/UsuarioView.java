/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.component.CustomJPasswordField;
import br.com.brunofarina.component.CustomJTextField;
import br.com.brunofarina.controller.UsuarioController;
import javax.swing.JLabel;
import br.com.brunofarina.model.TableModel;
import br.com.brunofarina.model.Usuario;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author Bruno
 */
public class UsuarioView extends AbstractView {

    private JLabel jlCodigo, jlNome, jlLogin, jlSenha;
    private CustomJTextField jtCodigo, jtNome, jtLogin;
    private CustomJPasswordField jtSenha;
    private JRadioButton JRadioId, JRadioNome;

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
        this.JRadioId = new JRadioButton("Código");
        this.JRadioNome = new JRadioButton("Nome");
        this.JRadioNome.setSelected(true);
        //inicio do posicionamento
        //int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente
        this.panelChavePesq.add(JRadioId);
        this.panelChavePesq.add(JRadioNome);
        this.btGroup.add(JRadioId);
        this.btGroup.add(JRadioNome);
        super.adicionaComponente(0, 0, 1, 1, 5, 5, jtCodigo);
        super.adicionaComponente(0, 1, 2, 1, 5, 5, jtNome);
        super.adicionaComponente(0, 2, 2, 1, 5, 5, jtLogin);
        super.adicionaComponente(0, 3, 2, 1, 5, 5, jtSenha);
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
        //jTable.setModel(usuarioController.preencherTabela());
        jbNovo.addActionListener(usuarioController);
        jbSalvar.addActionListener(usuarioController);
        jbEditar.addActionListener(usuarioController);
        jbExcluir.addActionListener(usuarioController);
        jbCancelar.addActionListener(usuarioController);
        jbPesquisar.addActionListener(usuarioController);
        jbProximo.addActionListener(usuarioController);
        jbAnterior.addActionListener(usuarioController);
        jbUltimo.addActionListener(usuarioController);
        jbInicio.addActionListener(usuarioController);
        jTable.addMouseListener(usuarioController);
    }

    @Override
    public void povoaJtable(TableModel tb) {
        super.povoaJtable(tb);
        jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(300);
    }

    public String getId() {
        return jtCodigo.getValor();
    }

    public void setId(int id) {
        jtCodigo.setTexto(String.valueOf(id));
    }

    public String getLogin() {
        return jtLogin.getValor();
    }

    public void setLogin(String login) {
        jtLogin.setTexto(login);
    }

    public String getSenha() {
        return jtSenha.getValor();
    }

    public void setSenha(String senha) {
        jtSenha.setTexto(senha);
    }

    public String getNome() {
        return jtNome.getValor();
    }

    public void setNome(String nome) {
        jtNome.setTexto(nome);
    }

    public boolean getSelectedRbId() {
        return JRadioId.isSelected();
    }

    public boolean getSelectedRbNome() {
        return JRadioNome.isSelected();
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
