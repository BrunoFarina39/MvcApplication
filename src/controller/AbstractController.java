/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Bruno
 */
public abstract class AbstractController {

    public AbstractController() {

    }

    public abstract void salvar();

    public abstract void editar();

    public abstract void excluir();

    public abstract void listar();
}
