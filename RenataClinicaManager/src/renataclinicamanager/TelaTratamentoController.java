/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOTratamento;
import db.Models.Tratamento;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class TelaTratamentoController implements Initializable 
{
    Tratamento traatual;
    
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
    private TableColumn<Tratamento, String> colnome;
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
    private TableView<Tratamento> tvpaciente;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private TableColumn<Tratamento, Double> colvalor;
    @FXML
    private JFXTextField txvalor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setMascaras();
        initColTb();
        estado(true);
    }  
    
    private void initColTb() 
    {
        colvalor.setCellValueFactory(new PropertyValueFactory("valor"));
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
        DAOTratamento dal = new DAOTratamento();
        List<Tratamento> res = dal.getList(filtro);
        ObservableList<Tratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvpaciente.setItems(modelo);
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
        MaskFieldUtil.maxField(txnome, 30);
        MaskFieldUtil.monetaryField(txvalor);
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
        if(tvpaciente.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txnome.requestFocus();
        }
        else
        {
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum paciente!")));
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvpaciente.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOTratamento dal = new DAOTratamento();
                Tratamento t;
                t = tvpaciente.getSelectionModel().getSelectedItem();
                if(dal.apagar(t.getCod()))
                {      
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Excluído com Sucesso!")));
                }
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
        String id;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txnome.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txnome, "RED");
        }
        if(txvalor.getText().isEmpty())
        {
            
            flag = true;
            setCorAlert(txvalor, "RED");
        }
        if(flag)
        {
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else
        {
            Tratamento t = new Tratamento(txnome.getText(),Double.parseDouble(txvalor.getText().replace(",", ".")));
            DAOTratamento dal = new DAOTratamento();

            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                if(dal.gravar(t))
                {
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
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
                t.setCod(traatual.getCod());
                if(dal.alterar(t))
                {
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
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
        txvalor.setFocusColor(null);
        txvalor.setUnFocusColor(null);
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
        if(tvpaciente.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvpaciente.getSelectionModel().getSelectedItem() != null)
            {
                
                Tratamento t = (Tratamento)tvpaciente.getSelectionModel().getSelectedItem();
                
                txvalor.setText(String.format("%6.2f", t.getValor()));
                txnome.setText(t.getNome());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                traatual = t;
            }
        }
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        carregaTabela("UPPER(tra_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
    }
}