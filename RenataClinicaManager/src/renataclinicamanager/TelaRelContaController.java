/*
 * The MIT License
 *
 * Copyright 2021 vicga.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import db.Banco.Banco;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaRelContaController implements Initializable {

    public static boolean contarp;
    
    @FXML
    private JFXDatePicker dtpini;
    @FXML
    private JFXDatePicker dtpfim;
    @FXML
    private JFXRadioButton rbrp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if(contarp)
            rbrp.setText("Recebidas");
        else
            rbrp.setText("Pagas");
    }    

    @FXML
    private void clkConfirmar(ActionEvent event) throws JRException 
    {
        if(dtpfim.getValue() != null && dtpini.getValue() != null)
        {
            if(contarp)
            {
                //recibimento
                if(rbrp.isSelected())
                {
                    //recebidas

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    Date dateini = Date.from(dtpini.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datefim = Date.from(dtpfim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    parameters.put("dataini", dateini);
                    parameters.put("datafim", datefim);

                    JasperPrint jp = JasperFillManager.fillReport("./reports/RelReceberR.jasper", parameters, Banco.getCon().getConnect());
                    JasperViewer.viewReport(jp,false); 
                }
                else
                {
                    //a receber

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    Date dateini = Date.from(dtpini.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datefim = Date.from(dtpfim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    parameters.put("dataini", dateini);
                    parameters.put("datafim", datefim);

                    JasperPrint jp = JasperFillManager.fillReport("./reports/RelReceber.jasper", parameters, Banco.getCon().getConnect());
                    JasperViewer.viewReport(jp,false); 
                }
            }
            else
            {
                //pagamento
                if(rbrp.isSelected())
                {
                    //pagas

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    Date dateini = Date.from(dtpini.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datefim = Date.from(dtpfim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    parameters.put("dataini", dateini);
                    parameters.put("datafim", datefim);

                    JasperPrint jp = JasperFillManager.fillReport("./reports/RelPagarP.jasper", parameters, Banco.getCon().getConnect());
                    JasperViewer.viewReport(jp,false); 
                }
                else
                {
                    //a pagar

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    Date dateini = Date.from(dtpini.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datefim = Date.from(dtpfim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    parameters.put("dataini", dateini);
                    parameters.put("datafim", datefim);

                    JasperPrint jp = JasperFillManager.fillReport("./reports/RelPagar.jasper", parameters, Banco.getCon().getConnect());
                    JasperViewer.viewReport(jp,false);  
                }
            }

            Stage stage = (Stage) dtpini.getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Atenção!");
            a.setHeaderText("Data");
            a.setContentText("é necessário indicar as datas do período");
        }
    }
    
}
