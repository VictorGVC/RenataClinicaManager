/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author vicga
 */
public class TelaPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane pntotal;
    @FXML
    private VBox pncabecalho;
    @FXML
    private MenuBar mnbar;
    @FXML
    private MenuItem mifuncionario;
    @FXML
    private MenuItem miclientes;
    @FXML
    private MenuItem mifornecedores;
    @FXML
    private MenuItem miproduto;
    @FXML
    private ToolBar tbatalhos;
    @FXML
    private AnchorPane pnmeio;
    @FXML
    private HBox pnrodape;
    @FXML
    private Label lbnome;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbcep;
    @FXML
    private TableView<?> tvprodutos;
    @FXML
    private TableColumn<?, ?> colprod;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
    }    

    @FXML
    private void clkChamaLogin(ActionEvent event) {
    }

    @FXML
    private void clkOpenFuncionarios(ActionEvent event) {
    }

    @FXML
    private void clkOpenClientes(ActionEvent event) {
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) {
    }

    @FXML
    private void clkOpenProduto(ActionEvent event) {
    }

    @FXML
    private void clkBackup(ActionEvent event) {
        pntotal.getScene().getStylesheets().clear();
        pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
    }

    @FXML
    private void clkRestore(ActionEvent event) {
        pntotal.getScene().getStylesheets().clear();
        pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Bright.css").toExternalForm());
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) {
    }

    @FXML
    private void clkTabelaProdutos(MouseEvent event) {
    }
    
}
