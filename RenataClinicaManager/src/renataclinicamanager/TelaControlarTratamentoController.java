/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOPaciente;
import db.DAL.DAOTratamento;
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import db.Models.Tratamento;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;

public class TelaControlarTratamentoController implements Initializable {

    PacienteTratamento ptatual;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private Label lbobg;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXComboBox<Tratamento> cbtratamento;
    @FXML
    private Pane pnfiltros;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private TableView<PacienteTratamento> tvtratamentos;
    @FXML
    private TableColumn<PacienteTratamento, String> colpaciente;
    @FXML
    private TableColumn<PacienteTratamento, String> coltratamento;
    @FXML
    private TableColumn<PacienteTratamento, String> colvalor;
    @FXML
    private TableColumn<PacienteTratamento, String> colfinalizado;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private Pane pnpesquisa;
    @FXML
    private JFXButton btiniciar;
    @FXML
    private JFXButton btfinalizar;
    @FXML
    private JFXRadioButton rbativo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setMascaras();
        initColTb();
        initCbFiltro();
        estado(true);
        carregaTratamentos("");
        carregaPacientes("");
    }   
    
    private void initCbFiltro()
    {
        List<String> l = new ArrayList();
        l.add("Paciente");
        l.add("Tratamento");
        
        cbcategoria.setItems(FXCollections.observableArrayList(l));
                
        cbcategoria.getSelectionModel().select(0);
        rbativo.setSelected(true);
    }
    
    private void setMascaras() 
    {
        MaskFieldUtil.cpfField(txcpf);
    }
    
    private void initColTb() 
    {
        colfinalizado.setCellValueFactory(new PropertyValueFactory("ativo"));
        colpaciente.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPaciente().getNome()));
        coltratamento.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getTratamento().getNome()));
        colvalor.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getTratamento().getValor()));
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        cbpaciente.getSelectionModel().clearSelection();
        cbtratamento.getSelectionModel().clearSelection();
        txcpf.clear();
    }
    
    private void carregaTabela(String filtro) 
    {
        DAOTratamento dal = new DAOTratamento();
        List<PacienteTratamento> res = dal.getPTList(filtro);
        ObservableList<PacienteTratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvtratamentos.setItems(modelo);
        tvtratamentos.refresh();
    }
    
    private void carregaTratamentos(String filtro)
    {
        DAOTratamento dal = new DAOTratamento();
        List<Tratamento> res = dal.getList(filtro.toUpperCase());
        ObservableList<Tratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbtratamento.setItems(modelo);
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro.toUpperCase());
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }
    
    private void estado(boolean b) 
    {
        pndados.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btnovo.setDisable(!b);
        btfinalizar.setDisable(!b);
        txfiltro.setDisable(!b);
        
        if(b)
            pnfiltro.setStyle(null);
        else
        {
            pnfiltro.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent;");
        }
        clkTFiltro(null);
    }
    
    private void setCorAlert(JFXComboBox tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        cbpaciente.setFocusColor(null);
        cbtratamento.setUnFocusColor(null);
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
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvtratamentos.getSelectionModel().getSelectedIndex() != -1)
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
                PacienteTratamento pt;
                pt = tvtratamentos.getSelectionModel().getSelectedItem();
                if(dal.apagarPT(pt))
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
                    a.setContentText("Erro ao Excluir!");
                    a.showAndWait();
                }
                clkTFiltro(null);
                limparCampos();
            }
        }
    }

    @FXML
    private void preencheCPF(ActionEvent event) 
    {
        txcpf.setText(cbpaciente.getSelectionModel().getSelectedItem().getCpf());
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tvtratamentos.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvtratamentos.getSelectionModel().getSelectedItem() != null)
                ptatual = tvtratamentos.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1)
        {
            char ativo;
            if(rbativo.isSelected())
                ativo = 'S';
            else
                ativo = 'N';
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                    carregaTabela("INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(p.pac_nome) LIKE '%" 
                           + txfiltro.getText().toUpperCase() + "%' AND pt.pt_ativo = '"+ativo+"'");
                    break;
                case 1:
                    carregaTabela("INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod WHERE UPPER(t.tra_nome) LIKE '%" 
                            + txfiltro.getText().toUpperCase() + "%' AND pt_ativo = '"+ativo+"'");
                    break;
            }
        }
        tvtratamentos.refresh();
    }


    @FXML
    private void clkBtIniciar(ActionEvent event) 
    {
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(cbpaciente.getSelectionModel().getSelectedIndex() == -1 && tvtratamentos.getSelectionModel().getFocusedIndex() == -1)
        {
            flag = true;
            setCorAlert(cbpaciente, "RED");
        }
        if(cbtratamento.getSelectionModel().getSelectedIndex() == -1 && tvtratamentos.getSelectionModel().getFocusedIndex() == -1)
        {
            flag = true;
            setCorAlert(cbtratamento, "RED");
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
            PacienteTratamento pt;
            if(pndados.isDisable())
                pt = ptatual;
            else
                pt = new PacienteTratamento(cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()),
                    (Tratamento)cbtratamento.getItems().get(cbtratamento.getSelectionModel().getSelectedIndex()));
            DAOTratamento dal = new DAOTratamento();
            
            setNormalColor();

            if(dal.iniciarTratamento(pt))
            {
                JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Tratamento Iniciado com Sucesso!")));
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
            
            clkTFiltro(null);
        }
    }

    @FXML
    private void clkBtFinalizar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvtratamentos.getSelectionModel().getSelectedIndex() != -1)
        {
            a.setHeaderText("Finalizar!");
            a.setTitle("Finalizar");
            a.setContentText("Confirma a Finalização do Tratamento");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES)
            {
                DAOTratamento dal = new DAOTratamento();
                PacienteTratamento pt;
                pt = tvtratamentos.getSelectionModel().getSelectedItem();
                if(dal.desativarPT(pt))
                {      
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Finalizado com Sucesso!")));
                }
                else
                { 
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Erro ao Finalizar!");
                    a.showAndWait();
                }
                clkTFiltro(null);
                limparCampos();
            }
        }
    }

    @FXML
    private void preenchePacientes(KeyEvent event) 
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%" + cbpaciente.getValue() + "%'");
    }
    
    @FXML
    private void preencheTratamentos(KeyEvent event) 
    {
        carregaTratamentos("UPPER(tra_nome) LIKE '%" + cbtratamento.getValue() + "%'");
    }

    @FXML
    private void completaPaciente(KeyEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        setCorAlert(txcpf, "BLACK");
        if(txcpf.getText().length() >= 13){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    if(!util.Util.isCpf(txcpf.getText()))
                    {
                        setCorAlert(txcpf, "RED");
                        a.setTitle("Atenção!");
                        a.setHeaderText("CPF");
                        a.setContentText("CPF");
                    }
                    else
                    {
                        DAOPaciente dp = new DAOPaciente();
                        cbpaciente.setValue(dp.get(txcpf.getText()));
                    }
                    return null;
                }
            };
            new Thread(task).start();
        }
    }
}
