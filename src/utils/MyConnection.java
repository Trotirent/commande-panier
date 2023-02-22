/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABDOU
 */
public class MyConnection {
    private static MyConnection Test;
    private static MyConnection data;

    /*public static PreparedStatement prepareStatement(String requete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public String url = "jdbc:mysql://127.0.0.1:3306/mikhasaeb";
    public String login="root";
    public String pwd="";
    public static Connection cn;
    public static MyConnection cno;
    
    public MyConnection() {
        
        try {
            cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
        }
    }
    
    public static MyConnection getInstance ()
    {
       if (cno == null)
            {
            cno = new MyConnection();
            }
            return cno;
    }
    
     public Connection getCnx() {
        return cn;
    }
      
     public static MyConnection getTest() {
        if (Test == null) {
            Test = new MyConnection();
        }
        return Test;
    }
    public void setCn(Connection cnx) {
        this.cn = cn;
    }
    public static MyConnection getConn() {
        return cno;
    }
}
   
  