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
import javafx.geometry.Insets;
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
public class TelaRecebimentosController implements Initializable {

    public static Conta conar,conr;
    private DateTimeFormatter form;
    
    // A Receber
    @FXML
    private Tab tabreceber;
    @FXML
    private TableView<Conta> tvrecebimentosar;
    @FXML
    private SplitPane pnprincipalap;
    @FXML
    private HBox pnbotoesp;
    @FXML
    private Pane pnfiltrosp;
    @FXML
    private HBox pnbotoesap1;
    @FXML
    private JFXButton btreceber;
    @FXML
    private VBox pnpesquisaar;
    @FXML
    private Pane pnfiltrosar;
    @FXML
    private JFXComboBox<String> cbcategoriaar;
    @FXML
    private JFXTextField txfiltroar;
    @FXML
    private JFXDatePicker dpdatainicialar;
    @FXML
    private JFXDatePicker dpdatafinalar;
    @FXML
    private TableColumn<Conta, Integer> colcodar;
    @FXML
    private TableColumn<Conta, String> colpacientear;
    @FXML
    private TableColumn<Conta, Integer> colparcelaar;
    @FXML
    private TableColumn<Conta, String> colvalorar;
    @FXML
    private TableColumn<Conta, String> coldatavencar;
    @FXML
    private TableColumn<Conta, String> colcontatoar;
    @FXML
    private TableColumn<Conta, String> coltratamentor;
    
