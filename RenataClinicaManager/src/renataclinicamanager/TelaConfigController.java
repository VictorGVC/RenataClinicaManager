/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setThemeDark(ActionEvent event) 
    {
        btlight.setPrefWidth(71);
        btdark.setPrefWidth(141);
    }

    @FXML
    private void setThemeligth(ActionEvent event) 
    {
        btdark.setPrefWidth(71);
        btlight.setPrefWidth(141);
    }
    
}
