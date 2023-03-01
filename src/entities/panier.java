/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mikhail
 */
public class panier {
    private int id_panier ; 
    private int id_user ; 
    private int id_produit ; 
    private String nom;
    private int prix;
    private int qty;

    public panier(int id_user, int id_produit, String nom, int prix, int qty) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.qty = qty;
    }

    public panier(String nom, int prix, int qty) {
        this.nom = nom;
        this.prix = prix;
        this.qty = qty;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public panier(int id_user, int id_produit, int qty) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public panier() {
    }

    public panier(int id_user, int id_produit) {
        this.id_user = id_user;
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id_user;
        hash = 47 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final panier other = (panier) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    public panier(int id_panier, String nom, int prix, int qty) {
        this.id_panier = id_panier;
        this.nom = nom;
        this.prix = prix;
        this.qty = qty;
    }

    public int getId_panier() {
        return id_panier;
    }

    @Override
    public String toString() {
        return  "nom=" + nom + ", prix=" + prix + ", qty=" + qty ;
    }
    
    
    
}
