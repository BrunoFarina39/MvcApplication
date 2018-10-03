/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.component;

/**
 *
 * @author Bruno
 */
public class ExceptionPassword extends Exception {

    public ExceptionPassword() {
        super("Não foi pessivel alterar a senha verifique se a senha atual esta correta ou se os campos Nova Senha e Conf. Senha são iguais!");
    }
}
