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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOPaciente;
import db.Models.Paciente;
import java.net.URL;
import java.util.ArrayList;
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
public class TelaPacientesController implements Initializable 
{
    private Paciente pacatual;
    private int rea;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private Pane pndados;
    @FXML
    private JFXComboBox<String> cbsexo;
    @FXML
    private JFXDatePicker dpdatanasc;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private TableColumn<Paciente, String> colnome;
    @FXML
    private TableColumn<Paciente, String> coltelefone;
    @FXML
    private TableColumn<Paciente, String> colrua;
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
    private TableColumn<Paciente, Integer> colnum;
    @FXML
    private TableColumn<Paciente, String> colrea;
    @FXML
    private TableView<Paciente> tvpaciente;
    @FXML
    private TableColumn<Paciente, String> colcpf;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txtelefone;
    @FXML
    private JFXTextField txrua;
    @FXML
    private JFXTextField txnumero;
    @FXML
    private JFXTextField txbairro;
    @FXML
    private JFXTextField txcidade;
    @FXML
    private JFXTextArea tarea;
    @FXML
    private JFXTextField txfiltro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setMascaras();
        listaSexo();
        listaCategoria();
        initColTb();
        estado(true);
        cbcategoria.getSelectionModel().select(1);
    }    
    
    private void listaSexo() 
    {
        List<String> list = new ArrayList();
        
        list.add("Masculino");
        list.add("Feminino");
        
        cbsexo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void listaCategoria() 
    {
        List<String> list = new ArrayList();
        
        list.add("CPF");
        list.add("Nome");
        list.add("Telefone");
        list.add("Rua");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
    }
    
    private String AddBarraN(String ini)
    {
        int j = 0;
        for (int i = 1; i < ini.length(); i++) 
        {
            if(i%36==0)
            {
                j++;
                ini = ini.substring(0, j*35) + "\n" + ini.substring(j*36,ini.length()-1);
            }
        }
        return ini;
    }
    
    private void initColTb() 
    {
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colrua.setCellValueFactory(new PropertyValueFactory("rua"));
        colrea.setCellValueFactory((v) -> new SimpleStringProperty(AddBarraN(v.getValue().getRea())));
        colnum.setCellValueFactory(new PropertyValueFactory("numero"));
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
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro);
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvpaciente.setItems(modelo);
    }
    
    private void limparCampos() 
    {
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        cbsexo.getSelectionModel().select(-1);
        cbcategoria.getSelectionModel().select(-1);
        dpdatanasc.setValue(null);
    }
    
    private void setMascaras() 
    {
        rea = 0;
        MaskFieldUtil.maxField(txnome, 30);
        MaskFieldUtil.cpfField(txcpf);
        MaskFieldUtil.foneField(txtelefone);
        MaskFieldUtil.maxField(txrua, 60);
        MaskFieldUtil.numericField(txnumero);
        MaskFieldUtil.maxField(txbairro, 40);
        MaskFieldUtil.maxField(txcidade, 30);
        MaskFieldUtil.dateField(dpdatanasc.getEditor());
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
        txcpf.requestFocus();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        if(tvpaciente.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txcpf.requestFocus();
        }
        else
            miniAlert("Selecione algum paciente!");
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvpaciente.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Exclus??o!");
            a.setTitle("Exclus??o");
            a.setContentText("Confirma a exclus??o");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOPaciente dal = new DAOPaciente();
                Paciente p;
                p = tvpaciente.getSelectionModel().getSelectedItem();
                String result = dal.apagar(p);
                if(result.isEmpty())
                    miniGAlert("Excluido com sucesso!");
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
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txcpf.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txcpf, "RED");
        }
        else if(!Util.isCpf(txcpf.getText()))
        {
            setCorAlert(txcpf, "RED");
            a.setContentText("CPF inv??lido!");
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
        if(txcidade.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txcidade, "RED");
        }
        if(txrua.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txrua, "RED");
        }
        if(txnumero.getText().isEmpty())
        {    
            flag = true;
            setCorAlert(txnumero, "RED");
        }
        else if(!Util.isSIPositive(txnumero.getText()))
        {
            setCorAlert(txnumero, "RED");
            a.setContentText("N??mero do endere??o inv??lido!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        if(flag)
        {
            a.setContentText("Campos obrigat??rios n??o preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else if(Util.isCpf(txcpf.getText()))
        {
            try {id = txcpf.getText();}
            catch (NumberFormatException e) {id = "";}

            char sexo = 'C';

            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';

            Paciente c = new Paciente(id, txnome.getText(),
                                    txtelefone.getText(),
                                    txrua.getText(),
                                    txcidade.getText(),
                                    txbairro.getText(),
                                    tarea.getText(),
                                    Integer.parseInt(txnumero.getText()),
                                    sexo,dpdatanasc.getValue());
            DAOPaciente dal = new DAOPaciente();

            setNormalColor();

            if(pnpesquisa.isDisable())
            {
                String error = dal.gravar(c);
                if(error.isEmpty())
                {
                    miniGAlert("Salvo com sucesso!");
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
                String error = dal.alterar(c,pacatual.getCpf());
                if(error.isEmpty())
                {
                    miniGAlert("Alterado com sucesso!");
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
        txcpf.setFocusColor(null);
        txcpf.setUnFocusColor(null);
        txnome.setFocusColor(null);
        txnome.setUnFocusColor(null);
        txtelefone.setFocusColor(null);
        txtelefone.setUnFocusColor(null);
        txcidade.setFocusColor(null);
        txcidade.setUnFocusColor(null);
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
    private void evtCpfDigitado(KeyEvent event) 
    {
        JFXSnackbar sb = new JFXSnackbar(pndados);
        if(txcpf.getText().length() >= 14)
        {
            if(!Util.isCpf(txcpf.getText()))
            {
                txcpf.setFocusColor(Paint.valueOf("RED"));
                Label lb = new Label("CPF inv??lido!");
                lb.setStyle("-fx-text-fill: RED");
                sb.enqueue(new JFXSnackbar.SnackbarEvent(lb));
            }
            else
                txcpf.setFocusColor(null);
        }
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tvpaciente.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvpaciente.getSelectionModel().getSelectedItem() != null)
            {
                
                Paciente p = (Paciente)tvpaciente.getSelectionModel().getSelectedItem();
                
                txcpf.setText(p.getCpf());
                txnome.setText(p.getNome());
                switch (p.getSexo()) {
                    case 'M':
                        cbsexo.getSelectionModel().selectFirst();
                        break;
                    case 'F':
                        cbsexo.getSelectionModel().selectLast();
                        break;
                    default:
                        cbsexo.getSelectionModel().select(-1);
                        break;
                }
                dpdatanasc.setValue(p.getDtnacimento());
                txtelefone.setText(p.getTelefone());
                tarea.setText(p.getRea());
                txrua.setText(p.getRua());
                txnumero.setText("" + p.getNumero());
                txbairro.setText(p.getBairro());
                txcidade.setText(p.getCidade());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                pacatual = p;
            }
        }
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                   carregaTabela("UPPER(pac_cpf) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 1:
                    carregaTabela("UPPER(pac_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabela("pac_telefone LIKE '%" + txfiltro.getText() + "%'");
                    break;
                case 3:
                    carregaTabela("UPPER(pac_rua) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
            }
        }
    }
}