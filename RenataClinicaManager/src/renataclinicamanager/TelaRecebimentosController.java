/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.Models.Conta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaRecebimentosController implements Initializable {

    // A Receber
    @FXML
    private Tab tabreceber;
    @FXML
    private TableView<Conta> tvrecebimentosar;
    @FXML
    private SplitPane pnprincipalap;
    @FXML
    private HBox pnbotoesp;
    @FXML
    private VBox pnpesquisap;
    @FXML
    private Pane pnfiltrosp;
    @FXML
    private HBox pnbotoesap1;
    @FXML
    private JFXButton btreceber;
    @FXML
    private VBox pnpesquisaar;
    @FXML
    private Pane pnfiltrosar;
    @FXML
    private JFXComboBox<String> cbcategoriaar;
    @FXML
    private JFXTextField txfiltroar;
    @FXML
    private JFXDatePicker dpdatainicialar;
    @FXML
    private JFXDatePicker dpdatafinalar;
    @FXML
    private TableColumn<Conta, Integer> colcodar;
    @FXML
    private TableColumn<Conta, String> colpacientear;
    @FXML
    private TableColumn<Conta, Integer> colparcelaar;
    @FXML
    private TableColumn<Conta, Double> colvalorar;
    @FXML
    private TableColumn<Conta, String> coldatavencar;
    @FXML
    private TableColumn<Conta, String> coltipoar;
    @FXML
    private TableColumn<Conta, String> colcontatoar;
    
    //Recebidas
    @FXML
    private Tab tabrecebidas;
    @FXML
    private JFXComboBox<String> cbcategoriar;
    @FXML
    private JFXTextField txfiltror;
    @FXML
    private JFXDatePicker dpdatainicialr;
    @FXML
    private JFXDatePicker dpdatafinalr;
    @FXML
    private TableColumn<Conta, Integer> colcodr;
    @FXML
    private TableColumn<Conta, String> colpacienter;
    @FXML
    private TableColumn<Conta, Integer> colparcelar;
    @FXML
    private TableColumn<Conta, Double> colvalorr;
    @FXML
    private TableColumn<Conta, String> coldatapagamentor;
    @FXML
    private TableColumn<Conta, String> coltipor;
    @FXML
    private TableColumn<Conta, String> colcontator;
    @FXML
    private JFXButton btestornar;
    @FXML
    private SplitPane pnprincipalp;
    @FXML
    private TableView<Conta> tvrecebimentosr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }   
    
    @FXML
    private void clkBtEstornar(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkTFiltroapd(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkTFiltroap(KeyEvent event) 
    {
        
    }

    @FXML
    private void clkTabelaap(MouseEvent event) 
    {
        
    }

    @FXML
    private void clkTFiltropd(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkTFiltrop(KeyEvent event) 
    {
        
    }

    @FXML
    private void clkTabelap(MouseEvent event) 
    {
        
    }

    @FXML
    private void clkBtReceber(ActionEvent event) 
    {
        
    }
}
