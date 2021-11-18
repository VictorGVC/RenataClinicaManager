/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.Banco.Banco;
import db.DAL.DAOPaciente;
import db.Models.Paciente;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaRelAtestadoController implements Initializable {

    private DateTimeFormatter form;
    
    @FXML
    private JFXComboBox<String> cbjustificativa;
    @FXML
    private JFXTextField txjustificativa;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXTextField txprocedimento;
    @FXML
    private JFXTextField txrg;
    @FXML
    private JFXTextField txperiodo;
    @FXML
    private JFXTextField txcid;
    @FXML
    private JFXComboBox<String> cbretorno;
    @FXML
    private JFXTextField txretorno;
    @FXML
    private JFXDatePicker dtpdia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        form = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        carregaCB();
    }    
    
    private void carregaCB()
    {
        List<String> jus = new ArrayList<>();
        jus.add("Justificativa de falta de trabalho");
        jus.add("Dispensa de atividades escolares");
        jus.add("Dispensa de atividades desportivas");
        jus.add("Dispensa de atividades judiciais");
        jus.add("Dispensa de atividades militares");
        jus.add("Outros");
        
        cbjustificativa.setItems(FXCollections.observableList(jus));
        
        carregaPacientes("UPPER(pac_nome) LIKE '%%'");
        
        List<String> ret = new ArrayList<>();
        ret.add("Retornar as atividades normais");
        ret.add("Permanecer em repouso por: ");
        ret.add("Outros: ");
        
        cbretorno.setItems(FXCollections.observableList(ret));
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro);
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }

    @FXML
    private void clkBtEmiteAtestado(ActionEvent event) 
    {
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            String justificativa;
            String interessado;
            String retorno;
            
            if(txjustificativa.getText().isEmpty())
                justificativa = cbjustificativa.getValue();
            else
                justificativa = txjustificativa.getText();
            
            Paciente p = cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex());
            
            if(txprocedimento.getText().isEmpty())
                interessado = p.getNome();
            else
                interessado = "representante legal "+txprocedimento.getText()+", que\n"+
                        p.getNome();
            
            if(txretorno.getText().isEmpty())
                retorno = cbretorno.getValue();
            else
                retorno = cbretorno.getValue() + txretorno.getText();
                
            parameters.put("justificativa", justificativa);
            parameters.put("interessado", interessado);
            parameters.put("rg", txrg.getText());
            parameters.put("rua",p.getRua());
            parameters.put("numero", ""+p.getNumero());
            parameters.put("bairro", p.getBairro());
            parameters.put("cidade",p.getCidade());
            parameters.put("estado", "SP");
            parameters.put("periodo", txperiodo.getText());
            parameters.put("dia", dtpdia.getValue().format(form));
            parameters.put("retorno", retorno);
            parameters.put("cid", txcid.getText());
            LocalDate ld = LocalDate.now();
            parameters.put("date", ""+ld.getDayOfMonth()+" de "+
                    ld.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())+" de "+
                    ld.getYear());

            JasperPrint jp = JasperFillManager.fillReport("./reports/RelAtestado.jasper", parameters, Banco.getCon().getConnect());
            JasperViewer.viewReport(jp,false); 

        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    @FXML
    private void clkFim(ActionEvent event) 
    {
        if(cbjustificativa.getValue().equals("Outros"))
            txjustificativa.setDisable(false);
        else
        {
            txjustificativa.clear();
            txjustificativa.setDisable(true);
        }  
    }

    @FXML
    private void clkRetorno(ActionEvent event) 
    {
        if(!cbretorno.getValue().equals("Retornar as atividades normais"))
            txretorno.setDisable(false);
        else
        {
            txretorno.clear();
            txretorno.setDisable(true);
        }
            
    }
    
}
