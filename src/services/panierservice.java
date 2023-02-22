/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.panier;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class panierservice implements IPService <panier>  {
    
    Connection cn = MyConnection.getTest().getCnx();

    @Override
    public void Ajouter(panier t) {
        try {
            System.out.println(t);
            String req = "INSERT INTO `panier`(`id_user`, `id_produit`, `nom`, `prix`, `qty`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, t.getId_user());
            pst.setInt(2, t.getId_produit());
            pst.setString(3, t.getNom());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getQty());
            pst.executeUpdate();
            System.out.println("Article ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int id) {
        try {
            String req = "DELETE FROM `panier` WHERE `id_panier` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }


    @Override
    public ResultSet Selectionner(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `panier` WHERE `id_user` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }

    @Override
    public void Modifier(int id,int qty) {
        try {
            String req = "UPDATE `panier` SET `qty`="+qty+" WHERE `id_panier` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

    
    
}
