/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.controller;

import br.com.brunofarina.application.TelaPrincipal;
import br.com.brunofarina.component.FiltroConsulta;
import br.com.brunofarina.dao.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.brunofarina.model.TableModel;
import br.com.brunofarina.model.Usuario;
import br.com.brunofarina.view.ViewPesquisa;
import br.com.brunofarina.view.UsuarioView;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController {

    private UsuarioView usuarioView;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    ViewPesquisa p;

    ActionListener actionListenerPesq = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listar(p.getPesquisa());
        }
    };

    MouseListener mouseListenerPesq = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                hidrataCampos();
                TelaPrincipal.jDesktopPane.moveToFront(usuarioView);
            }
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
    };

    ActionListener actionListener = new ActionListener() {
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
                    FiltroConsulta[] filtros = new FiltroConsulta[]{
                        new FiltroConsulta("Código", "codigo", FiltroConsulta.INTEIRO),
                        new FiltroConsulta("Nome", "nome", FiltroConsulta.STRING),};
                    p = ViewPesquisa.getTela(ViewPesquisa.USUARIO, filtros, actionListenerPesq, mouseListenerPesq);
                    p.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
                    break;
            }
        }
    };

    public UsuarioController() {

        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        usuarioView = UsuarioView.getTela(actionListener);
        usuario.setInputFilter((usuarioView.getCamposFilter()));
        this.usuarioView.render();
    }

    public void novo() {
        usuarioView.limpaCampos();
        usuarioView.statusNovo();
    }

    @Override
    public void salvar() {
        if (usuarioView.valida()) {
            usuario.setLogin(usuarioView.getJtLogin().getValor());
            usuario.setSenha(usuarioView.getJtSenha().getValor());
            usuario.setNome(usuarioView.getJtNome().getValor());

            if (usuarioView.getJtCodigo().getVazio()) {
                usuarioView.statusManutencao(usuarioDao.salvar(usuario));
                usuarioView.limpaCampos();
            } else {
                usuario.setId(Integer.parseInt(usuarioView.getJtCodigo().getValor()));
                usuarioView.statusManutencao(usuarioDao.editar(usuario));
                listar(p.getPesquisa());
            }
            usuarioView.statusInicial();
        }
    }

    public void hidrataCampos() {
        usuarioView.preencheCampos((Usuario) p.getRegistro());
        usuarioView.statusPesquisa();
    }

    @Override
    public void editar() {
        usuarioView.statusEditar();
    }

    @Override
    public void excluir() {
        usuario.setId(Integer.parseInt(usuarioView.getJtCodigo().getValor()));
        int jOptionPane = JOptionPane.showConfirmDialog(usuarioView, "Deseja excluir este usuário", "Exclusão", JOptionPane.YES_NO_OPTION);
        int opcaoSim = JOptionPane.YES_OPTION;
        try {
            if (jOptionPane == opcaoSim) {
                if (usuarioDao.excluir(usuario)) {
                    usuarioView.statusExclusao(true);
                    usuarioView.statusInicial();
                    listar(p.getPesquisa());
                } else {
                    usuarioView.statusExclusao(true);
                }
            }
        } catch (SQLException ex) {
            usuarioView.statusExclusao(false);
        }
    }

    public void cancelar() {
        usuarioView.statusInicial();
    }

    public void getTela() {
        usuarioView = UsuarioView.getTela(actionListener);
        usuario.setInputFilter((usuarioView.getCamposFilter()));
        usuarioView.render();
    }

    @Override
    public void listar(Object chave) {
        if (p.getRadio().equals("codigo")) {
            try {
                p.povoaJtable(new TableModel(usuarioDao.listarUsuario(Integer.parseInt(chave.toString())), Usuario.class));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite apenas Números");
            }
        } else {
            p.povoaJtable(new TableModel(usuarioDao.listarUsuario(chave.toString()), Usuario.class));
        }
    }
}
