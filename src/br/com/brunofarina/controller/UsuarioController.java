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
import br.com.brunofarina.view.UsuarioPesquisa;
import br.com.brunofarina.view.UsuarioView;
import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController {

    private UsuarioView usuarioView;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    UsuarioPesquisa p;

    ActionListener actionListenerPesq = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(".actionPerformed()");
        }
    };

    MouseListener mouseListenerPesq = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                editar();
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
                    p = new UsuarioPesquisa("Pesquisa de Usuário", filtros, actionListenerPesq, mouseListenerPesq, true, true, true, true);
                    p.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
                    break;
            }
        }
    };

    public UsuarioController() {

        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        usuarioView = UsuarioView.getTela();
        usuarioView.AddAction(actionListener);
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
            }
            listar(usuarioView.getPesquisa());
        }
    }

    @Override
    public void editar() {
        usuarioView.preencheCampos((Usuario) p.getRegistro());
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
                    // usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
                    usuarioDao.primeiroItemRs();
                    usuarioView.statusInicial();
                } else {
                    JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Exclusão", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* public void listar(Object chave) {
        if (chave instanceof Integer) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario((int) chave), Usuario.class));
        } else if (chave instanceof String) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(chave.toString()), Usuario.class));
        }
        usuarioDao.primeiroItemRs();
        setJTextAndJTable();
    }*/
    public void cancelar() {
        usuarioView.statusInicial();
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
    }

    public void getTela() {
        usuarioView = UsuarioView.getTela();
        //usuarioView.AddAction(actionListener, mouseListener);
        usuario.setInputFilter((usuarioView.getCamposFilter()));
        usuarioView.render();
        // usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
        usuarioDao.primeiroItemRs();
        //usuarioView.setSelecaoLinha(usuarioDao.retornaIndiceRs());
        usuarioView.statusInicial();
    }

    @Override
    public void listar(Object chave) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
