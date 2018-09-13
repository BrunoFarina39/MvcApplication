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
public interface CustomComponent extends Filter {

    public String getRotulo();

    public boolean getValido();

    public boolean getVazio();

    public void habilitar(boolean status);

    public void limpar();

    public boolean getFiltro();

    public void setTexto(String texto);

    public String getValor();
}
