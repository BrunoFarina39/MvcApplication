/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.controller;

import br.com.brunofarina.dao.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.brunofarina.model.TableModel;
import br.com.brunofarina.model.Usuario;
import br.com.brunofarina.view.UsuarioView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController implements ActionListener, MouseListener {

    private UsuarioView usuarioView;
    private Usuario usuario;
    private UsuarioDao usuarioDao;

    public UsuarioController(UsuarioView usuarioView) {
        this.usuarioView = usuarioView;
        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Novo":
                novo();
                break;
            case "Salvar":
                salvar();
                break;
            case "Editar":
                editar();
                break;
            case "Excluir":
                excluir();
                break;
            case "Cancelar":
                cancelar();
                break;
            case "Pesquisar":
                listar(usuarioView.getPesquisa());
                break;
            case ">":
                usuarioView.avancarItem();
                setaCampos();
                break;
            case "<":
                usuarioView.voltarItem();
                setaCampos();
                break;
            case ">>":
                usuarioView.ultimoItem();
                setaCampos();
                break;
            case "<<":
                usuarioView.primeiroItem();
                setaCampos();
                break;
        }
    }

    public void novo() {
        usuarioView.limpaCampos();
        usuarioView.statusNovo();
    }

    @Override
    public void salvar() {
        if (usuarioView.valida()) {
            usuario.setLogin(usuarioView.getLogin());
            usuario.setSenha(usuarioView.getSenha());
            usuario.setNome(usuarioView.getNome());

            if (usuarioView.getId().length() == 0) {
                usuarioView.statusManutencao(usuarioDao.salvar(usuario));
                usuarioView.limpaCampos();
            } else {
                usuario.setId(Integer.parseInt(usuarioView.getId()));
                usuarioView.statusManutencao(usuarioDao.editar(usuario));
            }
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
            usuarioView.statusSalvar();
        }
    }

    @Override
    public void editar() {
        usuarioView.statusEditar();
    }

    @Override
    public void excluir() {
        usuario.setId(Integer.parseInt(usuarioView.getId()));
        int jOptionPane = JOptionPane.showConfirmDialog(usuarioView, "Deseja excluir este usuário", "Exclusão", JOptionPane.YES_NO_OPTION);
        int opcaoSim = JOptionPane.YES_OPTION;
        try {
            if (jOptionPane == opcaoSim) {
                usuarioDao.excluir(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
        usuarioView.limpaCampos();
        usuarioView.statusInicial();

    }

    private void setaCampos() {
        usuario.setId(usuarioView.retornaIdObjeto());
        {
            try {
                usuarioView.preencheCampos(usuarioDao.buscaPorId(usuario));
            } catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                JOptionPane.showMessageDialog(usuarioView, "Não foi possivel preencher dados", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void listar(Object chave) {
        if (chave instanceof Integer) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario((int) chave), Usuario.class));
        } else if (chave instanceof String) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(chave.toString()), Usuario.class));
        }
    }

    public void cancelar() {
        usuarioView.limpaCampos();
        usuarioView.statusInicial();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setaCampos();
        usuarioView.setCont();
        usuarioView.statusLista();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
