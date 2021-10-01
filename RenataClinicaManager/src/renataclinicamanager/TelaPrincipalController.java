/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import db.DAL.DAOAgendamento;
import db.Models.Atendimento;
import db.Models.Funcionario;
import db.Models.PacienteTratamento;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    DateTimeFormatter form;
    
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
    private Label lbnome;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbcep;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        initDateFormat();
        setTimePattern();
        setAcessoSessao();
        initColTB();
        initTables();
    }  
    
    private void initDateFormat() 
    {
        form = DateTimeFormatter.ofPattern("dd/MM");
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
    
    private void initColTB()
    {
        colhrseg.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomeseg.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colhrter.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnometer.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colhrqua.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomequa.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colhrqui.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomequi.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colhrsex.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomesex.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
        colhrsab.setCellValueFactory((v) -> new SimpleStringProperty(timeParse(v.getValue().getHorario())));
        colnomesab.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getPt().getPaciente().getNome()));
    }
    
    private void initTables()
    {
        DAOAgendamento da = new DAOAgendamento();
        int day = dia.getDayOfWeek().getValue()-1;
        
        LocalDate seg = dia.minusDays(day);
        LocalDate ter = dia.minusDays(day-1);
        LocalDate qua = dia.minusDays(day-2);
        LocalDate qui = dia.minusDays(day-3);
        LocalDate sex = dia.minusDays(day-4);
        LocalDate sab = dia.minusDays(day-5);
        
        //INIT LABELS
        lbseg.setText(seg.format(form));
        lbter.setText(ter.format(form));
        lbqua.setText(qua.format(form));
        lbqui.setText(qui.format(form));
        lbsex.setText(sex.format(form));
        lbsab.setText(sab.format(form));
        
        List <Atendimento> agendaseg = geraLista(seg);
        List <Atendimento> agendater = geraLista(ter);
        List <Atendimento> agendaqua = geraLista(qua);
        List <Atendimento> agendaqui = geraLista(qui);
        List <Atendimento> agendasex = geraLista(sex);
        List <Atendimento> agendasab = geraLista(sab);
        
        agendaseg = getAgenda(seg, agendaseg);
        agendater = getAgenda(ter, agendater);
        agendaqua = getAgenda(qua, agendaqua);
        agendaqui = getAgenda(qui, agendaqui);
        agendasex = getAgenda(sex, agendasex);
        agendasab = getAgenda(sab, agendasab);
        
        tvseg.setItems(FXCollections.observableArrayList(agendaseg));
        tvter.setItems(FXCollections.observableArrayList(agendater));
        tvqua.setItems(FXCollections.observableArrayList(agendaqua));
        tvqui.setItems(FXCollections.observableArrayList(agendaqui));
        tvsex.setItems(FXCollections.observableArrayList(agendasex));
        tvsab.setItems(FXCollections.observableArrayList(agendasab));
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

    @FXML
    private void clkChamaLogin(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage) mnbar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        Scene scene = new Scene(root);
        
        stage.close();
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(850);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Fornecedores");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkBackup(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkRestore(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkFuncionarios(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        stage.setMaxHeight(678);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(800);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(544);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(544);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Feriados");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenAtestado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAtestado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(804);
        stage.setMaxHeight(638);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(876);
        stage.setMaxHeight(589);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(917);
        stage.setMaxHeight(643);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(804);
        stage.setMaxHeight(610);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
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

        stage.resizableProperty().setValue(Boolean.FALSE);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Agendamento");
        stage.setScene(scene);
        stage.showAndWait();
        initTables();
    }

    @FXML
    private void clkAtendimento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAtendimento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Atendimento");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkAgendar(ActionEvent event) 
    {
        
        
        initTables();
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
        if(!sat.getPt().getPaciente().getNome().isEmpty())
        {
            TelaPacienteInfoController controller = new TelaPacienteInfoController();
            controller.setInfo(sat);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPacienteInfo.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Alerta");
            a.setTitle("Alerta!");
            a.setContentText("Nenhum Paciente agendado nesse horário!");
            a.showAndWait();
        }
    }

    @FXML
    private void clkSelect(MouseEvent event) 
    {
        if (tvqua.getScene().focusOwnerProperty().get() instanceof TableView) 
        {
            TableView ftv = (TableView) tvqua.getScene().focusOwnerProperty().get();
            int index = ftv.getSelectionModel().getSelectedIndex();
            sat = (Atendimento)ftv.getSelectionModel().getSelectedItem();
            
            limpaSelecao(ftv, index);
        }
    }
}
