/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import db.DAL.DAOModelos;
import db.Models.Feriado;
import db.Models.Modelos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaReceituarioController implements Initializable 
{
    Feriado feratual;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private Pane pndados;
    @FXML
    private Label lbobg;
    @FXML
    private JFXButton btsalvar;
    @FXML
    private JFXTextArea tacabecalho;
    @FXML
    private JFXTextArea tarodape;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        carregaCampos();
    }
    
    private void carregaCampos()
    {
        DAOModelos dm = new DAOModelos();
        Modelos m = dm.getReceita();
        
        try {
            tacabecalho.setText(m.getCabecalho());
            tarodape.setText(m.getRodape());
        } catch (Exception e) {
        }
        
    }

    @FXML
    private void clkBtSalvar(ActionEvent event) 
    {
        String id;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tacabecalho.getText().isEmpty())
        {
            flag = true;
            setCorAlert(tacabecalho, "RED");
        }
        if(tarodape.getText().isEmpty())
        {
            flag = true;
            setCorAlert(tarodape, "RED");
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
            Modelos md = new Modelos(tarodape.getText(), tacabecalho.getText());
            DAOModelos dm = new DAOModelos();

            setNormalColor();
            
            if(dm.salvarCRR(md))
            {
                JFXSnackbar sb = new JFXSnackbar(pndados); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
            }
            else
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Não foi possível salvar!");
                a.showAndWait();
            }
        }
    }
    private void setNormalColor()
    {
        tacabecalho.setFocusColor(null);
        tarodape.setUnFocusColor(null);
    }
    
    private void setCorAlert(JFXTextArea tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
}