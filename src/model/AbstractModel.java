/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annotations.Coluna;

/**
 *
 * @author Bruno
 */
public abstract class AbstractModel {

    protected int id;

    public AbstractModel() {

    }

    @Coluna(posicao = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
