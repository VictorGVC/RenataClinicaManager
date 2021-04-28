/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaMateriaisController implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btApagar;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField txNomeProduto;
    @FXML
    private JFXTextField txPreco;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private TableView<?> tvProdutos;
    @FXML
    private TableColumn<?, ?> ColCod;
    @FXML
    private TableColumn<?, ?> ColProduto;
    @FXML
    private TableColumn<?, ?> ColCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NovoProd(ActionEvent event) {
    }

    @FXML
    private void EditarProduto(ActionEvent event) {
    }

    @FXML
    private void RemoverProduto(ActionEvent event) {
    }

    @FXML
    private void SalvarProduto(ActionEvent event) {
    }

    @FXML
    private void FiltrarProduto(KeyEvent event) {
    }
    
}
