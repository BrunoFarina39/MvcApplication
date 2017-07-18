/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Bruno
 */
public class AbstractView extends JInternalFrame {

    protected JPanel panel, panelCampos, panelBotoes, panelSul, panelInstPesq, panelPesquisa;
    protected JLabel jlTitulo;
    protected JButton jbNovo, jbSalvar, jbEditar, jbExcluir, jbListar, jbPesquisar;
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
        this.jbNovo = new JButton("Novo");
        this.jbSalvar = new JButton("Salvar");
        this.jbEditar = new JButton("Editar");
        this.jbExcluir = new JButton("Excluir");
        this.jbListar = new JButton("Listar");
        this.jbPesquisar = new JButton("Pesquisar");
        this.jlTitulo = new JLabel("Manutenção de...");
        this.jtPesquisar = new JTextField(15);
        String[] colunas = {"Nome", "Telefone", "Email"};
        Object[][] dados = {
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
        };
        this.jTable = new JTable(dados, colunas);

        Container container = getContentPane();
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
        this.panelBotoes.add(this.jbListar);

        this.panelSul.setLayout(new BorderLayout());
        this.panelSul.add(panelBotoes, BorderLayout.NORTH);
        this.panelPesquisa.setLayout(new BorderLayout());
        this.panelInstPesq.setLayout(new FlowLayout());
        this.panelInstPesq.add(jtPesquisar);
        this.panelInstPesq.add(jbPesquisar);
        this.panelPesquisa.add(panelInstPesq, BorderLayout.NORTH);
        this.panelPesquisa.add(jTable, BorderLayout.SOUTH);
        this.panelSul.add(panelPesquisa, BorderLayout.SOUTH);
    }
}
