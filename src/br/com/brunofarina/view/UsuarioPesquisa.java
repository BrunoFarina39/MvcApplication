/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.view;

import br.com.brunofarina.component.FiltroConsulta;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

/**
 *
 * @author Bruno
 */
public class UsuarioPesquisa extends AbstractViewPesquisa {

    public UsuarioPesquisa(String titulo, FiltroConsulta[] filtroConsulta, ActionListener actionListener, MouseListener mouseListener, boolean resizable, boolean closable, boolean maximizable, boolean iconable) {
        super(titulo, filtroConsulta, actionListener, mouseListener, resizable, closable, maximizable, iconable
        );
    }
}
