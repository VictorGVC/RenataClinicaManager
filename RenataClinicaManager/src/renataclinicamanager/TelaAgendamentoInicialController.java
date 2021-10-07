/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOAgendamento;
import db.DAL.DAOPaciente;
import db.DAL.DAOTratamento;
import db.Models.Atendimento;
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class TelaAgendamentoInicialController implements Initializable 
{
    public static Atendimento age;
    public static boolean b;
    
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXComboBox<PacienteTratamento> cbtratamento;
    @FXML
    private AnchorPane pnmain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCBPaciente();
    }  
    
    @FXML
    private void initCBPaciente()
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%%'");
    }
    
    public static void setHorario(Atendimento a)
    {
        age = a;
        b = false;
    }
    
    public static boolean getAccepted()
    {
        return b;
    }
    
    private void carregaCBTratamento()
    {
        DAOTratamento dt = new DAOTratamento();
        List<PacienteTratamento> res = dt.getPTList("INNER JOIN tratamento t "
                + "ON t.tra_cod = pt.tra_cod WHERE pt.pac_cpf = '"+cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf()+"' "+
                " AND UPPER(t.tra_nome) LIKE '%" +cbtratamento.getEditor().getText().toUpperCase()+ "%'");
        ObservableList<PacienteTratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbtratamento.setItems(modelo);
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro.toUpperCase());
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }

    @FXML
    private void preencherPaciente(KeyEvent event) 
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

    @FXML
    private void clkAgendar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(cbpaciente.getSelectionModel().getSelectedIndex()>=0)
        {
            if(cbtratamento.getSelectionModel().getSelectedIndex()>=0)
            {
                DAOAgendamento da = new DAOAgendamento();
                age.setPt(cbtratamento.getItems().get(cbtratamento.getSelectionModel().getSelectedIndex()));

                if(da.salvar(age))
                {
                    b = true;
                    
                    Stage stage = (Stage) cbpaciente.getScene().getWindow();
                    stage.close();
                }
                else
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Erro ao Agendar!");
                    a.showAndWait();
                }
            }
            else
            {
                a.setHeaderText("Alerta");
                a.setTitle("Alerta!");
                a.setContentText("Selecione um Tratamento!");
                a.showAndWait();
            }
        }
        else
        {
            a.setHeaderText("Alerta");
            a.setTitle("Alerta!");
            a.setContentText("Selecione um Paciente!");
            a.showAndWait();
        }
    }

    @FXML
    private void selectTratamento(ActionEvent event) 
    {
        if(cbtratamento.getSelectionModel().getSelectedIndex()>=0)
            txvalor.setText(""+cbtratamento.getItems().get(
                    cbtratamento.getSelectionModel().getSelectedIndex()).getTratamento().getValor());
    }

    @FXML
    private void preencherCPF(ActionEvent event) 
    {
        if(cbpaciente.getSelectionModel().getSelectedIndex()>=0)
        {
            txcpf.setText(
                    cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf());
        
            txvalor.setText("");
            carregaCBTratamento();
        }
    }

    private void initCBPaciente(KeyEvent event) 
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%" + cbpaciente.getValue() + "%'");
    }
}
