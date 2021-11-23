/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import db.Banco.Banco;
import db.DAL.DAOAgendamento;
import db.DAL.DAOConfig;
import db.DAL.DAOConta;
import db.DAL.DAOFeriado;
import db.Models.Atendimento;
import db.Models.Config;
import db.Models.Feriado;
import db.Models.Funcionario;
import db.Models.PacienteTratamento;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vicga
 */
public class TelaPrincipalController implements Initializable {
    
    public static Funcionario sessao;
    private DateFormat df1,df2;
    private LocalDate dia;
    private Atendimento sat;
    private DateTimeFormatter form;
    private DateTimeFormatter formatter;
    
    private Label label;
    @FXML
    private AnchorPane pntotal;
    @FXML
    private VBox pncabecalho;
    @FXML
    private MenuBar mnbar;
    @FXML
    private ToolBar tbatalhos;
    @FXML
    private HBox pnrodape;
    @FXML
    private MenuItem mifuncionario;
    @FXML
    private MenuItem micargo;
    @FXML
    private MenuItem mipaciente;
    @FXML
    private MenuItem mimaterial;
    @FXML
    private MenuItem mifornecedor;
    @FXML
    private MenuItem mitratamento;
    @FXML
    private MenuItem miferiado;
    @FXML
    private MenuItem miatestado;
    @FXML
    private MenuItem mireceituario;
    @FXML
    private TableView<Atendimento> tvseg;
    @FXML
    private TableColumn<Atendimento, String> colhrseg;
    @FXML
    private TableColumn<Atendimento, String> colnomeseg;
    @FXML
    private TableView<Atendimento> tvter;
    @FXML
    private TableColumn<Atendimento, String> colhrter;
    @FXML
    private TableColumn<Atendimento, String> colnometer;
    @FXML
    private TableView<Atendimento> tvqua;
    @FXML
    private TableColumn<Atendimento, String> colhrqua;
    @FXML
    private TableColumn<Atendimento, String> colnomequa;
    @FXML
    private TableView<Atendimento> tvqui;
    @FXML
    private TableColumn<Atendimento, String> colhrqui;
    @FXML
    private TableColumn<Atendimento, String> colnomequi;
    @FXML
    private TableView<Atendimento> tvsex;
    @FXML
    private TableColumn<Atendimento, String> colhrsex;
    @FXML
    private TableColumn<Atendimento, String> colnomesex;
    @FXML
    private TableView<Atendimento> tvsab;
    @FXML
    private TableColumn<Atendimento, String> colhrsab;
    @FXML
    private TableColumn<Atendimento, String> colnomesab;
    @FXML
    private HBox pntables;
    @FXML
    private Label lbseg;
    @FXML
    private Label lbter;
    @FXML
    private Label lbqua;
    @FXML
    private Label lbqui;
    @FXML
    private Label lbsex;
    @FXML
    private Label lbsab;
    @FXML
    private VBox pnfade;
    @FXML
    private MenuItem miinfo1;
    @FXML
    private MenuItem miinfo2;
    @FXML
    private MenuItem miinfo3;
    @FXML
    private MenuItem miinfo4;
    @FXML
    private MenuItem miinfo5;
    @FXML
    private MenuItem miinfo6;
    @FXML
    private MenuItem miregate1;
    @FXML
    private MenuItem miregate2;
    @FXML
    private MenuItem miregate3;
    @FXML
    private MenuItem miregate4;
    @FXML
    private MenuItem miregate5;
    @FXML
    private MenuItem miregate6;
    @FXML
    private Label lbbomdia;
    @FXML
    private Label lbdata;
    @FXML
    private Label lbnomeempresa;
    @FXML
    private Label lbcidade;
    @FXML
    private Label lbendereco;
    @FXML
    private JFXButton btconfig;
    @FXML
    private Label lbferseg;
    @FXML
    private Label lbferter;
    @FXML
    private Label lbferqua;
    @FXML
    private Label lbferqui;
    @FXML
    private Label lbfersex;
    @FXML
    private Label lbfersab;
    @FXML
    private AnchorPane pnnotapagar;
    @FXML
    private AnchorPane pnnotareceber;
    @FXML
    private JFXButton bthelp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDateFormat();
        setTimePattern();
        setAcessoSessao();
        initColTB();
        initTables();
        setInfo();
        btconfig.setTooltip(new Tooltip("Configurações"));
        notificacao();
    }  
    
    private void notificacao()
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        DAOConta dc = new DAOConta();
        
        if(dc.notificationPag())
        {
            pnnotapagar.setVisible(true);
        }
        
        if(dc.notificationRec())
        {
            pnnotareceber.setVisible(true);
        }
    }
    
    private boolean isSomethingSelected()
    {
        ObservableList <Node> componentes = pntables.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TableView)
                if(((TableView)n).getSelectionModel().getSelectedIndex() != -1)
                    return true;
        }
        return false;
    }
    
    private void setInfo()
    {
        DAOConfig dc = new DAOConfig();
        Config c = dc.get();
        lbbomdia.setText("Bom dia, "+sessao.getNome());
        lbdata.setText("Hoje é "+LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())+", "
                + LocalDate.now().getDayOfMonth()+" de "+LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())+" "
                        + "de "+LocalDate.now().getYear());
        lbcidade.setText(c.getCidade());
        lbendereco.setText(c.getEndereco());
        lbnomeempresa.setText(c.getNome());
    }
    
    private void setTema()
    {
        DAOConfig dc = new DAOConfig();
        
        pncabecalho.getScene().getStylesheets().clear();
        pncabecalho.getScene().getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
    }
    
    private void initDateFormat() 
    {
        form = DateTimeFormatter.ofPattern("dd/MM");
        formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    }
    
    private void setTimePattern()
    {
        dia = LocalDate.now();
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
    
    private String returnNome(Atendimento a)
    {
        if(a.getPaciente() == null)
            return a.getPt().getPaciente().getNome();
        else
            return a.getPaciente().getNome();
    }
    
    private void initColTB()
    {
        colhrseg.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomeseg.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        colhrter.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnometer.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        colhrqua.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomequa.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        colhrqui.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomequi.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        colhrsex.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomesex.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
        colhrsab.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomesab.setCellValueFactory((v) -> new SimpleStringProperty(returnNome(v.getValue())));
    }
    
    private void setEnableTv()
    {
        tvseg.setDisable(false);
        tvter.setDisable(false);
        tvqua.setDisable(false);
        tvqui.setDisable(false);
        tvsex.setDisable(false);
        tvsab.setDisable(false);
    }
    
    private void setLb()
    {
        lbferseg.setText("");
        lbferter.setText("");
        lbferqua.setText("");
        lbferqui.setText("");
        lbfersex.setText("");
        lbfersab.setText("");
    }
    
    private void initTables()
    {
        DAOAgendamento da = new DAOAgendamento();
        DAOFeriado df = new DAOFeriado();
        
        int day = dia.getDayOfWeek().getValue()-1;
        
        LocalDate seg = dia.minusDays(day);
        LocalDate ter = dia.minusDays(day-1);
        LocalDate qua = dia.minusDays(day-2);
        LocalDate qui = dia.minusDays(day-3);
        LocalDate sex = dia.minusDays(day-4);
        LocalDate sab = dia.minusDays(day-5);
        
        setEnableTv();
        
        setLb();
        
        lbseg.setText(seg.format(form));
        lbter.setText(ter.format(form));
        lbqua.setText(qua.format(form));
        lbqui.setText(qui.format(form));
        lbsex.setText(sex.format(form));
        lbsab.setText(sab.format(form));
        
        if(!df.isFeriado(seg))
        {
            List <Atendimento> agendaseg = geraLista(seg);
            agendaseg = getAgenda(seg, agendaseg);
            tvseg.setItems(FXCollections.observableArrayList(agendaseg));
        }
        else
        {
            Feriado f = df.getFeriado(seg);
            lbferseg.setText(f.getNome());
            tvseg.setDisable(true);
        }
        
        if(!df.isFeriado(ter))
        {
            List <Atendimento> agendater = geraLista(ter);
            agendater = getAgenda(ter, agendater);
            tvter.setItems(FXCollections.observableArrayList(agendater));
        }
        else
        {
            Feriado f = df.getFeriado(ter);
            lbferter.setText(f.getNome());
            tvter.setDisable(true);
        }
        
        if(!df.isFeriado(qua))
        {
            List <Atendimento> agendaqua = geraLista(qua);
            agendaqua = getAgenda(qua, agendaqua);
            tvqua.setItems(FXCollections.observableArrayList(agendaqua));
        }
        else
        {
            Feriado f = df.getFeriado(qua);
            lbferqua.setText(f.getNome());
            tvqua.setDisable(true);
        }
        
        if(!df.isFeriado(qui))
        {
            List <Atendimento> agendaqui = geraLista(qui);
            agendaqui = getAgenda(qui, agendaqui);
            tvqui.setItems(FXCollections.observableArrayList(agendaqui));
        }
        else
        {
            Feriado f = df.getFeriado(qui);
            lbferqui.setText(f.getNome());
            tvqui.setDisable(true);
        }
        
        if(!df.isFeriado(sex))
        {
            
            List <Atendimento> agendasex = geraLista(sex);
            agendasex = getAgenda(sex, agendasex);
            tvsex.setItems(FXCollections.observableArrayList(agendasex));
        }
        else
        {
            Feriado f = df.getFeriado(sex);
            lbfersex.setText(f.getNome());
            tvsex.setDisable(true);
        }
        
        if(!df.isFeriado(sab))
        {
            List <Atendimento> agendasab = geraLista(sab);
            agendasab = getAgenda(sab, agendasab);
            tvsab.setItems(FXCollections.observableArrayList(agendasab));
        }
        else
        {
            Feriado f = df.getFeriado(sab);
            lbfersab.setText(f.getNome());
            tvsab.setDisable(true);
        }
    }
    
    private void estado()
    {
        boolean minfo = sat.getPt().getPaciente().getNome().isEmpty();
        miinfo1.setDisable(minfo);
        miinfo2.setDisable(minfo);
        miinfo3.setDisable(minfo);
        miinfo4.setDisable(minfo);
        miinfo5.setDisable(minfo);
        miinfo6.setDisable(minfo);
        
        boolean miregate = minfo || 
                sat.getHorario().after(Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()));
        miregate1.setDisable(miregate);
        miregate2.setDisable(miregate);
        miregate3.setDisable(miregate);
        miregate4.setDisable(miregate);
        miregate5.setDisable(miregate);
        miregate6.setDisable(miregate);
    }
    
    private List<Atendimento> getAgenda(LocalDate d, List<Atendimento> li)
    {
        DAOAgendamento da = new DAOAgendamento();
        List<Atendimento> l = da.getAgendaDia(d);
        
        int i = 0;
        for (int j = 0; j < li.size(); j++)
        {
            if(i >= l.size())
                break;
            if(li.get(j).getHorario().equals(l.get(i).getHorario()))
            {
                li.set(j,l.get(i));
                i++;
            }
        }
        
        return li;
    }
    
    private List<Atendimento> geraLista(LocalDate d)
    {
        String ldt;
        List <Atendimento> agenda = new ArrayList<>();
        for (int i = 6; i < 19; i++) 
        {
            ldt = ""+d;
            ldt += " 0"+i+":"+"00"+":00";
            agenda.add(new Atendimento(Timestamp.valueOf(ldt), new PacienteTratamento()));
            for (int j = 1; j < 4; j++) 
            {
                ldt = ""+d;
                if(i<10)
                    ldt += " 0"+i+":"+15*j+":00";
                else
                    ldt += " "+i+":"+15*j+":00";
                agenda.add(new Atendimento(Timestamp.valueOf(ldt), new PacienteTratamento()));
            }
        }
        return agenda;
    }
    
    private void limpaSelecao(TableView tv, int index)
    {
        ObservableList <Node> componentes = pntables.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TableView)
                ((TableView)n).getSelectionModel().clearSelection();
        }
        tv.getSelectionModel().select(index);
    }
    
    public static void setSessao(Funcionario sess)
    {
        sessao = sess;
    }
    
    private void setAcessoSessao()
    {
        JSONArray ja = sessao.getC().getAcesso();
        for (int i = 0; i < ja.length(); i++) 
        {
            JSONObject jo = ja.getJSONObject(i);
            if(jo.get("acesso").equals("n"))
            {
                desabilitaBasica((String)jo.get("nome"));
            }
        }
    }
    
    private void desabilitaBasica(String basica)
    {
        switch(basica)
        {
            case "Pacientes":
                mipaciente.setVisible(false);
            break;
            case "Fornecedores":
                mifornecedor.setVisible(false);
            break;
            case "Funcionários":
                mifuncionario.setVisible(false);
            break;
            case "Materiais":
                mimaterial.setVisible(false);
            break;
            case "Tratamentos":
                mitratamento.setVisible(false);
            break;
            case "Feriados":
                miferiado.setVisible(false);
            break;
            case "Receituário":
                mireceituario.setVisible(false);
            break;
            case "Atestado":
                miatestado.setVisible(false);
            break;
            case "Cargos":
                micargo.setVisible(false);
            break;
        }
    }
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnfade); 
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
    private void clkChamaLogin(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage) mnbar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        Scene scene = new Scene(root);
        
        stage.close();
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(850);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Fornecedores");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkBackup(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        a.setHeaderText("Backup!");
        a.setTitle("Alerta");
        a.setContentText("Após a confirmação, o backup anterior será perdido, você tem certeza que deseja fazer isso?");
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.NO);
        a.getButtonTypes().add(ButtonType.YES);
        
        if (a.showAndWait().get() == ButtonType.YES)
        {
            Banco.realizaBackupRestauracao("banco\\backup.bat");
        }
    }

    @FXML
    private void clkRestore(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        a.setHeaderText("Restauração!");
        a.setTitle("Alerta");
        a.setContentText("Após a confirmação, todos os dados atuais serão retornados na versão do ultimo backup, você tem certeza que deseja fazer isso?");
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.NO);
        a.getButtonTypes().add(ButtonType.YES);
        
        if (a.showAndWait().get() == ButtonType.YES)
        {
            Banco.realizaBackupRestauracao("banco\\restore.bat");
        }
    }

    @FXML
    private void clkFuncionarios(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(1030);
//        stage.setMaxHeight(678);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkPacientes(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPaciente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(1030);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Pacientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenMateriais(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaMateriais.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(800);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Materiais");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkCargos(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaCargos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(555);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Cargos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenTratamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaTratamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(544);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Tratamentos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenFeriado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFeriado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(544);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Feriados");
        stage.setScene(scene);
        stage.showAndWait();
        
        initTables();
    }

    @FXML
    private void clkOpenAtestado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAtestado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(555);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Modelo de Atestado");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenReceituário(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaReceituario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(555);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Modelo de Receituário");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkRecebimento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRecebimentos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(804);
