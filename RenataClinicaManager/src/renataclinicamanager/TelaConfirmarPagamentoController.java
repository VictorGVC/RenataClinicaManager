package renataclinicamanager;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConta;
import db.Models.Conta;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class TelaConfirmarPagamentoController implements Initializable {

    static Conta con;
    static char conf;
    
    @FXML
    private JFXDatePicker dtpagamento;
    @FXML
    private JFXTextField txvalor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValues();
        conf = 'c';
    }    
    
    private void setValues()
    {
        txvalor.setText(String.format("%.2f", con.getValor()));
        dtpagamento.setValue(LocalDate.now());
    }
    
    static public void setConta(Conta c)
    {
        con = c;
    }
    
    static public char getConfirmado()
    {
        return conf;
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) 
    {
        Stage stage = (Stage) txvalor.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        con.setDtpagamento(dtpagamento.getValue());
        con.setValor(Double.parseDouble(txvalor.getText().replace(".", "").replace(',', '.')));
        DAOConta dc = new DAOConta();
        if(dc.pagar(con))
            conf = 'a';
        else
            conf = 'n';
            
        Stage stage = (Stage) txvalor.getScene().getWindow();
        stage.close();
    }
}
