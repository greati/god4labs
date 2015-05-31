/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vitor
 */
public class ConexaoSQLServer {
    
    private static final String LOGIN = "sa";//"sa";
    private static final String SENHA = "123";//"sqAdmin123";//123
    private static final String DATABASE = "GOD4LABS";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName="+DATABASE;
    
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, LOGIN, SENHA);
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
    
}
