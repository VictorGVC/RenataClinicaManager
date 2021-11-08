/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOConta;
import db.DAL.DAOPaciente;
import db.DAL.DAOTratamento;
import db.Models.Conta;
import db.Models.Funcionario;
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import util.MaskFieldUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaGerarContasReceberController implements Initializable {

    public static PacienteTratamento pt;
    
    private List<Conta> recatual;
    
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXTextField txvalor;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXComboBox<Paciente> cbpaciente;
    @FXML
    private JFXComboBox<PacienteTratamento> cbtratamento;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private AnchorPane pnparcelas;
    @FXML
    private JFXTextField txqtdparcelas;
    @FXML
    private JFXDatePicker dtpparcelas;
    @FXML
    private TableView<Conta> tvparcelas;
    @FXML
    private TableColumn<Conta, Integer> colparcela;
    @FXML
    private TableColumn<Conta, JFXDatePicker> coldtvencimentoparcela;
    @FXML
    private TableColumn<Conta, JFXTextField> colvalorparcela;
    @FXML
    private JFXTextField txtotalparcelas;
    @FXML
    private JFXButton btconfirmarparcelas;
    @FXML
    private JFXTextField txalocadoparcelas;
    @FXML
    private JFXButton btfechar;
    @FXML
    private JFXButton btdefault;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMasks();
        initCBPaciente();
        setPT();
        btconfirmar.setTooltip(new Tooltip("Confirmar"));
        txvalor.setAlignment(Pos.CENTER);
        initColTable();
    }   
    
    private void setPT()
    {
        if(pt != null)
        {
            List<Paciente> aux = cbpaciente.getItems();
            boolean b = true;
            int index = -1;

            for (int i = 0; i < aux.size() && b; i++) 
            {
                if(pt.getPaciente().getCpf().equals(aux.get(i).getCpf()))
                {
                    b = false;
                    index = i;
                }
            }

            cbpaciente.getSelectionModel().select(index);
            carregaCBTratamento();
            clkSelecionaPacientec(null);
            
            List<PacienteTratamento> aux1 = cbtratamento.getItems();
            b = true;
            index = -1;

            for (int i = 0; i < aux1.size() && b; i++) 
            {
                if(pt.getCodigo() == aux1.get(i).getCodigo())
                {
                    b = false;
                    index = i;
                }
            }

            cbtratamento.getSelectionModel().select(index);
            clkSelecionaTratamento(null);
        }
    }
    
    private void initColTable()
    {
        colvalorparcela.setCellValueFactory(new PropertyValueFactory("vvalor"));
        coldtvencimentoparcela.setCellValueFactory(new PropertyValueFactory("vdtvencimento"));
        colparcela.setCellValueFactory(new PropertyValueFactory("numero"));
    }
    
    private void carregaPacientes(String filtro)
    {
        DAOPaciente dal = new DAOPaciente();
        List<Paciente> res = dal.getList(filtro.toUpperCase());
        ObservableList<Paciente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbpaciente.setItems(modelo);
    }
    
    private void initCBPaciente()
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%%'");
    }
    
    private void miniGAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
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
        JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
        Label l = new Label();

        l.setText(txt);
        l.setPadding(new Insets(0,15,0,15));
        l.setStyle("-fx-background-color: red;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }
    
    private void setMasks()
    {
        MaskFieldUtil.cpfField(txcpf);
        MaskFieldUtil.maxField(txvalor, 10);
        MaskFieldUtil.monetaryField(txvalor);
    }
    
    private void setCorAlert(JFXComboBox tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setCorAlert(JFXTextField tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void setNormalColor()
    {
        txcpf.setFocusColor(null);
        txcpf.setUnFocusColor(null);
        cbpaciente.setFocusColor(null);
        cbpaciente.setUnFocusColor(null);
        cbtratamento.setFocusColor(null);
        cbtratamento.setUnFocusColor(null);
    }
    
    private void carregaCBTratamento()
    {
        DAOTratamento dt = new DAOTratamento();
        List<PacienteTratamento> res = dt.getPTList("INNER JOIN tratamento t "
                + "ON t.tra_cod = pt.tra_cod WHERE pt.pac_cpf = '"+cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf()+"' "+
                " AND UPPER(t.tra_nome) LIKE '%" +cbtratamento.getEditor().getText().toUpperCase()+ "%' AND pt.pt_ativo = 'S'");
        ObservableList<PacienteTratamento> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbtratamento.setItems(modelo);
    }
    
    private void initEvents(List<Conta> list)
    {       
        for (Conta conta : list) 
        {
            JFXTextField jtx = conta.getVvalor();
            jtx.setOnKeyReleased((e)->
            {
                double total = MaskFieldUtil.monetaryValueFromField(txvalor).doubleValue();
                double soma = 0; 
                for (Conta parcela : list) 
                {
                    if(parcela.getVvalor().getText().isEmpty())
                        parcela.setValor(0);
                    else
                    {
                        parcela.setValor(Double.parseDouble(parcela.getVvalor()
                            .getText().replace(".", "").replace(',', '.')));
                        soma += parcela.getValor();
                    }
                }
                double dif = total-soma;
                if(dif<0)
                    txalocadoparcelas.setUnFocusColor(Paint.valueOf("RED"));
                else
                    txalocadoparcelas.setUnFocusColor(Paint.valueOf("#8fcfcf"));
                txalocadoparcelas.setText(String.format("%.2f", dif));
            });
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(!Util.isCpf(txcpf.getText()))
        {
            flag = true;
            setCorAlert(txcpf, "RED");
        }
        if(cbpaciente.getSelectionModel().getSelectedIndex() == -1)
        {
            flag = true;
            setCorAlert(cbpaciente, "RED");
        }
        if(cbtratamento.getSelectionModel().getSelectedIndex() == -1)
        {
            flag = true;
            setCorAlert(cbtratamento, "RED");
        }
        if(txvalor.getText().isEmpty() || MaskFieldUtil.monetaryValueFromField(txvalor).doubleValue() == 0)
            flag = true;
        if(flag)
        {
            a.setContentText("Campos obrigatórios não preenchidos");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else
        {
            txtotalparcelas.setText(txvalor.getText());
            txqtdparcelas.setText(""+1);
            dtpparcelas.setValue(LocalDate.now());
            atualizaParcelas(null);
            pnparcelas.setVisible(true);
        }
    }

    @FXML
    private void clkSelecionaPacientec(ActionEvent event) 
    {
        if(cbpaciente.getSelectionModel().getSelectedIndex()>=0)
        {
            txcpf.setText(
                    cbpaciente.getItems().get(cbpaciente.getSelectionModel().getSelectedIndex()).getCpf());
        
            txvalor.setText("");
            carregaCBTratamento();
        }
    }

    @FXML
    private void clkCarregaPaciente(KeyEvent event) 
    {
        carregaPacientes("UPPER(pac_nome) LIKE '%" + cbpaciente.getValue() + "%'");
    }

    @FXML
    private void clkSelecionaTratamento(ActionEvent event) 
    {
        if(cbtratamento.getSelectionModel().getSelectedIndex()>=0)
            txvalor.setText(String.format("%.2f",cbtratamento.getItems().get(
                    cbtratamento.getSelectionModel().getSelectedIndex()).getTratamento().getValor()));
    }

    @FXML
    private void clkPreencheTratamento(KeyEvent event) 
    {
        carregaCBTratamento();
    }

    @FXML
    private void clkSelecionaPaciente(KeyEvent event) 
    {
        setCorAlert(txcpf, "BLACK");
        if(txcpf.getText().length() >= 14)
        {
            if(!util.Util.isCpf(txcpf.getText()))
            {
                setCorAlert(txcpf, "RED");
                miniAlert("CPF invalido!");
            }
            else
            {
                DAOPaciente dp = new DAOPaciente();
                cbpaciente.setValue(dp.get(txcpf.getText()));
            }
        }
    }

    @FXML
    private void atualizaParcelas(KeyEvent event) 
    {
        try 
        {
            int qtde = Integer.parseInt(txqtdparcelas.getText());
            double total = MaskFieldUtil.monetaryValueFromField(txvalor).doubleValue();
            double valpar = Double.parseDouble(new DecimalFormat("#.##").format(total/qtde).replace(',', '.'))
                    ,soma = 0;

            recatual = new ArrayList<>();
            for (int i = 0; i < qtde; i++) 
            {
                recatual.add(new Conta(i, dtpparcelas.getValue().plusMonths(i), valpar));
                soma += valpar;
            }
            double dif = total-soma;
            recatual.get(0).setValor(recatual.get(0).getValor()+dif);
            recatual.get(0).getVvalor().setText(String.format("%.2f", recatual.get(0).getValor()));
            txalocadoparcelas.setText(String.format("%.2f", 0.00));
            initEvents(recatual);

            tvparcelas.setItems(FXCollections.observableArrayList(recatual));
            tvparcelas.refresh();
        } 
        catch (Exception e) {}
    }

    @FXML
    private void atualizaParcelasD(ActionEvent event) 
    {
        atualizaParcelas(null);
    }

    @FXML
    private void clkBtConfirmaParcela(ActionEvent event) throws SQLException 
    {
        if(cbtratamento.getSelectionModel().getSelectedIndex() != -1)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            DAOConta dc = new DAOConta();

            setNormalColor();
            
            if (dc.gravarRec(recatual,cbtratamento.getItems().get(cbtratamento.getSelectionModel().getSelectedIndex()).getCodigo()))
            {
                miniGAlert("Salvo com sucesso!");
                pnparcelas.setVisible(false);
                Stage stage = (Stage) btconfirmar.getScene().getWindow();
                stage.close();
            }
            else
            {
                a.getButtonTypes().clear();
                a.getButtonTypes().add(ButtonType.OK);
                a.setContentText("Erro ao salvar contas!");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkBtFecharParcelas(ActionEvent event) 
    {
        pnparcelas.setVisible(false);
    }

    @FXML
    private void clkBtResetParcelas(ActionEvent event) 
    {
        atualizaParcelas(null);
    }
}
