/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.brunofarina.dao;

import br.com.brunofarina.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class ClienteDao extends AbstractDao<Cliente> {

    public ClienteDao() {
        super(Cliente.class);
    }

    public boolean Salvar(Cliente cliente) {
        String sql = "insert into cliente(nome,rgie,cpfcnpj,endereco,numero,bairro,cep,cidade_id,obs) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getRgIe());
            ps.setString(3, cliente.getCpfCnpj());
            ps.setString(4, cliente.getEndereco());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getBairro());
            ps.setString(7, cliente.getCep());
            ps.setInt(8, cliente.getCidade().getId());
            ps.setString(9, cliente.getObs());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean editar(Cliente cliente) {
        String sql = "update cliente set nome=?,rgie=?,cpfcnpj=?,endereco=?,numero=?,bairro=?,cep=?,cidade_id=?,obs=? where id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getRgIe());
            ps.setString(3, cliente.getCpfCnpj());
            ps.setString(4, cliente.getEndereco());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getBairro());
            ps.setString(7, cliente.getCep());
            ps.setInt(8, cliente.getCidade().getId());
            ps.setString(9, cliente.getObs());
            ps.setInt(10, cliente.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

}
