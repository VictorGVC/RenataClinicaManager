/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import db.Banco.Banco;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaRelAtestadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkBtEmiteAtestado(ActionEvent event) 
    {
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
                //parameters.put("content", tareceita.getText());

            JasperPrint jp = JasperFillManager.fillReport("./reports/RelAtestado.jrxml", parameters, Banco.getCon().getConnect());
            JasperViewer.viewReport(jp,false); 
            //Stage stage = (Stage) tareceita.getScene().getWindow();
            //stage.close();

        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
}
