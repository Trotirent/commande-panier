/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;
import java.util.List;


/**
 *
 * @author mikhail
 */
public interface ICService <T>{
    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(T t);
    public ResultSet Selectionner();
}
