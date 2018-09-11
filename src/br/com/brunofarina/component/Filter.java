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
public interface Filter {

    public String getNome();

    public void setObrigatorio(boolean obrigatorio);

    public boolean getObrigatorio();
}
