/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOFuncionario;
import db.DAL.DAOMaterial;
import db.Models.Atendimento;
import db.Models.Funcionario;
import db.Models.Material;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaAtendimentoController implements Initializable {

    @FXML
    private JFXComboBox<Funcionario> cbfuncionario;
    @FXML
    private JFXTextArea txobservacoes;
    @FXML
    private Label lbtratamento;
    @FXML
    private Label lbpaciente;
    @FXML
    private Label lbhorario;
    @FXML
    private JFXComboBox<Material> cbitens;
    @FXML
    private JFXTextField txquantidade;
    @FXML
    private TableView<Material> tvitensutilizados;
    @FXML
    private TableColumn<Material, String> colproduto;
    @FXML
    private TableColumn<Material, Integer> colqtde;

    public static Atendimento ate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregaDentistas("UPPER(fun_nome) LIKE '%%'");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        carregaMateriais("UPPER(mat_nome) LIKE '%%'");
    }    

    public static void setAtendimento(Atendimento t)
    {
        ate = t;
    }
    
    private void carregaDentistas(String filtro) throws SQLException
    {
        DAOFuncionario dal = new DAOFuncionario();
        List<Funcionario> res = dal.getList(filtro);
        ObservableList<Funcionario> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbfuncionario.setItems(modelo);
    }
    
    private void carregaMateriais(String filtro)
    {
        DAOMaterial dal = new DAOMaterial();
        List<Material> res = dal.getList(filtro);
        ObservableList<Material> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbitens.setItems(modelo);
    }
    
    @FXML
    private void clkBtAddUsado(ActionEvent event) {
    }

    @FXML
    private void clkBtRegistrarAt(ActionEvent event) {
    }

    @FXML
    private void clkSelectDente(ActionEvent event) {
        Button button = (Button) event.getSource();
        if(button.getOpacity() == 0)
            button.setOpacity(90);
        else
            button.setOpacity(0);
    }

    @FXML
    private void pesquisaDentista(KeyEvent event) {
    }

    @FXML
    private void pesquisaMaterial(KeyEvent event) {
    }
    
}
