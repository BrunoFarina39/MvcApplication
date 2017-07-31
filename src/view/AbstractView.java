/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Bruno
 */
public class AbstractView extends JInternalFrame {

    protected JPanel panel, panelCampos, panelBotoes, panelSul, panelInstPesq, panelPesquisa, panelScroll;
    protected JLabel jlTitulo;
    protected JButton jbNovo, jbSalvar, jbEditar, jbExcluir, jbPesquisar, jbInicio, jbUltimo, jbProximo, jbAnterior;
    protected JTextField jtPesquisar;
    protected JTable jTable;

    public AbstractView(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconable) {
        super(title, resizable, closable, maximizable, iconable);
        this.panel = new JPanel();
        this.panelCampos = new JPanel();
        this.panelBotoes = new JPanel();
        this.panelSul = new JPanel();
        this.panelInstPesq = new JPanel();
        this.panelPesquisa = new JPanel();
        this.panelScroll = new JPanel();
        this.jbNovo = new JButton("Novo");
        this.jbSalvar = new JButton("Salvar");
        this.jbEditar = new JButton("Editar");
        this.jbExcluir = new JButton("Excluir");
        this.jbPesquisar = new JButton("Pesquisar");
        this.jlTitulo = new JLabel("Manutenção de...");
        this.jtPesquisar = new JTextField(15);
        this.jbProximo = new JButton(">");
        this.jbAnterior = new JButton("<");
        this.jbInicio = new JButton("<<");
        this.jbUltimo = new JButton(">>");

        this.jbProximo.setSize(new Dimension(10, 10));
        this.jbAnterior.setSize(new Dimension(10, 10));
        this.jTable = new JTable();

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

        this.panelBotoes.setLayout(new FlowLayout());
        this.panelBotoes.add(this.jbNovo);
        this.panelBotoes.add(this.jbSalvar);
        this.panelBotoes.add(this.jbEditar);
        this.panelBotoes.add(this.jbExcluir);

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
        this.panelInstPesq.add(jbInicio);
        this.panelInstPesq.add(jbAnterior);
        this.panelInstPesq.add(jbProximo);
        this.panelInstPesq.add(jbUltimo);
        statusInicial();
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
}
