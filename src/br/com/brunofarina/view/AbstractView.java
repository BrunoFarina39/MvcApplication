/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.component.CustomComponent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import br.com.brunofarina.util.TestGridBagLayout;
import javax.swing.ButtonGroup;

/**
 *
 * @author Bruno
 */
public class AbstractView extends JInternalFrame {

    protected JPanel panel, panelCampos, panelBotoes, panelSul, panelInstPesq, panelPesquisa, panelScroll, panelChavePesq;
    protected JLabel jlTitulo;
    protected JButton jbNovo, jbSalvar, jbEditar, jbExcluir, jbCancelar, jbPesquisar, jbInicio, jbUltimo, jbProximo, jbAnterior;
    protected JTextField jtPesquisar;
    protected JTable jTable;
    private StringBuffer rotulo;
    private ArrayList<CustomComponent> campos;
    protected ButtonGroup btGroup;
    private int cont = 0;

    public AbstractView(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable) {
        super(title, resizable, closable, maximizable, iconable);
        this.panel = new JPanel();
        this.panelCampos = new JPanel();
        this.panelBotoes = new JPanel();
        this.panelSul = new JPanel();
        this.panelInstPesq = new JPanel();
        this.panelPesquisa = new JPanel();
        this.panelChavePesq = new JPanel();
        this.panelScroll = new JPanel();
        this.jbNovo = new JButton("Novo");
        this.jbSalvar = new JButton("Salvar");
        this.jbEditar = new JButton("Editar");
        this.jbExcluir = new JButton("Excluir");
        this.jbCancelar = new JButton("Cancelar");
        this.jbPesquisar = new JButton("Pesquisar");
        this.jlTitulo = new JLabel("Manutenção de...");
        this.jtPesquisar = new JTextField(15);
        this.jbProximo = new JButton(">");
        this.jbAnterior = new JButton("<");
        this.jbInicio = new JButton("<<");
        this.jbUltimo = new JButton(">>");
        this.btGroup = new ButtonGroup();

        this.jbProximo.setSize(new Dimension(10, 10));
        this.jbAnterior.setSize(new Dimension(10, 10));
        this.jTable = new JTable();
        rotulo = new StringBuffer();
        campos = new ArrayList<CustomComponent>();
        Container container = getContentPane();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(jTable);
        scroll.setViewportView(jTable);
        container.add(panel);

        this.panel.setLayout(new BorderLayout());
        panel.add(jlTitulo, BorderLayout.NORTH);
        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelSul, BorderLayout.SOUTH);

        GridBagLayout layoutCampos = new GridBagLayout();
        this.panelCampos.setLayout(layoutCampos);
        this.panelChavePesq.setLayout(new FlowLayout());
        this.panelBotoes.setLayout(new FlowLayout());
        this.panelBotoes.add(this.jbNovo);
        this.panelBotoes.add(this.jbSalvar);
        this.panelBotoes.add(this.jbEditar);
        this.panelBotoes.add(this.jbExcluir);
        this.panelBotoes.add(this.jbCancelar);

        this.panelSul.setLayout(new BorderLayout());
        this.panelSul.add(panelBotoes, BorderLayout.NORTH);
        this.panelSul.add(panelPesquisa, BorderLayout.SOUTH);

        this.panelPesquisa.setLayout(new BorderLayout());
        this.panelPesquisa.add(panelInstPesq, BorderLayout.NORTH);
        this.panelPesquisa.add(panelScroll, BorderLayout.SOUTH);

        scroll.setPreferredSize(new Dimension(600, 200));
        this.panelScroll.add(scroll);
        this.panelInstPesq.setLayout(new FlowLayout());
        this.panelInstPesq.add(jtPesquisar);
        this.panelInstPesq.add(jbPesquisar);
        this.panelInstPesq.add(panelChavePesq);
        this.panelInstPesq.add(jbInicio);
        this.panelInstPesq.add(jbAnterior);
        this.panelInstPesq.add(jbProximo);
        this.panelInstPesq.add(jbUltimo);
        statusInicial();
    }

    public void adicionaComponente(int linha, int coluna, int largura, int altura, int espVert, int espaHor, JComponent componente) {
        this.rotulo.delete(0, rotulo.length());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = linha;
        gbc.gridy = coluna;

        if (componente instanceof CustomComponent) {
            this.rotulo.append(((CustomComponent) componente).getNome());
            if (((CustomComponent) componente).getObrigatorio()) {
                this.rotulo.append(":*");
            } else {
                this.rotulo.append(":");
            }
            JLabel jlRotulo = new JLabel(this.rotulo.toString());
            campos.add((CustomComponent) componente);
            jlRotulo.setFont(new Font("Arial", Font.BOLD, 12));
            gbc.insets = new Insets(espVert, espVert, espaHor, espaHor);
            gbc.anchor = GridBagConstraints.EAST;
            panelCampos.add(jlRotulo, gbc);
            gbc.gridx++;
        }
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        panelCampos.add(componente, gbc);
        TestGridBagLayout.insereComponente(3, 3, panelCampos);
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
        Component[] jComponent = panelCampos.getComponents();
        for (int i = 0; i < jComponent.length; i++) {
            if (jComponent[i] instanceof JTextField) {
                ((JTextField) jComponent[i]).setText("");
            }
        }
    }

    public void habilitaCampos(boolean status) {
        Component[] jComponent = panelCampos.getComponents();
        for (int i = 0; i < jComponent.length; i++) {
            if (jComponent[i] instanceof JTextField) {
                ((JTextField) jComponent[i]).setEditable(status);
            }
        }
    }

    public void statusInicial() {
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        habilitaCampos(false);
    }

    public void statusNovo() {
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        habilitaCampos(true);
    }

    public void statusSalvar() {
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        habilitaCampos(false);
    }

    public void statusEditar() {
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(false);
        habilitaCampos(true);
    }

    public void statusLista() {
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        habilitaCampos(false);
    }

    public void statusManutencao(boolean status) {
        if (status) {
            JOptionPane.showMessageDialog(panelCampos, "Dados gravado/alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(panelCampos, "Não foi possivel gravar/alterar dados!");
        }
    }

    public int retornaIdTabela() {
        int linha = jTable.getSelectedRow();
        return Integer.parseInt(jTable.getValueAt(linha, 0).toString());
    }

    public String getPesquisa() {
        return jtPesquisar.getText();
    }

    public void avancarItem() {
        jTable.setRowSelectionInterval(cont, cont);
        if (jTable.getRowCount() - 1 != cont) {
            cont++;
        }
    }

    public void voltarItem() {
        if (cont != 0) {
            cont--;
        }
        jTable.setRowSelectionInterval(cont, cont);
    }

    public void ultimoItem() {
        cont = jTable.getRowCount() - 1;
        jTable.setRowSelectionInterval(cont, cont);
    }

    public void primeiroItem() {
        cont = 1;
        jTable.setRowSelectionInterval(0, 0);
    }
}