/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Bruno
 */
public class AbstractView extends JInternalFrame {

    protected JPanel panel, panelCampos, panelBotoes, panelSul, panelInstPesq, panelPesquisa;
    protected JLabel jlTitulo;
    public JButton jbNovo, jbSalvar, jbEditar, jbExcluir, jbListar, jbPesquisar, jbInicio, jbUltimo, jbProximo, jbAnterior;
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
        this.jbProximo = new JButton(">");
        this.jbAnterior = new JButton("<");
        this.jbInicio = new JButton("<<");
        this.jbUltimo = new JButton(">>");
        Object[] colunas = new String[]{"Nome", "Telefone", "Email"};
        Object[][] dados = {
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
        };
        this.jbProximo.setSize(new Dimension(10, 10));
        this.jbAnterior.setSize(new Dimension(10, 10));
        this.jTable = new JTable(dados, colunas);
        this.jTable.setPreferredScrollableViewportSize(new Dimension(240, 140));
        DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
        rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
        rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
        rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
        JTableHeader header = jTable.getTableHeader();
        header.setPreferredSize(new Dimension(100, 35));
        TableColumnModel modeloDaColuna = jTable.getColumnModel();
        modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda);
        modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
        modeloDaColuna.getColumn(0).setPreferredWidth(200);
        modeloDaColuna.getColumn(1).setPreferredWidth(200);
        modeloDaColuna.getColumn(2).setPreferredWidth(200);
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
        this.panelBotoes.add(this.jbListar);

        this.panelSul.setLayout(new BorderLayout());
        this.panelSul.add(panelBotoes, BorderLayout.NORTH);
        this.panelSul.add(panelPesquisa, BorderLayout.SOUTH);

        this.panelPesquisa.setLayout(new BorderLayout());
        this.panelPesquisa.add(panelInstPesq, BorderLayout.NORTH);
        this.panelPesquisa.add(scroll, BorderLayout.SOUTH);

        this.panelInstPesq.setLayout(new FlowLayout());
        this.panelInstPesq.add(jtPesquisar);
        this.panelInstPesq.add(jbPesquisar);
        this.panelInstPesq.add(jbInicio);
        this.panelInstPesq.add(jbAnterior);
        this.panelInstPesq.add(jbProximo);
        this.panelInstPesq.add(jbUltimo);
    }
}
