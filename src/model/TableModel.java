/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModel extends AbstractTableModel {

    private int colunas, linhas;
    private ResultSet rs;
    private ResultSetMetaData rsMetaData;

    public TableModel(ResultSet rs) {
        this.rs = rs;
        try {
            this.rsMetaData = rs.getMetaData();
            // this.rs.last();
            //this.linhas = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return linhas;
    }

    @Override
    public int getColumnCount() {
        this.linhas = 0;
        try {
            this.linhas = this.rsMetaData.getColumnCount();
        } catch (SQLException e) {
            System.err.println("Erro ao contar linhas da tabela: " + e);
        }
        return linhas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
