package renataclinicamanager;

import db.Models.Atendimento;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TelaPacienteInfoController implements Initializable {

    public static Atendimento age;
    private DateFormat df1,df2;
    
    @FXML
    private Label lbtranome;
    @FXML
    private Label lbtrapreco;
    @FXML
    private Label lbhr;
    @FXML
    private Label lbpacnome;
    @FXML
    private Label lbpactelefone;
    @FXML
    private Label lbbairro;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbnumero;
    @FXML
    private Label lbrea;
    @FXML
    private Label lbdia;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setAInfo();
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
    
    public static void setInfo(Atendimento a)
    {
        age = a;
    }
    
    private void setAInfo()
    {
        DateTimeFormatter form;
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lbdia.setText(""+age.getHorario().toLocalDateTime().toLocalDate().format(form));
        setTimePattern();
        lbhr.setText(timeParse(age.getHorario()));
        lbbairro.setText(age.getPt().getPaciente().getBairro());
        lbnumero.setText(""+age.getPt().getPaciente().getNumero());
        lbpacnome.setText(age.getPt().getPaciente().getNome());
        lbpactelefone.setText(age.getPt().getPaciente().getTelefone());
        lbrea.setText(age.getPt().getPaciente().getRea());
        lbrua.setText(age.getPt().getPaciente().getRua());
        lbtranome.setText(age.getPt().getTratamento().getNome());
        lbtrapreco.setText(""+age.getPt().getTratamento().getValor());
    }
}
