/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.model;

import br.com.brunofarina.annotations.Coluna;

/**
 *
 * @author Bruno
 */
public abstract class AbstractModel {

    protected int id;

    public AbstractModel() {

    }

    @Coluna(posicao = 0, nome = "Código")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
