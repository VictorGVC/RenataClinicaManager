/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOFornecedor;
import db.Models.Fornecedor;
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
import javafx.scene.input.KeyEvent;
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
public class TelaFornecedorController implements Initializable {

    Fornecedor foratual;
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
    private TableView<Fornecedor> tvFornecedores;
    @FXML
    private JFXTextField txcnpj;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txtelefone;
    @FXML
    private JFXComboBox<String> cbfiltro;
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
    private TableColumn<Fornecedor, String> colcnpj;
    @FXML
    private TableColumn<Fornecedor, String> colfornecedor;
    @FXML
    private TableColumn<Fornecedor, String> coltelefone;
    @FXML
    private HBox pnfiltro;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setMascaras();
        listaCategoria();
        initColTb();
        estado(true);
        cbfiltro.getSelectionModel().select(1);
    }    
    
    private void setMascaras()
    {
        MaskFieldUtil.cnpjField(txcnpj);
        MaskFieldUtil.maxField(txnome, 35);
        MaskFieldUtil.foneField(txtelefone);
        MaskFieldUtil.maxField(txfiltro, 20);
    }

    private void listaCategoria()
    {
        List<String> list = new ArrayList();
        
        list.add("CNPJ");
        list.add("Fornecedor");
        list.add("Telefone");
        
        cbfiltro.setItems(FXCollections.observableArrayList(list));
    }
    
    private void initColTb() 
    {
        colcnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colfornecedor.setCellValueFactory(new PropertyValueFactory("nome"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
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
        DAOFornecedor dal = new DAOFornecedor();
        List<Fornecedor> res = dal.getList(filtro);
        ObservableList<Fornecedor> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvFornecedores.setItems(modelo);
    }

    @FXML
    private void evtCnpjDigitado(KeyEvent event) 
    {
        JFXSnackbar sb = new JFXSnackbar(pndados);
        if(txcnpj.getText().length() >= 18)
        {
            if(!Util.isCnpj(txcnpj.getText()))
            {
                txcnpj.setFocusColor(Paint.valueOf("RED"));
                Label lb = new Label("CPF inválido!");
                lb.setStyle("-fx-text-fill: RED");
                sb.enqueue(new JFXSnackbar.SnackbarEvent(lb));
            }
            else
                txcnpj.setFocusColor(null);
        }
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
    
    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        txcnpj.requestFocus();
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        txcnpj.setFocusColor(null);
        txcnpj.setUnFocusColor(null);
        txnome.setFocusColor(null);
        txnome.setUnFocusColor(null);
        txtelefone.setFocusColor(null);
        txtelefone.setUnFocusColor(null);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        if(tvFornecedores.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txcnpj.requestFocus();
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
        
        if(tvFornecedores.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOFornecedor dal = new DAOFornecedor();
                Fornecedor f;
                f = (Fornecedor)tvFornecedores.getSelectionModel().getSelectedItem();
                String result = dal.apagar(f);
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
        String id;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        if(txcnpj.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txcnpj, "RED");
        }
        else if(!Util.isCnpj(txcnpj.getText()))
        {
            setCorAlert(txcnpj, "RED");
            a.setContentText("CPF inválido!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        if(txnome.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txnome, "RED");
        }
        if(txtelefone.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txtelefone, "RED");
        }
        if(flag)
        {
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else if(Util.isCnpj(txcnpj.getText()))
        {
            try {id = txcnpj.getText();}
            catch (NumberFormatException e) {id = "";}

            Fornecedor f = new Fornecedor(id, txnome.getText(),
                                    txtelefone.getText());
            DAOFornecedor dal = new DAOFornecedor();

            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                String error = dal.gravar(f);
                if(error.isEmpty())
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
                    a.setContentText(error);
                    a.showAndWait();
                }
            }
            else
            {
                String error = dal.alterar(f,foratual.getCnpj());
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
        if(cbfiltro.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbfiltro.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                   carregaTabela("UPPER(for_cnpj) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 1:
                    carregaTabela("UPPER(for_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabela("for_telefone LIKE '%" + txfiltro.getText() + "%'");
                    break;
            }
        }
    }

    @FXML
    private void clkBtTabela(MouseEvent event) 
    {
        if(tvFornecedores.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvFornecedores.getSelectionModel().getSelectedItem() != null)
            {
                Fornecedor f = (Fornecedor)tvFornecedores.getSelectionModel().getSelectedItem();
                
                txcnpj.setText(f.getCnpj());
                txnome.setText(f.getNome());
                txtelefone.setText(f.getTelefone());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                foratual = f;
            }
        }
    }
    
}
