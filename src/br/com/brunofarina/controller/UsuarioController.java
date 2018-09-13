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
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.brunofarina.model.TableModel;
import br.com.brunofarina.model.Usuario;
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

    public UsuarioController() {
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
                        listar(usuarioView.getPesquisa());
                        break;
                    case ">":
                        usuarioDao.avancaItemRs();
                        setJTextAndJTable();
                        break;
                    case "<":
                        usuarioDao.voltaItemRs();
                        setJTextAndJTable();
                        break;
                    case ">>":
                        usuarioDao.ultimoItemRs();
                        setJTextAndJTable();
                        break;
                    case "<<":
                        usuarioDao.primeiroItemRs();
                        setJTextAndJTable();
                        break;
                }
            }
        };
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    usuarioDao.setItemRs(usuarioView.getLinhaSelecionada() + 1);
                    usuarioView.preencheCampos(usuarioDao.retornaUsuario());
                    usuarioView.statusInicial();
                    testarNavegacao();
                } else if (e.getClickCount() == 2) {
                    editar();
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
        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        this.usuarioView = new UsuarioView(actionListener, mouseListener);
        usuario.setInputFilter((usuarioView.getCamposFilter()));
        this.usuarioView.render();
        usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
        usuarioDao.primeiroItemRs();
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        usuarioView.setSelecaoLinha(usuarioDao.retornaIndiceRs());
        usuarioView.statusInicial();
        usuarioView.setNavIsFirstOrLast(true);
        JTableHeader jTableHeader = usuarioView.getJTable().getTableHeader();
        jTableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable jTable = ((JTableHeader) evt.getSource()).getTable();
                jTable.setRowSelectionInterval(0, 0);
                usuarioDao.primeiroItemRs();
                testarNavegacao();

            }
        });
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
                    usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(), Usuario.class));
                    usuarioDao.primeiroItemRs();
                    setJTextAndJTable();
                    usuarioView.statusInicial();
                } else {
                    JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Exclusão", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(usuarioView, "Não foi possivel excluir usuário", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void listar(Object chave) {
        if (chave instanceof Integer) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario((int) chave), Usuario.class));
        } else if (chave instanceof String) {
            usuarioView.povoaJtable(new TableModel(usuarioDao.listarUsuario(chave.toString()), Usuario.class));
        }
        usuarioDao.primeiroItemRs();
        setJTextAndJTable();
    }

    public void cancelar() {
        usuarioView.statusInicial();
        testarNavegacao();
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
    }

    public void testarNavegacao() {
        if (usuarioDao.retornaTamanhoRs() == 1 || usuarioDao.retornaTamanhoRs() == 0) {
            usuarioView.setNavStatus(false);
        } else if (usuarioDao.isFirst()) {
            usuarioView.setNavIsFirstOrLast(true);
        } else if (usuarioDao.isLast()) {
            usuarioView.setNavIsFirstOrLast(false);
        } else {
            usuarioView.setNavStatus(true);
        }
    }

    public UsuarioView getView() {
        return this.usuarioView;
    }

    private void setJTextAndJTable() {
        usuarioView.preencheCampos(usuarioDao.retornaUsuario());
        usuarioView.setSelecaoLinha(usuarioDao.retornaIndiceRs());
        testarNavegacao();
        usuarioView.statusInicial();
    }
}
