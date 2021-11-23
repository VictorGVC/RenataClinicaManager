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
import db.DAL.DAOAgendamento;
import db.DAL.DAOFeriado;
import db.DAL.DAOPaciente;
import db.DAL.DAOTratamento;
import db.Models.Atendimento;
import db.Models.Feriado;
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;

public class TelaAgendamentoController implements Initializable {

    private DateFormat df1,df2;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private Label lbobg;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXTextField txtratamento;
    @FXML
    private TableView<PacienteTratamento> tvtratamentos;
    @FXML
    private TableView<Atendimento> tvagenda;
    @FXML
    private TableColumn<PacienteTratamento, String> coltratamento;
    @FXML
    private TableColumn<PacienteTratamento, String> colvalor;
    @FXML
    private TableColumn<Atendimento, String> colhorario;
    @FXML
    private TableColumn<Atendimento, String> colpaciente;
    @FXML
    private TableColumn<Atendimento, String> coltratamentoh;
    @FXML
    private JFXDatePicker dtpdata;
    @FXML
    private Pane pnaviso;
    @FXML
    private TableColumn<Atendimento, String> colcontato;
    @FXML
    private JFXButton btagendar;
    @FXML
    private Label lbferiado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTimePattern();
        setMascaras();
        initColTb();
        initDate();
        initCB();
        carregaHorarios(dtpdata.getValue());
        btagendar.setTooltip(new Tooltip("Confirmar Agendamento"));
    }    
    
    private void initCB()
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%%'");
    }
    
    private void initDate()
    {
        dtpdata.setValue(LocalDate.now());
    }
    
    private void setTimePattern()
    {
        df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2 = new SimpleDateFormat("HH:mm");
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    private String timeParse(Timestamp t)
    {
        String fullTime = t.toString();
        String timeStr = "";
        try {
            timeStr = df2.format(df1.parse(fullTime)); 
        } catch (ParseException e) { System.out.println(e); }
        
        return timeStr;
    }
    
    private void carregaHorarios(LocalDate data)
    {
        DAOAgendamento da = new DAOAgendamento();
        List <Atendimento> l = new ArrayList<>();
        l = da.getAgendaDia(dtpdata.getValue());
        String ldt;
        List <Atendimento> agendadia = new ArrayList<>();
        for (int i = 6; i < 19; i++) 
        {
            ldt = ""+dtpdata.getValue();
            ldt += " 0"+i+":"+"00"+":00";
            agendadia.add(new Atendimento(Timestamp.valueOf(ldt), new PacienteTratamento()));
            for (int j = 1; j < 4; j++) 
            {
                ldt = ""+dtpdata.getValue();
                if(i<10)
                    ldt += " 0"+i+":"+15*j+":00";
                else
                    ldt += " "+i+":"+15*j+":00";
                agendadia.add(new Atendimento(Timestamp.valueOf(ldt), new PacienteTratamento()));
            }
        }
        int i = 0;
        for (int j = 0; j < agendadia.size(); j++)
        {
            if(i >= l.size())
                break;
            if(agendadia.get(j).getHorario().equals(l.get(i).getHorario()))
            {
                agendadia.set(j,l.get(i));
                i++;
            }
        }
        
        tvagenda.setItems(FXCollections.observableArrayList(agendadia));
    }
    
    private void setMascaras() 
    {
        MaskFieldUtil.cpfField(txcpf);
    }
    
    private String returnNome(Atendimento a)
    {
        if(a.getPaciente() == null)
            return a.getPt().getPaciente().getNome();
        else
            return a.getPaciente().getNome();
    }
    
    private String returnTratamento(Atendimento a)
    {
        if(a.getPaciente() == null)
            return a.getPt().getTratamento().getNome();
        else
            return "Consulta";
    }
    
    private String returnContato(Atendimento a)
    {
        if(a.getPaciente() == null)
            return a.getPt().getPaciente().getTelefone();
        else
            return a.getPaciente().getTelefone();
    }
    
    private void initColTb()
    {
        coltratamento.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getTratamento().getNome()));
        colvalor.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getTratamento().getValor()));
        colhorario.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colpaciente.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        coltratamentoh.setCellValueFactory((v) -> new SimpleStringProperty(returnTratamento(v.getValue())));
        colcontato.setCellValueFactory((v) -> new SimpleStringProperty(returnContato(v.getValue())));
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro.toUpperCase());
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void preencheTratamentos()
    {
        DAOTratamento dt = new DAOTratamento();
        List<PacienteTratamento> res = dt.getPTList("INNER JOIN tratamento t "
                + "ON t.tra_cod = pt.tra_cod WHERE pt.pac_cpf = '"+cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf()+"' "+
                " AND UPPER(t.tra_nome) LIKE '%" +txtratamento.getText().toUpperCase()+ "%' AND pt.pt_ativo='S'");
        ObservableList<PacienteTratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvtratamentos.setItems(modelo);
    }
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnaviso); 
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
    private void preencheCPF(ActionEvent event) 
    {
        if(cbpaciente.getSelectionModel().getSelectedIndex() != -1)
        {
            txcpf.setText(cbpaciente.getSelectionModel().getSelectedItem().getCpf());
        
            preencheTratamentos();
        }
    }

    @FXML
    private void preenchePaciente(KeyEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        setCorAlert(txcpf, "BLACK");
        if(txcpf.getText().length() >= 14){
            
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
                        preencheTratamentos();
                    }
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void procuraPaciente(KeyEvent event) 
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%" + cbpaciente.getValue() + "%'");
    }


    @FXML
    private void clkAgendar(ActionEvent event) 
    {
        boolean ok = true;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(util.Util.isCpf(txcpf.getText()))
        {
            if(dtpdata.getValue().isAfter(LocalDate.now().minusDays(1)))
            {
                if(tvagenda.getSelectionModel().getSelectedIndex() != -1)
                {
                    if(!tvagenda.getSelectionModel().getSelectedItem().getPt().getPaciente().getNome().isEmpty())
                    {
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta!");
                        a.setContentText("Horário já agendado!");
                        a.showAndWait();
                    } 
                    else
                    {
                        DAOAgendamento da = new DAOAgendamento();
                        
                        if(tvtratamentos.getSelectionModel().getSelectedIndex() != -1)
                        {
                            Atendimento at = new Atendimento(
                                tvagenda.getSelectionModel().getSelectedItem().getHorario(),
                                tvtratamentos.getSelectionModel().getSelectedItem());

                            if(da.salvar(at))
                                miniGAlert("Agendado com sucesso!");
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
                            Atendimento at = new Atendimento(
                                tvagenda.getSelectionModel().getSelectedItem().getHorario(),
                                cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()));
                            
                            if(da.salvarAP(at))
                                miniGAlert("Agendado com sucesso!");
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
                    }
                }
                else
                {
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta!");
                    a.setContentText("Selecione um horário!");
                    a.showAndWait();
                }
            }
            else
            {
                a.setHeaderText("Alerta");
                a.setTitle("Alerta!");
                a.setContentText("Selecione uma data válida!");
                a.showAndWait();
            }
        }
        else
        {
            a.setHeaderText("Alerta");
            a.setTitle("Alerta!");
            a.setContentText("Paciente invalido!");
            a.showAndWait();
        }
        
        carregaHorarios(dtpdata.getValue());
    }

    @FXML
    private void clkCancelarAgendamento(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(tvagenda.getSelectionModel().getSelectedIndex() != -1)
        {
            DAOAgendamento da = new DAOAgendamento();
            Atendimento at = tvagenda.getSelectionModel().getSelectedItem();
            if(da.apagar(at))
            {
                JFXSnackbar sb = new JFXSnackbar(pnaviso); 
                Label text = new Label("Cancelado com Sucesso!");
                text.setStyle("-fx-text-fill: green");
                sb.enqueue(new JFXSnackbar.SnackbarEvent(text));
            }
            else
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.getButtonTypes().clear();
                a.getButtonTypes().add(ButtonType.OK);
                a.setHeaderText("ERRO");
                a.setTitle("ERRO!");
                a.setContentText("Erro ao Cancelar!");
                a.showAndWait();
            }
        }
        else
        {
            a.setHeaderText("Alerta");
            a.setTitle("Alerta!");
            a.setContentText("Selecione um agendamento!");
            a.showAndWait();
        }
        
        carregaHorarios(dtpdata.getValue());
    }

    @FXML
    private void subDay(ActionEvent event) 
    {
        tvagenda.setDisable(false);
        lbferiado.setText("");
        DAOFeriado df = new DAOFeriado();
        dtpdata.setValue(dtpdata.getValue().minusDays(1));
        if(!df.isFeriado(dtpdata.getValue()))
            carregaHorarios(dtpdata.getValue());
        else
        {
            Feriado f = df.getFeriado(dtpdata.getValue());
            lbferiado.setText(f.getNome());
            tvagenda.setDisable(true);
        }
    }

    @FXML
    private void addDay(ActionEvent event) 
    {
        tvagenda.setDisable(false);
        lbferiado.setText("");
        DAOFeriado df = new DAOFeriado();
        dtpdata.setValue(dtpdata.getValue().plusDays(1));
        if(!df.isFeriado(dtpdata.getValue()))
            carregaHorarios(dtpdata.getValue());
        else
        {
            Feriado f = df.getFeriado(dtpdata.getValue());
            lbferiado.setText(f.getNome());
            tvagenda.setDisable(true);
        }
    }

    @FXML
    private void clkPreencheTratamentos(KeyEvent event) 
    {
        preencheTratamentos();
    }

    @FXML
    private void selectDate(ActionEvent event) 
    {
        tvagenda.setDisable(false);
        lbferiado.setText("");
        DAOFeriado df = new DAOFeriado();
        if(!df.isFeriado(dtpdata.getValue()))
            carregaHorarios(dtpdata.getValue());
        else
        {
            Feriado f = df.getFeriado(dtpdata.getValue());
            lbferiado.setText(f.getNome());
            tvagenda.setDisable(true);
        }
    }
}
