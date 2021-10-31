/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConta;
import db.Models.Conta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaAddDespesaController implements Initializable {
    
    @FXML
    private JFXTextField txdescricao;
    @FXML
    private JFXDatePicker dtvencimento;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXButton btconfirmar;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMasks();
        btconfirmar.setTooltip(new Tooltip("Confirmar"));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.maxField(txdescricao, 17);
        MaskFieldUtil.maxField(txvalor, 10);
        MaskFieldUtil.monetaryField(txvalor);
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        txdescricao.setFocusColor(null);
        txdescricao.setUnFocusColor(null);
        txvalor.setFocusColor(null);
        txvalor.setUnFocusColor(null);
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txdescricao.getText().isEmpty()) 
        {
            flag = true;
            setCorAlert(txdescricao,"RED");
        }
        if(txvalor.getText().isEmpty())
        { 
            flag = true;
            setCorAlert(txvalor,"RED");
        }
        if(dtvencimento.getValue() == null)
        {
            flag = true;
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
            setNormalColor();
            Conta c = new Conta(dtvencimento.getValue(),
                    MaskFieldUtil.monetaryValueFromField(txvalor).doubleValue(),
                    txdescricao.getText());
            DAOConta dc = new DAOConta();
            
            if(dc.gerarDespesa(c))
            {      
                JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Conta Gerada com Sucesso!")));
                Stage stage = (Stage) btconfirmar.getScene().getWindow();
                stage.close();
            }
            else
            { 
                a.setAlertType(Alert.AlertType.ERROR);
                a.getButtonTypes().clear();
                a.getButtonTypes().add(ButtonType.OK);
                a.setHeaderText("ERRO");
                a.setTitle("ERRO!");
                a.setContentText("Erro ao Gerar Conta!");
                a.showAndWait();
            }
        }
    }
    
}
