/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class TelaFornecedorController implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btNovoFornecedor;
    @FXML
    private JFXButton btEditarFornecedor;
    @FXML
    private JFXButton btRemoverFornecedor;
    @FXML
    private JFXButton btAddFornecedor;
    @FXML
    private JFXButton btCancelarFornecedor;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField txCNPJ;
    @FXML
    private JFXTextField txNomeForcenedor;
    @FXML
    private JFXTextField txEmail;
    @FXML
    private JFXTextField txTelefone;
    @FXML
    private JFXTextField txIE;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<?> cbFiltro;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private TableView<?> tvFornecedores;
    @FXML
    private TableColumn<?, ?> ColCNPJ;
    @FXML
    private TableColumn<?, ?> ColFornecedor;
    @FXML
    private TableColumn<?, ?> ColIE;
    @FXML
    private TableColumn<?, ?> ColTelefone;
    @FXML
    private TableColumn<?, ?> ColEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NovoFornecedor(ActionEvent event) {
    }

    @FXML
    private void EditarFornecedor(ActionEvent event) {
    }

    @FXML
    private void RemoverFornecedor(ActionEvent event) {
    }

    @FXML
    private void SalvarFornecedor(ActionEvent event) {
    }

    @FXML
    private void CancelarFornecedor(ActionEvent event) {
    }

    @FXML
    private void evtCnpjDigitado(KeyEvent event) {
    }

    @FXML
    private void FiltrarFornecedor(KeyEvent event) {
    }
    
}
