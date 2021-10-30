/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConfig;
import db.DAL.DAOConta;
import db.Models.Conta;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaPagamentosController implements Initializable {

    static Conta conap,conp;
    DateTimeFormatter form;
    
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
    private TableColumn<Conta, String> coldatavencap;
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
    private TableColumn<Conta, String> coldatapagamentop;
    @FXML
    private Tab tabapagar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initDateFormat();
        initCbFiltro();
        initColAPagar();
        initColPagas();
        initDates();
        clkTFiltroap(null);
        clkTFiltrop(null);
    }  
    
    private void initDateFormat() 
    {
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
        l.add("Tipo");
        l.add("Fornecedor");
        
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
        coldatavencap.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getDtvencimento().format(form)));
        coltipoap.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontatoap.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getTelefone()));
    }
    
    private void initColPagas() 
    {
        colcodp.setCellValueFactory(new PropertyValueFactory("codigo"));
        colfornecedorp.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getNome()));
        colparcelap.setCellValueFactory(new PropertyValueFactory("numero"));
        colvalorp.setCellValueFactory((v) -> new SimpleStringProperty(""+String.format("%.2f", v.getValue().getValor())));
        coldatapagamentop.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getDtpagamento().format(form)));
        coltipop.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontatop.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getTelefone()));
    }
    
    private void carregaTabelaap(String filtro)
    {
        DAOConta dc = new DAOConta();
        List<Conta> res = dc.getContasP(filtro);
        ObservableList<Conta> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvpagamentosap.setItems(modelo);
        tvpagamentosap.refresh();
        tvpagamentosap.getItems();
    }
    
    private void carregaTabelap(String filtro)
    {
        DAOConta dc = new DAOConta();
        List<Conta> res = dc.getContasP(filtro);
        ObservableList<Conta> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvpagamentosp.setItems(modelo);
        tvpagamentosp.refresh();
    }

    @FXML
    private void clkBtLiquidar(ActionEvent event) throws IOException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvpagamentosap.getSelectionModel().getSelectedIndex() != -1)
        {
            TelaConfirmarPagamentoController controller = new TelaConfirmarPagamentoController();
            controller.setContaP(conap);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaConfirmarPagamento.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
            
            char conf = controller.getConfirmado();
            
            if(conf == 'a')
            {      
                JFXSnackbar sb = new JFXSnackbar(pnpesquisaap); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Pago com Sucesso!")));
            }
            else if(conf == 'n')
            { 
                a.setAlertType(Alert.AlertType.ERROR);
                a.getButtonTypes().clear();
                a.getButtonTypes().add(ButtonType.OK);
                a.setHeaderText("ERRO");
                a.setTitle("ERRO!");
                a.setContentText("Erro ao Liquidar!");
                a.showAndWait();
            }
            clkTFiltroap(null);
            clkTFiltrop(null);
        }
    }

    @FXML
    private void clkBtEstornar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvpagamentosp.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Estornar!");
            a.setTitle("Estornar");
            a.setContentText("Confirma Estornar a Parcela?");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOConta dal = new DAOConta();
                if(dal.estornarp(conp))
                {      
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisaap); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Estornado com Sucesso!")));
                }
                else
                { 
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Erro ao Estornar!");
                    a.showAndWait();
                }
                clkTFiltroap(null);
                clkTFiltrop(null);
            }
        }
    }

    @FXML
    private void clkBtAddDespesa(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAddDespesa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Despesa");
        stage.setScene(scene);
        stage.show();
        
        clkTFiltroap(null);
        clkTFiltrop(null);
    }

    @FXML
    private void clkTFiltroap(KeyEvent event) 
    {
        if(cbcategoriaap.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriaap.getSelectionModel().getSelectedIndex()) 
            {
                case 1:
                    carregaTabelaap("LEFT JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltroap.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialap.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalap.getValue()+"' "
                            + "AND c.pag_dtpagamento IS NULL");
                    break;
                case 0:
                    carregaTabelaap("LEFT JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(c.pag_tipo) LIKE '%" 
                           + txfiltroap.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialap.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalap.getValue()+"' "
                            + "AND c.pag_dtpagamento IS NULL");
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
        if(cbcategoriap.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriap.getSelectionModel().getSelectedIndex()) 
            {
                case 1:
                    carregaTabelap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialp.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalp.getValue()+"' "
                                   + "AND c.pag_dtpagamento IS NOT NULL");
                    break;
                case 0:
                    carregaTabelap("INNER JOIN fornecedor f ON f.for_cnpj = c.for_cnpj WHERE UPPER(c.pag_tipo) LIKE '%" 
                           + txfiltrop.getText().toUpperCase() + "%' AND c.pag_dtvencimento >= '"+dpdatainicialp.getValue()+"' AND c.pag_dtvencimento <= '"+dpdatafinalp.getValue()+"' "
                                    + "AND c.pag_dtpagamento IS NOT NULL");
                    break;
            }
        }
    }

    @FXML
    private void clkTabelaap(MouseEvent event) 
    {
        if(cbcategoriaap.getSelectionModel().getSelectedIndex() != -1)
            conap = tvpagamentosap.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clkTabelap(MouseEvent event) 
    {
        if(cbcategoriap.getSelectionModel().getSelectedIndex() != -1)
            conp = tvpagamentosp.getSelectionModel().getSelectedItem();
    }
}
