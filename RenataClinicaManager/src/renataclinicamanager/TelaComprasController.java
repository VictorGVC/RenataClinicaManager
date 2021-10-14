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
import db.DAL.DAOCompra;
import db.DAL.DAOFornecedor;
import db.DAL.DAOMaterial;
import db.Models.Compra;
import db.Models.Conta;
import db.Models.Fornecedor;
import db.Models.Material;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import util.MaskFieldUtil;

public class TelaComprasController implements Initializable 
{
    Compra comatual;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXDatePicker dtpInicio;
    @FXML
    private JFXTextField txtotal;
    @FXML
    private JFXComboBox<Fornecedor> cbfornecedor;
    @FXML
    private JFXComboBox<Material> cbproduto;
    @FXML
    private JFXTextField txquantidade;
    @FXML
    private JFXTextField txpreco;
    @FXML
    private TableView<Material> tvprodutos;
    @FXML
    private TableColumn<Material, String> colproduto;
    @FXML
    private TableColumn<Material, Double> colpreco;
    @FXML
    private TableColumn<Material, Integer> colquantidade;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private JFXDatePicker dtpfinal;
    @FXML
    private TableView<Compra> tvcompra;
    @FXML
    private TableColumn<Compra, Integer> colcod;
    @FXML
    private TableColumn<Compra, LocalDate> coldata;
    @FXML
    private TableColumn<Compra, String> colfornecedor;
    @FXML
    private TableColumn<Compra, Double> coltotal;
    @FXML
    private HBox pnfiltro;
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
    private JFXTextField txalocadoparcelas;
    @FXML
    private AnchorPane pnparcelas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        comatual = new Compra();
        
