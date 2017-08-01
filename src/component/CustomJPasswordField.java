package component;

import component.CustomComponent;
import javax.swing.JPasswordField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bruno
 */
public class CustomJPasswordField extends JPasswordField implements CustomComponent {

    private boolean obrigatorio, filtro;
    private String nome;

    public CustomJPasswordField(int colunas, boolean obrigatorio, boolean filtro, String nome) {
        super(colunas);
        this.obrigatorio = obrigatorio;
        this.filtro = filtro;
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean getObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean getValido() {
        return true;
    }

    @Override
    public boolean getVazio() {
        return getText().trim().length() == 0;
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }

    @Override
    public void limpar() {
        super.setText("");
    }

    @Override
    public boolean getFiltro() {
        return filtro;
    }

    @Override
    public void setTexto(String texto) {
        super.setText(texto);
    }

    @Override
    public String getValor() {
        return String.valueOf(super.getPassword());
    }
}
