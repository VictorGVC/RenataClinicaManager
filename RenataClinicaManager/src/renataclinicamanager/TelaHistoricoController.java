/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class TelaHistoricoController implements Initializable {

    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private JFXDatePicker dpdatainicial;
    @FXML
    private JFXDatePicker dpdatafinal;
    @FXML
    private JFXButton btlimpar;
    @FXML
    private JFXButton btfiltrar;
    @FXML
    private TableView<?> tvvendas;
    @FXML
    private TableColumn<?, ?> colcliente;
    @FXML
    private TableColumn<?, ?> coldata;
    @FXML
    private TableColumn<?, ?> coltotalven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void clkBtLimpar(ActionEvent event) {
    }

    @FXML
    private void clkBtFiltrar(ActionEvent event) {
    }

    @FXML
    private void clkTabelaVendas(MouseEvent event) {
    }
    
}
