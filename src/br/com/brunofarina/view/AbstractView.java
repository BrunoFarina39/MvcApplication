/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.component.CustomComponent;
import br.com.brunofarina.component.Filter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Bruno
 */
public class AbstractView extends JInternalFrame {

    protected JPanel panel, panelNorte, panelCentral, panelSul, panelScroll;
    protected JLabel jlTitulo;
    protected JButton jbNovo, jbSalvar, jbEditar, jbExcluir, jbCancelar, jbPesquisar;
    protected JTextField jtPesquisar;
    private StringBuffer rotulo;
    protected ArrayList<CustomComponent> campos;

    public AbstractView(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable) {
        super(title, resizable, closable, maximizable, iconable);
        this.panel = new JPanel();
        this.panelNorte = new JPanel();
        this.panelCentral = new JPanel();
        this.panelSul = new JPanel();
        this.panelScroll = new JPanel();
        this.jbNovo = new JButton("Novo");
        this.jbSalvar = new JButton("Salvar");
        this.jbEditar = new JButton("Editar");
        this.jbExcluir = new JButton("Excluir");
        this.jbCancelar = new JButton("Cancelar");
        this.jbPesquisar = new JButton("Pesquisar");
        this.jlTitulo = new JLabel("Manutenção de...");
        this.jtPesquisar = new JTextField(15);

        rotulo = new StringBuffer();
        campos = new ArrayList<>();
        Container container = getContentPane();
        container.add(panel);
        setVisible(true);
        pack();
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNorte.add(jlTitulo);
        this.panel.setLayout(new BorderLayout());
        panel.add(panelNorte, BorderLayout.NORTH);
        panel.add(panelCentral, BorderLayout.CENTER);
        panel.add(panelSul, BorderLayout.SOUTH);

        GridBagLayout layoutCampos = new GridBagLayout();
        this.panelCentral.setLayout(layoutCampos);
        this.panelSul.setLayout(new FlowLayout());
        this.panelSul.add(this.jbNovo);
        this.panelSul.add(this.jbSalvar);
        this.panelSul.add(this.jbEditar);
        this.panelSul.add(this.jbExcluir);
        this.panelSul.add(this.jbCancelar);
        this.panelSul.add(this.jbPesquisar);
    }

    protected void adicionaComponente(int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente) {
        this.rotulo.delete(0, rotulo.length());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = linha;
        gbc.gridy = coluna;

        if (componente instanceof CustomComponent) {

            this.rotulo.append(((CustomComponent) componente).getRotulo());
            if (((CustomComponent) componente).getObrigatorio()) {
                this.rotulo.append(":*");
            } else {
                this.rotulo.append(":");
            }
            JLabel jlRotulo = new JLabel(this.rotulo.toString());
            jlRotulo.setFont(new Font("Arial", Font.BOLD, 12));
            gbc.insets = new Insets(espVert, espVert, espaHor, espaHor);
            gbc.anchor = GridBagConstraints.EAST;
            panelCentral.add(jlRotulo, gbc);
            gbc.gridx++;
        }
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        panelCentral.add(componente, gbc);
        //TestGridBagLayout.insereComponente(3, 3, panelCampos);
    }

    public void adicionaArrayComponente(CustomComponent componente) {
        campos.add((CustomComponent) componente);
    }

    public ArrayList<Filter> getCamposFilter() {
        ArrayList<Filter> filter = new ArrayList<>();
        for (int i = 0; i < this.campos.size(); i++) {
            filter.add(this.campos.get(i));
        }
        return filter;
    }

    public boolean valida() {
        StringBuffer texto = new StringBuffer();
        StringBuilder obr = new StringBuilder();
        StringBuilder valido = new StringBuilder();
        boolean retorno = true;
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).getObrigatorio() && campos.get(i).getVazio()) {
                obr.append(campos.get(i).getNome().replace(":", "")).append("\n");
                retorno = false;
            } else if (!campos.get(i).getValido()) {
                valido.append(campos.get(i).getNome().replace(":", "")).append("\n");
                retorno = false;
            }
        }
        if (obr.length() > 0) {
            texto.append("Campo(s) obrigatório(s):\n").append(obr.toString());
        } else if (valido.length() > 0) {

            texto.append("Campo(s) inválido(s):\n").append(valido.toString());
        }

        if (!retorno) {
            JOptionPane.showMessageDialog(null, texto, "Verificar campos", JOptionPane.INFORMATION_MESSAGE);
        }
        return retorno;
    }

    public void limpaCampos() {
        Component[] component = panelCentral.getComponents();
        for (Component component1 : component) {
            if (component1 instanceof JTextField) {
                ((JTextField) component1).setText("");
            }
        }
    }

    private void habilitaCampos(boolean status) {
        Component[] component = panelCentral.getComponents();
        int count = 0;
        for (Component component1 : component) {
            if (component1 instanceof JTextField) {
                if (count == 0) {
                    ((JTextField) component1).setEditable(false);
                } else {
                    ((JTextField) component1).setEditable(status);
                }
                count++;
            }
        }
    }

    public void statusNovo() {
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jbPesquisar.setEnabled(false);
        limpaCampos();
        habilitaCampos(true);
    }

    public void statusEditar() {
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jbPesquisar.setEnabled(false);
        habilitaCampos(true);
    }

    public void statusPesquisa() {
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        jbCancelar.setEnabled(false);
        jbPesquisar.setEnabled(false);
        habilitaCampos(false);
    }

    public void statusInicial() {
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(false);
        jbPesquisar.setEnabled(true);
        limpaCampos();
        habilitaCampos(false);
    }

    public void statusManutencao(Object status) {
        if ((boolean) status) {
            JOptionPane.showMessageDialog(null, "Dados gravado/alterado com sucesso!");
            this.statusInicial();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel gravar/alterar dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void statusExclusao(boolean status) {
        if (status) {
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Object getPesquisa() {
        return jtPesquisar.getText();
    }
}
