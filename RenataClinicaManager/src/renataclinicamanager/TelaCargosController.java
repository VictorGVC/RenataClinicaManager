/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOCargo;
import db.Models.Cargo;
import java.net.URL;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONObject;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaCargosController implements Initializable 
{
    int codatual;
    JSONArray ja;
    
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
    private TableColumn<Cargo, String> colnome;
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
    private JFXListView<String> lvaccess;
    @FXML
    private JFXListView<String> lvnaccess;
    @FXML
    private TableView<Cargo> tvcargo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ja = new JSONArray();
        setMascaras();
        initColTb();
        estado(true);
        setJson();
    }    
    
    private void initColTb() 
    {
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
    
    private void setJson()
    {
        ja.clear();
        JSONObject jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Pacientes");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Fornecedores");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Funcionários");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Materiais");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Tratamentos");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Feriados");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Receituário");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Atestado");
        ja.put(jo);
        jo = new JSONObject();
        jo.put("acesso", "y");
        jo.put("nome", "Cargos");
        ja.put(jo);
    }
    
    private void setListJson()
    {
        List ly = new ArrayList();
        List ln = new ArrayList();
        for (int i = 0; i < ja.length(); i++) 
        {
            JSONObject jo = ja.getJSONObject(i);
            if(jo.get("acesso").equals("y"))
                ly.add(jo.get("nome").toString());
            else
                ln.add(jo.get("nome").toString());
        }
        
        ObservableList<String> oly;
        oly = FXCollections.observableArrayList(ly);
        
        ObservableList<String> oln;
        oln = FXCollections.observableArrayList(ln);
        lvaccess.setItems(oly);
        lvnaccess.setItems(oln);
    }
    
    private void carregaTabela(String filtro) 
    {
        DAOCargo dal = new DAOCargo();
        List<Cargo> res = dal.getList(filtro);
        ObservableList<Cargo> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvcargo.setItems(modelo);
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        lvaccess.getItems().clear();
        lvnaccess.getItems().clear();
        setJson();
    }
    
    private void setMascaras() 
    {
        MaskFieldUtil.maxField(txnome, 40);
        MaskFieldUtil.maxField(txfiltro, 40);
    }

    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        setJson();
        setListJson();
        txnome.requestFocus();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        if(tvcargo.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txnome.requestFocus();
        }
        else
        {
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum cargo!")));
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvcargo.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOCargo dal = new DAOCargo();
                Cargo c;
                c = tvcargo.getSelectionModel().getSelectedItem();
                String result = dal.apagar(c);
                if(result.isEmpty())
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
                    a.setContentText(result);
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
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else 
        {
            Cargo c = new Cargo(ja,txnome.getText());
            DAOCargo dal = new DAOCargo();
            
            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                if(dal.gravar(c))
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
                c.setCod(codatual);
                String error = dal.alterar(c);
                if(error.isEmpty())
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
                    a.setContentText(error);
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
        if(tvcargo.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvcargo.getSelectionModel().getSelectedItem() != null)
            {
                ja.clear();
                Cargo c = new Cargo();
                c = (Cargo)tvcargo.getSelectionModel().getSelectedItem();
                
                txnome.setText(c.getNome());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                codatual = c.getCod();
                ja = c.getAcesso();
                setListJson();
            }
        }
    }

    @FXML
    private void clkBtNa(MouseEvent event) 
    {
        String nome = lvnaccess.getSelectionModel().getSelectedItem();
        int index = lvnaccess.getSelectionModel().getSelectedIndex();
        
        boolean b = true;
        for (int i = 0; b && i < ja.length(); i++) 
        {
            JSONObject jo = ja.getJSONObject(i);
            if(jo.get("nome").equals(nome))
                ja.getJSONObject(i).put("acesso", "y");
        }
        lvnaccess.getItems().remove(index);
        lvaccess.getItems().add(nome);
        lvnaccess.getSelectionModel().select(-1);
    }

    @FXML
    private void clkBtAc(MouseEvent event) 
    {
        String nome = lvaccess.getSelectionModel().getSelectedItem();
        int index = lvaccess.getSelectionModel().getSelectedIndex();
        
        boolean b = true;
        for (int i = 0; b && i < ja.length(); i++) 
        {
            JSONObject jo = ja.getJSONObject(i);
            if(jo.get("nome").equals(nome))
                ja.getJSONObject(i).put("acesso", "n");
        }
        lvaccess.getItems().remove(index);
        lvnaccess.getItems().add(nome);
        lvaccess.getSelectionModel().select(-1);
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        carregaTabela("UPPER(car_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
    }
}
