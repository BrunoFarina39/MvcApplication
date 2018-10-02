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
public class ExecptionPassword extends Exception {

    public ExecptionPassword() {
        super("As senhas não correspondem!");
    }
}
