/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import db.Models.Atendimento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaAgendamentoInicialController implements Initializable 
{
    public static Atendimento age;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCBPaciente();
    }    
    
    public static void setHorario(Atendimento a)
    {
        age = a;
    }
    
    private void carregaTBTratamento()
    {
        
    }
    
    private void initCBPaciente()
    {
        
    }
}