//        stage.setMaxHeight(600);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Recebimento");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkControlarTratamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaControlarTratamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(876);
//        stage.setMaxHeight(589);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Controlar Tratamento");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkCompra(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaCompras.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Compra");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkPagamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPagamentos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(804);
//        stage.setMaxHeight(610);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Pagamento");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkAgendamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAgendamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/schedule.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Agendamento");
        stage.setScene(scene);
        stage.showAndWait();
        initTables();
    }

    @FXML
    private void clkAtendimento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaHistorico.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

//        stage.setMaxWidth(983);
//        stage.setMaxHeight(589);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Histórico de atendimento");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkAgendar(ActionEvent event) throws IOException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(isSomethingSelected() && !sat.getHorario().before(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                && sat.getPt().getPaciente().getNome().equals(""))
        {
            TelaAgendamentoInicialController.setHorario(sat);
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaAgendamentoInicial.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/schedule.png")));
            DAOConfig dc = new DAOConfig();
        
            scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
            stage.setTitle("Agendamento");
            stage.setScene(scene);
            stage.showAndWait();
            if(TelaAgendamentoInicialController.getAccepted())
                miniGAlert("Agendado com sucesso!");
            
            initTables();
        }
        else
        {
            a.setHeaderText("Alerta");
            a.setTitle("Alerta!");
            a.setContentText("Selecione um horário válido!");
            a.showAndWait();
        }
    }

    @FXML
    private void clkAddDay(ActionEvent event) 
    {
        dia = dia.plusWeeks(1);
        FadeTransition ft = new FadeTransition(Duration.millis(500), pnfade);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        initTables();
        ft = new FadeTransition(Duration.millis(500), pnfade);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void clkSubDay(ActionEvent event) 
    {
        dia = dia.minusWeeks(1);
        FadeTransition ft = new FadeTransition(Duration.millis(500), pnfade);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        initTables();
        ft = new FadeTransition(Duration.millis(500), pnfade);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void clkMostrarInfo(ActionEvent event) throws IOException 
    {
        TelaPacienteInfoController.setInfo(sat);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPacienteInfo.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkSelect(MouseEvent event) 
    {
        if (tvqua.getScene().focusOwnerProperty().get() instanceof TableView) 
        {
            TableView ftv = (TableView) tvqua.getScene().focusOwnerProperty().get();
            int index = ftv.getSelectionModel().getSelectedIndex();
            sat = (Atendimento)ftv.getSelectionModel().getSelectedItem();
            
            if(event.getButton() == MouseButton.SECONDARY)
                estado();
            limpaSelecao(ftv, index);
        }
    }

    @FXML
    private void clkRegistraAtendimento(ActionEvent event) throws IOException 
    {
        TelaAtendimentoController.setAtendimento(sat);
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaAtendimento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Atendimento");
        stage.setScene(scene);
        stage.showAndWait(); 
        
        if(TelaAtendimentoController.getSuccess())
            miniGAlert("Atendimento Salvo com sucesso!");
    }

    @FXML
    private void clkOpenConfig(ActionEvent event) throws IOException 
    {        
        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/config.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Configurações");
        stage.setScene(scene);
        stage.showAndWait();
        
        setInfo();
        setTema();
    }

    @FXML
    private void clkCarregarFeriados(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        a.setHeaderText("Carregamento de Calendário!");
        a.setTitle("Alerta");
        a.setContentText("Após a confirmação, todos os feriados cadastrados anteriormente serão apagados, você tem certeza que deseja fazer isso?");
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.NO);
        a.getButtonTypes().add(ButtonType.YES);
        
        if (a.showAndWait().get() == ButtonType.YES)
        {
            Task task = new Task<Void>() 
            {
                boolean b = false;
                @Override
                protected Void call() 
                {
                    JSONArray ja = null;
                    try{
                            URL url = new URL("https://api.calendario.com.br/?json=true&ano=2017&ibge=3529203&token=dmljZ2FicmllbDE3QGhvdG1haWwuY29tJmhhc2g9MjQ5MTAxMzk1&ano="+LocalDate.now().getYear());
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            if (conn.getResponseCode() != 200) {
                                    System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
                            }

                            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                            String output,json="";
                            while ((output = br.readLine()) != null) {
                                    json+= output;
                            }

                            ja = new JSONArray(json);

                            System.out.println(ja);
                            conn.disconnect();
                            b = true;
                    }
                    catch (Exception e) 
                    {
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setHeaderText("ERRO!");
                        a.setTitle("ERRO");
                        a.setContentText("Erro ao carregar feriados da web!");
                        a.getButtonTypes().clear();
                        a.getButtonTypes().add(ButtonType.OK);
                    }

                    DAOFeriado df = new DAOFeriado();

                    if(b)
                    {
                        df.apagarTudo();
                        for (int i = 0; i < ja.length(); i++) 
                        {
                            JSONObject json = ja.getJSONObject(i);
                            int type = json.getInt("type_code");
                            if(type == 1 || type == 2 || type == 3)
                            {
                                LocalDate date = LocalDate.parse(json.getString("date"),formatter);
                                df.gravar(new Feriado(json.getString("name"),date));
                            }
                        }
                    }
                    initTables();
                    miniGAlert("Feriados cadastrados com sucesso!");
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void clkEscondeNotAPagar(ActionEvent event) 
    {
        pnnotapagar.setVisible(false);
    }

    @FXML
    private void clkEscondeNotAReceber(ActionEvent event) 
    {
        pnnotareceber.setVisible(false);
    }

    @FXML
    private void clkRelReceituario(ActionEvent event) throws JRException, IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelReceituario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Receituário");
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void clkRelAtestado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelAtestado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        DAOConfig dc = new DAOConfig();
        
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Atestado");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkRelPacientes(ActionEvent event) 
    {
        Object[] options1 = { "Tratamentos ativos", "Tratamentos encerrados"};

        JPanel panel = new JPanel();

        int result = JOptionPane.showOptionDialog(null, panel, "Pacientes",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION)
        {
            try
            {

                JasperPrint jp = JasperFillManager.fillReport("./reports/RelPacientes.jasper", null, Banco.getCon().getConnect());
                JasperViewer.viewReport(jp,false); 

            } catch (Exception e) 
            {
                System.out.println(e);
            }
        }
        else
        {
            try
            {
                Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("ativo", "N");

                JasperPrint jp = JasperFillManager.fillReport("./reports/RelPacientes.jasper", parameters, Banco.getCon().getConnect());
                JasperViewer.viewReport(jp,false); 

            } catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void clkOpenHelp(ActionEvent event) throws URISyntaxException, IOException 
    {
        java.awt.Desktop.getDesktop().browse(new File("manual/index.html").toURI());
    }

    @FXML
    private void clkOpenLucro(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLucroAnual.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        
        stage.setTitle("Lucro");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkRelRecebimentos(ActionEvent event) throws IOException 
    {
        TelaRelContaController.contarp = true;
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelConta.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        
        DAOConfig dc = new DAOConfig();
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Contas");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkRelPagamentos(ActionEvent event) throws IOException 
    {
        TelaRelContaController.contarp = false;
        Parent root = FXMLLoader.load(getClass().getResource("TelaRelConta.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setResizable(false);
//        stage.setMaxWidth(917);
//        stage.setMaxHeight(643);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        
        DAOConfig dc = new DAOConfig();
        scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
        stage.setTitle("Contas");
        stage.setScene(scene);
        stage.show();
    }
}
