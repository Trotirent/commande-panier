/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import services.panierservice;
import entities.panier;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.DoubleStream.builder;
import static java.util.stream.IntStream.builder;
import static java.util.stream.LongStream.builder;
import static java.util.stream.Stream.builder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import entities.Commande;
import services.commandeservice;

/**
 * FXML Controller class
 *
 * @author ABDOU
 */
public class CommandebackofficeController implements Initializable {

    @FXML
    private TableColumn<Commande,String> idc;
    @FXML
    private TableColumn<Commande,String> prix;
    @FXML
    private TableColumn<Commande,String> produits;
    @FXML
    private TableColumn<Commande,String> adresse;
    @FXML
    private TableColumn<Commande,String> idcommande;
    @FXML
    private TableColumn<Commande,String> option;
    commandeservice cs = new commandeservice();
    Commande tmpc = new Commande();
    @FXML
    private TableView<Commande> display;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<Commande>  p = FXCollections.observableArrayList();
        ResultSet resultSet = cs.Selectionner();
        p.clear();   
        try {
            while (resultSet.next()){
                p.add(new  Commande(
                        resultSet.getInt("id_commande"),
                        resultSet.getInt("id_client"),
                        resultSet.getInt("prix_totale"),
                        resultSet.getString("produits"),
                        resultSet.getString("adresse")));
                display.setItems(p);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierfrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        idc.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        produits.setCellValueFactory(new PropertyValueFactory<>("produits"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        idcommande.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        
        /////////////////////////////////////////////////////////////////////////
        //add cell of button edit 
         Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            // make cell containing buttons
            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
               
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmpc = display.getSelectionModel().getSelectedItem();
                            cs.Supprimer( tmpc.getId_commande());
                        });
                        
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");                       
                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
         option.setCellFactory(cellFoctory);
  
    }
    
}