    //Recebidas
    @FXML
    private Tab tabrecebidas;
    @FXML
    private JFXComboBox<String> cbcategoriar;
    @FXML
    private JFXTextField txfiltror;
    @FXML
    private JFXDatePicker dpdatainicialr;
    @FXML
    private JFXDatePicker dpdatafinalr;
    @FXML
    private TableColumn<Conta, Integer> colcodr;
    @FXML
    private TableColumn<Conta, String> colpacienter;
    @FXML
    private TableColumn<Conta, Integer> colparcelar;
    @FXML
    private TableColumn<Conta, String> colvalorr;
    @FXML
    private TableColumn<Conta, String> coldatapagamentor;
    @FXML
    private TableColumn<Conta, String> colcontator;
    @FXML
    private JFXButton btestornar;
    @FXML
    private SplitPane pnprincipalp;
    @FXML
    private TableView<Conta> tvrecebimentosr;
    @FXML
    private VBox pnpesquisar;
    @FXML
    private TableColumn<Conta, String> coltratamentoar;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initDateFormat();
        initCbFiltro();
        initColAReceber();
        initColRecebidas();
        initDates();
        clkTFiltroar(null);
        clkTFiltror(null);
    }   
    
    private void initDateFormat() 
    {
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
    
    private void initDates()
    {
        dpdatafinalar.setValue(LocalDate.now().plusMonths(5));
        dpdatafinalr.setValue(LocalDate.now().plusMonths(5));
        dpdatainicialar.setValue(LocalDate.now().minusMonths(3));
        dpdatainicialr.setValue(LocalDate.now().minusMonths(3));
    }
    
    private void initCbFiltro()
    {
        List<String> l = new ArrayList();
        l.add("Tipo");
        l.add("Paciente");
        
        cbcategoriar.setItems(FXCollections.observableArrayList(l));
        cbcategoriaar.setItems(FXCollections.observableArrayList(l));
                
        cbcategoriar.getSelectionModel().select(0);
        cbcategoriaar.getSelectionModel().select(0);
    }
    
    private void initColAReceber() 
    {
        colcodar.setCellValueFactory(new PropertyValueFactory("codigo"));
        colpacientear.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colparcelaar.setCellValueFactory(new PropertyValueFactory("numero"));
        colvalorar.setCellValueFactory((v) -> new SimpleStringProperty(""+String.format("%.2f", v.getValue().getValor())));
        coldatavencar.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getDtvencimento().format(form)));
        coltratamentoar.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getTratamento().getNome()));
        colcontatoar.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getTelefone()));
    }
    
    private void initColRecebidas() 
    {
        colcodr.setCellValueFactory(new PropertyValueFactory("codigo"));
        colpacienter.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colparcelar.setCellValueFactory(new PropertyValueFactory("numero"));
        colvalorr.setCellValueFactory((v) -> new SimpleStringProperty(""+String.format("%.2f", v.getValue().getValor())));
        coldatapagamentor.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getDtpagamento().format(form)));
        coltratamentor.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getTratamento().getNome()));
        colcontator.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getTelefone()));
    }
    
    private void carregaTabelaar(String filtro)
    {
        DAOConta dc = new DAOConta();
        List<Conta> res = dc.getContasR(filtro);
        ObservableList<Conta> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebimentosar.setItems(modelo);
        tvrecebimentosar.refresh();
        tvrecebimentosar.getItems();
    }
    
    private void carregaTabelar(String filtro)
    {
        DAOConta dc = new DAOConta();
        List<Conta> res = dc.getContasR(filtro);
        ObservableList<Conta> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebimentosr.setItems(modelo);
        tvrecebimentosr.refresh();
    }
    
    private void miniGAlert(String txt, Pane p)
    {
        JFXSnackbar sb = new JFXSnackbar(p); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: green;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    @FXML
    private void clkBtEstornar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvrecebimentosr.getSelectionModel().getSelectedIndex() != -1)
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
                if(dal.estornarp(conr))
                    miniGAlert("Estornado com sucesso!", pnpesquisar);
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
                clkTFiltroar(null);
                clkTFiltror(null);
            }
        }
    }

    @FXML
    private void clkBtReceber(ActionEvent event) throws IOException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvrecebimentosar.getSelectionModel().getSelectedIndex() != -1)
        {
            TelaConfirmarPagamentoController controller = new TelaConfirmarPagamentoController();
            controller.setContaR(conar);
            
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
                miniGAlert("Recebido com sucesso!", pnpesquisaar);
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
            clkTFiltroar(null);
            clkTFiltror(null);
        }
    }

    @FXML
    private void clkTabelaar(MouseEvent event) 
    {
        if(cbcategoriaar.getSelectionModel().getSelectedIndex() != -1)
            conar = tvrecebimentosar.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clkTabelar(MouseEvent event) 
    {
        if(cbcategoriar.getSelectionModel().getSelectedIndex() != -1)
            conr = tvrecebimentosr.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clkTFiltroard(ActionEvent event) 
    {
        clkTFiltroar(null);
    }

    @FXML
    private void clkTFiltroar(KeyEvent event) 
    {
        if(cbcategoriaar.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriaar.getSelectionModel().getSelectedIndex()) 
            {
                case 1:
                    carregaTabelaar("INNER JOIN pessoatratamento pt ON pt.pt_cod = c.pt_cod " +
                            "INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod " +
                            "INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(p.pac_nome) LIKE '%" 
                           + txfiltroar.getText().toUpperCase() + "%' AND c.rec_dtvencimento >= '"+dpdatainicialar.getValue()+"' AND c.rec_dtvencimento <= '"+dpdatafinalar.getValue()+"' "
                                   + "AND c.rec_dtrecebimento IS NULL ORDER BY rec_dtvencimento");
                    break;
                case 0:
                    carregaTabelaar("INNER JOIN pessoatratamento pt ON pt.pt_cod = c.pt_cod " +
                            "INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod " +
                            "INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(t.tra_nome) LIKE '%" 
                           + txfiltroar.getText().toUpperCase() + "%' AND c.rec_dtvencimento >= '"+dpdatainicialar.getValue()+"' AND c.rec_dtvencimento <= '"+dpdatafinalar.getValue()+"' "
                                    + "AND c.rec_dtrecebimento IS NULL ORDER BY rec_dtvencimento");
                    break;
            }
        }
    }

    @FXML
    private void clkTFiltrord(ActionEvent event) 
    {
        clkTFiltror(null);
    }

    @FXML
    private void clkTFiltror(KeyEvent event) 
    {
        if(cbcategoriar.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoriar.getSelectionModel().getSelectedIndex()) 
            {
                case 1:
                    carregaTabelar("INNER JOIN pessoatratamento pt ON pt.pt_cod = c.pt_cod "
                            + "INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod"
                            + "INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(p.pac_nome) LIKE '%" 
                           + txfiltror.getText().toUpperCase() + "%' AND c.rec_dtvencimento >= '"+dpdatainicialr.getValue()+"' AND c.rec_dtvencimento <= '"+dpdatafinalr.getValue()+"' "
                                   + "AND c.rec_dtrecebimento IS NOT NULL ORDER BY rec_dtvencimento");
                    break;
                case 0:
                    carregaTabelar("INNER JOIN pessoatratamento pt ON pt.pt_cod = c.pt_cod " +
                            "INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod " +
                            "INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(t.tra_nome) LIKE '%" 
                           + txfiltror.getText().toUpperCase() + "%' AND c.rec_dtvencimento >= '"+dpdatainicialr.getValue()+"' AND c.rec_dtvencimento <= '"+dpdatafinalr.getValue()+"' "
                                    + "AND c.rec_dtrecebimento IS NOT NULL ORDER BY rec_dtvencimento");
                    break;
            }
        }
    }

    @FXML
    private void clkBtAddRecebimento(ActionEvent event) throws IOException 
    {
        TelaGerarContasReceberController.pt = null;
        Parent root = FXMLLoader.load(getClass().getResource("TelaGerarContasReceber.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(804);
        stage.setMaxHeight(628);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Adicionar Recebimento");
        stage.setScene(scene);
        stage.showAndWait();
        clkTFiltroar(null);
    }
}
