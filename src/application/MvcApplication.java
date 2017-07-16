/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import connection.Database;
import controller.LoginController;

/**
 *
 * @author Bruno Farina
 */
public class MvcApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database.getConnection();
        LoginController loginController = new LoginController();
    }

}
