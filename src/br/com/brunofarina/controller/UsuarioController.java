/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.controller;

import br.com.brunofarina.application.TelaLogin;
import br.com.brunofarina.application.TelaPrincipal;
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
        usuarioDao.primeiroItem();
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        usuarioView.setLinhaSelecionada(usuarioDao.retornaIndiceRs() - 1);
        usuarioView.statusLista();
        usuarioView.setIsFirst(true);

    }

    public void povoaPrimeiroItem() {
        usuarioDao.primeiroItem();
        setaCampos();
    }

    public void povoaUltimoItem() {
        usuarioDao.ultimoItem();
        setaCampos();
    }

    public void povoaProximoItem() {
        usuarioDao.avancaItem();
        setaCampos();
    }

    public void povoaItemAnterior() {
        usuarioDao.voltaItem();
        setaCampos();
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
                povoaProximoItem();
                break;
            case "<":
                povoaItemAnterior();
                break;
            case ">>":
                povoaUltimoItem();
                break;
            case "<<":
                povoaPrimeiroItem();
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
            povoaPrimeiroItem();
            usuarioView.statusLista();
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
                usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
                povoaPrimeiroItem();
                usuarioView.statusLista();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setaCampos() {
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        usuarioView.setLinhaSelecionada(usuarioDao.retornaIndiceRs() - 1);
        testarNavegacao();
        usuarioView.statusLista();
    }

    @Override
    public void listar(Object chave) {
        if (chave instanceof Integer) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario((int) chave), Usuario.class));
        } else if (chave instanceof String) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(chave.toString()), Usuario.class));
        }
        usuarioDao.primeiroItem();
        //usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        //usuarioView.setLinhaSelecionada(usuarioDao.retornaIndiceRs() - 1);
        //testarNavegacao();
        setaCampos();
    }

    public void cancelar() {
        usuarioView.statusLista();
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
    }

    public void testarNavegacao() {
        if (usuarioDao.retornaTamanhoRs() == 1) {
            usuarioView.setStatusBtPesq(false);
        } else if (usuarioDao.isFirst()) {
            usuarioView.setIsFirst(true);
        } else if (usuarioDao.isLast()) {
            usuarioView.setIsFirst(false);
        } else {
            usuarioView.setStatusBtPesq(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        usuarioDao.setItemRs(usuarioView.getLinhaSelecionada() + 1);
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        usuarioView.statusLista();
        testarNavegacao();
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
