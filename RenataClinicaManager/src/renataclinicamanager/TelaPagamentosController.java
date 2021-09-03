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
import db.DAL.DAOConta;
import db.Models.Conta;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class TelaPagamentosController implements Initializable {

    
    //CONTAS A PAGAR
    @FXML
    private Tab tabreceber;
    @FXML
    private TableView<Conta> tvpagamentosap;
    @FXML
    private TableColumn<Conta, Integer> colcodap;
    @FXML
    private TableColumn<Conta, String> colfornecedorap;
    @FXML
    private TableColumn<Conta, Integer> colparcelaap;
    @FXML
    private TableColumn<Conta, String> colvalorap;
    @FXML
    private TableColumn<Conta, LocalDate> coldatavencap;
    @FXML
    private TableColumn<Conta, String> coltipoap;
    @FXML
    private TableColumn<Conta, String> colcontatoap;
    @FXML
    private SplitPane pnprincipalap;
    @FXML
    private HBox pnbotoesap;
    @FXML
    private Pane pndadosap;
    @FXML
    private VBox pnpesquisaap;
    @FXML
    private Pane pnfiltrosap;
    @FXML
    private JFXComboBox<String> cbcategoriaap;
    @FXML
    private JFXTextField txfiltroap;
    @FXML
    private JFXDatePicker dpdatainicialap;
    @FXML
    private JFXDatePicker dpdatafinalap;
    @FXML
    private JFXButton btadddespesa;
    
    //CONTAS PAGAS
    @FXML
    private JFXButton btestornar;
    @FXML
    private TableView<Conta> tvpagamentosp;
    @FXML
    private TableColumn<Conta, Integer> colcodp;
    @FXML
    private TableColumn<Conta, String> colfornecedorp;
    @FXML
    private TableColumn<Conta, Integer> colparcelap;
    @FXML
    private TableColumn<Conta, String> colvalorp;
    @FXML
    private TableColumn<Conta, String> coltipop;
    @FXML
    private TableColumn<Conta, String> colcontatop;
    @FXML
    private SplitPane pnprincipalp;
    @FXML
    private HBox pnbotoesp;
    @FXML
    private VBox pnpesquisap;
    @FXML
    private Pane pnfiltrosp;
    @FXML
    private JFXComboBox<String> cbcategoriap;
    @FXML
    private JFXTextField txfiltrop;
    @FXML
    private JFXDatePicker dpdatainicialp;
    @FXML
    private JFXDatePicker dpdatafinalp;
    @FXML
    private JFXButton btliquidar;
    @FXML
    private Tab tabpagas;
    @FXML
    private TableColumn<Conta, LocalDate> coldatapagamentop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initCbFiltro();
        initColAPagar();
        initColPagas();
        initDates();
        clkTFiltrop(null);
        clkTFiltroap(null);
    }  
    
    private void initDates()
    {
        dpdatafinalap.setValue(LocalDate.now().plusMonths(5));
        dpdatafinalp.setValue(LocalDate.now().plusMonths(5));
        dpdatainicialap.setValue(LocalDate.now().minusMonths(3));
        dpdatainicialp.setValue(LocalDate.now().minusMonths(3));
    }
    
    private void initCbFiltro()
    {
        List<String> l = new ArrayList();
        l.add("Fornecedor");
        l.add("Tipo");
        
        cbcategoriap.setItems(FXCollections.observableArrayList(l));
        cbcategoriaap.setItems(FXCollections.observableArrayList(l));
                
        cbcategoriap.getSelectionModel().select(0);
        cbcategoriaap.getSelectionModel().select(0);
    }
    
    private void initColAPagar() 
    {
        colcodap.setCellValueFactory(new PropertyValueFactory("codigo"));
        colfornecedorap.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getNome()));
        colparcelaap.setCellValueFactory(new PropertyValueFactory("numero"));
        colvalorap.setCellValueFactory((v) -> new SimpleStringProperty(""+String.format("%.2f", v.getValue().getValor())));
        coldatavencap.setCellValueFactory(new PropertyValueFactory("dtvencimento"));
        coltipoap.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontatoap.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getTelefone()));
    }
    
    private void initColPagas() 
    {
        colcodp.setCellValueFactory(new PropertyValueFactory("codigo"));
        colfornecedorp.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getNome()));
        colparcelap.setCellValueFactory(new PropertyValueFactory("numero"));
        colvalorap.setCellValueFactory((v) -> new SimpleStringProperty(""+String.format("%.2f", v.getValue().getValor())));
        coldatapagamentop.setCellValueFactory(new PropertyValueFactory("dtpagamento"));
        coltipop.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontatop.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getTelefone()));
    }
    
    private void carregaTabelaap(String filtro)
    {
        DAOConta dc = new DAOConta();
        List<Conta> res = dc.getContas(filtro);
        ObservableList<Conta> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvpagamentosap.setItems(modelo);
        tvpagamentosap.refresh();
    }

    @FXML
    private void clkBtLiquidar(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkBtEstornar(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkTabelaap(MouseEvent event) 
    {
        
    }

    @FXML
    private void clkTabelap(MouseEvent event) 
    {
        
    }

    @FXML
    private void clkBtAddDespesa(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkTFiltroap(KeyEvent event) 
    {
        if(cbcategoriaap.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriaap.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                    carregaTabelaap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialap.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalap.getValue()+"'");
                    break;
                case 1:
                    carregaTabelaap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(c.pag_tipo) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialap.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalap.getValue()+"'");
                    break;
            }
        }
    }
    

    @FXML
    private void clkTFiltroapd(ActionEvent event) 
    {
        clkTFiltroap(null);
    }

    @FXML
    private void clkTFiltropd(ActionEvent event) 
    {
        clkTFiltrop(null);
    }

    @FXML
    private void clkTFiltrop(KeyEvent event) 
    {
        if(cbcategoriaap.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriaap.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                    carregaTabelaap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialp.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalp.getValue()+"'");
                    break;
                case 1:
                    carregaTabelaap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(c.pag_tipo) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialp.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalp.getValue()+"'");
                    break;
            }
        }
    }
}
