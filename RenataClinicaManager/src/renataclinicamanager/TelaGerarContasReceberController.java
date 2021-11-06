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
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaGerarContasReceberController implements Initializable {

    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXDatePicker dtvencimento;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXComboBox<PacienteTratamento> cbtratamento;
    @FXML
    private JFXTextField txcpf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMasks();
        btconfirmar.setTooltip(new Tooltip("Confirmar"));
    }    
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
        Label l = new Label();

        l.setText(txt);
        l.setPrefSize(170, 10);
        l.setStyle("-fx-background-color: green;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.cpfField(txcpf);
        MaskFieldUtil.dateField(dtvencimento.getEditor());
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
        txcpf.setFocusColor(null);
        txcpf.setUnFocusColor(null);
        cbpaciente.setFocusColor(null);
        cbpaciente.setUnFocusColor(null);
        cbtratamento.setFocusColor(null);
        cbtratamento.setUnFocusColor(null);
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkSelecionaPaciente(ActionEvent event) {
    }

    @FXML
    private void clkCarregaPaciente(KeyEvent event) {
    }

    @FXML
    private void clkSelecionaTratamento(ActionEvent event) {
    }

    @FXML
    private void clkPreencheTratamento(KeyEvent event) {
    }

    @FXML
    private void clkSelecionaPaciente(KeyEvent event) {
    }
}
