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
    private Class<?> classe;

    public TableModel(ArrayList<T> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        classe = lista.get(0).getClass();
        int colunas = 0;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                colunas++;
            }
        }
        return colunas;
    }

    @Override
    public String getColumnName(int column) {
        classe = lista.get(0).getClass();
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                Coluna anotacao = metodo.getAnnotation(Coluna.class);
                if (anotacao.posicao() == column) {
                    return anotacao.nome();
                }
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            classe = lista.get(0).getClass();
            Object object = lista.get(rowIndex);
            for (Method method : classe.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Coluna.class)) {
                    Coluna c = method.getAnnotation(Coluna.class);
                    if (c.posicao() == columnIndex) {
                        return method.invoke(object);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
