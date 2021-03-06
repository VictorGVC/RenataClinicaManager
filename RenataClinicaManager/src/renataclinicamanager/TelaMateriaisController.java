/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOMaterial;
import db.Models.Material;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import util.Util;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaMateriaisController implements Initializable {

    private int idatual;
    
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
    private Pane pnfiltros;
    @FXML
    private TableColumn<Material, Integer> colcodigo;
    @FXML
    private TableColumn<Material, String> colnome;
    @FXML
    private TableColumn<Material, String> colquantidade;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txqtde;
    @FXML
    private JFXTextField txfiltro;
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
    private TableView<Material> tvmateriais;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMascaras();
        initColTb();
        estado(true);
    }    
    
    private void setMascaras()
    {
        MaskFieldUtil.maxField(txnome, 35);
        MaskFieldUtil.numericField(txqtde);
        MaskFieldUtil.maxField(txqtde, 5);
    }
    
    private void initColTb() 
    {
        colcodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colquantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
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
        DAOMaterial dal = new DAOMaterial();
        List<Material> res = dal.getList(filtro);
        ObservableList<Material> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvmateriais.setItems(modelo);
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        txqtde.setFocusColor(null);
        txqtde.setUnFocusColor(null);
        txnome.setFocusColor(null);
        txnome.setUnFocusColor(null);
    }
    
    private void limparCampos() 
    {
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
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
        if(tvmateriais.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txnome.requestFocus();
        }
        else
            miniAlert("Selecione um material!");
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvmateriais.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclus??o!");
            a.setTitle("Exclus??o");
            a.setContentText("Confirma a exclus??o");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOMaterial dal = new DAOMaterial();
                Material m;
                m = (Material)tvmateriais.getSelectionModel().getSelectedItem();
                if(dal.apagar(idatual))
                    miniGAlert("Excluido com sucesso!");
                else
                { 
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Erro ao apagar");
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
        if(txqtde.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txqtde, "RED");
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

            Material m = new Material(Integer.parseInt(txqtde.getText()),txnome.getText());
            DAOMaterial dal = new DAOMaterial();

            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                if(dal.gravar(m))
                {
                    miniGAlert("Salvo com sucesso!");
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao gravar");
                    a.showAndWait();
                }
            }
            else
            {
                m.setId(idatual);
                if(dal.alterar(m))
                {
                    miniGAlert("Alterado com sucesso!");
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao alterar!");
                    a.showAndWait();
                }
            }
            clkBtFiltro(null);
        }
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
    private void clkBtFiltro(ActionEvent event) 
    {
        carregaTabela("UPPER(mat_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtTabela(MouseEvent event) 
    {
        if(tvmateriais.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvmateriais.getSelectionModel().getSelectedItem() != null)
            {
                Material m = (Material)tvmateriais.getSelectionModel().getSelectedItem();
                
                txnome.setText(m.getNome());
                txqtde.setText(""+m.getQtde());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                idatual = m.getId();
            }
        }
    }
    
}
