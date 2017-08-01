/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.dao.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.TableModel;
import model.Usuario;
import view.UsuarioView;

/**
 *
 * @author Bruno
 */
public class UsuarioController extends AbstractController implements ActionListener, MouseListener {

    private UsuarioView usuarioView;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private String status;

    public UsuarioController(UsuarioView usuarioView) {
        this.usuarioView = usuarioView;
        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDao();
        status = new String("novo");
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));

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
        }
    }

    public void novo() {
        status = "novo";
        usuarioView.limpaCampos();
        usuarioView.statusNovo();
    }

    @Override
    public void salvar() {
        if (usuarioView.valida()) {
            if (status.equals("novo")) {
                usuario.setLogin(usuarioView.getLogin());
                usuario.setSenha(usuarioView.getSenha());
                usuario.setNome(usuarioView.getNome());
                usuarioView.statusManutencao(usuarioDao.salvar(usuario));
                usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));
            } else {
                usuario.setId(Integer.parseInt(usuarioView.getId()));
                usuario.setLogin(usuarioView.getLogin());
                usuario.setSenha(usuarioView.getSenha());
                usuario.setNome(usuarioView.getNome());
                usuarioView.statusManutencao(usuarioDao.editar(usuario));
                usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));
                usuarioView.limpaCampos();
                status = new String("novo");
            }
            usuarioView.limpaCampos();
            usuarioView.statusSalvar();
        }
    }

    @Override
    public void editar() {
        status = "editar";
        usuarioView.statusEditar();
    }

    @Override
    public void excluir() {
        usuario.setId(Integer.parseInt(usuarioView.getId()));
        JOptionPane.showMessageDialog(usuarioView, usuarioDao.excluir(usuario));
        usuarioView.povoaJtable(new TableModel(usuarioDao.listar()));
        usuarioView.limpaCampos();
        usuarioView.statusInicial();

    }

    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void cancelar() {
        usuarioView.limpaCampos();
        usuarioView.statusInicial();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        usuarioView.preencheCampos();
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
