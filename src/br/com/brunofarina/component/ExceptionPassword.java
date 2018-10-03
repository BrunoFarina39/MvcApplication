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

    String message;

    public ExceptionPassword(String menssage) {
        this.message = menssage;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
