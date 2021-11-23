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

import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConta;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaLucroAnualController implements Initializable {

    @FXML
    private BarChart<String, Double> bclucro;
    @FXML
    private BarChart<String, Double> bcgg;
    @FXML
    private JFXTextField txano;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MaskFieldUtil.numericField(txano);
        MaskFieldUtil.maxField(txano, 4);
        
        txano.setText(""+LocalDate.now().getYear());
        try {
            calcularLucro(null);
        } catch (SQLException ex) {
            Logger.getLogger(TelaLucroAnualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void calcularLucro(ActionEvent event) throws SQLException 
    {
        bcgg.getData().clear();
        bclucro.getData().clear();
        if(txano.getText().length() == 4)
        {
            DAOConta dc = new DAOConta();
            
            List<String> meses = new ArrayList<>();
            meses.add("Jan");
            meses.add("Fev");
            meses.add("Mar");
            meses.add("Abr");
            meses.add("Mai");
            meses.add("Jun");
            meses.add("Jul");
            meses.add("Ago");
            meses.add("Set");
            meses.add("Out");
            meses.add("Nov");
            meses.add("Dez");
            
            String ano = txano.getText();
            
            List<Double> gastos = dc.getGastos(ano);
            List<Double> ganhos = dc.getGanhos(ano);
            
            XYChart.Series<String, Double> gastosxy = new XYChart.Series<>();
            gastosxy.setName("Gastos");
            XYChart.Series<String, Double> ganhosxy = new XYChart.Series<>();
            ganhosxy.setName("Ganhos");
            XYChart.Series<String, Double> lucrosxy = new XYChart.Series<>();
            lucrosxy.setName("Lucro/Prejuízo");
            
            for (int i = 0; i < 12; i++) 
            {
                gastosxy.getData().add(new XYChart.Data<>(meses.get(i), gastos.get(i)));
                ganhosxy.getData().add(new XYChart.Data<>(meses.get(i), ganhos.get(i)));
                lucrosxy.getData().add(new XYChart.Data<>(meses.get(i), ganhos.get(i)-gastos.get(i)));
            }
            
            bcgg.getData().addAll(ganhosxy,gastosxy);
            bclucro.getData().add(lucrosxy);
        }
    }
    
}