        setDate();
        setMascaras();
        initColTb();
        estado(true);
        carregaFornecedores("");
        carregaMateriais("");
    }    
    
    private void setDate()
    {
        dtpfinal.setValue(LocalDate.now());
        dtpInicio.setValue(LocalDate.now().minusMonths(1));
    }
    
    private void limparCampos() 
    {
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
        cbfornecedor.getSelectionModel().clearSelection();
        cbproduto.getSelectionModel().clearSelection();
    }
    
    private void setMascaras()
    {
        MaskFieldUtil.monetaryField(txtotal);
        MaskFieldUtil.numericField(txquantidade);
        MaskFieldUtil.maxField(txquantidade, 7);
        MaskFieldUtil.monetaryField(txpreco);
        MaskFieldUtil.maxField(txpreco, 9);
        MaskFieldUtil.monetaryField(txtotalparcelas);
        MaskFieldUtil.numericField(txqtdparcelas);
        MaskFieldUtil.monetaryField(txalocadoparcelas);
    }
    
    private void initColTb() 
    {
        colproduto.setCellValueFactory(new PropertyValueFactory("nome"));
        colquantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
        colpreco.setCellValueFactory(new PropertyValueFactory("valor"));
        colcod.setCellValueFactory(new PropertyValueFactory("id"));
        colfornecedor.setCellValueFactory((v) -> new SimpleStringProperty(""+v.getValue().getFornecedor().getNome()));
        coldata.setCellValueFactory(new PropertyValueFactory("dtcompra"));
        coltotal.setCellValueFactory(new PropertyValueFactory("total"));
        
        //Tabela de Parcelas
        colvalorparcela.setCellValueFactory(new PropertyValueFactory("vvalor"));
        coldtvencimentoparcela.setCellValueFactory(new PropertyValueFactory("vdtvencimento"));
        colparcela.setCellValueFactory(new PropertyValueFactory("numero"));
    }
    
    private void initEvents(Compra c)
    {
        List<Conta> list = c.getParcelas();
        
        for (Conta conta : list) 
        {
            JFXTextField jtx = conta.getVvalor();
            jtx.setOnKeyReleased((e)->
            {
                double total = Double.parseDouble(txtotal.getText().replace(".", "").replace(',', '.'));
                double soma = 0; 
                for (Conta parcela : comatual.getParcelas()) 
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
    
    private void estado(boolean b) 
    {
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btnovo.setDisable(!b);
        txfiltro.setDisable(!b);
        if(b)
            pnfiltro.setStyle(null);
        else
        {
            pnfiltro.setStyle("-fx-background-color: transparent;" +
                                "-fx-border-color: transparent;");
        }
        clkTFiltroT(null);
    }
    
    private void carregaTabela(String filtro) 
    {
        DAOCompra dal = new DAOCompra();
        List<Compra> res = dal.getList(filtro);
        ObservableList<Compra> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvcompra.setItems(modelo);
    }
    
    private void carregaFornecedores(String filtro)
    {
        DAOFornecedor dal = new DAOFornecedor();
        List<Fornecedor> res = dal.getList(filtro.toUpperCase());
        ObservableList<Fornecedor> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbfornecedor.setItems(modelo);
    }
    
    private void carregaMateriais(String filtro)
    {
        DAOMaterial dal = new DAOMaterial();
        List<Material> res = dal.getList(filtro.toUpperCase());
        ObservableList<Material> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        cbproduto.setItems(modelo);
    }
    
    private void setCorAlert(JFXComboBox tf, String cor) 
    {
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    private void atualizaTotal()
    {
        double soma = 0;
        for (Material produto : comatual.getProdutos()) 
            soma += produto.getValor()*produto.getQtde();
        txtotal.setText(String.format("%.2f", soma));
    }
    
    private void setNormalColor()
    {
        cbfornecedor.setFocusColor(null);
        cbfornecedor.setUnFocusColor(null);
    }

    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
        cbfornecedor.requestFocus();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(cbfornecedor.getSelectionModel().getSelectedIndex() == -1)
        {
            flag = true;
            setCorAlert(cbfornecedor, "RED");
        }
        else if(txtotal.getText().isEmpty() || Double.parseDouble(txtotal.getText().replace(".", "").replace(',', '.')) == 0)
            flag = true;
        if(flag)
        {
            a.setContentText("Campos obrigatórios não preenchidos ou material não inserido na lista!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else
        {
            txtotalparcelas.setText(txtotal.getText());
            txqtdparcelas.setText(""+1);
            dtpparcelas.setValue(LocalDate.now());
            atualizaParcelas(null);
            pnparcelas.setVisible(true);
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) 
    {
        if (!pndados.isDisabled())
        {
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkBtAddItem(ActionEvent event) 
    {
        boolean flag = true;
        Material m = new Material(Integer.parseInt(txquantidade.getText()), 
                Double.parseDouble(txpreco.getText().replace(".", "").replace(',', '.')), 
                ""+cbproduto.getValue());
        for (Material produto : comatual.getProdutos()) 
        {
            if(produto.getNome().equals(m.getNome()) && produto.getValor() == m.getValor())
            {
                flag = false;
                produto.setQtde(produto.getQtde()+m.getQtde());
            }
        }
        if(flag)
            comatual.getProdutos().add(m);
        tvprodutos.setItems(FXCollections.observableList(comatual.getProdutos()));
        tvprodutos.refresh();
        atualizaTotal();
    }

    @FXML
    private void clkBtRemoverItem(ActionEvent event) 
    {
        String nome;
        if(tvprodutos.getSelectionModel().getSelectedIndex() != -1)
        {
            nome = tvprodutos.getSelectionModel().getSelectedItem().getNome();
            for (int i = 0; i < comatual.getProdutos().size(); i++) 
            {
                if(comatual.getProdutos().get(i).getNome().equals(nome))
                    comatual.getProdutos().remove(i);
            }
        }
        tvprodutos.setItems(FXCollections.observableList(comatual.getProdutos()));
        atualizaTotal();
    }
    
    
    
    private void carregaTabelaProdutos(String filtro)
    {
        
    }

    private void clkTabela(MouseEvent event) 
    {
        if(tvcompra.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tvcompra.getSelectionModel().getSelectedItem() != null)
                comatual = tvcompra.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void clkTFiltroT(KeyEvent event) 
    {
        carregaTabela("INNER JOIN fornecedor f ON c.for_cnpj = f.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltro.getText().toUpperCase() + "%' AND c.com_data >='"+dtpInicio.getValue()+"' AND c.com_data <= '"+dtpfinal.getValue()+"'");
    }
    
    @FXML
    private void preencheFornecedores(KeyEvent event) 
    {
        carregaFornecedores("UPPER(for_nome) LIKE '%" + cbfornecedor.getValue() + "%'");
    }

    @FXML
    private void preencheMateriais(KeyEvent event) 
    {
        carregaMateriais("UPPER(mat_nome) LIKE '%" + cbproduto.getValue() + "%'");
    }

    @FXML
    private void clkTFiltro(ActionEvent event) 
    {
        carregaTabela("INNER JOIN fornecedor f ON c.for_cnpj = f.for_cnpj WHERE UPPER(f.for_nome) LIKE '%" 
                           + txfiltro.getText().toUpperCase() + "%' AND c.com_data >='"+dtpInicio.getValue()+"' AND c.com_data <= '"+dtpfinal.getValue()+"'");
    }

    @FXML
    private void clkBtConfirmaParcela(ActionEvent event) throws SQLException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DAOCompra dc = new DAOCompra();
        comatual.setFornecedor(cbfornecedor.getItems().get(cbfornecedor.getSelectionModel().getSelectedIndex()));
        comatual.setDtcompra(LocalDate.now());
        comatual.setTotal(Double.parseDouble(txtotal.getText().replace(".", "").replace(',', '.')));

        setNormalColor();
        String result = dc.gravar(comatual);

        if (result.isEmpty())
        {
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
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

    @FXML
    private void atualizaParcelas(KeyEvent event) 
    {
        try 
        {
            int qtde = Integer.parseInt(txqtdparcelas.getText());
            double total = Double.parseDouble(txtotal.getText().replace(".", "").replace(',', '.'));
            double valpar = Double.parseDouble(new DecimalFormat("#.##").format(total/qtde).replace(',', '.'))
                    ,soma = 0;

            comatual.setParcelas(new ArrayList<>());
            for (int i = 0; i < qtde; i++) 
            {
                comatual.getParcelas().add(new Conta(i, dtpparcelas.getValue().plusMonths(i), valpar));
                soma += valpar;
            }
            double dif = total-soma;
            comatual.getParcelas().get(0).setValor(comatual.getParcelas().get(0).getValor()+dif);
            comatual.getParcelas().get(0).getVvalor().setText(String.format("%.2f", comatual.getParcelas().get(0).getValor()));
            txalocadoparcelas.setText(String.format("%.2f", 0.00));
            initEvents(comatual);

            tvparcelas.setItems(FXCollections.observableArrayList(comatual.getParcelas()));
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
    private void clkTabelaCompra(MouseEvent event) 
    {
        if(tvcompra.getSelectionModel().getSelectedIndex() != -1)
            tvprodutos.setItems(FXCollections.observableList(tvcompra
                    .getSelectionModel().getSelectedItem().getProdutos()));
    }

    

}
