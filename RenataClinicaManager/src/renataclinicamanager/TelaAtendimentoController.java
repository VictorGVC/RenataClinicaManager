/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOAgendamento;
import db.DAL.DAOConfig;
import db.DAL.DAOFuncionario;
import db.DAL.DAOMaterial;
import db.Models.Atendimento;
import db.Models.Funcionario;
import db.Models.Material;
import db.Models.PacienteTratamento;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaAtendimentoController implements Initializable {

    public static Atendimento ate;
    public static boolean success;
    private SimpleDateFormat df1,df2;
    private int dentes[];
    
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
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXButton btaddproduto;
    @FXML
    private JFXButton btconfirmar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //se j?? for atendimento
        dentes = new int[32];
        setDateFormat();
        try {
            carregaDentistas("UPPER(fun_nome) LIKE '%%' AND fun_crm != ''");
            setLabels();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        if(ate.getDentista() != null)
            initAteAtendido();
        
        carregaMateriais("UPPER(mat_nome) LIKE '%%'");
        success = false;
        setMasks();
        initTable();
        setToolTip();
    }    
    
    private void setToolTip()
    {
        btaddproduto.setTooltip(new Tooltip("Adicionar Produto"));
        btconfirmar.setTooltip(new Tooltip("Confirmar"));
    }
    
    private void selecionaDentista(Funcionario c)
    {
        List<Funcionario> aux = cbfuncionario.getItems();
        boolean b = true;
        int index = -1;
        
        for (int i = 0; i < aux.size(); i++) 
        {
            if(c.getLogin().equals(aux.get(i).getLogin()))
            {
                b = true;
                index = i;
            }
        }
        
        cbfuncionario.getSelectionModel().select(index);
    }
    
    private void initAteAtendido()
    {
        initDentes();
        selecionaDentista(ate.getDentista());
        txobservacoes.setText(ate.getObservacoes());
        tvitensutilizados.setItems(FXCollections.observableArrayList(ate.getItens()));
    }
    
    private void initDentes()
    {
        ObservableList <Node> componentes = pnprincipal.getChildren();
        System.out.println(ate.getDentes());
        int index = 0;
        for (int i = 1; i < ate.getDentes().length(); i++) 
        {
            if(ate.getDentes().charAt(i)=='0'||ate.getDentes().charAt(i)=='1')
            {
                if(ate.getDentes().charAt(i)=='1')
                    for(Node n : componentes) 
                    {
                        if (n instanceof JFXButton)
                            try {
                                if(Integer.parseInt(((JFXButton)n).getText()) == index+1)
                                    ((JFXButton)n).setOpacity(0);
                            } catch (Exception e) {
                            }
                    }
                dentes[index] = Character.getNumericValue(ate.getDentes().charAt(i));
                index++;
            }
        }
        
    }
    
    private void setDateFormat()
    {
        df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2 = new SimpleDateFormat("dd/MM        HH:mm");
    }
    
    private void setLabels() throws ParseException
    {
        lbhorario.setText(""+df2.format(df1.parse(ate.getHorario().toString())));
        lbpaciente.setText(ate.getPt().getPaciente().getNome());
        lbtratamento.setText(ate.getPt().getTratamento().getNome());
    }
    
    private void initTable()
    {
        colproduto.setCellValueFactory(new PropertyValueFactory("nome"));
        colqtde.setCellValueFactory(new PropertyValueFactory("qtde"));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.numericField(txquantidade);
        MaskFieldUtil.maxField(txquantidade,4);
        MaskFieldUtil.maxField(cbfuncionario.getEditor(), 50);
        MaskFieldUtil.maxField(cbitens.getEditor(), 30);
        MaskFieldUtil.numericField(txquantidade);
    }

    public static void setAtendimento(Atendimento t)
    {
        ate = t;
    }
    
    public static boolean getSuccess()
    {
        return success;
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
    
    private void setCorAlert(JFXComboBox tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        cbfuncionario.setFocusColor(null);
        cbfuncionario.setUnFocusColor(null);
    }
    
    @FXML
    private void clkBtAddUsado(ActionEvent event) 
    {
        if(cbitens.getSelectionModel().getSelectedIndex() != -1 &&
                !txquantidade.getText().isEmpty() && 
                Double.parseDouble(txquantidade.getText()) > 0)
        {
            boolean flag = true;
            Material m = new Material(cbitens.getItems().get(cbitens.getSelectionModel().getSelectedIndex()).getId()
                    ,Integer.parseInt(txquantidade.getText()), 
                cbitens.getItems().get(cbitens.getSelectionModel().getSelectedIndex()).getNome());
            for (Material produto : ate.getItens()) 
            {
                if(produto.getNome().equals(m.getNome()))
                {
                    flag = false;
                    produto.setQtde(produto.getQtde()+m.getQtde());
                }
            }
            if(flag)
                ate.getItens().add(m);
            tvitensutilizados.setItems(FXCollections.observableList(ate.getItens()));
            tvitensutilizados.refresh();
        }
    }

    @FXML
    private void clkBtRegistrarAt(ActionEvent event) throws SQLException, IOException 
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        setNormalColor();
        if(cbfuncionario.getSelectionModel().getSelectedIndex() == -1)
        {
            setCorAlert(cbfuncionario, "RED");
            a.setContentText("Selecione um dentista!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else
        {
            ate.setDentista(cbfuncionario.getItems().get(
                    cbfuncionario.getSelectionModel().getSelectedIndex()));
            ate.setDentes(Arrays.toString(dentes));
            ate.setObservacoes(txobservacoes.getText());
            
            DAOAgendamento da = new DAOAgendamento();
            String error = da.salvarAtendimento(ate);
            if(error.isEmpty())
            {
                Alert b = new Alert(Alert.AlertType.CONFIRMATION);
                b.setHeaderText("Recebimento");
                b.setTitle("Recebimento");
                b.setContentText("Gostaria de gerar uma conta a pagar para esse atendimento?");
                b.getButtonTypes().clear();
                b.getButtonTypes().add(ButtonType.NO);
                b.getButtonTypes().add(ButtonType.YES);
                if (b.showAndWait().get() == ButtonType.YES)
                {
                    TelaGerarContasReceberController.pt = ate.getPt();
                    Parent root = FXMLLoader.load(getClass().getResource("TelaGerarContasReceber.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setMaxWidth(804);
                    stage.setMaxHeight(628);
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
                    DAOConfig dc = new DAOConfig();

                    scene.getStylesheets().add(getClass().getResource(dc.getTema()).toExternalForm());
                    stage.setTitle("Adicionar Recebimento");
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                success = true;
                Stage stage = (Stage) txobservacoes.getScene().getWindow();
                stage.close();
            }
            else
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText(error);
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkSelectDente(ActionEvent event) 
    {
        Button button = (Button) event.getSource();
        if(button.getOpacity() == 0)
            button.setOpacity(0.29);
        else
            button.setOpacity(0);
        dentes[Integer.parseInt(button.getText())-1] = 1;
    }

    @FXML
    private void pesquisaDentista(KeyEvent event) throws SQLException 
    {
        carregaDentistas("UPPER(fun_nome) LIKE '%"+cbfuncionario.getEditor().getText().toUpperCase()+"%' AND fun_crm != ''");
    }

    @FXML
    private void pesquisaMaterial(KeyEvent event) 
    {
        carregaMateriais("UPPER(mat_nome) LIKE '%"+cbitens.getEditor().getText().toUpperCase()+"%'");
    }

    @FXML
    private void minusDente(ActionEvent event) 
    {
        for (int i = 0; i < ate.getItens().size(); i++) 
        {
            if(ate.getItens().get(i).getNome().equals(tvitensutilizados.getSelectionModel().getSelectedItem().getNome()))
                ate.getItens().remove(i);
        }
        tvitensutilizados.refresh();
    }
}
