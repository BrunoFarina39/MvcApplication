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
public class FiltroConsulta {

    public static final int INTEIRO = 1;
    public static final int STRING = 2;
    private String filtro;
    private String campo;
    private int tipo;

    public FiltroConsulta(String filtro, String campo, int tipo) {
        this.filtro = filtro;
        this.campo = campo;
        this.tipo = tipo;
    }

    public String getCampo() {
        return campo;
    }

    public String getFiltro() {
        return filtro;
    }

    public int getTipo() {
        return tipo;
    }
}
