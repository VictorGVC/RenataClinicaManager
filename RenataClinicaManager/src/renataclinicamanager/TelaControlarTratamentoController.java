/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaControlarTratamentoController implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXButton btadicionar;
    @FXML
    private JFXButton btremover;
    @FXML
    private Label lbobg;
    @FXML
    private TableView<?> tvprodutos;
    @FXML
    private TableColumn<?, ?> colprod;
    @FXML
    private TableColumn<?, ?> colcodbarras;
    @FXML
    private TableColumn<?, ?> colpreco;

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
    private void clkBtAlterar(ActionEvent event) {
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clearPaciente(MouseEvent event) {
    }

    @FXML
    private void preencheCPF(ActionEvent event) {
    }

    @FXML
    private void clkBtAdicionar(ActionEvent event) {
    }

    @FXML
    private void clkBtRemover(ActionEvent event) {
    }

    @FXML
    private void clkTabelaProdutos(MouseEvent event) {
    }
    
}
