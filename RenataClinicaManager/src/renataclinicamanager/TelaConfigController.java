/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConfig;
import db.Models.Config;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaConfigController implements Initializable {

    @FXML
    private JFXButton btdark;
    @FXML
    private JFXButton btlight;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txendereco;
    @FXML
    private JFXTextField txcidade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initConfig();
    }    
    
    private void initConfig()
    {
        DAOConfig dc = new DAOConfig();
        Config c = dc.get();
        if(c.getTema().contains("Dark"))
            buttonAnimation(btdark, btlight);
        else
            buttonAnimation(btlight, btdark);
        txcidade.setText(c.getCidade());
        txendereco.setText(c.getEndereco());
        txnome.setText(c.getNome());
    }
    
    private void buttonAnimation(JFXButton buttonp,JFXButton buttonm)
    {
        DAOConfig dc = new DAOConfig();
        buttonm.setPrefWidth(71);
        
        final Animation animation;
        animation = new Transition() 
        {
            {
                setCycleDuration(Duration.seconds(0.1));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) 
            {
                buttonp.setPrefWidth(buttonp.getWidth()+20);
                buttonm.getScene().getStylesheets().clear();
                buttonm.getScene().getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
            }
        };
        animation.play();
    }

    @FXML
    private void setThemeDark(ActionEvent event) 
    {
        DAOConfig df = new DAOConfig();
        df.AlterarTema(true);
        buttonAnimation(btdark,btlight);
    }

    @FXML
    private void setThemeligth(ActionEvent event) 
    {
        DAOConfig dc = new DAOConfig();
        dc.AlterarTema(false);
        buttonAnimation(btlight,btdark);
    }

    @FXML
    private void clkSalvaConfig(ActionEvent event) 
    {
        Config c = new Config(txnome.getText(), txendereco.getText(), txcidade.getText());
        DAOConfig df = new DAOConfig();
        df.Salvar(c);
    }
}
