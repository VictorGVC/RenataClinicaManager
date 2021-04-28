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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaPagamentosController implements Initializable {

    @FXML
    private Tab tabNaoPago;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btQuitar;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    @FXML
    private CheckBox ckVencidos;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private Label dtvencimentop;
    @FXML
    private JFXDatePicker dtPagamentosinicial;
    @FXML
    private JFXDatePicker dtPagamentosfinal;
    @FXML
    private JFXButton btLimpar;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<?> tvContas;
    @FXML
    private TableColumn<?, ?> colCod;
    @FXML
    private TableColumn<?, ?> colParcela;
    @FXML
    private TableColumn<?, ?> colFornecedor;
    @FXML
    private TableColumn<?, ?> colValor;
    @FXML
    private TableColumn<?, ?> colVencimento;
    @FXML
    private TableColumn<?, ?> colPag;
    @FXML
    private TableColumn<?, ?> colTipo;
    @FXML
    private TableColumn<?, ?> colContato;
    @FXML
    private Tab tabPago;
    @FXML
    private AnchorPane pnprincipal1;
    @FXML
    private HBox pnbotoes1;
    @FXML
    private JFXButton btExtornar;
    @FXML
    private JFXButton btvoltarPag;
    @FXML
    private Pane pndados1;
    @FXML
    private JFXTextField txfiltroPag;
    @FXML
    private Label lbDtPagamentoPag;
    @FXML
    private JFXButton btLimparPag;
    @FXML
    private Label lbDTVencimentoPag;
    @FXML
    private JFXDatePicker dtVencimentoPagFinal;
    @FXML
    private JFXDatePicker dtVencimentoPagInicial;
    @FXML
    private JFXDatePicker dtPagamentosPagFinal;
    @FXML
    private JFXDatePicker dtPagamentosPagInicial;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private TableView<?> tvContasPag;
    @FXML
    private TableColumn<?, ?> colCodPag;
    @FXML
    private TableColumn<?, ?> colParcelaPag;
    @FXML
    private TableColumn<?, ?> colFornecedorPag;
    @FXML
    private TableColumn<?, ?> colValorPag;
    @FXML
    private TableColumn<?, ?> colVencimentoPag;
    @FXML
    private TableColumn<?, ?> colPagPag;
    @FXML
    private TableColumn<?, ?> colTipoPag;
    @FXML
    private TableColumn<?, ?> colContatoPag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkbtQuitar(ActionEvent event) {
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
    }

    @FXML
    private void clickCKVenc(ActionEvent event) {
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void clkDTVencimento(ActionEvent event) {
    }

    @FXML
    private void LimparTelaNaoPag(ActionEvent event) {
    }

    @FXML
    private void clkbtExtornar(ActionEvent event) {
    }

    @FXML
    private void clkTFiltroPag(KeyEvent event) {
    }

    @FXML
    private void LimparTelaPag(ActionEvent event) {
    }

    @FXML
    private void clkDTVencimentoPag(ActionEvent event) {
    }

    @FXML
    private void clkDTPagamento(ActionEvent event) {
    }
    
}
