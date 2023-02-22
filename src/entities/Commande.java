/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author ABDOU
 */
public class Commande {
    private int id_commande; 
    private int id_client ; 
    private int prix_totale;
    private String produits ; 
    private String adresse ; 

    public Commande() {
    }

    public int getId_commande() {
        return id_commande;
    }

    public Commande(int id_commande, int id_client, int prix_totale, String produits, String adresse) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.prix_totale = prix_totale;
        this.produits = produits;
        this.adresse = adresse;
    }

    public Commande(int id_client, int prix_totale, String produits, String adresse) {
        this.id_client = id_client;
        this.prix_totale = prix_totale;
        this.produits = produits;
        this.adresse = adresse;
    }

    public int getId_client() {
        return id_client;
    }

    public int getPrix_totale() {
        return prix_totale;
    }

    public String getProduits() {
        return produits;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setPrix_totale(int prix_totale) {
        this.prix_totale = prix_totale;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_commande;
        hash = 11 * hash + this.id_client;
        hash = 11 * hash + this.prix_totale;
        hash = 11 * hash + Objects.hashCode(this.produits);
        hash = 11 * hash + Objects.hashCode(this.adresse);
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
        final Commande other = (Commande) obj;
        if (this.id_commande != other.id_commande) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.prix_totale != other.prix_totale) {
            return false;
        }
        if (!Objects.equals(this.produits, other.produits)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_client=" + id_client + ", prix_totale=" + prix_totale + ", produits=" + produits + ", adresse=" + adresse + '}';
    }
    

    
}
