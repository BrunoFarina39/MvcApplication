/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class JTableUsuario extends AbstractTableModel {

    private ArrayList<Usuario> usuarios;
    private String[] colunas = {"Código", "Login", "Nome"};

    public JTableUsuario() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public void addRow(Usuario usuario) {
        this.usuarios.add(usuario);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.usuarios.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Usuario get(int linha) {
        return this.usuarios.get(linha);
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return this.usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            //case 0: this.usuarios.get(rowIndex).getId();
            case 0:
                return this.usuarios.get(rowIndex).getLogin();
            case 1:
                return this.usuarios.get(rowIndex).getNome();
        }
        return null;
    }
}
