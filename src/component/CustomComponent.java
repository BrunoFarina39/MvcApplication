/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

/**
 *
 * @author Bruno
 */
public interface CustomComponent {

    public String getNome();

    public boolean getObrigatorio();

    public boolean getValido();

    public boolean getVazio();

    public void habilitar(boolean status);

    public void limpar();

    public boolean getFiltro();

    public void setTexto(String texto);

    public String getValor();
}
