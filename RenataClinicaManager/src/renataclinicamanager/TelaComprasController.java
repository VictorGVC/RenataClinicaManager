/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOCompra;
import db.DAL.DAOFornecedor;
import db.DAL.DAOMaterial;
import db.Models.Compra;
import db.Models.Fornecedor;
import db.Models.Material;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaComprasController implements Initializable {

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
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXDatePicker dtpInicio;
    @FXML
    private JFXTextField txtotal;
    @FXML
    private JFXComboBox<Fornecedor> cbfornecedor;
    @FXML
    private JFXButton btadditem;
    @FXML
    private JFXButton btremoveitem;
    @FXML
    private JFXComboBox<Material> cbproduto;
    @FXML
    private JFXTextField txquantidade;
    @FXML
    private JFXTextField txpreco;
    @FXML
    private TableView<Material> tvprodutos;
    @FXML
    private TableColumn<Material, String> colproduto;
    @FXML
    private TableColumn<Material, Double> colpreco;
    @FXML
    private TableColumn<Material, Integer> colquantidade;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private JFXRadioButton rbaberto;
    @FXML
    private JFXDatePicker dtpfinal;
    @FXML
    private TableView<Compra> tvcompra;
    @FXML
    private TableColumn<Compra, Integer> colcod;
    @FXML
    private TableColumn<Compra, LocalDate> coldata;
    @FXML
    private TableColumn<Compra, String> colfornecedor;
    @FXML
    private TableColumn<Compra, String> colstatus;
    @FXML
    private TableColumn<Compra, Double> coltotal;
    @FXML
    private HBox pnfiltro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setMascaras();
        initColTb();
        estado(true);
        carregaFornecedores("");
        carregaMateriais("");
    }    
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
    }
    
    private void setMascaras()
    {
        MaskFieldUtil.monetaryField(txtotal);
        MaskFieldUtil.numericField(txquantidade);
        MaskFieldUtil.maxField(txquantidade, 7);
        MaskFieldUtil.monetaryField(txpreco);
        MaskFieldUtil.maxField(txpreco, 9);
    }
    
    private void initColTb() 
    {
        colproduto.setCellValueFactory(new PropertyValueFactory("nome"));
        colquantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
        colpreco.setCellValueFactory(new PropertyValueFactory("valor"));
        
        //SETAR OS VALORES CORRETOS DEPOIS
        colcod.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colfornecedor.setCellValueFactory(new PropertyValueFactory("cnpj"));
        coldata.setCellValueFactory(new PropertyValueFactory("cnpj"));
        coltotal.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colstatus.setCellValueFactory(new PropertyValueFactory("cnpj"));
    }
    
    private void estado(boolean b) 
    {
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btnovo.setDisable(!b);
        txfiltro.setDisable(!b);
        if(b)
            pnfiltro.setStyle(null);
        else
        {
            pnfiltro.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent;");
        }
      
        //carregaTabela("");
    }
    
    private void carregaTabela(String filtro) 
    {
        DAOCompra dal = new DAOCompra();
        List<Compra> res = dal.getList(filtro);
        ObservableList<Compra> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvcompra.setItems(modelo);
    }
    
    private void carregaFornecedores(String filtro)
    {
        DAOFornecedor dal = new DAOFornecedor();
        List<Fornecedor> res = dal.getList(filtro.toUpperCase());
        ObservableList<Fornecedor> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbfornecedor.setItems(modelo);
    }
    
    private void carregaMateriais(String filtro)
    {
        DAOMaterial dal = new DAOMaterial();
        List<Material> res = dal.getList(filtro.toUpperCase());
        ObservableList<Material> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbproduto.setItems(modelo);
    }

    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        cbfornecedor.requestFocus();
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        
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
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }


    @FXML
    private void dtpData(ActionEvent event) {
    }

    @FXML
    private void clkAberto(ActionEvent event) {
    }

    @FXML
    private void preencheFornecedores(KeyEvent event) 
    {
        carregaFornecedores("UPPER(for_nome) LIKE '%" + cbfornecedor.getValue() + "%'");
    }

    @FXML
    private void preencheMateriais(KeyEvent event) 
    {
        carregaMateriais("UPPER(mat_nome) LIKE '%" + cbproduto.getValue() + "%'");
    }
    
}
