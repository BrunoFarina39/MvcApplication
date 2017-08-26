/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.dao.AbstractDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bruno
 */
public class AdaptadorMouseTabela extends MouseAdapter {

    ResultSet rs;
    AbstractDao abs;

    public AdaptadorMouseTabela() {

    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        JTable jTable = ((JTableHeader) evt.getSource()).getTable();
        jTable.setRowSelectionInterval(0, 0);
        abs.primeiroItem();
    }

    public void setDao(AbstractDao abs) {
        this.abs = abs;
    }
}
