/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annotations.Coluna;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModel<T> extends AbstractTableModel {

    private ArrayList<T> lista;
    private String[] colunas;
    private Class<?> classe;

    public TableModel(ArrayList<T> lista) {
        this.lista = lista;
        this.colunas = colunas;
        this.classe = classe;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        return "Test";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            classe = lista.get(0).getClass();
            Object object = lista.get(rowIndex);
            for (Method method : classe.getDeclaredMethods()) {
                Coluna c = method.getAnnotation(Coluna.class);
                if (c != null && c.posicao() == columnIndex) {
                    return method.invoke(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
