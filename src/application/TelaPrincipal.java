/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.sun.security.ntlm.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import view.Cliente;

/**
 *
 * @author Bruno
 */
public class TelaPrincipal extends JFrame implements ActionListener {

    private JMenu jMenu;
    private JMenuBar jMenuBar;
    private JMenuItem jMenuItemUsuario, jMenuItemCliente, jMenuItemFornecedor, jMenuItemMarca, jMenuItemProduto, jMenuItemServico;
    private Cliente jInternalFrame;
    private JDesktopPane jDesktopPane;

    public TelaPrincipal() {
        jMenu = new JMenu();
        jMenuBar = new JMenuBar();
        jInternalFrame = new Cliente();
        jDesktopPane = new JDesktopPane();
        jDesktopPane.add(jInternalFrame);
        jMenuItemUsuario = new JMenuItem();
        jMenuItemCliente = new JMenuItem();
        jMenuItemFornecedor = new JMenuItem();
        jMenuItemMarca = new JMenuItem();
        jMenuItemProduto = new JMenuItem();
        jMenuItemServico = new JMenuItem();

        jMenuItemUsuario.setText("Usuário");
        jMenuItemCliente.setText("Cliente");
        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemMarca.setText("Marca");
        jMenuItemProduto.setText("Produto");
        jMenuItemServico.setText("Serviço");

        jMenu.setText("Cadastros");

        jMenuBar.add(jMenu);
        jMenu.add(jMenuItemUsuario);
        jMenu.add(jMenuItemCliente);
        jMenu.add(jMenuItemFornecedor);
        jMenu.add(jMenuItemMarca);
        jMenu.add(jMenuItemProduto);
        jMenu.add(jMenuItemServico);

        jMenuItemUsuario.addActionListener(this);
        jMenuItemCliente.addActionListener(this);
        jMenuItemFornecedor.addActionListener(this);
        jMenuItemMarca.addActionListener(this);
        jMenuItemProduto.addActionListener(this);
        jMenuItemServico.addActionListener(this);

        setContentPane(jDesktopPane);
        this.setJMenuBar(jMenuBar);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jMenuItemUsuario) {
            // implemetar controller usuario
        }
    }

}
