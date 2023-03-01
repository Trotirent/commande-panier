/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikhail
 */
public class commandeservice implements ICService <Commande>  {
    
    Connection cn = MyConnection.getTest().getCnx();

    public commandeservice() {
    }
        
    @Override
        public ResultSet Selectionner() {
            ResultSet rs = null;
        try {
            String req = "SELECT * FROM `commande`";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }

    @Override
    public void Ajouter(Commande t) {
        try {
            System.out.println(t);
            String req = "INSERT INTO `commande`(`id_client`, `prix_totale`, `produits`, `adresse`) VALUES (?,?,?,?)";
            String req2 = "DELETE FROM `panier` WHERE `id_user` = "+t.getId_client()+"";
            PreparedStatement pst = cn.prepareStatement(req);
            PreparedStatement pst2 = cn.prepareStatement(req2);
            pst.setInt(1, t.getId_client());
            pst.setInt(2, t.getPrix_totale());
            pst.setString(3, t.getProduits());
            pst.setString(4, t.getAdresse());
            pst.executeUpdate();
            pst2.executeUpdate();
            System.out.println("commande ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("non commande ajouté !");

        }
    }

    @Override
    public void Supprimer(int id) {
        try {
                   String req = "DELETE FROM `commande` WHERE `id_commande` ="+id+"";
                   PreparedStatement st = cn.prepareStatement(req);
                   st.executeUpdate(req);
               } catch (SQLException ex) {
                   System.err.println(ex.getMessage());
               }     }

    @Override
    public void Modifier(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
