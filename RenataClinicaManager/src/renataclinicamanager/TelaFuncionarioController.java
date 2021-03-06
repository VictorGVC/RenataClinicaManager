/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOCargo;
import db.DAL.DAOFuncionario;
import db.Models.Cargo;
import db.Models.Funcionario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaFuncionarioController implements Initializable {

//    private List lseg;
//    private List lter;
//    private List lqua;
//    private List lqui;
//    private List lsex;
//    private List lsab;
    private Funcionario funatual;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btativdesativ;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXComboBox<String> cbsexo;
    @FXML
    private JFXDatePicker dpdatanasc;
    @FXML
    private JFXTextField txlogin;
    @FXML
    private JFXPasswordField txsenha;
    @FXML
    private JFXPasswordField txsenhan;
    @FXML
    private JFXComboBox<Cargo> cbCargo;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private TableColumn<Funcionario, String> collogin;
    @FXML
    private TableColumn<Funcionario, String> colnome;
    @FXML
    private TableColumn<Funcionario, String> coltelefone;
    @FXML
    private TableColumn<Funcionario, Boolean> colativo;
    @FXML
    private TableColumn<Funcionario, String> colcargo;
    @FXML
    private TableView<Funcionario> tvfuncionarios;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txtelefone;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private HBox pnfiltro;
    @FXML
    private JFXTextField txcrm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        funatual = new Funcionario();
        setMascaras();
        initColTb();
        initCB();
        //initListas();
        try {
            estado(true);
        } catch (SQLException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbcategoria.getSelectionModel().select(0);
    }    
    
    private void initColTb()
    {
        colcargo.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getC().getNome()));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        collogin.setCellValueFactory(new PropertyValueFactory("login"));
        colativo.setCellValueFactory(new PropertyValueFactory("ativo"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
    }
    
//    private void initListas()
//    {
//        lseg = new ArrayList<String>();
//        lter = new ArrayList<String>();
//        lqua = new ArrayList<String>();
//        lqui = new ArrayList<String>();
//        lsex = new ArrayList<String>();
//        lsab = new ArrayList<String>();
//        
//        List<String> hrarios = new ArrayList<String>();
//        hrarios.add("06:00 - 07:00");
//        hrarios.add("07:00 - 08:00");
//        hrarios.add("08:00 - 09:00");
//        hrarios.add("09:00 - 10:00");
//        hrarios.add("10:00 - 11:00");
//        hrarios.add("11:00 - 12:00");
//        hrarios.add("12:00 - 13:00");
//        hrarios.add("13:00 - 14:00");
//        hrarios.add("14:00 - 15:00");
//        hrarios.add("15:00 - 16:00");
//        hrarios.add("16:00 - 17:00");
//        hrarios.add("17:00 - 18:00");
//        hrarios.add("18:00 - 19:00");
//        
//        lvsegunda.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvsegunda.setItems(FXCollections.observableArrayList(hrarios));
//        lvterca.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvterca.setItems(FXCollections.observableArrayList(hrarios));
//        lvquarta.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvquarta.setItems(FXCollections.observableArrayList(hrarios));
//        lvquinta.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvquinta.setItems(FXCollections.observableArrayList(hrarios));
//        lvsexta.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvsexta.setItems(FXCollections.observableArrayList(hrarios));
//        lvsabado.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        lvsabado.setItems(FXCollections.observableArrayList(hrarios));
//    }
    
    private void selecionaCargo(Cargo c)
    {
        List<Cargo> aux = cbCargo.getItems();
        boolean b = true;
        int index = -1;
        
        for (int i = 0; i < aux.size(); i++) 
        {
            if(c.getCod() == aux.get(i).getCod())
            {
                b = true;
                index = i;
            }
        }
        
        cbCargo.getSelectionModel().select(index);
    }
    
//    private void setListsJson()
//    {
//        JSONArray aux = funatual.getHorarios();
//        JSONArray jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("segunda"));
//        lseg.clear();
//        lseg.addAll(jaux.toList());
//        
//        jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("terca"));
//        lter.clear();
//        lter.addAll(jaux.toList());
//        
//        jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("quarta"));
//        lqua.clear();
//        lqua.addAll(jaux.toList());
//        
//        jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("quinta"));
//        lqui.clear();
//        lqui.addAll(jaux.toList());
//        
//        jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("sexta"));
//        lsex.clear();
//        lsex.addAll(jaux.toList());
//        
//        jaux = new JSONArray(aux.getJSONObject(0).getJSONArray("sabado"));
//        lsab.clear();
//        lsab.addAll(jaux.toList());
//    }
//    
//    private JSONArray getJsonArray()
//    {
//        JSONArray full = new JSONArray();
//        JSONObject jo = new JSONObject();
//        jo.put("segunda", lseg);
//        jo.put("terca", lter);
//        jo.put("quarta", lqua);
//        jo.put("quinta", lqui);
//        jo.put("sexta", lsex);
//        jo.put("sabado", lsab);
//        
//        full.put(jo);
//        
//        return full;
//    }
    
    private void estado(boolean b) throws SQLException 
    {
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        btnovo.setDisable(!b);
        txfiltro.setDisable(!b);
        btativdesativ.setDisable(!b);
        if(b)
            pnfiltro.setStyle(null);
        else
        {
            pnfiltro.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent;");
        }
      
        carregaTabela("");
    }
    
    private void initCB()
    {
        DAOCargo dal = new DAOCargo();
        cbCargo.setItems(FXCollections.observableArrayList(
                dal.getList("")));
        
        List<String> list = new ArrayList();
        list.add("Masculino");
        list.add("Feminino");
        cbsexo.setItems(FXCollections.observableArrayList(list));
        
        list = new ArrayList();
        
        list.add("Usu??rio");
        list.add("Nome");
        list.add("Cargo");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
    }
    
    private void setMascaras() 
    {
        MaskFieldUtil.maxField(txnome, 40);
        MaskFieldUtil.foneField(txtelefone);
        MaskFieldUtil.maxField(txlogin, 30);
        MaskFieldUtil.maxField(txsenha, 20);
        MaskFieldUtil.maxField(txsenhan, 20);
        MaskFieldUtil.dateField(dpdatanasc.getEditor());
    }
    
    private boolean listContais(List l,String s)
    {
        boolean b = true;
        for (int i = 0; i < l.size(); i++) 
        {
            if(l.get(i).equals(s))
                b = false;
        }
        
        return !b;
    }
    
    private void selectListObjects(ListView<String> lv,List<String> l)
    {
        lv.getSelectionModel().clearSelection();
        for (String s : l) 
            lv.getSelectionModel().select(s);
    }
    
    private void limparCampos() 
    {
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        cbCargo.getSelectionModel().select(-1);
        cbsexo.getSelectionModel().select(-1);
        //setListNull();
        dpdatanasc.setValue(null);
        cbcategoria.getSelectionModel().select(0);
    }
    
//    private void setListNull()
//    {
//        lseg.clear();
//        lter.clear();
//        lqua.clear();
//        lqui.clear();
//        lsex.clear();
//        lsab.clear();
//    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        txnome.setFocusColor(null);
        txnome.setUnFocusColor(null);
        txtelefone.setFocusColor(null);
        txtelefone.setUnFocusColor(null);
        txlogin.setFocusColor(null);
        txlogin.setUnFocusColor(null);
        txsenha.setFocusColor(null);
        txsenha.setUnFocusColor(null);
    }
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: green;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    private void miniAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: red;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }

    @FXML
    private void clkBtNovo(ActionEvent event) throws SQLException 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        txnome.requestFocus();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) throws SQLException 
    {
        if(tvfuncionarios.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            pnpesquisa.setDisable(false);
            txnome.requestFocus();
            txsenhan.setVisible(true);
        }
        else
            miniAlert("Selecione um paciente!");
    }
    
    private void carregaTabela(String filtro) throws SQLException 
    {
        DAOFuncionario dal = new DAOFuncionario();
        List<Funcionario> res = dal.getList(filtro);
        ObservableList<Funcionario> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvfuncionarios.setItems(modelo);
    }

    @FXML
    private void clkBtApagar(ActionEvent event) throws SQLException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvfuncionarios.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Exclus??o!");
            a.setTitle("Exclus??o");
            a.setContentText("Confirma a exclus??o");
            a.getButtonTypes().clear();
            a.getButtonTypes().add(ButtonType.NO);
            a.getButtonTypes().add(ButtonType.YES);
            if (a.showAndWait().get() == ButtonType.YES){
                
                DAOFuncionario dal = new DAOFuncionario();
                Funcionario f;
                f = tvfuncionarios.getSelectionModel().getSelectedItem();
                String result = dal.apagar(f);
                if(result.isEmpty())
                    miniGAlert("Excluido com sucesso!");
                else
                {    
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText(result);
                    a.showAndWait();
                }
                clkTFiltro(null);
                limparCampos();
            }
        }
        else{
            
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhum produto selecionado");
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtAtivar(ActionEvent event) throws SQLException 
    {
        if(tvfuncionarios.getSelectionModel().getSelectedIndex() != -1)
        {   
            DAOFuncionario dal = new DAOFuncionario();
            
            if(funatual.getAtivo() == 'S')
            {    
                String error;
                error = dal.desativar(tvfuncionarios.getSelectionModel().getSelectedItem());
                if(error.isEmpty())
                    miniGAlert("Desativado com sucesso!");
                else
                {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText(error);
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
            else
            {
                if(dal.ativar(tvfuncionarios.getSelectionModel().getSelectedItem()))
                    miniGAlert("Ativado com sucesso!");
            }
            clkTFiltro(null);
        }
        else
            miniAlert("Selecione algum funcion??rio!");
        clkTFiltro(null);
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws SQLException 
    {
        String id;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txnome.getText().isEmpty()) 
        {
            flag = true;
            setCorAlert(txnome,"RED");
        }
        if(txtelefone.getText().isEmpty())
        { 
            flag = true;
            setCorAlert(txtelefone,"RED");
        }
        if(txlogin.getText().isEmpty())
        {
            flag = true;
            setCorAlert(txlogin,"RED");
        }
        if(cbCargo.getSelectionModel().getSelectedIndex() == -1) 
        {   
            a.setContentText("Cargo deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else if(flag)
        {
            a.setContentText("Campos obrigat??rios n??o preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else 
        {
            char sexo = 'C';
            
            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';
            
            Funcionario f = new Funcionario(cbCargo.getValue(),txnome.getText(),
                    txlogin.getText(),txtelefone.getText(),
                    dpdatanasc.getValue(), 'S',sexo,txcrm.getText());
            DAOFuncionario dal = new DAOFuncionario();
            
            if(pnpesquisa.isDisable())
            {
                if(txsenha.getText().isEmpty())
                {
                    a.setContentText("Senha deve ser informada");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                    txsenha.requestFocus();
                }
                else
                {
                    setNormalColor();
                    String result = dal.gravar(f,Util.criptoSenha(txsenha.getText()));
                    
                    if (result.isEmpty())
                    {
                        miniGAlert("Salvo com sucesso!");
                        estado(true);
                        limparCampos();
                        pnpesquisa.setDisable(false);
                        clkTFiltro(null);
                    }
                    else
                    {
                        a.getButtonTypes().clear();
                        a.getButtonTypes().add(ButtonType.OK);
                        a.setContentText(result);
                        a.showAndWait();
                    }
                }
            }
            else
            {
                if(txsenha.getText().isEmpty() && (!txsenhan.getText().isEmpty()  
                        || !txlogin.getText().equals(funatual.getLogin())))
                {
                    a.getButtonTypes().clear();
                    a.getButtonTypes().add(ButtonType.OK);
                    a.setContentText("?? preciso fornecer a senha atual para "
                            + "alterar o login ou senha a senha!");
                    a.showAndWait();
                }
                else
                {
                    String result = dal.alterar(f,Util.criptoSenha(txsenhan.getText()),Util.criptoSenha(txsenha.getText()),funatual.getLogin());
                    
                    if (result.isEmpty())
                    {
                        miniGAlert("Alterado com sucesso!");
                        estado(true);
                        limparCampos();
                        pnpesquisa.setDisable(false);
                        clkTFiltro(null);
                        txsenhan.setVisible(false);
                    }
                    else
                    {
                        a.getButtonTypes().clear();
                        a.getButtonTypes().add(ButtonType.OK);
                        a.setContentText(result);
                        a.showAndWait();
                    }
                }
            }
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) throws SQLException 
    {
        if (!pndados.isDisabled())
        {
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tvfuncionarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvfuncionarios.getSelectionModel().getSelectedItem() != null)
            {
                Funcionario f = (Funcionario)tvfuncionarios.getSelectionModel().getSelectedItem();
                
                txnome.setText(f.getNome());
                if(f.getSexo() == 'M')
                    cbsexo.getSelectionModel().selectFirst();
                else if(f.getSexo() == 'F')
                    cbsexo.getSelectionModel().selectLast();
                else
                    cbsexo.getSelectionModel().select(-1);
                txtelefone.setText(f.getTelefone());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                funatual = f;
                txlogin.setText(f.getLogin());
                selecionaCargo(f.getC());
                txcrm.setText(f.getCrm());
                dpdatanasc.setValue(f.getDtnasc());
                //setListsJson();
            }
        }
    }

    @FXML
    private void clkTFiltro(ActionEvent event) throws SQLException 
    {
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1)
        {
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) 
            {
                case 0:
                   carregaTabela("UPPER(fun_login) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 1:
                    carregaTabela("UPPER(fun_nome) LIKE '%" + txfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabela("UPPER(fun_cargo) LIKE '%" + txfiltro.getText() + "%'");
                    break;
            }
        }
    }
}
