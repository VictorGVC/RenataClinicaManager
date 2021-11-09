/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOAgendamento;
import db.DAL.DAOConfig;
import db.DAL.DAOPaciente;
import db.Models.Atendimento;
import db.Models.Paciente;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaHistoricoController implements Initializable {

    private DateTimeFormatter form;
    private DateFormat df1,df2;
    
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXDatePicker dpdatainicial;
    @FXML
    private JFXDatePicker dpdatafinal;
    @FXML
    private TableColumn<Atendimento, String> coldata;
    @FXML
    private TableView<Atendimento> tvhistorico;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private TableColumn<Atendimento, String> colpaciente;
    @FXML
    private TableColumn<Atendimento, String> colhorario;
    @FXML
    private TableColumn<Atendimento, String> coltratamento;
    @FXML
    private TableColumn<Atendimento, String> colobservacoes;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private HBox pndados;
    @FXML
    private JFXTextField txfiltro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTimePattern();
        setMasks();
        initDates();
        initCol();
        initPacientes();
    }   
    
    private void setTimePattern()
    {
        df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2 = new SimpleDateFormat("HH:mm");
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    private void initDates()
    {
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dpdatainicial.setValue(LocalDate.now().minusMonths(4));
        dpdatafinal.setValue(LocalDate.now());
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

    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.cpfField(txcpf);
    }
    
    private void initCol()
    {
        colobservacoes.setCellValueFactory(new PropertyValueFactory("observacoes"));
        colpaciente.setCellValueFactory((v) -> new SimpleStringProperty(v.getValue().getPt().getPaciente().getNome()));
        coltratamento.setCellValueFactory((v) -> new SimpleStringProperty(v.getValue().getPt().getTratamento().getNome()));
        coldata.setCellValueFactory((v) -> new SimpleStringProperty(v.getValue().getHorario().toLocalDateTime().toLocalDate().format(form)));
        colhorario.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
    }
    
    private void initPacientes()
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%%'");
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro.toUpperCase());
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }
    
    private void carregaAtendimentos(String filtro) throws SQLException
    {
        DAOAgendamento dal = new DAOAgendamento();
        List<Atendimento> res = dal.getAtendimentosPaciente(filtro);
        ObservableList<Atendimento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvhistorico.setItems(modelo);
        tvhistorico.refresh();
    }
    
    private void miniAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pndados); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: red;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
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

    @FXML
    private void clkBtFiltrar(ActionEvent event) throws SQLException 
    {
        if(cbpaciente.getSelectionModel().getSelectedIndex() >= 0)
            carregaAtendimentos("INNER JOIN pessoatratamento pt ON pt.pt_cod = a.pt_cod " +
                            "INNER JOIN tratamento t ON t.tra_cod = pt.tra_cod " +
                            "INNER JOIN paciente p ON p.pac_cpf = pt.pac_cpf WHERE UPPER(t.tra_nome) LIKE '%" 
                           + txfiltro.getText().toUpperCase() + "%' AND a.age_dthr >= '"+dpdatainicial.getValue()+"' AND a.age_dthr <= '"+dpdatafinal.getValue()+"' "
                                   + "AND p.pac_cpf = '"+cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf()+"'");
    }

    @FXML
    private void clkAtendimento(ActionEvent event) throws IOException 
    {
        if(tvhistorico.getSelectionModel().getSelectedIndex() >=0)
        {
            TelaAtendimentoController controller = new TelaAtendimentoController();
            controller.setAtendimento(tvhistorico.getSelectionModel().getSelectedItem());

            Parent root = FXMLLoader.load(getClass().getResource("TelaAtendimento.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
            DAOConfig dc = new DAOConfig();

            scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
            stage.setTitle("Atendimento");
            stage.setScene(scene);
            stage.showAndWait(); 

            if(controller.getSuccess())
                miniGAlert("Atendimento Salvo com sucesso!");
        }
    }

    @FXML
    private void carregaCpf(ActionEvent event) throws SQLException 
    {
        if(cbpaciente.getSelectionModel().getSelectedIndex()>=0)
        {
            txcpf.setText(
                    cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf());
            clkBtFiltrar(null);
        }
    }
    
    @FXML
    private void carregaPacientes(KeyEvent event) 
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%" + cbpaciente.getValue() + "%'");
    }

    @FXML
    private void verificaCpf(KeyEvent event) 
    {
        setCorAlert(txcpf, "BLACK");
        if(txcpf.getText().length() >= 14)
        {
            if(!util.Util.isCpf(txcpf.getText()))
            {
                setCorAlert(txcpf, "RED");
                miniAlert("CPF invalido!");
            }
            else
            {
                DAOPaciente dp = new DAOPaciente();
                cbpaciente.setValue(dp.get(txcpf.getText()));
            }
        }
    }

    @FXML
    private void clkBtFiltrarT(KeyEvent event) throws SQLException 
    {
        clkBtFiltrar(null);
    }
}
