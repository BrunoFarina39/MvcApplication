/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.TelaLogin;
import application.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Usuario;

/**
 *
 * @author Bruno Farina
 */
public class LoginController {

    TelaLogin telaLogin;
    Usuario usuario;

    public LoginController() {
        this.telaLogin = new TelaLogin();
        this.telaLogin.adicionaOuvinte(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Login")) {
                    usuario = new Usuario();
                    usuario.setLogin(telaLogin.jtLogin.getText());
                    usuario.setSenha(String.valueOf(telaLogin.jtSenha.getPassword()));
                    if (usuario.autentica()) {
                        TelaPrincipal telaPrincipal = new TelaPrincipal();
                    } else {
                        System.err.println("Não Logado");
                    }
                }
            }
        });
    }
}
