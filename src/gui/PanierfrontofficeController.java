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
import entities.MailSender;
import services.commandeservice;

/**
 * FXML Controller class
 *
 * @author mikhail
 */
public class PanierfrontofficeController implements Initializable {

    @FXML
    private Pane pn_boutique;
    @FXML
    private Pane pn_panier;
    
    panierservice sp = new panierservice();
    commandeservice sc = new commandeservice(); 
    String mail="mikhail.mannai@esprit.tn";
    @FXML
    private TableView<panier> display;
    @FXML
    private TableColumn<panier,String> nom;
    @FXML
    private TableColumn<panier ,String> prix;
    @FXML
    private TableColumn<panier ,String> qty;
    
    panier tmpp = null ; 
    @FXML
    private TableColumn<panier,String> edit;
    @FXML
    private TableColumn<panier,String> id;
    @FXML
    private TextField tf_adresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void btn_btq(ActionEvent event) {
        pn_boutique.toFront();
    }

    @FXML
    private void btn_pn(ActionEvent event) {
        pn_panier.toFront();
    }

    @FXML
    private void ajouter1(ActionEvent event) {
        // (id_client , id_produit , qty )
        panier p = new panier(1,1,"t500",500,1);
        sp.Ajouter(p);
        
    }

    @FXML
    private void ajouter2(ActionEvent event) {
        panier p = new panier(1,1,"t900",900,1);
        sp.Ajouter(p);
    }
    
    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<panier>  l = FXCollections.observableArrayList();
        ResultSet resultSet = sp.Selectionner(1);
        l.clear();   
        try {
            while (resultSet.next()){
                l.add(new  panier(
                        resultSet.getInt("id_panier"),
                        resultSet.getString("nom"),
                        resultSet.getInt("prix"),
                        resultSet.getInt("qty")));
                display.setItems(l);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierfrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        id.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        
        /////////////////////////////////////////////////////////////////////////
        //add cell of button edit 
         Callback<TableColumn<panier, String>, TableCell<panier, String>> cellFoctory = (TableColumn<panier, String> param) -> {
            // make cell containing buttons
            final TableCell<panier, String> cell = new TableCell<panier, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
                        FontAwesomeIconView deditIcon = new FontAwesomeIconView(FontAwesomeIcon.MINUS);


                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deditIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            sp.Supprimer( tmpp.getId_panier());
                        });
                        
                        
                        editIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            //incqty
                            int id= tmpp.getId_panier();
                            int qty = tmpp.getQty();
                            qty++;
                            sp.Modifier(id, qty);
                        });
                        deditIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            //dicqty
                            int id= tmpp.getId_panier();
                            int qty = tmpp.getQty();
                            if(qty>1){
                                qty--;
                            }
                            sp.Modifier(id, qty);

                        });
                        
                        HBox managebtn = new HBox(deditIcon,deleteIcon,editIcon);
                        managebtn.setStyle("-fx-alignment:center");                       
                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);
    }

            
        
    
    @FXML
    private void valider(ActionEvent event) {
        ObservableList<panier>  l = FXCollections.observableArrayList();
        ResultSet resultSet = sp.Selectionner(1);
        l.clear();  
        int total = 0;
        String listp = "";
        try {
            while (resultSet.next()){
                listp+=resultSet.getString("nom");
                int a = resultSet.getInt("prix");
                int b = resultSet.getInt("qty");
                total+=a*b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierfrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String adresse =  tf_adresse.getText(); 
        Commande t = new Commande(1,total,listp,adresse);
        System.out.println(total);
        sc.Ajouter(t);
        try {
            MailSender.sendMail(mail);
        } catch (Exception ex) {
            Logger.getLogger(PanierfrontofficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
