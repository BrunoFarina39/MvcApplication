/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Farina
 */
public class Database {
    static Connection conexao;
   
    public static Connection getConnection(){
        try{
            if(conexao == null){
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mvc_application", "postgres", "postgres");
            } 
           
        }catch(ClassNotFoundException e){
              JOptionPane.showMessageDialog(null, "Não foi possível encontrar o driver de acesso ao banco de dados.");
              return null;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados.");
            return null;
        }
    return conexao;
    }
}
