/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.model;

import br.com.brunofarina.annotations.CampoObr;
import br.com.brunofarina.annotations.Coluna;
import br.com.brunofarina.component.Filter;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class Cliente extends AbstractModel {

    private String nome;
    private String rgIe;
    private String cpfCnpj;//ver data
    private String endereco;
    private int numero;
    private String bairro;
    private String cep;
    private Cidade cidade;
    private String obs;

    public Cliente() {
        this.cidade = new Cidade();
    }

    @Coluna(posicao = 0, nome = "Código")
    @Override
    public int getId() {
        return super.getId();
    }

    @Coluna(posicao = 1, nome = "Nome")
    @CampoObr
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRgIe() {
        return rgIe;
    }

    public void setRgIe(String rgIe) {
        this.rgIe = rgIe;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String CpfCnpj) {
        this.cpfCnpj = CpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setInputFilter(ArrayList<Filter> c) {
        for (Method method : Cliente.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CampoObr.class)) {
                for (int i = 0; i < c.size(); i++) {
                    if (c.get(i).getNome().equalsIgnoreCase(method.getName().replace("get", ""))) {
                        c.get(i).setObrigatorio(true);
                    }
                }
            }
        }
    }
}
