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

    @FXML
    private Tab tabreceber;
    @FXML
    private Tab tabrecebidas;
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btquitar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados11;
    @FXML
    private JFXTextField tparcelas11;
    @FXML
    private JFXTextField tcodigo11;
    @FXML
    private JFXTextField tvalor11;
    @FXML
    private JFXTextField tcontato11;
    @FXML
    private JFXDatePicker dpdatapag1;
    @FXML
    private JFXTextField ttipo11;
    @FXML
    private JFXTextField tfiltro1;
    @FXML
    private SplitPane pnprincipal1;
    @FXML
    private HBox pnbotoes1;
    @FXML
    private JFXButton btestornar;
    @FXML
    private JFXButton btcancelar1;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private Pane pnfiltros1;
    @FXML
    private JFXComboBox<?> cbcategoria1;
    @FXML
    private JFXDatePicker dpdatainicial1;
    @FXML
    private JFXDatePicker dpdatafinal1;
    @FXML
    private JFXTextField tcodvenda11;
    @FXML
    private Pane pnfiltros11;
    @FXML
    private JFXComboBox<?> cbcategoria11;
    @FXML
    private JFXDatePicker dpdatainicial11;
    @FXML
    private JFXDatePicker dpdatafinal11;
    @FXML
    private TableView<?> tvrecebimentos11;
    @FXML
    private TableColumn<?, ?> colcod11;
    @FXML
    private TableColumn<?, ?> colvenda11;
    @FXML
    private TableColumn<?, ?> colparcela11;
    @FXML
    private TableColumn<?, ?> colvalor11;
    @FXML
    private TableColumn<?, ?> coldatavenc11;
    @FXML
    private TableColumn<?, ?> coltipo11;
    @FXML
    private TableColumn<?, ?> colcontato11;
    @FXML
    private Pane pndados1;
    @FXML
    private JFXTextField tparcelas1;
    @FXML
    private JFXTextField tcodigo1;
    @FXML
    private JFXTextField tcodvenda1;
    @FXML
    private JFXTextField tvalor1;
    @FXML
    private JFXTextField tcontato1;
    @FXML
    private JFXDatePicker dpdatapag;
    @FXML
    private JFXTextField ttipo1;
    @FXML
    private JFXTextField tfiltrorecebidos;
    @FXML
    private TableView<?> tvrecebimentos1;
    @FXML
    private TableColumn<?, ?> colcod1;
    @FXML
    private TableColumn<?, ?> colvenda1;
    @FXML
    private TableColumn<?, ?> colparcela1;
    @FXML
    private TableColumn<?, ?> colvalor1;
    @FXML
    private TableColumn<?, ?> coldatavenc1;
    @FXML
    private TableColumn<?, ?> coltipo1;
    @FXML
    private TableColumn<?, ?> colcontato1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    @FXML
    private void clkBtQuitar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkTFiltro1(KeyEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkBtEstornar(ActionEvent event) {
    }

    
}
