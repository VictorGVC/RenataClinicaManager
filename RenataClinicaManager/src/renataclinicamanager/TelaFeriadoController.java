package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOFeriado;
import db.Models.Feriado;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaFeriadoController implements Initializable 
{
    private DateTimeFormatter form;
    private Feriado feratual;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private Pane pndados;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableColumn<Feriado, String> colnome;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private TableColumn<Feriado, String> coldata;
    @FXML
    private JFXDatePicker dpdata;
    @FXML
    private TableView<Feriado> tvferiado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setMascaras();
        initColTb();
        estado(true);
        initData();
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }  
    
    private void initData()
    {
        dpdata.setValue(LocalDate.now());
    }
    
    private void initColTb() 
    {
        coldata.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getData().format(form)));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
    }
    
    private void estado(boolean b) 
    {
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        btnovo.setDisable(!b);
        txfiltro.setDisable(!b);
        if(b)
            pnfiltro.setStyle(null);
        else
        {
            pnfiltro.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent;");
        }
        carregaTabela("");
    }
    
    private void carregaTabela(String filtro) 
    {
        DAOFeriado dal = new DAOFeriado();
        List<Feriado> res = dal.getList(filtro);
        ObservableList<Feriado> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvferiado.setItems(modelo);
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        dpdata.setValue(LocalDate.now());
    }
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: green;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    private void miniAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: red;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    private void setMascaras() 
    {
        MaskFieldUtil.maxField(txnome, 40);
        MaskFieldUtil.dateField(dpdata.getEditor());
        MaskFieldUtil.maxField(txfiltro, 40);
    }

    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        txnome.requestFocus();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        if(tvferiado.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txnome.requestFocus();
        }
        else
            miniAlert("Selecione um paciente!");
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvferiado.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclus??o!");
            a.setTitle("Exclus??o");
            a.setContentText("Confirma a exclus??o");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOFeriado dal = new DAOFeriado();
                Feriado f;
                f = tvferiado.getSelectionModel().getSelectedItem();
                if(dal.apagar(f.getCod()))
                    miniGAlert("Apagado com sucesso!");
                else
                { 
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Erro ao apagar!");
                    a.showAndWait();
                }
                carregaTabela("");
                limparCampos();
            }
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txnome.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txnome, "RED");
        }
        if(flag)
        {
            a.setContentText("Campos obrigat??rios n??o preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else
        {
            Feriado f = new Feriado(txnome.getText(),dpdata.getValue());
            DAOFeriado dal = new DAOFeriado();

            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                if(dal.gravar(f))
                {
                    miniGAlert("Salvo com sucesso!");
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao gravar!");
                    a.showAndWait();
                }
            }
            else
            {
                f.setCod(feratual.getCod());
                if(dal.alterar(f))
                {
                    miniGAlert("Alterado com sucesso!");
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("erro ao gravar");
                    a.showAndWait();
                }
            }
            clkTFiltro(null);
        }
    }
    
    private void setNormalColor()
    {
        txnome.setFocusColor(null);
        txnome.setUnFocusColor(null);
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) 
    {
        if (!pndados.isDisabled())
        {
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tvferiado.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvferiado.getSelectionModel().getSelectedItem() != null)
            {
                
                Feriado f = (Feriado)tvferiado.getSelectionModel().getSelectedItem();
                
                dpdata.setValue(f.getData());
                txnome.setText(f.getNome());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                feratual = f;
            }
        }
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        carregaTabela("UPPER(fer_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
    }
}