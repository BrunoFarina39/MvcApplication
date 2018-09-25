/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.CustomJTextField;
import br.com.brunofarina.component.FiltroConsulta;
import br.com.brunofarina.model.TableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bruno
 */
public abstract class AbstractViewPesquisa extends JInternalFrame {

    private JPanel panel, panelNorte, panelCentral, panelSul, panelScroll;
    private JLabel jlTitulo, jlPesquisa;
    private JButton jBPesquisar;
    private CustomJTextField jtPesquisa;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private FiltroConsulta[] filtros;
    private ButtonGroup buttonGroup;

    public AbstractViewPesquisa(String titulo, FiltroConsulta[] filtroConsulta, ActionListener actionListener, MouseListener mouseListener, boolean resizable, boolean closable, boolean maximizable, boolean iconable) {
        super(titulo, resizable, closable, maximizable, iconable);
        this.setVisible(true);
        this.setSize(600, 400);
        this.filtros = filtroConsulta;
        TelaPrincipal.jDesktopPane.add(this);
        TelaPrincipal.jDesktopPane.moveToFront(this);
        TelaPrincipal.jDesktopPane.selectFrame(true);

        this.panel = new JPanel();
        this.panelNorte = new JPanel();
        this.panelCentral = new JPanel();
        this.panelSul = new JPanel();
        this.jlTitulo = new JLabel("Pesquisa de Usuário");
        this.jlPesquisa = new JLabel("Pesquisar:");
        this.jtPesquisa = new CustomJTextField(20, true, "Pesquisa", "Pesquisa");
        this.jBPesquisar = new JButton("Pesquisar");
        this.buttonGroup = new ButtonGroup();
        this.jBPesquisar.addActionListener(actionListener);
        this.panelCentral.setLayout(new FlowLayout());
        this.panelCentral.add(this.jlPesquisa);
        this.panelCentral.add(this.jtPesquisa);
        this.panelCentral.add(this.jBPesquisar);

        this.jTable = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    component.setBackground(row % 2 == 0 ? Color.lightGray : Color.WHITE);
                }
                return component;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        this.jTable.setAutoCreateRowSorter(true);
        //jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        jTable.addMouseListener(mouseListener);
        this.jScrollPane = new JScrollPane(jTable);
        this.jScrollPane.setViewportView(jTable);
        this.panelScroll = new JPanel();
        this.panelScroll.add(this.jScrollPane);
        this.jScrollPane.setPreferredSize(new Dimension(600, 200));

        this.panelSul.setLayout(new FlowLayout());
        this.panelSul.add(this.jScrollPane);

        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNorte.add(jlTitulo);

        this.panel.setLayout(new BorderLayout());
        panel.add(this.panelNorte, BorderLayout.NORTH);
        panel.add(this.panelCentral, BorderLayout.CENTER);
        panel.add(panelSul, BorderLayout.SOUTH);

        Container container = getContentPane();
        container.add(this.panel);

        for (int i = 0; i < filtros.length; i++) {
            JRadioButton jr = new JRadioButton(filtros[i].getFiltro());
            jr.setName(filtros[i].getCampo());
            buttonGroup.add(jr);
            panelCentral.add(jr);
        }
    }

    public void povoaJtable(TableModel tb) {
        jTable.setModel(tb);
    }

    public Object getRegistro() {
        return ((TableModel) jTable.getModel()).carregaItem(jTable.getSelectedRow());
    }
}
