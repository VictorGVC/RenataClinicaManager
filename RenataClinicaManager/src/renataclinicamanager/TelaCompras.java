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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
public class TelaCompras implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField txTotal;
    @FXML
    private Label lbobg;
    @FXML
    private JFXComboBox<?> cbFornecedor;
    @FXML
    private Label lbtotal;
    @FXML
    private JFXButton btAddItem;
    @FXML
    private JFXButton btRemoverProd;
    @FXML
    private JFXComboBox<?> cbMarca;
    @FXML
    private JFXComboBox<?> cbProduto;
    @FXML
    private JFXTextField txQuantidade;
    @FXML
    private JFXTextField txPreco;
    @FXML
    private TableView<?> tvProduto;
    @FXML
    private TableColumn<?, ?> ColProduto;
    @FXML
    private TableColumn<?, ?> ColPreco;
    @FXML
    private TableColumn<?, ?> ColQtd;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXTextField txFiltro;
    @FXML
    private Label lbForncedor;
    @FXML
    private JFXButton btFechar;
    @FXML
    private CheckBox ckbAberto;
    @FXML
    private JFXDatePicker dtpInicio;
    @FXML
    private JFXDatePicker dtpFinal;
    @FXML
    private TableView<?> tvCompra;
    @FXML
    private TableColumn<?, ?> ColCod;
    @FXML
    private TableColumn<?, ?> ColData;
    @FXML
    private TableColumn<?, ?> ColFornecedor;
    @FXML
    private TableColumn<?, ?> ColStatus;
    @FXML
    private TableColumn<?, ?> ColTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkBtNovo(ActionEvent event) {
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkBtAddItem(ActionEvent event) {
    }

    @FXML
    private void clkBtRemoverItem(ActionEvent event) {
    }

    @FXML
    private void clkCbMarca(ActionEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void clkBtFechar(ActionEvent event) {
    }

    @FXML
    private void clickAberto(ActionEvent event) {
    }

    @FXML
    private void dtpData(ActionEvent event) {
    }
    
}
