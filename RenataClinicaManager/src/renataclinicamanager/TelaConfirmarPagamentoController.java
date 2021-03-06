package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import util.MaskFieldUtil;

public class TelaConfirmarPagamentoController implements Initializable {

    static Conta con;
    static char conf;
    static boolean pr;
    
    @FXML
    private JFXDatePicker dtpagamento;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValues();
        conf = 'c';
        setMasks();
        setToolTip();
    }    
    
    private void setToolTip()
    {
        btconfirmar.setTooltip(new Tooltip("Confirmar"));
        btcancelar.setTooltip(new Tooltip("Cancelar"));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.maxField(txvalor, 12);
        MaskFieldUtil.monetaryField(txvalor);
        MaskFieldUtil.dateField(dtpagamento.getEditor());
    }
    
    private void setValues()
    {
        txvalor.setText(String.format("%.2f", con.getValor()));
        dtpagamento.setValue(LocalDate.now());
    }
    
    static public void setContaP(Conta c)
    {
        con = c;
        pr = true;
    }
    
    static public void setContaR(Conta c)
    {
        con = c;
        pr = false;
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
        con.setValor(MaskFieldUtil.monetaryValueFromField(txvalor).doubleValue());
        DAOConta dc = new DAOConta();

        if(pr)
        {
            if(dc.pagar(con))
                conf = 'a';
            else
                conf = 'n';
        }
        else
        {
            if(dc.receber(con))
                conf = 'a';
            else
                conf = 'n';
        }

        Stage stage = (Stage) txvalor.getScene().getWindow();
        stage.close();
    }
}
