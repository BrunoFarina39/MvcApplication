/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModel extends AbstractTableModel {

    private ArrayList<Usuario> lista;
    private String[] colunas;

    public TableModel(ArrayList<Usuario> lista, String[] colunas) {
        this.lista = lista;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getId();
            case 1:
                return lista.get(rowIndex).getLogin();
            case 2:
                return lista.get(rowIndex).getNome();
        }
        return null;
    }
}
